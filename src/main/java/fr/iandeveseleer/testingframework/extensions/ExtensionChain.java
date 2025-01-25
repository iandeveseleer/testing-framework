package fr.iandeveseleer.testingframework.extensions;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({PreparationExtension.class})
public @interface ExtensionChain {
}
