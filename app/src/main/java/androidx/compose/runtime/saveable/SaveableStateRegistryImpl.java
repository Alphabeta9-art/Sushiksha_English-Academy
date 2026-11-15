package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SaveableStateRegistry.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0007\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000fH\u0016J\u001c\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0003H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000f0\u000e\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateRegistryImpl;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "restored", "", "", "", "", "canBeSaved", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "Landroidx/collection/MutableScatterMap;", "valueProviders", "", "Lkotlin/Function0;", "value", "consumeRestored", "key", "registerProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "valueProvider", "performSave", "runtime-saveable"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    private final Function1<Object, Boolean> canBeSaved;
    private final MutableScatterMap<String, List<Object>> restored;
    private MutableScatterMap<String, List<Function0<Object>>> valueProviders;

    public SaveableStateRegistryImpl(Map<String, ? extends List<? extends Object>> map, Function1<Object, Boolean> function1) {
        this.canBeSaved = function1;
        this.restored = !(map == null || map.isEmpty()) ? SaveableStateRegistryKt.toMutableScatterMap(map) : null;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return this.canBeSaved.invoke(value).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        MutableScatterMap<String, List<Object>> mutableScatterMap;
        MutableScatterMap<String, List<Object>> mutableScatterMap2 = this.restored;
        List<Object> listRemove = mutableScatterMap2 != null ? mutableScatterMap2.remove(key) : null;
        List<Object> list = listRemove;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (listRemove.size() > 1 && (mutableScatterMap = this.restored) != null) {
            mutableScatterMap.put(key, listRemove.subList(1, listRemove.size()));
        }
        return listRemove.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(final String key, final Function0<? extends Object> valueProvider) {
        if (SaveableStateRegistryKt.fastIsBlank(key)) {
            throw new IllegalArgumentException("Registered key is empty or blank".toString());
        }
        final MutableScatterMap<String, List<Function0<Object>>> mutableScatterMapMutableScatterMapOf = this.valueProviders;
        if (mutableScatterMapMutableScatterMapOf == null) {
            mutableScatterMapMutableScatterMapOf = ScatterMapKt.mutableScatterMapOf();
            this.valueProviders = mutableScatterMapMutableScatterMapOf;
        }
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap = mutableScatterMapMutableScatterMapOf;
        List<Function0<Object>> arrayList = mutableScatterMap.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            mutableScatterMap.set(key, arrayList);
        }
        arrayList.add(valueProvider);
        return new SaveableStateRegistry.Entry() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryImpl.registerProvider.3
            @Override // androidx.compose.runtime.saveable.SaveableStateRegistry.Entry
            public void unregister() {
                List<Function0<Object>> listRemove = mutableScatterMapMutableScatterMapOf.remove(key);
                if (listRemove != null) {
                    listRemove.remove(valueProvider);
                }
                List<Function0<Object>> list = listRemove;
                if (!(list == null || list.isEmpty())) {
                    mutableScatterMapMutableScatterMapOf.set(key, listRemove);
                }
            }
        };
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map<String, List<Object>> performSave() {
        HashMap map;
        long j;
        char c;
        long j2;
        long j3;
        Object[] k$iv;
        Object[] v$iv;
        ScatterMap this_$iv$iv;
        int $i$f$forEachIndexed;
        int j$iv$iv;
        Object[] k$iv2;
        int expectedMapSize;
        if (this.restored == null && this.valueProviders == null) {
            return MapsKt.emptyMap();
        }
        MutableScatterMap<String, List<Object>> mutableScatterMap = this.restored;
        int size = mutableScatterMap != null ? mutableScatterMap.get_size() : 0;
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap2 = this.valueProviders;
        int expectedMapSize2 = size + (mutableScatterMap2 != null ? mutableScatterMap2.get_size() : 0);
        HashMap $this$performSave_u24lambda_u240 = new HashMap(expectedMapSize2);
        ScatterMap scatterMap = this.restored;
        if (scatterMap != null) {
            ScatterMap this_$iv = scatterMap;
            j = 128;
            Object[] k$iv3 = this_$iv.keys;
            Object[] v$iv2 = this_$iv.values;
            j2 = 255;
            long[] m$iv$iv = this_$iv.metadata;
            c = 7;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            j3 = -9187201950435737472L;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    map = $this$performSave_u24lambda_u240;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        expectedMapSize = expectedMapSize2;
                    } else {
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv2 = 0;
                        while (j$iv$iv2 < bitCount$iv$iv) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                Object obj = k$iv3[index$iv$iv];
                                List v = (List) v$iv2[index$iv$iv];
                                String k = (String) obj;
                                $this$performSave_u24lambda_u240.put(k, v);
                            }
                            slot$iv$iv >>= 8;
                            j$iv$iv2++;
                            expectedMapSize2 = expectedMapSize2;
                        }
                        expectedMapSize = expectedMapSize2;
                        if (bitCount$iv$iv != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    $this$performSave_u24lambda_u240 = map;
                    expectedMapSize2 = expectedMapSize;
                }
            } else {
                map = $this$performSave_u24lambda_u240;
            }
        } else {
            map = $this$performSave_u24lambda_u240;
            j = 128;
            c = 7;
            j2 = 255;
            j3 = -9187201950435737472L;
        }
        ScatterMap scatterMap2 = this.valueProviders;
        if (scatterMap2 != null) {
            ScatterMap this_$iv2 = scatterMap2;
            int $i$f$forEach = 0;
            Object[] k$iv4 = this_$iv2.keys;
            Object[] v$iv3 = this_$iv2.values;
            ScatterMap this_$iv$iv2 = this_$iv2;
            int $i$f$forEachIndexed2 = 0;
            long[] m$iv$iv2 = this_$iv$iv2.metadata;
            int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
            int i$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv2) {
                while (true) {
                    long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                    ScatterMap this_$iv3 = this_$iv2;
                    int $i$f$forEach2 = $i$f$forEach;
                    if ((((~slot$iv$iv2) << c) & slot$iv$iv2 & j3) == j3) {
                        k$iv = k$iv4;
                        v$iv = v$iv3;
                        this_$iv$iv = this_$iv$iv2;
                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                    } else {
                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                        int j$iv$iv3 = 0;
                        while (j$iv$iv3 < bitCount$iv$iv2) {
                            long value$iv$iv$iv2 = slot$iv$iv2 & j2;
                            int $i$f$isFull2 = value$iv$iv$iv2 < j ? 1 : 0;
                            if ($i$f$isFull2 == 0) {
                                j$iv$iv = j$iv$iv3;
                                k$iv2 = k$iv4;
                            } else {
                                int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv3;
                                Object obj2 = k$iv4[index$iv$iv2];
                                j$iv$iv = j$iv$iv3;
                                List list = (List) v$iv3[index$iv$iv2];
                                String key = (String) obj2;
                                k$iv2 = k$iv4;
                                if (list.size() == 1) {
                                    Object value = ((Function0) list.get(0)).invoke();
                                    if (value != null) {
                                        if (!canBeSaved(value)) {
                                            throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(value).toString());
                                        }
                                        map.put(key, CollectionsKt.arrayListOf(value));
                                    }
                                } else {
                                    HashMap map2 = map;
                                    int size2 = list.size();
                                    ArrayList arrayList = new ArrayList(size2);
                                    int $i$f$forEachIndexed3 = 0;
                                    while ($i$f$forEachIndexed3 < size2) {
                                        int index = $i$f$forEachIndexed3;
                                        int i = size2;
                                        List list2 = list;
                                        Object value2 = ((Function0) list.get(index)).invoke();
                                        if (value2 == null || canBeSaved(value2)) {
                                            arrayList.add(value2);
                                            $i$f$forEachIndexed3++;
                                            size2 = i;
                                            list = list2;
                                        } else {
                                            throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(value2).toString());
                                        }
                                    }
                                    map2.put(key, arrayList);
                                }
                            }
                            slot$iv$iv2 >>= 8;
                            j$iv$iv3 = j$iv$iv + 1;
                            this_$iv$iv2 = this_$iv$iv2;
                            k$iv4 = k$iv2;
                            v$iv3 = v$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed2;
                        }
                        k$iv = k$iv4;
                        v$iv = v$iv3;
                        this_$iv$iv = this_$iv$iv2;
                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                        if (bitCount$iv$iv2 == 8) {
                        }
                    }
                    if (i$iv$iv2 != lastIndex$iv$iv2) {
                        i$iv$iv2++;
                        this_$iv2 = this_$iv3;
                        $i$f$forEach = $i$f$forEach2;
                        this_$iv$iv2 = this_$iv$iv;
                        k$iv4 = k$iv;
                        v$iv3 = v$iv;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                    }
                }
            }
        }
        return map;
    }
}
