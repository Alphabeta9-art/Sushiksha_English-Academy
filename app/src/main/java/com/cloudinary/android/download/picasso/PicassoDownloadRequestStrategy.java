package com.cloudinary.android.download.picasso;

import android.widget.ImageView;
import com.cloudinary.android.download.DownloadRequestStrategy;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes11.dex */
class PicassoDownloadRequestStrategy implements DownloadRequestStrategy {
    private final ImageView imageView;
    private final Picasso picasso;

    PicassoDownloadRequestStrategy(Picasso picasso, ImageView imageView) {
        this.picasso = picasso;
        this.imageView = imageView;
    }

    @Override // com.cloudinary.android.download.DownloadRequestStrategy
    public void cancel() {
        this.picasso.cancelRequest(this.imageView);
    }
}
