package com.cloudinary.android.download.fresco;

import android.content.Context;
import com.cloudinary.android.download.DownloadRequestBuilder;
import com.cloudinary.android.download.DownloadRequestBuilderFactory;
import com.cloudinary.android.download.DownloadRequestBuilderImpl;

/* JADX INFO: loaded from: classes11.dex */
public class FrescoDownloadRequestBuilderFactory implements DownloadRequestBuilderFactory {
    @Override // com.cloudinary.android.download.DownloadRequestBuilderFactory
    public DownloadRequestBuilder createDownloadRequestBuilder(Context context) {
        return new DownloadRequestBuilderImpl(context, new FrescoDownloadRequestBuilderStrategy(context));
    }
}
