package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DefaultCacheKeyFactory implements CacheKeyFactory {

    @Nullable
    private static DefaultCacheKeyFactory sInstance = null;

    protected DefaultCacheKeyFactory() {
    }

    public static synchronized DefaultCacheKeyFactory getInstance() {
        if (sInstance == null) {
            sInstance = new DefaultCacheKeyFactory();
        }
        return sInstance;
    }

    @Override // com.facebook.imagepipeline.cache.CacheKeyFactory
    public CacheKey getBitmapCacheKey(ImageRequest request, @Nullable Object callerContext) {
        BitmapMemoryCacheKey cacheKey = new BitmapMemoryCacheKey(getCacheKeySourceUri(request.getSourceUri()).toString(), request.getResizeOptions(), request.getRotationOptions(), request.getImageDecodeOptions(), null, null);
        cacheKey.setCallerContext(callerContext);
        return cacheKey;
    }

    @Override // com.facebook.imagepipeline.cache.CacheKeyFactory
    public CacheKey getPostprocessedBitmapCacheKey(ImageRequest request, @Nullable Object callerContext) {
        CacheKey postprocessorCacheKey;
        String postprocessorName;
        Postprocessor postprocessor = request.getPostprocessor();
        if (postprocessor != null) {
            CacheKey postprocessorCacheKey2 = postprocessor.getCacheKey();
            postprocessorCacheKey = postprocessorCacheKey2;
            postprocessorName = postprocessor.getClass().getName();
        } else {
            postprocessorCacheKey = null;
            postprocessorName = null;
        }
        BitmapMemoryCacheKey cacheKey = new BitmapMemoryCacheKey(getCacheKeySourceUri(request.getSourceUri()).toString(), request.getResizeOptions(), request.getRotationOptions(), request.getImageDecodeOptions(), postprocessorCacheKey, postprocessorName);
        cacheKey.setCallerContext(callerContext);
        return cacheKey;
    }

    @Override // com.facebook.imagepipeline.cache.CacheKeyFactory
    public CacheKey getEncodedCacheKey(ImageRequest request, @Nullable Object callerContext) {
        return getEncodedCacheKey(request, request.getSourceUri(), callerContext);
    }

    @Override // com.facebook.imagepipeline.cache.CacheKeyFactory
    public CacheKey getEncodedCacheKey(ImageRequest request, Uri sourceUri, @Nullable Object callerContext) {
        return new SimpleCacheKey(getCacheKeySourceUri(sourceUri).toString());
    }

    protected Uri getCacheKeySourceUri(Uri sourceUri) {
        return sourceUri;
    }
}
