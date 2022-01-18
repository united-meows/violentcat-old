package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;

public class ChannelRateListener extends RateListener {

	public static final long CHANNEL_RATE_RESET_INTERVAL = 5000L;
	public static final int CHANNEL_RATE_LIMIT = 5;

	public ChannelRateListener() {
		super(Action.MajorParameter.CHANNEL_ID, CHANNEL_RATE_LIMIT, CHANNEL_RATE_RESET_INTERVAL);
	}

}
