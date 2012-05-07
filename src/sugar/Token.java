package sugar;

import commons.*;
import kclass.*;

final class TEnv {
	
	String source;
	int uline;
	KArray<KToken> list;
	int bol;   // begin of line
	int indent_tab;
	
	TEnv(String source, int uline, KArray<KToken> a, int indent_tab) {
		this.source = source;
		this.uline = uline;
		this.list = a;
		this.bol = 0;
		this.indent_tab = indent_tab;
	}
	
	public final int lpos(int pos) {
		return (this.bol == 0) ? -1 : (int)(pos - this.bol);
	}
}

final class FToken {
	
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
	
	public static final int kchar(String t, int pos) {
		int ch = t.charAt(pos);
		return (ch < 0) ? _MULTI : cMatrix[ch];
	}
	
	public static final int parseINDENT(CTX ctx,  KToken tk, TEnv tenv, int pos, KMethod thunk) {
		int ch, c = 0;
		while((ch = tenv.source.charAt(pos++)) != 0) {
			if(ch == '\t') { c += tenv.indent_tab; }
			else if(ch == ' ') { c += 1; }
			break;
		}
		if(CTX.IS_NOTNULL(tk)) {
			tk.tt = KToken.TK_INDENT;
			tk.lpos = 0;		
		}
		return pos - 1;
	}	
	
	public static final int parseNL(CTX ctx,  KToken tk, TEnv tenv, int pos, KMethod thunk) {
		tenv.uline += 1;
		tenv.bol = pos + 1;
		return parseINDENT(ctx, tk, tenv, pos + 1, thunk);
	}
	
	public static final int parseNUM(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start, dot = 0;
		String ts = tenv.source;
		while((ch = ts.charAt(pos++)) != 0) {
			if(ch == '_') continue; // nothing
			if(ch == '.') {
				if(!Character.isDigit(ts.charAt(pos))) {
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
			tk.tt = (dot == 0) ? KToken.TK_INT : KToken.TK_FLOAT;
		}
		return pos - 1;  // next
	}
		
	public static final int parseSYMBOL(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		String ts = tenv.source;
		while((ch = ts.charAt(pos++)) != 0) {
			if(ch == '_' || Character.isLetterOrDigit(ch)) continue; // nothing
			break;
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO
			// KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			tk.tt = KToken.TK_SYMBOL;
		}
		return pos - 1;  // next
	}	
	
	public static final int parseUSYMBOL(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		String ts = tenv.source;
		while((ch = ts.charAt(pos++)) != 0) {
			if(ch == '_' || Character.isLetterOrDigit(ch)) continue; // nothing
			break;
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO
			// KSETv(tk->text, new_kString(ts + tok_start, (pos-1)-tok_start, SPOL_ASCII));
			tk.tt = KToken.TK_USYMBOL;
		}
		return pos - 1; // next
	}
	
	public static final int parseMSYMBOL(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		String ts = tenv.source;
		while((ch = ts.charAt(pos++)) != 0) {
			if(!(ch < 0)) break;
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO
			// KSETv(tk->text, new_kString(ts + tok_start, (pos - 1) - tok_start, SPOL_UTF8));
			tk.tt = KToken.TK_MSYMBOL;
		}
		return pos - 1; // next
	}
	
	public static final int parseOP1(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		if(CTX.IS_NOTNULL(tk)) {
			String s = tenv.source.substring(tok_start);
			// TODO
			// KSETv(tk->text, new_kString(s, 1, SPOL_ASCII|SPOL_POOL));
			tk.tt = KToken.TK_OPERATOR;
			tk.topch = s.charAt(tok_start);
		}
		return tok_start + 1;
	}
	
	public static final int parseOP(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		while((ch = tenv.source.charAt(pos++)) != 0) {
		if(Character.isLetter(ch)) break;
		switch(ch) {
			case '<': case '>': case '@': case '$': case '#':
			case '+': case '-': case '*': case '%': case '/':
			case '=': case '&': case '?': case ':': case '.':
			case '^': case '!': case '~': case '|':
			continue;
			}
			break;
		}
		if(CTX.IS_NOTNULL(tk)) {
			String s = tenv.source.substring(tok_start);
			// TODO
			// KSETv(tk->text, new_kString(s, (pos - 1) - tok_start, SPOL_ASCII|SPOL_POOL));
			tk.tt = KToken.TK_OPERATOR;
			// TODO
			// if(S_size(tk->text) == 1) {
			// 		tk->topch = S_text(tk->text)[0];
			// }
		}
		return pos - 1;
	}
	
	public static final int parseLINE(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, pos = tok_start;
		while((ch = tenv.source.charAt(pos++)) != 0) {
			if(ch == '\n') break;
		}
		return pos - 1;/*EOF*/
	}
	
	public static final int parseCOMMENT(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, prev = 0, level = 1, pos = tok_start + 2;
		/*@#nnnn is line number */
		if(tenv.source.charAt(pos) == '@' && tenv.source.charAt(pos + 1) == '#' && Character.isDigit(tenv.source.charAt(pos + 2))) {
			// TODL
			// tenv.uline >>= (sizeof(kshort_t)*8);
			// tenv->uline = (tenv->uline<<(sizeof(kshort_t)*8)) | (kshort_t)strtoll(tenv->source + pos + 2, NULL, 10);
		}
		while((ch = tenv.source.charAt(pos++)) != 0) {
			if(ch == '\n') {
				tenv.uline += 1;
			}
			if(prev == '*'  && ch == '/') {
				level--;
				if(level == 0) return pos;
			} else if(prev == '/' && ch == '*') {
				level++;
			}
			prev = ch;
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO perror.h
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with */");
			// KToken.Token_toERR(ctx, tk, errref);
		}
		return pos - 1;/*EOF*/
	}
	
	public static final int parseSLASH(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		String ts = tenv.source.substring(tok_start);
		if(ts.charAt(1) == '/') {
			return parseLINE(ctx, tk, tenv, tok_start, thunk);
		}
		if(ts.charAt(1) == '*') {
			return parseCOMMENT(ctx, tk, tenv, tok_start, thunk);
		}
		return parseOP(ctx, tk, tenv, tok_start, thunk);
	}
	
	public static final int parseDQUOTE(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, prev = '"', pos = tok_start + 1;
		while((ch = tenv.source.charAt(pos++)) != 0) {
			if(ch == '\n') {
				break;
			}
			if(ch == '"' && prev != '\\') {
				if(CTX.IS_NOTNULL(tk)) {
					// TODO
					// KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos - 1) - (tok_start + 1), 0));
					tk.tt = KToken.TK_TEXT;
				}
				return pos;
			}
			prev = ch;
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO perror.h
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with \"");
			// KToken.Token_toERR(ctx, tk, errref);
		}
		return pos - 1;
	}
	
	public static final int parseSKIP(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		return tok_start + 1;
	}
	
	public static final int parseUNDEF(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		if(CTX.IS_NOTNULL(tk)) {
			// TODO
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "undefined token character: %c", tenv->source[tok_start]);
			// KToken.Token_toERR(ctx, tk, errref);
		}
		while(tenv.source.charAt(++tok_start) != 0);
		return tok_start;
	}
	
	public static final int parseBLOCK(CTX ctx,  KToken tk, TEnv tenv, int tok_start, KMethod thunk) {
		int ch, level = 1, pos = tok_start + 1;
		tk.lpos += 1;
		while((ch = FToken.kchar(tenv.source, pos)) != 0) {
			if(ch == FToken._RBR/*}*/) {
				level--;
				if(level == 0) {
					if(CTX.IS_NOTNULL(tk)) {
						// TODO
						// KSETv(tk->text, new_kString(tenv->source + tok_start + 1, (pos-2)-(tok_start)+1), 0));
						tk.tt = KToken.TK_CODE;
					}
					return pos + 1;
				}
				pos++;
			}
			else if(ch == FToken._LBR/*'{'*/) {
				level++; pos++;
			}
			else {
				pos = FToken.MiniKonohaTokenMatrix(ctx, /*K_NULLTOKEN*/, tenv, pos, null);
			}
		}
		if(CTX.IS_NOTNULL(tk)) {
			// TODO
			// size_t errref = SUGAR_P(ERR_, tk->uline, tk->lpos, "must close with }");
			// Token_toERR(_ctx, tk, errref);
		}
		return pos - 1;
	}
	
	public static final int MiniKonohaTokenMatrix(CTX ctx,  KToken tk, TEnv tenv, int pos, KMethod thunk, int ch) {
		switch(ch) {
		case _NULL: case _UNDEF: case _TAB: case _SP: 
			return parseSKIP(ctx, tk, tenv, pos, thunk);
		case _DIGIT: 
			return parseNUM(ctx, tk, tenv, pos, thunk);
		case _UALPHA:
			return parseUSYMBOL(ctx, tk, tenv, pos, thunk);
		case _LALPHA: case _UNDER:
			return parseSYMBOL(ctx, tk, tenv, pos, thunk);
		case _MULTI:
			return parseMSYMBOL(ctx, tk, tenv, pos, thunk);
		case _NL:
			return parseNL(ctx, tk, tenv, pos, thunk);
		case _LPAR: case _RPAR: case _LSQ: case _RSQ: case _RBR: case _COMMA:
		case _SEMICOLON: case _AT:
			return parseOP1(ctx, tk, tenv, pos, thunk);
		case _LBR: 
			return parseBLOCK(ctx, tk, tenv, pos, thunk);
		case _LT: case _GT: case _OKIDOKI: case _SHARP: case _DOLLAR: case _PER:
		case _AND: case _STAR: case _PLUS: case _MINUS: case _DOT: case _COLON:
		case _EQ: case _QUESTION: case _VAR: case _CHILDER: case _HAT:
			return parseOP(ctx, tk, tenv, pos, thunk);
		case _QUOTE: case _BKQUOTE: case _BKSLASH:
			return parseUNDEF(ctx, tk, tenv, pos, thunk);
		case _DQUOTE:
			return parseDQUOTE(ctx, tk, tenv, pos, thunk);
		case _SLASH:
			return parseSLASH(ctx, tk, tenv, pos, thunk);
		}
		return 0;
	}
}

final class Tokenizer { // not original
	
		private static void tokenize(CTX ctx, TEnv tenv) {
		int ch, pos = 0;
		KToken tk = new KToken(); // TODO
		assert tk.tt == 0;
		tk.uline = tenv.uline;
		tk.lpos = tenv.lpos(0);
		pos = FToken.parseINDENT(ctx, tk, tenv, pos, null);
		while((ch = FToken.kchar(tenv.source, pos)) != 0) {
			if(tk.tt != 0) {
				tenv.list.add(tk);
				tk = new KToken(); // TODO
				tk.uline = tenv.uline;
				tk.lpos = tenv.lpos(pos);
			}
			int pos2 = FToken.MiniKonohaTokenMatrix(ctx, tk, tenv, pos, null, ch);
			assert pos2 > pos;
			pos = pos2;
		}
		if(tk.tt != 0) {
			tenv.list.add(tk);
		}
	}
	
	public static void ktokenize(CTX ctx, String source, int uline, KArray<KToken> a) {
		int i, pos = a.size();
		TEnv tenv = new TEnv(source, uline, a, 4);
		tokenize(ctx, tenv);
		if(tenv.uline == 0) {
			for(i = pos; i < a.size(); i++) {
				a.get(i).uline = 0;
			}
		}
	}
}

final class ParseSyntaxRule {
	
	private static int findTopCh(CTX ctx, KArray<KToken> tls, int s, int e, int tt, int closech) {
		int i;
		for(i = s; i < e; i++) {
			KToken tk = (KToken)tls.get(i);
			// TODO if(tk.tt == tt && S_text(tk->text)[0] == closech) return i;
		}
		CTX.DBG_ASSERT(i != e);
		return 0;
	}
	
	private static boolean checkNestedSyntax(CTX ctx, KArray<KToken> tls, int s, int e, int tt, int opench, int closech) {
		int i = s;
		KToken tk = (KToken)tls.get(i);
		String t; // TODO const char *t =  S_text(tk->text);
		if(t.charAt(0) == opench && t.charAt(1) == 0) {
			int ne = findTopCh(ctx, tls, i + 1, e, tk.tt, closech);
			tk.tt = tt; tk.kw = tt;
			// TODO KSETv(tk->sub, new(TokenArray, 0));
			tk.topch = opench; tk.closech = closech;
			makeSyntaxRule(ctx, tls, i + 1, ne, tk.sub);
			s = ne;
			return true;
		}
		return false;
	}
	
	private static boolean makeSyntaxRule(CTX ctx, KArray<KToken> tls, int s, int e, KArray<KToken> adst) {
		int i;
		int nameid = 0;
		// TODO dumpTokenArray(_ctx, 0, tls, s, e);
		for(i = s; i < e; i++) {
			KToken tk = (KToken)tls.get(i);
			if(tk.tt == KToken.TK_INDENT) continue;
			if(tk.tt == TK_TEXT /*|| tk.tt == TK_STEXT*/) {
				if(/* TODO checkNestexdSyntax */) {
				}
				else {
					tk.tt = KToken.TK_CODE;
					// TODO tk.kw = keyword(_ctx, S_text(tk->text), S_size(tk->text), FN_NEWID);
				}
				adst.add(tk);
				continue;
			}
			if(tk.tt == KToken.TK_SYMBOL || tk.tt == KToken.TK_USYMBOL) {
				if(i > 0 && ((KToken)tls.get(i - 1)).topch == '$') {
					// TODO
					// snprintf(nbuf, sizeof(nbuf), "$%s", S_text(tk->text));
					// tk->kw = keyword(_ctx, (const char*)nbuf, strlen(nbuf), FN_NEWID);
					tk.tt = KToken.TK_METNAME;
					if(nameid == 0) nameid = tk.kw;
					tk.nameid = nameid;
					nameid = 0;
					adst.add(tk); continue;
				}
				if(i + 1 < e && ((KToken)tls.get(i + 1)).topch == ':') {
					tk = (KToken)tls.get(i);
					// TODO nameid = keyword(_ctx, S_text(tk->text), S_size(tk->text), FN_NEWID);
					i++;
					continue;
				}
			}
			if(tk.tt == KToken.TK_OPERATOR) {
				if(/* checkNestedSyntax */) {
					adst.add(tk);
					continue;
				}
				if(((KToken)tls.get(i)).topch == '$') continue;
			}
			// TODO SUGAR_P(ERR_, tk->uline, tk->lpos, "illegal sugar syntax: %s", kToken_s(tk));
			return false;
		}
		return true;
	}
	
	public static void parseSyntaxRule(CTX ctx, String rule, int uline, KArray<KToken> a) {
		KArray<KToken> tls; // TODO kArray *tls = ctxsugar->tokens;
		int pos = tls.size();
		Tokenizer.ktokenize(ctx, rule, uline, tls);
	}
}