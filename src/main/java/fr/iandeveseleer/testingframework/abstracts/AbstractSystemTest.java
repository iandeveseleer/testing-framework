package fr.iandeveseleer.testingframework.abstracts;

import fr.iandeveseleer.testingframework.extensions.ExtensionChain;
import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;

@ExtensionChain
public abstract class AbstractSystemTest {

    protected TestService testService = ApplicationContextProvider.getBean(TestService.class);
}