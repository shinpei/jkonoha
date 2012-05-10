package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;
import commons.konoha2.kclass.KString;

public final class ParseDQUOTE implements FTokenizer {
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, prev = '"', pos = tok_start + 1;
		while((ch = tenv.source.charAt(pos++)) != 0) {
			if(ch == '\n') {
				break;
			}
			if(ch == '"' && prev != '\\') {
				if(tk != null /* CTX.IS_NOTNULL(tk) */) {
					tk.text = new KString(tenv.source.substring(tok_start + 1, pos - 1)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					System.out.println(tk.text.text);
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
		return pos - 1;
	}
}