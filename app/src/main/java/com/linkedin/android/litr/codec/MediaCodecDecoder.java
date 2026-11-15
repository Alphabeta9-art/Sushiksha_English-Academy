package com.linkedin.android.litr.codec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.utils.CodecUtils;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public final class MediaCodecDecoder implements Decoder {
    private boolean isReleased;
    private boolean isRunning;
    private MediaCodec mediaCodec;
    private final MediaCodec.BufferInfo outputBufferInfo = new MediaCodec.BufferInfo();

    @Override // com.linkedin.android.litr.codec.Decoder
    public void init(MediaFormat mediaFormat, Surface surface) throws TrackTranscoderException {
        this.mediaCodec = CodecUtils.getAndConfigureCodec(mediaFormat, surface, false, TrackTranscoderException.Error.DECODER_NOT_FOUND, TrackTranscoderException.Error.DECODER_FORMAT_NOT_FOUND, TrackTranscoderException.Error.DECODER_CONFIGURATION_ERROR);
        this.isReleased = this.mediaCodec == null;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void start() throws TrackTranscoderException {
        if (this.mediaCodec == null) {
            throw new IllegalStateException("Codec is not initialized");
        }
        if (!this.isRunning) {
            try {
                startDecoder();
            } catch (Exception codecException) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.INTERNAL_CODEC_ERROR, codecException);
            }
        }
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public int dequeueInputFrame(long timeout) {
        return this.mediaCodec.dequeueInputBuffer(timeout);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public Frame getInputFrame(int tag) {
        if (tag < 0) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        ByteBuffer inputBuffer = this.mediaCodec.getInputBuffer(tag);
        return new Frame(tag, inputBuffer, null);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void queueInputFrame(Frame frame) {
        this.mediaCodec.queueInputBuffer(frame.tag, frame.bufferInfo.offset, frame.bufferInfo.size, frame.bufferInfo.presentationTimeUs, frame.bufferInfo.flags);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public int dequeueOutputFrame(long timeout) {
        return this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, timeout);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public Frame getOutputFrame(int tag) {
        if (tag >= 0) {
            int i = Build.VERSION.SDK_INT;
            ByteBuffer buffer = this.mediaCodec.getOutputBuffer(tag);
            return new Frame(tag, buffer, this.outputBufferInfo);
        }
        return null;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void releaseOutputFrame(int tag, boolean render) {
        this.mediaCodec.releaseOutputBuffer(tag, render);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public MediaFormat getOutputFormat() {
        return this.mediaCodec.getOutputFormat();
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void stop() {
        if (this.isRunning) {
            this.mediaCodec.stop();
            this.isRunning = false;
        }
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void release() {
        if (!this.isReleased) {
            this.mediaCodec.release();
            this.isReleased = true;
        }
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public String getName() throws TrackTranscoderException {
        try {
            return this.mediaCodec.getName();
        } catch (IllegalStateException e) {
            throw new TrackTranscoderException(TrackTranscoderException.Error.CODEC_IN_RELEASED_STATE, e);
        }
    }

    private void startDecoder() {
        this.mediaCodec.start();
        this.isRunning = true;
    }
}
