package com.cloudinary.android.policy;

/* JADX INFO: loaded from: classes11.dex */
public class TimeWindow {
    private static final long DEFAULT_TIME_WINDOW = 10800000;
    private static String TAG = "TimeWindow";
    private final long maxExecutionDelayMillis;
    private final long minLatencyOffsetMillis;

    private TimeWindow(long minLatencyOffsetMillis, long maxExecutionDelayMillis) {
        this.minLatencyOffsetMillis = minLatencyOffsetMillis;
        this.maxExecutionDelayMillis = maxExecutionDelayMillis;
    }

    public static TimeWindow immediate() {
        return new TimeWindow(1L, 1000L);
    }

    public static TimeWindow getDefault() {
        return new TimeWindow(1L, DEFAULT_TIME_WINDOW);
    }

    public long getMinLatencyOffsetMillis() {
        return this.minLatencyOffsetMillis;
    }

    public long getMaxExecutionDelayMillis() {
        return this.maxExecutionDelayMillis;
    }

    public TimeWindow newDeferredWindow(int minutes) {
        long deferBy = minutes * 60 * 1000;
        return new TimeWindow(this.minLatencyOffsetMillis + deferBy, this.maxExecutionDelayMillis + deferBy);
    }

    public boolean isStartNow() {
        return this.minLatencyOffsetMillis <= 60000;
    }

    public boolean isImmediate() {
        return this.maxExecutionDelayMillis <= 60000;
    }

    public static final class Builder {
        private long minLatencyOffsetMillis = 0;
        private long maxExecutionDelayMillis = TimeWindow.DEFAULT_TIME_WINDOW;

        public Builder minLatencyMillis(long minLatencyMillis) {
            this.minLatencyOffsetMillis = minLatencyMillis;
            return this;
        }

        public Builder maxExecutionDelayMillis(long maxExecutionDelayMillis) {
            this.maxExecutionDelayMillis = maxExecutionDelayMillis;
            return this;
        }

        public TimeWindow build() {
            return new TimeWindow(this.minLatencyOffsetMillis, this.maxExecutionDelayMillis);
        }
    }
}
