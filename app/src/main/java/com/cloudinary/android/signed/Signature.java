package com.cloudinary.android.signed;

/* JADX INFO: loaded from: classes11.dex */
public class Signature {
    private final String apiKey;
    private final String signature;
    private final long timestamp;

    public Signature(String signature, String apiKey, long timestamp) {
        this.signature = signature;
        this.apiKey = apiKey;
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
