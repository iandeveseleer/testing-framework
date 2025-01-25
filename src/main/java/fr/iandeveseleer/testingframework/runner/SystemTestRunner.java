package fr.iandeveseleer.testingframework.runner;

import fr.iandeveseleer.testingframework.AppConfig;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@IncludeTags("SystemTest")
@IncludeClassNamePatterns(".*SystemTest$")
public abstract class SystemTestRunner {

  @BeforeSuite
  static void beforeSuite() throws Exception {
    // Initialize ApplicationContextProvider (will be used to access services in whole framework)
    ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
    applicationContextProvider.setApplicationContext(new AnnotationConfigApplicationContext(AppConfig.class));
    LOGGER.info("Before suite");
  }

  @AfterSuite
  static void afterSuite() {
    LOGGER.info("After suite");
  }
}
