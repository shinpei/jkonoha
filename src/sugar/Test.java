package sugar;

import commons.konoha2.*;
import commons.konoha2.kclass.*;

class Test {
	public static void main(String[] args) {
		CTX ctx = new CTX();
//		String source = " < + > ";
		String source = "a + b + 3 + 4 ";
//		String source = "a + b ";
//		String source = "a b";
		int uline = 0;
		KArray<KToken> a = new KArray<KToken>();
		Tokenizer.ktokenize(ctx, source, uline, a);
		for(int i = 0; i < a.size(); i++) {
//			System.out.println(a.get(i).text.text);
			System.out.println(a.get(i).tt);
		}
	}
}
