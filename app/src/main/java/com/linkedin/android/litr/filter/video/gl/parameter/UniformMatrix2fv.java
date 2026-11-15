package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class UniformMatrix2fv extends ShaderParameter {
    private FloatBuffer buffer;
    private int count;
    private float[] matrix;
    private int offset;
    private boolean transpose;

    public UniformMatrix2fv(String name, int count, boolean transpose, float[] matrix, int offset) {
        super(0, name);
        this.count = count;
        this.transpose = transpose;
        this.matrix = matrix;
        this.offset = offset;
    }

    public UniformMatrix2fv(String name, int count, boolean transpose, FloatBuffer buffer) {
        super(0, name);
        this.count = count;
        this.transpose = transpose;
        this.buffer = buffer;
    }

    @Override // com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter
    public void apply(int glProgram) {
        if (this.buffer != null) {
            GLES20.glUniformMatrix2fv(getLocation(glProgram), this.count, this.transpose, this.buffer);
        } else {
            GLES20.glUniformMatrix2fv(getLocation(glProgram), this.count, this.transpose, this.matrix, this.offset);
        }
    }
}
