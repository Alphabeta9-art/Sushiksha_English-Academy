package androidx.media3.common.util;

import java.lang.Exception;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes11.dex */
public abstract class RunnableFutureTask<R, E extends Exception> implements RunnableFuture<R> {
    private boolean canceled;
    private Exception exception;
    private R result;
    private Thread workThread;
    private final ConditionVariable started = new ConditionVariable();
    private final ConditionVariable finished = new ConditionVariable();
    private final Object cancelLock = new Object();

    protected abstract R doWork() throws Exception;

    protected RunnableFutureTask() {
    }

    public final void blockUntilStarted() {
        this.started.blockUninterruptible();
    }

    public final void blockUntilFinished() {
        this.finished.blockUninterruptible();
    }

    @Override // java.util.concurrent.Future
    public final R get() throws ExecutionException, InterruptedException {
        this.finished.block();
        return getResult();
    }

    @Override // java.util.concurrent.Future
    public final R get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        long timeoutMs = TimeUnit.MILLISECONDS.convert(timeout, unit);
        if (!this.finished.block(timeoutMs)) {
            throw new TimeoutException();
        }
        return getResult();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean interruptIfRunning) {
        synchronized (this.cancelLock) {
            if (this.canceled || this.finished.isOpen()) {
                return false;
            }
            this.canceled = true;
            cancelWork();
            Thread workThread = this.workThread;
            if (workThread != null) {
                if (interruptIfRunning) {
                    workThread.interrupt();
                }
            } else {
                this.started.open();
                this.finished.open();
            }
            return true;
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.finished.isOpen();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.canceled;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0057, code lost:
    
        throw r0;
     */
    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.cancelLock
            monitor-enter(r0)
            boolean r1 = r4.canceled     // Catch: java.lang.Throwable -> L5c
            if (r1 == 0) goto L9
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5c
            return
        L9:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L5c
            r4.workThread = r1     // Catch: java.lang.Throwable -> L5c
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5c
            androidx.media3.common.util.ConditionVariable r0 = r4.started
            r0.open()
            r0 = 0
            java.lang.Object r1 = r4.doWork()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L46
            r4.result = r1     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L46
            java.lang.Object r1 = r4.cancelLock
            monitor-enter(r1)
            androidx.media3.common.util.ConditionVariable r2 = r4.finished     // Catch: java.lang.Throwable -> L2d
            r2.open()     // Catch: java.lang.Throwable -> L2d
            r4.workThread = r0     // Catch: java.lang.Throwable -> L2d
            java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L2d
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2d
            goto L40
        L2b:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2d
            goto L57
        L2d:
            r0 = move-exception
            goto L2b
        L2f:
            r1 = move-exception
            r4.exception = r1     // Catch: java.lang.Throwable -> L46
            java.lang.Object r1 = r4.cancelLock
            monitor-enter(r1)
            androidx.media3.common.util.ConditionVariable r2 = r4.finished     // Catch: java.lang.Throwable -> L44
            r2.open()     // Catch: java.lang.Throwable -> L44
            r4.workThread = r0     // Catch: java.lang.Throwable -> L44
            java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L44
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L44
        L40:
            return
        L42:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L44
            goto L57
        L44:
            r0 = move-exception
            goto L42
        L46:
            r1 = move-exception
            java.lang.Object r2 = r4.cancelLock
            monitor-enter(r2)
            androidx.media3.common.util.ConditionVariable r3 = r4.finished     // Catch: java.lang.Throwable -> L58
            r3.open()     // Catch: java.lang.Throwable -> L58
            r4.workThread = r0     // Catch: java.lang.Throwable -> L58
            java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L58
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L58
            throw r1
        L56:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L58
        L57:
            throw r0
        L58:
            r0 = move-exception
            goto L56
        L5a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5c
            throw r1
        L5c:
            r1 = move-exception
            goto L5a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.RunnableFutureTask.run():void");
    }

    protected void cancelWork() {
    }

    private R getResult() throws ExecutionException {
        if (this.canceled) {
            throw new CancellationException();
        }
        if (this.exception != null) {
            throw new ExecutionException(this.exception);
        }
        return this.result;
    }
}
