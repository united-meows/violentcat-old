package pisi.unitedmeows.violentcat;


import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.user.AccountType;

public class Violentcat {

    public static void main(String[] args) {
        /* test here */

        String token = "OTMxMTgwNDA3Njk5OTU5ODc4.YeArVQ.hpBUz2-nPt_1T1tbad0qrZwjSVQ";
        DiscordClient client = new DiscordClient(AccountType.BOT, token);
        System.out.println(client.getUser("554371566553792555").id());


    }
	
}
