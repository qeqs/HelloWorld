package main.implementations;

import main.annotations.*;
import main.annotations.Component;
import main.interfaces.MessageProvider;
import main.interfaces.MessageRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 28.11.2016.
 */
@Component("joption")
public class JOptionMessageRenderer implements MessageRenderer {
	MessageProvider messageProvider;
	@Override
	public void render() {
		//JOptionPane.showConfirmDialog(null,messageProvider.getMessage());
		JFrame frame = new JFrame(messageProvider.getMessage());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JOptionPane.showConfirmDialog(frame,messageProvider.getMessage(),"Information",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	}

	@Autowired
	@Override
	public void setMessageProvider(MessageProvider provider) {
		messageProvider = provider;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}
}
