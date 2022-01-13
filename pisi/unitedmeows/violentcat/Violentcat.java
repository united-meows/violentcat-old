package pisi.unitedmeows.violentcat;


import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.user.AccountType;

public class Violentcat {

    public static void main(String[] args) {
        /* test here */

        String token = "OTMxMTgwNDA3Njk5OTU5ODc4.YeArVQ.i2yn4e4PavwPYI8HZlKt9eJy3oo";
        DiscordClient client = new DiscordClient(AccountType.BOT, token);
        System.out.println(client.getUser("903746238447501322").getId());


    }
	
}
