package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.BufferedOutputStream;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006a A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #2 {all -> 0x00c9, blocks: (B:3:0x0022, B:16:0x005d, B:21:0x0064, B:23:0x006a, B:28:0x00c2, B:32:0x00c8, B:5:0x0034, B:7:0x003e, B:8:0x0046, B:12:0x0050, B:14:0x0056), top: B:40:0x0022, inners: #4 }] */
    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(Resource<Bitmap> resource, File file, Options options) {
        Bitmap bitmap = resource.get();
        Bitmap.CompressFormat format = getFormat(bitmap, options);
        GlideTrace.beginSectionFormat("encode: [%dx%d] %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), format);
        try {
            long start = LogTime.getLogTime();
            int quality = ((Integer) options.get(COMPRESSION_QUALITY)).intValue();
            boolean success = false;
            OutputStream os = null;
            try {
                try {
                    os = new FileOutputStream(file);
                    if (this.arrayPool != null) {
                        os = new BufferedOutputStream(os, this.arrayPool);
                    }
                    bitmap.compress(format, quality, os);
                    os.close();
                    success = true;
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Failed to encode Bitmap", e);
                    }
                    if (os != null) {
                    }
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
                    }
                    GlideTrace.endSection();
                    return success;
                }
                try {
                    os.close();
                } catch (IOException e2) {
                }
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
                }
                GlideTrace.endSection();
                return success;
            } catch (Throwable th) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            GlideTrace.endSection();
            throw th2;
        }
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat format = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (format != null) {
            return format;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
