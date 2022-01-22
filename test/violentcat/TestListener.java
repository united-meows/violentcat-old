package test.violentcat;

import pisi.unitedmeows.eventapi.event.listener.Listener;
import pisi.unitedmeows.violentcat.client.events.GuildMessageEvent;

public class TestListener {

	public Listener<GuildMessageEvent> guildMessageEventListener = new Listener<>(event -> {
		System.out.println("received event");
		if (event.guildMessage().content().startsWith("!ping")) {
			event.guildMessage().textChannel().await().sendMessage("pong");
			event.guildMessage().textChannel().await().sendMessage("naber la :D");
		}
	});



}
