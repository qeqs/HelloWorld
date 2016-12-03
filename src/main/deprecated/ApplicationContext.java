package main.deprecated;

import main.annotations.analyzers.AutowiredAnalyzer;
import main.annotations.analyzers.InjectAnalyzer;
import main.annotations.analyzers.AnnotationAnalyzer;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created on 27.11.2016.
 */
@Deprecated
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
		analyzers.add(new AutowiredAnalyzer());

	}

	public Object getBean(String proprety) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Object instance = Class.forName(properties.getProperty(proprety)).newInstance();
		for (AnnotationAnalyzer analyzer : analyzers) {
			analyzer.analyze(instance.getClass(), instance);
		}
		return instance;
	}


}
