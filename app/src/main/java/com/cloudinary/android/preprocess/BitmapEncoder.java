package com.cloudinary.android.preprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.cloudinary.utils.StringUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final Format DEFAULT_FORMAT = Format.WEBP;
    private static final int DEFAULT_QUALITY = 100;
    private final Format format;
    private final int quality;

    public enum Format {
        WEBP,
        JPEG,
        PNG
    }

    public BitmapEncoder() {
        this(DEFAULT_FORMAT, 100);
    }

    public BitmapEncoder(Format format, int quality) {
        this.format = format;
        this.quality = quality;
    }

    @Override // com.cloudinary.android.preprocess.ResourceEncoder
    public String encode(Context context, Bitmap resource) throws ResourceCreationException {
        return saveFile(context, resource, this.quality, this.format);
    }

    protected final String saveFile(Context context, Bitmap resource, int quality, Format format) throws ResourceCreationException, FileNotFoundException {
        FileOutputStream fos = null;
        String fileName = UUID.randomUUID().toString();
        try {
            try {
                fos = context.openFileOutput(fileName, 0);
                resource.compress(adaptFormat(format), quality, fos);
                resource.recycle();
                if (fos != null) {
                    try {
                        fos.close();
                        if (StringUtils.isBlank(fileName)) {
                            context.deleteFile(fileName);
                        }
                    } catch (IOException e) {
                    }
                }
                return fileName;
            } catch (FileNotFoundException e2) {
                throw new ResourceCreationException("Could not create new file");
            }
        } catch (Throwable e3) {
            if (fos != null) {
                try {
                    fos.close();
                    if (StringUtils.isBlank(null)) {
                        context.deleteFile(fileName);
                    }
                } catch (IOException e4) {
                }
            }
            throw e3;
        }
    }

    private Bitmap.CompressFormat adaptFormat(Format format) {
        switch (format) {
            case WEBP:
                if (Build.VERSION.SDK_INT >= 30) {
                    return Bitmap.CompressFormat.WEBP_LOSSY;
                }
                return Bitmap.CompressFormat.WEBP;
            case JPEG:
                return Bitmap.CompressFormat.JPEG;
            default:
                return Bitmap.CompressFormat.PNG;
        }
    }
}
