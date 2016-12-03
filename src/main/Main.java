package main;


import main.annotations.Component;
import main.deprecated.ApplicationContext;
import main.interfaces.MessageRenderer;

import java.lang.reflect.InvocationTargetException;

@Component("Main")
public class Main {

    public static void main(String[] args)  {
	// write your code here

		//HelloWorldFactory.getInstance().getHelloWorld().helloWorld();

//	    ApplicationContext context = new ApplicationContext();
//	    MessageRenderer renderer= (MessageRenderer) context.getBean("renderer");
//	    renderer.render();
//
//	    renderer = (MessageRenderer) context.getBean("newrenderer");
//	    renderer.render();
	    try {
		    MessageRenderer renderer = (MessageRenderer) Context.getInstance().getBean("system.out");
		    renderer.render();

		    renderer = (MessageRenderer) Context.getInstance().getBean("jOption");
		    renderer.render();
	    }
	    catch (Exception e) {
		    e.printStackTrace();
	    }

	    System.exit(0);

	}

}
