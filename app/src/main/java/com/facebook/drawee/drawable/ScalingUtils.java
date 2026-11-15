package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ScalingUtils {

    public interface ScaleType {
        public static final ScaleType FIT_XY = ScaleTypeFitXY.INSTANCE;
        public static final ScaleType FIT_X = ScaleTypeFitX.INSTANCE;
        public static final ScaleType FIT_Y = ScaleTypeFitY.INSTANCE;
        public static final ScaleType FIT_START = ScaleTypeFitStart.INSTANCE;
        public static final ScaleType FIT_CENTER = ScaleTypeFitCenter.INSTANCE;
        public static final ScaleType FIT_END = ScaleTypeFitEnd.INSTANCE;
        public static final ScaleType CENTER = ScaleTypeCenter.INSTANCE;
        public static final ScaleType CENTER_INSIDE = ScaleTypeCenterInside.INSTANCE;
        public static final ScaleType CENTER_CROP = ScaleTypeCenterCrop.INSTANCE;
        public static final ScaleType FOCUS_CROP = ScaleTypeFocusCrop.INSTANCE;
        public static final ScaleType FIT_BOTTOM_START = ScaleTypeFitBottomStart.INSTANCE;

        Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2);
    }

    public interface StatefulScaleType {
        Object getState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static ScaleTypeDrawable getActiveScaleTypeDrawable(@Nullable Drawable drawable) {
        if (drawable == 0) {
            return null;
        }
        if (drawable instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) drawable;
        }
        if (drawable instanceof DrawableParent) {
            Drawable childDrawable = ((DrawableParent) drawable).getDrawable();
            return getActiveScaleTypeDrawable(childDrawable);
        }
        if (drawable instanceof ArrayDrawable) {
            ArrayDrawable fadeDrawable = (ArrayDrawable) drawable;
            int numLayers = fadeDrawable.getNumberOfLayers();
            for (int i = 0; i < numLayers; i++) {
                Drawable childDrawable2 = fadeDrawable.getDrawable(i);
                ScaleTypeDrawable result = getActiveScaleTypeDrawable(childDrawable2);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: com.facebook.drawee.drawable.ScalingUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    @Nullable
    public static ScaleType convertFromImageViewScaleType(ImageView.ScaleType scaleType) {
        switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
            case 1:
                return ScaleType.CENTER;
            case 2:
                return ScaleType.CENTER_CROP;
            case 3:
                return ScaleType.CENTER_INSIDE;
            case 4:
                return ScaleType.FIT_CENTER;
            case 5:
                return ScaleType.FIT_START;
            case 6:
                return ScaleType.FIT_END;
            case 7:
                return ScaleType.FIT_XY;
            default:
                return null;
        }
    }

    public static abstract class AbstractScaleType implements ScaleType {
        public abstract void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4);

        @Override // com.facebook.drawee.drawable.ScalingUtils.ScaleType
        public Matrix getTransform(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY) {
            float sX = parentRect.width() / childWidth;
            float sY = parentRect.height() / childHeight;
            getTransformImpl(outTransform, parentRect, childWidth, childHeight, focusX, focusY, sX, sY);
            return outTransform;
        }
    }

    private static class ScaleTypeFitXY extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitXY();

        private ScaleTypeFitXY() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float dx = parentRect.left;
            float dy = parentRect.top;
            outTransform.setScale(scaleX, scaleY);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_xy";
        }
    }

    private static class ScaleTypeFitStart extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitStart();

        private ScaleTypeFitStart() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = parentRect.left;
            float dy = parentRect.top;
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_start";
        }
    }

    private static class ScaleTypeFitBottomStart extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitBottomStart();

        private ScaleTypeFitBottomStart() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = parentRect.left;
            float dy = parentRect.top + (parentRect.height() - (childHeight * scale));
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_bottom_start";
        }
    }

    private static class ScaleTypeFitCenter extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitCenter();

        private ScaleTypeFitCenter() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = parentRect.left + ((parentRect.width() - (childWidth * scale)) * 0.5f);
            float dy = parentRect.top + ((parentRect.height() - (childHeight * scale)) * 0.5f);
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_center";
        }
    }

    private static class ScaleTypeFitEnd extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitEnd();

        private ScaleTypeFitEnd() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = parentRect.left + (parentRect.width() - (childWidth * scale));
            float dy = parentRect.top + (parentRect.height() - (childHeight * scale));
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_end";
        }
    }

    private static class ScaleTypeCenter extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeCenter();

        private ScaleTypeCenter() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float dx = parentRect.left + ((parentRect.width() - childWidth) * 0.5f);
            float dy = parentRect.top + ((parentRect.height() - childHeight) * 0.5f);
            outTransform.setTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return TtmlNode.CENTER;
        }
    }

    private static class ScaleTypeCenterInside extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeCenterInside();

        private ScaleTypeCenterInside() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(Math.min(scaleX, scaleY), 1.0f);
            float dx = parentRect.left + ((parentRect.width() - (childWidth * scale)) * 0.5f);
            float dy = parentRect.top + ((parentRect.height() - (childHeight * scale)) * 0.5f);
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "center_inside";
        }
    }

    private static class ScaleTypeCenterCrop extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeCenterCrop();

        private ScaleTypeCenterCrop() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale;
            float dx;
            float dy;
            if (scaleY > scaleX) {
                scale = scaleY;
                dx = parentRect.left + ((parentRect.width() - (childWidth * scale)) * 0.5f);
                dy = parentRect.top;
            } else {
                scale = scaleX;
                dx = parentRect.left;
                dy = parentRect.top + ((parentRect.height() - (childHeight * scale)) * 0.5f);
            }
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "center_crop";
        }
    }

    private static class ScaleTypeFocusCrop extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFocusCrop();

        private ScaleTypeFocusCrop() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale;
            float dx;
            float dy;
            if (scaleY > scaleX) {
                scale = scaleY;
                float dx2 = (parentRect.width() * 0.5f) - ((childWidth * scale) * focusX);
                dx = parentRect.left + Math.max(Math.min(dx2, 0.0f), parentRect.width() - (childWidth * scale));
                dy = parentRect.top;
            } else {
                scale = scaleX;
                dx = parentRect.left;
                float dy2 = (parentRect.height() * 0.5f) - ((childHeight * scale) * focusY);
                dy = Math.max(Math.min(dy2, 0.0f), parentRect.height() - (childHeight * scale)) + parentRect.top;
            }
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "focus_crop";
        }
    }

    private static class ScaleTypeFitX extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitX();

        private ScaleTypeFitX() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float dx = parentRect.left;
            float dy = parentRect.top + ((parentRect.height() - (childHeight * scaleX)) * 0.5f);
            outTransform.setScale(scaleX, scaleX);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_x";
        }
    }

    private static class ScaleTypeFitY extends AbstractScaleType {
        public static final ScaleType INSTANCE = new ScaleTypeFitY();

        private ScaleTypeFitY() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float dx = parentRect.left + ((parentRect.width() - (childWidth * scaleY)) * 0.5f);
            float dy = parentRect.top;
            outTransform.setScale(scaleY, scaleY);
            outTransform.postTranslate((int) (dx + 0.5f), (int) (0.5f + dy));
        }

        public String toString() {
            return "fit_y";
        }
    }

    public static class InterpolatingScaleType implements ScaleType, StatefulScaleType {

        @Nullable
        private final Rect mBoundsFrom;

        @Nullable
        private final Rect mBoundsTo;

        @Nullable
        private final PointF mFocusPointFrom;

        @Nullable
        private final PointF mFocusPointTo;
        private float mInterpolatingValue;
        private final float[] mMatrixValuesFrom;
        private final float[] mMatrixValuesInterpolated;
        private final float[] mMatrixValuesTo;
        private final ScaleType mScaleTypeFrom;
        private final ScaleType mScaleTypeTo;

        public InterpolatingScaleType(ScaleType scaleTypeFrom, ScaleType scaleTypeTo, @Nullable Rect boundsFrom, @Nullable Rect boundsTo, @Nullable PointF focusPointFrom, @Nullable PointF focusPointTo) {
            this.mMatrixValuesFrom = new float[9];
            this.mMatrixValuesTo = new float[9];
            this.mMatrixValuesInterpolated = new float[9];
            this.mScaleTypeFrom = scaleTypeFrom;
            this.mScaleTypeTo = scaleTypeTo;
            this.mBoundsFrom = boundsFrom;
            this.mBoundsTo = boundsTo;
            this.mFocusPointFrom = focusPointFrom;
            this.mFocusPointTo = focusPointTo;
        }

        public InterpolatingScaleType(ScaleType scaleTypeFrom, ScaleType scaleTypeTo, @Nullable Rect boundsFrom, @Nullable Rect boundsTo) {
            this(scaleTypeFrom, scaleTypeTo, boundsFrom, boundsTo, null, null);
        }

        public InterpolatingScaleType(ScaleType scaleTypeFrom, ScaleType scaleTypeTo) {
            this(scaleTypeFrom, scaleTypeTo, null, null);
        }

        public ScaleType getScaleTypeFrom() {
            return this.mScaleTypeFrom;
        }

        public ScaleType getScaleTypeTo() {
            return this.mScaleTypeTo;
        }

        @Nullable
        public Rect getBoundsFrom() {
            return this.mBoundsFrom;
        }

        @Nullable
        public Rect getBoundsTo() {
            return this.mBoundsTo;
        }

        @Nullable
        public PointF getFocusPointFrom() {
            return this.mFocusPointFrom;
        }

        @Nullable
        public PointF getFocusPointTo() {
            return this.mFocusPointTo;
        }

        public void setValue(float value) {
            this.mInterpolatingValue = value;
        }

        public float getValue() {
            return this.mInterpolatingValue;
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.StatefulScaleType
        public Object getState() {
            return Float.valueOf(this.mInterpolatingValue);
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.ScaleType
        public Matrix getTransform(Matrix transform, Rect parentBounds, int childWidth, int childHeight, float focusX, float focusY) {
            Rect boundsFrom = this.mBoundsFrom != null ? this.mBoundsFrom : parentBounds;
            Rect boundsTo = this.mBoundsTo != null ? this.mBoundsTo : parentBounds;
            this.mScaleTypeFrom.getTransform(transform, boundsFrom, childWidth, childHeight, this.mFocusPointFrom == null ? focusX : this.mFocusPointFrom.x, this.mFocusPointFrom == null ? focusY : this.mFocusPointFrom.y);
            transform.getValues(this.mMatrixValuesFrom);
            this.mScaleTypeTo.getTransform(transform, boundsTo, childWidth, childHeight, this.mFocusPointTo == null ? focusX : this.mFocusPointTo.x, this.mFocusPointTo == null ? focusY : this.mFocusPointTo.y);
            transform.getValues(this.mMatrixValuesTo);
            for (int i = 0; i < 9; i++) {
                this.mMatrixValuesInterpolated[i] = (this.mMatrixValuesFrom[i] * (1.0f - this.mInterpolatingValue)) + (this.mMatrixValuesTo[i] * this.mInterpolatingValue);
            }
            transform.setValues(this.mMatrixValuesInterpolated);
            return transform;
        }

        public String toString() {
            return String.format("InterpolatingScaleType(%s (%s) -> %s (%s))", String.valueOf(this.mScaleTypeFrom), String.valueOf(this.mFocusPointFrom), String.valueOf(this.mScaleTypeTo), String.valueOf(this.mFocusPointTo));
        }
    }
}
