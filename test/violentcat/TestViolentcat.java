package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.holders.channel.channels.TextChannel;
import pisi.unitedmeows.violentcat.holders.message.RichText;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.web.YWebClient;

import java.awt.*;
import java.util.Base64;

public class TestViolentcat {

	public static void main(String[] args) {

		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";

		DiscordClient discordClient = new DiscordClient(AccountType.BOT,
				new String(Base64.getDecoder().decode(tokenDecode)));

		discordClient.login();
		discordClient.setPresence(Presence.streaming("ersinle", "https://www.twitch.tv/gamegrim"));
		TextChannel textChannel = discordClient.getGuild("931282703477784690").getChannel("931282704014659676");
		textChannel.sendMessage("naber la :D");
		kThread.sleep(100);

		RichText richText = new RichText();
		RichText.Embed embed = new RichText.Embed().setAuthor(new RichText.Author().setName("anan"))
						.setDescription("naber :D").setColor(Color.red)
								.setFooter(new RichText.Footer().setText(":D"))
										.setTitle("kediler")
				.setImage(new RichText.Image().setUrl("https://media.discordapp.net/attachments/931282704014659676/932313282553978910/6c858b11004e447c18ecc7f037a23d08.jpg?width=338&height=488")
						.setHeight(488).setWidth(338))
				.setThumbnail(new RichText.Image().setUrl("https://media.discordapp.net/attachments/931282704014659676/932313618945556550/1a15ca7c34cc0372195b8c6ce1a8ba70.jpg")
						.setWidth(120)
						.setHeight(120));
		richText.addEmbed(embed);


		textChannel.getPins();
		textChannel.sendMessage(richText);



		kThread.sleep(100000);
	}
}
