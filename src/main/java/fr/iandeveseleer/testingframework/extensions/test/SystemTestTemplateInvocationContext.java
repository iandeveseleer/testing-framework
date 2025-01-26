package fr.iandeveseleer.testingframework.extensions.test;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import java.lang.reflect.Method;

public class SystemTestTemplateInvocationContext implements TestTemplateInvocationContext {

    private final Method systemTestMethod;
    private final DisplayNameGenerator displayNameGenerator;

    SystemTestTemplateInvocationContext(Method pSystemTestMethod, SystemTestDisplayNameGenerator pDisplayNameGenerator) {
        this.systemTestMethod = pSystemTestMethod;
        this.displayNameGenerator = pDisplayNameGenerator;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return String.format("%s [%d]", displayNameGenerator.generateDisplayNameForMethod(getClass(), systemTestMethod), invocationIndex);
    }
}