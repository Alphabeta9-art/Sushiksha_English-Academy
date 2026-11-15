package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ScaleTypeDrawable extends ForwardingDrawable {

    @Nullable
    Matrix mDrawMatrix;

    @Nullable
    PointF mFocusPoint;
    ScalingUtils.ScaleType mScaleType;

    @Nullable
    Object mScaleTypeState;
    private Matrix mTempMatrix;
    int mUnderlyingHeight;
    int mUnderlyingWidth;

    public ScaleTypeDrawable(@Nullable Drawable drawable, ScalingUtils.ScaleType scaleType) {
        super(drawable);
        this.mFocusPoint = null;
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mTempMatrix = new Matrix();
        this.mScaleType = scaleType;
    }

    public ScaleTypeDrawable(@Nullable Drawable drawable, ScalingUtils.ScaleType scaleType, @Nullable PointF focusPoint) {
        super(drawable);
        this.mFocusPoint = null;
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mTempMatrix = new Matrix();
        this.mScaleType = scaleType;
        this.mFocusPoint = focusPoint;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable
    @Nullable
    public Drawable setCurrent(@Nullable Drawable newDelegate) {
        Drawable previousDelegate = super.setCurrent(newDelegate);
        configureBounds();
        return previousDelegate;
    }

    public ScalingUtils.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        if (Objects.equal(this.mScaleType, scaleType)) {
            return;
        }
        this.mScaleType = scaleType;
        this.mScaleTypeState = null;
        configureBounds();
        invalidateSelf();
    }

    @Nullable
    public PointF getFocusPoint() {
        return this.mFocusPoint;
    }

    public void setFocusPoint(@Nullable PointF focusPoint) {
        if (Objects.equal(this.mFocusPoint, focusPoint)) {
            return;
        }
        if (focusPoint == null) {
            this.mFocusPoint = null;
        } else {
            if (this.mFocusPoint == null) {
                this.mFocusPoint = new PointF();
            }
            this.mFocusPoint.set(focusPoint);
        }
        configureBounds();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            int saveCount = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.mDrawMatrix);
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.draw(canvas);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        configureBounds();
    }

    private void configureBoundsIfUnderlyingChanged() {
        boolean scaleTypeChanged = false;
        if (this.mScaleType instanceof ScalingUtils.StatefulScaleType) {
            Object state = ((ScalingUtils.StatefulScaleType) this.mScaleType).getState();
            scaleTypeChanged = state == null || !state.equals(this.mScaleTypeState);
            this.mScaleTypeState = state;
        }
        Drawable current = getCurrent();
        if (current == null) {
            return;
        }
        boolean underlyingChanged = (this.mUnderlyingWidth == current.getIntrinsicWidth() && this.mUnderlyingHeight == current.getIntrinsicHeight()) ? false : true;
        if (underlyingChanged || scaleTypeChanged) {
            configureBounds();
        }
    }

    void configureBounds() {
        Drawable underlyingDrawable = getCurrent();
        if (underlyingDrawable == null) {
            this.mUnderlyingHeight = 0;
            this.mUnderlyingWidth = 0;
            this.mDrawMatrix = null;
            return;
        }
        Rect bounds = getBounds();
        int viewWidth = bounds.width();
        int viewHeight = bounds.height();
        int underlyingWidth = underlyingDrawable.getIntrinsicWidth();
        this.mUnderlyingWidth = underlyingWidth;
        int underlyingHeight = underlyingDrawable.getIntrinsicHeight();
        this.mUnderlyingHeight = underlyingHeight;
        if (underlyingWidth <= 0 || underlyingHeight <= 0) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        if (underlyingWidth == viewWidth && underlyingHeight == viewHeight) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (this.mScaleType == ScalingUtils.ScaleType.FIT_XY) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
        } else {
            underlyingDrawable.setBounds(0, 0, underlyingWidth, underlyingHeight);
            this.mScaleType.getTransform(this.mTempMatrix, bounds, underlyingWidth, underlyingHeight, this.mFocusPoint != null ? this.mFocusPoint.x : 0.5f, this.mFocusPoint != null ? this.mFocusPoint.y : 0.5f);
            this.mDrawMatrix = this.mTempMatrix;
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            transform.preConcat(this.mDrawMatrix);
        }
    }
}
