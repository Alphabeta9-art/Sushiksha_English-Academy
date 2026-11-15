package com.cloudinary.android.download.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.cloudinary.android.download.DownloadRequestStrategy;

/* JADX INFO: loaded from: classes11.dex */
class GlideDownloadRequestStrategy implements DownloadRequestStrategy {
    private final ViewTarget<ImageView, Drawable> target;

    GlideDownloadRequestStrategy(ViewTarget<ImageView, Drawable> target) {
        this.target = target;
    }

    @Override // com.cloudinary.android.download.DownloadRequestStrategy
    public void cancel() {
        ImageView view = (ImageView) this.target.getView();
        Glide.with(view).clear(view);
    }
}
