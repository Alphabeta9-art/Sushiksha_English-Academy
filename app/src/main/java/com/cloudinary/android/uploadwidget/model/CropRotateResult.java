package com.cloudinary.android.uploadwidget.model;

/* JADX INFO: loaded from: classes11.dex */
public class CropRotateResult {
    private CropPoints cropPoints;
    private int rotationAngle;

    public CropRotateResult(int rotationAngle, CropPoints cropPoints) {
        this.rotationAngle = rotationAngle;
        this.cropPoints = cropPoints;
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    public CropPoints getCropPoints() {
        return this.cropPoints;
    }
}
