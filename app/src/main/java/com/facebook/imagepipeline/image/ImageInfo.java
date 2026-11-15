package com.facebook.imagepipeline.image;

/* JADX INFO: loaded from: classes11.dex */
public interface ImageInfo extends HasImageMetadata {
    int getHeight();

    QualityInfo getQualityInfo();

    int getWidth();

    default int getSizeInBytes() {
        return 0;
    }
}
