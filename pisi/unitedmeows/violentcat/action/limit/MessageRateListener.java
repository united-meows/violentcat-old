package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;
import pisi.unitedmeows.yystal.parallel.Async;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageRateListener extends RateListener {
	public MessageRateListener() {
		super(Action.MajorParameter.SEND_MESSAGE, 5, 5000L);
	}
}