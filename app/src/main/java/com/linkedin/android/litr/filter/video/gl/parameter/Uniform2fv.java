package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform2fv extends ShaderParameter {
    private FloatBuffer buffer;
    private int count;

    public Uniform2fv(String name, int count, float[] buffer) {
        this(name, count, FloatBuffer.wrap(buffer));
    }

    public Uniform2fv(String name, int count, FloatBuffer buffer) {
        super(0, name);
        this.count = count;
        this.buffer = buffer;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform2fv(getLocation(glProgram), this.count, this.buffer);
    }
}
