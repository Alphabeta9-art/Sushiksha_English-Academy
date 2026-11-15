package com.facebook.imagepipeline.core;

import android.content.Context;
import android.os.Build;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.AndroidPredicates;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactoryProvider;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCache;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCacheBitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoderFactory;
import com.facebook.imagepipeline.producers.ExperimentalThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.MultiImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.SimpleImageTranscoderFactory;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePipelineFactory {
    private static boolean sForceSingleInstance;
    private static ImagePipeline sImagePipeline;

    @Nullable
    private AnimatedFactory mAnimatedFactory;

    @Nullable
    private CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache;

    @Nullable
    private InstrumentedMemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private final ImagePipelineConfigInterface mConfig;

    @Nullable
    private ImmutableMap<String, BufferedDiskCache> mDynamicBufferedDiskCaches;

    @Nullable
    private Map<String, FileCache> mDynamicFileCaches;

    @Nullable
    private CountingMemoryCache<CacheKey, PooledByteBuffer> mEncodedCountingMemoryCache;

    @Nullable
    private InstrumentedMemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;

    @Nullable
    private ImageDecoder mImageDecoder;

    @Nullable
    private ImageTranscoderFactory mImageTranscoderFactory;

    @Nullable
    private BufferedDiskCache mMainBufferedDiskCache;

    @Nullable
    private FileCache mMainFileCache;

    @Nullable
    private PlatformBitmapFactory mPlatformBitmapFactory;

    @Nullable
    private PlatformDecoder mPlatformDecoder;

    @Nullable
    private ProducerFactory mProducerFactory;

    @Nullable
    private ProducerSequenceFactory mProducerSequenceFactory;

    @Nullable
    private BufferedDiskCache mSmallImageBufferedDiskCache;

    @Nullable
    private FileCache mSmallImageFileCache;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    private static final Class<?> TAG = ImagePipelineFactory.class;

    @Nullable
    private static ImagePipelineFactory sInstance = null;

    public static ImagePipelineFactory getInstance() {
        return (ImagePipelineFactory) Preconditions.checkNotNull(sInstance, "ImagePipelineFactory was not initialized!");
    }

    public static void setInstance(ImagePipelineFactory newInstance) {
        sInstance = newInstance;
    }

    public static synchronized void initialize(Context context) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineFactory#initialize");
        }
        initialize(ImagePipelineConfig.newBuilder(context).build());
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public static synchronized void initialize(ImagePipelineConfigInterface imagePipelineConfig) {
        if (sInstance != null) {
            FLog.w(TAG, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
            if (sForceSingleInstance) {
                return;
            }
        }
        sInstance = new ImagePipelineFactory(imagePipelineConfig);
    }

    public static synchronized void forceSingleInstance() {
        sForceSingleInstance = true;
    }

    public static synchronized boolean hasBeenInitialized() {
        return sInstance != null;
    }

    public static synchronized void shutDown() {
        if (sInstance != null) {
            sInstance.getBitmapMemoryCache().removeAll(AndroidPredicates.True());
            sInstance.getEncodedMemoryCache().removeAll(AndroidPredicates.True());
            sInstance = null;
        }
    }

    public ImagePipelineFactory(ImagePipelineConfigInterface config) {
        ThreadHandoffProducerQueue threadHandoffProducerQueueImpl;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig()");
        }
        this.mConfig = (ImagePipelineConfigInterface) Preconditions.checkNotNull(config);
        if (this.mConfig.getExperiments().getIsExperimentalThreadHandoffQueueEnabled()) {
            threadHandoffProducerQueueImpl = new ExperimentalThreadHandoffProducerQueueImpl(config.getExecutorSupplier().getLightWeightBackgroundExecutor());
        } else {
            threadHandoffProducerQueueImpl = new ThreadHandoffProducerQueueImpl(config.getExecutorSupplier().getLightWeightBackgroundExecutor());
        }
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueueImpl;
        this.mCloseableReferenceFactory = new CloseableReferenceFactory(config.getCloseableReferenceLeakTracker());
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Nullable
    private AnimatedFactory getAnimatedFactory() {
        if (this.mAnimatedFactory == null) {
            this.mAnimatedFactory = AnimatedFactoryProvider.getAnimatedFactory(getPlatformBitmapFactory(), this.mConfig.getExecutorSupplier(), getBitmapCountingMemoryCache(), this.mConfig.getExperiments().getDownscaleFrameToDrawableDimensions(), this.mConfig.getExperiments().getUseBalancedAnimationStrategy(), this.mConfig.getExperiments().getAnimationRenderFpsLimit(), this.mConfig.getExecutorServiceForAnimatedImages());
        }
        return this.mAnimatedFactory;
    }

    @Nullable
    public DrawableFactory getAnimatedDrawableFactory(@Nullable Context context) {
        AnimatedFactory animatedFactory = getAnimatedFactory();
        if (animatedFactory == null) {
            return null;
        }
        return animatedFactory.getAnimatedDrawableFactory(context);
    }

    public CountingMemoryCache<CacheKey, CloseableImage> getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            this.mBitmapCountingMemoryCache = this.mConfig.getBitmapMemoryCacheFactory().create(this.mConfig.getBitmapMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapMemoryCacheTrimStrategy(), this.mConfig.getExperiments().getShouldStoreCacheEntrySize(), this.mConfig.getExperiments().getShouldIgnoreCacheSizeMismatch(), this.mConfig.getBitmapMemoryCacheEntryStateObserver());
        }
        return this.mBitmapCountingMemoryCache;
    }

    public InstrumentedMemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            MemoryCache<CacheKey, CloseableImage> backingCache = getBitmapCountingMemoryCache();
            this.mBitmapMemoryCache = InstrumentedMemoryCacheBitmapMemoryCacheFactory.get(backingCache, this.mConfig.getImageCacheStatsTracker());
        }
        return this.mBitmapMemoryCache;
    }

    public CountingMemoryCache<CacheKey, PooledByteBuffer> getEncodedCountingMemoryCache() {
        if (this.mEncodedCountingMemoryCache == null) {
            this.mEncodedCountingMemoryCache = EncodedCountingMemoryCacheFactory.get(this.mConfig.getEncodedMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getEncodedMemoryCacheTrimStrategy());
        }
        return this.mEncodedCountingMemoryCache;
    }

    public InstrumentedMemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCache() {
        MemoryCache<CacheKey, PooledByteBuffer> backingCache;
        if (this.mEncodedMemoryCache == null) {
            if (this.mConfig.getEncodedMemoryCacheOverride() != null) {
                backingCache = this.mConfig.getEncodedMemoryCacheOverride();
            } else {
                backingCache = getEncodedCountingMemoryCache();
            }
            this.mEncodedMemoryCache = EncodedMemoryCacheFactory.get(backingCache, this.mConfig.getImageCacheStatsTracker());
        }
        return this.mEncodedMemoryCache;
    }

    private ImageDecoder getImageDecoder() {
        if (this.mImageDecoder == null) {
            if (this.mConfig.getImageDecoder() != null) {
                this.mImageDecoder = this.mConfig.getImageDecoder();
            } else {
                AnimatedFactory animatedFactory = getAnimatedFactory();
                ImageDecoder gifDecoder = null;
                ImageDecoder webPDecoder = null;
                if (animatedFactory != null) {
                    gifDecoder = animatedFactory.getGifDecoder();
                    webPDecoder = animatedFactory.getWebPDecoder();
                }
                if (this.mConfig.getImageDecoderConfig() == null) {
                    this.mImageDecoder = new DefaultImageDecoder(gifDecoder, webPDecoder, getPlatformDecoder());
                } else {
                    this.mImageDecoder = new DefaultImageDecoder(gifDecoder, webPDecoder, getPlatformDecoder(), this.mConfig.getImageDecoderConfig().getCustomImageDecoders());
                    ImageFormatChecker.getInstance().setCustomImageFormatCheckers(this.mConfig.getImageDecoderConfig().getCustomImageFormats());
                }
            }
        }
        return this.mImageDecoder;
    }

    public BufferedDiskCache getMainBufferedDiskCache() {
        if (this.mMainBufferedDiskCache == null) {
            this.mMainBufferedDiskCache = new BufferedDiskCache(getMainFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().getIoBoundExecutor(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mMainBufferedDiskCache;
    }

    private Map<String, FileCache> getDynamicFileCaches() {
        if (this.mDynamicFileCaches == null) {
            this.mDynamicFileCaches = new HashMap();
            if (this.mConfig.getDynamicDiskCacheConfigMap() != null) {
                for (Map.Entry<String, DiskCacheConfig> diskCacheConfigEntry : this.mConfig.getDynamicDiskCacheConfigMap().entrySet()) {
                    this.mDynamicFileCaches.put(diskCacheConfigEntry.getKey(), this.mConfig.getFileCacheFactory().get(diskCacheConfigEntry.getValue()));
                }
            }
        }
        return this.mDynamicFileCaches;
    }

    private ImmutableMap<String, BufferedDiskCache> getDynamicBufferedDiskCaches() {
        if (this.mDynamicBufferedDiskCaches == null) {
            HashMap<String, BufferedDiskCache> bufferedDiskCaches = new HashMap<>();
            for (Map.Entry<String, FileCache> fileCacheEntry : getDynamicFileCaches().entrySet()) {
                bufferedDiskCaches.put(fileCacheEntry.getKey(), new BufferedDiskCache(fileCacheEntry.getValue(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().getIoBoundExecutor(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker()));
            }
            this.mDynamicBufferedDiskCaches = ImmutableMap.copyOf((Map) bufferedDiskCaches);
        }
        return this.mDynamicBufferedDiskCaches;
    }

    public FileCache getMainFileCache() {
        if (this.mMainFileCache == null) {
            DiskCacheConfig diskCacheConfig = this.mConfig.getMainDiskCacheConfig();
            this.mMainFileCache = this.mConfig.getFileCacheFactory().get(diskCacheConfig);
        }
        return this.mMainFileCache;
    }

    public ImagePipeline getImagePipeline() {
        if (sImagePipeline == null) {
            sImagePipeline = createImagePipeline();
        }
        return sImagePipeline;
    }

    private ImagePipeline createImagePipeline() {
        return new ImagePipeline(getProducerSequenceFactory(), this.mConfig.getRequestListeners(), this.mConfig.getRequestListener2s(), this.mConfig.isPrefetchEnabledSupplier(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), this.mThreadHandoffProducerQueue, this.mConfig.getExperiments().getSuppressBitmapPrefetchingSupplier(), this.mConfig.getExperiments().isLazyDataSource(), this.mConfig.getCallerContextVerifier(), this.mConfig);
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            this.mPlatformBitmapFactory = PlatformBitmapFactoryProvider.buildPlatformBitmapFactory(this.mConfig.getPoolFactory(), getPlatformDecoder(), getCloseableReferenceFactory());
        }
        return this.mPlatformBitmapFactory;
    }

    public PlatformDecoder getPlatformDecoder() {
        if (this.mPlatformDecoder == null) {
            this.mPlatformDecoder = PlatformDecoderFactory.buildPlatformDecoder(this.mConfig.getPoolFactory(), this.mConfig.getExperiments().getIsGingerbreadDecoderEnabled(), this.mConfig.getExperiments().getShouldUseDecodingBufferHelper(), this.mConfig.getExperiments().getPlatformDecoderOptions());
        }
        return this.mPlatformDecoder;
    }

    private ProducerFactory getProducerFactory() {
        if (this.mProducerFactory == null) {
            this.mProducerFactory = this.mConfig.getExperiments().getProducerFactoryMethod().createProducerFactory(this.mConfig.getContext(), this.mConfig.getPoolFactory().getSmallByteArrayPool(), getImageDecoder(), this.mConfig.getProgressiveJpegConfig(), this.mConfig.getDownsampleMode(), this.mConfig.getIsResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().getIsDecodeCancellationEnabled(), this.mConfig.getExecutorSupplier(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), getDynamicBufferedDiskCaches(), this.mConfig.getCacheKeyFactory(), getPlatformBitmapFactory(), this.mConfig.getExperiments().getBitmapPrepareToDrawMinSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawMaxSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawForPrefetch(), this.mConfig.getExperiments().getMaxBitmapSize(), getCloseableReferenceFactory(), this.mConfig.getExperiments().getKeepCancelledFetchAsLowPriority(), this.mConfig.getExperiments().getTrackedKeysSize());
        }
        return this.mProducerFactory;
    }

    private ProducerSequenceFactory getProducerSequenceFactory() {
        int i = Build.VERSION.SDK_INT;
        boolean useBitmapPrepareToDraw = this.mConfig.getExperiments().getUseBitmapPrepareToDraw();
        if (this.mProducerSequenceFactory == null) {
            this.mProducerSequenceFactory = new ProducerSequenceFactory(this.mConfig.getContext().getApplicationContext().getContentResolver(), getProducerFactory(), this.mConfig.getNetworkFetcher(), this.mConfig.getIsResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().getIsWebpSupportEnabled(), this.mThreadHandoffProducerQueue, this.mConfig.getDownsampleMode(), useBitmapPrepareToDraw, this.mConfig.getExperiments().getIsPartialImageCachingEnabled(), this.mConfig.getIsDiskCacheEnabled(), getImageTranscoderFactory(), this.mConfig.getExperiments().getIsEncodedMemoryCacheProbingEnabled(), this.mConfig.getExperiments().getIsDiskCacheProbingEnabled(), this.mConfig.getExperiments().getAllowDelay(), this.mConfig.getCustomProducerSequenceFactories());
        }
        return this.mProducerSequenceFactory;
    }

    public FileCache getSmallImageFileCache() {
        if (this.mSmallImageFileCache == null) {
            DiskCacheConfig diskCacheConfig = this.mConfig.getSmallImageDiskCacheConfig();
            this.mSmallImageFileCache = this.mConfig.getFileCacheFactory().get(diskCacheConfig);
        }
        return this.mSmallImageFileCache;
    }

    public CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.mCloseableReferenceFactory;
    }

    private BufferedDiskCache getSmallImageBufferedDiskCache() {
        if (this.mSmallImageBufferedDiskCache == null) {
            this.mSmallImageBufferedDiskCache = new BufferedDiskCache(getSmallImageFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().getIoBoundExecutor(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mSmallImageBufferedDiskCache;
    }

    private ImageTranscoderFactory getImageTranscoderFactory() {
        if (this.mImageTranscoderFactory == null) {
            if (this.mConfig.getImageTranscoderFactory() == null && this.mConfig.getImageTranscoderType() == null && this.mConfig.getExperiments().getIsNativeCodeDisabled()) {
                this.mImageTranscoderFactory = new SimpleImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapSize());
            } else {
                this.mImageTranscoderFactory = new MultiImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapSize(), this.mConfig.getExperiments().getUseDownsamplingRatioForResizing(), this.mConfig.getImageTranscoderFactory(), this.mConfig.getImageTranscoderType(), this.mConfig.getExperiments().getIsEnsureTranscoderLibraryLoaded());
            }
        }
        return this.mImageTranscoderFactory;
    }

    @Nullable
    public String reportData() {
        Objects.ToStringHelper b = Objects.toStringHelper("ImagePipelineFactory");
        if (this.mBitmapCountingMemoryCache != null) {
            b.add("bitmapCountingMemoryCache", this.mBitmapCountingMemoryCache.getDebugData());
        }
        if (this.mEncodedCountingMemoryCache != null) {
            b.add("encodedCountingMemoryCache", this.mEncodedCountingMemoryCache.getDebugData());
        }
        return b.toString();
    }
}
