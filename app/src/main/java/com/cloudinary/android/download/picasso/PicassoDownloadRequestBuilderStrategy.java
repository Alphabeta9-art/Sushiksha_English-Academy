package com.cloudinary.android.download.picasso;

import android.content.Context;
import android.widget.ImageView;
import com.cloudinary.android.download.DownloadRequestBuilderStrategy;
import com.cloudinary.android.download.DownloadRequestCallback;
import com.cloudinary.android.download.DownloadRequestStrategy;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes11.dex */
public class PicassoDownloadRequestBuilderStrategy implements DownloadRequestBuilderStrategy {
    private DownloadRequestCallback callback;
    private final Picasso picasso;
    private RequestCreator requestCreator;

    PicassoDownloadRequestBuilderStrategy(Context context) {
        this.picasso = new Picasso.Builder(context).build();
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(String url) {
        this.requestCreator = this.picasso.load(url);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(int resourceId) {
        this.requestCreator = this.picasso.load(resourceId);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy placeholder(int resourceId) {
        this.requestCreator.placeholder(resourceId);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy callback(DownloadRequestCallback callback) {
        this.callback = callback;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestStrategy into(ImageView imageView) {
        if (this.callback != null) {
            this.requestCreator.into(imageView, new Callback() { // from class: com.cloudinary.android.download.picasso.PicassoDownloadRequestBuilderStrategy.1
                public void onSuccess() {
                    PicassoDownloadRequestBuilderStrategy.this.callback.onSuccess();
                }

                public void onError(Exception e) {
                    PicassoDownloadRequestBuilderStrategy.this.callback.onFailure(e);
                }
            });
        } else {
            this.requestCreator.into(imageView);
        }
        return new PicassoDownloadRequestStrategy(this.picasso, imageView);
    }
}
