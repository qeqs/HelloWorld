package main;

import main.annotations.Component;
import main.annotations.analyzers.AnnotationAnalyzer;
import main.annotations.analyzers.AutowiredAnalyzer;
import main.annotations.analyzers.PostConstructAnalyzer;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created on 03.12.2016.
 */
public class Context {
	private ArrayList<AnnotationAnalyzer> analyzers = new ArrayList<AnnotationAnalyzer>();
	private java.util.Properties properties;
	private final Map<String,Class<?>> components;
	private static Context instance;
	static
	{
		instance = new Context();
	}
	private Context() {


		components =  ClassSearcher.getClassesFromPackage(new File("out\\production\\springTest\\classes\\"),"", Component.class);
		//analyzers.add(new InjectAnalyzer(properties));
		analyzers.add(new AutowiredAnalyzer());
		analyzers.add(new PostConstructAnalyzer());
	}

	public static Context getInstance() {
		return instance;
	}


	public Object getBean(String componentName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

		return getBean(components.get(componentName));
	}
	public Object getBean(Class<?> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

		Object instance = clazz.newInstance();

		for (AnnotationAnalyzer analyzer : analyzers) {
			analyzer.analyze(instance.getClass(), instance);
		}
		return instance;
	}

	@Deprecated
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Map<String,Class<?>> getComponents() {
		return (Map<String,Class<?>>)((HashMap<String,Class<?>>)components).clone();
	}
}