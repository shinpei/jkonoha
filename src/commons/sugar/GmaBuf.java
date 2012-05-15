package commons.sugar;

import commons.konoha2.kclass.*;

public class GmaBuf {//joseph:gmabuf_t in original konoha2 (/include/konoha2/sugar.h)
	int flag;
	int cflag;
	
	K_KonohaSpace ks;
	
	int thisCid;
	static int staticCid;
	K_Method mtd;
	GStack f = new GStack();
	GStack l = new GStack();
	K_Array lvarlst;
	int lvarlstTop;
}
