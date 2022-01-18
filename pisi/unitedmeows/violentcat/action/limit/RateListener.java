package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.yystal.parallel.Async;

import java.util.*;

public class RateListener {
	private Action.MajorParameter majorParameter;

	private int maxRate;
	private long resetInterval;

	public RateListener(Action.MajorParameter _majorParameter, int _maxRate, long _resetInterval) {
		majorParameter = _majorParameter;
		maxRate = _maxRate;
		resetInterval = _resetInterval;
		actions = new ArrayList<>();
		specificRateMap = new HashMap<>();
	}

	protected HashMap<String, SpecificRateListener> specificRateMap;
	protected List<Action<?>> actions;

	public void tick() {
		Iterator<Action<?>> actionIterator = actions.iterator();
		while (actionIterator.hasNext()) {
			Action<?> action = actionIterator.next();
			SpecificRateListener channelRateLimit = specificRateListener(action.majorName());
			if (!channelRateLimit.isRateLimited()) {
				Async.async(action::run);
				actionIterator.remove();
			}
		}
	}

	public void end(String majorName) {
		SpecificRateListener specificRateListener = specificRateMap.getOrDefault(majorName, null);
		if (specificRateListener != null)
			specificRateListener.add();
	}

	public void queue(Action<?> action) {
		actions.add(action);
	}

	public SpecificRateListener specificRateListener(String channelId) {
		return specificRateMap.computeIfAbsent(channelId, (k) -> new SpecificRateListener(maxRate, resetInterval));
	}


	public Action.MajorParameter majorParameter() {
		return majorParameter;
	}

}