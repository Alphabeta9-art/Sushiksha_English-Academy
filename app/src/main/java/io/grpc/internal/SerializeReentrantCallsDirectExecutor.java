package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes13.dex */
class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque<Runnable> taskQueue;

    SerializeReentrantCallsDirectExecutor() {
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0034 A[DONT_GENERATE] */
    @Override // java.util.concurrent.Executor
    public void execute(Runnable task) {
        Preconditions.checkNotNull(task, "'task' must not be null.");
        if (!this.executing) {
            this.executing = true;
            try {
                task.run();
                if (this.taskQueue != null) {
                }
            } catch (Throwable t) {
                try {
                    log.log(Level.SEVERE, "Exception while executing runnable " + task, t);
                } finally {
                    if (this.taskQueue != null) {
                        completeQueuedTasks();
                    }
                    this.executing = false;
                }
            }
            return;
        }
        enqueue(task);
    }

    private void completeQueuedTasks() {
        while (true) {
            Runnable task = this.taskQueue.poll();
            if (task != null) {
                try {
                    task.run();
                } catch (Throwable t) {
                    log.log(Level.SEVERE, "Exception while executing runnable " + task, t);
                }
            } else {
                return;
            }
        }
    }

    private void enqueue(Runnable r) {
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque<>(4);
        }
        this.taskQueue.add(r);
    }
}
