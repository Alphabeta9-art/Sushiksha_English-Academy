package com.cloudinary.android.preprocess;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
public interface ResourceEncoder<T> {
    String encode(Context context, T t) throws ResourceCreationException;
}
