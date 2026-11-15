package androidx.media3.extractor.mp4;

import androidx.collection.SieveCacheKt;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import com.google.common.base.Function;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class Mp4Extractor implements Extractor, SeekMap {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.mp4.Mp4Extractor$$ExternalSyntheticLambda1
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return Mp4Extractor.lambda$static$0();
        }
    };
    private static final int FILE_TYPE_HEIC = 2;
    private static final int FILE_TYPE_MP4 = 0;
    private static final int FILE_TYPE_QUICKTIME = 1;
    public static final int FLAG_READ_MOTION_PHOTO_METADATA = 2;
    public static final int FLAG_READ_SEF_DATA = 4;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private static final int STATE_READING_SEF = 3;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final ArrayDeque<Atom.ContainerAtom> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private int firstVideoTrackIndex;
    private final int flags;
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private Mp4Track[] tracks;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp4Extractor()};
    }

    public Mp4Extractor() {
        this(0);
    }

    public Mp4Extractor(int flags) {
        this.flags = flags;
        this.parserState = (flags & 4) != 0 ? 3 : 0;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        return Sniffer.sniffUnfragmented(input, (this.flags & 2) != 0);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        this.extractorOutput = output;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if (position == 0) {
            if (this.parserState != 3) {
                enterReadingAtomHeaderState();
                return;
            } else {
                this.sefReader.reset();
                this.slowMotionMetadataEntries.clear();
                return;
            }
        }
        for (Mp4Track track : this.tracks) {
            updateSampleIndex(track, timeUs);
            if (track.trueHdSampleRechunker != null) {
                track.trueHdSampleRechunker.reset();
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        while (true) {
            switch (this.parserState) {
                case 0:
                    if (!readAtomHeader(input)) {
                        return -1;
                    }
                    break;
                    break;
                case 1:
                    if (readAtomPayload(input, seekPosition)) {
                        return 1;
                    }
                    break;
                case 2:
                    return readSample(input, seekPosition);
                case 3:
                    return readSefData(input, seekPosition);
                default:
                    throw new IllegalStateException();
            }
        }
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long timeUs) {
        return getSeekPoints(timeUs, -1);
    }

    public SeekMap.SeekPoints getSeekPoints(long timeUs, int trackId) {
        long firstOffset;
        long firstTimeUs;
        long j;
        long firstOffset2;
        int secondSampleIndex;
        if (this.tracks.length == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        long secondTimeUs = C.TIME_UNSET;
        long secondOffset = -1;
        int mainTrackIndex = trackId != -1 ? trackId : this.firstVideoTrackIndex;
        if (mainTrackIndex != -1) {
            TrackSampleTable sampleTable = this.tracks[mainTrackIndex].sampleTable;
            int sampleIndex = getSynchronizationSampleIndex(sampleTable, timeUs);
            if (sampleIndex == -1) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            long sampleTimeUs = sampleTable.timestampsUs[sampleIndex];
            firstOffset = sampleTable.offsets[sampleIndex];
            if (sampleTimeUs < timeUs && sampleIndex < sampleTable.sampleCount - 1 && (secondSampleIndex = sampleTable.getIndexOfLaterOrEqualSynchronizationSample(timeUs)) != -1 && secondSampleIndex != sampleIndex) {
                secondTimeUs = sampleTable.timestampsUs[secondSampleIndex];
                secondOffset = sampleTable.offsets[secondSampleIndex];
            }
            firstTimeUs = sampleTimeUs;
        } else {
            firstOffset = Long.MAX_VALUE;
            firstTimeUs = timeUs;
        }
        if (trackId != -1) {
            j = C.TIME_UNSET;
            firstOffset2 = firstOffset;
        } else {
            int i = 0;
            firstOffset2 = firstOffset;
            while (true) {
                j = C.TIME_UNSET;
                if (i >= this.tracks.length) {
                    break;
                }
                if (i != this.firstVideoTrackIndex) {
                    TrackSampleTable sampleTable2 = this.tracks[i].sampleTable;
                    firstOffset2 = maybeAdjustSeekOffset(sampleTable2, firstTimeUs, firstOffset2);
                    if (secondTimeUs != C.TIME_UNSET) {
                        secondOffset = maybeAdjustSeekOffset(sampleTable2, secondTimeUs, secondOffset);
                    }
                }
                i++;
            }
        }
        SeekPoint firstSeekPoint = new SeekPoint(firstTimeUs, firstOffset2);
        if (secondTimeUs == j) {
            return new SeekMap.SeekPoints(firstSeekPoint);
        }
        SeekPoint secondSeekPoint = new SeekPoint(secondTimeUs, secondOffset);
        return new SeekMap.SeekPoints(firstSeekPoint, secondSeekPoint);
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput input) throws IOException {
        Atom.ContainerAtom containerAtom;
        if (this.atomHeaderBytesRead == 0) {
            if (!input.readFully(this.atomHeader.getData(), 0, 8, true)) {
                processEndOfStreamReadingAtomHeader();
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        if (this.atomSize == 1) {
            input.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (this.atomSize == 0) {
            long endPosition = input.getLength();
            if (endPosition == -1 && (containerAtom = this.containerAtoms.peek()) != null) {
                endPosition = containerAtom.endPosition;
            }
            if (endPosition != -1) {
                this.atomSize = (endPosition - input.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize < this.atomHeaderBytesRead) {
            throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
        }
        if (shouldParseContainerAtom(this.atomType)) {
            long endPosition2 = (input.getPosition() + this.atomSize) - ((long) this.atomHeaderBytesRead);
            if (this.atomSize != this.atomHeaderBytesRead && this.atomType == 1835365473) {
                maybeSkipRemainingMetaAtomHeaderBytes(input);
            }
            this.containerAtoms.push(new Atom.ContainerAtom(this.atomType, endPosition2));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(endPosition2);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            Assertions.checkState(this.atomHeaderBytesRead == 8);
            Assertions.checkState(this.atomSize <= SieveCacheKt.NodeLinkMask);
            ParsableByteArray atomData = new ParsableByteArray((int) this.atomSize);
            System.arraycopy(this.atomHeader.getData(), 0, atomData.getData(), 0, 8);
            this.atomData = atomData;
            this.parserState = 1;
        } else {
            processUnparsedAtom(input.getPosition() - ((long) this.atomHeaderBytesRead));
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private boolean readAtomPayload(ExtractorInput input, PositionHolder positionHolder) throws IOException {
        long atomPayloadSize = this.atomSize - ((long) this.atomHeaderBytesRead);
        long atomEndPosition = input.getPosition() + atomPayloadSize;
        boolean seekRequired = false;
        ParsableByteArray atomData = this.atomData;
        if (atomData != null) {
            input.readFully(atomData.getData(), this.atomHeaderBytesRead, (int) atomPayloadSize);
            if (this.atomType == 1718909296) {
                this.fileType = processFtypAtom(atomData);
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(new Atom.LeafAtom(this.atomType, atomData));
            }
        } else if (atomPayloadSize < 262144) {
            input.skipFully((int) atomPayloadSize);
        } else {
            positionHolder.position = input.getPosition() + atomPayloadSize;
            seekRequired = true;
        }
        processAtomEnded(atomEndPosition);
        return seekRequired && this.parserState != 2;
    }

    private int readSefData(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        int result = this.sefReader.read(input, seekPosition, this.slowMotionMetadataEntries);
        if (result == 1 && seekPosition.position == 0) {
            enterReadingAtomHeaderState();
        }
        return result;
    }

    private void processAtomEnded(long atomEndPosition) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == atomEndPosition) {
            Atom.ContainerAtom containerAtom = this.containerAtoms.pop();
            if (containerAtom.type == 1836019574) {
                processMoovAtom(containerAtom);
                this.containerAtoms.clear();
                this.parserState = 2;
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(containerAtom);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processMoovAtom(Atom.ContainerAtom moov) throws ParserException {
        Metadata smtaMetadata;
        Metadata xyzMetadata;
        int maxInputSize;
        int i;
        boolean z;
        Metadata mdtaMetadata;
        int firstVideoTrackIndex = -1;
        long durationUs = C.TIME_UNSET;
        List<Mp4Track> tracks = new ArrayList<>();
        Metadata udtaMetaMetadata = null;
        boolean z2 = true;
        boolean isQuickTime = this.fileType == 1;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Atom.LeafAtom udta = moov.getLeafAtomOfType(Atom.TYPE_udta);
        if (udta == null) {
            smtaMetadata = null;
            xyzMetadata = null;
        } else {
            AtomParsers.UdtaInfo udtaInfo = AtomParsers.parseUdta(udta);
            udtaMetaMetadata = udtaInfo.metaMetadata;
            Metadata smtaMetadata2 = udtaInfo.smtaMetadata;
            Metadata xyzMetadata2 = udtaInfo.xyzMetadata;
            if (udtaMetaMetadata != null) {
                gaplessInfoHolder.setFromMetadata(udtaMetaMetadata);
            }
            smtaMetadata = smtaMetadata2;
            xyzMetadata = xyzMetadata2;
        }
        Metadata mdtaMetadata2 = null;
        Atom.ContainerAtom meta = moov.getContainerAtomOfType(Atom.TYPE_meta);
        if (meta != null) {
            mdtaMetadata2 = AtomParsers.parseMdtaFromMeta(meta);
        }
        Metadata mvhdMetadata = AtomParsers.parseMvhd(((Atom.LeafAtom) Assertions.checkNotNull(moov.getLeafAtomOfType(Atom.TYPE_mvhd))).data).metadata;
        boolean ignoreEditLists = (this.flags & 1) != 0;
        Metadata udtaMetaMetadata2 = udtaMetaMetadata;
        GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
        Metadata mdtaMetadata3 = mdtaMetadata2;
        List<TrackSampleTable> trackSampleTables = AtomParsers.parseTraks(moov, gaplessInfoHolder2, C.TIME_UNSET, null, ignoreEditLists, isQuickTime, new Function() { // from class: androidx.media3.extractor.mp4.Mp4Extractor$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return Mp4Extractor.lambda$processMoovAtom$1((Track) obj);
            }
        });
        int trackCount = trackSampleTables.size();
        int i2 = 0;
        while (i2 < trackCount) {
            TrackSampleTable trackSampleTable = trackSampleTables.get(i2);
            if (trackSampleTable.sampleCount == 0) {
                z = z2;
                mdtaMetadata = mdtaMetadata3;
            } else {
                Track track = trackSampleTable.track;
                long trackDurationUs = track.durationUs != C.TIME_UNSET ? track.durationUs : trackSampleTable.durationUs;
                durationUs = Math.max(durationUs, trackDurationUs);
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i2, track.type));
                if (MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType)) {
                    maxInputSize = trackSampleTable.maximumSize * 16;
                } else {
                    int maxInputSize2 = trackSampleTable.maximumSize;
                    maxInputSize = maxInputSize2 + 30;
                }
                Format.Builder formatBuilder = track.format.buildUpon();
                formatBuilder.setMaxInputSize(maxInputSize);
                int maxInputSize3 = track.type;
                if (maxInputSize3 != 2 || trackDurationUs <= 0) {
                    i = 2;
                } else {
                    i = 2;
                    if (trackSampleTable.sampleCount > 1) {
                        float frameRate = trackSampleTable.sampleCount / (trackDurationUs / 1000000.0f);
                        formatBuilder.setFrameRate(frameRate);
                    }
                }
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder2, formatBuilder);
                int i3 = track.type;
                Metadata[] metadataArr = new Metadata[4];
                metadataArr[0] = smtaMetadata;
                z = true;
                metadataArr[1] = this.slowMotionMetadataEntries.isEmpty() ? null : new Metadata(this.slowMotionMetadataEntries);
                metadataArr[i] = xyzMetadata;
                metadataArr[3] = mvhdMetadata;
                mdtaMetadata = mdtaMetadata3;
                MetadataUtil.setFormatMetadata(i3, udtaMetaMetadata2, mdtaMetadata, formatBuilder, metadataArr);
                mp4Track.trackOutput.format(formatBuilder.build());
                if (track.type == i && firstVideoTrackIndex == -1) {
                    firstVideoTrackIndex = tracks.size();
                }
                tracks.add(mp4Track);
            }
            i2++;
            mdtaMetadata3 = mdtaMetadata;
            z2 = z;
            udta = udta;
            trackCount = trackCount;
            trackSampleTables = trackSampleTables;
            ignoreEditLists = ignoreEditLists;
            gaplessInfoHolder2 = gaplessInfoHolder2;
        }
        this.firstVideoTrackIndex = firstVideoTrackIndex;
        this.durationUs = durationUs;
        this.tracks = (Mp4Track[]) tracks.toArray(new Mp4Track[0]);
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(this.tracks);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    static /* synthetic */ Track lambda$processMoovAtom$1(Track track) {
        return track;
    }

    private int readSample(ExtractorInput input, PositionHolder positionHolder) throws IOException {
        TrackOutput trackOutput;
        TrackOutput.CryptoData cryptoData;
        long inputPosition = input.getPosition();
        if (this.sampleTrackIndex == -1) {
            this.sampleTrackIndex = getTrackIndexOfNextReadSample(inputPosition);
            if (this.sampleTrackIndex == -1) {
                return -1;
            }
        }
        Mp4Track track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput2 = track.trackOutput;
        int sampleIndex = track.sampleIndex;
        long position = track.sampleTable.offsets[sampleIndex];
        int sampleSize = track.sampleTable.sizes[sampleIndex];
        TrueHdSampleRechunker trueHdSampleRechunker = track.trueHdSampleRechunker;
        TrackOutput trackOutput3 = trackOutput2;
        long skipAmount = (position - inputPosition) + ((long) this.sampleBytesRead);
        if (skipAmount < 0 || skipAmount >= 262144) {
            positionHolder.position = position;
            return 1;
        }
        if (track.track.sampleTransformation == 1) {
            skipAmount += 8;
            sampleSize -= 8;
        }
        input.skipFully((int) skipAmount);
        if (track.track.nalUnitLengthFieldLength != 0) {
            byte[] nalLengthData = this.nalLength.getData();
            nalLengthData[0] = 0;
            nalLengthData[1] = 0;
            nalLengthData[2] = 0;
            int nalUnitLengthFieldLength = track.track.nalUnitLengthFieldLength;
            int nalUnitLengthFieldLengthDiff = 4 - track.track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < sampleSize) {
                if (this.sampleCurrentNalBytesRemaining == 0) {
                    input.readFully(nalLengthData, nalUnitLengthFieldLengthDiff, nalUnitLengthFieldLength);
                    this.sampleBytesRead += nalUnitLengthFieldLength;
                    long inputPosition2 = inputPosition;
                    this.nalLength.setPosition(0);
                    int nalLengthInt = this.nalLength.readInt();
                    if (nalLengthInt < 0) {
                        throw ParserException.createForMalformedContainer("Invalid NAL length", null);
                    }
                    this.sampleCurrentNalBytesRemaining = nalLengthInt;
                    this.nalStartCode.setPosition(0);
                    TrackOutput trackOutput4 = trackOutput3;
                    trackOutput4.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    sampleSize += nalUnitLengthFieldLengthDiff;
                    trackOutput3 = trackOutput4;
                    inputPosition = inputPosition2;
                } else {
                    long inputPosition3 = inputPosition;
                    int writtenBytes = trackOutput3.sampleData((DataReader) input, this.sampleCurrentNalBytesRemaining, false);
                    this.sampleBytesRead += writtenBytes;
                    this.sampleBytesWritten += writtenBytes;
                    this.sampleCurrentNalBytesRemaining -= writtenBytes;
                    inputPosition = inputPosition3;
                }
            }
            trackOutput = trackOutput3;
            cryptoData = null;
        } else {
            trackOutput = trackOutput3;
            cryptoData = null;
            if (MimeTypes.AUDIO_AC4.equals(track.track.format.sampleMimeType)) {
                if (this.sampleBytesWritten == 0) {
                    Ac4Util.getAc4SampleHeader(sampleSize, this.scratch);
                    trackOutput.sampleData(this.scratch, 7);
                    this.sampleBytesWritten += 7;
                }
                sampleSize += 7;
            } else if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.startSample(input);
            }
            while (this.sampleBytesWritten < sampleSize) {
                int writtenBytes2 = trackOutput.sampleData((DataReader) input, sampleSize - this.sampleBytesWritten, false);
                this.sampleBytesRead += writtenBytes2;
                this.sampleBytesWritten += writtenBytes2;
                this.sampleCurrentNalBytesRemaining -= writtenBytes2;
            }
        }
        long timeUs = track.sampleTable.timestampsUs[sampleIndex];
        int flags = track.sampleTable.flags[sampleIndex];
        if (trueHdSampleRechunker != null) {
            TrackOutput.CryptoData cryptoData2 = cryptoData;
            trueHdSampleRechunker.sampleMetadata(trackOutput, timeUs, flags, sampleSize, 0, null);
            if (sampleIndex + 1 == track.sampleTable.sampleCount) {
                trueHdSampleRechunker.outputPendingSampleMetadata(trackOutput, cryptoData2);
            }
        } else {
            trackOutput.sampleMetadata(timeUs, flags, sampleSize, 0, null);
        }
        track.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        return 0;
    }

    private int getTrackIndexOfNextReadSample(long inputPosition) {
        long preferredSkipAmount = Long.MAX_VALUE;
        boolean preferredRequiresReload = true;
        int preferredTrackIndex = -1;
        long preferredAccumulatedBytes = Long.MAX_VALUE;
        long minAccumulatedBytes = Long.MAX_VALUE;
        boolean minAccumulatedBytesRequiresReload = true;
        int minAccumulatedBytesTrackIndex = -1;
        for (int trackIndex = 0; trackIndex < this.tracks.length; trackIndex++) {
            Mp4Track track = this.tracks[trackIndex];
            int sampleIndex = track.sampleIndex;
            if (sampleIndex != track.sampleTable.sampleCount) {
                long sampleOffset = track.sampleTable.offsets[sampleIndex];
                long sampleAccumulatedBytes = ((long[][]) Util.castNonNull(this.accumulatedSampleSizes))[trackIndex][sampleIndex];
                long skipAmount = sampleOffset - inputPosition;
                boolean requiresReload = skipAmount < 0 || skipAmount >= 262144;
                if ((!requiresReload && preferredRequiresReload) || (requiresReload == preferredRequiresReload && skipAmount < preferredSkipAmount)) {
                    preferredRequiresReload = requiresReload;
                    preferredSkipAmount = skipAmount;
                    preferredTrackIndex = trackIndex;
                    preferredAccumulatedBytes = sampleAccumulatedBytes;
                }
                if (sampleAccumulatedBytes < minAccumulatedBytes) {
                    minAccumulatedBytes = sampleAccumulatedBytes;
                    minAccumulatedBytesRequiresReload = requiresReload;
                    minAccumulatedBytesTrackIndex = trackIndex;
                }
            }
        }
        if (minAccumulatedBytes == Long.MAX_VALUE || !minAccumulatedBytesRequiresReload || preferredAccumulatedBytes < MAXIMUM_READ_AHEAD_BYTES_STREAM + minAccumulatedBytes) {
            return preferredTrackIndex;
        }
        return minAccumulatedBytesTrackIndex;
    }

    private void updateSampleIndex(Mp4Track track, long timeUs) {
        TrackSampleTable sampleTable = track.sampleTable;
        int sampleIndex = sampleTable.getIndexOfEarlierOrEqualSynchronizationSample(timeUs);
        if (sampleIndex == -1) {
            sampleIndex = sampleTable.getIndexOfLaterOrEqualSynchronizationSample(timeUs);
        }
        track.sampleIndex = sampleIndex;
    }

    private void processEndOfStreamReadingAtomHeader() {
        if (this.fileType == 2 && (this.flags & 2) != 0) {
            TrackOutput trackOutput = this.extractorOutput.track(0, 4);
            Metadata metadata = this.motionPhotoMetadata == null ? null : new Metadata(this.motionPhotoMetadata);
            trackOutput.format(new Format.Builder().setMetadata(metadata).build());
            this.extractorOutput.endTracks();
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        }
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput input) throws IOException {
        this.scratch.reset(8);
        input.peekFully(this.scratch.getData(), 0, 8);
        AtomParsers.maybeSkipRemainingMetaAtomHeaderBytes(this.scratch);
        input.skipFully(this.scratch.getPosition());
        input.resetPeekPosition();
    }

    private void processUnparsedAtom(long atomStartPosition) {
        if (this.atomType == 1836086884) {
            this.motionPhotoMetadata = new MotionPhotoMetadata(0L, atomStartPosition, C.TIME_UNSET, atomStartPosition + ((long) this.atomHeaderBytesRead), this.atomSize - ((long) this.atomHeaderBytesRead));
        }
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] tracks) {
        long[][] accumulatedSampleSizes = new long[tracks.length][];
        int[] nextSampleIndex = new int[tracks.length];
        long[] nextSampleTimesUs = new long[tracks.length];
        boolean[] tracksFinished = new boolean[tracks.length];
        for (int i = 0; i < tracks.length; i++) {
            accumulatedSampleSizes[i] = new long[tracks[i].sampleTable.sampleCount];
            nextSampleTimesUs[i] = tracks[i].sampleTable.timestampsUs[0];
        }
        long accumulatedSampleSize = 0;
        int finishedTracks = 0;
        while (finishedTracks < tracks.length) {
            long minTimeUs = Long.MAX_VALUE;
            int minTimeTrackIndex = -1;
            for (int i2 = 0; i2 < tracks.length; i2++) {
                if (!tracksFinished[i2] && nextSampleTimesUs[i2] <= minTimeUs) {
                    minTimeTrackIndex = i2;
                    minTimeUs = nextSampleTimesUs[i2];
                }
            }
            int i3 = nextSampleIndex[minTimeTrackIndex];
            accumulatedSampleSizes[minTimeTrackIndex][i3] = accumulatedSampleSize;
            accumulatedSampleSize += (long) tracks[minTimeTrackIndex].sampleTable.sizes[i3];
            int trackSampleIndex = i3 + 1;
            nextSampleIndex[minTimeTrackIndex] = trackSampleIndex;
            if (trackSampleIndex < accumulatedSampleSizes[minTimeTrackIndex].length) {
                nextSampleTimesUs[minTimeTrackIndex] = tracks[minTimeTrackIndex].sampleTable.timestampsUs[trackSampleIndex];
            } else {
                tracksFinished[minTimeTrackIndex] = true;
                finishedTracks++;
            }
        }
        return accumulatedSampleSizes;
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable sampleTable, long seekTimeUs, long offset) {
        int sampleIndex = getSynchronizationSampleIndex(sampleTable, seekTimeUs);
        if (sampleIndex == -1) {
            return offset;
        }
        long sampleOffset = sampleTable.offsets[sampleIndex];
        return Math.min(sampleOffset, offset);
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable sampleTable, long timeUs) {
        int sampleIndex = sampleTable.getIndexOfEarlierOrEqualSynchronizationSample(timeUs);
        if (sampleIndex == -1) {
            return sampleTable.getIndexOfLaterOrEqualSynchronizationSample(timeUs);
        }
        return sampleIndex;
    }

    private static int processFtypAtom(ParsableByteArray atomData) {
        atomData.setPosition(8);
        int majorBrand = atomData.readInt();
        int fileType = brandToFileType(majorBrand);
        if (fileType != 0) {
            return fileType;
        }
        atomData.skipBytes(4);
        while (atomData.bytesLeft() > 0) {
            int fileType2 = brandToFileType(atomData.readInt());
            if (fileType2 != 0) {
                return fileType2;
            }
        }
        return 0;
    }

    private static int brandToFileType(int brand) {
        switch (brand) {
            case Sniffer.BRAND_HEIC /* 1751476579 */:
                return 2;
            case Sniffer.BRAND_QUICKTIME /* 1903435808 */:
                return 1;
            default:
                return 0;
        }
    }

    private static boolean shouldParseLeafAtom(int atom) {
        return atom == 1835296868 || atom == 1836476516 || atom == 1751411826 || atom == 1937011556 || atom == 1937011827 || atom == 1937011571 || atom == 1668576371 || atom == 1701606260 || atom == 1937011555 || atom == 1937011578 || atom == 1937013298 || atom == 1937007471 || atom == 1668232756 || atom == 1953196132 || atom == 1718909296 || atom == 1969517665 || atom == 1801812339 || atom == 1768715124;
    }

    private static boolean shouldParseContainerAtom(int atom) {
        return atom == 1836019574 || atom == 1953653099 || atom == 1835297121 || atom == 1835626086 || atom == 1937007212 || atom == 1701082227 || atom == 1835365473;
    }

    private static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track track, TrackSampleTable sampleTable, TrackOutput trackOutput) {
            TrueHdSampleRechunker trueHdSampleRechunker;
            this.track = track;
            this.sampleTable = sampleTable;
            this.trackOutput = trackOutput;
            if (MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType)) {
                trueHdSampleRechunker = new TrueHdSampleRechunker();
            } else {
                trueHdSampleRechunker = null;
            }
            this.trueHdSampleRechunker = trueHdSampleRechunker;
        }
    }
}
