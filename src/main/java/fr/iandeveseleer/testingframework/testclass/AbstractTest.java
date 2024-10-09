package fr.iandeveseleer.testingframework.testclass;

import fr.iandeveseleer.testingframework.service.TestService;
import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;

public abstract class AbstractTest {

  protected TestService testService = ApplicationContextProvider.getBean(TestService.class);
}