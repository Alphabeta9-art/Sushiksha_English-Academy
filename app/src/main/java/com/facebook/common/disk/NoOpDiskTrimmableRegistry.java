package com.facebook.common.disk;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class NoOpDiskTrimmableRegistry implements DiskTrimmableRegistry {

    @Nullable
    private static NoOpDiskTrimmableRegistry sInstance = null;

    private NoOpDiskTrimmableRegistry() {
    }

    public static synchronized NoOpDiskTrimmableRegistry getInstance() {
        if (sInstance == null) {
            sInstance = new NoOpDiskTrimmableRegistry();
        }
        return sInstance;
    }

    @Override // com.facebook.common.disk.DiskTrimmableRegistry
    public void registerDiskTrimmable(DiskTrimmable trimmable) {
    }

    @Override // com.facebook.common.disk.DiskTrimmableRegistry
    public void unregisterDiskTrimmable(DiskTrimmable trimmable) {
    }
}
