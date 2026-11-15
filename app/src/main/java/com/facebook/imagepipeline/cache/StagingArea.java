package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class StagingArea {
    private static final Class<?> TAG = StagingArea.class;
    private Map<CacheKey, EncodedImage> mMap = new HashMap();

    private StagingArea() {
    }

    public static StagingArea getInstance() {
        return new StagingArea();
    }

    public synchronized void put(CacheKey key, EncodedImage encodedImage) {
        Preconditions.checkNotNull(key);
        Preconditions.checkArgument(Boolean.valueOf(EncodedImage.isValid(encodedImage)));
        EncodedImage oldEntry = this.mMap.put(key, EncodedImage.cloneOrNull(encodedImage));
        EncodedImage.closeSafely(oldEntry);
        logStats();
    }

    public void clearAll() {
        List<EncodedImage> old;
        synchronized (this) {
            old = new ArrayList<>(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < old.size(); i++) {
            EncodedImage encodedImage = old.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public boolean remove(CacheKey key) {
        EncodedImage encodedImage;
        Preconditions.checkNotNull(key);
        synchronized (this) {
            encodedImage = this.mMap.remove(key);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            return encodedImage.isValid();
        } finally {
            encodedImage.close();
        }
    }

    public synchronized boolean remove(CacheKey key, EncodedImage encodedImage) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(encodedImage);
        Preconditions.checkArgument(Boolean.valueOf(EncodedImage.isValid(encodedImage)));
        EncodedImage oldValue = this.mMap.get(key);
        if (oldValue == null) {
            return false;
        }
        CloseableReference<PooledByteBuffer> oldRef = oldValue.getByteBufferRef();
        CloseableReference<PooledByteBuffer> ref = encodedImage.getByteBufferRef();
        if (oldRef != null && ref != null) {
            try {
                if (oldRef.get() == ref.get()) {
                    this.mMap.remove(key);
                    CloseableReference.closeSafely(ref);
                    CloseableReference.closeSafely(oldRef);
                    EncodedImage.closeSafely(oldValue);
                    logStats();
                    return true;
                }
            } catch (Throwable th) {
                CloseableReference.closeSafely(ref);
                CloseableReference.closeSafely(oldRef);
                EncodedImage.closeSafely(oldValue);
                throw th;
            }
        }
        CloseableReference.closeSafely(ref);
        CloseableReference.closeSafely(oldRef);
        EncodedImage.closeSafely(oldValue);
        return false;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0050 */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.facebook.imagepipeline.image.EncodedImage get(com.facebook.cache.common.CacheKey r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.facebook.common.internal.Preconditions.checkNotNull(r8)     // Catch: java.lang.Throwable -> L56
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r0 = r7.mMap     // Catch: java.lang.Throwable -> L56
            java.lang.Object r0 = r0.get(r8)     // Catch: java.lang.Throwable -> L56
            com.facebook.imagepipeline.image.EncodedImage r0 = (com.facebook.imagepipeline.image.EncodedImage) r0     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L52
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L56
            boolean r1 = com.facebook.imagepipeline.image.EncodedImage.isValid(r0)     // Catch: java.lang.Throwable -> L4c
            if (r1 != 0) goto L40
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r1 = r7.mMap     // Catch: java.lang.Throwable -> L3d
            r1.remove(r8)     // Catch: java.lang.Throwable -> L3d
            java.lang.Class<?> r1 = com.facebook.imagepipeline.cache.StagingArea.TAG     // Catch: java.lang.Throwable -> L3d
            java.lang.String r2 = "Found closed reference %d for key %s (%d)"
            int r3 = java.lang.System.identityHashCode(r0)     // Catch: java.lang.Throwable -> L3d
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r8.getUriString()     // Catch: java.lang.Throwable -> L3d
            int r5 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> L3d
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L3d
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4, r5}     // Catch: java.lang.Throwable -> L3d
            com.facebook.common.logging.FLog.w(r1, r2, r3)     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r7)
            r1 = 0
            return r1
        L3d:
            r1 = move-exception
            r2 = r0
            goto L4e
        L40:
            com.facebook.imagepipeline.image.EncodedImage r1 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r0)     // Catch: java.lang.Throwable -> L4c
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L47
            r0 = r1
            goto L52
        L47:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L4e
        L4c:
            r1 = move-exception
            r2 = r0
        L4e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L50
            throw r1     // Catch: java.lang.Throwable -> L56
        L50:
            r1 = move-exception
            goto L4e
        L52:
            monitor-exit(r7)
            return r0
        L54:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L56
            throw r8
        L56:
            r8 = move-exception
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.StagingArea.get(com.facebook.cache.common.CacheKey):com.facebook.imagepipeline.image.EncodedImage");
    }

    public synchronized boolean containsKey(CacheKey key) {
        Preconditions.checkNotNull(key);
        if (!this.mMap.containsKey(key)) {
            return false;
        }
        EncodedImage storedEncodedImage = this.mMap.get(key);
        synchronized (storedEncodedImage) {
            try {
                if (EncodedImage.isValid(storedEncodedImage)) {
                    return true;
                }
                this.mMap.remove(key);
                FLog.w(TAG, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(storedEncodedImage)), key.getUriString(), Integer.valueOf(System.identityHashCode(key)));
                return false;
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    private synchronized void logStats() {
        FLog.v(TAG, "Count = %d", Integer.valueOf(this.mMap.size()));
    }
}
