package pisi.unitedmeows.violentcat.holders.channel.channels;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.channel.Channel;

public class VoiceChannel extends Channel {

    public VoiceChannel(DiscordClient _client, String _name, String _id, String _parent_id, String _rtc_region, int _bitrate, int _user_limit, Type _type) {
        super(_client, _name, _id, _parent_id, _rtc_region, _bitrate, _user_limit, _type);
    }

    @Override
    public String toString() {
        return "name: " + name + " - " + "id: " + id + " - " + "parent id: " + parent_id + " - " + "rtc region: " + rtc_region + " - " + "bitrate: " + bitrate + " - " + "user limit: " + user_limit + " - " + "type: " + type;
    }
}
