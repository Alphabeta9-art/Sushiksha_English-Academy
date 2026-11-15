package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzmb implements zzmk {
    private final zzlx zza;
    private final zznb zzb;
    private final boolean zzc;
    private final zzke zzd;

    private zzmb(zznb zznbVar, zzke zzkeVar, zzlx zzlxVar) {
        this.zzb = zznbVar;
        this.zzc = zzkeVar.zzj(zzlxVar);
        this.zzd = zzkeVar;
        this.zza = zzlxVar;
    }

    static zzmb zzc(zznb zznbVar, zzke zzkeVar, zzlx zzlxVar) {
        return new zzmb(zznbVar, zzkeVar, zzlxVar);
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final int zza(Object obj) {
        zznb zznbVar = this.zzb;
        int iZzb = zznbVar.zzb(zznbVar.zzd(obj));
        return this.zzc ? iZzb + this.zzd.zzb(obj).zzb() : iZzb;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final int zzb(Object obj) {
        int iHashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final Object zze() {
        zzlx zzlxVar = this.zza;
        return zzlxVar instanceof zzks ? ((zzks) zzlxVar).zzt() : zzlxVar.zzaa().zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zzf(obj);
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzg(Object obj, Object obj2) {
        zzmm.zzr(this.zzb, obj, obj2);
        if (this.zzc) {
            zzmm.zzq(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzh(Object obj, zzmj zzmjVar, zzkd zzkdVar) throws IOException {
        boolean zZzO;
        zznb zznbVar = this.zzb;
        Object objZzc = zznbVar.zzc(obj);
        zzke zzkeVar = this.zzd;
        zzki zzkiVarZzc = zzkeVar.zzc(obj);
        while (zzmjVar.zzc() != Integer.MAX_VALUE) {
            try {
                int iZzd = zzmjVar.zzd();
                if (iZzd != 11) {
                    if ((iZzd & 7) == 2) {
                        Object objZzd = zzkeVar.zzd(zzkdVar, this.zza, iZzd >>> 3);
                        if (objZzd != null) {
                            zzkeVar.zzg(zzmjVar, objZzd, zzkdVar, zzkiVarZzc);
                        } else {
                            zZzO = zznbVar.zzr(objZzc, zzmjVar);
                        }
                    } else {
                        zZzO = zzmjVar.zzO();
                    }
                    if (!zZzO) {
                        break;
                    }
                } else {
                    Object objZzd2 = null;
                    int iZzj = 0;
                    zziv zzivVarZzp = null;
                    while (zzmjVar.zzc() != Integer.MAX_VALUE) {
                        int iZzd2 = zzmjVar.zzd();
                        if (iZzd2 == 16) {
                            iZzj = zzmjVar.zzj();
                            objZzd2 = zzkeVar.zzd(zzkdVar, this.zza, iZzj);
                        } else if (iZzd2 == 26) {
                            if (objZzd2 != null) {
                                zzkeVar.zzg(zzmjVar, objZzd2, zzkdVar, zzkiVarZzc);
                            } else {
                                zzivVarZzp = zzmjVar.zzp();
                            }
                        } else if (!zzmjVar.zzO()) {
                            break;
                        }
                    }
                    if (zzmjVar.zzd() != 12) {
                        throw zzlc.zzb();
                    }
                    if (zzivVarZzp != null) {
                        if (objZzd2 != null) {
                            zzkeVar.zzh(zzivVarZzp, objZzd2, zzkdVar, zzkiVarZzc);
                        } else {
                            zznbVar.zzk(objZzc, iZzj, zzivVarZzp);
                        }
                    }
                }
            } catch (Throwable th) {
                zznbVar.zzn(obj, objZzc);
                throw th;
            }
        }
        zznbVar.zzn(obj, objZzc);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0089  */
    /* JADX WARN: Code duplicated, block: B:60:0x008f A[SYNTHETIC] */
    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzik zzikVar) throws IOException {
        zznc zzncVar;
        int iZzi;
        zzks zzksVar = (zzks) obj;
        zznc zzncVar2 = zzksVar.zzc;
        if (zzncVar2 == zznc.zzc()) {
            zznc zzncVarZzf = zznc.zzf();
            zzksVar.zzc = zzncVarZzf;
            zzncVar = zzncVarZzf;
        } else {
            zzncVar = zzncVar2;
        }
        ((zzko) obj).zzi();
        Object objZzd = null;
        while (i < i2) {
            int iZzi2 = zzil.zzi(bArr, i, zzikVar);
            int i3 = zzikVar.zza;
            if (i3 == 11) {
                byte[] bArr2 = bArr;
                int i4 = i2;
                zzik zzikVar2 = zzikVar;
                int i5 = 0;
                zziv zzivVar = null;
                while (true) {
                    if (iZzi2 < i4) {
                        iZzi = zzil.zzi(bArr2, iZzi2, zzikVar2);
                        int i6 = zzikVar2.zza;
                        int i7 = i6 & 7;
                        switch (i6 >>> 3) {
                            case 2:
                                if (i7 == 0) {
                                    iZzi2 = zzil.zzi(bArr2, iZzi, zzikVar2);
                                    i5 = zzikVar2.zza;
                                    objZzd = this.zzd.zzd(zzikVar2.zzd, this.zza, i5);
                                } else if (i6 != 12) {
                                    iZzi2 = zzil.zzo(i6, bArr2, iZzi, i4, zzikVar2);
                                }
                                break;
                            case 3:
                                if (objZzd != null) {
                                    int i8 = zzmg.zza;
                                    throw null;
                                }
                                if (i7 == 2) {
                                    iZzi2 = zzil.zza(bArr2, iZzi, zzikVar2);
                                    zzivVar = (zziv) zzikVar2.zzc;
                                } else if (i6 != 12) {
                                    iZzi2 = zzil.zzo(i6, bArr2, iZzi, i4, zzikVar2);
                                }
                                break;
                                break;
                            default:
                                if (i6 != 12) {
                                    iZzi2 = zzil.zzo(i6, bArr2, iZzi, i4, zzikVar2);
                                }
                                break;
                        }
                    } else {
                        iZzi = iZzi2;
                    }
                }
                if (zzivVar != null) {
                    zzncVar.zzj((i5 << 3) | 2, zzivVar);
                }
                i = iZzi;
                bArr = bArr2;
                i2 = i4;
                zzikVar = zzikVar2;
            } else if ((i3 & 7) == 2) {
                objZzd = this.zzd.zzd(zzikVar.zzd, this.zza, i3 >>> 3);
                if (objZzd != null) {
                    int i9 = zzmg.zza;
                    throw null;
                }
                i = zzil.zzh(i3, bArr, iZzi2, i2, zzncVar, zzikVar);
            } else {
                i = zzil.zzo(i3, bArr, iZzi2, i2, zzikVar);
            }
        }
        if (i != i2) {
            throw zzlc.zzg();
        }
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzj(Object obj, zzno zznoVar) throws IOException {
        Iterator itZzf = this.zzd.zzb(obj).zzf();
        while (itZzf.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzf.next();
            zzkh zzkhVar = (zzkh) entry.getKey();
            if (zzkhVar.zze() != zznn.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzkhVar.zzg();
            zzkhVar.zzf();
            if (entry instanceof zzlf) {
                zznoVar.zzw(zzkhVar.zza(), ((zzlf) entry).zza().zzb());
            } else {
                zznoVar.zzw(zzkhVar.zza(), entry.getValue());
            }
        }
        zznb zznbVar = this.zzb;
        zznbVar.zzp(zznbVar.zzd(obj), zznoVar);
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final boolean zzk(Object obj, Object obj2) {
        zznb zznbVar = this.zzb;
        if (!zznbVar.zzd(obj).equals(zznbVar.zzd(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final boolean zzl(Object obj) {
        return this.zzd.zzb(obj).zzk();
    }
}
