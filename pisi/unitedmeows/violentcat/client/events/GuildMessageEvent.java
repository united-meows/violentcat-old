package pisi.unitedmeows.violentcat.client.events;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.violentcat.holders.message.GuildMessage;
import pisi.unitedmeows.violentcat.holders.user.BasicGuildUser;

public class GuildMessageEvent extends Event {

	private final BasicGuildUser basicGuildUser;
	private final GuildMessage guildMessage;

	public GuildMessageEvent(BasicGuildUser _basicGuildUser, GuildMessage _guildMessage) {
		basicGuildUser = _basicGuildUser;
		guildMessage = _guildMessage;
	}

	public BasicGuildUser basicGuildUser() {
		return basicGuildUser;
	}

	public GuildMessage guildMessage() {
		return guildMessage;
	}
}
