package com.cloudinary.android.download;

import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes11.dex */
public class DownloadRequestImpl implements DownloadRequest {
    private DownloadRequestBuilderStrategy downloadRequestBuilderStrategy;
    private DownloadRequestStrategy downloadRequestStrategy;
    private WeakReference<ImageView> imageViewRef;
    private boolean isCancelled;
    private boolean shouldStart;
    private Object source;

    DownloadRequestImpl(DownloadRequestBuilderStrategy downloadRequestBuilderStrategy, ImageView imageView) {
        this.downloadRequestBuilderStrategy = downloadRequestBuilderStrategy;
        this.imageViewRef = new WeakReference<>(imageView);
    }

    synchronized void setSource(Object source) {
        if (this.source == null) {
            if (source instanceof String) {
                this.downloadRequestBuilderStrategy.load((String) source);
            } else if (source instanceof Integer) {
                this.downloadRequestBuilderStrategy.load(((Integer) source).intValue());
            }
            this.source = source;
            if (this.shouldStart && !this.isCancelled) {
                doStart();
            }
        }
    }

    synchronized DownloadRequestImpl start() {
        if (!this.isCancelled && this.downloadRequestStrategy == null) {
            this.shouldStart = true;
            if (this.source != null) {
                doStart();
            }
        }
        return this;
    }

    private void doStart() {
        this.downloadRequestStrategy = this.downloadRequestBuilderStrategy.into(this.imageViewRef.get());
    }

    @Override // com.cloudinary.android.download.DownloadRequest
    public synchronized void cancel() {
        if (this.downloadRequestStrategy != null) {
            this.downloadRequestStrategy.cancel();
        }
        this.isCancelled = true;
    }
}
