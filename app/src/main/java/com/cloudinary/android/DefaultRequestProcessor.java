package com.cloudinary.android;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.cloudinary.Cloudinary;
import com.cloudinary.ProgressCallback;
import com.cloudinary.Uploader;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadStatus;
import com.cloudinary.android.payload.EmptyByteArrayException;
import com.cloudinary.android.payload.FileNotFoundException;
import com.cloudinary.android.payload.LocalUriNotFoundException;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadFactory;
import com.cloudinary.android.payload.ResourceNotFoundException;
import com.cloudinary.android.signed.Signature;
import com.cloudinary.android.signed.SignatureProvider;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes11.dex */
class DefaultRequestProcessor implements RequestProcessor {
    public static final String ERROR_COUNT_PARAM = "errorCount";
    private static final String TAG = "DefaultRequestProcessor";
    private final CallbackDispatcher callbackDispatcher;
    private AtomicInteger runningJobs = new AtomicInteger(0);

    DefaultRequestProcessor(CallbackDispatcher callbackDispatcher) {
        this.callbackDispatcher = callbackDispatcher;
    }

    /* JADX WARN: Code duplicated, block: B:116:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:118:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:119:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:121:0x030a  */
    @Override // com.cloudinary.android.RequestProcessor
    public UploadStatus processRequest(Context context, RequestParams params) throws Throwable {
        String requestId;
        boolean optionsLoadedSuccessfully;
        Map<String, Object> options;
        String requestId2;
        UploadStatus requestResultStatus;
        ErrorInfo error;
        UploadStatus requestResultStatus2;
        String requestId3 = params.getString("requestId", null);
        String uri = params.getString("uri", null);
        String optionsAsString = params.getString("options", null);
        int maxErrorRetries = params.getInt("maxErrorRetries", MediaManager.get().getGlobalUploadPolicy().getMaxErrorRetries());
        int errorCount = params.getInt(ERROR_COUNT_PARAM, 0);
        boolean isImmediate = params.getBoolean("immediate", false);
        Logger.i(TAG, String.format("Processing Request %s.", requestId3));
        this.callbackDispatcher.dispatchStart(requestId3);
        this.callbackDispatcher.wakeListenerServiceWithRequestStart(context, requestId3);
        Context appContext = context.getApplicationContext();
        Map resultData = null;
        try {
            Map<String, Object> options2 = StringUtils.isBlank(optionsAsString) ? new HashMap<>() : UploadRequest.decodeOptions(optionsAsString);
            requestId = requestId3;
            optionsLoadedSuccessfully = true;
            options = options2;
        } catch (Exception e) {
            requestId = requestId3;
            Logger.e(TAG, String.format("Request %s, error loading options.", requestId), e);
            optionsLoadedSuccessfully = false;
            options = null;
        }
        ErrorInfo error2 = null;
        if (!optionsLoadedSuccessfully) {
            requestId2 = requestId;
            requestResultStatus = UploadStatus.FAILURE;
            ErrorInfo error3 = new ErrorInfo(9, "Options could not be loaded.");
            Logger.d(TAG, String.format("Failing request %s, cannot load options.", requestId2));
            error = error3;
        } else if (StringUtils.isNotBlank(uri)) {
            Payload payload = PayloadFactory.fromUri(uri);
            try {
                try {
                    if (payload != null) {
                        try {
                            try {
                                try {
                                    this.runningJobs.incrementAndGet();
                                    requestId2 = requestId;
                                    try {
                                        resultData = doProcess(requestId2, appContext, options, params, payload);
                                        try {
                                            requestResultStatus = UploadStatus.SUCCESS;
                                        } catch (ErrorRetrievingSignatureException e2) {
                                            e = e2;
                                            Logger.e(TAG, String.format("Error retrieving signature for request %s.", requestId2), e);
                                            requestResultStatus2 = UploadStatus.FAILURE;
                                            error2 = new ErrorInfo(4, e.getMessage());
                                            this.runningJobs.decrementAndGet();
                                            requestResultStatus = requestResultStatus2;
                                            error = error2;
                                            if (requestResultStatus.isFinal()) {
                                                if (requestResultStatus == UploadStatus.SUCCESS) {
                                                    this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                } else {
                                                    this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                }
                                                this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                            } else {
                                                this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                            }
                                            Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                            return requestResultStatus;
                                        } catch (EmptyByteArrayException e3) {
                                            e = e3;
                                            Logger.e(TAG, String.format("EmptyByteArrayException for request %s.", requestId2), e);
                                            requestResultStatus2 = UploadStatus.FAILURE;
                                            error2 = new ErrorInfo(10, e.getMessage());
                                            this.runningJobs.decrementAndGet();
                                            requestResultStatus = requestResultStatus2;
                                            error = error2;
                                            if (requestResultStatus.isFinal()) {
                                                if (requestResultStatus == UploadStatus.SUCCESS) {
                                                    this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                } else {
                                                    this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                }
                                                this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                            } else {
                                                this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                            }
                                            Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                            return requestResultStatus;
                                        } catch (FileNotFoundException e4) {
                                            e = e4;
                                            Logger.e(TAG, String.format("FileNotFoundException for request %s.", requestId2), e);
                                            requestResultStatus2 = UploadStatus.FAILURE;
                                            error2 = new ErrorInfo(1, e.getMessage());
                                            this.runningJobs.decrementAndGet();
                                            requestResultStatus = requestResultStatus2;
                                            error = error2;
                                            if (requestResultStatus.isFinal()) {
                                                if (requestResultStatus == UploadStatus.SUCCESS) {
                                                    this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                } else {
                                                    this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                }
                                                this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                            } else {
                                                this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                            }
                                            Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                            return requestResultStatus;
                                        } catch (LocalUriNotFoundException e5) {
                                            e = e5;
                                            Logger.e(TAG, String.format("LocalUriNotFoundException for request %s.", requestId2), e);
                                            requestResultStatus2 = UploadStatus.FAILURE;
                                            error2 = new ErrorInfo(2, e.getMessage());
                                            this.runningJobs.decrementAndGet();
                                            requestResultStatus = requestResultStatus2;
                                            error = error2;
                                            if (requestResultStatus.isFinal()) {
                                                if (requestResultStatus == UploadStatus.SUCCESS) {
                                                    this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                } else {
                                                    this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                }
                                                this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                            } else {
                                                this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                            }
                                            Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                            return requestResultStatus;
                                        } catch (ResourceNotFoundException e6) {
                                            e = e6;
                                            Logger.e(TAG, String.format("ResourceNotFoundException for request %s.", requestId2), e);
                                            error2 = new ErrorInfo(3, e.getMessage());
                                            requestResultStatus = UploadStatus.FAILURE;
                                            this.runningJobs.decrementAndGet();
                                            error = error2;
                                            if (requestResultStatus.isFinal()) {
                                                if (requestResultStatus == UploadStatus.SUCCESS) {
                                                    this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                } else {
                                                    this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                }
                                                this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                            } else {
                                                this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                            }
                                            Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                            return requestResultStatus;
                                        } catch (IOException e7) {
                                            e = e7;
                                            try {
                                                if (!(e instanceof InterruptedIOException) || (e instanceof SocketTimeoutException)) {
                                                    Logger.e(TAG, String.format("IOException for request %s.", requestId2), e);
                                                    if (isImmediate) {
                                                        error2 = new ErrorInfo(5, e.getMessage());
                                                        requestResultStatus = UploadStatus.FAILURE;
                                                    } else if (errorCount >= maxErrorRetries) {
                                                        error2 = getMaxRetryError(errorCount);
                                                        requestResultStatus = UploadStatus.FAILURE;
                                                    } else {
                                                        params.putInt(ERROR_COUNT_PARAM, errorCount + 1);
                                                        error2 = new ErrorInfo(5, e.getMessage());
                                                        requestResultStatus = UploadStatus.RESCHEDULE;
                                                    }
                                                } else {
                                                    Logger.e(TAG, String.format("InterruptedIO exception for request %s.", requestId2), e);
                                                    error2 = new ErrorInfo(11, "Request cancelled.");
                                                    requestResultStatus = UploadStatus.FAILURE;
                                                }
                                                this.runningJobs.decrementAndGet();
                                                error = error2;
                                                if (requestResultStatus.isFinal()) {
                                                    if (requestResultStatus == UploadStatus.SUCCESS) {
                                                        this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
                                                    } else {
                                                        this.callbackDispatcher.dispatchError(context, requestId2, error);
                                                    }
                                                    this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
                                                } else {
                                                    this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
                                                }
                                                Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
                                                return requestResultStatus;
                                            } catch (Throwable th) {
                                                e = th;
                                                this.runningJobs.decrementAndGet();
                                                throw e;
                                            }
                                        } catch (Exception e8) {
                                            e = e8;
                                            Logger.e(TAG, String.format("Unexpected exception for request %s.", requestId2), e);
                                            error2 = new ErrorInfo(6, e.getMessage());
                                            requestResultStatus = UploadStatus.FAILURE;
                                        }
                                    } catch (ErrorRetrievingSignatureException e9) {
                                        e = e9;
                                    } catch (EmptyByteArrayException e10) {
                                        e = e10;
                                    } catch (FileNotFoundException e11) {
                                        e = e11;
                                    } catch (LocalUriNotFoundException e12) {
                                        e = e12;
                                    } catch (ResourceNotFoundException e13) {
                                        e = e13;
                                    } catch (IOException e14) {
                                        e = e14;
                                    } catch (Exception e15) {
                                        e = e15;
                                    } catch (Throwable th2) {
                                        e = th2;
                                        this.runningJobs.decrementAndGet();
                                        throw e;
                                    }
                                } catch (ErrorRetrievingSignatureException e16) {
                                    e = e16;
                                    requestId2 = requestId;
                                } catch (EmptyByteArrayException e17) {
                                    e = e17;
                                    requestId2 = requestId;
                                } catch (FileNotFoundException e18) {
                                    e = e18;
                                    requestId2 = requestId;
                                } catch (LocalUriNotFoundException e19) {
                                    e = e19;
                                    requestId2 = requestId;
                                } catch (ResourceNotFoundException e20) {
                                    e = e20;
                                    requestId2 = requestId;
                                }
                            } catch (ErrorRetrievingSignatureException e21) {
                                e = e21;
                                requestId2 = requestId;
                            } catch (EmptyByteArrayException e22) {
                                e = e22;
                                requestId2 = requestId;
                            } catch (FileNotFoundException e23) {
                                e = e23;
                                requestId2 = requestId;
                            } catch (LocalUriNotFoundException e24) {
                                e = e24;
                                requestId2 = requestId;
                            } catch (ResourceNotFoundException e25) {
                                e = e25;
                                requestId2 = requestId;
                            }
                        } catch (IOException e26) {
                            e = e26;
                            requestId2 = requestId;
                        } catch (Exception e27) {
                            e = e27;
                            requestId2 = requestId;
                        } catch (Throwable th3) {
                            e = th3;
                        }
                        this.runningJobs.decrementAndGet();
                    } else {
                        requestId2 = requestId;
                        Logger.d(TAG, String.format("Failing request %s, payload cannot be loaded.", requestId2));
                        error2 = new ErrorInfo(7, "Request payload could not be loaded.");
                        requestResultStatus = UploadStatus.FAILURE;
                    }
                    error = error2;
                } catch (Throwable th4) {
                    e = th4;
                }
            } catch (Throwable th5) {
                e = th5;
            }
        } else {
            requestId2 = requestId;
            requestResultStatus = UploadStatus.FAILURE;
            ErrorInfo error4 = new ErrorInfo(8, "Request payload is empty.");
            Logger.d(TAG, String.format("Failing request %s, Uri is empty.", requestId2));
            error = error4;
        }
        if (requestResultStatus.isFinal()) {
            if (requestResultStatus == UploadStatus.SUCCESS) {
                this.callbackDispatcher.dispatchSuccess(context, requestId2, resultData);
            } else {
                this.callbackDispatcher.dispatchError(context, requestId2, error);
            }
            this.callbackDispatcher.wakeListenerServiceWithRequestFinished(context, requestId2, requestResultStatus);
        } else {
            this.callbackDispatcher.dispatchReschedule(context, requestId2, error);
        }
        Logger.i(TAG, String.format("Finished processing request %s, result: %s", requestId2, requestResultStatus));
        return requestResultStatus;
    }

    private ErrorInfo getMaxRetryError(int errorCount) {
        String message = String.format(Locale.getDefault(), "Request reached max retries allowed (%d).", Integer.valueOf(errorCount));
        ErrorInfo error = new ErrorInfo(13, message);
        return error;
    }

    private Map doProcess(String requestId, Context appContext, Map<String, Object> options, RequestParams params, Payload payload) throws Throwable {
        String uploadUniqueId;
        int bufferSize;
        String str;
        String str2;
        ProcessorCallback processorCallback;
        String str3;
        SignatureProvider signatureProvider;
        Logger.d(TAG, String.format("Starting upload for request %s", requestId));
        Object preparedPayload = payload.prepare(appContext);
        long actualTotalBytes = payload.getLength(appContext);
        long offset = params.getLong(TypedValues.CycleType.S_WAVE_OFFSET, 0L);
        int defaultBufferSize = options.containsKey("chunk_size") ? ((Integer) options.get("chunk_size")).intValue() : Uploader.BUFFER_SIZE;
        if (offset > 0) {
            int bufferSize2 = params.getInt("original_buffer_size", defaultBufferSize);
            uploadUniqueId = params.getString("original_upload_id", null);
            bufferSize = bufferSize2;
        } else {
            int bufferSize3 = ObjectUtils.asInteger(options.get("chunk_size"), Integer.valueOf(defaultBufferSize)).intValue();
            uploadUniqueId = new Cloudinary().randomPublicId();
            bufferSize = bufferSize3;
        }
        if (!MediaManager.get().hasCredentials() && !Boolean.TRUE.equals(options.get("unsigned")) && (signatureProvider = MediaManager.get().getSignatureProvider()) != null) {
            try {
                Signature signature = signatureProvider.provideSignature(options);
                options.put("signature", signature.getSignature());
                options.put("timestamp", Long.valueOf(signature.getTimestamp()));
                options.put("api_key", signature.getApiKey());
            } catch (Exception e) {
                throw new ErrorRetrievingSignatureException("Could not retrieve signature from the given provider: " + signatureProvider.getName(), e);
            }
        }
        ProcessorCallback processorCallback2 = new ProcessorCallback(actualTotalBytes, offset, this.callbackDispatcher, requestId);
        try {
            Uploader uploader = MediaManager.get().getCloudinary().uploader();
            str = TypedValues.CycleType.S_WAVE_OFFSET;
            str2 = "original_buffer_size";
            processorCallback = processorCallback2;
            str3 = "original_upload_id";
            try {
                Map mapUploadLarge = uploader.uploadLarge(preparedPayload, options, bufferSize, offset, uploadUniqueId, processorCallback);
                params.putInt(str2, bufferSize);
                params.putLong(str, processorCallback.bytesUploaded - (processorCallback.bytesUploaded % ((long) bufferSize)));
                params.putString(str3, uploadUniqueId);
                return mapUploadLarge;
            } catch (Throwable th) {
                th = th;
                params.putInt(str2, bufferSize);
                Throwable th2 = th;
                params.putLong(str, processorCallback.bytesUploaded - (processorCallback.bytesUploaded % ((long) bufferSize)));
                params.putString(str3, uploadUniqueId);
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            str = TypedValues.CycleType.S_WAVE_OFFSET;
            str2 = r8;
            processorCallback = processorCallback2;
            str3 = "original_upload_id";
        }
    }

    private static final class ProcessorCallback implements ProgressCallback {
        long bytesNotified;
        long bytesUploaded;
        private final CallbackDispatcher dispatcher;
        final long notifyThrottlingStepSize;
        String requestId;
        long totalBytes;

        ProcessorCallback(long totalBytes, long offset, CallbackDispatcher dispatcher, String requestId) {
            this.notifyThrottlingStepSize = totalBytes > 0 ? totalBytes / 100 : 512000L;
            this.totalBytes = totalBytes;
            this.bytesNotified = offset;
            this.bytesUploaded = offset;
            this.dispatcher = dispatcher;
            this.requestId = requestId;
        }

        @Override // com.cloudinary.ProgressCallback
        public void onProgress(long bytes, long totalBytes) {
            this.bytesUploaded = bytes;
            if (this.bytesNotified + this.notifyThrottlingStepSize < bytes || totalBytes == this.totalBytes) {
                this.bytesNotified += this.notifyThrottlingStepSize;
                this.dispatcher.dispatchProgress(this.requestId, bytes, this.totalBytes);
            }
        }
    }
}
