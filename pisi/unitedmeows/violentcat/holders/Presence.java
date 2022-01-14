package pisi.unitedmeows.violentcat.holders;

public class Presence {

	protected String statusMessage;
	protected String url;
	protected Type type;
	protected Status status = Status.ONLINE;

	protected Presence(Status _status, Type _type, String _statusMessage) {
		type = _type;
		statusMessage = _statusMessage;
		status = _status != null ? _status : status;
	}

	protected void setUrl(String _url) {
		url = _url;
	}


	public Status getStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public Type getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public static Presence of(Type _type, String _message) {
		return new Presence(null, _type, _message);
	}

	public static Presence nothing() {
		return new Presence(null, Type.NOTHING, null);
	}

	public static Presence playing(String message) {
		return new Presence(null, Type.PLAYING, message);
	}

	public static Presence watching(String message) {
		return new Presence(null, Type.WATCHING, message);
	}

	public static Presence streaming(String message, String url) {
		final Presence presence =  new Presence(null, Type.STREAMING, message);
		presence.setUrl(url);
		return presence;

	}

	public static Presence listening(String listeningTo) {
		return new Presence(null, Type.LISTENING, listeningTo);
	}

	public Presence setStatus(Status _status) {
		status = _status;
		return this;
	}

	public enum Type {
		PLAYING(0),
		STREAMING(1),
		LISTENING(2),
		WATCHING(3),
		CUSTOM(4),
		COMPETING(5),
		NOTHING(2173);

		int id;
		Type(int _id) {
			id = _id;
		}

		public int getId() {
			return id;
		}
	}

	public enum Status {
		ONLINE("online"),
		IDLE("idle"),
		DO_NOT_DISTURB("dnd"),
		INVISIBLE("invisible"),
		OFFLINE("offline");
		String code;
		Status(String _code) {
			code = _code;
		}

		public String code() {
			return code;
		}
	}
}
