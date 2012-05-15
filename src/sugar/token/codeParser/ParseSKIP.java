package sugar.token.codeParser;

import sugar.*;
import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;

/**
 * This class is used to parse "SKIP" 
 * @author okachin
 *
 */

public final class ParseSKIP implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		return tok_start + 1;
	}
}