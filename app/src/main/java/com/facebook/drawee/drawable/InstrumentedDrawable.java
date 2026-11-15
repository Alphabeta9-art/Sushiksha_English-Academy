package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes11.dex */
public class InstrumentedDrawable extends ForwardingDrawable {
    private boolean mIsChecked;
    private final Listener mListener;
    private final String mScaleType;

    public interface Listener {
        void track(int i, int i2, int i3, int i4, int i5, int i6, String str);
    }

    public InstrumentedDrawable(Drawable drawable, Listener listener) {
        super(drawable);
        this.mIsChecked = false;
        this.mListener = listener;
        this.mScaleType = getScaleType(drawable);
    }

    private String getScaleType(Drawable drawable) {
        if (drawable instanceof ScaleTypeDrawable) {
            ScalingUtils.ScaleType type = ((ScaleTypeDrawable) drawable).getScaleType();
            return type.toString();
        }
        return "none";
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.mIsChecked) {
            this.mIsChecked = true;
            RectF bounds = new RectF();
            getRootBounds(bounds);
            int viewWidth = (int) bounds.width();
            int viewHeight = (int) bounds.height();
            getTransformedBounds(bounds);
            int scaledWidth = (int) bounds.width();
            int scaledHeight = (int) bounds.height();
            int imageWidth = getIntrinsicWidth();
            int imageHeight = getIntrinsicHeight();
            if (this.mListener != null) {
                this.mListener.track(viewWidth, viewHeight, imageWidth, imageHeight, scaledWidth, scaledHeight, this.mScaleType);
            }
        }
        super.draw(canvas);
    }
}
