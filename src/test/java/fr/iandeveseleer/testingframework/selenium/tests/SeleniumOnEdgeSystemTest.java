package fr.iandeveseleer.testingframework.selenium.tests;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import fr.iandeveseleer.testingframework.selenium.abstracts.AbstractSeleniumTesting;
import org.junit.jupiter.api.Disabled;

public class SeleniumOnEdgeSystemTest extends AbstractSeleniumTesting {

    @SystemTest(browser = "MicrosoftEdge")
    @Disabled("WIP - Edge is not working")
    public void testEdge() {
        assertions("MicrosoftEdge");
    }
}
