package pisi.unitedmeows.violentcat.action;

import pisi.unitedmeows.violentcat.action.limit.RateListener;
import pisi.unitedmeows.yystal.clazz.function;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.function.Consumer;

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
	protected Consumer<Result> afterTask;

	public Action(DiscordActionPool _actionPool, MajorParameter _parameter, String _majorName) {
		majorParameter = _parameter;
		actionPool = _actionPool;
		majorName = _majorName;
		finished = false;
		timer = new Stopwatch();
		timer.reset();
	}

	@Deprecated
	public void _queue() {
		actionPool.queue(this);
	}


	public void pre(RateListener _rateListener) {
		rateListener = _rateListener;
	}


	public void end(Result _result) {
		result = _result;
		finished = true;
		rateListener.end(majorName);
		if (afterTask != null) {
			afterTask.accept(result);
		}
	}

	public void endNoRate(Result _result) {
		result = _result;
		finished = true;
		rateListener.endNoRate(majorName);
		if (afterTask != null) {
			afterTask.accept(result);
		}
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

	public Action<Result> then(Consumer<Result> _afterTask) {
		if (afterTask != null)
			afterTask = afterTask.andThen(_afterTask);
		else
			afterTask = _afterTask;

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
		SEND_MESSAGE,
		CHANNEL_ID,
		USER_ID,
		APPLICATION_ID,
		WEBHOOK_ID,
		GLOBAL,
		OTHER;
	}
}
