package fr.iandeveseleer.testingframework.suites;

import fr.iandeveseleer.testingframework.runner.SeleniumSystemTestRunner;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("fr.iandeveseleer.testingframework.selenium.tests")
public class SeleniumTestRunner extends SeleniumSystemTestRunner {

}
