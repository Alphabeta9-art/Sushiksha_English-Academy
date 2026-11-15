package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.image.CloseableImage;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class CountingLruBitmapMemoryCacheFactory implements BitmapMemoryCacheFactory {
    @Override // com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory
    public CountingMemoryCache<CacheKey, CloseableImage> create(Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier, MemoryTrimmableRegistry memoryTrimmableRegistry, MemoryCache.CacheTrimStrategy trimStrategy, boolean storeEntrySize, boolean ignoreSizeMismatch, @Nullable CountingMemoryCache.EntryStateObserver<CacheKey> observer) {
        ValueDescriptor<CloseableImage> valueDescriptor = new ValueDescriptor<CloseableImage>() { // from class: com.facebook.imagepipeline.cache.CountingLruBitmapMemoryCacheFactory.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(CloseableImage value) {
                return value.getSizeInBytes();
            }
        };
        CountingMemoryCache<CacheKey, CloseableImage> countingCache = new LruCountingMemoryCache<>(valueDescriptor, trimStrategy, bitmapMemoryCacheParamsSupplier, observer, storeEntrySize, ignoreSizeMismatch);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingCache);
        return countingCache;
    }
}
