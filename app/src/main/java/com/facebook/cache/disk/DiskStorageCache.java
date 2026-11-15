package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.cache.common.WriterCallback;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DiskStorageCache implements FileCache, DiskTrimmable {
    public static final int START_OF_VERSIONING = 1;
    private static final double TRIMMING_LOWER_BOUND = 0.02d;
    private static final long UNINITIALIZED = -1;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CountDownLatch mCountDownLatch;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private boolean mIndexReady;
    private final long mLowDiskSpaceCacheSizeLimit;
    private final DiskStorage mStorage;
    private static final Class<?> TAG = DiskStorageCache.class;
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);
    private final Object mLock = new Object();
    private final StatFsHelper mStatFsHelper = StatFsHelper.getInstance();
    private long mCacheSizeLastUpdateTime = -1;
    private final CacheStats mCacheStats = new CacheStats();
    private final Clock mClock = SystemClock.get();
    final Set<String> mResourceIndex = new HashSet();

    static class CacheStats {
        private boolean mInitialized = false;
        private long mSize = -1;
        private long mCount = -1;

        CacheStats() {
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1L;
            this.mSize = -1L;
        }

        public synchronized void set(long size, long count) {
            this.mCount = count;
            this.mSize = size;
            this.mInitialized = true;
        }

        public synchronized void increment(long sizeIncrement, long countIncrement) {
            if (this.mInitialized) {
                this.mSize += sizeIncrement;
                this.mCount += countIncrement;
            }
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized long getCount() {
            return this.mCount;
        }
    }

    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long cacheSizeLimitMinimum, long lowDiskSpaceCacheSizeLimit, long defaultCacheSizeLimit) {
            this.mCacheSizeLimitMinimum = cacheSizeLimitMinimum;
            this.mLowDiskSpaceCacheSizeLimit = lowDiskSpaceCacheSizeLimit;
            this.mDefaultCacheSizeLimit = defaultCacheSizeLimit;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable DiskTrimmableRegistry diskTrimmableRegistry, Executor executorForBackgrountInit, boolean indexPopulateAtStartupEnabled) {
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        this.mDefaultCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mIndexPopulateAtStartupEnabled = indexPopulateAtStartupEnabled;
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        if (this.mIndexPopulateAtStartupEnabled) {
            this.mCountDownLatch = new CountDownLatch(1);
            executorForBackgrountInit.execute(new Runnable() { // from class: com.facebook.cache.disk.DiskStorageCache.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (DiskStorageCache.this.mLock) {
                        DiskStorageCache.this.maybeUpdateFileCacheSize();
                    }
                    DiskStorageCache.this.mIndexReady = true;
                    DiskStorageCache.this.mCountDownLatch.countDown();
                }
            });
        } else {
            this.mCountDownLatch = new CountDownLatch(0);
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public DiskStorage.DiskDumpInfo getDumpInfo() throws IOException {
        return this.mStorage.getDumpInfo();
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean isEnabled() {
        return this.mStorage.isEnabled();
    }

    protected void awaitIndex() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException e) {
            FLog.e(TAG, "Memory Index is not ready yet. ");
        }
    }

    public boolean isIndexReady() {
        return this.mIndexReady || !this.mIndexPopulateAtStartupEnabled;
    }

    @Override // com.facebook.cache.disk.FileCache
    @Nullable
    public BinaryResource getResource(CacheKey key) throws IOException {
        BinaryResource resource;
        String resourceId = null;
        SettableCacheEvent cacheEvent = SettableCacheEvent.obtain().setCacheKey(key);
        try {
            try {
                synchronized (this.mLock) {
                    resource = null;
                    List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                    for (int i = 0; i < resourceIds.size(); i++) {
                        resourceId = resourceIds.get(i);
                        cacheEvent.setResourceId(resourceId);
                        resource = this.mStorage.getResource(resourceId, key);
                        if (resource != null) {
                            break;
                        }
                    }
                    if (resource == null) {
                        this.mCacheEventListener.onMiss(cacheEvent);
                        this.mResourceIndex.remove(resourceId);
                    } else {
                        Preconditions.checkNotNull(resourceId);
                        this.mCacheEventListener.onHit(cacheEvent);
                        this.mResourceIndex.add(resourceId);
                    }
                }
                cacheEvent.recycle();
                return resource;
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "getResource", ioe);
                cacheEvent.setException(ioe);
                this.mCacheEventListener.onReadException(cacheEvent);
                cacheEvent.recycle();
                return null;
            }
        } catch (Throwable ioe2) {
            cacheEvent.recycle();
            throw ioe2;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean probe(CacheKey key) {
        try {
            synchronized (this.mLock) {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String resourceId = resourceIds.get(i);
                    if (this.mStorage.touch(resourceId, key)) {
                        this.mResourceIndex.add(resourceId);
                        return true;
                    }
                }
                return false;
            }
        } catch (IOException e) {
            SettableCacheEvent cacheEvent = SettableCacheEvent.obtain().setCacheKey(key).setResourceId(null).setException(e);
            this.mCacheEventListener.onReadException(cacheEvent);
            cacheEvent.recycle();
            return false;
        }
    }

    private DiskStorage.Inserter startInsert(String resourceId, CacheKey key) throws IOException {
        maybeEvictFilesInCacheDir();
        return this.mStorage.insert(resourceId, key);
    }

    private BinaryResource endInsert(DiskStorage.Inserter inserter, CacheKey key, String resourceId) throws IOException {
        BinaryResource resource;
        synchronized (this.mLock) {
            resource = inserter.commit(key);
            this.mResourceIndex.add(resourceId);
            this.mCacheStats.increment(resource.size(), 1L);
        }
        return resource;
    }

    @Override // com.facebook.cache.disk.FileCache
    public BinaryResource insert(CacheKey key, WriterCallback callback) throws IOException {
        String resourceId;
        SettableCacheEvent cacheEvent = SettableCacheEvent.obtain().setCacheKey(key);
        this.mCacheEventListener.onWriteAttempt(cacheEvent);
        synchronized (this.mLock) {
            resourceId = CacheKeyUtil.getFirstResourceId(key);
        }
        cacheEvent.setResourceId(resourceId);
        try {
            try {
                DiskStorage.Inserter inserter = startInsert(resourceId, key);
                try {
                    inserter.writeData(callback, key);
                    BinaryResource resource = endInsert(inserter, key, resourceId);
                    cacheEvent.setItemSize(resource.size()).setCacheSize(this.mCacheStats.getSize());
                    this.mCacheEventListener.onWriteSuccess(cacheEvent);
                    if (!inserter.cleanUp()) {
                        FLog.e(TAG, "Failed to delete temp file");
                    }
                    cacheEvent.recycle();
                    return resource;
                } catch (Throwable th) {
                    if (!inserter.cleanUp()) {
                        FLog.e(TAG, "Failed to delete temp file");
                    }
                    throw th;
                }
            } catch (IOException ioe) {
                cacheEvent.setException(ioe);
                this.mCacheEventListener.onWriteException(cacheEvent);
                FLog.e(TAG, "Failed inserting a file into the cache", ioe);
                throw ioe;
            }
        } catch (Throwable ioe2) {
            cacheEvent.recycle();
            throw ioe2;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public void remove(CacheKey key) {
        synchronized (this.mLock) {
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String resourceId = resourceIds.get(i);
                    this.mStorage.remove(resourceId);
                    this.mResourceIndex.remove(resourceId);
                }
            } catch (IOException e) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.DELETE_FILE, TAG, "delete: " + e.getMessage(), e);
            }
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long clearOldEntries(long cacheExpirationMs) {
        long cacheSizeBeforeClearance;
        long oldestRemainingEntryAgeMs = 0;
        synchronized (this.mLock) {
            try {
                long entryAgeMs = this.mClock.now();
                Collection<DiskStorage.Entry> allEntries = this.mStorage.getEntries();
                long cacheSizeBeforeClearance2 = this.mCacheStats.getSize();
                int itemsRemovedCount = 0;
                long itemsRemovedSize = 0;
                for (DiskStorage.Entry entry : allEntries) {
                    long now = entryAgeMs;
                    long entryAgeMs2 = Math.max(1L, Math.abs(entryAgeMs - entry.getTimestamp()));
                    if (entryAgeMs2 >= cacheExpirationMs) {
                        long entryRemovedSize = this.mStorage.remove(entry);
                        cacheSizeBeforeClearance = cacheSizeBeforeClearance2;
                        this.mResourceIndex.remove(entry.getId());
                        if (entryRemovedSize > 0) {
                            itemsRemovedCount++;
                            itemsRemovedSize += entryRemovedSize;
                            SettableCacheEvent cacheEvent = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(CacheEventListener.EvictionReason.CONTENT_STALE).setItemSize(entryRemovedSize).setCacheSize(cacheSizeBeforeClearance - itemsRemovedSize);
                            this.mCacheEventListener.onEviction(cacheEvent);
                            cacheEvent.recycle();
                        }
                    } else {
                        cacheSizeBeforeClearance = cacheSizeBeforeClearance2;
                        long cacheSizeBeforeClearance3 = Math.max(oldestRemainingEntryAgeMs, entryAgeMs2);
                        oldestRemainingEntryAgeMs = cacheSizeBeforeClearance3;
                    }
                    entryAgeMs = now;
                    allEntries = allEntries;
                    cacheSizeBeforeClearance2 = cacheSizeBeforeClearance;
                }
                this.mStorage.purgeUnexpectedResources();
                if (itemsRemovedCount > 0) {
                    maybeUpdateFileCacheSize();
                    this.mCacheStats.increment(-itemsRemovedSize, -itemsRemovedCount);
                }
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "clearOldEntries: " + ioe.getMessage(), ioe);
            }
        }
        return oldestRemainingEntryAgeMs;
    }

    private void maybeEvictFilesInCacheDir() throws IOException {
        synchronized (this.mLock) {
            boolean calculatedRightNow = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long cacheSize = this.mCacheStats.getSize();
            if (cacheSize > this.mCacheSizeLimit && !calculatedRightNow) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            if (cacheSize > this.mCacheSizeLimit) {
                evictAboveSize((this.mCacheSizeLimit * 9) / 10, CacheEventListener.EvictionReason.CACHE_FULL);
            }
        }
    }

    private void evictAboveSize(long desiredSize, CacheEventListener.EvictionReason reason) throws IOException {
        long cacheSizeBeforeClearance;
        try {
            Collection<DiskStorage.Entry> entries = getSortedEntries(this.mStorage.getEntries());
            long cacheSizeBeforeClearance2 = this.mCacheStats.getSize();
            long deleteSize = cacheSizeBeforeClearance2 - desiredSize;
            int itemCount = 0;
            long sumItemSizes = 0;
            for (DiskStorage.Entry entry : entries) {
                if (sumItemSizes > deleteSize) {
                    break;
                }
                long deletedSize = this.mStorage.remove(entry);
                Collection<DiskStorage.Entry> entries2 = entries;
                this.mResourceIndex.remove(entry.getId());
                if (deletedSize > 0) {
                    itemCount++;
                    sumItemSizes += deletedSize;
                    cacheSizeBeforeClearance = cacheSizeBeforeClearance2;
                    SettableCacheEvent cacheEvent = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(reason).setItemSize(deletedSize).setCacheSize(cacheSizeBeforeClearance - sumItemSizes).setCacheLimit(desiredSize);
                    this.mCacheEventListener.onEviction(cacheEvent);
                    cacheEvent.recycle();
                } else {
                    cacheSizeBeforeClearance = cacheSizeBeforeClearance2;
                }
                entries = entries2;
                cacheSizeBeforeClearance2 = cacheSizeBeforeClearance;
            }
            this.mCacheStats.increment(-sumItemSizes, -itemCount);
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException ioe) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "evictAboveSize: " + ioe.getMessage(), ioe);
            throw ioe;
        }
    }

    private Collection<DiskStorage.Entry> getSortedEntries(Collection<DiskStorage.Entry> allEntries) {
        long threshold = this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList<DiskStorage.Entry> sortedList = new ArrayList<>(allEntries.size());
        ArrayList<DiskStorage.Entry> listToSort = new ArrayList<>(allEntries.size());
        for (DiskStorage.Entry entry : allEntries) {
            if (entry.getTimestamp() > threshold) {
                sortedList.add(entry);
            } else {
                listToSort.add(entry);
            }
        }
        Collections.sort(listToSort, this.mEntryEvictionComparatorSupplier.get());
        sortedList.addAll(listToSort);
        return sortedList;
    }

    private void updateFileCacheSizeLimit() {
        StatFsHelper.StorageType storageType;
        if (this.mStorage.isExternal()) {
            storageType = StatFsHelper.StorageType.EXTERNAL;
        } else {
            storageType = StatFsHelper.StorageType.INTERNAL;
        }
        boolean isAvailableSpaceLowerThanHighLimit = this.mStatFsHelper.testLowDiskSpace(storageType, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize());
        if (isAvailableSpaceLowerThanHighLimit) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getSize() {
        return this.mCacheStats.getSize();
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getCount() {
        return this.mCacheStats.getCount();
    }

    @Override // com.facebook.cache.disk.FileCache
    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorage.clearAll();
                this.mResourceIndex.clear();
                this.mCacheEventListener.onCleared();
            } catch (IOException | NullPointerException e) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "clearAll: " + e.getMessage(), e);
            }
            this.mCacheStats.reset();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKeySync(CacheKey key) {
        synchronized (this.mLock) {
            List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
            for (int i = 0; i < resourceIds.size(); i++) {
                String resourceId = resourceIds.get(i);
                if (this.mResourceIndex.contains(resourceId)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKey(CacheKey key) {
        synchronized (this.mLock) {
            if (hasKeySync(key)) {
                return true;
            }
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String resourceId = resourceIds.get(i);
                    if (this.mStorage.contains(resourceId, key)) {
                        this.mResourceIndex.add(resourceId);
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToMinimum() {
        synchronized (this.mLock) {
            maybeUpdateFileCacheSize();
            long cacheSize = this.mCacheStats.getSize();
            if (this.mCacheSizeLimitMinimum <= 0 || cacheSize <= 0 || cacheSize < this.mCacheSizeLimitMinimum) {
                return;
            }
            double trimRatio = 1.0d - (this.mCacheSizeLimitMinimum / cacheSize);
            if (trimRatio > TRIMMING_LOWER_BOUND) {
                trimBy(trimRatio);
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToNothing() {
        clearAll();
    }

    private void trimBy(double trimRatio) {
        synchronized (this.mLock) {
            try {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
                long cacheSize = this.mCacheStats.getSize();
                long newMaxBytesInFiles = cacheSize - ((long) (cacheSize * trimRatio));
                evictAboveSize(newMaxBytesInFiles, CacheEventListener.EvictionReason.CACHE_MANAGER_TRIMMED);
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "trimBy: " + ioe.getMessage(), ioe);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean maybeUpdateFileCacheSize() {
        long now = this.mClock.now();
        if (!this.mCacheStats.isInitialized() || this.mCacheSizeLastUpdateTime == -1 || now - this.mCacheSizeLastUpdateTime > FILECACHE_SIZE_UPDATE_PERIOD_MS) {
            return maybeUpdateFileCacheSizeAndIndex();
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0102 A[Catch: IOException -> 0x0122, TryCatch #6 {IOException -> 0x0122, blocks: (B:48:0x00f7, B:51:0x0102, B:53:0x0106, B:55:0x010a, B:56:0x0117), top: B:81:0x00f7 }] */
    private boolean maybeUpdateFileCacheSizeAndIndex() {
        Set<String> tempResourceIndex;
        boolean foundFutureTimestamp;
        long size;
        long size2 = 0;
        int count = 0;
        boolean foundFutureTimestamp2 = false;
        int numFutureFiles = 0;
        int sizeFutureFiles = 0;
        long maxTimeDelta = -1;
        long now = this.mClock.now();
        long timeThreshold = FUTURE_TIMESTAMP_THRESHOLD_MS + now;
        if (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex.isEmpty()) {
            tempResourceIndex = this.mResourceIndex;
        } else if (this.mIndexPopulateAtStartupEnabled) {
            tempResourceIndex = new HashSet();
        } else {
            tempResourceIndex = null;
        }
        try {
            Collection<DiskStorage.Entry> entries = this.mStorage.getEntries();
            for (DiskStorage.Entry entry : entries) {
                try {
                    try {
                        count++;
                        size2 += entry.getSize();
                        try {
                            if (entry.getTimestamp() > timeThreshold) {
                                foundFutureTimestamp2 = true;
                                numFutureFiles++;
                                size = size2;
                                try {
                                    sizeFutureFiles = (int) (((long) sizeFutureFiles) + entry.getSize());
                                    maxTimeDelta = Math.max(entry.getTimestamp() - now, maxTimeDelta);
                                } catch (IOException e) {
                                    ioe = e;
                                    size2 = size;
                                    this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + ioe.getMessage(), ioe);
                                    return false;
                                }
                            } else {
                                size = size2;
                                if (this.mIndexPopulateAtStartupEnabled) {
                                    Preconditions.checkNotNull(tempResourceIndex);
                                    tempResourceIndex.add(entry.getId());
                                }
                            }
                            size2 = size;
                        } catch (IOException e2) {
                            ioe = e2;
                        }
                    } catch (IOException e3) {
                        ioe = e3;
                    }
                } catch (IOException e4) {
                    ioe = e4;
                }
            }
            if (foundFutureTimestamp2) {
                try {
                    foundFutureTimestamp = foundFutureTimestamp2;
                    try {
                        try {
                            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + numFutureFiles + " files , with a total size of " + sizeFutureFiles + " bytes, and a maximum time delta of " + maxTimeDelta + "ms", null);
                        } catch (IOException e5) {
                            ioe = e5;
                            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + ioe.getMessage(), ioe);
                            return false;
                        }
                    } catch (IOException e6) {
                        ioe = e6;
                    }
                } catch (IOException e7) {
                    ioe = e7;
                }
            } else {
                foundFutureTimestamp = foundFutureTimestamp2;
            }
            try {
                if (this.mCacheStats.getCount() != count) {
                    if (this.mIndexPopulateAtStartupEnabled) {
                        Preconditions.checkNotNull(tempResourceIndex);
                        this.mResourceIndex.clear();
                        this.mResourceIndex.addAll(tempResourceIndex);
                    }
                    this.mCacheStats.set(size2, count);
                } else {
                    try {
                        if (this.mCacheStats.getSize() != size2) {
                            if (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex != tempResourceIndex) {
                                Preconditions.checkNotNull(tempResourceIndex);
                                this.mResourceIndex.clear();
                                this.mResourceIndex.addAll(tempResourceIndex);
                            }
                            this.mCacheStats.set(size2, count);
                        }
                    } catch (IOException e8) {
                        ioe = e8;
                        this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + ioe.getMessage(), ioe);
                        return false;
                    }
                }
                this.mCacheSizeLastUpdateTime = now;
                return true;
            } catch (IOException e9) {
                ioe = e9;
            }
        } catch (IOException e10) {
            ioe = e10;
        }
    }
}
