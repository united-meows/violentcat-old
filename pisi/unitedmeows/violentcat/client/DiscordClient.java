package pisi.unitedmeows.violentcat.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.violentcat.user.DiscordUser;
import pisi.unitedmeows.violentcat.utils.JsonUtil;
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
		System.out.println(jsonResult);
		JsonObject data = new JsonParser().parse(jsonResult).getAsJsonObject();
		final String userId = JsonUtil.getString(data.get("id"));
		final String userName = JsonUtil.getString(data.get("username"));
		final String avatarId = JsonUtil.getString(data.get("avatar"));
		final int discriminator = JsonUtil.getInt(data.get("discriminator"));
		final int publicFlags = JsonUtil.getInt(data.get("public_flags"));
		final String banner = JsonUtil.getString(data.get("banner"));
		final String bannerColor = JsonUtil.getString(data.get("banner_color"));
		final String accent_color = JsonUtil.getString(data.get("accent_color"));

		return new DiscordUser(userId, userName, avatarId, discriminator, publicFlags, banner, bannerColor, accent_color);
	}

	/* soon .D */
	public void login() {

	}

	public AccountType accountType() {
		return accountType;
	}
}
