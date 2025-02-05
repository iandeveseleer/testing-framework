package fr.iandeveseleer.testingframework.selenium.tests;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.selenium.abstracts.AbstractSeleniumTesting;

public class SeleniumOnFirefoxSystemTest extends AbstractSeleniumTesting {

    @SystemTest(browser = "FIREFOX")
    public void testFirefox() {
        assertions("FIREFOX");
    }
}
