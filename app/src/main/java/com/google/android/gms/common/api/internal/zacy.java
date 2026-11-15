package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.0.1 */
/* JADX INFO: loaded from: classes11.dex */
final class zacy implements Runnable {
    final /* synthetic */ Result zaa;
    final /* synthetic */ zada zab;

    zacy(zada zadaVar, Result result) {
        this.zab = zadaVar;
        this.zaa = result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiClient googleApiClient;
        boolean z = 0;
        z = 0;
        try {
            try {
                BasePendingResult.zaa.set(true);
                PendingResult pendingResultOnSuccess = ((ResultTransform) Preconditions.checkNotNull(this.zab.zaa)).onSuccess(this.zaa);
                zada zadaVar = this.zab;
                zadaVar.zah.sendMessage(zadaVar.zah.obtainMessage(0, pendingResultOnSuccess));
                BasePendingResult.zaa.set(false);
                zada zadaVar2 = this.zab;
                Result result = this.zaa;
                zada.zan(result);
                GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zab.zag.get();
                googleApiClient = googleApiClient2;
                z = result;
                if (googleApiClient2 != null) {
                    zada zadaVar3 = this.zab;
                    googleApiClient.zap(zadaVar3);
                    z = zadaVar3;
                }
            } catch (RuntimeException e) {
                zada zadaVar4 = this.zab;
                zadaVar4.zah.sendMessage(zadaVar4.zah.obtainMessage(1, e));
                BasePendingResult.zaa.set(false);
                zada zadaVar5 = this.zab;
                Result result2 = this.zaa;
                zada.zan(result2);
                GoogleApiClient googleApiClient3 = (GoogleApiClient) this.zab.zag.get();
                googleApiClient = googleApiClient3;
                z = result2;
                if (googleApiClient3 != null) {
                }
            }
        } catch (Throwable th) {
            BasePendingResult.zaa.set(Boolean.valueOf(z));
            zada zadaVar6 = this.zab;
            zada.zan(this.zaa);
            GoogleApiClient googleApiClient4 = (GoogleApiClient) this.zab.zag.get();
            if (googleApiClient4 != null) {
                googleApiClient4.zap(this.zab);
            }
            throw th;
        }
    }
}
