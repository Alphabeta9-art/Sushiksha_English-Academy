package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzali<T> implements zzalv<T> {
    private final zzalc zza;
    private final zzamo<?, ?> zzb;
    private final boolean zzc;
    private final zzajm<?> zzd;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final int zza(T t) {
        zzamo<?, ?> zzamoVar = this.zzb;
        int iZzb = zzamoVar.zzb(zzamoVar.zzd(t)) + 0;
        return this.zzc ? iZzb + this.zzd.zza(t).zza() : iZzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final int zzb(T t) {
        int iHashCode = this.zzb.zzd(t).hashCode();
        if (this.zzc) {
            return (iHashCode * 53) + this.zzd.zza(t).hashCode();
        }
        return iHashCode;
    }

    static <T> zzali<T> zza(zzamo<?, ?> zzamoVar, zzajm<?> zzajmVar, zzalc zzalcVar) {
        return new zzali<>(zzamoVar, zzajmVar, zzalcVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final T zza() {
        if (this.zza instanceof zzajy) {
            return (T) ((zzajy) this.zza).zzo();
        }
        return (T) this.zza.zzq().zzf();
    }

    private zzali(zzamo<?, ?> zzamoVar, zzajm<?> zzajmVar, zzalc zzalcVar) {
        this.zzb = zzamoVar;
        this.zzc = zzajmVar.zza(zzalcVar);
        this.zzd = zzajmVar;
        this.zza = zzalcVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zzd(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, T t2) {
        zzalx.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzalx.zza(this.zzd, t, t2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, zzals zzalsVar, zzajk zzajkVar) throws IOException {
        boolean zZzt;
        zzamo<?, ?> zzamoVar = this.zzb;
        zzajm<?> zzajmVar = this.zzd;
        Object objZzc = zzamoVar.zzc(t);
        zzajr<T> zzajrVarZzb = zzajmVar.zzb(t);
        while (zzalsVar.zzc() != Integer.MAX_VALUE) {
            try {
                int iZzd = zzalsVar.zzd();
                int iZzj = 0;
                if (iZzd != 11) {
                    if ((iZzd & 7) == 2) {
                        Object objZza = zzajmVar.zza(zzajkVar, this.zza, iZzd >>> 3);
                        if (objZza != null) {
                            zzajmVar.zza(zzalsVar, objZza, zzajkVar, zzajrVarZzb);
                        } else {
                            zZzt = zzamoVar.zza(objZzc, zzalsVar, 0);
                        }
                    } else {
                        zZzt = zzalsVar.zzt();
                    }
                    if (!zZzt) {
                        zzamoVar.zzb(t, objZzc);
                        return;
                    }
                } else {
                    Object objZza2 = null;
                    zzaip zzaipVarZzp = null;
                    while (zzalsVar.zzc() != Integer.MAX_VALUE) {
                        int iZzd2 = zzalsVar.zzd();
                        if (iZzd2 == 16) {
                            iZzj = zzalsVar.zzj();
                            objZza2 = zzajmVar.zza(zzajkVar, this.zza, iZzj);
                        } else if (iZzd2 == 26) {
                            if (objZza2 != null) {
                                zzajmVar.zza(zzalsVar, objZza2, zzajkVar, zzajrVarZzb);
                            } else {
                                zzaipVarZzp = zzalsVar.zzp();
                            }
                        } else if (!zzalsVar.zzt()) {
                            break;
                        }
                    }
                    if (zzalsVar.zzd() != 12) {
                        throw zzakf.zzb();
                    }
                    if (zzaipVarZzp != null) {
                        if (objZza2 != null) {
                            zzajmVar.zza(zzaipVarZzp, objZza2, zzajkVar, zzajrVarZzb);
                        } else {
                            zzamoVar.zza(objZzc, iZzj, zzaipVarZzp);
                        }
                    }
                }
                zZzt = true;
                if (!zZzt) {
                    zzamoVar.zzb(t, objZzc);
                    return;
                }
            } catch (Throwable th) {
                zzamoVar.zzb(t, objZzc);
                throw th;
            }
        }
        zzamoVar.zzb(t, objZzc);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, byte[] bArr, int i, int i2, zzaik zzaikVar) throws IOException {
        zzamn zzamnVar;
        int iZzc;
        zzajy zzajyVar = (zzajy) t;
        zzamn zzamnVar2 = zzajyVar.zzb;
        if (zzamnVar2 != zzamn.zzc()) {
            zzamnVar = zzamnVar2;
        } else {
            zzamn zzamnVarZzd = zzamn.zzd();
            zzajyVar.zzb = zzamnVarZzd;
            zzamnVar = zzamnVarZzd;
        }
        ((zzajy.zzb) t).zza();
        zzajy.zzd zzdVar = null;
        while (i < i2) {
            int iZzc2 = zzail.zzc(bArr, i, zzaikVar);
            int i3 = zzaikVar.zza;
            if (i3 != 11) {
                if ((i3 & 7) == 2) {
                    zzdVar = (zzajy.zzd) this.zzd.zza(zzaikVar.zzd, this.zza, i3 >>> 3);
                    if (zzdVar != null) {
                        zzalr.zza();
                        throw new NoSuchMethodError();
                    }
                    i = zzail.zza(i3, bArr, iZzc2, i2, zzamnVar, zzaikVar);
                } else {
                    i = zzail.zza(i3, bArr, iZzc2, i2, zzaikVar);
                }
            } else {
                byte[] bArr2 = bArr;
                int i4 = i2;
                zzaik zzaikVar2 = zzaikVar;
                int i5 = 0;
                zzaip zzaipVar = null;
                while (true) {
                    if (iZzc2 >= i4) {
                        iZzc = iZzc2;
                    } else {
                        iZzc = zzail.zzc(bArr2, iZzc2, zzaikVar2);
                        int i6 = zzaikVar2.zza;
                        int i7 = i6 & 7;
                        switch (i6 >>> 3) {
                            case 2:
                                if (i7 == 0) {
                                    iZzc2 = zzail.zzc(bArr2, iZzc, zzaikVar2);
                                    i5 = zzaikVar2.zza;
                                    zzdVar = (zzajy.zzd) this.zzd.zza(zzaikVar2.zzd, this.zza, i5);
                                } else if (i6 != 12) {
                                    iZzc2 = zzail.zza(i6, bArr2, iZzc, i4, zzaikVar2);
                                }
                                break;
                            case 3:
                                if (zzdVar != null) {
                                    zzalr.zza();
                                    throw new NoSuchMethodError();
                                }
                                if (i7 == 2) {
                                    iZzc2 = zzail.zza(bArr2, iZzc, zzaikVar2);
                                    zzaipVar = (zzaip) zzaikVar2.zzc;
                                } else if (i6 != 12) {
                                    iZzc2 = zzail.zza(i6, bArr2, iZzc, i4, zzaikVar2);
                                }
                                break;
                                break;
                            default:
                                if (i6 != 12) {
                                    iZzc2 = zzail.zza(i6, bArr2, iZzc, i4, zzaikVar2);
                                }
                                break;
                        }
                    }
                }
                if (zzaipVar != null) {
                    zzamnVar.zza((i5 << 3) | 2, zzaipVar);
                }
                i = iZzc;
                bArr = bArr2;
                i2 = i4;
                zzaikVar = zzaikVar2;
            }
        }
        if (i != i2) {
            throw zzakf.zzg();
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, zzanf zzanfVar) throws IOException {
        Iterator itZzd = this.zzd.zza(t).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzajt zzajtVar = (zzajt) entry.getKey();
            if (zzajtVar.zzc() != zzand.MESSAGE || zzajtVar.zze() || zzajtVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzakj) {
                zzanfVar.zza(zzajtVar.zza(), (Object) ((zzakj) entry).zza().zzb());
            } else {
                zzanfVar.zza(zzajtVar.zza(), entry.getValue());
            }
        }
        zzamo<?, ?> zzamoVar = this.zzb;
        zzamoVar.zza(zzamoVar.zzd(t), zzanfVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final boolean zze(T t) {
        return this.zzd.zza(t).zzg();
    }
}
