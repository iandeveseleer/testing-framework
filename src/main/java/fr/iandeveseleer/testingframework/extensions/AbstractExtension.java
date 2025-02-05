package fr.iandeveseleer.testingframework.extensions;

import fr.iandeveseleer.testingframework.utils.ApplicationContextProvider;
import org.springframework.core.env.Environment;

public abstract class AbstractExtension {

    protected Environment environment = ApplicationContextProvider.getBean(Environment.class);
}
