package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;

    @Nullable
    private final WebpBitmapFactory mWebpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, bytesRef.get().size(), null, options);
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, BitmapFactory.Options options) {
        byte[] suffix = endsWithEOI(bytesRef, length) ? null : EOI;
        return decodeFileDescriptorAsPurgeable(bytesRef, length, suffix, options);
    }

    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix) throws IOException {
        int outputLength = (suffix == null ? 0 : suffix.length) + inputLength;
        MemoryFile memoryFile = new MemoryFile(null, outputLength);
        memoryFile.allowPurging(false);
        PooledByteBufferInputStream pbbIs = null;
        LimitedInputStream is = null;
        OutputStream os = null;
        try {
            pbbIs = new PooledByteBufferInputStream(bytesRef.get());
            is = new LimitedInputStream(pbbIs, inputLength);
            os = memoryFile.getOutputStream();
            ByteStreams.copy(is, os);
            if (suffix != null) {
                memoryFile.writeBytes(suffix, 0, inputLength, suffix.length);
            }
            return memoryFile;
        } finally {
            CloseableReference.closeSafely(bytesRef);
            Closeables.closeQuietly(pbbIs);
            Closeables.closeQuietly(is);
            Closeables.close(os, true);
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            Object rawFD = Preconditions.checkNotNull(getFileDescriptorMethod().invoke(memoryFile, new Object[0]));
            return (FileDescriptor) rawFD;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix, BitmapFactory.Options options) throws IOException {
        MemoryFile memoryFile = null;
        try {
            try {
                MemoryFile memoryFile2 = copyToMemoryFile(bytesRef, inputLength, suffix);
                FileDescriptor fd = getMemoryFileDescriptor(memoryFile2);
                if (this.mWebpBitmapFactory != null) {
                    Bitmap bitmap = this.mWebpBitmapFactory.decodeFileDescriptor(fd, null, options);
                    Bitmap bitmap2 = (Bitmap) Preconditions.checkNotNull(bitmap, "BitmapFactory returned null");
                    if (memoryFile2 != null) {
                        memoryFile2.close();
                    }
                    return bitmap2;
                }
                throw new IllegalStateException("WebpBitmapFactory is null");
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        } catch (Throwable e2) {
            if (0 != 0) {
                memoryFile.close();
            }
            throw e2;
        }
    }
}
