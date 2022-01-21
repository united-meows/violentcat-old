package pisi.unitedmeows.violentcat.user;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.user.BasicUser;

public class DiscordUser extends BasicUser {

	private String banner;
	private String bannerColor;
	private String accentColor;

	public DiscordUser(DiscordClient client, String _id, String _username, String _avatar, int _discriminator,
					   int _publicFlags, String _banner,
					   String _bannerColor, String _accentColor) {
		super(client, _id, _username, _discriminator, _avatar, _publicFlags);
		banner = _banner;
		bannerColor = _bannerColor;
		accentColor = _accentColor;
	}


	public String getBanner() {
		return banner;
	}

	public String getBannerColor() {
		return bannerColor;
	}

	public String getAccentColor() {
		return accentColor;
	}
}
