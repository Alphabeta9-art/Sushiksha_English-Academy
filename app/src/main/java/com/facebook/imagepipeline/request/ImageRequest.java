package com.facebook.imagepipeline.request;

import android.net.Uri;
import android.os.Build;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Fn;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.memory.config.MemorySpikeConfig;
import com.facebook.memory.helper.HashCode;
import java.io.File;
import java.util.HashMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImageRequest {
    public static final Fn<ImageRequest, Uri> REQUEST_TO_URI_FN = new Fn<ImageRequest, Uri>() { // from class: com.facebook.imagepipeline.request.ImageRequest.1
        @Override // com.facebook.common.internal.Fn
        @Nullable
        public Uri apply(@Nullable ImageRequest arg) {
            if (arg != null) {
                return arg.getSourceUri();
            }
            return null;
        }
    };
    private static boolean sCacheHashcode;
    private static boolean sUseCachedHashcodeInEquals;

    @Nullable
    private final BytesRange mBytesRange;
    private final CacheChoice mCacheChoice;
    protected int mCachesDisabled;

    @Nullable
    private final Boolean mDecodePrefetches;
    private final int mDelayMs;

    @Nullable
    private final String mDiskCacheId;
    private int mHashcode;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final boolean mIsDiskCacheEnabled;
    private final boolean mIsMemoryCacheEnabled;
    private final boolean mLoadThumbnailOnly;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final RequestLevel mLowestPermittedRequestLevel;

    @Nullable
    private final Postprocessor mPostprocessor;
    private final boolean mProgressiveRenderingEnabled;

    @Nullable
    private final RequestListener mRequestListener;
    private final Priority mRequestPriority;

    @Nullable
    private final ResizeOptions mResizeOptions;

    @Nullable
    private final Boolean mResizingAllowedOverride;
    private final RotationOptions mRotationOptions;

    @Nullable
    private File mSourceFile;
    private final Uri mSourceUri;
    private final int mSourceUriType;

    public enum CacheChoice {
        SMALL,
        DEFAULT,
        DYNAMIC
    }

    public @interface CachesLocationsMasks {
        public static final int BITMAP_READ = 1;
        public static final int BITMAP_WRITE = 2;
        public static final int DISK_READ = 16;
        public static final int DISK_WRITE = 32;
        public static final int ENCODED_READ = 4;
        public static final int ENCODED_WRITE = 8;
    }

    @Nullable
    public static ImageRequest fromFile(@Nullable File file) {
        if (file == null) {
            return null;
        }
        return fromUri(UriUtil.getUriForFile(file));
    }

    @Nullable
    public static ImageRequest fromUri(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        return ImageRequestBuilder.newBuilderWithSource(uri).build();
    }

    @Nullable
    public static ImageRequest fromUri(@Nullable String uriString) {
        if (uriString == null || uriString.length() == 0) {
            return null;
        }
        return fromUri(Uri.parse(uriString));
    }

    protected ImageRequest(ImageRequestBuilder builder) {
        RotationOptions rotationOptions;
        this.mCacheChoice = builder.getCacheChoice();
        this.mSourceUri = builder.getSourceUri();
        this.mSourceUriType = getSourceUriType(this.mSourceUri);
        this.mProgressiveRenderingEnabled = builder.isProgressiveRenderingEnabled();
        this.mLocalThumbnailPreviewsEnabled = builder.isLocalThumbnailPreviewsEnabled();
        this.mLoadThumbnailOnly = builder.getLoadThumbnailOnly();
        this.mImageDecodeOptions = builder.getImageDecodeOptions();
        this.mResizeOptions = builder.getResizeOptions();
        if (builder.getRotationOptions() == null) {
            rotationOptions = RotationOptions.autoRotate();
        } else {
            rotationOptions = builder.getRotationOptions();
        }
        this.mRotationOptions = rotationOptions;
        this.mBytesRange = builder.getBytesRange();
        this.mRequestPriority = builder.getRequestPriority();
        this.mLowestPermittedRequestLevel = builder.getLowestPermittedRequestLevel();
        this.mIsDiskCacheEnabled = builder.isDiskCacheEnabled();
        int cachesDisabledFlags = builder.getCachesDisabled();
        this.mCachesDisabled = this.mIsDiskCacheEnabled ? cachesDisabledFlags : cachesDisabledFlags | 48;
        this.mIsMemoryCacheEnabled = builder.isMemoryCacheEnabled();
        this.mDecodePrefetches = builder.shouldDecodePrefetches();
        this.mPostprocessor = builder.getPostprocessor();
        this.mRequestListener = builder.getRequestListener();
        this.mResizingAllowedOverride = builder.getResizingAllowedOverride();
        this.mDelayMs = builder.getDelayMs();
        this.mDiskCacheId = builder.getDiskCacheId();
    }

    public CacheChoice getCacheChoice() {
        return this.mCacheChoice;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public int getSourceUriType() {
        return this.mSourceUriType;
    }

    public int getPreferredWidth() {
        if (this.mResizeOptions != null) {
            return this.mResizeOptions.width;
        }
        return 2048;
    }

    public int getPreferredHeight() {
        if (this.mResizeOptions != null) {
            return this.mResizeOptions.height;
        }
        return 2048;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public RotationOptions getRotationOptions() {
        return this.mRotationOptions;
    }

    @Deprecated
    public boolean getAutoRotateEnabled() {
        return this.mRotationOptions.useImageMetadata();
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public boolean getProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public boolean getLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean getLoadThumbnailOnlyForAndroidSdkAboveQ() {
        return Build.VERSION.SDK_INT >= 29 && this.mLoadThumbnailOnly;
    }

    public Priority getPriority() {
        return this.mRequestPriority;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    public int getCachesDisabled() {
        return this.mCachesDisabled;
    }

    public boolean isDiskCacheEnabled() {
        return this.mIsDiskCacheEnabled;
    }

    public boolean isCacheEnabled(int cacheMask) {
        return (getCachesDisabled() & cacheMask) == 0;
    }

    public boolean isMemoryCacheEnabled() {
        return this.mIsMemoryCacheEnabled;
    }

    @Nullable
    public Boolean shouldDecodePrefetches() {
        return this.mDecodePrefetches;
    }

    @Nullable
    public Boolean getResizingAllowedOverride() {
        return this.mResizingAllowedOverride;
    }

    public int getDelayMs() {
        return this.mDelayMs;
    }

    public synchronized File getSourceFile() {
        if (this.mSourceFile == null) {
            Preconditions.checkNotNull(this.mSourceUri.getPath());
            this.mSourceFile = new File(this.mSourceUri.getPath());
        }
        return this.mSourceFile;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    @Nullable
    public RequestListener getRequestListener() {
        return this.mRequestListener;
    }

    @Nullable
    public String getDiskCacheId() {
        return this.mDiskCacheId;
    }

    public boolean equals(@Nullable Object o) {
        if (!(o instanceof ImageRequest)) {
            return false;
        }
        ImageRequest request = (ImageRequest) o;
        if (sUseCachedHashcodeInEquals) {
            int a = this.mHashcode;
            int b = request.mHashcode;
            if (a != 0 && b != 0 && a != b) {
                return false;
            }
        }
        if (this.mLocalThumbnailPreviewsEnabled != request.mLocalThumbnailPreviewsEnabled || this.mIsDiskCacheEnabled != request.mIsDiskCacheEnabled || this.mIsMemoryCacheEnabled != request.mIsMemoryCacheEnabled || !Objects.equal(this.mSourceUri, request.mSourceUri) || !Objects.equal(this.mCacheChoice, request.mCacheChoice) || !Objects.equal(this.mDiskCacheId, request.mDiskCacheId) || !Objects.equal(this.mSourceFile, request.mSourceFile) || !Objects.equal(this.mBytesRange, request.mBytesRange) || !Objects.equal(this.mImageDecodeOptions, request.mImageDecodeOptions) || !Objects.equal(this.mResizeOptions, request.mResizeOptions) || !Objects.equal(this.mRequestPriority, request.mRequestPriority) || !Objects.equal(this.mLowestPermittedRequestLevel, request.mLowestPermittedRequestLevel) || !Objects.equal(Integer.valueOf(this.mCachesDisabled), Integer.valueOf(request.mCachesDisabled)) || !Objects.equal(this.mDecodePrefetches, request.mDecodePrefetches) || !Objects.equal(this.mResizingAllowedOverride, request.mResizingAllowedOverride) || !Objects.equal(this.mRotationOptions, request.mRotationOptions) || this.mLoadThumbnailOnly != request.mLoadThumbnailOnly) {
            return false;
        }
        CacheKey thisPostprocessorKey = this.mPostprocessor != null ? this.mPostprocessor.getPostprocessorCacheKey() : null;
        CacheKey thatPostprocessorKey = request.mPostprocessor != null ? request.mPostprocessor.getPostprocessorCacheKey() : null;
        return Objects.equal(thisPostprocessorKey, thatPostprocessorKey) && this.mDelayMs == request.mDelayMs;
    }

    public int hashCode() {
        boolean cacheHashcode = sCacheHashcode;
        int result = 0;
        if (cacheHashcode) {
            result = this.mHashcode;
        }
        if (result == 0) {
            CacheKey postprocessorCacheKey = this.mPostprocessor != null ? this.mPostprocessor.getPostprocessorCacheKey() : null;
            if (MemorySpikeConfig.avoidObjectsHashCode()) {
                int result2 = HashCode.extend(0, this.mCacheChoice);
                result = HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(result2, this.mSourceUri), Boolean.valueOf(this.mLocalThumbnailPreviewsEnabled)), this.mBytesRange), this.mRequestPriority), this.mLowestPermittedRequestLevel), Integer.valueOf(this.mCachesDisabled)), Boolean.valueOf(this.mIsDiskCacheEnabled)), Boolean.valueOf(this.mIsMemoryCacheEnabled)), this.mImageDecodeOptions), this.mDecodePrefetches), this.mResizeOptions), this.mRotationOptions), postprocessorCacheKey), this.mResizingAllowedOverride), Integer.valueOf(this.mDelayMs)), Boolean.valueOf(this.mLoadThumbnailOnly));
            } else {
                result = Objects.hashCode(this.mCacheChoice, this.mDiskCacheId, this.mSourceUri, Boolean.valueOf(this.mLocalThumbnailPreviewsEnabled), this.mBytesRange, this.mRequestPriority, this.mLowestPermittedRequestLevel, Integer.valueOf(this.mCachesDisabled), Boolean.valueOf(this.mIsDiskCacheEnabled), Boolean.valueOf(this.mIsMemoryCacheEnabled), this.mImageDecodeOptions, this.mDecodePrefetches, this.mResizeOptions, this.mRotationOptions, postprocessorCacheKey, this.mResizingAllowedOverride, Integer.valueOf(this.mDelayMs), Boolean.valueOf(this.mLoadThumbnailOnly));
            }
            if (cacheHashcode) {
                this.mHashcode = result;
            }
        }
        return result;
    }

    public void recordHashCode(HashMap<String, Integer> hashCodeLog) {
        CacheKey postprocessorCacheKey = this.mPostprocessor != null ? this.mPostprocessor.getPostprocessorCacheKey() : null;
        hashCodeLog.put("ImageRequest", Integer.valueOf(hashCode()));
        hashCodeLog.put("ImageRequest.mSourceUri", Integer.valueOf(getHashCodeHelper(this.mSourceUri)));
        hashCodeLog.put("ImageRequest.mLocalThumbnailPreviewsEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mLocalThumbnailPreviewsEnabled))));
        hashCodeLog.put("ImageRequest.mBytesRange", Integer.valueOf(getHashCodeHelper(this.mBytesRange)));
        hashCodeLog.put("ImageRequest.mRequestPriority", Integer.valueOf(getHashCodeHelper(this.mRequestPriority)));
        hashCodeLog.put("ImageRequest.mLowestPermittedRequestLevel", Integer.valueOf(getHashCodeHelper(this.mLowestPermittedRequestLevel)));
        hashCodeLog.put("ImageRequest.mCachesDisabled", Integer.valueOf(getHashCodeHelper(Integer.valueOf(this.mCachesDisabled))));
        hashCodeLog.put("ImageRequest.mIsDiskCacheEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mIsDiskCacheEnabled))));
        hashCodeLog.put("ImageRequest.mIsMemoryCacheEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mIsMemoryCacheEnabled))));
        hashCodeLog.put("ImageRequest.mImageDecodeOptions", Integer.valueOf(getHashCodeHelper(this.mImageDecodeOptions)));
        hashCodeLog.put("ImageRequest.mDecodePrefetches", Integer.valueOf(getHashCodeHelper(this.mDecodePrefetches)));
        hashCodeLog.put("ImageRequest.mSoumResizeOptionsrceUri", Integer.valueOf(getHashCodeHelper(this.mResizeOptions)));
        hashCodeLog.put("ImageRequest.mRotationOptions", Integer.valueOf(getHashCodeHelper(this.mRotationOptions)));
        hashCodeLog.put("ImageRequest.postprocessorCacheKey", Integer.valueOf(getHashCodeHelper(postprocessorCacheKey)));
        hashCodeLog.put("ImageRequest.mResizingAllowedOverride", Integer.valueOf(getHashCodeHelper(this.mResizingAllowedOverride)));
        hashCodeLog.put("ImageRequest.mDelayMs", Integer.valueOf(getHashCodeHelper(Integer.valueOf(this.mDelayMs))));
        hashCodeLog.put("ImageRequest.mLoadThumbnailOnly", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mLoadThumbnailOnly))));
    }

    private static int getHashCodeHelper(@Nullable Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("uri", this.mSourceUri).add("cacheChoice", this.mCacheChoice).add("decodeOptions", this.mImageDecodeOptions).add("postprocessor", this.mPostprocessor).add("priority", this.mRequestPriority).add("resizeOptions", this.mResizeOptions).add("rotationOptions", this.mRotationOptions).add("bytesRange", this.mBytesRange).add("resizingAllowedOverride", this.mResizingAllowedOverride).add("progressiveRenderingEnabled", this.mProgressiveRenderingEnabled).add("localThumbnailPreviewsEnabled", this.mLocalThumbnailPreviewsEnabled).add("loadThumbnailOnly", this.mLoadThumbnailOnly).add("lowestPermittedRequestLevel", this.mLowestPermittedRequestLevel).add("cachesDisabled", this.mCachesDisabled).add("isDiskCacheEnabled", this.mIsDiskCacheEnabled).add("isMemoryCacheEnabled", this.mIsMemoryCacheEnabled).add("decodePrefetches", this.mDecodePrefetches).add("delayMs", this.mDelayMs).toString();
    }

    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);

        private int mValue;

        RequestLevel(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }

        public static RequestLevel getMax(RequestLevel requestLevel1, RequestLevel requestLevel2) {
            return requestLevel1.getValue() > requestLevel2.getValue() ? requestLevel1 : requestLevel2;
        }
    }

    private static int getSourceUriType(@Nullable Uri uri) {
        if (uri == null) {
            return -1;
        }
        if (UriUtil.isNetworkUri(uri)) {
            return 0;
        }
        if (uri.getPath() != null && UriUtil.isLocalFileUri(uri)) {
            if (MediaUtils.isVideo(MediaUtils.extractMime(uri.getPath()))) {
                return 2;
            }
            return 3;
        }
        if (UriUtil.isLocalContentUri(uri)) {
            return 4;
        }
        if (UriUtil.isLocalAssetUri(uri)) {
            return 5;
        }
        if (UriUtil.isLocalResourceUri(uri)) {
            return 6;
        }
        if (UriUtil.isDataUri(uri)) {
            return 7;
        }
        if (!UriUtil.isQualifiedResourceUri(uri)) {
            return -1;
        }
        return 8;
    }

    public static void setUseCachedHashcodeInEquals(boolean useCachedHashcodeInEquals) {
        sUseCachedHashcodeInEquals = useCachedHashcodeInEquals;
    }

    public static void setCacheHashcode(boolean cacheHashcode) {
        sCacheHashcode = cacheHashcode;
    }
}
