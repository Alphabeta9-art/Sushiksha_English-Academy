package com.cloudinary.android.uploadwidget.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.MimeTypes;
import com.facebook.common.util.UriUtil;

/* JADX INFO: loaded from: classes11.dex */
public class UriUtils {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:16:0x0056  */
    public static MediaType getMediaType(Context context, Uri uri) {
        String mimeType;
        if (uri.getScheme().equals(UriUtil.LOCAL_CONTENT_SCHEME)) {
            ContentResolver cr = context.getApplicationContext().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        }
        if (mimeType != null) {
            byte b = 0;
            String type = mimeType.split(DomExceptionUtils.SEPARATOR)[0];
            switch (type.hashCode()) {
                case 100313435:
                    if (!type.equals("image")) {
                        b = -1;
                    }
                    break;
                case 112202875:
                    if (!type.equals(MimeTypes.BASE_TYPE_VIDEO)) {
                        b = -1;
                    } else {
                        b = 1;
                    }
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    return MediaType.IMAGE;
                case 1:
                    return MediaType.VIDEO;
                default:
                    return null;
            }
        }
        return null;
    }

    public static Bitmap getVideoThumbnail(Context context, Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        return mediaMetadataRetriever.getFrameAtTime(1L);
    }
}
