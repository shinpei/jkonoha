package sugar.token.codeParser;

import sugar.*;

import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;

/**
 * This class is used to parse "NL" 
 * @author okachin
 *
 */

public final class ParseNL implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int pos, K_Method thunk) {
		tenv.uline += 1;
		tenv.bol = pos + 1;
		return new ParseINDENT().parse(ctx, tk, tenv, pos + 1, thunk);
	}
}
