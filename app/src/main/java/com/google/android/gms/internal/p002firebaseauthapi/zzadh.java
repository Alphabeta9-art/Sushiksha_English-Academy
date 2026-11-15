package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzadh {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzzk zzb;
    private final zzaew zzc;

    zzadh(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzzk(new zzadv(firebaseApp, zzads.zza()));
        this.zzc = new zzaew(applicationContext, scheduledExecutorService);
    }

    public final void zza(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zzb(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzb(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zzc(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzc(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zzd(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzd(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagw zzagwVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzagwVar);
        Preconditions.checkNotEmpty(zzagwVar.zzb());
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(zzagwVar, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, str2, str3, str4, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, new zzade(zzadfVar, zza));
    }

    public final void zza(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3, zzadf zzadfVar) {
        zzafp zzafpVarZza;
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotNull(zzadfVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential phoneAuthCredentialZza = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            zzafpVarZza = zzaft.zza(str, (String) Preconditions.checkNotNull(phoneAuthCredentialZza.zzc()), (String) Preconditions.checkNotNull(phoneAuthCredentialZza.getSmsCode()), str2, str3);
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            zzafpVarZza = zzafv.zza(str, Preconditions.checkNotEmpty(str2), Preconditions.checkNotEmpty(((TotpSecret) Preconditions.checkNotNull(totpMultiFactorAssertion.zza())).getSessionInfo()), Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str3);
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
        this.zzb.zza(zzafpVarZza, str, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, MultiFactorAssertion multiFactorAssertion, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzadfVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential phoneAuthCredentialZza = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            this.zzb.zza(zzafw.zza(str, (String) Preconditions.checkNotNull(phoneAuthCredentialZza.zzc()), (String) Preconditions.checkNotNull(phoneAuthCredentialZza.getSmsCode()), str2), new zzade(zzadfVar, zza));
        } else {
            if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
                TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
                this.zzb.zza(zzafy.zza(str, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str2, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzb())), new zzade(zzadfVar, zza));
                return;
            }
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    public final void zzb(String str, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzb(str, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagi zzagiVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzagiVar);
        this.zzb.zza(zzagiVar, new zzade(zzadfVar, zza));
    }

    public final void zze(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        this.zzb.zze(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagj zzagjVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzagjVar);
        this.zzb.zza(zzagjVar, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, String str5, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, str2, str3, str4, str5, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, zzahr zzahrVar, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzahrVar);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, zzahrVar, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagp zzagpVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzadfVar);
        Preconditions.checkNotNull(zzagpVar);
        this.zzb.zza(Preconditions.checkNotEmpty(zzagpVar.zzb()), zzagpVar.zza(), new zzade(zzadfVar, zza));
    }

    public final void zzc(String str, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzc(str, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagy zzagyVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzagyVar);
        this.zzb.zza(zzagyVar, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagg zzaggVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzaggVar);
        Preconditions.checkNotEmpty(zzaggVar.zzd());
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(zzaggVar, new zzade(zzadfVar, zza));
    }

    public final void zzb(zzagg zzaggVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzaggVar);
        Preconditions.checkNotEmpty(zzaggVar.zzc());
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzb(zzaggVar, new zzade(zzadfVar, zza));
    }

    public final void zza(zzagz zzagzVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzadfVar);
        Preconditions.checkNotNull(zzagzVar);
        String strZzd = zzagzVar.zzd();
        zzade zzadeVar = new zzade(zzadfVar, zza);
        if (this.zzc.zzd(strZzd)) {
            if (zzagzVar.zze()) {
                this.zzc.zzc(strZzd);
            } else {
                this.zzc.zzb(zzadeVar, strZzd);
                return;
            }
        }
        long jZzb = zzagzVar.zzb();
        boolean zZzf = zzagzVar.zzf();
        if (zza(jZzb, zZzf)) {
            zzagzVar.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(strZzd, zzadeVar, jZzb, zZzf);
        this.zzb.zza(zzagzVar, this.zzc.zza(zzadeVar, strZzd));
    }

    public final void zza(zzahd zzahdVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzahdVar);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzd(zzahdVar.zza(), new zzade(zzadfVar, zza));
    }

    public final void zzd(String str, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zze(str, new zzade(zzadfVar, zza));
    }

    public final void zza(zzahr zzahrVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzahrVar);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(zzahrVar, new zzade(zzadfVar, zza));
    }

    public final void zza(zzahw zzahwVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzahwVar);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(zzahwVar, new zzade(zzadfVar, zza));
    }

    public final void zzb(String str, String str2, String str3, String str4, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadfVar);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzb(str, str2, str3, str4, new zzade(zzadfVar, zza));
    }

    public final void zza(zzafn zzafnVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzadfVar);
        Preconditions.checkNotNull(zzafnVar.zzb());
        this.zzb.zza(zzafnVar.zzb(), zzafnVar.zzc(), new zzade(zzadfVar, zza));
    }

    public final void zza(zzzg zzzgVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzadfVar);
        Preconditions.checkNotNull(zzzgVar);
        this.zzb.zza(zzaeq.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzzgVar.zza())), new zzade(zzadfVar, zza));
    }

    public final void zza(String str, String str2, String str3, long j, boolean z, boolean z2, String str4, String str5, String str6, boolean z3, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str, "idToken should not be empty.");
        Preconditions.checkNotNull(zzadfVar);
        zzade zzadeVar = new zzade(zzadfVar, zza);
        if (this.zzc.zzd(str2)) {
            if (z) {
                this.zzc.zzc(str2);
            } else {
                this.zzc.zzb(zzadeVar, str2);
                return;
            }
        }
        zzahm zzahmVarZza = zzahm.zza(str, str2, str3, str4, str5, str6, null);
        if (zza(j, z3)) {
            zzahmVarZza.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(str2, zzadeVar, j, z3);
        this.zzb.zza(zzahmVarZza, this.zzc.zza(zzadeVar, str2));
    }

    public final void zza(zzzj zzzjVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzzjVar);
        Preconditions.checkNotNull(zzadfVar);
        String phoneNumber = zzzjVar.zzb().getPhoneNumber();
        zzade zzadeVar = new zzade(zzadfVar, zza);
        if (this.zzc.zzd(phoneNumber)) {
            if (zzzjVar.zzh()) {
                this.zzc.zzc(phoneNumber);
            } else {
                this.zzc.zzb(zzadeVar, phoneNumber);
                return;
            }
        }
        long jZza = zzzjVar.zza();
        boolean zZzi = zzzjVar.zzi();
        zzahk zzahkVarZza = zzahk.zza(zzzjVar.zze(), zzzjVar.zzb().getUid(), zzzjVar.zzb().getPhoneNumber(), zzzjVar.zzd(), zzzjVar.zzg(), zzzjVar.zzf(), zzzjVar.zzc());
        if (zza(jZza, zZzi)) {
            zzahkVarZza.zza(new zzafg(this.zzc.zzb()));
        }
        this.zzc.zza(phoneNumber, zzadeVar, jZza, zZzi);
        this.zzb.zza(zzahkVarZza, this.zzc.zza(zzadeVar, phoneNumber));
    }

    public final void zza(zzaho zzahoVar, zzadf zzadfVar) {
        this.zzb.zza(zzahoVar, new zzade((zzadf) Preconditions.checkNotNull(zzadfVar), zza));
    }

    public final void zza(String str, String str2, String str3, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotEmpty(str2, "uid should not be empty.");
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, str2, str3, new zzade(zzadfVar, zza));
    }

    public final void zze(String str, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzf(str, new zzade(zzadfVar, zza));
    }

    public final void zzf(String str, String str2, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zzf(str, str2, new zzade(zzadfVar, zza));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzadf zzadfVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzadfVar);
        this.zzb.zza(str, userProfileChangeRequest, new zzade(zzadfVar, zza));
    }

    public final void zzc(zzagg zzaggVar, zzadf zzadfVar) {
        Preconditions.checkNotNull(zzaggVar);
        this.zzb.zzc(zzaggVar, new zzade(zzadfVar, zza));
    }

    private static boolean zza(long j, boolean z) {
        if (j <= 0 || !z) {
            zza.w("App hash will not be appended to the request.", new Object[0]);
            return false;
        }
        return true;
    }
}
