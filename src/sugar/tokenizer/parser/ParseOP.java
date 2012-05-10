package sugar.tokenizer.parser;

import sugar.*;

import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;
import commons.konoha2.kclass.KString;

/**
 * This class is used to parse "OP" 
 * @author okachin
 *
 */

public final class ParseOP implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
	
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
			tk.text = new KString(s.substring(0, pos - tok_start)); // KSETv(tk->text, new_kString(s, (pos - 1) - tok_start, SPOL_ASCII|SPOL_POOL));
			tk.tt = KToken.TK_OPERATOR;
			if(tk.text.size() == 1) {
				tk.topch = tk.text.text.charAt(0);
			}
		}
//		return pos - 1;
		return pos;
	}
}
