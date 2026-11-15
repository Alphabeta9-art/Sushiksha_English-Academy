package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import androidx.collection.SieveCacheKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: DefaultNativeMemoryChunkPoolParams.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/memory/DefaultNativeMemoryChunkPoolParams;", "", "()V", "LARGE_BUCKET_LENGTH", "", "SMALL_BUCKET_LENGTH", "maxSizeHardCap", "getMaxSizeHardCap", "()I", "maxSizeSoftCap", "getMaxSizeSoftCap", "get", "Lcom/facebook/imagepipeline/memory/PoolParams;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultNativeMemoryChunkPoolParams {
    public static final DefaultNativeMemoryChunkPoolParams INSTANCE = new DefaultNativeMemoryChunkPoolParams();
    private static final int LARGE_BUCKET_LENGTH = 2;
    private static final int SMALL_BUCKET_LENGTH = 5;

    private DefaultNativeMemoryChunkPoolParams() {
    }

    @JvmStatic
    public static final PoolParams get() {
        SparseIntArray DEFAULT_BUCKETS = new SparseIntArray();
        DEFAULT_BUCKETS.put(1024, 5);
        DEFAULT_BUCKETS.put(2048, 5);
        DEFAULT_BUCKETS.put(4096, 5);
        DEFAULT_BUCKETS.put(8192, 5);
        DEFAULT_BUCKETS.put(16384, 5);
        DEFAULT_BUCKETS.put(32768, 5);
        DEFAULT_BUCKETS.put(65536, 5);
        DEFAULT_BUCKETS.put(131072, 5);
        DEFAULT_BUCKETS.put(262144, 2);
        DEFAULT_BUCKETS.put(524288, 2);
        DEFAULT_BUCKETS.put(1048576, 2);
        return new PoolParams(INSTANCE.getMaxSizeSoftCap(), INSTANCE.getMaxSizeHardCap(), DEFAULT_BUCKETS);
    }

    private final int getMaxSizeSoftCap() {
        int maxMemory = (int) Math.min(Runtime.getRuntime().maxMemory(), SieveCacheKt.NodeLinkMask);
        if (maxMemory < 16777216) {
            return 3145728;
        }
        if (maxMemory < 33554432) {
            return 6291456;
        }
        return 12582912;
    }

    private final int getMaxSizeHardCap() {
        int maxMemory = (int) Math.min(Runtime.getRuntime().maxMemory(), SieveCacheKt.NodeLinkMask);
        if (maxMemory < 16777216) {
            return maxMemory / 2;
        }
        return (maxMemory / 4) * 3;
    }
}
