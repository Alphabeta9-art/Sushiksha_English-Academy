package androidx.work;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public final class ArrayCreatingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    public Data merge(List<Data> inputs) {
        Object mergedValue;
        Data.Builder output = new Data.Builder();
        Map<String, Object> mergedValues = new HashMap<>();
        for (Data input : inputs) {
            for (Map.Entry<String, Object> entry : input.getKeyValueMap().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Class<?> valueClass = value.getClass();
                Object existingValue = mergedValues.get(key);
                if (existingValue == null) {
                    if (valueClass.isArray()) {
                        mergedValue = value;
                    } else {
                        mergedValue = createArrayFor(value);
                    }
                } else {
                    Class<?> existingValueClass = existingValue.getClass();
                    if (existingValueClass.equals(valueClass)) {
                        if (existingValueClass.isArray()) {
                            mergedValue = concatenateArrays(existingValue, value);
                        } else {
                            Object mergedValue2 = concatenateNonArrays(existingValue, value);
                            mergedValue = mergedValue2;
                        }
                    } else if (existingValueClass.isArray() && existingValueClass.getComponentType().equals(valueClass)) {
                        mergedValue = concatenateArrayAndNonArray(existingValue, value);
                    } else if (valueClass.isArray() && valueClass.getComponentType().equals(existingValueClass)) {
                        mergedValue = concatenateArrayAndNonArray(value, existingValue);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                mergedValues.put(key, mergedValue);
            }
        }
        output.putAll(mergedValues);
        return output.build();
    }

    private Object concatenateArrays(Object array1, Object array2) {
        int length1 = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        Object newArray = Array.newInstance(array1.getClass().getComponentType(), length1 + length2);
        System.arraycopy(array1, 0, newArray, 0, length1);
        System.arraycopy(array2, 0, newArray, length1, length2);
        return newArray;
    }

    private Object concatenateNonArrays(Object obj1, Object obj2) {
        Object newArray = Array.newInstance(obj1.getClass(), 2);
        Array.set(newArray, 0, obj1);
        Array.set(newArray, 1, obj2);
        return newArray;
    }

    private Object concatenateArrayAndNonArray(Object array, Object obj) {
        int arrayLength = Array.getLength(array);
        Object newArray = Array.newInstance(obj.getClass(), arrayLength + 1);
        System.arraycopy(array, 0, newArray, 0, arrayLength);
        Array.set(newArray, arrayLength, obj);
        return newArray;
    }

    private Object createArrayFor(Object obj) {
        Object newArray = Array.newInstance(obj.getClass(), 1);
        Array.set(newArray, 0, obj);
        return newArray;
    }
}
