package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.core.util.Pools;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes11.dex */
public class Engine implements EngineJobListener, MemoryCache.ResourceRemovedListener, EngineResource.ResourceListener {
    private static final int JOB_POOL_SIZE = 150;
    private static final String TAG = "Engine";
    private static final boolean VERBOSE_IS_LOGGABLE = Log.isLoggable(TAG, 2);
    private final ActiveResources activeResources;
    private final MemoryCache cache;
    private final DecodeJobFactory decodeJobFactory;
    private final LazyDiskCacheProvider diskCacheProvider;
    private final EngineJobFactory engineJobFactory;
    private final Jobs jobs;
    private final EngineKeyFactory keyFactory;
    private final ResourceRecycler resourceRecycler;

    public Engine(MemoryCache memoryCache, DiskCache.Factory diskCacheFactory, GlideExecutor diskCacheExecutor, GlideExecutor sourceExecutor, GlideExecutor sourceUnlimitedExecutor, GlideExecutor animationExecutor, boolean isActiveResourceRetentionAllowed) {
        this(memoryCache, diskCacheFactory, diskCacheExecutor, sourceExecutor, sourceUnlimitedExecutor, animationExecutor, null, null, null, null, null, null, isActiveResourceRetentionAllowed);
    }

    Engine(MemoryCache cache, DiskCache.Factory diskCacheFactory, GlideExecutor diskCacheExecutor, GlideExecutor sourceExecutor, GlideExecutor sourceUnlimitedExecutor, GlideExecutor animationExecutor, Jobs jobs, EngineKeyFactory keyFactory, ActiveResources activeResources, EngineJobFactory engineJobFactory, DecodeJobFactory decodeJobFactory, ResourceRecycler resourceRecycler, boolean isActiveResourceRetentionAllowed) {
        EngineKeyFactory keyFactory2;
        Jobs jobs2;
        EngineJobFactory engineJobFactory2;
        DecodeJobFactory decodeJobFactory2;
        ResourceRecycler resourceRecycler2;
        this.cache = cache;
        this.diskCacheProvider = new LazyDiskCacheProvider(diskCacheFactory);
        ActiveResources activeResources2 = activeResources == null ? new ActiveResources(isActiveResourceRetentionAllowed) : activeResources;
        this.activeResources = activeResources2;
        activeResources2.setListener(this);
        if (keyFactory != null) {
            keyFactory2 = keyFactory;
        } else {
            keyFactory2 = new EngineKeyFactory();
        }
        this.keyFactory = keyFactory2;
        if (jobs != null) {
            jobs2 = jobs;
        } else {
            jobs2 = new Jobs();
        }
        this.jobs = jobs2;
        if (engineJobFactory != null) {
            engineJobFactory2 = engineJobFactory;
        } else {
            engineJobFactory2 = new EngineJobFactory(diskCacheExecutor, sourceExecutor, sourceUnlimitedExecutor, animationExecutor, this, this);
        }
        this.engineJobFactory = engineJobFactory2;
        if (decodeJobFactory != null) {
            decodeJobFactory2 = decodeJobFactory;
        } else {
            decodeJobFactory2 = new DecodeJobFactory(this.diskCacheProvider);
        }
        this.decodeJobFactory = decodeJobFactory2;
        if (resourceRecycler != null) {
            resourceRecycler2 = resourceRecycler;
        } else {
            resourceRecycler2 = new ResourceRecycler();
        }
        this.resourceRecycler = resourceRecycler2;
        cache.setResourceRemovedListener(this);
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x007c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <R> com.bumptech.glide.load.engine.Engine.LoadStatus load(com.bumptech.glide.GlideContext r26, java.lang.Object r27, com.bumptech.glide.load.Key r28, int r29, int r30, java.lang.Class<?> r31, java.lang.Class<R> r32, com.bumptech.glide.Priority r33, com.bumptech.glide.load.engine.DiskCacheStrategy r34, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r35, boolean r36, boolean r37, com.bumptech.glide.load.Options r38, boolean r39, boolean r40, boolean r41, boolean r42, com.bumptech.glide.request.ResourceCallback r43, java.util.concurrent.Executor r44) throws java.lang.Throwable {
        /*
            r25 = this;
            r2 = r25
            boolean r0 = com.bumptech.glide.load.engine.Engine.VERBOSE_IS_LOGGABLE
            if (r0 == 0) goto Lb
            long r0 = com.bumptech.glide.util.LogTime.getLogTime()
            goto Ld
        Lb:
            r0 = 0
        Ld:
            r23 = r0
            r3 = r23
            com.bumptech.glide.load.engine.EngineKeyFactory r5 = r2.keyFactory
            r6 = r27
            r7 = r28
            r8 = r29
            r9 = r30
            r11 = r31
            r12 = r32
            r10 = r35
            r13 = r38
            com.bumptech.glide.load.engine.EngineKey r1 = r5.buildKey(r6, r7, r8, r9, r10, r11, r12, r13)
            monitor-enter(r25)
            r5 = r39
            com.bumptech.glide.load.engine.EngineResource r0 = r2.loadFromMemory(r1, r5, r3)     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L60
            r6 = r29
            r7 = r30
            r8 = r31
            r9 = r32
            r10 = r33
            r11 = r34
            r12 = r35
            r13 = r36
            r14 = r37
            r15 = r38
            r17 = r40
            r18 = r41
            r19 = r42
            r20 = r43
            r21 = r44
            r22 = r1
            r23 = r3
            r16 = r5
            r3 = r26
            r4 = r27
            r5 = r28
            com.bumptech.glide.load.engine.Engine$LoadStatus r1 = r2.waitForExistingOrStartNewJob(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)     // Catch: java.lang.Throwable -> L6f
            monitor-exit(r25)     // Catch: java.lang.Throwable -> L6f
            return r1
        L60:
            r22 = r1
            r23 = r3
            monitor-exit(r25)     // Catch: java.lang.Throwable -> L6f
            com.bumptech.glide.load.DataSource r1 = com.bumptech.glide.load.DataSource.MEMORY_CACHE
            r2 = 0
            r3 = r43
            r3.onResourceReady(r0, r1, r2)
            r1 = 0
            return r1
        L6f:
            r0 = move-exception
            r3 = r43
            goto L7a
        L73:
            r0 = move-exception
            r22 = r1
            r23 = r3
            r3 = r43
        L7a:
            monitor-exit(r25)     // Catch: java.lang.Throwable -> L7c
            throw r0
        L7c:
            r0 = move-exception
            goto L7a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.Engine.load(com.bumptech.glide.GlideContext, java.lang.Object, com.bumptech.glide.load.Key, int, int, java.lang.Class, java.lang.Class, com.bumptech.glide.Priority, com.bumptech.glide.load.engine.DiskCacheStrategy, java.util.Map, boolean, boolean, com.bumptech.glide.load.Options, boolean, boolean, boolean, boolean, com.bumptech.glide.request.ResourceCallback, java.util.concurrent.Executor):com.bumptech.glide.load.engine.Engine$LoadStatus");
    }

    private <R> LoadStatus waitForExistingOrStartNewJob(GlideContext glideContext, Object model, Key signature, int width, int height, Class<?> resourceClass, Class<R> transcodeClass, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> transformations, boolean isTransformationRequired, boolean isScaleOnlyOrNoTransform, Options options, boolean isMemoryCacheable, boolean useUnlimitedSourceExecutorPool, boolean useAnimationPool, boolean onlyRetrieveFromCache, ResourceCallback cb, Executor callbackExecutor, EngineKey key, long startTime) {
        EngineJob<?> current = this.jobs.get(key, onlyRetrieveFromCache);
        if (current != null) {
            current.addCallback(cb, callbackExecutor);
            if (VERBOSE_IS_LOGGABLE) {
                logWithTimeAndKey("Added to existing load", startTime, key);
            }
            return new LoadStatus(cb, current);
        }
        EngineJob<R> engineJob = this.engineJobFactory.build(key, isMemoryCacheable, useUnlimitedSourceExecutorPool, useAnimationPool, onlyRetrieveFromCache);
        DecodeJob<R> decodeJob = this.decodeJobFactory.build(glideContext, model, key, signature, width, height, resourceClass, transcodeClass, priority, diskCacheStrategy, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, onlyRetrieveFromCache, options, engineJob);
        this.jobs.put(key, engineJob);
        engineJob.addCallback(cb, callbackExecutor);
        engineJob.start(decodeJob);
        if (VERBOSE_IS_LOGGABLE) {
            logWithTimeAndKey("Started new load", startTime, key);
        }
        return new LoadStatus(cb, engineJob);
    }

    private EngineResource<?> loadFromMemory(EngineKey key, boolean isMemoryCacheable, long startTime) {
        if (!isMemoryCacheable) {
            return null;
        }
        EngineResource<?> active = loadFromActiveResources(key);
        if (active != null) {
            if (VERBOSE_IS_LOGGABLE) {
                logWithTimeAndKey("Loaded resource from active resources", startTime, key);
            }
            return active;
        }
        EngineResource<?> cached = loadFromCache(key);
        if (cached == null) {
            return null;
        }
        if (VERBOSE_IS_LOGGABLE) {
            logWithTimeAndKey("Loaded resource from cache", startTime, key);
        }
        return cached;
    }

    private static void logWithTimeAndKey(String log, long startTime, Key key) {
        Log.v(TAG, log + " in " + LogTime.getElapsedMillis(startTime) + "ms, key: " + key);
    }

    private EngineResource<?> loadFromActiveResources(Key key) {
        EngineResource<?> active = this.activeResources.get(key);
        if (active != null) {
            active.acquire();
        }
        return active;
    }

    private EngineResource<?> loadFromCache(Key key) {
        EngineResource<?> cached = getEngineResourceFromCache(key);
        if (cached != null) {
            cached.acquire();
            this.activeResources.activate(key, cached);
        }
        return cached;
    }

    private EngineResource<?> getEngineResourceFromCache(Key key) {
        Resource<?> cached = this.cache.remove(key);
        if (cached == null) {
            return null;
        }
        if (cached instanceof EngineResource) {
            EngineResource<?> result = (EngineResource) cached;
            return result;
        }
        EngineResource<?> result2 = new EngineResource<>(cached, true, true, key, this);
        return result2;
    }

    public void release(Resource<?> resource) {
        if (resource instanceof EngineResource) {
            ((EngineResource) resource).release();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    @Override // com.bumptech.glide.load.engine.EngineJobListener
    public synchronized void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> resource) {
        if (resource != null) {
            if (resource.isMemoryCacheable()) {
                this.activeResources.activate(key, resource);
            }
        }
        this.jobs.removeIfCurrent(key, engineJob);
    }

    @Override // com.bumptech.glide.load.engine.EngineJobListener
    public synchronized void onEngineJobCancelled(EngineJob<?> engineJob, Key key) {
        this.jobs.removeIfCurrent(key, engineJob);
    }

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache.ResourceRemovedListener
    public void onResourceRemoved(Resource<?> resource) {
        this.resourceRecycler.recycle(resource, true);
    }

    @Override // com.bumptech.glide.load.engine.EngineResource.ResourceListener
    public void onResourceReleased(Key cacheKey, EngineResource<?> resource) {
        this.activeResources.deactivate(cacheKey);
        if (resource.isMemoryCacheable()) {
            this.cache.put(cacheKey, resource);
        } else {
            this.resourceRecycler.recycle(resource, false);
        }
    }

    public void clearDiskCache() {
        this.diskCacheProvider.getDiskCache().clear();
    }

    public void shutdown() {
        this.engineJobFactory.shutdown();
        this.diskCacheProvider.clearDiskCacheIfCreated();
        this.activeResources.shutdown();
    }

    public class LoadStatus {
        private final ResourceCallback cb;
        private final EngineJob<?> engineJob;

        LoadStatus(ResourceCallback cb, EngineJob<?> engineJob) {
            this.cb = cb;
            this.engineJob = engineJob;
        }

        public void cancel() {
            synchronized (Engine.this) {
                this.engineJob.removeCallback(this.cb);
            }
        }
    }

    private static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {
        private volatile DiskCache diskCache;
        private final DiskCache.Factory factory;

        LazyDiskCacheProvider(DiskCache.Factory factory) {
            this.factory = factory;
        }

        synchronized void clearDiskCacheIfCreated() {
            if (this.diskCache == null) {
                return;
            }
            this.diskCache.clear();
        }

        @Override // com.bumptech.glide.load.engine.DecodeJob.DiskCacheProvider
        public DiskCache getDiskCache() {
            if (this.diskCache == null) {
                synchronized (this) {
                    if (this.diskCache == null) {
                        this.diskCache = this.factory.build();
                    }
                    if (this.diskCache == null) {
                        this.diskCache = new DiskCacheAdapter();
                    }
                }
            }
            return this.diskCache;
        }
    }

    static class DecodeJobFactory {
        private int creationOrder;
        final DecodeJob.DiskCacheProvider diskCacheProvider;
        final Pools.Pool<DecodeJob<?>> pool = FactoryPools.threadSafe(150, new FactoryPools.Factory<DecodeJob<?>>() { // from class: com.bumptech.glide.load.engine.Engine.DecodeJobFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
            public DecodeJob<?> create() {
                return new DecodeJob<>(DecodeJobFactory.this.diskCacheProvider, DecodeJobFactory.this.pool);
            }
        });

        DecodeJobFactory(DecodeJob.DiskCacheProvider diskCacheProvider) {
            this.diskCacheProvider = diskCacheProvider;
        }

        <R> DecodeJob<R> build(GlideContext glideContext, Object model, EngineKey loadKey, Key signature, int width, int height, Class<?> resourceClass, Class<R> transcodeClass, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> transformations, boolean isTransformationRequired, boolean isScaleOnlyOrNoTransform, boolean onlyRetrieveFromCache, Options options, DecodeJob.Callback<R> callback) {
            DecodeJob<R> result = (DecodeJob) Preconditions.checkNotNull(this.pool.acquire());
            int i = this.creationOrder;
            this.creationOrder = i + 1;
            return result.init(glideContext, model, loadKey, signature, width, height, resourceClass, transcodeClass, priority, diskCacheStrategy, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, onlyRetrieveFromCache, options, callback, i);
        }
    }

    static class EngineJobFactory {
        final GlideExecutor animationExecutor;
        final GlideExecutor diskCacheExecutor;
        final EngineJobListener engineJobListener;
        final Pools.Pool<EngineJob<?>> pool = FactoryPools.threadSafe(150, new FactoryPools.Factory<EngineJob<?>>() { // from class: com.bumptech.glide.load.engine.Engine.EngineJobFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
            public EngineJob<?> create() {
                return new EngineJob<>(EngineJobFactory.this.diskCacheExecutor, EngineJobFactory.this.sourceExecutor, EngineJobFactory.this.sourceUnlimitedExecutor, EngineJobFactory.this.animationExecutor, EngineJobFactory.this.engineJobListener, EngineJobFactory.this.resourceListener, EngineJobFactory.this.pool);
            }
        });
        final EngineResource.ResourceListener resourceListener;
        final GlideExecutor sourceExecutor;
        final GlideExecutor sourceUnlimitedExecutor;

        EngineJobFactory(GlideExecutor diskCacheExecutor, GlideExecutor sourceExecutor, GlideExecutor sourceUnlimitedExecutor, GlideExecutor animationExecutor, EngineJobListener engineJobListener, EngineResource.ResourceListener resourceListener) {
            this.diskCacheExecutor = diskCacheExecutor;
            this.sourceExecutor = sourceExecutor;
            this.sourceUnlimitedExecutor = sourceUnlimitedExecutor;
            this.animationExecutor = animationExecutor;
            this.engineJobListener = engineJobListener;
            this.resourceListener = resourceListener;
        }

        void shutdown() {
            Executors.shutdownAndAwaitTermination(this.diskCacheExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceUnlimitedExecutor);
            Executors.shutdownAndAwaitTermination(this.animationExecutor);
        }

        <R> EngineJob<R> build(Key key, boolean isMemoryCacheable, boolean useUnlimitedSourceGeneratorPool, boolean useAnimationPool, boolean onlyRetrieveFromCache) {
            EngineJob<R> result = (EngineJob) Preconditions.checkNotNull(this.pool.acquire());
            return result.init(key, isMemoryCacheable, useUnlimitedSourceGeneratorPool, useAnimationPool, onlyRetrieveFromCache);
        }
    }
}
