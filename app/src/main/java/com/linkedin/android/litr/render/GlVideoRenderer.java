package com.linkedin.android.litr.render;

import android.media.MediaFormat;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Build;
import android.view.Surface;
import com.facebook.imagepipeline.common.RotationOptions;
import com.linkedin.android.litr.codec.Frame;
import com.linkedin.android.litr.filter.GlFilter;
import com.linkedin.android.litr.filter.GlFrameRenderFilter;
import com.linkedin.android.litr.filter.video.gl.DefaultVideoFrameRenderFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class GlVideoRenderer implements Renderer {
    protected static final String KEY_ROTATION;
    private VideoRenderInputSurface inputSurface;
    private boolean inputSurfaceTextureInitialized;
    private VideoRenderOutputSurface outputSurface;
    private float[] mvpMatrix = new float[16];
    private List<GlFilter> filters = new ArrayList();

    static {
        int i = Build.VERSION.SDK_INT;
        KEY_ROTATION = "rotation-degrees";
    }

    public GlVideoRenderer(List<GlFilter> filters) {
        if (filters == null) {
            this.filters.add(new DefaultVideoFrameRenderFilter());
            return;
        }
        boolean hasFrameRenderFilter = false;
        for (GlFilter filter : filters) {
            if (filter instanceof GlFrameRenderFilter) {
                hasFrameRenderFilter = true;
                break;
            }
        }
        if (!hasFrameRenderFilter) {
            this.filters.add(new DefaultVideoFrameRenderFilter());
        }
        this.filters.addAll(filters);
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void init(Surface outputSurface, MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
        if (outputSurface == null) {
            throw new IllegalArgumentException("GlVideoRenderer requires an output surface");
        }
        if (targetMediaFormat == null) {
            throw new IllegalArgumentException("GlVideoRenderer requires target media format");
        }
        int rotation = 0;
        if (targetMediaFormat.containsKey(KEY_ROTATION)) {
            rotation = targetMediaFormat.getInteger(KEY_ROTATION);
        } else if (sourceMediaFormat != null && sourceMediaFormat.containsKey(KEY_ROTATION)) {
            rotation = sourceMediaFormat.getInteger(KEY_ROTATION);
        }
        float aspectRatio = 1.0f;
        if (targetMediaFormat.containsKey("width") && targetMediaFormat.containsKey("height")) {
            aspectRatio = targetMediaFormat.getInteger("width") / targetMediaFormat.getInteger("height");
        }
        this.outputSurface = new VideoRenderOutputSurface(outputSurface);
        this.inputSurface = new VideoRenderInputSurface();
        initMvpMatrix(rotation, aspectRatio);
        for (GlFilter filter : this.filters) {
            filter.init();
            filter.setVpMatrix(Arrays.copyOf(this.mvpMatrix, this.mvpMatrix.length), 0);
        }
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void onMediaFormatChanged(MediaFormat sourceMediaFormat, MediaFormat targetMediaFormat) {
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public Surface getInputSurface() {
        if (this.inputSurface != null) {
            return this.inputSurface.getSurface();
        }
        return null;
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void renderFrame(Frame inputFrame, long presentationTimeNs) {
        this.inputSurface.awaitNewImage();
        drawFrame(presentationTimeNs);
        this.outputSurface.setPresentationTime(presentationTimeNs);
        this.outputSurface.swapBuffers();
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public void release() {
        for (GlFilter filter : this.filters) {
            filter.release();
        }
        this.inputSurface.release();
        this.outputSurface.release();
    }

    @Override // com.linkedin.android.litr.render.Renderer
    public boolean hasFilters() {
        return (this.filters == null || this.filters.isEmpty()) ? false : true;
    }

    private void drawFrame(long presentationTimeNs) {
        initInputSurfaceTexture();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        for (GlFilter filter : this.filters) {
            filter.apply(presentationTimeNs);
        }
        GLES20.glFinish();
    }

    private void initMvpMatrix(int rotation, float videoAspectRatio) {
        float upX;
        float upY;
        float[] projectionMatrix = new float[16];
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.orthoM(projectionMatrix, 0, -videoAspectRatio, videoAspectRatio, -1.0f, 1.0f, -1.0f, 1.0f);
        float[] viewMatrix = new float[16];
        Matrix.setIdentityM(viewMatrix, 0);
        switch (rotation) {
            case 0:
                upX = 0.0f;
                upY = 1.0f;
                break;
            case RotationOptions.ROTATE_90 /* 90 */:
                upX = 1.0f;
                upY = 0.0f;
                break;
            case RotationOptions.ROTATE_180 /* 180 */:
                upX = 0.0f;
                upY = -1.0f;
                break;
            case RotationOptions.ROTATE_270 /* 270 */:
                upX = -1.0f;
                upY = 0.0f;
                break;
            default:
                float upX2 = (float) Math.sin(((double) rotation) / 3.141592653589793d);
                upX = upX2;
                upY = (float) Math.cos(((double) rotation) / 3.141592653589793d);
                break;
        }
        Matrix.setLookAtM(viewMatrix, 0, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, upX, upY, 0.0f);
        Matrix.setIdentityM(this.mvpMatrix, 0);
        Matrix.multiplyMM(this.mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
    }

    private void initInputSurfaceTexture() {
        if (!this.inputSurfaceTextureInitialized) {
            for (GlFilter filter : this.filters) {
                if (filter instanceof GlFrameRenderFilter) {
                    ((GlFrameRenderFilter) filter).initInputFrameTexture(this.inputSurface.getTextureId(), this.inputSurface.getTransformMatrix());
                }
            }
            this.inputSurfaceTextureInitialized = true;
        }
    }
}
