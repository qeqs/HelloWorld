package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Properties;

/**
 * Created on 27.11.2016.
 */
public class MessageFactory {
	private static MessageFactory ourInstance = new MessageFactory();

	public static MessageFactory getInstance() {
		return ourInstance;
	}

	private MessageFactory() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("classes.properties"));

			String rendererClass = properties.getProperty("renderer");
			String providerClass = properties.getProperty("provider");

			messageProvider = (MessageProvider)Class.forName(providerClass).newInstance();
			messageRenderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Properties properties;
	private  MessageProvider messageProvider;
	private MessageRenderer messageRenderer;

}
