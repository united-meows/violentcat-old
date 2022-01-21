package pisi.unitedmeows.violentcat.holders;

public class Invite {

    protected String code;
    protected int type;
    protected String expiresAt;


    protected int uses;
    protected int maxUses;
    protected int maxAge;
    protected boolean temporary;
    protected String createdAt;

    /*channel*/
    protected String channelId;
    protected String channelName;
    protected int channelType;

    /*inviter*/
    protected String id;
    protected String username;
    protected String avatar;
    protected String discriminator;
    protected int publicFlags;
    protected boolean bot;

    public Invite(String _code, int _type, String _expiresAt, int _uses, int _maxUses, int _maxAge, boolean _temporary, String _createdAt, String _channelId,
                  String _channelName, int _channelType, String _id, String _username, String _avatar, String _discriminator, int _publicFlags, boolean _bot) {
        this.code = _code;
        this.type = _type;
        this.expiresAt = _expiresAt;
        this.uses = _uses;
        this.maxUses = _maxUses;
        this.maxAge = _maxAge;
        this.temporary = _temporary;
        this.createdAt = _createdAt;
        this.channelId = _channelId;
        this.channelName = _channelName;
        this.channelType = _channelType;
        this.id = _id;
        this.username = _username;
        this.avatar = _avatar;
        this.discriminator = _discriminator;
        this.publicFlags = _publicFlags;
        this.bot = _bot;
    }


}
