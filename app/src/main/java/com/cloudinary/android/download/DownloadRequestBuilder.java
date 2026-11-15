package com.cloudinary.android.download;

import android.widget.ImageView;
import com.cloudinary.Transformation;
import com.cloudinary.android.ResponsiveUrl;

/* JADX INFO: loaded from: classes11.dex */
public interface DownloadRequestBuilder {
    DownloadRequestBuilder callback(DownloadRequestCallback downloadRequestCallback);

    DownloadRequest into(ImageView imageView);

    DownloadRequestBuilder load(int i);

    DownloadRequestBuilder load(String str);

    DownloadRequestBuilder placeholder(int i);

    DownloadRequestBuilder responsive(ResponsiveUrl.Preset preset);

    DownloadRequestBuilder responsive(ResponsiveUrl responsiveUrl);

    DownloadRequestBuilder transformation(Transformation transformation);
}
