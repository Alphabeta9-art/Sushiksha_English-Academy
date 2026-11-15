package com.facebook.infer.annotation;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class Assertions {
    public static <T> T assertNotNull(@Nullable T object, String explanation) {
        if (object == null) {
            throw new AssertionError(explanation);
        }
        return object;
    }

    public static <T> T assertNotNull(@Nullable T object) {
        if (object == null) {
            throw new AssertionError();
        }
        return object;
    }

    public static <T> T assumeNotNull(@Nullable T object, String explanation) {
        return object;
    }

    public static <T> T assumeNotNull(@Nullable T object) {
        return object;
    }

    public static <T> T nullsafeFIXME(@Nullable T object, String explanationOrTask) {
        return object;
    }

    public static <T> T assertGet(int i, List<T> list) {
        assertCondition(i >= 0 && i < list.size(), "Index not in bound");
        return (T) assertNotNull(list.get(i), "Null value");
    }

    public static <K, V> V assertGet(K k, Map<K, V> map) {
        assertCondition(map.containsKey(k), "Key not found");
        return (V) assertNotNull(map.get(k), "Null value");
    }

    public static void assumeCondition(boolean condition) {
    }

    public static void assumeCondition(boolean condition, String explanation) {
    }

    public static void assertCondition(boolean condition) {
        if (!condition) {
            throw new AssertionError();
        }
    }

    public static void assertCondition(boolean condition, String explanation) {
        if (!condition) {
            throw new AssertionError(explanation);
        }
    }

    public static AssertionError assertUnreachable() {
        throw new AssertionError();
    }

    public static AssertionError assertUnreachable(String explanation) {
        throw new AssertionError(explanation);
    }

    public static AssertionError assertUnreachable(Exception exception) {
        throw new AssertionError(exception);
    }
}
