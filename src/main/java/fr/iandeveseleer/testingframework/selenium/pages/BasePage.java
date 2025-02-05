package fr.iandeveseleer.testingframework.selenium.pages;

import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.env.Environment;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private Environment environment = ApplicationContextProvider.getBean(Environment.class);

    protected BasePage(WebDriver pWebDriver) {
        this.driver = pWebDriver;
        int timeout = environment.getProperty("testing.framework.selenium.timeout", Integer.class, 10);
        this.wait = new WebDriverWait(pWebDriver, Duration.ofSeconds(timeout));
        PageFactory.initElements(pWebDriver, this);
    }

    protected WebElement el(By pLocator, String pMessage) {
        return wait.withMessage(String.format("%s should be visible", pMessage)).until(ExpectedConditions.visibilityOfElementLocated(pLocator));
    }

    protected WebElement $(By pLocator, String pMessage) {
        return el(pLocator, pMessage);
    }

    public void goTo(String pUrl) {
        driver.get(pUrl);
    }

    public void go(String pUrl) {
        goTo(pUrl);
    }

    protected WebElement click(WebElement pWebElement) {
        wait.until(ExpectedConditions.elementToBeClickable(pWebElement)).click();
        return pWebElement;
    }

    protected WebElement enterText(WebElement pWebElement, String pText) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOf(pWebElement));
        webElement.clear();
        webElement.sendKeys(pText);
        return webElement;
    }
}
