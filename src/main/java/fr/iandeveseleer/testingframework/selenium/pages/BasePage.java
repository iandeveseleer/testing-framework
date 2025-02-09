package fr.iandeveseleer.testingframework.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    @Getter
    private WebDriver driver;

    public void goTo(String pUrl) {
        driver.get(pUrl);
    }

    public void go(String pUrl) {
        goTo(pUrl);
    }
}
