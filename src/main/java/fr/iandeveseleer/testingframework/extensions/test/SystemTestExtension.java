package fr.iandeveseleer.testingframework.extensions.test;

import fr.iandeveseleer.testingframework.annotations.SystemTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.support.AnnotationSupport;

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
                        key -> SystemTestRetrier.createFor(context.getRequiredTestMethod(), context),
                        SystemTestRetrier.class);
    }
}