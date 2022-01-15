package pisi.unitedmeows.violentcat.utils;

public enum Intent {

	GUILDS(0, false),

	GUILD_MEMBERS(1, true),

	GUILD_BANS(2, false),

	GUILD_EMOJIS(3, false),

	GUILD_INTEGRATIONS(4, false),

	GUILD_WEBHOOKS(5, false),

	GUILD_INVITES(6, false),

	GUILD_VOICE_STATES(7, false),

	GUILD_PRESENCES(8, true),

	GUILD_MESSAGES(9, false),

	GUILD_MESSAGE_REACTIONS(10, false),

	GUILD_MESSAGE_TYPING(11, false),

	DIRECT_MESSAGES(12, false),


	DIRECT_MESSAGE_REACTIONS(13, false),

	DIRECT_MESSAGE_TYPING(14, false);

	private final int id;

	private final boolean privileged;


	Intent(int id, boolean privileged) {
		this.id = id;
		this.privileged = privileged;
	}


	public int getId() {
		return id;
	}

	public boolean isPrivileged() {
		return privileged;
	}

	public static int calculateBitmask(Intent... intents) {
		int intentCount = 0;
		for (Intent intentValue : intents) {
			intentCount += (1 << intentValue.getId());
		}
		return intentCount;
	}
}