package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import com.facebook.drawee.R;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class GenericDraweeHierarchyInflater {
    public static GenericDraweeHierarchy inflateHierarchy(Context context, @Nullable AttributeSet attrs) {
        return inflateBuilder(context, attrs).build();
    }

    public static GenericDraweeHierarchyBuilder inflateBuilder(Context context, @Nullable AttributeSet attrs) throws Throwable {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchyBuilder#inflateBuilder");
        }
        Resources resources = context.getResources();
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(resources);
        GenericDraweeHierarchyBuilder builder2 = updateBuilder(builder, context, attrs);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return builder2;
    }

    public static GenericDraweeHierarchyBuilder updateBuilder(GenericDraweeHierarchyBuilder builder, Context context, @Nullable AttributeSet attrs) throws Throwable {
        boolean z;
        int indexCount;
        Context context2 = context;
        int progressBarAutoRotateInterval = 0;
        int roundedCornerRadius = 0;
        boolean roundTopLeft = true;
        boolean roundTopRight = true;
        boolean roundBottomLeft = true;
        boolean roundBottomRight = true;
        boolean roundTopStart = true;
        boolean roundTopEnd = true;
        boolean roundBottomStart = true;
        boolean roundBottomEnd = true;
        if (attrs != null) {
            TypedArray gdhAttrs = context2.obtainStyledAttributes(attrs, R.styleable.GenericDraweeHierarchy);
            try {
                int i = gdhAttrs.getIndexCount();
                int i2 = 0;
                while (true) {
                    int indexCount2 = i;
                    if (i2 >= indexCount2) {
                        break;
                    }
                    int attr = gdhAttrs.getIndex(i2);
                    int i3 = i2;
                    int i4 = R.styleable.GenericDraweeHierarchy_actualImageScaleType;
                    if (attr == i4) {
                        builder.setActualImageScaleType(getScaleTypeFromXml(gdhAttrs, attr));
                        indexCount = indexCount2;
                    } else if (attr == R.styleable.GenericDraweeHierarchy_placeholderImage) {
                        builder.setPlaceholderImage(getDrawable(context2, gdhAttrs, attr));
                        indexCount = indexCount2;
                    } else if (attr == R.styleable.GenericDraweeHierarchy_pressedStateOverlayImage) {
                        builder.setPressedStateOverlay(getDrawable(context2, gdhAttrs, attr));
                        indexCount = indexCount2;
                    } else if (attr == R.styleable.GenericDraweeHierarchy_progressBarImage) {
                        builder.setProgressBarImage(getDrawable(context2, gdhAttrs, attr));
                        indexCount = indexCount2;
                    } else if (attr == R.styleable.GenericDraweeHierarchy_fadeDuration) {
                        indexCount = indexCount2;
                        builder.setFadeDuration(gdhAttrs.getInt(attr, 0));
                    } else {
                        indexCount = indexCount2;
                        if (attr == R.styleable.GenericDraweeHierarchy_viewAspectRatio) {
                            builder.setDesiredAspectRatio(gdhAttrs.getFloat(attr, 0.0f));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_placeholderImageScaleType) {
                            builder.setPlaceholderImageScaleType(getScaleTypeFromXml(gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_retryImage) {
                            builder.setRetryImage(getDrawable(context2, gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_retryImageScaleType) {
                            builder.setRetryImageScaleType(getScaleTypeFromXml(gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_failureImage) {
                            builder.setFailureImage(getDrawable(context2, gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_failureImageScaleType) {
                            builder.setFailureImageScaleType(getScaleTypeFromXml(gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_progressBarImageScaleType) {
                            builder.setProgressBarImageScaleType(getScaleTypeFromXml(gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
                            progressBarAutoRotateInterval = gdhAttrs.getInteger(attr, progressBarAutoRotateInterval);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_backgroundImage) {
                            builder.setBackground(getDrawable(context2, gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_overlayImage) {
                            builder.setOverlay(getDrawable(context2, gdhAttrs, attr));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundAsCircle) {
                            getRoundingParams(builder).setRoundAsCircle(gdhAttrs.getBoolean(attr, false));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundedCornerRadius) {
                            roundedCornerRadius = gdhAttrs.getDimensionPixelSize(attr, roundedCornerRadius);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundTopLeft) {
                            roundTopLeft = gdhAttrs.getBoolean(attr, roundTopLeft);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundTopRight) {
                            roundTopRight = gdhAttrs.getBoolean(attr, roundTopRight);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundBottomLeft) {
                            roundBottomLeft = gdhAttrs.getBoolean(attr, roundBottomLeft);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundBottomRight) {
                            roundBottomRight = gdhAttrs.getBoolean(attr, roundBottomRight);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundTopStart) {
                            roundTopStart = gdhAttrs.getBoolean(attr, roundTopStart);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundTopEnd) {
                            roundTopEnd = gdhAttrs.getBoolean(attr, roundTopEnd);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundBottomStart) {
                            roundBottomStart = gdhAttrs.getBoolean(attr, roundBottomStart);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundBottomEnd) {
                            roundBottomEnd = gdhAttrs.getBoolean(attr, roundBottomEnd);
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundWithOverlayColor) {
                            getRoundingParams(builder).setOverlayColor(gdhAttrs.getColor(attr, 0));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderWidth) {
                            getRoundingParams(builder).setBorderWidth(gdhAttrs.getDimensionPixelSize(attr, 0));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderColor) {
                            getRoundingParams(builder).setBorderColor(gdhAttrs.getColor(attr, 0));
                        } else if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderPadding) {
                            z = false;
                            try {
                                getRoundingParams(builder).setPadding(gdhAttrs.getDimensionPixelSize(attr, 0));
                            } catch (Throwable th) {
                                th = th;
                            }
                        }
                    }
                    i2 = i3 + 1;
                    context2 = context;
                    i = indexCount;
                    gdhAttrs.recycle();
                    int i5 = Build.VERSION.SDK_INT;
                    if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
                        if (!roundTopLeft || !roundTopEnd) {
                        }
                        if (!roundTopRight || roundTopStart) {
                        }
                        if (!roundBottomRight || roundBottomStart) {
                        }
                        if (!roundBottomLeft || roundBottomEnd) {
                        }
                    } else {
                        if (!roundTopLeft || !roundTopStart) {
                        }
                        if (!roundTopRight || roundTopEnd) {
                        }
                        if (!roundBottomRight || roundBottomEnd) {
                        }
                        if (!roundBottomLeft || roundBottomStart) {
                        }
                    }
                    throw th;
                }
                boolean z2 = false;
                gdhAttrs.recycle();
                int i6 = Build.VERSION.SDK_INT;
                if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
                    roundTopLeft = roundTopLeft && roundTopEnd;
                    roundTopRight = roundTopRight && roundTopStart;
                    roundBottomRight = roundBottomRight && roundBottomStart;
                    if (roundBottomLeft && roundBottomEnd) {
                        z2 = true;
                    }
                    roundBottomLeft = z2;
                } else {
                    roundTopLeft = roundTopLeft && roundTopStart;
                    roundTopRight = roundTopRight && roundTopEnd;
                    roundBottomRight = roundBottomRight && roundBottomEnd;
                    if (roundBottomLeft && roundBottomStart) {
                        z2 = true;
                    }
                    roundBottomLeft = z2;
                }
            } catch (Throwable th2) {
                th = th2;
                z = false;
            }
        }
        if (builder.getProgressBarImage() != null && progressBarAutoRotateInterval > 0) {
            builder.setProgressBarImage(new AutoRotateDrawable(builder.getProgressBarImage(), progressBarAutoRotateInterval));
        }
        if (roundedCornerRadius > 0) {
            getRoundingParams(builder).setCornersRadii(roundTopLeft ? roundedCornerRadius : 0.0f, roundTopRight ? roundedCornerRadius : 0.0f, roundBottomRight ? roundedCornerRadius : 0.0f, roundBottomLeft ? roundedCornerRadius : 0.0f);
        }
        return builder;
    }

    private static RoundingParams getRoundingParams(GenericDraweeHierarchyBuilder builder) {
        if (builder.getRoundingParams() == null) {
            builder.setRoundingParams(new RoundingParams());
        }
        return builder.getRoundingParams();
    }

    @Nullable
    public static Drawable getDrawable(Context context, TypedArray gdhAttrs, int attrId) {
        int resourceId = gdhAttrs.getResourceId(attrId, 0);
        if (resourceId == 0) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        return context.getDrawable(resourceId);
    }

    @Nullable
    public static ScalingUtils.ScaleType getScaleTypeFromXml(TypedArray gdhAttrs, int attrId) {
        switch (gdhAttrs.getInt(attrId, -2)) {
            case -1:
                return null;
            case 0:
                return ScalingUtils.ScaleType.FIT_XY;
            case 1:
                return ScalingUtils.ScaleType.FIT_START;
            case 2:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 3:
                return ScalingUtils.ScaleType.FIT_END;
            case 4:
                return ScalingUtils.ScaleType.CENTER;
            case 5:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            case 6:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 7:
                return ScalingUtils.ScaleType.FOCUS_CROP;
            case 8:
                return ScalingUtils.ScaleType.FIT_BOTTOM_START;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }
}
