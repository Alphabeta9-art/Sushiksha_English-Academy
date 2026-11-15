package com.linkedin.android.litr.preview;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import androidx.work.Data;
import com.linkedin.android.litr.filter.GlFrameRenderFilter;
import com.linkedin.android.litr.filter.video.gl.DefaultVideoFrameRenderFilter;
import java.util.Arrays;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes13.dex */
public class VideoPreviewRenderer implements GLSurfaceView.Renderer {
    private static final int GL_TEXTURE_EXTERNAL_OES = 36197;
    private static final String TAG = VideoPreviewRenderer.class.getSimpleName();
    private final InputSurfaceTextureListener inputSurfaceTextureListener;
    private PreviewRenderListener previewRenderListener;
    private SurfaceTexture previewSurfaceTexture;
    private int textureHandle;
    private float[] stMatrix = new float[16];
    private float[] mvpMatrix = new float[16];
    private SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener = new SurfaceTexture.OnFrameAvailableListener() { // from class: com.linkedin.android.litr.preview.VideoPreviewRenderer.1
        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            VideoPreviewRenderer.this.previewRenderListener.onRenderRequested();
        }
    };
    private GlFrameRenderFilter frameRenderFilter = new DefaultVideoFrameRenderFilter();

    public interface InputSurfaceTextureListener {
        void onSurfaceTextureCreated(SurfaceTexture surfaceTexture);
    }

    interface PreviewRenderListener {
        void onEventQueued(Runnable runnable);

        void onRenderRequested();
    }

    public VideoPreviewRenderer(InputSurfaceTextureListener inputSurfaceTextureListener) {
        this.inputSurfaceTextureListener = inputSurfaceTextureListener;
        Matrix.setIdentityM(this.stMatrix, 0);
    }

    public void setFilter(final GlFrameRenderFilter frameRenderFilter) {
        if (this.frameRenderFilter != frameRenderFilter && this.previewRenderListener != null) {
            this.previewRenderListener.onEventQueued(new Runnable() { // from class: com.linkedin.android.litr.preview.VideoPreviewRenderer.2
                @Override // java.lang.Runnable
                public void run() {
                    VideoPreviewRenderer.this.frameRenderFilter.release();
                    if (VideoPreviewRenderer.this.previewSurfaceTexture != null) {
                        frameRenderFilter.init();
                        frameRenderFilter.setVpMatrix(Arrays.copyOf(VideoPreviewRenderer.this.mvpMatrix, VideoPreviewRenderer.this.mvpMatrix.length), 0);
                    }
                    VideoPreviewRenderer.this.frameRenderFilter = frameRenderFilter;
                }
            });
        }
    }

    public void setPreviewRenderListener(PreviewRenderListener previewRenderListener) {
        this.previewRenderListener = previewRenderListener;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int[] args = new int[1];
        GLES20.glGenTextures(args.length, args, 0);
        this.textureHandle = args[0];
        this.previewSurfaceTexture = new SurfaceTexture(this.textureHandle);
        this.previewSurfaceTexture.setOnFrameAvailableListener(this.onFrameAvailableListener);
        GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, this.textureHandle);
        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, Data.MAX_DATA_BYTES, 9729.0f);
        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10241, 9728.0f);
        GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10242, 33071);
        GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10243, 33071);
        GLES20.glBindTexture(3553, 0);
        this.inputSurfaceTextureListener.onSurfaceTextureCreated(this.previewSurfaceTexture);
        this.frameRenderFilter.init();
        GLES20.glGetIntegerv(3379, args, 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        initMvpMatrix(width / height);
        this.frameRenderFilter.setVpMatrix(Arrays.copyOf(this.mvpMatrix, this.mvpMatrix.length), 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl) {
        synchronized (this) {
            this.previewSurfaceTexture.updateTexImage();
            this.previewSurfaceTexture.getTransformMatrix(this.stMatrix);
        }
        GLES20.glClear(16384);
        this.frameRenderFilter.initInputFrameTexture(this.textureHandle, this.stMatrix);
        this.frameRenderFilter.apply(this.previewSurfaceTexture.getTimestamp());
    }

    public void release() {
        this.frameRenderFilter.release();
        if (this.previewSurfaceTexture != null) {
            this.previewSurfaceTexture.release();
        }
    }

    private void initMvpMatrix(float videoAspectRatio) {
        float[] projectionMatrix = new float[16];
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.orthoM(projectionMatrix, 0, -videoAspectRatio, videoAspectRatio, -1.0f, 1.0f, -1.0f, 1.0f);
        float[] viewMatrix = new float[16];
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setLookAtM(viewMatrix, 0, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        Matrix.setIdentityM(this.mvpMatrix, 0);
        Matrix.multiplyMM(this.mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
    }
}
