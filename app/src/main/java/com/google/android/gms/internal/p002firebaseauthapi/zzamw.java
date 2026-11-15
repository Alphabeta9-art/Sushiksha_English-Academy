package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzamw {
    public static final zzamw zza = new zzamw("DOUBLE", 0, zzand.DOUBLE, 1);
    public static final zzamw zzb = new zzamw("FLOAT", 1, zzand.FLOAT, 5);
    public static final zzamw zzc = new zzamw("INT64", 2, zzand.LONG, 0);
    public static final zzamw zzd = new zzamw("UINT64", 3, zzand.LONG, 0);
    public static final zzamw zze = new zzamw("INT32", 4, zzand.INT, 0);
    public static final zzamw zzf = new zzamw("FIXED64", 5, zzand.LONG, 1);
    public static final zzamw zzg = new zzamw("FIXED32", 6, zzand.INT, 5);
    public static final zzamw zzh = new zzamw("BOOL", 7, zzand.BOOLEAN, 0);
    public static final zzamw zzi = new zzamz("STRING", zzand.STRING);
    public static final zzamw zzj = new zzamy("GROUP", zzand.MESSAGE);
    public static final zzamw zzk = new zzanb("MESSAGE", zzand.MESSAGE);
    public static final zzamw zzl = new zzana("BYTES", zzand.BYTE_STRING);
    public static final zzamw zzm = new zzamw("UINT32", 12, zzand.INT, 0);
    public static final zzamw zzn = new zzamw("ENUM", 13, zzand.ENUM, 0);
    public static final zzamw zzo = new zzamw("SFIXED32", 14, zzand.INT, 5);
    public static final zzamw zzp = new zzamw("SFIXED64", 15, zzand.LONG, 1);
    public static final zzamw zzq = new zzamw("SINT32", 16, zzand.INT, 0);
    public static final zzamw zzr = new zzamw("SINT64", 17, zzand.LONG, 0);
    private static final /* synthetic */ zzamw[] zzs = {zza, zzb, zzc, zzd, zze, zzf, zzg, zzh, zzi, zzj, zzk, zzl, zzm, zzn, zzo, zzp, zzq, zzr};
    private final zzand zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzand zzb() {
        return this.zzt;
    }

    private zzamw(String str, int i, zzand zzandVar, int i2) {
        super(str, i);
        this.zzt = zzandVar;
        this.zzu = i2;
    }

    public static zzamw[] values() {
        return (zzamw[]) zzs.clone();
    }
}
