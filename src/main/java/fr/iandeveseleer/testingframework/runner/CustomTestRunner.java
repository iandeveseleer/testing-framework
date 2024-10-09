package fr.iandeveseleer.testingframework.runner;

import fr.iandeveseleer.testingframework.AppConfig;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public abstract class CustomTestRunner {

  @BeforeSuite
  static void beforeSuite() {
    // Initialize ApplicationContextProvider (will be used to access services in whole framework)
    ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
    applicationContextProvider.setApplicationContext(new AnnotationConfigApplicationContext(AppConfig.class));

    log.info("Before suite");
    TestService service = ApplicationContextProvider.getBean(TestService.class);
    service.callApi();
  }

  @AfterSuite
  static void afterSuite() {
    log.info("After suite");
  }
}
