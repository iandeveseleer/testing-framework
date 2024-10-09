package fr.iandeveseleer.testingframework.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

  public String callApi() {
    // Simulate API call logic
    System.out.println("Calling API...");
    return "API response"; // Simulated response
  }
}
