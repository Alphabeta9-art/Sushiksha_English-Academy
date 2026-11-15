package androidx.media3.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.media3.common.Bundleable;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public final class BundleableUtil {
    public static <T extends Bundleable> ImmutableList<Bundle> toBundleList(List<T> bundleableList) {
        return toBundleList(bundleableList, new Function() { // from class: androidx.media3.common.util.BundleableUtil$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ((Bundleable) obj).toBundle();
            }
        });
    }

    public static <T extends Bundleable> ImmutableList<Bundle> toBundleList(List<T> bundleableList, Function<T, Bundle> customToBundleFunc) {
        ImmutableList.Builder<Bundle> builder = ImmutableList.builder();
        for (int i = 0; i < bundleableList.size(); i++) {
            T bundleable = bundleableList.get(i);
            builder.add(customToBundleFunc.apply(bundleable));
        }
        return builder.build();
    }

    public static <T extends Bundleable> ImmutableList<T> fromBundleList(Bundleable.Creator<T> creator, List<Bundle> bundleList) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < bundleList.size(); i++) {
            Bundle bundle = (Bundle) Assertions.checkNotNull(bundleList.get(i));
            builder.add(creator.fromBundle(bundle));
        }
        return builder.build();
    }

    public static <T extends Bundleable> ArrayList<Bundle> toBundleArrayList(Collection<T> bundleables) {
        ArrayList<Bundle> arrayList = new ArrayList<>(bundleables.size());
        for (T element : bundleables) {
            arrayList.add(element.toBundle());
        }
        return arrayList;
    }

    public static <T extends Bundleable> SparseArray<T> fromBundleSparseArray(Bundleable.Creator<T> creator, SparseArray<Bundle> sparseArray) {
        ParcelableSparseArray parcelableSparseArray = (SparseArray<T>) new SparseArray(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            parcelableSparseArray.put(sparseArray.keyAt(i), creator.fromBundle(sparseArray.valueAt(i)));
        }
        return parcelableSparseArray;
    }

    public static <T extends Bundleable> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> bundleableSparseArray) {
        SparseArray<Bundle> sparseArray = new SparseArray<>(bundleableSparseArray.size());
        for (int i = 0; i < bundleableSparseArray.size(); i++) {
            sparseArray.put(bundleableSparseArray.keyAt(i), bundleableSparseArray.valueAt(i).toBundle());
        }
        return sparseArray;
    }

    public static Bundle stringMapToBundle(Map<String, String> bundleableMap) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : bundleableMap.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public static HashMap<String, String> bundleToStringHashMap(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        if (bundle == Bundle.EMPTY) {
            return map;
        }
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }

    public static ImmutableMap<String, String> bundleToStringImmutableMap(Bundle bundle) {
        if (bundle == Bundle.EMPTY) {
            return ImmutableMap.of();
        }
        HashMap<String, String> map = bundleToStringHashMap(bundle);
        return ImmutableMap.copyOf((Map) map);
    }

    public static Bundle getBundleWithDefault(Bundle bundle, String field, Bundle defaultValue) {
        Bundle result = bundle.getBundle(field);
        return result != null ? result : defaultValue;
    }

    public static ArrayList<Integer> getIntegerArrayListWithDefault(Bundle bundle, String field, ArrayList<Integer> defaultValue) {
        ArrayList<Integer> result = bundle.getIntegerArrayList(field);
        return result != null ? result : defaultValue;
    }

    public static void ensureClassLoader(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader((ClassLoader) Util.castNonNull(BundleableUtil.class.getClassLoader()));
        }
    }

    private BundleableUtil() {
    }
}
