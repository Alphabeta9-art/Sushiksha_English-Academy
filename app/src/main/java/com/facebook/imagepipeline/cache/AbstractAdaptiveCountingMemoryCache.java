package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractAdaptiveCountingMemoryCache<K, V> implements CountingMemoryCache<K, V> {
    static final int DEFAULT_ADAPTIVE_RATE_PROMIL = 10;
    static final int DEFAULT_LFU_FRACTION_PROMIL = 500;
    static final int MAX_FRACTION_PROMIL = 900;
    static final int MIN_FRACTION_PROMIL = 100;
    private static final String TAG = "AbstractArcCountingMemoryCache";
    static final int TOTAL_PROMIL = 1000;
    final int mAdaptiveRatePromil;
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mCachedEntries;
    private final int mFrequentlyUsedThreshold;
    final int mGhostListMaxSize;
    int mLFUFractionPromil;
    private long mLastCacheParamsCheck;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mLeastFrequentlyUsedExclusiveEntries;
    final AbstractAdaptiveCountingMemoryCache<K, V>.IntMapArrayList<K> mLeastFrequentlyUsedKeysGhostList;
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mMostFrequentlyUsedExclusiveEntries;
    final ArrayList<K> mMostFrequentlyUsedKeysGhostList;
    private final ValueDescriptor<V> mValueDescriptor;

    enum ArrayListType {
        LFU,
        MFU
    }

    protected abstract void logIllegalAdaptiveRate();

    protected abstract void logIllegalLfuFraction();

    public AbstractAdaptiveCountingMemoryCache(Supplier<MemoryCacheParams> memoryCacheParamsSupplier, MemoryCache.CacheTrimStrategy cacheTrimStrategy, ValueDescriptor<V> valueDescriptor, int adaptiveRatePromil, int frequentlyUsedThreshold, int ghostListMaxSize, int lfuFractionPromil) {
        FLog.d(TAG, "Create Adaptive Replacement Cache");
        this.mValueDescriptor = valueDescriptor;
        this.mLeastFrequentlyUsedExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mMostFrequentlyUsedExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = memoryCacheParamsSupplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mFrequentlyUsedThreshold = frequentlyUsedThreshold;
        this.mGhostListMaxSize = ghostListMaxSize;
        this.mLeastFrequentlyUsedKeysGhostList = new IntMapArrayList<>(this.mGhostListMaxSize);
        this.mMostFrequentlyUsedKeysGhostList = new ArrayList<>(this.mGhostListMaxSize);
        if (lfuFractionPromil < 100 || lfuFractionPromil > 900) {
            this.mLFUFractionPromil = 500;
            logIllegalLfuFraction();
        } else {
            this.mLFUFractionPromil = lfuFractionPromil;
        }
        if (adaptiveRatePromil <= 0 || adaptiveRatePromil >= 1000) {
            this.mAdaptiveRatePromil = 10;
            logIllegalAdaptiveRate();
        } else {
            this.mAdaptiveRatePromil = adaptiveRatePromil;
        }
    }

    private ValueDescriptor<CountingMemoryCache.Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> evictableValueDescriptor) {
        return new ValueDescriptor<CountingMemoryCache.Entry<K, V>>() { // from class: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(CountingMemoryCache.Entry<K, V> entry) {
                return evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef) {
        return cache(key, valueRef, null);
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    @Nullable
    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef, @Nullable CountingMemoryCache.EntryStateObserver<K> observer) {
        CountingMemoryCache.Entry<K, V> oldLFUExclusive;
        CountingMemoryCache.Entry<K, V> oldMFUExclusive;
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueRef);
        maybeUpdateCacheParams();
        CloseableReference<V> oldRefToClose = null;
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldLFUExclusive = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            oldMFUExclusive = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            boolean notPresentInBoth = oldLFUExclusive == null || oldMFUExclusive == null;
            Preconditions.checkState(notPresentInBoth);
            CountingMemoryCache.Entry<K, V> oldEntry = this.mCachedEntries.remove(key);
            if (oldEntry != null) {
                makeOrphan(oldEntry);
                oldRefToClose = referenceToClose(oldEntry);
            }
            if (canCacheNewValue(valueRef.get())) {
                CountingMemoryCache.Entry<K, V> newEntry = CountingMemoryCache.Entry.of(key, valueRef, observer);
                Integer storedAccessCount = this.mLeastFrequentlyUsedKeysGhostList.getValue(key);
                newEntry.accessCount = storedAccessCount != null ? storedAccessCount.intValue() : 0;
                this.mCachedEntries.put(key, newEntry);
                clientRef = newClientReference(newEntry);
            }
        }
        CloseableReference.closeSafely((CloseableReference<?>) oldRefToClose);
        maybeNotifyExclusiveEntryRemoval(oldLFUExclusive, oldMFUExclusive);
        maybeEvictEntries();
        return clientRef;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025  */
    private synchronized boolean canCacheNewValue(V value) {
        boolean z;
        int newValueSize = this.mValueDescriptor.getSizeInBytes(value);
        if (newValueSize <= this.mMemoryCacheParams.maxCacheEntrySize) {
            z = getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1 && getInUseSizeInBytes() <= this.mMemoryCacheParams.maxCacheSize - newValueSize;
        }
        return z;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(K key) {
        CountingMemoryCache.Entry<K, V> oldLFUExclusive;
        CountingMemoryCache.Entry<K, V> oldMFUExclusive;
        Preconditions.checkNotNull(key);
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldLFUExclusive = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            oldMFUExclusive = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.get(key);
            if (entry != null) {
                clientRef = newClientReference(entry);
            } else {
                maybeUpdateCacheFraction(key);
            }
        }
        maybeNotifyExclusiveEntryRemoval(oldLFUExclusive, oldMFUExclusive);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return clientRef;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public V inspect(K key) {
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
            CountingMemoryCache.Entry<K, V> oldExclusive = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            if (oldExclusive == null) {
                oldExclusive = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            }
            if (oldExclusive != null) {
                increaseAccessCount(oldExclusive);
                maybeAddToExclusives(oldExclusive);
            }
        }
    }

    private synchronized void maybeUpdateCacheFraction(K key) {
        if (this.mLeastFrequentlyUsedKeysGhostList.contains(key)) {
            if (this.mLFUFractionPromil + this.mAdaptiveRatePromil <= 900) {
                this.mLFUFractionPromil += this.mAdaptiveRatePromil;
            }
            this.mLeastFrequentlyUsedKeysGhostList.increaseValueIfExists(key);
        } else if (this.mLFUFractionPromil - this.mAdaptiveRatePromil >= 100 && this.mMostFrequentlyUsedKeysGhostList.contains(key)) {
            this.mLFUFractionPromil -= this.mAdaptiveRatePromil;
        }
    }

    private synchronized CloseableReference<V> newClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        increaseCounters(entry);
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.2
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(V unused) {
                AbstractAdaptiveCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    private synchronized void addElementToGhostList(K key, int accessCount, ArrayListType evictionType) {
        if (evictionType == ArrayListType.LFU) {
            this.mLeastFrequentlyUsedKeysGhostList.addPair(key, Integer.valueOf(accessCount));
        } else {
            if (this.mMostFrequentlyUsedKeysGhostList.size() == this.mGhostListMaxSize) {
                this.mMostFrequentlyUsedKeysGhostList.remove(0);
            }
            this.mMostFrequentlyUsedKeysGhostList.add(key);
        }
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
        if (entry.accessCount > this.mFrequentlyUsedThreshold) {
            this.mMostFrequentlyUsedExclusiveEntries.put(entry.key, entry);
        } else {
            this.mLeastFrequentlyUsedExclusiveEntries.put(entry.key, entry);
        }
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
            oldExclusive = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            if (oldExclusive == null) {
                oldExclusive = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            }
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
        ArrayList<CountingMemoryCache.Entry<K, V>> oldLFUExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldMFUExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        synchronized (this) {
            oldLFUExclusives = this.mLeastFrequentlyUsedExclusiveEntries.removeAll(predicate);
            oldMFUExclusives = this.mMostFrequentlyUsedExclusiveEntries.removeAll(predicate);
            oldEntries = this.mCachedEntries.removeAll(predicate);
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntriesRemoval(oldLFUExclusives, oldMFUExclusives);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return oldEntries.size();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public void clear() {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldLFUExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldMFUExclusives;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries;
        synchronized (this) {
            oldLFUExclusives = this.mLeastFrequentlyUsedExclusiveEntries.clear();
            oldMFUExclusives = this.mMostFrequentlyUsedExclusiveEntries.clear();
            oldEntries = this.mCachedEntries.clear();
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntriesRemoval(oldLFUExclusives, oldMFUExclusives);
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
        ArrayList<CountingMemoryCache.Entry<K, V>> oldLFUEntries;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldMFUEntries;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(trimType);
        synchronized (this) {
            int targetCacheSize = (int) (((double) this.mCachedEntries.getSizeInBytes()) * (1.0d - trimRatio));
            int targetEvictionQueueSize = Math.max(0, targetCacheSize - getInUseSizeInBytes());
            int MFUTargetEvictionQueueSize = this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
            int LFUTargetEvictionQueueSize = Math.max(0, targetEvictionQueueSize - MFUTargetEvictionQueueSize);
            if (targetEvictionQueueSize <= MFUTargetEvictionQueueSize) {
                MFUTargetEvictionQueueSize = targetEvictionQueueSize;
                LFUTargetEvictionQueueSize = 0;
            }
            oldLFUEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, LFUTargetEvictionQueueSize, this.mLeastFrequentlyUsedExclusiveEntries, ArrayListType.LFU);
            oldMFUEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, MFUTargetEvictionQueueSize, this.mMostFrequentlyUsedExclusiveEntries, ArrayListType.MFU);
            makeOrphans(oldLFUEntries, oldMFUEntries);
        }
        maybeClose(oldLFUEntries, oldMFUEntries);
        maybeNotifyExclusiveEntriesRemoval(oldLFUEntries, oldMFUEntries);
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
    public void maybeEvictEntries() {
        ArrayList<CountingMemoryCache.Entry<K, V>> oldLFUEntries;
        ArrayList<CountingMemoryCache.Entry<K, V>> oldMFUEntries;
        synchronized (this) {
            int maxCount = Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount());
            int maxSize = Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes());
            int LFUMaxCount = (int) ((((long) maxCount) * ((long) this.mLFUFractionPromil)) / 1000);
            int LFUMaxSize = (int) ((((long) maxSize) * ((long) this.mLFUFractionPromil)) / 1000);
            oldLFUEntries = trimExclusivelyOwnedEntries(LFUMaxCount, LFUMaxSize, this.mLeastFrequentlyUsedExclusiveEntries, ArrayListType.LFU);
            oldMFUEntries = trimExclusivelyOwnedEntries(maxCount - LFUMaxCount, maxSize - LFUMaxSize, this.mMostFrequentlyUsedExclusiveEntries, ArrayListType.MFU);
            makeOrphans(oldLFUEntries, oldMFUEntries);
        }
        maybeClose(oldLFUEntries, oldMFUEntries);
        maybeNotifyExclusiveEntriesRemoval(oldLFUEntries, oldMFUEntries);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    private synchronized ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries(int i, int i2, CountingLruMap<K, CountingMemoryCache.Entry<K, V>> countingLruMap, ArrayListType arrayListType) {
        int iMax = Math.max(i, 0);
        int iMax2 = Math.max(i2, 0);
        if (countingLruMap.getCount() <= iMax && countingLruMap.getSizeInBytes() <= iMax2) {
            return null;
        }
        ArrayList<CountingMemoryCache.Entry<K, V>> arrayList = new ArrayList<>();
        while (true) {
            if (countingLruMap.getCount() <= iMax && countingLruMap.getSizeInBytes() <= iMax2) {
                return arrayList;
            }
            Object objCheckNotNull = Preconditions.checkNotNull(countingLruMap.getFirstKey());
            addElementToGhostList(objCheckNotNull, ((CountingMemoryCache.Entry) Preconditions.checkNotNull((CountingMemoryCache.Entry) countingLruMap.get(objCheckNotNull))).accessCount, arrayListType);
            countingLruMap.remove(objCheckNotNull);
            arrayList.add(this.mCachedEntries.remove((K) objCheckNotNull));
        }
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries2) {
        maybeClose(oldEntries1);
        maybeClose(oldEntries2);
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            for (CountingMemoryCache.Entry<K, V> oldEntry : oldEntries) {
                CloseableReference.closeSafely((CloseableReference<?>) referenceToClose(oldEntry));
            }
        }
    }

    private void maybeNotifyExclusiveEntriesRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries2) {
        maybeNotifyExclusiveEntryRemoval(entries1);
        maybeNotifyExclusiveEntryRemoval(entries2);
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry1, @Nullable CountingMemoryCache.Entry<K, V> entry2) {
        maybeNotifyExclusiveEntryRemoval(entry1);
        maybeNotifyExclusiveEntryRemoval(entry2);
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

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries2) {
        makeOrphans(oldEntries1);
        makeOrphans(oldEntries2);
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

    private synchronized void increaseCounters(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
        increaseAccessCount(entry);
    }

    private synchronized void increaseAccessCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.accessCount++;
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
        return (this.mCachedEntries.getCount() - this.mLeastFrequentlyUsedExclusiveEntries.getCount()) - this.mMostFrequentlyUsedExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getInUseSizeInBytes() {
        return (this.mCachedEntries.getSizeInBytes() - this.mLeastFrequentlyUsedExclusiveEntries.getSizeInBytes()) - this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueCount() {
        return this.mLeastFrequentlyUsedExclusiveEntries.getCount() + this.mMostFrequentlyUsedExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mLeastFrequentlyUsedExclusiveEntries.getSizeInBytes() + this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
    }

    public String reportData() {
        return Objects.toStringHelper("CountingMemoryCache").add("cached_entries_count:", this.mCachedEntries.getCount()).add("exclusive_entries_count", getEvictionQueueCount()).toString();
    }

    class IntMapArrayList<E> {
        private final ArrayList<E> mFirstList;
        private final int mMaxCapacity;
        private final ArrayList<Integer> mSecondList;

        public IntMapArrayList(int maxCapacity) {
            this.mFirstList = new ArrayList<>(maxCapacity);
            this.mSecondList = new ArrayList<>(maxCapacity);
            this.mMaxCapacity = maxCapacity;
        }

        public void addPair(E element, Integer second) {
            if (this.mFirstList.size() == this.mMaxCapacity) {
                this.mFirstList.remove(0);
                this.mSecondList.remove(0);
            }
            this.mFirstList.add(element);
            this.mSecondList.add(second);
        }

        public void increaseValueIfExists(E element) {
            int index = this.mFirstList.indexOf(element);
            if (index < 0) {
                return;
            }
            Integer newInt = Integer.valueOf(this.mSecondList.get(index).intValue() + 1);
            if (index == this.mMaxCapacity - 1) {
                this.mSecondList.set(this.mMaxCapacity - 1, newInt);
                return;
            }
            this.mFirstList.remove(index);
            this.mSecondList.remove(index);
            this.mFirstList.add(element);
            this.mSecondList.add(newInt);
        }

        @Nullable
        public Integer getValue(E element) {
            int index = this.mFirstList.indexOf(element);
            if (index < 0) {
                return null;
            }
            return this.mSecondList.get(index);
        }

        public boolean contains(E element) {
            return this.mFirstList.contains(element);
        }

        public int size() {
            return this.mFirstList.size();
        }
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public CountingLruMap getCachedEntries() {
        return this.mCachedEntries;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public Map<Bitmap, Object> getOtherEntries() {
        return Collections.emptyMap();
    }
}
