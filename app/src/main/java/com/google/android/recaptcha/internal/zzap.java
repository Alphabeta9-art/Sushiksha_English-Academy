package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzap {
    public CompletableDeferred zza;
    private final CoroutineScope zzb;
    private final zzdc zzc;
    private final StandardIntegrityManager zzd;
    private long zzf;
    private boolean zzh;
    private zzat zze = zzat.zza;
    private final Mutex zzg = MutexKt.Mutex$default(false, 1, null);

    public zzap(Context context, CoroutineScope coroutineScope, zzdc zzdcVar, StandardIntegrityManager standardIntegrityManager, long j) {
        this.zzb = coroutineScope;
        this.zzc = zzdcVar;
        this.zzd = standardIntegrityManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzi(Continuation continuation) throws Exception {
        zzai zzaiVar;
        if (continuation instanceof zzai) {
            zzaiVar = (zzai) continuation;
            int i = zzaiVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzaiVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzaiVar = new zzai(this, continuation);
            }
        } else {
            zzaiVar = new zzai(this, continuation);
        }
        Object objAwait = zzaiVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzaiVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objAwait);
                Deferred deferredZza = zzas.zza(this.zzd.prepareIntegrityToken(StandardIntegrityManager.PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(this.zzf).build()));
                zzaiVar.zzc = 1;
                objAwait = deferredZza.await(zzaiVar);
                return objAwait == coroutine_suspended ? coroutine_suspended : objAwait;
            case 1:
                ResultKt.throwOnFailure(objAwait);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0067, code lost:
    
        if (r6 != r1) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object zzj(java.lang.String r5, kotlin.coroutines.Continuation r6) throws java.lang.Exception {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.google.android.recaptcha.internal.zzaj
            if (r0 == 0) goto L13
            r0 = r6
            com.google.android.recaptcha.internal.zzaj r0 = (com.google.android.recaptcha.internal.zzaj) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzc = r1
            goto L18
        L13:
            com.google.android.recaptcha.internal.zzaj r0 = new com.google.android.recaptcha.internal.zzaj
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2f;
                case 2: goto L2b;
                default: goto L23;
            }
        L23:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2b:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L69
        L2f:
            java.lang.String r5 = r0.zzd
            kotlin.ResultKt.throwOnFailure(r6)
            goto L47
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CompletableDeferred r6 = r4.zzf()
            r0.zzd = r5
            r2 = 1
            r0.zzc = r2
            java.lang.Object r6 = r6.await(r0)
            if (r6 == r1) goto L70
        L47:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenProvider r6 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider) r6
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest$Builder r2 = com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest.builder()
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest$Builder r5 = r2.setRequestHash(r5)
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest r5 = r5.build()
            com.google.android.gms.tasks.Task r5 = r6.request(r5)
            kotlinx.coroutines.Deferred r5 = com.google.android.recaptcha.internal.zzas.zza(r5)
            r6 = 0
            r0.zzd = r6
            r6 = 2
            r0.zzc = r6
            java.lang.Object r6 = r5.await(r0)
            if (r6 == r1) goto L70
        L69:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityToken r6 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityToken) r6
            java.lang.String r5 = r6.token()
            return r5
        L70:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zzj(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0061 A[PHI: r2 r5
  0x0061: PHI (r2v4 com.google.android.recaptcha.internal.zzap) = (r2v2 com.google.android.recaptcha.internal.zzap), (r2v5 com.google.android.recaptcha.internal.zzap) binds: [B:26:0x005f, B:13:0x002f] A[DONT_GENERATE, DONT_INLINE]
  0x0061: PHI (r5v5 java.lang.String) = (r5v3 java.lang.String), (r5v7 java.lang.String) binds: [B:26:0x005f, B:13:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:31:0x0071 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzc(String str, Continuation continuation) throws Exception {
        zzah zzahVar;
        zzap zzapVar;
        Object objZzj;
        if (continuation instanceof zzah) {
            zzahVar = (zzah) continuation;
            int i = zzahVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzahVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzahVar = new zzah(this, continuation);
            }
        } else {
            zzahVar = new zzah(this, continuation);
        }
        Object obj = zzahVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzahVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(obj);
                try {
                    zzahVar.zzd = this;
                    zzahVar.zze = str;
                    zzahVar.zzc = 1;
                    Object objZzj2 = zzj(str, zzahVar);
                    if (objZzj2 != coroutine_suspended) {
                        return objZzj2;
                    }
                } catch (Exception e) {
                    zzapVar = this;
                    zzahVar.zzd = zzapVar;
                    zzahVar.zze = str;
                    zzahVar.zzc = 2;
                    if (zzapVar.zze(zzahVar) != coroutine_suspended) {
                        zzahVar.zzd = null;
                        zzahVar.zze = null;
                        zzahVar.zzc = 3;
                        objZzj = zzapVar.zzj(str, zzahVar);
                        if (objZzj != coroutine_suspended) {
                            return objZzj;
                        }
                    }
                }
                return coroutine_suspended;
            case 1:
                str = zzahVar.zze;
                zzapVar = zzahVar.zzd;
                try {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                } catch (Exception e2) {
                    zzahVar.zzd = zzapVar;
                    zzahVar.zze = str;
                    zzahVar.zzc = 2;
                    if (zzapVar.zze(zzahVar) != coroutine_suspended) {
                        zzahVar.zzd = null;
                        zzahVar.zze = null;
                        zzahVar.zzc = 3;
                        objZzj = zzapVar.zzj(str, zzahVar);
                        if (objZzj != coroutine_suspended) {
                            return objZzj;
                        }
                    }
                    return coroutine_suspended;
                }
            case 2:
                str = zzahVar.zze;
                zzapVar = zzahVar.zzd;
                ResultKt.throwOnFailure(obj);
                zzahVar.zzd = null;
                zzahVar.zze = null;
                zzahVar.zzc = 3;
                objZzj = zzapVar.zzj(str, zzahVar);
                if (objZzj != coroutine_suspended) {
                    return objZzj;
                }
                return coroutine_suspended;
            case 3:
                ResultKt.throwOnFailure(obj);
                return obj;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object zzd(long j, Continuation continuation) {
        this.zzf = j;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0056 A[Catch: all -> 0x00b0, TRY_LEAVE, TryCatch #0 {all -> 0x00b0, blocks: (B:17:0x004c, B:19:0x0056, B:22:0x005c), top: B:34:0x004c }] */
    /* JADX WARN: Code duplicated, block: B:22:0x005c A[Catch: all -> 0x00b0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00b0, blocks: (B:17:0x004c, B:19:0x0056, B:22:0x005c), top: B:34:0x004c }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0096  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ab, code lost:
    
        if (kotlin.Unit.INSTANCE != r1) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object zze(kotlin.coroutines.Continuation r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzam
            if (r0 == 0) goto L13
            r0 = r14
            com.google.android.recaptcha.internal.zzam r0 = (com.google.android.recaptcha.internal.zzam) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzd = r1
            goto L18
        L13:
            com.google.android.recaptcha.internal.zzam r0 = new com.google.android.recaptcha.internal.zzam
            r0.<init>(r13, r14)
        L18:
            java.lang.Object r14 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L32;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L2d:
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lad
        L32:
            java.lang.Object r2 = r0.zza
            com.google.android.recaptcha.internal.zzap r5 = r0.zze
            kotlin.ResultKt.throwOnFailure(r14)
            goto L4c
        L3a:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.sync.Mutex r2 = r13.zzg
            r0.zze = r13
            r0.zza = r2
            r0.zzd = r3
            java.lang.Object r14 = r2.lock(r4, r0)
            if (r14 == r1) goto Lb6
            r5 = r13
        L4c:
            com.google.android.recaptcha.internal.zzat r14 = r5.zze     // Catch: java.lang.Throwable -> Lb0
            com.google.android.recaptcha.internal.zzat r6 = com.google.android.recaptcha.internal.zzat.zza     // Catch: java.lang.Throwable -> Lb0
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r6)     // Catch: java.lang.Throwable -> Lb0
            if (r14 != 0) goto L5c
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb0
            r2.unlock(r4)
            return r14
        L5c:
            com.google.android.recaptcha.internal.zzat r14 = com.google.android.recaptcha.internal.zzat.zzb     // Catch: java.lang.Throwable -> Lb0
            r5.zze = r14     // Catch: java.lang.Throwable -> Lb0
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb0
            r2.unlock(r4)
            com.google.android.recaptcha.internal.zzdc r14 = r5.zzc
            java.lang.String r2 = r14.zzd()
            r14.zzc(r2)
            r2 = 2
            r14.zzb(r2)
            r6 = 38
            com.google.android.recaptcha.internal.zzdf r14 = r14.zzf(r6)
            kotlinx.coroutines.CompletableDeferred r6 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r4, r3, r4)
            r5.zza = r6
            kotlinx.coroutines.CoroutineScope r7 = r5.zzb
            com.google.android.recaptcha.internal.zzao r10 = new com.google.android.recaptcha.internal.zzao
            r10.<init>(r5, r14, r4)
            r11 = 3
            r12 = 0
            r8 = 0
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r7, r8, r9, r10, r11, r12)
            r0.zze = r4
            r0.zza = r4
            r0.zzd = r2
            boolean r14 = r5.zzh
            if (r14 != 0) goto La9
            java.util.Timer r6 = new java.util.Timer
            r6.<init>()
            com.google.android.recaptcha.internal.zzak r7 = new com.google.android.recaptcha.internal.zzak
            r7.<init>(r5)
            r8 = 28800000(0x1b77400, double:1.42290906E-316)
            r10 = r8
            r6.schedule(r7, r8, r10)
            r5.zzh = r3
        La9:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            if (r14 == r1) goto Lb6
        Lad:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        Lb0:
            r0 = move-exception
            r14 = r0
            r2.unlock(r4)
            throw r14
        Lb6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zze(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final CompletableDeferred zzf() {
        CompletableDeferred completableDeferred = this.zza;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }
}
