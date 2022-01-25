package pisi.unitedmeows.violentcat.client.selfclient.structs;

public class PresenceUpdateStruct {

	public UserStruct user;
	public long last_modified;


	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PresenceUpdateStruct{");
		sb.append("user=").append(user);
		sb.append(", last_modified=").append(last_modified);
		sb.append('}');
		return sb.toString();
	}
}
