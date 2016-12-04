package main.annotations.analyzers;

import main.annotations.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 04.12.2016.
 */
public class PostConstructAnalyzer implements AnnotationAnalyzer{

	@Override
	public void analyze(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
		Method[] methods = cl.getDeclaredMethods();
		for (Method method: methods
		     ) {
			if(method.isAnnotationPresent(PostConstruct.class)&&method.getParameterCount()==0) {
				method.invoke(instance);
			}
		}
	}
}
