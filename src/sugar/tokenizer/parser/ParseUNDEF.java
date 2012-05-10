package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

public final class ParseUNDEF implements FTokenizer {
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			// TODO
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "undefined token character: %c", tenv->source[tok_start]);
			// KToken.Token_toERR(ctx, tk, errref);
		}
		while(tenv.source.charAt(++tok_start) != 0);
		return tok_start;
	}
}