package androidx.media3.common;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;

/* JADX INFO: loaded from: classes11.dex */
public final class GlTextureInfo {
    public static final GlTextureInfo UNSET = new GlTextureInfo(-1, -1, -1, -1, -1);
    private final int fboId;
    private final int height;
    private boolean isReleased;
    private final int rboId;
    private final int texId;
    private final int width;

    public GlTextureInfo(int texId, int fboId, int rboId, int width, int height) {
        this.texId = texId;
        this.fboId = fboId;
        this.rboId = rboId;
        this.width = width;
        this.height = height;
    }

    public int getTexId() {
        Assertions.checkState(!this.isReleased);
        return this.texId;
    }

    public int getFboId() {
        Assertions.checkState(!this.isReleased);
        return this.fboId;
    }

    public int getRboId() {
        Assertions.checkState(!this.isReleased);
        return this.rboId;
    }

    public int getWidth() {
        Assertions.checkState(!this.isReleased);
        return this.width;
    }

    public int getHeight() {
        Assertions.checkState(!this.isReleased);
        return this.height;
    }

    public void release() throws GlUtil.GlException {
        this.isReleased = true;
        if (this.texId != -1) {
            GlUtil.deleteTexture(this.texId);
        }
        if (this.fboId != -1) {
            GlUtil.deleteFbo(this.fboId);
        }
        if (this.rboId != -1) {
            GlUtil.deleteRbo(this.rboId);
        }
    }
}
