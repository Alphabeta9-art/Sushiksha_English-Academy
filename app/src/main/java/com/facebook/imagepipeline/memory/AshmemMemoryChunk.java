package com.facebook.imagepipeline.memory;

import android.os.SharedMemory;
import android.system.ErrnoException;
import android.util.Log;
import com.facebook.common.internal.Preconditions;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class AshmemMemoryChunk implements MemoryChunk, Closeable {
    private static final String TAG = "AshmemMemoryChunk";

    @Nullable
    private ByteBuffer mByteBuffer;
    private final long mId;

    @Nullable
    private SharedMemory mSharedMemory;

    public AshmemMemoryChunk(int size) {
        Preconditions.checkArgument(Boolean.valueOf(size > 0));
        try {
            this.mSharedMemory = SharedMemory.create(TAG, size);
            this.mByteBuffer = this.mSharedMemory.mapReadWrite();
            this.mId = System.identityHashCode(this);
        } catch (ErrnoException e) {
            throw new RuntimeException("Fail to create AshmemMemory", e);
        }
    }

    public AshmemMemoryChunk() {
        this.mSharedMemory = null;
        this.mByteBuffer = null;
        this.mId = System.identityHashCode(this);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!isClosed()) {
            if (this.mSharedMemory != null) {
                this.mSharedMemory.close();
            }
            if (this.mByteBuffer != null) {
                SharedMemory.unmap(this.mByteBuffer);
            }
            this.mByteBuffer = null;
            this.mSharedMemory = null;
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized boolean isClosed() {
        return this.mByteBuffer == null || this.mSharedMemory == null;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public int getSize() {
        Preconditions.checkNotNull(this.mSharedMemory);
        return this.mSharedMemory.getSize();
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int write(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(this.mByteBuffer);
        actualCount = MemoryChunkUtil.adjustByteCount(memoryOffset, count, getSize());
        MemoryChunkUtil.checkBounds(memoryOffset, byteArray.length, byteArrayOffset, actualCount, getSize());
        this.mByteBuffer.position(memoryOffset);
        this.mByteBuffer.put(byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int read(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(this.mByteBuffer);
        actualCount = MemoryChunkUtil.adjustByteCount(memoryOffset, count, getSize());
        MemoryChunkUtil.checkBounds(memoryOffset, byteArray.length, byteArrayOffset, actualCount, getSize());
        this.mByteBuffer.position(memoryOffset);
        this.mByteBuffer.get(byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized byte read(int offset) {
        Preconditions.checkState(!isClosed());
        Preconditions.checkArgument(Boolean.valueOf(offset >= 0));
        Preconditions.checkArgument(Boolean.valueOf(offset < getSize()));
        Preconditions.checkNotNull(this.mByteBuffer);
        return this.mByteBuffer.get(offset);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of an  AshmemMemoryChunk");
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.mByteBuffer;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getUniqueId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public void copy(int offset, MemoryChunk other, int otherOffset, int count) {
        Preconditions.checkNotNull(other);
        if (other.getUniqueId() == getUniqueId()) {
            Log.w(TAG, "Copying from AshmemMemoryChunk " + Long.toHexString(getUniqueId()) + " to AshmemMemoryChunk " + Long.toHexString(other.getUniqueId()) + " which are the same ");
            Preconditions.checkArgument(false);
        }
        if (other.getUniqueId() < getUniqueId()) {
            synchronized (other) {
                synchronized (this) {
                    try {
                        doCopy(offset, other, otherOffset, count);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (other) {
                try {
                    doCopy(offset, other, otherOffset, count);
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    private void doCopy(int offset, MemoryChunk other, int otherOffset, int count) {
        if (!(other instanceof AshmemMemoryChunk)) {
            throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
        }
        Preconditions.checkState(!isClosed());
        Preconditions.checkState(!other.isClosed());
        Preconditions.checkNotNull(this.mByteBuffer);
        Preconditions.checkNotNull(other.getByteBuffer());
        MemoryChunkUtil.checkBounds(offset, other.getSize(), otherOffset, count, getSize());
        this.mByteBuffer.position(offset);
        other.getByteBuffer().position(otherOffset);
        byte[] b = new byte[count];
        this.mByteBuffer.get(b, 0, count);
        other.getByteBuffer().put(b, 0, count);
    }
}
