package main;

import main.implementations.InjectAnalyzer;
import main.interfaces.AnnotationAnalyzer;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created on 27.11.2016.
 */
public class ApplicationContext {


	private java.util.Properties properties;
	private ArrayList<AnnotationAnalyzer> analyzers = new ArrayList<AnnotationAnalyzer>();

	public ApplicationContext() {
		properties = new java.util.Properties();
		try {
			properties.load(new FileInputStream("classes.properties"));
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		analyzers.add(new InjectAnalyzer(properties));


	}

	Object getBean(String proprety) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Object instance = Class.forName(properties.getProperty(proprety)).newInstance();
		for (AnnotationAnalyzer analyzer : analyzers) {
			analyzer.analyze(instance.getClass(), instance);
		}
		return instance;
	}


}
