package com.linkedin.android.litr.codec;

import android.media.MediaFormat;
import android.view.Surface;
import com.linkedin.android.litr.exception.TrackTranscoderException;

/* JADX INFO: loaded from: classes13.dex */
public interface Decoder {
    int dequeueInputFrame(long timeout);

    int dequeueOutputFrame(long timeout);

    Frame getInputFrame(int tag);

    String getName() throws TrackTranscoderException;

    MediaFormat getOutputFormat();

    Frame getOutputFrame(int tag);

    void init(MediaFormat mediaFormat, Surface surface) throws TrackTranscoderException;

    boolean isRunning();

    void queueInputFrame(Frame frame);

    void release();

    void releaseOutputFrame(int tag, boolean render);

    void start() throws TrackTranscoderException;

    void stop();
}
