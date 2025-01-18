package fr.iandeveseleer.testingframework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

  public String callApi() {
    LOGGER.info("Calling API...");
    return "API response";
  }
}
