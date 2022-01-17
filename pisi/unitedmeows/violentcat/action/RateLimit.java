package pisi.unitedmeows.violentcat.action;

public class RateLimit {

	protected int limit;
	protected int remaining;
	protected long resetAfterMilliseconds;
	protected int resetAfterSeconds;

	public RateLimit(int _limit, int _remaining, long _resetAfterMilliseconds, int _resetAfterSeconds) {
		limit = _limit;
		remaining = _remaining;
		resetAfterMilliseconds = _resetAfterMilliseconds;
		resetAfterSeconds = _resetAfterSeconds;
	}

	public int limit() {
		return limit;
	}

	public int remaining() {
		return remaining;
	}

	public int resetAfterSeconds() {
		return resetAfterSeconds;
	}

	public long resetAfterMilliseconds() {
		return resetAfterMilliseconds;
	}
}
