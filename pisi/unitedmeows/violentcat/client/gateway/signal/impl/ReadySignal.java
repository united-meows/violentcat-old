package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;

@RegisterSignal(op = Signal.SIGNAL_READY)
public class ReadySignal extends Signal {
    
    @Override
    public void read(DiscordClient client, JsonObject data) {


    }

    @Override
    public JsonObject write(DiscordClient client) {
        return null;
    }
}
