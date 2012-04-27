package jkonoha;

class Tenv {
	String source;
	long uline;
	char bol;
	int indent_tab;
	
	Tenv(String source, long uline, int indent_tab) {
		this.source = source;
		this.uline = uline;
		this.indent_tab = indent_tab;
	}
}

abstract class Parser {

	public abstract int parse(CTX ctx, KToken tk, Tenv tenv, int pos, KMethod thunk);
	
	public static final Parser parseINDENT = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
			char ch;
			int c = 0;
			while((ch = tenv.source[pos++]) != null) {
				if(ch == '\t') { c += tenv.indent_tab; }
				else if(ch == ' ') { c += 1; }
				break;
			}
			if(/* tk is not null */) {
				// tk.tt = ; // TODO
				tk.lpos = 0;		
			}
			return pos - 1;
		}
	}
	
	public static final Parser parseNL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
			tenv.uline += 1;
			tenv.bol = tenv.source[pos + 1];
			return parseINDENT(ctx, tk, tenv, pos, thunk)
		}
	}
	
	public static final Parser parseNUM = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int tok_start, KMethod thunk) {
			char ch;
			int pos = tok_start, dot = 0;
			String ts = tenv.source;
			while((ch = ts[pos++]) != null) {
				if(ch == '_') continue; // nothing
				if(ch == '.') {
					if(/* ts[pos] is not digit */) {
						pos--;
						break;
					}
					dot++;
					continue;
				}
				if((ch == 'e' || ch == 'E') && (ts[pos] == '+' || ts[pos] =='-')) {
					pos++;
					continue;
				}
				if(/* ch is not alnum */) break;
			}
			if(/* tk is not NULL */) {
				// TODO
				// KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
				// tk->tt = (dot == 0) ? TK_INT : TK_FLOAT;
			}
			return pos - 1;  // next
	}
		
	public static final Parser parseSYMBOL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int tok_start, KMethod thunk) {
			char ch;
			int pos = tok_start;
			String ts = tenv.source;
			while((ch = ts[pos++]) != 0) {
				if(ch == '_' || isalnum(ch)) continue; // nothing
				break;
			}
			if(/* tk is not NULL */) {
				KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
				tk->tt = TK_SYMBOL;
			}
			return pos - 1;  // next
		}
	};
	
	public static final Parser parseUSYMBOL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseMSYMBOL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseOP1 = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseOP = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseLINE = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseCOMMENT = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseSLASH = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseDQUOTE = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseSKIP = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseUNDEF = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
	
	public static final Parser parseBLOCK = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	}
}

class FToken {
	public final int _NULL = 0;
	public final int _UNDEF = 0;
	public final int _DIGIT = 0;
	public final int _UALPHA = 0;
	public final int _LALPHA = 0;
	public final int _MULTI = 0;
	public final int _NL = 0;
	public final int _TAB = 0;
	public final int _SP = 0;
	public final int _LPAR = 0;
	public final int _RPAR = 0;
	public final int _LSQ = 0;
	public final int _RSQ = 0;
	public final int _LBR = 0;
	public final int _RBR = 0;
}

abstract class Tokenize {
	static void tokenize(CTX ctx, Tenv env) {
		char ch;
		int pos = 0;
	}
}

class KTokenize extends Tokenize {
	CTX ctx;
	Tenv tenv;
	KArray a;
	
	KTokenize(CTX ctx, String source, long uline, KArray a) {
		this.ctx = ctx;
		this.tenv = new Tenv(source, uline);
		this.a = a;
	}
	
	public void ktokenize() {
		long i;
		
		tokenize(this.ctx, this.tenv);
		if(this.uline == 0) {
			for(i = pos;; i++) {
				a.Wtoks[i].uline = 0;
			}
		}
	}
}

class ParseSyntaxRule {
	KTokenize ktokenize;
	
	PraseSyntaxRule() {
	}
}