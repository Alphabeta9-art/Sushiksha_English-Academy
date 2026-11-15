package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;

/* JADX INFO: loaded from: classes11.dex */
public interface MediaSource {

    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline);
    }

    void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener);

    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j);

    void disable(MediaSourceCaller mediaSourceCaller);

    void enable(MediaSourceCaller mediaSourceCaller);

    MediaItem getMediaItem();

    void maybeThrowSourceInfoRefreshError() throws IOException;

    void prepareSource(MediaSourceCaller mediaSourceCaller, TransferListener transferListener, PlayerId playerId);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource(MediaSourceCaller mediaSourceCaller);

    void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener);

    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);

    public interface Factory {
        public static final Factory UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;

        MediaSource createMediaSource(MediaItem mediaItem);

        int[] getSupportedTypes();

        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider);

        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy);

        default Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory cmcdConfigurationFactory) {
            return this;
        }
    }

    public static final class MediaPeriodId extends androidx.media3.common.MediaPeriodId {
        public MediaPeriodId(Object periodUid) {
            super(periodUid);
        }

        public MediaPeriodId(Object periodUid, long windowSequenceNumber) {
            super(periodUid, windowSequenceNumber);
        }

        public MediaPeriodId(Object periodUid, long windowSequenceNumber, int nextAdGroupIndex) {
            super(periodUid, windowSequenceNumber, nextAdGroupIndex);
        }

        public MediaPeriodId(Object periodUid, int adGroupIndex, int adIndexInAdGroup, long windowSequenceNumber) {
            super(periodUid, adGroupIndex, adIndexInAdGroup, windowSequenceNumber);
        }

        public MediaPeriodId(androidx.media3.common.MediaPeriodId mediaPeriodId) {
            super(mediaPeriodId);
        }

        @Override // androidx.media3.common.MediaPeriodId
        public MediaPeriodId copyWithPeriodUid(Object newPeriodUid) {
            return new MediaPeriodId(super.copyWithPeriodUid(newPeriodUid));
        }

        @Override // androidx.media3.common.MediaPeriodId
        public MediaPeriodId copyWithWindowSequenceNumber(long windowSequenceNumber) {
            return new MediaPeriodId(super.copyWithWindowSequenceNumber(windowSequenceNumber));
        }
    }

    default Timeline getInitialTimeline() {
        return null;
    }

    default boolean isSingleWindow() {
        return true;
    }

    @Deprecated
    default void prepareSource(MediaSourceCaller caller, TransferListener mediaTransferListener) {
        prepareSource(caller, mediaTransferListener, PlayerId.UNSET);
    }
}
