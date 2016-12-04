package main.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 04.12.2016.
 */
@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
public @interface Qualifier {
	Class clazz() default Qualifier.class;
	String name() default "";
}
