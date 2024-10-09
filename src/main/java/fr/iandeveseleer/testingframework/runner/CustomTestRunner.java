package fr.iandeveseleer.testingframework.runner;

import fr.iandeveseleer.testingframework.AppConfig;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public abstract class CustomTestRunner {

  private static ApplicationContext applicationContext;

  @BeforeSuite
  static void beforeSuite() {
    // Initialize application context
    applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    // Initialize ApplicationContextProvider (will be used to access services in whole framework)
    ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
    applicationContextProvider.setApplicationContext(applicationContext);

    log.info("Before suite");
    TestService service = ApplicationContextProvider.getBean(TestService.class);
    service.callApi();
  }

  @AfterSuite
  static void afterSuite() {
    log.info("After suite");
  }
}
