package sugar.token.codeParser;

import sugar.*;


import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;
import commons.sugar.TEnv;

/**
 * This class is used to parse "OP1" 
 * @author okachin
 *
 */

public final class ParseOP1 implements FTokenizer {

	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			String s = tenv.source.substring(tok_start);
			tk.text = new K_String(s.substring(0, 1)); // KSETv(tk->text, new_kString(s, 1, SPOL_ASCII|SPOL_POOL));
			tk.tt = K_Token.TK_OPERATOR;
			tk.topch = s.charAt(0);
		}
		return tok_start + 1;
	}
}