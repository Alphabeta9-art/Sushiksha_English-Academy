package com.cloudinary.android.download;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes11.dex */
public interface DownloadRequestBuilderStrategy {
    DownloadRequestBuilderStrategy callback(DownloadRequestCallback downloadRequestCallback);

    DownloadRequestStrategy into(ImageView imageView);

    DownloadRequestBuilderStrategy load(int i);

    DownloadRequestBuilderStrategy load(String str);

    DownloadRequestBuilderStrategy placeholder(int i);
}
