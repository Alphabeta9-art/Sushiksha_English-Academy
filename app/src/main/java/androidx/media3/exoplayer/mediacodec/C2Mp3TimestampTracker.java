package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.extractor.MpegAudioUtil;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes11.dex */
final class C2Mp3TimestampTracker {
    private static final long DECODER_DELAY_FRAMES = 529;
    private static final String TAG = "C2Mp3TimestampTracker";
    private long anchorTimestampUs;
    private long processedFrames;
    private boolean seenInvalidMpegAudioHeader;

    C2Mp3TimestampTracker() {
    }

    public void reset() {
        this.anchorTimestampUs = 0L;
        this.processedFrames = 0L;
        this.seenInvalidMpegAudioHeader = false;
    }

    public long updateAndGetPresentationTimeUs(Format format, DecoderInputBuffer buffer) {
        if (this.processedFrames == 0) {
            this.anchorTimestampUs = buffer.timeUs;
        }
        if (this.seenInvalidMpegAudioHeader) {
            return buffer.timeUs;
        }
        ByteBuffer data = (ByteBuffer) Assertions.checkNotNull(buffer.data);
        int sampleHeaderData = 0;
        for (int i = 0; i < 4; i++) {
            sampleHeaderData = (sampleHeaderData << 8) | (data.get(i) & 255);
        }
        int frameCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(sampleHeaderData);
        if (frameCount == -1) {
            this.seenInvalidMpegAudioHeader = true;
            this.processedFrames = 0L;
            this.anchorTimestampUs = buffer.timeUs;
            Log.w(TAG, "MPEG audio header is invalid.");
            return buffer.timeUs;
        }
        long currentBufferTimestampUs = getBufferTimestampUs(format.sampleRate);
        this.processedFrames += (long) frameCount;
        return currentBufferTimestampUs;
    }

    public long getLastOutputBufferPresentationTimeUs(Format format) {
        return getBufferTimestampUs(format.sampleRate);
    }

    private long getBufferTimestampUs(long sampleRate) {
        return this.anchorTimestampUs + Math.max(0L, ((this.processedFrames - DECODER_DELAY_FRAMES) * 1000000) / sampleRate);
    }
}
