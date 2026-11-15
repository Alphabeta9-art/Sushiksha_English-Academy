package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform4i extends ShaderParameter {
    private int value1;
    private int value2;
    private int value3;
    private int value4;

    public Uniform4i(String name, int value1, int value2, int value3, int value4) {
        super(0, name);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform4i(getLocation(glProgram), this.value1, this.value2, this.value3, this.value4);
    }
}
