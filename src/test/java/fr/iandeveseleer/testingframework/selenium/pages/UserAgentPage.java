package fr.iandeveseleer.testingframework.selenium.pages;

import fr.iandeveseleer.testingframework.selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserAgentPage extends BasePage {

    public UserAgentPage(WebDriver pWebDriver) {
        super(pWebDriver);
    }

    public WebElement getUserAgent() {
        return el(By.cssSelector("div.row:nth-child(6) > div:nth-child(2)"), "Field containing browser name");
    }
}
