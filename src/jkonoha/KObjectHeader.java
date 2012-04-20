package jkonoha;

public class KObjectHeader {
	KMagicFlag	magicflag;  // i think we dont need this
	KClass[]	ct;
//	skip gc info field
	Karray kvproto;  // Karray is not KArray
}
