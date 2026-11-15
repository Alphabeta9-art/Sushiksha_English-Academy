package com.linkedin.android.litr;

import com.linkedin.android.litr.filter.GlFilter;
import com.linkedin.android.litr.io.MediaRange;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class TransformationOptions {
    public final int granularity;
    public final MediaRange sourceMediaRange;
    public final List<GlFilter> videoFilters;

    private TransformationOptions(int granularity, List<GlFilter> videoFilters, MediaRange sourceMediaRange) {
        this.granularity = granularity;
        this.videoFilters = videoFilters;
        this.sourceMediaRange = sourceMediaRange == null ? new MediaRange(0L, Long.MAX_VALUE) : sourceMediaRange;
    }

    public static class Builder {
        private int granularity = 100;
        private MediaRange sourceMediaRange;
        private List<GlFilter> videoFilters;

        public Builder setGranularity(int granularity) {
            this.granularity = granularity;
            return this;
        }

        public Builder setVideoFilters(List<GlFilter> videoFilters) {
            this.videoFilters = videoFilters;
            return this;
        }

        public Builder setSourceMediaRange(MediaRange sourceMediaRange) {
            this.sourceMediaRange = sourceMediaRange;
            return this;
        }

        public TransformationOptions build() {
            return new TransformationOptions(this.granularity, this.videoFilters, this.sourceMediaRange);
        }
    }
}
