package fr.iandeveseleer.testingframework.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class PreparationExtension extends AbstractExtension implements AfterEachCallback, BeforeEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        LOGGER.info("Actions after each test, calling API, init something.....");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        LOGGER.info("Actions before each test, calling API, clean something.....");
    }
}
