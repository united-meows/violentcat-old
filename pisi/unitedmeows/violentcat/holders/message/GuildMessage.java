package pisi.unitedmeows.violentcat.holders.message;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.action.RequestType;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.BasicGuildInfo;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.holders.user.BasicGuildUser;
import pisi.unitedmeows.violentcat.user.DiscordUser;

import java.util.StringJoiner;

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

	public Action<TextChannel> textChannel(RequestType requestType) {
		Action<TextChannel> action = new Action<TextChannel>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, channelId) {
			@Override
			public void run() {
				end(client.getGuild(guildId, requestType).await().getTextChannel(channelId, requestType).await());
			}
		};
		client.discordActionPool().queue(action);
		return action;
	}

	public Action<TextChannel> textChannel() {
		return textChannel(RequestType.CACHE_THEN_REQUEST);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", GuildMessage.class.getSimpleName() + "[", "]")
				.add("basicGuildInfo=" + basicGuildInfo)
				.add("channelId='" + channelId + "'")
				.add("guildId='" + guildId + "'")
				.add("id='" + id + "'")
				.add("sender=" + sender)
				.add("content='" + content + "'")
				.add("tts=" + tts)
				.add("client=" + client)
				.toString();
	}

	public Action<Guild> detailedGuildInfo() {
		return client.getGuild(guildId);
	}

	public BasicGuildInfo getBasicGuildInfo() {
		return basicGuildInfo;
	}
}
