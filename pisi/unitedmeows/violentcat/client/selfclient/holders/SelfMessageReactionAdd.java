package pisi.unitedmeows.violentcat.client.selfclient.holders;

import java.util.List;

public class SelfMessageReactionAdd {

    private String user_id;
    private String message_id;
    private String channel_id;
    private String guild_id;


    public static class Member {
        private User user;
        private List<String> roles;
        private String premium_since;
        private boolean pending;
        private String nick;
        private boolean mute;
        private String joined_at;
        private String hoisted_role;
        private int flags;
        private boolean deaf;
        private String communication_disabled_until;
        private String avatar;
    }

    public static class User {
        private String username;
        private String id;
        private String discriminator;
        private String avatar;
    }

    public static class Emoji {
        private String name;
        private String id;
    }

}
