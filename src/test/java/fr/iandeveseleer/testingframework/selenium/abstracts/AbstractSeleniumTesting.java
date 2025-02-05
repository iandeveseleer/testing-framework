package fr.iandeveseleer.testingframework.selenium.abstracts;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTest;
import fr.iandeveseleer.testingframework.annotations.Page;
import fr.iandeveseleer.testingframework.selenium.pages.UserAgentPage;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractSeleniumTesting extends AbstractSeleniumSystemTest {

    @Page
    private UserAgentPage userAgentPage;

    protected void assertions(String pBrowserName) {
        userAgentPage.goTo("https://my-user-agent.com/");
        WebElement element = userAgentPage.getUserAgent();
        String userAgent = element.getText();
        assertThat(userAgent).containsIgnoringCase(pBrowserName);
    }
}
