package com.linkedin.android.litr.codec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.utils.CodecUtils;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class MediaCodecEncoder implements Encoder {
    private boolean isRunning;
    private MediaCodec mediaCodec;
    private boolean isReleased = true;
    private final MediaCodec.BufferInfo encoderOutputBufferInfo = new MediaCodec.BufferInfo();

    @Override // com.linkedin.android.litr.codec.Encoder
    public void init(MediaFormat targetFormat) throws TrackTranscoderException {
        if (!targetFormat.containsKey("color-format")) {
            targetFormat.setInteger("color-format", 2130708361);
        }
        this.mediaCodec = CodecUtils.getAndConfigureCodec(targetFormat, null, true, TrackTranscoderException.Error.ENCODER_NOT_FOUND, TrackTranscoderException.Error.ENCODER_FORMAT_NOT_FOUND, TrackTranscoderException.Error.ENCODER_CONFIGURATION_ERROR);
        this.isReleased = this.mediaCodec == null;
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public Surface createInputSurface() {
        return this.mediaCodec.createInputSurface();
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void start() throws TrackTranscoderException {
        try {
            startEncoder();
        } catch (Exception codecException) {
            throw new TrackTranscoderException(TrackTranscoderException.Error.INTERNAL_CODEC_ERROR, codecException);
        }
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public int dequeueInputFrame(long timeout) {
        return this.mediaCodec.dequeueInputBuffer(timeout);
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public Frame getInputFrame(int tag) {
        if (tag < 0) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        ByteBuffer inputBuffer = this.mediaCodec.getInputBuffer(tag);
        return new Frame(tag, inputBuffer, null);
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void queueInputFrame(Frame frame) {
        this.mediaCodec.queueInputBuffer(frame.tag, frame.bufferInfo.offset, frame.bufferInfo.size, frame.bufferInfo.presentationTimeUs, frame.bufferInfo.flags);
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void signalEndOfInputStream() {
        this.mediaCodec.signalEndOfInputStream();
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public int dequeueOutputFrame(long timeout) {
        return this.mediaCodec.dequeueOutputBuffer(this.encoderOutputBufferInfo, timeout);
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public Frame getOutputFrame(int tag) {
        if (tag >= 0) {
            int i = Build.VERSION.SDK_INT;
            ByteBuffer buffer = this.mediaCodec.getOutputBuffer(tag);
            return new Frame(tag, buffer, this.encoderOutputBufferInfo);
        }
        return null;
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void releaseOutputFrame(int tag) {
        this.mediaCodec.releaseOutputBuffer(tag, false);
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public MediaFormat getOutputFormat() {
        return this.mediaCodec.getOutputFormat();
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void stop() {
        if (this.isRunning) {
            this.mediaCodec.stop();
            this.isRunning = false;
        }
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public void release() {
        if (!this.isReleased) {
            this.mediaCodec.release();
            this.isReleased = true;
        }
    }

    @Override // com.linkedin.android.litr.codec.Encoder
    public String getName() throws TrackTranscoderException {
        try {
            return this.mediaCodec.getName();
        } catch (IllegalStateException e) {
            throw new TrackTranscoderException(TrackTranscoderException.Error.CODEC_IN_RELEASED_STATE, e);
        }
    }

    private void startEncoder() {
        if (!this.isRunning) {
            this.mediaCodec.start();
            this.isRunning = true;
        }
    }
}
