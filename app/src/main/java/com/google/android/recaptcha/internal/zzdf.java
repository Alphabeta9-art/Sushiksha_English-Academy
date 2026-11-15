package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import kotlin.LazyKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzdf {
    private static zzoa zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final zzdg zzf;
    private final Context zzg;
    private final Integer zzh;
    private final String zzi = zznr.zzc(zznr.zzb(System.currentTimeMillis()));
    private final long zzj = System.currentTimeMillis();
    private final int zzk;

    public zzdf(int i, String str, String str2, String str3, String str4, String str5, zzdg zzdgVar, zzbw zzbwVar, Context context, Integer num) {
        this.zzk = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = str4;
        this.zzf = zzdgVar;
        this.zzg = context;
        this.zzh = num;
    }

    private final void zzc(int i, zzod zzodVar) {
        zzbr zzbrVar;
        String iSO3Language;
        String strValueOf;
        String iSO3Country = "";
        String strValueOf2 = EnvironmentCompat.MEDIA_UNKNOWN;
        zzoo zzooVarZzi = zzop.zzi();
        zzooVarZzi.zzx(this.zzk);
        zzooVarZzi.zzp(this.zzc);
        zzooVarZzi.zzs(this.zzd);
        String str = this.zze;
        if (str != null) {
            zzooVarZzi.zzw(str);
        }
        Integer num = this.zzh;
        if (num != null) {
            zzooVarZzi.zzu(num.intValue());
        }
        if (zzodVar != null) {
            zzooVarZzi.zzr(zzodVar);
        }
        zzooVarZzi.zzy(i);
        zzooVarZzi.zzv(this.zzi);
        zzooVarZzi.zzq(System.currentTimeMillis() - this.zzj);
        int i2 = zzbb.zza;
        for (zzbd zzbdVar : ((zzbf) LazyKt.lazy(zzdd.zza).getValue()).zza()) {
            zzooVarZzi.zze(0);
        }
        int i3 = zzbq.zza;
        int i4 = this.zzk;
        long jZzd = zzooVarZzi.zzd() * 1000;
        switch (i4 - 2) {
            case 4:
                zzbrVar = zzbr.zzb;
                break;
            case 5:
                zzbrVar = zzbr.zzc;
                break;
            case 6:
                zzbrVar = zzbr.zzd;
                break;
            case 7:
                zzbrVar = zzbr.zze;
                break;
            case 14:
                zzbrVar = zzbr.zzf;
                break;
            default:
                zzbrVar = zzbr.zza;
                break;
        }
        zzbq.zza(zzbrVar.zza(), jZzd);
        Context context = this.zzg;
        Set setZza = zzbk.zza(context);
        zzoa zzoaVar = zza;
        if (zzoaVar == null) {
            zzny zznyVarZzf = zzoa.zzf();
            zznyVarZzf.zze(Build.VERSION.SDK_INT);
            try {
                if (Build.VERSION.SDK_INT >= 33) {
                    int i5 = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L)).metaData.getInt("com.google.android.gms.version", -1);
                    strValueOf = i5 == -1 ? EnvironmentCompat.MEDIA_UNKNOWN : String.valueOf(i5);
                } else {
                    int i6 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("com.google.android.gms.version", -1);
                    strValueOf = i6 == -1 ? EnvironmentCompat.MEDIA_UNKNOWN : String.valueOf(i6);
                }
            } catch (PackageManager.NameNotFoundException e) {
                strValueOf = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            zznyVarZzf.zzr(strValueOf);
            zznyVarZzf.zzt("18.5.1");
            zznyVarZzf.zzq(Build.MODEL);
            zznyVarZzf.zzs(Build.MANUFACTURER);
            try {
                strValueOf2 = Build.VERSION.SDK_INT >= 33 ? String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode()) : Build.VERSION.SDK_INT >= 28 ? String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode()) : String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException e2) {
            }
            zznyVarZzf.zzp(strValueOf2);
            zzoaVar = (zzoa) zznyVarZzf.zzj();
        }
        zza = zzoaVar;
        zzny zznyVar = (zzny) zzoaVar.zzq();
        zznyVar.zzd(setZza);
        zzoa zzoaVar2 = (zzoa) zznyVar.zzj();
        try {
            iSO3Language = Locale.getDefault().getISO3Language();
        } catch (MissingResourceException e3) {
            iSO3Language = "";
        }
        try {
            iSO3Country = Locale.getDefault().getISO3Country();
        } catch (MissingResourceException e4) {
        }
        String str2 = this.zzb;
        zzpa zzpaVarZzf = zzpb.zzf();
        zzpaVarZzf.zzq(str2);
        zzpaVarZzf.zzd(zzoaVar2);
        zzpaVarZzf.zzp(iSO3Language);
        zzpaVarZzf.zze(iSO3Country);
        zzooVarZzi.zzt((zzpb) zzpaVarZzf.zzj());
        zzrd zzrdVarZzi = zzre.zzi();
        zzrdVarZzi.zzd(zzooVarZzi);
        this.zzf.zza((zzre) zzrdVarZzi.zzj());
    }

    public final void zza() {
        zzc(3, null);
    }

    public final void zzb(zzbj zzbjVar) {
        zzoc zzocVarZzg = zzod.zzg();
        zzocVarZzg.zzq(String.valueOf(zzbjVar.zzb().zza()));
        zzocVarZzg.zzd(zzbjVar.zza().zza());
        zzocVarZzg.zzp(zzbjVar.zzc().getErrorCode().getErrorCode());
        String strZzd = zzbjVar.zzd();
        if (strZzd != null) {
            zzocVarZzg.zze(strZzd);
        }
        zzc(4, (zzod) zzocVarZzg.zzj());
    }
}
