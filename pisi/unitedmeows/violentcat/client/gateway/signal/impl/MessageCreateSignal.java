package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.events.GuildMessageEvent;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.holders.message.GuildMessage;
import pisi.unitedmeows.violentcat.holders.user.BasicGuildUser;
import pisi.unitedmeows.violentcat.utils.JsonUtil;

@RegisterSignal(op = 0)
public class MessageCreateSignal extends Signal {

    @Override
    public void read(DiscordClient client, JsonObject data) {
        JsonObject d = data.get("d").getAsJsonObject();
        JsonObject a = d.getAsJsonObject("author");
        JsonObject m = d.getAsJsonObject("member");
        JsonArray r = m.getAsJsonArray("roles");

        String username = JsonUtil.getString(a.get("username"));
        int public_flags = JsonUtil.getInt(a.get("public_flags"));
        String authorId = JsonUtil.getString(a.get("id"));
        String discriminator = JsonUtil.getString(a.get("discriminator"));
        String avatar = JsonUtil.getString(a.get("avatar"));

        boolean tts = JsonUtil.getBoolean(d.get("tts"));
        String timestamp = JsonUtil.getString(d.get("timestamp"));
        String guild_id = JsonUtil.getString(d.get("guild_id"));
        String channel_id = JsonUtil.getString(d.get("channel_id"));
        String content = JsonUtil.getString(d.get("content"));
        int flags = JsonUtil.getInt(d.get("flags"));

        String messageId = JsonUtil.getString(d.get("id"));
        boolean mute = JsonUtil.getBoolean(m.get("mute"));
        String joinDate = JsonUtil.getString(m.get("joined_at"));
        String hoistedRole = JsonUtil.getString(m.get("hoisted_role"));
        boolean deaf = JsonUtil.getBoolean(m.get("deaf"));

        final BasicGuildUser basicGuildUser = new BasicGuildUser(client, authorId, username,
                Integer.parseInt(discriminator), avatar, public_flags, joinDate, hoistedRole, mute, deaf);

        final GuildMessage guildMessage = new GuildMessage(client, messageId, basicGuildUser, channel_id, content, tts,
                guild_id);

        client.eventSystem().fire(new GuildMessageEvent(basicGuildUser, guildMessage));

    }

    @Override
    public JsonObject write(DiscordClient client) {
        return null;
    }
}
