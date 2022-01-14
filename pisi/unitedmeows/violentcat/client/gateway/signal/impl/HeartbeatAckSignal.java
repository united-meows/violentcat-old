package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.SIGNAL;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;

@SIGNAL(op = Signal.SIGNAL_HEARTBEAT_ACK)
public class HeartbeatAckSignal extends Signal {

	@Override
	public void read(DiscordClient client, JsonObject data) {

	}

	@Override
	public JsonObject write(DiscordClient client) {
		JsonObject jsonObject = new JsonObject();
		//{ \"op\": 1, \"d\": null }"
		jsonObject.addProperty("op", Signal.SIGNAL_HEARTBEAT_ACK);
		jsonObject.add("d", JsonNull.INSTANCE);
		return jsonObject;
	}
}
