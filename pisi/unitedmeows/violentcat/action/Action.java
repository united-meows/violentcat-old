package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.yystal.clazz.function;
import pisi.unitedmeows.yystal.parallel.IFunction;
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

	protected RateLimit rateLimit;

	public Action(MajorParameter _parameter, String _majorName, RateLimit _rateLimit) {
		majorParameter = _parameter;
		majorName = _majorName;
		finished = false;
		timer = new Stopwatch();
		timer.reset();
		rateLimit = _rateLimit;
	}


	public void end(Result _result, Map<String, List<String>> _responseHeaders) {
		finished = true;
		/* do ratelimit parsing */

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
		return rateLimit;
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
