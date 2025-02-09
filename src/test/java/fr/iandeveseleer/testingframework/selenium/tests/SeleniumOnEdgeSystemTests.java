package fr.iandeveseleer.testingframework.selenium.tests;

import fr.iandeveseleer.testingframework.annotations.Browser;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.enums.BrowserType;
import fr.iandeveseleer.testingframework.selenium.abstracts.AbstractSeleniumTesting;

public class SeleniumOnEdgeSystemTests extends AbstractSeleniumTesting {

    @SystemTest
    @Browser(BrowserType.EDGE)
    public void testEdge() {
        assertions("Edg");
    }
}
