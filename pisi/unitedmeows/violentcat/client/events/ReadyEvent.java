package pisi.unitedmeows.violentcat.client.events;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.violentcat.holders.ApplicationInfo;
import pisi.unitedmeows.violentcat.user.SelfUser;

public class ReadyEvent extends Event {

	private ApplicationInfo appInfo;
	private SelfUser selfUser;

	public ReadyEvent(ApplicationInfo _appInfo, SelfUser _selfUser) {
		appInfo = _appInfo;
		selfUser = _selfUser;
	}

	public SelfUser selfUser() {
		return selfUser;
	}

	public ApplicationInfo appInfo() {
		return appInfo;
	}
}
