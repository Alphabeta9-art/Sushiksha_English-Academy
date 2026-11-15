package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbz {
    public static final /* synthetic */ int zza = 0;
    private static final GoogleApiAvailabilityLight zzb = GoogleApiAvailabilityLight.getInstance();

    public static final int zza(Context context) {
        switch (zzb.isGooglePlayServicesAvailable(context)) {
            case 1:
            case 3:
            case 9:
                return 4;
            default:
                return 3;
        }
    }
}
