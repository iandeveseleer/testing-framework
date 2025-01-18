package fr.iandeveseleer.testingframework.tests.selenium;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTest;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumSystemTest extends AbstractSeleniumSystemTest {

    @DriverUrl
    String url = "https://127.0.0.1:4444/hub";

    @DriverCapabilities
    DesiredCapabilities capabilities = new DesiredCapabilities();
    {
        capabilities.setCapability("browserName", "Chrome");
    }

    @Test
    public void test(RemoteWebDriver driver) {
        driver.get("https://iandeveseleer.fr/");
        assertThat(driver.getTitle()).contains("Ian Deveseleer");
    }
}
