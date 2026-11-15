package com.facebook.common.time;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public interface MonotonicClock {
    long nowNanos();

    default long now() {
        return TimeUnit.NANOSECONDS.toMillis(nowNanos());
    }

    static MonotonicClock of(Clock provider) {
        return new MonotonicClockWrapper(provider);
    }

    public static final class MonotonicClockWrapper implements MonotonicClock {
        private long mLast;
        private final Clock provider;

        private MonotonicClockWrapper(Clock provider) {
            this.provider = provider;
            this.mLast = provider.now();
        }

        @Override // com.facebook.common.time.MonotonicClock
        public long nowNanos() {
            return TimeUnit.MILLISECONDS.toNanos(now());
        }

        @Override // com.facebook.common.time.MonotonicClock
        public long now() {
            this.mLast = Math.max(this.mLast, this.provider.now());
            return this.mLast;
        }
    }
}
