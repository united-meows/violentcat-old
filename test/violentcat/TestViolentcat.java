package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;

public class TestViolentcat {

	public static void main(String[] args) {
		DiscordClient discordClient = new DiscordClient(AccountType.BOT, "TOKEN_HERE");
		discordClient.login();
		discordClient.setPresence(Presence.streaming("ersinle", "https://www.twitch.tv/gamegrim"));
		discordClient.getGuild("931282703477784690").getStageChannels();

		kThread.sleep(100000);
	}
}
