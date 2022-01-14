package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;

public class TestViolentcat {

	public static void main(String[] args) {
		DiscordClient discordClient = new DiscordClient(AccountType.BOT, "OTMxMTgwNDA3Njk5OTU5ODc4.YeArVQ.hpBUz2-nPt_1T1tbad0qrZwjSVQ");
		discordClient.login();
		discordClient.setPresence(Presence.streaming("ersinle"));

		kThread.sleep(100000);
	}
}
