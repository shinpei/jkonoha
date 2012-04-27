package jkonoha;

class KToken {
	KObjectHeader h;
	int tt;
	int kw;
//	KString text;
//	KArray sub;
	int lpos;
	
	
	
	/* ktoken_t */
	public final int TK_NONE = 0;
	public final int TK_INDENT = 1;
	public final int TK_SYMBOL = 2;
	public final int TK_USYMBOL = 3;
	public final int TK_TEXT = 4;
	public final int TK_FLOAT = 5;
	public final int TK_TYPE = 6;
	public final int AST_PARENTHESIS = 7;
	public final int AST_BRANCET = 8;
	public final int AST_BRACE = 9;
	public final int TK_OPERATOR = 10;
	public final int TK_MSYMBOL = 11;
	public final int TK_ERR = 12;
	public final int TK_CODE = 13;
	public final int TK_WHITESPACE = 14;
	public final int TK_METNAME = 15;
	public final int TK_MN = 16;
	public final int AST_OPTIONAL = 17;
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