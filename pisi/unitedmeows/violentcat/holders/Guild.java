package pisi.unitedmeows.violentcat.holders;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.action.RequestType;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.channel.Channel;
import pisi.unitedmeows.violentcat.holders.channel.channels.*;
import pisi.unitedmeows.violentcat.slashcmd.SlashCommandCreator;
import pisi.unitedmeows.violentcat.utils.JsonUtil;
import pisi.unitedmeows.yystal.utils.Iterate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public List<TextChannel> textChannels() {
        List<TextChannel> channels = new ArrayList<>();
        channelsAsIterable((x) -> {
            if (x instanceof TextChannel) {
                channels.add((TextChannel) x);
            }
            return true;
        });

        return channels;
    }

    public List<StageChannel> stageChannels() {
        List<StageChannel> channels = new ArrayList<>();
        channelsAsIterable((x) -> {
            if (x instanceof StageChannel) {
                channels.add((StageChannel) x);
            }
            return true;
        });

        return channels;
    }

    public List<VoiceChannel> voiceChannels() {
        List<VoiceChannel> channels = new ArrayList<>();
        channelsAsIterable((x) -> {
            if (x instanceof VoiceChannel) {
                channels.add((VoiceChannel) x);
            }
            return true;
        });

        return channels;
    }

    public List<CategoryChannel> categoryChannels() {
        List<CategoryChannel> channels = new ArrayList<>();
        channelsAsIterable((x) -> {
            if (x instanceof CategoryChannel) {
                channels.add((CategoryChannel) x);
            }
            return true;
        });

        return channels;
    }


    public List<NewsChannel> newsChannels() {
        List<NewsChannel> channels = new ArrayList<>();
        channelsAsIterable((x) -> {
            if (x instanceof NewsChannel) {
                channels.add((NewsChannel) x);
            }
            return true;
        });

        return channels;
    }


    public List<Channel> channels() {
        List<Channel> channels = new ArrayList<>();
        channelsAsIterable(channels::add);
        return channels;
    }

    public <X extends TextChannel> Action<X> getTextChannel(String channelId) { return getChannel(channelId); }
    public <X extends TextChannel> Action<X> getTextChannel(String channelId, RequestType requestType)
    { return getChannel(channelId, requestType); }

    public <X extends VoiceChannel> Action<X> getVoiceChannel(String channelId) { return getChannel(channelId); }
    public <X extends VoiceChannel> Action<X> getVoiceChannel(String channelId, RequestType requestType)
    { return getChannel(channelId, requestType); }

    public <X extends NewsChannel> Action<X> getNewsChannel(String channelId) { return getChannel(channelId); }
    public <X extends NewsChannel> Action<X> getNewsChannel(String channelId, RequestType requestType)
    { return getChannel(channelId, requestType); }

    public <X extends StageChannel> Action<X> getStageChannel(String channelId) { return getChannel(channelId); }
    public <X extends StageChannel> Action<X> getStageChannel(String channelId, RequestType requestType)
    { return getChannel(channelId, requestType); }

    public <X extends CategoryChannel> Action<X> getCategory(String channelId) { return getChannel(channelId); }
    public <X extends CategoryChannel> Action<X> getCategory(String channelId, RequestType requestType)
    { return getChannel(channelId, requestType); }


    @SuppressWarnings("unchecked")
    public <X extends Channel> Action<X> getChannel(String channelId) {
        return getChannel(channelId, RequestType.CACHE_THEN_REQUEST);
    }

    @SuppressWarnings("unchecked")
    public <X extends Channel> Action<X> getChannel(String channelId, RequestType requestType) {
        Action<X> action = new Action<X>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, channelId) {
            @Override
            public void run() {
                if (requestType == RequestType.CACHE_THEN_REQUEST) {
                    Channel cacheChannel = Channel._CHANNEL_CACHE.getIfPresent(channelId);
                    if (cacheChannel != null) {
                        System.out.println("got from cache channel");
                        endNoRate((X) cacheChannel);
                        return;
                    }
                }

                AtomicReference<X> channel = new AtomicReference<>(null);
                channelsAsIterable(x -> {
                    Channel._CHANNEL_CACHE.put(x.id(), x);
                    if (x.id().equalsIgnoreCase(channelId)) {
                        channel.set((X) x);
                        return false;
                    }
                    return true;
                });
                end(channel.get());
            }
        };
        client.discordActionPool().queue(action);
        return action;
    }


    public Action<Boolean> createSlashCommand(SlashCommandCreator commandCreator) {
        Action<Boolean> action = new Action<Boolean>(client.discordActionPool(), Action.MajorParameter.GUILD_ID,
                id()) {
            @Override
            public void run() {
                Gson gson = new Gson();
                String json = gson.toJson(commandCreator.json());
                end( client.webClient().postRequest(
                        String.format("https://discord.com/api/v9/applications/%s/guilds/%s/commands",
                                client.applicationInfo().id(), id()), json)
                        != null);
            }
        };
        action.queue();
        return action;
    }

    @SuppressWarnings("unchecked")
    public <X extends Channel> void channelsAsIterable(Iterate<X> iterate) {
        String jsonResult = client.webClient().downloadString("https://discord.com/api/v9/guilds/" + id + "/channels");
        JsonArray data = new JsonParser().parse(jsonResult).getAsJsonArray();
        for (int i = 0; i < data.size(); i++) {
            String name = JsonUtil.getString(data.get(i).getAsJsonObject().get("name"));
            int type = JsonUtil.getInt(data.get(i).getAsJsonObject().get("type"));
            String id = JsonUtil.getString(data.get(i).getAsJsonObject().get("id"));
            String parentId = JsonUtil.getString(data.get(i).getAsJsonObject().get("parent_id"));
            boolean nsfw = JsonUtil.getBoolean(data.get(i).getAsJsonObject().get("nsfw"));

            Channel.Type channelType = Channel.Type.parse(type);

            if (channelType == Channel.Type.GUILD_VOICE) {
                final int bitrate = JsonUtil.getInt(data.get(i).getAsJsonObject().get("bitrate"));
                final int user_limit = JsonUtil.getInt(data.get(i).getAsJsonObject().get("user_limit"));
                final String rtc_region = JsonUtil.getString(data.get(i).getAsJsonObject().get("rtc_region"));
                if (!iterate.next((X) new StageChannel(client, name, id, parentId, rtc_region, bitrate, user_limit, Channel.Type.GUILD_STAGE_VOICE)))
                    break;

            } else if (type == Channel.Type.GUILD_TEXT.getId() && data.get(i).getAsJsonObject().has("last_message_id")) {
                String lastMessageId = JsonUtil.getString(data.get(i).getAsJsonObject().get("last_message_id"));
                String topic = JsonUtil.getString(data.get(i).getAsJsonObject().get("topic"));
                if (!iterate.next((X) new TextChannel(client, name, id, lastMessageId, parentId, topic, nsfw, Channel.Type.GUILD_TEXT)))
                    break;
            } else if (type == Channel.Type.GUILD_STAGE_VOICE.getId()) {
                int bitrate = JsonUtil.getInt(data.get(i).getAsJsonObject().get("bitrate"));
                int user_limit = JsonUtil.getInt(data.get(i).getAsJsonObject().get("user_limit"));
                String rtc_region = JsonUtil.getString(data.get(i).getAsJsonObject().get("rtc_region"));
                if (!iterate.next((X)new StageChannel(client, name, id, parentId, rtc_region, bitrate, user_limit, Channel.Type.GUILD_STAGE_VOICE)))
                    break;
            } else if (type == Channel.Type.GUILD_CATEGORY.getId()) {
                if (!iterate.next((X) new CategoryChannel(client, name, id, Channel.Type.GUILD_CATEGORY)))
                    break;
            } else if (type == Channel.Type.GUILD_NEWS.getId() && data.get(i).getAsJsonObject().has("last_message_id")) {
                String lastMessageId = JsonUtil.getString(data.get(i).getAsJsonObject().get("last_message_id"));
                String topic = JsonUtil.getString(data.get(i).getAsJsonObject().get("topic"));
                if (!iterate.next((X)new NewsChannel(client, name, id, lastMessageId, parentId, topic, nsfw, Channel.Type.GUILD_NEWS)))
                    break;
            }
        }
    }

    public void members() {
        String result = client.webClient().downloadString(String.format("https://discord.com/api/v9/guilds/%s/members", id()));
        JsonArray array = new JsonParser().parse(result).getAsJsonArray();
        
        System.out.println(result);
    }



    public String name() {
        return name;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Guild{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", splash='").append(splash).append('\'');
        sb.append(", discovery_splash='").append(discovery_splash).append('\'');
        sb.append(", banner='").append(banner).append('\'');
        sb.append(", ownerId='").append(ownerId).append('\'');
        sb.append(", applicationId='").append(applicationId).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", afkChannelId='").append(afkChannelId).append('\'');
        sb.append(", afkTimeout=").append(afkTimeout);
        sb.append(", systemChannelId='").append(systemChannelId).append('\'');
        sb.append(", widgetEnabled=").append(widgetEnabled);
        sb.append(", widgetChannelId='").append(widgetChannelId).append('\'');
        sb.append(", verificationLevel=").append(verificationLevel);
        sb.append(", defaultMessageNotifications=").append(defaultMessageNotifications);
        sb.append(", mfaLevel=").append(mfaLevel);
        sb.append(", explicitContentFilter=").append(explicitContentFilter);
        sb.append(", maxPresences='").append(maxPresences).append('\'');
        sb.append(", maxMembers=").append(maxMembers);
        sb.append(", maxVideoChannelUsers=").append(maxVideoChannelUsers);
        sb.append(", vanityUrl='").append(vanityUrl).append('\'');
        sb.append(", premiumTier=").append(premiumTier);
        sb.append(", premiumSubscriptionCount=").append(premiumSubscriptionCount);
        sb.append(", systemChannelFlags=").append(systemChannelFlags);
        sb.append(", preferredLocale='").append(preferredLocale).append('\'');
        sb.append(", rulesChannelId='").append(rulesChannelId).append('\'');
        sb.append(", publicUpdatesChannelId='").append(publicUpdatesChannelId).append('\'');
        sb.append(", hubType='").append(hubType).append('\'');
        sb.append(", premiumProgressBarEnabled=").append(premiumProgressBarEnabled);
        sb.append(", nsfw=").append(nsfw);
        sb.append(", nsfwLevel=").append(nsfwLevel);
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }

    public String id() {
        return id;
    }
}
