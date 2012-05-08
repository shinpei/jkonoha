package commons.konoha2.kclass;

public class KString extends KObject {
	
	public String text;
	// TODO const char inline_text[SIZEOF_INLINETEXT];
	
	KString(String text) {
		this.text = text;
	}
	
	public final String text() {
		return text;
	}
	
	public final int size() {
		return this.text.length();
	}
}
