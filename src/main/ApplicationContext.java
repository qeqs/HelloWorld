package main;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created on 27.11.2016.
 */
public class ApplicationContext {


	private Properties properties;
	public ApplicationContext(){
		{
			properties = new Properties();
			try {
				properties.load(new FileInputStream("classes.properties"));

				String rendererClass = properties.getProperty("renderer");
				String providerClass = properties.getProperty("provider");

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	Object getBean(String proprety) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return Class.forName(properties.getProperty(proprety)).newInstance();
	}
}
