package pisi.unitedmeows.violentcat.client.gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.gateway.signal.ReadySignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.RegisterSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.Signal;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.HeartbeatAckSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.HeartbeatIntervalSignal;
import pisi.unitedmeows.violentcat.client.gateway.signal.impl.IdentifySignal;
import pisi.unitedmeows.violentcat.utils.JsonUtil;
import pisi.unitedmeows.yystal.parallel.Promise;
import pisi.unitedmeows.yystal.utils.Capsule;
import pisi.unitedmeows.yystal.utils.kThread;

import static pisi.unitedmeows.yystal.parallel.Async.*;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class DiscordClientGateway extends WebSocketClient {

	protected static final int QUEUE_DELAY = 30;

	protected static final HashMap<Integer, Signal> signals = new HashMap<Integer, Signal>();
	protected static final Gson gson = new GsonBuilder()
			.serializeNulls()
			.create();

	protected Thread sendThread;
	protected Queue<Signal> sendQueue;

	protected DiscordClient client;

	static {
		registerSignal(HeartbeatIntervalSignal.class);
		registerSignal(ReadySignal.class);
	}

	private static void registerSignal(Class<? extends Signal> clazz) {
		final RegisterSignal annotation = clazz.getAnnotation(RegisterSignal.class);
		try {
			signals.put(annotation.op(), clazz.newInstance());
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
	}

	private Promise heartBeatPromise;

	public DiscordClientGateway(DiscordClient _client, URI serverUri)
	{
		super(serverUri);
		client = _client;
		sendQueue = new ArrayDeque<>();
	}

	public void login(String token, Capsule capsule) {
		IdentifySignal identifySignal = new IdentifySignal(token,
				capsule.getOrDefault("intents", 513),
				capsule.getOrDefault("os", "linux"),
				capsule.getOrDefault("browser", "violentcat"),
				capsule.getOrDefault("device", "violentcat"),
				capsule.getOrDefault("largethreshold", -1),
				capsule.getOrDefault("shards", null));

		send(identifySignal);
	}

	public void login(String token) {
		login(token, Capsule.of());
	}

	public void setupHeartbeat(long miliseconds) {
		if (heartBeatPromise != null) {
			heartBeatPromise.stop();
		}
		System.out.println("setup heart beat " + miliseconds);
		heartBeatPromise = async_loop(() -> {
			/* sending heartbeat ack */
			send(new HeartbeatAckSignal());
		}, miliseconds > 200 ?  miliseconds - 50 : miliseconds);
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		System.out.println("Connection opened << " + serverHandshake.getHttpStatusMessage());
		sendThread = new Thread(this::write);
		sendThread.start();
	}

	protected void write() {
		while (!isClosed()) {
			if (!sendQueue.isEmpty()) {
				Signal signal = sendQueue.poll();
				String data = gson.toJson(signal.write(client));
				System.out.println("SENDING >> " + data);
				send(data);

			}
			kThread.sleep(QUEUE_DELAY);
		}

		sendQueue.clear();
	}

	@Override
	public void onMessage(String s) {
		JsonObject data = new JsonParser().parse(s).getAsJsonObject();
		int opCode = JsonUtil.getInt(data.get("op"));

		Signal signal = signals.getOrDefault(opCode, null);
		if (signal != null) {
			signal.read(client, data);
		}

		System.out.println("RECEIVED << " + s);
	}

	public void close() {
		heartBeatPromise.stop();
		super.close();
	}

	@Override
	public void onClose(int i, String s, boolean b) {
		System.out.println(s);
	}

	public void send(Signal signal) {
		sendQueue.add(signal);
	}

	@Override
	public void onError(Exception e) {
		System.out.println(e.getCause().getMessage());
	}
}