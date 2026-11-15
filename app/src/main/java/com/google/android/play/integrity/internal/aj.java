package com.google.android.play.integrity.internal;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.3.0 */
/* JADX INFO: loaded from: classes12.dex */
public final class aj implements an {
    private static final Object a = new Object();
    private volatile an b;
    private volatile Object c = a;

    private aj(an anVar) {
        this.b = anVar;
    }

    public static an b(an anVar) {
        return anVar instanceof aj ? anVar : new aj(anVar);
    }

    @Override // com.google.android.play.integrity.internal.an
    public final Object a() {
        Object objA = this.c;
        if (objA == a) {
            synchronized (this) {
                objA = this.c;
                if (objA == a) {
                    objA = this.b.a();
                    Object obj = this.c;
                    if (obj != a && obj != objA) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + objA + ". This is likely due to a circular dependency.");
                    }
                    this.c = objA;
                    this.b = null;
                }
            }
        }
        return objA;
    }
}
