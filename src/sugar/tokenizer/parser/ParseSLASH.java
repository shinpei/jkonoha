package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.ParseCOMMENT;
import sugar.tokenizer.ParseLINE;
import sugar.tokenizer.ParseOP;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

public final class ParseSLASH implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		String ts = tenv.source.substring(tok_start);
		if(ts.charAt(1) == '/') {
			ParseLINE pl = new ParseLINE();
			return pl.parse(ctx, tk, tenv, tok_start, thunk);
		}
		if(ts.charAt(1) == '*') {
			ParseCOMMENT pc = new ParseCOMMENT();
			return pc.parse(ctx, tk, tenv, tok_start, thunk);
		}
		ParseOP po = new ParseOP();
		return po.parse(ctx, tk, tenv, tok_start, thunk);
	}	
}