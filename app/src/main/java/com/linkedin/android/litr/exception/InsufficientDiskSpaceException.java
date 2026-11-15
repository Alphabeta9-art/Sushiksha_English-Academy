package com.linkedin.android.litr.exception;

import java.util.Locale;

/* JADX INFO: loaded from: classes13.dex */
public class InsufficientDiskSpaceException extends MediaTransformationException {
    private final long availableDiskSpaceInBytes;
    private final long estimatedTargetFileSizeInBytes;

    public InsufficientDiskSpaceException(long estimatedTargetFileSizeInBytes, long availableDiskSpaceInBytes) {
        this(estimatedTargetFileSizeInBytes, availableDiskSpaceInBytes, new Throwable());
    }

    public InsufficientDiskSpaceException(long estimatedTargetFileSizeInBytes, long availableDiskSpaceInBytes, Throwable cause) {
        super(cause);
        this.estimatedTargetFileSizeInBytes = estimatedTargetFileSizeInBytes;
        this.availableDiskSpaceInBytes = availableDiskSpaceInBytes;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format(Locale.ENGLISH, "Insufficient disk space, estimated file size in bytes %d, available disk space in bytes %d", Long.valueOf(this.estimatedTargetFileSizeInBytes), Long.valueOf(this.availableDiskSpaceInBytes));
    }
}
