package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes11.dex */
public class Limit implements Preprocess<Bitmap> {
    private final int height;
    private final int width;

    public Limit(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override // com.cloudinary.android.preprocess.Preprocess
    public Bitmap execute(Context context, Bitmap resource) {
        if (resource.getWidth() > this.width || resource.getHeight() > this.height) {
            double widthRatio = ((double) this.width) / ((double) resource.getWidth());
            double heightRatio = ((double) this.height) / ((double) resource.getHeight());
            if (heightRatio > widthRatio) {
                return Bitmap.createScaledBitmap(resource, this.width, (int) Math.round(((double) resource.getHeight()) * widthRatio), true);
            }
            return Bitmap.createScaledBitmap(resource, (int) Math.round(((double) resource.getWidth()) * heightRatio), this.height, true);
        }
        return resource;
    }
}
