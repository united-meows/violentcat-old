package pisi.unitedmeows.violentcat.holders.message;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.user.BasicUser;
import pisi.unitedmeows.violentcat.user.DiscordUser;

public class Message {

	protected String id;
	protected BasicUser sender;
	protected String content;
	protected boolean tts;
	protected DiscordClient client;

	public Message(DiscordClient _client, String _id, BasicUser _sender, String _content, boolean _tts) {
		id = _id;
		content = _content;
		sender = _sender;
		client = _client;
		tts = _tts;
	}

	public boolean tts() {
		return tts;
	}

	public String id() {
		return id;
	}

	public BasicUser sender() {
		return sender;
	}

	public String content() {
		return content;
	}
}
