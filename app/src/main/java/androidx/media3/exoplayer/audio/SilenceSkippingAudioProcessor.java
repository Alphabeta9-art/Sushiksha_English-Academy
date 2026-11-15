package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes11.dex */
public final class SilenceSkippingAudioProcessor extends androidx.media3.common.audio.BaseAudioProcessor {
    public static final long DEFAULT_MINIMUM_SILENCE_DURATION_US = 150000;
    public static final long DEFAULT_PADDING_SILENCE_US = 20000;
    public static final short DEFAULT_SILENCE_THRESHOLD_LEVEL = 1024;
    private static final int STATE_MAYBE_SILENT = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SILENT = 2;
    private int bytesPerFrame;
    private boolean enabled;
    private boolean hasOutputNoise;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferSize;
    private final long minimumSilenceDurationUs;
    private byte[] paddingBuffer;
    private final long paddingSilenceUs;
    private int paddingSize;
    private final short silenceThresholdLevel;
    private long skippedFrames;
    private int state;

    public SilenceSkippingAudioProcessor() {
        this(DEFAULT_MINIMUM_SILENCE_DURATION_US, 20000L, DEFAULT_SILENCE_THRESHOLD_LEVEL);
    }

    public SilenceSkippingAudioProcessor(long minimumSilenceDurationUs, long paddingSilenceUs, short silenceThresholdLevel) {
        Assertions.checkArgument(paddingSilenceUs <= minimumSilenceDurationUs);
        this.minimumSilenceDurationUs = minimumSilenceDurationUs;
        this.paddingSilenceUs = paddingSilenceUs;
        this.silenceThresholdLevel = silenceThresholdLevel;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.paddingBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat inputAudioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (inputAudioFormat.encoding == 2) {
            return this.enabled ? inputAudioFormat : AudioProcessor.AudioFormat.NOT_SET;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(inputAudioFormat);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return this.enabled;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer inputBuffer) {
        while (inputBuffer.hasRemaining() && !hasPendingOutput()) {
            switch (this.state) {
                case 0:
                    processNoisy(inputBuffer);
                    break;
                case 1:
                    processMaybeSilence(inputBuffer);
                    break;
                case 2:
                    processSilence(inputBuffer);
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    protected void onQueueEndOfStream() {
        if (this.maybeSilenceBufferSize > 0) {
            output(this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
        }
        if (!this.hasOutputNoise) {
            this.skippedFrames += (long) (this.paddingSize / this.bytesPerFrame);
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    protected void onFlush() {
        if (this.enabled) {
            this.bytesPerFrame = this.inputAudioFormat.bytesPerFrame;
            int maybeSilenceBufferSize = durationUsToFrames(this.minimumSilenceDurationUs) * this.bytesPerFrame;
            if (this.maybeSilenceBuffer.length != maybeSilenceBufferSize) {
                this.maybeSilenceBuffer = new byte[maybeSilenceBufferSize];
            }
            this.paddingSize = durationUsToFrames(this.paddingSilenceUs) * this.bytesPerFrame;
            if (this.paddingBuffer.length != this.paddingSize) {
                this.paddingBuffer = new byte[this.paddingSize];
            }
        }
        this.state = 0;
        this.skippedFrames = 0L;
        this.maybeSilenceBufferSize = 0;
        this.hasOutputNoise = false;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    protected void onReset() {
        this.enabled = false;
        this.paddingSize = 0;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.paddingBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    private void processNoisy(ByteBuffer inputBuffer) {
        int limit = inputBuffer.limit();
        inputBuffer.limit(Math.min(limit, inputBuffer.position() + this.maybeSilenceBuffer.length));
        int noiseLimit = findNoiseLimit(inputBuffer);
        if (noiseLimit == inputBuffer.position()) {
            this.state = 1;
        } else {
            inputBuffer.limit(noiseLimit);
            output(inputBuffer);
        }
        inputBuffer.limit(limit);
    }

    private void processMaybeSilence(ByteBuffer inputBuffer) {
        int limit = inputBuffer.limit();
        int noisePosition = findNoisePosition(inputBuffer);
        int maybeSilenceInputSize = noisePosition - inputBuffer.position();
        int maybeSilenceBufferRemaining = this.maybeSilenceBuffer.length - this.maybeSilenceBufferSize;
        if (noisePosition < limit && maybeSilenceInputSize < maybeSilenceBufferRemaining) {
            output(this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
            this.maybeSilenceBufferSize = 0;
            this.state = 0;
            return;
        }
        int bytesToWrite = Math.min(maybeSilenceInputSize, maybeSilenceBufferRemaining);
        inputBuffer.limit(inputBuffer.position() + bytesToWrite);
        inputBuffer.get(this.maybeSilenceBuffer, this.maybeSilenceBufferSize, bytesToWrite);
        this.maybeSilenceBufferSize += bytesToWrite;
        if (this.maybeSilenceBufferSize == this.maybeSilenceBuffer.length) {
            if (this.hasOutputNoise) {
                output(this.maybeSilenceBuffer, this.paddingSize);
                this.skippedFrames += (long) ((this.maybeSilenceBufferSize - (this.paddingSize * 2)) / this.bytesPerFrame);
            } else {
                this.skippedFrames += (long) ((this.maybeSilenceBufferSize - this.paddingSize) / this.bytesPerFrame);
            }
            updatePaddingBuffer(inputBuffer, this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
            this.maybeSilenceBufferSize = 0;
            this.state = 2;
        }
        inputBuffer.limit(limit);
    }

    private void processSilence(ByteBuffer inputBuffer) {
        int limit = inputBuffer.limit();
        int noisyPosition = findNoisePosition(inputBuffer);
        inputBuffer.limit(noisyPosition);
        this.skippedFrames += (long) (inputBuffer.remaining() / this.bytesPerFrame);
        updatePaddingBuffer(inputBuffer, this.paddingBuffer, this.paddingSize);
        if (noisyPosition < limit) {
            output(this.paddingBuffer, this.paddingSize);
            this.state = 0;
            inputBuffer.limit(limit);
        }
    }

    private void output(byte[] data, int length) {
        replaceOutputBuffer(length).put(data, 0, length).flip();
        if (length > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void output(ByteBuffer data) {
        int length = data.remaining();
        replaceOutputBuffer(length).put(data).flip();
        if (length > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void updatePaddingBuffer(ByteBuffer input, byte[] buffer, int size) {
        int fromInputSize = Math.min(input.remaining(), this.paddingSize);
        int fromBufferSize = this.paddingSize - fromInputSize;
        System.arraycopy(buffer, size - fromBufferSize, this.paddingBuffer, 0, fromBufferSize);
        input.position(input.limit() - fromInputSize);
        input.get(this.paddingBuffer, fromBufferSize, fromInputSize);
    }

    private int durationUsToFrames(long durationUs) {
        return (int) ((((long) this.inputAudioFormat.sampleRate) * durationUs) / 1000000);
    }

    private int findNoisePosition(ByteBuffer buffer) {
        for (int i = buffer.position(); i < buffer.limit(); i += 2) {
            if (Math.abs((int) buffer.getShort(i)) > this.silenceThresholdLevel) {
                return this.bytesPerFrame * (i / this.bytesPerFrame);
            }
        }
        int i2 = buffer.limit();
        return i2;
    }

    private int findNoiseLimit(ByteBuffer buffer) {
        int i = buffer.limit();
        do {
            i -= 2;
            if (i < buffer.position()) {
                int i2 = buffer.position();
                return i2;
            }
        } while (Math.abs((int) buffer.getShort(i)) <= this.silenceThresholdLevel);
        return (this.bytesPerFrame * (i / this.bytesPerFrame)) + this.bytesPerFrame;
    }
}
