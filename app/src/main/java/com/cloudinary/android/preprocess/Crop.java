package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

/* JADX INFO: loaded from: classes11.dex */
public class Crop implements Preprocess<Bitmap> {
    private Point p1;
    private Point p2;

    public Crop(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override // com.cloudinary.android.preprocess.Preprocess
    public Bitmap execute(Context context, Bitmap resource) throws PreprocessException {
        int startX;
        int width;
        int startY;
        int height;
        checkDiagonal();
        if (this.p1.x < this.p2.x) {
            int startX2 = this.p1.x;
            startX = startX2;
            width = this.p2.x - this.p1.x;
        } else {
            int startX3 = this.p2.x;
            startX = startX3;
            width = this.p1.x - this.p2.x;
        }
        if (this.p1.y < this.p2.y) {
            int startY2 = this.p1.y;
            startY = startY2;
            height = this.p2.y - this.p1.y;
        } else {
            int startY3 = this.p2.y;
            startY = startY3;
            height = this.p1.y - this.p2.y;
        }
        checkOutOfBounds(startX, startY, width, height, resource);
        return Bitmap.createBitmap(resource, startX, startY, width, height);
    }

    private void checkDiagonal() throws PreprocessException {
        if (this.p1.x == this.p2.x || this.p1.y == this.p2.y) {
            throw new PreprocessException("Points do not make a diagonal");
        }
    }

    private void checkOutOfBounds(int startX, int startY, int width, int height, Bitmap resource) throws PreprocessException {
        boolean isOutOfBounds = false;
        if (startX < 0 || startX > resource.getWidth() || width > resource.getWidth() || startX + width > resource.getWidth() || startY < 0 || startY > resource.getHeight() || height > resource.getHeight() || startY + height > resource.getHeight()) {
            isOutOfBounds = true;
        }
        if (isOutOfBounds) {
            throw new PreprocessException("Out of bounds");
        }
    }
}
