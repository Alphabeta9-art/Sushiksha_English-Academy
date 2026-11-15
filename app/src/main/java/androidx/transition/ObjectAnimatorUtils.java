package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

/* JADX INFO: loaded from: classes11.dex */
class ObjectAnimatorUtils {
    static <T> ObjectAnimator ofPointF(T target, Property<T, PointF> property, Path path) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.ofObject(target, property, path);
    }

    private ObjectAnimatorUtils() {
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static <T, V> ObjectAnimator ofObject(T target, Property<T, V> property, Path path) {
            return ObjectAnimator.ofObject(target, property, (TypeConverter) null, path);
        }
    }
}
