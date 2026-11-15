package com.cloudinary.android.uploadwidget.ui.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.compose.runtime.ComposerImplKt;
import androidx.core.graphics.ColorUtils;
import com.cloudinary.android.uploadwidget.model.CropPoints;
import com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureCallback;
import com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureDetector;

/* JADX INFO: loaded from: classes11.dex */
class CropOverlayView extends View implements CropOverlayGestureCallback {
    private static final int CORNER_HANDLE_LENGTH = 50;
    private static final int HANDLE_OFFSET_FROM_OVERLAY = 5;
    private static final int HANDLE_THICKNESS = 10;
    private static final int MIN_OVERLAY_SIZE = 5;
    private static final int NUMBER_OF_GUIDELINES = 4;
    private static final int SIDE_HANDLE_LENGTH = 40;
    private final Path bottomHandlePath;
    private final Path bottomLeftHandlePath;
    private final Path bottomRightHandlePath;
    private final Paint dimBackgroundPaint;
    private final Paint dottedPaint;
    private final Path dottedPath;
    private CropOverlayGestureDetector gestureDetector;
    private final Paint guidelinesPaint;
    private final Paint handlePaint;
    private Rect imageBounds;
    private boolean isAspectRatioLocked;
    private final Path leftHandlePath;
    private final Rect overlay;
    private final Path rightHandlePath;
    private final Path topHandlePath;
    private final Path topLeftHandlePath;
    private final Path topRightHandlePath;

    public CropOverlayView(Context context) {
        super(context);
        this.dottedPath = new Path();
        this.leftHandlePath = new Path();
        this.topHandlePath = new Path();
        this.rightHandlePath = new Path();
        this.bottomHandlePath = new Path();
        this.topLeftHandlePath = new Path();
        this.topRightHandlePath = new Path();
        this.bottomRightHandlePath = new Path();
        this.bottomLeftHandlePath = new Path();
        this.dottedPaint = new Paint();
        this.guidelinesPaint = new Paint();
        this.handlePaint = new Paint();
        this.dimBackgroundPaint = new Paint();
        this.overlay = new Rect();
        init();
    }

    public CropOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.dottedPath = new Path();
        this.leftHandlePath = new Path();
        this.topHandlePath = new Path();
        this.rightHandlePath = new Path();
        this.bottomHandlePath = new Path();
        this.topLeftHandlePath = new Path();
        this.topRightHandlePath = new Path();
        this.bottomRightHandlePath = new Path();
        this.bottomLeftHandlePath = new Path();
        this.dottedPaint = new Paint();
        this.guidelinesPaint = new Paint();
        this.handlePaint = new Paint();
        this.dimBackgroundPaint = new Paint();
        this.overlay = new Rect();
        init();
    }

    public CropOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.dottedPath = new Path();
        this.leftHandlePath = new Path();
        this.topHandlePath = new Path();
        this.rightHandlePath = new Path();
        this.bottomHandlePath = new Path();
        this.topLeftHandlePath = new Path();
        this.topRightHandlePath = new Path();
        this.bottomRightHandlePath = new Path();
        this.bottomLeftHandlePath = new Path();
        this.dottedPaint = new Paint();
        this.guidelinesPaint = new Paint();
        this.handlePaint = new Paint();
        this.dimBackgroundPaint = new Paint();
        this.overlay = new Rect();
        init();
    }

    private void init() {
        this.gestureDetector = new CropOverlayGestureDetector(this.overlay, this);
        this.dottedPaint.setColor(-1);
        this.dottedPaint.setStyle(Paint.Style.STROKE);
        this.dottedPaint.setStrokeWidth(5.0f);
        this.dottedPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 10.0f}, 0.0f));
        this.guidelinesPaint.setColor(-1);
        this.guidelinesPaint.setStyle(Paint.Style.STROKE);
        this.handlePaint.setColor(-1);
        this.handlePaint.setStyle(Paint.Style.FILL);
        this.dimBackgroundPaint.setColor(ColorUtils.setAlphaComponent(-16777216, ComposerImplKt.nodeKey));
        this.dimBackgroundPaint.setStyle(Paint.Style.FILL);
        setVisibility(4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.overlay.isEmpty()) {
            drawDottedPath(canvas);
            drawGuidelines(canvas);
            drawHandles(canvas);
            dimSurroundingBackground(canvas);
        }
    }

    private void drawDottedPath(Canvas canvas) {
        this.dottedPath.reset();
        this.dottedPath.moveTo(this.overlay.left, this.overlay.top);
        this.dottedPath.lineTo(this.overlay.right, this.overlay.top);
        this.dottedPath.lineTo(this.overlay.right, this.overlay.bottom);
        this.dottedPath.lineTo(this.overlay.left, this.overlay.bottom);
        this.dottedPath.lineTo(this.overlay.left, this.overlay.top);
        canvas.drawPath(this.dottedPath, this.dottedPaint);
    }

    private void drawGuidelines(Canvas canvas) {
        int widthDiff = this.overlay.width() / 4;
        for (int guidelineX = this.overlay.left; guidelineX <= this.overlay.right; guidelineX += widthDiff) {
            canvas.drawLine(guidelineX, this.overlay.top, guidelineX, this.overlay.bottom, this.guidelinesPaint);
        }
        int heightDiff = this.overlay.height() / 4;
        for (int guidelineY = this.overlay.top; guidelineY <= this.overlay.bottom; guidelineY += heightDiff) {
            canvas.drawLine(this.overlay.left, guidelineY, this.overlay.right, guidelineY, this.guidelinesPaint);
        }
    }

    private void drawHandles(Canvas canvas) {
        this.leftHandlePath.reset();
        float[] leftRadiusValues = {0.0f, 0.0f, 10.0f, 10.0f, 10.0f, 10.0f, 0.0f, 0.0f};
        this.leftHandlePath.addRoundRect(new RectF(this.overlay.left - 5, this.overlay.centerY() - 20, this.overlay.left + 10, this.overlay.centerY() + 20), leftRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.leftHandlePath, this.handlePaint);
        this.topHandlePath.reset();
        float[] topRadiusValues = {0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 10.0f, 10.0f, 10.0f};
        this.topHandlePath.addRoundRect(new RectF(this.overlay.centerX() - 20, this.overlay.top - 5, this.overlay.centerX() + 20, this.overlay.top + 10), topRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.topHandlePath, this.handlePaint);
        this.rightHandlePath.reset();
        float[] rightRadiusValues = {10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 10.0f};
        this.rightHandlePath.addRoundRect(new RectF(this.overlay.right - 10, this.overlay.centerY() - 20, this.overlay.right + 5, this.overlay.centerY() + 20), rightRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.rightHandlePath, this.handlePaint);
        this.bottomHandlePath.reset();
        float[] bottomRadiusValues = {10.0f, 10.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.bottomHandlePath.addRoundRect(new RectF(this.overlay.centerX() - 20, this.overlay.bottom - 10, this.overlay.centerX() + 20, this.overlay.bottom + 5), bottomRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.bottomHandlePath, this.handlePaint);
        this.topLeftHandlePath.reset();
        float[] topLeftHandleRadiusValues = {0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 10.0f, 0.0f, 0.0f};
        this.topLeftHandlePath.addRoundRect(new RectF(this.overlay.left - 5, this.overlay.top - 5, this.overlay.left + 10, this.overlay.top + 50), topLeftHandleRadiusValues, Path.Direction.CCW);
        this.topLeftHandlePath.addRoundRect(new RectF(this.overlay.left - 5, this.overlay.top - 5, this.overlay.left + 50, this.overlay.top + 10), topLeftHandleRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.topLeftHandlePath, this.handlePaint);
        this.topRightHandlePath.reset();
        float[] topRightRadiusValues = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 10.0f};
        this.topRightHandlePath.addRoundRect(new RectF(this.overlay.right - 50, this.overlay.top - 5, this.overlay.right + 5, this.overlay.top + 10), topRightRadiusValues, Path.Direction.CCW);
        this.topRightHandlePath.addRoundRect(new RectF(this.overlay.right - 10, this.overlay.top - 5, this.overlay.right + 5, this.overlay.top + 50), topRightRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.topRightHandlePath, this.handlePaint);
        this.bottomRightHandlePath.reset();
        float[] bottomRightRadiusValues = {10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.bottomRightHandlePath.addRoundRect(new RectF(this.overlay.right - 10, this.overlay.bottom - 50, this.overlay.right + 5, this.overlay.bottom + 5), bottomRightRadiusValues, Path.Direction.CCW);
        this.bottomRightHandlePath.addRoundRect(new RectF(this.overlay.right - 50, this.overlay.bottom - 10, this.overlay.right + 5, this.overlay.bottom + 5), bottomRightRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.bottomRightHandlePath, this.handlePaint);
        this.bottomLeftHandlePath.reset();
        float[] bottomLeftRadiusValues = {0.0f, 0.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.bottomLeftHandlePath.addRoundRect(new RectF(this.overlay.left - 5, this.overlay.bottom - 50, this.overlay.left + 10, this.overlay.bottom + 5), bottomLeftRadiusValues, Path.Direction.CCW);
        this.bottomLeftHandlePath.addRoundRect(new RectF(this.overlay.left - 5, this.overlay.bottom - 10, this.overlay.left + 50, this.overlay.bottom + 5), bottomLeftRadiusValues, Path.Direction.CCW);
        canvas.drawPath(this.bottomLeftHandlePath, this.handlePaint);
    }

    private void dimSurroundingBackground(Canvas canvas) {
        canvas.drawRect(0.0f, this.overlay.top - 5, this.overlay.left - 5, this.overlay.bottom + 5, this.dimBackgroundPaint);
        canvas.drawRect(0.0f, 0.0f, getWidth(), this.overlay.top - 5, this.dimBackgroundPaint);
        canvas.drawRect(this.overlay.right + 5, this.overlay.top - 5, getWidth(), this.overlay.bottom + 5, this.dimBackgroundPaint);
        canvas.drawRect(0.0f, this.overlay.bottom + 5, getWidth(), getHeight(), this.dimBackgroundPaint);
    }

    public boolean isAspectRatioLocked() {
        return this.isAspectRatioLocked;
    }

    public void setAspectRatioLocked(boolean aspectRatioLocked) {
        this.isAspectRatioLocked = aspectRatioLocked;
    }

    public CropPoints getCropPoints() {
        return new CropPoints(new Point(this.overlay.left, this.overlay.top), new Point(this.overlay.right, this.overlay.bottom));
    }

    public void reset() {
        this.overlay.set(this.imageBounds);
        invalidate();
    }

    public void set(Rect imageBounds) {
        this.imageBounds = imageBounds;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event, this.isAspectRatioLocked);
        return true;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureCallback
    public void onOverlayDragged(int distanceX, int distanceY) {
        if (this.overlay.left + distanceX <= this.imageBounds.left || this.overlay.right + distanceX >= this.imageBounds.right) {
            distanceX = 0;
        }
        if (this.overlay.top + distanceY <= this.imageBounds.top || this.overlay.bottom + distanceY >= this.imageBounds.bottom) {
            distanceY = 0;
        }
        this.overlay.offset(distanceX, distanceY);
        invalidate();
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureCallback
    public void onOverlaySizeChanged(int left, int top, int right, int bottom) {
        if (left >= this.imageBounds.left && top >= this.imageBounds.top && right <= this.imageBounds.right && bottom <= this.imageBounds.bottom && right - left > 5 && bottom - top > 5) {
            this.overlay.set(left, top, right, bottom);
            invalidate();
        }
    }
}
