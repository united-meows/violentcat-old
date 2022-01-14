package pisi.unitedmeows.violentcat.holders;

public class Presence {

	protected String statusMessage;
	protected Type type;
	protected Status status = Status.ONLINE;

	protected Presence(Status _status, Type _type, String _statusMessage) {
		type = _type;
		statusMessage = _statusMessage;
		status = _status != null ? _status : status;
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

	public static Presence streaming(String streamLink) {
		return new Presence(null, Type.STREAMING, streamLink);
	}

	public static Presence listening(String listeningTo) {
		return new Presence(null, Type.WATCHING, listeningTo);
	}

	public Presence setStatus(Status _status) {
		status = _status;
		return this;
	}

	public enum Type {
		PLAYING,
		LISTENING,
		STREAMING,
		WATCHING,
		NOTHING;
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
