package fr.iandeveseleer.testingframework.extensions.test;

import fr.iandeveseleer.testingframework.abstracts.AbstractSeleniumSystemTest;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public class SystemTestExtension implements TestTemplateInvocationContextProvider, TestExecutionExceptionHandler {

    private static final Namespace NAMESPACE = Namespace.create(SystemTestExtension.class);

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return AnnotationSupport.isAnnotated(context.getRequiredTestMethod(), SystemTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        SystemTest systemTest = context.getTestMethod().get().getAnnotation(SystemTest.class); // NOSONAR : Protected by SystemTestExtension
        Optional<Method> optionalMethod = context.getTestMethod();
        Optional<Object> optionalTestInstance = context.getTestInstance();

        if(optionalMethod.isPresent() && optionalTestInstance.isPresent()) {
            if (StringUtils.isNotEmpty(systemTest.browser()) && !(context.getRequiredTestInstance() instanceof AbstractSeleniumSystemTest)) {
                throw new IllegalStateException("The browser field can only be valued from a class extending AbstractSeleniumSystemTest");
            }

        }
        SystemTestRetrier systemTestRetrier = createSystemTestRetrier(context);
        return stream(spliteratorUnknownSize(systemTestRetrier, ORDERED), false);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext pTestExecutionContext, Throwable pThrowable) throws Throwable {
        ExtensionContext testTemplateContext = pTestExecutionContext.getParent()
                .orElseThrow(() -> new IllegalStateException("TestTemplateInvocationContext not found."));
        createSystemTestRetrier(testTemplateContext).failed(pThrowable);
    }

    private static SystemTestRetrier createSystemTestRetrier(ExtensionContext context) {
        return context.getStore(NAMESPACE)
                .getOrComputeIfAbsent(context.getRequiredTestMethod().toString(),
                        key -> SystemTestRetrier.createFor(context.getRequiredTestMethod(), new SystemTestDisplayNameGenerator()),
                        SystemTestRetrier.class);
    }
}