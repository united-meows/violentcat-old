package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.violentcat.action.limit.RateLimit;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.kThread;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class DiscordActionPool extends Thread {

	/* find a way to clear the map */

	protected Deque<Action<?>> actionQueue = new ArrayDeque<>();
	protected HashMap<Action.MajorParameter, HashMap<String, RateLimit>> rateLimits = new HashMap<>();
	protected boolean running;

	@Override
	public void run() {
		running = true;
		while (running) {
			Action<?> action = actionQueue.poll();
			if (action != null) {
				RateLimit theRateLimit = action.rateLimit();
				System.out.println(theRateLimit);
				long doAfter = 0;
				if (theRateLimit.limit != 2173) {
					if (theRateLimit.remaining <= 0) {
						doAfter = theRateLimit.resetAfterMilliseconds - Instant.now().getEpochSecond();
						if (doAfter < 0) {
							doAfter = 50;
						}
					}
				}
				if (doAfter == 0) {
					Async.async(action::run);
				} else {
					Async.async_w(action::run, doAfter);
				}
			}
			kThread.sleep(1);
		}

	}

	public void queue(Action<?> action) {
		actionQueue.add(action);
	}

	public void drain() {
		running = false;
	}
}