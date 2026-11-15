package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzjc implements zzmj {
    private final zzjb zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzjc(zzjb zzjbVar) {
        byte[] bArr = zzla.zzb;
        this.zza = zzjbVar;
        this.zza.zzc = this;
    }

    private final void zzP(Object obj, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzmkVar.zzh(obj, this, zzkdVar);
            if (this.zzb != this.zzc) {
                throw zzlc.zzg();
            }
            this.zzc = i;
        } catch (Throwable th) {
            this.zzc = i;
            throw th;
        }
    }

    private final void zzQ(Object obj, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        zzjb zzjbVar = this.zza;
        int iZzn = zzjbVar.zzn();
        if (zzjbVar.zza >= zzjbVar.zzb) {
            throw new zzlc("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZze = this.zza.zze(iZzn);
        this.zza.zza++;
        zzmkVar.zzh(obj, this, zzkdVar);
        this.zza.zzz(0);
        zzjb zzjbVar2 = this.zza;
        zzjbVar2.zza--;
        zzjbVar2.zzA(iZze);
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzd() != i) {
            throw zzlc.zzi();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzlc.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzlc.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzlc.zzg();
        }
    }

    public static zzjc zzq(zzjb zzjbVar) {
        zzjc zzjcVar = zzjbVar.zzc;
        return zzjcVar != null ? zzjcVar : new zzjc(zzjbVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzA(List list) throws IOException {
        int iZzm;
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            switch (this.zzb & 7) {
                case 1:
                    do {
                        zzlmVar.zzg(this.zza.zzo());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn = this.zza.zzn();
                    zzU(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzlmVar.zzg(this.zza.zzo());
                    } while (this.zza.zzd() < iZzd);
                    return;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 1:
                    do {
                        list.add(Long.valueOf(this.zza.zzo()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzU(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Long.valueOf(this.zza.zzo()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzB(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkk) {
            zzkk zzkkVar = (zzkk) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzn = this.zza.zzn();
                    zzT(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzkkVar.zzf(this.zza.zzc());
                    } while (this.zza.zzd() < iZzd);
                    return;
                case 5:
                    do {
                        zzkkVar.zzf(this.zza.zzc());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzT(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Float.valueOf(this.zza.zzc()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                case 5:
                    do {
                        list.add(Float.valueOf(this.zza.zzc()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    @Deprecated
    public final void zzC(List list, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        int iZzm;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzlc.zza();
        }
        do {
            Object objZze = zzmkVar.zze();
            zzP(objZze, zzmkVar, zzkdVar);
            zzmkVar.zzf(objZze);
            list.add(objZze);
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            } else {
                iZzm = this.zza.zzm();
            }
        } while (iZzm == i);
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzD(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzktVar.zzh(this.zza.zzh());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzktVar.zzh(this.zza.zzh());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Integer.valueOf(this.zza.zzh()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Integer.valueOf(this.zza.zzh()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzE(List list) throws IOException {
        int iZzm;
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzlmVar.zzg(this.zza.zzp());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzlmVar.zzg(this.zza.zzp());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Long.valueOf(this.zza.zzp()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Long.valueOf(this.zza.zzp()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzF(List list, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        int iZzm;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzlc.zza();
        }
        do {
            Object objZze = zzmkVar.zze();
            zzQ(objZze, zzmkVar, zzkdVar);
            zzmkVar.zzf(objZze);
            list.add(objZze);
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            } else {
                iZzm = this.zza.zzm();
            }
        } while (iZzm == i);
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzG(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzn = this.zza.zzn();
                    zzT(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzktVar.zzh(this.zza.zzk());
                    } while (this.zza.zzd() < iZzd);
                    return;
                case 5:
                    do {
                        zzktVar.zzh(this.zza.zzk());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzT(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Integer.valueOf(this.zza.zzk()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                case 5:
                    do {
                        list.add(Integer.valueOf(this.zza.zzk()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzH(List list) throws IOException {
        int iZzm;
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            switch (this.zzb & 7) {
                case 1:
                    do {
                        zzlmVar.zzg(this.zza.zzt());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn = this.zza.zzn();
                    zzU(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzlmVar.zzg(this.zza.zzt());
                    } while (this.zza.zzd() < iZzd);
                    return;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 1:
                    do {
                        list.add(Long.valueOf(this.zza.zzt()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzU(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Long.valueOf(this.zza.zzt()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzI(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzktVar.zzh(this.zza.zzl());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzktVar.zzh(this.zza.zzl());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Integer.valueOf(this.zza.zzl()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Integer.valueOf(this.zza.zzl()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzJ(List list) throws IOException {
        int iZzm;
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzlmVar.zzg(this.zza.zzu());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzlmVar.zzg(this.zza.zzu());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Long.valueOf(this.zza.zzu()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Long.valueOf(this.zza.zzu()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    public final void zzK(List list, boolean z) throws IOException {
        int iZzm;
        int iZzm2;
        if ((this.zzb & 7) != 2) {
            throw zzlc.zza();
        }
        if ((list instanceof zzlj) && !z) {
            zzlj zzljVar = (zzlj) list;
            do {
                zzp();
                zzljVar.zzb();
                if (this.zza.zzC()) {
                    return;
                } else {
                    iZzm2 = this.zza.zzm();
                }
            } while (iZzm2 == this.zzb);
        } else {
            do {
                list.add(z ? zzs() : zzr());
                if (this.zza.zzC()) {
                    return;
                } else {
                    iZzm = this.zza.zzm();
                }
            } while (iZzm == this.zzb);
            iZzm2 = iZzm;
        }
        this.zzd = iZzm2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzL(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzktVar.zzh(this.zza.zzn());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzktVar.zzh(this.zza.zzn());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Integer.valueOf(this.zza.zzn()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Integer.valueOf(this.zza.zzn()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzM(List list) throws IOException {
        int iZzm;
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzlmVar.zzg(this.zza.zzv());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzlmVar.zzg(this.zza.zzv());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Long.valueOf(this.zza.zzv()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Long.valueOf(this.zza.zzv()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzD();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final boolean zzO() throws IOException {
        int i;
        if (this.zza.zzC() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(i);
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final double zza() throws IOException {
        zzS(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final float zzb() throws IOException {
        zzS(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzc() throws IOException {
        int iZzm = this.zzd;
        if (iZzm != 0) {
            this.zzb = iZzm;
            this.zzd = 0;
        } else {
            iZzm = this.zza.zzm();
            this.zzb = iZzm;
        }
        if (iZzm == 0 || iZzm == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return iZzm >>> 3;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zze() throws IOException {
        zzS(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzf() throws IOException {
        zzS(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzg() throws IOException {
        zzS(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzh() throws IOException {
        zzS(5);
        return this.zza.zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzi() throws IOException {
        zzS(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final int zzj() throws IOException {
        zzS(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final long zzk() throws IOException {
        zzS(1);
        return this.zza.zzo();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final long zzl() throws IOException {
        zzS(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final long zzm() throws IOException {
        zzS(1);
        return this.zza.zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final long zzn() throws IOException {
        zzS(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final long zzo() throws IOException {
        zzS(0);
        return this.zza.zzv();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final zziv zzp() throws IOException {
        zzS(2);
        return this.zza.zzw();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final String zzr() throws IOException {
        zzS(2);
        return this.zza.zzx();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final String zzs() throws IOException {
        zzS(2);
        return this.zza.zzy();
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzt(Object obj, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        zzS(3);
        zzP(obj, zzmkVar, zzkdVar);
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzu(Object obj, zzmk zzmkVar, zzkd zzkdVar) throws IOException {
        zzS(2);
        zzQ(obj, zzmkVar, zzkdVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzv(List list) throws IOException {
        int iZzm;
        if (list instanceof zzim) {
            zzim zzimVar = (zzim) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzimVar.zze(this.zza.zzD());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzimVar.zze(this.zza.zzD());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Boolean.valueOf(this.zza.zzD()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Boolean.valueOf(this.zza.zzD()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzw(List list) throws IOException {
        int iZzm;
        if ((this.zzb & 7) != 2) {
            throw zzlc.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzC()) {
                return;
            } else {
                iZzm = this.zza.zzm();
            }
        } while (iZzm == this.zzb);
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzx(List list) throws IOException {
        int iZzm;
        if (list instanceof zzjx) {
            zzjx zzjxVar = (zzjx) list;
            switch (this.zzb & 7) {
                case 1:
                    do {
                        zzjxVar.zzf(this.zza.zzb());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn = this.zza.zzn();
                    zzU(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzjxVar.zzf(this.zza.zzb());
                    } while (this.zza.zzd() < iZzd);
                    return;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 1:
                    do {
                        list.add(Double.valueOf(this.zza.zzb()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzU(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Double.valueOf(this.zza.zzb()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzy(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 0:
                    do {
                        zzktVar.zzh(this.zza.zzf());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar = this.zza;
                    int iZzd = zzjbVar.zzd() + zzjbVar.zzn();
                    do {
                        zzktVar.zzh(this.zza.zzf());
                    } while (this.zza.zzd() < iZzd);
                    zzR(iZzd);
                    return;
            }
        } else {
            switch (this.zzb & 7) {
                case 0:
                    do {
                        list.add(Integer.valueOf(this.zza.zzf()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                case 1:
                default:
                    throw zzlc.zza();
                case 2:
                    zzjb zzjbVar2 = this.zza;
                    int iZzd2 = zzjbVar2.zzd() + zzjbVar2.zzn();
                    do {
                        list.add(Integer.valueOf(this.zza.zzf()));
                    } while (this.zza.zzd() < iZzd2);
                    zzR(iZzd2);
                    return;
            }
        }
        this.zzd = iZzm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.recaptcha.internal.zzmj
    public final void zzz(List list) throws IOException {
        int iZzm;
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzn = this.zza.zzn();
                    zzT(iZzn);
                    int iZzd = this.zza.zzd() + iZzn;
                    do {
                        zzktVar.zzh(this.zza.zzg());
                    } while (this.zza.zzd() < iZzd);
                    return;
                case 5:
                    do {
                        zzktVar.zzh(this.zza.zzg());
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        } else {
            switch (this.zzb & 7) {
                case 2:
                    int iZzn2 = this.zza.zzn();
                    zzT(iZzn2);
                    int iZzd2 = this.zza.zzd() + iZzn2;
                    do {
                        list.add(Integer.valueOf(this.zza.zzg()));
                    } while (this.zza.zzd() < iZzd2);
                    return;
                case 5:
                    do {
                        list.add(Integer.valueOf(this.zza.zzg()));
                        if (this.zza.zzC()) {
                            return;
                        } else {
                            iZzm = this.zza.zzm();
                        }
                    } while (iZzm == this.zzb);
                    break;
                default:
                    throw zzlc.zza();
            }
        }
        this.zzd = iZzm;
    }
}
