package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfControllerListener2;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfData;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePerfMonitor implements ImagePerfNotifier {
    private final Supplier<Boolean> mAsyncLogging;
    private boolean mEnabled;

    @Nullable
    private ForwardingRequestListener mForwardingRequestListener;

    @Nullable
    private ImagePerfControllerListener2 mImagePerfControllerListener2;

    @Nullable
    private List<ImagePerfDataListener> mImagePerfDataListeners;

    @Nullable
    private ImagePerfRequestListener mImagePerfRequestListener;
    private final ImagePerfState mImagePerfState = new ImagePerfState();
    private final MonotonicClock mMonotonicClock;
    private final PipelineDraweeController mPipelineDraweeController;

    public ImagePerfMonitor(MonotonicClock monotonicClock, PipelineDraweeController pipelineDraweeController, Supplier<Boolean> asyncLogging) {
        this.mMonotonicClock = monotonicClock;
        this.mPipelineDraweeController = pipelineDraweeController;
        this.mAsyncLogging = asyncLogging;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
        if (enabled) {
            setupListeners();
            if (this.mImagePerfControllerListener2 != null) {
                this.mPipelineDraweeController.addControllerListener2(this.mImagePerfControllerListener2);
            }
            if (this.mForwardingRequestListener != null) {
                this.mPipelineDraweeController.addRequestListener(this.mForwardingRequestListener);
                return;
            }
            return;
        }
        if (this.mImagePerfControllerListener2 != null) {
            this.mPipelineDraweeController.removeControllerListener2(this.mImagePerfControllerListener2);
        }
        if (this.mForwardingRequestListener != null) {
            this.mPipelineDraweeController.removeRequestListener(this.mForwardingRequestListener);
        }
    }

    public void addImagePerfDataListener(@Nullable ImagePerfDataListener imagePerfDataListener) {
        if (imagePerfDataListener == null) {
            return;
        }
        if (this.mImagePerfDataListeners == null) {
            this.mImagePerfDataListeners = new CopyOnWriteArrayList();
        }
        this.mImagePerfDataListeners.add(imagePerfDataListener);
    }

    public void removeImagePerfDataListener(ImagePerfDataListener imagePerfDataListener) {
        if (this.mImagePerfDataListeners == null) {
            return;
        }
        this.mImagePerfDataListeners.remove(imagePerfDataListener);
    }

    public void clearImagePerfDataListeners() {
        if (this.mImagePerfDataListeners != null) {
            this.mImagePerfDataListeners.clear();
        }
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifier
    public void notifyStatusUpdated(ImagePerfState state, ImageLoadStatus imageLoadStatus) {
        state.setImageLoadStatus(imageLoadStatus);
        if (!this.mEnabled || this.mImagePerfDataListeners == null || this.mImagePerfDataListeners.isEmpty()) {
            return;
        }
        if (imageLoadStatus == ImageLoadStatus.SUCCESS) {
            addViewportData();
        }
        ImagePerfData data = state.snapshot();
        for (ImagePerfDataListener listener : this.mImagePerfDataListeners) {
            listener.onImageLoadStatusUpdated(data, imageLoadStatus);
        }
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifier
    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState state, VisibilityState visibilityState) {
        if (!this.mEnabled || this.mImagePerfDataListeners == null || this.mImagePerfDataListeners.isEmpty()) {
            return;
        }
        ImagePerfData data = state.snapshot();
        for (ImagePerfDataListener listener : this.mImagePerfDataListeners) {
            listener.onImageVisibilityUpdated(data, visibilityState);
        }
    }

    public void addViewportData() {
        DraweeHierarchy hierarchy = this.mPipelineDraweeController.getHierarchy();
        if (hierarchy != null && hierarchy.getTopLevelDrawable() != null) {
            Rect bounds = hierarchy.getTopLevelDrawable().getBounds();
            this.mImagePerfState.setOnScreenWidth(bounds.width());
            this.mImagePerfState.setOnScreenHeight(bounds.height());
        }
    }

    private void setupListeners() {
        if (this.mImagePerfControllerListener2 == null) {
            this.mImagePerfControllerListener2 = new ImagePerfControllerListener2(this.mMonotonicClock, this.mImagePerfState, this, this.mAsyncLogging);
        }
        if (this.mImagePerfRequestListener == null) {
            this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
        }
        if (this.mForwardingRequestListener == null) {
            this.mForwardingRequestListener = new ForwardingRequestListener(this.mImagePerfRequestListener);
        }
    }

    public void reset() {
        clearImagePerfDataListeners();
        setEnabled(false);
        this.mImagePerfState.reset();
    }
}
