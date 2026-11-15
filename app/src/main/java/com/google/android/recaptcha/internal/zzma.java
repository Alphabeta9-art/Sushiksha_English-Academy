package com.google.android.recaptcha.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.soloader.Elf64_Ehdr;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzma<T> implements zzmk<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzni.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlx zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zznb zzm;
    private final zzke zzn;

    private zzma(int[] iArr, Object[] objArr, int i, int i2, zzlx zzlxVar, int i3, boolean z, int[] iArr2, int i4, int i5, zzmd zzmdVar, zzlk zzlkVar, zznb zznbVar, zzke zzkeVar, zzls zzlsVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzlxVar instanceof zzks;
        boolean z2 = false;
        if (zzkeVar != null && zzkeVar.zzj(zzlxVar)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzm = zznbVar;
        this.zzn = zzkeVar;
        this.zzg = zzlxVar;
    }

    private final Object zzA(Object obj, int i) {
        zzmk zzmkVarZzx = zzx(i);
        int iZzu = zzu(i) & 1048575;
        if (!zzN(obj, i)) {
            return zzmkVarZzx.zze();
        }
        Object object = zzb.getObject(obj, iZzu);
        if (zzQ(object)) {
            return object;
        }
        Object objZze = zzmkVarZzx.zze();
        if (object != null) {
            zzmkVarZzx.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzB(Object obj, int i, int i2) {
        zzmk zzmkVarZzx = zzx(i2);
        if (!zzR(obj, i, i2)) {
            return zzmkVarZzx.zze();
        }
        Object object = zzb.getObject(obj, zzu(i2) & 1048575);
        if (zzQ(object)) {
            return object;
        }
        Object objZze = zzmkVarZzx.zze();
        if (object != null) {
            zzmkVarZzx.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzD(Object obj) {
        if (!zzQ(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzE(Object obj, Object obj2, int i) {
        if (zzN(obj2, i)) {
            long jZzu = zzu(i) & 1048575;
            Object object = zzb.getObject(obj2, jZzu);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzmk zzmkVarZzx = zzx(i);
            if (!zzN(obj, i)) {
                if (zzQ(object)) {
                    Object objZze = zzmkVarZzx.zze();
                    zzmkVarZzx.zzg(objZze, object);
                    zzb.putObject(obj, jZzu, objZze);
                } else {
                    zzb.putObject(obj, jZzu, object);
                }
                zzH(obj, i);
                return;
            }
            Object object2 = zzb.getObject(obj, jZzu);
            if (!zzQ(object2)) {
                Object objZze2 = zzmkVarZzx.zze();
                zzmkVarZzx.zzg(objZze2, object2);
                zzb.putObject(obj, jZzu, objZze2);
                object2 = objZze2;
            }
            zzmkVarZzx.zzg(object2, object);
        }
    }

    private final void zzF(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzR(obj2, i2, i)) {
            long jZzu = zzu(i) & 1048575;
            Object object = zzb.getObject(obj2, jZzu);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzmk zzmkVarZzx = zzx(i);
            if (!zzR(obj, i2, i)) {
                if (zzQ(object)) {
                    Object objZze = zzmkVarZzx.zze();
                    zzmkVarZzx.zzg(objZze, object);
                    zzb.putObject(obj, jZzu, objZze);
                } else {
                    zzb.putObject(obj, jZzu, object);
                }
                zzI(obj, i2, i);
                return;
            }
            Object object2 = zzb.getObject(obj, jZzu);
            if (!zzQ(object2)) {
                Object objZze2 = zzmkVarZzx.zze();
                zzmkVarZzx.zzg(objZze2, object2);
                zzb.putObject(obj, jZzu, objZze2);
                object2 = objZze2;
            }
            zzmkVarZzx.zzg(object2, object);
        }
    }

    private final void zzG(Object obj, int i, zzmj zzmjVar) throws IOException {
        long j = i & 1048575;
        if (zzM(i)) {
            zzni.zzs(obj, j, zzmjVar.zzs());
        } else if (this.zzi) {
            zzni.zzs(obj, j, zzmjVar.zzr());
        } else {
            zzni.zzs(obj, j, zzmjVar.zzp());
        }
    }

    private final void zzH(Object obj, int i) {
        int iZzr = zzr(i);
        long j = 1048575 & iZzr;
        if (j == 1048575) {
            return;
        }
        zzni.zzq(obj, j, zzni.zzc(obj, j) | (1 << (iZzr >>> 20)));
    }

    private final void zzI(Object obj, int i, int i2) {
        zzni.zzq(obj, zzr(i2) & 1048575, i);
    }

    private final void zzJ(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzu(i) & 1048575, obj2);
        zzH(obj, i);
    }

    private final void zzK(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzu(i2) & 1048575, obj2);
        zzI(obj, i, i2);
    }

    private final boolean zzL(Object obj, Object obj2, int i) {
        return zzN(obj, i) == zzN(obj2, i);
    }

    private static boolean zzM(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzN(Object obj, int i) {
        int iZzr = zzr(i);
        long j = iZzr & 1048575;
        if (j != 1048575) {
            return (zzni.zzc(obj, j) & (1 << (iZzr >>> 20))) != 0;
        }
        int iZzu = zzu(i);
        long j2 = 1048575 & iZzu;
        switch (zzt(iZzu)) {
            case 0:
                return Double.doubleToRawLongBits(zzni.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzni.zzb(obj, j2)) != 0;
            case 2:
                return zzni.zzd(obj, j2) != 0;
            case 3:
                return zzni.zzd(obj, j2) != 0;
            case 4:
                return zzni.zzc(obj, j2) != 0;
            case 5:
                return zzni.zzd(obj, j2) != 0;
            case 6:
                return zzni.zzc(obj, j2) != 0;
            case 7:
                return zzni.zzw(obj, j2);
            case 8:
                Object objZzf = zzni.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zziv) {
                    return !zziv.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzni.zzf(obj, j2) != null;
            case 10:
                return !zziv.zzb.equals(zzni.zzf(obj, j2));
            case 11:
                return zzni.zzc(obj, j2) != 0;
            case 12:
                return zzni.zzc(obj, j2) != 0;
            case 13:
                return zzni.zzc(obj, j2) != 0;
            case 14:
                return zzni.zzd(obj, j2) != 0;
            case 15:
                return zzni.zzc(obj, j2) != 0;
            case 16:
                return zzni.zzd(obj, j2) != 0;
            case 17:
                return zzni.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzO(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzN(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzP(Object obj, int i, zzmk zzmkVar) {
        return zzmkVar.zzl(zzni.zzf(obj, i & 1048575));
    }

    private static boolean zzQ(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzks) {
            return ((zzks) obj).zzI();
        }
        return true;
    }

    private final boolean zzR(Object obj, int i, int i2) {
        return zzni.zzc(obj, (long) (zzr(i2) & 1048575)) == i;
    }

    private static boolean zzS(Object obj, long j) {
        return ((Boolean) zzni.zzf(obj, j)).booleanValue();
    }

    private static final void zzT(int i, Object obj, zzno zznoVar) throws IOException {
        if (obj instanceof String) {
            zznoVar.zzG(i, (String) obj);
        } else {
            zznoVar.zzd(i, (zziv) obj);
        }
    }

    static zznc zzd(Object obj) {
        zzks zzksVar = (zzks) obj;
        zznc zzncVar = zzksVar.zzc;
        if (zzncVar != zznc.zzc()) {
            return zzncVar;
        }
        zznc zzncVarZzf = zznc.zzf();
        zzksVar.zzc = zzncVarZzf;
        return zzncVarZzf;
    }

    static zzma zzm(Class cls, zzlu zzluVar, zzmd zzmdVar, zzlk zzlkVar, zznb zznbVar, zzke zzkeVar, zzls zzlsVar) {
        int i;
        int iCharAt;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int i6;
        int i7;
        int i8;
        char cCharAt;
        int i9;
        char cCharAt2;
        int i10;
        char cCharAt3;
        int i11;
        char cCharAt4;
        int i12;
        char cCharAt5;
        int i13;
        char cCharAt6;
        int i14;
        char cCharAt7;
        int i15;
        char cCharAt8;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int iObjectFieldOffset;
        int iObjectFieldOffset2;
        Field fieldZzC;
        char cCharAt9;
        int i24;
        Field fieldZzC2;
        Field fieldZzC3;
        int i25;
        char cCharAt10;
        int i26;
        char cCharAt11;
        int i27;
        char cCharAt12;
        int i28;
        char cCharAt13;
        if (!(zzluVar instanceof zzmi)) {
            throw null;
        }
        zzmi zzmiVar = (zzmi) zzluVar;
        String strZzd = zzmiVar.zzd();
        int length = strZzd.length();
        char c = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i29 = 1;
            while (true) {
                i = i29 + 1;
                if (strZzd.charAt(i29) < 55296) {
                    break;
                }
                i29 = i;
            }
        } else {
            i = 1;
        }
        int i30 = i + 1;
        int iCharAt2 = strZzd.charAt(i);
        if (iCharAt2 >= 55296) {
            int i31 = iCharAt2 & 8191;
            int i32 = 13;
            while (true) {
                i28 = i30 + 1;
                cCharAt13 = strZzd.charAt(i30);
                if (cCharAt13 < 55296) {
                    break;
                }
                i31 |= (cCharAt13 & 8191) << i32;
                i32 += 13;
                i30 = i28;
            }
            iCharAt2 = i31 | (cCharAt13 << i32);
            i30 = i28;
        }
        if (iCharAt2 == 0) {
            i4 = 0;
            iCharAt = 0;
            i3 = 0;
            i5 = 0;
            i2 = 0;
            i6 = 0;
            iArr = zza;
            i7 = 0;
        } else {
            int i33 = i30 + 1;
            int iCharAt3 = strZzd.charAt(i30);
            if (iCharAt3 >= 55296) {
                int i34 = iCharAt3 & 8191;
                int i35 = 13;
                while (true) {
                    i15 = i33 + 1;
                    cCharAt8 = strZzd.charAt(i33);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i34 |= (cCharAt8 & 8191) << i35;
                    i35 += 13;
                    i33 = i15;
                }
                iCharAt3 = i34 | (cCharAt8 << i35);
                i33 = i15;
            }
            int i36 = i33 + 1;
            int iCharAt4 = strZzd.charAt(i33);
            if (iCharAt4 >= 55296) {
                int i37 = iCharAt4 & 8191;
                int i38 = 13;
                while (true) {
                    i14 = i36 + 1;
                    cCharAt7 = strZzd.charAt(i36);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i37 |= (cCharAt7 & 8191) << i38;
                    i38 += 13;
                    i36 = i14;
                }
                iCharAt4 = i37 | (cCharAt7 << i38);
                i36 = i14;
            }
            int i39 = i36 + 1;
            int iCharAt5 = strZzd.charAt(i36);
            if (iCharAt5 >= 55296) {
                int i40 = iCharAt5 & 8191;
                int i41 = 13;
                while (true) {
                    i13 = i39 + 1;
                    cCharAt6 = strZzd.charAt(i39);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i40 |= (cCharAt6 & 8191) << i41;
                    i41 += 13;
                    i39 = i13;
                }
                iCharAt5 = i40 | (cCharAt6 << i41);
                i39 = i13;
            }
            int i42 = i39 + 1;
            int iCharAt6 = strZzd.charAt(i39);
            if (iCharAt6 >= 55296) {
                int i43 = iCharAt6 & 8191;
                int i44 = 13;
                while (true) {
                    i12 = i42 + 1;
                    cCharAt5 = strZzd.charAt(i42);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt5 & 8191) << i44;
                    i44 += 13;
                    i42 = i12;
                }
                iCharAt6 = i43 | (cCharAt5 << i44);
                i42 = i12;
            }
            int i45 = i42 + 1;
            iCharAt = strZzd.charAt(i42);
            if (iCharAt >= 55296) {
                int i46 = iCharAt & 8191;
                int i47 = 13;
                while (true) {
                    i11 = i45 + 1;
                    cCharAt4 = strZzd.charAt(i45);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt4 & 8191) << i47;
                    i47 += 13;
                    i45 = i11;
                }
                iCharAt = i46 | (cCharAt4 << i47);
                i45 = i11;
            }
            int i48 = i45 + 1;
            int iCharAt7 = strZzd.charAt(i45);
            if (iCharAt7 >= 55296) {
                int i49 = iCharAt7 & 8191;
                int i50 = 13;
                while (true) {
                    i10 = i48 + 1;
                    cCharAt3 = strZzd.charAt(i48);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt3 & 8191) << i50;
                    i50 += 13;
                    i48 = i10;
                }
                iCharAt7 = i49 | (cCharAt3 << i50);
                i48 = i10;
            }
            int i51 = i48 + 1;
            int iCharAt8 = strZzd.charAt(i48);
            if (iCharAt8 >= 55296) {
                int i52 = iCharAt8 & 8191;
                int i53 = 13;
                while (true) {
                    i9 = i51 + 1;
                    cCharAt2 = strZzd.charAt(i51);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt2 & 8191) << i53;
                    i53 += 13;
                    i51 = i9;
                }
                iCharAt8 = i52 | (cCharAt2 << i53);
                i51 = i9;
            }
            int i54 = i51 + 1;
            int iCharAt9 = strZzd.charAt(i51);
            if (iCharAt9 >= 55296) {
                int i55 = iCharAt9 & 8191;
                int i56 = 13;
                while (true) {
                    i8 = i54 + 1;
                    cCharAt = strZzd.charAt(i54);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i55 |= (cCharAt & 8191) << i56;
                    i56 += 13;
                    i54 = i8;
                }
                iCharAt9 = i55 | (cCharAt << i56);
                i54 = i8;
            }
            i2 = iCharAt3 + iCharAt3 + iCharAt4;
            int[] iArr2 = new int[iCharAt9 + iCharAt7 + iCharAt8];
            int i57 = iCharAt7;
            i3 = iCharAt5;
            i4 = i57;
            iArr = iArr2;
            i5 = iCharAt6;
            i6 = iCharAt9;
            i7 = iCharAt3;
            i30 = i54;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzmiVar.zze();
        Class<?> cls2 = zzmiVar.zza().getClass();
        int i58 = i6 + i4;
        int i59 = iCharAt + iCharAt;
        int[] iArr3 = new int[iCharAt * 3];
        Object[] objArr = new Object[i59];
        int i60 = 0;
        int i61 = 0;
        int i62 = i6;
        int i63 = i58;
        while (i30 < length) {
            int i64 = i30 + 1;
            int iCharAt10 = strZzd.charAt(i30);
            if (iCharAt10 >= c) {
                int i65 = iCharAt10 & 8191;
                int i66 = i64;
                int i67 = 13;
                while (true) {
                    i27 = i66 + 1;
                    cCharAt12 = strZzd.charAt(i66);
                    if (cCharAt12 < c) {
                        break;
                    }
                    i65 |= (cCharAt12 & 8191) << i67;
                    i67 += 13;
                    i66 = i27;
                }
                iCharAt10 = i65 | (cCharAt12 << i67);
                i16 = i27;
            } else {
                i16 = i64;
            }
            int i68 = i16 + 1;
            int iCharAt11 = strZzd.charAt(i16);
            if (iCharAt11 >= c) {
                int i69 = iCharAt11 & 8191;
                int i70 = i68;
                int i71 = 13;
                while (true) {
                    i26 = i70 + 1;
                    cCharAt11 = strZzd.charAt(i70);
                    if (cCharAt11 < c) {
                        break;
                    }
                    i69 |= (cCharAt11 & 8191) << i71;
                    i71 += 13;
                    i70 = i26;
                }
                iCharAt11 = i69 | (cCharAt11 << i71);
                i17 = i26;
            } else {
                i17 = i68;
            }
            if ((iCharAt11 & 1024) != 0) {
                iArr[i60] = i61;
                i60++;
            }
            int i72 = iCharAt11 & 255;
            int i73 = iCharAt11 & 2048;
            zzmi zzmiVar2 = zzmiVar;
            if (i72 >= 51) {
                int i74 = i17 + 1;
                int iCharAt12 = strZzd.charAt(i17);
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i75 = iCharAt12 & 8191;
                    int i76 = i74;
                    int i77 = 13;
                    while (true) {
                        i25 = i76 + 1;
                        cCharAt10 = strZzd.charAt(i76);
                        if (cCharAt10 < c2) {
                            break;
                        }
                        i75 |= (cCharAt10 & 8191) << i77;
                        i77 += 13;
                        i76 = i25;
                        c2 = 55296;
                    }
                    iCharAt12 = i75 | (cCharAt10 << i77);
                    i24 = i25;
                } else {
                    i24 = i74;
                }
                int i78 = i24;
                int i79 = i72 - 51;
                i18 = length;
                if (i79 == 9 || i79 == 17) {
                    int i80 = i61 / 3;
                    objArr[i80 + i80 + 1] = objArrZze[i2];
                    i2++;
                } else if (i79 == 12) {
                    if (zzmiVar2.zzc() == 1 || i73 != 0) {
                        int i81 = i61 / 3;
                        objArr[i81 + i81 + 1] = objArrZze[i2];
                        i2++;
                    } else {
                        i73 = 0;
                    }
                }
                int i82 = iCharAt12 + iCharAt12;
                Object obj = objArrZze[i82];
                if (obj instanceof Field) {
                    fieldZzC2 = (Field) obj;
                } else {
                    fieldZzC2 = zzC(cls2, (String) obj);
                    objArrZze[i82] = fieldZzC2;
                }
                int i83 = iCharAt10;
                int i84 = i73;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzC2);
                int i85 = i82 + 1;
                Object obj2 = objArrZze[i85];
                if (obj2 instanceof Field) {
                    fieldZzC3 = (Field) obj2;
                } else {
                    fieldZzC3 = zzC(cls2, (String) obj2);
                    objArrZze[i85] = fieldZzC3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzC3);
                strZzd = strZzd;
                i21 = i78;
                i23 = i84;
                i22 = 0;
                i19 = i83;
            } else {
                i18 = length;
                i19 = iCharAt10;
                int i86 = i2 + 1;
                Field fieldZzC4 = zzC(cls2, (String) objArrZze[i2]);
                if (i72 == 9 || i72 == 17) {
                    int i87 = i61 / 3;
                    objArr[i87 + i87 + 1] = fieldZzC4.getType();
                    i20 = i86;
                } else if (i72 == 27 || i72 == 49) {
                    i20 = i86 + 1;
                    int i88 = i61 / 3;
                    objArr[i88 + i88 + 1] = objArrZze[i86];
                } else if (i72 == 12 || i72 == 30 || i72 == 44) {
                    i19 = i19;
                    if (zzmiVar2.zzc() == 1 || i73 != 0) {
                        i20 = i86 + 1;
                        int i89 = i61 / 3;
                        objArr[i89 + i89 + 1] = objArrZze[i86];
                    } else {
                        i20 = i86;
                        i73 = 0;
                    }
                } else if (i72 == 50) {
                    i20 = i86 + 1;
                    i62++;
                    iArr[i62] = i61;
                    int i90 = i61 / 3;
                    int i91 = i90 + i90;
                    objArr[i91] = objArrZze[i86];
                    if (i73 != 0) {
                        objArr[i91 + 1] = objArrZze[i20];
                        i20++;
                    } else {
                        i73 = 0;
                    }
                    i19 = i19;
                } else {
                    i19 = i19;
                    i20 = i86;
                }
                int i92 = i73;
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzC4);
                int iObjectFieldOffset4 = 1048575;
                if ((iCharAt11 & 4096) == 0 || i72 > 17) {
                    i21 = i17;
                    i22 = 0;
                } else {
                    int i93 = i17 + 1;
                    int iCharAt13 = strZzd.charAt(i17);
                    if (iCharAt13 >= 55296) {
                        int i94 = iCharAt13 & 8191;
                        int i95 = 13;
                        while (true) {
                            i21 = i93 + 1;
                            cCharAt9 = strZzd.charAt(i93);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i94 |= (cCharAt9 & 8191) << i95;
                            i95 += 13;
                            i93 = i21;
                        }
                        iCharAt13 = i94 | (cCharAt9 << i95);
                    } else {
                        i21 = i93;
                    }
                    int i96 = i7 + i7 + (iCharAt13 / 32);
                    Object obj3 = objArrZze[i96];
                    if (obj3 instanceof Field) {
                        fieldZzC = (Field) obj3;
                    } else {
                        fieldZzC = zzC(cls2, (String) obj3);
                        objArrZze[i96] = fieldZzC;
                    }
                    i22 = iCharAt13 % 32;
                    iObjectFieldOffset4 = (int) unsafe.objectFieldOffset(fieldZzC);
                }
                if (i72 < 18 || i72 > 49) {
                    i23 = i92;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    iObjectFieldOffset2 = iObjectFieldOffset4;
                    i2 = i20;
                } else {
                    iArr[i63] = iObjectFieldOffset3;
                    i63++;
                    i23 = i92;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    iObjectFieldOffset2 = iObjectFieldOffset4;
                    i2 = i20;
                }
            }
            int i97 = i61 + 1;
            iArr3[i61] = i19;
            int i98 = i97 + 1;
            iArr3[i97] = ((iCharAt11 & 512) != 0 ? 536870912 : 0) | ((iCharAt11 & 256) != 0 ? 268435456 : 0) | (i23 != 0 ? Integer.MIN_VALUE : 0) | (i72 << 20) | iObjectFieldOffset;
            i61 = i98 + 1;
            iArr3[i98] = (i22 << 20) | iObjectFieldOffset2;
            i30 = i21;
            zzmiVar = zzmiVar2;
            length = i18;
            strZzd = strZzd;
            c = 55296;
        }
        zzmi zzmiVar3 = zzmiVar;
        return new zzma(iArr3, objArr, i3, i5, zzmiVar3.zza(), zzmiVar3.zzc(), false, iArr, i6, i58, zzmdVar, zzlkVar, zznbVar, zzkeVar, zzlsVar);
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzni.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzni.zzf(obj, j)).floatValue();
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zzni.zzf(obj, j)).intValue();
    }

    private final int zzq(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzs(i, 0);
    }

    private final int zzr(int i) {
        return this.zzc[i + 2];
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzt(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzu(int i) {
        return this.zzc[i + 1];
    }

    private static long zzv(Object obj, long j) {
        return ((Long) zzni.zzf(obj, j)).longValue();
    }

    private final zzkw zzw(int i) {
        int i2 = i / 3;
        return (zzkw) this.zzd[i2 + i2 + 1];
    }

    private final zzmk zzx(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzmk zzmkVar = (zzmk) objArr[i3];
        if (zzmkVar != null) {
            return zzmkVar;
        }
        zzmk zzmkVarZzb = zzmg.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzmkVarZzb;
        return zzmkVarZzb;
    }

    private final Object zzy(Object obj, int i, Object obj2, zznb zznbVar, Object obj3) {
        int i2 = this.zzc[i];
        Object objZzf = zzni.zzf(obj, zzu(i) & 1048575);
        if (objZzf == null || zzw(i) == null) {
            return obj2;
        }
        throw null;
    }

    private final Object zzz(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final int zza(Object obj) {
        int i;
        int iZzx;
        int iZzx2;
        int iZzx3;
        int iZzt;
        zzma<T> zzmaVar = this;
        Object obj2 = obj;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int iZzx4 = 0;
        int i5 = 1048575;
        while (i3 < zzmaVar.zzc.length) {
            int iZzu = zzmaVar.zzu(i3);
            int iZzt2 = zzt(iZzu);
            int[] iArr = zzmaVar.zzc;
            int i6 = iArr[i3];
            int i7 = iArr[i3 + 2];
            int i8 = i7 & i2;
            if (iZzt2 <= 17) {
                if (i8 != i5) {
                    i4 = i8 == i2 ? 0 : unsafe.getInt(obj2, i8);
                    i5 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = iZzu & i2;
            if (iZzt2 >= zzkj.DOUBLE_LIST_PACKED.zza()) {
                zzkj.SINT64_LIST_PACKED.zza();
            }
            int i10 = iZzx4;
            long j = i9;
            switch (iZzt2) {
                case 0:
                    zzmaVar = this;
                    obj2 = obj;
                    if (zzmaVar.zzO(obj2, i3, i5, i4, i)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 1:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 2:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(unsafe.getLong(obj2, j));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 3:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(unsafe.getLong(obj2, j));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 4:
                    obj2 = obj;
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(unsafe.getInt(obj2, j));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 5:
                    if (!zzO(obj, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 6:
                    if (!zzO(obj, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 7:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 1;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 8:
                    if (!zzmaVar.zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                        break;
                    } else {
                        int i11 = i6 << 3;
                        Object object = unsafe.getObject(obj2, j);
                        if (object instanceof zziv) {
                            int iZzx5 = zzjg.zzx(i11);
                            int iZzd = ((zziv) object).zzd();
                            iZzx4 = i10 + iZzx5 + zzjg.zzx(iZzd) + iZzd;
                            zzmaVar = this;
                        } else {
                            iZzx4 = i10 + zzjg.zzx(i11) + zzjg.zzw((String) object);
                            zzmaVar = this;
                        }
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 9:
                    zzmaVar = this;
                    if (zzmaVar.zzO(obj2, i3, i5, i4, i)) {
                        iZzx4 = i10 + zzmm.zzh(i6, unsafe.getObject(obj2, j), zzmaVar.zzx(i3));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 10:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        zziv zzivVar = (zziv) unsafe.getObject(obj2, j);
                        int iZzx6 = zzjg.zzx(i6 << 3);
                        int iZzd2 = zzivVar.zzd();
                        iZzx4 = i10 + iZzx6 + zzjg.zzx(iZzd2) + iZzd2;
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 11:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(unsafe.getInt(obj2, j));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 12:
                    obj2 = obj;
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(unsafe.getInt(obj2, j));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 13:
                    if (!zzO(obj, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 14:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                        obj2 = obj;
                    } else {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                        zzmaVar = this;
                        obj2 = obj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 15:
                    if (!zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        int i12 = unsafe.getInt(obj2, j);
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx((i12 + i12) ^ (i12 >> 31));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 16:
                    if (!zzmaVar.zzO(obj2, i3, i5, i4, i)) {
                        zzmaVar = this;
                    } else {
                        long j2 = unsafe.getLong(obj2, j);
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy((j2 + j2) ^ (j2 >> 63));
                        zzmaVar = this;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 17:
                    if (zzmaVar.zzO(obj2, i3, i5, i4, i)) {
                        iZzx4 = i10 + zzjg.zzt(i6, (zzlx) unsafe.getObject(obj2, j), zzmaVar.zzx(i3));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 18:
                    iZzx4 = i10 + zzmm.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 19:
                    iZzx4 = i10 + zzmm.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj2, j);
                    int i13 = zzmm.zza;
                    iZzx4 = i10 + (list.size() == 0 ? 0 : zzmm.zzg(list) + (list.size() * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 21:
                    List list2 = (List) unsafe.getObject(obj2, j);
                    int i14 = zzmm.zza;
                    int size = list2.size();
                    iZzx4 = i10 + (size == 0 ? 0 : zzmm.zzl(list2) + (size * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 22:
                    List list3 = (List) unsafe.getObject(obj2, j);
                    int i15 = zzmm.zza;
                    int size2 = list3.size();
                    iZzx4 = i10 + (size2 == 0 ? 0 : zzmm.zzf(list3) + (size2 * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 23:
                    iZzx4 = i10 + zzmm.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 24:
                    iZzx4 = i10 + zzmm.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj2, j);
                    int i16 = zzmm.zza;
                    int size3 = list4.size();
                    iZzx4 = i10 + (size3 == 0 ? 0 : size3 * (zzjg.zzx(i6 << 3) + 1));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 26:
                    List list5 = (List) unsafe.getObject(obj2, j);
                    int i17 = zzmm.zza;
                    int size4 = list5.size();
                    if (size4 == 0) {
                        iZzx = 0;
                    } else {
                        iZzx = zzjg.zzx(i6 << 3) * size4;
                        if (list5 instanceof zzlj) {
                            zzlj zzljVar = (zzlj) list5;
                            for (int i18 = 0; i18 < size4; i18++) {
                                Object objZzc = zzljVar.zzc();
                                if (objZzc instanceof zziv) {
                                    int iZzd3 = ((zziv) objZzc).zzd();
                                    iZzx += zzjg.zzx(iZzd3) + iZzd3;
                                } else {
                                    iZzx += zzjg.zzw((String) objZzc);
                                }
                            }
                        } else {
                            for (int i19 = 0; i19 < size4; i19++) {
                                Object obj3 = list5.get(i19);
                                if (obj3 instanceof zziv) {
                                    int iZzd4 = ((zziv) obj3).zzd();
                                    iZzx += zzjg.zzx(iZzd4) + iZzd4;
                                } else {
                                    iZzx += zzjg.zzw((String) obj3);
                                }
                            }
                        }
                    }
                    iZzx4 = iZzx + i10;
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 27:
                    List list6 = (List) unsafe.getObject(obj2, j);
                    zzmk zzmkVarZzx = zzmaVar.zzx(i3);
                    int i20 = zzmm.zza;
                    int size5 = list6.size();
                    if (size5 == 0) {
                        iZzx2 = 0;
                    } else {
                        iZzx2 = zzjg.zzx(i6 << 3) * size5;
                        for (int i21 = 0; i21 < size5; i21++) {
                            Object obj4 = list6.get(i21);
                            if (obj4 instanceof zzli) {
                                int iZza = ((zzli) obj4).zza();
                                iZzx2 += zzjg.zzx(iZza) + iZza;
                            } else {
                                iZzx2 += zzjg.zzv((zzlx) obj4, zzmkVarZzx);
                            }
                        }
                    }
                    iZzx4 = i10 + iZzx2;
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 28:
                    List list7 = (List) unsafe.getObject(obj2, j);
                    int i22 = zzmm.zza;
                    int size6 = list7.size();
                    if (size6 == 0) {
                        iZzx3 = 0;
                    } else {
                        iZzx3 = size6 * zzjg.zzx(i6 << 3);
                        for (int i23 = 0; i23 < list7.size(); i23++) {
                            int iZzd5 = ((zziv) list7.get(i23)).zzd();
                            iZzx3 += zzjg.zzx(iZzd5) + iZzd5;
                        }
                    }
                    iZzx4 = i10 + iZzx3;
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 29:
                    List list8 = (List) unsafe.getObject(obj2, j);
                    int i24 = zzmm.zza;
                    int size7 = list8.size();
                    iZzx4 = i10 + (size7 == 0 ? 0 : zzmm.zzk(list8) + (size7 * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 30:
                    List list9 = (List) unsafe.getObject(obj2, j);
                    int i25 = zzmm.zza;
                    int size8 = list9.size();
                    iZzx4 = i10 + (size8 == 0 ? 0 : zzmm.zza(list9) + (size8 * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 31:
                    iZzx4 = i10 + zzmm.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 32:
                    iZzx4 = i10 + zzmm.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 33:
                    List list10 = (List) unsafe.getObject(obj2, j);
                    int i26 = zzmm.zza;
                    int size9 = list10.size();
                    iZzx4 = i10 + (size9 == 0 ? 0 : zzmm.zzi(list10) + (size9 * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 34:
                    List list11 = (List) unsafe.getObject(obj2, j);
                    int i27 = zzmm.zza;
                    int size10 = list11.size();
                    iZzx4 = i10 + (size10 == 0 ? 0 : zzmm.zzj(list11) + (size10 * zzjg.zzx(i6 << 3)));
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 35:
                    int iZze = zzmm.zze((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZze) + iZze;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 36:
                    int iZzc = zzmm.zzc((List) unsafe.getObject(obj2, j));
                    if (iZzc > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzc) + iZzc;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 37:
                    int iZzg = zzmm.zzg((List) unsafe.getObject(obj2, j));
                    if (iZzg > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzg) + iZzg;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 38:
                    int iZzl = zzmm.zzl((List) unsafe.getObject(obj2, j));
                    if (iZzl > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzl) + iZzl;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 39:
                    int iZzf = zzmm.zzf((List) unsafe.getObject(obj2, j));
                    if (iZzf > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzf) + iZzf;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 40:
                    int iZze2 = zzmm.zze((List) unsafe.getObject(obj2, j));
                    if (iZze2 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZze2) + iZze2;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 41:
                    int iZzc2 = zzmm.zzc((List) unsafe.getObject(obj2, j));
                    if (iZzc2 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzc2) + iZzc2;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 42:
                    List list12 = (List) unsafe.getObject(obj2, j);
                    int i28 = zzmm.zza;
                    int size11 = list12.size();
                    if (size11 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(size11) + size11;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 43:
                    int iZzk = zzmm.zzk((List) unsafe.getObject(obj2, j));
                    if (iZzk > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzk) + iZzk;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 44:
                    int iZza2 = zzmm.zza((List) unsafe.getObject(obj2, j));
                    if (iZza2 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZza2) + iZza2;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 45:
                    int iZzc3 = zzmm.zzc((List) unsafe.getObject(obj2, j));
                    if (iZzc3 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzc3) + iZzc3;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 46:
                    int iZze3 = zzmm.zze((List) unsafe.getObject(obj2, j));
                    if (iZze3 > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZze3) + iZze3;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 47:
                    int iZzi = zzmm.zzi((List) unsafe.getObject(obj2, j));
                    if (iZzi > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzi) + iZzi;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 48:
                    int iZzj = zzmm.zzj((List) unsafe.getObject(obj2, j));
                    if (iZzj > 0) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(iZzj) + iZzj;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    List list13 = (List) unsafe.getObject(obj2, j);
                    zzmk zzmkVarZzx2 = zzmaVar.zzx(i3);
                    int i29 = zzmm.zza;
                    int size12 = list13.size();
                    if (size12 == 0) {
                        iZzt = 0;
                    } else {
                        iZzt = 0;
                        for (int i30 = 0; i30 < size12; i30++) {
                            iZzt += zzjg.zzt(i6, (zzlx) list13.get(i30), zzmkVarZzx2);
                        }
                    }
                    iZzx4 = i10 + iZzt;
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 50:
                    zzlr zzlrVar = (zzlr) unsafe.getObject(obj2, j);
                    if (!zzlrVar.isEmpty()) {
                        Iterator it = zzlrVar.entrySet().iterator();
                        if (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            entry.getKey();
                            entry.getValue();
                            throw null;
                        }
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 52:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 53:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(zzv(obj2, j));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 54:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(zzv(obj2, j));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 55:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(zzp(obj2, j));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 56:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 57:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 1;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 59:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        int i31 = i6 << 3;
                        Object object2 = unsafe.getObject(obj2, j);
                        if (object2 instanceof zziv) {
                            int iZzx7 = zzjg.zzx(i31);
                            int iZzd6 = ((zziv) object2).zzd();
                            iZzx4 = i10 + iZzx7 + zzjg.zzx(iZzd6) + iZzd6;
                        } else {
                            iZzx4 = i10 + zzjg.zzx(i31) + zzjg.zzw((String) object2);
                        }
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 60:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzmm.zzh(i6, unsafe.getObject(obj2, j), zzmaVar.zzx(i3));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 61:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        zziv zzivVar2 = (zziv) unsafe.getObject(obj2, j);
                        int iZzx8 = zzjg.zzx(i6 << 3);
                        int iZzd7 = zzivVar2.zzd();
                        iZzx4 = i10 + iZzx8 + zzjg.zzx(iZzd7) + iZzd7;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 62:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzx(zzp(obj2, j));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 63:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy(zzp(obj2, j));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 64:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 4;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 65:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + 8;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        int iZzp = zzp(obj2, j);
                        iZzx4 = zzjg.zzx(i6 << 3) + zzjg.zzx((iZzp + iZzp) ^ (iZzp >> 31)) + i10;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 67:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        long jZzv = zzv(obj2, j);
                        iZzx4 = i10 + zzjg.zzx(i6 << 3) + zzjg.zzy((jZzv + jZzv) ^ (jZzv >> 63));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 68:
                    if (zzmaVar.zzR(obj2, i6, i3)) {
                        iZzx4 = i10 + zzjg.zzt(i6, (zzlx) unsafe.getObject(obj2, j), zzmaVar.zzx(i3));
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
            }
            iZzx4 = i10;
            i3 += 3;
            i2 = 1048575;
        }
        zznb zznbVar = zzmaVar.zzm;
        int iZza3 = iZzx4 + zznbVar.zza(zznbVar.zzd(obj2));
        if (!zzmaVar.zzh) {
            return iZza3;
        }
        zzki zzkiVarZzb = zzmaVar.zzn.zzb(obj2);
        int iZza4 = 0;
        for (int i32 = 0; i32 < zzkiVarZzb.zza.zzb(); i32++) {
            Map.Entry entryZzg = zzkiVarZzb.zza.zzg(i32);
            iZza4 += zzki.zza((zzkh) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry2 : zzkiVarZzb.zza.zzc()) {
            iZza4 += zzki.zza((zzkh) entry2.getKey(), entry2.getValue());
        }
        return iZza3 + iZza4;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final int zzb(Object obj) {
        int iFloatToIntBits = 0;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzu = zzu(i);
            int[] iArr = this.zzc;
            int i2 = 1048575 & iZzu;
            int iZzt = zzt(iZzu);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzt) {
                case 0:
                    long jDoubleToLongBits = Double.doubleToLongBits(zzni.zza(obj, j));
                    byte[] bArr = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
                    break;
                case 1:
                    iFloatToIntBits = (iFloatToIntBits * 53) + Float.floatToIntBits(zzni.zzb(obj, j));
                    break;
                case 2:
                    long jZzd = zzni.zzd(obj, j);
                    byte[] bArr2 = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd ^ (jZzd >>> 32)));
                    break;
                case 3:
                    long jZzd2 = zzni.zzd(obj, j);
                    byte[] bArr3 = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd2 ^ (jZzd2 >>> 32)));
                    break;
                case 4:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 5:
                    long jZzd3 = zzni.zzd(obj, j);
                    byte[] bArr4 = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd3 ^ (jZzd3 >>> 32)));
                    break;
                case 6:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 7:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzla.zza(zzni.zzw(obj, j));
                    break;
                case 8:
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((String) zzni.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    int i4 = iFloatToIntBits * 53;
                    Object objZzf = zzni.zzf(obj, j);
                    iFloatToIntBits = i4 + (objZzf != null ? objZzf.hashCode() : 37);
                    break;
                case 10:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    break;
                case 11:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 12:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 13:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 14:
                    long jZzd4 = zzni.zzd(obj, j);
                    byte[] bArr5 = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd4 ^ (jZzd4 >>> 32)));
                    break;
                case 15:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzc(obj, j);
                    break;
                case 16:
                    long jZzd5 = zzni.zzd(obj, j);
                    byte[] bArr6 = zzla.zzb;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd5 ^ (jZzd5 >>> 32)));
                    break;
                case 17:
                    int i5 = iFloatToIntBits * 53;
                    Object objZzf2 = zzni.zzf(obj, j);
                    iFloatToIntBits = i5 + (objZzf2 != null ? objZzf2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    break;
                case 50:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzR(obj, i3, i)) {
                        long jDoubleToLongBits2 = Double.doubleToLongBits(zzn(obj, j));
                        byte[] bArr7 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
                    }
                    break;
                case 52:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + Float.floatToIntBits(zzo(obj, j));
                    }
                    break;
                case 53:
                    if (zzR(obj, i3, i)) {
                        long jZzv = zzv(obj, j);
                        byte[] bArr8 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzv ^ (jZzv >>> 32)));
                    }
                    break;
                case 54:
                    if (zzR(obj, i3, i)) {
                        long jZzv2 = zzv(obj, j);
                        byte[] bArr9 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzv2 ^ (jZzv2 >>> 32)));
                    }
                    break;
                case 55:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case 56:
                    if (zzR(obj, i3, i)) {
                        long jZzv3 = zzv(obj, j);
                        byte[] bArr10 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzv3 ^ (jZzv3 >>> 32)));
                    }
                    break;
                case 57:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzla.zza(zzS(obj, j));
                    }
                    break;
                case 59:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((String) zzni.zzf(obj, j)).hashCode();
                    }
                    break;
                case 60:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    }
                    break;
                case 61:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    }
                    break;
                case 62:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case 63:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case 64:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case 65:
                    if (zzR(obj, i3, i)) {
                        long jZzv4 = zzv(obj, j);
                        byte[] bArr11 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzv4 ^ (jZzv4 >>> 32)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzp(obj, j);
                    }
                    break;
                case 67:
                    if (zzR(obj, i3, i)) {
                        long jZzv5 = zzv(obj, j);
                        byte[] bArr12 = zzla.zzb;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzv5 ^ (jZzv5 >>> 32)));
                    }
                    break;
                case 68:
                    if (zzR(obj, i3, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzni.zzf(obj, j).hashCode();
                    }
                    break;
            }
        }
        int iHashCode = (iFloatToIntBits * 53) + this.zzm.zzd(obj).hashCode();
        return this.zzh ? (iHashCode * 53) + this.zzn.zzb(obj).zza.hashCode() : iHashCode;
    }

    /* JADX WARN: Code duplicated, block: B:582:0x0974 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:585:0x0c91 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:631:0x0985 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:634:0x0ca3 A[SYNTHETIC] */
    final int zzc(Object obj, byte[] bArr, int i, int i2, int i3, zzik zzikVar) throws IOException {
        int i4;
        Unsafe unsafe;
        Object obj2;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        zzik zzikVar2;
        int i10;
        int i11;
        boolean z;
        int i12;
        byte[] bArr2;
        int i13;
        Object objValueOf;
        boolean z2;
        int i14;
        int i15;
        Unsafe unsafe2;
        int i16;
        int iZzg;
        int i17;
        zzik zzikVar3;
        int i18;
        int i19;
        zzkz zzkzVar;
        int i20;
        int i21;
        int i22;
        int iZzl;
        int i23;
        int i24;
        int iZzl2;
        int i25;
        int iZzk;
        int iZzi;
        int i26;
        int i27;
        zzma<T> zzmaVar = this;
        Object obj3 = obj;
        byte[] bArr3 = bArr;
        int i28 = i2;
        zzik zzikVar4 = zzikVar;
        zzD(obj3);
        Unsafe unsafe3 = zzb;
        int i29 = -1;
        int iZzh = i;
        int i30 = -1;
        int i31 = 0;
        int i32 = 0;
        int i33 = 1048575;
        int i34 = 0;
        while (true) {
            if (iZzh < i28) {
                int i35 = iZzh + 1;
                byte b = bArr3[iZzh];
                if (b < 0) {
                    int iZzj = zzil.zzj(b, bArr3, i35, zzikVar4);
                    i9 = zzikVar4.zza;
                    iZzh = iZzj;
                } else {
                    i9 = b;
                    iZzh = i35;
                }
                int i36 = i9 >>> 3;
                int iZzs = i36 > i30 ? (i36 < zzmaVar.zze || i36 > zzmaVar.zzf) ? i29 : zzmaVar.zzs(i36, i31 / 3) : zzmaVar.zzq(i36);
                if (iZzs != i29) {
                    int i37 = i9 & 7;
                    int[] iArr = zzmaVar.zzc;
                    int i38 = iArr[iZzs + 1];
                    int i39 = iZzs;
                    int iZzt = zzt(i38);
                    int i40 = i9;
                    long j = i38 & 1048575;
                    if (iZzt <= 17) {
                        int i41 = iArr[i39 + 2];
                        int i42 = 1 << (i41 >>> 20);
                        int i43 = i41 & 1048575;
                        if (i43 != i33) {
                            if (i33 != 1048575) {
                                unsafe3.putInt(obj3, i33, i34);
                            }
                            i34 = i43 == 1048575 ? 0 : unsafe3.getInt(obj3, i43);
                            i33 = i43;
                        }
                        switch (iZzt) {
                            case 0:
                                zzikVar4 = zzikVar;
                                int i44 = iZzh;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                unsafe2 = unsafe3;
                                obj2 = obj3;
                                z2 = true;
                                if (i37 != 1) {
                                    i16 = i44;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    iZzh = i44 + 8;
                                    i34 |= i42;
                                    zzni.zzo(obj2, j, Double.longBitsToDouble(zzil.zzp(bArr3, i44)));
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    obj3 = obj2;
                                    unsafe3 = unsafe2;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 1:
                                zzikVar4 = zzikVar;
                                int i45 = iZzh;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                unsafe2 = unsafe3;
                                obj2 = obj3;
                                if (i37 != 5) {
                                    i16 = i45;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    iZzh = i45 + 4;
                                    i34 |= i42;
                                    zzni.zzp(obj2, j, Float.intBitsToFloat(zzil.zzb(bArr3, i45)));
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    obj3 = obj2;
                                    unsafe3 = unsafe2;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 2:
                            case 3:
                                zzikVar4 = zzikVar;
                                int i46 = iZzh;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                if (i37 != 0) {
                                    unsafe2 = unsafe3;
                                    obj2 = obj3;
                                    i16 = i46;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    int iZzl3 = zzil.zzl(bArr3, i46, zzikVar4);
                                    unsafe3.putLong(obj3, j, zzikVar4.zzb);
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    iZzh = iZzl3;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 4:
                            case 11:
                                zzikVar4 = zzikVar;
                                int i47 = iZzh;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                if (i37 != 0) {
                                    unsafe2 = unsafe3;
                                    obj2 = obj3;
                                    i16 = i47;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    iZzh = zzil.zzi(bArr3, i47, zzikVar4);
                                    unsafe3.putInt(obj3, j, zzikVar4.zza);
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 5:
                            case 14:
                                Unsafe unsafe4 = unsafe3;
                                int i48 = iZzh;
                                byte[] bArr4 = bArr3;
                                i12 = i39;
                                i14 = i40;
                                Object obj4 = obj3;
                                i15 = i33;
                                if (i37 != 1) {
                                    unsafe2 = unsafe4;
                                    obj2 = obj4;
                                    i16 = i48;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    int i49 = i48 + 8;
                                    i34 |= i42;
                                    long jZzp = zzil.zzp(bArr4, i48);
                                    obj3 = obj4;
                                    bArr3 = bArr4;
                                    unsafe3 = unsafe4;
                                    zzikVar4 = zzikVar;
                                    unsafe3.putLong(obj3, j, jZzp);
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    iZzh = i49;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 6:
                            case 13:
                                Unsafe unsafe5 = unsafe3;
                                int i50 = iZzh;
                                byte[] bArr5 = bArr3;
                                i12 = i39;
                                i14 = i40;
                                Object obj5 = obj3;
                                i15 = i33;
                                if (i37 != 5) {
                                    obj2 = obj5;
                                    unsafe2 = unsafe5;
                                    z2 = true;
                                    i16 = i50;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    unsafe5.putInt(obj5, j, zzil.zzb(bArr5, i50));
                                    bArr3 = bArr5;
                                    unsafe3 = unsafe5;
                                    zzikVar4 = zzikVar;
                                    iZzh = i50 + 4;
                                    obj3 = obj5;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 7:
                                Unsafe unsafe6 = unsafe3;
                                int i51 = iZzh;
                                byte[] bArr6 = bArr3;
                                i12 = i39;
                                i14 = i40;
                                Object obj6 = obj3;
                                i15 = i33;
                                if (i37 != 0) {
                                    obj2 = obj6;
                                    unsafe2 = unsafe6;
                                    z2 = true;
                                    i16 = i51;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    int iZzl4 = zzil.zzl(bArr6, i51, zzikVar);
                                    zzni.zzm(obj6, j, zzikVar.zzb != 0);
                                    bArr3 = bArr6;
                                    unsafe3 = unsafe6;
                                    zzikVar4 = zzikVar;
                                    iZzh = iZzl4;
                                    obj3 = obj6;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 8:
                                Unsafe unsafe7 = unsafe3;
                                int i52 = iZzh;
                                byte[] bArr7 = bArr3;
                                i12 = i39;
                                i14 = i40;
                                Object obj7 = obj3;
                                i15 = i33;
                                if (i37 != 2) {
                                    obj2 = obj7;
                                    unsafe2 = unsafe7;
                                    z2 = true;
                                    i16 = i52;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    if (zzM(i38)) {
                                        iZzg = zzil.zzi(bArr7, i52, zzikVar);
                                        int i53 = zzikVar.zza;
                                        if (i53 < 0) {
                                            throw zzlc.zzf();
                                        }
                                        i34 |= i42;
                                        if (i53 == 0) {
                                            zzikVar.zzc = "";
                                        } else {
                                            zzikVar.zzc = zznl.zzd(bArr7, iZzg, i53);
                                            iZzg += i53;
                                        }
                                    } else {
                                        i34 |= i42;
                                        iZzg = zzil.zzg(bArr7, i52, zzikVar);
                                    }
                                    unsafe7.putObject(obj7, j, zzikVar.zzc);
                                    bArr3 = bArr7;
                                    unsafe3 = unsafe7;
                                    zzikVar4 = zzikVar;
                                    iZzh = iZzg;
                                    obj3 = obj7;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 9:
                                Object obj8 = obj3;
                                Unsafe unsafe8 = unsafe3;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                if (i37 != 2) {
                                    int i54 = iZzh;
                                    obj2 = obj8;
                                    unsafe2 = unsafe8;
                                    z2 = true;
                                    i16 = i54;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    Object objZzA = zzmaVar.zzA(obj8, i12);
                                    int i55 = iZzh;
                                    byte[] bArr8 = bArr3;
                                    int iZzn = zzil.zzn(objZzA, zzmaVar.zzx(i12), bArr8, i55, i2, zzikVar);
                                    zzmaVar.zzJ(obj8, i12, objZzA);
                                    bArr3 = bArr8;
                                    unsafe3 = unsafe8;
                                    zzikVar4 = zzikVar;
                                    iZzh = iZzn;
                                    obj3 = obj8;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 10:
                                Object obj9 = obj3;
                                Unsafe unsafe9 = unsafe3;
                                zzikVar4 = zzikVar;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                if (i37 != 2) {
                                    obj2 = obj9;
                                    unsafe2 = unsafe9;
                                    i16 = iZzh;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    iZzh = zzil.zza(bArr3, iZzh, zzikVar4);
                                    unsafe9.putObject(obj9, j, zzikVar4.zzc);
                                    obj3 = obj9;
                                    unsafe3 = unsafe9;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 12:
                                Object obj10 = obj3;
                                Unsafe unsafe10 = unsafe3;
                                zzikVar4 = zzikVar;
                                i12 = i39;
                                i14 = i40;
                                i15 = i33;
                                if (i37 != 0) {
                                    obj2 = obj10;
                                    unsafe2 = unsafe10;
                                    i16 = iZzh;
                                    z2 = true;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    iZzh = zzil.zzi(bArr3, iZzh, zzikVar4);
                                    int i56 = zzikVar4.zza;
                                    zzkw zzkwVarZzw = zzmaVar.zzw(i12);
                                    if ((i38 & Integer.MIN_VALUE) == 0 || zzkwVarZzw == null || zzkwVarZzw.zza(i56)) {
                                        i34 |= i42;
                                        unsafe10.putInt(obj10, j, i56);
                                        obj3 = obj10;
                                        unsafe3 = unsafe10;
                                        i32 = i14;
                                        i31 = i12;
                                        i30 = i36;
                                        i33 = i15;
                                        i29 = -1;
                                        i28 = i2;
                                    } else {
                                        zzd(obj10).zzj(i14, Long.valueOf(i56));
                                        obj3 = obj10;
                                        unsafe3 = unsafe10;
                                        i32 = i14;
                                        i31 = i12;
                                        i30 = i36;
                                        i33 = i15;
                                        i29 = -1;
                                        i28 = i2;
                                    }
                                }
                                break;
                            case 15:
                                Object obj11 = obj3;
                                Unsafe unsafe11 = unsafe3;
                                zzikVar4 = zzikVar;
                                i12 = i39;
                                i14 = i40;
                                if (i37 != 0) {
                                    obj2 = obj11;
                                    i16 = iZzh;
                                    i15 = i33;
                                    z2 = true;
                                    unsafe2 = unsafe11;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    iZzh = zzil.zzi(bArr3, iZzh, zzikVar4);
                                    unsafe11.putInt(obj11, j, zzjb.zzF(zzikVar4.zza));
                                    obj3 = obj11;
                                    unsafe3 = unsafe11;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            case 16:
                                if (i37 != 0) {
                                    i12 = i39;
                                    obj2 = obj3;
                                    i16 = iZzh;
                                    i15 = i33;
                                    z2 = true;
                                    i14 = i40;
                                    unsafe2 = unsafe3;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    zzikVar4 = zzikVar;
                                    int iZzl5 = zzil.zzl(bArr3, iZzh, zzikVar4);
                                    unsafe3.putLong(obj3, j, zzjb.zzG(zzikVar4.zzb));
                                    obj3 = obj3;
                                    unsafe3 = unsafe3;
                                    iZzh = iZzl5;
                                    i31 = i39;
                                    i30 = i36;
                                    i32 = i40;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                            default:
                                int i57 = iZzh;
                                i12 = i39;
                                z2 = true;
                                i14 = i40;
                                i15 = i33;
                                unsafe2 = unsafe3;
                                obj2 = obj3;
                                if (i37 != 3) {
                                    i16 = i57;
                                    bArr2 = bArr;
                                    i10 = i36;
                                    unsafe = unsafe2;
                                    i11 = i34;
                                    i33 = i15;
                                    zzikVar2 = zzikVar;
                                    i6 = i14;
                                    z = z2;
                                    iZzh = i16;
                                } else {
                                    i34 |= i42;
                                    Object objZzA2 = zzmaVar.zzA(obj2, i12);
                                    int iZzm = zzil.zzm(objZzA2, zzmaVar.zzx(i12), bArr3, i57, i2, (i36 << 3) | 4, zzikVar);
                                    zzmaVar.zzJ(obj2, i12, objZzA2);
                                    bArr3 = bArr;
                                    zzikVar4 = zzikVar;
                                    iZzh = iZzm;
                                    i32 = i14;
                                    i31 = i12;
                                    i30 = i36;
                                    obj3 = obj2;
                                    unsafe3 = unsafe2;
                                    i33 = i15;
                                    i29 = -1;
                                    i28 = i2;
                                }
                                break;
                        }
                    } else {
                        unsafe = unsafe3;
                        Object obj12 = obj3;
                        int i58 = iZzh;
                        i12 = i39;
                        if (iZzt != 27) {
                            i18 = i33;
                            zzikVar3 = zzikVar;
                            i11 = i34;
                            if (iZzt <= 49) {
                                long j2 = i38;
                                zzkz zzkzVar2 = (zzkz) zzb.getObject(obj12, j);
                                if (zzkzVar2.zzc()) {
                                    zzkzVar = zzkzVar2;
                                } else {
                                    int size = zzkzVar2.size();
                                    zzkz zzkzVarZzd = zzkzVar2.zzd(size == 0 ? 10 : size + size);
                                    zzb.putObject(obj12, j, zzkzVarZzd);
                                    zzkzVar = zzkzVarZzd;
                                }
                                switch (iZzt) {
                                    case 18:
                                    case 35:
                                        zzkz zzkzVar3 = zzkzVar;
                                        i20 = i40;
                                        i21 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzjx zzjxVar = (zzjx) zzkzVar3;
                                            int iZzi2 = zzil.zzi(bArr, i21, zzikVar3);
                                            int i59 = zzikVar3.zza + iZzi2;
                                            while (iZzi2 < i59) {
                                                zzjxVar.zzf(Double.longBitsToDouble(zzil.zzp(bArr, iZzi2)));
                                                iZzi2 += 8;
                                            }
                                            if (iZzi2 != i59) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi2;
                                            i22 = i21;
                                        } else if (i37 == 1) {
                                            iZzl = i21 + 8;
                                            zzjx zzjxVar2 = (zzjx) zzkzVar3;
                                            zzjxVar2.zzf(Double.longBitsToDouble(zzil.zzp(bArr, i21)));
                                            while (iZzl < i2) {
                                                int iZzi3 = zzil.zzi(bArr, iZzl, zzikVar3);
                                                if (i20 == zzikVar3.zza) {
                                                    zzjxVar2.zzf(Double.longBitsToDouble(zzil.zzp(bArr, iZzi3)));
                                                    iZzl = iZzi3 + 8;
                                                } else {
                                                    iZzh = iZzl;
                                                    i22 = i21;
                                                }
                                            }
                                            iZzh = iZzl;
                                            i22 = i21;
                                        } else {
                                            i22 = i21;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 19:
                                    case 36:
                                        zzkz zzkzVar4 = zzkzVar;
                                        i20 = i40;
                                        i21 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzkk zzkkVar = (zzkk) zzkzVar4;
                                            int iZzi4 = zzil.zzi(bArr, i21, zzikVar3);
                                            int i60 = zzikVar3.zza + iZzi4;
                                            while (iZzi4 < i60) {
                                                zzkkVar.zzf(Float.intBitsToFloat(zzil.zzb(bArr, iZzi4)));
                                                iZzi4 += 4;
                                            }
                                            if (iZzi4 != i60) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi4;
                                            i22 = i21;
                                        } else if (i37 == 5) {
                                            iZzl = i21 + 4;
                                            zzkk zzkkVar2 = (zzkk) zzkzVar4;
                                            zzkkVar2.zzf(Float.intBitsToFloat(zzil.zzb(bArr, i21)));
                                            while (iZzl < i2) {
                                                int iZzi5 = zzil.zzi(bArr, iZzl, zzikVar3);
                                                if (i20 == zzikVar3.zza) {
                                                    zzkkVar2.zzf(Float.intBitsToFloat(zzil.zzb(bArr, iZzi5)));
                                                    iZzl = iZzi5 + 4;
                                                } else {
                                                    iZzh = iZzl;
                                                    i22 = i21;
                                                }
                                            }
                                            iZzh = iZzl;
                                            i22 = i21;
                                        } else {
                                            i22 = i21;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 20:
                                    case 21:
                                    case 37:
                                    case 38:
                                        zzkz zzkzVar5 = zzkzVar;
                                        i20 = i40;
                                        i21 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzlm zzlmVar = (zzlm) zzkzVar5;
                                            int iZzi6 = zzil.zzi(bArr, i21, zzikVar3);
                                            int i61 = zzikVar3.zza + iZzi6;
                                            while (iZzi6 < i61) {
                                                iZzi6 = zzil.zzl(bArr, iZzi6, zzikVar3);
                                                zzlmVar.zzg(zzikVar3.zzb);
                                            }
                                            if (iZzi6 != i61) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi6;
                                            i22 = i21;
                                        } else if (i37 == 0) {
                                            zzlm zzlmVar2 = (zzlm) zzkzVar5;
                                            iZzl = zzil.zzl(bArr, i21, zzikVar3);
                                            zzlmVar2.zzg(zzikVar3.zzb);
                                            while (iZzl < i2) {
                                                int iZzi7 = zzil.zzi(bArr, iZzl, zzikVar3);
                                                if (i20 == zzikVar3.zza) {
                                                    iZzl = zzil.zzl(bArr, iZzi7, zzikVar3);
                                                    zzlmVar2.zzg(zzikVar3.zzb);
                                                } else {
                                                    iZzh = iZzl;
                                                    i22 = i21;
                                                }
                                            }
                                            iZzh = iZzl;
                                            i22 = i21;
                                        } else {
                                            i22 = i21;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 22:
                                    case 29:
                                    case 39:
                                    case 43:
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            iZzh = zzil.zzf(bArr, i58, zzkzVar, zzikVar3);
                                            i22 = i58;
                                            i20 = i40;
                                        } else if (i37 == 0) {
                                            i20 = i40;
                                            iZzh = zzil.zzk(i40, bArr, i58, i2, zzkzVar, zzikVar3);
                                            i22 = i58;
                                        } else {
                                            i20 = i40;
                                            i22 = i58;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 23:
                                    case 32:
                                    case 40:
                                    case 46:
                                        i23 = i40;
                                        i24 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzlm zzlmVar3 = (zzlm) zzkzVar;
                                            int iZzi8 = zzil.zzi(bArr, i24, zzikVar3);
                                            int i62 = zzikVar3.zza + iZzi8;
                                            while (iZzi8 < i62) {
                                                zzlmVar3.zzg(zzil.zzp(bArr, iZzi8));
                                                iZzi8 += 8;
                                            }
                                            if (iZzi8 != i62) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi8;
                                            i22 = i24;
                                            i20 = i23;
                                        } else if (i37 == 1) {
                                            iZzl2 = i24 + 8;
                                            zzlm zzlmVar4 = (zzlm) zzkzVar;
                                            zzlmVar4.zzg(zzil.zzp(bArr, i24));
                                            while (iZzl2 < i2) {
                                                int iZzi9 = zzil.zzi(bArr, iZzl2, zzikVar3);
                                                if (i23 == zzikVar3.zza) {
                                                    zzlmVar4.zzg(zzil.zzp(bArr, iZzi9));
                                                    iZzl2 = iZzi9 + 8;
                                                } else {
                                                    iZzh = iZzl2;
                                                    i22 = i24;
                                                    i20 = i23;
                                                }
                                            }
                                            iZzh = iZzl2;
                                            i22 = i24;
                                            i20 = i23;
                                        } else {
                                            i22 = i24;
                                            i20 = i23;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 24:
                                    case 31:
                                    case 41:
                                    case 45:
                                        i23 = i40;
                                        i24 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzkt zzktVar = (zzkt) zzkzVar;
                                            int iZzi10 = zzil.zzi(bArr, i24, zzikVar3);
                                            int i63 = zzikVar3.zza + iZzi10;
                                            while (iZzi10 < i63) {
                                                zzktVar.zzh(zzil.zzb(bArr, iZzi10));
                                                iZzi10 += 4;
                                            }
                                            if (iZzi10 != i63) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi10;
                                            i22 = i24;
                                            i20 = i23;
                                        } else if (i37 == 5) {
                                            iZzl2 = i24 + 4;
                                            zzkt zzktVar2 = (zzkt) zzkzVar;
                                            zzktVar2.zzh(zzil.zzb(bArr, i24));
                                            while (iZzl2 < i2) {
                                                int iZzi11 = zzil.zzi(bArr, iZzl2, zzikVar3);
                                                if (i23 == zzikVar3.zza) {
                                                    zzktVar2.zzh(zzil.zzb(bArr, iZzi11));
                                                    iZzl2 = iZzi11 + 4;
                                                } else {
                                                    iZzh = iZzl2;
                                                    i22 = i24;
                                                    i20 = i23;
                                                }
                                            }
                                            iZzh = iZzl2;
                                            i22 = i24;
                                            i20 = i23;
                                        } else {
                                            i22 = i24;
                                            i20 = i23;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 25:
                                    case 42:
                                        i23 = i40;
                                        i24 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 2) {
                                            zzim zzimVar = (zzim) zzkzVar;
                                            int iZzi12 = zzil.zzi(bArr, i24, zzikVar3);
                                            int i64 = zzikVar3.zza + iZzi12;
                                            while (iZzi12 < i64) {
                                                iZzi12 = zzil.zzl(bArr, iZzi12, zzikVar3);
                                                zzimVar.zze(zzikVar3.zzb != 0);
                                            }
                                            if (iZzi12 != i64) {
                                                throw zzlc.zzi();
                                            }
                                            iZzh = iZzi12;
                                            i22 = i24;
                                            i20 = i23;
                                        } else if (i37 == 0) {
                                            zzim zzimVar2 = (zzim) zzkzVar;
                                            iZzl2 = zzil.zzl(bArr, i24, zzikVar3);
                                            zzimVar2.zze(zzikVar3.zzb != 0);
                                            while (iZzl2 < i2) {
                                                int iZzi13 = zzil.zzi(bArr, iZzl2, zzikVar3);
                                                if (i23 == zzikVar3.zza) {
                                                    iZzl2 = zzil.zzl(bArr, iZzi13, zzikVar3);
                                                    zzimVar2.zze(zzikVar3.zzb != 0);
                                                } else {
                                                    iZzh = iZzl2;
                                                    i22 = i24;
                                                    i20 = i23;
                                                }
                                            }
                                            iZzh = iZzl2;
                                            i22 = i24;
                                            i20 = i23;
                                        } else {
                                            i22 = i24;
                                            i20 = i23;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 26:
                                        i23 = i40;
                                        i24 = i58;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 != 2) {
                                            i22 = i24;
                                            i20 = i23;
                                            iZzh = i22;
                                            if (iZzh != i22) {
                                                zzikVar3 = zzikVar3;
                                                unsafe = unsafe;
                                                i6 = i20;
                                                i33 = i18;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                            } else {
                                                zzikVar3 = zzikVar3;
                                                bArr3 = bArr;
                                                unsafe3 = unsafe;
                                                i31 = i12;
                                                i30 = i10;
                                                obj3 = obj2;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                                i28 = i2;
                                                zzikVar4 = zzikVar3;
                                                i32 = i20;
                                            }
                                        } else {
                                            if ((j2 & 536870912) == 0) {
                                                iZzl2 = zzil.zzi(bArr, i24, zzikVar3);
                                                int i65 = zzikVar3.zza;
                                                if (i65 < 0) {
                                                    throw zzlc.zzf();
                                                }
                                                if (i65 == 0) {
                                                    zzkzVar.add("");
                                                } else {
                                                    zzkzVar.add(new String(bArr, iZzl2, i65, zzla.zza));
                                                    iZzl2 += i65;
                                                }
                                                while (iZzl2 < i2) {
                                                    int iZzi14 = zzil.zzi(bArr, iZzl2, zzikVar3);
                                                    if (i23 == zzikVar3.zza) {
                                                        iZzl2 = zzil.zzi(bArr, iZzi14, zzikVar3);
                                                        int i66 = zzikVar3.zza;
                                                        if (i66 < 0) {
                                                            throw zzlc.zzf();
                                                        }
                                                        if (i66 == 0) {
                                                            zzkzVar.add("");
                                                        } else {
                                                            zzkzVar.add(new String(bArr, iZzl2, i66, zzla.zza));
                                                            iZzl2 += i66;
                                                        }
                                                    }
                                                }
                                            } else {
                                                iZzl2 = zzil.zzi(bArr, i24, zzikVar3);
                                                int i67 = zzikVar3.zza;
                                                if (i67 < 0) {
                                                    throw zzlc.zzf();
                                                }
                                                if (i67 == 0) {
                                                    zzkzVar.add("");
                                                } else {
                                                    int i68 = iZzl2 + i67;
                                                    if (!zznl.zze(bArr, iZzl2, i68)) {
                                                        throw zzlc.zzd();
                                                    }
                                                    zzkzVar.add(new String(bArr, iZzl2, i67, zzla.zza));
                                                    iZzl2 = i68;
                                                }
                                                while (iZzl2 < i2) {
                                                    int iZzi15 = zzil.zzi(bArr, iZzl2, zzikVar3);
                                                    if (i23 == zzikVar3.zza) {
                                                        iZzl2 = zzil.zzi(bArr, iZzi15, zzikVar3);
                                                        int i69 = zzikVar3.zza;
                                                        if (i69 < 0) {
                                                            throw zzlc.zzf();
                                                        }
                                                        if (i69 == 0) {
                                                            zzkzVar.add("");
                                                        } else {
                                                            int i70 = iZzl2 + i69;
                                                            if (!zznl.zze(bArr, iZzl2, i70)) {
                                                                throw zzlc.zzd();
                                                            }
                                                            zzkzVar.add(new String(bArr, iZzl2, i69, zzla.zza));
                                                            iZzl2 = i70;
                                                        }
                                                    }
                                                }
                                            }
                                            iZzh = iZzl2;
                                            i22 = i24;
                                            i20 = i23;
                                            if (iZzh != i22) {
                                                zzikVar3 = zzikVar3;
                                                unsafe = unsafe;
                                                i6 = i20;
                                                i33 = i18;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                            } else {
                                                zzikVar3 = zzikVar3;
                                                bArr3 = bArr;
                                                unsafe3 = unsafe;
                                                i31 = i12;
                                                i30 = i10;
                                                obj3 = obj2;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                                i28 = i2;
                                                zzikVar4 = zzikVar3;
                                                i32 = i20;
                                            }
                                        }
                                        break;
                                    case 27:
                                        i10 = i36;
                                        if (i37 == 2) {
                                            obj2 = obj;
                                            iZzh = zzil.zze(zzmaVar.zzx(i12), i40, bArr, i58, i2, zzkzVar, zzikVar3);
                                            i20 = i40;
                                            i22 = i58;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            obj2 = obj;
                                            i22 = i58;
                                            i20 = i40;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 28:
                                        i23 = i40;
                                        i10 = i36;
                                        if (i37 != 2) {
                                            obj2 = obj;
                                            i22 = i58;
                                            zzikVar3 = zzikVar3;
                                            i20 = i23;
                                            iZzh = i22;
                                            if (iZzh != i22) {
                                                zzikVar3 = zzikVar3;
                                                unsafe = unsafe;
                                                i6 = i20;
                                                i33 = i18;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                            } else {
                                                zzikVar3 = zzikVar3;
                                                bArr3 = bArr;
                                                unsafe3 = unsafe;
                                                i31 = i12;
                                                i30 = i10;
                                                obj3 = obj2;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                                i28 = i2;
                                                zzikVar4 = zzikVar3;
                                                i32 = i20;
                                            }
                                        } else {
                                            int iZzi16 = zzil.zzi(bArr, i58, zzikVar3);
                                            int i71 = zzikVar3.zza;
                                            if (i71 < 0) {
                                                throw zzlc.zzf();
                                            }
                                            if (i71 > bArr.length - iZzi16) {
                                                throw zzlc.zzi();
                                            }
                                            if (i71 == 0) {
                                                zzkzVar.add(zziv.zzb);
                                            } else {
                                                zzkzVar.add(zziv.zzk(bArr, iZzi16, i71));
                                                iZzi16 += i71;
                                            }
                                            while (iZzi16 < i2) {
                                                int iZzi17 = zzil.zzi(bArr, iZzi16, zzikVar3);
                                                if (i23 != zzikVar3.zza) {
                                                    obj2 = obj;
                                                    iZzh = iZzi16;
                                                    i22 = i58;
                                                    zzikVar3 = zzikVar3;
                                                    i20 = i23;
                                                    if (iZzh != i22) {
                                                        zzikVar3 = zzikVar3;
                                                        unsafe = unsafe;
                                                        i6 = i20;
                                                        i33 = i18;
                                                        z = true;
                                                        zzikVar2 = zzikVar3;
                                                        bArr2 = bArr;
                                                    } else {
                                                        zzikVar3 = zzikVar3;
                                                        bArr3 = bArr;
                                                        unsafe3 = unsafe;
                                                        i31 = i12;
                                                        i30 = i10;
                                                        obj3 = obj2;
                                                        i33 = i18;
                                                        i34 = i11;
                                                        i29 = -1;
                                                        i28 = i2;
                                                        zzikVar4 = zzikVar3;
                                                        i32 = i20;
                                                    }
                                                    break;
                                                } else {
                                                    iZzi16 = zzil.zzi(bArr, iZzi17, zzikVar3);
                                                    int i72 = zzikVar3.zza;
                                                    if (i72 < 0) {
                                                        throw zzlc.zzf();
                                                    }
                                                    if (i72 > bArr.length - iZzi16) {
                                                        throw zzlc.zzi();
                                                    }
                                                    if (i72 == 0) {
                                                        zzkzVar.add(zziv.zzb);
                                                    } else {
                                                        zzkzVar.add(zziv.zzk(bArr, iZzi16, i72));
                                                        iZzi16 += i72;
                                                    }
                                                }
                                            }
                                            obj2 = obj;
                                            iZzh = iZzi16;
                                            i22 = i58;
                                            zzikVar3 = zzikVar3;
                                            i20 = i23;
                                            if (iZzh != i22) {
                                                zzikVar3 = zzikVar3;
                                                unsafe = unsafe;
                                                i6 = i20;
                                                i33 = i18;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                            } else {
                                                zzikVar3 = zzikVar3;
                                                bArr3 = bArr;
                                                unsafe3 = unsafe;
                                                i31 = i12;
                                                i30 = i10;
                                                obj3 = obj2;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                                i28 = i2;
                                                zzikVar4 = zzikVar3;
                                                i32 = i20;
                                            }
                                        }
                                        break;
                                    case 30:
                                    case 44:
                                        if (i37 == 2) {
                                            i25 = i40;
                                            i22 = i58;
                                            iZzk = zzil.zzf(bArr, i58, zzkzVar, zzikVar3);
                                        } else if (i37 != 0) {
                                            i22 = i58;
                                            i10 = i36;
                                            obj2 = obj;
                                            i20 = i40;
                                            iZzh = i22;
                                            if (iZzh != i22) {
                                                zzikVar3 = zzikVar3;
                                                unsafe = unsafe;
                                                i6 = i20;
                                                i33 = i18;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                            } else {
                                                zzikVar3 = zzikVar3;
                                                bArr3 = bArr;
                                                unsafe3 = unsafe;
                                                i31 = i12;
                                                i30 = i10;
                                                obj3 = obj2;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                                i28 = i2;
                                                zzikVar4 = zzikVar3;
                                                i32 = i20;
                                            }
                                        } else {
                                            i25 = i40;
                                            i22 = i58;
                                            iZzk = zzil.zzk(i40, bArr, i58, i2, zzkzVar, zzikVar3);
                                        }
                                        zzmm.zzo(obj12, i36, zzkzVar, zzmaVar.zzw(i12), null, zzmaVar.zzm);
                                        i10 = i36;
                                        obj2 = obj;
                                        zzikVar3 = zzikVar3;
                                        i20 = i25;
                                        iZzh = iZzk;
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 33:
                                    case 47:
                                        if (i37 == 2) {
                                            zzkt zzktVar3 = (zzkt) zzkzVar;
                                            int iZzi18 = zzil.zzi(bArr, i58, zzikVar3);
                                            int i73 = zzikVar3.zza + iZzi18;
                                            while (iZzi18 < i73) {
                                                iZzi18 = zzil.zzi(bArr, iZzi18, zzikVar3);
                                                zzktVar3.zzh(zzjb.zzF(zzikVar3.zza));
                                            }
                                            if (iZzi18 != i73) {
                                                throw zzlc.zzi();
                                            }
                                            i20 = i40;
                                            i22 = i58;
                                            iZzh = iZzi18;
                                            obj2 = obj12;
                                            i10 = i36;
                                        } else if (i37 == 0) {
                                            zzkt zzktVar4 = (zzkt) zzkzVar;
                                            iZzi = zzil.zzi(bArr, i58, zzikVar3);
                                            zzktVar4.zzh(zzjb.zzF(zzikVar3.zza));
                                            while (iZzi < i2) {
                                                int iZzi19 = zzil.zzi(bArr, iZzi, zzikVar3);
                                                if (i40 == zzikVar3.zza) {
                                                    iZzi = zzil.zzi(bArr, iZzi19, zzikVar3);
                                                    zzktVar4.zzh(zzjb.zzF(zzikVar3.zza));
                                                } else {
                                                    i20 = i40;
                                                    i22 = i58;
                                                    iZzh = iZzi;
                                                    obj2 = obj12;
                                                    i10 = i36;
                                                }
                                            }
                                            i20 = i40;
                                            i22 = i58;
                                            iZzh = iZzi;
                                            obj2 = obj12;
                                            i10 = i36;
                                        } else {
                                            i20 = i40;
                                            i22 = i58;
                                            obj2 = obj12;
                                            i10 = i36;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    case 34:
                                    case 48:
                                        if (i37 == 2) {
                                            zzlm zzlmVar5 = (zzlm) zzkzVar;
                                            int iZzi20 = zzil.zzi(bArr, i58, zzikVar3);
                                            int i74 = zzikVar3.zza + iZzi20;
                                            while (iZzi20 < i74) {
                                                iZzi20 = zzil.zzl(bArr, iZzi20, zzikVar3);
                                                zzlmVar5.zzg(zzjb.zzG(zzikVar3.zzb));
                                            }
                                            if (iZzi20 != i74) {
                                                throw zzlc.zzi();
                                            }
                                            i20 = i40;
                                            i22 = i58;
                                            iZzh = iZzi20;
                                            obj2 = obj12;
                                            i10 = i36;
                                        } else if (i37 == 0) {
                                            zzlm zzlmVar6 = (zzlm) zzkzVar;
                                            iZzi = zzil.zzl(bArr, i58, zzikVar3);
                                            zzlmVar6.zzg(zzjb.zzG(zzikVar3.zzb));
                                            while (iZzi < i2) {
                                                int iZzi21 = zzil.zzi(bArr, iZzi, zzikVar3);
                                                if (i40 == zzikVar3.zza) {
                                                    iZzi = zzil.zzl(bArr, iZzi21, zzikVar3);
                                                    zzlmVar6.zzg(zzjb.zzG(zzikVar3.zzb));
                                                } else {
                                                    i20 = i40;
                                                    i22 = i58;
                                                    iZzh = iZzi;
                                                    obj2 = obj12;
                                                    i10 = i36;
                                                }
                                            }
                                            i20 = i40;
                                            i22 = i58;
                                            iZzh = iZzi;
                                            obj2 = obj12;
                                            i10 = i36;
                                        } else {
                                            i20 = i40;
                                            i22 = i58;
                                            obj2 = obj12;
                                            i10 = i36;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                    default:
                                        zzkz zzkzVar6 = zzkzVar;
                                        int i75 = i2;
                                        i20 = i40;
                                        obj2 = obj12;
                                        i10 = i36;
                                        if (i37 == 3) {
                                            int i76 = (i20 & (-8)) | 4;
                                            zzmk zzmkVarZzx = zzmaVar.zzx(i12);
                                            int iZzc = zzil.zzc(zzmkVarZzx, bArr, i58, i75, i76, zzikVar3);
                                            i22 = i58;
                                            zzkzVar6.add(zzikVar3.zzc);
                                            while (iZzc < i75) {
                                                int iZzi22 = zzil.zzi(bArr, iZzc, zzikVar3);
                                                if (i20 == zzikVar3.zza) {
                                                    iZzc = zzil.zzc(zzmkVarZzx, bArr, iZzi22, i75, i76, zzikVar3);
                                                    zzkzVar6.add(zzikVar3.zzc);
                                                    i75 = i2;
                                                } else {
                                                    iZzh = iZzc;
                                                }
                                            }
                                            iZzh = iZzc;
                                        } else {
                                            i22 = i58;
                                            iZzh = i22;
                                        }
                                        if (iZzh != i22) {
                                            zzikVar3 = zzikVar3;
                                            unsafe = unsafe;
                                            i6 = i20;
                                            i33 = i18;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                        } else {
                                            zzikVar3 = zzikVar3;
                                            bArr3 = bArr;
                                            unsafe3 = unsafe;
                                            i31 = i12;
                                            i30 = i10;
                                            obj3 = obj2;
                                            i33 = i18;
                                            i34 = i11;
                                            i29 = -1;
                                            i28 = i2;
                                            zzikVar4 = zzikVar3;
                                            i32 = i20;
                                        }
                                        break;
                                }
                            } else {
                                i19 = i40;
                                i17 = i58;
                                obj2 = obj12;
                                i10 = i36;
                                if (iZzt != 50) {
                                    Unsafe unsafe12 = zzb;
                                    long j3 = iArr[i12 + 2] & 1048575;
                                    switch (iZzt) {
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            z = true;
                                            if (i37 == 1) {
                                                unsafe12.putObject(obj2, j, Double.valueOf(Double.longBitsToDouble(zzil.zzp(bArr2, i26))));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = i26 + 8;
                                            } else {
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 52:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 5) {
                                                unsafe12.putObject(obj2, j, Float.valueOf(Float.intBitsToFloat(zzil.zzb(bArr2, i26))));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = i26 + 4;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 53:
                                        case 54:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 0) {
                                                int iZzl6 = zzil.zzl(bArr2, i26, zzikVar2);
                                                unsafe12.putObject(obj2, j, Long.valueOf(zzikVar2.zzb));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = iZzl6;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 55:
                                        case 62:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 0) {
                                                int iZzi23 = zzil.zzi(bArr2, i26, zzikVar2);
                                                unsafe12.putObject(obj2, j, Integer.valueOf(zzikVar2.zza));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = iZzi23;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 56:
                                        case 65:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 1) {
                                                unsafe12.putObject(obj2, j, Long.valueOf(zzil.zzp(bArr2, i26)));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = i26 + 8;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 57:
                                        case 64:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 5) {
                                                unsafe12.putObject(obj2, j, Integer.valueOf(zzil.zzb(bArr2, i26)));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = i26 + 4;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case Elf64_Ehdr.e_shentsize /* 58 */:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            if (i37 == 0) {
                                                int iZzl7 = zzil.zzl(bArr2, i26, zzikVar2);
                                                unsafe12.putObject(obj2, j, Boolean.valueOf(zzikVar2.zzb != 0));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = iZzl7;
                                                z = true;
                                            } else {
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 59:
                                            i6 = i19;
                                            i26 = i17;
                                            zzikVar2 = zzikVar3;
                                            bArr2 = bArr;
                                            i27 = i12;
                                            if (i37 == 2) {
                                                int iZzi24 = zzil.zzi(bArr2, i26, zzikVar2);
                                                int i77 = zzikVar2.zza;
                                                if (i77 == 0) {
                                                    unsafe12.putObject(obj2, j, "");
                                                } else {
                                                    int i78 = iZzi24 + i77;
                                                    if ((i38 & 536870912) != 0 && !zznl.zze(bArr2, iZzi24, i78)) {
                                                        throw zzlc.zzd();
                                                    }
                                                    unsafe12.putObject(obj2, j, new String(bArr2, iZzi24, i77, zzla.zza));
                                                    iZzi24 = i78;
                                                }
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = iZzi24;
                                                z = true;
                                            } else {
                                                unsafe = unsafe;
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 60:
                                            i6 = i19;
                                            if (i37 == 2) {
                                                Object objZzB = zzmaVar.zzB(obj2, i10, i12);
                                                int iZzn2 = zzil.zzn(objZzB, zzmaVar.zzx(i12), bArr, i17, i2, zzikVar3);
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                                zzmaVar.zzK(obj2, i10, i12, objZzB);
                                                iZzh = iZzn2;
                                                i26 = i17;
                                                unsafe = unsafe;
                                                i27 = i12;
                                                z = true;
                                            } else {
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                                i26 = i17;
                                                unsafe = unsafe;
                                                i27 = i12;
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 61:
                                            i6 = i19;
                                            if (i37 == 2) {
                                                int iZza = zzil.zza(bArr, i17, zzikVar3);
                                                unsafe12.putObject(obj2, j, zzikVar3.zzc);
                                                unsafe12.putInt(obj2, j3, i10);
                                                unsafe = unsafe;
                                                i27 = i12;
                                                iZzh = iZza;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                                i26 = i17;
                                            } else {
                                                zzikVar2 = zzikVar3;
                                                unsafe = unsafe;
                                                i27 = i12;
                                                z = true;
                                                bArr2 = bArr;
                                                i26 = i17;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 63:
                                            i6 = i19;
                                            if (i37 == 0) {
                                                int iZzi25 = zzil.zzi(bArr, i17, zzikVar3);
                                                int i79 = zzikVar3.zza;
                                                zzkw zzkwVarZzw2 = zzmaVar.zzw(i12);
                                                if (zzkwVarZzw2 == null || zzkwVarZzw2.zza(i79)) {
                                                    unsafe12.putObject(obj2, j, Integer.valueOf(i79));
                                                    unsafe12.putInt(obj2, j3, i10);
                                                } else {
                                                    i6 = i6;
                                                    zzd(obj2).zzj(i6, Long.valueOf(i79));
                                                }
                                                unsafe = unsafe;
                                                i27 = i12;
                                                iZzh = iZzi25;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                                i26 = i17;
                                            } else {
                                                i6 = i6;
                                                zzikVar2 = zzikVar3;
                                                unsafe = unsafe;
                                                i27 = i12;
                                                z = true;
                                                bArr2 = bArr;
                                                i26 = i17;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                            if (i37 == 0) {
                                                int iZzi26 = zzil.zzi(bArr, i17, zzikVar3);
                                                unsafe12.putObject(obj2, j, Integer.valueOf(zzjb.zzF(zzikVar3.zza)));
                                                unsafe12.putInt(obj2, j3, i10);
                                                i27 = i12;
                                                iZzh = iZzi26;
                                                i6 = i19;
                                                zzikVar2 = zzikVar3;
                                                unsafe = unsafe;
                                                z = true;
                                                bArr2 = bArr;
                                                i26 = i17;
                                            } else {
                                                zzikVar2 = zzikVar3;
                                                i27 = i12;
                                                i6 = i19;
                                                bArr2 = bArr;
                                                unsafe = unsafe;
                                                i26 = i17;
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 67:
                                            if (i37 == 0) {
                                                int iZzl8 = zzil.zzl(bArr, i17, zzikVar3);
                                                unsafe12.putObject(obj2, j, Long.valueOf(zzjb.zzG(zzikVar3.zzb)));
                                                unsafe12.putInt(obj2, j3, i10);
                                                iZzh = iZzl8;
                                                zzikVar2 = zzikVar3;
                                                i27 = i12;
                                                i6 = i19;
                                                bArr2 = bArr;
                                                unsafe = unsafe;
                                                i26 = i17;
                                                z = true;
                                            } else {
                                                zzikVar2 = zzikVar3;
                                                i27 = i12;
                                                i6 = i19;
                                                bArr2 = bArr;
                                                unsafe = unsafe;
                                                i26 = i17;
                                                z = true;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        case 68:
                                            if (i37 == 3) {
                                                Object objZzB2 = zzmaVar.zzB(obj2, i10, i12);
                                                int iZzm2 = zzil.zzm(objZzB2, zzmaVar.zzx(i12), bArr, i17, i2, (i19 & (-8)) | 4, zzikVar);
                                                zzmaVar.zzK(obj2, i10, i12, objZzB2);
                                                iZzh = iZzm2;
                                                unsafe = unsafe;
                                                i27 = i12;
                                                i6 = i19;
                                                z = true;
                                                zzikVar2 = zzikVar;
                                                bArr2 = bArr;
                                                i26 = i17;
                                            } else {
                                                unsafe = unsafe;
                                                i27 = i12;
                                                i6 = i19;
                                                z = true;
                                                zzikVar2 = zzikVar3;
                                                bArr2 = bArr;
                                                i26 = i17;
                                                iZzh = i26;
                                            }
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                        default:
                                            unsafe = unsafe;
                                            i6 = i19;
                                            i26 = i17;
                                            z = true;
                                            zzikVar2 = zzikVar3;
                                            i27 = i12;
                                            bArr2 = bArr;
                                            iZzh = i26;
                                            if (iZzh == i26) {
                                                i12 = i27;
                                                i33 = i18;
                                            } else {
                                                i28 = i2;
                                                bArr3 = bArr2;
                                                i30 = i10;
                                                zzikVar4 = zzikVar2;
                                                obj3 = obj2;
                                                i32 = i6;
                                                i31 = i27;
                                                unsafe3 = unsafe;
                                                i33 = i18;
                                                i34 = i11;
                                                i29 = -1;
                                            }
                                            break;
                                    }
                                } else {
                                    if (i37 == 2) {
                                        Unsafe unsafe13 = zzb;
                                        Object objZzz = zzmaVar.zzz(i12);
                                        Object object = unsafe13.getObject(obj2, j);
                                        if (zzls.zza(object)) {
                                            zzlr zzlrVarZzb = zzlr.zza().zzb();
                                            zzls.zzb(zzlrVarZzb, object);
                                            unsafe13.putObject(obj2, j, zzlrVarZzb);
                                        }
                                        throw null;
                                    }
                                    iZzh = i17;
                                    unsafe = unsafe;
                                    i6 = i19;
                                    i33 = i18;
                                    z = true;
                                    zzikVar2 = zzikVar3;
                                    bArr2 = bArr;
                                }
                            }
                        } else if (i37 == 2) {
                            zzkz zzkzVarZzd2 = (zzkz) unsafe.getObject(obj12, j);
                            if (!zzkzVarZzd2.zzc()) {
                                int size2 = zzkzVarZzd2.size();
                                zzkzVarZzd2 = zzkzVarZzd2.zzd(size2 == 0 ? 10 : size2 + size2);
                                unsafe.putObject(obj12, j, zzkzVarZzd2);
                            }
                            int iZze = zzil.zze(zzmaVar.zzx(i12), i40, bArr, i58, i2, zzkzVarZzd2, zzikVar);
                            bArr3 = bArr;
                            i32 = i40;
                            i31 = i12;
                            obj3 = obj12;
                            i30 = i36;
                            i29 = -1;
                            zzikVar4 = zzikVar;
                            iZzh = iZze;
                            unsafe3 = unsafe;
                            i28 = i2;
                        } else {
                            i17 = i58;
                            zzikVar3 = zzikVar;
                            obj2 = obj12;
                            i10 = i36;
                            i18 = i33;
                            i11 = i34;
                            i19 = i40;
                            iZzh = i17;
                            unsafe = unsafe;
                            i6 = i19;
                            i33 = i18;
                            z = true;
                            zzikVar2 = zzikVar3;
                            bArr2 = bArr;
                        }
                    }
                } else {
                    zzikVar2 = zzikVar4;
                    i10 = i36;
                    unsafe = unsafe3;
                    obj2 = obj3;
                    i11 = i34;
                    z = true;
                    i12 = 0;
                    i6 = i9;
                    bArr2 = bArr3;
                }
                i4 = i3;
                if (i6 != i4 || i4 == 0) {
                    if (!zzmaVar.zzh || zzikVar2.zzd == zzkd.zza) {
                        iZzh = zzil.zzh(i6, bArr2, iZzh, i2, zzd(obj2), zzikVar2);
                        i13 = i2;
                    } else {
                        zzkq zzkqVarZza = zzikVar2.zzd.zza(zzmaVar.zzg, i10);
                        if (zzkqVarZza == null) {
                            iZzh = zzil.zzh(i6, bArr2, iZzh, i2, zzd(obj2), zzikVar2);
                            i13 = i2;
                        } else {
                            byte[] bArr9 = bArr2;
                            zzik zzikVar5 = zzikVar2;
                            zzko zzkoVar = (zzko) obj2;
                            zzkoVar.zzi();
                            zzki zzkiVar = zzkoVar.zzb;
                            zznm zznmVar = zzkqVarZza.zza.zzb;
                            if (zznmVar == zznm.ENUM) {
                                zzil.zzi(bArr9, iZzh, zzikVar5);
                                throw null;
                            }
                            switch (zznmVar) {
                                case DOUBLE:
                                    Double dValueOf = Double.valueOf(Double.longBitsToDouble(zzil.zzp(bArr9, iZzh)));
                                    iZzh += 8;
                                    objValueOf = dValueOf;
                                    break;
                                case FLOAT:
                                    Float fValueOf = Float.valueOf(Float.intBitsToFloat(zzil.zzb(bArr9, iZzh)));
                                    iZzh += 4;
                                    objValueOf = fValueOf;
                                    break;
                                case INT64:
                                case UINT64:
                                    iZzh = zzil.zzl(bArr9, iZzh, zzikVar5);
                                    objValueOf = Long.valueOf(zzikVar5.zzb);
                                    break;
                                case INT32:
                                case UINT32:
                                    iZzh = zzil.zzi(bArr9, iZzh, zzikVar5);
                                    objValueOf = Integer.valueOf(zzikVar5.zza);
                                    break;
                                case FIXED64:
                                case SFIXED64:
                                    Long lValueOf = Long.valueOf(zzil.zzp(bArr9, iZzh));
                                    iZzh += 8;
                                    objValueOf = lValueOf;
                                    break;
                                case FIXED32:
                                case SFIXED32:
                                    Integer numValueOf = Integer.valueOf(zzil.zzb(bArr9, iZzh));
                                    iZzh += 4;
                                    objValueOf = numValueOf;
                                    break;
                                case BOOL:
                                    iZzh = zzil.zzl(bArr9, iZzh, zzikVar5);
                                    objValueOf = Boolean.valueOf(zzikVar5.zzb != 0 ? z : false);
                                    break;
                                case STRING:
                                    iZzh = zzil.zzg(bArr9, iZzh, zzikVar5);
                                    objValueOf = zzikVar5.zzc;
                                    break;
                                case GROUP:
                                    int i80 = zzmg.zza;
                                    throw null;
                                case MESSAGE:
                                    int i81 = zzmg.zza;
                                    throw null;
                                case BYTES:
                                    iZzh = zzil.zza(bArr9, iZzh, zzikVar5);
                                    objValueOf = zzikVar5.zzc;
                                    break;
                                case ENUM:
                                    throw new IllegalStateException("Shouldn't reach here.");
                                case SINT32:
                                    iZzh = zzil.zzi(bArr9, iZzh, zzikVar5);
                                    objValueOf = Integer.valueOf(zzjb.zzF(zzikVar5.zza));
                                    break;
                                case SINT64:
                                    iZzh = zzil.zzl(bArr9, iZzh, zzikVar5);
                                    objValueOf = Long.valueOf(zzjb.zzG(zzikVar5.zzb));
                                    break;
                                default:
                                    objValueOf = null;
                                    break;
                            }
                            zzkiVar.zzi(zzkqVarZza.zza, objValueOf);
                            i13 = i2;
                        }
                    }
                    bArr3 = bArr;
                    i31 = i12;
                    i30 = i10;
                    i28 = i13;
                    obj3 = obj2;
                    i32 = i6;
                    unsafe3 = unsafe;
                    i34 = i11;
                    i29 = -1;
                    zzikVar4 = zzikVar;
                } else {
                    i5 = i2;
                    i8 = iZzh;
                    i7 = i11;
                }
            } else {
                i4 = i3;
                unsafe = unsafe3;
                obj2 = obj3;
                i5 = i28;
                int i82 = i34;
                i6 = i32;
                i7 = i82;
                i8 = iZzh;
            }
        }
        if (i33 != 1048575) {
            unsafe.putInt(obj2, i33, i7);
        }
        int i83 = zzmaVar.zzk;
        while (i83 < zzmaVar.zzl) {
            zzmaVar.zzy(obj2, zzmaVar.zzj[i83], null, zzmaVar.zzm, obj);
            i83++;
            zzmaVar = this;
            obj2 = obj;
        }
        if (i4 == 0) {
            if (i8 != i5) {
                throw zzlc.zzg();
            }
        } else if (i8 > i5 || i6 != i4) {
            throw zzlc.zzg();
        }
        return i8;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final Object zze() {
        return ((zzks) this.zzg).zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzf(Object obj) {
        if (zzQ(obj)) {
            if (obj instanceof zzks) {
                zzks zzksVar = (zzks) obj;
                zzksVar.zzG(Integer.MAX_VALUE);
                zzksVar.zza = 0;
                zzksVar.zzE();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int iZzu = zzu(i);
                long j = 1048575 & iZzu;
                switch (zzt(iZzu)) {
                    case 9:
                    case 17:
                        if (zzN(obj, i)) {
                            zzx(i).zzf(zzb.getObject(obj, j));
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        ((zzkz) zzni.zzf(obj, j)).zzb();
                        break;
                    case 50:
                        Object object = zzb.getObject(obj, j);
                        if (object != null) {
                            Unsafe unsafe = zzb;
                            ((zzlr) object).zzc();
                            unsafe.putObject(obj, j, object);
                        }
                        break;
                    case 60:
                    case 68:
                        if (zzR(obj, this.zzc[i], i)) {
                            zzx(i).zzf(zzb.getObject(obj, j));
                        }
                        break;
                }
            }
            this.zzm.zzm(obj);
            if (this.zzh) {
                this.zzn.zzf(obj);
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzg(Object obj, Object obj2) {
        zzD(obj);
        if (obj2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzu = zzu(i);
            int i2 = 1048575 & iZzu;
            int[] iArr = this.zzc;
            int iZzt = zzt(iZzu);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzt) {
                case 0:
                    if (zzN(obj2, i)) {
                        zzni.zzo(obj, j, zzni.zza(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 1:
                    if (zzN(obj2, i)) {
                        zzni.zzp(obj, j, zzni.zzb(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 2:
                    if (zzN(obj2, i)) {
                        zzni.zzr(obj, j, zzni.zzd(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 3:
                    if (zzN(obj2, i)) {
                        zzni.zzr(obj, j, zzni.zzd(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 4:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 5:
                    if (zzN(obj2, i)) {
                        zzni.zzr(obj, j, zzni.zzd(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 6:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 7:
                    if (zzN(obj2, i)) {
                        zzni.zzm(obj, j, zzni.zzw(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 8:
                    if (zzN(obj2, i)) {
                        zzni.zzs(obj, j, zzni.zzf(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 9:
                    zzE(obj, obj2, i);
                    break;
                case 10:
                    if (zzN(obj2, i)) {
                        zzni.zzs(obj, j, zzni.zzf(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 11:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 12:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 13:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 14:
                    if (zzN(obj2, i)) {
                        zzni.zzr(obj, j, zzni.zzd(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 15:
                    if (zzN(obj2, i)) {
                        zzni.zzq(obj, j, zzni.zzc(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 16:
                    if (zzN(obj2, i)) {
                        zzni.zzr(obj, j, zzni.zzd(obj2, j));
                        zzH(obj, i);
                    }
                    break;
                case 17:
                    zzE(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzkz zzkzVarZzd = (zzkz) zzni.zzf(obj, j);
                    zzkz zzkzVar = (zzkz) zzni.zzf(obj2, j);
                    int size = zzkzVarZzd.size();
                    int size2 = zzkzVar.size();
                    if (size > 0 && size2 > 0) {
                        if (!zzkzVarZzd.zzc()) {
                            zzkzVarZzd = zzkzVarZzd.zzd(size2 + size);
                        }
                        zzkzVarZzd.addAll(zzkzVar);
                    }
                    if (size > 0) {
                        zzkzVar = zzkzVarZzd;
                    }
                    zzni.zzs(obj, j, zzkzVar);
                    break;
                case 50:
                    int i4 = zzmm.zza;
                    zzni.zzs(obj, j, zzls.zzb(zzni.zzf(obj, j), zzni.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case Elf64_Ehdr.e_shentsize /* 58 */:
                case 59:
                    if (zzR(obj2, i3, i)) {
                        zzni.zzs(obj, j, zzni.zzf(obj2, j));
                        zzI(obj, i3, i);
                    }
                    break;
                case 60:
                    zzF(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case 67:
                    if (zzR(obj2, i3, i)) {
                        zzni.zzs(obj, j, zzni.zzf(obj2, j));
                        zzI(obj, i3, i);
                    }
                    break;
                case 68:
                    zzF(obj, obj2, i);
                    break;
            }
        }
        zzmm.zzr(this.zzm, obj, obj2);
        if (this.zzh) {
            zzmm.zzq(this.zzn, obj, obj2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:170:0x051e A[Catch: all -> 0x0545, TryCatch #5 {all -> 0x0545, blocks: (B:168:0x0519, B:170:0x051e, B:171:0x0522), top: B:204:0x0519 }] */
    /* JADX WARN: Code duplicated, block: B:176:0x052e A[LOOP:1: B:174:0x052a->B:176:0x052e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:178:0x053c  */
    /* JADX WARN: Code duplicated, block: B:180:0x0540  */
    /* JADX WARN: Code duplicated, block: B:191:0x0553 A[LOOP:2: B:189:0x054f->B:191:0x0553, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:193:0x0561  */
    /* JADX WARN: Code duplicated, block: B:295:0x0528 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:318:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzh(Object obj, zzmj zzmjVar, zzkd zzkdVar) throws Throwable {
        Object obj2;
        zznb zznbVar;
        int i;
        int i2;
        zzkd zzkdVar2 = zzkdVar;
        if (zzkdVar2 == null) {
            throw null;
        }
        zzD(obj);
        zznb zznbVar2 = this.zzm;
        zzke zzkeVar = this.zzn;
        Object objZzc = null;
        zzki zzkiVarZzc = null;
        while (true) {
            try {
                int iZzc = zzmjVar.zzc();
                int iZzq = zzq(iZzc);
                if (iZzq >= 0) {
                    zzke zzkeVar2 = zzkeVar;
                    zzkd zzkdVar3 = zzkdVar2;
                    zznbVar = zznbVar2;
                    obj2 = obj;
                    int iZzu = zzu(iZzq);
                    try {
                        switch (zzt(iZzu)) {
                            case 0:
                                zzni.zzo(obj, iZzu & 1048575, zzmjVar.zza());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 1:
                                zzni.zzp(obj, iZzu & 1048575, zzmjVar.zzb());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 2:
                                zzni.zzr(obj, iZzu & 1048575, zzmjVar.zzl());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 3:
                                zzni.zzr(obj, iZzu & 1048575, zzmjVar.zzo());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 4:
                                zzni.zzq(obj, iZzu & 1048575, zzmjVar.zzg());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 5:
                                zzni.zzr(obj, iZzu & 1048575, zzmjVar.zzk());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 6:
                                zzni.zzq(obj, iZzu & 1048575, zzmjVar.zzf());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 7:
                                zzni.zzm(obj, iZzu & 1048575, zzmjVar.zzN());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 8:
                                zzG(obj, iZzu, zzmjVar);
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 9:
                                zzlx zzlxVar = (zzlx) zzA(obj, iZzq);
                                zzmjVar.zzu(zzlxVar, zzx(iZzq), zzkdVar3);
                                zzJ(obj, iZzq, zzlxVar);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 10:
                                zzni.zzs(obj, iZzu & 1048575, zzmjVar.zzp());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 11:
                                zzni.zzq(obj, iZzu & 1048575, zzmjVar.zzj());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 12:
                                int iZze = zzmjVar.zze();
                                zzkw zzkwVarZzw = zzw(iZzq);
                                if (zzkwVarZzw == null || zzkwVarZzw.zza(iZze)) {
                                    zzni.zzq(obj, iZzu & 1048575, iZze);
                                    zzH(obj, iZzq);
                                    zznbVar2 = zznbVar;
                                    zzkdVar2 = zzkdVar3;
                                    zzkeVar = zzkeVar2;
                                } else {
                                    objZzc = zzmm.zzp(obj, iZzc, iZze, objZzc, zznbVar);
                                    zznbVar2 = zznbVar;
                                    zzkdVar2 = zzkdVar3;
                                    zzkeVar = zzkeVar2;
                                }
                                break;
                            case 13:
                                zzni.zzq(obj, iZzu & 1048575, zzmjVar.zzh());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 14:
                                zzni.zzr(obj, iZzu & 1048575, zzmjVar.zzm());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 15:
                                zzni.zzq(obj, iZzu & 1048575, zzmjVar.zzi());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 16:
                                zzni.zzr(obj, iZzu & 1048575, zzmjVar.zzn());
                                zzH(obj, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 17:
                                zzlx zzlxVar2 = (zzlx) zzA(obj, iZzq);
                                zzmjVar.zzt(zzlxVar2, zzx(iZzq), zzkdVar3);
                                zzJ(obj, iZzq, zzlxVar2);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 18:
                                zzmjVar.zzx(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 19:
                                zzmjVar.zzB(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 20:
                                zzmjVar.zzE(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 21:
                                zzmjVar.zzM(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 22:
                                zzmjVar.zzD(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 23:
                                zzmjVar.zzA(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 24:
                                zzmjVar.zzz(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 25:
                                zzmjVar.zzv(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 26:
                                if (zzM(iZzu)) {
                                    ((zzjc) zzmjVar).zzK(zzlk.zza(obj, iZzu & 1048575), true);
                                } else {
                                    ((zzjc) zzmjVar).zzK(zzlk.zza(obj, iZzu & 1048575), false);
                                }
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 27:
                                zzmjVar.zzF(zzlk.zza(obj, iZzu & 1048575), zzx(iZzq), zzkdVar3);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 28:
                                zzmjVar.zzw(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 29:
                                zzmjVar.zzL(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 30:
                                List listZza = zzlk.zza(obj, iZzu & 1048575);
                                zzmjVar.zzy(listZza);
                                objZzc = zzmm.zzo(obj2, iZzc, listZza, zzw(iZzq), objZzc, zznbVar);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 31:
                                zzmjVar.zzG(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 32:
                                zzmjVar.zzH(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 33:
                                zzmjVar.zzI(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 34:
                                zzmjVar.zzJ(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 35:
                                zzmjVar.zzx(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 36:
                                zzmjVar.zzB(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 37:
                                zzmjVar.zzE(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 38:
                                zzmjVar.zzM(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 39:
                                zzmjVar.zzD(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 40:
                                zzmjVar.zzA(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 41:
                                zzmjVar.zzz(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 42:
                                zzmjVar.zzv(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 43:
                                zzmjVar.zzL(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 44:
                                List listZza2 = zzlk.zza(obj, iZzu & 1048575);
                                zzmjVar.zzy(listZza2);
                                zznbVar2 = zznbVar;
                                Object obj3 = objZzc;
                                try {
                                    try {
                                        objZzc = zzmm.zzo(obj2, iZzc, listZza2, zzw(iZzq), obj3, zznbVar2);
                                        zznbVar2 = zznbVar2;
                                        zzkdVar2 = zzkdVar3;
                                        zzkeVar = zzkeVar2;
                                    } catch (zzlb e) {
                                        obj2 = obj2;
                                        objZzc = obj3;
                                        zznbVar = zznbVar2;
                                        try {
                                            zznbVar.zzs(zzmjVar);
                                            if (objZzc == null) {
                                                objZzc = zznbVar.zzc(obj);
                                            }
                                            if (!zznbVar.zzr(objZzc, zzmjVar)) {
                                                for (i2 = this.zzk; i2 < this.zzl; i2++) {
                                                    zzy(obj2, this.zzj[i2], objZzc, zznbVar, obj);
                                                }
                                                if (objZzc != null) {
                                                    zznbVar.zzn(obj, objZzc);
                                                }
                                            }
                                            zznbVar2 = zznbVar;
                                            zzkdVar2 = zzkdVar3;
                                            zzkeVar = zzkeVar2;
                                        } catch (Throwable th) {
                                            th = th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        obj2 = obj2;
                                        objZzc = obj3;
                                        zznbVar = zznbVar2;
                                        for (i = this.zzk; i < this.zzl; i++) {
                                            zzy(obj2, this.zzj[i], objZzc, zznbVar, obj);
                                        }
                                        if (objZzc != null) {
                                            zznbVar.zzn(obj, objZzc);
                                        }
                                        throw th;
                                    }
                                } catch (zzlb e2) {
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                                break;
                            case 45:
                                zzmjVar.zzG(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 46:
                                zzmjVar.zzH(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 47:
                                zzmjVar.zzI(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 48:
                                zzmjVar.zzJ(zzlk.zza(obj, iZzu & 1048575));
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                                zzmjVar.zzC(zzlk.zza(obj, iZzu & 1048575), zzx(iZzq), zzkdVar3);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 50:
                                Object objZzz = zzz(iZzq);
                                long jZzu = zzu(iZzq) & 1048575;
                                Object objZzf = zzni.zzf(obj, jZzu);
                                if (objZzf == null) {
                                    objZzf = zzlr.zza().zzb();
                                    zzni.zzs(obj, jZzu, objZzf);
                                } else if (zzls.zza(objZzf)) {
                                    Object objZzb = zzlr.zza().zzb();
                                    zzls.zzb(objZzb, objZzf);
                                    zzni.zzs(obj, jZzu, objZzb);
                                    objZzf = objZzb;
                                }
                                throw null;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                zzni.zzs(obj, iZzu & 1048575, Double.valueOf(zzmjVar.zza()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 52:
                                zzni.zzs(obj, iZzu & 1048575, Float.valueOf(zzmjVar.zzb()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 53:
                                zzni.zzs(obj, iZzu & 1048575, Long.valueOf(zzmjVar.zzl()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 54:
                                zzni.zzs(obj, iZzu & 1048575, Long.valueOf(zzmjVar.zzo()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 55:
                                zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(zzmjVar.zzg()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 56:
                                zzni.zzs(obj, iZzu & 1048575, Long.valueOf(zzmjVar.zzk()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 57:
                                zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(zzmjVar.zzf()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case Elf64_Ehdr.e_shentsize /* 58 */:
                                zzni.zzs(obj, iZzu & 1048575, Boolean.valueOf(zzmjVar.zzN()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 59:
                                zzG(obj, iZzu, zzmjVar);
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 60:
                                zzlx zzlxVar3 = (zzlx) zzB(obj, iZzc, iZzq);
                                zzmjVar.zzu(zzlxVar3, zzx(iZzq), zzkdVar3);
                                zzK(obj, iZzc, iZzq, zzlxVar3);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 61:
                                zzni.zzs(obj, iZzu & 1048575, zzmjVar.zzp());
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 62:
                                zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(zzmjVar.zzj()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 63:
                                int iZze2 = zzmjVar.zze();
                                zzkw zzkwVarZzw2 = zzw(iZzq);
                                if (zzkwVarZzw2 == null || zzkwVarZzw2.zza(iZze2)) {
                                    zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(iZze2));
                                    zzI(obj, iZzc, iZzq);
                                    zznbVar2 = zznbVar;
                                    zzkdVar2 = zzkdVar3;
                                    zzkeVar = zzkeVar2;
                                } else {
                                    objZzc = zzmm.zzp(obj, iZzc, iZze2, objZzc, zznbVar);
                                    zznbVar2 = zznbVar;
                                    zzkdVar2 = zzkdVar3;
                                    zzkeVar = zzkeVar2;
                                }
                                break;
                            case 64:
                                zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(zzmjVar.zzh()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 65:
                                zzni.zzs(obj, iZzu & 1048575, Long.valueOf(zzmjVar.zzm()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                zzni.zzs(obj, iZzu & 1048575, Integer.valueOf(zzmjVar.zzi()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 67:
                                zzni.zzs(obj, iZzu & 1048575, Long.valueOf(zzmjVar.zzn()));
                                zzI(obj, iZzc, iZzq);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            case 68:
                                zzlx zzlxVar4 = (zzlx) zzB(obj, iZzc, iZzq);
                                zzmjVar.zzt(zzlxVar4, zzx(iZzq), zzkdVar3);
                                zzK(obj, iZzc, iZzq, zzlxVar4);
                                zznbVar2 = zznbVar;
                                zzkdVar2 = zzkdVar3;
                                zzkeVar = zzkeVar2;
                                break;
                            default:
                                if (objZzc == null) {
                                    objZzc = zznbVar.zzc(obj);
                                }
                                try {
                                    if (zznbVar.zzr(objZzc, zzmjVar)) {
                                        zznbVar2 = zznbVar;
                                        zzkdVar2 = zzkdVar3;
                                        zzkeVar = zzkeVar2;
                                    } else {
                                        for (int i3 = this.zzk; i3 < this.zzl; i3++) {
                                            zzy(obj2, this.zzj[i3], objZzc, zznbVar, obj);
                                        }
                                    }
                                } catch (zzlb e3) {
                                    zznbVar.zzs(zzmjVar);
                                    if (objZzc == null) {
                                        objZzc = zznbVar.zzc(obj);
                                    }
                                    if (!zznbVar.zzr(objZzc, zzmjVar)) {
                                        while (i2 < this.zzl) {
                                            zzy(obj2, this.zzj[i2], objZzc, zznbVar, obj);
                                        }
                                        if (objZzc != null) {
                                            zznbVar.zzn(obj, objZzc);
                                        }
                                    }
                                    zznbVar2 = zznbVar;
                                    zzkdVar2 = zzkdVar3;
                                    zzkeVar = zzkeVar2;
                                }
                                break;
                        }
                    } catch (zzlb e4) {
                    }
                } else if (iZzc == Integer.MAX_VALUE) {
                    for (int i4 = this.zzk; i4 < this.zzl; i4++) {
                        zznb zznbVar3 = zznbVar2;
                        zzy(obj, this.zzj[i4], objZzc, zznbVar3, obj);
                        zznbVar2 = zznbVar3;
                    }
                    zznbVar = zznbVar2;
                } else {
                    try {
                        Object objZzd = !this.zzh ? null : zzkeVar.zzd(zzkdVar2, this.zzg, iZzc);
                        if (objZzd != null) {
                            if (zzkiVarZzc == null) {
                                zzkiVarZzc = zzkeVar.zzc(obj);
                            }
                            zzke zzkeVar3 = zzkeVar;
                            obj2 = obj;
                            zznb zznbVar4 = zznbVar2;
                            zzki zzkiVar = zzkiVarZzc;
                            Object obj4 = objZzc;
                            try {
                                zzkeVar3.zze(obj2, zzmjVar, objZzd, zzkdVar2, zzkiVar, obj4, zznbVar4);
                                objZzc = obj4;
                                zzkiVarZzc = zzkiVar;
                                zzkeVar = zzkeVar3;
                                zznbVar2 = zznbVar4;
                                zzkdVar2 = zzkdVar2;
                            } catch (Throwable th4) {
                                th = th4;
                                objZzc = obj4;
                                zznbVar = zznbVar4;
                            }
                        } else {
                            zzke zzkeVar4 = zzkeVar;
                            zzkd zzkdVar4 = zzkdVar2;
                            zznbVar = zznbVar2;
                            obj2 = obj;
                            try {
                                zznbVar.zzs(zzmjVar);
                                if (objZzc == null) {
                                    objZzc = zznbVar.zzc(obj);
                                }
                                try {
                                    if (zznbVar.zzr(objZzc, zzmjVar)) {
                                        zznbVar2 = zznbVar;
                                        zzkdVar2 = zzkdVar4;
                                        zzkeVar = zzkeVar4;
                                    } else {
                                        for (int i5 = this.zzk; i5 < this.zzl; i5++) {
                                            zzy(obj2, this.zzj[i5], objZzc, zznbVar, obj);
                                        }
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        obj2 = obj;
                        zznbVar = zznbVar2;
                    }
                }
            } catch (Throwable th8) {
                th = th8;
                obj2 = obj;
            }
            while (i < this.zzl) {
                zzy(obj2, this.zzj[i], objZzc, zznbVar, obj);
            }
            if (objZzc != null) {
                zznbVar.zzn(obj, objZzc);
            }
            throw th;
        }
        if (objZzc != null) {
            zznbVar.zzn(obj, objZzc);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzik zzikVar) throws IOException {
        zzc(obj, bArr, i, i2, 0, zzikVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0024  */
    @Override // com.google.android.recaptcha.internal.zzmk
    public final void zzj(Object obj, zzno zznoVar) throws IOException {
        Map.Entry entry;
        Iterator it;
        int i;
        int i2;
        int i3;
        zzma<T> zzmaVar = this;
        if (zzmaVar.zzh) {
            zzki zzkiVarZzb = zzmaVar.zzn.zzb(obj);
            if (zzkiVarZzb.zza.isEmpty()) {
                entry = null;
                it = null;
            } else {
                Iterator itZzf = zzkiVarZzb.zzf();
                entry = (Map.Entry) itZzf.next();
                it = itZzf;
            }
        } else {
            entry = null;
            it = null;
        }
        int[] iArr = zzmaVar.zzc;
        Unsafe unsafe = zzb;
        int i4 = 0;
        int i5 = 1048575;
        int i6 = 0;
        while (i4 < iArr.length) {
            int iZzu = zzmaVar.zzu(i4);
            int[] iArr2 = zzmaVar.zzc;
            int iZzt = zzt(iZzu);
            int i7 = iArr2[i4];
            if (iZzt <= 17) {
                int i8 = iArr2[i4 + 2];
                int i9 = i8 & 1048575;
                if (i9 != i5) {
                    i6 = i9 == 1048575 ? 0 : unsafe.getInt(obj, i9);
                    i5 = i9;
                }
                i = i5;
                i2 = i6;
                i3 = 1 << (i8 >>> 20);
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
            }
            while (entry != null && zzmaVar.zzn.zza(entry) <= i7) {
                zzmaVar.zzn.zzi(zznoVar, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j = iZzu & 1048575;
            switch (iZzt) {
                case 0:
                    zzmaVar = this;
                    if (zzmaVar.zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzf(i7, zzni.zza(obj, j));
                    }
                    break;
                case 1:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzo(i7, zzni.zzb(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 2:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzt(i7, unsafe.getLong(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 3:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzK(i7, unsafe.getLong(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 4:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzr(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 5:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzm(i7, unsafe.getLong(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 6:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzk(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 7:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzb(i7, zzni.zzw(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 8:
                    if (zzmaVar.zzO(obj, i4, i, i2, i3)) {
                        zzT(i7, unsafe.getObject(obj, j), zznoVar);
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 9:
                    zzmaVar = this;
                    if (zzmaVar.zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzv(i7, unsafe.getObject(obj, j), zzmaVar.zzx(i4));
                    }
                    break;
                case 10:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzd(i7, (zziv) unsafe.getObject(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 11:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzI(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 12:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzi(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 13:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzx(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 14:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzz(i7, unsafe.getLong(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 15:
                    if (zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzB(i7, unsafe.getInt(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 16:
                    if (zzmaVar.zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzD(i7, unsafe.getLong(obj, j));
                        zzmaVar = this;
                    } else {
                        zzmaVar = this;
                    }
                    break;
                case 17:
                    if (zzmaVar.zzO(obj, i4, i, i2, i3)) {
                        zznoVar.zzq(i7, unsafe.getObject(obj, j), zzmaVar.zzx(i4));
                    }
                    break;
                case 18:
                    zzmm.zzu(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 19:
                    zzmm.zzy(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 20:
                    zzmm.zzA(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 21:
                    zzmm.zzG(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 22:
                    zzmm.zzz(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 23:
                    zzmm.zzx(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 24:
                    zzmm.zzw(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 25:
                    zzmm.zzt(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 26:
                    int i10 = zzmaVar.zzc[i4];
                    List list = (List) unsafe.getObject(obj, j);
                    int i11 = zzmm.zza;
                    if (list != null && !list.isEmpty()) {
                        zznoVar.zzH(i10, list);
                    }
                    break;
                case 27:
                    int i12 = zzmaVar.zzc[i4];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zzmk zzmkVarZzx = zzmaVar.zzx(i4);
                    int i13 = zzmm.zza;
                    if (list2 != null && !list2.isEmpty()) {
                        for (int i14 = 0; i14 < list2.size(); i14++) {
                            ((zzjh) zznoVar).zzv(i12, list2.get(i14), zzmkVarZzx);
                        }
                    }
                    break;
                case 28:
                    int i15 = zzmaVar.zzc[i4];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i16 = zzmm.zza;
                    if (list3 != null && !list3.isEmpty()) {
                        zznoVar.zze(i15, list3);
                    }
                    break;
                case 29:
                    zzmm.zzF(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 30:
                    zzmm.zzv(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 31:
                    zzmm.zzB(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 32:
                    zzmm.zzC(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 33:
                    zzmm.zzD(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 34:
                    zzmm.zzE(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, false);
                    break;
                case 35:
                    zzmm.zzu(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 36:
                    zzmm.zzy(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 37:
                    zzmm.zzA(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 38:
                    zzmm.zzG(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 39:
                    zzmm.zzz(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 40:
                    zzmm.zzx(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 41:
                    zzmm.zzw(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 42:
                    zzmm.zzt(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 43:
                    zzmm.zzF(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 44:
                    zzmm.zzv(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 45:
                    zzmm.zzB(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 46:
                    zzmm.zzC(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 47:
                    zzmm.zzD(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case 48:
                    zzmm.zzE(zzmaVar.zzc[i4], (List) unsafe.getObject(obj, j), zznoVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    int i17 = zzmaVar.zzc[i4];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zzmk zzmkVarZzx2 = zzmaVar.zzx(i4);
                    int i18 = zzmm.zza;
                    if (list4 != null && !list4.isEmpty()) {
                        for (int i19 = 0; i19 < list4.size(); i19++) {
                            ((zzjh) zznoVar).zzq(i17, list4.get(i19), zzmkVarZzx2);
                        }
                    }
                    break;
                case 50:
                    if (unsafe.getObject(obj, j) != null) {
                        throw null;
                    }
                    break;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzf(i7, zzn(obj, j));
                    }
                    break;
                case 52:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzo(i7, zzo(obj, j));
                    }
                    break;
                case 53:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzt(i7, zzv(obj, j));
                    }
                    break;
                case 54:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzK(i7, zzv(obj, j));
                    }
                    break;
                case 55:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzr(i7, zzp(obj, j));
                    }
                    break;
                case 56:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzm(i7, zzv(obj, j));
                    }
                    break;
                case 57:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzk(i7, zzp(obj, j));
                    }
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzb(i7, zzS(obj, j));
                    }
                    break;
                case 59:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zzT(i7, unsafe.getObject(obj, j), zznoVar);
                    }
                    break;
                case 60:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzv(i7, unsafe.getObject(obj, j), zzmaVar.zzx(i4));
                    }
                    break;
                case 61:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzd(i7, (zziv) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzI(i7, zzp(obj, j));
                    }
                    break;
                case 63:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzi(i7, zzp(obj, j));
                    }
                    break;
                case 64:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzx(i7, zzp(obj, j));
                    }
                    break;
                case 65:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzz(i7, zzv(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzB(i7, zzp(obj, j));
                    }
                    break;
                case 67:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzD(i7, zzv(obj, j));
                    }
                    break;
                case 68:
                    if (zzmaVar.zzR(obj, i7, i4)) {
                        zznoVar.zzq(i7, unsafe.getObject(obj, j), zzmaVar.zzx(i4));
                    }
                    break;
            }
            i4 += 3;
            i6 = i2;
            i5 = i;
            entry = entry;
        }
        while (entry != null) {
            zzmaVar.zzn.zzi(zznoVar, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        zznb zznbVar = zzmaVar.zzm;
        zznbVar.zzq(zznbVar.zzd(obj), zznoVar);
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final boolean zzk(Object obj, Object obj2) {
        boolean zZzH;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzu = zzu(i);
            long j = iZzu & 1048575;
            switch (zzt(iZzu)) {
                case 0:
                    if (!zzL(obj, obj2, i) || Double.doubleToLongBits(zzni.zza(obj, j)) != Double.doubleToLongBits(zzni.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzL(obj, obj2, i) || Float.floatToIntBits(zzni.zzb(obj, j)) != Float.floatToIntBits(zzni.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzL(obj, obj2, i) || zzni.zzd(obj, j) != zzni.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzL(obj, obj2, i) || zzni.zzd(obj, j) != zzni.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzL(obj, obj2, i) || zzni.zzd(obj, j) != zzni.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzL(obj, obj2, i) || zzni.zzw(obj, j) != zzni.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzL(obj, obj2, i) || !zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzL(obj, obj2, i) || !zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzL(obj, obj2, i) || !zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzL(obj, obj2, i) || zzni.zzd(obj, j) != zzni.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzL(obj, obj2, i) || zzni.zzc(obj, j) != zzni.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzL(obj, obj2, i) || zzni.zzd(obj, j) != zzni.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzL(obj, obj2, i) || !zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zZzH = zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j));
                    break;
                case 50:
                    zZzH = zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case Elf64_Ehdr.e_shentsize /* 58 */:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case 67:
                case 68:
                    long jZzr = zzr(i) & 1048575;
                    if (zzni.zzc(obj, jZzr) != zzni.zzc(obj2, jZzr) || !zzmm.zzH(zzni.zzf(obj, j), zzni.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    continue;
                    break;
            }
            if (!zZzH) {
                return false;
            }
        }
        if (!this.zzm.zzd(obj).equals(this.zzm.zzd(obj2))) {
            return false;
        }
        if (this.zzh) {
            return this.zzn.zzb(obj).equals(this.zzn.zzb(obj2));
        }
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzmk
    public final boolean zzl(Object obj) {
        int i;
        int i2;
        Object obj2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i3 < this.zzk) {
            int[] iArr = this.zzj;
            int[] iArr2 = this.zzc;
            int i6 = iArr[i3];
            int i7 = iArr2[i6];
            int iZzu = zzu(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i5) {
                i = i5;
                i2 = i4;
            } else if (i9 != 1048575) {
                i2 = zzb.getInt(obj, i9);
                i = i9;
            } else {
                i2 = i4;
                i = i9;
            }
            if ((268435456 & iZzu) != 0) {
                obj2 = obj;
                if (zzO(obj2, i6, i, i2, i10)) {
                }
                return false;
            }
            obj2 = obj;
            switch (zzt(iZzu)) {
                case 9:
                case 17:
                    if (zzO(obj2, i6, i, i2, i10) && !zzP(obj2, iZzu, zzx(i6))) {
                        return false;
                    }
                    i3++;
                    obj = obj2;
                    i5 = i;
                    i4 = i2;
                    break;
                    break;
                case 27:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    List list = (List) zzni.zzf(obj2, iZzu & 1048575);
                    if (list.isEmpty()) {
                        continue;
                    } else {
                        zzmk zzmkVarZzx = zzx(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzmkVarZzx.zzl(list.get(i11))) {
                                return false;
                            }
                        }
                    }
                    i3++;
                    obj = obj2;
                    i5 = i;
                    i4 = i2;
                    break;
                case 50:
                    if (!((zzlr) zzni.zzf(obj2, iZzu & 1048575)).isEmpty()) {
                        throw null;
                    }
                    continue;
                    i3++;
                    obj = obj2;
                    i5 = i;
                    i4 = i2;
                    break;
                    break;
                case 60:
                case 68:
                    if (!zzR(obj2, i7, i6)) {
                        continue;
                    } else if (!zzP(obj2, iZzu, zzx(i6))) {
                        return false;
                    }
                    i3++;
                    obj = obj2;
                    i5 = i;
                    i4 = i2;
                default:
                    continue;
                    i3++;
                    obj = obj2;
                    i5 = i;
                    i4 = i2;
                    break;
            }
        }
        return !this.zzh || this.zzn.zzb(obj).zzk();
    }
}
