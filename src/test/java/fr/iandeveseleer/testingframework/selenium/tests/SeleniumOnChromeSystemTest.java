package fr.iandeveseleer.testingframework.selenium.tests;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.selenium.abstracts.AbstractSeleniumTesting;

public class SeleniumOnChromeSystemTest extends AbstractSeleniumTesting {

    @SystemTest
    public void testChrome() {
        assertions("CHROME");
    }

    @SystemTest(browser = "CHROME")
    public void testChrome2() {
        assertions("CHROME");
    }
}
