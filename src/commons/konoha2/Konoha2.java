package konoha;
import java.util.*;

final class KModLocal {
	
}

final class KModShare {
	
}

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
