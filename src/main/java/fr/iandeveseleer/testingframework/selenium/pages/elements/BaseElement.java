package fr.iandeveseleer.testingframework.selenium.pages.elements;

import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.core.env.Environment;

import java.time.Duration;

@Slf4j
public class BaseElement implements WrapsElement {

    private Environment environment = ApplicationContextProvider.getBean(Environment.class);

    private WebElement webElement;

    @Getter
    private WebDriver webDriver;

    private final FluentWait<WebDriver> webDriverFluentWait;

    private String elementDescription = StringUtils.EMPTY;

    public BaseElement(WebDriver pWebDriver, WebElement pWebElement) {
        webElement = pWebElement;
        webDriver = pWebDriver;
        webDriverFluentWait = new FluentWait<>(pWebDriver)
                .withTimeout(Duration.ofSeconds(environment.getProperty("testing.framework.selenium.timeout", Integer.class, 10)))
                .pollingEvery(Duration.ofMillis(500));
    }

    public BaseElement(WebDriver pWebDriver, WebElement pWebElement, String pElementDescription) {
        this(pWebDriver, pWebElement);
        elementDescription = pElementDescription;
    }

    public BaseElement waitAndCheckVisibility() {
        webElement = webDriverFluentWait
                .withMessage(String.format("%s should be visible", this))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(webElement));
        return this;
    }

    public BaseElement click() {
        webDriverFluentWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(webElement), ExpectedConditions.elementToBeClickable(webElement)));
        webElement.click();
        return this;
    }

    public String text() {
        try {
            return webElement.getText();
        } catch (Exception e) {
            throw enhanceExceptionMessage(e, "getText");
        }
    }

    public boolean displayed() {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            throw enhanceExceptionMessage(e, "isDisplayed");
        }
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }

    @Override
    public String toString() {
        return elementDescription;
    }

    private RuntimeException enhanceExceptionMessage(Exception e, String action) {
        String newMessage = String.format("[%s] Error performing '%s' : %s", this, action, e.getLocalizedMessage());

        try {
            return (RuntimeException) e.getClass().getConstructor(String.class, Throwable.class).newInstance(newMessage, e);
        } catch (Exception ex) {
            return new RuntimeException(newMessage, ex);
        }
    }
}
