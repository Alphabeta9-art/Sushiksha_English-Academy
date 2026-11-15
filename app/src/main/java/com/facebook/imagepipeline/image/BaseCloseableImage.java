package com.facebook.imagepipeline.image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class BaseCloseableImage implements CloseableImage {
    private static final Set<String> mImageExtrasList = new HashSet(Arrays.asList("encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config", "is_rounded", "non_fatal_decode_error", "modified_url", "image_color_space"));

    @Nullable
    private ImageInfo mCacheImageInfo;
    private Map<String, Object> mExtras = new HashMap();

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public boolean isStateful() {
        return false;
    }

    @Override // com.facebook.imagepipeline.image.HasImageMetadata, com.facebook.fresco.middleware.HasExtraData
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    @Override // com.facebook.fresco.middleware.HasExtraData
    public void putExtras(@Nullable Map<String, ?> extras) {
        if (extras == null) {
            return;
        }
        for (String extra : mImageExtrasList) {
            Object val = extras.get(extra);
            if (val != null) {
                this.mExtras.put(extra, val);
            }
        }
    }

    @Override // com.facebook.fresco.middleware.HasExtraData
    public <E> void putExtra(String extra, @Nullable E value) {
        if (mImageExtrasList.contains(extra)) {
            this.mExtras.put(extra, value);
        }
    }

    @Override // com.facebook.fresco.middleware.HasExtraData
    public <T> T getExtra(String str) {
        return (T) getExtra(str, null);
    }

    @Override // com.facebook.fresco.middleware.HasExtraData
    public <T> T getExtra(String str, @Nullable T t) {
        T t2 = (T) this.mExtras.get(str);
        if (t2 == null) {
            return t;
        }
        return t2;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public ImageInfo getImageInfo() {
        if (this.mCacheImageInfo == null) {
            this.mCacheImageInfo = new ImageInfoImpl(getWidth(), getHeight(), getSizeInBytes(), getQualityInfo(), getExtras());
        }
        return this.mCacheImageInfo;
    }
}
