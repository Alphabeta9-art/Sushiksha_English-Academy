package com.linkedin.android.litr.render;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;

/* JADX INFO: loaded from: classes13.dex */
class VideoRenderOutputSurface {
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    private Surface surface;
    private EGLDisplay eglDisplay = EGL14.EGL_NO_DISPLAY;
    private EGLContext eglContext = EGL14.EGL_NO_CONTEXT;
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    VideoRenderOutputSurface(Surface surface) {
        this.surface = surface;
        eglSetup();
        makeCurrent();
    }

    boolean swapBuffers() {
        return EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
    }

    void setPresentationTime(long nanoseconds) {
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, nanoseconds);
    }

    public void release() {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
            EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay);
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;
            this.eglContext = EGL14.EGL_NO_CONTEXT;
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
        if (this.surface != null) {
            this.surface.release();
            this.surface = null;
        }
    }

    private void eglSetup() {
        this.eglDisplay = EGL14.eglGetDisplay(0);
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] version = new int[2];
        if (!EGL14.eglInitialize(this.eglDisplay, version, 0, version, 1)) {
            this.eglDisplay = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        int[] egl14ConfigAttributes = {12324, 8, 12323, 8, 12322, 8, 12352, 4, EGL_RECORDABLE_ANDROID, 1, 12344};
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (EGL14.eglChooseConfig(this.eglDisplay, egl14ConfigAttributes, 0, configs, 0, configs.length, numConfigs, 0)) {
            int[] egl14ContextAttributes = {12440, 2, 12344};
            this.eglContext = EGL14.eglCreateContext(this.eglDisplay, configs[0], EGL14.EGL_NO_CONTEXT, egl14ContextAttributes, 0);
            checkEglError("eglCreateContext");
            if (this.eglContext == null) {
                throw new RuntimeException("null context");
            }
            int[] surfaceAttribs = {12344};
            this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, configs[0], this.surface, surfaceAttribs, 0);
            checkEglError("eglCreateWindowSurface");
            if (this.eglSurface == null) {
                throw new RuntimeException("surface was null");
            }
            return;
        }
        throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
    }

    private void makeCurrent() {
        if (!EGL14.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    private void checkEglError(String message) {
        int error = EGL14.eglGetError();
        if (error != 12288) {
            throw new RuntimeException(message + ": EGL error: 0x" + Integer.toHexString(error));
        }
    }
}
