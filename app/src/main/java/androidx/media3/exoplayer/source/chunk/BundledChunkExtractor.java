package androidx.media3.exoplayer.source.chunk;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DummyTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final ChunkExtractor.Factory FACTORY = new ChunkExtractor.Factory() { // from class: androidx.media3.exoplayer.source.chunk.BundledChunkExtractor$$ExternalSyntheticLambda0
        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public final ChunkExtractor createProgressiveMediaExtractor(int i, Format format, boolean z, List list, TrackOutput trackOutput, PlayerId playerId) {
            return BundledChunkExtractor.lambda$static$0(i, format, z, list, trackOutput, playerId);
        }
    };
    private static final PositionHolder POSITION_HOLDER = new PositionHolder();
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    private long endTimeUs;
    private final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    static /* synthetic */ ChunkExtractor lambda$static$0(int primaryTrackType, Format format, boolean enableEventMessageTrack, List closedCaptionFormats, TrackOutput playerEmsgTrackOutput, PlayerId playerId) {
        int flags;
        Extractor extractor;
        String containerMimeType = format.containerMimeType;
        if (MimeTypes.isText(containerMimeType)) {
            return null;
        }
        if (MimeTypes.isMatroska(containerMimeType)) {
            extractor = new MatroskaExtractor(1);
        } else {
            if (!enableEventMessageTrack) {
                flags = 0;
            } else {
                int flags2 = 0 | 4;
                flags = flags2;
            }
            extractor = new FragmentedMp4Extractor(flags, null, null, closedCaptionFormats, playerEmsgTrackOutput);
        }
        return new BundledChunkExtractor(extractor, primaryTrackType, format);
    }

    public BundledChunkExtractor(Extractor extractor, int primaryTrackType, Format primaryTrackManifestFormat) {
        this.extractor = extractor;
        this.primaryTrackType = primaryTrackType;
        this.primaryTrackManifestFormat = primaryTrackManifestFormat;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public ChunkIndex getChunkIndex() {
        if (this.seekMap instanceof ChunkIndex) {
            return (ChunkIndex) this.seekMap;
        }
        return null;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider, long startTimeUs, long endTimeUs) {
        this.trackOutputProvider = trackOutputProvider;
        this.endTimeUs = endTimeUs;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            if (startTimeUs != C.TIME_UNSET) {
                this.extractor.seek(0L, startTimeUs);
            }
            this.extractorInitialized = true;
            return;
        }
        this.extractor.seek(0L, startTimeUs == C.TIME_UNSET ? 0L : startTimeUs);
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            this.bindingTrackOutputs.valueAt(i).bind(trackOutputProvider, endTimeUs);
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void release() {
        this.extractor.release();
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput input) throws IOException {
        int result = this.extractor.read(input, POSITION_HOLDER);
        Assertions.checkState(result != 1);
        return result == 0;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int id, int type) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(id);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            BindingTrackOutput bindingTrackOutput2 = new BindingTrackOutput(id, type, type == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput2.bind(this.trackOutputProvider, this.endTimeUs);
            this.bindingTrackOutputs.put(id, bindingTrackOutput2);
            return bindingTrackOutput2;
        }
        return bindingTrackOutput;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        Format[] sampleFormats = new Format[this.bindingTrackOutputs.size()];
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            sampleFormats[i] = (Format) Assertions.checkStateNotNull(this.bindingTrackOutputs.valueAt(i).sampleFormat);
        }
        this.sampleFormats = sampleFormats;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    private static final class BindingTrackOutput implements TrackOutput {
        private long endTimeUs;
        private final DummyTrackOutput fakeTrackOutput = new DummyTrackOutput();
        private final int id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int id, int type, Format manifestFormat) {
            this.id = id;
            this.type = type;
            this.manifestFormat = manifestFormat;
        }

        public void bind(ChunkExtractor.TrackOutputProvider trackOutputProvider, long endTimeUs) {
            if (trackOutputProvider == null) {
                this.trackOutput = this.fakeTrackOutput;
                return;
            }
            this.endTimeUs = endTimeUs;
            this.trackOutput = trackOutputProvider.track(this.id, this.type);
            if (this.sampleFormat != null) {
                this.trackOutput.format(this.sampleFormat);
            }
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format format) {
            this.sampleFormat = this.manifestFormat != null ? format.withManifestFormatInfo(this.manifestFormat) : format;
            ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.sampleFormat);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader input, int length, boolean allowEndOfInput, int sampleDataPart) throws IOException {
            return ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(input, length, allowEndOfInput);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray data, int length, int sampleDataPart) {
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(data, length);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long timeUs, int flags, int size, int offset, TrackOutput.CryptoData cryptoData) {
            if (this.endTimeUs != C.TIME_UNSET && timeUs >= this.endTimeUs) {
                this.trackOutput = this.fakeTrackOutput;
            }
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata(timeUs, flags, size, offset, cryptoData);
        }
    }
}
