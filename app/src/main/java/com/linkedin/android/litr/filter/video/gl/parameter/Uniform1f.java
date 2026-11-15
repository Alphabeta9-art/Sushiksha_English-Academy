package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform1f extends ShaderParameter {
    private float value;

    public Uniform1f(String name, float value) {
        super(0, name);
        this.value = value;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform1f(getLocation(glProgram), this.value);
    }
}
