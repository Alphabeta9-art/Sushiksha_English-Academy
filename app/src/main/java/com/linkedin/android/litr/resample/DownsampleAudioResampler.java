package com.linkedin.android.litr.resample;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class DownsampleAudioResampler implements AudioResampler {
    private float ratio(int remaining, int all) {
        return remaining / all;
    }

    @Override // com.linkedin.android.litr.resample.AudioResampler
    public void resample(ByteBuffer inputBuffer, ByteBuffer outputBuffer, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
        int inputSampleRate = getSampleRate(sourceMediaFormat);
        int outputSampleRate = getSampleRate(targetMediaFormat);
        int channels = getChannels(targetMediaFormat);
        if (inputSampleRate < outputSampleRate) {
            throw new IllegalArgumentException("Illegal use of DownsampleAudioResampler");
        }
        boolean z = true;
        if (channels != 1 && channels != 2) {
            throw new IllegalArgumentException("Illegal use of DownsampleAudioResampler. Channels:" + channels);
        }
        int inputSamples = inputBuffer.remaining() / channels;
        int outputSamples = (int) Math.ceil(((double) inputSamples) * (((double) outputSampleRate) / ((double) inputSampleRate)));
        int dropSamples = inputSamples - outputSamples;
        int remainingOutputSamples = outputSamples;
        int remainingDropSamples = dropSamples;
        float remainingOutputSamplesRatio = ratio(remainingOutputSamples, outputSamples);
        float remainingDropSamplesRatio = ratio(remainingDropSamples, dropSamples);
        while (true) {
            if (remainingOutputSamples <= 0 && remainingDropSamples <= 0) {
                return;
            }
            if (remainingOutputSamplesRatio >= remainingDropSamplesRatio) {
                int i = 0;
                while (i < channels) {
                    outputBuffer.put(inputBuffer.get());
                    i++;
                    z = z;
                }
                remainingOutputSamples--;
                remainingOutputSamplesRatio = ratio(remainingOutputSamples, outputSamples);
                z = z;
            } else {
                boolean z2 = z;
                for (int i2 = 0; i2 < channels; i2++) {
                    inputBuffer.position(inputBuffer.position() + 1);
                }
                remainingDropSamples--;
                remainingDropSamplesRatio = ratio(remainingDropSamples, dropSamples);
                z = z2;
            }
        }
    }
}
