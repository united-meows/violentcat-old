package pisi.unitedmeows.violentcat.holders.message;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.BasicGuildInfo;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.holders.user.BasicGuildUser;
import pisi.unitedmeows.violentcat.user.DiscordUser;

public class GuildMessage extends Message
{
	protected BasicGuildInfo basicGuildInfo;
	protected String channelId;
	protected String guildId;

	public GuildMessage(DiscordClient _client, String _id, BasicGuildUser _sender, String _channelId, String _content, boolean _tts,
						String _guildId) {
		super(_client, _id, _sender, _content, _tts);


		guildId = _guildId;
		channelId = _channelId;
	}

	public BasicGuildUser getSender() {
		return (BasicGuildUser) sender;
	}

	public Action<TextChannel> textChannel() {
		Action<TextChannel> action = new Action<TextChannel>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, channelId) {
			@Override
			public void run() {
				end(client.getGuild(guildId).await().getTextChannel(channelId).await());
			}
		};
		client.discordActionPool().queue(action);
		return action;
	}

	public Action<Guild> detailedGuildInfo() {
		return client.getGuild(guildId);
	}

	public BasicGuildInfo getBasicGuildInfo() {
		return basicGuildInfo;
	}
}
