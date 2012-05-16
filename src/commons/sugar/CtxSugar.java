package commons.sugar;

import commons.konoha2.kclass.*;
import commons.konoha2.*;

public class CtxSugar {//joseph:ctxsugar_t in original konoha2 (/include/konoha2/sugar.h)
	public KModLocal	h;//TODO
	public K_Array		tokens;
	public KArray		cwb;//TODO karray_t is a struct including union.
	public int			errCount;
	public K_Array		errors;
	public K_Block		singleBlock;//TODO
	public K_Gamma		gma;
	public K_Array		lvarlst;
	public K_Array		definedMethods;
}
