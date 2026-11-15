package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class RoundedBitmapDrawable extends RoundedDrawable {
    private static boolean sDefaultRepeatEdgePixels = false;

    @Nullable
    private final Bitmap mBitmap;

    @Nullable
    private RectF mBitmapClipRect;
    private final Paint mBorderPaint;

    @Nullable
    private WeakReference<Bitmap> mLastBitmap;
    private final Paint mPaint;
    private boolean mRepeatEdgePixels;

    public static void setDefaultRepeatEdgePixels(boolean defaultRepeatEdgePixels) {
        sDefaultRepeatEdgePixels = defaultRepeatEdgePixels;
    }

    public static boolean getDefaultRepeatEdgePixels() {
        return sDefaultRepeatEdgePixels;
    }

    public RoundedBitmapDrawable(Resources res, @Nullable Bitmap bitmap, @Nullable Paint paint, boolean repeatEdgePixels) {
        super(new BitmapDrawable(res, bitmap));
        this.mPaint = new Paint();
        this.mBorderPaint = new Paint(1);
        this.mBitmapClipRect = null;
        this.mBitmap = bitmap;
        if (paint != null) {
            this.mPaint.set(paint);
        }
        this.mPaint.setFlags(1);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mRepeatEdgePixels = repeatEdgePixels;
    }

    public RoundedBitmapDrawable(Resources res, @Nullable Bitmap bitmap, @Nullable Paint paint) {
        this(res, bitmap, paint, sDefaultRepeatEdgePixels);
    }

    public RoundedBitmapDrawable(Resources res, @Nullable Bitmap bitmap) {
        this(res, bitmap, null);
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable
    protected void updateTransform() {
        super.updateTransform();
        if (!this.mRepeatEdgePixels) {
            if (this.mBitmapClipRect == null) {
                this.mBitmapClipRect = new RectF();
            }
            this.mTransform.mapRect(this.mBitmapClipRect, this.mBitmapBounds);
        }
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedBitmapDrawable#draw");
        }
        if (!shouldRound()) {
            super.draw(canvas);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        updateTransform();
        updatePath();
        updatePaint();
        int saveCount = canvas.save();
        canvas.concat(this.mInverseParentTransform);
        if (!this.mRepeatEdgePixels && this.mBitmapClipRect != null) {
            int saveCount2 = canvas.save();
            canvas.clipRect(this.mBitmapClipRect);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.restoreToCount(saveCount2);
        } else {
            canvas.drawPath(this.mPath, this.mPaint);
        }
        if (this.mBorderWidth > 0.0f) {
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
            canvas.drawPath(this.mBorderPath, this.mBorderPaint);
        }
        canvas.restoreToCount(saveCount);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private void updatePaint() {
        if (this.mLastBitmap == null || this.mLastBitmap.get() != this.mBitmap) {
            this.mLastBitmap = new WeakReference<>(this.mBitmap);
            this.mPaint.setShader(new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            this.mIsShaderTransformDirty = true;
        }
        if (this.mIsShaderTransformDirty) {
            this.mPaint.getShader().setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
        this.mPaint.setFilterBitmap(getPaintFilterBitmap());
    }

    public static RoundedBitmapDrawable fromBitmapDrawable(Resources res, BitmapDrawable bitmapDrawable) {
        return new RoundedBitmapDrawable(res, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable
    boolean shouldRound() {
        return super.shouldRound() && this.mBitmap != null;
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            super.setAlpha(alpha);
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.mPaint.setColorFilter(colorFilter);
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, com.facebook.drawee.drawable.Rounded
    public void setRepeatEdgePixels(boolean repeatEdgePixels) {
        this.mRepeatEdgePixels = repeatEdgePixels;
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public boolean getRepeatEdgePixels() {
        return this.mRepeatEdgePixels;
    }
}
