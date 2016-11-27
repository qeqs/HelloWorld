package main;


import java.lang.reflect.Method;

@Component("Main")
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
	// write your code here

		//HelloWorldFactory.getInstance().getHelloWorld().helloWorld();

	    ApplicationContext context = new ApplicationContext();
	    MessageProvider provider= (MessageProvider) context.getBean("provider");
	    MessageRenderer renderer= (MessageRenderer) context.getBean("renderer");
	    renderer.setMessageProvider(provider);
	    renderer.render();
    }

}
