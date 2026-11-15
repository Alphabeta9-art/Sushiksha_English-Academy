package androidx.work.impl;

/* JADX INFO: loaded from: classes11.dex */
public interface ExecutionListener {
    void onExecuted(String workSpecId, boolean needsReschedule);
}
