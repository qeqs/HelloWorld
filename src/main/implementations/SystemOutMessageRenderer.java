package main.implementations;

import main.annotations.AutoInject;
import main.annotations.Autowired;
import main.annotations.Component;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

/**
 * Created on 27.11.2016.
 */
@Component("system.out")
public class SystemOutMessageRenderer implements MessageRenderer {
	MessageProvider messageProvider;
	@Override
	public void render() {
		if(messageProvider!=null){
			System.out.println(messageProvider.getMessage());
		}
	}

	@AutoInject("provider")
	@Override
	public void setMessageProvider(MessageProvider provider) {
		messageProvider = provider;
	}
	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
}
