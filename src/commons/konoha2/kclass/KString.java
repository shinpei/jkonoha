package commons.konoha2.kclass;

/**
 * _kString in original konoha2
 * @author okachin
 *
 */

public class KString extends KObject {
	
	public String text;
	// TODO const char inline_text[SIZEOF_INLINETEXT];
	
	public KString(String text) {
		this.text = text;
	}
	
	/**
	 * size
	 * @return int
	 */
	
	public final int size() {
		return this.text.length();
	}
}
