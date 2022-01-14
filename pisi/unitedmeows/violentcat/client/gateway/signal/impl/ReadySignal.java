package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.holders.ApplicationInfo;
import pisi.unitedmeows.violentcat.user.SelfUser;

@RegisterSignal(op = Signal.SIGNAL_READY)
public class ReadySignal extends Signal {
    
    @Override
    public void read(DiscordClient client, JsonObject data) {
        boolean verified = data.getAsJsonObject("user").get("verified").getAsBoolean();
        String username = data.getAsJsonObject("user").get("username").getAsString();
        boolean mfa = data.getAsJsonObject("user").get("mfa_enabled").getAsBoolean();
        int id = data.getAsJsonObject("user").get("id").getAsInt();
        int flags = data.getAsJsonObject("user").get("flags").getAsInt();
        String email = data.getAsJsonObject("user").get("email").getAsString();
        int discriminator = data.getAsJsonObject("user").get("discriminator").getAsInt();
        boolean bot = data.getAsJsonObject("user").get("bot").getAsBoolean();
        String avatar = data.getAsJsonObject("user").get("avatar").getAsString();

        client.selfUser = new SelfUser(verified, mfa, bot, username, email, avatar, id, flags, discriminator);

        int applicationId = data.getAsJsonObject("application").get("id").getAsInt();
        int applicationFlags = data.getAsJsonObject("application").get("flags").getAsInt();

       client.applicationInfo = new ApplicationInfo(applicationId, applicationFlags);
    }

    @Override
    public JsonObject write(DiscordClient client) {
        return null;
    }
}
