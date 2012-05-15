package commons.klib;

import commons.konoha2.*;
import commons.konoha2.kclass.*;

public class KLib{
/*	public inline static size_t size64(size_t s)
	{
		size_t base = sizeof(struct _kObject);
		while(base < s) {
			base *= 2;
		}
		return base;
	}*/
	
	//strhash,casehash -> String.hashCode
	
	static /*inline*/ String shortname(String str)
	{
		/*XXX g++ 4.4.5 need char* cast to compile it. */
	    String p = new String(str.substring(str.lastIndexOf('/')+1));
	    return p;
	}
	
	static /*inline*/ KString SFileid(CTX ctx, int fileid) //TODO
	{
		int n = (fileid >> (sizeof(kshort_t) * 8));
		DBG_ASSERT(n < kArray_size(ctx.share.fileidList));
		return ctx.share.fileidList.strings[n];
	}
	
	static /*inline*/ KString Spack_(CTX ctx, kpack_t packid) //kpack_t = unsined int
	{
		DBG_ASSERT(packid < kArray_size(ctx.share.packList));
		return ctx.share.packList.strings[packid];
	}
	
	static /*inline*/ KString S_UN_(CTX ctx, kuname_t un) //kuname_t = unsined int
	{
		DBG_ASSERT(un < kArray_size(ctx.share.unameList));
		return ctx.share.unameList.strings[un];
	}
	
	static /*inline*/ KString S_CT_(CTX ctx, kclass_t *ct) //TODO
	{
		return ctx.lib2.KCT_shortName(ctx, ct);
	}
	
	static /*inline*/ KString S_ty_(CTX ctx, ktype_t ty) //ktype_t = unsined int
	{
		DBG_ASSERT(ty < KARRAYSIZE(ctx.share.ca.bytemax, intptr)); //intptr = not exist
		return S_CT_(ctx, CT_(ty));
	}
	
	static /*inline*/ KString S_fn_(CTX ctx, ksymbol_t sym) //ksymbol_t = unsined int
	{
		size_t index = (size_t) MN_UNMASK(sym);
		DBG_ASSERT(index < kArray_size(ctx.share.symbolList));
		return ctx.share.symbolList.strings[index];
	}
	
	static /*inline*/ uintptr_t longid(kushort_t packdom, kushort_t un) //uintptr_t, uintptr_t = unsigned long int
	{
		uintptr_t hcode = packdom;
		return (hcode << (sizeof(kshort_t)*8)) | un;
	}

	static /*inline*/ kclass_t CT_P0(CTX ctx, kclass_t *ct, ktype_t ty) //TODO
	{
		kparam_t p = {ty, 0};
		return kClassTable_Generics(ct, TY_void, 1, &p);
	}
	
	static /*inline*/ void map_addu(CTX ctx, kmap_t *kmp, uintptr_t hcode, uintptr_t uvalue) //TODO
	{
		kmape_t *e = kmap_newentry(kmp, hcode);
		e.uvalue = uvalue;
		kmap_add(kmp, e);
	}

	static /*inline*/ uintptr_t map_getu(CTX ctx, kmap_t *kmp, uintptr_t hcode, uintptr_t def) //TODO
	{
		kmape_t *e = kmap_get(kmp, hcode);
		while(e != NULL) {
			if(e.hcode == hcode) return e.uvalue;
		}
		return def;
	}

	static /*inline*/ size_t check_index(CTX ctx, kint_t n, size_t max, int pline) //kint_t = long int
	{
		size_t n1 = (size_t)n;
		if(unlikely(!(n1 < max))) {
			kreportf(CRIT_, pline, "Script!!: out of array index %ld < %lu", n, max);
		}
		return n1;
	}
	
/*	static inline void Method_setProceedMethod(CTX, kMethod *mtd, kMethod *mtd2) //TODO
	{
		DBG_ASSERT(mtd != mtd2);
		DBG_ASSERT(mtd.proceedNUL == null);
		KINITv(((struct _kMethod*)mtd).proceedNUL, mtd2);
	}*/
}
