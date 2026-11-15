package com.cloudinary.api.signing;

import com.cloudinary.SignatureAlgorithm;
import com.cloudinary.Util;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;

/* JADX INFO: loaded from: classes11.dex */
public class ApiResponseSignatureVerifier {
    private final String secretKey;
    private final SignatureAlgorithm signatureAlgorithm;

    public ApiResponseSignatureVerifier(String secretKey) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("Secret key is required");
        }
        this.secretKey = secretKey;
        this.signatureAlgorithm = SignatureAlgorithm.SHA1;
    }

    public ApiResponseSignatureVerifier(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("Secret key is required");
        }
        this.secretKey = secretKey;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public boolean verifySignature(String publicId, String version, String signature) {
        return Util.produceSignature(ObjectUtils.asMap("public_id", StringUtils.emptyIfNull(publicId), "version", StringUtils.emptyIfNull(version)), this.secretKey, this.signatureAlgorithm).equals(signature);
    }
}
