package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public final class FileLocker implements Closeable {

    @Nullable
    private FileLock mLock;
    private FileOutputStream mLockFileOutputStream;

    public static FileLocker lock(File lockFile) throws IOException {
        return new FileLocker(lockFile, false);
    }

    @Nullable
    public static FileLocker tryLock(File lockFile) throws IOException {
        FileLocker fileLocker = new FileLocker(lockFile, true);
        if (fileLocker.mLock == null) {
            fileLocker.close();
            return null;
        }
        return fileLocker;
    }

    private void init(File lockFile, boolean tryLock) throws IOException {
        FileLock lock;
        this.mLockFileOutputStream = new FileOutputStream(lockFile);
        try {
            if (tryLock) {
                try {
                    lock = this.mLockFileOutputStream.getChannel().tryLock();
                } catch (IOException e) {
                    lock = null;
                }
            } else {
                lock = this.mLockFileOutputStream.getChannel().lock();
            }
            if (lock == null) {
                this.mLockFileOutputStream.close();
            }
            this.mLock = lock;
        } catch (Throwable th) {
            if (0 == 0) {
                this.mLockFileOutputStream.close();
            }
            throw th;
        }
    }

    private FileLocker(File lockFile, boolean tryLock) throws IOException {
        init(lockFile, tryLock);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.mLock != null) {
                this.mLock.release();
            }
        } finally {
            this.mLockFileOutputStream.close();
        }
    }
}
