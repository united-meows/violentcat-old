package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.utils.JsonUtil;

@RegisterSignal(op = Signal.SIGNAL_SEND_MESSAGE)
public class SendMessageSignal extends Signal {
    @Override
    public void read(DiscordClient client, JsonObject data) {
        JsonObject d = data.get("d").getAsJsonObject();
        JsonObject a = d.getAsJsonObject("author");

        int type = JsonUtil.getInt(d.get("type"));
        boolean tts = JsonUtil.getBoolean(d.get("tts"));
        String timestamp = JsonUtil.getString(d.get("timestamp"));
        String referenced_message = JsonUtil.getString(d.get("referenced_message"));
        boolean pinned = JsonUtil.getBoolean(d.get("pinned"));
        /**
         *       "mentions":[
         *
         *       ],
         *       "mention_roles":[
         *
         *       ]
         */
        boolean mention_everyone = JsonUtil.getBoolean(d.get("mention_everyone"));
        /**
         *       "member":{
         *          "roles":[
         *             "931283104339992597"
         *          ],
         */
        boolean mute = JsonUtil.getBoolean(d.get("mute"));
        String joined_at = JsonUtil.getString(d.get("joined_at"));
        String hoisted_role = JsonUtil.getString(d.get("hoisted_role"));
        boolean deaf = JsonUtil.getBoolean(d.get("deaf"));
        String id = JsonUtil.getString(d.get("id"));
        int flags = JsonUtil.getInt(d.get("flags"));
        /**
         *  "embeds":[
         *          {
         *             "type":"rich",
         *             "title":"Hello, Embed!",
         *             "description":"This is an embedded message."
         *          }
         *       ],
         */
        String edited_timestamp = JsonUtil.getString(d.get("edited_timestamp"));
        String content = JsonUtil.getString(d.get("content"));
        /**
         * "components":[
         *
         *       ],
         */
        String channel_id = JsonUtil.getString(d.get("channel_id"));

        String username = JsonUtil.getString(a.get("username"));
        int public_flags = JsonUtil.getInt(a.get("public_flags"));
        String author_id = JsonUtil.getString(a.get("id"));
        String discriminator = JsonUtil.getString(a.get("discriminator"));
        boolean bot = JsonUtil.getBoolean(a.get("bot"));
        String avatar = JsonUtil.getString(a.get("avatar"));
        /**
         * , "attachments":[
         *
         *       ]
         */
    }

    @Override
    public JsonObject write(DiscordClient client) {
        return null;
    }
}
