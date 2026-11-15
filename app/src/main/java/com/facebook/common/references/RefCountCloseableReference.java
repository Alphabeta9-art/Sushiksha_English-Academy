package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class RefCountCloseableReference<T> extends CloseableReference<T> {
    private RefCountCloseableReference(SharedReference<T> sharedReference, @Nullable CloseableReference.LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        super(sharedReference, leakHandler, stacktrace);
    }

    RefCountCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        super(t, resourceReleaser, leakHandler, stacktrace, false);
    }

    @Override // com.facebook.common.references.CloseableReference
    /* JADX INFO: renamed from: clone */
    public CloseableReference<T> mo8779clone() {
        Preconditions.checkState(isValid());
        return new RefCountCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace);
    }
}
