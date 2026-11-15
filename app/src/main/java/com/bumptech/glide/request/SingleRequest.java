package com.bumptech.glide.request;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes11.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final String GLIDE_TAG = "Glide";
    private final TransitionFactory<? super R> animationFactory;
    private final Executor callbackExecutor;
    private final Context context;
    private int cookie;
    private volatile Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private final GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    private final Object model;
    private final int overrideHeight;
    private final int overrideWidth;
    private Drawable placeholderDrawable;
    private final Priority priority;
    private final RequestCoordinator requestCoordinator;
    private final List<RequestListener<R>> requestListeners;
    private final Object requestLock;
    private final BaseRequestOptions<?> requestOptions;
    private RuntimeException requestOrigin;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    private final String tag;
    private final Target<R> target;
    private final RequestListener<R> targetListener;
    private final Class<R> transcodeClass;
    private int width;
    private static final String TAG = "GlideRequest";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object requestLock, Object model, Class<R> transcodeClass, BaseRequestOptions<?> requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, List<RequestListener<R>> requestListeners, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory, Executor callbackExecutor) {
        return new SingleRequest<>(context, glideContext, requestLock, model, transcodeClass, requestOptions, overrideWidth, overrideHeight, priority, target, targetListener, requestListeners, requestCoordinator, engine, animationFactory, callbackExecutor);
    }

    private SingleRequest(Context context, GlideContext glideContext, Object requestLock, Object model, Class<R> transcodeClass, BaseRequestOptions<?> requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, List<RequestListener<R>> requestListeners, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory, Executor callbackExecutor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
        this.requestLock = requestLock;
        this.context = context;
        this.glideContext = glideContext;
        this.model = model;
        this.transcodeClass = transcodeClass;
        this.requestOptions = requestOptions;
        this.overrideWidth = overrideWidth;
        this.overrideHeight = overrideHeight;
        this.priority = priority;
        this.target = target;
        this.targetListener = targetListener;
        this.requestListeners = requestListeners;
        this.requestCoordinator = requestCoordinator;
        this.engine = engine;
        this.animationFactory = animationFactory;
        this.callbackExecutor = callbackExecutor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext.getExperiments().isEnabled(GlideBuilder.LogRequestOrigins.class)) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.requestLock) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            this.startTime = LogTime.getLogTime();
            if (this.model == null) {
                if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                    this.width = this.overrideWidth;
                    this.height = this.overrideHeight;
                }
                int logLevel = getFallbackDrawable() == null ? 5 : 3;
                onLoadFailed(new GlideException("Received null model"), logLevel);
                return;
            }
            if (this.status == Status.RUNNING) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (this.status == Status.COMPLETE) {
                onResourceReady(this.resource, DataSource.MEMORY_CACHE, false);
                return;
            }
            experimentalNotifyRequestStarted(this.model);
            this.cookie = GlideTrace.beginSectionAsync(TAG);
            this.status = Status.WAITING_FOR_SIZE;
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                onSizeReady(this.overrideWidth, this.overrideHeight);
            } else {
                this.target.getSize(this);
            }
            if ((this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE) && canNotifyStatusChanged()) {
                this.target.onLoadStarted(getPlaceholderDrawable());
            }
            if (IS_VERBOSE_LOGGABLE) {
                logV("finished run method in " + LogTime.getElapsedMillis(this.startTime));
            }
        }
    }

    private void experimentalNotifyRequestStarted(Object model) {
        if (this.requestListeners == null) {
            return;
        }
        for (RequestListener<R> requestListener : this.requestListeners) {
            if (requestListener instanceof ExperimentalRequestListener) {
                ((ExperimentalRequestListener) requestListener).onRequestStarted(model);
            }
        }
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        if (this.loadStatus != null) {
            this.loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        Resource<R> toRelease = null;
        synchronized (this.requestLock) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            if (this.status == Status.CLEARED) {
                return;
            }
            cancel();
            if (this.resource != null) {
                toRelease = this.resource;
                this.resource = null;
            }
            if (canNotifyCleared()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            GlideTrace.endSectionAsync(TAG, this.cookie);
            this.status = Status.CLEARED;
            if (toRelease != null) {
                this.engine.release(toRelease);
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.requestLock) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(int resourceId) {
        Resources.Theme theme = this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme();
        return DrawableDecoderCompat.getDrawable(this.context, resourceId, theme);
    }

    private void setErrorPlaceholder() {
        if (!canNotifyStatusChanged()) {
            return;
        }
        Drawable error = null;
        if (this.model == null) {
            error = getFallbackDrawable();
        }
        if (error == null) {
            error = getErrorDrawable();
        }
        if (error == null) {
            error = getPlaceholderDrawable();
        }
        this.target.onLoadFailed(error);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.bumptech.glide.request.ResourceCallback, com.bumptech.glide.request.SingleRequest] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.bumptech.glide.load.engine.Engine] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int width, int height) throws Throwable {
        ?? r1 = this;
        r1.stateVerifier.throwIfRecycled();
        Object obj = r1.requestLock;
        synchronized (obj) {
            try {
                try {
                    if (IS_VERBOSE_LOGGABLE) {
                        r1.logV("Got onSizeReady in " + LogTime.getElapsedMillis(r1.startTime));
                    }
                    if (r1.status == Status.WAITING_FOR_SIZE) {
                        r1.status = Status.RUNNING;
                        float sizeMultiplier = r1.requestOptions.getSizeMultiplier();
                        r1.width = maybeApplySizeMultiplier(width, sizeMultiplier);
                        r1.height = maybeApplySizeMultiplier(height, sizeMultiplier);
                        if (IS_VERBOSE_LOGGABLE) {
                            r1.logV("finished setup for calling load in " + LogTime.getElapsedMillis(r1.startTime));
                        }
                        try {
                            ?? r2 = r1.engine;
                            GlideContext glideContext = r1.glideContext;
                            Object obj2 = r1.model;
                            Key signature = r1.requestOptions.getSignature();
                            try {
                                int i = r1.width;
                                try {
                                    int i2 = r1.height;
                                    Class<?> resourceClass = r1.requestOptions.getResourceClass();
                                    try {
                                        Class<R> cls = r1.transcodeClass;
                                        try {
                                            Priority priority = r1.priority;
                                            DiskCacheStrategy diskCacheStrategy = r1.requestOptions.getDiskCacheStrategy();
                                            Map<Class<?>, Transformation<?>> transformations = r1.requestOptions.getTransformations();
                                            boolean zIsTransformationRequired = r1.requestOptions.isTransformationRequired();
                                            boolean zIsScaleOnlyOrNoTransform = r1.requestOptions.isScaleOnlyOrNoTransform();
                                            Options options = r1.requestOptions.getOptions();
                                            boolean zIsMemoryCacheable = r1.requestOptions.isMemoryCacheable();
                                            boolean useUnlimitedSourceGeneratorsPool = r1.requestOptions.getUseUnlimitedSourceGeneratorsPool();
                                            boolean useAnimationPool = r1.requestOptions.getUseAnimationPool();
                                            boolean onlyRetrieveFromCache = r1.requestOptions.getOnlyRetrieveFromCache();
                                            Executor executor = r1.callbackExecutor;
                                            r1 = obj;
                                            try {
                                                r1.loadStatus = r2.load(glideContext, obj2, signature, i, i2, resourceClass, cls, priority, diskCacheStrategy, transformations, zIsTransformationRequired, zIsScaleOnlyOrNoTransform, options, zIsMemoryCacheable, useUnlimitedSourceGeneratorsPool, useAnimationPool, onlyRetrieveFromCache, r1, executor);
                                                if (r1.status != Status.RUNNING) {
                                                    r1.loadStatus = null;
                                                }
                                                if (IS_VERBOSE_LOGGABLE) {
                                                    r1.logV("finished onSizeReady in " + LogTime.getElapsedMillis(r1.startTime));
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            r1 = obj;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        r1 = obj;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    r1 = obj;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                r1 = obj;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            r1 = obj;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    r1 = obj;
                }
            } catch (Throwable th8) {
                th = th8;
            }
        }
    }

    private static int maybeApplySizeMultiplier(int size, float sizeMultiplier) {
        return size == Integer.MIN_VALUE ? size : Math.round(size * sizeMultiplier);
    }

    private boolean canSetResource() {
        return this.requestCoordinator == null || this.requestCoordinator.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        return this.requestCoordinator == null || !this.requestCoordinator.getRoot().isAnyResourceSet();
    }

    private void notifyRequestCoordinatorLoadSucceeded() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestSuccess(this);
        }
    }

    private void notifyRequestCoordinatorLoadFailed() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestFailed(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource, boolean isLoadedFromAlternateCacheKey) {
        this.stateVerifier.throwIfRecycled();
        try {
            synchronized (this.requestLock) {
                this.loadStatus = null;
                if (resource == null) {
                    GlideException exception = new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null.");
                    onLoadFailed(exception);
                    if (0 != 0) {
                        this.engine.release(null);
                        return;
                    }
                    return;
                }
                Object received = resource.get();
                if (received == null || !this.transcodeClass.isAssignableFrom(received.getClass())) {
                    this.resource = null;
                    GlideException exception2 = new GlideException("Expected to receive an object of " + this.transcodeClass + " but instead got " + (received != null ? received.getClass() : "") + "{" + received + "} inside Resource{" + resource + "}." + (received != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data."));
                    onLoadFailed(exception2);
                    if (resource != null) {
                        this.engine.release(resource);
                        return;
                    }
                    return;
                }
                if (canSetResource()) {
                    onResourceReady(resource, received, dataSource, isLoadedFromAlternateCacheKey);
                    if (0 != 0) {
                        this.engine.release(null);
                        return;
                    }
                    return;
                }
                this.resource = null;
                this.status = Status.COMPLETE;
                GlideTrace.endSectionAsync(TAG, this.cookie);
                if (resource != null) {
                    this.engine.release(resource);
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                this.engine.release(null);
            }
            throw th;
        }
    }

    private void onResourceReady(Resource<R> resource, R result, DataSource dataSource, boolean isAlternateCacheKey) throws Throwable {
        Throwable th;
        R result2;
        DataSource dataSource2;
        boolean anyListenerHandledUpdatingTarget;
        boolean isAlternateCacheKey2;
        boolean isFirstResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource;
        if (this.glideContext.getLogLevel() <= 3) {
            Log.d(GLIDE_TAG, "Finished loading " + result.getClass().getSimpleName() + " from " + dataSource + " for " + this.model + " with size [" + this.width + "x" + this.height + "] in " + LogTime.getElapsedMillis(this.startTime) + " ms");
        }
        notifyRequestCoordinatorLoadSucceeded();
        boolean z = true;
        this.isCallingCallbacks = true;
        try {
            if (this.requestListeners != null) {
                Iterator<RequestListener<R>> it = this.requestListeners.iterator();
                anyListenerHandledUpdatingTarget = false;
                while (anyListenerHandledUpdatingTarget) {
                    RequestListener<R> listener = it.next();
                    R result3 = result;
                    DataSource dataSource3 = dataSource;
                    try {
                        boolean anyListenerHandledUpdatingTarget2 = listener.onResourceReady(result3, this.model, this.target, dataSource3, isFirstResource) | anyListenerHandledUpdatingTarget;
                        if (listener instanceof ExperimentalRequestListener) {
                            ExperimentalRequestListener<R> experimentalRequestListener = (ExperimentalRequestListener) listener;
                            boolean isAlternateCacheKey3 = isAlternateCacheKey;
                            try {
                                isAlternateCacheKey2 = isAlternateCacheKey3;
                                anyListenerHandledUpdatingTarget2 |= experimentalRequestListener.onResourceReady(result3, this.model, this.target, dataSource3, isFirstResource, isAlternateCacheKey3);
                            } catch (Throwable th2) {
                                th = th2;
                                this.isCallingCallbacks = false;
                                throw th;
                            }
                        } else {
                            isAlternateCacheKey2 = isAlternateCacheKey;
                        }
                        anyListenerHandledUpdatingTarget = anyListenerHandledUpdatingTarget2;
                        result = result3;
                        dataSource = dataSource3;
                        isAlternateCacheKey = isAlternateCacheKey2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                result2 = result;
                dataSource2 = dataSource;
            } else {
                result2 = result;
                dataSource2 = dataSource;
                anyListenerHandledUpdatingTarget = false;
            }
            try {
                if (this.targetListener == null || !this.targetListener.onResourceReady(result2, this.model, this.target, dataSource2, isFirstResource)) {
                    z = false;
                }
                if (!(anyListenerHandledUpdatingTarget | z)) {
                    Transition<? super R> animation = this.animationFactory.build(dataSource2, isFirstResource);
                    this.target.onResourceReady(result2, animation);
                }
                this.isCallingCallbacks = false;
                GlideTrace.endSectionAsync(TAG, this.cookie);
            } catch (Throwable th4) {
                th = th4;
                this.isCallingCallbacks = false;
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException e) {
        onLoadFailed(e, 5);
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    private void onLoadFailed(GlideException e, int maxLogLevel) {
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            e.setOrigin(this.requestOrigin);
            int logLevel = this.glideContext.getLogLevel();
            if (logLevel <= maxLogLevel) {
                Log.w(GLIDE_TAG, "Load failed for [" + this.model + "] with dimensions [" + this.width + "x" + this.height + "]", e);
                if (logLevel <= 4) {
                    e.logRootCauses(GLIDE_TAG);
                }
            }
            this.loadStatus = null;
            this.status = Status.FAILED;
            notifyRequestCoordinatorLoadFailed();
            boolean z = true;
            this.isCallingCallbacks = true;
            boolean anyListenerHandledUpdatingTarget = false;
            try {
                if (this.requestListeners != null) {
                    for (RequestListener<R> listener : this.requestListeners) {
                        anyListenerHandledUpdatingTarget |= listener.onLoadFailed(e, this.model, this.target, isFirstReadyResource());
                    }
                }
                if (this.targetListener == null || !this.targetListener.onLoadFailed(e, this.model, this.target, isFirstReadyResource())) {
                    z = false;
                }
                if (!(anyListenerHandledUpdatingTarget | z)) {
                    setErrorPlaceholder();
                }
                this.isCallingCallbacks = false;
                GlideTrace.endSectionAsync(TAG, this.cookie);
            } catch (Throwable th) {
                this.isCallingCallbacks = false;
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request o) {
        int localOverrideWidth;
        int localOverrideHeight;
        Object localModel;
        Class<R> cls;
        BaseRequestOptions<?> localRequestOptions;
        Priority localPriority;
        int localListenerCount;
        int otherLocalOverrideWidth;
        int otherLocalOverrideHeight;
        Object otherLocalModel;
        Class<R> cls2;
        BaseRequestOptions<?> otherLocalRequestOptions;
        Priority otherLocalPriority;
        int otherLocalListenerCount;
        if (!(o instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            localOverrideWidth = this.overrideWidth;
            localOverrideHeight = this.overrideHeight;
            localModel = this.model;
            cls = this.transcodeClass;
            localRequestOptions = this.requestOptions;
            localPriority = this.priority;
            localListenerCount = this.requestListeners != null ? this.requestListeners.size() : 0;
        }
        SingleRequest<?> other = (SingleRequest) o;
        synchronized (other.requestLock) {
            otherLocalOverrideWidth = other.overrideWidth;
            otherLocalOverrideHeight = other.overrideHeight;
            otherLocalModel = other.model;
            cls2 = other.transcodeClass;
            otherLocalRequestOptions = other.requestOptions;
            otherLocalPriority = other.priority;
            otherLocalListenerCount = other.requestListeners != null ? other.requestListeners.size() : 0;
        }
        return localOverrideWidth == otherLocalOverrideWidth && localOverrideHeight == otherLocalOverrideHeight && Util.bothModelsNullEquivalentOrEquals(localModel, otherLocalModel) && cls.equals(cls2) && Util.bothBaseRequestOptionsNullEquivalentOrEquals(localRequestOptions, otherLocalRequestOptions) && localPriority == otherLocalPriority && localListenerCount == otherLocalListenerCount;
    }

    private void logV(String message) {
        Log.v(TAG, message + " this: " + this.tag);
    }

    public String toString() {
        Object localModel;
        Class<R> cls;
        synchronized (this.requestLock) {
            localModel = this.model;
            cls = this.transcodeClass;
        }
        return super.toString() + "[model=" + localModel + ", transcodeClass=" + cls + "]";
    }
}
