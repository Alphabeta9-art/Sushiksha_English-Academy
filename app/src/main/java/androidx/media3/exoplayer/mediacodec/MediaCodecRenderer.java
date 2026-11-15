package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.OggOpusAudioPacketizer;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.FrameworkCryptoConfig;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = {0, 0, 1, 103, 66, -64, Ascii.VT, -38, 37, -112, 0, 0, 1, 104, -50, Ascii.SI, 19, 32, 0, 0, 1, 101, -120, -124, Ascii.CR, -50, 113, Ascii.CAN, -96, 0, 47, -65, Ascii.FS, 49, -61, 39, 93, 120};
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    protected static final float CODEC_OPERATING_RATE_UNSET = -1.0f;
    private static final int DRAIN_ACTION_FLUSH = 1;
    private static final int DRAIN_ACTION_FLUSH_AND_UPDATE_DRM_SESSION = 2;
    private static final int DRAIN_ACTION_NONE = 0;
    private static final int DRAIN_ACTION_REINITIALIZE = 3;
    private static final int DRAIN_STATE_NONE = 0;
    private static final int DRAIN_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int DRAIN_STATE_WAIT_END_OF_STREAM = 2;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final String TAG = "MediaCodecRenderer";
    private final float assumedMinimumCodecOperatingRate;
    private ArrayDeque<MediaCodecInfo> availableCodecInfos;
    private final DecoderInputBuffer buffer;
    private final BatchBuffer bypassBatchBuffer;
    private boolean bypassDrainAndReinitialize;
    private boolean bypassEnabled;
    private final DecoderInputBuffer bypassSampleBuffer;
    private boolean bypassSampleBufferPending;
    private C2Mp3TimestampTracker c2Mp3TimestampTracker;
    private MediaCodecAdapter codec;
    private int codecAdaptationWorkaroundMode;
    private final MediaCodecAdapter.Factory codecAdapterFactory;
    private int codecDrainAction;
    private int codecDrainState;
    private DrmSession codecDrmSession;
    private boolean codecHasOutputMediaFormat;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private Format codecInputFormat;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosBufferTimestampWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagation;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecNeedsSosFlushWorkaround;
    private float codecOperatingRate;
    private MediaFormat codecOutputMediaFormat;
    private boolean codecOutputMediaFormatChanged;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private float currentPlaybackSpeed;
    private final ArrayList<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private final boolean enableDecoderFallback;
    private Format inputFormat;
    private int inputIndex;
    private boolean inputStreamEnded;
    private boolean isDecodeOnlyOutputBuffer;
    private boolean isLastOutputBuffer;
    private long largestQueuedPresentationTimeUs;
    private long lastBufferInStreamPresentationTimeUs;
    private long lastProcessedOutputBufferTimeUs;
    private final MediaCodecSelector mediaCodecSelector;
    private MediaCrypto mediaCrypto;
    private boolean mediaCryptoRequiresSecureDecoder;
    private boolean needToNotifyOutputFormatChangeAfterStreamChange;
    private final DecoderInputBuffer noDataBuffer;
    private final OggOpusAudioPacketizer oggOpusAudioPacketizer;
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private Format outputFormat;
    private int outputIndex;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo;
    private boolean pendingOutputEndOfStream;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    private ExoPlaybackException pendingPlaybackException;
    private DecoderInitializationException preferredDecoderInitializationException;
    private long renderTimeLimitMs;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private DrmSession sourceDrmSession;
    private float targetPlaybackSpeed;
    private boolean waitingForFirstSampleInFormat;

    protected abstract List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException;

    protected abstract MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f);

    protected abstract boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException;

    protected abstract int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException;

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final MediaCodecInfo codecInfo;
        public final String diagnosticInfo;
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable cause, boolean secureDecoderRequired, int errorCode) {
            this("Decoder init failed: [" + errorCode + "], " + format, cause, format.sampleMimeType, secureDecoderRequired, null, buildCustomDiagnosticInfo(errorCode), null);
        }

        public DecoderInitializationException(Format format, Throwable cause, boolean secureDecoderRequired, MediaCodecInfo mediaCodecInfo) {
            this("Decoder init failed: " + mediaCodecInfo.name + ", " + format, cause, format.sampleMimeType, secureDecoderRequired, mediaCodecInfo, Util.SDK_INT >= 21 ? getDiagnosticInfoV21(cause) : null, null);
        }

        private DecoderInitializationException(String message, Throwable cause, String mimeType, boolean secureDecoderRequired, MediaCodecInfo mediaCodecInfo, String diagnosticInfo, DecoderInitializationException fallbackDecoderInitializationException) {
            super(message, cause);
            this.mimeType = mimeType;
            this.secureDecoderRequired = secureDecoderRequired;
            this.codecInfo = mediaCodecInfo;
            this.diagnosticInfo = diagnosticInfo;
            this.fallbackDecoderInitializationException = fallbackDecoderInitializationException;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DecoderInitializationException copyWithFallbackException(DecoderInitializationException fallbackException) {
            return new DecoderInitializationException(getMessage(), getCause(), this.mimeType, this.secureDecoderRequired, this.codecInfo, this.diagnosticInfo, fallbackException);
        }

        private static String getDiagnosticInfoV21(Throwable cause) {
            if (cause instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) cause).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int errorCode) {
            String sign = errorCode < 0 ? "neg_" : "";
            return "androidx.media3.exoplayer.mediacodec.MediaCodecRenderer_" + sign + Math.abs(errorCode);
        }
    }

    public MediaCodecRenderer(int trackType, MediaCodecAdapter.Factory codecAdapterFactory, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, float assumedMinimumCodecOperatingRate) {
        super(trackType);
        this.codecAdapterFactory = codecAdapterFactory;
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector);
        this.enableDecoderFallback = enableDecoderFallback;
        this.assumedMinimumCodecOperatingRate = assumedMinimumCodecOperatingRate;
        this.noDataBuffer = DecoderInputBuffer.newNoDataInstance();
        this.buffer = new DecoderInputBuffer(0);
        this.bypassSampleBuffer = new DecoderInputBuffer(2);
        this.bypassBatchBuffer = new BatchBuffer();
        this.decodeOnlyPresentationTimestamps = new ArrayList<>();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.currentPlaybackSpeed = 1.0f;
        this.targetPlaybackSpeed = 1.0f;
        this.renderTimeLimitMs = C.TIME_UNSET;
        this.pendingOutputStreamChanges = new ArrayDeque<>();
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        this.bypassBatchBuffer.ensureSpaceForWrite(0);
        this.bypassBatchBuffer.data.order(ByteOrder.nativeOrder());
        this.oggOpusAudioPacketizer = new OggOpusAudioPacketizer();
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecReconfigurationState = 0;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        this.lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
    }

    public void setRenderTimeLimitMs(long renderTimeLimitMs) {
        this.renderTimeLimitMs = renderTimeLimitMs;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, format);
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw createRendererException(e, format, PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED);
        }
    }

    protected final void maybeInitCodecOrBypass() throws ExoPlaybackException {
        if (this.codec != null || this.bypassEnabled || this.inputFormat == null) {
            return;
        }
        if (isBypassPossible(this.inputFormat)) {
            initBypass(this.inputFormat);
            return;
        }
        setCodecDrmSession(this.sourceDrmSession);
        String mimeType = this.inputFormat.sampleMimeType;
        if (this.codecDrmSession != null) {
            CryptoConfig cryptoConfig = this.codecDrmSession.getCryptoConfig();
            if (this.mediaCrypto == null) {
                if (cryptoConfig == null) {
                    DrmSession.DrmSessionException drmError = this.codecDrmSession.getError();
                    if (drmError == null) {
                        return;
                    }
                } else if (cryptoConfig instanceof FrameworkCryptoConfig) {
                    FrameworkCryptoConfig frameworkCryptoConfig = (FrameworkCryptoConfig) cryptoConfig;
                    try {
                        this.mediaCrypto = new MediaCrypto(frameworkCryptoConfig.uuid, frameworkCryptoConfig.sessionId);
                        this.mediaCryptoRequiresSecureDecoder = !frameworkCryptoConfig.forceAllowInsecureDecoderComponents && this.mediaCrypto.requiresSecureDecoderComponent(mimeType);
                    } catch (MediaCryptoException e) {
                        throw createRendererException(e, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
                    }
                }
            }
            if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC && (cryptoConfig instanceof FrameworkCryptoConfig)) {
                int drmSessionState = this.codecDrmSession.getState();
                if (drmSessionState == 1) {
                    DrmSession.DrmSessionException drmSessionException = (DrmSession.DrmSessionException) Assertions.checkNotNull(this.codecDrmSession.getError());
                    throw createRendererException(drmSessionException, this.inputFormat, drmSessionException.errorCode);
                }
                if (drmSessionState != 4) {
                    return;
                }
            }
        }
        try {
            maybeInitCodecWithFallback(this.mediaCrypto, this.mediaCryptoRequiresSecureDecoder);
        } catch (DecoderInitializationException e2) {
            throw createRendererException(e2, this.inputFormat, PlaybackException.ERROR_CODE_DECODER_INIT_FAILED);
        }
    }

    protected final boolean isBypassPossible(Format format) {
        return this.sourceDrmSession == null && shouldUseBypass(format);
    }

    protected boolean shouldUseBypass(Format format) {
        return false;
    }

    protected boolean shouldInitCodec(MediaCodecInfo codecInfo) {
        return true;
    }

    protected boolean shouldReinitCodec() {
        return false;
    }

    protected boolean getCodecNeedsEosPropagation() {
        return false;
    }

    protected final void setPendingPlaybackException(ExoPlaybackException exception) {
        this.pendingPlaybackException = exception;
    }

    protected final void updateOutputFormatForTime(long presentationTimeUs) throws ExoPlaybackException {
        boolean outputFormatChanged = false;
        Format format = this.outputStreamInfo.formatQueue.pollFloor(presentationTimeUs);
        if (format == null && this.needToNotifyOutputFormatChangeAfterStreamChange && this.codecOutputMediaFormat != null) {
            format = this.outputStreamInfo.formatQueue.pollFirst();
        }
        if (format != null) {
            this.outputFormat = format;
            outputFormatChanged = true;
        }
        if (outputFormatChanged || (this.codecOutputMediaFormatChanged && this.outputFormat != null)) {
            onOutputFormatChanged(this.outputFormat, this.codecOutputMediaFormat);
            this.codecOutputMediaFormatChanged = false;
            this.needToNotifyOutputFormatChangeAfterStreamChange = false;
        }
    }

    protected final MediaCodecAdapter getCodec() {
        return this.codec;
    }

    protected final MediaFormat getCodecOutputMediaFormat() {
        return this.codecOutputMediaFormat;
    }

    protected final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onEnabled(boolean joining, boolean mayRenderStartOfStream) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onStreamChanged(Format[] formats, long startPositionUs, long offsetUs) throws ExoPlaybackException {
        if (this.outputStreamInfo.streamOffsetUs == C.TIME_UNSET) {
            setOutputStreamInfo(new OutputStreamInfo(C.TIME_UNSET, startPositionUs, offsetUs));
            return;
        }
        if (this.pendingOutputStreamChanges.isEmpty() && (this.largestQueuedPresentationTimeUs == C.TIME_UNSET || (this.lastProcessedOutputBufferTimeUs != C.TIME_UNSET && this.lastProcessedOutputBufferTimeUs >= this.largestQueuedPresentationTimeUs))) {
            setOutputStreamInfo(new OutputStreamInfo(C.TIME_UNSET, startPositionUs, offsetUs));
            if (this.outputStreamInfo.streamOffsetUs != C.TIME_UNSET) {
                onProcessedStreamChange();
                return;
            }
            return;
        }
        this.pendingOutputStreamChanges.add(new OutputStreamInfo(this.largestQueuedPresentationTimeUs, startPositionUs, offsetUs));
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onPositionReset(long positionUs, boolean joining) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.pendingOutputEndOfStream = false;
        if (this.bypassEnabled) {
            this.bypassBatchBuffer.clear();
            this.bypassSampleBuffer.clear();
            this.bypassSampleBufferPending = false;
            this.oggOpusAudioPacketizer.reset();
        } else {
            flushOrReinitializeCodec();
        }
        if (this.outputStreamInfo.formatQueue.size() > 0) {
            this.waitingForFirstSampleInFormat = true;
        }
        this.outputStreamInfo.formatQueue.clear();
        this.pendingOutputStreamChanges.clear();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float currentPlaybackSpeed, float targetPlaybackSpeed) throws ExoPlaybackException {
        this.currentPlaybackSpeed = currentPlaybackSpeed;
        this.targetPlaybackSpeed = targetPlaybackSpeed;
        updateCodecOperatingRate(this.codecInputFormat);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onDisabled() {
        this.inputFormat = null;
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        this.pendingOutputStreamChanges.clear();
        flushOrReleaseCodec();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onReset() {
        try {
            disableBypass();
            releaseCodec();
        } finally {
            setSourceDrmSession(null);
        }
    }

    private void disableBypass() {
        this.bypassDrainAndReinitialize = false;
        this.bypassBatchBuffer.clear();
        this.bypassSampleBuffer.clear();
        this.bypassSampleBufferPending = false;
        this.bypassEnabled = false;
        this.oggOpusAudioPacketizer.reset();
    }

    protected void releaseCodec() {
        try {
            if (this.codec != null) {
                this.codec.release();
                this.decoderCounters.decoderReleaseCount++;
                onCodecReleased(this.codecInfo.name);
            }
            this.codec = null;
            if (this.mediaCrypto != null) {
                this.mediaCrypto.release();
            }
        } catch (Throwable th) {
            this.codec = null;
            if (this.mediaCrypto != null) {
                this.mediaCrypto.release();
            }
            throw th;
        } finally {
            this.mediaCrypto = null;
            setCodecDrmSession(null);
            resetCodecStateForRelease();
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onStarted() {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onStopped() {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        boolean isRecoverable = false;
        if (this.pendingOutputEndOfStream) {
            this.pendingOutputEndOfStream = false;
            processEndOfStream();
        }
        if (this.pendingPlaybackException != null) {
            ExoPlaybackException playbackException = this.pendingPlaybackException;
            this.pendingPlaybackException = null;
            throw playbackException;
        }
        try {
            if (this.outputStreamEnded) {
                renderToEndOfStream();
                return;
            }
            if (this.inputFormat == null && !readSourceOmittingSampleData(2)) {
                return;
            }
            maybeInitCodecOrBypass();
            if (this.bypassEnabled) {
                TraceUtil.beginSection("bypassRender");
                while (bypassRender(positionUs, elapsedRealtimeUs)) {
                }
                TraceUtil.endSection();
            } else if (this.codec != null) {
                long renderStartTimeMs = SystemClock.elapsedRealtime();
                TraceUtil.beginSection("drainAndFeed");
                while (drainOutputBuffer(positionUs, elapsedRealtimeUs) && shouldContinueRendering(renderStartTimeMs)) {
                }
                while (feedInputBuffer() && shouldContinueRendering(renderStartTimeMs)) {
                }
                TraceUtil.endSection();
            } else {
                this.decoderCounters.skippedInputBufferCount += skipSource(positionUs);
                readSourceOmittingSampleData(1);
            }
            this.decoderCounters.ensureUpdated();
        } catch (IllegalStateException e) {
            if (isMediaCodecException(e)) {
                onCodecError(e);
                if (Util.SDK_INT >= 21 && isRecoverableMediaCodecExceptionV21(e)) {
                    isRecoverable = true;
                }
                if (isRecoverable) {
                    releaseCodec();
                }
                throw createRendererException(createDecoderException(e, getCodecInfo()), this.inputFormat, isRecoverable, PlaybackException.ERROR_CODE_DECODING_FAILED);
            }
            throw e;
        }
    }

    protected final boolean flushOrReinitializeCodec() throws ExoPlaybackException {
        boolean released = flushOrReleaseCodec();
        if (released) {
            maybeInitCodecOrBypass();
        }
        return released;
    }

    protected boolean flushOrReleaseCodec() {
        if (this.codec == null) {
            return false;
        }
        if (this.codecDrainAction == 3 || this.codecNeedsFlushWorkaround || ((this.codecNeedsSosFlushWorkaround && !this.codecHasOutputMediaFormat) || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos))) {
            releaseCodec();
            return true;
        }
        if (this.codecDrainAction == 2) {
            Assertions.checkState(Util.SDK_INT >= 23);
            if (Util.SDK_INT >= 23) {
                try {
                    updateDrmSessionV23();
                } catch (ExoPlaybackException e) {
                    Log.w(TAG, "Failed to update the DRM session, releasing the codec instead.", e);
                    releaseCodec();
                    return true;
                }
            }
        }
        flushCodec();
        return false;
    }

    private void flushCodec() {
        try {
            this.codec.flush();
        } finally {
            resetCodecStateForFlush();
        }
    }

    protected void resetCodecStateForFlush() {
        resetInputBuffer();
        resetOutputBuffer();
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.codecReceivedEos = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.isDecodeOnlyOutputBuffer = false;
        this.isLastOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        this.lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
        if (this.c2Mp3TimestampTracker != null) {
            this.c2Mp3TimestampTracker.reset();
        }
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.codecReconfigurationState = this.codecReconfigured ? 1 : 0;
    }

    protected void resetCodecStateForRelease() {
        resetCodecStateForFlush();
        this.pendingPlaybackException = null;
        this.c2Mp3TimestampTracker = null;
        this.availableCodecInfos = null;
        this.codecInfo = null;
        this.codecInputFormat = null;
        this.codecOutputMediaFormat = null;
        this.codecOutputMediaFormatChanged = false;
        this.codecHasOutputMediaFormat = false;
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecNeedsSosFlushWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsEosOutputExceptionWorkaround = false;
        this.codecNeedsEosBufferTimestampWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsEosPropagation = false;
        this.codecReconfigured = false;
        this.codecReconfigurationState = 0;
        this.mediaCryptoRequiresSecureDecoder = false;
    }

    protected MediaCodecDecoderException createDecoderException(Throwable cause, MediaCodecInfo codecInfo) {
        return new MediaCodecDecoderException(cause, codecInfo);
    }

    private boolean readSourceOmittingSampleData(int readFlags) throws ExoPlaybackException {
        FormatHolder formatHolder = getFormatHolder();
        this.noDataBuffer.clear();
        int result = readSource(formatHolder, this.noDataBuffer, readFlags | 4);
        if (result == -5) {
            onInputFormatChanged(formatHolder);
            return true;
        }
        if (result == -4 && this.noDataBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            processEndOfStream();
            return false;
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:55:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x004c A[SYNTHETIC] */
    private void maybeInitCodecWithFallback(MediaCrypto crypto, boolean mediaCryptoRequiresSecureDecoder) throws DecoderInitializationException {
        DecoderInitializationException exception;
        if (this.availableCodecInfos == null) {
            try {
                List<MediaCodecInfo> allAvailableCodecInfos = getAvailableCodecInfos(mediaCryptoRequiresSecureDecoder);
                this.availableCodecInfos = new ArrayDeque<>();
                if (this.enableDecoderFallback) {
                    this.availableCodecInfos.addAll(allAvailableCodecInfos);
                } else if (!allAvailableCodecInfos.isEmpty()) {
                    this.availableCodecInfos.add(allAvailableCodecInfos.get(0));
                }
                this.preferredDecoderInitializationException = null;
            } catch (MediaCodecUtil.DecoderQueryException e) {
                throw new DecoderInitializationException(this.inputFormat, e, mediaCryptoRequiresSecureDecoder, -49998);
            }
        }
        if (this.availableCodecInfos.isEmpty()) {
            throw new DecoderInitializationException(this.inputFormat, (Throwable) null, mediaCryptoRequiresSecureDecoder, -49999);
        }
        MediaCodecInfo preferredCodecInfo = this.availableCodecInfos.peekFirst();
        while (this.codec == null) {
            MediaCodecInfo codecInfo = this.availableCodecInfos.peekFirst();
            if (!shouldInitCodec(codecInfo)) {
                return;
            }
            try {
                initCodec(codecInfo, crypto);
            } catch (Exception e2) {
                if (codecInfo != preferredCodecInfo) {
                    throw e2;
                }
                try {
                    Log.w(TAG, "Preferred decoder instantiation failed. Sleeping for 50ms then retrying.");
                    Thread.sleep(50L);
                    initCodec(codecInfo, crypto);
                } catch (Exception e3) {
                    Log.w(TAG, "Failed to initialize decoder: " + codecInfo, e3);
                    this.availableCodecInfos.removeFirst();
                    exception = new DecoderInitializationException(this.inputFormat, e3, mediaCryptoRequiresSecureDecoder, codecInfo);
                    onCodecError(exception);
                    if (this.preferredDecoderInitializationException == null) {
                        this.preferredDecoderInitializationException = this.preferredDecoderInitializationException.copyWithFallbackException(exception);
                    } else {
                        this.preferredDecoderInitializationException = exception;
                    }
                    if (!this.availableCodecInfos.isEmpty()) {
                        throw this.preferredDecoderInitializationException;
                    }
                }
                Log.w(TAG, "Failed to initialize decoder: " + codecInfo, e3);
                this.availableCodecInfos.removeFirst();
                exception = new DecoderInitializationException(this.inputFormat, e3, mediaCryptoRequiresSecureDecoder, codecInfo);
                onCodecError(exception);
                if (this.preferredDecoderInitializationException == null) {
                    this.preferredDecoderInitializationException = this.preferredDecoderInitializationException.copyWithFallbackException(exception);
                } else {
                    this.preferredDecoderInitializationException = exception;
                }
                if (!this.availableCodecInfos.isEmpty()) {
                    throw this.preferredDecoderInitializationException;
                }
            }
        }
        this.availableCodecInfos = null;
    }

    private List<MediaCodecInfo> getAvailableCodecInfos(boolean mediaCryptoRequiresSecureDecoder) throws MediaCodecUtil.DecoderQueryException {
        List<MediaCodecInfo> codecInfos = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, mediaCryptoRequiresSecureDecoder);
        if (codecInfos.isEmpty() && mediaCryptoRequiresSecureDecoder) {
            codecInfos = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, false);
            if (!codecInfos.isEmpty()) {
                Log.w(TAG, "Drm session requires secure decoder for " + this.inputFormat.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + codecInfos + ".");
            }
        }
        return codecInfos;
    }

    private void initBypass(Format format) {
        disableBypass();
        String mimeType = format.sampleMimeType;
        if (!MimeTypes.AUDIO_AAC.equals(mimeType) && !MimeTypes.AUDIO_MPEG.equals(mimeType) && !MimeTypes.AUDIO_OPUS.equals(mimeType)) {
            this.bypassBatchBuffer.setMaxSampleCount(1);
        } else {
            this.bypassBatchBuffer.setMaxSampleCount(32);
        }
        this.bypassEnabled = true;
    }

    private void initCodec(MediaCodecInfo codecInfo, MediaCrypto crypto) throws Exception {
        float codecOperatingRate;
        float codecOperatingRate2;
        String codecName = codecInfo.name;
        if (Util.SDK_INT < 23) {
            codecOperatingRate = -1.0f;
        } else {
            codecOperatingRate = getCodecOperatingRateV23(this.targetPlaybackSpeed, this.inputFormat, getStreamFormats());
        }
        if (codecOperatingRate > this.assumedMinimumCodecOperatingRate) {
            codecOperatingRate2 = codecOperatingRate;
        } else {
            codecOperatingRate2 = -1.0f;
        }
        onReadyToInitializeCodec(this.inputFormat);
        long codecInitializingTimestamp = SystemClock.elapsedRealtime();
        MediaCodecAdapter.Configuration configuration = getMediaCodecConfiguration(codecInfo, this.inputFormat, crypto, codecOperatingRate2);
        if (Util.SDK_INT >= 31) {
            Api31.setLogSessionIdToMediaCodecFormat(configuration, getPlayerId());
        }
        try {
            TraceUtil.beginSection("createCodec:" + codecName);
            this.codec = this.codecAdapterFactory.createAdapter(configuration);
            TraceUtil.endSection();
            long codecInitializedTimestamp = SystemClock.elapsedRealtime();
            if (!codecInfo.isFormatSupported(this.inputFormat)) {
                Log.w(TAG, Util.formatInvariant("Format exceeds selected codec's capabilities [%s, %s]", Format.toLogString(this.inputFormat), codecName));
            }
            this.codecInfo = codecInfo;
            this.codecOperatingRate = codecOperatingRate2;
            this.codecInputFormat = this.inputFormat;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(codecName);
            this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(codecName, this.codecInputFormat);
            this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(codecName);
            this.codecNeedsSosFlushWorkaround = codecNeedsSosFlushWorkaround(codecName);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(codecName);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(codecName);
            this.codecNeedsEosBufferTimestampWorkaround = codecNeedsEosBufferTimestampWorkaround(codecName);
            this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(codecName, this.codecInputFormat);
            this.codecNeedsEosPropagation = codecNeedsEosPropagationWorkaround(codecInfo) || getCodecNeedsEosPropagation();
            if (this.codec.needsReconfiguration()) {
                this.codecReconfigured = true;
                this.codecReconfigurationState = 1;
                this.codecNeedsAdaptationWorkaroundBuffer = this.codecAdaptationWorkaroundMode != 0;
            }
            if ("c2.android.mp3.decoder".equals(codecInfo.name)) {
                this.c2Mp3TimestampTracker = new C2Mp3TimestampTracker();
            }
            if (getState() == 2) {
                this.codecHotswapDeadlineMs = SystemClock.elapsedRealtime() + 1000;
            }
            this.decoderCounters.decoderInitCount++;
            long elapsed = codecInitializedTimestamp - codecInitializingTimestamp;
            onCodecInitialized(codecName, configuration, codecInitializedTimestamp, elapsed);
        } catch (Throwable th) {
            TraceUtil.endSection();
            throw th;
        }
    }

    private boolean shouldContinueRendering(long renderStartTimeMs) {
        return this.renderTimeLimitMs == C.TIME_UNSET || SystemClock.elapsedRealtime() - renderStartTimeMs < this.renderTimeLimitMs;
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private void setSourceDrmSession(DrmSession session) {
        DrmSession.replaceSession(this.sourceDrmSession, session);
        this.sourceDrmSession = session;
    }

    private void setCodecDrmSession(DrmSession session) {
        DrmSession.replaceSession(this.codecDrmSession, session);
        this.codecDrmSession = session;
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        if (this.codec == null || this.codecDrainState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.codecDrainState == 0 && shouldReinitCodec()) {
            drainAndReinitializeCodec();
        }
        if (this.inputIndex < 0) {
            this.inputIndex = this.codec.dequeueInputBufferIndex();
            if (this.inputIndex < 0) {
                return false;
            }
            this.buffer.data = this.codec.getInputBuffer(this.inputIndex);
            this.buffer.clear();
        }
        if (this.codecDrainState == 1) {
            if (!this.codecNeedsEosPropagation) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                resetInputBuffer();
            }
            this.codecDrainState = 2;
            return false;
        }
        if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            this.buffer.data.put(ADAPTATION_WORKAROUND_BUFFER);
            this.codec.queueInputBuffer(this.inputIndex, 0, ADAPTATION_WORKAROUND_BUFFER.length, 0L, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        }
        if (this.codecReconfigurationState == 1) {
            for (int i = 0; i < this.codecInputFormat.initializationData.size(); i++) {
                byte[] data = this.codecInputFormat.initializationData.get(i);
                this.buffer.data.put(data);
            }
            this.codecReconfigurationState = 2;
        }
        int adaptiveReconfigurationBytes = this.buffer.data.position();
        FormatHolder formatHolder = getFormatHolder();
        try {
            int result = readSource(formatHolder, this.buffer, 0);
            if (hasReadStreamToEnd() || this.buffer.isLastSample()) {
                this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
            }
            if (result == -3) {
                return false;
            }
            if (result == -5) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                onInputFormatChanged(formatHolder);
                return true;
            }
            if (this.buffer.isEndOfStream()) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                this.inputStreamEnded = true;
                if (!this.codecReceivedBuffers) {
                    processEndOfStream();
                    return false;
                }
                try {
                    if (!this.codecNeedsEosPropagation) {
                        this.codecReceivedEos = true;
                        this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                        resetInputBuffer();
                    }
                    return false;
                } catch (MediaCodec.CryptoException e) {
                    throw createRendererException(e, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e.getErrorCode()));
                }
            }
            if (!this.codecReceivedBuffers && !this.buffer.isKeyFrame()) {
                this.buffer.clear();
                if (this.codecReconfigurationState == 2) {
                    this.codecReconfigurationState = 1;
                }
                return true;
            }
            boolean bufferEncrypted = this.buffer.isEncrypted();
            if (bufferEncrypted) {
                this.buffer.cryptoInfo.increaseClearDataFirstSubSampleBy(adaptiveReconfigurationBytes);
            }
            if (this.codecNeedsDiscardToSpsWorkaround && !bufferEncrypted) {
                NalUnitUtil.discardToSps(this.buffer.data);
                if (this.buffer.data.position() == 0) {
                    return true;
                }
                this.codecNeedsDiscardToSpsWorkaround = false;
            }
            long presentationTimeUs = this.buffer.timeUs;
            if (this.c2Mp3TimestampTracker != null) {
                presentationTimeUs = this.c2Mp3TimestampTracker.updateAndGetPresentationTimeUs(this.inputFormat, this.buffer);
                this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, this.c2Mp3TimestampTracker.getLastOutputBufferPresentationTimeUs(this.inputFormat));
            }
            if (this.buffer.isDecodeOnly()) {
                this.decodeOnlyPresentationTimestamps.add(Long.valueOf(presentationTimeUs));
            }
            if (this.waitingForFirstSampleInFormat) {
                if (!this.pendingOutputStreamChanges.isEmpty()) {
                    this.pendingOutputStreamChanges.peekLast().formatQueue.add(presentationTimeUs, this.inputFormat);
                } else {
                    this.outputStreamInfo.formatQueue.add(presentationTimeUs, this.inputFormat);
                }
                this.waitingForFirstSampleInFormat = false;
            }
            this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, presentationTimeUs);
            this.buffer.flip();
            if (this.buffer.hasSupplementalData()) {
                handleInputBufferSupplementalData(this.buffer);
            }
            onQueueInputBuffer(this.buffer);
            try {
                if (bufferEncrypted) {
                    try {
                        this.codec.queueSecureInputBuffer(this.inputIndex, 0, this.buffer.cryptoInfo, presentationTimeUs, 0);
                    } catch (MediaCodec.CryptoException e2) {
                        e = e2;
                        throw createRendererException(e, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e.getErrorCode()));
                    }
                } else {
                    this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), presentationTimeUs, 0);
                }
                resetInputBuffer();
                this.codecReceivedBuffers = true;
                this.codecReconfigurationState = 0;
                this.decoderCounters.queuedInputBufferCount++;
                return true;
            } catch (MediaCodec.CryptoException e3) {
                e = e3;
            }
        } catch (DecoderInputBuffer.InsufficientCapacityException e4) {
            onCodecError(e4);
            readSourceOmittingSampleData(0);
            flushCodec();
            return true;
        }
    }

    protected void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
    }

    protected void onCodecInitialized(String name, MediaCodecAdapter.Configuration configuration, long initializedTimestampMs, long initializationDurationMs) {
    }

    protected void onCodecReleased(String name) {
    }

    protected void onCodecError(Exception codecError) {
    }

    /* JADX WARN: Code duplicated, block: B:67:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00e9  */
    protected DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        int overridingDiscardReasons;
        boolean z = true;
        this.waitingForFirstSampleInFormat = true;
        Format newFormat = (Format) Assertions.checkNotNull(formatHolder.format);
        if (newFormat.sampleMimeType == null) {
            throw createRendererException(new IllegalArgumentException(), newFormat, PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED);
        }
        setSourceDrmSession(formatHolder.drmSession);
        this.inputFormat = newFormat;
        if (this.bypassEnabled) {
            this.bypassDrainAndReinitialize = true;
            return null;
        }
        if (this.codec == null) {
            this.availableCodecInfos = null;
            maybeInitCodecOrBypass();
            return null;
        }
        MediaCodecAdapter codec = this.codec;
        MediaCodecInfo codecInfo = this.codecInfo;
        Format oldFormat = this.codecInputFormat;
        if (drmNeedsCodecReinitialization(codecInfo, newFormat, this.codecDrmSession, this.sourceDrmSession)) {
            drainAndReinitializeCodec();
            return new DecoderReuseEvaluation(codecInfo.name, oldFormat, newFormat, 0, 128);
        }
        boolean drainAndUpdateCodecDrmSession = this.sourceDrmSession != this.codecDrmSession;
        Assertions.checkState(!drainAndUpdateCodecDrmSession || Util.SDK_INT >= 23);
        DecoderReuseEvaluation evaluation = canReuseCodec(codecInfo, oldFormat, newFormat);
        switch (evaluation.result) {
            case 0:
                drainAndReinitializeCodec();
                overridingDiscardReasons = 0;
                if (evaluation.result == 0 && (this.codec != codec || this.codecDrainAction == 3)) {
                    return new DecoderReuseEvaluation(codecInfo.name, oldFormat, newFormat, 0, overridingDiscardReasons);
                }
                return evaluation;
            case 1:
                if (!updateCodecOperatingRate(newFormat)) {
                    int overridingDiscardReasons2 = 0 | 16;
                    overridingDiscardReasons = overridingDiscardReasons2;
                } else {
                    this.codecInputFormat = newFormat;
                    if (drainAndUpdateCodecDrmSession) {
                        if (!drainAndUpdateCodecDrmSessionV23()) {
                            int overridingDiscardReasons3 = 0 | 2;
                            overridingDiscardReasons = overridingDiscardReasons3;
                        } else {
                            overridingDiscardReasons = 0;
                        }
                    } else if (!drainAndFlushCodec()) {
                        int overridingDiscardReasons4 = 0 | 2;
                        overridingDiscardReasons = overridingDiscardReasons4;
                    } else {
                        overridingDiscardReasons = 0;
                    }
                }
                if (evaluation.result == 0) {
                    break;
                }
                return evaluation;
            case 2:
                if (!updateCodecOperatingRate(newFormat)) {
                    int overridingDiscardReasons5 = 0 | 16;
                    overridingDiscardReasons = overridingDiscardReasons5;
                } else {
                    this.codecReconfigured = true;
                    this.codecReconfigurationState = 1;
                    if (this.codecAdaptationWorkaroundMode != 2 && (this.codecAdaptationWorkaroundMode != 1 || newFormat.width != oldFormat.width || newFormat.height != oldFormat.height)) {
                        z = false;
                    }
                    this.codecNeedsAdaptationWorkaroundBuffer = z;
                    this.codecInputFormat = newFormat;
                    if (drainAndUpdateCodecDrmSession && !drainAndUpdateCodecDrmSessionV23()) {
                        int overridingDiscardReasons6 = 0 | 2;
                        overridingDiscardReasons = overridingDiscardReasons6;
                    } else {
                        overridingDiscardReasons = 0;
                    }
                }
                if (evaluation.result == 0) {
                    break;
                }
                return evaluation;
            case 3:
                if (!updateCodecOperatingRate(newFormat)) {
                    int overridingDiscardReasons7 = 0 | 16;
                    overridingDiscardReasons = overridingDiscardReasons7;
                } else {
                    this.codecInputFormat = newFormat;
                    if (drainAndUpdateCodecDrmSession && !drainAndUpdateCodecDrmSessionV23()) {
                        int overridingDiscardReasons8 = 0 | 2;
                        overridingDiscardReasons = overridingDiscardReasons8;
                    } else {
                        overridingDiscardReasons = 0;
                    }
                }
                if (evaluation.result == 0) {
                    break;
                }
                return evaluation;
            default:
                throw new IllegalStateException();
        }
    }

    protected void onOutputFormatChanged(Format format, MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    protected void handleInputBufferSupplementalData(DecoderInputBuffer buffer) throws ExoPlaybackException {
    }

    protected void onQueueInputBuffer(DecoderInputBuffer buffer) throws ExoPlaybackException {
    }

    protected void onProcessedOutputBuffer(long presentationTimeUs) {
        this.lastProcessedOutputBufferTimeUs = presentationTimeUs;
        while (!this.pendingOutputStreamChanges.isEmpty() && presentationTimeUs >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            setOutputStreamInfo(this.pendingOutputStreamChanges.poll());
            onProcessedStreamChange();
        }
    }

    protected void onProcessedStreamChange() {
    }

    protected DecoderReuseEvaluation canReuseCodec(MediaCodecInfo codecInfo, Format oldFormat, Format newFormat) {
        return new DecoderReuseEvaluation(codecInfo.name, oldFormat, newFormat, 0, 1);
    }

    protected void onOutputStreamOffsetUsChanged(long outputStreamOffsetUs) {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return this.inputFormat != null && (isSourceReady() || hasOutputBuffer() || (this.codecHotswapDeadlineMs != C.TIME_UNSET && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs));
    }

    protected float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    protected float getCodecOperatingRate() {
        return this.codecOperatingRate;
    }

    protected float getCodecOperatingRateV23(float targetPlaybackSpeed, Format format, Format[] streamFormats) {
        return -1.0f;
    }

    protected final boolean updateCodecOperatingRate() throws ExoPlaybackException {
        return updateCodecOperatingRate(this.codecInputFormat);
    }

    private boolean updateCodecOperatingRate(Format format) throws ExoPlaybackException {
        if (Util.SDK_INT < 23 || this.codec == null || this.codecDrainAction == 3 || getState() == 0) {
            return true;
        }
        float newCodecOperatingRate = getCodecOperatingRateV23(this.targetPlaybackSpeed, format, getStreamFormats());
        if (this.codecOperatingRate == newCodecOperatingRate) {
            return true;
        }
        if (newCodecOperatingRate == -1.0f) {
            drainAndReinitializeCodec();
            return false;
        }
        if (this.codecOperatingRate == -1.0f && newCodecOperatingRate <= this.assumedMinimumCodecOperatingRate) {
            return true;
        }
        Bundle codecParameters = new Bundle();
        codecParameters.putFloat("operating-rate", newCodecOperatingRate);
        this.codec.setParameters(codecParameters);
        this.codecOperatingRate = newCodecOperatingRate;
        return true;
    }

    private boolean drainAndFlushCodec() {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 1;
        }
        return true;
    }

    private boolean drainAndUpdateCodecDrmSessionV23() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 2;
        } else {
            updateDrmSessionV23();
        }
        return true;
    }

    private void drainAndReinitializeCodec() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            this.codecDrainAction = 3;
        } else {
            reinitializeCodec();
        }
    }

    private boolean drainOutputBuffer(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        boolean z;
        boolean processedOutputBuffer;
        int outputIndex;
        if (!hasOutputBuffer()) {
            if (!this.codecNeedsEosOutputExceptionWorkaround || !this.codecReceivedEos) {
                outputIndex = this.codec.dequeueOutputBufferIndex(this.outputBufferInfo);
            } else {
                try {
                    outputIndex = this.codec.dequeueOutputBufferIndex(this.outputBufferInfo);
                } catch (IllegalStateException e) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            }
            if (outputIndex < 0) {
                if (outputIndex == -2) {
                    processOutputMediaFormatChanged();
                    return true;
                }
                if (this.codecNeedsEosPropagation && (this.inputStreamEnded || this.codecDrainState == 2)) {
                    processEndOfStream();
                }
                return false;
            }
            if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                this.codec.releaseOutputBuffer(outputIndex, false);
                return true;
            }
            if (this.outputBufferInfo.size == 0 && (this.outputBufferInfo.flags & 4) != 0) {
                processEndOfStream();
                return false;
            }
            this.outputIndex = outputIndex;
            this.outputBuffer = this.codec.getOutputBuffer(outputIndex);
            if (this.outputBuffer != null) {
                this.outputBuffer.position(this.outputBufferInfo.offset);
                this.outputBuffer.limit(this.outputBufferInfo.offset + this.outputBufferInfo.size);
            }
            if (this.codecNeedsEosBufferTimestampWorkaround && this.outputBufferInfo.presentationTimeUs == 0 && (this.outputBufferInfo.flags & 4) != 0 && this.largestQueuedPresentationTimeUs != C.TIME_UNSET) {
                this.outputBufferInfo.presentationTimeUs = this.largestQueuedPresentationTimeUs;
            }
            this.isDecodeOnlyOutputBuffer = isDecodeOnlyBuffer(this.outputBufferInfo.presentationTimeUs);
            this.isLastOutputBuffer = this.lastBufferInStreamPresentationTimeUs == this.outputBufferInfo.presentationTimeUs;
            updateOutputFormatForTime(this.outputBufferInfo.presentationTimeUs);
        }
        if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
            try {
                z = false;
                try {
                    processedOutputBuffer = processOutputBuffer(positionUs, elapsedRealtimeUs, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, 1, this.outputBufferInfo.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, this.outputFormat);
                } catch (IllegalStateException e2) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return z;
                }
            } catch (IllegalStateException e3) {
                z = false;
            }
        } else {
            z = false;
            processedOutputBuffer = processOutputBuffer(positionUs, elapsedRealtimeUs, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, 1, this.outputBufferInfo.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, this.outputFormat);
        }
        if (processedOutputBuffer) {
            onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
            boolean isEndOfStream = (this.outputBufferInfo.flags & 4) != 0 ? true : z;
            resetOutputBuffer();
            if (!isEndOfStream) {
                return true;
            }
            processEndOfStream();
        }
        return z;
    }

    private void processOutputMediaFormatChanged() {
        this.codecHasOutputMediaFormat = true;
        MediaFormat mediaFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && mediaFormat.getInteger("width") == 32 && mediaFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            mediaFormat.setInteger("channel-count", 1);
        }
        this.codecOutputMediaFormat = mediaFormat;
        this.codecOutputMediaFormatChanged = true;
    }

    protected void renderToEndOfStream() throws ExoPlaybackException {
    }

    private void processEndOfStream() throws ExoPlaybackException {
        switch (this.codecDrainAction) {
            case 1:
                flushCodec();
                break;
            case 2:
                flushCodec();
                updateDrmSessionV23();
                break;
            case 3:
                reinitializeCodec();
                break;
            default:
                this.outputStreamEnded = true;
                renderToEndOfStream();
                break;
        }
    }

    protected final void setPendingOutputEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    protected final long getOutputStreamOffsetUs() {
        return this.outputStreamInfo.streamOffsetUs;
    }

    private void setOutputStreamInfo(OutputStreamInfo outputStreamInfo) {
        this.outputStreamInfo = outputStreamInfo;
        if (outputStreamInfo.streamOffsetUs != C.TIME_UNSET) {
            this.needToNotifyOutputFormatChangeAfterStreamChange = true;
            onOutputStreamOffsetUsChanged(outputStreamInfo.streamOffsetUs);
        }
    }

    protected static boolean supportsFormatDrm(Format format) {
        return format.cryptoType == 0 || format.cryptoType == 2;
    }

    private boolean drmNeedsCodecReinitialization(MediaCodecInfo codecInfo, Format newFormat, DrmSession oldSession, DrmSession newSession) throws ExoPlaybackException {
        CryptoConfig newCryptoConfig;
        CryptoConfig oldCryptoConfig;
        boolean requiresSecureDecoder;
        if (oldSession == newSession) {
            return false;
        }
        if (newSession == null || oldSession == null || (newCryptoConfig = newSession.getCryptoConfig()) == null || (oldCryptoConfig = oldSession.getCryptoConfig()) == null || !newCryptoConfig.getClass().equals(oldCryptoConfig.getClass())) {
            return true;
        }
        if (!(newCryptoConfig instanceof FrameworkCryptoConfig)) {
            return false;
        }
        FrameworkCryptoConfig newFrameworkCryptoConfig = (FrameworkCryptoConfig) newCryptoConfig;
        if (!newSession.getSchemeUuid().equals(oldSession.getSchemeUuid()) || Util.SDK_INT < 23 || C.PLAYREADY_UUID.equals(oldSession.getSchemeUuid()) || C.PLAYREADY_UUID.equals(newSession.getSchemeUuid())) {
            return true;
        }
        if (newFrameworkCryptoConfig.forceAllowInsecureDecoderComponents) {
            requiresSecureDecoder = false;
        } else {
            requiresSecureDecoder = newSession.requiresSecureDecoder(newFormat.sampleMimeType);
        }
        if (codecInfo.secure || !requiresSecureDecoder) {
            return false;
        }
        return true;
    }

    private void reinitializeCodec() throws ExoPlaybackException {
        releaseCodec();
        maybeInitCodecOrBypass();
    }

    private boolean isDecodeOnlyBuffer(long presentationTimeUs) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (this.decodeOnlyPresentationTimestamps.get(i).longValue() == presentationTimeUs) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }

    private void updateDrmSessionV23() throws ExoPlaybackException {
        CryptoConfig cryptoConfig = this.sourceDrmSession.getCryptoConfig();
        if (cryptoConfig instanceof FrameworkCryptoConfig) {
            try {
                this.mediaCrypto.setMediaDrmSession(((FrameworkCryptoConfig) cryptoConfig).sessionId);
            } catch (MediaCryptoException e) {
                throw createRendererException(e, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
            }
        }
        setCodecDrmSession(this.sourceDrmSession);
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
    }

    private boolean bypassRender(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        boolean z;
        boolean z2;
        Assertions.checkState(!this.outputStreamEnded);
        if (this.bypassBatchBuffer.hasSamples()) {
            z = false;
            if (!processOutputBuffer(positionUs, elapsedRealtimeUs, null, this.bypassBatchBuffer.data, this.outputIndex, 0, this.bypassBatchBuffer.getSampleCount(), this.bypassBatchBuffer.getFirstSampleTimeUs(), this.bypassBatchBuffer.isDecodeOnly(), this.bypassBatchBuffer.isEndOfStream(), this.outputFormat)) {
                return false;
            }
            onProcessedOutputBuffer(this.bypassBatchBuffer.getLastSampleTimeUs());
            this.bypassBatchBuffer.clear();
        } else {
            z = false;
        }
        if (this.inputStreamEnded) {
            this.outputStreamEnded = true;
            return z;
        }
        if (this.bypassSampleBufferPending) {
            Assertions.checkState(this.bypassBatchBuffer.append(this.bypassSampleBuffer));
            this.bypassSampleBufferPending = z;
        }
        if (!this.bypassDrainAndReinitialize) {
            z2 = true;
        } else {
            if (this.bypassBatchBuffer.hasSamples()) {
                return true;
            }
            z2 = true;
            disableBypass();
            this.bypassDrainAndReinitialize = z;
            maybeInitCodecOrBypass();
            if (!this.bypassEnabled) {
                return z;
            }
        }
        bypassRead();
        if (this.bypassBatchBuffer.hasSamples()) {
            this.bypassBatchBuffer.flip();
        }
        return (this.bypassBatchBuffer.hasSamples() || this.inputStreamEnded || this.bypassDrainAndReinitialize) ? z2 : z;
    }

    /* JADX WARN: Switch 'out' block B:3:0x0010 for B:4:0x001c already processed. Defaulting to fallback option. */
    private void bypassRead() throws ExoPlaybackException {
        Assertions.checkState(!this.inputStreamEnded);
        FormatHolder formatHolder = getFormatHolder();
        this.bypassSampleBuffer.clear();
        do {
            this.bypassSampleBuffer.clear();
            int result = readSource(formatHolder, this.bypassSampleBuffer, 0);
            switch (result) {
                case -5:
                    onInputFormatChanged(formatHolder);
                    return;
                case -4:
                    if (this.bypassSampleBuffer.isEndOfStream()) {
                        this.inputStreamEnded = true;
                        return;
                    }
                    if (this.waitingForFirstSampleInFormat) {
                        this.outputFormat = (Format) Assertions.checkNotNull(this.inputFormat);
                        onOutputFormatChanged(this.outputFormat, null);
                        this.waitingForFirstSampleInFormat = false;
                    }
                    this.bypassSampleBuffer.flip();
                    if (this.inputFormat != null && this.inputFormat.sampleMimeType != null && this.inputFormat.sampleMimeType.equals(MimeTypes.AUDIO_OPUS)) {
                        this.oggOpusAudioPacketizer.packetize(this.bypassSampleBuffer, this.inputFormat.initializationData);
                    }
                    break;
                    break;
                case -3:
                    return;
                default:
                    throw new IllegalStateException();
            }
        } while (this.bypassBatchBuffer.append(this.bypassSampleBuffer));
        this.bypassSampleBufferPending = true;
    }

    private static boolean isMediaCodecException(IllegalStateException error) {
        if (Util.SDK_INT >= 21 && isMediaCodecExceptionV21(error)) {
            return true;
        }
        StackTraceElement[] stackTrace = error.getStackTrace();
        return stackTrace.length > 0 && stackTrace[0].getClassName().equals("android.media.MediaCodec");
    }

    private static boolean isMediaCodecExceptionV21(IllegalStateException error) {
        return error instanceof MediaCodec.CodecException;
    }

    private static boolean isRecoverableMediaCodecExceptionV21(IllegalStateException error) {
        if (error instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) error).isRecoverable();
        }
        return false;
    }

    private static boolean codecNeedsFlushWorkaround(String name) {
        return Util.SDK_INT < 18 || (Util.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(name) || "OMX.SEC.avc.dec.secure".equals(name))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(name) || "OMX.Exynos.avc.dec.secure".equals(name)));
    }

    private int codecAdaptationWorkaroundMode(String name) {
        if (Util.SDK_INT <= 25 && "OMX.Exynos.avc.dec.secure".equals(name) && (Util.MODEL.startsWith("SM-T585") || Util.MODEL.startsWith("SM-A510") || Util.MODEL.startsWith("SM-A520") || Util.MODEL.startsWith("SM-J700"))) {
            return 2;
        }
        if (Util.SDK_INT < 24) {
            if ("OMX.Nvidia.h264.decode".equals(name) || "OMX.Nvidia.h264.decode.secure".equals(name)) {
                if ("flounder".equals(Util.DEVICE) || "flounder_lte".equals(Util.DEVICE) || "grouper".equals(Util.DEVICE) || "tilapia".equals(Util.DEVICE)) {
                    return 1;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String name, Format format) {
        return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(name);
    }

    private static boolean codecNeedsSosFlushWorkaround(String name) {
        return Util.SDK_INT == 29 && "c2.android.aac.decoder".equals(name);
    }

    private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo codecInfo) {
        String name = codecInfo.name;
        return (Util.SDK_INT <= 25 && "OMX.rk.video_decoder.avc".equals(name)) || (Util.SDK_INT <= 17 && "OMX.allwinner.video.decoder.avc".equals(name)) || ((Util.SDK_INT <= 29 && ("OMX.broadcom.video_decoder.tunnel".equals(name) || "OMX.broadcom.video_decoder.tunnel.secure".equals(name) || "OMX.bcm.vdec.avc.tunnel".equals(name) || "OMX.bcm.vdec.avc.tunnel.secure".equals(name) || "OMX.bcm.vdec.hevc.tunnel".equals(name) || "OMX.bcm.vdec.hevc.tunnel.secure".equals(name))) || ("Amazon".equals(Util.MANUFACTURER) && "AFTS".equals(Util.MODEL) && codecInfo.secure));
    }

    private static boolean codecNeedsEosFlushWorkaround(String name) {
        return (Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(name)) || (Util.SDK_INT <= 19 && (("hb2000".equals(Util.DEVICE) || "stvm8".equals(Util.DEVICE)) && ("OMX.amlogic.avc.decoder.awesome".equals(name) || "OMX.amlogic.avc.decoder.awesome.secure".equals(name))));
    }

    private static boolean codecNeedsEosBufferTimestampWorkaround(String codecName) {
        return Util.SDK_INT < 21 && "OMX.SEC.mp3.dec".equals(codecName) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("baffin") || Util.DEVICE.startsWith("grand") || Util.DEVICE.startsWith("fortuna") || Util.DEVICE.startsWith("gprimelte") || Util.DEVICE.startsWith("j2y18lte") || Util.DEVICE.startsWith("ms01"));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String name) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(name);
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String name, Format format) {
        return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(name);
    }

    private static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET);
        public final TimedValueQueue<Format> formatQueue = new TimedValueQueue<>();
        public final long previousStreamLastBufferTimeUs;
        public final long startPositionUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long previousStreamLastBufferTimeUs, long startPositionUs, long streamOffsetUs) {
            this.previousStreamLastBufferTimeUs = previousStreamLastBufferTimeUs;
            this.startPositionUs = startPositionUs;
            this.streamOffsetUs = streamOffsetUs;
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static void setLogSessionIdToMediaCodecFormat(MediaCodecAdapter.Configuration codecConfiguration, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                codecConfiguration.mediaFormat.setString("log-session-id", logSessionId.getStringId());
            }
        }
    }
}
