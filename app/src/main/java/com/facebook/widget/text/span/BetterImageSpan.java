package com.facebook.widget.text.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes11.dex */
public class BetterImageSpan extends ReplacementSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    private final int mAlignment;
    private Rect mBounds;
    private final Drawable mDrawable;
    private final Paint.FontMetricsInt mFontMetricsInt;
    protected int mHeight;
    private final Rect mMargin;
    protected int mWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BetterImageSpanAlignment {
    }

    public static final int normalizeAlignment(int alignment) {
        switch (alignment) {
            case 0:
                return 0;
            case 1:
            default:
                return 1;
            case 2:
                return 2;
        }
    }

    public BetterImageSpan(Drawable drawable) {
        this(drawable, 1);
    }

    public BetterImageSpan(Drawable drawable, int verticalAlignment) {
        this(drawable, verticalAlignment, new Rect());
    }

    public BetterImageSpan(Drawable drawable, int verticalAlignment, Rect margin) {
        this.mFontMetricsInt = new Paint.FontMetricsInt();
        this.mDrawable = drawable;
        this.mAlignment = verticalAlignment;
        this.mMargin = margin;
        updateBounds();
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public Rect getMargin() {
        return this.mMargin;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetrics) {
        updateBounds();
        if (fontMetrics == null) {
            return this.mWidth;
        }
        return calculateLineWidthAndFontHeight(fontMetrics);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.getFontMetricsInt(this.mFontMetricsInt);
        int iconTop = getIconTop(y, this.mFontMetricsInt.ascent, this.mFontMetricsInt.descent, top, bottom);
        float iconLeft = this.mMargin.left + x;
        canvas.translate(iconLeft, iconTop);
        this.mDrawable.draw(canvas);
        canvas.translate(-iconLeft, -iconTop);
    }

    public void updateBounds() {
        this.mBounds = this.mDrawable.getBounds();
        this.mWidth = this.mBounds.width() + this.mMargin.left + this.mMargin.right;
        this.mHeight = this.mBounds.height();
    }

    protected int getOffsetAboveBaseline(int ascent, int descent) {
        switch (this.mAlignment) {
            case 0:
                int textHeight = this.mHeight;
                return (descent - textHeight) - this.mMargin.bottom;
            case 1:
            default:
                return (-this.mHeight) - this.mMargin.bottom;
            case 2:
                int textHeight2 = (descent - ascent) + this.mMargin.top + this.mMargin.bottom;
                int offset = (textHeight2 - this.mHeight) / 2;
                return (ascent + offset) - this.mMargin.bottom;
        }
    }

    protected int getIconTop(int baseline, int ascent, int descent, int top, int bottom) {
        return getOffsetAboveBaseline(this.mFontMetricsInt.ascent, this.mFontMetricsInt.descent) + baseline;
    }

    protected int calculateLineWidthAndFontHeight(Paint.FontMetricsInt fontMetrics) {
        int offsetAbove;
        int offsetAbove2 = getOffsetAboveBaseline(fontMetrics.ascent, fontMetrics.descent);
        int offsetBelow = this.mHeight + offsetAbove2;
        if (this.mAlignment == 2) {
            offsetAbove = offsetAbove2 - this.mMargin.top;
            offsetBelow += this.mMargin.bottom;
        } else {
            offsetAbove = offsetAbove2 - this.mMargin.top;
        }
        updateFontHeight(fontMetrics, offsetAbove, offsetBelow);
        return this.mWidth;
    }

    protected void updateFontHeight(Paint.FontMetricsInt fontMetrics, int offsetAbove, int offsetBelow) {
        if (offsetAbove < fontMetrics.ascent) {
            fontMetrics.ascent = offsetAbove;
        }
        if (offsetAbove < fontMetrics.top) {
            fontMetrics.top = offsetAbove;
        }
        if (offsetBelow > fontMetrics.descent) {
            fontMetrics.descent = offsetBelow;
        }
        if (offsetBelow > fontMetrics.bottom) {
            fontMetrics.bottom = offsetBelow;
        }
    }
}
