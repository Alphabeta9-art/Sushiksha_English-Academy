package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabaseMigrations;
import androidx.work.impl.model.Preference;

/* JADX INFO: loaded from: classes11.dex */
public class PreferenceUtils {
    public static final String KEY_LAST_CANCEL_ALL_TIME_MS = "last_cancel_all_time_ms";
    public static final String KEY_RESCHEDULE_NEEDED = "reschedule_needed";
    public static final String PREFERENCES_FILE_NAME = "androidx.work.util.preferences";
    private final WorkDatabase mWorkDatabase;

    public PreferenceUtils(WorkDatabase workDatabase) {
        this.mWorkDatabase = workDatabase;
    }

    public long getLastCancelAllTimeMillis() {
        Long value = this.mWorkDatabase.preferenceDao().getLongValue(KEY_LAST_CANCEL_ALL_TIME_MS);
        if (value != null) {
            return value.longValue();
        }
        return 0L;
    }

    public LiveData<Long> getLastCancelAllTimeMillisLiveData() {
        LiveData<Long> observableValue = this.mWorkDatabase.preferenceDao().getObservableLongValue(KEY_LAST_CANCEL_ALL_TIME_MS);
        return Transformations.map(observableValue, new Function<Long, Long>() { // from class: androidx.work.impl.utils.PreferenceUtils.1
            @Override // androidx.arch.core.util.Function
            public Long apply(Long value) {
                return Long.valueOf(value != null ? value.longValue() : 0L);
            }
        });
    }

    public void setLastCancelAllTimeMillis(final long timeMillis) {
        Preference preference = new Preference(KEY_LAST_CANCEL_ALL_TIME_MS, timeMillis);
        this.mWorkDatabase.preferenceDao().insertPreference(preference);
    }

    public boolean getNeedsReschedule() {
        Long value = this.mWorkDatabase.preferenceDao().getLongValue(KEY_RESCHEDULE_NEEDED);
        return value != null && value.longValue() == 1;
    }

    public void setNeedsReschedule(boolean needsReschedule) {
        Preference preference = new Preference(KEY_RESCHEDULE_NEEDED, needsReschedule);
        this.mWorkDatabase.preferenceDao().insertPreference(preference);
    }

    public static void migrateLegacyPreferences(Context context, SupportSQLiteDatabase sqLiteDatabase) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        if (sharedPreferences.contains(KEY_RESCHEDULE_NEEDED) || sharedPreferences.contains(KEY_LAST_CANCEL_ALL_TIME_MS)) {
            long lastCancelTimeMillis = sharedPreferences.getLong(KEY_LAST_CANCEL_ALL_TIME_MS, 0L);
            boolean needsReschedule = sharedPreferences.getBoolean(KEY_RESCHEDULE_NEEDED, false);
            long reschedule = needsReschedule ? 1L : 0L;
            sqLiteDatabase.beginTransaction();
            try {
                sqLiteDatabase.execSQL(WorkDatabaseMigrations.INSERT_PREFERENCE, new Object[]{KEY_LAST_CANCEL_ALL_TIME_MS, Long.valueOf(lastCancelTimeMillis)});
                sqLiteDatabase.execSQL(WorkDatabaseMigrations.INSERT_PREFERENCE, new Object[]{KEY_RESCHEDULE_NEEDED, Long.valueOf(reschedule)});
                sharedPreferences.edit().clear().apply();
                sqLiteDatabase.setTransactionSuccessful();
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }
    }
}
