package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

/* JADX INFO: loaded from: classes11.dex */
class PropertyValuesHolderUtils {
    static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.ofObject(property, path);
    }

    private PropertyValuesHolderUtils() {
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static <V> PropertyValuesHolder ofObject(Property<?, V> property, Path path) {
            return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
        }
    }
}
