package main.implementations;

import main.annotations.Component;
import main.interfaces.MessageProvider;

/**
 * Created on 27.11.2016.
 */
@Component("HelloWorld")
public class HelloWorldMessageProvider implements MessageProvider {
	@Override
	public String getMessage() {
		return "Hello World";
	}
}
