package com.facebook.fresco.ui.common;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePerfState {

    @Nullable
    private Object mCallerContext;

    @Nullable
    private String mControllerId;

    @Nullable
    private DimensionsInfo mDimensionsInfo;

    @Nullable
    private Throwable mErrorThrowable;

    @Nullable
    private ControllerListener2.Extras mExtraData;

    @Nullable
    private Object mImageInfo;

    @Nullable
    private Object mImageRequest;
    private boolean mIsPrefetch;

    @Nullable
    private String mRequestId;
    private long mControllerSubmitTimeMs = -1;
    private long mControllerIntermediateImageSetTimeMs = -1;
    private long mControllerFinalImageSetTimeMs = -1;
    private long mControllerFailureTimeMs = -1;
    private long mControllerCancelTimeMs = -1;
    private long mImageRequestStartTimeMs = -1;
    private long mImageRequestEndTimeMs = -1;
    private int mOnScreenWidthPx = -1;
    private int mOnScreenHeightPx = -1;
    private ImageLoadStatus mImageLoadStatus = ImageLoadStatus.UNKNOWN;
    private VisibilityState mVisibilityState = VisibilityState.UNKNOWN;
    private long mVisibilityEventTimeMs = -1;
    private long mInvisibilityEventTimeMs = -1;
    private long mImageDrawTimeMs = -1;

    public void reset() {
        this.mRequestId = null;
        this.mImageRequest = null;
        this.mCallerContext = null;
        this.mImageInfo = null;
        this.mIsPrefetch = false;
        this.mOnScreenWidthPx = -1;
        this.mOnScreenHeightPx = -1;
        this.mErrorThrowable = null;
        this.mImageLoadStatus = ImageLoadStatus.UNKNOWN;
        this.mVisibilityState = VisibilityState.UNKNOWN;
        this.mDimensionsInfo = null;
        this.mExtraData = null;
        resetPointsTimestamps();
    }

    public void resetPointsTimestamps() {
        this.mImageRequestStartTimeMs = -1L;
        this.mImageRequestEndTimeMs = -1L;
        this.mControllerSubmitTimeMs = -1L;
        this.mControllerFinalImageSetTimeMs = -1L;
        this.mControllerFailureTimeMs = -1L;
        this.mControllerCancelTimeMs = -1L;
        this.mVisibilityEventTimeMs = -1L;
        this.mInvisibilityEventTimeMs = -1L;
        this.mImageDrawTimeMs = -1L;
    }

    public void setImageLoadStatus(ImageLoadStatus imageLoadStatus) {
        this.mImageLoadStatus = imageLoadStatus;
    }

    public ImageLoadStatus getImageLoadStatus() {
        return this.mImageLoadStatus;
    }

    public void setControllerId(@Nullable String controllerId) {
        this.mControllerId = controllerId;
    }

    public void setRequestId(@Nullable String requestId) {
        this.mRequestId = requestId;
    }

    public void setImageRequest(@Nullable Object imageRequest) {
        this.mImageRequest = imageRequest;
    }

    public void setCallerContext(@Nullable Object callerContext) {
        this.mCallerContext = callerContext;
    }

    public void setControllerSubmitTimeMs(long controllerSubmitTimeMs) {
        this.mControllerSubmitTimeMs = controllerSubmitTimeMs;
    }

    public void setControllerIntermediateImageSetTimeMs(long controllerIntermediateImageSetTimeMs) {
        this.mControllerIntermediateImageSetTimeMs = controllerIntermediateImageSetTimeMs;
    }

    public void setControllerFinalImageSetTimeMs(long controllerFinalImageSetTimeMs) {
        this.mControllerFinalImageSetTimeMs = controllerFinalImageSetTimeMs;
    }

    public void setControllerFailureTimeMs(long controllerFailureTimeMs) {
        this.mControllerFailureTimeMs = controllerFailureTimeMs;
    }

    public void setControllerCancelTimeMs(long controllerCancelTimeMs) {
        this.mControllerCancelTimeMs = controllerCancelTimeMs;
    }

    public void setImageRequestStartTimeMs(long imageRequestStartTimeMs) {
        this.mImageRequestStartTimeMs = imageRequestStartTimeMs;
    }

    public void setImageRequestEndTimeMs(long imageRequestEndTimeMs) {
        this.mImageRequestEndTimeMs = imageRequestEndTimeMs;
    }

    public void setVisibilityEventTimeMs(long visibilityEventTimeMs) {
        this.mVisibilityEventTimeMs = visibilityEventTimeMs;
    }

    public void setInvisibilityEventTimeMs(long invisibilityEventTimeMs) {
        this.mInvisibilityEventTimeMs = invisibilityEventTimeMs;
    }

    public void setPrefetch(boolean prefetch) {
        this.mIsPrefetch = prefetch;
    }

    public void setImageInfo(@Nullable Object imageInfo) {
        this.mImageInfo = imageInfo;
    }

    public void setOnScreenWidth(int onScreenWidthPx) {
        this.mOnScreenWidthPx = onScreenWidthPx;
    }

    public void setOnScreenHeight(int onScreenHeightPx) {
        this.mOnScreenHeightPx = onScreenHeightPx;
    }

    public void setErrorThrowable(@Nullable Throwable errorThrowable) {
        this.mErrorThrowable = errorThrowable;
    }

    public void setVisible(boolean visible) {
        this.mVisibilityState = visible ? VisibilityState.VISIBLE : VisibilityState.INVISIBLE;
    }

    public void setImageDrawTimeMs(long imageDrawTimeMs) {
        this.mImageDrawTimeMs = imageDrawTimeMs;
    }

    public ImagePerfData snapshot() {
        return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mIsPrefetch, this.mOnScreenWidthPx, this.mOnScreenHeightPx, this.mErrorThrowable, this.mVisibilityState, this.mVisibilityEventTimeMs, this.mInvisibilityEventTimeMs, this.mImageDrawTimeMs, this.mDimensionsInfo, this.mExtraData);
    }

    public long getImageDrawTimeMs() {
        return this.mImageDrawTimeMs;
    }

    public void setDimensionsInfo(DimensionsInfo dimensionsInfo) {
        this.mDimensionsInfo = dimensionsInfo;
    }

    @Nullable
    public DimensionsInfo getDimensionsInfo() {
        return this.mDimensionsInfo;
    }

    public void setExtraData(@Nullable ControllerListener2.Extras extraData) {
        this.mExtraData = extraData;
    }

    @Nullable
    public Object getExtraData() {
        return this.mExtraData;
    }
}
