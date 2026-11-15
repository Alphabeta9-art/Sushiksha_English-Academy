package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzki {
    private static final zzki zzb = new zzki(true);
    final zzmu zza = new zzmn(16);
    private boolean zzc;
    private boolean zzd;

    private zzki() {
    }

    public static int zza(zzkh zzkhVar, Object obj) {
        zznm zznmVarZzd = zzkhVar.zzd();
        int iZza = zzkhVar.zza();
        zzkhVar.zzg();
        int iZzx = zzjg.zzx(iZza << 3);
        if (zznmVarZzd == zznm.GROUP) {
            zzlx zzlxVar = (zzlx) obj;
            byte[] bArr = zzla.zzb;
            if (zzlxVar instanceof zzih) {
                throw null;
            }
            iZzx += iZzx;
        }
        zznn zznnVar = zznn.INT;
        int iZzy = 4;
        switch (zznmVarZzd) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                iZzy = 8;
                break;
            case FLOAT:
                ((Float) obj).floatValue();
                break;
            case INT64:
                iZzy = zzjg.zzy(((Long) obj).longValue());
                break;
            case UINT64:
                iZzy = zzjg.zzy(((Long) obj).longValue());
                break;
            case INT32:
                iZzy = zzjg.zzy(((Integer) obj).intValue());
                break;
            case FIXED64:
                ((Long) obj).longValue();
                iZzy = 8;
                break;
            case FIXED32:
                ((Integer) obj).intValue();
                break;
            case BOOL:
                ((Boolean) obj).booleanValue();
                iZzy = 1;
                break;
            case STRING:
                if (!(obj instanceof zziv)) {
                    iZzy = zzjg.zzw((String) obj);
                } else {
                    int iZzd = ((zziv) obj).zzd();
                    iZzy = zzjg.zzx(iZzd) + iZzd;
                }
                break;
            case GROUP:
                iZzy = ((zzlx) obj).zzn();
                break;
            case MESSAGE:
                if (!(obj instanceof zzlh)) {
                    iZzy = zzjg.zzu((zzlx) obj);
                } else {
                    int iZza2 = ((zzlh) obj).zza();
                    iZzy = zzjg.zzx(iZza2) + iZza2;
                }
                break;
            case BYTES:
                if (!(obj instanceof zziv)) {
                    int length = ((byte[]) obj).length;
                    iZzy = zzjg.zzx(length) + length;
                } else {
                    int iZzd2 = ((zziv) obj).zzd();
                    iZzy = zzjg.zzx(iZzd2) + iZzd2;
                }
                break;
            case UINT32:
                iZzy = zzjg.zzx(((Integer) obj).intValue());
                break;
            case ENUM:
                iZzy = !(obj instanceof zzku) ? zzjg.zzy(((Integer) obj).intValue()) : zzjg.zzy(((zzku) obj).zza());
                break;
            case SFIXED32:
                ((Integer) obj).intValue();
                break;
            case SFIXED64:
                ((Long) obj).longValue();
                iZzy = 8;
                break;
            case SINT32:
                int iIntValue = ((Integer) obj).intValue();
                iZzy = zzjg.zzx((iIntValue + iIntValue) ^ (iIntValue >> 31));
                break;
            case SINT64:
                long jLongValue = ((Long) obj).longValue();
                iZzy = zzjg.zzy((jLongValue + jLongValue) ^ (jLongValue >> 63));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return iZzx + iZzy;
    }

    public static zzki zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzmc) {
            return ((zzmc) obj).zzd();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzm(Map.Entry entry) {
        zzlx zzlxVarZzj;
        zzkh zzkhVar = (zzkh) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzlh;
        zzkhVar.zzg();
        if (zzkhVar.zze() != zznn.MESSAGE) {
            if (z) {
                throw new IllegalStateException("Lazy fields must be message-valued");
            }
            this.zza.put(zzkhVar, zzl(value));
            return;
        }
        Object objZze = zze(zzkhVar);
        if (objZze == null) {
            this.zza.put(zzkhVar, zzl(value));
            if (z) {
                this.zzd = true;
                return;
            }
            return;
        }
        if (z) {
            throw null;
        }
        if (objZze instanceof zzmc) {
            zzlxVarZzj = zzkhVar.zzc((zzmc) objZze, (zzmc) value);
        } else {
            zzlw zzlwVarZzab = ((zzlx) objZze).zzab();
            zzkhVar.zzb(zzlwVarZzab, (zzlx) value);
            zzlxVarZzj = zzlwVarZzab.zzj();
        }
        this.zza.put(zzkhVar, zzlxVarZzj);
    }

    private static boolean zzn(Map.Entry entry) {
        zzkh zzkhVar = (zzkh) entry.getKey();
        if (zzkhVar.zze() != zznn.MESSAGE) {
            return true;
        }
        zzkhVar.zzg();
        Object value = entry.getValue();
        if (value instanceof zzly) {
            return ((zzly) value).zzo();
        }
        if (value instanceof zzlh) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        zzkh zzkhVar = (zzkh) entry.getKey();
        Object value = entry.getValue();
        if (zzkhVar.zze() != zznn.MESSAGE) {
            return zza(zzkhVar, value);
        }
        zzkhVar.zzg();
        zzkhVar.zzf();
        if (!(value instanceof zzlh)) {
            int iZza = ((zzkh) entry.getKey()).zza();
            int iZzx = zzjg.zzx(8);
            return iZzx + iZzx + zzjg.zzx(16) + zzjg.zzx(iZza) + zzjg.zzx(24) + zzjg.zzu((zzlx) value);
        }
        int iZza2 = ((zzkh) entry.getKey()).zza();
        int iZzx2 = zzjg.zzx(8);
        int iZzx3 = zzjg.zzx(16) + zzjg.zzx(iZza2);
        int iZzx4 = zzjg.zzx(24);
        int iZza3 = ((zzlh) value).zza();
        return iZzx2 + iZzx2 + iZzx3 + iZzx4 + zzjg.zzx(iZza3) + iZza3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzki) {
            return this.zza.equals(((zzki) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int iZzo = 0;
        for (int i = 0; i < this.zza.zzb(); i++) {
            iZzo += zzo(this.zza.zzg(i));
        }
        Iterator it = this.zza.zzc().iterator();
        while (it.hasNext()) {
            iZzo += zzo((Map.Entry) it.next());
        }
        return iZzo;
    }

    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzki clone() {
        zzki zzkiVar = new zzki();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            zzkiVar.zzi((zzkh) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzkiVar.zzi((zzkh) entry.getKey(), entry.getValue());
        }
        zzkiVar.zzd = this.zzd;
        return zzkiVar;
    }

    public final Object zze(zzkh zzkhVar) {
        Object obj = this.zza.get(zzkhVar);
        if (!(obj instanceof zzlh)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        return this.zzd ? new zzlg(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            if (entryZzg.getValue() instanceof zzks) {
                ((zzks) entryZzg.getValue()).zzD();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzki zzkiVar) {
        for (int i = 0; i < zzkiVar.zza.zzb(); i++) {
            zzm(zzkiVar.zza.zzg(i));
        }
        Iterator it = zzkiVar.zza.zzc().iterator();
        while (it.hasNext()) {
            zzm((Map.Entry) it.next());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x004d  */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zzlh) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zzku) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0033, code lost:
    
        if ((r4 instanceof byte[]) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
    
        if (r0 != false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(com.google.android.recaptcha.internal.zzkh r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.zzg()
            com.google.android.recaptcha.internal.zznm r0 = r3.zzd()
            byte[] r1 = com.google.android.recaptcha.internal.zzla.zzb
            if (r4 == 0) goto L7e
            com.google.android.recaptcha.internal.zznm r1 = com.google.android.recaptcha.internal.zznm.DOUBLE
            com.google.android.recaptcha.internal.zznn r1 = com.google.android.recaptcha.internal.zznn.INT
            com.google.android.recaptcha.internal.zznn r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L45;
                case 1: goto L42;
                case 2: goto L3f;
                case 3: goto L3c;
                case 4: goto L39;
                case 5: goto L36;
                case 6: goto L2d;
                case 7: goto L24;
                case 8: goto L1b;
                default: goto L1a;
            }
        L1a:
            goto L56
        L1b:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzlx
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzlh
            if (r0 != 0) goto L49
            goto L56
        L24:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzku
            if (r0 != 0) goto L49
            goto L56
        L2d:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zziv
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L56
            goto L49
        L36:
            boolean r0 = r4 instanceof java.lang.String
            goto L47
        L39:
            boolean r0 = r4 instanceof java.lang.Boolean
            goto L47
        L3c:
            boolean r0 = r4 instanceof java.lang.Double
            goto L47
        L3f:
            boolean r0 = r4 instanceof java.lang.Float
            goto L47
        L42:
            boolean r0 = r4 instanceof java.lang.Long
            goto L47
        L45:
            boolean r0 = r4 instanceof java.lang.Integer
        L47:
            if (r0 == 0) goto L56
        L49:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzlh
            if (r0 == 0) goto L50
            r0 = 1
            r2.zzd = r0
        L50:
            com.google.android.recaptcha.internal.zzmu r0 = r2.zza
            r0.put(r3, r4)
            return
        L56:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r3.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.recaptcha.internal.zznm r3 = r3.zzd()
            com.google.android.recaptcha.internal.zznn r3 = r3.zza()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        L7e:
            r3 = 0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzki.zzi(com.google.android.recaptcha.internal.zzkh, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzc().iterator();
        while (it.hasNext()) {
            if (!zzn((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private zzki(boolean z) {
        zzg();
        zzg();
    }
}
