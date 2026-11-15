package com.cloudinary.android.preprocess;

import android.content.Context;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadNotFoundException;

/* JADX INFO: loaded from: classes11.dex */
public interface ResourceDecoder<T> {
    T decode(Context context, Payload payload) throws PayloadNotFoundException, PayloadDecodeException;
}
