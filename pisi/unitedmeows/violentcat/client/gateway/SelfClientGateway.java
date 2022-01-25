package pisi.unitedmeows.violentcat.client.gateway;

import com.google.gson.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.HeartbeatAckSignal;
import pisi.unitedmeows.violentcat.client.selfclient.structs.PresenceUpdateStruct;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.JsonUtil;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;
import pisi.unitedmeows.yystal.utils.kThread;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;

public class SelfClientGateway extends WebSocketClient {

	protected Promise heartbeatPromise;

	private Gson gson = new GsonBuilder()
			.serializeNulls()
			.create();

	private boolean initialized;

	protected Queue<String> sendQueue;
	private Thread sendThread;

	public 	SelfClientGateway(URI serverUri) {
		super(serverUri);
		sendQueue = new ArrayDeque<>();
	}

	@Override
	public void connect() {
		super.connect();
	}

	public void startHeartBeating(long interval) {
		if (heartbeatPromise != null) {
			heartbeatPromise.stop();
		}
		heartbeatPromise = Async.async_loop(() -> {
			JsonObject jsonObject = new JsonObject();
			//{ \"op\": 1, \"d\": null }"
			jsonObject.addProperty("op", Signal.SIGNAL_HEARTBEAT_ACK);
			jsonObject.add("d", JsonNull.INSTANCE);

			write(gson.toJson(jsonObject));
		}, interval);
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		System.out.println("connection opened");
	}

	@Override
	public void onMessage(String s) {
		System.out.println("RECEIVED << " + s);
		JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
		int opcode = JsonUtil.getInt(jsonObject.get("op"));
		String title = JsonUtil.getString(jsonObject.get("t"));
		JsonObject d = jsonObject.get("d").getAsJsonObject();
		switch (opcode) {
			case 10: {
				initialized = true;
				sendThread = new Thread(this::write);
				sendThread.start();
				// HEARTBEAT_INTERVAL
				final long heartbeatInterval = JsonUtil.getLong(d.get("heartbeat_interval"));
				startHeartBeating(heartbeatInterval);
				return;
			}
		}

		switch (title) {
			case "READY": {

				break;
			}
			case "PRESENCE_UPDATE": {
				PresenceUpdateStruct presenceUpdateStruct = new GsonWrap<PresenceUpdateStruct>(d) {}.build();
				System.out.println(presenceUpdateStruct);
				break;
			}
			case "MESSAGE_REACTION_ADD": {

				break;
			}
			case "MESSAGE_CREATE": {

				break;
			}
		}



	}

	public void write(String data) {
		sendQueue.add(data);
	}

	protected void write() {
		while (!isClosed()) {
			if (!sendQueue.isEmpty()) {
				String data = sendQueue.poll();
				System.out.println("SENDING >> " + data);
				send(data);

			}
			kThread.sleep(30);
		}

		sendQueue.clear();
	}

	@Override
	public void onClose(int i, String s, boolean b) {
		System.out.println("closed " + s);
	}


	@Override
	public void onError(Exception e) {

	}
}
