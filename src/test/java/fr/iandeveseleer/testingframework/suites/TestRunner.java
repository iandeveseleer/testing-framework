package fr.iandeveseleer.testingframework.suites;

import fr.iandeveseleer.testingframework.runner.SystemTestRunner;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("fr.iandeveseleer.testingframework.basic.tests")
public class TestRunner extends SystemTestRunner {

}
