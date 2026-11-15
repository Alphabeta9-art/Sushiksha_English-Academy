package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform2i extends ShaderParameter {
    private int value1;
    private int value2;

    public Uniform2i(String name, int value1, int value2) {
        super(0, name);
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform2i(getLocation(glProgram), this.value1, this.value2);
    }
}
