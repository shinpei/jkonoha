package sugar.token.codeParser;

import sugar.*;
import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;

/**
 * This class is used to parse "DQUOTE" 
 * @author okachin
 *
 */

public final class ParseDQUOTE implements FTokenizer {
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		int ch, prev = '"', pos = tok_start + 1;
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length()) break;
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(ch == '\n') {
				break;
			}
			if(ch == '"' && prev != '\\') {
				if(tk != null /* CTX.IS_NOTNULL(tk) */) {
//					tk.text = new KString(tenv.source.substring(tok_start + 1, pos - 1)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					tk.text = new K_String(tenv.source.substring(tok_start + 1, pos)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					tk.tt = K_Token.TK_TEXT;
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