package com.google.firebase.firestore;

import com.facebook.common.statfs.StatFsHelper;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes12.dex */
public final class PersistentCacheSettings implements LocalCacheSettings {
    private final long sizeBytes;

    public static Builder newBuilder() {
        return new Builder();
    }

    private PersistentCacheSettings(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersistentCacheSettings that = (PersistentCacheSettings) o;
        if (this.sizeBytes == that.sizeBytes) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (this.sizeBytes ^ (this.sizeBytes >>> 32));
    }

    public String toString() {
        return "PersistentCacheSettings{sizeBytes=" + this.sizeBytes + AbstractJsonLexerKt.END_OBJ;
    }

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public static class Builder {
        private long sizeBytes;

        private Builder() {
            this.sizeBytes = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }

        public Builder setSizeBytes(long sizeBytes) {
            this.sizeBytes = sizeBytes;
            return this;
        }

        public PersistentCacheSettings build() {
            return new PersistentCacheSettings(this.sizeBytes);
        }
    }
}
