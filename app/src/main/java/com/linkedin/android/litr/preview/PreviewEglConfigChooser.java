package com.linkedin.android.litr.preview;

import android.opengl.GLSurfaceView;
import android.os.Build;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes13.dex */
class PreviewEglConfigChooser implements GLSurfaceView.EGLConfigChooser {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 2;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final boolean USE_RGB_888;
    private final int alphaSize;
    private final int blueSize;
    private final int[] configSpec;
    private final int depthSize;
    private final int greenSize;
    private final int redSize;
    private final int stencilSize;

    static {
        int i = Build.VERSION.SDK_INT;
        USE_RGB_888 = true;
    }

    PreviewEglConfigChooser() {
        this(USE_RGB_888 ? 8 : 5, USE_RGB_888 ? 8 : 6, USE_RGB_888 ? 8 : 5, 0, 0, 0, 2);
    }

    PreviewEglConfigChooser(final int redSize, final int greenSize, final int blueSize, final int alphaSize, final int depthSize, final int stencilSize, final int version) {
        this.configSpec = filterConfigSpec(new int[]{12324, redSize, 12323, greenSize, 12322, blueSize, 12321, alphaSize, 12325, depthSize, 12326, stencilSize, 12344}, version);
        this.redSize = redSize;
        this.greenSize = greenSize;
        this.blueSize = blueSize;
        this.alphaSize = alphaSize;
        this.depthSize = depthSize;
        this.stencilSize = stencilSize;
    }

    private int[] filterConfigSpec(final int[] configSpec, final int version) {
        if (version != 2) {
            return configSpec;
        }
        int len = configSpec.length;
        int[] newConfigSpec = new int[len + 2];
        System.arraycopy(configSpec, 0, newConfigSpec, 0, len - 1);
        newConfigSpec[len - 1] = 12352;
        newConfigSpec[len] = 4;
        newConfigSpec[len + 1] = 12344;
        return newConfigSpec;
    }

    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(final EGL10 egl, final EGLDisplay display) {
        int[] num_config = new int[1];
        if (!egl.eglChooseConfig(display, this.configSpec, null, 0, num_config)) {
            throw new IllegalArgumentException("eglChooseConfig failed");
        }
        int config_size = num_config[0];
        if (config_size <= 0) {
            throw new IllegalArgumentException("No configs match configSpec");
        }
        EGLConfig[] configs = new EGLConfig[config_size];
        if (!egl.eglChooseConfig(display, this.configSpec, configs, config_size, num_config)) {
            throw new IllegalArgumentException("eglChooseConfig#2 failed");
        }
        EGLConfig config = chooseConfig(egl, display, configs);
        if (config == null) {
            throw new IllegalArgumentException("No config chosen");
        }
        return config;
    }

    private EGLConfig chooseConfig(final EGL10 egl, final EGLDisplay display, final EGLConfig[] configs) {
        int length = configs.length;
        int i = 0;
        while (i < length) {
            EGLConfig config = configs[i];
            EGL10 egl2 = egl;
            EGLDisplay display2 = display;
            int d = findConfigAttrib(egl2, display2, config, 12325, 0);
            int s = findConfigAttrib(egl2, display2, config, 12326, 0);
            if (d >= this.depthSize && s >= this.stencilSize) {
                int r = findConfigAttrib(egl2, display2, config, 12324, 0);
                int g = findConfigAttrib(egl2, display2, config, 12323, 0);
                int b = findConfigAttrib(egl2, display2, config, 12322, 0);
                int a = findConfigAttrib(egl2, display2, config, 12321, 0);
                if (r == this.redSize && g == this.greenSize && b == this.blueSize && a == this.alphaSize) {
                    return config;
                }
            }
            i++;
            egl = egl2;
            display = display2;
        }
        return null;
    }

    private int findConfigAttrib(final EGL10 egl, final EGLDisplay display, final EGLConfig config, final int attribute, final int defaultValue) {
        int[] value = new int[1];
        if (egl.eglGetConfigAttrib(display, config, attribute, value)) {
            return value[0];
        }
        return defaultValue;
    }
}
