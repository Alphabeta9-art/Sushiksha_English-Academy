package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
final class zzj implements OnCompleteListener<com.google.firebase.auth.internal.zzh> {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    zzj(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zza = phoneAuthOptions;
        this.zzb = str;
        this.zzc = firebaseAuth;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<com.google.firebase.auth.internal.zzh> task) {
        String strZzd;
        String strZzb;
        String strZzc;
        if (task.isSuccessful()) {
            strZzd = task.getResult().zzd();
            strZzb = task.getResult().zzb();
            strZzc = task.getResult().zzc();
        } else {
            Exception exception = task.getException();
            String str = "Error while validating application identity: ";
            if (exception != null) {
                str = "Error while validating application identity: " + exception.getMessage();
            }
            Log.e("FirebaseAuth", str);
            if (exception != null && com.google.firebase.auth.internal.zza.zza(exception)) {
                FirebaseAuth.zza((FirebaseException) exception, this.zza, this.zzb);
                return;
            }
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            strZzc = null;
            strZzd = null;
            strZzb = null;
        }
        long jLongValue = this.zza.zzg().longValue();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacksZza = this.zzc.zza(this.zza.zzh(), this.zza.zze());
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacksZza2 = TextUtils.isEmpty(strZzd) ? this.zzc.zza(this.zza, onVerificationStateChangedCallbacksZza, task.getResult()) : onVerificationStateChangedCallbacksZza;
        com.google.firebase.auth.internal.zzao zzaoVar = (com.google.firebase.auth.internal.zzao) Preconditions.checkNotNull(this.zza.zzc());
        String str2 = (com.google.android.gms.internal.p002firebaseauthapi.zzag.zzc(strZzc) && this.zzc.zzb() != null && this.zzc.zzb().zza("PHONE_PROVIDER")) ? "NO_RECAPTCHA" : strZzc;
        if (zzaoVar.zzd()) {
            this.zzc.zze.zza(zzaoVar, (String) Preconditions.checkNotNull(this.zza.zzh()), this.zzc.zzi, jLongValue, this.zza.zzd() != null, this.zza.zzk(), strZzd, strZzb, str2, this.zzc.zzi(), onVerificationStateChangedCallbacksZza2, this.zza.zzi(), this.zza.zza());
        } else {
            this.zzc.zze.zza(zzaoVar, (PhoneMultiFactorInfo) Preconditions.checkNotNull(this.zza.zzf()), this.zzc.zzi, jLongValue, this.zza.zzd() != null, this.zza.zzk(), strZzd, strZzb, str2, this.zzc.zzi(), onVerificationStateChangedCallbacksZza2, this.zza.zzi(), this.zza.zza());
        }
    }
}
