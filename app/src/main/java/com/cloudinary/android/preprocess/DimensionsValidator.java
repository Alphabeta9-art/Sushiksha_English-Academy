package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes11.dex */
public class DimensionsValidator implements Preprocess<Bitmap> {
    private final int maxHeight;
    private final int maxWidth;
    private final int minHeight;
    private final int minWidth;

    public DimensionsValidator(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override // com.cloudinary.android.preprocess.Preprocess
    public Bitmap execute(Context context, Bitmap resource) throws ValidationException {
        if (resource.getWidth() > this.maxWidth || resource.getWidth() < this.minWidth || resource.getHeight() > this.maxHeight || resource.getHeight() < this.minHeight) {
            throw new ValidationException("Resource dimensions are invalid");
        }
        return resource;
    }
}
