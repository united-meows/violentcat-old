package pisi.unitedmeows.violentcat.webhook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.holders.message.RichText;
import pisi.unitedmeows.yystal.web.YWebClient;

import java.util.List;

public class WebhookClient {
	private static final Gson gson = new Gson();

	private String username;
	private String avatarUrl;
	private boolean tts;
	private String url;

	public WebhookClient(String _url) {
		url = _url;
	}

	public void send(RichText richText) {
		JsonObject jsonObject = richText.jsonRaw();
		jsonObject.addProperty("username", username);
		jsonObject.addProperty("avatar_url", avatarUrl);
		jsonObject.addProperty("tts", tts);
		YWebClient webClient = new YWebClient();
		webClient.setUserAgent("cat");
		webClient.postRequest(url, gson.toJson(jsonObject));
	}

	public void send(String content) {
		send(RichText.create().setContent(content));
	}

	public WebhookClient setAvatarUrl(String _avatarUrl) {
		avatarUrl = _avatarUrl;
		return this;
	}

	public WebhookClient setTts(boolean _tts) {
		tts = _tts;
		return this;
	}

	public WebhookClient setUrl(String _url) {
		url = _url;
		return this;
	}

	public WebhookClient setUsername(String _username) {
		username = _username;
		return this;
	}
}
