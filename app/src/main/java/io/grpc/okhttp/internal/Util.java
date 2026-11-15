package io.grpc.okhttp.internal;

import com.bumptech.glide.load.Key;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public final class Util {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);

    private Util() {
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static <T> List<T> immutableList(T[] elements) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) elements.clone()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] intersect(Class<T> cls, T[] tArr, T[] tArr2) {
        List listIntersect = intersect(tArr, tArr2);
        return (T[]) listIntersect.toArray((Object[]) Array.newInstance((Class<?>) cls, listIntersect.size()));
    }

    private static <T> List<T> intersect(T[] first, T[] second) {
        List<T> result = new ArrayList<>();
        for (T a : first) {
            for (T b : second) {
                if (a.equals(b)) {
                    result.add(b);
                    break;
                }
            }
        }
        return result;
    }
}
