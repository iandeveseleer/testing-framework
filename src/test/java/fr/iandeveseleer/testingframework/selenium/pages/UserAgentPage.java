package fr.iandeveseleer.testingframework.selenium.pages;

import fr.iandeveseleer.testingframework.annotations.ElementName;
import fr.iandeveseleer.testingframework.selenium.pages.elements.BaseElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class UserAgentPage extends BasePage {

    @Getter
    @FindBy(using = "ua")
    @ElementName("User agent chain element")
    private BaseElement userAgentChain;
}
