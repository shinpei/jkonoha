package sugar;

import tool.Konoha;
import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Object;
import commons.konoha2.kclass.K_String;
import commons.sugar.CtxSugar;
import commons.sugar.K_Expr;

public class Perror {
	public static String T_emsg(CTX ctx, int pe) { 
		switch(pe) {
		case CRIT_:							// not exist
		case ERR_: return "(error)";		// not exist
		case WARN_: return "(warning)";		// not exist
		case INFO_:							// not exist
			if(CTX_isInteractive() || CTX_isCompileOnly() || verbose_sugar) { // not exist
				return "(info)";
			}
			return null;
		case DEBUG_:							// not exist
			if(Konoha.verboseSugar) {
				return "(debug)";
			}
			return null;
		}
		return "(unknown)";
	}
	
	public static int sugar_p(CTX ctx, int pe, int uline, int lpos, Object... ap) {
		int errref = vperrorf(ctx, pe, uline, lpos, ap); //TODO vperrorf is not exist
		return errref;
	}

	public static K_Expr Token_p(CTX ctx, K_Token tk, int pe, Object... ap) {
		vperrorf(ctx, pe, tk.uline, tk.lpos, ap); //TODO vperrorf is not exist
		// return K_NULLEXPR; TODO
		return null;
	}
	
	public static K_String KStrerror(CTX ctx, int eno) {
		CtxSugar base = ctx.modlocal.get(CTX.MOD_sugar); //TODO
		int i;
		for(i = eno; i < base.errors.size(); i++) {
			K_String emsg = base.errors.get(i);          //TODO
			if(emsg.text.indexOf("(error)") != -1) {
				return emsg;
			}
		}
		//DBG_ABORT("kerrno=%d, |errmsgs|=%d", kArray_size(base->errors)); TODO DBG_ABORT is not exist
		return ctx.share.emptyString; //TODO emptyString is not exist
	}
}
