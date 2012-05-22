package sugar;

import commons.konoha2.CTX;
import commons.sugar.KModSugar;

public class Sugar {

	public static int keyword(CTX ctx, String name, int def) {
		KModSugar kmodsugar = (KModSugar)ctx.kmodsugar();
		return kmodsugar.keywordMapNN.getcode(ctx, kmodsugar.keywordList, name, def);
	}
}
