package jkonoha;

import java.util.ArrayList;

final class KModLocal {
	
}

final class KModShare {
	
}

final class CTX {

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
	
	public static boolean IS_NOTNULL(KToken tk) {
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

/* ---------------------------------------------------------------- */

final class KClass {
	// TODO KCLASSSPI
	int packid;
	int packdom;
	int cid;
	int cflag;
	int bcid;
	int supcid;
	int magicflag;
	int cstruct_size;
	// TODO KField fields;
	int fsize;
	int fallocsize;
	String DBG_NAME;
	int nameid;
	int optvalue;
	// TODO etc
}

/* ---------------------------------------------------------------- */

class KObject {
	public KObjectHeader h;
	
	/* union */
	KObject[] fields;
	int[] ndata;
}

/* ---------------------------------------------------------------- */

final class KObjectHeader {
	public int magicflag;
}

/* ---------------------------------------------------------------- */

final class KString {
	public KObjectHeader h;
	
	/* C macros */
	// konoha2.h
	public static final int SPOL_TEXT = (1<<0);
	public static final int SPOL_ASCII = (1<<1);
	public static final int SPOL_UTF8 = (1<<2);
	public static final int SPOL_POOL = (1<<3);
	public static final int SPOL_NOCOPY = (1<<4);
}



/* ---------------------------------------------------------------- */

final class KArray<T> extends ArrayList<Object> {
	public KObjectHeader h;
	
	KArray() {
	}
}

/* ---------------------------------------------------------------- */

final class KMethod {
	public KObjectHeader h;
}