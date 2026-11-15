package com.linkedin.android.litr.preview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes13.dex */
public class VideoFilterPreviewView extends GLSurfaceView {
    public VideoFilterPreviewView(Context context) {
        this(context, null);
    }

    public VideoFilterPreviewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextFactory(new PreviewEglContextFactory());
        setEGLConfigChooser(new PreviewEglConfigChooser());
    }

    @Override // android.opengl.GLSurfaceView
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        super.setRenderer(renderer);
        if (renderer instanceof VideoPreviewRenderer) {
            ((VideoPreviewRenderer) renderer).setPreviewRenderListener(new VideoPreviewRenderer.PreviewRenderListener() { // from class: com.linkedin.android.litr.preview.VideoFilterPreviewView.1
                @Override // com.linkedin.android.litr.preview.VideoPreviewRenderer.PreviewRenderListener
                public void onRenderRequested() {
                    VideoFilterPreviewView.this.requestRender();
                }

                @Override // com.linkedin.android.litr.preview.VideoPreviewRenderer.PreviewRenderListener
                public void onEventQueued(Runnable runnable) {
                    VideoFilterPreviewView.this.queueEvent(runnable);
                }
            });
        }
    }
}
