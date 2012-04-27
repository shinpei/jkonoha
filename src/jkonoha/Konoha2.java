package jkonoha;

final class CTX {
/* C macros */
	static final long KObject_NullObject = (1<<0);
	
	public static boolean IS_NOTNULL(KToken tk) {
		return ((tk.h.magicflag & KObject_NullObject) != KObject_NullObject);
	}
	
	int				safepoint;
	KSfp				esp;
	KLib2			lib2;
	KMemShare		memshare;
	KMemLocal		memlocal;
	KShare			share;
	KLocal			local;
	KStack			stack;
	KLogger			logger;
	Array<KModShare>	modshare;
	Array<ModLocal>		modlocal;
}

class KObjectHeader {
	long magicflag;
}

class KArray extends ArrayList<T> {
}