package sugar.tokenizer;

import commons.konoha2.kclass.*;
import sugar.*;

public class TEnv {
	
	public String source;
	public int uline;
	public KArray<KToken> list;
	public int bol;   // begin of line
	public int indent_tab;
	
	TEnv(String source, int uline, KArray<KToken> a, int indent_tab) {
		this.source = source;
		this.uline = uline;
		this.list = a;
		this.bol = 0;
		this.indent_tab = indent_tab;
	}
	
	public final int lpos(int pos) {
		return (this.bol == 0) ? -1 : (int)(pos - this.bol);
	}
}