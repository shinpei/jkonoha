package commons.konoha2;

import commons.sugar.KArray;

public class Kwb {
	KArray m;
	int pos;
	
	static void Kwb_init(karray_t *m, kwb_t *wb) {
		wb->m = m;
		wb->pos = m->bytesize;
	}

	static void Kwb_write(CTX, kwb_t *wb, const char *data, size_t bytelen) {
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
