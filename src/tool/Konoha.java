package tool;

import commons.konoha2.*;
import java.io.*;

public class Konoha {

	public static void main(String[] args) {
		int scriptidx; // to global
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
			ret = shell(konoha)
		}
		close(konoha);
		// MODGC_check_malloced_size()
		System.exit(ret ? 1/* TODO assertResult */ : 1);
	}
	
	public static int ginit(String[] args) {
		
	}
	
	public static int builtin_test(String name) {
		
	}
	
	public static int test(String testname) {
		
	}
	
	public static CTX open() {
		
	}
	
	public static void startup(CTX konoha, String startup_script) {
		String buf, path = System.getenv("KONOHA_SCRIPTPATH"), local = "";
		if(path == null) {
			path = System.getenv("KONOHA_HOME");
			local = "/script";
		}
		if(path == null) {
			path = System.getenv("HOME");
			local = "/.konoha2/script";
		}
		buf = path + local + "/" + startup_script + ".k\0";
		/*snprintf(buf, sizeof(buf), "%s%s/%s.k", path, local, startup_script);*/
		if(!load(konoha, buf)) {
			System.exit(1);
		}
		
	}
	
	public static boolean load(CTX konoha, String scriptname) {
		BEGIN_(konoha);
		boolean res = (MODSUGAR_loadscript((CTX_t)konoha, scriptname, strlen(scriptname), 0) == K_CONTINUE);
		END_(konoha);
		return res;
	}
	
	public static String readline(String prompt)
	{
		static int checkCTL = 0;
		int ch, pos = 0;
		static StringBuffer linebuf = new StringBuffer(1024); // THREAD-UNSAFE
		FileWriter fw = new FileWriter(stdout);
		fw.write(prompt);
		BufferedReader br = new BufferedReader(new FileReader(stdin));
		while((ch = br.read()) != -1) { //TODO while(... != EOF)
			if(ch == '\r') continue;
			if(ch == 27) {
				/* ^[[A */;
				br.read(); br.read();
				if(checkCTL == 0) {
					fr.write(" - use readline, it provides better shell experience.\n");
					checkCTL = 1;
				}
				continue;
			}
			if(ch == '\n' || pos == linebuf.length() - 1) {
				linebuf.append(0);
				break;
			}
			linebuf.append(ch);
			pos++;
		}
		if(ch == -1) return null; //TODO while(ch == EOF)
		String p = new String(linebuf);
		return p;
	}

	static int add_history(String line)
	{
		return 0;
	}
	
	static void shell(CTX)
	{
		kwb_t wb;
		kwb_init(&(_ctx->stack->cwb), &wb);
		kline_t uline = FILEID_("(shell)") | 1;
		while(1) {
			kline_t inc = 0;
			kstatus_t status = readstmt(_ctx, &wb, &inc);
			if(status == K_CONTINUE && kwb_bytesize(&wb) > 0) {
				status = konoha_eval((konoha_t)_ctx, kwb_top(&wb, 1), uline);
				uline += inc;
				kwb_free(&wb);
				if(status != K_FAILED) {
					dumpEval(_ctx, &wb);
					kwb_free(&wb);
				}
			}
			if(status == K_BREAK) {
				break;
			}
		}
		kwb_free(&wb);
		fprintf(stdout, "\n");
		return;
	}
	
	static void show_version(CTX)
	{
		int i;
		fprintf(stdout, "Konoha 2.0-alpha (Miyajima) (%d, %s)\n", K_REVISION, __DATE__);
		fprintf(stdout, "[gcc %s]\n", __VERSION__);
		fprintf(stdout, "options:");
		for(i = 0; i < MOD_MAX; i++) {
			if(_ctx->modshare[i] != NULL) {
				fprintf(stdout, " %s", _ctx->modshare[i]->name);
			}
		}
		fprintf(stdout, "\n");
	}
	
	public static boolean konoha_shell(CTX konoha) { //"shell" is already exist
		void *handler = dlopen("libreadline" K_OSDLLEXT, RTLD_LAZY);
		void *f = (handler != NULL) ? dlsym(handler, "readline") : null;
		kreadline = (f != NULL) ? (char* (*)(const char*))f : readline;
		f = (handler != NULL) ? dlsym(handler, "add_history") : null;
		kadd_history = (f != NULL) ? (int (*)(const char*))f : add_history;
		show_version((CTX_t)konoha);
		shell((CTX_t)konoha);//"shell" is here
		return 1;
	}
	
	public static void close(CTX konoha) {
		kcontext_free((CTX_t)konoha, (kcontext_t*)konoha);
	}

}
