package main;

/**
 * Created on 27.11.2016.
 */
public class HelloWorldMessageProvider implements MessageProvider {
	@Override
	public String getMessage() {
		return "Hello World";
	}
}
