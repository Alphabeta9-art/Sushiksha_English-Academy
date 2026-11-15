package com.linkedin.android.litr.resample;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public interface AudioResampler {
    void resample(final ByteBuffer inputBuffer, final ByteBuffer outputBuffer, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat);

    default int getSampleRate(MediaFormat format) {
        return format.getInteger("sample-rate");
    }

    default int getChannels(MediaFormat format) {
        return format.getInteger("channel-count");
    }
}
