package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

public final class ParseLINE implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		while((ch = tenv.source.charAt(pos++)) != 0) {
			System.out.println(tk.text.text);
			if(ch == '\n') break;
		}
		return pos - 1;/*EOF*/
	}
}
