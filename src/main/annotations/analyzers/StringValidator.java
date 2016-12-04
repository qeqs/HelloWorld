package main.annotations.analyzers;

import main.annotations.ValidateString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Comparator;

/**
 * Created on 04.12.2016.
 */
public class StringValidator implements ConstraintValidator<ValidateString,String> {


	ValidateString validateString;
	Comparator comparator;

	public void initialize(ValidateString validate) {
		validateString = validate;
		try {
			comparator = validate.comparator().newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

		return 0 == comparator.compare(s, validateString.value());
	}
}
