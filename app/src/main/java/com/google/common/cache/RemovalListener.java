package com.google.common.cache;

/* JADX INFO: loaded from: classes12.dex */
@ElementTypesAreNonnullByDefault
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> notification);
}
