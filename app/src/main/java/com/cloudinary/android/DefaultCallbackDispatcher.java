package com.cloudinary.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.util.Pools;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.ListenerService;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.UploadResult;
import com.cloudinary.android.callback.UploadStatus;
import com.cloudinary.utils.StringUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes11.dex */
class DefaultCallbackDispatcher implements CallbackDispatcher {
    private static final int ERROR_MESSAGE = 1;
    private static final int PROGRESS_MESSAGE = 2;
    private static final int RESCHEDULE_MESSAGE = 3;
    private static final int START_MESSAGE = 0;
    private static final int SUCCESS_MESSAGE = 4;
    private static final String TAG = "DefaultCallbackDispatcher";
    private Handler handler;
    private ReentrantReadWriteLock readWriteLock;
    private Class<?> listenerServiceClass = null;
    private boolean isListenerServiceAlreadyRegistered = false;
    private final Map<Integer, UploadCallbackWrapper> callbackRegistry = new ConcurrentHashMap();
    private final Map<String, UploadResult> pendingResults = new ConcurrentHashMap();

    DefaultCallbackDispatcher(Context context) {
        initListenerClass(context);
        this.readWriteLock = new ReentrantReadWriteLock();
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.cloudinary.android.DefaultCallbackDispatcher.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                CallbackMessage callbackMessage = (CallbackMessage) msg.obj;
                String requestId = callbackMessage.requestId;
                switch (msg.what) {
                    case 0:
                        callbackMessage.callback.onStart(requestId);
                        break;
                    case 1:
                        callbackMessage.callback.onError(requestId, callbackMessage.error);
                        break;
                    case 2:
                        callbackMessage.callback.onProgress(requestId, callbackMessage.bytes, callbackMessage.totalBytes);
                        break;
                    case 3:
                        callbackMessage.callback.onReschedule(requestId, callbackMessage.error);
                        break;
                    case 4:
                        callbackMessage.callback.onSuccess(requestId, callbackMessage.resultData);
                        break;
                }
                if (msg.what != 2) {
                    Logger.d(DefaultCallbackDispatcher.TAG, String.format("Dispatching callback for request %s. Type: %d", requestId, Integer.valueOf(msg.what)));
                }
                callbackMessage.recycle();
            }
        };
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public synchronized void registerCallback(String requestId, UploadCallback callback) {
        this.readWriteLock.writeLock().lock();
        if (callback != null) {
            try {
                Logger.d(TAG, String.format("Registered callback %s", callback.getClass().getSimpleName()));
                if (callback instanceof ListenerService) {
                    Logger.d(TAG, "Listener service registered.");
                    this.isListenerServiceAlreadyRegistered = true;
                }
                int callbackId = System.identityHashCode(callback);
                UploadCallbackWrapper uploadCallbackWrapper = new UploadCallbackWrapper(callback);
                uploadCallbackWrapper.addRequestId(requestId);
                this.callbackRegistry.put(Integer.valueOf(callbackId), uploadCallbackWrapper);
            } catch (Throwable th) {
                this.readWriteLock.writeLock().unlock();
                throw th;
            }
        }
        this.readWriteLock.writeLock().unlock();
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public synchronized void registerCallback(UploadCallback callback) {
        this.readWriteLock.writeLock().lock();
        if (callback != null) {
            try {
                Logger.d(TAG, String.format("Registered callback %s", callback.getClass().getSimpleName()));
                if (callback instanceof ListenerService) {
                    Logger.d(TAG, "Listener service registered.");
                    this.isListenerServiceAlreadyRegistered = true;
                }
                this.callbackRegistry.put(Integer.valueOf(System.identityHashCode(callback)), new UploadCallbackWrapper(callback));
            } catch (Throwable th) {
                this.readWriteLock.writeLock().unlock();
                throw th;
            }
        }
        this.readWriteLock.writeLock().unlock();
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public synchronized void unregisterCallback(UploadCallback callback) {
        if (callback != null) {
            Logger.d(TAG, String.format("Unregistered callback %s", callback.getClass().getSimpleName()));
            if (callback instanceof ListenerService) {
                Logger.d(TAG, "Listener service unregistered.");
                this.isListenerServiceAlreadyRegistered = false;
            }
            this.callbackRegistry.remove(Integer.valueOf(System.identityHashCode(callback)));
        }
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void wakeListenerServiceWithRequestStart(Context appContext, String requestId) {
        Logger.d(TAG, String.format("wakeListenerServiceWithRequestStart, listenerClass: %s, alreadyRegistered: %s", this.listenerServiceClass, Boolean.valueOf(this.isListenerServiceAlreadyRegistered)));
        if (this.listenerServiceClass != null && !this.isListenerServiceAlreadyRegistered) {
            appContext.startService(new Intent(appContext, this.listenerServiceClass).setAction(MediaManager.ACTION_REQUEST_STARTED).putExtra(MediaManager.INTENT_EXTRA_REQUEST_ID, requestId));
        }
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void wakeListenerServiceWithRequestFinished(Context appContext, String requestId, UploadStatus uploadStatus) {
        Logger.d(TAG, String.format("wakeListenerServiceWithRequestFinished, listenerClass: %s, alreadyRegistered: %s", this.listenerServiceClass, Boolean.valueOf(this.isListenerServiceAlreadyRegistered)));
        if (this.listenerServiceClass != null && !this.isListenerServiceAlreadyRegistered) {
            appContext.startService(new Intent(appContext, this.listenerServiceClass).setAction(MediaManager.ACTION_REQUEST_FINISHED).putExtra(MediaManager.INTENT_EXTRA_REQUEST_ID, requestId).putExtra(MediaManager.INTENT_EXTRA_REQUEST_RESULT_STATUS, uploadStatus));
        }
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void dispatchStart(String requestId) {
        dispatchMessage(requestId, 0, CallbackMessage.obtain());
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void dispatchProgress(String requestId, long bytes, long totalBytes) {
        CallbackMessage callbackMessage = CallbackMessage.obtain();
        callbackMessage.bytes = bytes;
        callbackMessage.totalBytes = totalBytes;
        dispatchMessage(requestId, 2, callbackMessage);
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void dispatchError(Context context, String requestId, ErrorInfo error) {
        this.pendingResults.put(requestId, new UploadResult(null, error));
        CallbackMessage callbackMessage = CallbackMessage.obtain();
        callbackMessage.error = error;
        dispatchMessage(requestId, 1, callbackMessage);
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void dispatchReschedule(Context context, String requestId, ErrorInfo error) {
        CallbackMessage callbackMessage = CallbackMessage.obtain();
        callbackMessage.error = error;
        dispatchMessage(requestId, 3, callbackMessage);
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public void dispatchSuccess(Context context, String requestId, Map resultData) {
        this.pendingResults.put(requestId, new UploadResult(resultData, null));
        CallbackMessage callbackMessage = CallbackMessage.obtain();
        callbackMessage.resultData = resultData;
        dispatchMessage(requestId, 4, callbackMessage);
    }

    @Override // com.cloudinary.android.CallbackDispatcher
    public UploadResult popPendingResult(String requestId) {
        return this.pendingResults.remove(requestId);
    }

    private void dispatchMessage(String requestId, int what, CallbackMessage callbackMessage) {
        this.readWriteLock.readLock().lock();
        try {
            for (UploadCallbackWrapper wrapper : this.callbackRegistry.values()) {
                if (wrapper != null && wrapper.matches(requestId)) {
                    CallbackMessage copy = CallbackMessage.obtain(callbackMessage);
                    copy.callback = wrapper.callback;
                    copy.requestId = requestId;
                    this.handler.obtainMessage(what, copy).sendToTarget();
                }
            }
            callbackMessage.recycle();
            this.readWriteLock.readLock().unlock();
        } catch (Throwable th) {
            callbackMessage.recycle();
            this.readWriteLock.readLock().unlock();
            throw th;
        }
    }

    private void initListenerClass(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String className = null;
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName, 128);
            if (info != null && info.metaData != null) {
                className = (String) info.metaData.get("cloudinaryCallbackService");
                if (StringUtils.isNotBlank(className)) {
                    this.listenerServiceClass = Class.forName(className);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, String.format("Package name not found: %s", packageName));
        } catch (ClassNotFoundException e2) {
            Logger.e(TAG, String.format("Listener class name not found: %s", className));
        }
    }

    private static final class UploadCallbackWrapper {
        private final UploadCallback callback;
        private final Set<String> requestIds;

        private UploadCallbackWrapper(UploadCallback callback) {
            this.callback = callback;
            this.requestIds = new HashSet();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRequestId(String requestId) {
            this.requestIds.add(requestId);
        }

        boolean matches(String requestId) {
            return this.requestIds.isEmpty() || this.requestIds.contains(requestId);
        }
    }

    private static final class CallbackMessage {
        private static final Pools.SynchronizedPool<CallbackMessage> sPool = new Pools.SynchronizedPool<>(100);
        private long bytes;
        private UploadCallback callback;
        private ErrorInfo error;
        private String requestId;
        private Map resultData;
        private long totalBytes;

        private CallbackMessage() {
        }

        static CallbackMessage obtain() {
            CallbackMessage instance = sPool.acquire();
            return instance != null ? instance : new CallbackMessage();
        }

        static CallbackMessage obtain(CallbackMessage callbackMessage) {
            CallbackMessage instance = obtain();
            instance.requestId = callbackMessage.requestId;
            instance.callback = callbackMessage.callback;
            instance.bytes = callbackMessage.bytes;
            instance.totalBytes = callbackMessage.totalBytes;
            instance.error = callbackMessage.error;
            instance.resultData = callbackMessage.resultData;
            return instance;
        }

        void recycle() {
            this.callback = null;
            this.requestId = null;
            this.bytes = -1L;
            this.totalBytes = -1L;
            this.error = null;
            this.resultData = null;
            sPool.release(this);
        }
    }
}
