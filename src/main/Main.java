package main;


import main.annotations.Component;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

@Component("Main")
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
	// write your code here

		//HelloWorldFactory.getInstance().getHelloWorld().helloWorld();

	    ApplicationContext context = new ApplicationContext();
	    MessageRenderer renderer= (MessageRenderer) context.getBean("renderer");
	    renderer.render();

	    renderer = (MessageRenderer) context.getBean("newrenderer");
	    renderer.render();
		System.exit(0);

	}

}
