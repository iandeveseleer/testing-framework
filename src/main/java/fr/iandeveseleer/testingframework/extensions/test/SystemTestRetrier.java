package fr.iandeveseleer.testingframework.extensions.test;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Slf4j
public class SystemTestRetrier implements Iterator<SystemTestTemplateInvocationContext> {

    private final String systemTestName;
    private final int maxAttempts = 3;
    private int attempts;
    private int exceptionsCount;

    private SystemTestRetrier(String pSystemTestName) {
        this.systemTestName = pSystemTestName;
        this.attempts = 0;
        this.exceptionsCount = 0;
    }

    static SystemTestRetrier createFor(Method test, ExtensionContext context) {
        AnnotationSupport.findAnnotation(test, SystemTest.class)
                .orElseThrow(() -> new ExtensionConfigurationException("Method must be annotated with @SystemTest"));
        return new SystemTestRetrier(context.getDisplayName());
    }

    @Override
    public boolean hasNext() {
        if (attempts == 0) {
            return true;
        } else {
            int successfulExecutionCount = attempts - exceptionsCount;
            int remainingExecutionCount = maxAttempts - attempts;
            int requiredSuccessCount = 1 - successfulExecutionCount;

            return remainingExecutionCount >= requiredSuccessCount && requiredSuccessCount > 0;
        }
    }

    @Override
    public SystemTestTemplateInvocationContext next() {
        if (hasNext()) {
            attempts++;
            return new SystemTestTemplateInvocationContext(systemTestName);
        }
        throw new NoSuchElementException();
    }

    <E extends Throwable> void failed(E exception) throws E {
        exceptionsCount++;
        LOGGER.info("Test execution failed: {} ({}/{})", exception.getLocalizedMessage(), attempts, maxAttempts);
        throw exception;
    }
}