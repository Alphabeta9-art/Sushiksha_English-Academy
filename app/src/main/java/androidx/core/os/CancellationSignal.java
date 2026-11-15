package androidx.core.os;

/* JADX INFO: loaded from: classes10.dex */
@Deprecated
public final class CancellationSignal {
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    public interface OnCancelListener {
        void onCancel();
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this) {
            z = this.mIsCanceled;
        }
        return z;
    }

    public void throwIfCanceled() {
        if (isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0037, code lost:
    
        throw r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void cancel() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.mIsCanceled     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L7
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            return
        L7:
            r0 = 1
            r4.mIsCanceled = r0     // Catch: java.lang.Throwable -> L3c
            r4.mCancelInProgress = r0     // Catch: java.lang.Throwable -> L3c
            androidx.core.os.CancellationSignal$OnCancelListener r0 = r4.mOnCancelListener     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r1 = r4.mCancellationSignalObj     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            r2 = 0
            if (r0 == 0) goto L17
            r0.onCancel()     // Catch: java.lang.Throwable -> L20
        L17:
            if (r1 == 0) goto L2d
            r3 = r1
            android.os.CancellationSignal r3 = (android.os.CancellationSignal) r3     // Catch: java.lang.Throwable -> L20
            r3.cancel()     // Catch: java.lang.Throwable -> L20
            goto L2d
        L20:
            r3 = move-exception
            monitor-enter(r4)
            r4.mCancelInProgress = r2     // Catch: java.lang.Throwable -> L2b
            r4.notifyAll()     // Catch: java.lang.Throwable -> L2b
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            throw r3
        L29:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            goto L37
        L2b:
            r2 = move-exception
            goto L29
        L2d:
            monitor-enter(r4)
            r4.mCancelInProgress = r2     // Catch: java.lang.Throwable -> L38
            r4.notifyAll()     // Catch: java.lang.Throwable -> L38
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L38
            return
        L36:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L38
        L37:
            throw r2
        L38:
            r2 = move-exception
            goto L36
        L3a:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            throw r0
        L3c:
            r0 = move-exception
            goto L3a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.os.CancellationSignal.cancel():void");
    }

    public void setOnCancelListener(OnCancelListener listener) {
        synchronized (this) {
            waitForCancelFinishedLocked();
            if (this.mOnCancelListener == listener) {
                return;
            }
            this.mOnCancelListener = listener;
            if (!this.mIsCanceled || listener == null) {
                return;
            }
            listener.onCancel();
        }
    }

    public Object getCancellationSignalObject() {
        Object obj;
        synchronized (this) {
            if (this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = new android.os.CancellationSignal();
                if (this.mIsCanceled) {
                    ((android.os.CancellationSignal) this.mCancellationSignalObj).cancel();
                }
            }
            obj = this.mCancellationSignalObj;
        }
        return obj;
    }

    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
