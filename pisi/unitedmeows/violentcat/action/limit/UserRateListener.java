package pisi.unitedmeows.violentcat.action.limit;

import pisi.unitedmeows.violentcat.action.Action;

public class UserRateListener extends RateListener{

	public UserRateListener() {
		super(Action.MajorParameter.USER_ID, 5, 1000);
	}
}
