package test.violentcat;

import pisi.unitedmeows.eventapi.event.listener.Listener;
import pisi.unitedmeows.violentcat.client.events.GuildMessageEvent;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;

public class TestListener {

	public Listener<GuildMessageEvent> guildMessageEventListener = new Listener<>(event -> {
		System.out.println("received event");
		if (event.guildMessage().content().startsWith("!deletechannel")) {
			TextChannel channel = event.guildMessage().textChannel().await();;
			channel.sendMessage("deleting channel");
			event.guildMessage().textChannel().await().deleteChannel("932026414641848360");
		}
	});



}
