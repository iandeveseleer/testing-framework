package fr.iandeveseleer.testingframework.extensions.test;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Slf4j
public class SystemTestRetrier implements Iterator<SystemTestTemplateInvocationContext> {

    private final Method systemTestMethod;
    private final SystemTestDisplayNameGenerator systemTestDisplayNameGenerator;
    private final int maxAttempts = 3;
    private int attempts;
    private int exceptionsCount;

    private SystemTestRetrier(Method pSystemTestMethod, SystemTestDisplayNameGenerator pDisplayNameGenerator) {
        this.systemTestMethod = pSystemTestMethod;
        this.systemTestDisplayNameGenerator = pDisplayNameGenerator;
        this.attempts = 0;
        this.exceptionsCount = 0;
    }

    static SystemTestRetrier createFor(Method pSystemTestMethod, SystemTestDisplayNameGenerator pDisplayNameGenerator) {
        return new SystemTestRetrier(pSystemTestMethod, pDisplayNameGenerator);
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
            return new SystemTestTemplateInvocationContext(systemTestMethod, systemTestDisplayNameGenerator);
        }
        throw new NoSuchElementException();
    }

    <E extends Throwable> void failed(E exception) throws E {
        exceptionsCount++;
        LOGGER.info("Test execution failed: {} ({}/{})", exception.getLocalizedMessage(), attempts, maxAttempts);
        throw exception;
    }
}