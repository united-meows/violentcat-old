package pisi.unitedmeows.violentcat.client.selfclient.holders;

import java.util.List;

public class SelfMessageCreate {

    private int type;
    private boolean tts;
    private String timestamp;
    private String referenced_message;
    private boolean pinned;
    private String nonce;
    private boolean mention_everyone;

    private String id;
    private int flags;
    private String edited_timestamp;
    private String content;
    private String channel_id;
    private String guild_id;


    private Member member;

    public static class Member {
        private List<String> roles;
        private boolean mute;
        private String joined_at;
        private String hoisted_role;
        private int flags;
        private boolean deaf;



    }

    public static class Author {
        private String username;
        private int public_flags;
        private String id;
        private String discriminator;
        private String avatar_decoration;
        private String avatar;
    }


}
