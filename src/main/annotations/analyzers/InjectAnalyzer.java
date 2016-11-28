package main.annotations.analyzers;

import main.annotations.AutoInject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created on 27.11.2016.
 */
public class InjectAnalyzer implements AnnotationAnalyzer {
	private Properties props;
	public InjectAnalyzer(Properties props){
		this.props = props;
	}
	@Override
	public void analyze(Class<?> cl,Object instance)  {
		Method[] methods = cl.getMethods();
		Field[] fields = cl.getFields();
		Constructor<?>[] constructors = cl.getConstructors();
		for (Method method: methods ) {
			if (method.isAnnotationPresent(AutoInject.class)) {
				try {
					method.invoke(instance, Class.forName(props.getProperty(method.getAnnotation(AutoInject.class).value())).newInstance());
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
		for (Field field:fields ) {
			if (field.isAnnotationPresent(AutoInject.class)) try {
				field.set(instance, Class.forName(props.getProperty(field.getAnnotation(AutoInject.class).value())).newInstance());
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
		for (Constructor<?> contructor:constructors ) {
			if(contructor.isAnnotationPresent(AutoInject.class)) try {
				instance = contructor.newInstance(Class.forName(props.getProperty(contructor.getAnnotation(AutoInject.class).value())).newInstance());
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
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
