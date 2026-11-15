package com.cloudinary.transformation;

import com.cloudinary.utils.Base64Coder;

/* JADX INFO: loaded from: classes11.dex */
public class FetchLayer extends AbstractLayer<FetchLayer> {
    public FetchLayer() {
        this.type = "fetch";
    }

    public FetchLayer url(String remoteUrl) {
        this.publicId = Base64Coder.encodeURLSafeString(remoteUrl);
        return this;
    }

    @Override // com.cloudinary.transformation.AbstractLayer
    public FetchLayer type(String type) {
        throw new UnsupportedOperationException("Cannot modify type for fetch layers");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.cloudinary.transformation.AbstractLayer
    public FetchLayer getThis() {
        return this;
    }
}
