package commons.sugar;

import commons.konoha2.kclass.*;

public class GmaBuf {//joseph:gmabuf_t in original konoha2 (/include/konoha2/sugar.h)
	int flag;
	int cflag;
	
	K_KonohaSpace ks = new K_KonohaSpace();//TODO
	
	int thisCid;
	static int staticCid;
	K_Method mtd = new K_Method();//TODO
	GStack f = new GStack();
	GStack l = new GStack();
	K_Array lvarlst = new K_Array();//TODO
	int lvarlstTop;
}
