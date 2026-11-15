package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class Uniform4iv extends ShaderParameter {
    private IntBuffer buffer;
    private int count;

    public Uniform4iv(String name, int count, int[] buffer) {
        this(name, count, IntBuffer.wrap(buffer));
    }

    public Uniform4iv(String name, int count, IntBuffer buffer) {
        super(0, name);
        this.count = count;
        this.buffer = buffer;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        GLES20.glUniform4iv(getLocation(glProgram), this.count, this.buffer);
    }
}
