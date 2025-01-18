package fr.iandeveseleer.testingframework.tests.basic;

import fr.iandeveseleer.testingframework.abstracts.AbstractSystemTest;
import org.junit.jupiter.api.Test;

/**
 * Created on 03/10/2024
 **/
public class FirstClassSystemTest extends AbstractSystemTest {

  @Test
  public void firstTest() {
    String response = testService.callApi();
    assert response.equals("API response"); // Replace with an appropriate assertion
    System.out.println("Test passed: " + response);
  }
}
