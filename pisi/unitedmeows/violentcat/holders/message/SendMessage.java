package pisi.unitedmeows.violentcat.holders.message;

public class SendMessage {

	protected String content;
	protected boolean tts;

	public SendMessage setContent(String _content) {
		content = _content;
		return this;
	}
}
