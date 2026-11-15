package com.facebook.imagepipeline.image;

import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class ImageInfoImpl implements ImageInfo {
    private final Map<String, Object> extras;
    private final int height;
    private final QualityInfo qualityInfo;
    private final int sizeInBytes;
    private final int width;

    public ImageInfoImpl(int width, int height, int sizeInBytes, QualityInfo qualityInfo, Map<String, Object> extras) {
        this.width = width;
        this.height = height;
        this.sizeInBytes = sizeInBytes;
        this.qualityInfo = qualityInfo;
        this.extras = extras;
    }

    @Override // com.facebook.imagepipeline.image.HasImageMetadata, com.facebook.fresco.middleware.HasExtraData
    public Map<String, Object> getExtras() {
        return this.extras;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getWidth() {
        return this.width;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getHeight() {
        return this.height;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getSizeInBytes() {
        return this.sizeInBytes;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return this.qualityInfo;
    }
}
