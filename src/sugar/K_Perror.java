package sugar;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import commons.sugar.CtxSugar;
import commons.klib.KLib;

public class K_Perror {//joseph: port konoha2/src/sugar/perror.h to jkonoha/src/sugar/K_Perror.java
	
	public static int vPerrorf(CTX ctx, int pe, int uline, int lpos, String fmt, K_Object... ap/*TODO*/) {//joseph: vperrorf in original konoha2 (src/sugar/perror.h)
		String msg = T_emsg(ctx, pe);
		int errref = -1;
		if(msg != null) {
			CtxSugar base = new CtxSugar();//ctx.KArrayList<KModLocal>;
			Kwb wb;//TODO
			Kwb_init(base.cwb, wb);
			if(uline > 0) {
				String file = T_file(uline);//TODO T_file
				kwb_printf(msg + KLib.shortname(file) + uline);// shortname is in KLib.java
			}
			else {
				kwb_printf(wb, msg);
			}
			kwb_vprintf(wb, fmt, ap);
			msg = kwb_top(wb, 1);
			//TODO new_kSting, kArray_size, kArray_add
			K_String emsg = new_kString(msg, msg, 0);//TODO new_kString in (/konoha2/package/konoha.rope/rope_glue.c)
			errref = base.errors.size();
			base.errors.add(emsg);//TODO
			if(pe == ERR_ || pe == CRIT_) {//TDOO ERR, CRIT_ enum in (/include/konoha2/konoha2.h)
				base.errCount ++;
			}
			//kreport(pe, (String)emsg.h.ct.unbox(ctx, (K_Object)emsg));//TODO
			//kreport(pe, (String)O_ct(emsg).unbox(ctx, (K_Object)emsg));//TODO O_ct(o) ((o)->h.ct)
			kreport(pe, S_text(emsg));//TODO S_text(s) ((const char*) (O_ct(s)->unbox(_ctx, (kObject*)s)))
		}									//			(String)O_ct(s).unbox(ctx, (K_Object)s)
		return errref;
	}
	
	public static int Kerrno(CTX ctx) {//joseph: Kerrno in original konoha2 (src/sugar/perror.h)
		//K_Array<K_Object> i = new K_Array<K_Object>();
		return ctx.modlocal.errors.size();//TODO
	}

}
