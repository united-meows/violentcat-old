package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;

import java.util.Base64;

public class TestViolentcat {

	public static void main(String[] args) {

		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";

		DiscordClient discordClient = new DiscordClient(AccountType.BOT,
				new String(Base64.getDecoder().decode(tokenDecode)));

		discordClient.login();
		discordClient.setPresence(Presence.streaming("ersinle", "https://www.twitch.tv/gamegrim"));
		discordClient.getGuild("931282703477784690").channelsAsIterable(x -> {
			if (x instanceof TextChannel) {
				((TextChannel)x).sendMessage("anani götünden sikeyim");
			}
			return true;
		});

		kThread.sleep(100000);
	}
}
