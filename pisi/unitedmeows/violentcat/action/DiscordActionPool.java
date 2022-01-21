package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.violentcat.action.limit.ChannelRateListener;
import pisi.unitedmeows.violentcat.action.limit.GuildRateListener;
import pisi.unitedmeows.violentcat.action.limit.MessageRateListener;
import pisi.unitedmeows.violentcat.action.limit.RateLimit;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.kThread;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class DiscordActionPool extends Thread {

	protected boolean running;

	protected ChannelRateListener channelRateListener = new ChannelRateListener();
	protected GuildRateListener guildRateListener = new GuildRateListener();
	protected MessageRateListener messageRateListener = new MessageRateListener();

	@Override
	public void run() {
		running = true;
		while (running) {
			channelRateListener.tick();
			guildRateListener.tick();
			messageRateListener.tick();
			kThread.sleep(100);
		}
	}

	public void queue(Action<?> action) {
		if (action.majorParameter() == Action.MajorParameter.CHANNEL_ID) {
			action.pre(channelRateListener);
			channelRateListener.queue(action);
		} else if (action.majorParameter() == Action.MajorParameter.GUILD_ID) {
			action.pre(guildRateListener);
			guildRateListener.queue(action);
		} else if (action.majorParameter() == Action.MajorParameter.SEND_MESSAGE) {
			action.pre(messageRateListener);
			messageRateListener.queue(action);
		}
	}

	public void drain() {
		running = false;
	}
}