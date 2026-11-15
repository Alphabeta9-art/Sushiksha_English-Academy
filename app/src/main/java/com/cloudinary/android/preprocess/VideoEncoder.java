package com.cloudinary.android.preprocess;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: classes11.dex */
public class VideoEncoder implements ResourceEncoder<Uri> {
    @Override // com.cloudinary.android.preprocess.ResourceEncoder
    public String encode(Context context, Uri resource) {
        return resource.toString();
    }
}
