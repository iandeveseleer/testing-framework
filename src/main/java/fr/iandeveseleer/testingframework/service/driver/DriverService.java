package fr.iandeveseleer.testingframework.service.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverService {

    private final Environment environment;

    public WebDriver initializeWebDriver(DesiredCapabilities pCapabilities, String pGridUrl) {
        boolean isDockerMode = Boolean.parseBoolean(environment.getProperty("testing.framework.selenium.docker-mode"));
        WebDriver driver;
        if (Boolean.TRUE.equals(isDockerMode)) {
            // Not working for now due to : https://github.com/docker-java/docker-java/pull/2364
            LOGGER.info("Running in Docker mode for : {}", pCapabilities);
            WebDriverManager wdm = WebDriverManager
                    .getInstance(pCapabilities.getBrowserName())
                    .dockerCustomImage("selenium/standalone-" + pCapabilities.getBrowserName().toLowerCase())
                    .enableRecording()
                    .disableTracing()
                    .browserInDocker();
            driver = wdm.create();
        } else {
            LOGGER.info("Running in Grid mode for : {}", pCapabilities);
            driver = WebDriverManager.getInstance()
                    .remoteAddress(pGridUrl)
                    .enableRecording()
                    .disableTracing()
                    .capabilities(pCapabilities).create();
        }
        return driver;
    }
}
