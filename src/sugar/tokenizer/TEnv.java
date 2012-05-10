package sugar.tokenizer;

import commons.konoha2.kclass.*;

import sugar.*;

/**
 * tenv_t in original konoha2  
 * @author okachin
 *
 */

public class TEnv {
	
	public String source;
	public int uline;
	public KArray<KToken> list;
	public int bol;   // begin of line
	public int indent_tab;
	public FTokenizer[] fmat;
	
	TEnv(String source, int uline, KArray<KToken> a, int indent_tab, FTokenizer[] fmat) {
		this.source = source;
		this.uline = uline;
		this.list = a;
		this.bol = 0;
		this.indent_tab = indent_tab;
		this.fmat = fmat;
	}
	
	/**
	 * This method is used to know the position of line.
	 * @param int pos
	 * @return int
	 */
	
	public final int lpos(int pos) {
		return (this.bol == 0) ? -1 : (int)(pos - this.bol);
	}
}