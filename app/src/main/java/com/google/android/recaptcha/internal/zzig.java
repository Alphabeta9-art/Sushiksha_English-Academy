package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzif;
import com.google.android.recaptcha.internal.zzig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public abstract class zzig<MessageType extends zzig<MessageType, BuilderType>, BuilderType extends zzif<MessageType, BuilderType>> implements zzlx {
    protected int zza = 0;

    /* JADX WARN: Multi-variable type inference failed */
    protected static void zzc(Iterable iterable, List list) {
        byte[] bArr = zzla.zzb;
        if (iterable == 0) {
            throw null;
        }
        if (!(iterable instanceof zzlj)) {
            if (iterable instanceof zzmf) {
                list.addAll(iterable);
                return;
            }
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
            }
            int size = list.size();
            for (Object obj : iterable) {
                if (obj == null) {
                    String str = "Element at index " + (list.size() - size) + " is null.";
                    for (int size2 = list.size() - 1; size2 >= size; size2--) {
                        list.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                list.add(obj);
            }
            return;
        }
        List listZza = ((zzlj) iterable).zza();
        zzlj zzljVar = (zzlj) list;
        int size3 = list.size();
        for (Object obj2 : listZza) {
            if (obj2 == null) {
                String str2 = "Element at index " + (zzljVar.size() - size3) + " is null.";
                for (int size4 = zzljVar.size() - 1; size4 >= size3; size4--) {
                    zzljVar.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            if (obj2 instanceof zziv) {
                zzljVar.zzb();
            } else if (obj2 instanceof byte[]) {
                byte[] bArr2 = (byte[]) obj2;
                zziv.zzk(bArr2, 0, bArr2.length);
                zzljVar.zzb();
            } else {
                zzljVar.add((String) obj2);
            }
        }
    }

    int zza(zzmk zzmkVar) {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzlx
    public final zziv zzb() {
        try {
            int iZzn = zzn();
            zziv zzivVar = zziv.zzb;
            byte[] bArr = new byte[iZzn];
            zzjg zzjgVarZzz = zzjg.zzz(bArr, 0, iZzn);
            zze(zzjgVarZzz);
            zzjgVarZzz.zzA();
            return new zziu(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzd() {
        try {
            int iZzn = zzn();
            byte[] bArr = new byte[iZzn];
            zzjg zzjgVarZzz = zzjg.zzz(bArr, 0, iZzn);
            zze(zzjgVarZzz);
            zzjgVarZzz.zzA();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
