package sugar.tokenizer.parser;

import sugar.KToken;
import sugar.tokenizer.FTokenizer;
import sugar.tokenizer.TEnv;

import commons.konoha2.CTX;
import commons.konoha2.kclass.KMethod;
import commons.konoha2.kclass.KString;

final class ParseOP1 implements FTokenizer {

	@Override public final int parse(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		if(CTX.IS_NOTNULL(tk)) {
			String s = tenv.source.substring(tok_start);
			tk.text = new KString(s.substring(0, 1)); // KSETv(tk->text, new_kString(s, 1, SPOL_ASCII|SPOL_POOL));
			System.out.println(tk.text.text);
			tk.tt = KToken.TK_OPERATOR;
			tk.topch = s.charAt(tok_start);
		}
		return tok_start + 1;
	}
}