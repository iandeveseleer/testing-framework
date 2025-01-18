package fr.iandeveseleer.testingframework;

import fr.iandeveseleer.testingframework.runner.SystemTestRunner;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("fr.iandeveseleer.testingframework.tests.basic")
public class TestRunner extends SystemTestRunner {

}
