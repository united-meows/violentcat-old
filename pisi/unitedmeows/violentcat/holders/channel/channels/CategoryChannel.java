package pisi.unitedmeows.violentcat.holders.channel.channels;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.channel.Channel;

public class CategoryChannel extends Channel {


    public CategoryChannel(DiscordClient _client, String _name, String _id, Type _type) {
        super(_client, _name, _id, _type);
    }

    @Override
    public String toString() {
        return "name: " + name + " - " + "id: " + id + " - " + "type: " + type;
    }
}
