package sugar.tokenizer.parser;

import sugar.*;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

/**
 * This class is used to parse "COMMENT" 
 * @author okachin
 *
 */

public final class ParseCOMMENT implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, prev = 0, level = 1, pos = tok_start + 2;
		/*@#nnnn is line number */
		if(tenv.source.charAt(pos) == '@' && tenv.source.charAt(pos + 1) == '#' && Character.isDigit(tenv.source.charAt(pos + 2))) {
			// TODO
			// tenv->uline >>= (sizeof(kshort_t)*8);
			// tenv->uline = (tenv->uline<<(sizeof(kshort_t)*8)) | (kshort_t)strtoll(tenv->source + pos + 2, NULL, 10);
		}
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length()) break;
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(ch == '\n') {
				tenv.uline += 1;
			}
			if(prev == '*'  && ch == '/') {
				level--;
//				if(level == 0) return pos;
				if(level == 0) return pos + 1;
			} else if(prev == '/' && ch == '*') {
				level++;
			}
			prev = ch;
		}
		if(tk != null /* CTX.IS_NOTNULL(tk) */) {
			// TODO perror.h
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with */");
			// KToken.Token_toERR(ctx, tk, errref);
		}
//		return pos - 1;/*EOF*/
		return pos;/*EOF*/
	}
}
