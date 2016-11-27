package main.interfaces;

import main.annotations.Autowired;
import main.annotations.Component;
import main.annotations.AutoInject;

/**
 * Created on 27.11.2016.
 */
@Component("renderer")
public interface MessageRenderer {
	void render();

	void setMessageProvider(MessageProvider provider);

	MessageProvider getMessageProvider();
}
