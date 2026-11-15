package com.cloudinary.android;

import android.content.Context;
import androidx.work.Data;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.payload.FilePayload;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadNotFoundException;
import com.cloudinary.android.policy.TimeWindow;
import com.cloudinary.android.policy.UploadPolicy;
import com.cloudinary.android.preprocess.PreprocessChain;
import com.cloudinary.android.preprocess.PreprocessException;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes11.dex */
public class UploadRequest<T extends Payload> {
    private static final String TAG = UploadRequest.class.getSimpleName();
    private UploadCallback callback;
    private Long maxFileSize;
    private Map<String, Object> options;
    private PreprocessChain preprocessChain;
    private final UploadContext<T> uploadContext;
    private final Object optionsLockObject = new Object();
    private String requestId = UUID.randomUUID().toString();
    private boolean dispatched = false;
    private UploadPolicy uploadPolicy = MediaManager.get().getGlobalUploadPolicy();
    private TimeWindow timeWindow = TimeWindow.getDefault();
    private String optionsAsString = null;
    private boolean startNow = false;

    public UploadRequest(UploadContext<T> uploadContext) {
        this.uploadContext = uploadContext;
    }

    UploadRequest(UploadContext<T> uploadContext, Map<String, Object> options) {
        this.uploadContext = uploadContext;
        this.options = options;
    }

    static String encodeOptions(Map<String, Object> options) throws IOException {
        return ObjectUtils.serialize(options);
    }

    static Map<String, Object> decodeOptions(String encoded) throws IOException, ClassNotFoundException {
        return (Map) ObjectUtils.deserialize(encoded);
    }

    public synchronized UploadRequest<T> callback(UploadCallback callback) {
        assertNotDispatched();
        this.callback = new DelegateCallback(callback);
        return this;
    }

    public synchronized UploadRequest<T> unsigned(String uploadPreset) {
        assertNotDispatched();
        verifyOptionsExist();
        this.options.put("unsigned", true);
        this.options.put("upload_preset", uploadPreset);
        return this;
    }

    public synchronized UploadRequest<T> maxFileSize(long bytes) {
        assertNotDispatched();
        this.maxFileSize = Long.valueOf(bytes);
        return this;
    }

    public synchronized UploadRequest<T> preprocess(PreprocessChain preprocessChain) {
        assertNotDispatched();
        this.preprocessChain = preprocessChain;
        return this;
    }

    public synchronized UploadRequest<T> constrain(TimeWindow timeWindow) {
        assertNotDispatched();
        this.timeWindow = timeWindow;
        return this;
    }

    public synchronized UploadRequest<T> options(Map<String, Object> options) {
        assertNotDispatched();
        this.options = options;
        return this;
    }

    public synchronized UploadRequest<T> option(String name, Object value) {
        assertNotDispatched();
        verifyOptionsExist();
        this.options.put(name, value);
        return this;
    }

    public synchronized UploadRequest<T> policy(UploadPolicy policy) {
        assertNotDispatched();
        this.uploadPolicy = policy;
        return this;
    }

    public synchronized String dispatch() {
        return dispatch(null);
    }

    public synchronized String startNow(Context context) {
        this.startNow = true;
        return dispatch(context);
    }

    public synchronized String dispatch(final Context context) {
        assertNotDispatched();
        verifyOptionsExist();
        boolean hasPreprocess = true;
        this.dispatched = true;
        serializeOptions();
        MediaManager.get().registerCallback(this.requestId, this.callback);
        final RequestDispatcher dispatcher = this.uploadContext.getDispatcher();
        if (this.preprocessChain == null || this.preprocessChain.isEmpty()) {
            hasPreprocess = false;
        }
        if (!hasPreprocess && this.maxFileSize == null) {
            doDispatch(dispatcher, context, this);
        } else {
            if (context == null) {
                throw new IllegalArgumentException("A valid android context must be supplied to UploadRequest.dispatch() when using preprocessing or setting maxFileSize");
            }
            MediaManager.get().execute(new Runnable() { // from class: com.cloudinary.android.UploadRequest.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        UploadRequest newRequest = UploadRequest.this.preprocessChain != null ? UploadRequest.this.preprocessAndClone(context) : UploadRequest.this;
                        long length = newRequest.getPayload().getLength(context);
                        if (UploadRequest.this.maxFileSize != null && length > UploadRequest.this.maxFileSize.longValue()) {
                            MediaManager.get().dispatchRequestError(context, UploadRequest.this.requestId, new ErrorInfo(12, String.format("Payload size is too large, %d, max is %d", Long.valueOf(length), UploadRequest.this.maxFileSize)));
                        } else {
                            UploadRequest.this.doDispatch(dispatcher, context, newRequest);
                        }
                    } catch (PayloadNotFoundException e) {
                        MediaManager.get().dispatchRequestError(context, UploadRequest.this.requestId, new ErrorInfo(12, e.getClass().getSimpleName() + ": " + e.getMessage()));
                    } catch (PreprocessException e2) {
                        MediaManager.get().dispatchRequestError(context, UploadRequest.this.requestId, new ErrorInfo(12, e2.getClass().getSimpleName() + ": " + e2.getMessage()));
                    } catch (RuntimeException e3) {
                        Logger.e(UploadRequest.TAG, "Error running preprocess for request", e3);
                        MediaManager.get().dispatchRequestError(context, UploadRequest.this.requestId, new ErrorInfo(12, e3.getClass().getSimpleName() + ": " + e3.getMessage()));
                    }
                }
            });
        }
        return this.requestId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDispatch(RequestDispatcher dispatcher, Context context, UploadRequest<T> uploadRequest) {
        if (this.startNow) {
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null when calling startNow()");
            }
            dispatcher.startNow(context, uploadRequest);
            return;
        }
        dispatcher.dispatch(uploadRequest);
    }

    synchronized void serializeOptions() {
        try {
            this.optionsAsString = encodeOptions(this.options);
        } catch (IOException e) {
            throw new InvalidParamsException("Parameters must be serializable", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UploadRequest preprocessAndClone(Context context) throws PayloadNotFoundException, PreprocessException {
        String newFile = this.preprocessChain.execute(context, getPayload());
        UploadRequest<FilePayload> uploadRequest = new UploadRequest<>(new UploadContext(new FilePayload(newFile), getUploadContext().getDispatcher()));
        uploadRequest.uploadPolicy = this.uploadPolicy;
        uploadRequest.timeWindow = TimeWindow.getDefault();
        uploadRequest.callback = this.callback;
        uploadRequest.options = this.options;
        uploadRequest.optionsAsString = this.optionsAsString;
        uploadRequest.requestId = this.requestId;
        uploadRequest.dispatched = this.dispatched;
        return uploadRequest;
    }

    private void verifyOptionsExist() {
        if (this.options == null) {
            synchronized (this.optionsLockObject) {
                if (this.options == null) {
                    this.options = new HashMap();
                }
            }
        }
    }

    public String getRequestId() {
        return this.requestId;
    }

    public T getPayload() {
        return (T) this.uploadContext.getPayload();
    }

    UploadCallback getCallback() {
        return this.callback;
    }

    UploadContext<T> getUploadContext() {
        return this.uploadContext;
    }

    TimeWindow getTimeWindow() {
        return this.timeWindow;
    }

    private void assertNotDispatched() {
        if (this.dispatched) {
            throw new IllegalStateException("Request already dispatched");
        }
    }

    private String getOptionsString() {
        return this.optionsAsString;
    }

    UploadPolicy getUploadPolicy() {
        return this.uploadPolicy;
    }

    void defferByMinutes(int minutes) {
        this.timeWindow = this.timeWindow.newDeferredWindow(minutes);
    }

    void populateParamsFromFields(RequestParams target) {
        target.putString("uri", getPayload().toUri());
        target.putString("requestId", getRequestId());
        target.putInt("maxErrorRetries", getUploadPolicy().getMaxErrorRetries());
        target.putString("options", getOptionsString());
    }

    public Data buildPayload(File payloadFile) {
        Data.Builder dataBuilder = new Data.Builder();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(payloadFile));
            try {
                PayloadData data = new PayloadData(getPayload().toUri(), getRequestId(), getUploadPolicy().getMaxErrorRetries(), getOptionsString());
                oos.writeObject(data);
                oos.close();
            } catch (Throwable th) {
                try {
                    oos.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBuilder.putString(PayloadData.KEY, payloadFile.getAbsolutePath());
        return dataBuilder.build();
    }

    private static final class DelegateCallback implements UploadCallback {
        private final UploadCallback callback;

        DelegateCallback(UploadCallback callback) {
            this.callback = callback;
        }

        @Override // com.cloudinary.android.callback.UploadCallback
        public void onStart(String requestId) {
            this.callback.onStart(requestId);
        }

        @Override // com.cloudinary.android.callback.UploadCallback
        public void onProgress(String requestId, long bytes, long totalBytes) {
            this.callback.onProgress(requestId, bytes, totalBytes);
        }

        @Override // com.cloudinary.android.callback.UploadCallback
        public void onSuccess(String requestId, Map resultData) {
            this.callback.onSuccess(requestId, resultData);
            MediaManager.get().unregisterCallback(this);
        }

        @Override // com.cloudinary.android.callback.UploadCallback
        public void onError(String requestId, ErrorInfo error) {
            this.callback.onError(requestId, error);
            MediaManager.get().unregisterCallback(this);
        }

        @Override // com.cloudinary.android.callback.UploadCallback
        public void onReschedule(String requestId, ErrorInfo error) {
            this.callback.onReschedule(requestId, error);
        }
    }

    public static class PayloadData implements Serializable {
        public static final transient String KEY = "payload_file_path";
        private final int maxErrorRetries;
        private final String options;
        private final String requestId;
        private final String uri;

        public PayloadData() {
            this(null, null, 1, null);
        }

        public PayloadData(String uri, String requestId, int maxErrorRetries, String options) {
            this.uri = uri;
            this.requestId = requestId;
            this.maxErrorRetries = maxErrorRetries;
            this.options = options;
        }

        public String getUri() {
            return this.uri;
        }

        public String getRequestId() {
            return this.requestId;
        }

        public int getMaxErrorRetries() {
            return this.maxErrorRetries;
        }

        public String getOptions() {
            return this.options;
        }
    }
}
