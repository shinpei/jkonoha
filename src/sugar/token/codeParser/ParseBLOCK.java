package sugar.token.codeParser;

import sugar.K_Token;
import sugar.token.*;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;
import commons.konoha2.kclass.K_String;
import commons.sugar.TEnv;

/**
 * This class is used to parse "BLOCK" 
 * @author okachin
 *
 */

public final class ParseBLOCK implements FTokenizer { // TODO
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		int ch, level = 1, pos = tok_start + 1;
		FTokenizer[] fmat = tenv.fmat;
		tk.lpos += 1;
		while((ch = Tokenizer.kchar(tenv.source, pos)) != 0) {
			if(ch == Tokenizer._RBR/*}*/) {
				level--;
				if(level == 0) {
					if(tk != null /* CTX.IS_NOTNULL(tk) */) {
						tk.text = new K_String(tenv.source.substring(tok_start + 1, pos)); // TODO KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos-2)-(tok_start)+1), 0));
						tk.tt = K_Token.TK_CODE;
					}
					return pos + 1;
				}
				pos++;
			}
			else if(ch == Tokenizer._LBR/*'{'*/) {
				level++; pos++;
			}
			else {
				pos = fmat[ch].parse(ctx, /* TODO K_NULLTOKEN*/null, tenv, pos, null);
			}
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			// TODO
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with }");
			// Token_toERR(_ctx, tk, errref);
		}
		return pos - 1;
	}	
}