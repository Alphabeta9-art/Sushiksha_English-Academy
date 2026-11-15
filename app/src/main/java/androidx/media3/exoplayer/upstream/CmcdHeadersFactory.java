package androidx.media3.exoplayer.upstream;

import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.collect.ImmutableMap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes11.dex */
public final class CmcdHeadersFactory {
    public static final String OBJECT_TYPE_AUDIO_ONLY = "a";
    public static final String OBJECT_TYPE_INIT_SEGMENT = "i";
    public static final String OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO = "av";
    public static final String OBJECT_TYPE_VIDEO_ONLY = "v";
    public static final String STREAMING_FORMAT_DASH = "d";
    public static final String STREAMING_FORMAT_HLS = "h";
    public static final String STREAMING_FORMAT_SS = "s";
    public static final String STREAM_TYPE_LIVE = "l";
    public static final String STREAM_TYPE_VOD = "v";
    private final long bufferedDurationUs;
    private long chunkDurationUs;
    private final CmcdConfiguration cmcdConfiguration;
    private final boolean isLive;
    private String objectType;
    private final String streamingFormat;
    private final ExoTrackSelection trackSelection;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamingFormat {
    }

    public static String getObjectType(ExoTrackSelection trackSelection) {
        Assertions.checkArgument(trackSelection != null);
        int trackType = MimeTypes.getTrackType(trackSelection.getSelectedFormat().sampleMimeType);
        if (trackType == -1) {
            trackType = MimeTypes.getTrackType(trackSelection.getSelectedFormat().containerMimeType);
        }
        if (trackType == 1) {
            return OBJECT_TYPE_AUDIO_ONLY;
        }
        if (trackType == 2) {
            return "v";
        }
        return null;
    }

    public CmcdHeadersFactory(CmcdConfiguration cmcdConfiguration, ExoTrackSelection trackSelection, long bufferedDurationUs, String streamingFormat, boolean isLive) {
        Assertions.checkArgument(bufferedDurationUs >= 0);
        this.cmcdConfiguration = cmcdConfiguration;
        this.trackSelection = trackSelection;
        this.bufferedDurationUs = bufferedDurationUs;
        this.streamingFormat = streamingFormat;
        this.isLive = isLive;
        this.chunkDurationUs = C.TIME_UNSET;
    }

    public CmcdHeadersFactory setChunkDurationUs(long chunkDurationUs) {
        Assertions.checkArgument(chunkDurationUs >= 0);
        this.chunkDurationUs = chunkDurationUs;
        return this;
    }

    public CmcdHeadersFactory setObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public ImmutableMap<String, String> createHttpRequestHeaders() {
        ImmutableMap<String, String> customData = this.cmcdConfiguration.requestConfig.getCustomData();
        int bitrateKbps = Util.ceilDivide(this.trackSelection.getSelectedFormat().bitrate, 1000);
        CmcdObject.Builder cmcdObject = new CmcdObject.Builder().setCustomData(customData.get(CmcdConfiguration.KEY_CMCD_OBJECT));
        if (!getIsInitSegment()) {
            if (this.cmcdConfiguration.isBitrateLoggingAllowed()) {
                cmcdObject.setBitrateKbps(bitrateKbps);
            }
            if (this.cmcdConfiguration.isTopBitrateLoggingAllowed()) {
                TrackGroup trackGroup = this.trackSelection.getTrackGroup();
                int topBitrate = this.trackSelection.getSelectedFormat().bitrate;
                for (int i = 0; i < trackGroup.length; i++) {
                    topBitrate = Math.max(topBitrate, trackGroup.getFormat(i).bitrate);
                }
                cmcdObject.setTopBitrateKbps(Util.ceilDivide(topBitrate, 1000));
            }
            if (this.cmcdConfiguration.isObjectDurationLoggingAllowed() && this.chunkDurationUs != C.TIME_UNSET) {
                cmcdObject.setObjectDurationMs(this.chunkDurationUs / 1000);
            }
        }
        if (this.cmcdConfiguration.isObjectTypeLoggingAllowed()) {
            cmcdObject.setObjectType(this.objectType);
        }
        CmcdRequest.Builder cmcdRequest = new CmcdRequest.Builder().setCustomData(customData.get(CmcdConfiguration.KEY_CMCD_REQUEST));
        if (!getIsInitSegment() && this.cmcdConfiguration.isBufferLengthLoggingAllowed()) {
            cmcdRequest.setBufferLengthMs(this.bufferedDurationUs / 1000);
        }
        if (this.cmcdConfiguration.isMeasuredThroughputLoggingAllowed() && this.trackSelection.getLatestBitrateEstimate() != Long.MIN_VALUE) {
            cmcdRequest.setMeasuredThroughputInKbps(Util.ceilDivide(this.trackSelection.getLatestBitrateEstimate(), 1000L));
        }
        CmcdSession.Builder cmcdSession = new CmcdSession.Builder().setCustomData(customData.get(CmcdConfiguration.KEY_CMCD_SESSION));
        if (this.cmcdConfiguration.isContentIdLoggingAllowed()) {
            cmcdSession.setContentId(this.cmcdConfiguration.contentId);
        }
        if (this.cmcdConfiguration.isSessionIdLoggingAllowed()) {
            cmcdSession.setSessionId(this.cmcdConfiguration.sessionId);
        }
        if (this.cmcdConfiguration.isStreamingFormatLoggingAllowed()) {
            cmcdSession.setStreamingFormat(this.streamingFormat);
        }
        if (this.cmcdConfiguration.isStreamTypeLoggingAllowed()) {
            cmcdSession.setStreamType(this.isLive ? STREAM_TYPE_LIVE : "v");
        }
        CmcdStatus.Builder cmcdStatus = new CmcdStatus.Builder().setCustomData(customData.get(CmcdConfiguration.KEY_CMCD_STATUS));
        if (this.cmcdConfiguration.isMaximumRequestThroughputLoggingAllowed()) {
            cmcdStatus.setMaximumRequestedThroughputKbps(this.cmcdConfiguration.requestConfig.getRequestedMaximumThroughputKbps(bitrateKbps));
        }
        ImmutableMap.Builder<String, String> httpRequestHeaders = ImmutableMap.builder();
        cmcdObject.build().populateHttpRequestHeaders(httpRequestHeaders);
        cmcdRequest.build().populateHttpRequestHeaders(httpRequestHeaders);
        cmcdSession.build().populateHttpRequestHeaders(httpRequestHeaders);
        cmcdStatus.build().populateHttpRequestHeaders(httpRequestHeaders);
        return httpRequestHeaders.buildOrThrow();
    }

    private boolean getIsInitSegment() {
        return this.objectType != null && this.objectType.equals("i");
    }

    private static final class CmcdObject {
        public final int bitrateKbps;
        public final String customData;
        public final long objectDurationMs;
        public final String objectType;
        public final int topBitrateKbps;

        public static final class Builder {
            private String customData;
            private String objectType;
            private int bitrateKbps = C.RATE_UNSET_INT;
            private int topBitrateKbps = C.RATE_UNSET_INT;
            private long objectDurationMs = C.TIME_UNSET;

            public Builder setBitrateKbps(int bitrateKbps) {
                this.bitrateKbps = bitrateKbps;
                return this;
            }

            public Builder setTopBitrateKbps(int topBitrateKbps) {
                this.topBitrateKbps = topBitrateKbps;
                return this;
            }

            public Builder setObjectDurationMs(long objectDurationMs) {
                Assertions.checkArgument(objectDurationMs >= 0);
                this.objectDurationMs = objectDurationMs;
                return this;
            }

            public Builder setObjectType(String objectType) {
                this.objectType = objectType;
                return this;
            }

            public Builder setCustomData(String customData) {
                this.customData = customData;
                return this;
            }

            public CmcdObject build() {
                return new CmcdObject(this);
            }
        }

        private CmcdObject(Builder builder) {
            this.bitrateKbps = builder.bitrateKbps;
            this.topBitrateKbps = builder.topBitrateKbps;
            this.objectDurationMs = builder.objectDurationMs;
            this.objectType = builder.objectType;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> httpRequestHeaders) {
            StringBuilder headerValue = new StringBuilder();
            if (this.bitrateKbps != -2147483647) {
                headerValue.append(Util.formatInvariant("%s=%d,", "br", Integer.valueOf(this.bitrateKbps)));
            }
            if (this.topBitrateKbps != -2147483647) {
                headerValue.append(Util.formatInvariant("%s=%d,", "tb", Integer.valueOf(this.topBitrateKbps)));
            }
            if (this.objectDurationMs != C.TIME_UNSET) {
                headerValue.append(Util.formatInvariant("%s=%d,", "d", Long.valueOf(this.objectDurationMs)));
            }
            if (!TextUtils.isEmpty(this.objectType)) {
                headerValue.append(Util.formatInvariant("%s=%s,", CmcdConfiguration.KEY_OBJECT_TYPE, this.objectType));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                headerValue.append(Util.formatInvariant("%s,", this.customData));
            }
            if (headerValue.length() == 0) {
                return;
            }
            headerValue.setLength(headerValue.length() - 1);
            httpRequestHeaders.put(CmcdConfiguration.KEY_CMCD_OBJECT, headerValue.toString());
        }
    }

    private static final class CmcdRequest {
        public final long bufferLengthMs;
        public final String customData;
        public final long measuredThroughputInKbps;

        public static final class Builder {
            private String customData;
            private long bufferLengthMs = C.TIME_UNSET;
            private long measuredThroughputInKbps = Long.MIN_VALUE;

            public Builder setBufferLengthMs(long bufferLengthMs) {
                Assertions.checkArgument(bufferLengthMs >= 0);
                this.bufferLengthMs = ((50 + bufferLengthMs) / 100) * 100;
                return this;
            }

            public Builder setMeasuredThroughputInKbps(long measuredThroughputInKbps) {
                Assertions.checkArgument(measuredThroughputInKbps >= 0);
                this.measuredThroughputInKbps = ((50 + measuredThroughputInKbps) / 100) * 100;
                return this;
            }

            public Builder setCustomData(String customData) {
                this.customData = customData;
                return this;
            }

            public CmcdRequest build() {
                return new CmcdRequest(this);
            }
        }

        private CmcdRequest(Builder builder) {
            this.bufferLengthMs = builder.bufferLengthMs;
            this.measuredThroughputInKbps = builder.measuredThroughputInKbps;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> httpRequestHeaders) {
            StringBuilder headerValue = new StringBuilder();
            if (this.bufferLengthMs != C.TIME_UNSET) {
                headerValue.append(Util.formatInvariant("%s=%d,", CmcdConfiguration.KEY_BUFFER_LENGTH, Long.valueOf(this.bufferLengthMs)));
            }
            if (this.measuredThroughputInKbps != Long.MIN_VALUE) {
                headerValue.append(Util.formatInvariant("%s=%d,", CmcdConfiguration.KEY_MEASURED_THROUGHPUT, Long.valueOf(this.measuredThroughputInKbps)));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                headerValue.append(Util.formatInvariant("%s,", this.customData));
            }
            if (headerValue.length() == 0) {
                return;
            }
            headerValue.setLength(headerValue.length() - 1);
            httpRequestHeaders.put(CmcdConfiguration.KEY_CMCD_REQUEST, headerValue.toString());
        }
    }

    private static final class CmcdSession {
        public static final int VERSION = 1;
        public final String contentId;
        public final String customData;
        public final String sessionId;
        public final String streamType;
        public final String streamingFormat;

        public static final class Builder {
            private String contentId;
            private String customData;
            private String sessionId;
            private String streamType;
            private String streamingFormat;

            public Builder setContentId(String contentId) {
                Assertions.checkArgument(contentId == null || contentId.length() <= 64);
                this.contentId = contentId;
                return this;
            }

            public Builder setSessionId(String sessionId) {
                Assertions.checkArgument(sessionId == null || sessionId.length() <= 64);
                this.sessionId = sessionId;
                return this;
            }

            public Builder setStreamingFormat(String streamingFormat) {
                this.streamingFormat = streamingFormat;
                return this;
            }

            public Builder setStreamType(String streamType) {
                this.streamType = streamType;
                return this;
            }

            public Builder setCustomData(String customData) {
                this.customData = customData;
                return this;
            }

            public CmcdSession build() {
                return new CmcdSession(this);
            }
        }

        private CmcdSession(Builder builder) {
            this.contentId = builder.contentId;
            this.sessionId = builder.sessionId;
            this.streamingFormat = builder.streamingFormat;
            this.streamType = builder.streamType;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> httpRequestHeaders) {
            StringBuilder headerValue = new StringBuilder();
            if (!TextUtils.isEmpty(this.contentId)) {
                headerValue.append(Util.formatInvariant("%s=\"%s\",", CmcdConfiguration.KEY_CONTENT_ID, this.contentId));
            }
            if (!TextUtils.isEmpty(this.sessionId)) {
                headerValue.append(Util.formatInvariant("%s=\"%s\",", CmcdConfiguration.KEY_SESSION_ID, this.sessionId));
            }
            if (!TextUtils.isEmpty(this.streamingFormat)) {
                headerValue.append(Util.formatInvariant("%s=%s,", CmcdConfiguration.KEY_STREAMING_FORMAT, this.streamingFormat));
            }
            if (!TextUtils.isEmpty(this.streamType)) {
                headerValue.append(Util.formatInvariant("%s=%s,", CmcdConfiguration.KEY_STREAM_TYPE, this.streamType));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                headerValue.append(Util.formatInvariant("%s,", this.customData));
            }
            if (headerValue.length() == 0) {
                return;
            }
            headerValue.setLength(headerValue.length() - 1);
            httpRequestHeaders.put(CmcdConfiguration.KEY_CMCD_SESSION, headerValue.toString());
        }
    }

    private static final class CmcdStatus {
        public final String customData;
        public final int maximumRequestedThroughputKbps;

        public static final class Builder {
            private String customData;
            private int maximumRequestedThroughputKbps = C.RATE_UNSET_INT;

            public Builder setMaximumRequestedThroughputKbps(int maximumRequestedThroughputKbps) {
                int i;
                Assertions.checkArgument(maximumRequestedThroughputKbps == -2147483647 || maximumRequestedThroughputKbps >= 0);
                if (maximumRequestedThroughputKbps == -2147483647) {
                    i = maximumRequestedThroughputKbps;
                } else {
                    i = ((maximumRequestedThroughputKbps + 50) / 100) * 100;
                }
                this.maximumRequestedThroughputKbps = i;
                return this;
            }

            public Builder setCustomData(String customData) {
                this.customData = customData;
                return this;
            }

            public CmcdStatus build() {
                return new CmcdStatus(this);
            }
        }

        private CmcdStatus(Builder builder) {
            this.maximumRequestedThroughputKbps = builder.maximumRequestedThroughputKbps;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> httpRequestHeaders) {
            StringBuilder headerValue = new StringBuilder();
            if (this.maximumRequestedThroughputKbps != -2147483647) {
                headerValue.append(Util.formatInvariant("%s=%d,", CmcdConfiguration.KEY_MAXIMUM_REQUESTED_BITRATE, Integer.valueOf(this.maximumRequestedThroughputKbps)));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                headerValue.append(Util.formatInvariant("%s,", this.customData));
            }
            if (headerValue.length() == 0) {
                return;
            }
            headerValue.setLength(headerValue.length() - 1);
            httpRequestHeaders.put(CmcdConfiguration.KEY_CMCD_STATUS, headerValue.toString());
        }
    }
}
