package androidx.work.impl.model;

import androidx.lifecycle.LiveData;

/* JADX INFO: loaded from: classes11.dex */
public interface PreferenceDao {
    Long getLongValue(String key);

    LiveData<Long> getObservableLongValue(String key);

    void insertPreference(Preference preference);
}
