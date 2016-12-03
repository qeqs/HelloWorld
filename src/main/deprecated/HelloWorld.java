package main.deprecated;

/**
 * Created on 27.11.2016.
 */
@Deprecated
public class HelloWorld {

	private String message = "Hello world";

	public void setMessage(String message) {this.message = message;}

	public void helloWorld() {
		System.out.println(message);
	}
}
