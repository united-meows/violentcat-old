package pisi.unitedmeows.violentcat.holders.guild;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.action.RequestType;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.yystal.clazz.prop;

public class BasicGuildInfo {

	protected String id;
	protected DiscordClient client;

	public final prop<Guild> detailedGuild = new prop<Guild>(null) {
		@Override
		public Guild get() {
			if (value == null) {
				value = client.getGuild(id).await();
			}
			return value;
		}

		@Deprecated
		@Override
		public void set(Guild newValue) {}
	};


	@SuppressWarnings("unchecked")
	public Action<Boolean> kickMember(String userId, String reason) {
		Action<Boolean> action = new Action<Boolean>(client.discordActionPool(), Action.MajorParameter.GUILD_ID, id()) {
			@Override
			public void run() {
				client.webClient().header("X-Audit-Log-Reason", reason);
				client.webClient().deleteRequest(String.format("https://discord.com/api/v9/guilds/%s/members/%s",
						id(), userId));
				client.webClient().headers().remove("X-Audit-Log-Reason");
			}
		};
		action._queue();
		return action;
	}

	@SuppressWarnings("unchecked")
	public Action<Boolean> kickMember(String userId) {
		Action<Boolean> action = new Action<Boolean>(client.discordActionPool(), Action.MajorParameter.GUILD_ID, id()) {
			@Override
			public void run() {
				client.webClient().deleteRequest(String.format("https://discord.com/api/v9/guilds/%s/members/%s",
						id(), userId));
			}
		};
		action._queue();
		return action;
	}

	public String id() {
		return id;
	}

	public Action<GuildPreview> preview() {
		return client.guildPreview(id);
	}

	public Action<GuildPreview> preview(RequestType requestType) {
		return client.guildPreview(id, requestType);
	}

	public BasicGuildInfo(String _id, DiscordClient _client) {
		id = _id;
		client = _client;
	}
}
