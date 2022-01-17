package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

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
				RateLimit theRateLimit = rateLimit(action.majorParameter(), action.majorName());
				long doAfter = 0;
				if (theRateLimit.limit != 2173) {
					if (theRateLimit.remaining <= 0) {
						doAfter = theRateLimit.resetAfterMilliseconds - System.currentTimeMillis();
						if (doAfter < 0) {
							doAfter = 50;
						}
					} else if (theRateLimit.remaining > 2) {
						rateLimits.get(action.majorParameter).remove(action.majorName);
					}
				} else
					rateLimits.get(action.majorParameter).remove(action.majorName);

				if (doAfter == 0) {
					Async.async(action::run);
				} else {
					Async.async_w(action::run, doAfter);
				}
			}
			kThread.sleep(1);
		}
	}

	public RateLimit rateLimit(Action.MajorParameter parameter, String parameterName) {
		return rateLimits.computeIfAbsent(parameter, (k) -> new HashMap<String, RateLimit>()).computeIfAbsent
				(parameterName, (k) ->
						new RateLimit(2173, 2173, -1,  -1));
	}

	public void queue(Action<?> action) {
		actionQueue.add(action);
	}

	public void drain() {
		running = false;
	}
}