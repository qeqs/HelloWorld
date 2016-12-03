package main.annotations.analyzers;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 27.11.2016.
 */
public interface AnnotationAnalyzer {
	void analyze(Class<?> cl,Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException;
}
