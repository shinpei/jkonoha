package commons.sugar;

import commons.konoha2.KObjectHeader;

public class KGamma {//joseph:kGamma in original konoha2 (/include/konoha2/sugar.h)
	KObjectHeader h;
	GmaBuf genv = new GmaBuf();
	/*TODO
		#define kGamma_TOPLEVEL        (kflag_t)(1)
		#define kGamma_isTOPLEVEL(GMA)  TFLAG_is(kflag_t, GMA->genv->flag, kGamma_TOPLEVEL)
		#define kGamma_ERROR           (kflag_t)(1<<1)
		#define kGamma_isERROR(GMA)    TFLAG_is(kflag_t, GMA->genv->flag, kGamma_ERROR)
		#define kGamma_setERROR(GMA,B) TFLAG_set(kflag_t, GMA->genv->flag, kGamma_ERROR, B)
	 */
}
