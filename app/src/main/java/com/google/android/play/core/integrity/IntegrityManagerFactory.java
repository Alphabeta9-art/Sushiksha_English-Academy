package com.google.android.play.core.integrity;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.3.0 */
/* JADX INFO: loaded from: classes12.dex */
public class IntegrityManagerFactory {
    private IntegrityManagerFactory() {
    }

    public static IntegrityManager create(Context applicationContext) {
        return z.a(applicationContext).a();
    }

    public static StandardIntegrityManager createStandard(Context applicationContext) {
        return ax.a(applicationContext).a();
    }
}
