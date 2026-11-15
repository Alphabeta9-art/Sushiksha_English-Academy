package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapDecoder implements ResourceDecoder<Bitmap> {
    private final int height;
    private final int width;

    public BitmapDecoder() {
        this(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public BitmapDecoder(int width, int height) {
        this.width = width;
        this.height = height;
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.cloudinary.android.preprocess.ResourceDecoder
    public Bitmap decode(Context context, Payload payload) throws PayloadNotFoundException, PayloadDecodeException {
        return bitmapFromPayload(context, payload, this.width, this.height);
    }

    protected final Bitmap bitmapFromPayload(Context context, Payload payload, int width, int height) throws PayloadNotFoundException, PayloadDecodeException {
        Object resource = payload.prepare(context);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = null;
        if (resource instanceof File) {
            BitmapFactory.decodeFile(((File) resource).getPath(), options);
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(((File) resource).getPath(), options);
        } else if (resource instanceof InputStream) {
            InputStream is = null;
            try {
                BitmapFactory.decodeStream((InputStream) resource, null, options);
                options.inSampleSize = calculateInSampleSize(options, width, height);
                options.inJustDecodeBounds = false;
                is = (InputStream) payload.prepare(context);
                bitmap = BitmapFactory.decodeStream(is, null, options);
                try {
                    ((InputStream) resource).close();
                } catch (IOException e) {
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Throwable th) {
                try {
                    ((InputStream) resource).close();
                } catch (IOException e3) {
                }
                if (is != null) {
                    try {
                        is.close();
                        throw th;
                    } catch (IOException e4) {
                        throw th;
                    }
                }
                throw th;
            }
        } else if (resource instanceof byte[]) {
            byte[] data = (byte[]) resource;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        if (bitmap == null) {
            throw new PayloadDecodeException();
        }
        return bitmap;
    }
}
