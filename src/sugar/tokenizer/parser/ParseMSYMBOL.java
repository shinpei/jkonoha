package sugar.tokenizer.parser;

import sugar.*;
import sugar.tokenizer.*;
import commons.*;
import commons.konoha2.*;
import commons.konoha2.kclass.*;

/**
 * This class is used to parse "MYSYMBOL" 
 * @author okachin
 *
 */

public final class ParseMSYMBOL implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		String ts = tenv.source;
		while((ch = ts.charAt(pos++)) != 0) {
			if(!(ch < 0)) break;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			tk.text = new KString(ts.substring(tok_start, pos - 1)); // TODO KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_UFT8));
			System.out.println(tk.text.text);
			tk.tt = KToken.TK_MSYMBOL;
		}
		return pos - 1; // next
	}
}