package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;
    final SparseArray<Bucket<V>> mBuckets;
    final Counter mFree;
    private boolean mIgnoreHardCap;
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    final Counter mUsed;

    protected abstract V alloc(int i);

    protected abstract void free(V v);

    protected abstract int getBucketedSize(int i);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i);

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        if (this.mPoolParams.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean ignoreHardCap) {
        this(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.mIgnoreHardCap = ignoreHardCap;
    }

    protected void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    @Nullable
    protected synchronized V getValue(Bucket<V> bucket) {
        return bucket.get();
    }

    @Override // com.facebook.common.memory.Pool
    public V get(int size) throws Throwable {
        V value;
        V value2;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(size);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null && (value2 = getValue(bucket)) != null) {
                Preconditions.checkState(this.mInUseValues.add(value2));
                int bucketedSize2 = getBucketedSizeForValue(value2);
                int sizeInBytes = getSizeInBytes(bucketedSize2);
                this.mUsed.increment(sizeInBytes);
                this.mFree.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value2)), Integer.valueOf(bucketedSize2));
                }
                return value2;
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (!canAllocate(sizeInBytes2)) {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
            this.mUsed.increment(sizeInBytes2);
            if (bucket != null) {
                bucket.incrementInUseCount();
            }
            try {
                V value3 = alloc(bucketedSize);
                value = value3;
            } catch (Throwable e) {
                synchronized (this) {
                    this.mUsed.decrement(sizeInBytes2);
                    Bucket<V> bucket2 = getBucket(bucketedSize);
                    if (bucket2 != null) {
                        bucket2.decrementInUseCount();
                    }
                    Throwables.propagateIfPossible(e);
                    value = null;
                }
            }
            synchronized (this) {
                Preconditions.checkState(this.mInUseValues.add(value));
                trimToSoftCap();
                this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
            }
            return value;
        }
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(V value) {
        Preconditions.checkNotNull(value);
        int bucketedSize = getBucketedSizeForValue(value);
        int sizeInBytes = getSizeInBytes(bucketedSize);
        synchronized (this) {
            Bucket<V> bucket = getBucketIfPresent(bucketedSize);
            if (!this.mInUseValues.remove(value)) {
                FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                free(value);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucket == null || bucket.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(value)) {
                if (bucket != null) {
                    bucket.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
                free(value);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucket.release(value);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
            }
            logStats();
        }
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    protected void onParamsChanged() {
    }

    protected boolean isReusable(V value) {
        Preconditions.checkNotNull(value);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        Preconditions.checkState(!isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0);
    }

    private synchronized void legacyInitBuckets(SparseIntArray inUseCounts) {
        Preconditions.checkNotNull(inUseCounts);
        this.mBuckets.clear();
        SparseIntArray bucketSizes = this.mPoolParams.bucketSizes;
        if (bucketSizes != null) {
            for (int i = 0; i < bucketSizes.size(); i++) {
                int bucketSize = bucketSizes.keyAt(i);
                int maxLength = bucketSizes.valueAt(i);
                int bucketInUseCount = inUseCounts.get(bucketSize, 0);
                this.mBuckets.put(bucketSize, new Bucket<>(getSizeInBytes(bucketSize), maxLength, bucketInUseCount, this.mPoolParams.fixBucketsReinitialization));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private synchronized void initBuckets() {
        SparseIntArray bucketSizes = this.mPoolParams.bucketSizes;
        if (bucketSizes != null) {
            fillBuckets(bucketSizes);
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private void fillBuckets(SparseIntArray bucketSizes) {
        this.mBuckets.clear();
        for (int i = 0; i < bucketSizes.size(); i++) {
            int bucketSize = bucketSizes.keyAt(i);
            int maxLength = bucketSizes.valueAt(i);
            this.mBuckets.put(bucketSize, new Bucket<>(getSizeInBytes(bucketSize), maxLength, 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    private List<Bucket<V>> refillBuckets() {
        List<Bucket<V>> bucketsToTrim = new ArrayList<>(this.mBuckets.size());
        int len = this.mBuckets.size();
        for (int i = 0; i < len; i++) {
            Bucket<V> oldBucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
            int bucketSize = oldBucket.mItemSize;
            int maxLength = oldBucket.mMaxLength;
            int bucketInUseCount = oldBucket.getInUseCount();
            if (oldBucket.getFreeListSize() > 0) {
                bucketsToTrim.add(oldBucket);
            }
            this.mBuckets.setValueAt(i, new Bucket<>(getSizeInBytes(bucketSize), maxLength, bucketInUseCount, this.mPoolParams.fixBucketsReinitialization));
        }
        return bucketsToTrim;
    }

    void trimToNothing() {
        List<Bucket<V>> bucketsToTrim;
        synchronized (this) {
            if (this.mPoolParams.fixBucketsReinitialization) {
                bucketsToTrim = refillBuckets();
            } else {
                bucketsToTrim = new ArrayList<>(this.mBuckets.size());
                SparseIntArray inUseCounts = new SparseIntArray();
                for (int i = 0; i < this.mBuckets.size(); i++) {
                    Bucket<V> bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
                    if (bucket.getFreeListSize() > 0) {
                        bucketsToTrim.add(bucket);
                    }
                    inUseCounts.put(this.mBuckets.keyAt(i), bucket.getInUseCount());
                }
                legacyInitBuckets(inUseCounts);
            }
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (int i2 = 0; i2 < bucketsToTrim.size(); i2++) {
            Bucket<V> bucket2 = bucketsToTrim.get(i2);
            while (true) {
                V item = bucket2.pop();
                if (item == null) {
                    break;
                } else {
                    free(item);
                }
            }
        }
    }

    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    synchronized void trimToSize(int targetSize) {
        int bytesToFree = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - targetSize, this.mFree.mNumBytes);
        if (bytesToFree <= 0) {
            return;
        }
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(bytesToFree));
        }
        logStats();
        for (int i = 0; i < this.mBuckets.size() && bytesToFree > 0; i++) {
            Bucket<V> bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
            while (bytesToFree > 0) {
                V value = bucket.pop();
                if (value == null) {
                    break;
                }
                free(value);
                bytesToFree -= bucket.mItemSize;
                this.mFree.decrement(bucket.mItemSize);
            }
        }
        logStats();
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
        }
    }

    @Nullable
    private synchronized Bucket<V> getBucketIfPresent(int bucketedSize) {
        return this.mBuckets.get(bucketedSize);
    }

    @Nullable
    synchronized Bucket<V> getBucket(int bucketedSize) {
        Bucket<V> bucket = this.mBuckets.get(bucketedSize);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(bucketedSize));
            }
            Bucket<V> newBucket = newBucket(bucketedSize);
            this.mBuckets.put(bucketedSize, newBucket);
            return newBucket;
        }
        return bucket;
    }

    Bucket<V> newBucket(int bucketedSize) {
        return new Bucket<>(getSizeInBytes(bucketedSize), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean isMaxSizeSoftCapExceeded;
        isMaxSizeSoftCapExceeded = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (isMaxSizeSoftCapExceeded) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return isMaxSizeSoftCapExceeded;
    }

    synchronized boolean canAllocate(int sizeInBytes) {
        if (this.mIgnoreHardCap) {
            return true;
        }
        int hardCap = this.mPoolParams.maxSizeHardCap;
        if (sizeInBytes > hardCap - this.mUsed.mNumBytes) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int softCap = this.mPoolParams.maxSizeSoftCap;
        if (sizeInBytes > softCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            trimToSize(softCap - sizeInBytes);
        }
        if (sizeInBytes <= hardCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        Map<String, Integer> stats;
        stats = new HashMap<>();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            int bucketedSize = this.mBuckets.keyAt(i);
            Bucket<V> bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
            String BUCKET_USED_KEY = "buckets_used_" + getSizeInBytes(bucketedSize);
            stats.put(BUCKET_USED_KEY, Integer.valueOf(bucket.getInUseCount()));
        }
        stats.put("soft_cap", Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        stats.put("hard_cap", Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        stats.put("used_count", Integer.valueOf(this.mUsed.mCount));
        stats.put("used_bytes", Integer.valueOf(this.mUsed.mNumBytes));
        stats.put("free_count", Integer.valueOf(this.mFree.mCount));
        stats.put("free_bytes", Integer.valueOf(this.mFree.mNumBytes));
        return stats;
    }

    static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int numBytes) {
            this.mCount++;
            this.mNumBytes += numBytes;
        }

        public void decrement(int numBytes) {
            if (this.mNumBytes >= numBytes && this.mCount > 0) {
                this.mCount--;
                this.mNumBytes -= numBytes;
            } else {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
            }
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object value) {
            super("Invalid value: " + value.toString());
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object size) {
            super("Invalid size: " + size.toString());
        }
    }

    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object size) {
            super(size);
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int hardCap, int usedBytes, int freeBytes, int allocSize) {
            super("Pool hard cap violation? Hard cap = " + hardCap + " Used size = " + usedBytes + " Free size = " + freeBytes + " Request size = " + allocSize);
        }
    }
}
