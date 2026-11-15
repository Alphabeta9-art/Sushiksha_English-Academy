package com.google.common.cache;

/* JADX INFO: loaded from: classes12.dex */
@ElementTypesAreNonnullByDefault
public interface Weigher<K, V> {
    int weigh(K key, V value);
}
