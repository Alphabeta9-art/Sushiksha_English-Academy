package com.facebook.soloader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ElfZipFileChannel implements ElfByteChannel {

    @Nullable
    private InputStream mIs;
    private final long mLength;
    private boolean mOpened = true;
    private long mPos = 0;
    private final ZipEntry mZipEntry;
    private final ZipFile mZipFile;

    public ElfZipFileChannel(ZipFile zipFile, ZipEntry zipEntry) throws IOException {
        this.mZipFile = zipFile;
        this.mZipEntry = zipEntry;
        this.mLength = this.mZipEntry.getSize();
        this.mIs = this.mZipFile.getInputStream(this.mZipEntry);
        if (this.mIs == null) {
            throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
        }
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long position() throws IOException {
        return this.mPos;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel position(long newPosition) throws IOException {
        if (this.mIs == null) {
            throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
        }
        if (newPosition == this.mPos) {
            return this;
        }
        if (newPosition > this.mLength) {
            newPosition = this.mLength;
        }
        if (newPosition >= this.mPos) {
            this.mIs.skip(newPosition - this.mPos);
        } else {
            this.mIs.close();
            this.mIs = this.mZipFile.getInputStream(this.mZipEntry);
            if (this.mIs == null) {
                throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
            }
            this.mIs.skip(newPosition);
        }
        this.mPos = newPosition;
        return this;
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer dst) throws IOException {
        return read(dst, this.mPos);
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public int read(ByteBuffer dst, long position) throws IOException {
        if (this.mIs == null) {
            throw new IOException("InputStream is null");
        }
        int wanted = dst.remaining();
        long possible = this.mLength - position;
        if (possible <= 0) {
            return -1;
        }
        if (wanted > ((int) possible)) {
            wanted = (int) possible;
        }
        position(position);
        if (dst.hasArray()) {
            this.mIs.read(dst.array(), 0, wanted);
            dst.position(dst.position() + wanted);
        } else {
            byte[] bytes = new byte[wanted];
            this.mIs.read(bytes, 0, wanted);
            dst.put(bytes, 0, wanted);
        }
        this.mPos += (long) wanted;
        return wanted;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long size() throws IOException {
        return this.mLength;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel truncate(long size) throws IOException {
        throw new UnsupportedOperationException("ElfZipFileChannel doesn't support truncate");
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer src) throws IOException {
        throw new UnsupportedOperationException("ElfZipFileChannel doesn't support write");
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mOpened;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.mIs != null) {
            this.mIs.close();
            this.mOpened = false;
        }
    }
}
