package fr.iandeveseleer.testingframework.runner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;


@Suite
//@IncludeEngines("junit-jupiter")
@IncludeClassNamePatterns(".*TS")
@SuiteDisplayName("testing-framework suite")
//@SpringBootTest
@Slf4j
public class CustomTestRunner {

  @BeforeSuite
  static void beforeSuite() {
    log.info("Before suite");
    // executes before the test suite
  }

  @AfterSuite
  static void afterSuite() {
    log.info("After suite");
  }
}
