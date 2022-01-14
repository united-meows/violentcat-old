package pisi.unitedmeows.violentcat.user;

public class SelfUser {

    private boolean verified, mfa, bot;
    private String username, email, avatar;
    private int id, flags, discriminator;


    public SelfUser(boolean _verified, boolean _mfa, boolean _bot, String _username, String _email, String _avatar,
                    int _id, int _flags, int _discriminator) {
        verified = _verified;
        mfa = _mfa;
        bot = _bot;
        username = _username;
        email = _email;
        avatar = _email;
        id = _id;
        flags = _flags;
        discriminator = _discriminator;
    }



}
