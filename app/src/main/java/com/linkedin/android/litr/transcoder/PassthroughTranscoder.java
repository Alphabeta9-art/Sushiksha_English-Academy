package com.linkedin.android.litr.transcoder;

import android.media.MediaCodec;
import android.os.Build;
import android.util.Log;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class PassthroughTranscoder extends TrackTranscoder {
    private static final int DEFAULT_BUFFER_SIZE = 1048576;
    private static final String TAG = PassthroughTranscoder.class.getSimpleName();
    int lastResult;
    ByteBuffer outputBuffer;
    MediaCodec.BufferInfo outputBufferInfo;

    PassthroughTranscoder(MediaSource mediaSource, int sourceTrack, MediaTarget mediaTarget, int targetTrack) {
        super(mediaSource, sourceTrack, mediaTarget, targetTrack, null, null, null, null);
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public void start() throws TrackTranscoderException {
        this.mediaSource.selectTrack(this.sourceTrack);
        this.outputBufferInfo = new MediaCodec.BufferInfo();
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public void stop() {
        if (this.outputBuffer != null) {
            this.outputBuffer.clear();
            this.outputBuffer = null;
        }
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public int processNextFrame() {
        int outputFlags;
        if (this.lastResult == 3) {
            return this.lastResult;
        }
        if (!this.targetTrackAdded) {
            this.targetFormat = this.mediaSource.getTrackFormat(this.sourceTrack);
            if (this.duration > 0) {
                this.targetFormat.setLong("durationUs", this.duration);
            }
            this.targetTrack = this.mediaMuxer.addTrack(this.targetFormat, this.targetTrack);
            this.targetTrackAdded = true;
            int bufferSize = this.targetFormat.containsKey("max-input-size") ? this.targetFormat.getInteger("max-input-size") : 1048576;
            this.outputBuffer = ByteBuffer.allocate(bufferSize);
            this.lastResult = 1;
            return this.lastResult;
        }
        int selectedTrack = this.mediaSource.getSelectedTrack();
        if (selectedTrack != -1 && selectedTrack != this.sourceTrack) {
            this.lastResult = 2;
            return this.lastResult;
        }
        this.lastResult = 2;
        int bytesRead = this.mediaSource.readSampleData(this.outputBuffer, 0);
        long sampleTime = this.mediaSource.getCurrentPosition();
        int inputFlags = this.mediaSource.getSampleFlags();
        if (bytesRead <= 0 || (inputFlags & 4) != 0) {
            this.outputBuffer.clear();
            this.progress = 1.0f;
            this.lastResult = 3;
            Log.d(TAG, "Reach EoS on input stream");
        } else if (sampleTime >= this.sourceMediaSelection.getEnd()) {
            this.outputBuffer.clear();
            this.progress = 1.0f;
            this.outputBufferInfo.set(0, 0, sampleTime - this.sourceMediaSelection.getStart(), this.outputBufferInfo.flags | 4);
            this.mediaMuxer.writeSampleData(this.targetTrack, this.outputBuffer, this.outputBufferInfo);
            advanceToNextTrack();
            this.lastResult = 3;
            Log.d(TAG, "Reach selection end on input stream");
        } else {
            if (sampleTime >= this.sourceMediaSelection.getStart()) {
                if ((inputFlags & 1) == 0) {
                    outputFlags = 0;
                } else {
                    int i = Build.VERSION.SDK_INT;
                    outputFlags = 1;
                }
                long sampleTime2 = sampleTime - this.sourceMediaSelection.getStart();
                if (this.duration > 0) {
                    this.progress = sampleTime2 / this.duration;
                }
                this.outputBufferInfo.set(0, bytesRead, sampleTime2, outputFlags);
                this.mediaMuxer.writeSampleData(this.targetTrack, this.outputBuffer, this.outputBufferInfo);
            }
            this.mediaSource.advance();
        }
        return this.lastResult;
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public String getEncoderName() {
        return "passthrough";
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public String getDecoderName() {
        return "passthrough";
    }
}
