package test;

import sugar.KToken;
import sugar.tokenizer.*;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import commons.sugar.KKonohaSpace;

class Test {
	public static void main(String[] args) {
		CTX ctx = new CTX();
		String source = "ab \n + /* hogehogeghohogeg hogehogh hoge */cd\n//hogehogehogehogehogehoge\nhugahuga + 3";
		int uline = 0;
		KArray<KToken> a = new KArray<KToken>();
		KKonohaSpace ks = new KKonohaSpace();
		Tokenizer.ktokenize(ctx, ks, source, uline, a);
		for(int i = 0; i < a.size(); i++) {
			System.out.print("{ " + a.get(i).tt + " : ");
			if(a.get(i).text != null) {
				System.out.println((a.get(i)).text.text + " }");
			}
			else {
				System.out.println("null }");
			}
		}
	}
}
