package pisi.unitedmeows.violentcat.user;

public class DiscordUser {

	private String id;
	private String username;
	private String avatar;
	private int discriminator;
	private int publicFlags;
	private String banner;
	private String bannerColor;
	private String accentColor;

	public DiscordUser(String _id, String _username, String _avatar, int _discriminator, int _publicFlags, String _banner,
					   String _bannerColor, String _accentColor) {
		id = _id;
		username = _username;
		avatar = _avatar;
		discriminator = _discriminator;
		publicFlags = _publicFlags;
		banner = _banner;
		bannerColor = _bannerColor;
		accentColor = _accentColor;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getAvatar() {
		return avatar;
	}

	public int getDiscriminator() {
		return discriminator;
	}

	public int getPublicFlags() {
		return publicFlags;
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
