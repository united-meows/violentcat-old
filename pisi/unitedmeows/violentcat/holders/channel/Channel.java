package pisi.unitedmeows.violentcat.holders.channel;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Invite;
import pisi.unitedmeows.violentcat.utils.JsonUtil;

import java.time.Duration;

public class Channel {

    protected String name;
    protected String id;
    protected String last_message_id;
    protected String parent_id;
    protected String topic;
    protected String rtc_region;
    protected int bitrate;
    protected int user_limit;
    protected boolean nsfw;
    protected Type type;

    public static final Cache<String, Channel> _CHANNEL_CACHE;

    static {
        _CHANNEL_CACHE =  Caffeine.newBuilder().maximumSize(500)
                .expireAfterWrite(Duration.ofSeconds(30))
                .build();
    }


    protected DiscordClient client;

    public Channel(DiscordClient _client, String _name, String _id, String _last_message_id, String _parent_id, String _topic, boolean _nsfw, Type _type) {
        client = _client;
        name = _name;
        id = _id;
        last_message_id = _last_message_id;
        parent_id = _parent_id;
        topic = _topic;
        nsfw = _nsfw;
        type = _type;
    }

    public Channel(DiscordClient _client, String _name, String _id, String _parent_id, String _rtc_region, int _bitrate, int _user_limit, Type _type) {
        client = _client;
        name = _name;
        id = _id;
        parent_id = _parent_id;
        rtc_region = _rtc_region;
        bitrate = _bitrate;
        user_limit = _user_limit;
        type = _type;
    }

    public Channel(DiscordClient _client, String _name, String _id, Type _type) {
        client = _client;
        name = _name;
        id = _id;
        type = _type;
    }

    public Action<Invite> createInvite(int maxAge, int maxUses) {
        return createInvite(maxAge, maxUses, false, true);
    }

    public Action<Invite> createInvite(int maxAge, int maxUses, boolean temporary, boolean unique) {
        Action<Invite> action = new Action<Invite>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, id) {
            @Override
            public void run() {
                Gson gson = new Gson();
                JsonObject requestData = new JsonObject();
                requestData.addProperty("max_age", maxAge);
                requestData.addProperty("max_uses", maxUses);
                requestData.addProperty("temporary", temporary);
                requestData.addProperty("unique", unique);
                final String jsonResult = client.webClient().postRequest("https://discord.com/api/v9/channels/" +
                        id + "/invites", gson.toJson(requestData));
                JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
                JsonObject c = data.getAsJsonObject("channel");
                JsonObject i = data.getAsJsonObject("inviter");

                String code = JsonUtil.getString(data.get("code"));
                int type = JsonUtil.getInt(data.get("type"));
                String expires_at = JsonUtil.getString(data.get("expires_at"));
                String channelId = JsonUtil.getString(c.get("id"));
                String channelName = JsonUtil.getString(c.get("name"));
                int channelType = JsonUtil.getInt(c.get("type"));
                String id = JsonUtil.getString(i.get("id"));
                String username =  JsonUtil.getString(i.get("username"));
                String avatar = JsonUtil.getString(i.get("avatar"));
                String discriminator = JsonUtil.getString(i.get("discriminator"));
                int public_flags = JsonUtil.getInt(i.get("public_flags"));
                boolean bot = JsonUtil.getBoolean(i.get("bot"));
                int uses = JsonUtil.getInt(data.get("uses"));
                int max_uses = JsonUtil.getInt(data.get("max_uses"));
                int max_age = JsonUtil.getInt(data.get("max_age"));
                boolean temporaryR = JsonUtil.getBoolean(data.get("temporary"));
                String created_at = JsonUtil.getString(data.get("created_at"));
                end(new Invite(code, type, expires_at, uses, max_uses, max_age, temporaryR, created_at, channelId,
                        channelName, channelType, id, username, avatar, discriminator, public_flags, bot));
            }
        };
        client.discordActionPool().queue(action);
        return action;
    }

    public Action<Boolean> deleteChannel() {
        Action<Boolean> action = new Action<Boolean>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, id) {
            @Override
            public void run() {
                client.webClient().deleteRequest("https://discord.com/api/v9/channels/" + id);
                end(true);
            }
        };
        client.discordActionPool().queue(action);
        return action;
    }

    public Action<Invite> getInvite() {
        Action<Invite> action = new Action<Invite>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, id) {
            @Override
            public void run() {
                String jsonResult = client.webClient().downloadString("https://discord.com/api/v9/channels/" +
                        id + "/invites");
                JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
                JsonObject c = data.getAsJsonObject("channel");
                JsonObject i = data.getAsJsonObject("inviter");

                String code = JsonUtil.getString(data.get("code"));
                int type = JsonUtil.getInt(data.get("type"));
                String expires_at = JsonUtil.getString(data.get("expires_at"));
                String channelId = JsonUtil.getString(c.get("id"));
                String channelName = JsonUtil.getString(c.get("name"));
                int channelType = JsonUtil.getInt(c.get("type"));
                String id = JsonUtil.getString(i.get("id"));
                String username =  JsonUtil.getString(i.get("username"));
                String avatar = JsonUtil.getString(i.get("avatar"));
                String discriminator = JsonUtil.getString(i.get("discriminator"));
                int public_flags = JsonUtil.getInt(i.get("public_flags"));
                boolean bot = JsonUtil.getBoolean(i.get("bot"));
                int uses = JsonUtil.getInt(data.get("uses"));
                int max_uses = JsonUtil.getInt(data.get("max_uses"));
                int max_age = JsonUtil.getInt(data.get("max_age"));
                boolean temporary = JsonUtil.getBoolean(data.get("temporary"));
                String created_at = JsonUtil.getString(data.get("created_at"));
                end(new Invite(code, type, expires_at, uses, max_uses, max_age, temporary, created_at, channelId,
                        channelName, channelType, id, username, avatar, discriminator, public_flags, bot));
            }
        };
        client.discordActionPool().queue(action);
        return action;
    }

    public String id() {
        return id;
    }

    public enum Type {
        GUILD_TEXT(0),
        GUILD_VOICE(2),
        GUILD_CATEGORY(4),
        GUILD_NEWS(5),
        GUILD_STORE(6),
        GUILD_NEWS_THREAD(10),
        GUILD_PUBLIC_THREAD(11),
        GUILD_PRIVATE_THREAD(12),
        GUILD_STAGE_VOICE(13);

        int id;
        Type(int _id) {
            id = _id;
        }

        public static Type parse(int id) {
            for (Type value : values()) {
                if (value.id == id)
                    return value;
            }
            return GUILD_TEXT;
        }

        public int getId() {
            return id;
        }
    }

}
