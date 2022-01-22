package pisi.unitedmeows.violentcat.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.gateway.SelfClientGateway;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.web.YWebClient;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelfClient {

	protected String token;
	protected YWebClient webClient;

	protected SelfClientGateway gateway;
	protected String userAgent;

	private static final String API_VERSION = "9";
	private static Gson gson = new GsonBuilder()
			.serializeNulls()
			.create();


	public prop<Integer> clientVersion = new prop<Integer>(111699) {
		@Override
		public Integer get() {
			if (value == -2173) {
				String appPage = webClient.downloadString("https://discord.com/app");
				String toFind = "build_number:\"";
				Matcher matcher = Pattern.compile("/assets/.{20}.js").matcher(appPage);
				while (matcher.find()) {
					String asset = matcher.group();
					String content = webClient.downloadString("https://discord.com" + asset);

					if (content.contains(toFind)) {
						String buildNumber = content.substring(content.indexOf(toFind) + toFind.length()).split("\"")[0];
						value = Integer.parseInt(buildNumber);
						break;
					}
				}
			}

			if (value == -2173)
				value = -1;

			return value;
		}

		@Override
		public void set(Integer newValue) {}
	};


	public SelfClient(String _token) {
		token = _token;
		try {
			gateway = new SelfClientGateway(new URI("wss://gateway.discord.gg/?v=" + API_VERSION + "&encoding=json"));
		} catch (Exception ex) {ex.printStackTrace();}
		gateway.connect();
		webClient = new YWebClient();
		webClient.header("Authorization", token);
		System.out.println(clientVersion.get());

	}

	public void login() {
		JsonObject main = new JsonObject();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("token", token);
		JsonObject properties = new JsonObject();;
		properties.addProperty("os", "Windows");
		properties.addProperty("browser", "chrome");
		properties.addProperty("device", "");
		properties.addProperty("system_locale", "da-DK");
		properties.addProperty("browser_user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36");
		properties.addProperty("browser_version", "91.0.4472.106");
		properties.addProperty("os_version", "10");
		properties.addProperty("referring_domain", "");
		properties.addProperty("referrer_current", "");
		properties.addProperty("referring_domain_current", "");
		properties.addProperty("release_channel", "stable");
		properties.addProperty("client_build_number", clientVersion.get().toString());
		properties.add("client_event_source", null);

		properties.addProperty("compress", false);
		//jsonObject.add("shard", null);
		jsonObject.add("presence", null);
		jsonObject.add("properties", properties);

		main.addProperty("op", 2);
		main.add("d", jsonObject);

		gateway.write(gson.toJson(main));
	}

	public SelfClientGateway gateway() {
		return gateway;
	}

	public YWebClient webClient() {
		return webClient;
	}

}
