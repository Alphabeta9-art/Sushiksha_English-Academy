package com.facebook.fresco.ui.common;

import android.net.Uri;
import com.facebook.common.internal.Fn;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class MultiUriHelper {
    @Nullable
    public static <T> Uri getMainUri(@Nullable T mainRequest, @Nullable T lowResRequest, @Nullable T[] firstAvailableRequest, Fn<T, Uri> requestToUri) {
        Uri uri;
        Uri uri2;
        if (mainRequest != null && (uri2 = requestToUri.apply(mainRequest)) != null) {
            return uri2;
        }
        if (firstAvailableRequest != null && firstAvailableRequest.length > 0 && firstAvailableRequest[0] != null && (uri = requestToUri.apply(firstAvailableRequest[0])) != null) {
            return uri;
        }
        if (lowResRequest != null) {
            return requestToUri.apply(lowResRequest);
        }
        return null;
    }
}
