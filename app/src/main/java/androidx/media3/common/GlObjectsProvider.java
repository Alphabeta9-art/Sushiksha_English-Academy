package androidx.media3.common;

import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import androidx.media3.common.util.GlUtil;

/* JADX INFO: loaded from: classes11.dex */
public interface GlObjectsProvider {

    @Deprecated
    public static final GlObjectsProvider DEFAULT = new GlObjectsProvider() { // from class: androidx.media3.common.GlObjectsProvider.1
        @Override // androidx.media3.common.GlObjectsProvider
        public EGLContext createEglContext(EGLDisplay eglDisplay, int openGlVersion, int[] configAttributes) throws GlUtil.GlException {
            return GlUtil.createEglContext(EGL14.EGL_NO_CONTEXT, eglDisplay, openGlVersion, configAttributes);
        }

        @Override // androidx.media3.common.GlObjectsProvider
        public EGLSurface createEglSurface(EGLDisplay eglDisplay, Object surface, int colorTransfer, boolean isEncoderInputSurface) throws GlUtil.GlException {
            return GlUtil.createEglSurface(eglDisplay, surface, colorTransfer, isEncoderInputSurface);
        }

        @Override // androidx.media3.common.GlObjectsProvider
        public EGLSurface createFocusedPlaceholderEglSurface(EGLContext eglContext, EGLDisplay eglDisplay, int[] configAttributes) throws GlUtil.GlException {
            return GlUtil.createFocusedPlaceholderEglSurface(eglContext, eglDisplay, configAttributes);
        }

        @Override // androidx.media3.common.GlObjectsProvider
        public GlTextureInfo createBuffersForTexture(int texId, int width, int height) throws GlUtil.GlException {
            int fboId = GlUtil.createFboForTexture(texId);
            return new GlTextureInfo(texId, fboId, -1, width, height);
        }
    };

    GlTextureInfo createBuffersForTexture(int i, int i2, int i3) throws GlUtil.GlException;

    EGLContext createEglContext(EGLDisplay eGLDisplay, int i, int[] iArr) throws GlUtil.GlException;

    EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i, boolean z) throws GlUtil.GlException;

    EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay, int[] iArr) throws GlUtil.GlException;
}
