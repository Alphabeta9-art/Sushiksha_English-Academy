package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

/* JADX INFO: loaded from: classes11.dex */
final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    public ConstantBitrateSeeker(long inputLength, long firstFramePosition, MpegAudioUtil.Header mpegAudioHeader, boolean allowSeeksIfLengthUnknown) {
        super(inputLength, firstFramePosition, mpegAudioHeader.bitrate, mpegAudioHeader.frameSize, allowSeeksIfLengthUnknown);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long position) {
        return getTimeUsAtPosition(position);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return -1L;
    }
}
