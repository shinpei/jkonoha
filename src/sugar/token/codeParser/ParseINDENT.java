package sugar.token.codeParser;

import sugar.*;
import sugar.token.*;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import commons.sugar.TEnv;

/**
 * This class is used to parse "INDENT" 
 * @author okachin
 *
 */

public final class ParseINDENT implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  K_Token tk, TEnv tenv, int pos, K_Method thunk) {
		int ch, c = 0;
//		while((ch = tenv.source.charAt(pos++)) != 0) {
		while(true) {
			pos++;
			if(pos >= tenv.source.length()) break;
			if((ch = tenv.source.charAt(pos)) == 0) break;
			if(ch == '\t') { c += tenv.indent_tab; }
			else if(ch == ' ') { c += 1; }
			break;
		}
		if(tk != null/* TODO CTX.IS_NOTNULL(tk) */) {
			tk.tt = K_Token.TK_INDENT;
			tk.lpos = 0;		
		}
		return pos - 1;
	}
}	