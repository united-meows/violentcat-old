package pisi.unitedmeows.violentcat.holders;

import com.google.gson.annotations.SerializedName;

public class ApplicationInfo {

    private String id;
    private String name;
    private String icon;
    private String description;
    private String summary;
    private boolean hook;
    private boolean bot_private;
    private boolean bot_require_code_grant;
    private String verify_key;

    private OwnerInfo owner;


    private int flags;
    public static class OwnerInfo {
        private String id;
        private String username;
        private String avatar;
        private String discriminator;
        private int private_flags;
        private int ownerFlags;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("OwnerInfo{");
            sb.append("id='").append(id).append('\'');
            sb.append(", username='").append(username).append('\'');
            sb.append(", avatar='").append(avatar).append('\'');
            sb.append(", discriminator='").append(discriminator).append('\'');
            sb.append(", private_flags=").append(private_flags);
            sb.append(", ownerFlags=").append(ownerFlags);
            sb.append('}');
            return sb.toString();
        }
    }

/*    private ApplicationInfo(String _id, String _name, String _icon, String _description, String _summary, boolean _hook, boolean _bot_private,
                           boolean _bot_require_code_grant, String _verify_key, String _ownerId, String _username, String _avatar, String _discriminator, int _private_flags,
    int _ownerFlags, int _flags) {
        id = _id;
        name = _name;
        icon = _icon;
        description = _description;
        summary = _summary;
        hook = _hook;
        bot_private = _bot_private;
        bot_require_code_grant = _bot_require_code_grant;
        verify_key = _verify_key;
        ownerId = _ownerId;
        username = _username;
        avatar = _avatar;
        discriminator = _discriminator;
        private_flags = _private_flags;
        ownerFlags = _ownerFlags;
        flags = _flags;
    }*/

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String icon() {
        return icon;
    }

    public String description() {
        return description;
    }

    public String summary() {
        return summary;
    }

    public boolean hook() {
        return hook;
    }

    public boolean isprivateBot() {
        return bot_private;
    }

    public boolean isRequiredCodeGrant() {
        return bot_require_code_grant;
    }

    public String verifyKey() {
        return verify_key;
    }

    private OwnerInfo owner() {
        return owner;
    }

    private int flags() {
        return flags;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApplicationInfo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", hook=").append(hook);
        sb.append(", bot_private=").append(bot_private);
        sb.append(", bot_require_code_grant=").append(bot_require_code_grant);
        sb.append(", verify_key='").append(verify_key).append('\'');
        sb.append(", owner=").append(owner);
        sb.append(", flags=").append(flags);
        sb.append('}');
        return sb.toString();
    }
}
