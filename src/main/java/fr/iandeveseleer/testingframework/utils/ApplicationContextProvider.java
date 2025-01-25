package fr.iandeveseleer.testingframework.utils;

import fr.iandeveseleer.testingframework.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider {

  private static ApplicationContext context;

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) {
    context = applicationContext;
  }

  public static <T> T getBean(Class<T> beanClass) {
    // Initialize ApplicationContextProvider (will be used to access services in whole framework)
    if(context == null) {
      context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
    return context.getBean(beanClass);
  }
}
