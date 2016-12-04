package main.implementations;

import main.HelloWorldComparator;
import main.annotations.Component;
import main.annotations.ValidateString;
import main.interfaces.MessageProvider;

/**
 * Created on 27.11.2016.
 */
@Component("helloWorld")
public class HelloWorldMessageProvider implements MessageProvider {

	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(@ValidateString(comparator = HelloWorldComparator.class,value = "Hello World")String message) {
		this.message = message;
	}
	public HelloWorldMessageProvider(){
		setMessage("Hello World");
	}
}
