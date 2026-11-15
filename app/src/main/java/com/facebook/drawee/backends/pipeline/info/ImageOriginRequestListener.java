package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.listener.BaseRequestListener;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImageOriginRequestListener extends BaseRequestListener {
    private String mControllerId;

    @Nullable
    private final ImageOriginListener mImageOriginLister;

    public ImageOriginRequestListener(String controllerId, @Nullable ImageOriginListener imageOriginLister) {
        this.mImageOriginLister = imageOriginLister;
        init(controllerId);
    }

    public void init(String controllerId) {
        this.mControllerId = controllerId;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onUltimateProducerReached(String requestId, String ultimateProducerName, boolean successful) {
        if (this.mImageOriginLister != null) {
            this.mImageOriginLister.onImageLoaded(this.mControllerId, ImageOriginUtils.mapProducerNameToImageOrigin(ultimateProducerName), successful, ultimateProducerName);
        }
    }
}
