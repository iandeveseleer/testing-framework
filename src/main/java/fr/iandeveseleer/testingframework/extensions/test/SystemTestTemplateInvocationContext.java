package fr.iandeveseleer.testingframework.extensions.test;

import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

public class SystemTestTemplateInvocationContext implements TestTemplateInvocationContext {

	private final String systemTestName;

	SystemTestTemplateInvocationContext(String pSystemTestName) {
		this.systemTestName = pSystemTestName;
	}

	@Override
	public String getDisplayName(int invocationIndex) {
		return String.format("%s [%d]", systemTestName, invocationIndex);
	}
}