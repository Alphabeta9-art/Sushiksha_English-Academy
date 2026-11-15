package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform2f extends ShaderParameter {
    private float value1;
    private float value2;

    public Uniform2f(String name, float value1, float value2) {
        super(0, name);
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform2f(getLocation(glProgram), this.value1, this.value2);
    }
}
