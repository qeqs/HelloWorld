package main.interfaces;

import main.annotations.Component;

/**
 * Created on 27.11.2016.
 */
@Component("provider")
public interface MessageProvider {
	String getMessage();
}
