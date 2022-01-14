package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;

@RegisterSignal(op = Signal.SIGNAL_PRESENCE)
public class PresenceUpdateSignal extends Signal {
    private int type, since;
    private String message;

    public PresenceUpdateSignal(int _type, int _since, String _message) {
        this.type = _type;
        this.since = _since;
        this.message = _message;
    }

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
        presence.addProperty("type", type);
        activities.add(presence);
        data.add("activities", activities);

        data.addProperty("status", "online");
        data.addProperty("afk", false);

        main.add("d", data);
        return main;
    }
}
