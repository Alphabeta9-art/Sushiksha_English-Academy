package com.facebook.common.disk;

/* JADX INFO: loaded from: classes11.dex */
public interface DiskTrimmableRegistry {
    void registerDiskTrimmable(DiskTrimmable diskTrimmable);

    void unregisterDiskTrimmable(DiskTrimmable diskTrimmable);
}
