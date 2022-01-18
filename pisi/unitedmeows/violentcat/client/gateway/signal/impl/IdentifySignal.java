package pisi.unitedmeows.violentcat.client.gateway.signal.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.holders.Presence;

import java.util.List;

public class IdentifySignal extends Signal {


	@Override
	public void read(DiscordClient client, JsonObject data) {

	}

	private final String token, os, browser, device;
	private final int intents;
	private int largethreshold = -1;
	private int[] shard = null;

	public IdentifySignal(String _token, int _intents, String _os, String _browser, String _device) {
		token = _token;
		intents = _intents;
		os = _os;
		browser = _browser;
		device = _device;
	}

	public IdentifySignal(String _token, int _intents, String _os, String _browser, String _device, int _largethreshold, int[] _shard) {
		token = _token;
		intents = _intents;
		os = _os;
		browser = _browser;
		device = _device;
		largethreshold = _largethreshold;
		shard = _shard;
	}

	@Override
	public JsonObject write(DiscordClient client) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("op", Signal.SIGNAL_IDENTIFY);

		JsonObject dElement = new JsonObject();
		dElement.addProperty("token", token);
		dElement.addProperty("intents", intents);
		JsonObject properties = new JsonObject();
		properties.addProperty("$os", os);
		properties.addProperty("$browser", (client.presence() != null && client.presence().getStatus() ==
				Presence.Status.MOBILE) ? client.presence().getStatus().code() : browser);
		properties.addProperty("$device", device);
		dElement.add("properties", properties);


		jsonObject.add("d", dElement);
		if (largethreshold != -1) {
			jsonObject.addProperty("large_threshold", largethreshold);
		}
		if (shard != null) {
			JsonElement result = new GsonBuilder().create().toJsonTree(shard);
			jsonObject.add("shard", result);
		}


		return jsonObject;
	}
}
