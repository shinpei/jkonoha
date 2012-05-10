package sugar.tokenizer;

import commons.konoha2.*;

/**
 * Ftokenizer in original konoha2
 * @author okachin
 *
 */

public interface FTokenizer {
	
	/**
	 * for parseINDENT, parseSKIP ... in original konoha2
	 * @param CTX ctx
	 * @param KToken tk
	 * @param TEnv tenv
	 * @param int pos
	 * @param KMethod thunk
	 * @return
	 */
	
	public abstract int parse(CTX ctx, KToken tk, TEnv tenv, int pos, KMethod thunk);
}