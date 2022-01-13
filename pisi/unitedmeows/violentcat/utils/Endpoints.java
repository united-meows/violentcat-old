package pisi.unitedmeows.violentcat.utils;

import pisi.unitedmeows.violentcat.Violentcat;

public enum Endpoints {

    AUDIT_LOG("/guilds/%s/audit-logs"), /*{guild.id}*/
    CHANNELS("/channels/%s"), /*{channel.id*/
    GUILD_EMOJI("/guilds/%s/emojis/%s"), /*{guild.id} & {emoji.id}*/
    GUILD("/guilds/%s"), /*{guild.id}*/
    GUILD_TEMPLATE("/guilds/templates/%s"), /*{template.code}*/
    INVITE("/invites/%s"), /*{invite.code}*/
    STAGE("/stage-instances/%s"), /*{channel.id} */
    STICKER("/stickers/%s"), /*{sticker.id} */
    STICKER_PACKS("/sticker-packs"),
    STICKERS_GUILD("/guilds/%s/stickers"), /*{guild.id}*/
    STICKER_GUILD("/guilds/%s/stickers/%s"), /*{guild.id} & {sticker.id}*/
    USER("/users/%s"), /*{user.id}*/
    WEBHOOK("/webhooks/%s"), /*{webhook.id}*/
    WEBHOOK_w_TOKEN("/webhooks/%s/%s"); /*{webhook.id} & {webhook.token}*/


    String url;

    Endpoints(String _url) {
        this.url = _url;
    }



    public String getURL(Endpoints endpoints) {
        return "https://" + Violentcat.DISCORD_DOMAIN + "/api/v" + Violentcat.DISCORD_API_VERSION + endpoints;
    }



}
