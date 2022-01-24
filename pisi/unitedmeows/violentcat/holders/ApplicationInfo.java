package pisi.unitedmeows.violentcat.holders;

public class ApplicationInfo {

    private String id;
    private String name;
    private String icon;
    private String description;
    private String summary;
    private boolean hook;
    private boolean bot_public;
    private boolean bot_require_code_grant;
    private String verify_key;

    private String ownerId;
    private String username;
    private String avatar;
    private String discriminator;
    private int public_flags;
    private int ownerFlags;

    private int flags;


    public ApplicationInfo(String _id, String _name, String _icon, String _description, String _summary, boolean _hook, boolean _bot_public,
                           boolean _bot_require_code_grant, String _verify_key, String _ownerId, String _username, String _avatar, String _discriminator, int _public_flags,
    int _ownerFlags, int _flags) {
        id = _id;
        name = _name;
        icon = _icon;
        description = _description;
        summary = _summary;
        hook = _hook;
        bot_public = _bot_public;
        bot_require_code_grant = _bot_require_code_grant;
        verify_key = _verify_key;
        ownerId = _ownerId;
        username = _username;
        avatar = _avatar;
        discriminator = _discriminator;
        public_flags = _public_flags;
        ownerFlags = _ownerFlags;
        flags = _flags;
    }
}
