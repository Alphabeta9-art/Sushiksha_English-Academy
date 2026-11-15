package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.common.RotationOptions;

/* JADX INFO: loaded from: classes11.dex */
public class OrientedDrawable extends ForwardingDrawable {
    private int mExifOrientation;
    private int mRotationAngle;
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;

    public OrientedDrawable(Drawable drawable, int rotationAngle) {
        this(drawable, rotationAngle, 0);
    }

    public OrientedDrawable(Drawable drawable, int rotationAngle, int exifOrientation) {
        int i;
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = rotationAngle - (rotationAngle % 90);
        if (exifOrientation >= 0 && exifOrientation <= 8) {
            i = exifOrientation;
        } else {
            i = 0;
        }
        this.mExifOrientation = i;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mRotationAngle <= 0 && (this.mExifOrientation == 0 || this.mExifOrientation == 1)) {
            super.draw(canvas);
            return;
        }
        int saveCount = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.mExifOrientation == 5 || this.mExifOrientation == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.mExifOrientation == 5 || this.mExifOrientation == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Drawable underlyingDrawable = getCurrent();
        if (this.mRotationAngle > 0 || (this.mExifOrientation != 0 && this.mExifOrientation != 1)) {
            switch (this.mExifOrientation) {
                case 2:
                    this.mRotationMatrix.setScale(-1.0f, 1.0f);
                    break;
                case 3:
                case 6:
                default:
                    this.mRotationMatrix.setRotate(this.mRotationAngle, bounds.centerX(), bounds.centerY());
                    break;
                case 4:
                    this.mRotationMatrix.setScale(1.0f, -1.0f);
                    break;
                case 5:
                    this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
                    this.mRotationMatrix.postScale(1.0f, -1.0f);
                    break;
                case 7:
                    this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
                    this.mRotationMatrix.postScale(-1.0f, 1.0f);
                    break;
            }
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(bounds);
            this.mTempMatrix.mapRect(this.mTempRectF);
            underlyingDrawable.setBounds((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            return;
        }
        underlyingDrawable.setBounds(bounds);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        if (!this.mRotationMatrix.isIdentity()) {
            transform.preConcat(this.mRotationMatrix);
        }
    }
}
