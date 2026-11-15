package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform3f extends ShaderParameter {
    private float value1;
    private float value2;
    private float value3;

    public Uniform3f(String name, float value1, float value2, float value3) {
        super(0, name);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform3f(getLocation(glProgram), this.value1, this.value2, this.value3);
    }
}
