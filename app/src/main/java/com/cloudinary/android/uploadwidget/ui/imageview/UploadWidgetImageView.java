package com.cloudinary.android.uploadwidget.ui.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.cloudinary.android.uploadwidget.model.BitmapManager;
import com.cloudinary.android.uploadwidget.model.CropPoints;
import com.cloudinary.android.uploadwidget.model.Dimensions;
import com.facebook.imagepipeline.common.RotationOptions;

/* JADX INFO: loaded from: classes11.dex */
public class UploadWidgetImageView extends FrameLayout {
    private Bitmap bitmap;
    private Rect bitmapBounds;
    private CropOverlayView cropOverlayView;
    private Uri imageUri;
    private ImageView imageView;
    private int originalWidth;
    private int rotationAngle;
    private boolean sizeChanged;

    public UploadWidgetImageView(Context context) {
        super(context);
        this.bitmapBounds = new Rect();
        init(context);
    }

    public UploadWidgetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.bitmapBounds = new Rect();
        init(context);
    }

    public UploadWidgetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.bitmapBounds = new Rect();
        init(context);
    }

    private void init(Context context) {
        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        addView(this.imageView);
        this.cropOverlayView = new CropOverlayView(context);
        this.cropOverlayView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.cropOverlayView);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.imageUri != null) {
            setBitmap(w, h);
        }
        this.sizeChanged = true;
    }

    private void setBitmap(int w, int h) {
        BitmapManager.get().load(getContext(), this.imageUri, w, h, new BitmapManager.LoadCallback() { // from class: com.cloudinary.android.uploadwidget.ui.imageview.UploadWidgetImageView.1
            @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
            public void onSuccess(Bitmap bitmap, Dimensions originalDimensions) {
                UploadWidgetImageView.this.bitmap = bitmap;
                if (UploadWidgetImageView.this.rotationAngle != 0) {
                    UploadWidgetImageView.this.rotateBitmapBy(UploadWidgetImageView.this.rotationAngle);
                }
                UploadWidgetImageView.this.updateImageViewBitmap();
                UploadWidgetImageView.this.originalWidth = originalDimensions.getWidth();
            }

            @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
            public void onFailure() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateImageViewBitmap() {
        this.imageView.setImageBitmap(this.bitmap);
        setBitmapBounds();
        this.cropOverlayView.reset();
    }

    public boolean isAspectRatioLocked() {
        return this.cropOverlayView.isAspectRatioLocked();
    }

    public void setAspectRatioLocked(boolean aspectRatioLocked) {
        this.cropOverlayView.setAspectRatioLocked(aspectRatioLocked);
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
        if (imageUri != null && this.sizeChanged) {
            setBitmap(getWidth(), getHeight());
        }
    }

    public void showCropOverlay() {
        this.cropOverlayView.setVisibility(0);
    }

    public void hideCropOverlay() {
        this.cropOverlayView.setVisibility(4);
    }

    public CropPoints getCropPoints() {
        float ratio = this.originalWidth / this.bitmap.getWidth();
        if (this.rotationAngle % RotationOptions.ROTATE_180 != 0) {
            ratio = this.originalWidth / this.bitmap.getHeight();
        }
        CropPoints cropPoints = this.cropOverlayView.getCropPoints();
        Point p1 = cropPoints.getPoint1();
        Point p2 = cropPoints.getPoint2();
        p1.x = (int) ((p1.x - this.bitmapBounds.left) * ratio);
        p1.y = (int) ((p1.y - this.bitmapBounds.top) * ratio);
        p2.x = (int) ((p2.x - this.bitmapBounds.left) * ratio);
        p2.y = (int) ((p2.y - this.bitmapBounds.top) * ratio);
        return cropPoints;
    }

    public Bitmap getResultBitmap() {
        CropPoints cropPoints = this.cropOverlayView.getCropPoints();
        Point p1 = cropPoints.getPoint1();
        Point p2 = cropPoints.getPoint2();
        if (p2.x - p1.x != this.bitmap.getWidth() || p2.y - p1.y != this.bitmap.getHeight()) {
            Bitmap resultBitmap = this.bitmap;
            return Bitmap.createBitmap(resultBitmap, p1.x - this.bitmapBounds.left, p1.y - this.bitmapBounds.top, p2.x - p1.x, p2.y - p1.y);
        }
        Bitmap resultBitmap2 = this.bitmap;
        return resultBitmap2;
    }

    public void rotateImage() {
        this.rotationAngle = (this.rotationAngle + 90) % 360;
        rotateBitmapBy(90);
        updateImageViewBitmap();
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rotateBitmapBy(int degrees) {
        float scale;
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, this.bitmap.getWidth() / 2, this.bitmap.getHeight() / 2);
        if (degrees % RotationOptions.ROTATE_180 != 0) {
            scale = Math.max(this.bitmap.getWidth() / getHeight(), this.bitmap.getHeight() / getWidth());
        } else {
            scale = Math.max(this.bitmap.getWidth() / getWidth(), this.bitmap.getHeight() / getHeight());
        }
        float dstWidth = this.bitmap.getWidth() / scale;
        float dstHeight = this.bitmap.getHeight() / scale;
        if (this.bitmap.getWidth() != dstWidth || this.bitmap.getHeight() != dstHeight) {
            float sx = dstWidth / this.bitmap.getWidth();
            float sy = dstHeight / this.bitmap.getHeight();
            matrix.postScale(sx, sy);
        }
        this.bitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), matrix, false);
    }

    private void setBitmapBounds() {
        int left = (getWidth() - this.bitmap.getWidth()) / 2;
        int top = (getHeight() - this.bitmap.getHeight()) / 2;
        this.bitmapBounds.set(left, top, this.bitmap.getWidth() + left, this.bitmap.getHeight() + top);
        this.cropOverlayView.set(this.bitmapBounds);
    }
}
