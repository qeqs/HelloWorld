package main;

/**
 * Created on 27.11.2016.
 */
public class SystemOutMessageRenderer implements MessageRenderer {
	MessageProvider messageProvider;
	@Override
	public void render() {
		if(messageProvider!=null){
			System.out.println(messageProvider.getMessage());
		}
	}

	@Override
	public void setMessageProvider(MessageProvider provider) {
		messageProvider = provider;
	}
	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
}
