package fr.iandeveseleer.testingframework.basic.tests;

import fr.iandeveseleer.testingframework.abstracts.AbstractSystemTests;
import fr.iandeveseleer.testingframework.annotations.SystemTest;

/**
 * Created on 03/10/2024
 **/
public class SecondClassSystemTests extends AbstractSystemTests {

  @SystemTest
  public void secondTest() {
    String response = testService.callApi();
    assert response.equals("API response");
    System.out.println("Test passed: " + response);
  }
}
