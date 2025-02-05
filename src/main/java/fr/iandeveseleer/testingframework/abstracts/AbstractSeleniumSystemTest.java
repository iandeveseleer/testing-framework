package fr.iandeveseleer.testingframework.abstracts;

import fr.iandeveseleer.testingframework.extensions.SeleniumExtension;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(SeleniumExtension.class)
public abstract class AbstractSeleniumSystemTest extends AbstractSystemTest {

    @Getter
    private WebDriver driver;
}