package commons.sugar;

import commons.konoha2.kclass.*;
import commons.konoha2.*;

public class CtxSugar {//joseph:ctxsugar_t in original konoha2 (/include/konoha2/sugar.h)
	KModLocal	h;//TODO
	K_Array		tokens;
	KArray		cwb;//TODO karray_t is a struct including union.
	int			errCount;
	K_Array		errors;
	K_Block		singleBlock;//TODO
	KGamma		gma;
	K_Array		lvarlst;
	K_Array		definedMethods;
}
