package sugar.tokenizer;

import sugar.KToken;
import sugar.tokenizer.parser.*;
import commons.*;
import commons.sugar.*;
import commons.konoha2.*;
import commons.konoha2.kclass.*;

/**
 * This class is used for tokenize.
 * @author okachin
 *
 */

public final class Tokenizer { // not original
	
	/**
	 * This method is used to reduce character's types to 41 types.
	 * 
	 * @param t imported sourcecode
	 * @param pos position of sourcecode
	 * @return character type
	 */
	
	public static final int kchar(String t, int pos) {
		int ch = t.charAt(pos);
		return (ch < 0) ? _MULTI : cMatrix[ch];
	}	
	
	private static final void tokenize(CTX ctx, TEnv tenv) {
		int ch, pos = 0;
		FTokenizer fmat[] = tenv.fmat;
		KToken tk = new KToken(); // TODO
		assert tk.tt == 0;
		tk.uline = tenv.uline;
		tk.lpos = tenv.lpos(0);
		pos = new ParseINDENT().parse(ctx, tk, tenv, pos, null);
		while(pos < tenv.source.length() && (ch = kchar(tenv.source, pos)) != 0) {
			if(tk.tt != 0) {
				tenv.list.add(tk);
				tk = new KToken(); // TODO
				tk.uline = tenv.uline;
				tk.lpos = tenv.lpos(pos);
			}
			int pos2 = fmat[ch].parse(ctx, tk, tenv, pos, null);
			assert pos2 > pos;
			pos = pos2;
		}
		if(tk.tt != 0) {
			tenv.list.add(tk);
		}
	}
	
	/**
	 * This method returns a matrix of parser about each character code.
	 * @return matrix of parser about each character code
	 */
	
	public static FTokenizer[] MiniKonohaTokenMatrix() {
		FTokenizer[] fmat = new FTokenizer[KCHAR_MAX];
		fmat[_NULL] = new ParseSKIP();
		fmat[_UNDEF] = new ParseSKIP();
		fmat[_DIGIT] = new ParseNUM();
		fmat[_UALPHA] = new ParseUSYMBOL();
		fmat[_LALPHA] = new ParseSYMBOL();
		fmat[_MULTI] = new ParseMSYMBOL();
		fmat[_NL] = new ParseNL();
		fmat[_TAB] = new ParseSKIP();
		fmat[_SP] = new ParseSKIP();
		fmat[_LPAR] = new ParseOP1();
		fmat[_RPAR] = new ParseOP1();
		fmat[_LSQ] = new ParseOP1();
		fmat[_RSQ] = new ParseOP1();
		fmat[_LBR] = new ParseBLOCK();
		fmat[_RBR] = new ParseOP1();
		fmat[_LT] = new ParseOP();
		fmat[_GT] = new ParseOP();
		fmat[_QUOTE] = new ParseUNDEF();
		fmat[_DQUOTE] = new ParseDQUOTE();
		fmat[_BKQUOTE] = new ParseDQUOTE();
		fmat[_OKIDOKI] = new ParseOP();
		fmat[_SHARP] = new ParseOP();
		fmat[_DOLLAR] = new ParseOP();
		fmat[_PER] = new ParseOP();
		fmat[_AND] = new ParseOP();
		fmat[_STAR] = new ParseOP();
		fmat[_PLUS] = new ParseOP();
		fmat[_COMMA] = new ParseOP1();
		fmat[_MINUS] = new ParseOP();
		fmat[_DOT] = new ParseOP();
		fmat[_SLASH] = new ParseSLASH();
		fmat[_COLON] = new ParseOP();
		fmat[_SEMICOLON] = new ParseOP1();
		fmat[_EQ] = new ParseOP();
		fmat[_QUESTION] = new ParseOP();
		fmat[_AT] = new ParseOP1();
		fmat[_VAR] = new ParseOP();
		fmat[_CHILDER] = new ParseOP();
		fmat[_BKSLASH] = new ParseUNDEF();
		fmat[_HAT] = new ParseOP();
		fmat[_UNDER] = new ParseSYMBOL();
		return fmat;
	}
	
	/**
	 * This method is used to tokenize imported sourcecode.
	 * 
	 * @param ctx context of konoha2
	 * @param ks KonohaSpace class
	 * @param source imported sourcecode
	 * @param uline the line on being disposed
	 * @param a tokens which sourcecode is divided into
	 */
	
	public static void ktokenize(CTX ctx, KKonohaSpace ks, String source, int uline, KArray<KToken> a) {
		int i, pos = a.size();
		FTokenizer fmat[];
		fmat = MiniKonohaTokenMatrix();
		// TODD ks = null ? fmat = MiniKonohaTokenMatrix() : KKonohaSpace.tokenizerMatrix();
		TEnv tenv = new TEnv(source, uline, a, 4, fmat);
		tokenize(ctx, tenv);
		if(tenv.uline == 0) {
			for(i = pos; i < a.size(); i++) {
				a.get(i).uline = 0;
			}
		}
	}
	
	/* const */
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
	public static final int KCHAR_MAX = 41;
	
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
}
	


/*final class ParseSyntaxRule {
	
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
			if(tk.tt == TK_TEXT || tk.tt == TK_STEXT) {
				if( TODO checkNestexdSyntax ) {
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
				if( checkNestedSyntax ) {
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
}*/