package com.facebook.fresco.ui.common;

import com.facebook.common.internal.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePerfData {
    public static final int UNSET = -1;

    @Nullable
    private final Object mCallerContext;
    private final long mControllerCancelTimeMs;
    private final long mControllerFailureTimeMs;
    private final long mControllerFinalImageSetTimeMs;

    @Nullable
    private final String mControllerId;
    private final long mControllerIntermediateImageSetTimeMs;
    private final long mControllerSubmitTimeMs;

    @Nullable
    private final DimensionsInfo mDimensionsInfo;

    @Nullable
    private final Throwable mErrorThrowable;

    @Nullable
    private ControllerListener2.Extras mExtraData;
    private final long mImageDrawTimeMs;

    @Nullable
    private final Object mImageInfo;

    @Nullable
    private final Object mImageRequest;
    private final long mImageRequestEndTimeMs;
    private final long mImageRequestStartTimeMs;
    private final long mInvisibilityEventTimeMs;
    private final boolean mIsPrefetch;
    private final int mOnScreenHeightPx;
    private final int mOnScreenWidthPx;

    @Nullable
    private final String mRequestId;
    private final long mVisibilityEventTimeMs;
    private final VisibilityState mVisibilityState;

    public ImagePerfData(@Nullable String controllerId, @Nullable String requestId, @Nullable Object imageRequest, @Nullable Object callerContext, @Nullable Object imageInfo, long controllerSubmitTimeMs, long controllerIntermediateImageSetTimeMs, long controllerFinalImageSetTimeMs, long controllerFailureTimeMs, long controllerCancelTimeMs, long imageRequestStartTimeMs, long imageRequestEndTimeMs, boolean isPrefetch, int onScreenWidthPx, int onScreenHeightPx, @Nullable Throwable errorThrowable, VisibilityState visibilityState, long visibilityEventTimeMs, long invisibilityEventTime, long imageDrawTimeMs, @Nullable DimensionsInfo dimensionsInfo, @Nullable ControllerListener2.Extras extraData) {
        this.mControllerId = controllerId;
        this.mRequestId = requestId;
        this.mImageRequest = imageRequest;
        this.mCallerContext = callerContext;
        this.mImageInfo = imageInfo;
        this.mControllerSubmitTimeMs = controllerSubmitTimeMs;
        this.mControllerIntermediateImageSetTimeMs = controllerIntermediateImageSetTimeMs;
        this.mControllerFinalImageSetTimeMs = controllerFinalImageSetTimeMs;
        this.mControllerFailureTimeMs = controllerFailureTimeMs;
        this.mControllerCancelTimeMs = controllerCancelTimeMs;
        this.mImageRequestStartTimeMs = imageRequestStartTimeMs;
        this.mImageRequestEndTimeMs = imageRequestEndTimeMs;
        this.mIsPrefetch = isPrefetch;
        this.mOnScreenWidthPx = onScreenWidthPx;
        this.mOnScreenHeightPx = onScreenHeightPx;
        this.mErrorThrowable = errorThrowable;
        this.mVisibilityState = visibilityState;
        this.mVisibilityEventTimeMs = visibilityEventTimeMs;
        this.mInvisibilityEventTimeMs = invisibilityEventTime;
        this.mImageDrawTimeMs = imageDrawTimeMs;
        this.mDimensionsInfo = dimensionsInfo;
        this.mExtraData = extraData;
    }

    public long getImageDrawTimeMs() {
        return this.mImageDrawTimeMs;
    }

    @Nullable
    public String getControllerId() {
        return this.mControllerId;
    }

    @Nullable
    public String getRequestId() {
        return this.mRequestId;
    }

    @Nullable
    public Object getImageRequest() {
        return this.mImageRequest;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Nullable
    public Object getImageInfo() {
        return this.mImageInfo;
    }

    public long getControllerSubmitTimeMs() {
        return this.mControllerSubmitTimeMs;
    }

    public long getControllerIntermediateImageSetTimeMs() {
        return this.mControllerIntermediateImageSetTimeMs;
    }

    public long getControllerFinalImageSetTimeMs() {
        return this.mControllerFinalImageSetTimeMs;
    }

    public long getControllerFailureTimeMs() {
        return this.mControllerFailureTimeMs;
    }

    public long getImageRequestStartTimeMs() {
        return this.mImageRequestStartTimeMs;
    }

    public long getImageRequestEndTimeMs() {
        return this.mImageRequestEndTimeMs;
    }

    public boolean isPrefetch() {
        return this.mIsPrefetch;
    }

    public int getOnScreenWidthPx() {
        return this.mOnScreenWidthPx;
    }

    public int getOnScreenHeightPx() {
        return this.mOnScreenHeightPx;
    }

    @Nullable
    public Throwable getErrorThrowable() {
        return this.mErrorThrowable;
    }

    public long getFinalImageLoadTimeMs() {
        if (getImageRequestEndTimeMs() == -1 || getImageRequestStartTimeMs() == -1) {
            return -1L;
        }
        return getImageRequestEndTimeMs() - getImageRequestStartTimeMs();
    }

    public long getIntermediateImageLoadTimeMs() {
        return this.mControllerIntermediateImageSetTimeMs;
    }

    public VisibilityState getVisibilityState() {
        return this.mVisibilityState;
    }

    public long getVisibilityEventTimeMs() {
        return this.mVisibilityEventTimeMs;
    }

    public long getInvisibilityEventTimeMs() {
        return this.mInvisibilityEventTimeMs;
    }

    @Nullable
    public DimensionsInfo getDimensionsInfo() {
        return this.mDimensionsInfo;
    }

    @Nullable
    public ControllerListener2.Extras getExtraData() {
        return this.mExtraData;
    }

    public void setExtraData(ControllerListener2.Extras extraData) {
        this.mExtraData = extraData;
    }

    public String createDebugString() {
        return Objects.toStringHelper(this).add("controller ID", this.mControllerId).add("request ID", this.mRequestId).add("controller submit", this.mControllerSubmitTimeMs).add("controller final image", this.mControllerFinalImageSetTimeMs).add("controller failure", this.mControllerFailureTimeMs).add("controller cancel", this.mControllerCancelTimeMs).add("start time", this.mImageRequestStartTimeMs).add("end time", this.mImageRequestEndTimeMs).add("prefetch", this.mIsPrefetch).add("caller context", this.mCallerContext).add("image request", this.mImageRequest).add("image info", this.mImageInfo).add("on-screen width", this.mOnScreenWidthPx).add("on-screen height", this.mOnScreenHeightPx).add("visibility state", this.mVisibilityState).add("visibility event", this.mVisibilityEventTimeMs).add("invisibility event", this.mInvisibilityEventTimeMs).add("image draw event", this.mImageDrawTimeMs).add("dimensions info", this.mDimensionsInfo).add("extra data", this.mExtraData).toString();
    }
}
