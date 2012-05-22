package test;

import java.io.*;

import sugar.K_Token;

import sugar.token.*;

import commons.konoha2.*;
import commons.konoha2.kclass.*;
import commons.sugar.K_KonohaSpace;

//import org.apache.log4j.*;

class Test {
	public static void main(String[] args)  throws FileNotFoundException, IOException  {
		CTX ctx = new CTX();
		int uline = 0;
		K_Array<K_Token> a = new K_Array<K_Token>();
		K_KonohaSpace ks = new K_KonohaSpace();
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
//		//							ok
//		\							ok
//		"xxx;"						ok
//		"/","xxx;"					ok
//		()							ok
//		[]							ok
//		{}							ok
//		' 							ok
//		" 							ok
//		@@@							ok
//		'`'							ok
//		,@							ok
//		,@							ok
//		'1 < 2, 2 < 2, 3=3'			ok
//		'hoge, hoge, hogu'			ok
//		/							ok
//		xxx/						ok
		String source = br.readLine();
		if(source != null) {
//			Tokenizer.ktokenize(ctx, ks, source, uline, a);
			ks.tokenize(ctx, source, uline, a);
			for(int i = 0; i < a.size(); i++) {
				System.out.print("{ token type:" + a.get(i).tt + ", ");
				if(a.get(i).text != null) {
					System.out.print("text: " + a.get(i).text.text + ", ");
				}
				else {
					System.out.print("text: null, ");
				}
				System.out.println("uline:" + a.get(i).uline + " }");
			}
		}	
	}
}
