package commons.konoha2;

import java.util.*;

public final class CTX {

	int				safepoint;
//	KSfp				esp;
//	KLib2			lib2;
//	KMemShare		memshare;
//	KMemLocal		memlocal;
//	KShare			share;
//	KLocal			local;
//	KStack			stack;
//	KLogger			logger;
	ArrayList<KModShare> modshare;
	ArrayList<KModLocal> modlocal;
	
	
	/* C macros */
	// konoha2.h
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
		return ((tk.h.magicflag & KObject_NullObject) != KObject_NullObject);
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
}