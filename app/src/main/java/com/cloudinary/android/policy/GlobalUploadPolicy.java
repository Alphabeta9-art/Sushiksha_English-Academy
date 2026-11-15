package com.cloudinary.android.policy;

/* JADX INFO: loaded from: classes11.dex */
public class GlobalUploadPolicy extends UploadPolicy {
    private static final int DEFAULT_MAX_CONCURRENT_REQUESTS = 5;
    private final int maxConcurrentRequests;

    private GlobalUploadPolicy(UploadPolicy.NetworkType networkType, boolean requiresCharging, boolean requiresIdle, int maxErrorRetries, long backoffMillis, UploadPolicy.BackoffPolicy backoffPolicy, int maxConcurrentRequests) {
        super(networkType, requiresCharging, requiresIdle, maxErrorRetries, backoffMillis, backoffPolicy);
        this.maxConcurrentRequests = maxConcurrentRequests;
    }

    public static GlobalUploadPolicy defaultPolicy() {
        return new Builder().build();
    }

    public int getMaxConcurrentRequests() {
        return this.maxConcurrentRequests;
    }

    public static final class Builder extends UploadPolicy.BaseBuilder<Builder> {
        private int maxConcurrentRequests = 5;

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy.BaseBuilder backoffCriteria(long j, UploadPolicy.BackoffPolicy backoffPolicy) {
            return super.backoffCriteria(j, backoffPolicy);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy.BaseBuilder maxRetries(int i) {
            return super.maxRetries(i);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy.BaseBuilder networkPolicy(UploadPolicy.NetworkType networkType) {
            return super.networkPolicy(networkType);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy.BaseBuilder requiresCharging(boolean z) {
            return super.requiresCharging(z);
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public /* bridge */ /* synthetic */ UploadPolicy.BaseBuilder requiresIdle(boolean z) {
            return super.requiresIdle(z);
        }

        public Builder maxConcurrentRequests(int maxConcurrentRequests) {
            this.maxConcurrentRequests = maxConcurrentRequests;
            return this;
        }

        @Override // com.cloudinary.android.policy.UploadPolicy.BaseBuilder
        public GlobalUploadPolicy build() {
            return new GlobalUploadPolicy(this.networkPolicy, this.requiresCharging, this.requiresIdle, this.maxRetries, this.backoffMillis, this.backoffPolicy, this.maxConcurrentRequests);
        }
    }
}
