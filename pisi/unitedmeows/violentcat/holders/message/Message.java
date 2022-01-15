package pisi.unitedmeows.violentcat.holders.message;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.user.DiscordUser;

public class Message {

	protected DiscordUser sender;
	protected String content;
	protected DiscordClient client;

	public Message(DiscordClient _client, DiscordUser _sender, String _content) {
		content = _content;
		sender = _sender;
		client = _client;
	}

	public DiscordUser sender() {
		return sender;
	}

	public String content() {
		return content;
	}
}
