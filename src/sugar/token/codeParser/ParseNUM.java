package sugar.token.codeParser;

import sugar.*;


import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;
import commons.sugar.TEnv;

/**
 * This class is used to parse "NUM" 
 * @author okachin
 *
 */

public final class ParseNUM implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		int ch, pos = tok_start, dot = 0;
		String ts = tenv.source;
//		while((ch = ts.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= ts.length()) break;
			if((ch = ts.charAt(pos)) == 0) break;
			if(ch == '_') continue; // nothing
			if(ch == '.') {
				if(!Character.isDigit(ts.charAt(pos))) {
					break;
				}
				dot++;
				continue;
			}
			if((ch == 'e' || ch == 'E') && (ts.charAt(pos) == '+' || ts.charAt(pos) == '-')) {
				pos++;
				continue;
			}
			if(!Character.isLetterOrDigit(ch)) break;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
//			tk.text = new KString(ts.substring(tok_start, pos - 1)); // TODO KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			tk.text = new K_String(ts.substring(tok_start, pos)); // TODO KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			tk.tt = (dot == 0) ? K_Token.TK_INT : K_Token.TK_FLOAT;
		}
//		return pos - 1;  // next
		return pos;  // next
	}
}