package tool;

import commons.konoha2.*;

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
		
	}
	
	public static boolean load(CTX konoha, String scriptname) {
		
	}
	
	public static boolean shell(CTX konoha) {
		
	}
	
	public static void close(CTX konoha) {
		
	}

}
