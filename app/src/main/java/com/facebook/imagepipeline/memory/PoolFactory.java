package com.facebook.imagepipeline.memory;

import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PoolFactory {

    @Nullable
    private MemoryChunkPool mAshmemMemoryChunkPool;

    @Nullable
    private BitmapPool mBitmapPool;

    @Nullable
    private MemoryChunkPool mBufferMemoryChunkPool;
    private final PoolConfig mConfig;

    @Nullable
    private FlexByteArrayPool mFlexByteArrayPool;

    @Nullable
    private MemoryChunkPool mNativeMemoryChunkPool;

    @Nullable
    private PooledByteBufferFactory mPooledByteBufferFactory;

    @Nullable
    private PooledByteStreams mPooledByteStreams;

    @Nullable
    private SharedByteArray mSharedByteArray;

    @Nullable
    private ByteArrayPool mSmallByteArrayPool;

    public PoolFactory(PoolConfig config) {
        this.mConfig = (PoolConfig) Preconditions.checkNotNull(config);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x0044  */
    public BitmapPool getBitmapPool() {
        MemoryTrimmableRegistry memoryTrimmableRegistry;
        if (this.mBitmapPool == null) {
            String bitmapPoolType = this.mConfig.getBitmapPoolType();
            switch (bitmapPoolType) {
                case "dummy":
                    this.mBitmapPool = new DummyBitmapPool();
                    break;
                case "dummy_with_tracking":
                    this.mBitmapPool = new DummyTrackingInUseBitmapPool();
                    break;
                case "experimental":
                    int bitmapPoolMaxPoolSize = this.mConfig.getBitmapPoolMaxPoolSize();
                    int bitmapPoolMaxBitmapSize = this.mConfig.getBitmapPoolMaxBitmapSize();
                    NoOpPoolStatsTracker noOpPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
                    if (this.mConfig.isRegisterLruBitmapPoolAsMemoryTrimmable()) {
                        memoryTrimmableRegistry = this.mConfig.getMemoryTrimmableRegistry();
                    } else {
                        memoryTrimmableRegistry = null;
                    }
                    this.mBitmapPool = new LruBitmapPool(bitmapPoolMaxPoolSize, bitmapPoolMaxBitmapSize, noOpPoolStatsTracker, memoryTrimmableRegistry);
                    break;
                case "legacy_default_params":
                    this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), DefaultBitmapPoolParams.get(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
                    break;
                case "legacy":
                default:
                    int i = Build.VERSION.SDK_INT;
                    this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapPoolParams(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
                    break;
            }
        }
        return this.mBitmapPool;
    }

    @Nullable
    public MemoryChunkPool getBufferMemoryChunkPool() {
        if (this.mBufferMemoryChunkPool == null) {
            try {
                Class<?> clazz = Class.forName("com.facebook.imagepipeline.memory.BufferMemoryChunkPool");
                Constructor<?> cons = clazz.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class);
                this.mBufferMemoryChunkPool = (MemoryChunkPool) cons.newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException e) {
                this.mBufferMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                this.mBufferMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                this.mBufferMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                this.mBufferMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                this.mBufferMemoryChunkPool = null;
            }
        }
        return this.mBufferMemoryChunkPool;
    }

    public FlexByteArrayPool getFlexByteArrayPool() {
        if (this.mFlexByteArrayPool == null) {
            this.mFlexByteArrayPool = new FlexByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mFlexByteArrayPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }

    @Nullable
    public MemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            try {
                Class<?> clazz = Class.forName("com.facebook.imagepipeline.memory.NativeMemoryChunkPool");
                Constructor<?> cons = clazz.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class);
                this.mNativeMemoryChunkPool = (MemoryChunkPool) cons.newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException e) {
                FLog.e("PoolFactory", "", e);
                this.mNativeMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                FLog.e("PoolFactory", "", e2);
                this.mNativeMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                FLog.e("PoolFactory", "", e3);
                this.mNativeMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                FLog.e("PoolFactory", "", e4);
                this.mNativeMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                FLog.e("PoolFactory", "", e5);
                this.mNativeMemoryChunkPool = null;
            }
        }
        return this.mNativeMemoryChunkPool;
    }

    @Nullable
    private MemoryChunkPool getAshmemMemoryChunkPool() {
        if (this.mAshmemMemoryChunkPool == null) {
            try {
                Class<?> clazz = Class.forName("com.facebook.imagepipeline.memory.AshmemMemoryChunkPool");
                Constructor<?> cons = clazz.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class);
                this.mAshmemMemoryChunkPool = (MemoryChunkPool) cons.newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException e) {
                this.mAshmemMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                this.mAshmemMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                this.mAshmemMemoryChunkPool = null;
            }
        }
        return this.mAshmemMemoryChunkPool;
    }

    public PooledByteBufferFactory getPooledByteBufferFactory() {
        return getPooledByteBufferFactory(!NativeCodeSetup.getUseNativeCode() ? 1 : 0);
    }

    public PooledByteBufferFactory getPooledByteBufferFactory(int memoryChunkType) {
        if (this.mPooledByteBufferFactory == null) {
            MemoryChunkPool memoryChunkPool = getMemoryChunkPool(memoryChunkType);
            Preconditions.checkNotNull(memoryChunkPool, "failed to get pool for chunk type: " + memoryChunkType);
            this.mPooledByteBufferFactory = new MemoryPooledByteBufferFactory(memoryChunkPool, getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public SharedByteArray getSharedByteArray() {
        if (this.mSharedByteArray == null) {
            this.mSharedByteArray = new SharedByteArray(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mSharedByteArray;
    }

    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }

    @Nullable
    private MemoryChunkPool getMemoryChunkPool(int memoryChunkType) {
        switch (memoryChunkType) {
            case 0:
                return getNativeMemoryChunkPool();
            case 1:
                return getBufferMemoryChunkPool();
            case 2:
                return getAshmemMemoryChunkPool();
            default:
                throw new IllegalArgumentException("Invalid MemoryChunkType");
        }
    }
}
