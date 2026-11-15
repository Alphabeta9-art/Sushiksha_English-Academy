package com.linkedin.android.litr.filter.video.gl.parameter;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes13.dex */
public abstract class ShaderParameter {
    public static final int TYPE_ATTRIBUTE = 1;
    public static final int TYPE_UNIFORM = 0;
    public String name;
    public int type;

    public abstract void apply(int glProgram);

    protected ShaderParameter(int type, String name) {
        this.type = type;
        this.name = name;
    }

    protected int getLocation(int glProgram) {
        switch (this.type) {
            case 0:
                return GLES20.glGetUniformLocation(glProgram, this.name);
            case 1:
                return GLES20.glGetAttribLocation(glProgram, this.name);
            default:
                return -1;
        }
    }
}
