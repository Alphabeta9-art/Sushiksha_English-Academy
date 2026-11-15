package androidx.media3.common.audio;

import android.util.SparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes11.dex */
public final class ChannelMixingAudioProcessor extends BaseAudioProcessor {
    private final SparseArray<ChannelMixingMatrix> matrixByInputChannelCount = new SparseArray<>();

    public void putChannelMixingMatrix(ChannelMixingMatrix matrix) {
        int inputChannelCount = matrix.getInputChannelCount();
        this.matrixByInputChannelCount.put(inputChannelCount, matrix);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    protected AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat inputAudioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (inputAudioFormat.encoding != 2) {
            throw new AudioProcessor.UnhandledAudioFormatException(inputAudioFormat);
        }
        ChannelMixingMatrix channelMixingMatrix = this.matrixByInputChannelCount.get(inputAudioFormat.channelCount);
        if (channelMixingMatrix == null) {
            throw new AudioProcessor.UnhandledAudioFormatException("No mixing matrix for input channel count", inputAudioFormat);
        }
        if (channelMixingMatrix.isIdentity()) {
            return AudioProcessor.AudioFormat.NOT_SET;
        }
        return new AudioProcessor.AudioFormat(inputAudioFormat.sampleRate, channelMixingMatrix.getOutputChannelCount(), 2);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer inputBuffer) {
        ChannelMixingMatrix channelMixingMatrix = (ChannelMixingMatrix) Assertions.checkStateNotNull(this.matrixByInputChannelCount.get(this.inputAudioFormat.channelCount));
        int inputFramesToMix = inputBuffer.remaining() / this.inputAudioFormat.bytesPerFrame;
        ByteBuffer outputBuffer = replaceOutputBuffer(this.outputAudioFormat.bytesPerFrame * inputFramesToMix);
        int inputChannelCount = channelMixingMatrix.getInputChannelCount();
        int outputChannelCount = channelMixingMatrix.getOutputChannelCount();
        float[] outputFrame = new float[outputChannelCount];
        while (inputBuffer.hasRemaining()) {
            for (int inputChannelIndex = 0; inputChannelIndex < inputChannelCount; inputChannelIndex++) {
                short inputValue = inputBuffer.getShort();
                for (int outputChannelIndex = 0; outputChannelIndex < outputChannelCount; outputChannelIndex++) {
                    outputFrame[outputChannelIndex] = outputFrame[outputChannelIndex] + (channelMixingMatrix.getMixingCoefficient(inputChannelIndex, outputChannelIndex) * inputValue);
                }
            }
            for (int outputChannelIndex2 = 0; outputChannelIndex2 < outputChannelCount; outputChannelIndex2++) {
                short shortValue = (short) Util.constrainValue(outputFrame[outputChannelIndex2], -32768.0f, 32767.0f);
                outputBuffer.put((byte) (shortValue & 255));
                outputBuffer.put((byte) ((shortValue >> 8) & 255));
                outputFrame[outputChannelIndex2] = 0.0f;
            }
        }
        outputBuffer.flip();
    }
}
