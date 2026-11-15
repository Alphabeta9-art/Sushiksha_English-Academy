package com.cloudinary.android.uploadwidget.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.collection.LruCache;
import com.cloudinary.android.uploadwidget.utils.BitmapUtils;
import com.cloudinary.android.uploadwidget.utils.MediaType;
import com.cloudinary.android.uploadwidget.utils.UriUtils;
import com.cloudinary.utils.StringUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapManager {
    private static BitmapManager instance;
    private LruCache<String, Bitmap> memoryCache;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public interface LoadCallback {
        void onFailure();

        void onSuccess(Bitmap bitmap, Dimensions dimensions);
    }

    public interface SaveCallback {
        void onFailure();

        void onSuccess(Uri uri);
    }

    public static synchronized BitmapManager get() {
        if (instance == null) {
            instance = new BitmapManager();
        }
        return instance;
    }

    private BitmapManager() {
        initMemoryCache();
    }

    public void load(final Context context, final Uri uri, final int width, final int height, final LoadCallback callback) {
        this.executor.execute(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String hash = BitmapManager.this.getHash(uri.toString() + width + height);
                    Bitmap bitmap = (Bitmap) BitmapManager.this.memoryCache.get(hash);
                    if (bitmap == null) {
                        bitmap = BitmapUtils.decodeSampledBitmapFromUri(context, uri, width, height);
                        BitmapManager.this.memoryCache.put(hash, bitmap);
                    }
                    Dimensions dimensions = BitmapUtils.getBitmapDimensions(context, uri);
                    BitmapManager.this.onLoadSuccess(bitmap, dimensions, callback);
                } catch (Exception e) {
                    BitmapManager.this.onLoadFailed(callback);
                }
            }
        });
    }

    public void thumbnail(final Context context, final Uri uri, final int width, final int height, final LoadCallback callback) {
        this.executor.execute(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (UriUtils.getMediaType(context, uri) == MediaType.VIDEO) {
                        String hash = BitmapManager.this.getHash(uri.toString() + width + height);
                        Bitmap bitmap = (Bitmap) BitmapManager.this.memoryCache.get(hash);
                        if (bitmap == null) {
                            bitmap = UriUtils.getVideoThumbnail(context, uri);
                            BitmapManager.this.memoryCache.put(hash, bitmap);
                        }
                        Dimensions dimensions = BitmapUtils.getBitmapDimensions(context, uri);
                        BitmapManager.this.onLoadSuccess(bitmap, dimensions, callback);
                        return;
                    }
                    BitmapManager.this.onLoadFailed(callback);
                } catch (Exception e) {
                    BitmapManager.this.onLoadFailed(callback);
                }
            }
        });
    }

    public void save(final Context context, final Bitmap bitmap, final SaveCallback callback) {
        this.executor.execute(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.3
            @Override // java.lang.Runnable
            public void run() {
                FileOutputStream fos = null;
                String fileName = UUID.randomUUID().toString();
                try {
                    try {
                        try {
                            fos = context.openFileOutput(fileName, 0);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            Uri bitmapUri = Uri.fromFile(context.getFileStreamPath(fileName));
                            BitmapManager.this.onSaveSuccess(bitmapUri, callback);
                            if (fos != null) {
                                fos.close();
                                if (StringUtils.isBlank(fileName)) {
                                    context.deleteFile(fileName);
                                }
                            }
                        } catch (Exception e) {
                            BitmapManager.this.onSaveFailed(callback);
                            if (fos != null) {
                                fos.close();
                                if (StringUtils.isBlank(fileName)) {
                                }
                            }
                        }
                    } catch (Throwable th) {
                        if (fos != null) {
                            try {
                                fos.close();
                                if (StringUtils.isBlank(fileName)) {
                                    context.deleteFile(fileName);
                                }
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            }
        });
    }

    private void initMemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        this.memoryCache = new LruCache<String, Bitmap>(cacheSize) { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.collection.LruCache
            public int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadSuccess(final Bitmap bitmap, final Dimensions dimensions, final LoadCallback callback) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.5
            @Override // java.lang.Runnable
            public void run() {
                if (callback != null) {
                    callback.onSuccess(bitmap, dimensions);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadFailed(final LoadCallback callback) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.6
            @Override // java.lang.Runnable
            public void run() {
                if (callback != null) {
                    callback.onFailure();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSaveSuccess(final Uri resultUri, final SaveCallback callback) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.7
            @Override // java.lang.Runnable
            public void run() {
                if (callback != null) {
                    callback.onSuccess(resultUri);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSaveFailed(final SaveCallback callback) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.cloudinary.android.uploadwidget.model.BitmapManager.8
            @Override // java.lang.Runnable
            public void run() {
                if (callback != null) {
                    callback.onFailure();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getHash(String plaintext) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(plaintext.getBytes());
            String hash = new String(digest.digest());
            return hash;
        } catch (Exception e) {
            return "";
        }
    }
}
