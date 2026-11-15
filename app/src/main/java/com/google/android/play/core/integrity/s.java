package com.google.android.play.core.integrity;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.3.0 */
/* JADX INFO: loaded from: classes12.dex */
final class s {
    private final com.google.android.play.integrity.internal.an b;
    private final com.google.android.play.integrity.internal.an d;
    private final com.google.android.play.integrity.internal.an e;
    private final com.google.android.play.integrity.internal.an f;
    private final s a = this;
    private final com.google.android.play.integrity.internal.an c = com.google.android.play.integrity.internal.aj.b(ac.a);

    /* synthetic */ s(Context context, r rVar) {
        this.b = com.google.android.play.integrity.internal.al.b(context);
        com.google.android.play.integrity.internal.an anVar = this.b;
        this.d = new au(anVar, l.a);
        this.e = com.google.android.play.integrity.internal.aj.b(new al(anVar, this.c, this.d, l.a));
        this.f = com.google.android.play.integrity.internal.aj.b(new ab(this.e));
    }

    public final IntegrityManager a() {
        return (IntegrityManager) this.f.a();
    }
}
