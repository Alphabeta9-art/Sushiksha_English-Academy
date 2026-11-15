package com.facebook.common.util;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.internal.Preconditions;
import com.facebook.infer.annotation.Assertions;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class UriUtil {
    public static final String DATA_SCHEME = "data";
    public static final String HTTPS_SCHEME = "https";
    public static final String HTTP_SCHEME = "http";
    public static final String LOCAL_ASSET_SCHEME = "asset";
    private static final Uri LOCAL_CONTACT_IMAGE_URI = Uri.withAppendedPath((Uri) Assertions.assumeNotNull(ContactsContract.AUTHORITY_URI), "display_photo");
    public static final String LOCAL_CONTENT_SCHEME = "content";
    public static final String LOCAL_FILE_SCHEME = "file";
    public static final String LOCAL_RESOURCE_SCHEME = "res";
    public static final String QUALIFIED_RESOURCE_SCHEME = "android.resource";

    @Nullable
    public static URL uriToUrl(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNetworkUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return HTTPS_SCHEME.equals(scheme) || HTTP_SCHEME.equals(scheme);
    }

    public static boolean isLocalFileUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return LOCAL_FILE_SCHEME.equals(scheme);
    }

    public static boolean isLocalContentUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return LOCAL_CONTENT_SCHEME.equals(scheme);
    }

    public static boolean isLocalContactUri(Uri uri) {
        return uri.getPath() != null && isLocalContentUri(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith((String) Assertions.assumeNotNull(LOCAL_CONTACT_IMAGE_URI.getPath()));
    }

    public static boolean isLocalCameraUri(Uri uri) {
        String uriString = uri.toString();
        return uriString.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || uriString.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean isLocalAssetUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return LOCAL_ASSET_SCHEME.equals(scheme);
    }

    public static boolean isLocalResourceUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return LOCAL_RESOURCE_SCHEME.equals(scheme);
    }

    public static boolean isQualifiedResourceUri(@Nullable Uri uri) {
        String scheme = getSchemeOrNull(uri);
        return QUALIFIED_RESOURCE_SCHEME.equals(scheme);
    }

    public static boolean isDataUri(@Nullable Uri uri) {
        return "data".equals(getSchemeOrNull(uri));
    }

    @Nullable
    public static String getSchemeOrNull(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }

    @Nullable
    public static Uri parseUriOrNull(@Nullable String uriAsString) {
        if (uriAsString != null) {
            return Uri.parse(uriAsString);
        }
        return null;
    }

    @Nullable
    public static String getRealPathFromUri(ContentResolver contentResolver, Uri srcUri) {
        String[] selectionArgs;
        String selection;
        Uri uri;
        int idx;
        String result = null;
        String mimeTypeString = contentResolver.getType(srcUri);
        if (!isLocalContentUri(srcUri)) {
            if (!isLocalFileUri(srcUri)) {
                return null;
            }
            return srcUri.getPath();
        }
        boolean isVideo = mimeTypeString != null && mimeTypeString.startsWith("video/");
        int i = Build.VERSION.SDK_INT;
        if ("com.android.providers.media.documents".equals(srcUri.getAuthority())) {
            String documentId = DocumentsContract.getDocumentId(srcUri);
            Preconditions.checkNotNull(documentId);
            Uri uri2 = (Uri) Preconditions.checkNotNull(getExternalContentUri(isVideo));
            String selection2 = getMediaIdString(isVideo) + "=?";
            String[] selectionArgs2 = {documentId.split(":")[1]};
            selectionArgs = selectionArgs2;
            selection = selection2;
            uri = uri2;
        } else {
            selectionArgs = null;
            selection = null;
            uri = srcUri;
        }
        Cursor cursor = contentResolver.query(uri, new String[]{getDataPathString(isVideo)}, selection, selectionArgs, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst() && (idx = cursor.getColumnIndexOrThrow(getDataPathString(isVideo))) != -1) {
                    result = cursor.getString(idx);
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return result;
    }

    private static Uri getExternalContentUri(boolean isVideo) {
        if (isVideo) {
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    private static String getMediaIdString(boolean isVideo) {
        return "_id";
    }

    private static String getDataPathString(boolean isVideo) {
        return "_data";
    }

    @Nullable
    public static AssetFileDescriptor getAssetFileDescriptor(ContentResolver contentResolver, Uri srcUri) {
        if (!isLocalContentUri(srcUri)) {
            return null;
        }
        try {
            return contentResolver.openAssetFileDescriptor(srcUri, "r");
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Uri getUriForFile(File file) {
        return Uri.fromFile(file);
    }

    public static Uri getUriForResourceId(int resourceId) {
        return new Uri.Builder().scheme(LOCAL_RESOURCE_SCHEME).path(String.valueOf(resourceId)).build();
    }

    public static Uri getUriForQualifiedResource(String packageName, int resourceId) {
        return new Uri.Builder().scheme(QUALIFIED_RESOURCE_SCHEME).authority(packageName).path(String.valueOf(resourceId)).build();
    }
}
