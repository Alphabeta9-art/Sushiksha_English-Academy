package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform4f extends ShaderParameter {
    private float value1;
    private float value2;
    private float value3;
    private float value4;

    public Uniform4f(String name, float value1, float value2, float value3, float value4) {
        super(0, name);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform4f(getLocation(glProgram), this.value1, this.value2, this.value3, this.value4);
    }
}
