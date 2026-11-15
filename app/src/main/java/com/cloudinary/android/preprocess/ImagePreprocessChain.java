package com.cloudinary.android.preprocess;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePreprocessChain extends PreprocessChain<Bitmap> {
    public static ImagePreprocessChain limitDimensionsChain(int maxWidth, int maxHeight) {
        return (ImagePreprocessChain) new ImagePreprocessChain().loadWith(new BitmapDecoder(maxWidth, maxHeight)).addStep(new Limit(maxWidth, maxHeight));
    }

    @Override // com.cloudinary.android.preprocess.PreprocessChain
    protected ResourceEncoder<Bitmap> getDefaultEncoder() {
        return new BitmapEncoder();
    }

    @Override // com.cloudinary.android.preprocess.PreprocessChain
    protected ResourceDecoder<Bitmap> getDefaultDecoder() {
        return new BitmapDecoder();
    }
}
