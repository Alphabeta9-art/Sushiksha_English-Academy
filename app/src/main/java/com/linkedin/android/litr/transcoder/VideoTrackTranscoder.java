package com.linkedin.android.litr.transcoder;

import android.media.MediaFormat;
import android.util.Log;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.codec.Frame;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.GlVideoRenderer;
import com.linkedin.android.litr.render.Renderer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes13.dex */
public class VideoTrackTranscoder extends TrackTranscoder {
    private static final String TAG = VideoTrackTranscoder.class.getSimpleName();
    int lastDecodeFrameResult;
    int lastEncodeFrameResult;
    int lastExtractFrameResult;
    GlVideoRenderer renderer;
    private MediaFormat sourceVideoFormat;
    private MediaFormat targetVideoFormat;

    VideoTrackTranscoder(MediaSource mediaSource, int sourceTrack, MediaTarget mediaTarget, int targetTrack, MediaFormat targetFormat, Renderer renderer, Decoder decoder, Encoder encoder) throws TrackTranscoderException {
        super(mediaSource, sourceTrack, mediaTarget, targetTrack, targetFormat, renderer, decoder, encoder);
        this.lastExtractFrameResult = 2;
        this.lastDecodeFrameResult = 2;
        this.lastEncodeFrameResult = 2;
        this.targetVideoFormat = targetFormat;
        if (!(renderer instanceof GlVideoRenderer)) {
            throw new IllegalArgumentException("Cannot use non-OpenGL video renderer in " + VideoTrackTranscoder.class.getSimpleName());
        }
        this.renderer = (GlVideoRenderer) renderer;
        initCodecs();
    }

    private void initCodecs() throws TrackTranscoderException {
        this.sourceVideoFormat = this.mediaSource.getTrackFormat(this.sourceTrack);
        if (this.sourceVideoFormat.containsKey("frame-rate")) {
            int sourceFrameRate = this.sourceVideoFormat.getInteger("frame-rate");
            this.targetVideoFormat.setInteger("frame-rate", sourceFrameRate);
        }
        this.encoder.init(this.targetFormat);
        this.renderer.init(this.encoder.createInputSurface(), this.sourceVideoFormat, this.targetVideoFormat);
        this.decoder.init(this.sourceVideoFormat, this.renderer.getInputSurface());
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public void start() throws TrackTranscoderException {
        this.mediaSource.selectTrack(this.sourceTrack);
        this.encoder.start();
        this.decoder.start();
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public void stop() {
        this.encoder.stop();
        this.encoder.release();
        this.decoder.stop();
        this.decoder.release();
        this.renderer.release();
    }

    @Override // com.linkedin.android.litr.transcoder.TrackTranscoder
    public int processNextFrame() throws TrackTranscoderException {
        if (!this.encoder.isRunning() || !this.decoder.isRunning()) {
            return -3;
        }
        int result = 2;
        if (this.lastExtractFrameResult != 3) {
            this.lastExtractFrameResult = extractAndEnqueueInputFrame();
        }
        if (this.lastDecodeFrameResult != 3) {
            this.lastDecodeFrameResult = resizeDecodedInputFrame();
        }
        if (this.lastEncodeFrameResult != 3) {
            this.lastEncodeFrameResult = writeEncodedOutputFrame();
        }
        if (this.lastEncodeFrameResult == 1) {
            result = 1;
        }
        if (this.lastExtractFrameResult == 3 && this.lastDecodeFrameResult == 3 && this.lastEncodeFrameResult == 3) {
            return 3;
        }
        return result;
    }

    private int extractAndEnqueueInputFrame() throws TrackTranscoderException {
        int selectedTrack = this.mediaSource.getSelectedTrack();
        if (selectedTrack != this.sourceTrack && selectedTrack != -1) {
            return 2;
        }
        int tag = this.decoder.dequeueInputFrame(0L);
        if (tag >= 0) {
            Frame frame = this.decoder.getInputFrame(tag);
            if (frame == null) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.NO_FRAME_AVAILABLE);
            }
            int bytesRead = this.mediaSource.readSampleData(frame.buffer, 0);
            long sampleTime = this.mediaSource.getCurrentPosition();
            int sampleFlags = this.mediaSource.getSampleFlags();
            if (bytesRead <= 0 || (sampleFlags & 4) != 0) {
                frame.bufferInfo.set(0, 0, -1L, 4);
                this.decoder.queueInputFrame(frame);
                Log.d(TAG, "EoS reached on the input stream");
                return 3;
            }
            if (sampleTime >= this.sourceMediaSelection.getEnd()) {
                frame.bufferInfo.set(0, 0, -1L, 4);
                this.decoder.queueInputFrame(frame);
                advanceToNextTrack();
                Log.d(TAG, "EoS reached on the input stream");
                return 3;
            }
            frame.bufferInfo.set(0, bytesRead, sampleTime, sampleFlags);
            this.decoder.queueInputFrame(frame);
            this.mediaSource.advance();
            return 2;
        }
        switch (tag) {
            case -1:
                return 2;
            default:
                Log.e(TAG, "Unhandled value " + tag + " when decoding an input frame");
                return 2;
        }
    }

    private int resizeDecodedInputFrame() throws TrackTranscoderException {
        int tag = this.decoder.dequeueOutputFrame(0L);
        if (tag >= 0) {
            Frame frame = this.decoder.getOutputFrame(tag);
            if (frame == null) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.NO_FRAME_AVAILABLE);
            }
            if ((frame.bufferInfo.flags & 4) != 0) {
                Log.d(TAG, "EoS on decoder output stream");
                this.decoder.releaseOutputFrame(tag, false);
                this.encoder.signalEndOfInputStream();
                return 3;
            }
            boolean isFrameAfterSelectionStart = frame.bufferInfo.presentationTimeUs >= this.sourceMediaSelection.getStart();
            this.decoder.releaseOutputFrame(tag, isFrameAfterSelectionStart);
            if (!isFrameAfterSelectionStart) {
                return 2;
            }
            this.renderer.renderFrame(null, TimeUnit.MICROSECONDS.toNanos(frame.bufferInfo.presentationTimeUs - this.sourceMediaSelection.getStart()));
            return 2;
        }
        switch (tag) {
            case -2:
                this.sourceVideoFormat = this.decoder.getOutputFormat();
                this.renderer.onMediaFormatChanged(this.sourceVideoFormat, this.targetVideoFormat);
                Log.d(TAG, "Decoder output format changed: " + this.sourceVideoFormat);
                return 2;
            case -1:
                return 2;
            default:
                Log.e(TAG, "Unhandled value " + tag + " when receiving decoded input frame");
                return 2;
        }
    }

    private int writeEncodedOutputFrame() throws TrackTranscoderException {
        int encodeFrameResult = 2;
        int index = this.encoder.dequeueOutputFrame(0L);
        if (index >= 0) {
            Frame frame = this.encoder.getOutputFrame(index);
            if (frame == null) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.NO_FRAME_AVAILABLE);
            }
            if ((frame.bufferInfo.flags & 4) != 0) {
                Log.d(TAG, "Encoder produced EoS, we are done");
                this.progress = 1.0f;
                encodeFrameResult = 3;
            } else if (frame.bufferInfo.size > 0 && (frame.bufferInfo.flags & 2) == 0) {
                this.mediaMuxer.writeSampleData(this.targetTrack, frame.buffer, frame.bufferInfo);
                if (this.duration > 0) {
                    this.progress = frame.bufferInfo.presentationTimeUs / this.duration;
                }
            }
            this.encoder.releaseOutputFrame(index);
            return encodeFrameResult;
        }
        switch (index) {
            case -2:
                MediaFormat outputMediaFormat = this.encoder.getOutputFormat();
                if (!this.targetTrackAdded) {
                    this.targetFormat = outputMediaFormat;
                    this.targetVideoFormat = outputMediaFormat;
                    this.targetTrack = this.mediaMuxer.addTrack(outputMediaFormat, this.targetTrack);
                    this.targetTrackAdded = true;
                    this.renderer.onMediaFormatChanged(this.sourceVideoFormat, this.targetVideoFormat);
                }
                Log.d(TAG, "Encoder output format received " + outputMediaFormat);
                return 1;
            case -1:
                return 2;
            default:
                Log.e(TAG, "Unhandled value " + index + " when receiving encoded output frame");
                return 2;
        }
    }
}
