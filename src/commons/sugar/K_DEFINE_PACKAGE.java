package commons.sugar;

public class K_DEFINE_PACKAGE {
	int konoha_checksum;
	String name;
	String version;
	String libname;
	String libversion;
	String note;
	// TODO kobool_t = ?
	kbool_t (*initPackage)(CTX, const struct _kKonohaSpace *, int, String**, kline_t);
	kbool_t (*setupPackage)(CTX, const struct _kKonohaSpace *, kline_t);
	kbool_t (*initKonohaSpace)(CTX, const struct _kKonohaSpace *, kline_t);
	kbool_t (*setupKonohaSpace)(CTX, const struct _kKonohaSpace *, kline_t);
	int konoha_revision;
}
