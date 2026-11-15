package com.linkedin.android.litr.filter;

/* JADX INFO: loaded from: classes13.dex */
public interface GlFilter {
    void apply(long presentationTimeNs);

    void init();

    void release();

    void setVpMatrix(float[] vpMatrix, int vpMatrixOffset);
}
