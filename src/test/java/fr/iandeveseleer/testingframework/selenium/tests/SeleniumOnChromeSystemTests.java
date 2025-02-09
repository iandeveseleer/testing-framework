package fr.iandeveseleer.testingframework.selenium.tests;

import fr.iandeveseleer.testingframework.annotations.Browser;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.enums.BrowserType;
import fr.iandeveseleer.testingframework.selenium.abstracts.AbstractSeleniumTesting;

public class SeleniumOnChromeSystemTests extends AbstractSeleniumTesting {

    @SystemTest
    public void testChrome() {
        assertions("CHROME");
    }

    @SystemTest()
    @Browser(BrowserType.CHROME)
    public void testChrome2() {
        assertions("CHROME");
    }
}
