package com.cloudinary.android.download.glide;

import android.content.Context;
import com.cloudinary.android.download.DownloadRequestBuilderFactory;
import com.cloudinary.android.download.DownloadRequestBuilderImpl;

/* JADX INFO: loaded from: classes11.dex */
public class GlideDownloadRequestBuilderFactory implements DownloadRequestBuilderFactory {
    @Override // com.cloudinary.android.download.DownloadRequestBuilderFactory
    public DownloadRequestBuilderImpl createDownloadRequestBuilder(Context context) {
        return new DownloadRequestBuilderImpl(context, new GlideDownloadRequestBuilderStrategy(context));
    }
}
