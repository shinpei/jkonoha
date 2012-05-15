package sugar;

import commons.konoha2.*;
import commons.konoha2.kclass.*;

/**
 * _kToken in original konoha2
 * @author okachin
 *
 */

public final class K_Token extends K_Object {
	
	public int tt;
	public int kw;
	
	/* union */
	public K_String text;
	public K_Array sub;
	
	public int uline;
	
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
	
	public final void Token_toERR(int errref) {
		this.tt = TK_ERR;
		// TODO KSETv(tk->text, ctxsugar->errors->strings[errref]);
	}
	
	/* ktoken_t */
	public static final int TK_NONE = 0;
	public static final int TK_INDENT = 1;
	public static final int TK_SYMBOL = 2;
	public static final int TK_USYMBOL = 3;
	public static final int TK_TEXT = 4;
	public static final int TK_INT = 5;
	public static final int TK_FLOAT = 6;
	public static final int TK_TYPE = 7;
	public static final int AST_PARENTHESIS = 8;
	public static final int AST_BRANCET = 9;
	public static final int AST_BRACE = 10;
	public static final int TK_OPERATOR = 11;
	public static final int TK_MSYMBOL = 12;
	public static final int TK_ERR = 13;
	public static final int TK_CODE = 14;
	public static final int TK_WHITESPACE = 15;
	public static final int TK_METNAME = 16;
	public static final int TK_MN = 17;
	public static final int AST_OPTIONAL = 18;
}