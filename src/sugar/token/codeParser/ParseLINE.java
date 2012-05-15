package sugar.token.codeParser;

import sugar.*;
import sugar.token.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Method;

/**
 * This class is used to parse "LINE" 
 * @author okachin
 *
 */

public final class ParseLINE implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int tok_start, K_Method thunk) {
		int ch, pos = tok_start;
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length()) break;
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(ch == '\n') break;
		}
//		return pos - 1;/*EOF*/
		return pos;/*EOF*/
	}
}
