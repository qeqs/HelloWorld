package main.annotations;

import main.annotations.analyzers.StringValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;
import javax.validation.Constraint;

/**
 * Created on 04.12.2016.
 */
@Constraint(validatedBy = {StringValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValidateString {
	Class<? extends Comparator> comparator();
	String value();
}
