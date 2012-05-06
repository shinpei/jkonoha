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
	ArrayList<KModShare>	modshare;
	ArrayList<ModLocal>		modlocal;
	
	
	
	
	
	/* C macros */
	public static final int KMOD_logger = 0;
//	public static final int KMOD_gc = 1;
	public static final int KMOD_code = 2;
	public static final int KMOD_sugar = 3;
	public static final int KMOD_float = 11;
//	public static final int KMOD_jit = 12;
	public static final int KMOD_iconv = 13;
	public static final int KMOD_IO = 14;
	public static final int KMOD_llvm = 15;
	public static final int KMOD_REGEX = 16;
	
	public static final long KObject_NullObject = (1<<0);
	
	public static boolean IS_NOTNULL(KToken tk) {
		return ((tk.h.magicflag & KObject_NullObject) != KObject_NullObject);
	}
}

class KObjectHeader {
	public long magicflag;
}

class KArray<T> extends ArrayList<Object> {
	public KObjectHeader h;
	
	KArray() {
	}
}

class KMethod {
	public KObjectHeader h;
}