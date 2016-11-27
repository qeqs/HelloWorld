package main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 27.11.2016.
 */
@Target(ElementType.TYPE)
@Retention(value=RUNTIME)
public @interface Component {
	String value();
}
