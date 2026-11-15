package com.google.android.recaptcha.internal;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zznf extends zznh {
    zznf(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    /* JADX WARN: Failed to inline method: com.google.android.recaptcha.internal.zzni.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Failed to inline method: com.google.android.recaptcha.internal.zzni.zzj(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r5v0 'z' boolean)' in method call: com.google.android.recaptcha.internal.zzni.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r5v0 'z' boolean)' in method call: com.google.android.recaptcha.internal.zzni.zzj(java.lang.Object, long, boolean):void */
    @Override // com.google.android.recaptcha.internal.zznh
    public final void zzc(Object obj, long j, boolean z) {
        if (zzni.zzb) {
            zzni.zzi(obj, j, z);
        } else {
            zzni.zzj(obj, j, z);
        }
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final void zzd(Object obj, long j, byte b) {
        if (zzni.zzb) {
            zzni.zzD(obj, j, b);
        } else {
            zzni.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.recaptcha.internal.zznh
    public final boolean zzg(Object obj, long j) {
        return zzni.zzb ? zzni.zzt(obj, j) : zzni.zzu(obj, j);
    }
}
