package coil.intercept;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.compose.material.MenuKt;
import androidx.media3.extractor.ts.TsExtractor;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.decode.ImageSource;
import coil.fetch.DrawableResult;
import coil.fetch.FetchResult;
import coil.fetch.Fetcher;
import coil.fetch.SourceResult;
import coil.memory.MemoryCache;
import coil.memory.MemoryCacheService;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.Options;
import coil.request.RequestService;
import coil.request.SuccessResult;
import coil.size.Scale;
import coil.size.Size;
import coil.transform.Transformation;
import coil.util.Bitmaps;
import coil.util.DrawableUtils;
import coil.util.Logger;
import coil.util.SystemCallbacks;
import coil.util.Utils;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: EngineInterceptor.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 42\u00020\u0001:\u000234B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0012J.\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010\u001dJ6\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010#J>\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010'J0\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0081@¢\u0006\u0004\b*\u0010+J&\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\"\u001a\u00020\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcoil/intercept/EngineInterceptor;", "Lcoil/intercept/Interceptor;", "imageLoader", "Lcoil/ImageLoader;", "systemCallbacks", "Lcoil/util/SystemCallbacks;", "requestService", "Lcoil/request/RequestService;", "logger", "Lcoil/util/Logger;", "<init>", "(Lcoil/ImageLoader;Lcoil/util/SystemCallbacks;Lcoil/request/RequestService;Lcoil/util/Logger;)V", "memoryCacheService", "Lcoil/memory/MemoryCacheService;", "intercept", "Lcoil/request/ImageResult;", "chain", "Lcoil/intercept/Interceptor$Chain;", "(Lcoil/intercept/Interceptor$Chain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "request", "Lcoil/request/ImageRequest;", "mappedData", "", "_options", "Lcoil/request/Options;", "eventListener", "Lcoil/EventListener;", "(Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch", "Lcoil/fetch/FetchResult;", "components", "Lcoil/ComponentRegistry;", "options", "(Lcoil/ComponentRegistry;Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decode", "fetchResult", "Lcoil/fetch/SourceResult;", "(Lcoil/fetch/SourceResult;Lcoil/ComponentRegistry;Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transform", "result", "transform$coil_base_release", "(Lcoil/intercept/EngineInterceptor$ExecuteResult;Lcoil/request/ImageRequest;Lcoil/request/Options;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertDrawableToBitmap", "Landroid/graphics/Bitmap;", "drawable", "Landroid/graphics/drawable/Drawable;", "transformations", "", "Lcoil/transform/Transformation;", "ExecuteResult", "Companion", "coil-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EngineInterceptor implements Interceptor {
    private static final String TAG = "EngineInterceptor";
    private final ImageLoader imageLoader;
    private final Logger logger;
    private final MemoryCacheService memoryCacheService;
    private final RequestService requestService;
    private final SystemCallbacks systemCallbacks;

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$decode$1, reason: invalid class name */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {203}, m = "decode", n = {"this", "fetchResult", "components", "request", "mappedData", "options", "eventListener", "decoder", "searchIndex"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.decode(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {WebSocketProtocol.PAYLOAD_SHORT, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 148}, m = "execute", n = {"this", "request", "mappedData", "eventListener", "options", "components", "fetchResult", "this", "request", "eventListener", "options", "fetchResult"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C04241 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C04241(Continuation<? super C04241> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.execute(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$fetch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {169}, m = "fetch", n = {"this", "components", "request", "mappedData", "options", "eventListener", "fetcher", "searchIndex"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"})
    static final class C04251 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C04251(Continuation<? super C04251> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.fetch(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$intercept$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", i = {0, 0}, l = {MenuKt.OutTransitionDuration}, m = "intercept", n = {"this", "chain"}, s = {"L$0", "L$1"})
    static final class C04261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04261(Continuation<? super C04261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.intercept(null, this);
        }
    }

    public EngineInterceptor(ImageLoader imageLoader, SystemCallbacks systemCallbacks, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.systemCallbacks = systemCallbacks;
        this.requestService = requestService;
        this.logger = logger;
        this.memoryCacheService = new MemoryCacheService(this.imageLoader, this.requestService, this.logger);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // coil.intercept.Interceptor
    public Object intercept(Interceptor.Chain chain, Continuation<? super ImageResult> continuation) throws Throwable {
        C04261 c04261;
        EngineInterceptor engineInterceptor;
        Interceptor.Chain chain2;
        if (continuation instanceof C04261) {
            c04261 = (C04261) continuation;
            if ((c04261.label & Integer.MIN_VALUE) != 0) {
                c04261.label -= Integer.MIN_VALUE;
            } else {
                c04261 = new C04261(continuation);
            }
        } else {
            c04261 = new C04261(continuation);
        }
        C04261 c04262 = c04261;
        Object $result = c04262.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04262.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    ImageRequest request = chain.getRequest();
                    Object data = request.getData();
                    Size size = chain.getSize();
                    EventListener eventListener = Utils.getEventListener(chain);
                    Options options = this.requestService.options(request, size);
                    Scale scale = options.getScale();
                    eventListener.mapStart(request, data);
                    Object mappedData = this.imageLoader.getComponents().map(data, options);
                    eventListener.mapEnd(request, mappedData);
                    MemoryCache.Key cacheKey = this.memoryCacheService.newCacheKey(request, mappedData, options, eventListener);
                    MemoryCache.Value cacheValue = cacheKey != null ? this.memoryCacheService.getCacheValue(request, cacheKey, size, scale) : null;
                    if (cacheValue != null) {
                        return this.memoryCacheService.newResult(chain, request, cacheKey, cacheValue);
                    }
                    CoroutineDispatcher fetcherDispatcher = request.getFetcherDispatcher();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(request, mappedData, options, eventListener, cacheKey, chain, null);
                    c04262.L$0 = this;
                    c04262.L$1 = chain;
                    c04262.label = 1;
                    Object objWithContext = BuildersKt.withContext(fetcherDispatcher, anonymousClass2, c04262);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objWithContext;
                } catch (Throwable th) {
                    throwable = th;
                    engineInterceptor = this;
                    chain2 = chain;
                }
                break;
            case 1:
                chain2 = (Interceptor.Chain) c04262.L$1;
                engineInterceptor = (EngineInterceptor) c04262.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    return $result;
                } catch (Throwable th2) {
                    throwable = th2;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (throwable instanceof CancellationException) {
            throw throwable;
        }
        return engineInterceptor.requestService.errorResult(chain2.getRequest(), throwable);
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$intercept$2, reason: invalid class name */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcoil/request/SuccessResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "coil.intercept.EngineInterceptor$intercept$2", f = "EngineInterceptor.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SuccessResult>, Object> {
        final /* synthetic */ MemoryCache.Key $cacheKey;
        final /* synthetic */ Interceptor.Chain $chain;
        final /* synthetic */ EventListener $eventListener;
        final /* synthetic */ Object $mappedData;
        final /* synthetic */ Options $options;
        final /* synthetic */ ImageRequest $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ImageRequest imageRequest, Object obj, Options options, EventListener eventListener, MemoryCache.Key key, Interceptor.Chain chain, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$request = imageRequest;
            this.$mappedData = obj;
            this.$options = options;
            this.$eventListener = eventListener;
            this.$cacheKey = key;
            this.$chain = chain;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EngineInterceptor.this.new AnonymousClass2(this.$request, this.$mappedData, this.$options, this.$eventListener, this.$cacheKey, this.$chain, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SuccessResult> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass2 = this;
                    anonymousClass2.label = 1;
                    Object objExecute = EngineInterceptor.this.execute(anonymousClass2.$request, anonymousClass2.$mappedData, anonymousClass2.$options, anonymousClass2.$eventListener, anonymousClass2);
                    if (objExecute == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objExecute;
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    anonymousClass2 = this;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ExecuteResult result = (ExecuteResult) $result;
            EngineInterceptor.this.systemCallbacks.registerMemoryPressureCallbacks();
            boolean isCached = EngineInterceptor.this.memoryCacheService.setCacheValue(anonymousClass2.$cacheKey, anonymousClass2.$request, result);
            Drawable drawable = result.getDrawable();
            ImageRequest imageRequest = anonymousClass2.$request;
            DataSource dataSource = result.getDataSource();
            MemoryCache.Key key = anonymousClass2.$cacheKey;
            if (!(isCached)) {
                key = null;
            }
            return new SuccessResult(drawable, imageRequest, dataSource, key, result.getDiskCacheKey(), result.getIsSampled(), Utils.isPlaceholderCached(anonymousClass2.$chain));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:101:0x0246  */
    /* JADX WARN: Code duplicated, block: B:102:0x024a  */
    /* JADX WARN: Code duplicated, block: B:104:0x024d  */
    /* JADX WARN: Code duplicated, block: B:29:0x00d5 A[Catch: all -> 0x023c, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x023c, blocks: (B:24:0x00bc, B:42:0x0112, B:29:0x00d5, B:33:0x00f0, B:41:0x010c), top: B:119:0x00bc }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00e8 A[Catch: all -> 0x0104, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0104, blocks: (B:26:0x00ce, B:32:0x00e8, B:36:0x00f9), top: B:109:0x00ce }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00f9 A[Catch: all -> 0x0104, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0104, blocks: (B:26:0x00ce, B:32:0x00e8, B:36:0x00f9), top: B:109:0x00ce }] */
    /* JADX WARN: Code duplicated, block: B:40:0x010a  */
    /* JADX WARN: Code duplicated, block: B:46:0x0137 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x0138  */
    /* JADX WARN: Code duplicated, block: B:50:0x0150 A[Catch: all -> 0x0234, TRY_LEAVE, TryCatch #3 {all -> 0x0234, blocks: (B:48:0x0146, B:50:0x0150), top: B:113:0x0146 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0187 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:60:0x019a A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:57:0x0188, B:54:0x0169, B:60:0x019a, B:62:0x01a7), top: B:108:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x01a7 A[Catch: all -> 0x0231, TRY_LEAVE, TryCatch #0 {all -> 0x0231, blocks: (B:57:0x0188, B:54:0x0169, B:60:0x019a, B:62:0x01a7), top: B:108:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:69:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:71:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:76:0x020b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:79:0x0217  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:80:0x021b  */
    /* JADX WARN: Code duplicated, block: B:82:0x021e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0228  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, coil.ComponentRegistry] */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v20, types: [kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, coil.ComponentRegistry] */
    /* JADX WARN: Type inference failed for: r7v6, types: [T, coil.request.Options] */
    public final Object execute(ImageRequest imageRequest, Object obj, Options options, EventListener eventListener, Continuation<? super ExecuteResult> continuation) throws Throwable {
        C04241 c04241;
        Object obj2;
        T t;
        SourceResult sourceResult;
        ImageSource source;
        SourceResult sourceResult2;
        C04241 c04242;
        Object objFetch;
        EngineInterceptor engineInterceptor;
        ImageRequest imageRequest2;
        Object obj3;
        EventListener eventListener2;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        Ref.ObjectRef objectRef3;
        Ref.ObjectRef objectRef4;
        ComponentRegistry.Builder builderNewBuilder;
        Pair<Fetcher.Factory<?>, Class<?>> fetcherFactory;
        Decoder.Factory decoderFactory;
        T t2;
        FetchResult fetchResult;
        EngineInterceptor engineInterceptor2;
        ImageRequest imageRequest3;
        Ref.ObjectRef objectRef5;
        EventListener eventListener3;
        ExecuteResult executeResult;
        Ref.ObjectRef objectRef6;
        EngineInterceptor engineInterceptor3;
        EventListener eventListener4;
        ImageRequest imageRequest4;
        Ref.ObjectRef objectRef7;
        EngineInterceptor engineInterceptor4;
        EventListener eventListener5;
        Object objWithContext;
        Ref.ObjectRef objectRef8;
        T t3;
        SourceResult sourceResult3;
        BitmapDrawable bitmapDrawable;
        Object objTransform$coil_base_release;
        ImageSource source2;
        ImageRequest imageRequest5;
        Ref.ObjectRef objectRef9;
        ?? r3;
        Drawable drawable;
        BitmapDrawable bitmapDrawable2;
        Bitmap bitmap;
        if (continuation instanceof C04241) {
            c04241 = (C04241) continuation;
            if ((c04241.label & Integer.MIN_VALUE) != 0) {
                c04241.label -= Integer.MIN_VALUE;
            } else {
                c04241 = new C04241(continuation);
            }
        } else {
            c04241 = new C04241(continuation);
        }
        C04241 c04243 = c04241;
        Object obj4 = c04243.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Ref.ObjectRef objectRef10 = c04243.label;
        try {
            switch (objectRef10) {
                case 0:
                    ResultKt.throwOnFailure(obj4);
                    Ref.ObjectRef objectRef11 = new Ref.ObjectRef();
                    objectRef11.element = options;
                    Ref.ObjectRef objectRef12 = new Ref.ObjectRef();
                    objectRef12.element = this.imageLoader.getComponents();
                    Ref.ObjectRef objectRef13 = new Ref.ObjectRef();
                    try {
                        objectRef11.element = this.requestService.updateOptionsOnWorkerThread((Options) objectRef11.element);
                        if (imageRequest.getFetcherFactory() != null) {
                            builderNewBuilder = ((ComponentRegistry) objectRef12.element).newBuilder();
                            fetcherFactory = imageRequest.getFetcherFactory();
                            if (fetcherFactory != null) {
                                builderNewBuilder.getFetcherFactories$coil_base_release().add(0, fetcherFactory);
                            }
                            decoderFactory = imageRequest.getDecoderFactory();
                            if (decoderFactory != null) {
                                builderNewBuilder.getDecoderFactories$coil_base_release().add(0, decoderFactory);
                                break;
                            }
                            objectRef12.element = builderNewBuilder.build();
                            ComponentRegistry componentRegistry = (ComponentRegistry) objectRef12.element;
                            Options options2 = (Options) objectRef11.element;
                            c04243.L$0 = this;
                            c04243.L$1 = imageRequest;
                            c04243.L$2 = obj;
                            c04243.L$3 = eventListener;
                            c04243.L$4 = objectRef11;
                            c04243.L$5 = objectRef12;
                            c04243.L$6 = objectRef13;
                            c04243.L$7 = objectRef13;
                            c04243.label = 1;
                            c04242 = c04243;
                            objFetch = fetch(componentRegistry, imageRequest, obj, options2, eventListener, c04242);
                            if (objFetch == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            engineInterceptor = this;
                            imageRequest2 = imageRequest;
                            obj3 = obj;
                            eventListener2 = eventListener;
                            objectRef = objectRef11;
                            objectRef2 = objectRef12;
                            objectRef3 = objectRef13;
                            objectRef4 = objectRef3;
                            t2 = objFetch;
                            obj2 = objectRef11;
                            objectRef3.element = t2;
                            fetchResult = (FetchResult) objectRef4.element;
                            if (!(fetchResult instanceof SourceResult)) {
                                CoroutineDispatcher decoderDispatcher = imageRequest2.getDecoderDispatcher();
                                objectRef7 = objectRef4;
                                engineInterceptor4 = engineInterceptor;
                                Ref.ObjectRef objectRef14 = objectRef7;
                                ImageRequest imageRequest6 = imageRequest2;
                                Ref.ObjectRef objectRef15 = objectRef;
                                eventListener5 = eventListener2;
                                EngineInterceptor$execute$executeResult$1 engineInterceptor$execute$executeResult$1 = new EngineInterceptor$execute$executeResult$1(engineInterceptor, objectRef7, objectRef2, imageRequest2, obj3, objectRef, eventListener2, null);
                                c04242.L$0 = engineInterceptor4;
                                c04242.L$1 = imageRequest6;
                                c04242.L$2 = eventListener5;
                                c04242.L$3 = objectRef15;
                                c04242.L$4 = objectRef14;
                                c04242.L$5 = null;
                                c04242.L$6 = null;
                                c04242.L$7 = null;
                                c04242.label = 2;
                                objWithContext = BuildersKt.withContext(decoderDispatcher, engineInterceptor$execute$executeResult$1, c04242);
                                r3 = objectRef14;
                                objectRef9 = objectRef15;
                                imageRequest5 = imageRequest6;
                                if (objWithContext == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Ref.ObjectRef objectRef16 = objectRef9;
                                executeResult = (ExecuteResult) objWithContext;
                                objectRef6 = objectRef16;
                                engineInterceptor3 = engineInterceptor4;
                                eventListener4 = eventListener5;
                                imageRequest4 = imageRequest5;
                                objectRef8 = r3;
                                t3 = objectRef8.element;
                                if (t3 instanceof SourceResult) {
                                    sourceResult3 = (SourceResult) t3;
                                } else {
                                    sourceResult3 = null;
                                }
                                if (sourceResult3 != null) {
                                    Utils.closeQuietly(source2);
                                }
                                Options options3 = (Options) objectRef6.element;
                                bitmapDrawable = null;
                                c04242.L$0 = null;
                                c04242.L$1 = null;
                                c04242.L$2 = null;
                                c04242.L$3 = null;
                                c04242.L$4 = null;
                                c04242.L$5 = null;
                                c04242.L$6 = null;
                                c04242.L$7 = null;
                                c04242.label = 3;
                                objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options3, eventListener4, c04242);
                                if (objTransform$coil_base_release == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                objectRef10 = objectRef4;
                                engineInterceptor2 = engineInterceptor;
                                imageRequest3 = imageRequest2;
                                objectRef5 = objectRef;
                                eventListener3 = eventListener2;
                                if (!(fetchResult instanceof DrawableResult)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                executeResult = new ExecuteResult(((DrawableResult) objectRef10.element).getDrawable(), ((DrawableResult) objectRef10.element).getIsSampled(), ((DrawableResult) objectRef10.element).getDataSource(), null);
                                objectRef6 = objectRef5;
                                engineInterceptor3 = engineInterceptor2;
                                eventListener4 = eventListener3;
                                imageRequest4 = imageRequest3;
                                objectRef8 = objectRef10;
                                t3 = objectRef8.element;
                                if (t3 instanceof SourceResult) {
                                    sourceResult3 = (SourceResult) t3;
                                } else {
                                    sourceResult3 = null;
                                }
                                if (sourceResult3 != null) {
                                    Utils.closeQuietly(source2);
                                }
                                Options options4 = (Options) objectRef6.element;
                                bitmapDrawable = null;
                                c04242.L$0 = null;
                                c04242.L$1 = null;
                                c04242.L$2 = null;
                                c04242.L$3 = null;
                                c04242.L$4 = null;
                                c04242.L$5 = null;
                                c04242.L$6 = null;
                                c04242.L$7 = null;
                                c04242.label = 3;
                                objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options4, eventListener4, c04242);
                                if (objTransform$coil_base_release == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            t = objectRef10.element;
                            if (t instanceof SourceResult) {
                                sourceResult2 = (SourceResult) t;
                            } else {
                                sourceResult = obj2;
                            }
                            if (sourceResult != 0) {
                                sourceResult = sourceResult2;
                                Utils.closeQuietly(source);
                            }
                            sourceResult = sourceResult2;
                            sourceResult = sourceResult2;
                            throw th;
                        }
                        try {
                            if (imageRequest.getDecoderFactory() != null) {
                                builderNewBuilder = ((ComponentRegistry) objectRef12.element).newBuilder();
                                fetcherFactory = imageRequest.getFetcherFactory();
                                if (fetcherFactory != null) {
                                    builderNewBuilder.getFetcherFactories$coil_base_release().add(0, fetcherFactory);
                                }
                                decoderFactory = imageRequest.getDecoderFactory();
                                if (decoderFactory != null) {
                                    builderNewBuilder.getDecoderFactories$coil_base_release().add(0, decoderFactory);
                                    break;
                                }
                                objectRef12.element = builderNewBuilder.build();
                            }
                            ComponentRegistry componentRegistry2 = (ComponentRegistry) objectRef12.element;
                            Options options5 = (Options) objectRef11.element;
                            c04243.L$0 = this;
                            c04243.L$1 = imageRequest;
                            c04243.L$2 = obj;
                            c04243.L$3 = eventListener;
                            c04243.L$4 = objectRef11;
                            c04243.L$5 = objectRef12;
                            c04243.L$6 = objectRef13;
                            c04243.L$7 = objectRef13;
                            c04243.label = 1;
                            c04242 = c04243;
                            try {
                                objFetch = fetch(componentRegistry2, imageRequest, obj, options5, eventListener, c04242);
                                if (objFetch == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                engineInterceptor = this;
                                imageRequest2 = imageRequest;
                                obj3 = obj;
                                eventListener2 = eventListener;
                                objectRef = objectRef11;
                                objectRef2 = objectRef12;
                                objectRef3 = objectRef13;
                                objectRef4 = objectRef3;
                                t2 = objFetch;
                                obj2 = objectRef11;
                                try {
                                    objectRef3.element = t2;
                                    fetchResult = (FetchResult) objectRef4.element;
                                    if (!(fetchResult instanceof SourceResult)) {
                                        CoroutineDispatcher decoderDispatcher2 = imageRequest2.getDecoderDispatcher();
                                        objectRef7 = objectRef4;
                                        try {
                                            engineInterceptor4 = engineInterceptor;
                                            Ref.ObjectRef objectRef17 = objectRef7;
                                            ImageRequest imageRequest7 = imageRequest2;
                                            Ref.ObjectRef objectRef18 = objectRef;
                                            eventListener5 = eventListener2;
                                            EngineInterceptor$execute$executeResult$1 engineInterceptor$execute$executeResult$2 = new EngineInterceptor$execute$executeResult$1(engineInterceptor, objectRef7, objectRef2, imageRequest2, obj3, objectRef, eventListener2, null);
                                            c04242.L$0 = engineInterceptor4;
                                            c04242.L$1 = imageRequest7;
                                            c04242.L$2 = eventListener5;
                                            c04242.L$3 = objectRef18;
                                            c04242.L$4 = objectRef17;
                                            c04242.L$5 = null;
                                            c04242.L$6 = null;
                                            c04242.L$7 = null;
                                            c04242.label = 2;
                                            objWithContext = BuildersKt.withContext(decoderDispatcher2, engineInterceptor$execute$executeResult$2, c04242);
                                            r3 = objectRef17;
                                            objectRef9 = objectRef18;
                                            imageRequest5 = imageRequest7;
                                            if (objWithContext == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            Ref.ObjectRef objectRef19 = objectRef9;
                                            executeResult = (ExecuteResult) objWithContext;
                                            objectRef6 = objectRef19;
                                            engineInterceptor3 = engineInterceptor4;
                                            eventListener4 = eventListener5;
                                            imageRequest4 = imageRequest5;
                                            objectRef8 = r3;
                                            t3 = objectRef8.element;
                                            if (t3 instanceof SourceResult) {
                                                sourceResult3 = (SourceResult) t3;
                                            } else {
                                                sourceResult3 = null;
                                            }
                                            if (sourceResult3 != null && (source2 = sourceResult3.getSource()) != null) {
                                                Utils.closeQuietly(source2);
                                            }
                                            Options options6 = (Options) objectRef6.element;
                                            bitmapDrawable = null;
                                            c04242.L$0 = null;
                                            c04242.L$1 = null;
                                            c04242.L$2 = null;
                                            c04242.L$3 = null;
                                            c04242.L$4 = null;
                                            c04242.L$5 = null;
                                            c04242.L$6 = null;
                                            c04242.L$7 = null;
                                            c04242.label = 3;
                                            objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options6, eventListener4, c04242);
                                            if (objTransform$coil_base_release == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            objectRef10 = objectRef7;
                                            obj2 = null;
                                        }
                                    } else {
                                        objectRef10 = objectRef4;
                                        engineInterceptor2 = engineInterceptor;
                                        imageRequest3 = imageRequest2;
                                        objectRef5 = objectRef;
                                        eventListener3 = eventListener2;
                                        try {
                                            if (!(fetchResult instanceof DrawableResult)) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            executeResult = new ExecuteResult(((DrawableResult) objectRef10.element).getDrawable(), ((DrawableResult) objectRef10.element).getIsSampled(), ((DrawableResult) objectRef10.element).getDataSource(), null);
                                            objectRef6 = objectRef5;
                                            engineInterceptor3 = engineInterceptor2;
                                            eventListener4 = eventListener3;
                                            imageRequest4 = imageRequest3;
                                            objectRef8 = objectRef10;
                                            t3 = objectRef8.element;
                                            if (t3 instanceof SourceResult) {
                                                sourceResult3 = (SourceResult) t3;
                                            } else {
                                                sourceResult3 = null;
                                            }
                                            if (sourceResult3 != null) {
                                                Utils.closeQuietly(source2);
                                            }
                                            Options options7 = (Options) objectRef6.element;
                                            bitmapDrawable = null;
                                            c04242.L$0 = null;
                                            c04242.L$1 = null;
                                            c04242.L$2 = null;
                                            c04242.L$3 = null;
                                            c04242.L$4 = null;
                                            c04242.L$5 = null;
                                            c04242.L$6 = null;
                                            c04242.L$7 = null;
                                            c04242.label = 3;
                                            objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options7, eventListener4, c04242);
                                            if (objTransform$coil_base_release == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    objectRef10 = objectRef4;
                                    obj2 = null;
                                }
                                t = objectRef10.element;
                                if (t instanceof SourceResult) {
                                    sourceResult2 = (SourceResult) t;
                                } else {
                                    sourceResult = obj2;
                                }
                                if (sourceResult != 0 && (source = sourceResult.getSource()) != null) {
                                    sourceResult = sourceResult2;
                                    Utils.closeQuietly(source);
                                }
                                sourceResult = sourceResult2;
                                sourceResult = sourceResult2;
                                throw th;
                            } catch (Throwable th4) {
                                th = th4;
                                obj2 = null;
                                objectRef10 = objectRef13;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            objectRef10 = objectRef13;
                            obj2 = null;
                        }
                        ExecuteResult executeResult2 = (ExecuteResult) objTransform$coil_base_release;
                        drawable = executeResult2.getDrawable();
                        if (drawable instanceof BitmapDrawable) {
                            bitmapDrawable2 = (BitmapDrawable) drawable;
                        } else {
                            bitmapDrawable2 = bitmapDrawable;
                        }
                        if (bitmapDrawable2 != null && (bitmap = bitmapDrawable2.getBitmap()) != null) {
                            bitmap.prepareToDraw();
                        }
                        return executeResult2;
                    } catch (Throwable th6) {
                        th = th6;
                        obj2 = null;
                        objectRef10 = objectRef13;
                    }
                    break;
                case 1:
                    objectRef3 = (Ref.ObjectRef) c04243.L$7;
                    objectRef4 = (Ref.ObjectRef) c04243.L$6;
                    Ref.ObjectRef objectRef20 = (Ref.ObjectRef) c04243.L$5;
                    Ref.ObjectRef objectRef21 = (Ref.ObjectRef) c04243.L$4;
                    EventListener eventListener6 = (EventListener) c04243.L$3;
                    Object obj5 = c04243.L$2;
                    ImageRequest imageRequest8 = (ImageRequest) c04243.L$1;
                    EngineInterceptor engineInterceptor5 = (EngineInterceptor) c04243.L$0;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        objectRef2 = objectRef20;
                        objectRef = objectRef21;
                        eventListener2 = eventListener6;
                        obj3 = obj5;
                        t2 = obj4;
                        imageRequest2 = imageRequest8;
                        engineInterceptor = engineInterceptor5;
                        c04242 = c04243;
                        obj2 = imageRequest8;
                        objectRef3.element = t2;
                        fetchResult = (FetchResult) objectRef4.element;
                        if (!(fetchResult instanceof SourceResult)) {
                            objectRef10 = objectRef4;
                            engineInterceptor2 = engineInterceptor;
                            imageRequest3 = imageRequest2;
                            objectRef5 = objectRef;
                            eventListener3 = eventListener2;
                            if (!(fetchResult instanceof DrawableResult)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            executeResult = new ExecuteResult(((DrawableResult) objectRef10.element).getDrawable(), ((DrawableResult) objectRef10.element).getIsSampled(), ((DrawableResult) objectRef10.element).getDataSource(), null);
                            objectRef6 = objectRef5;
                            engineInterceptor3 = engineInterceptor2;
                            eventListener4 = eventListener3;
                            imageRequest4 = imageRequest3;
                            objectRef8 = objectRef10;
                            t3 = objectRef8.element;
                            if (t3 instanceof SourceResult) {
                                sourceResult3 = (SourceResult) t3;
                            } else {
                                sourceResult3 = null;
                            }
                            if (sourceResult3 != null) {
                                Utils.closeQuietly(source2);
                            }
                            Options options8 = (Options) objectRef6.element;
                            bitmapDrawable = null;
                            c04242.L$0 = null;
                            c04242.L$1 = null;
                            c04242.L$2 = null;
                            c04242.L$3 = null;
                            c04242.L$4 = null;
                            c04242.L$5 = null;
                            c04242.L$6 = null;
                            c04242.L$7 = null;
                            c04242.label = 3;
                            objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options8, eventListener4, c04242);
                            if (objTransform$coil_base_release == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            ExecuteResult executeResult3 = (ExecuteResult) objTransform$coil_base_release;
                            drawable = executeResult3.getDrawable();
                            if (drawable instanceof BitmapDrawable) {
                                bitmapDrawable2 = (BitmapDrawable) drawable;
                            } else {
                                bitmapDrawable2 = bitmapDrawable;
                            }
                            if (bitmapDrawable2 != null) {
                                bitmap.prepareToDraw();
                            }
                            return executeResult3;
                        }
                        CoroutineDispatcher decoderDispatcher3 = imageRequest2.getDecoderDispatcher();
                        objectRef7 = objectRef4;
                        engineInterceptor4 = engineInterceptor;
                        Ref.ObjectRef objectRef110 = objectRef7;
                        ImageRequest imageRequest9 = imageRequest2;
                        Ref.ObjectRef objectRef111 = objectRef;
                        eventListener5 = eventListener2;
                        EngineInterceptor$execute$executeResult$1 engineInterceptor$execute$executeResult$3 = new EngineInterceptor$execute$executeResult$1(engineInterceptor, objectRef7, objectRef2, imageRequest2, obj3, objectRef, eventListener2, null);
                        c04242.L$0 = engineInterceptor4;
                        c04242.L$1 = imageRequest9;
                        c04242.L$2 = eventListener5;
                        c04242.L$3 = objectRef111;
                        c04242.L$4 = objectRef110;
                        c04242.L$5 = null;
                        c04242.L$6 = null;
                        c04242.L$7 = null;
                        c04242.label = 2;
                        objWithContext = BuildersKt.withContext(decoderDispatcher3, engineInterceptor$execute$executeResult$3, c04242);
                        r3 = objectRef110;
                        objectRef9 = objectRef111;
                        imageRequest5 = imageRequest9;
                        if (objWithContext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Ref.ObjectRef objectRef112 = objectRef9;
                        executeResult = (ExecuteResult) objWithContext;
                        objectRef6 = objectRef112;
                        engineInterceptor3 = engineInterceptor4;
                        eventListener4 = eventListener5;
                        imageRequest4 = imageRequest5;
                        objectRef8 = r3;
                        t3 = objectRef8.element;
                        if (t3 instanceof SourceResult) {
                            sourceResult3 = (SourceResult) t3;
                        } else {
                            sourceResult3 = null;
                        }
                        if (sourceResult3 != null) {
                            Utils.closeQuietly(source2);
                        }
                        Options options9 = (Options) objectRef6.element;
                        bitmapDrawable = null;
                        c04242.L$0 = null;
                        c04242.L$1 = null;
                        c04242.L$2 = null;
                        c04242.L$3 = null;
                        c04242.L$4 = null;
                        c04242.L$5 = null;
                        c04242.L$6 = null;
                        c04242.L$7 = null;
                        c04242.label = 3;
                        objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options9, eventListener4, c04242);
                        if (objTransform$coil_base_release == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ExecuteResult executeResult4 = (ExecuteResult) objTransform$coil_base_release;
                        drawable = executeResult4.getDrawable();
                        if (drawable instanceof BitmapDrawable) {
                            bitmapDrawable2 = (BitmapDrawable) drawable;
                        } else {
                            bitmapDrawable2 = bitmapDrawable;
                        }
                        if (bitmapDrawable2 != null) {
                            bitmap.prepareToDraw();
                        }
                        return executeResult4;
                    } catch (Throwable th7) {
                        th = th7;
                        objectRef10 = objectRef4;
                        obj2 = null;
                    }
                    t = objectRef10.element;
                    if (t instanceof SourceResult) {
                        sourceResult2 = (SourceResult) t;
                    } else {
                        sourceResult = obj2;
                    }
                    if (sourceResult != 0) {
                        sourceResult = sourceResult2;
                        Utils.closeQuietly(source);
                    }
                    sourceResult = sourceResult2;
                    sourceResult = sourceResult2;
                    throw th;
                case 2:
                    objectRef10 = (Ref.ObjectRef) c04243.L$4;
                    Ref.ObjectRef objectRef22 = (Ref.ObjectRef) c04243.L$3;
                    eventListener5 = (EventListener) c04243.L$2;
                    ImageRequest imageRequest10 = (ImageRequest) c04243.L$1;
                    engineInterceptor4 = (EngineInterceptor) c04243.L$0;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        c04242 = c04243;
                        objWithContext = obj4;
                        r3 = objectRef10;
                        objectRef9 = objectRef22;
                        imageRequest5 = imageRequest10;
                        Ref.ObjectRef objectRef113 = objectRef9;
                        executeResult = (ExecuteResult) objWithContext;
                        objectRef6 = objectRef113;
                        engineInterceptor3 = engineInterceptor4;
                        eventListener4 = eventListener5;
                        imageRequest4 = imageRequest5;
                        objectRef8 = r3;
                        t3 = objectRef8.element;
                        if (t3 instanceof SourceResult) {
                            sourceResult3 = (SourceResult) t3;
                        } else {
                            sourceResult3 = null;
                        }
                        if (sourceResult3 != null) {
                            Utils.closeQuietly(source2);
                        }
                        Options options10 = (Options) objectRef6.element;
                        bitmapDrawable = null;
                        c04242.L$0 = null;
                        c04242.L$1 = null;
                        c04242.L$2 = null;
                        c04242.L$3 = null;
                        c04242.L$4 = null;
                        c04242.L$5 = null;
                        c04242.L$6 = null;
                        c04242.L$7 = null;
                        c04242.label = 3;
                        objTransform$coil_base_release = engineInterceptor3.transform$coil_base_release(executeResult, imageRequest4, options10, eventListener4, c04242);
                        if (objTransform$coil_base_release == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ExecuteResult executeResult5 = (ExecuteResult) objTransform$coil_base_release;
                        drawable = executeResult5.getDrawable();
                        if (drawable instanceof BitmapDrawable) {
                            bitmapDrawable2 = (BitmapDrawable) drawable;
                        } else {
                            bitmapDrawable2 = bitmapDrawable;
                        }
                        if (bitmapDrawable2 != null) {
                            bitmap.prepareToDraw();
                        }
                        return executeResult5;
                    } catch (Throwable th8) {
                        th = th8;
                        obj2 = null;
                    }
                    break;
                case 3:
                    ResultKt.throwOnFailure(obj4);
                    objTransform$coil_base_release = obj4;
                    bitmapDrawable = null;
                    ExecuteResult executeResult6 = (ExecuteResult) objTransform$coil_base_release;
                    drawable = executeResult6.getDrawable();
                    if (drawable instanceof BitmapDrawable) {
                        bitmapDrawable2 = (BitmapDrawable) drawable;
                    } else {
                        bitmapDrawable2 = bitmapDrawable;
                    }
                    if (bitmapDrawable2 != null) {
                        bitmap.prepareToDraw();
                    }
                    return executeResult6;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th9) {
            th = th9;
            obj2 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x0078  */
    /* JADX WARN: Code duplicated, block: B:18:0x00a6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:23:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:25:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00a7 -> B:20:0x00ae). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object fetch(coil.ComponentRegistry r18, coil.request.ImageRequest r19, java.lang.Object r20, coil.request.Options r21, coil.EventListener r22, kotlin.coroutines.Continuation<? super coil.fetch.FetchResult> r23) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.fetch(coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x007c  */
    /* JADX WARN: Code duplicated, block: B:18:0x00ae A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x00af  */
    /* JADX WARN: Code duplicated, block: B:22:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:24:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:25:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:27:0x00db  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00af -> B:20:0x00b5). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object decode(coil.fetch.SourceResult r17, coil.ComponentRegistry r18, coil.request.ImageRequest r19, java.lang.Object r20, coil.request.Options r21, coil.EventListener r22, kotlin.coroutines.Continuation<? super coil.intercept.EngineInterceptor.ExecuteResult> r23) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.decode(coil.fetch.SourceResult, coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object transform$coil_base_release(ExecuteResult result, ImageRequest request, Options options, EventListener eventListener, Continuation<? super ExecuteResult> continuation) {
        List<Transformation> transformations = request.getTransformations();
        if (transformations.isEmpty()) {
            return result;
        }
        if (!(result.getDrawable() instanceof BitmapDrawable) && !request.getAllowConversionToBitmap()) {
            Logger $this$log$iv = this.logger;
            if ($this$log$iv != null && $this$log$iv.getLevel() <= 4) {
                String type = result.getDrawable().getClass().getCanonicalName();
                $this$log$iv.log(TAG, 4, "allowConversionToBitmap=false, skipping transformations for type " + type + '.', null);
            }
            return result;
        }
        return BuildersKt.withContext(request.getTransformationDispatcher(), new EngineInterceptor$transform$3(this, result, options, transformations, eventListener, request, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap convertDrawableToBitmap(Drawable drawable, Options options, List<? extends Transformation> transformations) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap.Config config = Bitmaps.getSafeConfig(bitmap);
            if (ArraysKt.contains(Utils.getVALID_TRANSFORMATION_CONFIGS(), config)) {
                return bitmap;
            }
            Logger $this$log$iv = this.logger;
            if ($this$log$iv != null && $this$log$iv.getLevel() <= 4) {
                $this$log$iv.log(TAG, 4, "Converting bitmap with config " + config + " to apply transformations: " + transformations + '.', null);
            }
        } else {
            Logger $this$log$iv2 = this.logger;
            if ($this$log$iv2 != null && $this$log$iv2.getLevel() <= 4) {
                $this$log$iv2.log(TAG, 4, "Converting drawable of type " + drawable.getClass().getCanonicalName() + " to apply transformations: " + transformations + '.', null);
            }
        }
        return DrawableUtils.INSTANCE.convertToBitmap(drawable, options.getConfig(), options.getSize(), options.getScale(), options.getAllowInexactSize());
    }

    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ0\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcoil/intercept/EngineInterceptor$ExecuteResult;", "", "drawable", "Landroid/graphics/drawable/Drawable;", "isSampled", "", "dataSource", "Lcoil/decode/DataSource;", "diskCacheKey", "", "<init>", "(Landroid/graphics/drawable/Drawable;ZLcoil/decode/DataSource;Ljava/lang/String;)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "getDataSource", "()Lcoil/decode/DataSource;", "getDiskCacheKey", "()Ljava/lang/String;", "copy", "coil-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ExecuteResult {
        private final DataSource dataSource;
        private final String diskCacheKey;
        private final Drawable drawable;
        private final boolean isSampled;

        public ExecuteResult(Drawable drawable, boolean isSampled, DataSource dataSource, String diskCacheKey) {
            this.drawable = drawable;
            this.isSampled = isSampled;
            this.dataSource = dataSource;
            this.diskCacheKey = diskCacheKey;
        }

        public final Drawable getDrawable() {
            return this.drawable;
        }

        /* JADX INFO: renamed from: isSampled, reason: from getter */
        public final boolean getIsSampled() {
            return this.isSampled;
        }

        public final DataSource getDataSource() {
            return this.dataSource;
        }

        public final String getDiskCacheKey() {
            return this.diskCacheKey;
        }

        public static /* synthetic */ ExecuteResult copy$default(ExecuteResult executeResult, Drawable drawable, boolean z, DataSource dataSource, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                drawable = executeResult.drawable;
            }
            if ((i & 2) != 0) {
                z = executeResult.isSampled;
            }
            if ((i & 4) != 0) {
                dataSource = executeResult.dataSource;
            }
            if ((i & 8) != 0) {
                str = executeResult.diskCacheKey;
            }
            return executeResult.copy(drawable, z, dataSource, str);
        }

        public final ExecuteResult copy(Drawable drawable, boolean isSampled, DataSource dataSource, String diskCacheKey) {
            return new ExecuteResult(drawable, isSampled, dataSource, diskCacheKey);
        }
    }
}
