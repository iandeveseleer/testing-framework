package fr.iandeveseleer.testingframework.extensions;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTest;
import fr.iandeveseleer.testingframework.annotations.Page;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Slf4j
public class SeleniumExtension extends AbstractExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create("fr.iandeveseleer.testingframework");
    private static final String DRIVER = "driver";

    @Override
    public void beforeEach(ExtensionContext pTestExecutionContext) throws NoSuchFieldException {
        // Initialize Selenium mode
        boolean isDockerMode = Boolean.parseBoolean(environment.getProperty("testing.framework.selenium.docker-mode"));
        String gridUrl = environment.getProperty("testing.framework.selenium.grid.url");
        initializeWebDriver(pTestExecutionContext, isDockerMode, gridUrl);
    }

    private static void initializeWebDriver(ExtensionContext pTestExecutionContext, boolean pIsDockerMode, String pGridUrl) throws NoSuchFieldException {
        // Find browser annotation
        // Initialize driver based on test case information
        DesiredCapabilities capabilities = prepareCapabilities(pTestExecutionContext);
        WebDriver driver;
        if (Boolean.TRUE.equals(pIsDockerMode)) {
            // Not working for now due to : https://github.com/docker-java/docker-java/pull/2364
            LOGGER.info("Running in Docker mode for : {}", capabilities);
            WebDriverManager wdm = WebDriverManager
                    .getInstance(capabilities.getBrowserName())
                    .dockerCustomImage("selenium/standalone-" + capabilities.getBrowserName().toLowerCase())
                    .enableRecording()
                    .disableTracing()
                    .browserInDocker();
            driver = wdm.create();
        } else {
            LOGGER.info("Running in Grid mode for : {}", capabilities);
            driver = WebDriverManager.getInstance()
                    .remoteAddress(pGridUrl)
                    .enableRecording()
                    .disableTracing()
                    .capabilities(capabilities).create();
        }
        // Set driver in context and in driver field of test class
        Object testInstance = pTestExecutionContext.getRequiredTestInstance();
        if (testInstance instanceof AbstractSeleniumSystemTest) {
            injectDriverInTestClasses(testInstance, driver);
            initAndInjectPagesInTestClasses(testInstance, driver);
        }
        pTestExecutionContext.getStore(NAMESPACE).put(DRIVER, driver);
    }

    private static DesiredCapabilities prepareCapabilities(ExtensionContext pTestExecutionContext) {
        SystemTest systemTestAnnotation = AnnotationSupport.findAnnotation(pTestExecutionContext.getRequiredTestMethod(), SystemTest.class).get(); // NOSONAR : Protected by SystemTestExtension

        String desiredBrowser = systemTestAnnotation.browser().toLowerCase();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Default to CHROME
        capabilities.setBrowserName(StringUtils.isNotEmpty(desiredBrowser) ? desiredBrowser : "chrome");
        return capabilities;
    }

    private static void injectDriverInTestClasses(Object pTestExecutionInstance, WebDriver pWebDriver) throws NoSuchFieldException {
        Field field = AbstractSeleniumSystemTest.class.getDeclaredField(DRIVER);
        if(field != null) {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, pTestExecutionInstance, pWebDriver);
        }
    }

    private static void initAndInjectPagesInTestClasses(Object pTestExecutionInstance, WebDriver pWebDriver) {
        Class<?> clazz = pTestExecutionInstance.getClass();
        while (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Page.class)) {
                    try {
                        ReflectionUtils.makeAccessible(field);
                        Object pageInstance = field.getType().getConstructor(WebDriver.class).newInstance(pWebDriver);
                        PageFactory.initElements(pWebDriver, pageInstance);
                        ReflectionUtils.setField(field, pTestExecutionInstance, pageInstance);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to initialize @Page: " + field.getName(), e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    @Override
    public void afterEach(ExtensionContext pTestExecutionContext) {
        WebDriver driver = pTestExecutionContext.getStore(NAMESPACE).get(DRIVER, WebDriver.class);
        if (driver != null) {
            driver.quit();
        }
    }
}
