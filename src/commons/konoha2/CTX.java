package commons.konoha2;

import java.util.*;

import commons.konoha2.kclass.*;
import commons.sugar.KClass;
import commons.sugar.KModSugar;

public final class CTX {

	public int				safepoint;
//	KSfp			esp;
//	KLib2			lib2;
//	KMemShare		memshare;
//	KMemLocal		memlocal;
	public KShare			share;
//	KLocal			local;
//	KStack			stack;
//	KLogger			logger;
	public ArrayList<KModShare> modshare;
	public ArrayList<KModLocal> modlocal;
	
	
/* C macros */
	// konoha2.h
	/* kcid_t */
	public static final int CLASS_newid = -1;
	public static final int TY_unknown = -1;
	
//	#define CT_(t) (_ctx->share->ca.cts[t])
//	#define TY_isUnbox(t) FLAG_is(CT_(t)->cflag, kClass_UnboxType)
	
	public static final int FN_NONAME = -1;
	public static final int FN_NEWID = -2;
	
	public static final int MOD_logger = 0;
//	public static final int MOD_gc = 1;
	public static final int MOD_code = 2;
	public static final int MOD_sugar = 3;
	public static final int MOD_float = 11;
//	public static final int MOD_jit = 12;
	public static final int MOD_iconv = 13;
	public static final int MOD_IO = 14;
	public static final int MOD_llvm = 15;
	public static final int MOD_REGEX = 16;
	
	
	public static final int KObject_NullObject = (1<<0);
	
	public static boolean IS_NOTNULL(Object o) {
		if(!(o instanceof K_Object)) return false;
		return ((((K_Object)o).h.magicflag & KObject_NullObject) != KObject_NullObject);
	}
	
	
	public static void DBG_ASSERT(boolean a) {
		assert(a);
	}
	
	// sugar.h
	public final KModLocal ctxsugar() {
		return this.modlocal.get(MOD_sugar);
	}
	
	public final KModShare kmodsugar() {
		return this.modshare.get(MOD_sugar);
	}
	
	public final KClass CT_Token() {
		return ((KModSugar)this.kmodsugar()).cToken;
	}
	
	public final KClass CT_Expr() {
		return ((KModSugar)this.kmodsugar()).cExpr;
	}
	
	public final KClass CT_Stmt() {
		return ((KModSugar)this.kmodsugar()).cStmt;
	}
	
	public final KClass CT_Block() {
		return ((KModSugar)this.kmodsugar()).cBlock;
	}
	
	public final KClass CT_KonohaSpace() {
		return ((KModSugar)this.kmodsugar()).cKonohaSpace;
	}
	
	public final KClass CT_Gamma() {
		return ((KModSugar)this.kmodsugar()).cGamma;
	}
	
	public final KClass CT_TokenArray() {
		return ((KModSugar)this.kmodsugar()).cTokenArray;
	}
}