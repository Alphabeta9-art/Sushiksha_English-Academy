package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ForwardingDrawable extends Drawable implements Drawable.Callback, TransformCallback, TransformAwareDrawable, DrawableParent {
    private static final Matrix sTempTransform = new Matrix();

    @Nullable
    private Drawable mCurrentDelegate;
    private final DrawableProperties mDrawableProperties = new DrawableProperties();

    @Nullable
    protected TransformCallback mTransformCallback;

    public ForwardingDrawable(@Nullable Drawable drawable) {
        this.mCurrentDelegate = drawable;
        DrawableUtils.setCallbacks(this.mCurrentDelegate, this, this);
    }

    @Nullable
    public Drawable setCurrent(@Nullable Drawable newDelegate) {
        Drawable previousDelegate = setCurrentWithoutInvalidate(newDelegate);
        invalidateSelf();
        return previousDelegate;
    }

    @Nullable
    protected Drawable setCurrentWithoutInvalidate(@Nullable Drawable newDelegate) {
        Drawable previousDelegate = this.mCurrentDelegate;
        DrawableUtils.setCallbacks(previousDelegate, null, null);
        DrawableUtils.setCallbacks(newDelegate, null, null);
        DrawableUtils.setDrawableProperties(newDelegate, this.mDrawableProperties);
        DrawableUtils.copyProperties(newDelegate, this);
        DrawableUtils.setCallbacks(newDelegate, this, this);
        this.mCurrentDelegate = newDelegate;
        return previousDelegate;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mCurrentDelegate == null) {
            return 0;
        }
        return this.mCurrentDelegate.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mDrawableProperties.setAlpha(alpha);
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setAlpha(alpha);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean dither) {
        this.mDrawableProperties.setDither(dither);
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setDither(dither);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean filterBitmap) {
        this.mDrawableProperties.setFilterBitmap(filterBitmap);
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setFilterBitmap(filterBitmap);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean visible, boolean restart) {
        boolean superResult = super.setVisible(visible, restart);
        if (this.mCurrentDelegate == null) {
            return superResult;
        }
        return this.mCurrentDelegate.setVisible(visible, restart);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setBounds(bounds);
        }
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        if (this.mCurrentDelegate == null) {
            return super.getConstantState();
        }
        return this.mCurrentDelegate.getConstantState();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (this.mCurrentDelegate == null) {
            return false;
        }
        return this.mCurrentDelegate.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] state) {
        if (this.mCurrentDelegate == null) {
            return super.onStateChange(state);
        }
        return this.mCurrentDelegate.setState(state);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int level) {
        if (this.mCurrentDelegate == null) {
            return super.onLevelChange(level);
        }
        return this.mCurrentDelegate.setLevel(level);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.mCurrentDelegate == null) {
            return super.getIntrinsicWidth();
        }
        return this.mCurrentDelegate.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.mCurrentDelegate == null) {
            return super.getIntrinsicHeight();
        }
        return this.mCurrentDelegate.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect padding) {
        if (this.mCurrentDelegate == null) {
            return super.getPadding(padding);
        }
        return this.mCurrentDelegate.getPadding(padding);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable getCurrent() {
        return this.mCurrentDelegate;
    }

    @Override // com.facebook.drawee.drawable.DrawableParent
    @Nullable
    public Drawable setDrawable(@Nullable Drawable newDrawable) {
        return setCurrent(newDrawable);
    }

    @Override // com.facebook.drawee.drawable.DrawableParent
    @Nullable
    public Drawable getDrawable() {
        return getCurrent();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    @Override // com.facebook.drawee.drawable.TransformAwareDrawable
    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    protected void getParentTransform(Matrix transform) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(transform);
        } else {
            transform.reset();
        }
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getRootBounds(RectF bounds) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(bounds);
        } else {
            bounds.set(getBounds());
        }
    }

    public void getTransformedBounds(RectF outBounds) {
        getParentTransform(sTempTransform);
        outBounds.set(getBounds());
        sTempTransform.mapRect(outBounds);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float x, float y) {
        if (this.mCurrentDelegate != null) {
            this.mCurrentDelegate.setHotspot(x, y);
        }
    }
}
