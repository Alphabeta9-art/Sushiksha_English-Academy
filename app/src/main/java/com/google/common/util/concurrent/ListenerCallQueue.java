package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.cloudinary.metadata.MetadataField;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes12.dex */
@ElementTypesAreNonnullByDefault
final class ListenerCallQueue<L> {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    interface Event<L> {
        void call(L listener);
    }

    ListenerCallQueue() {
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(listener, executor));
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String label) {
        enqueueHelper(event, label);
    }

    private void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(label, MetadataField.LABEL);
        synchronized (this.listeners) {
            for (PerListenerQueue<L> queue : this.listeners) {
                queue.add(event, label);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    private static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        boolean isThreadScheduled;
        final L listener;
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
        final Queue<Object> labelQueue = Queues.newArrayDeque();

        PerListenerQueue(L l, Executor executor) {
            this.listener = (L) Preconditions.checkNotNull(l);
            this.executor = (Executor) Preconditions.checkNotNull(executor);
        }

        synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        void dispatch() {
            boolean scheduleEventRunner = false;
            synchronized (this) {
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                    scheduleEventRunner = true;
                }
            }
            if (scheduleEventRunner) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, (Throwable) e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARN: Bottom block not found for handler: all -> 0x005d */
        /* JADX WARN: Code duplicated, block: B:10:0x001e  */
        /* JADX WARN: Code duplicated, block: B:18:0x0027 A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Code duplicated, block: B:40:0x001f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0029, code lost:
        
            r2.call(r9.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x002f, code lost:
        
            r4 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0030, code lost:
        
            com.google.common.util.concurrent.ListenerCallQueue.logger.log(java.util.logging.Level.SEVERE, "Exception while executing callback: " + r9.listener + " " + r3, (java.lang.Throwable) r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0066, code lost:
        
            throw r1;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r9 = this;
                r0 = 1
            L1:
                r1 = 0
                monitor-enter(r9)     // Catch: java.lang.Throwable -> L5d
                boolean r2 = r9.isThreadScheduled     // Catch: java.lang.Throwable -> L5b
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L5b
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch: java.lang.Throwable -> L5b
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L5b
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L5b
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch: java.lang.Throwable -> L5b
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L5b
                if (r2 != 0) goto L28
                r9.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L5b
                r0 = 0
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5b
                if (r0 == 0) goto L27
                monitor-enter(r9)
                r9.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L25
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L25
                goto L27
            L23:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L25
                goto L66
            L25:
                r1 = move-exception
                goto L23
            L27:
                return
            L28:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5b
                L r4 = r9.listener     // Catch: java.lang.RuntimeException -> L2f java.lang.Throwable -> L5d
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L2f java.lang.Throwable -> L5d
                goto L58
            L2f:
                r4 = move-exception
                java.util.logging.Logger r5 = com.google.common.util.concurrent.ListenerCallQueue.access$000()     // Catch: java.lang.Throwable -> L5d
                java.util.logging.Level r6 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L5d
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
                r7.<init>()     // Catch: java.lang.Throwable -> L5d
                java.lang.String r8 = "Exception while executing callback: "
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L5d
                L r8 = r9.listener     // Catch: java.lang.Throwable -> L5d
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L5d
                java.lang.String r8 = " "
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L5d
                java.lang.StringBuilder r7 = r7.append(r3)     // Catch: java.lang.Throwable -> L5d
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L5d
                r5.log(r6, r7, r4)     // Catch: java.lang.Throwable -> L5d
            L58:
                goto L1
            L59:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5b
                throw r2     // Catch: java.lang.Throwable -> L5d
            L5b:
                r2 = move-exception
                goto L59
            L5d:
                r2 = move-exception
                if (r0 == 0) goto L69
                monitor-enter(r9)
                r9.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L67
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L67
                goto L69
            L65:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L67
            L66:
                throw r1
            L67:
                r1 = move-exception
                goto L65
            L69:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }
}
