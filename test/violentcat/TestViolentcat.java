package test.violentcat;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.SelfClient;
import pisi.unitedmeows.violentcat.holders.Guild;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.slashcmd.SlashCommandCreator;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.Base64;

public class TestViolentcat {

	public static void main(String[] args) {
		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";
		DiscordClient discordClient = new DiscordClient(AccountType.BOT,
				new String(Base64.getDecoder().decode(tokenDecode))).login().setPresence(
						Presence.playing("cats"));

		discordClient.addListener(new TestListener());
		discordClient.createSlashCommand(SlashCommandCreator.create().setName("test").
				setDescription("Example test slash command"));



		Guild guild = discordClient.getGuild("931282703477784690").await();

		kThread.sleep(100000);
	}
}
