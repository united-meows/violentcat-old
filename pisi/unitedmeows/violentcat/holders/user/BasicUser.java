package pisi.unitedmeows.violentcat.holders.user;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.user.DiscordUser;

public class BasicUser {

	protected String username;
	protected String id;
	protected String avatar;
	protected int discriminator;
	protected DiscordClient client;
	protected int publicFlags;

	public BasicUser(DiscordClient _client, String _id, String _username, int _discriminator, String _avatar,
					 int _publicFlags) {
		username = _username;
		id = _id;
		avatar = _avatar;
		discriminator = _discriminator;
		client = _client;
		publicFlags = _publicFlags;
	}

	public Action<DiscordUser> detailedUser() {
		return client.getUser(id);
	}

	public String username() {
		return username;
	}

	public String id() {
		return id;
	}

	public String avatar() {
		return avatar;
	}

	public int discriminator() {
		return discriminator;
	}
}
