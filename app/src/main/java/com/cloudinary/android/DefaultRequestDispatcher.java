package com.cloudinary.android;

import android.content.Context;
import com.cloudinary.android.callback.ErrorInfo;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
class DefaultRequestDispatcher implements RequestDispatcher {
    private static final String TAG = DefaultRequestDispatcher.class.getSimpleName();
    private final ImmediateRequestsRunner immediateRequestsRunner;
    private final BackgroundRequestStrategy strategy;
    private final SecureRandom rand = new SecureRandom();
    private final Set<String> abortedRequestIds = new HashSet();
    private final Object cancellationLock = new Object();

    DefaultRequestDispatcher(BackgroundRequestStrategy strategy, ImmediateRequestsRunner immediateRequestsRunner) {
        this.strategy = strategy;
        this.immediateRequestsRunner = immediateRequestsRunner;
    }

    @Override // com.cloudinary.android.RequestDispatcher
    public final String dispatch(UploadRequest request) {
        String requestId = request.getRequestId();
        int totalCount = this.strategy.getPendingImmediateJobsCount() + this.strategy.getRunningJobsCount();
        if (!request.getTimeWindow().isImmediate() && totalCount >= MediaManager.get().getGlobalUploadPolicy().getMaxConcurrentRequests()) {
            int minutes = this.rand.nextInt(10) + 10;
            request.defferByMinutes(minutes);
            Logger.d(TAG, String.format("Request %s deferred by %d minutes.", requestId, Integer.valueOf(minutes)));
        }
        Logger.d(TAG, String.format("Dispatching Request %s, scheduled start in %d minutes.", requestId, Long.valueOf(request.getTimeWindow().getMinLatencyOffsetMillis() / 60000)));
        synchronized (this.cancellationLock) {
            if (this.abortedRequestIds.remove(requestId)) {
                MediaManager.get().dispatchRequestError(null, requestId, new ErrorInfo(11, "Request cancelled"));
                return requestId;
            }
            this.strategy.doDispatch(request);
            return requestId;
        }
    }

    @Override // com.cloudinary.android.RequestDispatcher
    public String startNow(Context context, UploadRequest request) {
        String requestId = request.getRequestId();
        synchronized (this.cancellationLock) {
            if (this.abortedRequestIds.remove(requestId)) {
                MediaManager.get().dispatchRequestError(null, requestId, new ErrorInfo(11, "Request cancelled"));
                return requestId;
            }
            this.immediateRequestsRunner.runRequest(context, request);
            return requestId;
        }
    }

    @Override // com.cloudinary.android.RequestDispatcher
    public boolean cancelRequest(String requestId) {
        boolean cancelled;
        synchronized (this.cancellationLock) {
            cancelled = this.strategy.cancelRequest(requestId);
            if (!cancelled) {
                this.abortedRequestIds.add(requestId);
            }
        }
        return cancelled;
    }

    @Override // com.cloudinary.android.RequestDispatcher
    public void queueRoomFreed() {
        int room = (MediaManager.get().getGlobalUploadPolicy().getMaxConcurrentRequests() - this.strategy.getPendingImmediateJobsCount()) - this.strategy.getRunningJobsCount();
        Logger.d(TAG, String.format("queueRoomFreed called, there's room for %d requests.", Integer.valueOf(room)));
        if (room > 0) {
            this.strategy.executeRequestsNow(room);
        }
    }

    @Override // com.cloudinary.android.RequestDispatcher
    public int cancelAllRequests() {
        return this.strategy.cancelAllRequests();
    }
}
