package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.cache.common.HasDebugData;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class LruCountingMemoryCache<K, V> implements CountingMemoryCache<K, V>, MemoryCache<K, V>, HasDebugData {
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mCachedEntries;

    @Nullable
    private final CountingMemoryCache.EntryStateObserver<K> mEntryStateObserver;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mExclusiveEntries;
    private final boolean mIgnoreSizeMismatch;
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    private final boolean mStoreEntrySize;
    private final ValueDescriptor<V> mValueDescriptor;
    final Map<Bitmap, Object> mOtherEntries = new WeakHashMap();
    private long mLastCacheParamsCheck = SystemClock.uptimeMillis();

    public LruCountingMemoryCache(ValueDescriptor<V> valueDescriptor, MemoryCache.CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> memoryCacheParamsSupplier, @Nullable CountingMemoryCache.EntryStateObserver<K> entryStateObserver, boolean storeEntrySize, boolean ignoreSizeMismatch) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = memoryCacheParamsSupplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
        this.mEntryStateObserver = entryStateObserver;
        this.mStoreEntrySize = storeEntrySize;
        this.mIgnoreSizeMismatch = ignoreSizeMismatch;
    }

    private ValueDescriptor<CountingMemoryCache.Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> evictableValueDescriptor) {
        return new ValueDescriptor<CountingMemoryCache.Entry<K, V>>() { // from class: com.facebook.imagepipeline.cache.LruCountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(CountingMemoryCache.Entry<K, V> entry) {
                if (LruCountingMemoryCache.this.mStoreEntrySize) {
                    return entry.size;
                }
                return evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef) {
        return cache(key, valueRef, this.mEntryStateObserver);
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    @Nullable
    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef, @Nullable CountingMemoryCache.EntryStateObserver<K> observer) {
        CountingMemoryCache.Entry<K, V> oldExclusive;
        CountingMemoryCache.Entry<K, V> newEntry;
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueRef);
        maybeUpdateCacheParams();
        CloseableReference<V> oldRefToClose = null;
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldExclusive = this.mExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> oldEntry = this.mCachedEntries.remove(key);
            if (oldEntry != null) {
                makeOrphan(oldEntry);
                oldRefToClose = referenceToClose(oldEntry);
            }
            V value = valueRef.get();
            int size = this.mValueDescriptor.getSizeInBytes(value);
            if (canCacheNewValueOfSize(size)) {
                if (this.mStoreEntrySize) {
                    newEntry = CountingMemoryCache.Entry.of(key, valueRef, size, observer);
                } else {
                    newEntry = CountingMemoryCache.Entry.of(key, valueRef, observer);
                }
                this.mCachedEntries.put(key, newEntry);
                clientRef = newClientReference(newEntry);
            }
        }
        CloseableReference.closeSafely((CloseableReference<?>) oldRefToClose);
        maybeNotifyExclusiveEntryRemoval(oldExclusive);
        maybeEvictEntries();
        return clientRef;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001f  */
    private synchronized boolean canCacheNewValueOfSize(int newValueSize) {
        boolean z;
        if (newValueSize <= this.mMemoryCacheParams.maxCacheEntrySize) {
            z = getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1 && getInUseSizeInBytes() <= this.mMemoryCacheParams.maxCacheSize - newValueSize;
        }
        return z;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(K key) {
        CountingMemoryCache.Entry<K, V> oldExclusive;
        Preconditions.checkNotNull(key);
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldExclusive = this.mExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.get(key);
            if (entry != null) {
                clientRef = newClientReference(entry);
            }
        }
        maybeNotifyExclusiveEntryRemoval(oldExclusive);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return clientRef;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public synchronized V inspect(K key) {
        CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.get(key);
        if (entry == null) {
            return null;
        }
        return entry.valueRef.get();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public void probe(K key) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            CountingMemoryCache.Entry<K, V> oldExclusive = this.mExclusiveEntries.remove(key);
            if (oldExclusive != null) {
                this.mExclusiveEntries.put(key, oldExclusive);
            }
        }
    }

    private synchronized CloseableReference<V> newClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.LruCountingMemoryCache.2
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(V unused) {
                LruCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseClientReference(CountingMemoryCache.Entry<K, V> entry) {
        boolean isExclusiveAdded;
        CloseableReference<V> oldRefToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            isExclusiveAdded = maybeAddToExclusives(entry);
            oldRefToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely((CloseableReference<?>) oldRefToClose);
        maybeNotifyExclusiveEntryInsertion(isExclusiveAdded ? entry : null);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized boolean maybeAddToExclusives(CountingMemoryCache.Entry<K, V> entry) {
        if (entry.isOrphan || entry.clientCount != 0) {
            return false;
        }
        this.mExclusiveEntries.put(entry.key, entry);
        return true;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    @Nullable
    public CloseableReference<V> reuse(K key) {
        CountingMemoryCache.Entry<K, V> oldExclusive;
        Preconditions.checkNotNull(key);
        CloseableReference<V> clientRef = null;
        boolean removed = false;
        synchronized (this) {
            oldExclusive = this.mExclusiveEntries.remove(key);
            if (oldExclusive != null) {
                CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.remove(key);
                Preconditions.checkNotNull(entry);
                Preconditions.checkState(entry.clientCount == 0);
                CloseableReference<V> clientRef2 = entry.valueRef;
                removed = true;
                clientRef = clientRef2;
            }
        }
        if (removed) {
            maybeNotifyExclusiveEntryRemoval(oldExclusive);
        }
        return clientRef;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        synchronized (this) {
            oldExclusives = this.mExclusiveEntries.removeAll(predicate);
            oldEntries = this.mCachedEntries.removeAll(predicate);
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldExclusives);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return oldEntries.size();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public void clear() {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        synchronized (this) {
            oldExclusives = this.mExclusiveEntries.clear();
            oldEntries = this.mCachedEntries.clear();
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldExclusives);
        maybeUpdateCacheParams();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(K key) {
        return this.mCachedEntries.contains(key);
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType trimType) {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(trimType);
        synchronized (this) {
            int targetCacheSize = (int) (((double) this.mCachedEntries.getSizeInBytes()) * (1.0d - trimRatio));
            int targetEvictionQueueSize = Math.max(0, targetCacheSize - getInUseSizeInBytes());
            oldEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, targetEvictionQueueSize);
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldEntries);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs > SystemClock.uptimeMillis()) {
            return;
        }
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public MemoryCacheParams getMemoryCacheParams() {
        return this.mMemoryCacheParams;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public CountingLruMap<K, CountingMemoryCache.Entry<K, V>> getCachedEntries() {
        return this.mCachedEntries;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public Map<Bitmap, Object> getOtherEntries() {
        return this.mOtherEntries;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public void maybeEvictEntries() {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        synchronized (this) {
            int maxCount = Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount());
            int maxSize = Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes());
            oldEntries = trimExclusivelyOwnedEntries(maxCount, maxSize);
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldEntries);
    }

    @Nullable
    private synchronized ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries(int count, int size) {
        int count2 = Math.max(count, 0);
        int size2 = Math.max(size, 0);
        if (this.mExclusiveEntries.getCount() <= count2 && this.mExclusiveEntries.getSizeInBytes() <= size2) {
            return null;
        }
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries = new ArrayList<>();
        while (true) {
            if (this.mExclusiveEntries.getCount() <= count2 && this.mExclusiveEntries.getSizeInBytes() <= size2) {
                break;
            }
            K key = this.mExclusiveEntries.getFirstKey();
            if (key == null) {
                if (this.mIgnoreSizeMismatch) {
                    this.mExclusiveEntries.resetSize();
                    break;
                }
                throw new IllegalStateException(String.format("key is null, but exclusiveEntries count: %d, size: %d", Integer.valueOf(this.mExclusiveEntries.getCount()), Integer.valueOf(this.mExclusiveEntries.getSizeInBytes())));
            }
            this.mExclusiveEntries.remove(key);
            oldEntries.add(this.mCachedEntries.remove(key));
        }
        return oldEntries;
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            for (CountingMemoryCache.Entry<K, V> oldEntry : oldEntries) {
                CloseableReference.closeSafely((CloseableReference<?>) referenceToClose(oldEntry));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries) {
        if (entries != null) {
            for (CountingMemoryCache.Entry<K, V> entry : entries) {
                maybeNotifyExclusiveEntryRemoval(entry);
            }
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, false);
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, true);
        }
    }

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            for (CountingMemoryCache.Entry<K, V> oldEntry : oldEntries) {
                makeOrphan(oldEntry);
            }
        }
    }

    private synchronized void makeOrphan(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    private synchronized void increaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void decreaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        return (entry.isOrphan && entry.clientCount == 0) ? entry.valueRef : null;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueCount() {
        return this.mExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mExclusiveEntries.getSizeInBytes();
    }

    @Override // com.facebook.cache.common.HasDebugData
    @Nullable
    public synchronized String getDebugData() {
        return Objects.toStringHelper("CountingMemoryCache").add("cached_entries_count", this.mCachedEntries.getCount()).add("cached_entries_size_bytes", this.mCachedEntries.getSizeInBytes()).add("exclusive_entries_count", this.mExclusiveEntries.getCount()).add("exclusive_entries_size_bytes", this.mExclusiveEntries.getSizeInBytes()).toString();
    }
}
