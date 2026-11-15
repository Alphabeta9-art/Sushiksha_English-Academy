package com.cloudinary;

/* JADX INFO: loaded from: classes11.dex */
public enum SignatureAlgorithm {
    SHA1("SHA-1"),
    SHA256("SHA-256");

    private final String algorithmId;

    SignatureAlgorithm(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getAlgorithmId() {
        return this.algorithmId;
    }
}
