package com.linkedin.android.litr.filter.util;

import android.opengl.Matrix;
import com.linkedin.android.litr.filter.Transform;

/* JADX INFO: loaded from: classes13.dex */
public class GlFilterUtil {
    public static float[] createFilterMvpMatrix(float[] vpMatrix, Transform transform) {
        float videoAspectRatio;
        float scaleX;
        float scaleY;
        float translateX;
        float translateY;
        boolean isPortraitVideo = vpMatrix[0] == 0.0f;
        if (isPortraitVideo) {
            videoAspectRatio = 1.0f / Math.abs(vpMatrix[4]);
        } else {
            float videoAspectRatio2 = vpMatrix[0];
            videoAspectRatio = 1.0f / Math.abs(videoAspectRatio2);
        }
        if (isPortraitVideo) {
            scaleX = transform.size.x;
            scaleY = transform.size.y * videoAspectRatio;
        } else {
            scaleX = transform.size.x * videoAspectRatio;
            scaleY = transform.size.y;
        }
        if (isPortraitVideo) {
            translateX = (transform.position.x * 2.0f) - 1.0f;
            translateY = (1.0f - (transform.position.y * 2.0f)) * videoAspectRatio;
        } else {
            translateX = ((transform.position.x * 2.0f) - 1.0f) * videoAspectRatio;
            translateY = 1.0f - (transform.position.y * 2.0f);
        }
        float[] modelMatrix = new float[16];
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, translateX, translateY, 0.0f);
        Matrix.rotateM(modelMatrix, 0, transform.rotation, 0.0f, 0.0f, 1.0f);
        Matrix.scaleM(modelMatrix, 0, scaleX, scaleY, 1.0f);
        float[] mvpMatrix = new float[16];
        Matrix.multiplyMM(mvpMatrix, 0, vpMatrix, 0, modelMatrix, 0);
        return mvpMatrix;
    }
}
