package main.implementations;

import main.annotations.*;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

/**
 * Created on 27.11.2016.
 */
@Component("system.out")
public class SystemOutMessageRenderer implements MessageRenderer {
	MessageProvider messageProvider;

	@PostConstruct
	public void render() {
		if(messageProvider!=null){
			System.out.println(messageProvider.getMessage());
		}
	}

	@AutoInject("provider")
	@Autowired
	public void setMessageProvider( MessageProvider provider) {
		messageProvider = provider;
	}
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
}
