package fr.iandeveseleer.testingframework.extensions;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTests;
import fr.iandeveseleer.testingframework.annotations.Browser;
import fr.iandeveseleer.testingframework.annotations.Page;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.enums.BrowserType;
import fr.iandeveseleer.testingframework.selenium.pages.BaseElementDecorator;
import fr.iandeveseleer.testingframework.service.driver.DriverService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Slf4j
public class SeleniumExtension extends AbstractExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create("fr.iandeveseleer.testingframework");

    private final DriverService driverService = ApplicationContextProvider.getBean(DriverService.class);
    private static final String DRIVER = "driver";

    @Override
    public void beforeEach(ExtensionContext pTestExecutionContext) throws NoSuchFieldException {
        // Initialize Selenium mode
        String gridUrl = environment.getProperty("testing.framework.selenium.grid.url");
        initializeWebDriver(pTestExecutionContext, gridUrl);
    }

    private void initializeWebDriver(ExtensionContext pTestExecutionContext, String pGridUrl) throws NoSuchFieldException {
        // Find browser annotation
        // Initialize driver based on test case information
        DesiredCapabilities capabilities = prepareCapabilities(pTestExecutionContext);
        WebDriver driver = driverService.initializeWebDriver(capabilities, pGridUrl);
        // Set driver in context and in driver field of test class
        Object testInstance = pTestExecutionContext.getRequiredTestInstance();
        if (testInstance instanceof AbstractSeleniumSystemTests) {
            injectDriverInTestClasses(testInstance, driver);
            initAndInjectPagesInTestClasses(testInstance, driver);
        }
        pTestExecutionContext.getStore(NAMESPACE).put(DRIVER, driver);
    }

    private DesiredCapabilities prepareCapabilities(ExtensionContext pTestExecutionContext) {
        Browser browserAnnotation = AnnotationSupport.findAnnotation(pTestExecutionContext.getRequiredTestMethod(), Browser.class).get(); // NOSONAR : Protected by SystemTestExtension

        BrowserType desiredBrowser = browserAnnotation.value();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Default to CHROME
        capabilities.setBrowserName(desiredBrowser != null ? desiredBrowser.getCapabilityName() : "chrome");
        return capabilities;
    }

    private void injectDriverInTestClasses(Object pTestExecutionInstance, WebDriver pWebDriver) throws NoSuchFieldException {
        Field field = AbstractSeleniumSystemTests.class.getDeclaredField(DRIVER);
        if(field != null) {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, pTestExecutionInstance, pWebDriver);
        }
    }

    private void initAndInjectPagesInTestClasses(Object pTestExecutionInstance, WebDriver pWebDriver) {
        Class<?> clazz = pTestExecutionInstance.getClass();
        while (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Page.class)) {
                    try {
                        ReflectionUtils.makeAccessible(field);
                        Object pageInstance = field.getType().getConstructor().newInstance();
                        Field driverField = findDriverField(pageInstance.getClass(), DRIVER);
                        if(driverField != null) {
                            ReflectionUtils.makeAccessible(driverField);
                            ReflectionUtils.setField(driverField, pageInstance, pWebDriver);
                            PageFactory.initElements(new BaseElementDecorator(new DefaultElementLocatorFactory(pWebDriver), pWebDriver), pageInstance);
                            ReflectionUtils.setField(field, pTestExecutionInstance, pageInstance);
                        } else {
                            throw new RuntimeException("Failed to initialize @Page: " + field.getName() + ". No driver field found.");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e.getLocalizedMessage(), e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private Field findDriverField(Class<?> pClass, String pFieldName) {
        while (pClass != Object.class) {
            try {
                Field field = pClass.getDeclaredField(pFieldName);
                if (field != null) {
                    return field;
                }
            } catch (NoSuchFieldException e) {
                LOGGER.debug("No field '{}' found in class: {}", pFieldName, pClass.getName());
            }
            pClass = pClass.getSuperclass();
        }
        return null;
    }

    @Override
    public void afterEach(ExtensionContext pTestExecutionContext) {
        WebDriver driver = pTestExecutionContext.getStore(NAMESPACE).get(DRIVER, WebDriver.class);
        if (driver != null) {
            driver.quit();
        }
    }
}
