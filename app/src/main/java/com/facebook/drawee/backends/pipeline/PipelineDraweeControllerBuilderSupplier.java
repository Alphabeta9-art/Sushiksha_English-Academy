package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder> {
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Set<ControllerListener2> mBoundControllerListeners2;
    private final Context mContext;

    @Nullable
    private final ImagePerfDataListener mDefaultImagePerfDataListener;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context) {
        this(context, null);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, @Nullable DraweeConfig draweeConfig) {
        this(context, ImagePipelineFactory.getInstance(), draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, @Nullable DraweeConfig draweeConfig) {
        this(context, imagePipelineFactory, null, null, draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, Set<ControllerListener> boundControllerListeners, Set<ControllerListener2> boundControllerListeners2, @Nullable DraweeConfig draweeConfig) {
        this.mContext = context;
        this.mImagePipeline = imagePipelineFactory.getImagePipeline();
        if (draweeConfig != null && draweeConfig.getPipelineDraweeControllerFactory() != null) {
            this.mPipelineDraweeControllerFactory = draweeConfig.getPipelineDraweeControllerFactory();
        } else {
            this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
        }
        this.mPipelineDraweeControllerFactory.init(context.getResources(), DeferredReleaser.getInstance(), imagePipelineFactory.getAnimatedDrawableFactory(context), UiThreadImmediateExecutorService.getInstance(), this.mImagePipeline.getBitmapMemoryCache(), draweeConfig != null ? draweeConfig.getCustomDrawableFactories() : null, draweeConfig != null ? draweeConfig.getDebugOverlayEnabledSupplier() : null);
        this.mBoundControllerListeners = boundControllerListeners;
        this.mBoundControllerListeners2 = boundControllerListeners2;
        this.mDefaultImagePerfDataListener = draweeConfig != null ? draweeConfig.getImagePerfDataListener() : null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.internal.Supplier
    public PipelineDraweeControllerBuilder get() {
        PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners, this.mBoundControllerListeners2);
        return pipelineDraweeControllerBuilder.setPerfDataListener(this.mDefaultImagePerfDataListener);
    }
}
