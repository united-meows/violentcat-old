package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.violentcat.action.limit.RateLimit;
import pisi.unitedmeows.yystal.clazz.function;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.List;
import java.util.Map;

public abstract class Action<Result> extends function {

	private static final int DEFAULT_TIMEOUT = 10000;

	protected boolean finished;
	protected Stopwatch timer;
	protected int timeout = DEFAULT_TIMEOUT;
	protected MajorParameter majorParameter;
	protected Result result;
	protected String majorName;
	protected DiscordActionPool actionPool;

	public Action(DiscordActionPool _actionPool, MajorParameter _parameter, String _majorName) {
		majorParameter = _parameter;
		actionPool = _actionPool;
		majorName = _majorName;
		finished = false;
		timer = new Stopwatch();
		timer.reset();
	}


	public void end(Result _result, Map<String, List<String>> _responseHeaders) {
		finished = true;
		/* do ratelimit parsing */
		List<String> limit = _responseHeaders.getOrDefault("x-ratelimit-limit", null);
		if (limit != null)
			rateLimit().limit = Integer.parseInt(limit.get(0));

		List<String> remaining = _responseHeaders.getOrDefault("x-ratelimit-remaining", null);
		if (remaining != null)
			rateLimit().remaining = Integer.parseInt(remaining.get(0));

		List<String> resetAfter = _responseHeaders.getOrDefault("x-ratelimit-reset-after", null);
		if (resetAfter != null)
			rateLimit().resetAfterSeconds = Double.parseDouble(resetAfter.get(0));

		List<String> reset = _responseHeaders.getOrDefault("x-ratelimit-reset", null);
		if (reset != null)
			rateLimit().resetAfterMilliseconds = Long.parseLong(reset.get(0).replaceAll("\\.[^ ]+", ""));


		result = _result;
	}

	public Result await() {
		while (!finished && !timer.isReached(timeout)) {
			kThread.sleep(1000);
		}
		return result;
	}

	public Action<Result> setTimeout(int _timeout) {
		timeout = _timeout;
		return this;
	}

	public RateLimit rateLimit() {
		return actionPool.rateLimit(majorParameter, majorName);
	}

	public MajorParameter majorParameter() {
		return majorParameter;
	}

	public String majorName() {
		return majorName;
	}


	public static enum MajorParameter {
		GUILD_ID,
		CHANNEL_ID,
		WEBHOOK_ID,
		OTHER;
	}
}
