package pisi.unitedmeows.violentcat.holders.channel.channels;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.channel.Channel;
import pisi.unitedmeows.violentcat.holders.message.RichText;

public class TextChannel extends Channel {


    public TextChannel(DiscordClient _client, String _name, String _id, String _last_message_id, String _parent_id, String _topic, boolean _nsfw, Type _type) {
        super(_client, _name, _id, _last_message_id, _parent_id, _topic, _nsfw, _type);
    }

    /**
     * {
     *   "content": "Hello, World!",
     *   "tts": false,
     *   "embeds": [{
     *     "title": "Hello, Embed!",
     *     "description": "This is an embedded message."
     *   }]
     * }
     */
    public void sendMessage(String message) {
        client.webClient().postRequest("https://discord.com/api/v9/channels/" + id + "/messages", String.format("{  \"content\": \"%s\", \"tts\": false}", message));
    }

    public void sendMessage(RichText richText) {
        client.webClient().postRequest("https://discord.com/api/v9/channels/" + id + "/messages", richText.json());
    }

    @Override
    public String toString() {
        return "name: " + name + " - " + "id: " + id + " - " + "last_message_id: " + last_message_id + " - " + "parent id: " + parent_id + " - " + "topic: " + topic + " - " + "nsfw: " + nsfw + " - " + "type: " + type;
    }
}
