package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/* JADX INFO: loaded from: classes11.dex */
public class Rotate implements Preprocess<Bitmap> {
    private int rotationAngle;

    public Rotate(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    @Override // com.cloudinary.android.preprocess.Preprocess
    public Bitmap execute(Context context, Bitmap resource) {
        Matrix matrix = new Matrix();
        matrix.setRotate(this.rotationAngle);
        return Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, false);
    }
}
