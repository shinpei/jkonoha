package sugar.token.codeParser;

import sugar.*;


import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.sugar.TEnv;

/**
 * This class is used to parse "SLASH" 
 * @author okachin
 *
 */

public final class ParseSLASH implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		String ts = tenv.source.substring(tok_start);
		if(ts.length() < 2) return new ParseOP().parse(ctx, tk, tenv, tok_start, thunk);
		if(ts.charAt(1) == '/') {
			return new ParseLINE().parse(ctx, tk, tenv, tok_start, thunk);
		}
		if(ts.charAt(1) == '*') {
			return new ParseCOMMENT().parse(ctx, tk, tenv, tok_start, thunk);
		}
		return new ParseOP().parse(ctx, tk, tenv, tok_start, thunk);
	}	
}