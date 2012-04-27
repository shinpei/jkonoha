package jkonoha;

class KToken {
	KObjectHeader h;
	int tt;
	int kw;
//	KString text;
//	KArray sub;
	int lpos;
}

class KModSugar {
	KModShare h;
	KClass cToken;
	KClass cExpr;
	KClass cStmt;
	KClass cBlock;
	KClass cKonohaSpace;
	KClass cGamma;
	
	KArray aBuffer;
}