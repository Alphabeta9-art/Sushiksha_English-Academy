package com.linkedin.android.litr.render;

import android.media.MediaFormat;
import android.view.Surface;
import com.linkedin.android.litr.codec.Frame;

/* JADX INFO: loaded from: classes13.dex */
public interface Renderer {
    Surface getInputSurface();

    boolean hasFilters();

    void init(Surface outputSurface, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat);

    void onMediaFormatChanged(MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat);

    void release();

    void renderFrame(Frame inputFrame, long presentationTimeNs);
}
