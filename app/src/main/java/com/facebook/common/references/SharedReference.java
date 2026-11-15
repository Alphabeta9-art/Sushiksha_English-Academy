package com.facebook.common.references;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class SharedReference<T> {
    private static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();
    private int mRefCount;

    @Nullable
    private final ResourceReleaser<T> mResourceReleaser;

    @Nullable
    private T mValue;

    public SharedReference(T t, @Nullable ResourceReleaser<T> resourceReleaser, boolean z) {
        this.mValue = (T) Preconditions.checkNotNull(t);
        this.mResourceReleaser = resourceReleaser;
        this.mRefCount = 1;
        if (z) {
            addLiveReference(t);
        }
    }

    public SharedReference(T value, ResourceReleaser<T> resourceReleaser) {
        this(value, resourceReleaser, false);
    }

    private static void addLiveReference(Object value) {
        synchronized (sLiveObjects) {
            Integer count = sLiveObjects.get(value);
            if (count == null) {
                sLiveObjects.put(value, 1);
            } else {
                sLiveObjects.put(value, Integer.valueOf(count.intValue() + 1));
            }
        }
    }

    private static void removeLiveReference(Object value) {
        synchronized (sLiveObjects) {
            Integer count = sLiveObjects.get(value);
            if (count == null) {
                FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", value.getClass());
            } else if (count.intValue() == 1) {
                sLiveObjects.remove(value);
            } else {
                sLiveObjects.put(value, Integer.valueOf(count.intValue() - 1));
            }
        }
    }

    @Nullable
    public synchronized T get() {
        return this.mValue;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }

    public static boolean isValid(@Nullable SharedReference<?> ref) {
        return ref != null && ref.isValid();
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public synchronized boolean addReferenceIfValid() {
        if (!isValid()) {
            return false;
        }
        addReference();
        return true;
    }

    public synchronized boolean deleteReferenceIfValid() {
        if (!isValid()) {
            return false;
        }
        deleteReference();
        return true;
    }

    public void deleteReference() {
        T deleted;
        if (decreaseRefCount() == 0) {
            synchronized (this) {
                deleted = this.mValue;
                this.mValue = null;
            }
            if (deleted != null) {
                if (this.mResourceReleaser != null) {
                    this.mResourceReleaser.release(deleted);
                }
                removeLiveReference(deleted);
            }
        }
    }

    private synchronized int decreaseRefCount() {
        ensureValid();
        Preconditions.checkArgument(Boolean.valueOf(this.mRefCount > 0));
        this.mRefCount--;
        return this.mRefCount;
    }

    private void ensureValid() {
        if (!isValid(this)) {
            throw new NullReferenceException();
        }
    }

    public synchronized int getRefCountTestOnly() {
        return this.mRefCount;
    }

    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public static String reportData() {
        return Objects.toStringHelper("SharedReference").add("live_objects_count", sLiveObjects.size()).toString();
    }
}
