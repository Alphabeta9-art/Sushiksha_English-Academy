package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.CryptoInfo;
import com.google.common.base.Supplier;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes11.dex */
final class AsynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private static final int STATE_CREATED = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_SHUT_DOWN = 2;
    private final AsynchronousMediaCodecCallback asynchronousMediaCodecCallback;
    private final AsynchronousMediaCodecBufferEnqueuer bufferEnqueuer;
    private final MediaCodec codec;
    private boolean codecReleased;
    private int state;
    private final boolean synchronizeCodecInteractionsWithQueueing;

    public static final class Factory implements MediaCodecAdapter.Factory {
        private final Supplier<HandlerThread> callbackThreadSupplier;
        private final Supplier<HandlerThread> queueingThreadSupplier;
        private final boolean synchronizeCodecInteractionsWithQueueing;

        public Factory(final int trackType, boolean synchronizeCodecInteractionsWithQueueing) {
            this(new Supplier() { // from class: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return AsynchronousMediaCodecAdapter.Factory.lambda$new$0(trackType);
                }
            }, new Supplier() { // from class: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda1
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return AsynchronousMediaCodecAdapter.Factory.lambda$new$1(trackType);
                }
            }, synchronizeCodecInteractionsWithQueueing);
        }

        static /* synthetic */ HandlerThread lambda$new$0(int trackType) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createCallbackThreadLabel(trackType));
        }

        static /* synthetic */ HandlerThread lambda$new$1(int trackType) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createQueueingThreadLabel(trackType));
        }

        Factory(Supplier<HandlerThread> callbackThreadSupplier, Supplier<HandlerThread> queueingThreadSupplier, boolean synchronizeCodecInteractionsWithQueueing) {
            this.callbackThreadSupplier = callbackThreadSupplier;
            this.queueingThreadSupplier = queueingThreadSupplier;
            this.synchronizeCodecInteractionsWithQueueing = synchronizeCodecInteractionsWithQueueing;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
        public AsynchronousMediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration configuration) throws Exception {
            String codecName = configuration.codecInfo.name;
            AsynchronousMediaCodecAdapter codecAdapter = null;
            MediaCodec codec = null;
            try {
                TraceUtil.beginSection("createCodec:" + codecName);
                MediaCodec codec2 = MediaCodec.createByCodecName(codecName);
                try {
                    codecAdapter = new AsynchronousMediaCodecAdapter(codec2, this.callbackThreadSupplier.get(), this.queueingThreadSupplier.get(), this.synchronizeCodecInteractionsWithQueueing);
                    TraceUtil.endSection();
                    codecAdapter.initialize(configuration.mediaFormat, configuration.surface, configuration.crypto, configuration.flags);
                    return codecAdapter;
                } catch (Exception e) {
                    e = e;
                    codec = codec2;
                    if (codecAdapter != null) {
                        codecAdapter.release();
                    } else if (codec != null) {
                        codec.release();
                    }
                    throw e;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
    }

    private AsynchronousMediaCodecAdapter(MediaCodec codec, HandlerThread callbackThread, HandlerThread enqueueingThread, boolean synchronizeCodecInteractionsWithQueueing) {
        this.codec = codec;
        this.asynchronousMediaCodecCallback = new AsynchronousMediaCodecCallback(callbackThread);
        this.bufferEnqueuer = new AsynchronousMediaCodecBufferEnqueuer(codec, enqueueingThread);
        this.synchronizeCodecInteractionsWithQueueing = synchronizeCodecInteractionsWithQueueing;
        this.state = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initialize(MediaFormat mediaFormat, Surface surface, MediaCrypto crypto, int flags) {
        this.asynchronousMediaCodecCallback.initialize(this.codec);
        TraceUtil.beginSection("configureCodec");
        this.codec.configure(mediaFormat, surface, crypto, flags);
        TraceUtil.endSection();
        this.bufferEnqueuer.start();
        TraceUtil.beginSection("startCodec");
        this.codec.start();
        TraceUtil.endSection();
        this.state = 1;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public boolean needsReconfiguration() {
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueInputBuffer(int index, int offset, int size, long presentationTimeUs, int flags) {
        this.bufferEnqueuer.queueInputBuffer(index, offset, size, presentationTimeUs, flags);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueSecureInputBuffer(int index, int offset, CryptoInfo info, long presentationTimeUs, int flags) {
        this.bufferEnqueuer.queueSecureInputBuffer(index, offset, info, presentationTimeUs, flags);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void releaseOutputBuffer(int index, boolean render) {
        this.codec.releaseOutputBuffer(index, render);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void releaseOutputBuffer(int index, long renderTimeStampNs) {
        this.codec.releaseOutputBuffer(index, renderTimeStampNs);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueInputBufferIndex() {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueInputBufferIndex();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueOutputBufferIndex(bufferInfo);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public MediaFormat getOutputFormat() {
        return this.asynchronousMediaCodecCallback.getOutputFormat();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public ByteBuffer getInputBuffer(int index) {
        return this.codec.getInputBuffer(index);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public ByteBuffer getOutputBuffer(int index) {
        return this.codec.getOutputBuffer(index);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void flush() {
        this.bufferEnqueuer.flush();
        this.codec.flush();
        this.asynchronousMediaCodecCallback.flush();
        this.codec.start();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void release() {
        try {
            if (this.state == 1) {
                this.bufferEnqueuer.shutdown();
                this.asynchronousMediaCodecCallback.shutdown();
            }
            this.state = 2;
        } finally {
            if (!this.codecReleased) {
                this.codec.release();
                this.codecReleased = true;
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setOnFrameRenderedListener(final MediaCodecAdapter.OnFrameRenderedListener listener, Handler handler) {
        maybeBlockOnQueueing();
        this.codec.setOnFrameRenderedListener(new MediaCodec.OnFrameRenderedListener() { // from class: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter$$ExternalSyntheticLambda0
            @Override // android.media.MediaCodec.OnFrameRenderedListener
            public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
                this.f$0.m8286x4a2a5e4a(listener, mediaCodec, j, j2);
            }
        }, handler);
    }

    /* JADX INFO: renamed from: lambda$setOnFrameRenderedListener$0$androidx-media3-exoplayer-mediacodec-AsynchronousMediaCodecAdapter, reason: not valid java name */
    /* synthetic */ void m8286x4a2a5e4a(MediaCodecAdapter.OnFrameRenderedListener listener, MediaCodec codec, long presentationTimeUs, long nanoTime) {
        listener.onFrameRendered(this, presentationTimeUs, nanoTime);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setOutputSurface(Surface surface) {
        maybeBlockOnQueueing();
        this.codec.setOutputSurface(surface);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setParameters(Bundle params) {
        maybeBlockOnQueueing();
        this.codec.setParameters(params);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setVideoScalingMode(int scalingMode) {
        maybeBlockOnQueueing();
        this.codec.setVideoScalingMode(scalingMode);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public PersistableBundle getMetrics() {
        maybeBlockOnQueueing();
        return this.codec.getMetrics();
    }

    void onError(MediaCodec.CodecException error) {
        this.asynchronousMediaCodecCallback.onError(this.codec, error);
    }

    void onOutputFormatChanged(MediaFormat format) {
        this.asynchronousMediaCodecCallback.onOutputFormatChanged(this.codec, format);
    }

    private void maybeBlockOnQueueing() {
        if (this.synchronizeCodecInteractionsWithQueueing) {
            try {
                this.bufferEnqueuer.waitUntilQueueingComplete();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String createCallbackThreadLabel(int trackType) {
        return createThreadLabel(trackType, "ExoPlayer:MediaCodecAsyncAdapter:");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String createQueueingThreadLabel(int trackType) {
        return createThreadLabel(trackType, "ExoPlayer:MediaCodecQueueingThread:");
    }

    private static String createThreadLabel(int trackType, String prefix) {
        StringBuilder labelBuilder = new StringBuilder(prefix);
        if (trackType == 1) {
            labelBuilder.append("Audio");
        } else if (trackType == 2) {
            labelBuilder.append("Video");
        } else {
            labelBuilder.append("Unknown(").append(trackType).append(")");
        }
        return labelBuilder.toString();
    }
}
