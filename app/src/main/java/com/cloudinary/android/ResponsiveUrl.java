package com.cloudinary.android;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.Cloudinary;
import com.cloudinary.Url;

/* JADX INFO: loaded from: classes11.dex */
public class ResponsiveUrl {
    private static final int DEFAULT_MAX_DIMENSION = 1000;
    private static final int DEFAULT_MIN_DIMENSION = 200;
    private static final int DEFAULT_STEP_SIZE = 200;
    private static final SparseArray<Url> viewsInProgress = new SparseArray<>();
    private final boolean autoHeight;
    private final boolean autoWidth;
    private final Cloudinary cloudinary;
    private final String cropMode;
    private final String gravity;
    private int stepSize = 200;
    private int maxDimension = 1000;
    private int minDimension = 200;

    public interface Callback {
        void onUrlReady(Url url);
    }

    ResponsiveUrl(Cloudinary cloudinary, boolean autoWidth, boolean autoHeight, String cropMode, String gravity) {
        this.cloudinary = cloudinary;
        this.autoWidth = autoWidth;
        this.autoHeight = autoHeight;
        this.cropMode = cropMode;
        this.gravity = gravity;
    }

    public ResponsiveUrl stepSize(int stepSize) {
        this.stepSize = stepSize;
        return this;
    }

    public ResponsiveUrl maxDimension(int maxDimension) {
        this.maxDimension = maxDimension;
        return this;
    }

    public ResponsiveUrl minDimension(int minDimension) {
        this.minDimension = minDimension;
        return this;
    }

    public void generate(String publicId, View view, Callback callback) {
        generate(this.cloudinary.url().publicId(publicId), view, callback);
    }

    public void generate(final Url baseUrl, final View view, final Callback callback) {
        assertViewValidForResponsive(view);
        final int key = view.hashCode();
        int width = view.getWidth();
        int height = view.getHeight();
        if (conditionsAreMet(width, height)) {
            callback.onUrlReady(buildUrl(view, baseUrl));
            viewsInProgress.remove(key);
        } else {
            viewsInProgress.put(key, baseUrl);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.cloudinary.android.ResponsiveUrl.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (baseUrl.equals(ResponsiveUrl.viewsInProgress.get(key))) {
                        callback.onUrlReady(ResponsiveUrl.this.buildUrl(view, baseUrl));
                        ResponsiveUrl.viewsInProgress.remove(key);
                        return true;
                    }
                    return true;
                }
            });
        }
    }

    private void assertViewValidForResponsive(View view) {
        int i = Build.VERSION.SDK_INT;
        if ((view instanceof ImageView) && ((ImageView) view).getAdjustViewBounds()) {
            throw new IllegalArgumentException("Cannot use responsive Url with AdjustViewBounds");
        }
    }

    private boolean conditionsAreMet(int width, int height) {
        boolean widthOk = !this.autoWidth || width > 0;
        boolean heightOk = !this.autoHeight || height > 0;
        return widthOk && heightOk;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Url buildUrl(View view, Url baseUrl) {
        int contentWidth = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
        int contentHeight = (view.getHeight() - view.getPaddingTop()) - view.getPaddingBottom();
        return buildUrl(baseUrl, contentWidth, contentHeight);
    }

    public Url buildUrl(Url baseUrl, int width, int height) {
        Url url = baseUrl.m8550clone();
        url.transformation().chain();
        if (this.autoHeight) {
            url.transformation().height(Integer.valueOf(trimAndRoundUp(height)));
        }
        if (this.autoWidth) {
            url.transformation().width(Integer.valueOf(trimAndRoundUp(width)));
        }
        url.transformation().crop(this.cropMode).gravity(this.gravity);
        return url;
    }

    private int trimAndRoundUp(int dimension) {
        int value = (((dimension - 1) / this.stepSize) + 1) * this.stepSize;
        return Math.max(this.minDimension, Math.min(value, this.maxDimension));
    }

    public enum Preset {
        AUTO_FILL(true, true, "fill", "auto"),
        FIT(true, true, "fit", TtmlNode.CENTER);

        private final boolean autoHeight;
        private final boolean autoWidth;
        private final String cropMode;
        private final String gravity;

        Preset(boolean autoWidth, boolean autoHeight, String cropMode, String gravity) {
            this.autoWidth = autoWidth;
            this.autoHeight = autoHeight;
            this.cropMode = cropMode;
            this.gravity = gravity;
        }

        public ResponsiveUrl get(Cloudinary cloudinary) {
            return new ResponsiveUrl(cloudinary, this.autoWidth, this.autoHeight, this.cropMode, this.gravity);
        }
    }
}
