package commons.konoha2;

import commons.sugar.*;

public final class KObjectHeader {
	public int magicflag;
	KClass ct; //RENAME
	
	/*union*/
	public long refc;
	//void *gcinfo;
	public long hashcode;
	
	public KArray kvproto;
	// TODO
}