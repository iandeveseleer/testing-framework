package fr.iandeveseleer.testingframework.selenium.abstracts;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTests;
import fr.iandeveseleer.testingframework.annotations.Page;
import fr.iandeveseleer.testingframework.selenium.pages.UserAgentPage;
import fr.iandeveseleer.testingframework.selenium.pages.elements.BaseElement;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractSeleniumTesting extends AbstractSeleniumSystemTests {

    @Page
    private UserAgentPage userAgentPage;

    protected void assertions(String pBrowserName) {
        userAgentPage.goTo("https://whatmyuseragent.com/");
        BaseElement userAgent = userAgentPage.getUserAgentChain();
        userAgent.waitAndCheckVisibility();
        assertThat(userAgent.text()).containsIgnoringCase(pBrowserName);
        userAgentPage.goTo("https://whatmyuseragent.com/");
        assertThat(userAgent.text()).containsIgnoringCase(pBrowserName);
    }
}
