package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes11.dex */
public class PooledByteStreams {
    private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 16384);
    }

    public PooledByteStreams(ByteArrayPool byteArrayPool, int tempBufSize) {
        Preconditions.checkArgument(Boolean.valueOf(tempBufSize > 0));
        this.mTempBufSize = tempBufSize;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream from, OutputStream to) throws IOException {
        long count = 0;
        byte[] tmp = this.mByteArrayPool.get(this.mTempBufSize);
        while (true) {
            try {
                int read = from.read(tmp, 0, this.mTempBufSize);
                if (read != -1) {
                    to.write(tmp, 0, read);
                    count += (long) read;
                } else {
                    this.mByteArrayPool.release(tmp);
                    return count;
                }
            } catch (Throwable th) {
                this.mByteArrayPool.release(tmp);
                throw th;
            }
        }
    }

    public long copy(InputStream from, OutputStream to, long bytesToCopy) throws IOException {
        Preconditions.checkState(bytesToCopy > 0);
        long copied = 0;
        byte[] tmp = this.mByteArrayPool.get(this.mTempBufSize);
        while (copied < bytesToCopy) {
            try {
                int read = from.read(tmp, 0, (int) Math.min(this.mTempBufSize, bytesToCopy - copied));
                if (read != -1) {
                    to.write(tmp, 0, read);
                    copied += (long) read;
                } else {
                    this.mByteArrayPool.release(tmp);
                    return copied;
                }
            } catch (Throwable th) {
                this.mByteArrayPool.release(tmp);
                throw th;
            }
        }
        this.mByteArrayPool.release(tmp);
        return copied;
    }
}
