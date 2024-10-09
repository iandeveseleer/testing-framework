package fr.iandeveseleer.testingframework.runner;


import fr.iandeveseleer.testingframework.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public abstract class CustomTestRunner {

  @Autowired
  private static TestService testService;

  @BeforeSuite
  static void beforeSuite() {
    //TODO : how could we call services in before
    log.info("Before suite");
    testService.callApi();
  }

  @AfterSuite
  static void afterSuite() {
    log.info("After suite");
  }
}
