package sugar.token.codeParser;

import sugar.K_Token;


import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;
import commons.sugar.TEnv;

/**
 * This class is used to parse "USYMBOL" 
 * @author okachin
 *
 */

public final class ParseUSYMBOL implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		int ch, pos = tok_start;
		String ts = tenv.source;
//		while((ch = ts.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= ts.length()) break;
			if((ch = ts.charAt(pos)) == 0) break;
			if(ch == '_' || Character.isLetterOrDigit(ch)) continue; // nothing
			break;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
//			tk.text = new KString(ts.substring(tok_start, pos - 1)); // TODO KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			tk.text = new K_String(ts.substring(tok_start, pos)); // TODO KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			System.out.println(tk.text.text);
			tk.tt = K_Token.TK_USYMBOL;
		}
//		return pos - 1; // next
		return pos; // next
	}
}