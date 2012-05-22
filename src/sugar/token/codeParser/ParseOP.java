package sugar.token.codeParser;

import sugar.*;


import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;
import commons.sugar.TEnv;

/**
 * This class is used to parse "OP" 
 * @author okachin
 *
 */

public final class ParseOP implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
	
		int ch, pos = tok_start;
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length()) break;
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(Character.isLetter(ch)) break;
			switch(ch) {
			case '<': case '>': case '@': case '$': case '#':
			case '+': case '-': case '*': case '%': case '/':
			case '=': case '&': case '?': case ':': case '.':
			case '^': case '!': case '~': case '|':
				continue;
			}
			break;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			String s = tenv.source.substring(tok_start);
			// TODO
//			tk.text = new KString(s.substring(0, (pos - 1) - tok_start)); // KSETv(tk->text, new_kString(s, (pos - 1) - tok_start, SPOL_ASCII|SPOL_POOL));
			tk.text = new K_String(s.substring(0, pos - tok_start)); // KSETv(tk->text, new_kString(s, (pos - 1) - tok_start, SPOL_ASCII|SPOL_POOL));
			tk.tt = K_Token.TK_OPERATOR;
			if(tk.text.size() == 1) {
				tk.topch = tk.text.text.charAt(0);
			}
		}
//		return pos - 1;
		return pos;
	}
}
