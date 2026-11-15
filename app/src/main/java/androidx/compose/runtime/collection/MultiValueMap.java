package androidx.compose.runtime.collection;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.collection.ScatterMap;
import androidx.exifinterface.media.ExifInterface;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MultiValueMap.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u001d\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00162\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0012¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0012¢\u0006\u0004\b\u001d\u0010\u001bJ\u0017\u0010\u001e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\"\u0010 J\u0013\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\u0016¢\u0006\u0004\b$\u0010%J;\u0010&\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002!\u0010'\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\t0(H\u0086\b¢\u0006\u0004\b+\u0010,J3\u0010&\u001a\u00020\t2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\t0(H\u0086\b¢\u0006\u0004\b+\u0010-J8\u0010.\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002!\u0010/\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0004\b0\u0010,J\u0013\u00101\u001a\u00020\u00122\b\u00102\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u000206HÖ\u0001R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0004\u0092\u0001\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¨\u00067"}, d2 = {"Landroidx/compose/runtime/collection/MultiValueMap;", "K", "", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "map", "Landroidx/collection/MutableScatterMap;", "constructor-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/MutableScatterMap;", "add", "", "key", "value", "add-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)V", "clear", "clear-impl", "(Landroidx/collection/MutableScatterMap;)V", "contains", "", "contains-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Z", "get", "Landroidx/collection/ObjectList;", "get-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Landroidx/collection/ObjectList;", "isEmpty", "isEmpty-impl", "(Landroidx/collection/MutableScatterMap;)Z", "isNotEmpty", "isNotEmpty-impl", "removeLast", "removeLast-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Ljava/lang/Object;", "removeFirst", "removeFirst-impl", "values", "values-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/ObjectList;", "forEachValue", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "forEachValue-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function1;)V", "removeValueIf", "condition", "removeValueIf-impl", MetadataValidation.EQUALS, "other", "hashCode", "", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class MultiValueMap<K, V> {
    private final MutableScatterMap<Object, Object> map;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ MultiValueMap m4444boximpl(MutableScatterMap mutableScatterMap) {
        return new MultiValueMap(mutableScatterMap);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <K, V> MutableScatterMap<Object, Object> m4446constructorimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4449equalsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Object obj) {
        return (obj instanceof MultiValueMap) && Intrinsics.areEqual(mutableScatterMap, ((MultiValueMap) obj).getMap());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4450equalsimpl0(MutableScatterMap<Object, Object> mutableScatterMap, MutableScatterMap<Object, Object> mutableScatterMap2) {
        return Intrinsics.areEqual(mutableScatterMap, mutableScatterMap2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4454hashCodeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4460toStringimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return "MultiValueMap(map=" + mutableScatterMap + ')';
    }

    public boolean equals(Object other) {
        return m4449equalsimpl(this.map, other);
    }

    public int hashCode() {
        return m4454hashCodeimpl(this.map);
    }

    public String toString() {
        return m4460toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableScatterMap getMap() {
        return this.map;
    }

    private /* synthetic */ MultiValueMap(MutableScatterMap map) {
        this.map = map;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ MutableScatterMap m4447constructorimpl$default(MutableScatterMap mutableScatterMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = 1;
        if ((i & 1) != 0) {
            mutableScatterMap = new MutableScatterMap(0, i2, null);
        }
        return m4446constructorimpl(mutableScatterMap);
    }

    /* JADX INFO: renamed from: add-impl, reason: not valid java name */
    public static final void m4443addimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, V v) {
        MutableObjectList mutableObjectListMutableObjectListOf;
        MutableObjectList mutableObjectList;
        Object obj;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(k);
        boolean z = iFindInsertIndex < 0;
        Object obj2 = z ? null : mutableScatterMap.values[iFindInsertIndex];
        boolean z2 = !TypeIntrinsics.isMutableList(obj2);
        if (obj2 == null) {
            obj = v;
        } else if (obj2 instanceof MutableObjectList) {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableObjectList<kotlin.Any>");
            mutableObjectList = (MutableObjectList) obj2;
            mutableObjectList.add(v);
        } else {
            mutableObjectListMutableObjectListOf = ObjectListKt.mutableObjectListOf(obj2, v);
        }
        if (z) {
            obj = mutableObjectListMutableObjectListOf;
            obj = mutableObjectList;
            int i = ~iFindInsertIndex;
            mutableScatterMap.keys[i] = k;
            mutableScatterMap.values[i] = obj;
            return;
        }
        obj = mutableObjectListMutableObjectListOf;
        obj = mutableObjectList;
        mutableScatterMap.values[iFindInsertIndex] = obj;
    }

    /* JADX INFO: renamed from: clear-impl, reason: not valid java name */
    public static final void m4445clearimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        mutableScatterMap.clear();
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m4448containsimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        return mutableScatterMap.contains(k);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final ObjectList<V> m4453getimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        Object entry = mutableScatterMap.get(k);
        if (entry == null) {
            return ObjectListKt.emptyObjectList();
        }
        return entry instanceof MutableObjectList ? (ObjectList) entry : ObjectListKt.objectListOf(entry);
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m4455isEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isEmpty();
    }

    /* JADX INFO: renamed from: isNotEmpty-impl, reason: not valid java name */
    public static final boolean m4456isNotEmptyimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.isNotEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: removeLast-impl, reason: not valid java name */
    public static final V m4458removeLastimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        V v = (V) mutableScatterMap.get(k);
        if (v == 0) {
            return null;
        }
        if (v instanceof MutableObjectList) {
            MutableObjectList mutableObjectList = (MutableObjectList) v;
            V v2 = (V) ExtensionsKt.removeLast(mutableObjectList);
            Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
            if (mutableObjectList.isEmpty()) {
                mutableScatterMap.remove(k);
            }
            if (mutableObjectList.getSize() == 1) {
                mutableScatterMap.set(k, mutableObjectList.first());
            }
            return v2;
        }
        mutableScatterMap.remove(k);
        return v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: removeFirst-impl, reason: not valid java name */
    public static final V m4457removeFirstimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k) {
        V v = (V) mutableScatterMap.get(k);
        if (v == 0) {
            return null;
        }
        if (v instanceof MutableObjectList) {
            MutableObjectList mutableObjectList = (MutableObjectList) v;
            V v2 = (V) mutableObjectList.removeAt(0);
            if (mutableObjectList.isEmpty()) {
                mutableScatterMap.remove(k);
            }
            if (mutableObjectList.getSize() == 1) {
                mutableScatterMap.set(k, mutableObjectList.first());
            }
            return v2;
        }
        mutableScatterMap.remove(k);
        return v;
    }

    /* JADX INFO: renamed from: values-impl, reason: not valid java name */
    public static final ObjectList<V> m4461valuesimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        ScatterMap this_$iv;
        ScatterMap this_$iv2;
        int i;
        if (mutableScatterMap.isEmpty()) {
            return ObjectListKt.emptyObjectList();
        }
        MutableObjectList result = new MutableObjectList(0, 1, null);
        MutableScatterMap<Object, Object> this_$iv3 = mutableScatterMap;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object entry = v$iv[index$iv$iv];
                            this_$iv2 = this_$iv3;
                            if (entry instanceof MutableObjectList) {
                                Intrinsics.checkNotNull(entry, "null cannot be cast to non-null type androidx.collection.MutableObjectList<V of androidx.compose.runtime.collection.MultiValueMap>");
                                result.addAll((MutableObjectList) entry);
                            } else {
                                Intrinsics.checkNotNull(entry, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
                                result.add(entry);
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        this_$iv3 = this_$iv2;
                    }
                    this_$iv = this_$iv3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv3 = this_$iv;
            }
        }
        return result;
    }

    /* JADX INFO: renamed from: forEachValue-impl, reason: not valid java name */
    public static final void m4451forEachValueimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, Function1<? super V, Unit> function1) {
        Object it = mutableScatterMap.get(k);
        if (it != null) {
            if (it instanceof MutableObjectList) {
                ObjectList this_$iv = (ObjectList) it;
                Object[] content$iv = this_$iv.content;
                int i = this_$iv._size;
                for (int i$iv = 0; i$iv < i; i$iv++) {
                    Object value = content$iv[i$iv];
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
                    function1.invoke(value);
                }
                return;
            }
            function1.invoke(it);
        }
    }

    /* JADX INFO: renamed from: forEachValue-impl, reason: not valid java name */
    public static final void m4452forEachValueimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function1<? super V, Unit> function1) {
        int $i$f$forEachValue;
        Object[] v$iv;
        ScatterMap this_$iv$iv;
        int i;
        int $i$f$forEachValue2;
        int i2 = 0;
        MutableScatterMap<Object, Object> this_$iv = mutableScatterMap;
        int $i$f$forEachValue3 = 0;
        Object[] v$iv2 = this_$iv.values;
        ScatterMap this_$iv$iv2 = this_$iv;
        long[] m$iv$iv = this_$iv$iv2.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            int i3 = i2;
            ScatterMap this_$iv2 = this_$iv;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$forEachValue = $i$f$forEachValue3;
                v$iv = v$iv2;
                this_$iv$iv = this_$iv$iv2;
            } else {
                int i4 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i4;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i4;
                        Object it = v$iv2[index$iv$iv];
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (it instanceof MutableObjectList) {
                            ObjectList this_$iv3 = (ObjectList) it;
                            Object[] content$iv = this_$iv3.content;
                            int i5 = this_$iv3._size;
                            int i$iv = 0;
                            while (i$iv < i5) {
                                int i$iv2 = i$iv;
                                Object value = content$iv[i$iv2];
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
                                function1.invoke(value);
                                i$iv = i$iv2 + 1;
                            }
                        } else {
                            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type V of androidx.compose.runtime.collection.MultiValueMap");
                            function1.invoke(it);
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i4 = i;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    v$iv2 = v$iv2;
                    this_$iv$iv2 = this_$iv$iv2;
                }
                $i$f$forEachValue = $i$f$forEachValue3;
                v$iv = v$iv2;
                this_$iv$iv = this_$iv$iv2;
                if (bitCount$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            i2 = i3;
            this_$iv = this_$iv2;
            $i$f$forEachValue3 = $i$f$forEachValue;
            v$iv2 = v$iv;
            this_$iv$iv2 = this_$iv$iv;
        }
    }

    /* JADX INFO: renamed from: removeValueIf-impl, reason: not valid java name */
    public static final void m4459removeValueIfimpl(MutableScatterMap<Object, Object> mutableScatterMap, K k, Function1<? super V, Boolean> function1) {
        Object it = mutableScatterMap.get(k);
        if (it != null) {
            if (it instanceof MutableObjectList) {
                MutableObjectList this_$iv = (MutableObjectList) it;
                int gap$iv = 0;
                int size$iv = this_$iv._size;
                Object[] content$iv = this_$iv.content;
                MutableObjectList this_$iv$iv = this_$iv;
                IntRange intRangeUntil = RangesKt.until(0, this_$iv$iv._size);
                int i$iv = intRangeUntil.getFirst();
                int last = intRangeUntil.getLast();
                if (i$iv <= last) {
                    while (true) {
                        content$iv[i$iv - gap$iv] = content$iv[i$iv];
                        if (function1.invoke(content$iv[i$iv]).booleanValue()) {
                            gap$iv++;
                        }
                        if (i$iv == last) {
                            break;
                        } else {
                            i$iv++;
                        }
                    }
                }
                ArraysKt.fill(content$iv, (Object) null, size$iv - gap$iv, size$iv);
                this_$iv._size -= gap$iv;
                if (((MutableObjectList) it).isEmpty()) {
                    mutableScatterMap.remove(k);
                }
                if (((MutableObjectList) it).getSize() == 0) {
                    mutableScatterMap.set(k, ((MutableObjectList) it).first());
                    return;
                }
                return;
            }
            if (function1.invoke(it).booleanValue()) {
                mutableScatterMap.remove(k);
            }
        }
    }
}
