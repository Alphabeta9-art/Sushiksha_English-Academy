package com.linkedin.android.litr.exception;

/* JADX INFO: loaded from: classes13.dex */
public abstract class MediaTransformationException extends Exception {
    private String jobId;

    public MediaTransformationException(Throwable cause) {
        super(cause);
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "Media transformation failed for job id: " + this.jobId;
    }
}
