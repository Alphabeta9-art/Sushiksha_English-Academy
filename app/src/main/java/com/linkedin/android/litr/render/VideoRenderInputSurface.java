package com.linkedin.android.litr.render;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.view.Surface;
import androidx.work.Data;

/* JADX INFO: loaded from: classes13.dex */
class VideoRenderInputSurface implements SurfaceTexture.OnFrameAvailableListener {
    private static final int FRAME_WAIT_TIMEOUT_MS = 10000;
    private boolean frameAvailable;
    private final Object frameSyncObject = new Object();
    private int textureId = createTexture();
    private SurfaceTexture surfaceTexture = new SurfaceTexture(this.textureId);
    private Surface surface = new Surface(this.surfaceTexture);

    VideoRenderInputSurface() {
        this.surfaceTexture.setOnFrameAvailableListener(this);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this.frameSyncObject) {
            if (this.frameAvailable) {
                throw new RuntimeException("frameAvailable already set, frame could be dropped");
            }
            this.frameAvailable = true;
            this.frameSyncObject.notifyAll();
        }
    }

    Surface getSurface() {
        return this.surface;
    }

    int getTextureId() {
        return this.textureId;
    }

    float[] getTransformMatrix() {
        float[] transformMatrix = new float[16];
        this.surfaceTexture.getTransformMatrix(transformMatrix);
        return transformMatrix;
    }

    void awaitNewImage() {
        synchronized (this.frameSyncObject) {
            while (!this.frameAvailable) {
                try {
                    this.frameSyncObject.wait(10000L);
                    if (!this.frameAvailable) {
                        throw new RuntimeException("Surface frame wait timed out");
                    }
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                }
            }
            this.frameAvailable = false;
        }
        GlRenderUtils.checkGlError("before updateTexImage");
        this.surfaceTexture.updateTexImage();
    }

    void release() {
        if (this.surface != null) {
            this.surface.release();
            this.surface = null;
        }
    }

    private int createTexture() {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        int textureID = textures[0];
        GLES20.glBindTexture(36197, textureID);
        GlRenderUtils.checkGlError("glBindTexture textureID");
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, Data.MAX_DATA_BYTES, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GlRenderUtils.checkGlError("glTexParameter");
        return textureID;
    }
}
