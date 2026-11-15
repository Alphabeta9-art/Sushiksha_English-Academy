package androidx.room.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes11.dex */
public class CopyLock {
    private static final Map<String, Lock> sThreadLocks = new HashMap();
    private final File mCopyLockFile;
    private final boolean mFileLevelLock;
    private FileChannel mLockChannel;
    private final Lock mThreadLock;

    public CopyLock(String name, File lockDir, boolean processLock) {
        this.mCopyLockFile = new File(lockDir, name + ".lck");
        this.mThreadLock = getThreadLock(this.mCopyLockFile.getAbsolutePath());
        this.mFileLevelLock = processLock;
    }

    public void lock() {
        this.mThreadLock.lock();
        if (this.mFileLevelLock) {
            try {
                this.mLockChannel = new FileOutputStream(this.mCopyLockFile).getChannel();
                this.mLockChannel.lock();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to grab copy lock.", e);
            }
        }
    }

    public void unlock() {
        if (this.mLockChannel != null) {
            try {
                this.mLockChannel.close();
            } catch (IOException e) {
            }
        }
        this.mThreadLock.unlock();
    }

    private static Lock getThreadLock(String key) {
        Lock threadLock;
        synchronized (sThreadLocks) {
            threadLock = sThreadLocks.get(key);
            if (threadLock == null) {
                threadLock = new ReentrantLock();
                sThreadLocks.put(key, threadLock);
            }
        }
        return threadLock;
    }
}
