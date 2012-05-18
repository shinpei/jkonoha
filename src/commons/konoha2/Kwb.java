package commons.konoha2;

import commons.sugar.KArray;

public class Kwb {
	KArray m;
	int pos;

	public Kwb(kArray m) {
		this.m = m;
		this.pos = m.byteSize;
	}

	static void Kwb_write(CTX, kwb_t *wb, const char *data, size_t bytelen) {//How to do new KArray m or this.m?
		karray_t *m = wb->m;
		if(!(m->bytesize + bytelen < m->bytemax)) {
			karray_expand(_ctx, m, m->bytesize + bytelen);
		}
		memcpy(m->bytebuf + m->bytesize, data, bytelen);
		m->bytesize += bytelen;
	}

	static void Kwb_putc(CTX, kwb_t *wb, ...) {
		char buf[256];
		int ch, len = 0;
		va_list ap;
		va_start(ap , wb);
		while((ch = (int)va_arg(ap, int)) != -1) {
			buf[len] = ch;
			len++;
	 	}
		Kwb_write(_ctx, wb, buf, len);
		va_end(ap);
	}

	public void vprintf(CTX ctx, Kwb wb, Object... ap) {//String... ap
		//va_list ap2;
		//va_copy(ap2, ap);
		this.m = wb.m;//TODO this.m or KArray m = wb.m ?
		Object ap2 = ap;
		int s = m.byteSize;
		int n = vsnprintf( m.bytebuf + s, m.byteMax - s, ap);//TODO
		if(n >= (m.byteMax - s)) {
			karray_expand(ctx, m, n + 1);//TODO
			n = vsnprintf(m.bytebuf + s, m.byteMax - s, ap);
		}
		//va_end(ap2);
		m.byteSize += n;
	}

	public void printf (CTX ctx, Kwb wb, Object... ap) {//TODO NO IDEA
		//*note:fmt is an Array, the type is Sting. This means String[] fmt = new String[]
		//va_list ap;
		//va_start(ap, fmt);
		vprintf(ctx, wb, ap);
		//va_end(ap);
	}

	public String top(CTX ctx, Kwb wb, boolean ensureZero) {
		KArray m = wb.m;
		if(ensureZero) {
			if( !(m.byteSize + 1 < m.byteMax) ) {
				karray_expand(ctx, m, m.byteSize + 1);//TODO
			}
			m.bytebuf += 0;//TODO
		}
		return (m.bytebuf + wb.pos);
	}

	public void free(Kwb wb) {
		this.m = wb.m;
		bzero(m.bytebuf + wb.pos, m.byteSize - wb.pos);//TODO
		this.m.byteSize = wb.pos;
	}
}
