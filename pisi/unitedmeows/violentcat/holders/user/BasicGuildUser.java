package pisi.unitedmeows.violentcat.holders.user;

import pisi.unitedmeows.violentcat.client.DiscordClient;

public class BasicGuildUser extends BasicUser
{

	protected String joinDate;
	protected String hoistedRole;
	protected boolean muted, deaf;
	/* roles */
	public BasicGuildUser(DiscordClient _client, String _id, String _username, int _discriminator, String _avatar,
						  int _publicFlags, String _joinDate, String _hoistedRole, boolean _muted, boolean _deaf) {
		super(_client, _id, _username, _discriminator, _avatar, _publicFlags);
		muted = _muted;
		joinDate = _joinDate;
		hoistedRole = _hoistedRole;
		deaf = _deaf;
	}


}
