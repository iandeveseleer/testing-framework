package fr.iandeveseleer.testingframework.testclass;

import fr.iandeveseleer.testingframework.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractTest {

  //
  @Autowired
  protected TestService testService;

  // Static block to simulate "before suite" initialization
  static {
    System.out.println("Static initialization block - Executed before any test cases.");
    initializeSuite();
  }

  // Method to handle initialization logic
  private static void initializeSuite() {
    // Here you can make service calls or set up any required initialization.
    System.out.println("Performing suite-level setup...");
    // Example: Service calls or initializing external components.
  }

  // Utility method that can be used by individual test classes
  protected void commonSetUp() {
    System.out.println("Common test setup logic.");
  }

  // Common utility methods for all tests can be added here
}