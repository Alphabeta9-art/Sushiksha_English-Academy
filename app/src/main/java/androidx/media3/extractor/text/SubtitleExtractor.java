package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class SubtitleExtractor implements Extractor {
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int STATE_CREATED = 0;
    private static final int STATE_EXTRACTING = 2;
    private static final int STATE_FINISHED = 4;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_RELEASED = 5;
    private static final int STATE_SEEKING = 3;
    private int bytesRead;
    private ExtractorOutput extractorOutput;
    private final Format format;
    private final SubtitleDecoder subtitleDecoder;
    private TrackOutput trackOutput;
    private final CueEncoder cueEncoder = new CueEncoder();
    private final ParsableByteArray subtitleData = new ParsableByteArray();
    private final List<Long> timestamps = new ArrayList();
    private final List<ParsableByteArray> samples = new ArrayList();
    private int state = 0;
    private long seekTimeUs = C.TIME_UNSET;

    public SubtitleExtractor(SubtitleDecoder subtitleDecoder, Format format) {
        this.subtitleDecoder = subtitleDecoder;
        this.format = format.buildUpon().setSampleMimeType(MimeTypes.TEXT_EXOPLAYER_CUES).setCodecs(format.sampleMimeType).build();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        return true;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        Assertions.checkState(this.state == 0);
        this.extractorOutput = output;
        this.trackOutput = this.extractorOutput.track(0, 3);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, C.TIME_UNSET));
        this.trackOutput.format(this.format);
        this.state = 1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        int iCheckedCast;
        Assertions.checkState((this.state == 0 || this.state == 5) ? false : true);
        if (this.state == 1) {
            ParsableByteArray parsableByteArray = this.subtitleData;
            if (input.getLength() != -1) {
                iCheckedCast = Ints.checkedCast(input.getLength());
            } else {
                iCheckedCast = 1024;
            }
            parsableByteArray.reset(iCheckedCast);
            this.bytesRead = 0;
            this.state = 2;
        }
        if (this.state == 2) {
            boolean inputFinished = readFromInput(input);
            if (inputFinished) {
                decode();
                writeToOutput();
                this.state = 4;
            }
        }
        if (this.state == 3) {
            boolean inputFinished2 = skipInput(input);
            if (inputFinished2) {
                writeToOutput();
                this.state = 4;
            }
        }
        return this.state == 4 ? -1 : 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        Assertions.checkState((this.state == 0 || this.state == 5) ? false : true);
        this.seekTimeUs = timeUs;
        if (this.state == 2) {
            this.state = 1;
        }
        if (this.state == 4) {
            this.state = 3;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        if (this.state == 5) {
            return;
        }
        this.subtitleDecoder.release();
        this.state = 5;
    }

    private boolean skipInput(ExtractorInput input) throws IOException {
        int iCheckedCast;
        if (input.getLength() != -1) {
            iCheckedCast = Ints.checkedCast(input.getLength());
        } else {
            iCheckedCast = 1024;
        }
        return input.skip(iCheckedCast) == -1;
    }

    private boolean readFromInput(ExtractorInput input) throws IOException {
        if (this.subtitleData.capacity() == this.bytesRead) {
            this.subtitleData.ensureCapacity(this.bytesRead + 1024);
        }
        int readResult = input.read(this.subtitleData.getData(), this.bytesRead, this.subtitleData.capacity() - this.bytesRead);
        if (readResult != -1) {
            this.bytesRead += readResult;
        }
        long inputLength = input.getLength();
        return (inputLength != -1 && ((long) this.bytesRead) == inputLength) || readResult == -1;
    }

    private void decode() throws IOException {
        try {
            SubtitleInputBuffer inputBuffer = this.subtitleDecoder.dequeueInputBuffer();
            while (inputBuffer == null) {
                Thread.sleep(5L);
                inputBuffer = this.subtitleDecoder.dequeueInputBuffer();
            }
            inputBuffer.ensureSpaceForWrite(this.bytesRead);
            inputBuffer.data.put(this.subtitleData.getData(), 0, this.bytesRead);
            inputBuffer.data.limit(this.bytesRead);
            this.subtitleDecoder.queueInputBuffer(inputBuffer);
            SubtitleOutputBuffer outputBuffer = this.subtitleDecoder.dequeueOutputBuffer();
            while (outputBuffer == null) {
                Thread.sleep(5L);
                outputBuffer = this.subtitleDecoder.dequeueOutputBuffer();
            }
            for (int i = 0; i < outputBuffer.getEventTimeCount(); i++) {
                List<Cue> cues = outputBuffer.getCues(outputBuffer.getEventTime(i));
                byte[] cuesSample = this.cueEncoder.encode(cues);
                this.timestamps.add(Long.valueOf(outputBuffer.getEventTime(i)));
                this.samples.add(new ParsableByteArray(cuesSample));
            }
            outputBuffer.release();
        } catch (SubtitleDecoderException e) {
            throw ParserException.createForMalformedContainer("SubtitleDecoder failed.", e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    private void writeToOutput() {
        int index;
        Assertions.checkStateNotNull(this.trackOutput);
        Assertions.checkState(this.timestamps.size() == this.samples.size());
        if (this.seekTimeUs == C.TIME_UNSET) {
            index = 0;
        } else {
            index = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) this.timestamps, Long.valueOf(this.seekTimeUs), true, true);
        }
        for (int i = index; i < this.samples.size(); i++) {
            ParsableByteArray sample = this.samples.get(i);
            sample.setPosition(0);
            int size = sample.getData().length;
            this.trackOutput.sampleData(sample, size);
            this.trackOutput.sampleMetadata(this.timestamps.get(i).longValue(), 1, size, 0, null);
        }
    }
}
