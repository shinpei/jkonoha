package jkonoha;

class KToken {
	public KObjectHeader h;
	public int tt;
	public int kw;
	
	/* union */
	public KString text;
	public KArray sub;
	
	public long uline;
	
	/* union */
	public int lpos;
	public int closech;
	public int nameid;
	public int mn_type;
	
	/* union */
	public int topch;
	public int symbol;
	public int ty;
	public int mn;
	
	public final void Token_toERR(long errref) {
		this.tt = TK_ERR;
		// TODO KSETv(tk->text, ctxsugar->errors->strings[errref]);
	}
	
	/* ktoken_t */
	public static final int TK_NONE = 0;
	public static final int TK_INDENT = 1;
	public static final int TK_SYMBOL = 2;
	public static final int TK_USYMBOL = 3;
	public static final int TK_TEXT = 4;
	public static final int TK_FLOAT = 5;
	public static final int TK_TYPE = 6;
	public static final int AST_PARENTHESIS = 7;
	public static final int AST_BRANCET = 8;
	public static final int AST_BRACE = 9;
	public static final int TK_OPERATOR = 10;
	public static final int TK_MSYMBOL = 11;
	public static final int TK_ERR = 12;
	public static final int TK_CODE = 13;
	public static final int TK_WHITESPACE = 14;
	public static final int TK_METNAME = 15;
	public static final int TK_MN = 16;
	public static final int AST_OPTIONAL = 17;
}

class KModSugar {
	KModShare h;
//	KClass cToken;
//	KClass cExpr;
//	KClass cStmt;
//	KClass cBlock;
//	KClass cKonohaSpace;
//	KClass cGamma;
	
	KArray aBuffer;
}