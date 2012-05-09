package tool;

//import sun.net.dns.ResolverConfiguration.Options;
import commons.konoha2.*;
import java.io.*;

public class Konoha {
	public static final int K_PAGESIZE = 4096;
	
	public static int compileonly_flag = 0; //global variable
	public static int interactive_flag = 0; //global variable
	
	public static int verbose_debug = 0;	//global variable
	public static int verbose_gc	= 0;	//global variable
	public static int verbose_sugar = 0;	//global variable
	public static int verbose_code	= 0;	//global variable

	public static String builtin_test		= null;	//global variable
	public static String test_script		= null;	//global variable
	public static String startup_script		= null;	//global variable
	
	public static String optarg;//TODO import gnu.getopt.Getopt; in ginit()
	public static int optind;//TODO import gnu.getopt.Getopt; in ginit();
	//TODO make long_options(struct)
	private static String[] long_options = { "verbose", "verbose:gc", "verbose:sugar", 
								"verbose:code", "interactive", "typecheck", 
								"start-with", "test", "test-with", "builtin-test", "NULL" };
	
	public static void main(String[] args) {
		//int scriptidx; // to global
		boolean ret = true;
		int scriptidx = ginit(args);
		if(true /* TODO builtin_test != NULL */) {
			System.exit(builtin_test("hoge" /* TODO builtin_test */));
		}
		if(true /* TODO test_script != NULL */) {
			System.exit(test("hoge" /* TODO test_script */));
		}
		CTX konoha = open();
		if(true /* TODO startup_script != NULL */) {
			startup(konoha, "hoge" /* TODO startup_script */);
		}
		if(scriptidx < args.length) {
			ret = load(konoha, args[scriptidx]);
		}
		if(ret && true /* TODO interactive_flag */) {
			ret = shell(konoha);
		}
		close(konoha);
		// MODGC_check_malloced_size()
		System.exit(ret ? 1/* TODO assertResult */ : 1);
	}
	

	public static int ginit(String[] args) {
		if (System.getenv("KONOHA_DEBUG") != null) {
			verbose_debug = 1;
			verbose_gc = 1;
			verbose_sugar = 1;
			verbose_code = 1;
		}
		//TODO Getopt options = new Getopt(long_options/*TODO long_options(struct)*/, args, "icI:S:");
		//TODO import gnu.getopt.Getopt;
		while (true) {
			int option_index = 0;
			int c = option_index;//TODO options.getopt();
			if (c == -1) break; /*Detect the end of the options.*/
			switch (c) {
			case 0://TODO
				/* If this option set a flag, do nothing else now. */
			case 'c':
				compileonly_flag = 1;
				break;
			case 'i':
				interactive_flag = 1;
				break;
			case 'B':
				builtin_test = optarg;
				break;
			case 'S':
				startup_script = optarg;
				break;
			case 'T':
				test_script = optarg;
				break;
			case '?':
				break;
			default:
				//TODO abort ();
				break;
			}
		}
		if (optind < args.length) {
			interactive_flag = 1;
		}
		return optind;
	}
	
	
	public static int builtin_test(String name) {//TODO
		return 1;
	}
	
	public static int test(String testname) {
		//reduced error message
		verbose_debug	= 0;
		verbose_sugar	= 0;
		verbose_gc 		= 0;
		verbose_code	= 0;
		CTX konoha = new CTX();//TODO konoha_t(CTX) konoha = konoha_open();
		if (startup_script != null) {
			startup (konoha, startup_script);
		}
		int ret = 0;//OK
		//TODO snprintf
		String[] script_file = new String[256];
		String[] correct_file = new String[256];
		String[] result_file = new String[256];
		/*snprintf(script_file, 256, "%s", testname);
		snprintf(correct_file, 256, "%s.proof", script_file);
		snprintf(result_file, 256, "%s.tested", script_file);*/
		//TODO ファイルの書き込み
		try {
			FileWriter fp = new FileWriter(testname);
			fp.write(script_file);//TODO
			fp.close();
		} catch (IOException e) {
			System.out.println (e + "Exception occured");
		}
		//TODO
		return 1;
	}
	
	public static CTX open() {
		init();
		return new_context (null, K_PAGESIZE * 8);
	}
	public static void init() {//used in open()
		int isInit = 0;
		if (isInit == 0)
			isInit = 1;
	}
	public static CTX new_context (CTX _ctx, int stacksize) {//used in open()
		CTX newctx = new CTX ();
		int ctxid_counter = 0;
		ctxid_counter++;
		if (_ctx == null) {//null means first one.
			/*TODO 	struct _klib2 *klib2 = (struct _klib2*)calloc(sizeof(klib2_t) + sizeof(kcontext_t), 1);
			 */
			_klib2 klib2 = new _klib2();
			klib2_init(klib2);//TODO klib2_init method(function)
			newctx = (CTX)(klib2 + 1);
			newctx.lib2 = (CTX)klib2;
			_ctx = newctx;
			/*newctx->modshare = (kmodshare_t**)calloc(sizeof(kmodshare_t*), MOD_MAX);
			newctx->modlocal = (kmodlocal_t**)calloc(sizeof(kmodlocal_t*), MOD_MAX);
			
			//TODO MODLOGGER_init method, MODGC_init method, KCLASSTABLE_init method
			MODLOGGER_init(_ctx, newctx);
			MODGC_init(_ctx, newctx);
			KCLASSTABLE_init(_ctx, newctx);*/
			
		} else {//others take ctx as its parent
			newctx.lib2 = _ctx.lib2;
			newctx.memshare = _ctx.memshare;
			newctx.share = _ctx.share;
			newctx.modshare = _ctx.modshare;
			newctx.modlocal = _ctx.modlocal;
		}
		KRUNTIME_init(_ctx, newctx, stacksize);//TODO below↓ KRUNTIME_init method
		/*if (IS_ROOTCTX(newctx)) {//TODO porting the method
			MODCODE_init(_ctx, newctx);
			MODSUGAR_init(_ctx, newctx);
			KCLASSTABLE_loadMethod(_ctx);
			MODSUGAR_loadMethod(_ctx)
		}*/
		return newctx;
	}
	public static void KRUNTIME_init (CTX _ctx, CTX ctx, int stacksize) {//used in new_context
		int i;
		//TODO kstack_t *base = (kstack_t*)KCALLOC(sizeof(kstack_t), 1);
		
	}
	public static void startup(CTX konoha, String startup_script) {
		String[] buf = new String[256];
		String path = System.getenv("KONOHA_SCRIPTPATH");
		String local = "";
		if (path == null) {
			path = System.getenv("KONOHA_HOME");
			local = "/script";
		}
		if (path == null) {
			path = System.getenv("HOME");
			local = "/.konoha/script";
		}
		//TODO snprintf(buf, sizeof(buf), "%s%s%s.k", path, local, startup_script);
		if (true /*TODO load(konoha, (const char*)buf)*/ ) {
			System.exit(1);
		}
		
	}
	
	public static boolean load(CTX konoha, String scriptname) {
		
	}
	
	public static boolean shell(CTX konoha) {
		
	}
	
	public static void close(CTX konoha) {
		
	}

}
