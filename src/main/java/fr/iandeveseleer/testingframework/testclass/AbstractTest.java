package fr.iandeveseleer.testingframework.testclass;

import fr.iandeveseleer.testingframework.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractTest {

  @Autowired
  protected TestService testService;
}