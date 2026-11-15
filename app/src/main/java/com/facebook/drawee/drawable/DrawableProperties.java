package com.facebook.drawee.drawable;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DrawableProperties {
    private static final int UNSET = -1;
    private int mAlpha = -1;
    private boolean mIsSetColorFilter = false;

    @Nullable
    private ColorFilter mColorFilter = null;
    private int mDither = -1;
    private int mFilterBitmap = -1;

    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        this.mIsSetColorFilter = colorFilter != null;
    }

    public void setDither(boolean z) {
        this.mDither = z ? 1 : 0;
    }

    public void setFilterBitmap(boolean z) {
        this.mFilterBitmap = z ? 1 : 0;
    }

    public void applyTo(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (this.mAlpha != -1) {
            drawable.setAlpha(this.mAlpha);
        }
        if (this.mIsSetColorFilter) {
            drawable.setColorFilter(this.mColorFilter);
        }
        if (this.mDither != -1) {
            drawable.setDither(this.mDither != 0);
        }
        if (this.mFilterBitmap != -1) {
            drawable.setFilterBitmap(this.mFilterBitmap != 0);
        }
    }
}
