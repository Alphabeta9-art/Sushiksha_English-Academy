package com.linkedin.android.litr.io;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.linkedin.android.litr.exception.MediaTargetException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes13.dex */
public class MediaMuxerMediaTarget implements MediaTarget {
    private static final String TAG = MediaMuxerMediaTarget.class.getSimpleName();
    boolean isStarted;
    private MediaFormat[] mediaFormatsToAdd;
    MediaMuxer mediaMuxer;
    private int numberOfTracksToAdd;
    private String outputFilePath;
    private ParcelFileDescriptor parcelFileDescriptor;
    LinkedList<MediaSample> queue;
    private int trackCount;

    public MediaMuxerMediaTarget(Context context, Uri outputFileUri, int trackCount, int orientationHint, int outputFormat) throws MediaTargetException {
        MediaMuxer mediaMuxer;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.parcelFileDescriptor = context.getContentResolver().openFileDescriptor(outputFileUri, "rwt");
                if (this.parcelFileDescriptor != null) {
                    mediaMuxer = new MediaMuxer(this.parcelFileDescriptor.getFileDescriptor(), outputFormat);
                } else {
                    throw new IOException("Inaccessible URI " + outputFileUri);
                }
            } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(outputFileUri.getScheme()) && outputFileUri.getPath() != null) {
                mediaMuxer = new MediaMuxer(outputFileUri.getPath(), outputFormat);
            } else {
                throw new MediaTargetException(MediaTargetException.Error.UNSUPPORTED_URI_TYPE, outputFileUri, outputFormat, new Throwable());
            }
            init(mediaMuxer, trackCount, orientationHint);
        } catch (IOException ioException) {
            releaseFileDescriptor();
            throw new MediaTargetException(MediaTargetException.Error.IO_FAILUE, outputFileUri, outputFormat, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new MediaTargetException(MediaTargetException.Error.INVALID_PARAMS, outputFileUri, outputFormat, illegalArgumentException);
        }
    }

    public MediaMuxerMediaTarget(String outputFilePath, int trackCount, int orientationHint, int outputFormat) throws MediaTargetException {
        this.outputFilePath = outputFilePath;
        try {
            MediaMuxer mediaMuxer = new MediaMuxer(outputFilePath, outputFormat);
            init(mediaMuxer, trackCount, orientationHint);
        } catch (IOException ioException) {
            throw new MediaTargetException(MediaTargetException.Error.IO_FAILUE, outputFilePath, outputFormat, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new MediaTargetException(MediaTargetException.Error.INVALID_PARAMS, outputFilePath, outputFormat, illegalArgumentException);
        }
    }

    private void init(MediaMuxer mediaMuxer, int trackCount, int orientationHint) throws IllegalArgumentException {
        this.trackCount = trackCount;
        this.mediaMuxer = mediaMuxer;
        this.mediaMuxer.setOrientationHint(orientationHint);
        this.numberOfTracksToAdd = 0;
        this.isStarted = false;
        this.queue = new LinkedList<>();
        this.mediaFormatsToAdd = new MediaFormat[trackCount];
    }

    @Override // com.linkedin.android.litr.io.MediaTarget
    public int addTrack(MediaFormat mediaFormat, int targetTrack) {
        this.mediaFormatsToAdd[targetTrack] = mediaFormat;
        this.numberOfTracksToAdd++;
        if (this.numberOfTracksToAdd == this.trackCount) {
            Log.d(TAG, "All tracks added, starting MediaMuxer, writing out " + this.queue.size() + " queued samples");
            for (MediaFormat trackMediaFormat : this.mediaFormatsToAdd) {
                this.mediaMuxer.addTrack(trackMediaFormat);
            }
            this.mediaMuxer.start();
            this.isStarted = true;
            while (!this.queue.isEmpty()) {
                MediaSample mediaSample = this.queue.removeFirst();
                this.mediaMuxer.writeSampleData(mediaSample.targetTrack, mediaSample.buffer, mediaSample.info);
            }
        }
        return targetTrack;
    }

    @Override // com.linkedin.android.litr.io.MediaTarget
    public void writeSampleData(int targetTrack, ByteBuffer buffer, MediaCodec.BufferInfo info) {
        if (this.isStarted) {
            if (buffer == null) {
                Log.e(TAG, "Trying to write a null buffer, skipping");
                return;
            } else {
                this.mediaMuxer.writeSampleData(targetTrack, buffer, info);
                return;
            }
        }
        MediaSample mediaSample = new MediaSample(targetTrack, buffer, info);
        this.queue.addLast(mediaSample);
    }

    @Override // com.linkedin.android.litr.io.MediaTarget
    public void release() {
        this.mediaMuxer.release();
        releaseFileDescriptor();
    }

    @Override // com.linkedin.android.litr.io.MediaTarget
    public String getOutputFilePath() {
        return this.outputFilePath != null ? this.outputFilePath : "";
    }

    private void releaseFileDescriptor() {
        try {
            if (this.parcelFileDescriptor != null) {
                this.parcelFileDescriptor.close();
                this.parcelFileDescriptor = null;
            }
        } catch (IOException e) {
        }
    }

    private class MediaSample {
        private ByteBuffer buffer;
        private MediaCodec.BufferInfo info;
        private int targetTrack;

        private MediaSample(int targetTrack, ByteBuffer buffer, MediaCodec.BufferInfo info) {
            this.targetTrack = targetTrack;
            this.info = new MediaCodec.BufferInfo();
            this.info.set(0, info.size, info.presentationTimeUs, info.flags);
            this.buffer = ByteBuffer.allocate(buffer.capacity());
            this.buffer.put(buffer);
            this.buffer.flip();
        }
    }
}
