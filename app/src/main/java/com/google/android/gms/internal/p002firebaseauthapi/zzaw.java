package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlin.UShort;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaw<K, V> extends zzap<K, V> {
    private static final zzap<Object, Object> zza = new zzaw(null, new Object[0], 0);

    @CheckForNull
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    final zzak<V> zza() {
        return new zzba(this.zzc, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    final zzau<Map.Entry<K, V>> zzb() {
        return new zzav(this, this.zzc, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    final zzau<K> zzc() {
        return new zzax(this, new zzba(this.zzc, 0, this.zzd));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v10 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r16v9 */
    static <K, V> zzaw<K, V> zza(int i, Object[] objArr, zzas<K, V> zzasVar) {
        int iHighestOneBit;
        int i2;
        char c;
        char c2;
        boolean z;
        int i3;
        ?? r16;
        int i4 = i;
        Object[] objArrCopyOf = objArr;
        if (i4 == 0) {
            return (zzaw) zza;
        }
        zzar zzarVar = null;
        Object obj = null;
        zzar zzarVar2 = null;
        zzar zzarVar3 = null;
        boolean z2 = false;
        int i5 = 1;
        if (i4 == 1) {
            zzai.zza(Objects.requireNonNull(objArrCopyOf[0]), Objects.requireNonNull(objArrCopyOf[1]));
            return new zzaw<>(null, objArrCopyOf, 1);
        }
        zzy.zzb(i4, objArrCopyOf.length >> 1);
        char c3 = 2;
        int iMax = Math.max(i4, 2);
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
            while (((double) iHighestOneBit) * 0.7d < iMax) {
                iHighestOneBit <<= 1;
            }
        } else {
            iHighestOneBit = 1073741824;
            if (!(iMax < 1073741824)) {
                throw new IllegalArgumentException(String.valueOf("collection too large"));
            }
        }
        if (i4 == 1) {
            zzai.zza(Objects.requireNonNull(objArrCopyOf[0]), Objects.requireNonNull(objArrCopyOf[1]));
            r16 = 0;
            i2 = 1;
            c = 2;
        } else {
            int i6 = iHighestOneBit - 1;
            if (iHighestOneBit <= 128) {
                byte[] bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                int i7 = 0;
                int i8 = 0;
                while (i7 < i4) {
                    int i9 = i7 * 2;
                    int i10 = i8 * 2;
                    Object objRequireNonNull = Objects.requireNonNull(objArrCopyOf[i9]);
                    Object objRequireNonNull2 = Objects.requireNonNull(objArrCopyOf[i9 ^ i5]);
                    zzai.zza(objRequireNonNull, objRequireNonNull2);
                    int iZza = zzah.zza(objRequireNonNull.hashCode());
                    while (true) {
                        int i11 = iZza & i6;
                        z = z2;
                        i3 = i5;
                        int i12 = bArr[i11] & 255;
                        if (i12 == 255) {
                            bArr[i11] = (byte) i10;
                            if (i8 < i7) {
                                objArrCopyOf[i10] = objRequireNonNull;
                                objArrCopyOf[i10 ^ 1] = objRequireNonNull2;
                            }
                            i8++;
                            break;
                        }
                        if (objRequireNonNull.equals(objArrCopyOf[i12 == true ? 1 : 0])) {
                            int i13 = ~i12;
                            zzarVar2 = new zzar(objRequireNonNull, objRequireNonNull2, Objects.requireNonNull(objArrCopyOf[i13 == true ? 1 : 0]));
                            objArrCopyOf[i13 == true ? 1 : 0] = objRequireNonNull2;
                            break;
                        }
                        iZza = i11 + 1;
                        z2 = z;
                        i5 = i3;
                    }
                    i7++;
                    z2 = z;
                    i5 = i3;
                }
                boolean z3 = z2;
                i2 = i5;
                if (i8 == i4) {
                    c = 2;
                    obj = bArr;
                    r16 = z3;
                } else {
                    Object[] objArr2 = new Object[3];
                    objArr2[z3 ? 1 : 0] = bArr;
                    objArr2[i2] = Integer.valueOf(i8);
                    objArr2[2] = zzarVar2;
                    obj = objArr2;
                    c = 2;
                    r16 = z3;
                }
            } else {
                boolean z4 = false;
                i2 = 1;
                if (iHighestOneBit <= 32768) {
                    short[] sArr = new short[iHighestOneBit];
                    Arrays.fill(sArr, (short) -1);
                    int i14 = 0;
                    for (int i15 = 0; i15 < i4; i15++) {
                        int i16 = i15 * 2;
                        int i17 = i14 * 2;
                        Object objRequireNonNull3 = Objects.requireNonNull(objArrCopyOf[i16]);
                        Object objRequireNonNull4 = Objects.requireNonNull(objArrCopyOf[i16 ^ 1]);
                        zzai.zza(objRequireNonNull3, objRequireNonNull4);
                        int iZza2 = zzah.zza(objRequireNonNull3.hashCode());
                        while (true) {
                            int i18 = iZza2 & i6;
                            int i19 = sArr[i18] & UShort.MAX_VALUE;
                            if (i19 == 65535) {
                                sArr[i18] = (short) i17;
                                if (i14 < i15) {
                                    objArrCopyOf[i17] = objRequireNonNull3;
                                    objArrCopyOf[i17 ^ 1] = objRequireNonNull4;
                                }
                                i14++;
                                break;
                            }
                            if (objRequireNonNull3.equals(objArrCopyOf[i19 == true ? 1 : 0])) {
                                int i20 = ~i19;
                                zzarVar3 = new zzar(objRequireNonNull3, objRequireNonNull4, Objects.requireNonNull(objArrCopyOf[i20 == true ? 1 : 0]));
                                objArrCopyOf[i20 == true ? 1 : 0] = objRequireNonNull4;
                                break;
                            }
                            iZza2 = i18 + 1;
                        }
                    }
                    obj = i14 == i4 ? sArr : new Object[]{sArr, Integer.valueOf(i14), zzarVar3};
                    c = 2;
                    r16 = z4;
                } else {
                    int[] iArr = new int[iHighestOneBit];
                    Arrays.fill(iArr, -1);
                    int i21 = 0;
                    int i22 = 0;
                    while (i21 < i4) {
                        int i23 = i21 * 2;
                        int i24 = i22 * 2;
                        Object objRequireNonNull5 = Objects.requireNonNull(objArrCopyOf[i23]);
                        Object objRequireNonNull6 = Objects.requireNonNull(objArrCopyOf[i23 ^ 1]);
                        zzai.zza(objRequireNonNull5, objRequireNonNull6);
                        int iZza3 = zzah.zza(objRequireNonNull5.hashCode());
                        while (true) {
                            int i25 = iZza3 & i6;
                            int i26 = iArr[i25];
                            if (i26 == -1) {
                                iArr[i25] = i24;
                                if (i22 < i21) {
                                    objArrCopyOf[i24] = objRequireNonNull5;
                                    objArrCopyOf[i24 ^ 1] = objRequireNonNull6;
                                }
                                i22++;
                                c2 = c3;
                                break;
                            }
                            c2 = c3;
                            if (objRequireNonNull5.equals(objArrCopyOf[i26])) {
                                int i27 = i26 ^ 1;
                                zzarVar = new zzar(objRequireNonNull5, objRequireNonNull6, Objects.requireNonNull(objArrCopyOf[i27]));
                                objArrCopyOf[i27] = objRequireNonNull6;
                                break;
                            }
                            iZza3 = i25 + 1;
                            c3 = c2;
                        }
                        i21++;
                        c3 = c2;
                    }
                    c = c3;
                    if (i22 == i4) {
                        obj = iArr;
                        r16 = z4;
                    } else {
                        Object[] objArr3 = new Object[3];
                        objArr3[0] = iArr;
                        objArr3[1] = Integer.valueOf(i22);
                        objArr3[c] = zzarVar;
                        obj = objArr3;
                        r16 = z4;
                    }
                }
            }
        }
        boolean z5 = obj instanceof Object[];
        Object obj2 = obj;
        if (z5) {
            Object[] objArr4 = (Object[]) obj;
            zzar zzarVar4 = (zzar) objArr4[c];
            if (zzasVar == null) {
                throw zzarVar4.zza();
            }
            zzasVar.zza = zzarVar4;
            Object obj3 = objArr4[r16];
            int iIntValue = ((Integer) objArr4[i2]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue << 1);
            obj2 = obj3;
            i4 = iIntValue;
        }
        return new zzaw<>(obj2, objArrCopyOf, i4);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap, java.util.Map
    @CheckForNull
    public final V get(@CheckForNull Object obj) {
        V v;
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i = this.zzd;
        if (obj == null) {
            v = null;
        } else if (i == 1) {
            if (Objects.requireNonNull(objArr[0]).equals(obj)) {
                v = (V) Objects.requireNonNull(objArr[1]);
            } else {
                v = null;
            }
        } else if (obj2 == null) {
            v = null;
        } else if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int iZza = zzah.zza(obj.hashCode());
            while (true) {
                int i2 = iZza & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i3])) {
                    v = (V) objArr[i3 ^ 1];
                    break;
                }
                iZza = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iZza2 = zzah.zza(obj.hashCode());
            while (true) {
                int i4 = iZza2 & length2;
                int i5 = sArr[i4] & UShort.MAX_VALUE;
                if (i5 == 65535) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i5])) {
                    v = (V) objArr[i5 ^ 1];
                    break;
                }
                iZza2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iZza3 = zzah.zza(obj.hashCode());
            while (true) {
                int i6 = iZza3 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i7])) {
                    v = (V) objArr[i7 ^ 1];
                    break;
                }
                iZza3 = i6 + 1;
            }
        }
        if (v == null) {
            return null;
        }
        return v;
    }

    private zzaw(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    final boolean zzd() {
        return false;
    }
}
