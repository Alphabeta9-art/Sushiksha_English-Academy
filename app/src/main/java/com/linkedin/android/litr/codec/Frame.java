package com.linkedin.android.litr.codec;

import android.media.MediaCodec;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class Frame {
    public final ByteBuffer buffer;
    public final MediaCodec.BufferInfo bufferInfo;
    public final int tag;

    public Frame(int tag, ByteBuffer buffer, MediaCodec.BufferInfo bufferInfo) {
        this.tag = tag;
        this.buffer = buffer;
        if (bufferInfo == null) {
            this.bufferInfo = new MediaCodec.BufferInfo();
        } else {
            this.bufferInfo = bufferInfo;
        }
    }
}
