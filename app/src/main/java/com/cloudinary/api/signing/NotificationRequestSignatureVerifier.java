package com.cloudinary.api.signing;

import com.cloudinary.SignatureAlgorithm;
import com.cloudinary.utils.StringUtils;

/* JADX INFO: loaded from: classes11.dex */
public class NotificationRequestSignatureVerifier {
    private final SignedPayloadValidator signedPayloadValidator;

    public NotificationRequestSignatureVerifier(String secretKey) {
        this.signedPayloadValidator = new SignedPayloadValidator(secretKey, SignatureAlgorithm.SHA1);
    }

    public NotificationRequestSignatureVerifier(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        this.signedPayloadValidator = new SignedPayloadValidator(secretKey, signatureAlgorithm);
    }

    public boolean verifySignature(String body, String timestamp, String signature) {
        return this.signedPayloadValidator.validateSignedPayload(StringUtils.emptyIfNull(body) + StringUtils.emptyIfNull(timestamp), signature);
    }

    public boolean verifySignature(String body, String timestamp, String signature, long secondsValidFor) {
        try {
            long parsedTimestamp = Long.parseLong(timestamp);
            return verifySignature(body, timestamp, signature) && (System.currentTimeMillis() / 1000) - parsedTimestamp <= secondsValidFor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Provided timestamp is not a valid number", e);
        }
    }
}
