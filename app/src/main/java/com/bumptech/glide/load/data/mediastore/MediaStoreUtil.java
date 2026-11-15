package com.bumptech.glide.load.data.mediastore;

import android.net.Uri;
import androidx.media3.common.MimeTypes;
import com.facebook.common.util.UriUtil;

/* JADX INFO: loaded from: classes11.dex */
public final class MediaStoreUtil {
    private static final int MINI_THUMB_HEIGHT = 384;
    private static final int MINI_THUMB_WIDTH = 512;

    private MediaStoreUtil() {
    }

    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && UriUtil.LOCAL_CONTENT_SCHEME.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    public static boolean isAndroidPickerUri(Uri uri) {
        return isMediaStoreUri(uri) && uri.getPathSegments().contains("picker");
    }

    private static boolean isVideoUri(Uri uri) {
        return uri.getPathSegments().contains(MimeTypes.BASE_TYPE_VIDEO);
    }

    public static boolean isMediaStoreVideoUri(Uri uri) {
        return isMediaStoreUri(uri) && isVideoUri(uri);
    }

    public static boolean isMediaStoreImageUri(Uri uri) {
        return isMediaStoreUri(uri) && !isVideoUri(uri);
    }

    public static boolean isThumbnailSize(int width, int height) {
        return width != Integer.MIN_VALUE && height != Integer.MIN_VALUE && width <= 512 && height <= 384;
    }
}
