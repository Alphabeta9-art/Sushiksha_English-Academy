package com.cloudinary.android.preprocess;

/* JADX INFO: loaded from: classes11.dex */
public class Parameters {
    private int frameRate;
    private int height;
    private int keyFramesInterval;
    private String requestId;
    private int targetAudioBitrateKbps;
    private int targetVideoBitrateKbps;
    private int width;

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTargetVideoBitrateKbps() {
        return this.targetVideoBitrateKbps;
    }

    public void setTargetVideoBitrateKbps(int targetVideoBitrateKbps) {
        this.targetVideoBitrateKbps = targetVideoBitrateKbps;
    }

    public int getKeyFramesInterval() {
        return this.keyFramesInterval;
    }

    public void setKeyFramesInterval(int keyFramesInterval) {
        this.keyFramesInterval = keyFramesInterval;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public int getTargetAudioBitrateKbps() {
        return this.targetAudioBitrateKbps;
    }

    public void setTargetAudioBitrateKbps(int targetAudioBitrateKbps) {
        this.targetAudioBitrateKbps = targetAudioBitrateKbps;
    }
}
