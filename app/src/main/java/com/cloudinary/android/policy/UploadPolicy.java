package com.cloudinary.android.policy;

/* JADX INFO: loaded from: classes11.dex */
public class UploadPolicy {
    private static final long DEFAULT_BACKOFF_MILLIS = 120000;
    private static final int DEFAULT_MAX_ERROR_RETRIES = 5;
    private final long backoffMillis;
    private final BackoffPolicy backoffPolicy;
    private final int maxErrorRetries;
    private final NetworkType networkType;
    private final boolean requiresCharging;
    private final boolean requiresIdle;
    private static final BackoffPolicy DEFAULT_BACKOFF_POLICY = BackoffPolicy.EXPONENTIAL;
    private static String TAG = "UploadPolicy";

    public enum BackoffPolicy {
        LINEAR,
        EXPONENTIAL
    }

    public enum NetworkType {
        NONE,
        ANY,
        UNMETERED
    }

    protected UploadPolicy(NetworkType networkType, boolean requiresCharging, boolean requiresIdle, int maxErrorRetries, long backoffMillis, BackoffPolicy backoffPolicy) {
        this.networkType = networkType;
        this.requiresCharging = requiresCharging;
        this.requiresIdle = requiresIdle;
        this.maxErrorRetries = maxErrorRetries;
        this.backoffMillis = backoffMillis;
        this.backoffPolicy = backoffPolicy;
    }

    public static UploadPolicy defaultPolicy() {
        return new Builder().build();
    }

    public NetworkType getNetworkType() {
        return this.networkType;
    }

    public boolean isRequiresCharging() {
        return this.requiresCharging;
    }

    public boolean isRequiresIdle() {
        return this.requiresIdle;
    }

    public int getMaxErrorRetries() {
        return this.maxErrorRetries;
    }

    public BackoffPolicy getBackoffPolicy() {
        return this.backoffPolicy;
    }

    public long getBackoffMillis() {
        return this.backoffMillis;
    }

    public boolean hasRequirements() {
        return this.requiresCharging || this.requiresIdle || this.networkType == NetworkType.UNMETERED;
    }

    public Builder newBuilder() {
        return (Builder) ((Builder) ((Builder) ((Builder) ((Builder) new Builder().requiresCharging(this.requiresCharging)).requiresIdle(this.requiresIdle)).backoffCriteria(this.backoffMillis, this.backoffPolicy)).maxRetries(this.maxErrorRetries)).networkPolicy(this.networkType);
    }

    static abstract class BaseBuilder<T extends BaseBuilder> {
        NetworkType networkPolicy = NetworkType.ANY;
        boolean requiresCharging = false;
        boolean requiresIdle = false;
        int maxRetries = 5;
        long backoffMillis = UploadPolicy.DEFAULT_BACKOFF_MILLIS;
        BackoffPolicy backoffPolicy = UploadPolicy.DEFAULT_BACKOFF_POLICY;

        BaseBuilder() {
        }

        public T networkPolicy(NetworkType networkPolicy) {
            if (networkPolicy == NetworkType.NONE) {
                throw new IllegalArgumentException("An upload request requires network");
            }
            this.networkPolicy = networkPolicy;
            return this;
        }

        public T requiresCharging(boolean requiresCharging) {
            this.requiresCharging = requiresCharging;
            return this;
        }

        public T requiresIdle(boolean requiresIdle) {
            this.requiresIdle = requiresIdle;
            return this;
        }

        public T maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public T backoffCriteria(long backoffMs, BackoffPolicy backoffPolicy) {
            this.backoffMillis = backoffMs;
            this.backoffPolicy = backoffPolicy;
            return this;
        }

        public UploadPolicy build() {
            return new UploadPolicy(this.networkPolicy, this.requiresCharging, this.requiresIdle, this.maxRetries, this.backoffMillis, this.backoffPolicy);
        }
    }

    public static class Builder extends BaseBuilder<Builder> {
        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ BaseBuilder backoffCriteria(long j, BackoffPolicy backoffPolicy) {
            return super.backoffCriteria(j, backoffPolicy);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy build() {
            return super.build();
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ BaseBuilder maxRetries(int i) {
            return super.maxRetries(i);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ BaseBuilder networkPolicy(NetworkType networkType) {
            return super.networkPolicy(networkType);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ BaseBuilder requiresCharging(boolean z) {
            return super.requiresCharging(z);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ BaseBuilder requiresIdle(boolean z) {
            return super.requiresIdle(z);
        }
    }
}
