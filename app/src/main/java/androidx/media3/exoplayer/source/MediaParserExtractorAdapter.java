package androidx.media3.exoplayer.source;

import android.media.MediaParser;
import android.net.Uri;
import android.util.Pair;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.mediaparser.InputReaderAdapterV30;
import androidx.media3.exoplayer.source.mediaparser.MediaParserUtil;
import androidx.media3.exoplayer.source.mediaparser.OutputConsumerAdapterV30;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public final class MediaParserExtractorAdapter implements ProgressiveMediaExtractor {
    public static final ProgressiveMediaExtractor.Factory FACTORY = new ProgressiveMediaExtractor.Factory() { // from class: androidx.media3.exoplayer.source.MediaParserExtractorAdapter$$ExternalSyntheticLambda0
        @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor.Factory
        public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
            return new MediaParserExtractorAdapter(playerId);
        }
    };
    private String parserName;
    private final OutputConsumerAdapterV30 outputConsumerAdapter = new OutputConsumerAdapterV30();
    private final InputReaderAdapterV30 inputReaderAdapter = new InputReaderAdapterV30();
    private final MediaParser mediaParser = MediaParser.create(this.outputConsumerAdapter, new String[0]);

    public MediaParserExtractorAdapter(PlayerId playerId) {
        this.mediaParser.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, true);
        this.mediaParser.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, true);
        this.mediaParser.setParameter(MediaParserUtil.PARAMETER_INCLUDE_SUPPLEMENTAL_DATA, true);
        this.parserName = "android.media.mediaparser.UNKNOWN";
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(this.mediaParser, playerId);
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> responseHeaders, long position, long length, ExtractorOutput output) throws IOException {
        this.outputConsumerAdapter.setExtractorOutput(output);
        this.inputReaderAdapter.setDataReader(dataReader, length);
        this.inputReaderAdapter.setCurrentPosition(position);
        String currentParserName = this.mediaParser.getParserName();
        if ("android.media.mediaparser.UNKNOWN".equals(currentParserName)) {
            this.mediaParser.advance(this.inputReaderAdapter);
            this.parserName = this.mediaParser.getParserName();
            this.outputConsumerAdapter.setSelectedParserName(this.parserName);
        } else if (!currentParserName.equals(this.parserName)) {
            this.parserName = this.mediaParser.getParserName();
            this.outputConsumerAdapter.setSelectedParserName(this.parserName);
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        this.mediaParser.release();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        if ("android.media.mediaparser.Mp3Parser".equals(this.parserName)) {
            this.outputConsumerAdapter.disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        return this.inputReaderAdapter.getPosition();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long position, long seekTimeUs) {
        this.inputReaderAdapter.setCurrentPosition(position);
        Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.outputConsumerAdapter.getSeekPoints(seekTimeUs);
        this.mediaParser.seek((MediaParser.SeekPoint) (((MediaParser.SeekPoint) seekPoints.second).position == position ? seekPoints.second : seekPoints.first));
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        boolean shouldContinue = this.mediaParser.advance(this.inputReaderAdapter);
        positionHolder.position = this.inputReaderAdapter.getAndResetSeekPosition();
        if (!shouldContinue) {
            return -1;
        }
        if (positionHolder.position != -1) {
            return 1;
        }
        return 0;
    }
}
