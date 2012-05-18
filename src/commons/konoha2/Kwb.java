package commons.konoha2;

import commons.sugar.KArray;

public class Kwb {
	KArray m;
	int pos;
	
	public Kwb(KArray m) {
		this.m = m;
		this.pos = m.byteSize;
	}

	public void write(CTX ctx, String data, int byteLen) {
		if(!(m.byteSize + byteLen < m.byteMax)) {
			/*karray_expand(_ctx, m, m->bytesize + byteLen);*/
		}
		/*memcpy(m.bytebuf + m.byteSize, data, byteLen);*/
		m.bytebuf += m.byteSize + data.substring(0,byteLen + 1);	
		m.byteSize += byteLen;
	}

	public void Kwb_putc(CTX ctx,int... ap) {
		String buf;
		int len = 0;
		for(int ch : ap) {
			buf += ch;
			len++;
	 	}
		write(ctx, buf, len);
	}

	static void Kwb_vprintf(CTX, kwb_t *wb, const char *fmt, va_list ap) {
		va_list ap2;
		va_copy(ap2, ap);
		karray_t *m = wb->m;
		size_t s = m->bytesize;
		size_t n = vsnprintf( m->bytebuf + s, m->bytemax - s, fmt, ap);
		if(n >= (m->bytemax - s)) {
			karray_expand(_ctx, m, n + 1);
			n = vsnprintf(m->bytebuf + s, m->bytemax - s, fmt, ap2);
		}
		va_end(ap2);
		m->bytesize += n;
	}

	static void Kwb_printf(CTX, kwb_t *wb, const char *fmt, ...) {
		va_list ap;
		va_start(ap, fmt);
		Kwb_vprintf(_ctx, wb, fmt, ap);
		va_end(ap);
	}

	static const char* Kwb_top(CTX, kwb_t *wb, int ensureZero) {
		karray_t *m = wb->m;
		if(ensureZero) {
			if(!(m->bytesize + 1 < m->bytemax)) {
				karray_expand(_ctx, m, m->bytesize + 1);
			}
			m->bytebuf[m->bytesize] = 0;
		}
		return (const char*)m->bytebuf + wb->pos;
	}

	static void Kwb_free(kwb_t *wb) {
		karray_t *m = wb->m;
		bzero(m->bytebuf + wb->pos, m->bytesize - wb->pos);
		m->bytesize = wb->pos;
	}
}
