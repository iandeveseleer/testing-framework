package fr.iandeveseleer.testingframework.annotations;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.iandeveseleer.testingframework.extensions.test.SystemTestExtension;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Execution(SAME_THREAD)
@ExtendWith(SystemTestExtension.class)
@Tag("SystemTest")
@TestTemplate
public @interface SystemTest {

    String identifier() default StringUtils.EMPTY;
}