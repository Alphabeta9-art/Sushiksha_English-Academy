package com.google.android.play.core.integrity;

import android.os.Build;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.3.0 */
/* JADX INFO: loaded from: classes12.dex */
final class ao extends IntegrityTokenRequest {
    private final String a;
    private final Long b;
    private final Object c = null;

    /* synthetic */ ao(String str, Long l, Object obj, an anVar) {
        this.a = str;
        this.b = l;
    }

    private static boolean a() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    public final Long cloudProjectNumber() {
        return this.b;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0032  */
    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntegrityTokenRequest) {
            IntegrityTokenRequest integrityTokenRequest = (IntegrityTokenRequest) obj;
            if (this.a.equals(integrityTokenRequest.nonce())) {
                Long l = this.b;
                if (l == null) {
                    z = integrityTokenRequest.cloudProjectNumber() == null;
                } else if (l.equals(integrityTokenRequest.cloudProjectNumber())) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (!(obj instanceof ao) || !a()) {
            return z;
        }
        ao aoVar = (ao) obj;
        if (!z) {
            return false;
        }
        Object obj2 = aoVar.c;
        return true;
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() ^ 1000003;
        Long l = this.b;
        int iHashCode2 = (iHashCode * 1000003) ^ (l == null ? 0 : l.hashCode());
        return a() ? iHashCode2 * 1000003 : iHashCode2;
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    public final String nonce() {
        return this.a;
    }

    public final String toString() {
        String strConcat = "IntegrityTokenRequest{nonce=" + this.a + ", cloudProjectNumber=" + this.b;
        if (a()) {
            strConcat = strConcat.concat(", network=null");
        }
        return strConcat.concat("}");
    }
}
