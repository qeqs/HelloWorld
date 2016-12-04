package main.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 04.12.2016.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface PostConstruct {}
