package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferedDiskCache.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"H\u0002J\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0086\u0002J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010)\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\"J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "", "fileCache", "Lcom/facebook/cache/disk/FileCache;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "readExecutor", "Ljava/util/concurrent/Executor;", "writeExecutor", "imageCacheStatsTracker", "Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "(Lcom/facebook/cache/disk/FileCache;Lcom/facebook/common/memory/PooledByteBufferFactory;Lcom/facebook/common/memory/PooledByteStreams;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;)V", "size", "", "getSize", "()J", "stagingArea", "Lcom/facebook/imagepipeline/cache/StagingArea;", "addKeyForAsyncProbing", "", "key", "Lcom/facebook/cache/common/CacheKey;", "checkInStagingAreaAndFileCache", "", "clearAll", "Lbolts/Task;", "Ljava/lang/Void;", "contains", "containsAsync", "containsSync", "diskCheckSync", "foundPinnedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "pinnedImage", "get", "isCancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getAsync", "probe", "put", "encodedImage", "readFromDiskCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "remove", "writeToDiskCache", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BufferedDiskCache {
    private static final Class<?> TAG = BufferedDiskCache.class;
    private final FileCache fileCache;
    private final ImageCacheStatsTracker imageCacheStatsTracker;
    private final PooledByteBufferFactory pooledByteBufferFactory;
    private final PooledByteStreams pooledByteStreams;
    private final Executor readExecutor;
    private final StagingArea stagingArea;
    private final Executor writeExecutor;

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor readExecutor, Executor writeExecutor, ImageCacheStatsTracker imageCacheStatsTracker) {
        Intrinsics.checkNotNullParameter(fileCache, "fileCache");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(pooledByteStreams, "pooledByteStreams");
        Intrinsics.checkNotNullParameter(readExecutor, "readExecutor");
        Intrinsics.checkNotNullParameter(writeExecutor, "writeExecutor");
        Intrinsics.checkNotNullParameter(imageCacheStatsTracker, "imageCacheStatsTracker");
        this.fileCache = fileCache;
        this.pooledByteBufferFactory = pooledByteBufferFactory;
        this.pooledByteStreams = pooledByteStreams;
        this.readExecutor = readExecutor;
        this.writeExecutor = writeExecutor;
        this.imageCacheStatsTracker = imageCacheStatsTracker;
        StagingArea stagingArea = StagingArea.getInstance();
        Intrinsics.checkNotNullExpressionValue(stagingArea, "getInstance()");
        this.stagingArea = stagingArea;
    }

    public final boolean containsSync(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.stagingArea.containsKey(key) || this.fileCache.hasKeySync(key);
    }

    public final Task<Boolean> contains(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (containsSync(key)) {
            Task<Boolean> taskForResult = Task.forResult(true);
            Intrinsics.checkNotNullExpressionValue(taskForResult, "{\n        Task.forResult(true)\n      }");
            return taskForResult;
        }
        return containsAsync(key);
    }

    private final Task<Boolean> containsAsync(final CacheKey key) {
        try {
            final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_containsAsync");
            Task<Boolean> taskCall = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BufferedDiskCache.containsAsync$lambda$0(token, this, key);
                }
            }, this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(taskCall, "{\n      val token = Fres…      readExecutor)\n    }");
            return taskCall;
        } catch (Exception exception) {
            FLog.w(TAG, exception, "Failed to schedule disk-cache read for %s", key.getUriString());
            Task<Boolean> taskForError = Task.forError(exception);
            Intrinsics.checkNotNullExpressionValue(taskForError, "{\n      // Log failure\n …forError(exception)\n    }");
            return taskForError;
        }
    }

    static final Boolean containsAsync$lambda$0(Object $token, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            Boolean boolValueOf = Boolean.valueOf(this$0.checkInStagingAreaAndFileCache(key));
            FrescoInstrumenter.onEndWork(currentToken);
            return boolValueOf;
        } catch (Throwable th) {
            try {
                FrescoInstrumenter.markFailure($token, th);
                throw th;
            } catch (Throwable th2) {
                FrescoInstrumenter.onEndWork(currentToken);
                throw th2;
            }
        }
    }

    public final boolean diskCheckSync(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (containsSync(key)) {
            return true;
        }
        return checkInStagingAreaAndFileCache(key);
    }

    public final Task<EncodedImage> get(CacheKey key, AtomicBoolean isCancelled) {
        Task<EncodedImage> taskFoundPinnedImage;
        Task<EncodedImage> taskFoundPinnedImage2;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(isCancelled, "isCancelled");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            EncodedImage pinnedImage = this.stagingArea.get(key);
            return (pinnedImage == null || (taskFoundPinnedImage2 = foundPinnedImage(key, pinnedImage)) == null) ? getAsync(key, isCancelled) : taskFoundPinnedImage2;
        }
        FrescoSystrace.beginSection("BufferedDiskCache#get");
        try {
            EncodedImage pinnedImage2 = this.stagingArea.get(key);
            return (pinnedImage2 == null || (taskFoundPinnedImage = foundPinnedImage(key, pinnedImage2)) == null) ? getAsync(key, isCancelled) : taskFoundPinnedImage;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Task<Void> probe(final CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_probe");
            Task<Void> taskCall = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BufferedDiskCache.probe$lambda$3(token, this, key);
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(taskCall, "{\n      val token = Fres…     writeExecutor)\n    }");
            return taskCall;
        } catch (Exception exception) {
            FLog.w(TAG, exception, "Failed to schedule disk-cache probe for %s", key.getUriString());
            Task<Void> taskForError = Task.forError(exception);
            Intrinsics.checkNotNullExpressionValue(taskForError, "{\n      FLog.w(TAG, exce…forError(exception)\n    }");
            return taskForError;
        }
    }

    static final Void probe$lambda$3(Object $token, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            this$0.fileCache.probe(key);
            return null;
        } finally {
            FrescoInstrumenter.onEndWork(currentToken);
        }
    }

    public final void addKeyForAsyncProbing(CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.fileCache.probe(key);
    }

    private final boolean checkInStagingAreaAndFileCache(CacheKey key) {
        EncodedImage result = this.stagingArea.get(key);
        if (result != null) {
            result.close();
            FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
            this.imageCacheStatsTracker.onStagingAreaHit(key);
            return true;
        }
        FLog.v(TAG, "Did not find image for %s in staging area", key.getUriString());
        this.imageCacheStatsTracker.onStagingAreaMiss(key);
        try {
            return this.fileCache.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    private final Task<EncodedImage> getAsync(final CacheKey key, final AtomicBoolean isCancelled) {
        try {
            final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_getAsync");
            Task<EncodedImage> taskCall = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BufferedDiskCache.getAsync$lambda$4(token, isCancelled, this, key);
                }
            }, this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(taskCall, "{\n      val token = Fres…      readExecutor)\n    }");
            return taskCall;
        } catch (Exception exception) {
            FLog.w(TAG, exception, "Failed to schedule disk-cache read for %s", key.getUriString());
            Task<EncodedImage> taskForError = Task.forError(exception);
            Intrinsics.checkNotNullExpressionValue(taskForError, "{\n      // Log failure\n …forError(exception)\n    }");
            return taskForError;
        }
    }

    static final EncodedImage getAsync$lambda$4(Object $token, AtomicBoolean isCancelled, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(isCancelled, "$isCancelled");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            if (isCancelled.get()) {
                throw new CancellationException();
            }
            EncodedImage result = this$0.stagingArea.get(key);
            if (result != null) {
                FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
                this$0.imageCacheStatsTracker.onStagingAreaHit(key);
            } else {
                FLog.v(TAG, "Did not find image for %s in staging area", key.getUriString());
                this$0.imageCacheStatsTracker.onStagingAreaMiss(key);
                try {
                    PooledByteBuffer buffer = this$0.readFromDiskCache(key);
                    if (buffer == null) {
                        FrescoInstrumenter.onEndWork(currentToken);
                        return null;
                    }
                    CloseableReference ref = CloseableReference.of(buffer);
                    Intrinsics.checkNotNullExpressionValue(ref, "of(buffer)");
                    try {
                        EncodedImage encodedImage = new EncodedImage((CloseableReference<PooledByteBuffer>) ref);
                        CloseableReference.closeSafely((CloseableReference<?>) ref);
                        result = encodedImage;
                    } catch (Throwable th) {
                        CloseableReference.closeSafely((CloseableReference<?>) ref);
                        throw th;
                    }
                } catch (Exception e) {
                    FrescoInstrumenter.onEndWork(currentToken);
                    return null;
                }
            }
            if (!Thread.interrupted()) {
                FrescoInstrumenter.onEndWork(currentToken);
                return result;
            }
            FLog.v(TAG, "Host thread was interrupted, decreasing reference count");
            result.close();
            throw new InterruptedException();
        } catch (Throwable th2) {
            try {
                FrescoInstrumenter.markFailure($token, th2);
                throw th2;
            } catch (Throwable th3) {
                FrescoInstrumenter.onEndWork(currentToken);
                throw th3;
            }
        }
    }

    public final void put(final CacheKey key, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (!EncodedImage.isValid(encodedImage)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.stagingArea.put(key, encodedImage);
            final EncodedImage finalEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            try {
                final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync");
                this.writeExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BufferedDiskCache.put$lambda$6$lambda$5(token, this, key, finalEncodedImage);
                    }
                });
                return;
            } catch (Exception exception) {
                FLog.w(TAG, exception, "Failed to schedule disk-cache write for %s", key.getUriString());
                this.stagingArea.remove(key, encodedImage);
                EncodedImage.closeSafely(finalEncodedImage);
                return;
            }
        }
        FrescoSystrace.beginSection("BufferedDiskCache#put");
        try {
            if (!EncodedImage.isValid(encodedImage)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.stagingArea.put(key, encodedImage);
            final EncodedImage finalEncodedImage2 = EncodedImage.cloneOrNull(encodedImage);
            try {
                final Object token2 = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync");
                this.writeExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BufferedDiskCache.put$lambda$6$lambda$5(token2, this, key, finalEncodedImage2);
                    }
                });
            } catch (Exception exception2) {
                FLog.w(TAG, exception2, "Failed to schedule disk-cache write for %s", key.getUriString());
                this.stagingArea.remove(key, encodedImage);
                EncodedImage.closeSafely(finalEncodedImage2);
            }
            Unit unit = Unit.INSTANCE;
            FrescoSystrace.endSection();
        } catch (Throwable th) {
            FrescoSystrace.endSection();
            throw th;
        }
    }

    static final void put$lambda$6$lambda$5(Object $token, BufferedDiskCache this$0, CacheKey key, EncodedImage $finalEncodedImage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            this$0.writeToDiskCache(key, $finalEncodedImage);
            StagingArea stagingArea = this$0.stagingArea;
            Intrinsics.checkNotNull($finalEncodedImage);
            stagingArea.remove(key, $finalEncodedImage);
            EncodedImage.closeSafely($finalEncodedImage);
            FrescoInstrumenter.onEndWork(currentToken);
        } catch (Throwable th) {
            try {
                FrescoInstrumenter.markFailure($token, th);
                throw th;
            } catch (Throwable th2) {
                StagingArea stagingArea2 = this$0.stagingArea;
                Intrinsics.checkNotNull($finalEncodedImage);
                stagingArea2.remove(key, $finalEncodedImage);
                EncodedImage.closeSafely($finalEncodedImage);
                FrescoInstrumenter.onEndWork(currentToken);
                throw th2;
            }
        }
    }

    public final Task<Void> remove(final CacheKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.stagingArea.remove(key);
        try {
            final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_remove");
            Task<Void> taskCall = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda3
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BufferedDiskCache.remove$lambda$7(token, this, key);
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(taskCall, "{\n      val token = Fres…     writeExecutor)\n    }");
            return taskCall;
        } catch (Exception exception) {
            FLog.w(TAG, exception, "Failed to schedule disk-cache remove for %s", key.getUriString());
            Task<Void> taskForError = Task.forError(exception);
            Intrinsics.checkNotNullExpressionValue(taskForError, "{\n      // Log failure\n …forError(exception)\n    }");
            return taskForError;
        }
    }

    static final Void remove$lambda$7(Object $token, BufferedDiskCache this$0, CacheKey key) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            this$0.stagingArea.remove(key);
            this$0.fileCache.remove(key);
            FrescoInstrumenter.onEndWork(currentToken);
            return null;
        } catch (Throwable th) {
            try {
                FrescoInstrumenter.markFailure($token, th);
                throw th;
            } catch (Throwable th2) {
                FrescoInstrumenter.onEndWork(currentToken);
                throw th2;
            }
        }
    }

    public final Task<Void> clearAll() {
        this.stagingArea.clearAll();
        final Object token = FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_clearAll");
        try {
            Task<Void> taskCall = Task.call(new Callable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BufferedDiskCache.clearAll$lambda$8(token, this);
                }
            }, this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(taskCall, "{\n      Task.call(\n     …     writeExecutor)\n    }");
            return taskCall;
        } catch (Exception exception) {
            FLog.w(TAG, exception, "Failed to schedule disk-cache clear", new Object[0]);
            Task<Void> taskForError = Task.forError(exception);
            Intrinsics.checkNotNullExpressionValue(taskForError, "{\n      // Log failure\n …forError(exception)\n    }");
            return taskForError;
        }
    }

    static final Void clearAll$lambda$8(Object $token, BufferedDiskCache this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object currentToken = FrescoInstrumenter.onBeginWork($token, null);
        try {
            this$0.stagingArea.clearAll();
            this$0.fileCache.clearAll();
            FrescoInstrumenter.onEndWork(currentToken);
            return null;
        } catch (Throwable th) {
            try {
                FrescoInstrumenter.markFailure($token, th);
                throw th;
            } catch (Throwable th2) {
                FrescoInstrumenter.onEndWork(currentToken);
                throw th2;
            }
        }
    }

    public final long getSize() {
        return this.fileCache.getSize();
    }

    private final Task<EncodedImage> foundPinnedImage(CacheKey key, EncodedImage pinnedImage) {
        FLog.v(TAG, "Found image for %s in staging area", key.getUriString());
        this.imageCacheStatsTracker.onStagingAreaHit(key);
        Task<EncodedImage> taskForResult = Task.forResult(pinnedImage);
        Intrinsics.checkNotNullExpressionValue(taskForResult, "forResult(pinnedImage)");
        return taskForResult;
    }

    private final PooledByteBuffer readFromDiskCache(CacheKey key) throws IOException {
        try {
            FLog.v(TAG, "Disk cache read for %s", key.getUriString());
            BinaryResource diskCacheResource = this.fileCache.getResource(key);
            if (diskCacheResource == null) {
                FLog.v(TAG, "Disk cache miss for %s", key.getUriString());
                this.imageCacheStatsTracker.onDiskCacheMiss(key);
                return null;
            }
            FLog.v(TAG, "Found entry in disk cache for %s", key.getUriString());
            this.imageCacheStatsTracker.onDiskCacheHit(key);
            InputStream is = diskCacheResource.openStream();
            try {
                PooledByteBuffer byteBuffer = this.pooledByteBufferFactory.newByteBuffer(is, (int) diskCacheResource.size());
                is.close();
                FLog.v(TAG, "Successful read from disk cache for %s", key.getUriString());
                return byteBuffer;
            } catch (Throwable th) {
                is.close();
                throw th;
            }
        } catch (IOException ioe) {
            FLog.w(TAG, ioe, "Exception reading from cache for %s", key.getUriString());
            this.imageCacheStatsTracker.onDiskCacheGetFail(key);
            throw ioe;
        }
    }

    private final void writeToDiskCache(CacheKey key, final EncodedImage encodedImage) {
        FLog.v(TAG, "About to write to disk-cache for key %s", key.getUriString());
        try {
            this.fileCache.insert(key, new WriterCallback() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache$$ExternalSyntheticLambda6
                @Override // com.facebook.cache.common.WriterCallback
                public final void write(OutputStream outputStream) throws IOException {
                    BufferedDiskCache.writeToDiskCache$lambda$9(encodedImage, this, outputStream);
                }
            });
            this.imageCacheStatsTracker.onDiskCachePut(key);
            FLog.v(TAG, "Successful disk-cache write for key %s", key.getUriString());
        } catch (IOException ioe) {
            FLog.w(TAG, ioe, "Failed to write to disk-cache for key %s", key.getUriString());
        }
    }

    static final void writeToDiskCache$lambda$9(EncodedImage $encodedImage, BufferedDiskCache this$0, OutputStream os) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNull($encodedImage);
        InputStream inputStream = $encodedImage.getInputStream();
        if (inputStream == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        this$0.pooledByteStreams.copy(inputStream, os);
    }
}
