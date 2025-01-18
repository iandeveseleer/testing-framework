package fr.iandeveseleer.testingframework.runner;

import com.sun.tools.javac.Main;
import fr.iandeveseleer.testingframework.AppConfig;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@IncludeClassNamePatterns(".*SystemTest$")
public abstract class SeleniumSystemTestRunner {

  @BeforeSuite
  static void beforeSuite() throws Exception {
    SystemTestRunner.beforeSuite();

    LOGGER.info("Before Selenium suite");
  }

  @AfterSuite
  static void afterSuite() {
    SystemTestRunner.afterSuite();
    LOGGER.info("After Selenium suite");
  }
}
