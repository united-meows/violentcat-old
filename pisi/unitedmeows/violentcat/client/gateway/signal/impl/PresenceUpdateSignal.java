package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;


@RegisterSignal(op = Signal.SIGNAL_PRESENCE)
public class PresenceUpdateSignal extends Signal {


    @Override
    public void read(DiscordClient client, JsonObject data) {

    }

    @Override
    public JsonObject write(DiscordClient client) {
        JsonObject main = new JsonObject();
        main.addProperty("op", SIGNAL_PRESENCE);
        JsonObject data = new JsonObject();
        data.addProperty("since", since);

        JsonArray activities = new JsonArray();
        JsonObject presence = new JsonObject();
        presence.addProperty("name", message);
        if(url != null && !url.isEmpty()) {
            presence.addProperty("url", url);
        }
        presence.addProperty("type", type);
        activities.add(presence);
        data.add("activities", activities);

        data.addProperty("status", status);
        data.addProperty("afk", afk);

        main.add("d", data);
        return main;
    }
}
