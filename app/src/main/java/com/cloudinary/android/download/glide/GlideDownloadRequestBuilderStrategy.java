package com.cloudinary.android.download.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.cloudinary.android.download.DownloadRequestBuilderStrategy;
import com.cloudinary.android.download.DownloadRequestCallback;
import com.cloudinary.android.download.DownloadRequestStrategy;

/* JADX INFO: loaded from: classes11.dex */
class GlideDownloadRequestBuilderStrategy implements DownloadRequestBuilderStrategy {
    private RequestBuilder<Drawable> requestBuilder;

    GlideDownloadRequestBuilderStrategy(Context context) {
        this.requestBuilder = Glide.with(context).asDrawable();
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(String url) {
        this.requestBuilder.load(url);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(int resourceId) {
        this.requestBuilder.load(Integer.valueOf(resourceId));
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy placeholder(int resourceId) {
        this.requestBuilder.placeholder(resourceId);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy callback(final DownloadRequestCallback callback) {
        this.requestBuilder.listener(new RequestListener<Drawable>() { // from class: com.cloudinary.android.download.glide.GlideDownloadRequestBuilderStrategy.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                callback.onFailure(e);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callback.onSuccess();
                return false;
            }
        });
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestStrategy into(ImageView imageView) {
        ViewTarget<ImageView, Drawable> target = this.requestBuilder.into(imageView);
        return new GlideDownloadRequestStrategy(target);
    }
}
