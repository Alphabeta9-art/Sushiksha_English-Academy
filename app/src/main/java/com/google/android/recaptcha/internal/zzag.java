package com.google.android.recaptcha.internal;

import android.content.Context;
import java.nio.charset.StandardCharsets;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzag implements zzaa {
    private final zzdc zza;
    private final zzap zzb;
    private boolean zzc = true;
    private String zzd = "";
    private final int zze;

    public zzag(zzdc zzdcVar, Context context, CoroutineScope coroutineScope, zzap zzapVar, int i) {
        this.zza = zzdcVar;
        this.zzb = zzapVar;
        this.zze = i;
    }

    private static final String zzi(zziv zzivVar) {
        zzhz zzhzVarZzg = zzhz.zzg();
        byte[] bArrZzl = zzivVar.zzl();
        byte[] bArrZzd = zzhr.zza().zza(zzhzVarZzg.zzi(bArrZzl, 0, bArrZzl.length), StandardCharsets.UTF_8).zzd();
        zziv zzivVarZzk = zziv.zzk(bArrZzd, 0, bArrZzd.length);
        zzhz zzhzVarZzh = zzhz.zzh();
        byte[] bArrZzl2 = zzivVarZzk.zzl();
        return zzhzVarZzh.zzi(bArrZzl2, 0, bArrZzl2.length);
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final int zza() {
        return 2;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final zzdc zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final Object zzc(String str, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzae(this, str, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0081, code lost:
    
        if (r10.zze(r0) != r1) goto L24;
     */
    @Override // com.google.android.recaptcha.internal.zzaa
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object zzd(com.google.android.recaptcha.internal.zzps r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.google.android.recaptcha.internal.zzaf
            if (r0 == 0) goto L13
            r0 = r10
            com.google.android.recaptcha.internal.zzaf r0 = (com.google.android.recaptcha.internal.zzaf) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzd = r1
            goto L18
        L13:
            com.google.android.recaptcha.internal.zzaf r0 = new com.google.android.recaptcha.internal.zzaf
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 0
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L34;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            java.lang.Object r9 = r0.zza
            com.google.android.recaptcha.internal.zzdf r9 = (com.google.android.recaptcha.internal.zzdf) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L83
        L34:
            com.google.android.recaptcha.internal.zzdf r9 = r0.zze
            java.lang.Object r2 = r0.zza
            com.google.android.recaptcha.internal.zzag r2 = (com.google.android.recaptcha.internal.zzag) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L74
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
            com.google.android.recaptcha.internal.zzdf r10 = com.google.android.recaptcha.internal.zzab.zzc(r8)
            int r2 = r8.zze
            r4 = 3
            if (r2 != r4) goto L8a
            long r4 = r9.zzf()
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L55
            goto L8a
        L55:
            com.google.android.recaptcha.internal.zziv r2 = r9.zzg()
            java.lang.String r2 = zzi(r2)
            r8.zzd = r2
            com.google.android.recaptcha.internal.zzap r2 = r8.zzb
            long r4 = r9.zzf()
            r0.zza = r8
            r0.zze = r10
            r9 = 1
            r0.zzd = r9
            java.lang.Object r9 = r2.zzd(r4, r0)
            if (r9 == r1) goto L89
            r2 = r8
            r9 = r10
        L74:
            com.google.android.recaptcha.internal.zzap r10 = r2.zzb
            r0.zza = r9
            r0.zze = r3
            r2 = 2
            r0.zzd = r2
            java.lang.Object r10 = r10.zze(r0)
            if (r10 == r1) goto L89
        L83:
            r9.zza()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L89:
            return r1
        L8a:
            r9 = 0
            r8.zzc = r9
            com.google.android.recaptcha.internal.zzbj r9 = new com.google.android.recaptcha.internal.zzbj
            com.google.android.recaptcha.internal.zzbh r0 = com.google.android.recaptcha.internal.zzbh.zzb
            com.google.android.recaptcha.internal.zzbg r1 = com.google.android.recaptcha.internal.zzbg.zzac
            r9.<init>(r0, r1, r3)
            r10.zzb(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzag.zzd(com.google.android.recaptcha.internal.zzps, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final void zze(zzqf zzqfVar) {
        this.zzd = zzi(zzqfVar.zzf());
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final boolean zzf() {
        return this.zzc;
    }
}
