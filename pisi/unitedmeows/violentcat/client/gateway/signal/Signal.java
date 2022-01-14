package pisi.unitedmeows.violentcat.client.gateway.signal;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;

public abstract class Signal {

	public static final int SIGNAL_HEARTBEAT_INTERVAL = 10;
	public static final int SIGNAL_HEARTBEAT_ACK = 1;
	public static final int SIGNAL_IDENTIFY = 2;

	public abstract void read(DiscordClient client, JsonObject data);
	public abstract JsonObject write(DiscordClient client);
}
