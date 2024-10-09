package fr.iandeveseleer.testingframework;

import fr.iandeveseleer.testingframework.runner.CustomTestRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@IncludeClassNamePatterns(".*Test")
@SuiteDisplayName("testing-framework suite")
@Slf4j
@SelectPackages("fr.iandeveseleer.testingframework.tests")
public class TestRunner {

  @BeforeSuite
  static void beforeSuite() {
    log.info("Before suite");
  }

  @AfterSuite
  static void afterSuite() {
    log.info("After suite");
  }
}
