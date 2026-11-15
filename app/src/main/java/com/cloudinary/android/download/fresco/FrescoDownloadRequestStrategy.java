package com.cloudinary.android.download.fresco;

import com.cloudinary.android.download.DownloadRequestStrategy;

/* JADX INFO: loaded from: classes11.dex */
class FrescoDownloadRequestStrategy implements DownloadRequestStrategy {
    FrescoDownloadRequestStrategy() {
    }

    @Override // com.cloudinary.android.download.DownloadRequestStrategy
    public void cancel() {
        throw new UnsupportedOperationException("Fresco doesn't support cancellation of download requests.");
    }
}
