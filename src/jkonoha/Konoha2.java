package jkonoha;

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
//	Array<KModShare>	modshare;
//	Array<ModLocal>		modlocal;
	
	
	
	
	
	/* C macros */
	public static final long KObject_NullObject = (1<<0);
	
	public static boolean IS_NOTNULL(KToken tk) {
		return ((tk.h.magicflag & KObject_NullObject) != KObject_NullObject);
	}
}

class KObjectHeader {
	public long magicflag;
}

class KArray extends ArrayList<T> {
	public KObjectHeader h;
}

class KMethod {
	public KObjectHeader h;
}