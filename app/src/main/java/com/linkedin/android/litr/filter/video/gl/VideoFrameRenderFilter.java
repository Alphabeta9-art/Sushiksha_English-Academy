package com.linkedin.android.litr.filter.video.gl;

import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.linkedin.android.litr.filter.GlFrameRenderFilter;
import com.linkedin.android.litr.filter.Transform;
import com.linkedin.android.litr.filter.util.GlFilterUtil;
import com.linkedin.android.litr.filter.video.gl.parameter.ShaderParameter;
import com.linkedin.android.litr.render.GlRenderUtils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class VideoFrameRenderFilter implements GlFrameRenderFilter {
    protected static final String DEFAULT_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main()\n{\ngl_FragColor = texture2D(sTexture, vTextureCoord);\n}";
    protected static final String DEFAULT_VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main()\n{\ngl_Position = uMVPMatrix * aPosition;\nvTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}";
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
    private int aPositionHandle;
    private int aTextureHandle;
    private final String fragmentShader;
    private int fragmentShaderHandle;
    private int glProgram;
    private int inputFrameTextureHandle;
    private float[] inputFrameTextureMatrix;
    private float[] mvpMatrix;
    private int mvpMatrixHandle;
    private int mvpMatrixOffset;
    private final ShaderParameter[] shaderParameters;
    private final Transform transform;
    private FloatBuffer triangleVertices;
    private final float[] triangleVerticesData;
    private int uStMatrixHandle;
    private final String vertexShader;
    private int vertexShaderHandle;

    protected VideoFrameRenderFilter(String vertexShader, String fragmentShader, ShaderParameter[] shaderParameters) {
        this(vertexShader, fragmentShader, shaderParameters, null);
    }

    protected VideoFrameRenderFilter(String vertexShader, String fragmentShader, ShaderParameter[] shaderParameters, Transform transform) {
        this.mvpMatrix = new float[16];
        this.inputFrameTextureMatrix = new float[16];
        this.triangleVerticesData = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
        this.shaderParameters = shaderParameters;
        this.transform = transform != null ? transform : new Transform(new PointF(1.0f, 1.0f), new PointF(0.5f, 0.5f), 0.0f);
        this.triangleVertices = ByteBuffer.allocateDirect(this.triangleVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.triangleVertices.put(this.triangleVerticesData).position(0);
    }

    @Override // com.linkedin.android.litr.filter.GlFilter
    public void init() {
        Matrix.setIdentityM(this.inputFrameTextureMatrix, 0);
        this.vertexShaderHandle = GlRenderUtils.loadShader(35633, this.vertexShader);
        if (this.vertexShaderHandle == 0) {
            throw new RuntimeException("failed loading vertex shader");
        }
        this.fragmentShaderHandle = GlRenderUtils.loadShader(35632, this.fragmentShader);
        if (this.fragmentShaderHandle == 0) {
            release();
            throw new RuntimeException("failed loading fragment shader");
        }
        this.glProgram = GlRenderUtils.createProgram(this.vertexShaderHandle, this.fragmentShaderHandle);
        if (this.glProgram == 0) {
            release();
            throw new RuntimeException("failed creating glProgram");
        }
        this.aPositionHandle = GLES20.glGetAttribLocation(this.glProgram, "aPosition");
        GlRenderUtils.checkGlError("glGetAttribLocation aPosition");
        if (this.aPositionHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        this.aTextureHandle = GLES20.glGetAttribLocation(this.glProgram, "aTextureCoord");
        GlRenderUtils.checkGlError("glGetAttribLocation aTextureCoord");
        if (this.aTextureHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aTextureCoord");
        }
        this.mvpMatrixHandle = GLES20.glGetUniformLocation(this.glProgram, "uMVPMatrix");
        GlRenderUtils.checkGlError("glGetUniformLocation uMVPMatrix");
        if (this.mvpMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uMVPMatrix");
        }
        this.uStMatrixHandle = GLES20.glGetUniformLocation(this.glProgram, "uSTMatrix");
        GlRenderUtils.checkGlError("glGetUniformLocation uSTMatrix");
        if (this.uStMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uSTMatrix");
        }
    }

    @Override // com.linkedin.android.litr.filter.GlFilter
    public void setVpMatrix(float[] vpMatrix, int vpMatrixOffset) {
        this.mvpMatrix = GlFilterUtil.createFilterMvpMatrix(vpMatrix, this.transform);
        this.mvpMatrixOffset = vpMatrixOffset;
    }

    @Override // com.linkedin.android.litr.filter.GlFrameRenderFilter
    public void initInputFrameTexture(int textureHandle, float[] transformMatrix) {
        this.inputFrameTextureHandle = textureHandle;
        this.inputFrameTextureMatrix = transformMatrix;
    }

    @Override // com.linkedin.android.litr.filter.GlFilter
    public void apply(long presentationTimeNs) {
        this.triangleVertices.position(0);
        GLES20.glVertexAttribPointer(this.aPositionHandle, 3, 5126, false, 20, (Buffer) this.triangleVertices);
        GlRenderUtils.checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.aPositionHandle);
        GlRenderUtils.checkGlError("glEnableVertexAttribArray aPositionHandle");
        this.triangleVertices.position(3);
        GLES20.glVertexAttribPointer(this.aTextureHandle, 2, 5126, false, 20, (Buffer) this.triangleVertices);
        GlRenderUtils.checkGlError("glVertexAttribPointer aTextureHandle");
        GLES20.glEnableVertexAttribArray(this.aTextureHandle);
        GlRenderUtils.checkGlError("glEnableVertexAttribArray aTextureHandle");
        GlRenderUtils.checkGlError("onDrawFrame start");
        GLES20.glUseProgram(this.glProgram);
        GlRenderUtils.checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.inputFrameTextureHandle);
        if (this.shaderParameters != null) {
            for (ShaderParameter shaderParameter : this.shaderParameters) {
                shaderParameter.apply(this.glProgram);
            }
        }
        GLES20.glUniformMatrix4fv(this.mvpMatrixHandle, 1, false, this.mvpMatrix, this.mvpMatrixOffset);
        GLES20.glUniformMatrix4fv(this.uStMatrixHandle, 1, false, this.inputFrameTextureMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GlRenderUtils.checkGlError("glDrawArrays");
    }

    @Override // com.linkedin.android.litr.filter.GlFilter
    public void release() {
        GLES20.glDeleteProgram(this.glProgram);
        GLES20.glDeleteShader(this.vertexShaderHandle);
        GLES20.glDeleteShader(this.fragmentShaderHandle);
        GLES20.glDeleteBuffers(1, new int[]{this.aTextureHandle}, 0);
        this.glProgram = 0;
        this.vertexShaderHandle = 0;
        this.fragmentShaderHandle = 0;
        this.aTextureHandle = 0;
    }
}
