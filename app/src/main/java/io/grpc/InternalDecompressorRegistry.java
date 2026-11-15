package io.grpc;

/* JADX INFO: loaded from: classes13.dex */
public final class InternalDecompressorRegistry {
    private InternalDecompressorRegistry() {
    }

    public static byte[] getRawAdvertisedMessageEncodings(DecompressorRegistry reg) {
        return reg.getRawAdvertisedMessageEncodings();
    }
}
