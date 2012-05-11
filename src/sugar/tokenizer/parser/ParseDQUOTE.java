package sugar.tokenizer.parser;

import sugar.*;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;
import commons.konoha2.kclass.KString;

/**
 * This class is used to parse "DQUOTE" 
 * @author okachin
 *
 */

public final class ParseDQUOTE implements FTokenizer {
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, prev = '"', pos = tok_start + 1;
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length());
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(ch == '\n') {
				break;
			}
			if(ch == '"' && prev != '\\') {
				if(tk != null /* CTX.IS_NOTNULL(tk) */) {
//					tk.text = new KString(tenv.source.substring(tok_start + 1, pos - 1)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					tk.text = new KString(tenv.source.substring(tok_start + 1, pos)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					tk.tt = KToken.TK_TEXT;
				}
				return pos;
			}
			prev = ch;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			// TODO perror.h
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with \"");
			// KToken.Token_toERR(ctx, tk, errref);
		}
//		return pos - 1;
		return pos;
	}
}