package pisi.unitedmeows.violentcat.holders.channel;

import pisi.unitedmeows.violentcat.client.DiscordClient;

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
        GULD_PRIVATE_THREAD(12),
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
