package pisi.unitedmeows.violentcat.client;

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
		String jsonResult = yWebClient.downloadString("https://discord.com/api/v8/users/903746238447501322");
		/* viserys knk nbr :DD yapsana bunu :DDDD */

		return new DiscordUser();
	}

	/* soon .D */
	public void login() {

	}

	public AccountType accountType() {
		return accountType;
	}
}
