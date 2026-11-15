package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes11.dex */
final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    private final HandlerThread callbackThread;
    private MediaFormat currentFormat;
    private Handler handler;
    private IllegalStateException internalException;
    private MediaCodec.CodecException mediaCodecException;
    private long pendingFlushCount;
    private MediaFormat pendingOutputFormat;
    private boolean shutDown;
    private final Object lock = new Object();
    private final IntArrayQueue availableInputBuffers = new IntArrayQueue();
    private final IntArrayQueue availableOutputBuffers = new IntArrayQueue();
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos = new ArrayDeque<>();
    private final ArrayDeque<MediaFormat> formats = new ArrayDeque<>();

    AsynchronousMediaCodecCallback(HandlerThread callbackThread) {
        this.callbackThread = callbackThread;
    }

    public void initialize(MediaCodec codec) {
        Assertions.checkState(this.handler == null);
        this.callbackThread.start();
        Handler handler = new Handler(this.callbackThread.getLooper());
        codec.setCallback(this, handler);
        this.handler = handler;
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }

    public int dequeueInputBufferIndex() {
        synchronized (this.lock) {
            maybeThrowException();
            int iRemove = -1;
            if (isFlushingOrShutdown()) {
                return -1;
            }
            if (!this.availableInputBuffers.isEmpty()) {
                iRemove = this.availableInputBuffers.remove();
            }
            return iRemove;
        }
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) throws Throwable {
        synchronized (this.lock) {
            try {
                try {
                    maybeThrowException();
                    try {
                        if (isFlushingOrShutdown()) {
                            return -1;
                        }
                        if (this.availableOutputBuffers.isEmpty()) {
                            return -1;
                        }
                        int bufferIndex = this.availableOutputBuffers.remove();
                        if (bufferIndex >= 0) {
                            Assertions.checkStateNotNull(this.currentFormat);
                            MediaCodec.BufferInfo nextBufferInfo = this.bufferInfos.remove();
                            bufferInfo.set(nextBufferInfo.offset, nextBufferInfo.size, nextBufferInfo.presentationTimeUs, nextBufferInfo.flags);
                        } else if (bufferIndex == -2) {
                            this.currentFormat = this.formats.remove();
                        }
                        return bufferIndex;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            throw th;
        }
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            if (this.currentFormat == null) {
                throw new IllegalStateException();
            }
            mediaFormat = this.currentFormat;
        }
        return mediaFormat;
    }

    public void flush() {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            ((Handler) Util.castNonNull(this.handler)).post(new Runnable() { // from class: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.onFlushCompleted();
                }
            });
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec codec, int index) {
        synchronized (this.lock) {
            this.availableInputBuffers.add(index);
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec codec, int index, MediaCodec.BufferInfo info) {
        synchronized (this.lock) {
            if (this.pendingOutputFormat != null) {
                addOutputFormat(this.pendingOutputFormat);
                this.pendingOutputFormat = null;
            }
            this.availableOutputBuffers.add(index);
            this.bufferInfos.add(info);
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec codec, MediaCodec.CodecException e) {
        synchronized (this.lock) {
            this.mediaCodecException = e;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec codec, MediaFormat format) {
        synchronized (this.lock) {
            addOutputFormat(format);
            this.pendingOutputFormat = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFlushCompleted() {
        synchronized (this.lock) {
            if (this.shutDown) {
                return;
            }
            this.pendingFlushCount--;
            if (this.pendingFlushCount > 0) {
                return;
            }
            if (this.pendingFlushCount < 0) {
                setInternalException(new IllegalStateException());
            } else {
                flushInternal();
            }
        }
    }

    private void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        this.availableInputBuffers.clear();
        this.availableOutputBuffers.clear();
        this.bufferInfos.clear();
        this.formats.clear();
    }

    private boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    private void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.add(-2);
        this.formats.add(mediaFormat);
    }

    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
    }

    private void maybeThrowInternalException() {
        if (this.internalException != null) {
            IllegalStateException e = this.internalException;
            this.internalException = null;
            throw e;
        }
    }

    private void maybeThrowMediaCodecException() {
        if (this.mediaCodecException != null) {
            MediaCodec.CodecException codecException = this.mediaCodecException;
            this.mediaCodecException = null;
            throw codecException;
        }
    }

    private void setInternalException(IllegalStateException e) {
        synchronized (this.lock) {
            this.internalException = e;
        }
    }
}
