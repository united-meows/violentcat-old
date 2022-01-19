package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.violentcat.action.limit.RateLimit;
import pisi.unitedmeows.violentcat.action.limit.RateListener;
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
	protected RateListener rateListener;

	public Action(DiscordActionPool _actionPool, MajorParameter _parameter, String _majorName) {
		majorParameter = _parameter;
		actionPool = _actionPool;
		majorName = _majorName;
		finished = false;
		timer = new Stopwatch();
		timer.reset();
	}


	public void pre(RateListener _rateListener) {
		rateListener = _rateListener;
	}


	public void end(Result _result) {
		result = _result;
		finished = true;
		rateListener.end(majorName);
	}

	public Result await() {
		while (!finished && !timer.isReached(timeout)) {
			kThread.sleep(1);
		}
		return result;
	}

	public Action<Result> setTimeout(int _timeout) {
		timeout = _timeout;
		return this;
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
		GLOBAL,
		OTHER;
	}
}
