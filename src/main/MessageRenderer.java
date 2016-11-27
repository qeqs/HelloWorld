package main;

/**
 * Created on 27.11.2016.
 */
@Component("renderer")
public interface MessageRenderer {
	void render();

	@Autowired
	void setMessageProvider(MessageProvider provider);

	MessageProvider getMessageProvider();
}
