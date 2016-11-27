package main;


import java.lang.reflect.Method;

@Component("Main")
public class Main {

    public static void main(String[] args) {
	// write your code here

		HelloWorldFactory.getInstance().getHelloWorld().helloWorld();
    }

}
