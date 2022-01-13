package pisi.unitedmeows.violentcat;

import pisi.unitedmeows.violentcat.headers.VHeader;
import pisi.unitedmeows.yystal.web.YWebClient;

public class Violentcat {

    public static void main(String[] args) {
        /* test here */

        String token = "OTMxMTgwNDA3Njk5OTU5ODc4.YeArVQ.T6qvPT72qSx7k_Wqsn-U5sGfS1E";
        YWebClient yWebClient = new YWebClient();
        yWebClient.header(VHeader.AUTHORIZATION, VHeader.BOT + token);
        yWebClient.setUserAgent("cats");
        String result = yWebClient.downloadString("https://discord.com/api/v8/users/903746238447501322");
        System.out.println(result);


    }
	
}
