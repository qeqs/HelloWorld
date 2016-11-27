package main.deprecated;

/**
 * Created on 27.11.2016.
 */
public class HelloWorldFactory {
	private static HelloWorldFactory instance;

	public static HelloWorldFactory getInstance() {
		if(instance==null)instance=new HelloWorldFactory();
		return instance;
	}
	public HelloWorld getHelloWorld(){
		HelloWorld helloWorldInstance = null;
		try {
			helloWorldInstance = (HelloWorld)Class.forName(HelloWorld.class.getName()).newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return helloWorldInstance;
	}

}
