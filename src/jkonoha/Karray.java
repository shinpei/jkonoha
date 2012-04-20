package jkonoha;

public class Karray {
	int bytesize; // >= 0
	/* union */
	String bytebuf;
	ArrayList<KClass>	cts;
	Kvs					kvs;
	Kopl				opl;
	ArrayList<KObject>	objects;
	ArrayList<KObject>	refhead;
	/* end of union */
	int bytemax;
}


