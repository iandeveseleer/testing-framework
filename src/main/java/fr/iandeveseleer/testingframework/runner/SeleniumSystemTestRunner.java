package fr.iandeveseleer.testingframework.runner;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;

@Slf4j
public abstract class SeleniumSystemTestRunner extends SystemTestRunner {

    @BeforeSuite
    static void beforeSuite() {
        LOGGER.info("Before Selenium suite");
    }

    @AfterSuite
    static void afterSuite() {
        LOGGER.info("After Selenium suite");
    }
}
