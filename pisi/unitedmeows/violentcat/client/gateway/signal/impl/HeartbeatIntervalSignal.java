package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;

@RegisterSignal(op = Signal.SIGNAL_HEARTBEAT_INTERVAL)
public class HeartbeatIntervalSignal extends Signal {

	@Override
	public void read(DiscordClient client, JsonObject data) {
		client.clientGateway().setupHeartbeat(data.get("d").getAsJsonObject().get("heartbeat_interval").getAsInt());
	}

	@Override
	public JsonObject write(DiscordClient client) {
		return null;
	}
}
