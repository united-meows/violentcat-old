package pisi.unitedmeows.violentcat.client;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.action.DiscordActionPool;
import pisi.unitedmeows.violentcat.action.RequestType;
import pisi.unitedmeows.violentcat.client.gateway.DiscordClientGateway;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.IdentifySignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.PresenceUpdateSignal;
import pisi.unitedmeows.violentcat.holders.ApplicationInfo;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.slashcmd.SlashCommandCreator;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.violentcat.user.DiscordUser;
import pisi.unitedmeows.violentcat.user.SelfUser;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Intent;
import pisi.unitedmeows.violentcat.utils.JsonUtil;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.utils.Capsule;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class DiscordClient {

	protected AccountType accountType;
	protected String token;

	protected DiscordClientGateway clientGateway;

	/* create self user info holder class */
	protected Presence presence;

	private SelfUser selfUser;

	private ApplicationInfo applicationInfo;
	protected DiscordActionPool discordActionPool;
	protected DiscordClient _self;
	protected BasicEventSystem eventSystem;

	protected YWebClient webClient;
	protected int intents = Intent.calculateBitmask(Intent.DIRECT_MESSAGES,
			Intent.GUILD_MESSAGES, Intent.GUILD_MESSAGE_REACTIONS,
			Intent.GUILDS, Intent.GUILD_MEMBERS);

	protected static Cache<String, Guild> _GUILD_CACHE;

	static {
		_GUILD_CACHE = Caffeine.newBuilder().maximumSize(100)
				.expireAfterWrite(Duration.ofMinutes(2))
				.build();
	}

	public DiscordClient(AccountType _accountType, String _token) {
		accountType = _accountType;
		token = _token;
		webClient = createWebClient(token);
		_self = this;
		discordActionPool = new DiscordActionPool();
		discordActionPool.start();
		eventSystem = new BasicEventSystem();

	}

	public DiscordClient login(Capsule optional) {
		String result = webClient.downloadString("https://discord.com/api/v9/oauth2/applications/@me");
		applicationInfo = new GsonWrap<ApplicationInfo>(result) { }.build(); /* viserys :D */
		System.out.println(applicationInfo);
		if (applicationInfo == null) {

			kThread.sleep(1000000);
		}
		try {
			clientGateway = new DiscordClientGateway(this, new URI("wss://gateway.discord.gg/?v=9&encoding=json"));
		} catch (Exception ex) {}
		clientGateway.connect();
		clientGateway.login(token, optional);
		return this;
	}


	public BasicEventSystem eventSystem() {
		return eventSystem;
	}

	public DiscordClient setPresence(Presence _presence) {
		presence = _presence;
		if (clientGateway != null) {
			clientGateway.send(new PresenceUpdateSignal(presence));
		}
		return this;
	}

	public String getToken() {
		return token;
	}

	public Action<Guild> getGuild(String guildId) {
		return getGuild(guildId, RequestType.CACHE_THEN_REQUEST);
	}


	/* add missing array elements */
	public Action<Guild> getGuild(String guildId, final RequestType requestType) {
		Action<Guild> action =  new Action<Guild>(discordActionPool(), Action.MajorParameter.GUILD_ID, guildId) {
			@Override
			public void run() {

				if (requestType == RequestType.CACHE_THEN_REQUEST) {
					Guild guild = _GUILD_CACHE.getIfPresent(guildId);
					if (guild != null) {
						endNoRate(guild);
						return;
					}
				}

				String jsonResult = webClient.downloadString("https://discord.com/api/v9/guilds/" + guildId);
				JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
				JsonArray jsonArray = data.getAsJsonArray("emojis");
				String id = JsonUtil.getString(data.get("id"));
				String name = JsonUtil.getString(data.get("name"));
				String icon = JsonUtil.getString(data.get("icon"));
				String desc = JsonUtil.getString(data.get("description"));
				String splash = JsonUtil.getString(data.get("splash"));
				String discovery_splash = JsonUtil.getString(data.get("discovery_splash"));
				/**
				 *  "emojis":[
				 *
				 *    ],
				 *    "stickers":[
				 *
				 *    ],
				 */
				String banner = JsonUtil.getString(data.get("banner"));
				String owner_id = JsonUtil.getString(data.get("owner_id"));
				String application_id = JsonUtil.getString(data.get("application_id"));
				String region = JsonUtil.getString(data.get("region"));
				String afk_channel_id = JsonUtil.getString(data.get("afk_channel_id"));
				int afk_timeout = JsonUtil.getInt(data.get("afk_timeout"));
				String system_channel_id = JsonUtil.getString(data.get("system_channel_id"));
				boolean widget_enabled = JsonUtil.getBoolean(data.get("widget_enabled"));
				String widget_channel_id = JsonUtil.getString(data.get("widget_channel_id"));
				int verification_level = JsonUtil.getInt(data.get("verification_level"));
				int default_message_notifications = JsonUtil.getInt(data.get("default_message_notifications"));
				int mfa_level = JsonUtil.getInt(data.get("mfa_level"));
				int explicit_content_filter = JsonUtil.getInt(data.get("explicit_content_filter"));
				String max_presences = JsonUtil.getString(data.get("max_presences"));
				int max_members = JsonUtil.getInt(data.get("max_members"));
				int max_video_channel_users = JsonUtil.getInt(data.get("max_video_channel_users"));
				String vanity_url_code = JsonUtil.getString(data.get("vanity_url_code"));
				int premium_tier = JsonUtil.getInt(data.get("premium_tier"));
				int premium_subscription_count = JsonUtil.getInt(data.get("premium_subscription_count"));
				int system_channel_flags = JsonUtil.getInt(data.get("system_channel_flags"));
				String preferred_locale = JsonUtil.getString(data.get("preferred_locale"));
				String rules_channel_id = JsonUtil.getString(data.get("rules_channel_id"));
				String public_updates_channel_id = JsonUtil.getString(data.get("public_updates_channel_id"));
				String hub_type = JsonUtil.getString(data.get("hub_type"));
				boolean premium_progress_bar_enabled = JsonUtil.getBoolean(data.get("premium_progress_bar_enabled"));
				boolean nsfw = JsonUtil.getBoolean(data.get("nsfw"));
				int nsfw_level = JsonUtil.getInt(data.get("nsfw_level"));

				Guild guild = new Guild(_self, id, name, icon, desc, splash, discovery_splash, banner, owner_id,
						application_id, region, afk_channel_id, afk_timeout, system_channel_id, widget_enabled,
						widget_channel_id, verification_level, default_message_notifications, mfa_level,
						explicit_content_filter, max_presences, max_members, max_video_channel_users, vanity_url_code,
						premium_tier, premium_subscription_count, system_channel_flags, preferred_locale, rules_channel_id,
						public_updates_channel_id, hub_type, premium_progress_bar_enabled, nsfw, nsfw_level);

				_GUILD_CACHE.put(id, guild);
				/* add to cache */
				end(guild);
			}
		};
		discordActionPool.queue(action);
		return action;
	}

	public Action<DiscordUser> getUser(String id) {
		Action<DiscordUser> userAction = new Action<DiscordUser>(discordActionPool(), Action.MajorParameter.USER_ID, id) {
			@Override
			public void run() {
				String jsonResult = webClient.downloadString("https://discord.com/api/v9/users/" + id);
				JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
				final String userId = JsonUtil.getString(data.get("id"));
				final String userName = JsonUtil.getString(data.get("username"));
				final String avatarId = JsonUtil.getString(data.get("avatar"));
				final int discriminator = JsonUtil.getInt(data.get("discriminator"));
				final int publicFlags = JsonUtil.getInt(data.get("public_flags"));
				final String banner = JsonUtil.getString(data.get("banner"));
				final String bannerColor = JsonUtil.getString(data.get("banner_color"));
				final String accent_color = JsonUtil.getString(data.get("accent_color"));

				end(new DiscordUser(_self, userId, userName, avatarId, discriminator, publicFlags, banner, bannerColor,
						accent_color));
			}
		};

		discordActionPool.queue(userAction);
		return userAction;
	}

	/* change to action<SlashCommand> */
	public Action<Boolean> createSlashCommand(SlashCommandCreator commandCreator) {

		Action<Boolean> action = new Action<Boolean>(discordActionPool(), Action.MajorParameter.APPLICATION_ID,
				applicationInfo.id()) {
			@Override
			public void run() {
				Gson gson = new Gson();
				String json = gson.toJson(commandCreator.json());
				end( webClient.postRequest(
						String.format("https://discord.com/api/v9/applications/%s/commands",
								applicationInfo.id()), json)
						!= null);
			}
		};
		action.queue();
		return action;
	}

	public SelfUser selfUser() {
		return selfUser;
	}

	protected static YWebClient createWebClient(String token) {
		YWebClient webClient = new YWebClient();
		webClient.header("Authorization", "Bot " + token);
		webClient.setUserAgent("cats");
		return webClient;
	}

	public Presence presence() {
		return presence;
	}

	public void setIntents(int intents) {
		this.intents = intents;
	}

	public int intents() {
		return intents;
	}

	@Deprecated
	public void setSelfUser(SelfUser selfUser) {
		this.selfUser = selfUser;
	}

	public DiscordActionPool discordActionPool() {
		return discordActionPool;
	}

	@Deprecated
	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	public ApplicationInfo applicationInfo() {
		return applicationInfo;
	}

	public DiscordClient login() {
		return login(Capsule.of());
	}

	public DiscordClient awaitReady() {
		while (applicationInfo == null) {
			kThread.sleep(50);
		}

		return this;
	}

	public DiscordClient addListener(Object o) {
		eventSystem.subscribeAll(o);
		return this;
	}

	public DiscordClientGateway clientGateway() {
		return clientGateway;
	}

	public YWebClient webClient() {
		return webClient;
	}

	public AccountType accountType() {
		return accountType;
	}
}
