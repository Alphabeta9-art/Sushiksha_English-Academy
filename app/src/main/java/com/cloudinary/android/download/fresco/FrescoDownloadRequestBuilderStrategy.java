package com.cloudinary.android.download.fresco;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.cloudinary.android.download.DownloadRequestBuilderStrategy;
import com.cloudinary.android.download.DownloadRequestCallback;
import com.cloudinary.android.download.DownloadRequestStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/* JADX INFO: loaded from: classes11.dex */
class FrescoDownloadRequestBuilderStrategy implements DownloadRequestBuilderStrategy {
    private GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder;
    private ImageRequestBuilder imageRequestBuilder;

    FrescoDownloadRequestBuilderStrategy(Context context) {
        this.genericDraweeHierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(context.getResources());
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(String url) {
        this.imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy load(int resourceId) {
        this.imageRequestBuilder = ImageRequestBuilder.newBuilderWithResourceId(resourceId);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy placeholder(int resourceId) {
        this.genericDraweeHierarchyBuilder.setPlaceholderImage(resourceId);
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestBuilderStrategy callback(final DownloadRequestCallback callback) {
        this.imageRequestBuilder.setRequestListener(new BaseRequestListener() { // from class: com.cloudinary.android.download.fresco.FrescoDownloadRequestBuilderStrategy.1
            @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
            public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
                callback.onSuccess();
                super.onRequestSuccess(request, requestId, isPrefetch);
            }

            @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
            public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
                callback.onFailure(throwable);
                super.onRequestFailure(request, requestId, throwable, isPrefetch);
            }
        });
        return this;
    }

    @Override // com.cloudinary.android.download.DownloadRequestBuilderStrategy
    public DownloadRequestStrategy into(ImageView imageView) {
        if (!(imageView instanceof DraweeView)) {
            throw new IllegalArgumentException("ImageView must be an instance of DraweeView.");
        }
        ImageRequest imageRequest = this.imageRequestBuilder.build();
        DraweeHierarchy genericDraweeHierarchy = this.genericDraweeHierarchyBuilder.build();
        DraweeView draweeView = (DraweeView) imageView;
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(imageRequest).build();
        draweeController.setHierarchy(genericDraweeHierarchy);
        draweeView.setController(draweeController);
        return new FrescoDownloadRequestStrategy();
    }
}
