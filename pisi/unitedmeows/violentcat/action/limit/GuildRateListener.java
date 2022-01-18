package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;

public class GuildRateListener extends RateListener {
	public GuildRateListener() {
		super(Action.MajorParameter.GUILD_ID, 5, 1000);
	}
}