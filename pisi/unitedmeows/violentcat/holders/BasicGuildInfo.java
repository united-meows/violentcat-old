package pisi.unitedmeows.violentcat.holders;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.yystal.clazz.prop;

public class BasicGuildInfo {

	protected String id;
	protected DiscordClient client;

	public final prop<Guild> detailedGuild = new prop<Guild>(null) {
		@Override
		public Guild get() {
			if (value == null) {
			//	value = client.getGuild(id);
			}
			return value;
		}

		@Deprecated
		@Override
		public void set(Guild newValue) {}
	};


	public BasicGuildInfo(String _id, DiscordClient _client) {
		id = _id;
		client = _client;
	}
}
