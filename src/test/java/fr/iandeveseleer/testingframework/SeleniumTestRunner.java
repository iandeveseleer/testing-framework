package fr.iandeveseleer.testingframework;

import fr.iandeveseleer.testingframework.runner.SeleniumSystemTestRunner;
import fr.iandeveseleer.testingframework.runner.SystemTestRunner;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("fr.iandeveseleer.testingframework.tests.selenium")
public class SeleniumTestRunner extends SeleniumSystemTestRunner {

}
