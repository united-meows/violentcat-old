package pisi.unitedmeows.violentcat.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.violentcat.user.DiscordUser;
import pisi.unitedmeows.yystal.web.YWebClient;

public class DiscordClient {

	protected AccountType accountType;
	protected String token;

	public DiscordClient(AccountType _accountType, String _token) {
		accountType = _accountType;
		token = _token;
	}

	public DiscordUser getUser(String id) {
		YWebClient yWebClient = new YWebClient();
		yWebClient.header("Authorization", "Bot " + token);
		yWebClient.setUserAgent("cats");
		String jsonResult = yWebClient.downloadString("https://discord.com/api/v9/users/" + id);
		JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
		final String userId = data.get("id").getAsString();
		final String userName = data.get("username").getAsString();
		final String avatarId = data.get("avatar").getAsString();
		final int discriminator = data.get("discriminator").getAsInt();
		final int publicFlags = data.get("public_flags").getAsInt();
		final String banner = data.get("banner") == null ? "0" : data.get("banner").getAsString();
		final String bannerColor = data.get("banner_color") == null ? "0" : "a";
		final String accent_color = data.get("accent_color") == null ? "0" : "a";

		return new DiscordUser(userId, userName, avatarId, discriminator, publicFlags, banner, bannerColor, accent_color);
	}

	/* soon .D */
	public void login() {

	}

	public AccountType accountType() {
		return accountType;
	}
}
