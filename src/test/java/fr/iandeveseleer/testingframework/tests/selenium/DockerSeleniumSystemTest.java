package fr.iandeveseleer.testingframework.tests.selenium;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTest;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import io.github.bonigarcia.seljup.DockerBrowser;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static io.github.bonigarcia.seljup.BrowserType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DockerSeleniumSystemTest extends AbstractSeleniumSystemTest {

    @SystemTest
    public void testChrome(@DockerBrowser(type = CHROME) WebDriver driver) {
        assertions(driver);
    }

    @SystemTest
    public void testFirefox(@DockerBrowser(type = FIREFOX) WebDriver driver) {
        assertions(driver);
    }

    @SystemTest
    public void testEdge(@DockerBrowser(type = EDGE) WebDriver driver) {
        assertions(driver);
    }

    private static void assertions(WebDriver driver) {
        driver.get("https://iandeveseleer.fr/");
        assertThat(driver.getTitle()).contains("Ian Deveseleer");
    }
}
