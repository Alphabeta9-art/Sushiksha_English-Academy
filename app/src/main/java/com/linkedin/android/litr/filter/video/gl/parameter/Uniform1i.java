package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform1i extends ShaderParameter {
    private int value;

    public Uniform1i(String name, int value) {
        super(0, name);
        this.value = value;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform1i(getLocation(glProgram), this.value);
    }
}
