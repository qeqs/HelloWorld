package main.implementations;

import main.HelloWorldComparator;
import main.annotations.*;
import main.annotations.Component;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 28.11.2016.
 */
@Component("jOption")
public class JOptionMessageRenderer implements MessageRenderer {
	MessageProvider messageProvider;

	@PostConstruct
	public void render() {
		//JOptionPane.showConfirmDialog(null,messageProvider.getMessage());
		if (messageProvider == null) return;
		JFrame frame = new JFrame(messageProvider.getMessage());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JOptionPane.showConfirmDialog(frame, messageProvider.getMessage(), "Information", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	@Autowired
	public void setMessageProvider(@Qualifier(name = "helloWorld") MessageProvider provider) {
		@ValidateString(comparator = HelloWorldComparator.class,value = "Hello World")String check = provider.getMessage();
		messageProvider = provider;
	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
}
