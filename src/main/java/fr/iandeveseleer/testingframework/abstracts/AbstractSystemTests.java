package fr.iandeveseleer.testingframework.abstracts;

import fr.iandeveseleer.testingframework.extensions.ExtensionChain;
import fr.iandeveseleer.testingframework.extensions.test.SystemTestDisplayNameGenerator;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import org.junit.jupiter.api.DisplayNameGeneration;

@ExtensionChain
@DisplayNameGeneration(SystemTestDisplayNameGenerator.class)
public abstract class AbstractSystemTests {

    protected TestService testService = ApplicationContextProvider.getBean(TestService.class);
}