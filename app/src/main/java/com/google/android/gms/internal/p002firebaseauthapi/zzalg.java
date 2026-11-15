package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.soloader.Elf64_Ehdr;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalg<T> implements zzalv<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzamp.zzb();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzalc zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzalk zzn;
    private final zzakm zzo;
    private final zzamo<?, ?> zzp;
    private final zzajm<?> zzq;
    private final zzakz zzr;

    private static <T> double zza(T t, long j) {
        return ((Double) zzamp.zze(t, j)).doubleValue();
    }

    private static <T> float zzb(T t, long j) {
        return ((Float) zzamp.zze(t, j)).floatValue();
    }

    private static int zza(byte[] bArr, int i, int i2, zzamw zzamwVar, Class<?> cls, zzaik zzaikVar) throws IOException {
        switch (zzalj.zza[zzamwVar.ordinal()]) {
            case 1:
                int iZzd = zzail.zzd(bArr, i, zzaikVar);
                zzaikVar.zzc = Boolean.valueOf(zzaikVar.zzb != 0);
                return iZzd;
            case 2:
                return zzail.zza(bArr, i, zzaikVar);
            case 3:
                zzaikVar.zzc = Double.valueOf(zzail.zza(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzaikVar.zzc = Integer.valueOf(zzail.zzc(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzaikVar.zzc = Long.valueOf(zzail.zzd(bArr, i));
                return i + 8;
            case 8:
                zzaikVar.zzc = Float.valueOf(zzail.zzb(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int iZzc = zzail.zzc(bArr, i, zzaikVar);
                zzaikVar.zzc = Integer.valueOf(zzaikVar.zza);
                return iZzc;
            case 12:
            case 13:
                int iZzd2 = zzail.zzd(bArr, i, zzaikVar);
                zzaikVar.zzc = Long.valueOf(zzaikVar.zzb);
                return iZzd2;
            case 14:
                return zzail.zza(zzalr.zza().zza((Class) cls), bArr, i, i2, zzaikVar);
            case 15:
                int iZzc2 = zzail.zzc(bArr, i, zzaikVar);
                zzaikVar.zzc = Integer.valueOf(zzajb.zze(zzaikVar.zza));
                return iZzc2;
            case 16:
                int iZzd3 = zzail.zzd(bArr, i, zzaikVar);
                zzaikVar.zzc = Long.valueOf(zzajb.zza(zzaikVar.zzb));
                return iZzd3;
            case 17:
                return zzail.zzb(bArr, i, zzaikVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final int zza(T t) {
        int i;
        int i2;
        zzalg<T> zzalgVar = this;
        T t2 = t;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int iZza = 0;
        int i6 = 1048575;
        while (i4 < zzalgVar.zzc.length) {
            int iZzc = zzalgVar.zzc(i4);
            int i7 = (267386880 & iZzc) >>> 20;
            int i8 = zzalgVar.zzc[i4];
            int i9 = zzalgVar.zzc[i4 + 2];
            int i10 = i9 & i3;
            if (i7 > 17) {
                i = 0;
            } else {
                if (i10 != i6) {
                    if (i10 == i3) {
                        i2 = 0;
                    } else {
                        i2 = unsafe.getInt(t2, i10);
                    }
                    i5 = i2;
                    i6 = i10;
                }
                i = 1 << (i9 >>> 20);
            }
            long j = iZzc & i3;
            if (i7 < zzajs.DOUBLE_LIST_PACKED.zza() || i7 <= zzajs.SINT64_LIST_PACKED.zza()) {
            }
            int i11 = iZza;
            switch (i7) {
                case 0:
                    zzalgVar = this;
                    t2 = t;
                    if (zzalgVar.zza(t2, i4, i6, i5, i)) {
                        iZza = i11 + zzajg.zza(i8, 0.0d);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 1:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zza(i8, 0.0f);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 2:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzb(i8, unsafe.getLong(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 3:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zze(i8, unsafe.getLong(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 4:
                    t2 = t;
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzd(i8, unsafe.getInt(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 5:
                    if (!zza(t, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zza(i8, 0L);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 6:
                    if (!zza(t, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zzc(i8, 0);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 7:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zza(i8, true);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 8:
                    if (!zzalgVar.zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                        break;
                    } else {
                        Object object = unsafe.getObject(t2, j);
                        if (object instanceof zzaip) {
                            iZza = i11 + zzajg.zza(i8, (zzaip) object);
                            zzalgVar = this;
                        } else {
                            iZza = i11 + zzajg.zza(i8, (String) object);
                            zzalgVar = this;
                        }
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 9:
                    zzalgVar = this;
                    if (zzalgVar.zza(t2, i4, i6, i5, i)) {
                        iZza = i11 + zzalx.zza(i8, unsafe.getObject(t2, j), (zzalv<?>) zzalgVar.zze(i4));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 10:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zza(i8, (zzaip) unsafe.getObject(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 11:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzg(i8, unsafe.getInt(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 12:
                    t2 = t;
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzb(i8, unsafe.getInt(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 13:
                    if (!zza(t, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zze(i8, 0);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 14:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                        t2 = t;
                    } else {
                        iZza = i11 + zzajg.zzc(i8, 0L);
                        zzalgVar = this;
                        t2 = t;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 15:
                    if (!zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzf(i8, unsafe.getInt(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 16:
                    if (!zzalgVar.zza(t2, i4, i6, i5, i)) {
                        zzalgVar = this;
                    } else {
                        iZza = i11 + zzajg.zzd(i8, unsafe.getLong(t2, j));
                        zzalgVar = this;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 17:
                    if (zzalgVar.zza(t2, i4, i6, i5, i)) {
                        iZza = i11 + zzajg.zza(i8, (zzalc) unsafe.getObject(t2, j), zzalgVar.zze(i4));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 18:
                    iZza = i11 + zzalx.zzd(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 19:
                    iZza = i11 + zzalx.zzc(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 20:
                    iZza = i11 + zzalx.zzf(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 21:
                    iZza = i11 + zzalx.zzj(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 22:
                    iZza = i11 + zzalx.zze(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 23:
                    iZza = i11 + zzalx.zzd(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 24:
                    iZza = i11 + zzalx.zzc(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 25:
                    iZza = i11 + zzalx.zza(i8, (List<?>) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 26:
                    iZza = i11 + zzalx.zzb(i8, (List) unsafe.getObject(t2, j));
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 27:
                    iZza = i11 + zzalx.zzb(i8, (List<?>) unsafe.getObject(t2, j), (zzalv<?>) zzalgVar.zze(i4));
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 28:
                    iZza = i11 + zzalx.zza(i8, (List<zzaip>) unsafe.getObject(t2, j));
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 29:
                    iZza = i11 + zzalx.zzi(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 30:
                    iZza = i11 + zzalx.zzb(i8, (List<Integer>) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 31:
                    iZza = i11 + zzalx.zzc(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 32:
                    iZza = i11 + zzalx.zzd(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 33:
                    iZza = i11 + zzalx.zzg(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 34:
                    iZza = i11 + zzalx.zzh(i8, (List) unsafe.getObject(t2, j), false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 35:
                    int iZzd = zzalx.zzd((List) unsafe.getObject(t2, j));
                    if (iZzd > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzd) + iZzd;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 36:
                    int iZzc2 = zzalx.zzc((List) unsafe.getObject(t2, j));
                    if (iZzc2 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzc2) + iZzc2;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 37:
                    int iZzf = zzalx.zzf((List) unsafe.getObject(t2, j));
                    if (iZzf > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzf) + iZzf;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 38:
                    int iZzj = zzalx.zzj((List) unsafe.getObject(t2, j));
                    if (iZzj > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzj) + iZzj;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 39:
                    int iZze = zzalx.zze((List) unsafe.getObject(t2, j));
                    if (iZze > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZze) + iZze;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 40:
                    int iZzd2 = zzalx.zzd((List) unsafe.getObject(t2, j));
                    if (iZzd2 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzd2) + iZzd2;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 41:
                    int iZzc3 = zzalx.zzc((List) unsafe.getObject(t2, j));
                    if (iZzc3 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzc3) + iZzc3;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 42:
                    int iZza2 = zzalx.zza((List<?>) unsafe.getObject(t2, j));
                    if (iZza2 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZza2) + iZza2;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 43:
                    int iZzi = zzalx.zzi((List) unsafe.getObject(t2, j));
                    if (iZzi > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzi) + iZzi;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 44:
                    int iZzb = zzalx.zzb((List) unsafe.getObject(t2, j));
                    if (iZzb > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzb) + iZzb;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 45:
                    int iZzc4 = zzalx.zzc((List) unsafe.getObject(t2, j));
                    if (iZzc4 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzc4) + iZzc4;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 46:
                    int iZzd3 = zzalx.zzd((List) unsafe.getObject(t2, j));
                    if (iZzd3 > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzd3) + iZzd3;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 47:
                    int iZzg = zzalx.zzg((List) unsafe.getObject(t2, j));
                    if (iZzg > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzg) + iZzg;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 48:
                    int iZzh = zzalx.zzh((List) unsafe.getObject(t2, j));
                    if (iZzh > 0) {
                        iZza = i11 + zzajg.zzi(i8) + zzajg.zzj(iZzh) + iZzh;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    iZza = i11 + zzalx.zza(i8, (List<zzalc>) unsafe.getObject(t2, j), (zzalv<?>) zzalgVar.zze(i4));
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 50:
                    iZza = i11 + zzalgVar.zzr.zza(i8, unsafe.getObject(t2, j), zzalgVar.zzf(i4));
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, 0.0d);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 52:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, 0.0f);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 53:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzb(i8, zzd(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 54:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zze(i8, zzd(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 55:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzd(i8, zzc(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 56:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, 0L);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 57:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzc(i8, 0);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, true);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 59:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        Object object2 = unsafe.getObject(t2, j);
                        if (object2 instanceof zzaip) {
                            iZza = i11 + zzajg.zza(i8, (zzaip) object2);
                        } else {
                            iZza = i11 + zzajg.zza(i8, (String) object2);
                        }
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 60:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzalx.zza(i8, unsafe.getObject(t2, j), (zzalv<?>) zzalgVar.zze(i4));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 61:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, (zzaip) unsafe.getObject(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 62:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzg(i8, zzc(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 63:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzb(i8, zzc(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 64:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zze(i8, 0);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 65:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzc(i8, 0L);
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzf(i8, zzc(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 67:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zzd(i8, zzd(t2, j));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 68:
                    if (zzalgVar.zzc(t2, i8, i4)) {
                        iZza = i11 + zzajg.zza(i8, (zzalc) unsafe.getObject(t2, j), zzalgVar.zze(i4));
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
            }
            iZza = i11;
            i4 += 3;
            i3 = 1048575;
        }
        zzamo<?, ?> zzamoVar = zzalgVar.zzp;
        int iZza3 = iZza + zzamoVar.zza(zzamoVar.zzd(t2));
        if (zzalgVar.zzh) {
            zzajr<T> zzajrVarZza = zzalgVar.zzq.zza(t2);
            int iZza4 = zzajrVarZza.zza.zza();
            int iZza5 = 0;
            for (int i12 = 0; i12 < iZza4; i12++) {
                Map.Entry entryZza = zzajrVarZza.zza.zza(i12);
                iZza5 += zzajr.zza((zzajt<?>) entryZza.getKey(), entryZza.getValue());
            }
            for (Map.Entry entry : zzajrVarZza.zza.zzb()) {
                iZza5 += zzajr.zza((zzajt<?>) entry.getKey(), entry.getValue());
            }
            return iZza3 + iZza5;
        }
        return iZza3;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final int zzb(T t) {
        int length = this.zzc.length;
        int iZza = 0;
        for (int i = 0; i < length; i += 3) {
            int iZzc = zzc(i);
            int i2 = this.zzc[i];
            long j = 1048575 & iZzc;
            int iHashCode = 37;
            switch ((iZzc & 267386880) >>> 20) {
                case 0:
                    iZza = (iZza * 53) + zzakb.zza(Double.doubleToLongBits(zzamp.zza(t, j)));
                    break;
                case 1:
                    iZza = (iZza * 53) + Float.floatToIntBits(zzamp.zzb(t, j));
                    break;
                case 2:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzd(t, j));
                    break;
                case 3:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzd(t, j));
                    break;
                case 4:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 5:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzd(t, j));
                    break;
                case 6:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 7:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzh(t, j));
                    break;
                case 8:
                    iZza = (iZza * 53) + ((String) zzamp.zze(t, j)).hashCode();
                    break;
                case 9:
                    Object objZze = zzamp.zze(t, j);
                    if (objZze != null) {
                        iHashCode = objZze.hashCode();
                    }
                    iZza = (iZza * 53) + iHashCode;
                    break;
                case 10:
                    iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    break;
                case 11:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 12:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 13:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 14:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzd(t, j));
                    break;
                case 15:
                    iZza = (iZza * 53) + zzamp.zzc(t, j);
                    break;
                case 16:
                    iZza = (iZza * 53) + zzakb.zza(zzamp.zzd(t, j));
                    break;
                case 17:
                    Object objZze2 = zzamp.zze(t, j);
                    if (objZze2 != null) {
                        iHashCode = objZze2.hashCode();
                    }
                    iZza = (iZza * 53) + iHashCode;
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
                    iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    break;
                case 50:
                    iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(Double.doubleToLongBits(zza(t, j)));
                    }
                    break;
                case 52:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + Float.floatToIntBits(zzb(t, j));
                    }
                    break;
                case 53:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zzd(t, j));
                    }
                    break;
                case 54:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zzd(t, j));
                    }
                    break;
                case 55:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case 56:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zzd(t, j));
                    }
                    break;
                case 57:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zze(t, j));
                    }
                    break;
                case 59:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + ((String) zzamp.zze(t, j)).hashCode();
                    }
                    break;
                case 60:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    }
                    break;
                case 61:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    }
                    break;
                case 62:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case 63:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case 64:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case 65:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zzd(t, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzc(t, j);
                    }
                    break;
                case 67:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzakb.zza(zzd(t, j));
                    }
                    break;
                case 68:
                    if (zzc(t, i2, i)) {
                        iZza = (iZza * 53) + zzamp.zze(t, j).hashCode();
                    }
                    break;
            }
        }
        int iHashCode2 = (iZza * 53) + this.zzp.zzd(t).hashCode();
        if (this.zzh) {
            return (iHashCode2 * 53) + this.zzq.zza(t).hashCode();
        }
        return iHashCode2;
    }

    private static <T> int zzc(T t, long j) {
        return ((Integer) zzamp.zze(t, j)).intValue();
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 41361. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    final int zza(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.p002firebaseauthapi.zzaik r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 4136
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzalg.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzaik):int");
    }

    private final int zza(int i) {
        if (i >= this.zze && i <= this.zzf) {
            return zza(i, 0);
        }
        return -1;
    }

    private final int zzb(int i) {
        return this.zzc[i + 2];
    }

    private final int zza(int i, int i2) {
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

    private final int zzc(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzd(T t, long j) {
        return ((Long) zzamp.zze(t, j)).longValue();
    }

    private final zzakd zzd(int i) {
        return (zzakd) this.zzd[((i / 3) << 1) + 1];
    }

    static <T> zzalg<T> zza(Class<T> cls, zzala zzalaVar, zzalk zzalkVar, zzakm zzakmVar, zzamo<?, ?> zzamoVar, zzajm<?> zzajmVar, zzakz zzakzVar) {
        int i;
        int iCharAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr;
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
        zzalt zzaltVar;
        int i17;
        int[] iArr2;
        int i18;
        int iObjectFieldOffset;
        int i19;
        int i20;
        int i21;
        int iObjectFieldOffset2;
        int iObjectFieldOffset3;
        Field fieldZza;
        char cCharAt9;
        int i22;
        Field fieldZza2;
        Field fieldZza3;
        int i23;
        char cCharAt10;
        int i24;
        char cCharAt11;
        int i25;
        char cCharAt12;
        int i26;
        char cCharAt13;
        if (zzalaVar instanceof zzalt) {
            zzalt zzaltVar2 = (zzalt) zzalaVar;
            String strZzd = zzaltVar2.zzd();
            int length = strZzd.length();
            char c = 55296;
            if (strZzd.charAt(0) < 55296) {
                i = 1;
            } else {
                int i27 = 1;
                while (true) {
                    i = i27 + 1;
                    if (strZzd.charAt(i27) < 55296) {
                        break;
                    }
                    i27 = i;
                }
            }
            int i28 = i + 1;
            int iCharAt2 = strZzd.charAt(i);
            if (iCharAt2 >= 55296) {
                int i29 = iCharAt2 & 8191;
                int i30 = 13;
                while (true) {
                    i26 = i28 + 1;
                    cCharAt13 = strZzd.charAt(i28);
                    if (cCharAt13 < 55296) {
                        break;
                    }
                    i29 |= (cCharAt13 & 8191) << i30;
                    i30 += 13;
                    i28 = i26;
                }
                iCharAt2 = i29 | (cCharAt13 << i30);
                i28 = i26;
            }
            if (iCharAt2 == 0) {
                i3 = 0;
                i5 = 0;
                iCharAt = 0;
                i2 = 0;
                i4 = 0;
                i7 = 0;
                iArr = zza;
                i6 = 0;
            } else {
                int i31 = i28 + 1;
                int iCharAt3 = strZzd.charAt(i28);
                if (iCharAt3 >= 55296) {
                    int i32 = iCharAt3 & 8191;
                    int i33 = 13;
                    while (true) {
                        i15 = i31 + 1;
                        cCharAt8 = strZzd.charAt(i31);
                        if (cCharAt8 < 55296) {
                            break;
                        }
                        i32 |= (cCharAt8 & 8191) << i33;
                        i33 += 13;
                        i31 = i15;
                    }
                    iCharAt3 = i32 | (cCharAt8 << i33);
                    i31 = i15;
                }
                int i34 = i31 + 1;
                int iCharAt4 = strZzd.charAt(i31);
                if (iCharAt4 >= 55296) {
                    int i35 = iCharAt4 & 8191;
                    int i36 = 13;
                    while (true) {
                        i14 = i34 + 1;
                        cCharAt7 = strZzd.charAt(i34);
                        if (cCharAt7 < 55296) {
                            break;
                        }
                        i35 |= (cCharAt7 & 8191) << i36;
                        i36 += 13;
                        i34 = i14;
                    }
                    iCharAt4 = i35 | (cCharAt7 << i36);
                    i34 = i14;
                }
                int i37 = i34 + 1;
                int iCharAt5 = strZzd.charAt(i34);
                if (iCharAt5 >= 55296) {
                    int i38 = iCharAt5 & 8191;
                    int i39 = 13;
                    while (true) {
                        i13 = i37 + 1;
                        cCharAt6 = strZzd.charAt(i37);
                        if (cCharAt6 < 55296) {
                            break;
                        }
                        i38 |= (cCharAt6 & 8191) << i39;
                        i39 += 13;
                        i37 = i13;
                    }
                    iCharAt5 = i38 | (cCharAt6 << i39);
                    i37 = i13;
                }
                int i40 = i37 + 1;
                int iCharAt6 = strZzd.charAt(i37);
                if (iCharAt6 >= 55296) {
                    int i41 = iCharAt6 & 8191;
                    int i42 = 13;
                    while (true) {
                        i12 = i40 + 1;
                        cCharAt5 = strZzd.charAt(i40);
                        if (cCharAt5 < 55296) {
                            break;
                        }
                        i41 |= (cCharAt5 & 8191) << i42;
                        i42 += 13;
                        i40 = i12;
                    }
                    iCharAt6 = i41 | (cCharAt5 << i42);
                    i40 = i12;
                }
                int i43 = i40 + 1;
                iCharAt = strZzd.charAt(i40);
                if (iCharAt >= 55296) {
                    int i44 = iCharAt & 8191;
                    int i45 = 13;
                    while (true) {
                        i11 = i43 + 1;
                        cCharAt4 = strZzd.charAt(i43);
                        if (cCharAt4 < 55296) {
                            break;
                        }
                        i44 |= (cCharAt4 & 8191) << i45;
                        i45 += 13;
                        i43 = i11;
                    }
                    iCharAt = i44 | (cCharAt4 << i45);
                    i43 = i11;
                }
                int i46 = i43 + 1;
                int iCharAt7 = strZzd.charAt(i43);
                if (iCharAt7 >= 55296) {
                    int i47 = iCharAt7 & 8191;
                    int i48 = 13;
                    while (true) {
                        i10 = i46 + 1;
                        cCharAt3 = strZzd.charAt(i46);
                        if (cCharAt3 < 55296) {
                            break;
                        }
                        i47 |= (cCharAt3 & 8191) << i48;
                        i48 += 13;
                        i46 = i10;
                    }
                    iCharAt7 = i47 | (cCharAt3 << i48);
                    i46 = i10;
                }
                int i49 = i46 + 1;
                int iCharAt8 = strZzd.charAt(i46);
                if (iCharAt8 >= 55296) {
                    int i50 = iCharAt8 & 8191;
                    int i51 = 13;
                    while (true) {
                        i9 = i49 + 1;
                        cCharAt2 = strZzd.charAt(i49);
                        if (cCharAt2 < 55296) {
                            break;
                        }
                        i50 |= (cCharAt2 & 8191) << i51;
                        i51 += 13;
                        i49 = i9;
                    }
                    iCharAt8 = i50 | (cCharAt2 << i51);
                    i49 = i9;
                }
                int i52 = i49 + 1;
                int iCharAt9 = strZzd.charAt(i49);
                if (iCharAt9 >= 55296) {
                    int i53 = iCharAt9 & 8191;
                    int i54 = 13;
                    while (true) {
                        i8 = i52 + 1;
                        cCharAt = strZzd.charAt(i52);
                        if (cCharAt < 55296) {
                            break;
                        }
                        i53 |= (cCharAt & 8191) << i54;
                        i54 += 13;
                        i52 = i8;
                    }
                    iCharAt9 = i53 | (cCharAt << i54);
                    i52 = i8;
                }
                int[] iArr3 = new int[iCharAt9 + iCharAt7 + iCharAt8];
                int i55 = (iCharAt3 << 1) + iCharAt4;
                int i56 = iCharAt7;
                i2 = iCharAt5;
                i3 = i56;
                i4 = iCharAt6;
                i5 = i55;
                iArr = iArr3;
                i6 = iCharAt3;
                i7 = iCharAt9;
                i28 = i52;
            }
            Unsafe unsafe = zzb;
            Object[] objArrZze = zzaltVar2.zze();
            Class<?> cls2 = zzaltVar2.zza().getClass();
            int[] iArr4 = new int[iCharAt * 3];
            Object[] objArr = new Object[iCharAt << 1];
            int i57 = i7 + i3;
            int i58 = i7;
            int i59 = i57;
            int i60 = 0;
            int i61 = 0;
            while (i28 < length) {
                int i62 = i28 + 1;
                int iCharAt10 = strZzd.charAt(i28);
                if (iCharAt10 < c) {
                    i16 = i62;
                } else {
                    int i63 = iCharAt10 & 8191;
                    int i64 = i62;
                    int i65 = 13;
                    while (true) {
                        i25 = i64 + 1;
                        cCharAt12 = strZzd.charAt(i64);
                        if (cCharAt12 < c) {
                            break;
                        }
                        i63 |= (cCharAt12 & 8191) << i65;
                        i65 += 13;
                        i64 = i25;
                    }
                    iCharAt10 = i63 | (cCharAt12 << i65);
                    i16 = i25;
                }
                int i66 = i16 + 1;
                int iCharAt11 = strZzd.charAt(i16);
                if (iCharAt11 < c) {
                    zzaltVar = zzaltVar2;
                    i17 = i66;
                } else {
                    int i67 = iCharAt11 & 8191;
                    int i68 = i66;
                    int i69 = 13;
                    while (true) {
                        i24 = i68 + 1;
                        cCharAt11 = strZzd.charAt(i68);
                        zzaltVar = zzaltVar2;
                        if (cCharAt11 < 55296) {
                            break;
                        }
                        i67 |= (cCharAt11 & 8191) << i69;
                        i69 += 13;
                        i68 = i24;
                        zzaltVar2 = zzaltVar;
                    }
                    iCharAt11 = i67 | (cCharAt11 << i69);
                    i17 = i24;
                }
                int i70 = iCharAt11 & 255;
                int i71 = length;
                if ((iCharAt11 & 1024) != 0) {
                    iArr[i61] = i60;
                    i61++;
                }
                int i72 = iCharAt10;
                if (i70 >= 51) {
                    int i73 = i17 + 1;
                    int iCharAt12 = strZzd.charAt(i17);
                    char c2 = 55296;
                    if (iCharAt12 >= 55296) {
                        int i74 = iCharAt12 & 8191;
                        int i75 = 13;
                        while (true) {
                            i23 = i73 + 1;
                            cCharAt10 = strZzd.charAt(i73);
                            if (cCharAt10 < c2) {
                                break;
                            }
                            i74 |= (cCharAt10 & 8191) << i75;
                            i75 += 13;
                            i73 = i23;
                            c2 = 55296;
                        }
                        iCharAt12 = i74 | (cCharAt10 << i75);
                        i73 = i23;
                    }
                    int i76 = i70 - 51;
                    int i77 = iCharAt12;
                    if (i76 == 9 || i76 == 17) {
                        objArr[((i60 / 3) << 1) + 1] = objArrZze[i5];
                        i5++;
                    } else if (i76 == 12 && (zzaltVar.zzb().equals(zzalo.PROTO2) || (iCharAt11 & 2048) != 0)) {
                        objArr[((i60 / 3) << 1) + 1] = objArrZze[i5];
                        i5++;
                    }
                    int i78 = i77 << 1;
                    Object obj = objArrZze[i78];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZze[i78] = fieldZza2;
                    }
                    iArr2 = iArr4;
                    i18 = i6;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i79 = i78 + 1;
                    Object obj2 = objArrZze[i79];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZze[i79] = fieldZza3;
                    }
                    iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza3);
                    i19 = i73;
                    i21 = 0;
                } else {
                    iArr2 = iArr4;
                    i18 = i6;
                    int i80 = i5 + 1;
                    Field fieldZza4 = zza(cls2, (String) objArrZze[i5]);
                    if (i70 == 9 || i70 == 17) {
                        objArr[((i60 / 3) << 1) + 1] = fieldZza4.getType();
                    } else if (i70 == 27 || i70 == 49) {
                        objArr[((i60 / 3) << 1) + 1] = objArrZze[i80];
                        i80++;
                    } else if (i70 == 12 || i70 == 30 || i70 == 44) {
                        if (zzaltVar.zzb() == zzalo.PROTO2 || (iCharAt11 & 2048) != 0) {
                            objArr[((i60 / 3) << 1) + 1] = objArrZze[i80];
                            i80++;
                        }
                    } else if (i70 == 50) {
                        int i81 = i58 + 1;
                        iArr[i58] = i60;
                        int i82 = (i60 / 3) << 1;
                        int i83 = i80 + 1;
                        objArr[i82] = objArrZze[i80];
                        if ((iCharAt11 & 2048) == 0) {
                            i80 = i83;
                            i58 = i81;
                        } else {
                            i80 = i83 + 1;
                            objArr[i82 + 1] = objArrZze[i83];
                            i58 = i81;
                        }
                    }
                    int iObjectFieldOffset4 = (int) unsafe.objectFieldOffset(fieldZza4);
                    if (((iCharAt11 & 4096) != 0) && i70 <= 17) {
                        int i84 = i17 + 1;
                        int iCharAt13 = strZzd.charAt(i17);
                        if (iCharAt13 < 55296) {
                            i19 = i84;
                        } else {
                            int i85 = iCharAt13 & 8191;
                            int i86 = 13;
                            while (true) {
                                i19 = i84 + 1;
                                cCharAt9 = strZzd.charAt(i84);
                                if (cCharAt9 < 55296) {
                                    break;
                                }
                                i85 |= (cCharAt9 & 8191) << i86;
                                i86 += 13;
                                i84 = i19;
                            }
                            iCharAt13 = i85 | (cCharAt9 << i86);
                        }
                        int i87 = (i18 << 1) + (iCharAt13 / 32);
                        Object obj3 = objArrZze[i87];
                        if (obj3 instanceof Field) {
                            fieldZza = (Field) obj3;
                        } else {
                            fieldZza = zza(cls2, (String) obj3);
                            objArrZze[i87] = fieldZza;
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza);
                        i20 = iCharAt13 % 32;
                    } else {
                        iObjectFieldOffset = 1048575;
                        i19 = i17;
                        i20 = 0;
                    }
                    if (i70 >= 18 && i70 <= 49) {
                        iArr[i59] = iObjectFieldOffset4;
                        i5 = i80;
                        i59++;
                        i21 = i20;
                        iObjectFieldOffset2 = iObjectFieldOffset4;
                        iObjectFieldOffset3 = iObjectFieldOffset;
                    } else {
                        i5 = i80;
                        i21 = i20;
                        iObjectFieldOffset2 = iObjectFieldOffset4;
                        iObjectFieldOffset3 = iObjectFieldOffset;
                    }
                }
                int i88 = i60 + 1;
                iArr2[i60] = i72;
                int i89 = i88 + 1;
                int i90 = iObjectFieldOffset2;
                int i91 = ((iCharAt11 & 512) != 0 ? 536870912 : 0) | ((iCharAt11 & 256) != 0 ? 268435456 : 0);
                if ((iCharAt11 & 2048) != 0) {
                    i22 = Integer.MIN_VALUE;
                } else {
                    i22 = 0;
                }
                iArr2[i88] = i91 | i22 | (i70 << 20) | i90;
                i60 = i89 + 1;
                iArr2[i89] = (i21 << 20) | iObjectFieldOffset3;
                length = i71;
                i28 = i19;
                zzaltVar2 = zzaltVar;
                i6 = i18;
                iArr4 = iArr2;
                c = 55296;
            }
            return new zzalg<>(iArr4, objArr, i2, i4, zzaltVar2.zza(), false, iArr, i7, i57, zzalkVar, zzakmVar, zzamoVar, zzajmVar, zzakzVar);
        }
        throw new NoSuchMethodError();
    }

    private final zzalv zze(int i) {
        int i2 = (i / 3) << 1;
        zzalv zzalvVar = (zzalv) this.zzd[i2];
        if (zzalvVar != null) {
            return zzalvVar;
        }
        zzalv<T> zzalvVarZza = zzalr.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zzalvVarZza;
        return zzalvVarZza;
    }

    static zzamn zzc(Object obj) {
        zzajy zzajyVar = (zzajy) obj;
        zzamn zzamnVar = zzajyVar.zzb;
        if (zzamnVar == zzamn.zzc()) {
            zzamn zzamnVarZzd = zzamn.zzd();
            zzajyVar.zzb = zzamnVarZzd;
            return zzamnVarZzd;
        }
        return zzamnVar;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzamo<UT, UB> zzamoVar, Object obj2) {
        zzakd zzakdVarZzd;
        int i2 = this.zzc[i];
        Object objZze = zzamp.zze(obj, zzc(i) & 1048575);
        if (objZze == null || (zzakdVarZzd = zzd(i)) == null) {
            return ub;
        }
        return (UB) zza(i, i2, this.zzr.zze(objZze), zzakdVarZzd, ub, zzamoVar, obj2);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzakd zzakdVar, UB ub, zzamo<UT, UB> zzamoVar, Object obj) {
        zzakx<?, ?> zzakxVarZza = this.zzr.zza(zzf(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzakdVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzamoVar.zzc(obj);
                }
                zzaiu zzaiuVarZzc = zzaip.zzc(zzaku.zza(zzakxVarZza, next.getKey(), next.getValue()));
                try {
                    zzaku.zza(zzaiuVarZzc.zzb(), zzakxVarZza, next.getKey(), next.getValue());
                    zzamoVar.zza(ub, i2, zzaiuVarZzc.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final Object zzf(int i) {
        return this.zzd[(i / 3) << 1];
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object zza(T t, int i) {
        zzalv zzalvVarZze = zze(i);
        long jZzc = zzc(i) & 1048575;
        if (!zzc((Object) t, i)) {
            return zzalvVarZze.zza();
        }
        Object object = zzb.getObject(t, jZzc);
        if (zzg(object)) {
            return object;
        }
        Object objZza = zzalvVarZze.zza();
        if (object != null) {
            zzalvVarZze.zza(objZza, object);
        }
        return objZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object zza(T t, int i, int i2) {
        zzalv zzalvVarZze = zze(i2);
        if (!zzc(t, i, i2)) {
            return zzalvVarZze.zza();
        }
        Object object = zzb.getObject(t, zzc(i2) & 1048575);
        if (zzg(object)) {
            return object;
        }
        Object objZza = zzalvVarZze.zza();
        if (object != null) {
            zzalvVarZze.zza(objZza, object);
        }
        return objZza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final T zza() {
        return (T) this.zzn.zza(this.zzg);
    }

    private static Field zza(Class<?> cls, String str) {
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

    private zzalg(int[] iArr, Object[] objArr, int i, int i2, zzalc zzalcVar, boolean z, int[] iArr2, int i3, int i4, zzalk zzalkVar, zzakm zzakmVar, zzamo<?, ?> zzamoVar, zzajm<?> zzajmVar, zzakz zzakzVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzalcVar instanceof zzajy;
        this.zzh = zzajmVar != null && zzajmVar.zza(zzalcVar);
        this.zzj = false;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzn = zzalkVar;
        this.zzo = zzakmVar;
        this.zzp = zzamoVar;
        this.zzq = zzajmVar;
        this.zzg = zzalcVar;
        this.zzr = zzakzVar;
    }

    private static void zzf(Object obj) {
        if (!zzg(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + String.valueOf(obj));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zzd(T t) {
        if (!zzg(t)) {
            return;
        }
        if (t instanceof zzajy) {
            zzajy zzajyVar = (zzajy) t;
            zzajyVar.zzb(Integer.MAX_VALUE);
            zzajyVar.zza = 0;
            zzajyVar.zzt();
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzc = zzc(i);
            long j = 1048575 & iZzc;
            switch ((iZzc & 267386880) >>> 20) {
                case 9:
                case 17:
                    if (zzc((Object) t, i)) {
                        zze(i).zzd(zzb.getObject(t, j));
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
                    this.zzo.zzb(t, j);
                    break;
                case 50:
                    Object object = zzb.getObject(t, j);
                    if (object != null) {
                        zzb.putObject(t, j, this.zzr.zzc(object));
                    }
                    break;
                case 60:
                case 68:
                    if (zzc(t, this.zzc[i], i)) {
                        zze(i).zzd(zzb.getObject(t, j));
                    }
                    break;
            }
        }
        this.zzp.zzf(t);
        if (this.zzh) {
            this.zzq.zzc(t);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, T t2) {
        zzf(t);
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzc = zzc(i);
            long j = 1048575 & iZzc;
            int i2 = this.zzc[i];
            switch ((iZzc & 267386880) >>> 20) {
                case 0:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza(t, j, zzamp.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 1:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzb(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 2:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 3:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 4:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 5:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 6:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 7:
                    if (zzc((Object) t2, i)) {
                        zzamp.zzc(t, j, zzamp.zzh(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 8:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza(t, j, zzamp.zze(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza(t, j, zzamp.zze(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 11:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 12:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 13:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 14:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 15:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 16:
                    if (zzc((Object) t2, i)) {
                        zzamp.zza((Object) t, j, zzamp.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 17:
                    zza(t, t2, i);
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
                    this.zzo.zza(t, t2, j);
                    break;
                case 50:
                    zzalx.zza(this.zzr, t, t2, j);
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
                    if (zzc(t2, i2, i)) {
                        zzamp.zza(t, j, zzamp.zze(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case 67:
                    if (zzc(t2, i2, i)) {
                        zzamp.zza(t, j, zzamp.zze(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzalx.zza(this.zzp, t, t2);
        if (this.zzh) {
            zzalx.zza(this.zzq, t, t2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:169:0x05c2 A[Catch: all -> 0x05ed, TryCatch #5 {all -> 0x05ed, blocks: (B:167:0x05bd, B:169:0x05c2, B:170:0x05c7), top: B:201:0x05bd }] */
    /* JADX WARN: Code duplicated, block: B:175:0x05d5 A[LOOP:1: B:173:0x05d1->B:175:0x05d5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:177:0x05e4  */
    /* JADX WARN: Code duplicated, block: B:179:0x05e8  */
    /* JADX WARN: Code duplicated, block: B:189:0x05fe A[LOOP:2: B:187:0x05fa->B:189:0x05fe, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:191:0x060f  */
    /* JADX WARN: Code duplicated, block: B:286:0x05ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:314:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0, types: [com.google.android.gms.internal.firebase-auth-api.zzals] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, zzals zzalsVar, zzajk zzajkVar) throws Throwable {
        T t2;
        zzamo zzamoVar;
        Object objZza;
        int i;
        zzalg<T> zzalgVar;
        int i2;
        Object objZza2;
        zzalg<T> zzalgVar2 = this;
        zzajk zzajkVar2 = zzajkVar;
        if (zzajkVar2 == null) {
            throw new NullPointerException();
        }
        zzf(t);
        zzamo zzamoVar2 = zzalgVar2.zzp;
        zzajm<?> zzajmVar = zzalgVar2.zzq;
        Object objZza3 = null;
        zzajr zzajrVarZzb = null;
        while (true) {
            try {
                int iZzc = zzalsVar.zzc();
                int iZza = zzalgVar2.zza(iZzc);
                if (iZza < 0) {
                    if (iZzc == Integer.MAX_VALUE) {
                        int i3 = zzalgVar2.zzl;
                        Object objZza4 = objZza3;
                        while (i3 < zzalgVar2.zzm) {
                            zzamo zzamoVar3 = zzamoVar2;
                            objZza4 = zzalgVar2.zza(t, zzalgVar2.zzk[i3], objZza4, (zzamo<UT, Object>) zzamoVar3, t);
                            zzamoVar2 = zzamoVar3;
                            i3++;
                            zzalgVar2 = zzalgVar2;
                        }
                        if (objZza4 != null) {
                            zzamoVar2.zzb(t, objZza4);
                            return;
                        }
                        return;
                    }
                    zzalgVar = zzalgVar2;
                    try {
                        Object objZza5 = !zzalgVar.zzh ? null : zzajmVar.zza(zzajkVar2, zzalgVar.zzg, iZzc);
                        if (objZza5 != null) {
                            if (zzajrVarZzb == null) {
                                zzajrVarZzb = zzajmVar.zzb(t);
                            }
                            zzajm<?> zzajmVar2 = zzajmVar;
                            t2 = t;
                            zzajr zzajrVar = zzajrVarZzb;
                            zzamo zzamoVar4 = zzamoVar2;
                            try {
                                objZza3 = zzajmVar2.zza(t2, zzalsVar, objZza5, zzajkVar2, zzajrVar, objZza3, zzamoVar4);
                                zzajrVarZzb = zzajrVar;
                                zzalgVar2 = zzalgVar;
                                zzajmVar = zzajmVar2;
                                zzamoVar2 = zzamoVar4;
                                zzajkVar2 = zzajkVar2;
                            } catch (Throwable th) {
                                th = th;
                                zzamoVar = zzamoVar4;
                            }
                        } else {
                            zzajm<?> zzajmVar3 = zzajmVar;
                            zzajk zzajkVar3 = zzajkVar2;
                            zzamoVar = zzamoVar2;
                            t2 = t;
                            try {
                                zzamoVar.zza((zzals) zzalsVar);
                                if (objZza3 == null) {
                                    objZza3 = zzamoVar.zzc(t2);
                                }
                                if (!zzamoVar.zza(objZza3, (zzals) zzalsVar, 0)) {
                                    Object objZza6 = objZza3;
                                    for (int i4 = zzalgVar.zzl; i4 < zzalgVar.zzm; i4++) {
                                        objZza6 = zzalgVar.zza(t2, zzalgVar.zzk[i4], objZza6, (zzamo<UT, Object>) zzamoVar, t);
                                    }
                                    if (objZza6 != null) {
                                        zzamoVar.zzb(t2, objZza6);
                                        return;
                                    }
                                    return;
                                }
                                zzamoVar2 = zzamoVar;
                                zzalgVar2 = zzalgVar;
                                zzajkVar2 = zzajkVar3;
                                zzajmVar = zzajmVar3;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        t2 = t;
                        zzamoVar = zzamoVar2;
                    }
                    objZza = objZza3;
                    i = zzalgVar2.zzl;
                    while (i < zzalgVar2.zzm) {
                        objZza = zzalgVar2.zza(t2, zzalgVar2.zzk[i], objZza, (zzamo<UT, Object>) zzamoVar, t);
                        i++;
                        zzalgVar2 = this;
                    }
                    if (objZza != null) {
                        zzamoVar.zzb(t2, objZza);
                    }
                    throw th;
                }
                zzalgVar = zzalgVar2;
                zzajm<?> zzajmVar4 = zzajmVar;
                zzajk zzajkVar4 = zzajkVar2;
                zzamoVar = zzamoVar2;
                t2 = t;
                int iZzc2 = zzalgVar.zzc(iZza);
                switch ((267386880 & iZzc2) >>> 20) {
                    case 0:
                        zzamp.zza(t2, iZzc2 & 1048575, zzalsVar.zza());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 1:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzb());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 2:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzl());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 3:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzo());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 4:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzg());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 5:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzk());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 6:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzf());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 7:
                        zzamp.zzc(t2, iZzc2 & 1048575, zzalsVar.zzs());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 8:
                        zzalgVar.zza((Object) t2, iZzc2, (zzals) zzalsVar);
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 9:
                        zzalc zzalcVar = (zzalc) zzalgVar.zza((Object) t2, iZza);
                        zzalsVar.zzb(zzalcVar, zzalgVar.zze(iZza), zzajkVar4);
                        zzalgVar.zza(t2, iZza, zzalcVar);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 10:
                        zzamp.zza(t2, iZzc2 & 1048575, zzalsVar.zzp());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 11:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzj());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 12:
                        int iZze = zzalsVar.zze();
                        zzakd zzakdVarZzd = zzalgVar.zzd(iZza);
                        if (zzakdVarZzd == null || zzakdVarZzd.zza(iZze)) {
                            zzamp.zza((Object) t2, iZzc2 & 1048575, iZze);
                            zzalgVar.zzb((Object) t2, iZza);
                            zzamoVar2 = zzamoVar;
                            zzalgVar2 = zzalgVar;
                            zzajkVar2 = zzajkVar4;
                            zzajmVar = zzajmVar4;
                        } else {
                            objZza3 = zzalx.zza(t2, iZzc, iZze, objZza3, zzamoVar);
                            zzamoVar2 = zzamoVar;
                            zzalgVar2 = zzalgVar;
                            zzajkVar2 = zzajkVar4;
                            zzajmVar = zzajmVar4;
                        }
                        break;
                    case 13:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzh());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 14:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzm());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 15:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzi());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 16:
                        zzamp.zza((Object) t2, iZzc2 & 1048575, zzalsVar.zzn());
                        zzalgVar.zzb((Object) t2, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 17:
                        zzalc zzalcVar2 = (zzalc) zzalgVar.zza((Object) t2, iZza);
                        zzalsVar.zza(zzalcVar2, zzalgVar.zze(iZza), zzajkVar4);
                        zzalgVar.zza(t2, iZza, zzalcVar2);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 18:
                        zzalsVar.zzc(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 19:
                        zzalsVar.zzg(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 20:
                        zzalsVar.zzi(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 21:
                        zzalsVar.zzq(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 22:
                        zzalsVar.zzh(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 23:
                        zzalsVar.zzf(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 24:
                        zzalsVar.zze(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 25:
                        zzalsVar.zza(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 26:
                        if (zzg(iZzc2)) {
                            zzalsVar.zzo(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        } else {
                            zzalsVar.zzn(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        }
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 27:
                        zzalsVar.zzb(zzalgVar.zzo.zza(t2, iZzc2 & 1048575), zzalgVar.zze(iZza), zzajkVar4);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 28:
                        zzalsVar.zzb(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 29:
                        zzalsVar.zzp(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 30:
                        List listZza = zzalgVar.zzo.zza(t2, iZzc2 & 1048575);
                        zzalsVar.zzd(listZza);
                        objZza3 = zzalx.zza(t2, iZzc, listZza, zzalgVar.zzd(iZza), objZza3, zzamoVar);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 31:
                        zzalsVar.zzj(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 32:
                        zzalsVar.zzk(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 33:
                        zzalsVar.zzl(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 34:
                        zzalsVar.zzm(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 35:
                        zzalsVar.zzc(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 36:
                        zzalsVar.zzg(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 37:
                        zzalsVar.zzi(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 38:
                        zzalsVar.zzq(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 39:
                        zzalsVar.zzh(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 40:
                        zzalsVar.zzf(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 41:
                        zzalsVar.zze(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 42:
                        zzalsVar.zza(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 43:
                        zzalsVar.zzp(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 44:
                        List listZza2 = zzalgVar.zzo.zza(t2, iZzc2 & 1048575);
                        zzalsVar.zzd(listZza2);
                        zzamoVar2 = zzamoVar;
                        Object obj = objZza3;
                        try {
                            objZza3 = zzalx.zza(t2, iZzc, listZza2, zzalgVar.zzd(iZza), obj, zzamoVar2);
                            zzamoVar2 = zzamoVar2;
                            zzalgVar2 = zzalgVar;
                            zzajkVar2 = zzajkVar4;
                            zzajmVar = zzajmVar4;
                        } catch (zzake e) {
                            t2 = t2;
                            objZza3 = obj;
                            zzamoVar = zzamoVar2;
                            zzalgVar2 = zzalgVar;
                            try {
                                zzamoVar.zza((zzals) zzalsVar);
                                if (objZza3 == null) {
                                    objZza3 = zzamoVar.zzc(t2);
                                }
                                if (!zzamoVar.zza(objZza3, (zzals) zzalsVar, 0)) {
                                    objZza2 = objZza3;
                                    for (i2 = zzalgVar2.zzl; i2 < zzalgVar2.zzm; i2++) {
                                        objZza2 = zzalgVar2.zza(t2, zzalgVar2.zzk[i2], objZza2, (zzamo<UT, Object>) zzamoVar, t);
                                    }
                                    if (objZza2 != null) {
                                        zzamoVar.zzb(t2, objZza2);
                                        return;
                                    }
                                    return;
                                }
                                zzamoVar2 = zzamoVar;
                                zzajkVar2 = zzajkVar4;
                                zzajmVar = zzajmVar4;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            t2 = t2;
                            objZza3 = obj;
                            zzamoVar = zzamoVar2;
                            zzalgVar2 = zzalgVar;
                            objZza = objZza3;
                            i = zzalgVar2.zzl;
                            while (i < zzalgVar2.zzm) {
                                objZza = zzalgVar2.zza(t2, zzalgVar2.zzk[i], objZza, (zzamo<UT, Object>) zzamoVar, t);
                                i++;
                                zzalgVar2 = this;
                            }
                            if (objZza != null) {
                                zzamoVar.zzb(t2, objZza);
                            }
                            throw th;
                        }
                        break;
                    case 45:
                        zzalsVar.zzj(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 46:
                        zzalsVar.zzk(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 47:
                        zzalsVar.zzl(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 48:
                        zzalsVar.zzm(zzalgVar.zzo.zza(t2, iZzc2 & 1048575));
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        zzalsVar.zza(zzalgVar.zzo.zza(t2, iZzc2 & 1048575), zzalgVar.zze(iZza), zzajkVar4);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 50:
                        Object objZzf = zzalgVar.zzf(iZza);
                        long jZzc = zzalgVar.zzc(iZza) & 1048575;
                        Object objZze = zzamp.zze(t2, jZzc);
                        if (objZze == null) {
                            objZze = zzalgVar.zzr.zzb(objZzf);
                            zzamp.zza(t2, jZzc, objZze);
                        } else if (zzalgVar.zzr.zzf(objZze)) {
                            Object objZzb = zzalgVar.zzr.zzb(objZzf);
                            zzalgVar.zzr.zza(objZzb, objZze);
                            zzamp.zza(t2, jZzc, objZzb);
                            objZze = objZzb;
                        }
                        zzalsVar.zza(zzalgVar.zzr.zze(objZze), zzalgVar.zzr.zza(objZzf), zzajkVar4);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                        zzamp.zza(t2, iZzc2 & 1048575, Double.valueOf(zzalsVar.zza()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 52:
                        zzamp.zza(t2, iZzc2 & 1048575, Float.valueOf(zzalsVar.zzb()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 53:
                        zzamp.zza(t2, iZzc2 & 1048575, Long.valueOf(zzalsVar.zzl()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 54:
                        zzamp.zza(t2, iZzc2 & 1048575, Long.valueOf(zzalsVar.zzo()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 55:
                        zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(zzalsVar.zzg()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 56:
                        zzamp.zza(t2, iZzc2 & 1048575, Long.valueOf(zzalsVar.zzk()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 57:
                        zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(zzalsVar.zzf()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case Elf64_Ehdr.e_shentsize /* 58 */:
                        zzamp.zza(t2, iZzc2 & 1048575, Boolean.valueOf(zzalsVar.zzs()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 59:
                        zzalgVar.zza((Object) t2, iZzc2, (zzals) zzalsVar);
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 60:
                        zzalc zzalcVar3 = (zzalc) zzalgVar.zza(t2, iZzc, iZza);
                        zzalsVar.zzb(zzalcVar3, zzalgVar.zze(iZza), zzajkVar4);
                        zzalgVar.zza(t2, iZzc, iZza, zzalcVar3);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 61:
                        zzamp.zza(t2, iZzc2 & 1048575, zzalsVar.zzp());
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 62:
                        zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(zzalsVar.zzj()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 63:
                        int iZze2 = zzalsVar.zze();
                        zzakd zzakdVarZzd2 = zzalgVar.zzd(iZza);
                        if (zzakdVarZzd2 == null || zzakdVarZzd2.zza(iZze2)) {
                            zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(iZze2));
                            zzalgVar.zzb(t2, iZzc, iZza);
                            zzamoVar2 = zzamoVar;
                            zzalgVar2 = zzalgVar;
                            zzajkVar2 = zzajkVar4;
                            zzajmVar = zzajmVar4;
                        } else {
                            objZza3 = zzalx.zza(t2, iZzc, iZze2, objZza3, zzamoVar);
                            zzamoVar2 = zzamoVar;
                            zzalgVar2 = zzalgVar;
                            zzajkVar2 = zzajkVar4;
                            zzajmVar = zzajmVar4;
                        }
                        break;
                    case 64:
                        zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(zzalsVar.zzh()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 65:
                        zzamp.zza(t2, iZzc2 & 1048575, Long.valueOf(zzalsVar.zzm()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                        zzamp.zza(t2, iZzc2 & 1048575, Integer.valueOf(zzalsVar.zzi()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 67:
                        zzamp.zza(t2, iZzc2 & 1048575, Long.valueOf(zzalsVar.zzn()));
                        zzalgVar.zzb(t2, iZzc, iZza);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    case 68:
                        zzalc zzalcVar4 = (zzalc) zzalgVar.zza(t2, iZzc, iZza);
                        zzalsVar.zza(zzalcVar4, zzalgVar.zze(iZza), zzajkVar4);
                        zzalgVar.zza(t2, iZzc, iZza, zzalcVar4);
                        zzamoVar2 = zzamoVar;
                        zzalgVar2 = zzalgVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                    default:
                        if (objZza3 == null) {
                            try {
                                objZza3 = zzamoVar.zzc(t2);
                            } catch (zzake e2) {
                                zzalgVar2 = zzalgVar;
                                zzamoVar.zza((zzals) zzalsVar);
                                if (objZza3 == null) {
                                    objZza3 = zzamoVar.zzc(t2);
                                }
                                if (!zzamoVar.zza(objZza3, (zzals) zzalsVar, 0)) {
                                    objZza2 = objZza3;
                                    while (i2 < zzalgVar2.zzm) {
                                        objZza2 = zzalgVar2.zza(t2, zzalgVar2.zzk[i2], objZza2, (zzamo<UT, Object>) zzamoVar, t);
                                    }
                                    if (objZza2 != null) {
                                        zzamoVar.zzb(t2, objZza2);
                                        return;
                                    }
                                    return;
                                }
                                zzamoVar2 = zzamoVar;
                                zzajkVar2 = zzajkVar4;
                                zzajmVar = zzajmVar4;
                            }
                        }
                        if (!zzamoVar.zza(objZza3, (zzals) zzalsVar, 0)) {
                            Object objZza7 = objZza3;
                            for (int i5 = zzalgVar.zzl; i5 < zzalgVar.zzm; i5++) {
                                objZza7 = zzalgVar.zza(t2, zzalgVar.zzk[i5], objZza7, (zzamo<UT, Object>) zzamoVar, t);
                            }
                            if (objZza7 != null) {
                                zzamoVar.zzb(t2, objZza7);
                                return;
                            }
                            return;
                        }
                        zzalgVar2 = zzalgVar;
                        zzamoVar2 = zzamoVar;
                        zzajkVar2 = zzajkVar4;
                        zzajmVar = zzajmVar4;
                        break;
                        break;
                }
                zzalgVar2 = zzalgVar;
            } catch (Throwable th6) {
                th = th6;
                t2 = t;
                zzamoVar = zzamoVar2;
            }
            objZza = objZza3;
            i = zzalgVar2.zzl;
            while (i < zzalgVar2.zzm) {
                objZza = zzalgVar2.zza(t2, zzalgVar2.zzk[i], objZza, (zzamo<UT, Object>) zzamoVar, t);
                i++;
                zzalgVar2 = this;
            }
            if (objZza != null) {
                zzamoVar.zzb(t2, objZza);
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, byte[] bArr, int i, int i2, zzaik zzaikVar) throws IOException {
        zza(t, bArr, i, i2, 0, zzaikVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zza(T t, T t2, int i) {
        if (!zzc((Object) t2, i)) {
            return;
        }
        long jZzc = zzc(i) & 1048575;
        Object object = zzb.getObject(t2, jZzc);
        if (object == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + String.valueOf(t2));
        }
        zzalv zzalvVarZze = zze(i);
        if (!zzc((Object) t, i)) {
            if (!zzg(object)) {
                zzb.putObject(t, jZzc, object);
            } else {
                Object objZza = zzalvVarZze.zza();
                zzalvVarZze.zza(objZza, object);
                zzb.putObject(t, jZzc, objZza);
            }
            zzb((Object) t, i);
            return;
        }
        Object object2 = zzb.getObject(t, jZzc);
        if (!zzg(object2)) {
            Object objZza2 = zzalvVarZze.zza();
            zzalvVarZze.zza(objZza2, object2);
            zzb.putObject(t, jZzc, objZza2);
            object2 = objZza2;
        }
        zzalvVarZze.zza(object2, object);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zzb(T t, T t2, int i) {
        int i2 = this.zzc[i];
        if (!zzc(t2, i2, i)) {
            return;
        }
        long jZzc = zzc(i) & 1048575;
        Object object = zzb.getObject(t2, jZzc);
        if (object == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + String.valueOf(t2));
        }
        zzalv zzalvVarZze = zze(i);
        if (!zzc(t, i2, i)) {
            if (!zzg(object)) {
                zzb.putObject(t, jZzc, object);
            } else {
                Object objZza = zzalvVarZze.zza();
                zzalvVarZze.zza(objZza, object);
                zzb.putObject(t, jZzc, objZza);
            }
            zzb(t, i2, i);
            return;
        }
        Object object2 = zzb.getObject(t, jZzc);
        if (!zzg(object2)) {
            Object objZza2 = zzalvVarZze.zza();
            zzalvVarZze.zza(objZza2, object2);
            zzb.putObject(t, jZzc, objZza2);
            object2 = objZza2;
        }
        zzalvVarZze.zza(object2, object);
    }

    private final void zza(Object obj, int i, zzals zzalsVar) throws IOException {
        if (zzg(i)) {
            zzamp.zza(obj, i & 1048575, zzalsVar.zzr());
        } else if (this.zzi) {
            zzamp.zza(obj, i & 1048575, zzalsVar.zzq());
        } else {
            zzamp.zza(obj, i & 1048575, zzalsVar.zzp());
        }
    }

    private final void zzb(T t, int i) {
        int iZzb = zzb(i);
        long j = 1048575 & iZzb;
        if (j == 1048575) {
            return;
        }
        zzamp.zza((Object) t, j, zzamp.zzc(t, j) | (1 << (iZzb >>> 20)));
    }

    private final void zzb(T t, int i, int i2) {
        zzamp.zza((Object) t, zzb(i2) & 1048575, i);
    }

    private final void zza(T t, int i, Object obj) {
        zzb.putObject(t, zzc(i) & 1048575, obj);
        zzb((Object) t, i);
    }

    private final void zza(T t, int i, int i2, Object obj) {
        zzb.putObject(t, zzc(i2) & 1048575, obj);
        zzb(t, i, i2);
    }

    private final <K, V> void zza(zzanf zzanfVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzanfVar.zza(i, this.zzr.zza(zzf(i2)), this.zzr.zzd(obj));
        }
    }

    private static void zza(int i, Object obj, zzanf zzanfVar) throws IOException {
        if (obj instanceof String) {
            zzanfVar.zza(i, (String) obj);
        } else {
            zzanfVar.zza(i, (zzaip) obj);
        }
    }

    /* JADX WARN: Code duplicated, block: B:176:0x05db  */
    /* JADX WARN: Code duplicated, block: B:9:0x003a  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final void zza(T t, zzanf zzanfVar) throws IOException {
        Map.Entry<?, ?> entry;
        Iterator it;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        Iterator itZzc;
        Map.Entry<?, ?> entry2;
        zzalg<T> zzalgVar = this;
        int i6 = 267386880;
        int i7 = 1;
        int i8 = 1048575;
        if (zzanfVar.zza() == 2) {
            zza(zzalgVar.zzp, t, zzanfVar);
            if (zzalgVar.zzh) {
                zzajr<T> zzajrVarZza = zzalgVar.zzq.zza(t);
                if (!zzajrVarZza.zza.isEmpty()) {
                    itZzc = zzajrVarZza.zzc();
                    entry2 = (Map.Entry) itZzc.next();
                } else {
                    itZzc = null;
                    entry2 = null;
                }
            } else {
                itZzc = null;
                entry2 = null;
            }
            for (int length = zzalgVar.zzc.length - 3; length >= 0; length -= 3) {
                int iZzc = zzalgVar.zzc(length);
                int i9 = zzalgVar.zzc[length];
                while (entry2 != null && zzalgVar.zzq.zza(entry2) > i9) {
                    zzalgVar.zzq.zza(zzanfVar, entry2);
                    entry2 = itZzc.hasNext() ? (Map.Entry) itZzc.next() : null;
                }
                switch ((iZzc & 267386880) >>> 20) {
                    case 0:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zza(t, iZzc & 1048575));
                        }
                        break;
                    case 1:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zzb(t, iZzc & 1048575));
                        }
                        break;
                    case 2:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzb(i9, zzamp.zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 3:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zze(i9, zzamp.zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 4:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzc(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 5:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 6:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzb(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 7:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zzh(t, iZzc & 1048575));
                        }
                        break;
                    case 8:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zza(i9, zzamp.zze(t, iZzc & 1048575), zzanfVar);
                        }
                        break;
                    case 9:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzb(i9, zzamp.zze(t, iZzc & 1048575), zzalgVar.zze(length));
                        }
                        break;
                    case 10:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, (zzaip) zzamp.zze(t, iZzc & 1048575));
                        }
                        break;
                    case 11:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzf(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 12:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 13:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzd(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 14:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzc(i9, zzamp.zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 15:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zze(i9, zzamp.zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 16:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zzd(i9, zzamp.zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 17:
                        if (zzalgVar.zzc((Object) t, length)) {
                            zzanfVar.zza(i9, zzamp.zze(t, iZzc & 1048575), zzalgVar.zze(length));
                        }
                        break;
                    case 18:
                        zzalx.zzb(zzalgVar.zzc[length], (List<Double>) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 19:
                        zzalx.zzf(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 20:
                        zzalx.zzh(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 21:
                        zzalx.zzn(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 22:
                        zzalx.zzg(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 23:
                        zzalx.zze(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 24:
                        zzalx.zzd(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 25:
                        zzalx.zza(zzalgVar.zzc[length], (List<Boolean>) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 26:
                        zzalx.zzb(zzalgVar.zzc[length], (List<String>) zzamp.zze(t, iZzc & 1048575), zzanfVar);
                        break;
                    case 27:
                        zzalx.zzb(zzalgVar.zzc[length], (List<?>) zzamp.zze(t, iZzc & 1048575), zzanfVar, (zzalv<?>) zzalgVar.zze(length));
                        break;
                    case 28:
                        zzalx.zza(zzalgVar.zzc[length], (List<zzaip>) zzamp.zze(t, iZzc & 1048575), zzanfVar);
                        break;
                    case 29:
                        zzalx.zzm(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 30:
                        zzalx.zzc(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 31:
                        zzalx.zzi(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 32:
                        zzalx.zzj(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 33:
                        zzalx.zzk(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 34:
                        zzalx.zzl(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, false);
                        break;
                    case 35:
                        zzalx.zzb(zzalgVar.zzc[length], (List<Double>) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 36:
                        zzalx.zzf(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 37:
                        zzalx.zzh(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 38:
                        zzalx.zzn(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 39:
                        zzalx.zzg(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 40:
                        zzalx.zze(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 41:
                        zzalx.zzd(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 42:
                        zzalx.zza(zzalgVar.zzc[length], (List<Boolean>) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 43:
                        zzalx.zzm(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 44:
                        zzalx.zzc(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 45:
                        zzalx.zzi(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 46:
                        zzalx.zzj(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 47:
                        zzalx.zzk(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case 48:
                        zzalx.zzl(zzalgVar.zzc[length], (List) zzamp.zze(t, iZzc & 1048575), zzanfVar, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        zzalx.zza(zzalgVar.zzc[length], (List<?>) zzamp.zze(t, iZzc & 1048575), zzanfVar, (zzalv<?>) zzalgVar.zze(length));
                        break;
                    case 50:
                        zzalgVar.zza(zzanfVar, i9, zzamp.zze(t, iZzc & 1048575), length);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zza(t, iZzc & 1048575));
                        }
                        break;
                    case 52:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zzb(t, iZzc & 1048575));
                        }
                        break;
                    case 53:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzb(i9, zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 54:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zze(i9, zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 55:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzc(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 56:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 57:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzb(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case Elf64_Ehdr.e_shentsize /* 58 */:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zze(t, iZzc & 1048575));
                        }
                        break;
                    case 59:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zza(i9, zzamp.zze(t, iZzc & 1048575), zzanfVar);
                        }
                        break;
                    case 60:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzb(i9, zzamp.zze(t, iZzc & 1048575), zzalgVar.zze(length));
                        }
                        break;
                    case 61:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, (zzaip) zzamp.zze(t, iZzc & 1048575));
                        }
                        break;
                    case 62:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzf(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 63:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 64:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzd(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 65:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzc(i9, zzd(t, iZzc & 1048575));
                        }
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zze(i9, zzc(t, iZzc & 1048575));
                        }
                        break;
                    case 67:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zzd(i9, zzd(t, iZzc & 1048575));
                        }
                        break;
                    case 68:
                        if (zzalgVar.zzc(t, i9, length)) {
                            zzanfVar.zza(i9, zzamp.zze(t, iZzc & 1048575), zzalgVar.zze(length));
                        }
                        break;
                }
            }
            while (entry2 != null) {
                zzalgVar.zzq.zza(zzanfVar, entry2);
                entry2 = itZzc.hasNext() ? (Map.Entry) itZzc.next() : null;
            }
            return;
        }
        if (zzalgVar.zzh) {
            zzajr<T> zzajrVarZza2 = zzalgVar.zzq.zza(t);
            if (!zzajrVarZza2.zza.isEmpty()) {
                Iterator itZzd = zzajrVarZza2.zzd();
                entry = (Map.Entry) itZzd.next();
                it = itZzd;
            } else {
                entry = null;
                it = null;
            }
        } else {
            entry = null;
            it = null;
        }
        int length2 = zzalgVar.zzc.length;
        Unsafe unsafe = zzb;
        int i10 = 0;
        int i11 = 0;
        int i12 = 1048575;
        while (i10 < length2) {
            int iZzc2 = zzalgVar.zzc(i10);
            int i13 = i6;
            int i14 = zzalgVar.zzc[i10];
            int i15 = (iZzc2 & i13) >>> 20;
            if (i15 > 17) {
                i = i7;
                i2 = i12;
                i3 = i11;
                i4 = 0;
            } else {
                int i16 = zzalgVar.zzc[i10 + 2];
                i = i7;
                int i17 = i16 & i8;
                if (i17 != i12) {
                    if (i17 == i8) {
                        i11 = 0;
                    } else {
                        i11 = unsafe.getInt(t, i17);
                    }
                    i12 = i17;
                }
                i2 = i12;
                i3 = i11;
                i4 = i << (i16 >>> 20);
            }
            while (entry != null && zzalgVar.zzq.zza(entry) <= i14) {
                zzalgVar.zzq.zza(zzanfVar, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            Iterator it2 = it;
            long j = iZzc2 & i8;
            switch (i15) {
                case 0:
                    i5 = i;
                    z = false;
                    zzalgVar = this;
                    if (zzalgVar.zza(t, i10, i2, i3, i4)) {
                        zzanfVar.zza(i14, zzamp.zza(t, j));
                    }
                    break;
                case 1:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zza(i14, zzamp.zzb(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 2:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzb(i14, unsafe.getLong(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 3:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zze(i14, unsafe.getLong(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 4:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzc(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 5:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zza(i14, unsafe.getLong(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 6:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzb(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 7:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zza(i14, zzamp.zzh(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 8:
                    i5 = i;
                    z = false;
                    if (!zzalgVar.zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zza(i14, unsafe.getObject(t, j), zzanfVar);
                        zzalgVar = this;
                    }
                    break;
                case 9:
                    i5 = i;
                    z = false;
                    zzalgVar = this;
                    if (zzalgVar.zza(t, i10, i2, i3, i4)) {
                        zzanfVar.zzb(i14, unsafe.getObject(t, j), zzalgVar.zze(i10));
                    }
                    break;
                case 10:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zza(i14, (zzaip) unsafe.getObject(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 11:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzf(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 12:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zza(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 13:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzd(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 14:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzc(i14, unsafe.getLong(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 15:
                    i5 = i;
                    z = false;
                    if (!zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zze(i14, unsafe.getInt(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 16:
                    i5 = i;
                    z = false;
                    if (!zzalgVar.zza(t, i10, i2, i3, i4)) {
                        zzalgVar = this;
                    } else {
                        zzanfVar.zzd(i14, unsafe.getLong(t, j));
                        zzalgVar = this;
                    }
                    break;
                case 17:
                    i5 = i;
                    z = false;
                    if (zzalgVar.zza(t, i10, i2, i3, i4)) {
                        zzanfVar.zza(i14, unsafe.getObject(t, j), zzalgVar.zze(i10));
                    }
                    break;
                case 18:
                    i5 = i;
                    z = false;
                    zzalx.zzb(zzalgVar.zzc[i10], (List<Double>) unsafe.getObject(t, j), zzanfVar, false);
                    break;
                case 19:
                    i5 = i;
                    zzalx.zzf(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 20:
                    i5 = i;
                    zzalx.zzh(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 21:
                    i5 = i;
                    zzalx.zzn(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 22:
                    i5 = i;
                    zzalx.zzg(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 23:
                    i5 = i;
                    zzalx.zze(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 24:
                    i5 = i;
                    zzalx.zzd(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 25:
                    i5 = i;
                    zzalx.zza(zzalgVar.zzc[i10], (List<Boolean>) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 26:
                    i5 = i;
                    zzalx.zzb(zzalgVar.zzc[i10], (List<String>) unsafe.getObject(t, j), zzanfVar);
                    z = false;
                    break;
                case 27:
                    i5 = i;
                    zzalx.zzb(zzalgVar.zzc[i10], (List<?>) unsafe.getObject(t, j), zzanfVar, (zzalv<?>) zzalgVar.zze(i10));
                    z = false;
                    break;
                case 28:
                    i5 = i;
                    zzalx.zza(zzalgVar.zzc[i10], (List<zzaip>) unsafe.getObject(t, j), zzanfVar);
                    z = false;
                    break;
                case 29:
                    i5 = i;
                    zzalx.zzm(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 30:
                    i5 = i;
                    zzalx.zzc(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 31:
                    i5 = i;
                    zzalx.zzi(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 32:
                    i5 = i;
                    zzalx.zzj(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 33:
                    i5 = i;
                    zzalx.zzk(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 34:
                    i5 = i;
                    zzalx.zzl(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, false);
                    z = false;
                    break;
                case 35:
                    i5 = 1;
                    zzalx.zzb(zzalgVar.zzc[i10], (List<Double>) unsafe.getObject(t, j), zzanfVar, true);
                    z = false;
                    break;
                case 36:
                    zzalx.zzf(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 37:
                    zzalx.zzh(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 38:
                    zzalx.zzn(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 39:
                    zzalx.zzg(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 40:
                    zzalx.zze(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 41:
                    zzalx.zzd(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 42:
                    zzalx.zza(zzalgVar.zzc[i10], (List<Boolean>) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 43:
                    zzalx.zzm(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 44:
                    zzalx.zzc(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 45:
                    zzalx.zzi(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 46:
                    zzalx.zzj(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 47:
                    zzalx.zzk(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, true);
                    i5 = 1;
                    z = false;
                    break;
                case 48:
                    zzalx.zzl(zzalgVar.zzc[i10], (List) unsafe.getObject(t, j), zzanfVar, i);
                    i5 = 1;
                    z = false;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzalx.zza(zzalgVar.zzc[i10], (List<?>) unsafe.getObject(t, j), zzanfVar, (zzalv<?>) zzalgVar.zze(i10));
                    i5 = i;
                    z = false;
                    break;
                case 50:
                    zzalgVar.zza(zzanfVar, i14, unsafe.getObject(t, j), i10);
                    i5 = i;
                    z = false;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, zza(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 52:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, zzb(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 53:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzb(i14, zzd(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 54:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zze(i14, zzd(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 55:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzc(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 56:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, zzd(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 57:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzb(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, zze(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 59:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zza(i14, unsafe.getObject(t, j), zzanfVar);
                        i5 = i;
                        z = false;
                    }
                    break;
                case 60:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzb(i14, unsafe.getObject(t, j), zzalgVar.zze(i10));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 61:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, (zzaip) unsafe.getObject(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 62:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzf(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 63:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 64:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzd(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 65:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzc(i14, zzd(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zze(i14, zzc(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 67:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zzd(i14, zzd(t, j));
                        i5 = i;
                        z = false;
                    }
                    break;
                case 68:
                    if (!zzalgVar.zzc(t, i14, i10)) {
                        i5 = i;
                        z = false;
                    } else {
                        zzanfVar.zza(i14, unsafe.getObject(t, j), zzalgVar.zze(i10));
                        i5 = i;
                        z = false;
                    }
                    break;
                default:
                    i5 = i;
                    z = false;
                    break;
            }
            i10 += 3;
            i11 = i3;
            i6 = i13;
            it = it2;
            i8 = 1048575;
            i12 = i2;
            entry = entry;
            i7 = i5;
        }
        Iterator it3 = it;
        while (entry != null) {
            zzalgVar.zzq.zza(zzanfVar, entry);
            entry = it3.hasNext() ? (Map.Entry) it3.next() : null;
        }
        zza(zzalgVar.zzp, t, zzanfVar);
    }

    private static <UT, UB> void zza(zzamo<UT, UB> zzamoVar, T t, zzanf zzanfVar) throws IOException {
        zzamoVar.zzb(zzamoVar.zzd(t), zzanfVar);
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzc((Object) t, i) == zzc((Object) t2, i);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final boolean zzb(T t, T t2) {
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            boolean zZza = true;
            if (i < length) {
                int iZzc = zzc(i);
                long j = iZzc & 1048575;
                switch ((iZzc & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzamp.zza(t, j)) != Double.doubleToLongBits(zzamp.zza(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzamp.zzb(t, j)) != Float.floatToIntBits(zzamp.zzb(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzamp.zzd(t, j) != zzamp.zzd(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzamp.zzd(t, j) != zzamp.zzd(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzamp.zzd(t, j) != zzamp.zzd(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzamp.zzh(t, j) != zzamp.zzh(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzamp.zzd(t, j) != zzamp.zzd(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzamp.zzc(t, j) != zzamp.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzamp.zzd(t, j) != zzamp.zzd(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j))) {
                            zZza = false;
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
                        zZza = zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j));
                        break;
                    case 50:
                        zZza = zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j));
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
                        long jZzb = zzb(i) & 1048575;
                        if (zzamp.zzc(t, jZzb) != zzamp.zzc(t2, jZzb) || !zzalx.zza(zzamp.zze(t, j), zzamp.zze(t2, j))) {
                            zZza = false;
                        }
                        break;
                }
                if (!zZza) {
                    return false;
                }
                i += 3;
            } else {
                if (!this.zzp.zzd(t).equals(this.zzp.zzd(t2))) {
                    return false;
                }
                if (this.zzh) {
                    return this.zzq.zza(t).equals(this.zzq.zza(t2));
                }
                return true;
            }
        }
    }

    private static boolean zzg(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzc(T t, int i) {
        int iZzb = zzb(i);
        long j = iZzb & 1048575;
        if (j != 1048575) {
            return (zzamp.zzc(t, j) & (1 << (iZzb >>> 20))) != 0;
        }
        int iZzc = zzc(i);
        long j2 = 1048575 & iZzc;
        switch ((iZzc & 267386880) >>> 20) {
            case 0:
                return Double.doubleToRawLongBits(zzamp.zza(t, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzamp.zzb(t, j2)) != 0;
            case 2:
                return zzamp.zzd(t, j2) != 0;
            case 3:
                return zzamp.zzd(t, j2) != 0;
            case 4:
                return zzamp.zzc(t, j2) != 0;
            case 5:
                return zzamp.zzd(t, j2) != 0;
            case 6:
                return zzamp.zzc(t, j2) != 0;
            case 7:
                return zzamp.zzh(t, j2);
            case 8:
                Object objZze = zzamp.zze(t, j2);
                if (objZze instanceof String) {
                    return !((String) objZze).isEmpty();
                }
                if (objZze instanceof zzaip) {
                    return !zzaip.zza.equals(objZze);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzamp.zze(t, j2) != null;
            case 10:
                return !zzaip.zza.equals(zzamp.zze(t, j2));
            case 11:
                return zzamp.zzc(t, j2) != 0;
            case 12:
                return zzamp.zzc(t, j2) != 0;
            case 13:
                return zzamp.zzc(t, j2) != 0;
            case 14:
                return zzamp.zzd(t, j2) != 0;
            case 15:
                return zzamp.zzc(t, j2) != 0;
            case 16:
                return zzamp.zzd(t, j2) != 0;
            case 17:
                return zzamp.zze(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzc((Object) t, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18, types: [com.google.android.gms.internal.firebase-auth-api.zzalv] */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v8, types: [com.google.android.gms.internal.firebase-auth-api.zzalv] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalv
    public final boolean zze(T t) {
        int i;
        int i2;
        T t2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i4 >= this.zzl) {
                return !this.zzh || this.zzq.zza(t).zzg();
            }
            int i6 = this.zzk[i4];
            int i7 = this.zzc[i6];
            int iZzc = zzc(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i3) {
                i = i3;
                i2 = i5;
            } else if (i9 == 1048575) {
                i2 = i5;
                i = i9;
            } else {
                i2 = zzb.getInt(t, i9);
                i = i9;
            }
            if (!((268435456 & iZzc) != 0)) {
                t2 = t;
            } else {
                t2 = t;
                if (!zza(t2, i6, i, i2, i10)) {
                    return false;
                }
            }
            switch ((267386880 & iZzc) >>> 20) {
                case 9:
                case 17:
                    if (zza(t2, i6, i, i2, i10) && !zza((Object) t2, iZzc, zze(i6))) {
                        return false;
                    }
                    break;
                    break;
                case 27:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    List list = (List) zzamp.zze(t2, iZzc & 1048575);
                    if (!list.isEmpty()) {
                        ?? Zze = zze(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!Zze.zze(list.get(i11))) {
                                z = false;
                            }
                        }
                    }
                    if (!z) {
                        return false;
                    }
                    break;
                    break;
                case 50:
                    Map<?, ?> mapZzd = this.zzr.zzd(zzamp.zze(t2, iZzc & 1048575));
                    if (!mapZzd.isEmpty()) {
                        if (this.zzr.zza(zzf(i6)).zzc.zzb() == zzand.MESSAGE) {
                            ?? Zza = 0;
                            for (Object obj : mapZzd.values()) {
                                if (Zza == 0) {
                                    Zza = Zza;
                                    Zza = zzalr.zza().zza((Class) obj.getClass());
                                }
                                Zza = Zza;
                                if (!Zza.zze(obj)) {
                                    z = false;
                                }
                            }
                        }
                    }
                    if (!z) {
                        return false;
                    }
                    break;
                    break;
                case 60:
                case 68:
                    if (zzc(t2, i7, i6) && !zza((Object) t2, iZzc, zze(i6))) {
                        return false;
                    }
                    break;
                    break;
            }
            i4++;
            t = t2;
            i3 = i;
            i5 = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzalv zzalvVar) {
        return zzalvVar.zze(zzamp.zze(obj, i & 1048575));
    }

    private static boolean zzg(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzajy) {
            return ((zzajy) obj).zzu();
        }
        return true;
    }

    private final boolean zzc(T t, int i, int i2) {
        return zzamp.zzc(t, (long) (zzb(i2) & 1048575)) == i;
    }

    private static <T> boolean zze(T t, long j) {
        return ((Boolean) zzamp.zze(t, j)).booleanValue();
    }
}
