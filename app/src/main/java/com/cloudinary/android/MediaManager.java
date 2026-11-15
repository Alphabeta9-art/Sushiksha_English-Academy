package com.cloudinary.android;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.cloudinary.Cloudinary;
import com.cloudinary.Configuration;
import com.cloudinary.Url;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.UploadResult;
import com.cloudinary.android.callback.UploadStatus;
import com.cloudinary.android.download.DownloadRequestBuilder;
import com.cloudinary.android.download.DownloadRequestBuilderFactory;
import com.cloudinary.android.payload.ByteArrayPayload;
import com.cloudinary.android.payload.FilePayload;
import com.cloudinary.android.payload.LocalUriPayload;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.ResourcePayload;
import com.cloudinary.android.policy.GlobalUploadPolicy;
import com.cloudinary.android.signed.SignatureProvider;
import com.cloudinary.utils.Analytics;
import com.cloudinary.utils.StringUtils;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public class MediaManager {
    public static final String ACTION_REQUEST_FINISHED = "com.cloudinary.ACTION_REQUEST_FINISHED";
    public static final String ACTION_REQUEST_STARTED = "com.cloudinary.ACTION_REQUEST_STARTED";
    public static final String INTENT_EXTRA_REQUEST_ID = "INTENT_EXTRA_REQUEST_ID";
    public static final String INTENT_EXTRA_REQUEST_RESULT_STATUS = "INTENT_EXTRA_REQUEST_RESULT_STATUS";
    private static final String TAG = MediaManager.class.getSimpleName();
    public static final String VERSION = "3.0.2";
    private static MediaManager _instance;
    private final CallbackDispatcher callbackDispatcher;
    private final Cloudinary cloudinary;
    private DownloadRequestBuilderFactory downloadRequestBuilderFactory;
    private final ImmediateRequestsRunner immediateRequestsRunner;
    private final RequestDispatcher requestDispatcher;
    private final RequestProcessor requestProcessor;
    private final SignatureProvider signatureProvider;
    private final String techVersion = Integer.toString(Build.VERSION.SDK_INT) + ".0";
    private GlobalUploadPolicy globalUploadPolicy = GlobalUploadPolicy.defaultPolicy();
    private final ExecutorService executor = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private MediaManager(Context context, SignatureProvider signatureProvider, Map config) {
        BackgroundRequestStrategy strategy = BackgroundStrategyProvider.provideStrategy();
        this.callbackDispatcher = new DefaultCallbackDispatcher(context);
        this.requestProcessor = new DefaultRequestProcessor(this.callbackDispatcher);
        this.immediateRequestsRunner = new DefaultImmediateRequestsRunner(this.requestProcessor);
        this.requestDispatcher = new DefaultRequestDispatcher(strategy, this.immediateRequestsRunner);
        strategy.init(context);
        this.signatureProvider = signatureProvider;
        String cloudinaryUrl = Utils.cloudinaryUrlFromContext(context);
        if (config != null) {
            this.cloudinary = new Cloudinary(config);
        } else if (StringUtils.isNotBlank(cloudinaryUrl)) {
            this.cloudinary = new Cloudinary(cloudinaryUrl);
        } else {
            this.cloudinary = new Cloudinary();
        }
        if (this.cloudinary.config.analytics == null) {
            this.cloudinary.config.analytics = true;
        }
        this.cloudinary.setAnalytics(new Analytics("F", VERSION, this.techVersion, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, this.techVersion, "0"));
        this.cloudinary.config.secure = true;
        this.callbackDispatcher.registerCallback(new UploadCallback() { // from class: com.cloudinary.android.MediaManager.1
            @Override // com.cloudinary.android.callback.UploadCallback
            public void onStart(String requestId) {
            }

            @Override // com.cloudinary.android.callback.UploadCallback
            public void onProgress(String requestId, long bytes, long totalBytes) {
            }

            @Override // com.cloudinary.android.callback.UploadCallback
            public void onSuccess(String requestId, Map resultData) {
                MediaManager.this.requestDispatcher.queueRoomFreed();
            }

            @Override // com.cloudinary.android.callback.UploadCallback
            public void onError(String requestId, ErrorInfo error) {
                MediaManager.this.requestDispatcher.queueRoomFreed();
            }

            @Override // com.cloudinary.android.callback.UploadCallback
            public void onReschedule(String requestId, ErrorInfo error) {
                MediaManager.this.requestDispatcher.queueRoomFreed();
            }
        });
    }

    public static void init(Context context) {
        init(context, (SignatureProvider) null, (Map) null);
    }

    public static void init(Context context, Map config) {
        init(context, (SignatureProvider) null, config);
    }

    public static void init(Context context, Configuration config) {
        Map<String, Object> map = null;
        if (config != null) {
            map = config.asMap();
        }
        init(context, (SignatureProvider) null, map);
    }

    public static void init(Context context, SignatureProvider signatureProvider) {
        init(context, signatureProvider, (Map) null);
    }

    public static void init(Context context, SignatureProvider provider, Map config) {
        synchronized (MediaManager.class) {
            try {
                if (context == null) {
                    throw new IllegalArgumentException("context cannot be null.");
                }
                if (_instance == null) {
                    _instance = new MediaManager(context, provider, config);
                } else {
                    throw new IllegalStateException("MediaManager is already initialized");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void init(Context context, SignatureProvider provider, Configuration config) {
        Map<String, Object> mapAsMap = null;
        if (config != null) {
            mapAsMap = config.asMap();
        }
        init(context, provider, mapAsMap);
    }

    public static MediaManager get() {
        if (_instance == null) {
            throw new IllegalStateException("Must call init() before accessing Cloudinary.");
        }
        return _instance;
    }

    public static void setLogLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    public Cloudinary getCloudinary() {
        return this.cloudinary;
    }

    public Url url() {
        Url url = this.cloudinary.url();
        return url;
    }

    public boolean cancelRequest(String requestId) {
        return this.immediateRequestsRunner.cancelRequest(requestId) || this.requestDispatcher.cancelRequest(requestId);
    }

    public int cancelAllRequests() {
        return this.requestDispatcher.cancelAllRequests() + this.immediateRequestsRunner.cancelAllRequests();
    }

    public UploadRequest upload(int rawResourceId) {
        return buildUploadRequest(new ResourcePayload(Integer.valueOf(rawResourceId)));
    }

    public UploadRequest upload(Uri uri) {
        return buildUploadRequest(new LocalUriPayload(uri));
    }

    public UploadRequest upload(byte[] bytes) {
        return buildUploadRequest(new ByteArrayPayload(bytes));
    }

    public UploadRequest upload(String filePath) {
        return buildUploadRequest(new FilePayload(filePath));
    }

    public UploadRequest upload(Payload payload) {
        return buildUploadRequest(payload);
    }

    public GlobalUploadPolicy getGlobalUploadPolicy() {
        return this.globalUploadPolicy;
    }

    public void setGlobalUploadPolicy(GlobalUploadPolicy globalUploadPolicy) {
        this.globalUploadPolicy = globalUploadPolicy;
    }

    public void registerCallback(UploadCallback callback) {
        this.callbackDispatcher.registerCallback(callback);
    }

    void registerCallback(String requestId, UploadCallback callback) {
        this.callbackDispatcher.registerCallback(requestId, callback);
    }

    public void unregisterCallback(UploadCallback callback) {
        this.callbackDispatcher.unregisterCallback(callback);
    }

    public UploadResult popPendingResult(String requestId) {
        return this.callbackDispatcher.popPendingResult(requestId);
    }

    void dispatchRequestError(Context context, String requestId, ErrorInfo error) {
        this.callbackDispatcher.dispatchError(context, requestId, error);
    }

    private UploadRequest<Payload> buildUploadRequest(Payload payload) {
        UploadContext<Payload> payloadUploadContext = new UploadContext<>(payload, this.requestDispatcher);
        return new UploadRequest<>(payloadUploadContext);
    }

    public ResponsiveUrl responsiveUrl(boolean autoWidth, boolean autoHeight, String cropMode, String gravity) {
        return new ResponsiveUrl(this.cloudinary, autoWidth, autoHeight, cropMode, gravity);
    }

    public ResponsiveUrl responsiveUrl(ResponsiveUrl.Preset preset) {
        return preset.get(getCloudinary());
    }

    public void responsiveUrl(View view, Url baseUrl, ResponsiveUrl.Preset preset, ResponsiveUrl.Callback callback) {
        preset.get(getCloudinary()).generate(baseUrl, view, callback);
    }

    public void responsiveUrl(View view, String publicId, ResponsiveUrl.Preset preset, ResponsiveUrl.Callback callback) {
        preset.get(getCloudinary()).generate(publicId, view, callback);
    }

    UploadStatus processRequest(Context context, RequestParams params) {
        return this.requestProcessor.processRequest(context, params);
    }

    boolean hasCredentials() {
        return StringUtils.isNotBlank(this.cloudinary.config.apiKey) && StringUtils.isNotBlank(this.cloudinary.config.apiSecret);
    }

    SignatureProvider getSignatureProvider() {
        return this.signatureProvider;
    }

    void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    public void setDownloadRequestBuilderFactory(DownloadRequestBuilderFactory factory) {
        this.downloadRequestBuilderFactory = factory;
    }

    public DownloadRequestBuilder download(Context context) {
        if (this.downloadRequestBuilderFactory == null) {
            throw new IllegalStateException("Must set a factory before downloading.");
        }
        return this.downloadRequestBuilderFactory.createDownloadRequestBuilder(context);
    }
}
