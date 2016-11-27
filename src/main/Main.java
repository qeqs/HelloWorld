package main;


import main.annotations.Component;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

@Component("Main")
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
	// write your code here

		//HelloWorldFactory.getInstance().getHelloWorld().helloWorld();

	    ApplicationContext context = new ApplicationContext();
	    MessageRenderer renderer= (MessageRenderer) context.getBean("renderer");
	    renderer.render();
    }

}
