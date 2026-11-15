package com.linkedin.android.litr.render;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.codec.Frame;
import com.linkedin.android.litr.resample.AudioResampler;
import com.linkedin.android.litr.resample.DownsampleAudioResampler;
import com.linkedin.android.litr.resample.PassThroughAudioResampler;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes13.dex */
public class PassthroughSoftwareRenderer implements Renderer {
    static final long FRAME_WAIT_TIMEOUT = TimeUnit.SECONDS.toMicros(0);
    private static final String TAG = "PassthroughSwRenderer";
    private AudioResampler audioResampler;
    public final Encoder encoder;
    private final long frameWaitTimeoutUs;
    private MediaFormat sourceAudioFormat;
    private MediaFormat targetAudioFormat;

    public PassthroughSoftwareRenderer(Encoder encoder) {
        this(encoder, FRAME_WAIT_TIMEOUT);
    }

    public PassthroughSoftwareRenderer(Encoder encoder, long frameWaitTimeoutUs) {
        this.audioResampler = new PassThroughAudioResampler();
        this.encoder = encoder;
        this.frameWaitTimeoutUs = frameWaitTimeoutUs;
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void init(Surface outputSurface, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
        onMediaFormatChanged(sourceMediaFormat, targetMediaFormat);
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void onMediaFormatChanged(MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
        this.sourceAudioFormat = sourceMediaFormat;
        this.targetAudioFormat = targetMediaFormat;
        initAudioResampler();
    }

    private void initAudioResampler() {
        if (this.sourceAudioFormat == null || this.targetAudioFormat == null) {
            return;
        }
        int inputSampleRate = this.audioResampler.getSampleRate(this.sourceAudioFormat);
        int outputSampleRate = this.audioResampler.getSampleRate(this.targetAudioFormat);
        if (inputSampleRate > outputSampleRate) {
            this.audioResampler = new DownsampleAudioResampler();
        } else {
            this.audioResampler = new PassThroughAudioResampler();
        }
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public Surface getInputSurface() {
        return null;
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void renderFrame(Frame inputFrame, long presentationTimeNs) {
        boolean areBytesRemaining;
        if (inputFrame == null || inputFrame.buffer == null) {
            Log.e(TAG, "Null or empty input frame provided");
            return;
        }
        ByteBuffer inputBuffer = null;
        boolean isNewInputFrame = true;
        do {
            areBytesRemaining = false;
            int tag = this.encoder.dequeueInputFrame(this.frameWaitTimeoutUs);
            if (tag >= 0) {
                Frame outputFrame = this.encoder.getInputFrame(tag);
                if (outputFrame == null) {
                    Log.e(TAG, "No input frame returned by an encoder, dropping a frame");
                    return;
                }
                ByteBuffer outputBuffer = outputFrame.buffer;
                if (isNewInputFrame) {
                    isNewInputFrame = false;
                    inputBuffer = inputFrame.buffer.asReadOnlyBuffer();
                    inputBuffer.rewind();
                }
                int outSize = outputBuffer.remaining();
                int inSize = inputBuffer.remaining();
                int inputBufferLimit = inputBuffer.limit();
                if (outSize < inSize) {
                    inputBuffer.limit(inputBuffer.position() + outSize);
                }
                this.audioResampler.resample(inputBuffer, outputBuffer, this.sourceAudioFormat, this.targetAudioFormat);
                inputBuffer.limit(inputBufferLimit);
                areBytesRemaining = inputBuffer.hasRemaining();
                MediaCodec.BufferInfo bufferInfo = outputFrame.bufferInfo;
                bufferInfo.offset = 0;
                bufferInfo.size = outputBuffer.position();
                bufferInfo.presentationTimeUs = TimeUnit.NANOSECONDS.toMicros(presentationTimeNs);
                bufferInfo.flags = inputFrame.bufferInfo.flags;
                this.encoder.queueInputFrame(outputFrame);
                inputBuffer = inputBuffer;
                isNewInputFrame = isNewInputFrame;
            } else {
                switch (tag) {
                    case -1:
                        Log.e(TAG, "Encoder input frame timeout, dropping a frame");
                        break;
                    default:
                        Log.e(TAG, "Unhandled value " + tag + " when receiving decoded input frame");
                        break;
                }
            }
        } while (areBytesRemaining);
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void release() {
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public boolean hasFilters() {
        return false;
    }
}
