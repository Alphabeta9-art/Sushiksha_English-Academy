package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzdz extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzek zzd;
    final /* synthetic */ zzea zze;
    final /* synthetic */ String zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdz(zzek zzekVar, zzea zzeaVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzekVar;
        this.zze = zzeaVar;
        this.zzf = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdz(this.zzd, this.zze, this.zzf, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdz) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0053, code lost:
    
        if (r3.zzg(r7, r4, r6) != r0) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x007d, code lost:
    
        if (r1.zzh(r7, r2, r6) == r0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x007f, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.zzc
            switch(r1) {
                case 0: goto L15;
                case 1: goto Ld;
                default: goto L9;
            }
        L9:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L80
        Ld:
            java.lang.Object r1 = r6.zzb
            java.lang.Object r2 = r6.zza
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L6c
            goto L55
        L15:
            kotlin.ResultKt.throwOnFailure(r7)
            com.google.android.recaptcha.internal.zzek r7 = r6.zzd
            com.google.android.recaptcha.internal.zzbt r1 = new com.google.android.recaptcha.internal.zzbt
            r1.<init>()
            r7.zza = r1
            java.lang.String r7 = r6.zzf     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzhz r1 = com.google.android.recaptcha.internal.zzhz.zzh()     // Catch: java.lang.Exception -> L6c
            byte[] r7 = r1.zzj(r7)     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzro r2 = com.google.android.recaptcha.internal.zzro.zzi(r7)     // Catch: java.lang.Exception -> L6c
            r2.zzf()     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzea r7 = r6.zze     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzge r7 = com.google.android.recaptcha.internal.zzea.zzb(r7)     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzrg r7 = r7.zza(r2)     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzgz r1 = com.google.android.recaptcha.internal.zzgz.zzb()     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzea r3 = r6.zze     // Catch: java.lang.Exception -> L6c
            java.util.List r7 = r7.zzi()     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzek r4 = r6.zzd     // Catch: java.lang.Exception -> L6c
            r6.zza = r2     // Catch: java.lang.Exception -> L6c
            r6.zzb = r1     // Catch: java.lang.Exception -> L6c
            r5 = 1
            r6.zzc = r5     // Catch: java.lang.Exception -> L6c
            java.lang.Object r7 = com.google.android.recaptcha.internal.zzea.zzc(r3, r7, r4, r6)     // Catch: java.lang.Exception -> L6c
            if (r7 == r0) goto L7f
        L55:
            r7 = r1
            com.google.android.recaptcha.internal.zzgz r7 = (com.google.android.recaptcha.internal.zzgz) r7     // Catch: java.lang.Exception -> L6c
            r7.zzf()     // Catch: java.lang.Exception -> L6c
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzgz r1 = (com.google.android.recaptcha.internal.zzgz) r1     // Catch: java.lang.Exception -> L6c
            long r3 = r1.zza(r7)     // Catch: java.lang.Exception -> L6c
            kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch: java.lang.Exception -> L6c
            com.google.android.recaptcha.internal.zzro r2 = (com.google.android.recaptcha.internal.zzro) r2     // Catch: java.lang.Exception -> L6c
            r2.zzf()     // Catch: java.lang.Exception -> L6c
            goto L80
        L6c:
            r7 = move-exception
            com.google.android.recaptcha.internal.zzea r1 = r6.zze
            com.google.android.recaptcha.internal.zzek r2 = r6.zzd
            r3 = 0
            r6.zza = r3
            r6.zzb = r3
            r3 = 2
            r6.zzc = r3
            java.lang.Object r7 = com.google.android.recaptcha.internal.zzea.zzd(r1, r7, r2, r6)
            if (r7 != r0) goto L80
        L7f:
            return r0
        L80:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdz.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
