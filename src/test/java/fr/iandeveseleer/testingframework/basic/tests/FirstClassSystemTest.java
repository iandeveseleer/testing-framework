package fr.iandeveseleer.testingframework.basic.tests;

import fr.iandeveseleer.testingframework.abstracts.AbstractSystemTest;
import fr.iandeveseleer.testingframework.annotations.SystemTest;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

/**
 * Created on 03/10/2024
 **/
public class FirstClassSystemTest extends AbstractSystemTest {

  @SystemTest(identifier = "First test with custom identifier")
  public void firstTest() {
    String response = testService.callApi();
    assert response.equals("API response");
  }

  @SystemTest
  public void firstTestWithAssertionError() {
    throw new AssertionFailedError("This test is expected to fail");
  }

  @Test
  public void firstNotSystemTest() {
    String response = testService.callApi();
    assert response.equals("API response");
  }

  @Test
  public void firstNotSystemTestWithAssertionError() {
    throw new AssertionFailedError("This test is expected to fail");
  }
}
