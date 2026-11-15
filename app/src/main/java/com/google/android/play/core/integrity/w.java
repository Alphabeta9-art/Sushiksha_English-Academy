package com.google.android.play.core.integrity;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.3.0 */
/* JADX INFO: loaded from: classes12.dex */
final class w implements aw {
    private final com.google.android.play.integrity.internal.an b;
    private final com.google.android.play.integrity.internal.an d;
    private final com.google.android.play.integrity.internal.an e;
    private final com.google.android.play.integrity.internal.an f;
    private final com.google.android.play.integrity.internal.an g;
    private final w a = this;
    private final com.google.android.play.integrity.internal.an c = com.google.android.play.integrity.internal.aj.b(bb.a);

    /* synthetic */ w(Context context, v vVar) {
        this.b = com.google.android.play.integrity.internal.al.b(context);
        com.google.android.play.integrity.internal.an anVar = this.b;
        this.d = new au(anVar, n.a);
        this.e = com.google.android.play.integrity.internal.aj.b(new bp(anVar, this.c, this.d, n.a));
        com.google.android.play.integrity.internal.an anVar2 = this.e;
        this.f = com.google.android.play.integrity.internal.aj.b(new bu(anVar2));
        this.g = com.google.android.play.integrity.internal.aj.b(new ba(anVar2, this.f));
    }

    @Override // com.google.android.play.core.integrity.aw
    public final StandardIntegrityManager a() {
        return (StandardIntegrityManager) this.g.a();
    }
}
