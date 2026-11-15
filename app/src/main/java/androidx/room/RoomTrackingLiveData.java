package androidx.room;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes11.dex */
class RoomTrackingLiveData<T> extends LiveData<T> {
    final Callable<T> mComputeFunction;
    private final InvalidationLiveDataContainer mContainer;
    final RoomDatabase mDatabase;
    final boolean mInTransaction;
    final InvalidationTracker.Observer mObserver;
    final AtomicBoolean mInvalid = new AtomicBoolean(true);
    final AtomicBoolean mComputing = new AtomicBoolean(false);
    final AtomicBoolean mRegisteredObserver = new AtomicBoolean(false);
    final Runnable mRefreshRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.1
        /* JADX WARN: Bottom block not found for handler: all -> 0x0054 */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Exception {
            /*
                r6 = this;
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.mRegisteredObserver
                r1 = 0
                r2 = 1
                boolean r0 = r0.compareAndSet(r1, r2)
                if (r0 == 0) goto L1b
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                androidx.room.RoomDatabase r0 = r0.mDatabase
                androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                androidx.room.InvalidationTracker$Observer r3 = r3.mObserver
                r0.addWeakObserver(r3)
            L1b:
                r0 = 0
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                boolean r3 = r3.compareAndSet(r1, r2)
                if (r3 == 0) goto L5d
                r3 = 0
            L27:
                androidx.room.RoomTrackingLiveData r4 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Throwable -> L54
                java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid     // Catch: java.lang.Throwable -> L54
                boolean r4 = r4.compareAndSet(r2, r1)     // Catch: java.lang.Throwable -> L54
                if (r4 == 0) goto L45
                r0 = 1
                androidx.room.RoomTrackingLiveData r4 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                java.util.concurrent.Callable<T> r4 = r4.mComputeFunction     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                java.lang.Object r4 = r4.call()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                r3 = r4
                goto L27
            L3c:
                r2 = move-exception
                java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L54
                java.lang.String r5 = "Exception while computing database live data."
                r4.<init>(r5, r2)     // Catch: java.lang.Throwable -> L54
                throw r4     // Catch: java.lang.Throwable -> L54
            L45:
                if (r0 == 0) goto L4c
                androidx.room.RoomTrackingLiveData r4 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Throwable -> L54
                androidx.room.RoomTrackingLiveData.access$000(r4, r3)     // Catch: java.lang.Throwable -> L54
            L4c:
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                r3.set(r1)
                goto L5d
            L54:
                r2 = move-exception
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                r3.set(r1)
                throw r2
            L5d:
                if (r0 == 0) goto L6a
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.mInvalid
                boolean r3 = r3.get()
                if (r3 == 0) goto L6a
                goto L1b
            L6a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomTrackingLiveData.AnonymousClass1.run():void");
        }
    };
    final Runnable mInvalidationRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.2
        @Override // java.lang.Runnable
        public void run() {
            boolean isActive = RoomTrackingLiveData.this.hasActiveObservers();
            if (RoomTrackingLiveData.this.mInvalid.compareAndSet(false, true) && isActive) {
                RoomTrackingLiveData.this.getQueryExecutor().execute(RoomTrackingLiveData.this.mRefreshRunnable);
            }
        }
    };

    RoomTrackingLiveData(RoomDatabase database, InvalidationLiveDataContainer container, boolean inTransaction, Callable<T> computeFunction, String[] tableNames) {
        this.mDatabase = database;
        this.mInTransaction = inTransaction;
        this.mComputeFunction = computeFunction;
        this.mContainer = container;
        this.mObserver = new InvalidationTracker.Observer(tableNames) { // from class: androidx.room.RoomTrackingLiveData.3
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set<String> tables) {
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.mInvalidationRunnable);
            }
        };
    }

    @Override // androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        this.mContainer.onActive(this);
        getQueryExecutor().execute(this.mRefreshRunnable);
    }

    @Override // androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        this.mContainer.onInactive(this);
    }

    Executor getQueryExecutor() {
        if (this.mInTransaction) {
            return this.mDatabase.getTransactionExecutor();
        }
        return this.mDatabase.getQueryExecutor();
    }
}
