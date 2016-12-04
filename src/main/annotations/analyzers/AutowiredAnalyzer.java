package main.annotations.analyzers;


import main.ClassSearcher;
import main.Context;
import main.annotations.AutoInject;
import main.annotations.Autowired;
import main.annotations.Component;
import main.annotations.Qualifier;

import java.io.File;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 28.11.2016.
 */
public class AutowiredAnalyzer implements AnnotationAnalyzer {

	public AutowiredAnalyzer() {

	}

	@Override
	public void analyze(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
		Method[] methods = cl.getDeclaredMethods();
		Field[] fields = cl.getDeclaredFields();
		Constructor<?>[] constructors = cl.getDeclaredConstructors();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Autowired.class)) {

				int modifiers = method.getModifiers();
				method.setAccessible(true);

				ArrayList<Object> params = new ArrayList<Object>();

				for (Class<?> type : method.getParameterTypes()) {

					if(type.isAnnotationPresent(Qualifier.class)) {
						Qualifier qualifier = type.getAnnotation(Qualifier.class);
						if(qualifier.name().equals(""))
							params.add(Context.getInstance().getBean(qualifier.clazz()));
						else
							params.add(Context.getInstance().getBean(qualifier.name()));
					}
					else
					if (Context.getInstance().getComponents().containsValue(type)) {
						if (!type.isInterface()) {
							params.add(Context.getInstance().getBean(type));
						} else {
							for (Object entry : ((HashMap) Context.getInstance().getComponents()).values()) {
								if (!((Class<?>) entry).isInterface() && type.isAssignableFrom(((Class<?>) entry))) {
									params.add(Context.getInstance().getBean((Class<?>) entry));
									break;
								}
							}
						}

					}
				}
				method.invoke(instance,params.toArray());

				if(Modifier.isPrivate(modifiers)||Modifier.isProtected(modifiers))
					method.setAccessible(false);
			}
		}
		for (Field field : fields) {
			if (field.isAnnotationPresent(AutoInject.class)) {

				int modifiers = field.getModifiers();
				field.setAccessible(true);
				if(field.isAnnotationPresent(Qualifier.class)) {
					Qualifier qualifier = field.getAnnotation(Qualifier.class);
					if(qualifier.name().equals(""))
						field.set(instance,Context.getInstance().getBean(qualifier.clazz()));
					else
						field.set(instance,Context.getInstance().getBean(qualifier.name()));
				}
				else
				if (!field.getType().isInterface()) field.set(instance, Context.getInstance().getBean(field.getType()));
				else
				{
					for (Object entry:((HashMap)Context.getInstance().getComponents()).values()) {
						if(!((Class<?>)entry).isInterface()&&field.getType().isAssignableFrom(((Class<?>)entry))) {
							field.set(instance,Context.getInstance().getBean((Class<?>) entry));
							break;
						}
					}
				}

				if(Modifier.isPrivate(modifiers)||Modifier.isProtected(modifiers))
					field.setAccessible(false);
			}

		}

		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(AutoInject.class)) {
				ArrayList<Object> params = new ArrayList<Object>();
				for (Class<?> type : constructor.getParameterTypes()) {
					if (Context.getInstance().getComponents().containsValue(type))
						params.add(Context.getInstance().getBean(type));
				}
				instance = constructor.newInstance(params.toArray());

			}
		}
	}
}
