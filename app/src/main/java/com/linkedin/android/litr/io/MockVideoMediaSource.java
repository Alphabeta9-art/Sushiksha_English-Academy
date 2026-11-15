package com.linkedin.android.litr.io;

import android.media.MediaFormat;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MockVideoMediaSource.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/linkedin/android/litr/io/MockVideoMediaSource;", "Lcom/linkedin/android/litr/io/MediaSource;", "trackFormat", "Landroid/media/MediaFormat;", "(Landroid/media/MediaFormat;)V", "currentPosition", "", "frameDuration", "orientationHint", "", "selectedTrack", "trackDuration", "advance", "", "getOrientationHint", "getSampleFlags", "getSampleTime", "getSampleTrackIndex", "getSize", "getTrackCount", "getTrackFormat", "track", "readSampleData", "buffer", "Ljava/nio/ByteBuffer;", TypedValues.CycleType.S_WAVE_OFFSET, "release", "seekTo", "position", "mode", "selectTrack", "litr_release"}, k = 1, mv = {1, 4, 2})
public final class MockVideoMediaSource implements MediaSource {
    private long currentPosition;
    private final long frameDuration;
    private final int orientationHint;
    private int selectedTrack;
    private final long trackDuration;
    private final MediaFormat trackFormat;

    public MockVideoMediaSource(MediaFormat trackFormat) {
        Intrinsics.checkNotNullParameter(trackFormat, "trackFormat");
        this.trackFormat = trackFormat;
        this.selectedTrack = -1;
        if (!this.trackFormat.containsKey("durationUs")) {
            throw new AssertionError("Assertion failed");
        }
        this.trackDuration = this.trackFormat.getLong("durationUs");
        if (!this.trackFormat.containsKey("frame-rate")) {
            throw new AssertionError("Assertion failed");
        }
        this.frameDuration = 1000000 / ((long) this.trackFormat.getInteger("frame-rate"));
        int i = Build.VERSION.SDK_INT;
        this.orientationHint = this.trackFormat.containsKey("rotation-degrees") ? this.trackFormat.getInteger("rotation-degrees") : 0;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getOrientationHint() {
        return this.orientationHint;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getTrackCount() {
        return 1;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public MediaFormat getTrackFormat(int track) {
        return this.trackFormat;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void selectTrack(int track) {
        this.selectedTrack = track;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void seekTo(long position, int mode) {
        this.currentPosition = position;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    /* JADX INFO: renamed from: getSampleTrackIndex, reason: from getter */
    public int getSelectedTrack() {
        return this.selectedTrack;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int readSampleData(ByteBuffer buffer, int offset) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return 1;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    /* JADX INFO: renamed from: getSampleTime, reason: from getter */
    public long getCurrentPosition() {
        return this.currentPosition;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getSampleFlags() {
        return this.currentPosition < this.trackDuration ? 0 : 4;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void advance() {
        this.currentPosition += this.frameDuration;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void release() {
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public long getSize() {
        return -1L;
    }
}
