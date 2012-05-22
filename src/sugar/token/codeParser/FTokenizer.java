package sugar.token.codeParser;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import commons.sugar.TEnv;
import sugar.*;
import sugar.token.*;

/**
 * Ftokenizer in original konoha2
 * @author okachin
 *
 */

public interface FTokenizer {
	
	/**
	 * for parseINDENT, parseSKIP ... in original konoha2
	 * @param ctx
	 * konoha's context
	 * @param tk
	 * token 
	 * @param tenv
	 * source, line, and etc...
	 * @param pos
	 * position of line
	 * @param thunk
	 * @return int
	 */
	
	public abstract int parse(CTX ctx, K_Token tk, TEnv tenv, int pos, K_Method thunk);
}