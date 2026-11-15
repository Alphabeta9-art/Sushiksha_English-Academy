package com.linkedin.android.litr;

import android.media.MediaFormat;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.Renderer;

/* JADX INFO: loaded from: classes13.dex */
public class TrackTransform {
    private final Decoder decoder;
    private final Encoder encoder;
    private final MediaSource mediaSource;
    private final MediaTarget mediaTarget;
    private final Renderer renderer;
    private final int sourceTrack;
    private final MediaFormat targetFormat;
    private final int targetTrack;

    private TrackTransform(MediaSource mediaSource, Decoder decoder, Renderer renderer, Encoder encoder, MediaTarget mediaTarget, MediaFormat targetFormat, int sourceTrack, int targetTrack) {
        this.mediaSource = mediaSource;
        this.decoder = decoder;
        this.renderer = renderer;
        this.encoder = encoder;
        this.mediaTarget = mediaTarget;
        this.targetFormat = targetFormat;
        this.sourceTrack = sourceTrack;
        this.targetTrack = targetTrack;
    }

    public MediaSource getMediaSource() {
        return this.mediaSource;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public Encoder getEncoder() {
        return this.encoder;
    }

    public MediaTarget getMediaTarget() {
        return this.mediaTarget;
    }

    public MediaFormat getTargetFormat() {
        return this.targetFormat;
    }

    public int getSourceTrack() {
        return this.sourceTrack;
    }

    public int getTargetTrack() {
        return this.targetTrack;
    }

    public static class Builder {
        private Decoder decoder;
        private Encoder encoder;
        private final MediaSource mediaSource;
        private final MediaTarget mediaTarget;
        private Renderer renderer;
        private final int sourceTrack;
        private MediaFormat targetFormat;
        private int targetTrack;

        public Builder(MediaSource mediaSource, int sourceTrack, MediaTarget mediaTarget) {
            this.mediaSource = mediaSource;
            this.sourceTrack = sourceTrack;
            this.mediaTarget = mediaTarget;
            this.targetTrack = sourceTrack;
        }

        public Builder setDecoder(Decoder decoder) {
            this.decoder = decoder;
            return this;
        }

        public Builder setRenderer(Renderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public Builder setEncoder(Encoder encoder) {
            this.encoder = encoder;
            return this;
        }

        public Builder setTargetFormat(MediaFormat targetFormat) {
            this.targetFormat = targetFormat;
            return this;
        }

        public Builder setTargetTrack(int targetTrack) {
            this.targetTrack = targetTrack;
            return this;
        }

        public TrackTransform build() {
            return new TrackTransform(this.mediaSource, this.decoder, this.renderer, this.encoder, this.mediaTarget, this.targetFormat, this.sourceTrack, this.targetTrack);
        }
    }
}
