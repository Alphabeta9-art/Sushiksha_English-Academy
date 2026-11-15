package com.linkedin.android.litr.preview;

import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes13.dex */
class PreviewEglContextFactory implements GLSurfaceView.EGLContextFactory {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final String TAG = "EContextFactory";
    private final int EGL_CLIENT_VERSION = 2;

    PreviewEglContextFactory() {
    }

    @Override // android.opengl.GLSurfaceView.EGLContextFactory
    public EGLContext createContext(final EGL10 egl, final EGLDisplay display, final EGLConfig config) {
        int[] attrib_list = {EGL_CONTEXT_CLIENT_VERSION, 2, 12344};
        return egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT, attrib_list);
    }

    @Override // android.opengl.GLSurfaceView.EGLContextFactory
    public void destroyContext(final EGL10 egl, final EGLDisplay display, final EGLContext context) {
        if (!egl.eglDestroyContext(display, context)) {
            Log.e(TAG, "display:" + display + " context: " + context);
            throw new RuntimeException("eglDestroyContex" + egl.eglGetError());
        }
    }
}
