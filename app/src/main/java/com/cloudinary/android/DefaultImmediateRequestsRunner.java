package com.cloudinary.android;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public class DefaultImmediateRequestsRunner implements ImmediateRequestsRunner {
    private static final String TAG = "DefaultImmediateRequestsRunner";
    protected static final Map<String, Future> tasks = new ConcurrentHashMap();
    private final ExecutorService executor = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final RequestProcessor requestProcessor;

    DefaultImmediateRequestsRunner(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override // com.cloudinary.android.ImmediateRequestsRunner
    public synchronized void runRequest(final Context context, UploadRequest uploadRequest) {
        final ImmediateRequestParams params = new ImmediateRequestParams();
        uploadRequest.populateParamsFromFields(params);
        params.putBoolean("immediate", true);
        final String requestId = uploadRequest.getRequestId();
        tasks.put(requestId, this.executor.submit(new Runnable() { // from class: com.cloudinary.android.DefaultImmediateRequestsRunner.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DefaultImmediateRequestsRunner.this.requestProcessor.processRequest(context, params);
                } finally {
                    DefaultImmediateRequestsRunner.tasks.remove(requestId);
                }
            }
        }));
    }

    @Override // com.cloudinary.android.ImmediateRequestsRunner
    public synchronized boolean cancelRequest(String requestId) {
        Future task = tasks.remove(requestId);
        if (task == null) {
            return false;
        }
        task.cancel(true);
        return true;
    }

    @Override // com.cloudinary.android.ImmediateRequestsRunner
    public synchronized int cancelAllRequests() {
        int size;
        for (Future task : tasks.values()) {
            task.cancel(true);
        }
        size = tasks.size();
        tasks.clear();
        return size;
    }

    private static final class ImmediateRequestParams implements RequestParams {
        private final Map<String, Object> map;

        private ImmediateRequestParams() {
            this.map = new HashMap();
        }

        @Override // com.cloudinary.android.RequestParams
        public void putString(String key, String value) {
            if (value == null) {
                this.map.remove(key);
            } else {
                this.map.put(key, value);
            }
        }

        @Override // com.cloudinary.android.RequestParams
        public void putInt(String key, int value) {
            this.map.put(key, Integer.valueOf(value));
        }

        @Override // com.cloudinary.android.RequestParams
        public void putLong(String key, long value) {
            this.map.put(key, Long.valueOf(value));
        }

        @Override // com.cloudinary.android.RequestParams
        public void putBoolean(String key, boolean value) {
            this.map.put(key, Boolean.valueOf(value));
        }

        @Override // com.cloudinary.android.RequestParams
        public String getString(String key, String defaultValue) {
            if (this.map.containsKey(key)) {
                return this.map.get(key).toString();
            }
            return null;
        }

        @Override // com.cloudinary.android.RequestParams
        public int getInt(String key, int defaultValue) {
            return this.map.containsKey(key) ? ((Integer) this.map.get(key)).intValue() : defaultValue;
        }

        @Override // com.cloudinary.android.RequestParams
        public long getLong(String key, long defaultValue) {
            return this.map.containsKey(key) ? ((Long) this.map.get(key)).longValue() : defaultValue;
        }

        @Override // com.cloudinary.android.RequestParams
        public boolean getBoolean(String key, boolean defaultValue) {
            return this.map.containsKey(key) ? ((Boolean) this.map.get(key)).booleanValue() : defaultValue;
        }
    }
}
