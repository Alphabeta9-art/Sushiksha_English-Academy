package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes11.dex */
class DeferredReleaserConcurrentImpl extends DeferredReleaser {
    private final Object mLock = new Object();
    private final Runnable releaseRunnable = new Runnable() { // from class: com.facebook.drawee.components.DeferredReleaserConcurrentImpl.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (DeferredReleaserConcurrentImpl.this.mLock) {
                ArrayList<DeferredReleaser.Releasable> tmp = DeferredReleaserConcurrentImpl.this.mTempList;
                DeferredReleaserConcurrentImpl.this.mTempList = DeferredReleaserConcurrentImpl.this.mPendingReleasables;
                DeferredReleaserConcurrentImpl.this.mPendingReleasables = tmp;
            }
            int size = DeferredReleaserConcurrentImpl.this.mTempList.size();
            for (int i = 0; i < size; i++) {
                ((DeferredReleaser.Releasable) DeferredReleaserConcurrentImpl.this.mTempList.get(i)).release();
            }
            DeferredReleaserConcurrentImpl.this.mTempList.clear();
        }
    };
    private ArrayList<DeferredReleaser.Releasable> mPendingReleasables = new ArrayList<>();
    private ArrayList<DeferredReleaser.Releasable> mTempList = new ArrayList<>();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    @Override // com.facebook.drawee.components.DeferredReleaser
    public void scheduleDeferredRelease(DeferredReleaser.Releasable releasable) {
        if (!isOnUiThread()) {
            releasable.release();
            return;
        }
        synchronized (this.mLock) {
            if (this.mPendingReleasables.contains(releasable)) {
                return;
            }
            this.mPendingReleasables.add(releasable);
            boolean shouldSchedule = this.mPendingReleasables.size() == 1;
            if (shouldSchedule) {
                this.mUiHandler.post(this.releaseRunnable);
            }
        }
    }

    @Override // com.facebook.drawee.components.DeferredReleaser
    public void cancelDeferredRelease(DeferredReleaser.Releasable releasable) {
        synchronized (this.mLock) {
            this.mPendingReleasables.remove(releasable);
        }
    }
}
