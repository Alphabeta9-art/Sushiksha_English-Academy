package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes10.dex */
public class ImageViewCompat {
    public static ColorStateList getImageTintList(ImageView view) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.getImageTintList(view);
    }

    public static void setImageTintList(ImageView view, ColorStateList tintList) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setImageTintList(view, tintList);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static PorterDuff.Mode getImageTintMode(ImageView view) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.getImageTintMode(view);
    }

    public static void setImageTintMode(ImageView view, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setImageTintMode(view, mode);
        int i2 = Build.VERSION.SDK_INT;
    }

    private ImageViewCompat() {
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static ColorStateList getImageTintList(ImageView imageView) {
            return imageView.getImageTintList();
        }

        static void setImageTintList(ImageView imageView, ColorStateList tint) {
            imageView.setImageTintList(tint);
        }

        static PorterDuff.Mode getImageTintMode(ImageView imageView) {
            return imageView.getImageTintMode();
        }

        static void setImageTintMode(ImageView imageView, PorterDuff.Mode tintMode) {
            imageView.setImageTintMode(tintMode);
        }
    }
}
