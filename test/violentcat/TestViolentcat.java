package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.web.YWebClient;

public class TestViolentcat {

	public static void main(String[] args) {
		DiscordClient discordClient = new DiscordClient(AccountType.BOT, "OTMxMTgwNDA3Njk5OTU5ODc4.YeArVQ.hpBUz2-nPt_1T1tbad0qrZwjSVQ");
		System.out.println(discordClient.getUser("903746238447501322").getId());

	}
}
