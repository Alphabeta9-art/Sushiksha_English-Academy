package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.RoundedNinePatchDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class WrappingUtils {
    private static final String TAG = "WrappingUtils";
    private static final Drawable sEmptyDrawable = new ColorDrawable(0);

    @Nullable
    static Drawable maybeWrapWithScaleType(@Nullable Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        return maybeWrapWithScaleType(drawable, scaleType, null);
    }

    @Nullable
    static Drawable maybeWrapWithScaleType(@Nullable Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType, @Nullable PointF focusPoint) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithScaleType");
        }
        if (drawable == null || scaleType == null) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return drawable;
        }
        ScaleTypeDrawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scaleType);
        if (focusPoint != null) {
            scaleTypeDrawable.setFocusPoint(focusPoint);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return scaleTypeDrawable;
    }

    @Nullable
    static Drawable maybeWrapWithMatrix(@Nullable Drawable drawable, @Nullable Matrix matrix) {
        if (drawable == null || matrix == null) {
            return drawable;
        }
        return new MatrixDrawable(drawable, matrix);
    }

    static ScaleTypeDrawable wrapChildWithScaleType(DrawableParent parent, ScalingUtils.ScaleType scaleType) {
        Drawable child = maybeWrapWithScaleType(parent.setDrawable(sEmptyDrawable), scaleType);
        parent.setDrawable(child);
        Preconditions.checkNotNull(child, "Parent has no child drawable!");
        return (ScaleTypeDrawable) child;
    }

    static void updateOverlayColorRounding(DrawableParent parent, @Nullable RoundingParams roundingParams) {
        Drawable child = parent.getDrawable();
        if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.OVERLAY_COLOR) {
            if (child instanceof RoundedCornersDrawable) {
                RoundedCornersDrawable roundedCornersDrawable = (RoundedCornersDrawable) child;
                applyRoundingParams(roundedCornersDrawable, roundingParams);
                roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
                return;
            }
            parent.setDrawable(maybeWrapWithRoundedOverlayColor(parent.setDrawable(sEmptyDrawable), roundingParams));
            return;
        }
        if (child instanceof RoundedCornersDrawable) {
            parent.setDrawable(((RoundedCornersDrawable) child).setCurrent(sEmptyDrawable));
            sEmptyDrawable.setCallback(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static void updateLeafRounding(DrawableParent parent, @Nullable RoundingParams roundingParams, Resources resources) {
        DrawableParent parent2 = findDrawableParentForLeaf(parent);
        Drawable drawable = parent2.getDrawable();
        if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.BITMAP_ONLY) {
            if (drawable instanceof Rounded) {
                Rounded rounded = (Rounded) drawable;
                applyRoundingParams(rounded, roundingParams);
                return;
            } else {
                if (drawable != 0) {
                    parent2.setDrawable(sEmptyDrawable);
                    Drawable rounded2 = applyLeafRounding(drawable, roundingParams, resources);
                    parent2.setDrawable(rounded2);
                    return;
                }
                return;
            }
        }
        if (drawable instanceof Rounded) {
            resetRoundingParams((Rounded) drawable);
        }
    }

    static Drawable maybeWrapWithRoundedOverlayColor(@Nullable Drawable drawable, @Nullable RoundingParams roundingParams) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithRoundedOverlayColor");
            }
            if (drawable != null && roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.OVERLAY_COLOR) {
                RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
                applyRoundingParams(roundedCornersDrawable, roundingParams);
                roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
                return roundedCornersDrawable;
            }
            return drawable;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    static Drawable maybeApplyLeafRounding(@Nullable Drawable drawable, @Nullable RoundingParams roundingParams, Resources resources) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("WrappingUtils#maybeApplyLeafRounding");
            }
            if (drawable != null && roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.BITMAP_ONLY) {
                if (!(drawable instanceof ForwardingDrawable)) {
                    return applyLeafRounding(drawable, roundingParams, resources);
                }
                DrawableParent parent = findDrawableParentForLeaf((ForwardingDrawable) drawable);
                Drawable child = parent.setDrawable(sEmptyDrawable);
                parent.setDrawable(applyLeafRounding(child, roundingParams, resources));
                return drawable;
            }
            return drawable;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    private static Drawable applyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            RoundedBitmapDrawable roundedBitmapDrawable = new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint(), roundingParams.getRepeatEdgePixels());
            applyRoundingParams(roundedBitmapDrawable, roundingParams);
            return roundedBitmapDrawable;
        }
        if (drawable instanceof NinePatchDrawable) {
            NinePatchDrawable ninePatchDrawableDrawable = (NinePatchDrawable) drawable;
            RoundedNinePatchDrawable roundedNinePatchDrawable = new RoundedNinePatchDrawable(ninePatchDrawableDrawable);
            applyRoundingParams(roundedNinePatchDrawable, roundingParams);
            return roundedNinePatchDrawable;
        }
        if (drawable instanceof ColorDrawable) {
            RoundedColorDrawable roundedColorDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable) drawable);
            applyRoundingParams(roundedColorDrawable, roundingParams);
            return roundedColorDrawable;
        }
        FLog.w(TAG, "Don't know how to round that drawable: %s", drawable);
        return drawable;
    }

    static void applyRoundingParams(Rounded rounded, RoundingParams roundingParams) {
        rounded.setCircle(roundingParams.getRoundAsCircle());
        rounded.setRadii(roundingParams.getCornersRadii());
        rounded.setBorder(roundingParams.getBorderColor(), roundingParams.getBorderWidth());
        rounded.setPadding(roundingParams.getPadding());
        rounded.setScaleDownInsideBorders(roundingParams.getScaleDownInsideBorders());
        rounded.setPaintFilterBitmap(roundingParams.getPaintFilterBitmap());
        rounded.setRepeatEdgePixels(roundingParams.getRepeatEdgePixels());
    }

    static void resetRoundingParams(Rounded rounded) {
        rounded.setCircle(false);
        rounded.setRadius(0.0f);
        rounded.setBorder(0, 0.0f);
        rounded.setPadding(0.0f);
        rounded.setScaleDownInsideBorders(false);
        rounded.setPaintFilterBitmap(false);
        rounded.setRepeatEdgePixels(RoundedBitmapDrawable.getDefaultRepeatEdgePixels());
    }

    static DrawableParent findDrawableParentForLeaf(DrawableParent parent) {
        while (true) {
            Object drawable = parent.getDrawable();
            if (drawable == parent || !(drawable instanceof DrawableParent)) {
                break;
            }
            parent = (DrawableParent) drawable;
        }
        return parent;
    }
}
