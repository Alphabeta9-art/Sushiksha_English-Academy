package com.facebook.common.statfs;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class StatFsHelper {
    public static final long DEFAULT_DISK_OLIVE_LEVEL_IN_BYTES = 1048576000;
    public static final long DEFAULT_DISK_RED_LEVEL_IN_BYTES = 104857600;
    public static final int DEFAULT_DISK_RED_LEVEL_IN_MB = 100;
    public static final long DEFAULT_DISK_YELLOW_LEVEL_IN_BYTES = 419430400;
    public static final int DEFAULT_DISK_YELLOW_LEVEL_IN_MB = 400;
    private static final long RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    private static StatFsHelper sStatsFsHelper;

    @Nullable
    private volatile File mExternalPath;

    @Nullable
    private volatile File mInternalPath;
    private long mLastRestatTime;

    @Nullable
    private volatile StatFs mInternalStatFs = null;

    @Nullable
    private volatile StatFs mExternalStatFs = null;
    private volatile boolean mInitialized = false;
    private final Lock lock = new ReentrantLock();

    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized StatFsHelper getInstance() {
        if (sStatsFsHelper == null) {
            sStatsFsHelper = new StatFsHelper();
        }
        return sStatsFsHelper;
    }

    protected StatFsHelper() {
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean testLowDiskSpace(StorageType storageType, long freeSpaceThreshold) {
        ensureInitialized();
        long availableStorageSpace = getAvailableStorageSpace(storageType);
        return availableStorageSpace <= 0 || availableStorageSpace < freeSpaceThreshold;
    }

    public long getFreeStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFS = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFS != null) {
            int i = Build.VERSION.SDK_INT;
            long blockSize = statFS.getBlockSizeLong();
            long availableBlocks = statFS.getFreeBlocksLong();
            return blockSize * availableBlocks;
        }
        return -1L;
    }

    public long getTotalStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFS = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFS != null) {
            int i = Build.VERSION.SDK_INT;
            long blockSize = statFS.getBlockSizeLong();
            long totalBlocks = statFS.getBlockCountLong();
            return blockSize * totalBlocks;
        }
        return -1L;
    }

    public long getAvailableStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFS = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFS != null) {
            int i = Build.VERSION.SDK_INT;
            long blockSize = statFS.getBlockSizeLong();
            long availableBlocks = statFS.getAvailableBlocksLong();
            return blockSize * availableBlocks;
        }
        return 0L;
    }

    public boolean isLowSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) < DEFAULT_DISK_YELLOW_LEVEL_IN_BYTES;
    }

    public boolean isVeryLowSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) < DEFAULT_DISK_RED_LEVEL_IN_BYTES;
    }

    public boolean isHighSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) > DEFAULT_DISK_OLIVE_LEVEL_IN_BYTES;
    }

    private void maybeUpdateStats() {
        if (this.lock.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.mLastRestatTime > RESTAT_INTERVAL_MS) {
                    updateStats();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public void resetStats() {
        if (this.lock.tryLock()) {
            try {
                ensureInitialized();
                updateStats();
            } finally {
                this.lock.unlock();
            }
        }
    }

    private void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }

    @Nullable
    private StatFs updateStatsHelper(@Nullable StatFs statfs, @Nullable File dir) {
        if (dir == null || !dir.exists()) {
            return null;
        }
        try {
            if (statfs == null) {
                statfs = createStatFs(dir.getAbsolutePath());
            } else {
                statfs.restat(dir.getAbsolutePath());
            }
            return statfs;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (Throwable ex) {
            throw Throwables.propagate(ex);
        }
    }

    protected static StatFs createStatFs(String path) {
        return new StatFs(path);
    }
}
