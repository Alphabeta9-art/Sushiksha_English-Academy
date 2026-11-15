package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzafa extends BroadcastReceiver {
    private final String zza;
    private final /* synthetic */ zzaew zzb;

    public zzafa(zzaew zzaewVar, String str) {
        this.zzb = zzaewVar;
        this.zza = str;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            switch (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode()) {
                case 0:
                    String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    zzaez zzaezVar = (zzaez) this.zzb.zzd.get(this.zza);
                    if (zzaezVar == null) {
                        zzaew.zza.e("Verification code received with no active retrieval session.", new Object[0]);
                    } else {
                        zzaezVar.zze = zzaew.zza(str);
                        if (zzaezVar.zze == null) {
                            zzaew.zza.e("Unable to extract verification code.", new Object[0]);
                        } else if (!zzag.zzc(zzaezVar.zzd)) {
                            zzaew.zza(this.zzb, this.zza);
                        }
                    }
                    break;
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
