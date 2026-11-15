package com.linkedin.android.litr.analytics;

import android.media.MediaFormat;

/* JADX INFO: loaded from: classes13.dex */
public class TrackTransformationInfo {
    public static final long UNKNOWN_VALUE = -1;
    private String decoderCodec;
    private long duration = -1;
    private String encoderCodec;
    private MediaFormat sourceFormat;
    private MediaFormat targetFormat;

    public MediaFormat getSourceFormat() {
        return this.sourceFormat;
    }

    public MediaFormat getTargetFormat() {
        return this.targetFormat;
    }

    public String getDecoderCodec() {
        return this.decoderCodec;
    }

    public String getEncoderCodec() {
        return this.encoderCodec;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setSourceFormat(MediaFormat sourceFormat) {
        this.sourceFormat = sourceFormat;
    }

    public void setTargetFormat(MediaFormat targetFormat) {
        this.targetFormat = targetFormat;
    }

    public void setDecoderCodec(String decoderCodec) {
        this.decoderCodec = decoderCodec;
    }

    public void setEncoderCodec(String encoderCodec) {
        this.encoderCodec = encoderCodec;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
