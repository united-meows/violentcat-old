package pisi.unitedmeows.violentcat.holders;

import pisi.unitedmeows.violentcat.client.DiscordClient;

import java.awt.image.BufferedImage;

public class Guild {

	protected String id;
	protected String name;
	protected String icon;
	protected String desc;
	protected String splash;
	protected String discovery_splash;
	protected String banner;
	protected String ownerId;
	protected String applicationId;
	protected String region;
	protected String afkChannelId;
	protected int afkTimeout;
	protected String systemChannelId;
	protected boolean widgetEnabled;
	protected String widgetChannelId;
	protected int verificationLevel;
	protected int defaultMessageNotifications;
	protected int mfaLevel;
	protected int explicitContentFilter;
	protected String maxPresences;
	protected int maxMembers;
	protected int maxVideoChannelUsers;
	protected String vanityUrl;
	protected int premiumTier;
	protected int premiumSubscriptionCount;
	protected int systemChannelFlags;
	protected String preferredLocale;
	protected String rulesChannelId;
	protected String publicUpdatesChannelId;
	protected String hubType;
	protected boolean premiumProgressBarEnabled;
	protected boolean nsfw;
	protected int nsfwLevel;
	/* emojis */
	/* stickers */


	protected DiscordClient client;

	public Guild(DiscordClient client, String id, String name, String icon, String desc,
				 String splash, String discovery_splash, String banner, String ownerId,
				 String applicationId, String region, String afkChannelId, int afkTimeout,
				 String systemChannelId, boolean widgetEnabled, String widgetChannelId,
				 int verificationLevel, int defaultMessageNotifications, int mfaLevel,
				 int explicitContentFilter, String maxPresences, int maxMembers,
				 int maxVideoChannelUsers, String vanityUrl, int premiumTier,
				 int premiumSubscriptionCount, int systemChannelFlags, String preferredLocale,
				 String rulesChannelId, String publicUpdatesChannelId, String hubType,
				 boolean premiumProgressBarEnabled, boolean nsfw, int nsfwLevel) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.desc = desc;
		this.splash = splash;
		this.discovery_splash = discovery_splash;
		this.banner = banner;
		this.ownerId = ownerId;
		this.applicationId = applicationId;
		this.region = region;
		this.afkChannelId = afkChannelId;
		this.afkTimeout = afkTimeout;
		this.systemChannelId = systemChannelId;
		this.widgetEnabled = widgetEnabled;
		this.widgetChannelId = widgetChannelId;
		this.verificationLevel = verificationLevel;
		this.defaultMessageNotifications = defaultMessageNotifications;
		this.mfaLevel = mfaLevel;
		this.explicitContentFilter = explicitContentFilter;
		this.maxPresences = maxPresences;
		this.maxMembers = maxMembers;
		this.maxVideoChannelUsers = maxVideoChannelUsers;
		this.vanityUrl = vanityUrl;
		this.premiumTier = premiumTier;
		this.premiumSubscriptionCount = premiumSubscriptionCount;
		this.systemChannelFlags = systemChannelFlags;
		this.preferredLocale = preferredLocale;
		this.rulesChannelId = rulesChannelId;
		this.publicUpdatesChannelId = publicUpdatesChannelId;
		this.hubType = hubType;
		this.premiumProgressBarEnabled = premiumProgressBarEnabled;
		this.nsfw = nsfw;
		this.nsfwLevel = nsfwLevel;
		this.client = client;
	}

	public TextChannel getTextChannel(String channelId) {
		return null;
	}

	public /*Channel */void getChannel(String channelId) {

	}

	public /*VoiceChannel*/void getVoiceChannel(String channelId) {

	}
}
