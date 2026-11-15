package com.cloudinary.android.uploadwidget.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.cloudinary.android.uploadwidget.model.Dimensions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapUtils {
    public static Bitmap decodeSampledBitmapFromUri(Context context, Uri uri, int reqWidth, int reqHeight) throws FileNotFoundException {
        InputStream justDecodeBoundsStream = null;
        InputStream sampledBitmapStream = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            justDecodeBoundsStream = getUriInputStream(context, uri);
            BitmapFactory.decodeStream(justDecodeBoundsStream, null, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            sampledBitmapStream = getUriInputStream(context, uri);
            Bitmap sampledBitmap = BitmapFactory.decodeStream(sampledBitmapStream, null, options);
            Bitmap bitmap = getScaledBitmap(sampledBitmap, reqWidth, reqHeight);
            return bitmap;
        } finally {
            if (justDecodeBoundsStream != null) {
                try {
                    justDecodeBoundsStream.close();
                } catch (IOException e) {
                }
            }
            if (sampledBitmapStream != null) {
                try {
                    sampledBitmapStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public static Dimensions getBitmapDimensions(Context context, Uri uri) throws FileNotFoundException {
        InputStream justDecodeBoundsStream = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            justDecodeBoundsStream = getUriInputStream(context, uri);
            BitmapFactory.decodeStream(justDecodeBoundsStream, null, options);
            return new Dimensions(options.outWidth, options.outHeight);
        } finally {
            if (justDecodeBoundsStream != null) {
                try {
                    justDecodeBoundsStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static InputStream getUriInputStream(Context context, Uri uri) throws FileNotFoundException {
        return context.getContentResolver().openInputStream(uri);
    }

    private static Bitmap getScaledBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        if (reqWidth > 0 && reqHeight > 0) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float scale = Math.max(width / reqWidth, height / reqHeight);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int) (width / scale), (int) (height / scale), false);
            if (resized != null) {
                if (resized != bitmap) {
                    bitmap.recycle();
                }
                return resized;
            }
        }
        return bitmap;
    }
}
