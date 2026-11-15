package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryPooledByteBufferFactory.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferFactory;", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pool", "Lcom/facebook/imagepipeline/memory/MemoryChunkPool;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "(Lcom/facebook/imagepipeline/memory/MemoryChunkPool;Lcom/facebook/common/memory/PooledByteStreams;)V", "newByteBuf", "Lcom/facebook/imagepipeline/memory/MemoryPooledByteBuffer;", "inputStream", "Ljava/io/InputStream;", "outputStream", "Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferOutputStream;", "newByteBuffer", "initialCapacity", "", "bytes", "", "size", "newOutputStream", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MemoryPooledByteBufferFactory implements PooledByteBufferFactory {
    private final MemoryChunkPool pool;
    private final PooledByteStreams pooledByteStreams;

    public MemoryPooledByteBufferFactory(MemoryChunkPool pool, PooledByteStreams pooledByteStreams) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        Intrinsics.checkNotNullParameter(pooledByteStreams, "pooledByteStreams");
        this.pool = pool;
        this.pooledByteStreams = pooledByteStreams;
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(int size) {
        if (!(size > 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        CloseableReference chunkRef = CloseableReference.of(this.pool.get(size), this.pool);
        Intrinsics.checkNotNullExpressionValue(chunkRef, "of(pool[size], pool)");
        try {
            return new MemoryPooledByteBuffer(chunkRef, size);
        } finally {
            chunkRef.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream) throws Throwable {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream outputStream = new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, null);
        try {
            return newByteBuf(inputStream, outputStream);
        } finally {
            outputStream.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(byte[] bytes) throws Throwable {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        MemoryPooledByteBufferOutputStream outputStream = new MemoryPooledByteBufferOutputStream(this.pool, bytes.length);
        try {
            try {
                outputStream.write(bytes, 0, bytes.length);
                MemoryPooledByteBuffer byteBuffer = outputStream.toByteBuffer();
                outputStream.close();
                return byteBuffer;
            } catch (IOException ioe) {
                RuntimeException runtimeExceptionPropagate = Throwables.propagate(ioe);
                Intrinsics.checkNotNullExpressionValue(runtimeExceptionPropagate, "propagate(ioe)");
                throw runtimeExceptionPropagate;
            }
        } catch (Throwable ioe2) {
            outputStream.close();
            throw ioe2;
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream, int initialCapacity) throws Throwable {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream outputStream = new MemoryPooledByteBufferOutputStream(this.pool, initialCapacity);
        try {
            return newByteBuf(inputStream, outputStream);
        } finally {
            outputStream.close();
        }
    }

    public final MemoryPooledByteBuffer newByteBuf(InputStream inputStream, MemoryPooledByteBufferOutputStream outputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        this.pooledByteStreams.copy(inputStream, outputStream);
        return outputStream.toByteBuffer();
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBufferOutputStream newOutputStream() {
        return new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, null);
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBufferOutputStream newOutputStream(int initialCapacity) {
        return new MemoryPooledByteBufferOutputStream(this.pool, initialCapacity);
    }
}
