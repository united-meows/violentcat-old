package pisi.unitedmeows.violentcat.holders.message;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.BasicGuildInfo;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.user.DiscordUser;

public class GuildMessage extends Message
{
	protected BasicGuildInfo basicGuildInfo;
	protected TextChannel channel;

	public GuildMessage(DiscordClient _client, DiscordUser _sender, TextChannel _channel,  String _content,
						BasicGuildInfo _basicGuildInfo) {
		super(_client, _sender, _content);
		basicGuildInfo = _basicGuildInfo;
		channel = _channel;
	}

	public TextChannel getChannel() {
		return channel;
	}

	public BasicGuildInfo getBasicGuildInfo() {
		return basicGuildInfo;
	}
}
