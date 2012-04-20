package jkonoha;

public final class CTX {
	int				safepoint;
	KSfp				esp;
	KLib2			lib2;
	KMemShare		memshare;
	KMemLocal		memlocal;
	KShare			share;
	KLocal			local;
	KStack			stack;
	KLogger			logger;
	Array<KModShare>	modshare;
	Array<ModLocal>		modlocal;
}

