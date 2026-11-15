package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzkf extends zzke {
    zzkf() {
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final int zza(Map.Entry entry) {
        return ((zzkp) entry.getKey()).zza;
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final zzki zzb(Object obj) {
        return ((zzko) obj).zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final zzki zzc(Object obj) {
        return ((zzko) obj).zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final Object zzd(zzkd zzkdVar, zzlx zzlxVar, int i) {
        return zzkdVar.zza(zzlxVar, i);
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final Object zze(Object obj, zzmj zzmjVar, Object obj2, zzkd zzkdVar, zzki zzkiVar, Object obj3, zznb zznbVar) throws IOException {
        zzkq zzkqVar = (zzkq) obj2;
        zznm zznmVar = zzkqVar.zza.zzb;
        Object objZzk = null;
        if (zznmVar == zznm.ENUM) {
            zzmjVar.zzg();
            throw null;
        }
        switch (zznmVar) {
            case DOUBLE:
                objZzk = Double.valueOf(zzmjVar.zza());
                break;
            case FLOAT:
                objZzk = Float.valueOf(zzmjVar.zzb());
                break;
            case INT64:
                objZzk = Long.valueOf(zzmjVar.zzl());
                break;
            case UINT64:
                objZzk = Long.valueOf(zzmjVar.zzo());
                break;
            case INT32:
                objZzk = Integer.valueOf(zzmjVar.zzg());
                break;
            case FIXED64:
                objZzk = Long.valueOf(zzmjVar.zzk());
                break;
            case FIXED32:
                objZzk = Integer.valueOf(zzmjVar.zzf());
                break;
            case BOOL:
                objZzk = Boolean.valueOf(zzmjVar.zzN());
                break;
            case STRING:
                objZzk = zzmjVar.zzr();
                break;
            case GROUP:
                Object objZze = zzkiVar.zze(zzkqVar.zza);
                if (!(objZze instanceof zzks)) {
                    throw null;
                }
                zzmk zzmkVarZzb = zzmg.zza().zzb(objZze.getClass());
                if (!((zzks) objZze).zzI()) {
                    Object objZze2 = zzmkVarZzb.zze();
                    zzmkVarZzb.zzg(objZze2, objZze);
                    zzkiVar.zzi(zzkqVar.zza, objZze2);
                    objZze = objZze2;
                }
                zzmjVar.zzt(objZze, zzmkVarZzb, zzkdVar);
                return obj3;
            case MESSAGE:
                Object objZze3 = zzkiVar.zze(zzkqVar.zza);
                if (!(objZze3 instanceof zzks)) {
                    throw null;
                }
                zzmk zzmkVarZzb2 = zzmg.zza().zzb(objZze3.getClass());
                if (!((zzks) objZze3).zzI()) {
                    Object objZze4 = zzmkVarZzb2.zze();
                    zzmkVarZzb2.zzg(objZze4, objZze3);
                    zzkiVar.zzi(zzkqVar.zza, objZze4);
                    objZze3 = objZze4;
                }
                zzmjVar.zzu(objZze3, zzmkVarZzb2, zzkdVar);
                return obj3;
            case BYTES:
                objZzk = zzmjVar.zzp();
                break;
            case UINT32:
                objZzk = Integer.valueOf(zzmjVar.zzj());
                break;
            case ENUM:
                throw new IllegalStateException("Shouldn't reach here.");
            case SFIXED32:
                objZzk = Integer.valueOf(zzmjVar.zzh());
                break;
            case SFIXED64:
                objZzk = Long.valueOf(zzmjVar.zzm());
                break;
            case SINT32:
                objZzk = Integer.valueOf(zzmjVar.zzi());
                break;
            case SINT64:
                objZzk = Long.valueOf(zzmjVar.zzn());
                break;
        }
        switch (zzkqVar.zza.zzb.ordinal()) {
            case 9:
            case 10:
                Object objZze5 = zzkiVar.zze(zzkqVar.zza);
                if (objZze5 != null) {
                    byte[] bArr = zzla.zzb;
                    objZzk = ((zzlx) objZze5).zzab().zzc((zzlx) objZzk).zzk();
                }
                break;
        }
        zzkiVar.zzi(zzkqVar.zza, objZzk);
        return obj3;
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final void zzf(Object obj) {
        ((zzko) obj).zzb.zzg();
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final void zzg(zzmj zzmjVar, Object obj, zzkd zzkdVar, zzki zzkiVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final void zzh(zziv zzivVar, Object obj, zzkd zzkdVar, zzki zzkiVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final void zzi(zzno zznoVar, Map.Entry entry) throws IOException {
        zzkp zzkpVar = (zzkp) entry.getKey();
        zznm zznmVar = zznm.DOUBLE;
        switch (zzkpVar.zzb) {
            case DOUBLE:
                zznoVar.zzf(zzkpVar.zza, ((Double) entry.getValue()).doubleValue());
                break;
            case FLOAT:
                zznoVar.zzo(zzkpVar.zza, ((Float) entry.getValue()).floatValue());
                break;
            case INT64:
                zznoVar.zzt(zzkpVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case UINT64:
                zznoVar.zzK(zzkpVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case INT32:
                zznoVar.zzr(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case FIXED64:
                zznoVar.zzm(zzkpVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case FIXED32:
                zznoVar.zzk(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case BOOL:
                zznoVar.zzb(zzkpVar.zza, ((Boolean) entry.getValue()).booleanValue());
                break;
            case STRING:
                zznoVar.zzG(zzkpVar.zza, (String) entry.getValue());
                break;
            case GROUP:
                zznoVar.zzq(zzkpVar.zza, entry.getValue(), zzmg.zza().zzb(entry.getValue().getClass()));
                break;
            case MESSAGE:
                zznoVar.zzv(zzkpVar.zza, entry.getValue(), zzmg.zza().zzb(entry.getValue().getClass()));
                break;
            case BYTES:
                zznoVar.zzd(zzkpVar.zza, (zziv) entry.getValue());
                break;
            case UINT32:
                zznoVar.zzI(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case ENUM:
                zznoVar.zzr(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED32:
                zznoVar.zzx(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED64:
                zznoVar.zzz(zzkpVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case SINT32:
                zznoVar.zzB(zzkpVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SINT64:
                zznoVar.zzD(zzkpVar.zza, ((Long) entry.getValue()).longValue());
                break;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzke
    final boolean zzj(zzlx zzlxVar) {
        return zzlxVar instanceof zzko;
    }
}
