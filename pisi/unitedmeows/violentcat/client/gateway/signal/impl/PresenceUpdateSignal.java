package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.holders.Presence;


@RegisterSignal(op = Signal.SIGNAL_PRESENCE)
public class PresenceUpdateSignal extends Signal {


    private Presence presence;

    public PresenceUpdateSignal(Presence _presence) {
        presence = _presence;
    }


    @Override
    public void read(DiscordClient client, JsonObject data) {

    }

    @Override
    public JsonObject write(DiscordClient client) {
        JsonObject main = new JsonObject();
        main.addProperty("op", SIGNAL_PRESENCE);
        JsonObject data = new JsonObject();
        data.addProperty("since", 999998);

        JsonArray activities = new JsonArray();
        JsonObject prensenceJson = new JsonObject();
        prensenceJson.addProperty("name", presence.getStatusMessage());
        if(presence.getType() == Presence.Type.STREAMING) {
            prensenceJson.addProperty("url", presence.getUrl());
        }
        prensenceJson.addProperty("type", presence.getType().getId());
        activities.add(prensenceJson);
        data.add("activities", activities);

        data.addProperty("status", presence.getStatus().code());
        data.addProperty("afk", false);

        main.add("d", data);
        return main;
    }
}
