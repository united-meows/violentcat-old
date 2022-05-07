package pisi.unitedmeows.violentcat.holders.guild;

public class GuildPreview {

    ///guilds/{guild.id}/preview
    private String id;
    private String name;
    private String icon;
    private String splash;
    private String discoverySplash;
    private int approximate_member_count;
    private int approximate_presence_count;
    private String description;

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String icon() {
        return icon;
    }

    public String splash() {
        return splash;
    }

    public String discoverySplash() {
        return discoverySplash;
    }

    public int approximate_member_count() {
        return approximate_member_count;
    }

    public int approximate_presence_count() {
        return approximate_presence_count;
    }

    public String description() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GuildPreview{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", splash='").append(splash).append('\'');
        sb.append(", discoverySplash='").append(discoverySplash).append('\'');
        sb.append(", approximate_member_count=").append(approximate_member_count);
        sb.append(", approximate_presence_count=").append(approximate_presence_count);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
