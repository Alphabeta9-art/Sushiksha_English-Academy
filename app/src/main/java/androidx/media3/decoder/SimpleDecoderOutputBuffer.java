package androidx.media3.decoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes11.dex */
public class SimpleDecoderOutputBuffer extends DecoderOutputBuffer {
    public ByteBuffer data;
    private final DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> owner;

    public SimpleDecoderOutputBuffer(DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> owner) {
        this.owner = owner;
    }

    public ByteBuffer init(long timeUs, int size) {
        this.timeUs = timeUs;
        if (this.data == null || this.data.capacity() < size) {
            this.data = ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
        }
        this.data.position(0);
        this.data.limit(size);
        return this.data;
    }

    @Override // androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        if (this.data != null) {
            this.data.clear();
        }
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer
    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
