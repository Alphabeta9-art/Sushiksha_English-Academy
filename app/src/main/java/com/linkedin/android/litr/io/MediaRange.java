package com.linkedin.android.litr.io;

/* JADX INFO: loaded from: classes13.dex */
public class MediaRange {
    private final long end;
    private final long start;

    public MediaRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }
}
