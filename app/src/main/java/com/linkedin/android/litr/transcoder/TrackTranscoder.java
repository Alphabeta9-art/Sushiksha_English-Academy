package com.linkedin.android.litr.transcoder;

import android.media.MediaFormat;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.io.MediaRange;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.Renderer;

/* JADX INFO: loaded from: classes13.dex */
public abstract class TrackTranscoder {
    public static final int ERROR_TRANSCODER_NOT_RUNNING = -3;
    public static final int NO_SELECTED_TRACK = -1;
    public static final int RESULT_EOS_REACHED = 3;
    public static final int RESULT_FRAME_PROCESSED = 2;
    public static final int RESULT_OUTPUT_MEDIA_FORMAT_CHANGED = 1;
    public static final int UNDEFINED_VALUE = -1;
    protected final Decoder decoder;
    protected long duration;
    protected final Encoder encoder;
    protected final MediaTarget mediaMuxer;
    protected final MediaSource mediaSource;
    protected float progress;
    protected final Renderer renderer;
    protected final MediaRange sourceMediaSelection;
    protected int sourceTrack;
    protected MediaFormat targetFormat;
    protected int targetTrack;
    protected boolean targetTrackAdded;

    public abstract int processNextFrame() throws TrackTranscoderException;

    public abstract void start() throws TrackTranscoderException;

    public abstract void stop();

    TrackTranscoder(MediaSource mediaSource, int sourceTrack, MediaTarget mediaTarget, int targetTrack, MediaFormat targetFormat, Renderer renderer, Decoder decoder, Encoder encoder) {
        this.duration = -1L;
        this.mediaSource = mediaSource;
        this.sourceTrack = sourceTrack;
        this.targetTrack = targetTrack;
        this.mediaMuxer = mediaTarget;
        this.targetFormat = targetFormat;
        this.renderer = renderer;
        this.decoder = decoder;
        this.encoder = encoder;
        this.sourceMediaSelection = mediaSource.getSelection();
        MediaFormat sourceMedia = mediaSource.getTrackFormat(sourceTrack);
        if (sourceMedia.containsKey("durationUs")) {
            this.duration = sourceMedia.getLong("durationUs");
            if (targetFormat != null) {
                targetFormat.setLong("durationUs", this.duration);
            }
        }
        if (this.sourceMediaSelection.getEnd() < this.sourceMediaSelection.getStart()) {
            throw new IllegalArgumentException("Range end should be greater than range start");
        }
        this.duration = Math.min(this.duration, this.sourceMediaSelection.getEnd());
        this.duration -= this.sourceMediaSelection.getStart();
    }

    public int getSourceTrack() {
        return this.sourceTrack;
    }

    public int getTargetTrack() {
        return this.targetTrack;
    }

    public float getProgress() {
        return this.progress;
    }

    public String getEncoderName() throws TrackTranscoderException {
        return this.encoder.getName();
    }

    public String getDecoderName() throws TrackTranscoderException {
        return this.decoder.getName();
    }

    public MediaFormat getTargetMediaFormat() {
        return this.targetFormat;
    }

    protected void advanceToNextTrack() {
        while (this.mediaSource.getSampleTrackIndex() == this.sourceTrack) {
            this.mediaSource.advance();
            if ((this.mediaSource.getSampleFlags() & 4) != 0) {
                return;
            }
        }
    }
}
