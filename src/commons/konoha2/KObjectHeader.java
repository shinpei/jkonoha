package commons.konoha2;

import commons.sugar.*;

public final class KObjectHeader {
	public int magicflag;
	K_Class ct; //RENAME
	
	/*union*/
	long refc;
	//void *gcinfo;
	long hashcode;
	
	KArray kvproto;
	// TODO
}