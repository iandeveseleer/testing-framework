package fr.iandeveseleer.testingframework.tests.basic;

import fr.iandeveseleer.testingframework.abstracts.AbstractSystemTest;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import org.junit.jupiter.api.Test;

/**
 * Created on 03/10/2024
 **/
public class SecondClassSystemTest extends AbstractSystemTest {

  @SystemTest
  public void secondTest() {
    String response = testService.callApi();
    assert response.equals("API response");
    System.out.println("Test passed: " + response);
  }
}
