package pisi.unitedmeows.violentcat.action.limit;

import java.util.ArrayList;
import java.util.List;

public class SpecificRateListener {

	private int rateLimit;
	private long resetInterval;
	private int tempOnList;

	public SpecificRateListener(int _max, long _resetInterval) {
		rateLimit = _max;
		resetInterval = _resetInterval;
		executedMillis = new ArrayList<>();
	}

	protected List<Long> executedMillis;

	public void add() {
		tempOnList--;
		executedMillis.add(System.currentTimeMillis());
	}

	public void addNoRate() {
		tempOnList--;
	}

	protected boolean isRateLimited() {
		executedMillis.removeIf(time -> System.currentTimeMillis() - time >= resetInterval);
		boolean state = executedMillis.size() + tempOnList < rateLimit;
		if (state) {
			tempOnList++;
		}
		return !state;
	}
}
