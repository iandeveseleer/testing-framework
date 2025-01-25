package fr.iandeveseleer.testingframework.runner;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;

@Slf4j
@IncludeClassNamePatterns(".*SystemTest$")
public abstract class SeleniumSystemTestRunner {

    @BeforeSuite
    static void beforeSuite() {
        SystemTestRunner.beforeSuite();
        LOGGER.info("Before Selenium suite");
    }

    @AfterSuite
    static void afterSuite() {
        SystemTestRunner.afterSuite();
        LOGGER.info("After Selenium suite");
    }
}
