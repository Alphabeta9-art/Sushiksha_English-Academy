package com.bumptech.glide.manager;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.bumptech.glide.util.Util;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
final class LifecycleLifecycle implements Lifecycle, LifecycleObserver {
    private final androidx.lifecycle.Lifecycle lifecycle;
    private final Set<LifecycleListener> lifecycleListeners = new HashSet();

    LifecycleLifecycle(androidx.lifecycle.Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner owner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStart();
        }
    }

    @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner owner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStop();
        }
    }

    @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onDestroy();
        }
        owner.getLifecycle().removeObserver(this);
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(LifecycleListener listener) {
        this.lifecycleListeners.add(listener);
        if (this.lifecycle.getState() == androidx.lifecycle.Lifecycle.State.DESTROYED) {
            listener.onDestroy();
        } else if (this.lifecycle.getState().isAtLeast(androidx.lifecycle.Lifecycle.State.STARTED)) {
            listener.onStart();
        } else {
            listener.onStop();
        }
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(LifecycleListener listener) {
        this.lifecycleListeners.remove(listener);
    }
}
