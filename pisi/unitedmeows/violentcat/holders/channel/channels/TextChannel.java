package pisi.unitedmeows.violentcat.holders.channel.channels;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.holders.channel.Channel;
import pisi.unitedmeows.violentcat.holders.message.RichText;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;

public class TextChannel extends Channel {

    private Promise typingPromise;
    public TextChannel(DiscordClient _client, String _name, String _id, String _last_message_id, String _parent_id, String _topic, boolean _nsfw, Type _type) {
        super(_client, _name, _id, _last_message_id, _parent_id, _topic, _nsfw, _type);
    }

    public Action<Boolean> sendMessage(String message) {
        Action<Boolean> action = new Action<Boolean>(client.discordActionPool(), Action.MajorParameter.CHANNEL_ID, id()) {
            @Override
            public void run() {
                client.webClient().postRequest("https://discord.com/api/v9/channels/" + id + "/messages", String.format("{  \"content\": \"%s\", \"tts\": false}", message));
                end(true);
            }
        };
        client.discordActionPool().queue(action);
        return action;
    }

    public void sendMessage(RichText richText) {
        client.webClient().postRequest("https://discord.com/api/v9/channels/" + id + "/messages", richText.json());
    }

    public void startTyping() {
        if (typingPromise != null) {
            typingPromise.stop();
        }

        typingPromise = Async.async_loop(this::sendTyping, 7000);
    }

    public void stopTyping() {
        if (typingPromise != null) {
            typingPromise.stop();
        }
    }

    /* this will make you in typing state for 8 seconds */
    public void sendTyping() {
        client.webClient().postRequest("https://discord.com/api/v9/channels/" + id + " /typing", "true");
    }

    public void getPins() {
        System.out.println(client.webClient().downloadString("https://discord.com/api/v9/channels/" + id + "/pins"));
    }

    @Override
    public String toString() {
        return "name: " + name + " - " + "id: " + id + " - " + "last_message_id: " + last_message_id + " - " + "parent id: " + parent_id + " - " + "topic: " + topic + " - " + "nsfw: " + nsfw + " - " + "type: " + type;
    }
}
