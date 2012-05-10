package sugar.tokenizer;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import sugar.*;
import sugar.tokenizer.*;

public interface FTokenizer {
	
	public abstract int parse(CTX ctx, KToken tk, TEnv tenv, int pos, KMethod thunk);
}