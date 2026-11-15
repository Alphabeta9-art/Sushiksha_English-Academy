package com.linkedin.android.litr.analytics;

import android.media.MediaFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class TransformationStatsCollector {
    private List<TrackTransformationInfo> trackTransformationInfos = new ArrayList(2);

    public List<TrackTransformationInfo> getStats() {
        return this.trackTransformationInfos;
    }

    public void addSourceTrack(MediaFormat sourceMediaFormat) {
        TrackTransformationInfo trackTransformationInfo = new TrackTransformationInfo();
        trackTransformationInfo.setSourceFormat(sourceMediaFormat);
        this.trackTransformationInfos.add(trackTransformationInfo);
    }

    public void setTrackCodecs(int track, String decoderCodec, String encoderCodec) {
        TrackTransformationInfo trackTransformationInfo = this.trackTransformationInfos.get(track);
        trackTransformationInfo.setDecoderCodec(decoderCodec);
        trackTransformationInfo.setEncoderCodec(encoderCodec);
    }

    public void setTargetFormat(int track, MediaFormat mediaFormat) {
        this.trackTransformationInfos.get(track).setTargetFormat(mediaFormat);
    }

    public void increaseTrackProcessingDuration(int track, long frameProcessingDuration) {
        TrackTransformationInfo trackTransformationInfo = this.trackTransformationInfos.get(track);
        long duration = trackTransformationInfo.getDuration();
        trackTransformationInfo.setDuration(duration + frameProcessingDuration);
    }
}
