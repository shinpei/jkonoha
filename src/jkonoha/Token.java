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
			while((ch = tenv.source.charAt(pos++)) != null) {
				if(ch == '\t') { c += tenv.indent_tab; }
				else if(ch == ' ') { c += 1; }
				break;
			}
			if(CTX.IS_NOTNULL(tk)) {
				// tk.tt = ; // TODO
				tk.lpos = 0;		
			}
			return pos - 1;
		}
	};
	
	public static final Parser parseNL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
			tenv.uline += 1;
			tenv.bol = tenv.source.charAt(pos + 1);
			return parseINDENT(ctx, tk, tenv, pos, thunk);
		}
	};
	
	public static final Parser parseNUM = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int tok_start, KMethod thunk) {
			char ch;
			int pos = tok_start, dot = 0;
			String ts = tenv.source;
			while((ch = ts.charAt(pos++)) != null) {
				if(ch == '_') continue; // nothing
				if(ch == '.') {
					if(!Character.isDigit(ts.charAt(pos))) {
						pos--;
						break;
					}
					dot++;
					continue;
				}
				if((ch == 'e' || ch == 'E') && (ts.charAt(pos) == '+' || ts.charAt(pos) == '-')) {
					pos++;
					continue;
				}
				if(!Character.isLetterOrDigit(ch)) break;
			}
			if(CTX.IS_NOTNULL(tk)) {
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
			while((ch = ts.charAt(pos++)) != 0) {
				if(ch == '_' || isalnum(ch)) continue; // nothing
				break;
			}
			if(CTX.IS_NOTNULL(tk)) {
				// TODO
				// KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
				// tk->tt = TK_SYMBOL;
			}
			return pos - 1;  // next
		}
	};
	
	public static final Parser parseUSYMBOL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseMSYMBOL = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseOP1 = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseOP = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseLINE = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseCOMMENT = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseSLASH = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseDQUOTE = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseSKIP = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseUNDEF = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
	
	public static final Parser parseBLOCK = new Parser() {
		@Override public int parse(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		}
	};
}

class FToken extends Parser {
	
	public static final int _NULL = 0;
	public static final int _UNDEF = 1;
	public static final int _DIGIT = 2;
	public static final int _UALPHA = 3;
	public static final int _LALPHA = 4;
	public static final int _MULTI = 5;
	public static final int _NL = 6;
	public static final int _TAB = 7;
	public static final int _SP = 8;
	public static final int _LPAR = 9;
	public static final int _RPAR = 10;
	public static final int _LSQ = 11;
	public static final int _RSQ = 12;
	public static final int _LBR = 13;
	public static final int _RBR = 14;
	public static final int _LT = 15;
	public static final int _GT = 16;
	public static final int _QUOTE = 17;
	public static final int _DQUOTE = 18;
	public static final int _BKQUOTE = 19;
	public static final int _OKIDOKI = 20;
	public static final int _SHARP = 21;
	public static final int _DOLLAR = 22;
	public static final int _PER = 23;
	public static final int _AND = 24;
	public static final int _STAR = 25;
	public static final int _PLUS = 26;
	public static final int _COMMA = 27;
	public static final int _MINUS = 28;
	public static final int _DOT = 29;
	public static final int _SLASH = 30;
	public static final int _COLON = 31;
	public static final int _SEMICOLON = 32;
	public static final int _EQ = 33;
	public static final int _QUESTION = 34;
	public static final int _AT = 35;
	public static final int _VAR = 36;
	public static final int _CHILDER = 37;
	public static final int _BKSLASH = 38;
	public static final int _HAT = 39;
	public static final int _UNDER = 40;
	public static final int _KCHAR_MAX = 41;
	
	public static final char[] cMatrix = {
	0/*nul*/, 1/*soh*/, 1/*stx*/, 1/*etx*/, 1/*eot*/, 1/*enq*/, 1/*ack*/, 1/*bel*/,
	1/*bs*/,  _TAB/*ht*/, _NL/*nl*/, 1/*vt*/, 1/*np*/, 1/*cr*/, 1/*so*/, 1/*si*/,
	/*	020 dle  021 dc1  022 dc2  023 dc3  024 dc4  025 nak  026 syn  027 etb*/
	1, 1, 1, 1,     1, 1, 1, 1,
	/*	030 can  031 em   032 sub  033 esc  034 fs   035 gs   036 rs   037 us*/
	1, 1, 1, 1,     1, 1, 1, 1,
	/*040 sp   041  !   042  "   043  #   044  $   045  %   046  &   047  '*/
	_SP, _OKIDOKI, _DQUOTE, _SHARP, _DOLLAR, _PER, _AND, _QUOTE,
	/*050  (   051  )   052  *   053  +   054  ,   055  -   056  .   057  /*/
	_LPAR, _RPAR, _STAR, _PLUS, _COMMA, _MINUS, _DOT, _SLASH,
	/*060  0   061  1   062  2   063  3   064  4   065  5   066  6   067  7 */
	_DIGIT, _DIGIT, _DIGIT, _DIGIT,  _DIGIT, _DIGIT, _DIGIT, _DIGIT,
	/*	070  8   071  9   072  :   073  ;   074  <   075  =   076  >   077  ? */
	_DIGIT, _DIGIT, _COLON, _SEMICOLON, _LT, _EQ, _GT, _QUESTION,
	/*100  @   101  A   102  B   103  C   104  D   105  E   106  F   107  G */
	_AT, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA,
	/*110  H   111  I   112  J   113  K   114  L   115  M   116  N   117  O */
	_UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA,
	/*120  P   121  Q   122  R   123  S   124  T   125  U   126  V   127  W*/
	_UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA, _UALPHA,
	/*130  X   131  Y   132  Z   133  [   134  \   135  ]   136  ^   137  _*/
	_UALPHA, _UALPHA, _UALPHA, _LSQ, _BKSLASH, _RSQ, _HAT, _UNDER,
	/*140  `   141  a   142  b   143  c   144  d   145  e   146  f   147  g*/
	_BKQUOTE, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA,
	/*150  h   151  i   152  j   153  k   154  l   155  m   156  n   157  o*/
	_LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA,
	/*160  p   161  q   162  r   163  s   164  t   165  u   166  v   167  w*/
	_LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA, _LALPHA,
	/*170  x   171  y   172  z   173  {   174  |   175  }   176  ~   177 del*/
	_LALPHA, _LALPHA, _LALPHA, _LBR, _VAR, _RBR, _CHILDER, 1,
	};	
	
	@Override public final int parse(CTX ctx, KToken tk, Tenv tenv, int pos, KMethod thunk) {
	}
	
	public final int kchar(String t, int pos) {
		int ch = t.charAt(pos);
		return (ch < 0) ? _MULTI : cMatrix[ch];
	}
	
	int MiniKonohaTokenMatrix(CTX ctx,  KToken tk, Tenv tenv, int pos, KMethod thunk) {
		
	}
}

class Tokenize {
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