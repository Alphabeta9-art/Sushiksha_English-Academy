package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DefaultCloseableReference<T> extends CloseableReference<T> {
    private static final String TAG = "DefaultCloseableReference";

    private DefaultCloseableReference(SharedReference<T> sharedReference, @Nullable CloseableReference.LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        super(sharedReference, leakHandler, stacktrace);
    }

    DefaultCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        super(t, resourceReleaser, leakHandler, stacktrace, true);
    }

    @Override // com.facebook.common.references.CloseableReference
    /* JADX INFO: renamed from: clone */
    public CloseableReference<T> mo8779clone() {
        Preconditions.checkState(isValid());
        return new DefaultCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace != null ? new Throwable() : null);
    }

    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.mIsClosed) {
                    T ref = this.mSharedReference.get();
                    FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mSharedReference)), ref == null ? null : ref.getClass().getName());
                    if (this.mLeakHandler != null) {
                        this.mLeakHandler.reportLeak(this.mSharedReference, this.mStacktrace);
                    }
                    close();
                    super.finalize();
                    return;
                }
                super.finalize();
            }
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }
}
