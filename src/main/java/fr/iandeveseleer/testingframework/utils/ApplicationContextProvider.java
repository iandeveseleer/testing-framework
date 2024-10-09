package fr.iandeveseleer.testingframework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider {

  private static ApplicationContext context;

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) {
    context = applicationContext;
  }

  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }
}
