package com.cloudinary.android.download;

import android.content.Context;
import android.widget.ImageView;
import com.cloudinary.Transformation;
import com.cloudinary.Url;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.ResponsiveUrl;
import com.cloudinary.utils.StringUtils;

/* JADX INFO: loaded from: classes11.dex */
public class DownloadRequestBuilderImpl implements DownloadRequestBuilder {
    private DownloadRequestCallback callback;
    private Context context;
    private DownloadRequestBuilderStrategy downloadRequestBuilderStrategy;
    private boolean isCloudinaryPublicIdSource;
    private int placeholder;
    private ResponsiveUrl responsive;
    private Object source;
    private Transformation transformation;

    public DownloadRequestBuilderImpl(Context context, DownloadRequestBuilderStrategy downloadRequestBuilderStrategy) {
        this.context = context;
        this.downloadRequestBuilderStrategy = downloadRequestBuilderStrategy;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder load(String source) {
        this.isCloudinaryPublicIdSource = !StringUtils.isRemoteUrl(source);
        this.source = source;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder load(int resource) {
        this.source = Integer.valueOf(resource);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder transformation(Transformation transformation) {
        this.transformation = transformation;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder responsive(ResponsiveUrl responsiveUrl) {
        this.responsive = responsiveUrl;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder responsive(ResponsiveUrl.Preset responsivePreset) {
        this.responsive = MediaManager.get().responsiveUrl(responsivePreset);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder placeholder(int resourceId) {
        this.placeholder = resourceId;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequestBuilder callback(DownloadRequestCallback callback) {
        this.callback = callback;
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilder
    public DownloadRequest into(ImageView imageView) {
        final DownloadRequestImpl downloadRequestImpl = new DownloadRequestImpl(this.downloadRequestBuilderStrategy, imageView);
        if (this.source == null) {
            throw new IllegalStateException("Source is null.");
        }
        if (this.source instanceof String) {
            if (this.isCloudinaryPublicIdSource) {
                Url url = MediaManager.get().url().publicId(this.source).transformation(this.transformation);
                if (this.responsive != null) {
                    this.responsive.generate(url, imageView, new ResponsiveUrl.Callback() { // from class: com.cloudinary.android.download.DownloadRequestBuilderImpl.1
                        @Override // com.cloudinary.android.ResponsiveUrl.Callback
                        public void onUrlReady(Url url2) {
                            downloadRequestImpl.setSource(url2.generate());
                        }
                    });
                } else {
                    downloadRequestImpl.setSource(url.generate());
                }
            } else {
                downloadRequestImpl.setSource(this.source);
            }
        } else if (this.source instanceof Integer) {
            downloadRequestImpl.setSource(this.source);
        } else {
            throw new IllegalArgumentException("Load source is not an instance of a correct type.");
        }
        if (this.placeholder != 0) {
            this.downloadRequestBuilderStrategy.placeholder(this.placeholder);
        }
        if (this.callback != null) {
            this.downloadRequestBuilderStrategy.callback(this.callback);
        }
        return downloadRequestImpl.start();
    }
}
