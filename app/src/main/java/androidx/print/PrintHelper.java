package androidx.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes11.dex */
public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    final Object mLock = new Object();
    int mScaleMode = 2;
    int mColorMode = 2;
    int mOrientation = 1;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        PRINT_ACTIVITY_RESPECTS_ORIENTATION = true;
        int i3 = Build.VERSION.SDK_INT;
        IS_MIN_MARGINS_HANDLING_CORRECT = true;
    }

    public static boolean systemSupportsPrint() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public PrintHelper(Context context) {
        this.mContext = context;
    }

    public void setScaleMode(int scaleMode) {
        this.mScaleMode = scaleMode;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int colorMode) {
        this.mColorMode = colorMode;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public int getOrientation() {
        int i = Build.VERSION.SDK_INT;
        if (this.mOrientation == 0) {
            return 1;
        }
        return this.mOrientation;
    }

    public void printBitmap(String jobName, Bitmap bitmap) {
        printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        PrintAttributes.MediaSize mediaSize;
        int i = Build.VERSION.SDK_INT;
        if (bitmap == null) {
            return;
        }
        PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
        if (isPortrait(bitmap)) {
            mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
        } else {
            mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        }
        PrintAttributes attr = new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
        printManager.print(jobName, new PrintBitmapAdapter(jobName, this.mScaleMode, bitmap, callback), attr);
    }

    private class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        PrintBitmapAdapter(String jobName, int fittingMode, Bitmap bitmap, OnPrintFinishCallback callback) {
            this.mJobName = jobName;
            this.mFittingMode = fittingMode;
            this.mBitmap = bitmap;
            this.mCallback = callback;
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.mAttributes = newPrintAttributes;
            PrintDocumentInfo info = new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build();
            boolean changed = !newPrintAttributes.equals(oldPrintAttributes);
            layoutResultCallback.onLayoutFinished(info, changed);
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
        }
    }

    public void printBitmap(String jobName, Uri imageFile) throws FileNotFoundException {
        printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
        int i = Build.VERSION.SDK_INT;
        PrintDocumentAdapter printDocumentAdapter = new PrintUriAdapter(jobName, imageFile, callback, this.mScaleMode);
        PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1 || this.mOrientation == 0) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
        } else if (this.mOrientation == 2) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
        PrintAttributes attr = builder.build();
        printManager.print(jobName, printDocumentAdapter, attr);
    }

    private class PrintUriAdapter extends PrintDocumentAdapter {
        PrintAttributes mAttributes;
        Bitmap mBitmap = null;
        final OnPrintFinishCallback mCallback;
        final int mFittingMode;
        final Uri mImageFile;
        final String mJobName;
        AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

        PrintUriAdapter(String jobName, Uri imageFile, OnPrintFinishCallback callback, int fittingMode) {
            this.mJobName = jobName;
            this.mImageFile = imageFile;
            this.mCallback = callback;
            this.mFittingMode = fittingMode;
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [androidx.print.PrintHelper$PrintUriAdapter$1] */
        @Override // android.print.PrintDocumentAdapter
        public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) throws Throwable {
            synchronized (this) {
                try {
                    this.mAttributes = newPrintAttributes;
                } catch (Throwable th) {
                    th = th;
                    while (true) {
                        throw th;
                    }
                }
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else {
                if (this.mBitmap != null) {
                    PrintDocumentInfo info = new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build();
                    boolean changed = !newPrintAttributes.equals(oldPrintAttributes);
                    layoutResultCallback.onLayoutFinished(info, changed);
                    return;
                }
                this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: androidx.print.PrintHelper.PrintUriAdapter.1
                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.print.PrintHelper.PrintUriAdapter.1.1
                            @Override // android.os.CancellationSignal.OnCancelListener
                            public void onCancel() {
                                PrintUriAdapter.this.cancelLoad();
                                cancel(false);
                            }
                        });
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Bitmap doInBackground(Uri... uris) {
                        try {
                            return PrintHelper.this.loadConstrainedBitmap(PrintUriAdapter.this.mImageFile);
                        } catch (FileNotFoundException e) {
                            return null;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* JADX WARN: Code duplicated, block: B:26:0x0057  */
                    /* JADX WARN: Code duplicated, block: B:27:0x007c  */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Bitmap bitmap) {
                        Bitmap bitmapCreateBitmap;
                        PrintAttributes.MediaSize mediaSize;
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION || PrintHelper.this.mOrientation == 0)) {
                            synchronized (this) {
                                mediaSize = PrintUriAdapter.this.mAttributes.getMediaSize();
                            }
                            if (mediaSize != null && mediaSize.isPortrait() != PrintHelper.isPortrait(bitmap)) {
                                Matrix matrix = new Matrix();
                                matrix.postRotate(90.0f);
                                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            }
                            PrintUriAdapter.this.mBitmap = bitmapCreateBitmap;
                            if (bitmapCreateBitmap != null) {
                                layoutResultCallback.onLayoutFailed(null);
                            } else {
                                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(PrintUriAdapter.this.mJobName).setContentType(1).setPageCount(1).build(), !newPrintAttributes.equals(oldPrintAttributes));
                            }
                            PrintUriAdapter.this.mLoadBitmap = null;
                        }
                        bitmapCreateBitmap = bitmap;
                        PrintUriAdapter.this.mBitmap = bitmapCreateBitmap;
                        if (bitmapCreateBitmap != null) {
                            layoutResultCallback.onLayoutFailed(null);
                        } else {
                            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(PrintUriAdapter.this.mJobName).setContentType(1).setPageCount(1).build(), !newPrintAttributes.equals(oldPrintAttributes));
                        }
                        PrintUriAdapter.this.mLoadBitmap = null;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onCancelled(Bitmap result) {
                        layoutResultCallback.onLayoutCancelled();
                        PrintUriAdapter.this.mLoadBitmap = null;
                    }
                }.execute(new Uri[0]);
            }
        }

        void cancelLoad() {
            synchronized (PrintHelper.this.mLock) {
                if (PrintHelper.this.mDecodeOptions != null) {
                    int i = Build.VERSION.SDK_INT;
                    PrintHelper.this.mDecodeOptions = null;
                }
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            cancelLoad();
            if (this.mLoadBitmap != null) {
                this.mLoadBitmap.cancel(true);
            }
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static boolean isPortrait(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    private static PrintAttributes.Builder copyAttributes(PrintAttributes other) {
        PrintAttributes.Builder b = new PrintAttributes.Builder().setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
        if (other.getColorMode() != 0) {
            b.setColorMode(other.getColorMode());
        }
        int i = Build.VERSION.SDK_INT;
        if (other.getDuplexMode() != 0) {
            b.setDuplexMode(other.getDuplexMode());
        }
        return b;
    }

    static Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
        float scale;
        Matrix matrix = new Matrix();
        float scale2 = content.width() / imageWidth;
        if (fittingMode == 2) {
            scale = Math.max(scale2, content.height() / imageHeight);
        } else {
            scale = Math.min(scale2, content.height() / imageHeight);
        }
        matrix.postScale(scale, scale);
        float translateX = (content.width() - (imageWidth * scale)) / 2.0f;
        float translateY = (content.height() - (imageHeight * scale)) / 2.0f;
        matrix.postTranslate(translateX, translateY);
        return matrix;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.print.PrintHelper$1] */
    void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        final PrintAttributes pdfAttributes;
        if (IS_MIN_MARGINS_HANDLING_CORRECT) {
            pdfAttributes = attributes;
        } else {
            pdfAttributes = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        }
        new AsyncTask<Void, Void, Throwable>() { // from class: androidx.print.PrintHelper.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Code duplicated, block: B:35:0x00cd A[Catch: all -> 0x00e9, DONT_GENERATE, TryCatch #3 {all -> 0x00e9, blocks: (B:2:0x0000, B:5:0x000a, B:19:0x0099, B:21:0x00a0, B:24:0x00a7, B:35:0x00cd, B:28:0x00bb, B:30:0x00c2, B:33:0x00c9, B:38:0x00d2, B:40:0x00d9, B:43:0x00e0, B:45:0x00e4, B:47:0x00e8, B:9:0x002b, B:11:0x0033, B:13:0x0064, B:17:0x0086, B:27:0x00ac, B:16:0x0078, B:12:0x0041), top: B:56:0x0000, inners: #0 }] */
            @Override // android.os.AsyncTask
            public Throwable doInBackground(Void... params) {
                RectF contentRect;
                try {
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    PrintedPdfDocument pdfDocument = new PrintedPdfDocument(PrintHelper.this.mContext, pdfAttributes);
                    Bitmap maybeGrayscale = PrintHelper.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    try {
                        PdfDocument.Page page = pdfDocument.startPage(1);
                        if (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                            contentRect = new RectF(page.getInfo().getContentRect());
                        } else {
                            PrintedPdfDocument dummyDocument = new PrintedPdfDocument(PrintHelper.this.mContext, attributes);
                            PdfDocument.Page dummyPage = dummyDocument.startPage(1);
                            RectF contentRect2 = new RectF(dummyPage.getInfo().getContentRect());
                            dummyDocument.finishPage(dummyPage);
                            dummyDocument.close();
                            contentRect = contentRect2;
                        }
                        Matrix matrix = PrintHelper.getMatrix(maybeGrayscale.getWidth(), maybeGrayscale.getHeight(), contentRect, fittingMode);
                        if (!PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                            matrix.postTranslate(contentRect.left, contentRect.top);
                            page.getCanvas().clipRect(contentRect);
                        }
                        page.getCanvas().drawBitmap(maybeGrayscale, matrix, null);
                        pdfDocument.finishPage(page);
                        if (!cancellationSignal.isCanceled()) {
                            pdfDocument.writeTo(new FileOutputStream(fileDescriptor.getFileDescriptor()));
                        }
                        return null;
                    } finally {
                        pdfDocument.close();
                        if (fileDescriptor != null) {
                            try {
                                fileDescriptor.close();
                            } catch (IOException e) {
                            }
                        }
                        if (maybeGrayscale != bitmap) {
                            maybeGrayscale.recycle();
                        }
                    }
                } catch (Throwable t) {
                    return t;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Throwable throwable) {
                if (cancellationSignal.isCanceled()) {
                    writeResultCallback.onWriteCancelled();
                } else if (throwable == null) {
                    writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(PrintHelper.LOG_TAG, "Error writing printed content", throwable);
                    writeResultCallback.onWriteFailed(null);
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x005f, code lost:
    
        throw r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.graphics.Bitmap loadConstrainedBitmap(android.net.Uri r11) throws java.lang.Throwable {
        /*
            r10 = this;
            if (r11 == 0) goto L6a
            android.content.Context r0 = r10.mContext
            if (r0 == 0) goto L6a
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            r10.loadBitmap(r11, r0)
            int r2 = r0.outWidth
            int r3 = r0.outHeight
            r4 = 0
            if (r2 <= 0) goto L69
            if (r3 <= 0) goto L69
            int r5 = java.lang.Math.max(r2, r3)
            r6 = 1
        L1f:
            r7 = 3500(0xdac, float:4.905E-42)
            if (r5 <= r7) goto L28
            int r5 = r5 >>> 1
            int r6 = r6 << 1
            goto L1f
        L28:
            if (r6 <= 0) goto L68
            int r7 = java.lang.Math.min(r2, r3)
            int r7 = r7 / r6
            if (r7 <= 0) goto L68
            java.lang.Object r7 = r10.mLock
            monitor-enter(r7)
            r8 = 0
            android.graphics.BitmapFactory$Options r9 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L62
            r9.<init>()     // Catch: java.lang.Throwable -> L62
            r10.mDecodeOptions = r9     // Catch: java.lang.Throwable -> L62
            android.graphics.BitmapFactory$Options r9 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L62
            r9.inMutable = r1     // Catch: java.lang.Throwable -> L62
            android.graphics.BitmapFactory$Options r1 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L62
            r1.inSampleSize = r6     // Catch: java.lang.Throwable -> L62
            android.graphics.BitmapFactory$Options r1 = r10.mDecodeOptions     // Catch: java.lang.Throwable -> L62
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L66
            android.graphics.Bitmap r7 = r10.loadBitmap(r11, r1)     // Catch: java.lang.Throwable -> L56
            java.lang.Object r8 = r10.mLock
            monitor-enter(r8)
            r10.mDecodeOptions = r4     // Catch: java.lang.Throwable -> L54
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L54
            return r7
        L52:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L54
            goto L5f
        L54:
            r4 = move-exception
            goto L52
        L56:
            r7 = move-exception
            java.lang.Object r8 = r10.mLock
            monitor-enter(r8)
            r10.mDecodeOptions = r4     // Catch: java.lang.Throwable -> L60
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L60
            throw r7
        L5e:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L60
        L5f:
            throw r4
        L60:
            r4 = move-exception
            goto L5e
        L62:
            r4 = move-exception
            r1 = r8
        L64:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L66
            throw r4
        L66:
            r4 = move-exception
            goto L64
        L68:
            return r4
        L69:
            return r4
        L6a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "bad argument to getScaledBitmap"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.print.PrintHelper.loadConstrainedBitmap(android.net.Uri):android.graphics.Bitmap");
    }

    private Bitmap loadBitmap(Uri uri, BitmapFactory.Options o) throws FileNotFoundException {
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream is = null;
        try {
            is = this.mContext.getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(is, null, o);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException t) {
                    Log.w(LOG_TAG, "close fail ", t);
                }
            }
        }
    }

    static Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
        if (colorMode != 1) {
            return original;
        }
        Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(grayscale);
        Paint p = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0.0f);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        p.setColorFilter(f);
        c.drawBitmap(original, 0.0f, 0.0f, p);
        c.setBitmap(null);
        return grayscale;
    }
}
