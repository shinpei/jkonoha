package sugar.token.codeParser;

import sugar.*;
import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

/**
 * This class is used to parse "SKIP" 
 * @author okachin
 *
 */

public final class ParseSKIP implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		return tok_start + 1;
	}
}