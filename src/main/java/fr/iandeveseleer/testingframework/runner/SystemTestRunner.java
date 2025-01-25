package fr.iandeveseleer.testingframework.runner;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;

@Slf4j
@IncludeTags("SystemTest")
@IncludeClassNamePatterns(".*SystemTest$")
public abstract class SystemTestRunner {

    @BeforeSuite
    static void beforeSuite() {
        LOGGER.info("Before suite");
    }

    @AfterSuite
    static void afterSuite() {
        LOGGER.info("After suite");
    }
}
