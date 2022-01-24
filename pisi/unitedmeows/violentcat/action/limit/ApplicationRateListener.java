package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;

public class ApplicationRateListener extends RateListener
{
	public ApplicationRateListener() {
		super(Action.MajorParameter.APPLICATION_ID, 10, 1000L);
	}
}
