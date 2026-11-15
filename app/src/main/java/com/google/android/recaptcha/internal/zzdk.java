package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzdk implements zzdg {
    private static Timer zza;
    private final Context zzb;
    private final zzdl zzc;
    private final CoroutineScope zzd;
    private final zzda zze;

    public zzdk(Context context, zzdl zzdlVar, CoroutineScope coroutineScope) {
        this.zzb = context;
        this.zzc = zzdlVar;
        this.zzd = coroutineScope;
        zzcy zzcyVar = zzda.zza;
        Context context2 = this.zzb;
        zzda zzdaVar = null;
        try {
            zzda zzdaVar2 = zzda.zzd;
            zzdaVar2 = zzdaVar2 == null ? new zzda(context2, null) : zzdaVar2;
            zzda.zzd = zzdaVar2;
            zzdaVar = zzdaVar2;
        } catch (Exception e) {
        }
        this.zze = zzdaVar;
        zzh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        zzda zzdaVar;
        zzda zzdaVar2 = this.zze;
        if (zzdaVar2 != null) {
            for (List<zzdb> list : CollectionsKt.windowed(zzdaVar2.zzd(), 20, 20, true)) {
                zzor zzorVarZzi = zzos.zzi();
                ArrayList arrayList = new ArrayList();
                for (zzdb zzdbVar : list) {
                    try {
                        zzre zzreVarZzk = zzre.zzk(zzhz.zzg().zzj(zzdbVar.zzc()));
                        int iZzL = zzreVarZzk.zzL();
                        int i = iZzL - 1;
                        if (iZzL == 0) {
                            throw null;
                        }
                        switch (i) {
                            case 0:
                                zzorVarZzi.zzp(zzreVarZzk.zzf());
                                break;
                            case 1:
                                zzorVarZzi.zzq(zzreVarZzk.zzg());
                                break;
                        }
                        arrayList.add(zzdbVar);
                    } catch (Exception e) {
                        zzda zzdaVar3 = this.zze;
                        if (zzdaVar3 != null) {
                            zzdaVar3.zzf(zzdbVar);
                        }
                    }
                }
                if (zzorVarZzi.zzd() + zzorVarZzi.zze() != 0) {
                    if (this.zzc.zza(((zzos) zzorVarZzi.zzj()).zzd()) && (zzdaVar = this.zze) != null) {
                        zzdaVar.zza(arrayList);
                    }
                }
            }
        }
    }

    private final void zzh() {
        if (zza == null) {
            zza = new Timer();
            Timer timer = zza;
            if (timer != null) {
                timer.schedule(new zzdh(this), 120000L, 120000L);
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzdg
    public final void zza(zzre zzreVar) {
        BuildersKt__Builders_commonKt.launch$default(this.zzd, null, null, new zzdj(this, zzreVar, null), 3, null);
        zzh();
    }
}
