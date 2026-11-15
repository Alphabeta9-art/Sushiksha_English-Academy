package androidx.media3.common;

import android.os.Bundle;

/* JADX INFO: loaded from: classes11.dex */
public interface Bundleable {

    public interface Creator<T extends Bundleable> {
        T fromBundle(Bundle bundle);
    }

    Bundle toBundle();
}
