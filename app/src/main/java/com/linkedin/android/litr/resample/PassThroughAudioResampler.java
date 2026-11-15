package com.linkedin.android.litr.resample;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class PassThroughAudioResampler implements AudioResampler {
    @Override // com.linkedin.android.litr.resample.AudioResampler
    public void resample(ByteBuffer inputBuffer, ByteBuffer outputBuffer, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
        outputBuffer.put(inputBuffer);
    }
}
