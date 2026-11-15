package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzan extends SuspendLambda implements Function2 {
    long zza;
    boolean zzb;
    int zzc;
    final /* synthetic */ zzap zzd;
    final /* synthetic */ zzdf zze;
    final /* synthetic */ Ref.ObjectRef zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzan(zzap zzapVar, zzdf zzdfVar, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzapVar;
        this.zze = zzdfVar;
        this.zzf = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzan(this.zzd, this.zze, this.zzf, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzan) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0037  */
    /* JADX WARN: Code duplicated, block: B:37:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0052 -> B:12:0x0029). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0084 -> B:11:0x0028). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.zzc
            r2 = 0
            r3 = 1
            switch(r1) {
                case 0: goto L20;
                case 1: goto L14;
                default: goto Lb;
            }
        Lb:
            boolean r1 = r8.zzb
            long r4 = r8.zza
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r8
            goto L28
        L14:
            long r4 = r8.zza
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L1c
            r1 = r0
            r0 = r8
            goto L3b
        L1c:
            r9 = move-exception
            r1 = r9
            r9 = r8
            goto L5e
        L20:
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = 1000(0x3e8, double:4.94E-321)
            r9 = r8
            r1 = r3
            goto L29
        L28:
            long r4 = r4 + r4
        L29:
            if (r1 == 0) goto L88
            com.google.android.recaptcha.internal.zzap r1 = r9.zzd     // Catch: java.lang.Exception -> L5d
            r9.zza = r4     // Catch: java.lang.Exception -> L5d
            r9.zzc = r3     // Catch: java.lang.Exception -> L5d
            java.lang.Object r1 = com.google.android.recaptcha.internal.zzap.zza(r1, r9)     // Catch: java.lang.Exception -> L5d
            if (r1 == r0) goto L5c
            r7 = r0
            r0 = r9
            r9 = r1
            r1 = r7
        L3b:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenProvider r9 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider) r9     // Catch: java.lang.Exception -> L56
            com.google.android.recaptcha.internal.zzap r6 = r0.zzd     // Catch: java.lang.Exception -> L56
            kotlinx.coroutines.CompletableDeferred r6 = r6.zzf()     // Catch: java.lang.Exception -> L56
            r6.complete(r9)     // Catch: java.lang.Exception -> L56
            com.google.android.recaptcha.internal.zzap r9 = r0.zzd     // Catch: java.lang.Exception -> L56
            com.google.android.recaptcha.internal.zzat r6 = com.google.android.recaptcha.internal.zzat.zzc     // Catch: java.lang.Exception -> L56
            com.google.android.recaptcha.internal.zzap.zzh(r9, r6)     // Catch: java.lang.Exception -> L56
            com.google.android.recaptcha.internal.zzdf r9 = r0.zze     // Catch: java.lang.Exception -> L56
            r9.zza()     // Catch: java.lang.Exception -> L56
            r9 = r0
            r0 = r1
            r1 = r2
            goto L29
        L56:
            r9 = move-exception
            r7 = r1
            r1 = r9
            r9 = r0
            r0 = r7
            goto L5e
        L5c:
            return r0
        L5d:
            r1 = move-exception
        L5e:
            kotlin.jvm.internal.Ref$ObjectRef r6 = r9.zzf
            r6.element = r1
            boolean r6 = r1 instanceof com.google.android.play.core.integrity.StandardIntegrityException
            if (r6 == 0) goto L74
            r6 = r1
            com.google.android.play.core.integrity.StandardIntegrityException r6 = (com.google.android.play.core.integrity.StandardIntegrityException) r6
            int r6 = r6.getErrorCode()
            switch(r6) {
                case -100: goto L72;
                case -18: goto L72;
                case -12: goto L72;
                case -8: goto L72;
                case -3: goto L72;
                default: goto L70;
            }
        L70:
            r6 = r2
            goto L75
        L72:
            r6 = r3
            goto L75
        L74:
            r6 = r2
        L75:
            if (r6 == 0) goto L87
            r9.zza = r4
            r9.zzb = r3
            r1 = 2
            r9.zzc = r1
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r4, r9)
            if (r1 == r0) goto L86
            r1 = r6
            goto L28
        L86:
            return r0
        L87:
            throw r1
        L88:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzan.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
