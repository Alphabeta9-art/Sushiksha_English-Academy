package com.linkedin.android.litr.codec;

import android.graphics.Canvas;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PassthroughDecoder.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0007H\u0016J\b\u0010!\u001a\u00020\u001eH\u0016J\u0018\u0010\"\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u000eH\u0016J\b\u0010$\u001a\u00020\u001eH\u0016J\b\u0010%\u001a\u00020\u001eH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/linkedin/android/litr/codec/PassthroughDecoder;", "Lcom/linkedin/android/litr/codec/Decoder;", "inputBufferCapacity", "", "(I)V", "availableFrames", "", "Lcom/linkedin/android/litr/codec/Frame;", "decodeQueue", "", "decodedFrames", "", "dequeuedInputFrames", "isRunning", "", "mediaFormat", "Landroid/media/MediaFormat;", "surface", "Landroid/view/Surface;", "dequeueInputFrame", "timeout", "", "dequeueOutputFrame", "getInputFrame", "tag", "getName", "", "getOutputFormat", "getOutputFrame", "init", "", "queueInputFrame", TypedValues.AttributesType.S_FRAME, "release", "releaseOutputFrame", "render", TtmlNode.START, "stop", "litr_release"}, k = 1, mv = {1, 4, 2})
public final class PassthroughDecoder implements Decoder {
    private boolean isRunning;
    private MediaFormat mediaFormat;
    private Surface surface;
    private final Set<Frame> availableFrames = new LinkedHashSet();
    private final Map<Integer, Frame> dequeuedInputFrames = new LinkedHashMap();
    private final List<Integer> decodeQueue = new ArrayList();
    private final Map<Integer, Frame> decodedFrames = new LinkedHashMap();

    public PassthroughDecoder(int inputBufferCapacity) {
        for (int tag = 0; tag < 2; tag++) {
            this.availableFrames.add(new Frame(tag, ByteBuffer.allocate(inputBufferCapacity), new MediaCodec.BufferInfo()));
        }
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void init(MediaFormat mediaFormat, Surface surface) {
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        this.mediaFormat = mediaFormat;
        this.surface = surface;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void start() {
        this.isRunning = true;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    /* JADX INFO: renamed from: isRunning, reason: from getter */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public int dequeueInputFrame(long timeout) {
        Frame availableFrame = (Frame) CollectionsKt.firstOrNull(this.availableFrames);
        if (availableFrame != null) {
            this.availableFrames.remove(availableFrame);
            this.dequeuedInputFrames.put(Integer.valueOf(availableFrame.tag), availableFrame);
            return availableFrame.tag;
        }
        return -1;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public Frame getInputFrame(int tag) {
        return this.dequeuedInputFrames.get(Integer.valueOf(tag));
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void queueInputFrame(Frame frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        this.dequeuedInputFrames.remove(Integer.valueOf(frame.tag));
        this.decodeQueue.add(Integer.valueOf(frame.tag));
        this.decodedFrames.put(Integer.valueOf(frame.tag), frame);
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public int dequeueOutputFrame(long timeout) {
        if (this.decodeQueue.isEmpty()) {
            return -1;
        }
        return this.decodeQueue.remove(0).intValue();
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public Frame getOutputFrame(int tag) {
        return this.decodedFrames.get(Integer.valueOf(tag));
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void releaseOutputFrame(int tag, boolean render) {
        Surface surface = this.surface;
        if (surface != null && render) {
            Canvas canvas = surface.lockCanvas(null);
            surface.unlockCanvasAndPost(canvas);
        }
        Frame it = this.decodedFrames.remove(Integer.valueOf(tag));
        if (it != null) {
            this.availableFrames.add(it);
        }
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat = this.mediaFormat;
        if (mediaFormat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaFormat");
        }
        return mediaFormat;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void stop() {
        this.isRunning = false;
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public void release() {
    }

    @Override // com.linkedin.android.litr.codec.Decoder
    public String getName() {
        return "PassthroughDecoder";
    }
}
