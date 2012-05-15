package sugar.token.codeParser;

import sugar.*;

import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;

/**
 * This class is used to parse "UNDEF" 
 * @author okachin
 *
 */

public final class ParseUNDEF implements FTokenizer {
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			// TODO
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "undefined token character: %c", tenv->source[tok_start]);
			// KToken.Token_toERR(ctx, tk, errref);
		}
//		while(tenv.source.charAt(++tok_start) != 0);
		while(true) {
			tok_start++;
			if(tok_start >= tenv.source.length()) break;
			if(tenv.source.charAt(tok_start) != 0) break;
		}
		return tok_start;
	}
}