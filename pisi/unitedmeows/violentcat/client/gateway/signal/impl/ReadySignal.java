package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.events.ReadyEvent;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.holders.ApplicationInfo;
import pisi.unitedmeows.violentcat.user.SelfUser;
import pisi.unitedmeows.violentcat.utils.JsonUtil;

@RegisterSignal(op = Signal.SIGNAL_READY)
public class ReadySignal extends Signal {

    @Override
    public void read(DiscordClient client, JsonObject data) {
        boolean verified = data.getAsJsonObject("user").get("verified").getAsBoolean();
        String username = JsonUtil.getString(data.getAsJsonObject("user").get("username"));
        boolean mfa = data.getAsJsonObject("user").get("mfa_enabled").getAsBoolean();
        int id = JsonUtil.getInt(data.getAsJsonObject("user").get("id"));
        int flags = JsonUtil.getInt(data.getAsJsonObject("user").get("flags"));
        String email = JsonUtil.getString(data.getAsJsonObject("user").get("email"));
        int discriminator = JsonUtil.getInt(data.getAsJsonObject("user").get("discriminator"));
        boolean bot = data.getAsJsonObject("user").get("bot").getAsBoolean();
        String avatar = JsonUtil.getString(data.getAsJsonObject("user").get("avatar"));

        client.setSelfUser(new SelfUser(verified, mfa, bot, username, email, avatar, id, flags, discriminator));

        client.eventSystem().fire(new ReadyEvent(client.applicationInfo(), client.selfUser()));
    }

    @Override
    public JsonObject write(DiscordClient client) {
        return null;
    }
}
