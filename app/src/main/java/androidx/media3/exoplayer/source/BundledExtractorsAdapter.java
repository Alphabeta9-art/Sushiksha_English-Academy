package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0077  */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> responseHeaders, long position, long length, ExtractorOutput output) throws IOException {
        ExtractorInput extractorInput = new DefaultExtractorInput(dataReader, position, length);
        this.extractorInput = extractorInput;
        if (this.extractor != null) {
            return;
        }
        Extractor[] extractors = this.extractorsFactory.createExtractors(uri, responseHeaders);
        if (extractors.length == 1) {
            this.extractor = extractors[0];
        } else {
            for (Extractor extractor : extractors) {
                try {
                    if (extractor.sniff(extractorInput)) {
                        this.extractor = extractor;
                        Assertions.checkState(this.extractor != null || extractorInput.getPosition() == position);
                        extractorInput.resetPeekPosition();
                        break;
                    } else {
                        boolean z = this.extractor != null || extractorInput.getPosition() == position;
                        Assertions.checkState(z);
                        extractorInput.resetPeekPosition();
                    }
                } catch (EOFException e) {
                    if (this.extractor != null || extractorInput.getPosition() == position) {
                    }
                } catch (Throwable th) {
                    Assertions.checkState(this.extractor != null || extractorInput.getPosition() == position);
                    extractorInput.resetPeekPosition();
                    throw th;
                }
                Assertions.checkState(z);
                extractorInput.resetPeekPosition();
            }
            if (this.extractor == null) {
                throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(extractors) + ") could read the stream.", (Uri) Assertions.checkNotNull(uri));
            }
        }
        this.extractor.init(output);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        if (this.extractor != null) {
            this.extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        if (this.extractor instanceof Mp3Extractor) {
            ((Mp3Extractor) this.extractor).disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        if (this.extractorInput != null) {
            return this.extractorInput.getPosition();
        }
        return -1L;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long position, long seekTimeUs) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(position, seekTimeUs);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }
}
