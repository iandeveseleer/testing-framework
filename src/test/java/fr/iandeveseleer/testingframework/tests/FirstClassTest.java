package fr.iandeveseleer.testingframework.tests;

import fr.iandeveseleer.testingframework.testclass.AbstractTest;
import org.junit.jupiter.api.Test;

/**
 * Created on 03/10/2024
 **/
public class FirstClassTest extends AbstractTest {

  @Test
  public void testServiceMethod() {
    String response = testService.callApi();
    assert response.equals("API response"); // Replace with an appropriate assertion
    System.out.println("Test passed: " + response);
  }

  @Test
  public void testServiceMethod2() {
    String response = testService.callApi();
    assert response.equals("API response"); // Replace with an appropriate assertion
    System.out.println("Test passed: " + response);
  }
}
