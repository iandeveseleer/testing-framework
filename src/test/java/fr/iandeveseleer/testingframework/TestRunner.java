package fr.iandeveseleer.testingframework;

import fr.iandeveseleer.testingframework.runner.CustomTestRunner;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@IncludeClassNamePatterns(".*Test")
@SuiteDisplayName("testing-framework suite")
@SelectPackages("fr.iandeveseleer.testingframework.tests")
public class TestRunner extends CustomTestRunner {

}
