package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import com.facebook.imagepipeline.nativecode.NativeImageTranscoderFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiImageTranscoderFactory.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/MultiImageTranscoderFactory;", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "maxBitmapSize", "", "useDownSamplingRatio", "", "primaryImageTranscoderFactory", "imageTranscoderType", "ensureTranscoderLibraryLoaded", "(IZLcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;Ljava/lang/Integer;Z)V", "Ljava/lang/Integer;", "createImageTranscoder", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoder;", "imageFormat", "Lcom/facebook/imageformat/ImageFormat;", "isResizingEnabled", "getCustomImageTranscoder", "getImageTranscoderWithType", "getNativeImageTranscoder", "getSimpleImageTranscoder", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MultiImageTranscoderFactory implements ImageTranscoderFactory {
    private final boolean ensureTranscoderLibraryLoaded;
    private final Integer imageTranscoderType;
    private final int maxBitmapSize;
    private final ImageTranscoderFactory primaryImageTranscoderFactory;
    private final boolean useDownSamplingRatio;

    public MultiImageTranscoderFactory(int maxBitmapSize, boolean useDownSamplingRatio, ImageTranscoderFactory primaryImageTranscoderFactory, Integer imageTranscoderType, boolean ensureTranscoderLibraryLoaded) {
        this.maxBitmapSize = maxBitmapSize;
        this.useDownSamplingRatio = useDownSamplingRatio;
        this.primaryImageTranscoderFactory = primaryImageTranscoderFactory;
        this.imageTranscoderType = imageTranscoderType;
        this.ensureTranscoderLibraryLoaded = ensureTranscoderLibraryLoaded;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean isResizingEnabled) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        ImageTranscoder imageTranscoder = getCustomImageTranscoder(imageFormat, isResizingEnabled);
        if (imageTranscoder == null) {
            imageTranscoder = getImageTranscoderWithType(imageFormat, isResizingEnabled);
        }
        if (imageTranscoder == null && NativeCodeSetup.getUseNativeCode()) {
            imageTranscoder = getNativeImageTranscoder(imageFormat, isResizingEnabled);
        }
        return imageTranscoder == null ? getSimpleImageTranscoder(imageFormat, isResizingEnabled) : imageTranscoder;
    }

    private final ImageTranscoder getCustomImageTranscoder(ImageFormat imageFormat, boolean isResizingEnabled) {
        ImageTranscoderFactory imageTranscoderFactory = this.primaryImageTranscoderFactory;
        if (imageTranscoderFactory != null) {
            return imageTranscoderFactory.createImageTranscoder(imageFormat, isResizingEnabled);
        }
        return null;
    }

    private final ImageTranscoder getNativeImageTranscoder(ImageFormat imageFormat, boolean isResizingEnabled) {
        return NativeImageTranscoderFactory.getNativeImageTranscoderFactory(this.maxBitmapSize, this.useDownSamplingRatio, this.ensureTranscoderLibraryLoaded).createImageTranscoder(imageFormat, isResizingEnabled);
    }

    private final ImageTranscoder getSimpleImageTranscoder(ImageFormat imageFormat, boolean isResizingEnabled) {
        ImageTranscoder imageTranscoderCreateImageTranscoder = new SimpleImageTranscoderFactory(this.maxBitmapSize).createImageTranscoder(imageFormat, isResizingEnabled);
        Intrinsics.checkNotNullExpressionValue(imageTranscoderCreateImageTranscoder, "SimpleImageTranscoderFac…ormat, isResizingEnabled)");
        return imageTranscoderCreateImageTranscoder;
    }

    private final ImageTranscoder getImageTranscoderWithType(ImageFormat imageFormat, boolean isResizingEnabled) {
        if (this.imageTranscoderType == null) {
            return null;
        }
        switch (this.imageTranscoderType.intValue()) {
            case 0:
                return getNativeImageTranscoder(imageFormat, isResizingEnabled);
            case 1:
                return getSimpleImageTranscoder(imageFormat, isResizingEnabled);
            default:
                throw new IllegalArgumentException("Invalid ImageTranscoderType");
        }
    }
}
