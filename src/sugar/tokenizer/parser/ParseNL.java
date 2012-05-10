package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;

final class ParseNL implements FTokenizer {
	
	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int pos, KMethod thunk) {
		tenv.uline += 1;
		tenv.bol = pos + 1;
		ParseINDENT pi = new ParseINDENT();
		return pi.parse(ctx, tk, tenv, pos + 1, thunk);
	}
}
