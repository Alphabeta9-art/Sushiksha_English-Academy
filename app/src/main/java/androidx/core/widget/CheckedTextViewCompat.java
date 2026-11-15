package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CheckedTextView;
import androidx.annotation.ReplaceWith;

/* JADX INFO: loaded from: classes10.dex */
public final class CheckedTextViewCompat {
    private CheckedTextViewCompat() {
    }

    public static void setCheckMarkTintList(CheckedTextView textView, ColorStateList tint) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setCheckMarkTintList(textView, tint);
    }

    public static ColorStateList getCheckMarkTintList(CheckedTextView textView) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.getCheckMarkTintList(textView);
    }

    public static void setCheckMarkTintMode(CheckedTextView textView, PorterDuff.Mode tintMode) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setCheckMarkTintMode(textView, tintMode);
    }

    public static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView textView) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.getCheckMarkTintMode(textView);
    }

    @ReplaceWith(expression = "textView.getCheckMarkDrawable()")
    @Deprecated
    public static Drawable getCheckMarkDrawable(CheckedTextView textView) {
        return textView.getCheckMarkDrawable();
    }

    private static class Api21Impl {
        private Api21Impl() {
        }

        static void setCheckMarkTintList(CheckedTextView textView, ColorStateList tint) {
            textView.setCheckMarkTintList(tint);
        }

        static ColorStateList getCheckMarkTintList(CheckedTextView textView) {
            return textView.getCheckMarkTintList();
        }

        static void setCheckMarkTintMode(CheckedTextView textView, PorterDuff.Mode tintMode) {
            textView.setCheckMarkTintMode(tintMode);
        }

        static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView textView) {
            return textView.getCheckMarkTintMode();
        }
    }
}
