package commons.sugar;

import commons.konoha2.kclass.K_Object;

public class KArray {//joseph: karray_t in original konoha2 (/include/konoha2/konoha2.h)
	int byteSize;
	
	/*union*/
	String		bytebuf;
	K_Class		cts;
	Kvs			kvs;
	Kopl		opl;
	K_Object	objects;//TODO
	K_Object	refhead;
	
	/*union {	//TODO
		char  *bytebuf;
		const struct _kclass **cts;
		struct kvs_t          *kvs;
		struct kopl_t          *opl;
		const struct _kObject **objects;
		struct _kObject       **refhead;  // stack->ref
	};*/
	int byteMax;
}
