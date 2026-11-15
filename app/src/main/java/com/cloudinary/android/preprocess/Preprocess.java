package com.cloudinary.android.preprocess;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
public interface Preprocess<T> {
    T execute(Context context, T t) throws PreprocessException;
}
