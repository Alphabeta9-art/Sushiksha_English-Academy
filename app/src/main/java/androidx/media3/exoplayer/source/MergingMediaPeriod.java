package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaPeriod[] periods;
    private TrackGroupArray trackGroups;
    private final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    private MediaPeriod[] enabledPeriods = new MediaPeriod[0];

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, long[] periodTimeOffsetsUs, MediaPeriod... periods) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.periods = periods;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        for (int i = 0; i < periods.length; i++) {
            if (periodTimeOffsetsUs[i] != 0) {
                this.periods[i] = new TimeOffsetMediaPeriod(periods[i], periodTimeOffsetsUs[i]);
            }
        }
    }

    public MediaPeriod getChildPeriod(int index) {
        if (!(this.periods[index] instanceof TimeOffsetMediaPeriod)) {
            return this.periods[index];
        }
        return ((TimeOffsetMediaPeriod) this.periods[index]).mediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long positionUs) {
        this.callback = callback;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod period : this.periods) {
            period.prepare(this, positionUs);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod period : this.periods) {
            period.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] selections, boolean[] mayRetainStreamFlags, SampleStream[] streams, boolean[] streamResetFlags, long positionUs) {
        int[] selectionChildIndices;
        ExoTrackSelection[] exoTrackSelectionArr = selections;
        int[] streamChildIndices = new int[exoTrackSelectionArr.length];
        int[] selectionChildIndices2 = new int[exoTrackSelectionArr.length];
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            Integer streamChildIndex = streams[i] == null ? null : this.streamPeriodIndices.get(streams[i]);
            streamChildIndices[i] = streamChildIndex == null ? -1 : streamChildIndex.intValue();
            if (exoTrackSelectionArr[i] != null) {
                TrackGroup mergedTrackGroup = exoTrackSelectionArr[i].getTrackGroup();
                selectionChildIndices2[i] = Integer.parseInt(mergedTrackGroup.id.substring(0, mergedTrackGroup.id.indexOf(":")));
            } else {
                selectionChildIndices2[i] = -1;
            }
        }
        this.streamPeriodIndices.clear();
        SampleStream[] newStreams = new SampleStream[exoTrackSelectionArr.length];
        SampleStream[] childStreams = new SampleStream[exoTrackSelectionArr.length];
        ExoTrackSelection[] childSelections = new ExoTrackSelection[exoTrackSelectionArr.length];
        ArrayList<MediaPeriod> enabledPeriodsList = new ArrayList<>(this.periods.length);
        int i2 = 0;
        long positionUs2 = positionUs;
        while (i2 < this.periods.length) {
            int j = 0;
            while (j < exoTrackSelectionArr.length) {
                childStreams[j] = streamChildIndices[j] == i2 ? streams[j] : null;
                if (selectionChildIndices2[j] == i2) {
                    ExoTrackSelection mergedTrackSelection = (ExoTrackSelection) Assertions.checkNotNull(exoTrackSelectionArr[j]);
                    TrackGroup childTrackGroup = (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(mergedTrackSelection.getTrackGroup()));
                    childSelections[j] = new ForwardingTrackSelection(mergedTrackSelection, childTrackGroup);
                } else {
                    childSelections[j] = null;
                }
                j++;
                streamChildIndices = streamChildIndices;
            }
            int[] streamChildIndices2 = streamChildIndices;
            int i3 = i2;
            long selectPositionUs = this.periods[i2].selectTracks(childSelections, mayRetainStreamFlags, childStreams, streamResetFlags, positionUs2);
            if (i3 == 0) {
                positionUs2 = selectPositionUs;
            } else if (selectPositionUs != positionUs2) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean periodEnabled = false;
            int j2 = 0;
            while (j2 < exoTrackSelectionArr.length) {
                if (selectionChildIndices2[j2] == i3) {
                    SampleStream childStream = (SampleStream) Assertions.checkNotNull(childStreams[j2]);
                    newStreams[j2] = childStreams[j2];
                    periodEnabled = true;
                    selectionChildIndices = selectionChildIndices2;
                    this.streamPeriodIndices.put(childStream, Integer.valueOf(i3));
                } else {
                    selectionChildIndices = selectionChildIndices2;
                    if (streamChildIndices2[j2] == i3) {
                        Assertions.checkState(childStreams[j2] == null);
                    }
                }
                j2++;
                exoTrackSelectionArr = selections;
                selectionChildIndices2 = selectionChildIndices;
            }
            int[] selectionChildIndices3 = selectionChildIndices2;
            if (periodEnabled) {
                enabledPeriodsList.add(this.periods[i3]);
            }
            i2 = i3 + 1;
            exoTrackSelectionArr = selections;
            streamChildIndices = streamChildIndices2;
            selectionChildIndices2 = selectionChildIndices3;
        }
        System.arraycopy(newStreams, 0, streams, 0, newStreams.length);
        this.enabledPeriods = (MediaPeriod[]) enabledPeriodsList.toArray(new MediaPeriod[0]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.enabledPeriods);
        return positionUs2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long positionUs, boolean toKeyframe) {
        for (MediaPeriod period : this.enabledPeriods) {
            period.discardBuffer(positionUs, toKeyframe);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long positionUs) {
        this.compositeSequenceableLoader.reevaluateBuffer(positionUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(long positionUs) {
        if (!this.childrenPendingPreparation.isEmpty()) {
            int childrenPendingPreparationSize = this.childrenPendingPreparation.size();
            for (int i = 0; i < childrenPendingPreparationSize; i++) {
                this.childrenPendingPreparation.get(i).continueLoading(positionUs);
            }
            return false;
        }
        return this.compositeSequenceableLoader.continueLoading(positionUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long discontinuityUs = C.TIME_UNSET;
        for (MediaPeriod period : this.enabledPeriods) {
            long otherDiscontinuityUs = period.readDiscontinuity();
            if (otherDiscontinuityUs == C.TIME_UNSET) {
                if (discontinuityUs != C.TIME_UNSET && period.seekToUs(discontinuityUs) != discontinuityUs) {
                    throw new IllegalStateException("Unexpected child seekToUs result.");
                }
            } else if (discontinuityUs == C.TIME_UNSET) {
                discontinuityUs = otherDiscontinuityUs;
                for (MediaPeriod previousPeriod : this.enabledPeriods) {
                    if (previousPeriod == period) {
                        break;
                    }
                    if (previousPeriod.seekToUs(discontinuityUs) != discontinuityUs) {
                        throw new IllegalStateException("Unexpected child seekToUs result.");
                    }
                }
            } else if (otherDiscontinuityUs != discontinuityUs) {
                throw new IllegalStateException("Conflicting discontinuities.");
            }
        }
        return discontinuityUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long positionUs) {
        long positionUs2 = this.enabledPeriods[0].seekToUs(positionUs);
        for (int i = 1; i < this.enabledPeriods.length; i++) {
            if (this.enabledPeriods[i].seekToUs(positionUs2) != positionUs2) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return positionUs2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
        MediaPeriod queryPeriod = this.enabledPeriods.length > 0 ? this.enabledPeriods[0] : this.periods[0];
        return queryPeriod.getAdjustedSeekPositionUs(positionUs, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod preparedPeriod) {
        this.childrenPendingPreparation.remove(preparedPeriod);
        if (!this.childrenPendingPreparation.isEmpty()) {
            return;
        }
        int totalTrackGroupCount = 0;
        for (MediaPeriod period : this.periods) {
            totalTrackGroupCount += period.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArray = new TrackGroup[totalTrackGroupCount];
        int trackGroupIndex = 0;
        for (int i = 0; i < this.periods.length; i++) {
            TrackGroupArray periodTrackGroups = this.periods[i].getTrackGroups();
            int periodTrackGroupCount = periodTrackGroups.length;
            int j = 0;
            while (j < periodTrackGroupCount) {
                TrackGroup childTrackGroup = periodTrackGroups.get(j);
                TrackGroup mergedTrackGroup = childTrackGroup.copyWithId(i + ":" + childTrackGroup.id);
                this.childTrackGroupByMergedTrackGroup.put(mergedTrackGroup, childTrackGroup);
                trackGroupArray[trackGroupIndex] = mergedTrackGroup;
                j++;
                trackGroupIndex++;
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArray);
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod ignored) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    private static final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
        private MediaPeriod.Callback callback;
        private final MediaPeriod mediaPeriod;
        private final long timeOffsetUs;

        public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod, long timeOffsetUs) {
            this.mediaPeriod = mediaPeriod;
            this.timeOffsetUs = timeOffsetUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void prepare(MediaPeriod.Callback callback, long positionUs) {
            this.callback = callback;
            this.mediaPeriod.prepare(this, positionUs - this.timeOffsetUs);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void maybeThrowPrepareError() throws IOException {
            this.mediaPeriod.maybeThrowPrepareError();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public TrackGroupArray getTrackGroups() {
            return this.mediaPeriod.getTrackGroups();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> trackSelections) {
            return this.mediaPeriod.getStreamKeys(trackSelections);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long selectTracks(ExoTrackSelection[] selections, boolean[] mayRetainStreamFlags, SampleStream[] streams, boolean[] streamResetFlags, long positionUs) {
            SampleStream[] childStreams = new SampleStream[streams.length];
            int i = 0;
            while (true) {
                SampleStream childStream = null;
                if (i >= streams.length) {
                    break;
                }
                TimeOffsetSampleStream sampleStream = (TimeOffsetSampleStream) streams[i];
                if (sampleStream != null) {
                    childStream = sampleStream.getChildStream();
                }
                childStreams[i] = childStream;
                i++;
            }
            long startPositionUs = this.mediaPeriod.selectTracks(selections, mayRetainStreamFlags, childStreams, streamResetFlags, positionUs - this.timeOffsetUs);
            for (int i2 = 0; i2 < streams.length; i2++) {
                SampleStream childStream2 = childStreams[i2];
                if (childStream2 == null) {
                    streams[i2] = null;
                } else if (streams[i2] == null || ((TimeOffsetSampleStream) streams[i2]).getChildStream() != childStream2) {
                    streams[i2] = new TimeOffsetSampleStream(childStream2, this.timeOffsetUs);
                }
            }
            return this.timeOffsetUs + startPositionUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void discardBuffer(long positionUs, boolean toKeyframe) {
            this.mediaPeriod.discardBuffer(positionUs - this.timeOffsetUs, toKeyframe);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long readDiscontinuity() {
            long discontinuityPositionUs = this.mediaPeriod.readDiscontinuity();
            if (discontinuityPositionUs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            return this.timeOffsetUs + discontinuityPositionUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long seekToUs(long positionUs) {
            return this.mediaPeriod.seekToUs(positionUs - this.timeOffsetUs) + this.timeOffsetUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
            return this.mediaPeriod.getAdjustedSeekPositionUs(positionUs - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
            if (bufferedPositionUs == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return this.timeOffsetUs + bufferedPositionUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return this.timeOffsetUs + nextLoadPositionUs;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(long positionUs) {
            return this.mediaPeriod.continueLoading(positionUs - this.timeOffsetUs);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return this.mediaPeriod.isLoading();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long positionUs) {
            this.mediaPeriod.reevaluateBuffer(positionUs - this.timeOffsetUs);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod source) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        }
    }

    private static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream, long timeOffsetUs) {
            this.sampleStream = sampleStream;
            this.timeOffsetUs = timeOffsetUs;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.sampleStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer buffer, int readFlags) {
            int readResult = this.sampleStream.readData(formatHolder, buffer, readFlags);
            if (readResult == -4) {
                buffer.timeUs = Math.max(0L, buffer.timeUs + this.timeOffsetUs);
            }
            return readResult;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long positionUs) {
            return this.sampleStream.skipData(positionUs - this.timeOffsetUs);
        }
    }

    private static final class ForwardingTrackSelection implements ExoTrackSelection {
        private final TrackGroup trackGroup;
        private final ExoTrackSelection trackSelection;

        public ForwardingTrackSelection(ExoTrackSelection trackSelection, TrackGroup trackGroup) {
            this.trackSelection = trackSelection;
            this.trackGroup = trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getType() {
            return this.trackSelection.getType();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int length() {
            return this.trackSelection.length();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public Format getFormat(int index) {
            return this.trackSelection.getFormat(index);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getIndexInTrackGroup(int index) {
            return this.trackSelection.getIndexInTrackGroup(index);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(Format format) {
            return this.trackSelection.indexOf(format);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(int indexInTrackGroup) {
            return this.trackSelection.indexOf(indexInTrackGroup);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void enable() {
            this.trackSelection.enable();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void disable() {
            this.trackSelection.disable();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Format getSelectedFormat() {
            return this.trackSelection.getSelectedFormat();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndexInTrackGroup() {
            return this.trackSelection.getSelectedIndexInTrackGroup();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.trackSelection.getSelectedIndex();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return this.trackSelection.getSelectionReason();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return this.trackSelection.getSelectionData();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlaybackSpeed(float playbackSpeed) {
            this.trackSelection.onPlaybackSpeed(playbackSpeed);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onDiscontinuity() {
            this.trackSelection.onDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onRebuffer() {
            this.trackSelection.onRebuffer();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlayWhenReadyChanged(boolean playWhenReady) {
            this.trackSelection.onPlayWhenReadyChanged(playWhenReady);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs, List<? extends MediaChunk> queue, MediaChunkIterator[] mediaChunkIterators) {
            this.trackSelection.updateSelectedTrack(playbackPositionUs, bufferedDurationUs, availableDurationUs, queue, mediaChunkIterators);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int evaluateQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
            return this.trackSelection.evaluateQueueSize(playbackPositionUs, queue);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean shouldCancelChunkLoad(long playbackPositionUs, Chunk loadingChunk, List<? extends MediaChunk> queue) {
            return this.trackSelection.shouldCancelChunkLoad(playbackPositionUs, loadingChunk, queue);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean excludeTrack(int index, long exclusionDurationMs) {
            return this.trackSelection.excludeTrack(index, exclusionDurationMs);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean isTrackExcluded(int index, long nowMs) {
            return this.trackSelection.isTrackExcluded(index, nowMs);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public long getLatestBitrateEstimate() {
            return this.trackSelection.getLatestBitrateEstimate();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ForwardingTrackSelection)) {
                return false;
            }
            ForwardingTrackSelection that = (ForwardingTrackSelection) o;
            return this.trackSelection.equals(that.trackSelection) && this.trackGroup.equals(that.trackGroup);
        }

        public int hashCode() {
            int result = (17 * 31) + this.trackGroup.hashCode();
            return (result * 31) + this.trackSelection.hashCode();
        }
    }
}
