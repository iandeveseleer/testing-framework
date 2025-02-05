package fr.iandeveseleer.testingframework.utils;

import fr.iandeveseleer.testingframework.AppConfig;
import lombok.Getter;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ApplicationContextProvider {

    @Getter
    private static ApplicationContext context;

    private ApplicationContextProvider() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T getBean(Class<T> beanClass) {
        // Initialize ApplicationContextProvider (will be used to access services in whole framework)
        if (context == null) {
            context = new SpringApplicationBuilder(AppConfig.class)
                    .bannerMode(Banner.Mode.OFF)
                    .logStartupInfo(false)
                    .profiles("systemtests")
                    .run();
        }
        return context.getBean(beanClass);
    }
}
