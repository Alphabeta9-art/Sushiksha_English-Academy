package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.EGLSurfaceTexture;
import androidx.media3.common.util.GlUtil;

/* JADX INFO: loaded from: classes11.dex */
public final class PlaceholderSurface extends Surface {
    private static final String TAG = "PlaceholderSurface";
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final PlaceholderSurfaceThread thread;
    private boolean threadReleased;

    public static synchronized boolean isSecureSupported(Context context) {
        if (!secureModeInitialized) {
            secureMode = getSecureMode(context);
            secureModeInitialized = true;
        }
        return secureMode != 0;
    }

    public static PlaceholderSurface newInstanceV17(Context context, boolean secure) {
        Assertions.checkState(!secure || isSecureSupported(context));
        PlaceholderSurfaceThread thread = new PlaceholderSurfaceThread();
        return thread.init(secure ? secureMode : 0);
    }

    private PlaceholderSurface(PlaceholderSurfaceThread thread, SurfaceTexture surfaceTexture, boolean secure) {
        super(surfaceTexture);
        this.thread = thread;
        this.secure = secure;
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private static int getSecureMode(Context context) {
        if (GlUtil.isProtectedContentExtensionSupported(context)) {
            if (GlUtil.isSurfacelessContextExtensionSupported()) {
                return 1;
            }
            return 2;
        }
        return 0;
    }

    private static class PlaceholderSurfaceThread extends HandlerThread implements Handler.Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 2;
        private EGLSurfaceTexture eglSurfaceTexture;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private PlaceholderSurface surface;

        public PlaceholderSurfaceThread() {
            super("ExoPlayer:PlaceholderSurface");
        }

        public PlaceholderSurface init(int secureMode) {
            start();
            this.handler = new Handler(getLooper(), this);
            this.eglSurfaceTexture = new EGLSurfaceTexture(this.handler);
            boolean wasInterrupted = false;
            synchronized (this) {
                this.handler.obtainMessage(1, secureMode, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        wasInterrupted = true;
                    }
                }
            }
            if (wasInterrupted) {
                Thread.currentThread().interrupt();
            }
            if (this.initException != null) {
                throw this.initException;
            }
            if (this.initError != null) {
                throw this.initError;
            }
            return (PlaceholderSurface) Assertions.checkNotNull(this.surface);
        }

        public void release() {
            Assertions.checkNotNull(this.handler);
            this.handler.sendEmptyMessage(2);
        }

        /* JADX WARN: Code duplicated, block: B:63:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0076, code lost:
        
            throw r0;
         */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean handleMessage(android.os.Message r5) {
            /*
                r4 = this;
                int r0 = r5.what
                r1 = 1
                switch(r0) {
                    case 1: goto L1d;
                    case 2: goto L7;
                    default: goto L6;
                }
            L6:
                return r1
            L7:
                r4.releaseInternal()     // Catch: java.lang.Throwable -> Le
            La:
                r4.quit()
                goto L17
            Le:
                r0 = move-exception
                java.lang.String r2 = "PlaceholderSurface"
                java.lang.String r3 = "Failed to release placeholder surface"
                androidx.media3.common.util.Log.e(r2, r3, r0)     // Catch: java.lang.Throwable -> L18
                goto La
            L17:
                return r1
            L18:
                r0 = move-exception
                r4.quit()
                throw r0
            L1d:
                int r0 = r5.arg1     // Catch: java.lang.Error -> L2c androidx.media3.common.util.GlUtil.GlException -> L40 java.lang.RuntimeException -> L59 java.lang.Throwable -> L6e
                r4.initInternal(r0)     // Catch: java.lang.Error -> L2c androidx.media3.common.util.GlUtil.GlException -> L40 java.lang.RuntimeException -> L59 java.lang.Throwable -> L6e
                monitor-enter(r4)
                r4.notify()     // Catch: java.lang.Throwable -> L2a
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L2a
                goto L68
            L28:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L2a
                goto L76
            L2a:
                r0 = move-exception
                goto L28
            L2c:
                r0 = move-exception
                java.lang.String r2 = "PlaceholderSurface"
                java.lang.String r3 = "Failed to initialize placeholder surface"
                androidx.media3.common.util.Log.e(r2, r3, r0)     // Catch: java.lang.Throwable -> L6e
                r4.initError = r0     // Catch: java.lang.Throwable -> L6e
                monitor-enter(r4)
                r4.notify()     // Catch: java.lang.Throwable -> L3e
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3e
                goto L68
            L3c:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3e
                goto L76
            L3e:
                r0 = move-exception
                goto L3c
            L40:
                r0 = move-exception
                java.lang.String r2 = "PlaceholderSurface"
                java.lang.String r3 = "Failed to initialize placeholder surface"
                androidx.media3.common.util.Log.e(r2, r3, r0)     // Catch: java.lang.Throwable -> L6e
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L6e
                r2.<init>(r0)     // Catch: java.lang.Throwable -> L6e
                r4.initException = r2     // Catch: java.lang.Throwable -> L6e
                monitor-enter(r4)
                r4.notify()     // Catch: java.lang.Throwable -> L57
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L57
                goto L68
            L55:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L57
                goto L76
            L57:
                r0 = move-exception
                goto L55
            L59:
                r0 = move-exception
                java.lang.String r2 = "PlaceholderSurface"
                java.lang.String r3 = "Failed to initialize placeholder surface"
                androidx.media3.common.util.Log.e(r2, r3, r0)     // Catch: java.lang.Throwable -> L6e
                r4.initException = r0     // Catch: java.lang.Throwable -> L6e
                monitor-enter(r4)
                r4.notify()     // Catch: java.lang.Throwable -> L6c
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L6c
            L68:
                return r1
            L6a:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L6c
                goto L76
            L6c:
                r0 = move-exception
                goto L6a
            L6e:
                r0 = move-exception
                monitor-enter(r4)
                r4.notify()     // Catch: java.lang.Throwable -> L77
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L77
                throw r0
            L75:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L77
            L76:
                throw r0
            L77:
                r0 = move-exception
                goto L75
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.PlaceholderSurface.PlaceholderSurfaceThread.handleMessage(android.os.Message):boolean");
        }

        private void initInternal(int secureMode) throws GlUtil.GlException {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.init(secureMode);
            this.surface = new PlaceholderSurface(this, this.eglSurfaceTexture.getSurfaceTexture(), secureMode != 0);
        }

        private void releaseInternal() {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.release();
        }
    }
}
