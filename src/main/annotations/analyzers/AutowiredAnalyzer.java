package main.annotations.analyzers;


import main.ClassSearcher;
import main.annotations.AutoInject;
import main.annotations.Autowired;
import main.annotations.Component;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 28.11.2016.
 */
public class AutowiredAnalyzer implements AnnotationAnalyzer {

	List<Class<?>> components;

	public AutowiredAnalyzer(){

		components =  ClassSearcher.getClassesForPackageAndAnnotation(new File(".\\"),"", Component.class);
	}

	@Override
	public void analyze(Class<?> cl, Object instance) {
		Method[] methods = cl.getMethods();
		Field[] fields = cl.getFields();
		Constructor<?>[] constructors = cl.getConstructors();
		for (Method method: methods ) {
			if (method.isAnnotationPresent(Autowired.class)) {
				try {
					for (Class<?> clazz: components) {
						//if(components.contains(method.getParameterTypes()[0]))
						method.invoke(instance, clazz.newInstance());
					}
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
				catch (IllegalArgumentException e){

				}
			}
		}
		for (Field field:fields ) {
			if (field.isAnnotationPresent(AutoInject.class)) try {
				for (Class<?> clazz: components)
				field.set(instance, clazz.newInstance());
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalArgumentException e){

			}
		}
		for (Constructor<?> constructor:constructors ) {
			if(constructor.isAnnotationPresent(AutoInject.class)) try {
				if(constructor.getParameterCount()==1)
				for (Class<?> clazz: components)
				instance = constructor.newInstance(clazz.newInstance());

			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			catch (IllegalArgumentException e){

			}
		}
	}
}
