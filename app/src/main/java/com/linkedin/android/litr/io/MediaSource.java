package com.linkedin.android.litr.io;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public interface MediaSource {
    void advance();

    int getOrientationHint();

    int getSampleFlags();

    long getSampleTime();

    int getSampleTrackIndex();

    long getSize();

    int getTrackCount();

    MediaFormat getTrackFormat(int track);

    int readSampleData(ByteBuffer buffer, int offset);

    void release();

    void seekTo(long position, int mode);

    void selectTrack(int track);

    default MediaRange getSelection() {
        return new MediaRange(0L, Long.MAX_VALUE);
    }
}
