package commons.konoha2;

import commons.sugar.*;

public final class KObjectHeader {
	public int magicflag;
	K_Class ct; //RENAME
	
	/*union*/
	public long refc;
	//void *gcinfo;
	public long hashcode;
	
	public KArray kvproto;
	// TODO
}