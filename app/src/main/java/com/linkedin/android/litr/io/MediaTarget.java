package com.linkedin.android.litr.io;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public interface MediaTarget {
    int addTrack(MediaFormat mediaFormat, int targetTrack);

    String getOutputFilePath();

    void release();

    void writeSampleData(int targetTrack, ByteBuffer buffer, MediaCodec.BufferInfo info);
}
