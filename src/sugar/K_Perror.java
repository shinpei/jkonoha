package sugar;

import commons.konoha2.CTX;
import commons.konoha2.kclass.K_Object;
import commons.konoha2.kclass.K_String;
import commons.sugar.CtxSugar;
import commons.sugar.K_Expr;

public class K_Perror {
	public static String TEmsg(CTX ctx, int pe) {
		switch(pe) {
		case CRIT_:
		case ERR_: return "(error)";
		case WARN_: return "(warning)";
		case INFO_:
			if(CTX_isInteractive() || CTX_isCompileOnly() || verbose_sugar) {
				return "(info)";
			}
			return null;
		case DEBUG_:
			if(verbose_sugar) {
				return "(debug)";
			}
			return null;
		}
		return "(unknown)";
	}
	
	public static int sugar_p(CTX ctx, int pe, int uline, int lpos, String fmt, K_Object... ap) {
		int errref = vperrorf(ctx, pe, uline, lpos, fmt, ap);
		return errref;
	}

	public static K_Expr Token_p(CTX ctx, K_Token tk, int pe, String fmt, K_Object... ap) {
		vperrorf(ctx, pe, tk.uline, tk.lpos, fmt, ap);
		return K_NULLEXPR;
	}
	
	public static K_String Kstrerror(CTX ctx, int eno) {
		CtxSugar base = ctx.modlocal; // CtxSugar.?
		int i;
		for(i = eno; i < base.errors.size(); i++) {
			K_String emsg = base.errors.strings[i];
			if(strstr(S_text(emsg),"(error)") != null) { // String.indexOf("(error)") != -1 ?
				return emsg;
			}
		}
		DBG_ABORT("kerrno=%d, |errmsgs|=%d", kArray_size(base->errors)); // DBG_ABORT is not exist
		return ctx.share.emptyString;
	}
}
