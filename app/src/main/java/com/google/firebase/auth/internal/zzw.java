package com.google.firebase.auth.internal;

import com.google.android.gms.internal.p002firebaseauthapi.zzagv;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzw implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    @Override // com.google.firebase.auth.ActionCodeResult
    public final int getOperation() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        switch (i) {
            case 0:
                return this.zzb;
            case 1:
                return this.zzc;
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x0074  */
    public zzw(zzagv zzagvVar) {
        this.zzb = zzagvVar.zzg() ? zzagvVar.zzc() : zzagvVar.zzb();
        this.zzc = zzagvVar.zzb();
        ActionCodeInfo zzuVar = null;
        if (!zzagvVar.zzh()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        int i = 2;
        switch (zzagvVar.zzd()) {
            case "PASSWORD_RESET":
                i = 0;
                break;
            case "VERIFY_EMAIL":
                i = 1;
                break;
            case "EMAIL_SIGNIN":
                i = 4;
                break;
            case "VERIFY_AND_CHANGE_EMAIL":
                i = 5;
                break;
            case "RECOVER_EMAIL":
                break;
            case "REVERT_SECOND_FACTOR_ADDITION":
                i = 6;
                break;
            default:
                i = 3;
                break;
        }
        this.zza = i;
        if (this.zza == 4 || this.zza == 3) {
            this.zzd = null;
            return;
        }
        if (zzagvVar.zzf()) {
            zzuVar = new zzt(zzagvVar.zzb(), zzbi.zza(zzagvVar.zza()));
        } else if (zzagvVar.zzg()) {
            zzuVar = new zzr(zzagvVar.zzc(), zzagvVar.zzb());
        } else if (zzagvVar.zze()) {
            zzuVar = new zzu(zzagvVar.zzb());
        }
        this.zzd = zzuVar;
    }
}
