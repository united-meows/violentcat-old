package test.violentcat;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.holders.message.RichText;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;

import java.awt.*;
import java.util.Base64;

import static pisi.unitedmeows.yystal.parallel.Async.*;

public class TestViolentcat {

	public static void main(String[] args) {
		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";

		DiscordClient discordClient = new DiscordClient(AccountType.BOT,
				new String(Base64.getDecoder().decode(tokenDecode)));

		discordClient.setPresence(Presence.mobile());
		discordClient.login();
		Guild guild = discordClient.getGuild("931282703477784690").await();
		TextChannel textChannel = (TextChannel) guild.getChannel("931282704014659676").await();

		final RichText richText = RichText.create().createEmbed().
				author()
				.setName("naber")
				.setUrl("https://google.com").
				end().
				setColor(Color.CYAN).
				setTitle("kediler").
				end();



		textChannel.sendMessage(richText);



		kThread.sleep(100000);
	}
}
