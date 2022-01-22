package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.Base64;

public class TestViolentcat {

	public static void main(String[] args) {
		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";

		DiscordClient discordClient = new DiscordClient(AccountType.BOT,
				new String(Base64.getDecoder().decode(tokenDecode))).setPresence(Presence.mobile()).login();

		discordClient.addListener(new TestListener());



		Guild guild = discordClient.getGuild("931282703477784690").await();


		kThread.sleep(100000);
	}
}
