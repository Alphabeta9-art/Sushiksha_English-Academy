package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.DataChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
class HlsChunkSource {
    public static final int CHUNK_PUBLICATION_STATE_PRELOAD = 0;
    public static final int CHUNK_PUBLICATION_STATE_PUBLISHED = 1;
    public static final int CHUNK_PUBLICATION_STATE_REMOVED = 2;
    private static final int KEY_CACHE_SIZE = 4;
    private final CmcdConfiguration cmcdConfiguration;
    private final DataSource encryptionDataSource;
    private Uri expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isPrimaryTimestampSource;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final PlayerId playerId;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private boolean seenExpectedPlaylistError;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private ExoTrackSelection trackSelection;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache(4);
    private byte[] scratchSpace = Util.EMPTY_BYTE_ARRAY;
    private long liveEdgeInPeriodTimeUs = C.TIME_UNSET;

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    public HlsChunkSource(HlsExtractorFactory extractorFactory, HlsPlaylistTracker playlistTracker, Uri[] playlistUrls, Format[] playlistFormats, HlsDataSourceFactory dataSourceFactory, TransferListener mediaTransferListener, TimestampAdjusterProvider timestampAdjusterProvider, long timestampAdjusterInitializationTimeoutMs, List<Format> muxedCaptionFormats, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
        this.extractorFactory = extractorFactory;
        this.playlistTracker = playlistTracker;
        this.playlistUrls = playlistUrls;
        this.playlistFormats = playlistFormats;
        this.timestampAdjusterProvider = timestampAdjusterProvider;
        this.timestampAdjusterInitializationTimeoutMs = timestampAdjusterInitializationTimeoutMs;
        this.muxedCaptionFormats = muxedCaptionFormats;
        this.playerId = playerId;
        this.cmcdConfiguration = cmcdConfiguration;
        this.mediaDataSource = dataSourceFactory.createDataSource(1);
        if (mediaTransferListener != null) {
            this.mediaDataSource.addTransferListener(mediaTransferListener);
        }
        this.encryptionDataSource = dataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(playlistFormats);
        ArrayList<Integer> initialTrackSelection = new ArrayList<>();
        for (int i = 0; i < playlistUrls.length; i++) {
            if ((playlistFormats[i].roleFlags & 16384) == 0) {
                initialTrackSelection.add(Integer.valueOf(i));
            }
        }
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, Ints.toArray(initialTrackSelection));
    }

    public void maybeThrowError() throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        if (this.expectedPlaylistUrl != null && this.seenExpectedPlaylistError) {
            this.playlistTracker.maybeThrowPlaylistRefreshError(this.expectedPlaylistUrl);
        }
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void setTrackSelection(ExoTrackSelection trackSelection) {
        this.trackSelection = trackSelection;
    }

    public ExoTrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsPrimaryTimestampSource(boolean isPrimaryTimestampSource) {
        this.isPrimaryTimestampSource = isPrimaryTimestampSource;
    }

    public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
        HlsMediaPlaylist mediaPlaylist;
        int selectedIndex = this.trackSelection.getSelectedIndex();
        if (selectedIndex < this.playlistUrls.length && selectedIndex != -1) {
            mediaPlaylist = this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackSelection.getSelectedIndexInTrackGroup()], true);
        } else {
            mediaPlaylist = null;
        }
        if (mediaPlaylist == null || mediaPlaylist.segments.isEmpty() || !mediaPlaylist.hasIndependentSegments) {
            return positionUs;
        }
        long startOfPlaylistInPeriodUs = mediaPlaylist.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long relativePositionUs = positionUs - startOfPlaylistInPeriodUs;
        int segmentIndex = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) mediaPlaylist.segments, Long.valueOf(relativePositionUs), true, true);
        long firstSyncUs = mediaPlaylist.segments.get(segmentIndex).relativeStartTimeUs;
        long secondSyncUs = firstSyncUs;
        if (segmentIndex != mediaPlaylist.segments.size() - 1) {
            secondSyncUs = mediaPlaylist.segments.get(segmentIndex + 1).relativeStartTimeUs;
        }
        return seekParameters.resolveSeekPositionUs(relativePositionUs, firstSyncUs, secondSyncUs) + startOfPlaylistInPeriodUs;
    }

    public int getChunkPublicationState(HlsMediaChunk mediaChunk) {
        List<HlsMediaPlaylist.Part> partsInCurrentPlaylist;
        if (mediaChunk.partIndex == -1) {
            return 1;
        }
        Uri playlistUrl = this.playlistUrls[this.trackGroup.indexOf(mediaChunk.trackFormat)];
        HlsMediaPlaylist mediaPlaylist = (HlsMediaPlaylist) Assertions.checkNotNull(this.playlistTracker.getPlaylistSnapshot(playlistUrl, false));
        int segmentIndexInPlaylist = (int) (mediaChunk.chunkIndex - mediaPlaylist.mediaSequence);
        if (segmentIndexInPlaylist < 0) {
            return 1;
        }
        if (segmentIndexInPlaylist < mediaPlaylist.segments.size()) {
            partsInCurrentPlaylist = mediaPlaylist.segments.get(segmentIndexInPlaylist).parts;
        } else {
            partsInCurrentPlaylist = mediaPlaylist.trailingParts;
        }
        if (mediaChunk.partIndex >= partsInCurrentPlaylist.size()) {
            return 2;
        }
        HlsMediaPlaylist.Part newPart = partsInCurrentPlaylist.get(mediaChunk.partIndex);
        if (newPart.isPreload) {
            return 0;
        }
        Uri newUri = Uri.parse(UriUtil.resolve(mediaPlaylist.baseUri, newPart.url));
        return Util.areEqual(newUri, mediaChunk.dataSpec.uri) ? 1 : 2;
    }

    public void getNextChunk(long playbackPositionUs, long loadPositionUs, List<HlsMediaChunk> queue, boolean allowEndOfStream, HlsChunkHolder out) {
        long bufferedDurationUs;
        long timeToLiveEdgeUs;
        long startOfPlaylistInPeriodUs;
        long chunkMediaSequence;
        HlsMediaPlaylist playlist;
        Uri selectedPlaylistUrl;
        int partIndex;
        String objectType;
        CmcdHeadersFactory objectType2;
        HlsChunkHolder hlsChunkHolder = out;
        HlsMediaChunk previous = queue.isEmpty() ? null : (HlsMediaChunk) Iterables.getLast(queue);
        int oldTrackIndex = previous == null ? -1 : this.trackGroup.indexOf(previous.trackFormat);
        long bufferedDurationUs2 = loadPositionUs - playbackPositionUs;
        long timeToLiveEdgeUs2 = resolveTimeToLiveEdgeUs(playbackPositionUs);
        if (previous != null && !this.independentSegments) {
            long subtractedDurationUs = previous.getDurationUs();
            long bufferedDurationUs3 = Math.max(0L, bufferedDurationUs2 - subtractedDurationUs);
            if (timeToLiveEdgeUs2 == C.TIME_UNSET) {
                bufferedDurationUs = bufferedDurationUs3;
                timeToLiveEdgeUs = timeToLiveEdgeUs2;
            } else {
                bufferedDurationUs = bufferedDurationUs3;
                timeToLiveEdgeUs = Math.max(0L, timeToLiveEdgeUs2 - subtractedDurationUs);
            }
        } else {
            bufferedDurationUs = bufferedDurationUs2;
            timeToLiveEdgeUs = timeToLiveEdgeUs2;
        }
        MediaChunkIterator[] mediaChunkIterators = createMediaChunkIterators(previous, loadPositionUs);
        this.trackSelection.updateSelectedTrack(playbackPositionUs, bufferedDurationUs, timeToLiveEdgeUs, queue, mediaChunkIterators);
        int selectedTrackIndex = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean switchingTrack = oldTrackIndex != selectedTrackIndex;
        Uri selectedPlaylistUrl2 = this.playlistUrls[selectedTrackIndex];
        if (!this.playlistTracker.isSnapshotValid(selectedPlaylistUrl2)) {
            hlsChunkHolder.playlistUrl = selectedPlaylistUrl2;
            this.seenExpectedPlaylistError &= selectedPlaylistUrl2.equals(this.expectedPlaylistUrl);
            this.expectedPlaylistUrl = selectedPlaylistUrl2;
            return;
        }
        HlsMediaPlaylist playlist2 = this.playlistTracker.getPlaylistSnapshot(selectedPlaylistUrl2, true);
        Assertions.checkNotNull(playlist2);
        this.independentSegments = playlist2.hasIndependentSegments;
        updateLiveEdgeTimeUs(playlist2);
        long startOfPlaylistInPeriodUs2 = playlist2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(previous, switchingTrack, playlist2, startOfPlaylistInPeriodUs2, loadPositionUs);
        boolean switchingTrack2 = switchingTrack;
        long chunkMediaSequence2 = ((Long) nextMediaSequenceAndPartIndex.first).longValue();
        int partIndex2 = ((Integer) nextMediaSequenceAndPartIndex.second).intValue();
        if (chunkMediaSequence2 < playlist2.mediaSequence && previous != null && switchingTrack2) {
            selectedTrackIndex = oldTrackIndex;
            Uri selectedPlaylistUrl3 = this.playlistUrls[selectedTrackIndex];
            HlsMediaPlaylist playlist3 = this.playlistTracker.getPlaylistSnapshot(selectedPlaylistUrl3, true);
            Assertions.checkNotNull(playlist3);
            long startOfPlaylistInPeriodUs3 = playlist3.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            Pair<Long, Integer> nextMediaSequenceAndPartIndexWithoutAdapting = getNextMediaSequenceAndPartIndex(previous, false, playlist3, startOfPlaylistInPeriodUs3, loadPositionUs);
            long chunkMediaSequence3 = ((Long) nextMediaSequenceAndPartIndexWithoutAdapting.first).longValue();
            int partIndex3 = ((Integer) nextMediaSequenceAndPartIndexWithoutAdapting.second).intValue();
            startOfPlaylistInPeriodUs = startOfPlaylistInPeriodUs3;
            chunkMediaSequence = chunkMediaSequence3;
            playlist = playlist3;
            selectedPlaylistUrl = selectedPlaylistUrl3;
            partIndex = partIndex3;
        } else {
            startOfPlaylistInPeriodUs = startOfPlaylistInPeriodUs2;
            chunkMediaSequence = chunkMediaSequence2;
            playlist = playlist2;
            selectedPlaylistUrl = selectedPlaylistUrl2;
            partIndex = partIndex2;
        }
        long startOfPlaylistInPeriodUs4 = startOfPlaylistInPeriodUs;
        if (chunkMediaSequence < playlist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        SegmentBaseHolder segmentBaseHolder = getNextSegmentHolder(playlist, chunkMediaSequence, partIndex);
        if (segmentBaseHolder == null) {
            if (!playlist.hasEndTag) {
                hlsChunkHolder.playlistUrl = selectedPlaylistUrl;
                this.seenExpectedPlaylistError &= selectedPlaylistUrl.equals(this.expectedPlaylistUrl);
                this.expectedPlaylistUrl = selectedPlaylistUrl;
                return;
            } else if (allowEndOfStream || playlist.segments.isEmpty()) {
                out.endOfStream = true;
                return;
            } else {
                segmentBaseHolder = new SegmentBaseHolder((HlsMediaPlaylist.SegmentBase) Iterables.getLast(playlist.segments), (playlist.mediaSequence + ((long) playlist.segments.size())) - 1, -1);
                hlsChunkHolder = out;
            }
        }
        this.seenExpectedPlaylistError = false;
        this.expectedPlaylistUrl = null;
        if (this.cmcdConfiguration == null) {
            objectType2 = null;
        } else {
            CmcdHeadersFactory cmcdHeadersFactory = new CmcdHeadersFactory(this.cmcdConfiguration, this.trackSelection, bufferedDurationUs, CmcdHeadersFactory.STREAMING_FORMAT_HLS, !playlist.hasEndTag);
            if (getIsMuxedAudioAndVideo()) {
                objectType = CmcdHeadersFactory.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO;
            } else {
                objectType = CmcdHeadersFactory.getObjectType(this.trackSelection);
            }
            objectType2 = cmcdHeadersFactory.setObjectType(objectType);
        }
        CmcdHeadersFactory cmcdHeadersFactory2 = objectType2;
        Uri initSegmentKeyUri = getFullEncryptionKeyUri(playlist, segmentBaseHolder.segmentBase.initializationSegment);
        hlsChunkHolder.chunk = maybeCreateEncryptionChunkFor(initSegmentKeyUri, selectedTrackIndex, true, cmcdHeadersFactory2);
        if (hlsChunkHolder.chunk != null) {
            return;
        }
        Uri mediaSegmentKeyUri = getFullEncryptionKeyUri(playlist, segmentBaseHolder.segmentBase);
        hlsChunkHolder.chunk = maybeCreateEncryptionChunkFor(mediaSegmentKeyUri, selectedTrackIndex, false, cmcdHeadersFactory2);
        if (hlsChunkHolder.chunk != null) {
            return;
        }
        HlsMediaChunk previous2 = previous;
        SegmentBaseHolder segmentBaseHolder2 = segmentBaseHolder;
        boolean shouldSpliceIn = HlsMediaChunk.shouldSpliceIn(previous2, selectedPlaylistUrl, playlist, segmentBaseHolder2, startOfPlaylistInPeriodUs4);
        Uri selectedPlaylistUrl4 = selectedPlaylistUrl;
        if (shouldSpliceIn && segmentBaseHolder2.isPreload) {
            return;
        }
        int selectedTrackIndex2 = selectedTrackIndex;
        HlsExtractorFactory hlsExtractorFactory = this.extractorFactory;
        DataSource dataSource = this.mediaDataSource;
        Format format = this.playlistFormats[selectedTrackIndex2];
        List<Format> list = this.muxedCaptionFormats;
        int partIndex4 = this.trackSelection.getSelectionReason();
        hlsChunkHolder.chunk = HlsMediaChunk.createInstance(hlsExtractorFactory, dataSource, format, startOfPlaylistInPeriodUs4, playlist, segmentBaseHolder2, selectedPlaylistUrl4, list, partIndex4, this.trackSelection.getSelectionData(), this.isPrimaryTimestampSource, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, previous2, this.keyCache.get(mediaSegmentKeyUri), this.keyCache.get(initSegmentKeyUri), shouldSpliceIn, this.playerId, cmcdHeadersFactory2);
    }

    private boolean getIsMuxedAudioAndVideo() {
        Format format = this.trackGroup.getFormat(this.trackSelection.getSelectedIndex());
        String audioMimeType = MimeTypes.getAudioMediaMimeType(format.codecs);
        String videoMimeType = MimeTypes.getVideoMediaMimeType(format.codecs);
        return (audioMimeType == null || videoMimeType == null) ? false : true;
    }

    private static SegmentBaseHolder getNextSegmentHolder(HlsMediaPlaylist mediaPlaylist, long nextMediaSequence, int nextPartIndex) {
        int segmentIndexInPlaylist = (int) (nextMediaSequence - mediaPlaylist.mediaSequence);
        if (segmentIndexInPlaylist == mediaPlaylist.segments.size()) {
            int index = nextPartIndex != -1 ? nextPartIndex : 0;
            if (index < mediaPlaylist.trailingParts.size()) {
                return new SegmentBaseHolder(mediaPlaylist.trailingParts.get(index), nextMediaSequence, index);
            }
            return null;
        }
        HlsMediaPlaylist.Segment mediaSegment = mediaPlaylist.segments.get(segmentIndexInPlaylist);
        if (nextPartIndex == -1) {
            return new SegmentBaseHolder(mediaSegment, nextMediaSequence, -1);
        }
        if (nextPartIndex < mediaSegment.parts.size()) {
            return new SegmentBaseHolder(mediaSegment.parts.get(nextPartIndex), nextMediaSequence, nextPartIndex);
        }
        if (segmentIndexInPlaylist + 1 < mediaPlaylist.segments.size()) {
            return new SegmentBaseHolder(mediaPlaylist.segments.get(segmentIndexInPlaylist + 1), 1 + nextMediaSequence, -1);
        }
        if (mediaPlaylist.trailingParts.isEmpty()) {
            return null;
        }
        return new SegmentBaseHolder(mediaPlaylist.trailingParts.get(0), 1 + nextMediaSequence, 0);
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            this.keyCache.put(encryptionKeyChunk.dataSpec.uri, (byte[]) Assertions.checkNotNull(encryptionKeyChunk.getResult()));
        }
    }

    public boolean maybeExcludeTrack(Chunk chunk, long exclusionDurationMs) {
        return this.trackSelection.excludeTrack(this.trackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), exclusionDurationMs);
    }

    public boolean onPlaylistError(Uri playlistUrl, long exclusionDurationMs) {
        int trackSelectionIndex;
        int trackGroupIndex = -1;
        for (int i = 0; i < this.playlistUrls.length; i++) {
            if (this.playlistUrls[i].equals(playlistUrl)) {
                trackGroupIndex = i;
                break;
            }
        }
        if (trackGroupIndex == -1 || (trackSelectionIndex = this.trackSelection.indexOf(trackGroupIndex)) == -1) {
            return true;
        }
        this.seenExpectedPlaylistError |= playlistUrl.equals(this.expectedPlaylistUrl);
        if (exclusionDurationMs != C.TIME_UNSET) {
            return this.trackSelection.excludeTrack(trackSelectionIndex, exclusionDurationMs) && this.playlistTracker.excludeMediaPlaylist(playlistUrl, exclusionDurationMs);
        }
        return true;
    }

    public MediaChunkIterator[] createMediaChunkIterators(HlsMediaChunk previous, long loadPositionUs) {
        HlsChunkSource hlsChunkSource = this;
        HlsMediaChunk hlsMediaChunk = previous;
        int oldTrackIndex = hlsMediaChunk == null ? -1 : hlsChunkSource.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        MediaChunkIterator[] chunkIterators = new MediaChunkIterator[hlsChunkSource.trackSelection.length()];
        int i = 0;
        while (i < chunkIterators.length) {
            int trackIndex = hlsChunkSource.trackSelection.getIndexInTrackGroup(i);
            Uri playlistUrl = hlsChunkSource.playlistUrls[trackIndex];
            if (!hlsChunkSource.playlistTracker.isSnapshotValid(playlistUrl)) {
                chunkIterators[i] = MediaChunkIterator.EMPTY;
            } else {
                HlsMediaPlaylist playlist = hlsChunkSource.playlistTracker.getPlaylistSnapshot(playlistUrl, false);
                Assertions.checkNotNull(playlist);
                long startOfPlaylistInPeriodUs = playlist.startTimeUs - hlsChunkSource.playlistTracker.getInitialStartTimeUs();
                boolean switchingTrack = trackIndex != oldTrackIndex;
                Pair<Long, Integer> chunkMediaSequenceAndPartIndex = hlsChunkSource.getNextMediaSequenceAndPartIndex(hlsMediaChunk, switchingTrack, playlist, startOfPlaylistInPeriodUs, loadPositionUs);
                long chunkMediaSequence = ((Long) chunkMediaSequenceAndPartIndex.first).longValue();
                int partIndex = ((Integer) chunkMediaSequenceAndPartIndex.second).intValue();
                chunkIterators[i] = new HlsMediaPlaylistSegmentIterator(playlist.baseUri, startOfPlaylistInPeriodUs, getSegmentBaseList(playlist, chunkMediaSequence, partIndex));
            }
            i++;
            hlsChunkSource = this;
            hlsMediaChunk = previous;
        }
        return chunkIterators;
    }

    public int getPreferredQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return queue.size();
        }
        return this.trackSelection.evaluateQueueSize(playbackPositionUs, queue);
    }

    public boolean shouldCancelLoad(long playbackPositionUs, Chunk loadingChunk, List<? extends MediaChunk> queue) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(playbackPositionUs, loadingChunk, queue);
    }

    static List<HlsMediaPlaylist.SegmentBase> getSegmentBaseList(HlsMediaPlaylist playlist, long mediaSequence, int partIndex) {
        int firstSegmentIndexInPlaylist = (int) (mediaSequence - playlist.mediaSequence);
        if (firstSegmentIndexInPlaylist < 0 || playlist.segments.size() < firstSegmentIndexInPlaylist) {
            return ImmutableList.of();
        }
        List<HlsMediaPlaylist.SegmentBase> segmentBases = new ArrayList<>();
        if (firstSegmentIndexInPlaylist < playlist.segments.size()) {
            if (partIndex != -1) {
                HlsMediaPlaylist.Segment firstSegment = playlist.segments.get(firstSegmentIndexInPlaylist);
                if (partIndex == 0) {
                    segmentBases.add(firstSegment);
                } else if (partIndex < firstSegment.parts.size()) {
                    segmentBases.addAll(firstSegment.parts.subList(partIndex, firstSegment.parts.size()));
                }
                firstSegmentIndexInPlaylist++;
            }
            partIndex = 0;
            segmentBases.addAll(playlist.segments.subList(firstSegmentIndexInPlaylist, playlist.segments.size()));
        }
        if (playlist.partTargetDurationUs != C.TIME_UNSET) {
            int partIndex2 = partIndex == -1 ? 0 : partIndex;
            if (partIndex2 < playlist.trailingParts.size()) {
                segmentBases.addAll(playlist.trailingParts.subList(partIndex2, playlist.trailingParts.size()));
            }
        }
        return Collections.unmodifiableList(segmentBases);
    }

    public boolean obtainsChunksForPlaylist(Uri playlistUrl) {
        return Util.contains(this.playlistUrls, playlistUrl);
    }

    private Pair<Long, Integer> getNextMediaSequenceAndPartIndex(HlsMediaChunk previous, boolean switchingTrack, HlsMediaPlaylist mediaPlaylist, long startOfPlaylistInPeriodUs, long loadPositionUs) {
        int partIndex;
        int partIndex2;
        List<HlsMediaPlaylist.Part> parts;
        long nextChunkIndex;
        if (previous == null || switchingTrack) {
            long endOfPlaylistInPeriodUs = startOfPlaylistInPeriodUs + mediaPlaylist.durationUs;
            long targetPositionInPeriodUs = (previous == null || this.independentSegments) ? loadPositionUs : previous.startTimeUs;
            if (!mediaPlaylist.hasEndTag && targetPositionInPeriodUs >= endOfPlaylistInPeriodUs) {
                return new Pair<>(Long.valueOf(mediaPlaylist.mediaSequence + ((long) mediaPlaylist.segments.size())), -1);
            }
            long targetPositionInPlaylistUs = targetPositionInPeriodUs - startOfPlaylistInPeriodUs;
            int segmentIndexInPlaylist = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) mediaPlaylist.segments, Long.valueOf(targetPositionInPlaylistUs), true, !this.playlistTracker.isLive() || previous == null);
            long mediaSequence = ((long) segmentIndexInPlaylist) + mediaPlaylist.mediaSequence;
            int partIndex3 = -1;
            if (segmentIndexInPlaylist < 0) {
                partIndex = -1;
            } else {
                HlsMediaPlaylist.Segment segment = mediaPlaylist.segments.get(segmentIndexInPlaylist);
                if (targetPositionInPlaylistUs < segment.relativeStartTimeUs + segment.durationUs) {
                    parts = segment.parts;
                } else {
                    parts = mediaPlaylist.trailingParts;
                }
                int i = 0;
                while (true) {
                    if (i >= parts.size()) {
                        partIndex = partIndex3;
                        break;
                    }
                    HlsMediaPlaylist.Part part = parts.get(i);
                    int segmentIndexInPlaylist2 = segmentIndexInPlaylist;
                    partIndex = partIndex3;
                    if (targetPositionInPlaylistUs >= part.relativeStartTimeUs + part.durationUs) {
                        i++;
                        segmentIndexInPlaylist = segmentIndexInPlaylist2;
                        partIndex3 = partIndex;
                    } else {
                        if (part.isIndependent) {
                            partIndex2 = i;
                            mediaSequence += parts == mediaPlaylist.trailingParts ? 1L : 0L;
                        }
                        return new Pair<>(Long.valueOf(mediaSequence), Integer.valueOf(partIndex2));
                    }
                }
            }
            partIndex2 = partIndex;
            return new Pair<>(Long.valueOf(mediaSequence), Integer.valueOf(partIndex2));
        }
        if (previous.isLoadCompleted()) {
            if (previous.partIndex == -1) {
                nextChunkIndex = previous.getNextChunkIndex();
            } else {
                nextChunkIndex = previous.chunkIndex;
            }
            return new Pair<>(Long.valueOf(nextChunkIndex), Integer.valueOf(previous.partIndex != -1 ? previous.partIndex + 1 : -1));
        }
        return new Pair<>(Long.valueOf(previous.chunkIndex), Integer.valueOf(previous.partIndex));
    }

    private long resolveTimeToLiveEdgeUs(long playbackPositionUs) {
        boolean resolveTimeToLiveEdgePossible = this.liveEdgeInPeriodTimeUs != C.TIME_UNSET;
        return resolveTimeToLiveEdgePossible ? this.liveEdgeInPeriodTimeUs - playbackPositionUs : C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist mediaPlaylist) {
        long endTimeUs;
        if (mediaPlaylist.hasEndTag) {
            endTimeUs = C.TIME_UNSET;
        } else {
            endTimeUs = mediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        }
        this.liveEdgeInPeriodTimeUs = endTimeUs;
    }

    private Chunk maybeCreateEncryptionChunkFor(Uri keyUri, int selectedTrackIndex, boolean isInitSegment, CmcdHeadersFactory cmcdHeadersFactory) {
        if (keyUri == null) {
            return null;
        }
        byte[] encryptionKey = this.keyCache.remove(keyUri);
        if (encryptionKey != null) {
            this.keyCache.put(keyUri, encryptionKey);
            return null;
        }
        ImmutableMap<String, String> httpRequestHeaders = ImmutableMap.of();
        if (cmcdHeadersFactory != null) {
            if (isInitSegment) {
                cmcdHeadersFactory.setObjectType("i");
            }
            httpRequestHeaders = cmcdHeadersFactory.createHttpRequestHeaders();
        }
        DataSpec dataSpec = new DataSpec.Builder().setUri(keyUri).setFlags(1).setHttpRequestHeaders(httpRequestHeaders).build();
        return new EncryptionKeyChunk(this.encryptionDataSource, dataSpec, this.playlistFormats[selectedTrackIndex], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist playlist, HlsMediaPlaylist.SegmentBase segmentBase) {
        if (segmentBase == null || segmentBase.fullSegmentEncryptionKeyUri == null) {
            return null;
        }
        return UriUtil.resolveToUri(playlist.baseUri, segmentBase.fullSegmentEncryptionKeyUri);
    }

    static final class SegmentBaseHolder {
        public final boolean isPreload;
        public final long mediaSequence;
        public final int partIndex;
        public final HlsMediaPlaylist.SegmentBase segmentBase;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase segmentBase, long mediaSequence, int partIndex) {
            this.segmentBase = segmentBase;
            this.mediaSequence = mediaSequence;
            this.partIndex = partIndex;
            this.isPreload = (segmentBase instanceof HlsMediaPlaylist.Part) && ((HlsMediaPlaylist.Part) segmentBase).isPreload;
        }
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup group, int[] tracks) {
            super(group, tracks);
            this.selectedIndex = indexOf(group.getFormat(tracks[0]));
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs, List<? extends MediaChunk> queue, MediaChunkIterator[] mediaChunkIterators) {
            long nowMs = SystemClock.elapsedRealtime();
            if (!isTrackExcluded(this.selectedIndex, nowMs)) {
                return;
            }
            for (int i = this.length - 1; i >= 0; i--) {
                if (!isTrackExcluded(i, nowMs)) {
                    this.selectedIndex = i;
                    return;
                }
            }
            throw new IllegalStateException();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.selectedIndex;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }
    }

    private static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format trackFormat, int trackSelectionReason, Object trackSelectionData, byte[] scratchSpace) {
            super(dataSource, dataSpec, 3, trackFormat, trackSelectionReason, trackSelectionData, scratchSpace);
        }

        @Override // androidx.media3.exoplayer.source.chunk.DataChunk
        protected void consume(byte[] data, int limit) {
            this.result = Arrays.copyOf(data, limit);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final String playlistBaseUri;
        private final List<HlsMediaPlaylist.SegmentBase> segmentBases;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(String playlistBaseUri, long startOfPlaylistInPeriodUs, List<HlsMediaPlaylist.SegmentBase> segmentBases) {
            super(0L, segmentBases.size() - 1);
            this.playlistBaseUri = playlistBaseUri;
            this.startOfPlaylistInPeriodUs = startOfPlaylistInPeriodUs;
            this.segmentBases = segmentBases;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            Uri chunkUri = UriUtil.resolveToUri(this.playlistBaseUri, segmentBase.url);
            return new DataSpec(chunkUri, segmentBase.byteRangeOffset, segmentBase.byteRangeLength);
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + this.segmentBases.get((int) getCurrentIndex()).relativeStartTimeUs;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            long segmentStartTimeInPeriodUs = this.startOfPlaylistInPeriodUs + segmentBase.relativeStartTimeUs;
            return segmentBase.durationUs + segmentStartTimeInPeriodUs;
        }
    }
}
