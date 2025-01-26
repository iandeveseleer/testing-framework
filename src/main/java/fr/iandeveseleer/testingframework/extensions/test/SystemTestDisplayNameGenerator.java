package fr.iandeveseleer.testingframework.extensions.test;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.util.Optional;

public class SystemTestDisplayNameGenerator extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return testClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return nestedClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        Optional<SystemTest> systemTest = AnnotationSupport.findAnnotation(testMethod, SystemTest.class);

        String displayName = testMethod.getName();
        if (systemTest.isPresent()) {
            String systemTestIdentifier = systemTest.get().identifier();
            if (StringUtils.isNotEmpty(systemTestIdentifier)) {
                displayName = String.format("%s - %s", systemTestIdentifier, displayName);
            }
        }
        return displayName;
    }
}