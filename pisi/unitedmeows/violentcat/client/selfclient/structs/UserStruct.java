package pisi.unitedmeows.violentcat.client.selfclient.structs;

public class UserStruct {

	public String id;
	public String discriminator;
	public String avatar;
	public int public_flags;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("UserStruct{");
		sb.append("id='").append(id).append('\'');
		sb.append(", discriminator='").append(discriminator).append('\'');
		sb.append(", avatar='").append(avatar).append('\'');
		sb.append(", public_flags=").append(public_flags);
		sb.append('}');
		return sb.toString();
	}
}
