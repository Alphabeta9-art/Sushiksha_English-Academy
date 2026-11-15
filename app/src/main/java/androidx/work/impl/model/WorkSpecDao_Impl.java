package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.Constraints;
import androidx.work.ContentUriTriggers;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.WorkInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes11.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkSpec> __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetPeriodStartTime;

    public WorkSpecDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter<WorkSpec>(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) throws IOException {
                if (workSpec.id != null) {
                    supportSQLiteStatement.bindString(1, workSpec.id);
                } else {
                    supportSQLiteStatement.bindNull(1);
                }
                supportSQLiteStatement.bindLong(2, WorkTypeConverters.stateToInt(workSpec.state));
                if (workSpec.workerClassName != null) {
                    supportSQLiteStatement.bindString(3, workSpec.workerClassName);
                } else {
                    supportSQLiteStatement.bindNull(3);
                }
                if (workSpec.inputMergerClassName != null) {
                    supportSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                } else {
                    supportSQLiteStatement.bindNull(4);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, byteArrayInternal2);
                }
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.periodStartTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1L : 0L);
                supportSQLiteStatement.bindLong(17, WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    supportSQLiteStatement.bindLong(18, WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    supportSQLiteStatement.bindLong(19, constraints.requiresCharging() ? 1L : 0L);
                    supportSQLiteStatement.bindLong(20, constraints.requiresDeviceIdle() ? 1L : 0L);
                    supportSQLiteStatement.bindLong(21, constraints.requiresBatteryNotLow() ? 1L : 0L);
                    supportSQLiteStatement.bindLong(22, constraints.requiresStorageNotLow() ? 1L : 0L);
                    supportSQLiteStatement.bindLong(23, constraints.getTriggerContentUpdateDelay());
                    supportSQLiteStatement.bindLong(24, constraints.getTriggerMaxContentDelay());
                    byte[] bArrContentUriTriggersToByteArray = WorkTypeConverters.contentUriTriggersToByteArray(constraints.getContentUriTriggers());
                    if (bArrContentUriTriggersToByteArray == null) {
                        supportSQLiteStatement.bindNull(25);
                        return;
                    } else {
                        supportSQLiteStatement.bindBlob(25, bArrContentUriTriggersToByteArray);
                        return;
                    }
                }
                supportSQLiteStatement.bindNull(18);
                supportSQLiteStatement.bindNull(19);
                supportSQLiteStatement.bindNull(20);
                supportSQLiteStatement.bindNull(21);
                supportSQLiteStatement.bindNull(22);
                supportSQLiteStatement.bindNull(23);
                supportSQLiteStatement.bindNull(24);
                supportSQLiteStatement.bindNull(25);
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetPeriodStartTime = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new SharedSQLiteStatement(__db) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(final WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDelete.acquire();
        if (id == null) {
            _stmt.bindNull(1);
        } else {
            _stmt.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(final String id, final Data output) throws IOException {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfSetOutput.acquire();
        byte[] _tmp = Data.toByteArrayInternal(output);
        if (_tmp == null) {
            _stmt.bindNull(1);
        } else {
            _stmt.bindBlob(1, _tmp);
        }
        if (id == null) {
            _stmt.bindNull(2);
        } else {
            _stmt.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setPeriodStartTime(final String id, final long periodStartTime) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfSetPeriodStartTime.acquire();
        _stmt.bindLong(1, periodStartTime);
        if (id == null) {
            _stmt.bindNull(2);
        } else {
            _stmt.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetPeriodStartTime.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (id == null) {
            _stmt.bindNull(1);
        } else {
            _stmt.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (id == null) {
            _stmt.bindNull(1);
        } else {
            _stmt.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(final String id, final long startTime) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        _stmt.bindLong(1, startTime);
        if (id == null) {
            _stmt.bindNull(2);
        } else {
            _stmt.bindString(2, id);
        }
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(_stmt);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(final String id) throws Throwable {
        RoomSQLiteQuery _statement;
        WorkSpec _result;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE id=?", 1);
        if (id == null) {
            _statement2.bindNull(1);
        } else {
            _statement2.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMRequiresBatteryNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfMRequiresStorageNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
            int _cursorIndexOfInputMergerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
            try {
                int _cursorIndexOfInput = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
                _statement = _statement2;
                try {
                    int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                    try {
                        int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                        int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                        int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        int _cursorIndexOfBackoffPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                        int _cursorIndexOfBackoffDelayDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                        int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                        int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                        int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                        int _cursorIndexOfExpedited = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                        int _cursorIndexOfOutOfQuotaPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                        if (_cursor.moveToFirst()) {
                            String _tmpId = _cursor.getString(_cursorIndexOfId);
                            String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                            Constraints _tmpConstraints = new Constraints();
                            int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                            NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                            _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                            int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                            boolean _tmpMRequiresCharging = _tmp_1 != 0;
                            _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                            int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                            boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                            _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                            int _tmp_3 = _cursor.getInt(_cursorIndexOfMRequiresBatteryNotLow);
                            boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                            _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                            int _tmp_4 = _cursor.getInt(_cursorIndexOfMRequiresStorageNotLow);
                            boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                            _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                            long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                            _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                            long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                            _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                            byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                            ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                            _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                            _result = new WorkSpec(_tmpId, _tmpWorkerClassName);
                            int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                            _result.state = WorkTypeConverters.intToState(_tmp_6);
                            _result.inputMergerClassName = _cursor.getString(_cursorIndexOfInputMergerClassName);
                            byte[] _tmp_7 = _cursor.getBlob(_cursorIndexOfInput);
                            _result.input = Data.fromByteArray(_tmp_7);
                            byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput);
                            _result.output = Data.fromByteArray(_tmp_8);
                            _result.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay);
                            _result.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration);
                            _result.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration);
                            _result.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                            int _tmp_9 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                            _result.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_9);
                            _result.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration);
                            _result.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime);
                            _result.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration);
                            _result.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt);
                            int _tmp_10 = _cursor.getInt(_cursorIndexOfExpedited);
                            _result.expedited = _tmp_10 != 0;
                            int _tmp_11 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                            _result.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_11);
                            _result.constraints = _tmpConstraints;
                        } else {
                            _result = null;
                        }
                        _cursor.close();
                        _statement.release();
                        return _result;
                    } catch (Throwable th) {
                        th = th;
                        _cursor.close();
                        _statement.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                _statement = _statement2;
            }
        } catch (Throwable th4) {
            th = th4;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec[] getWorkSpecs(final List<String> ids) throws Throwable {
        RoomSQLiteQuery _statement;
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT ");
        _stringBuilder.append("*");
        _stringBuilder.append(" FROM workspec WHERE id IN (");
        int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : ids) {
            if (_item == null) {
                _statement2.bindNull(_argIndex);
            } else {
                _statement2.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMRequiresBatteryNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfMRequiresStorageNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            try {
                int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                try {
                    int _cursorIndexOfExpedited = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
                    try {
                        int _cursorIndexOfInputMergerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
                        try {
                            int _cursorIndexOfBackoffDelayDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
                            _statement = _statement2;
                            try {
                                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                                try {
                                    int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                                    int _cursorIndexOfInputMergerClassName2 = _cursorIndexOfInitialDelay;
                                    int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                                    int _cursorIndexOfInput = _cursorIndexOfIntervalDuration;
                                    int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                                    int _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration;
                                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                                    int _cursorIndexOfRunAttemptCount2 = _cursorIndexOfRunAttemptCount;
                                    int _cursorIndexOfRunAttemptCount3 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                                    int _cursorIndexOfBackoffDelayDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                                    int _cursorIndexOfFlexDuration2 = _cursorIndexOfBackoffDelayDuration2;
                                    int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                                    int _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime;
                                    int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                                    int _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration;
                                    int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                                    int _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt;
                                    int _cursorIndexOfExpedited2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                                    int _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited2;
                                    int _cursorIndexOfOutOfQuotaPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                                    int _cursorIndexOfOutOfQuotaPolicy2 = _cursorIndexOfOutOfQuotaPolicy;
                                    int _cursorIndexOfOutOfQuotaPolicy3 = _cursor.getCount();
                                    WorkSpec[] _result = new WorkSpec[_cursorIndexOfOutOfQuotaPolicy3];
                                    int _index = 0;
                                    while (_cursor.moveToNext()) {
                                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                                        String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfExpedited);
                                        Constraints _tmpConstraints = new Constraints();
                                        int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                                        NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                                        int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                                        int _cursorIndexOfId2 = _cursorIndexOfId;
                                        _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                                        int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                                        boolean _tmpMRequiresCharging = _tmp_1 != 0;
                                        _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                                        int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                                        boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                                        _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                                        int _tmp_3 = _cursor.getInt(_cursorIndexOfMRequiresBatteryNotLow);
                                        boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                                        _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                                        int _tmp_4 = _cursor.getInt(_cursorIndexOfMRequiresStorageNotLow);
                                        boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                                        _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                                        long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                                        int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                                        int _cursorIndexOfMRequiresDeviceIdle2 = _cursorIndexOfMRequiresDeviceIdle;
                                        _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                                        long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                                        _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                                        byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                                        ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                                        _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                                        int _cursorIndexOfWorkerClassName = _cursorIndexOfExpedited;
                                        WorkSpec[] _result2 = _result;
                                        WorkSpec _item_1 = new WorkSpec(_tmpId, _tmpWorkerClassName);
                                        int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                                        int _cursorIndexOfState2 = _cursorIndexOfState;
                                        _item_1.state = WorkTypeConverters.intToState(_tmp_6);
                                        _item_1.inputMergerClassName = _cursor.getString(_cursorIndexOfInputMergerClassName);
                                        byte[] _tmp_7 = _cursor.getBlob(_cursorIndexOfBackoffDelayDuration);
                                        _item_1.input = Data.fromByteArray(_tmp_7);
                                        byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput);
                                        _item_1.output = Data.fromByteArray(_tmp_8);
                                        int _cursorIndexOfInitialDelay3 = _cursorIndexOfInputMergerClassName2;
                                        int _cursorIndexOfInitialDelay4 = _cursorIndexOfInputMergerClassName;
                                        _item_1.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                                        int _cursorIndexOfIntervalDuration3 = _cursorIndexOfInput;
                                        int _cursorIndexOfIntervalDuration4 = _cursorIndexOfBackoffDelayDuration;
                                        _item_1.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration3);
                                        int _cursorIndexOfFlexDuration3 = _cursorIndexOfIntervalDuration2;
                                        _item_1.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                                        int _cursorIndexOfRunAttemptCount4 = _cursorIndexOfRunAttemptCount2;
                                        _item_1.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount4);
                                        int _cursorIndexOfBackoffPolicy = _cursorIndexOfRunAttemptCount3;
                                        int _tmp_9 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                                        _item_1.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_9);
                                        int _cursorIndexOfBackoffDelayDuration3 = _cursorIndexOfFlexDuration2;
                                        _item_1.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration3);
                                        int _cursorIndexOfPeriodStartTime3 = _cursorIndexOfInitialDelay2;
                                        _item_1.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime3);
                                        int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfPeriodStartTime2;
                                        _item_1.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                                        int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfMinimumRetentionDuration2;
                                        _item_1.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                                        int _cursorIndexOfExpedited3 = _cursorIndexOfScheduleRequestedAt2;
                                        int _tmp_10 = _cursor.getInt(_cursorIndexOfExpedited3);
                                        _item_1.expedited = _tmp_10 != 0;
                                        int _cursorIndexOfOutOfQuotaPolicy4 = _cursorIndexOfOutOfQuotaPolicy2;
                                        int _tmp_11 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy4);
                                        _item_1.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_11);
                                        _item_1.constraints = _tmpConstraints;
                                        _result2[_index] = _item_1;
                                        _index++;
                                        _cursorIndexOfFlexDuration2 = _cursorIndexOfBackoffDelayDuration3;
                                        _cursorIndexOfBackoffDelayDuration = _cursorIndexOfIntervalDuration4;
                                        _cursorIndexOfInput = _cursorIndexOfIntervalDuration3;
                                        _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration3;
                                        _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay4;
                                        _cursorIndexOfRunAttemptCount2 = _cursorIndexOfRunAttemptCount4;
                                        _cursorIndexOfInputMergerClassName2 = _cursorIndexOfInitialDelay3;
                                        _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime3;
                                        _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration3;
                                        _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt3;
                                        _result = _result2;
                                        _cursorIndexOfId = _cursorIndexOfId2;
                                        _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                                        _cursorIndexOfOutOfQuotaPolicy2 = _cursorIndexOfOutOfQuotaPolicy4;
                                        _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                                        _cursorIndexOfMRequiresDeviceIdle = _cursorIndexOfMRequiresDeviceIdle2;
                                        _cursorIndexOfState = _cursorIndexOfState2;
                                        _cursorIndexOfRunAttemptCount3 = _cursorIndexOfBackoffPolicy;
                                        _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited3;
                                        _cursorIndexOfExpedited = _cursorIndexOfWorkerClassName;
                                    }
                                    WorkSpec[] _result3 = _result;
                                    _cursor.close();
                                    _statement.release();
                                    return _result3;
                                } catch (Throwable th) {
                                    th = th;
                                    _cursor.close();
                                    _statement.release();
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _statement = _statement2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _statement = _statement2;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    _statement = _statement2;
                }
            } catch (Throwable th6) {
                th = th6;
                _statement = _statement2;
            }
        } catch (Throwable th7) {
            th = th7;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(final String name) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            List<WorkSpec.IdAndState> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                WorkSpec.IdAndState _item = new WorkSpec.IdAndState();
                _item.id = _cursor.getString(_cursorIndexOfId);
                int _tmp = _cursor.getInt(_cursorIndexOfState);
                _item.state = WorkTypeConverters.intToState(_tmp);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllWorkSpecIds() {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            List<String> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                String _item = _cursor.getString(0);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<String>> getAllWorkSpecIdsLiveData() {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, true, new Callable<List<String>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.10
            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor _cursor = DBUtil.query(WorkSpecDao_Impl.this.__db, _statement, false, null);
                    try {
                        List<String> _result = new ArrayList<>(_cursor.getCount());
                        while (_cursor.moveToNext()) {
                            String _item = _cursor.getString(0);
                            _result.add(_item);
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        _cursor.close();
                        WorkSpecDao_Impl.this.__db.endTransaction();
                        return _result;
                    } catch (Throwable th) {
                        _cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    throw th2;
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkInfo.State getState(final String id) {
        WorkInfo.State _result;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            if (_cursor.moveToFirst()) {
                int _tmp = _cursor.getInt(0);
                _result = WorkTypeConverters.intToState(_tmp);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec.WorkInfoPojo getWorkStatusPojoForId(final String id) {
        WorkSpec.WorkInfoPojo _result;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id=?", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor _cursor = DBUtil.query(this.__db, _statement, true, null);
            try {
                int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                while (_cursor.moveToNext()) {
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey = _cursor.getString(_cursorIndexOfId);
                        ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                        if (_tmpTagsCollection == null) {
                            ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                            _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                        }
                    }
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                        if (_tmpProgressCollection == null) {
                            ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                            _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                        }
                    }
                }
                _cursor.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                __fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                if (_cursor.moveToFirst()) {
                    ArrayList<String> _tmpTagsCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                        _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                    }
                    if (_tmpTagsCollection_1 == null) {
                        _tmpTagsCollection_1 = new ArrayList<>();
                    }
                    ArrayList<Data> _tmpProgressCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                        _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                    }
                    if (_tmpProgressCollection_1 == null) {
                        _tmpProgressCollection_1 = new ArrayList<>();
                    }
                    _result = new WorkSpec.WorkInfoPojo();
                    _result.id = _cursor.getString(_cursorIndexOfId);
                    int _tmp = _cursor.getInt(_cursorIndexOfState);
                    _result.state = WorkTypeConverters.intToState(_tmp);
                    byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                    _result.output = Data.fromByteArray(_tmp_1);
                    _result.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                    _result.tags = _tmpTagsCollection_1;
                    _result.progress = _tmpProgressCollection_1;
                } else {
                    _result = null;
                }
                this.__db.setTransactionSuccessful();
                _cursor.close();
                _statement.release();
                this.__db.endTransaction();
                return _result;
            } catch (Throwable th) {
                _cursor.close();
                _statement.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.__db.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(final List<String> ids) throws Throwable {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : ids) {
            if (_item == null) {
                _statement.bindNull(_argIndex);
            } else {
                _statement.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor _cursor = DBUtil.query(this.__db, _statement, true, null);
            try {
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                    int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                    ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                    ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                    while (_cursor.moveToNext()) {
                        try {
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey = _cursor.getString(_cursorIndexOfId);
                                ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                                if (_tmpTagsCollection == null) {
                                    ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                                    _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                                }
                            }
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                                ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                                if (_tmpProgressCollection == null) {
                                    ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                                    _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            _cursor.close();
                            _statement.release();
                            throw th;
                        }
                    }
                    _cursor.moveToPosition(-1);
                    __fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                    __fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                    List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        ArrayList<String> _tmpTagsCollection_1 = null;
                        if (!_cursor.isNull(_cursorIndexOfId)) {
                            try {
                                String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                                try {
                                    _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                                } catch (Throwable th2) {
                                    th = th2;
                                    _cursor.close();
                                    _statement.release();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        }
                        if (_tmpTagsCollection_1 == null) {
                            _tmpTagsCollection_1 = new ArrayList<>();
                        }
                        ArrayList<Data> _tmpProgressCollection_1 = null;
                        try {
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                                _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                            }
                            if (_tmpProgressCollection_1 == null) {
                                _tmpProgressCollection_1 = new ArrayList<>();
                            }
                            WorkSpec.WorkInfoPojo _item_1 = new WorkSpec.WorkInfoPojo();
                            int _inputSize2 = _inputSize;
                            try {
                                _item_1.id = _cursor.getString(_cursorIndexOfId);
                                int _tmp = _cursor.getInt(_cursorIndexOfState);
                                _item_1.state = WorkTypeConverters.intToState(_tmp);
                                byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                                _item_1.output = Data.fromByteArray(_tmp_1);
                                _item_1.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                                _item_1.tags = _tmpTagsCollection_1;
                                _item_1.progress = _tmpProgressCollection_1;
                                _result.add(_item_1);
                                _stringBuilder = _stringBuilder;
                                _inputSize = _inputSize2;
                                _cursorIndexOfId = _cursorIndexOfId;
                            } catch (Throwable th4) {
                                th = th4;
                                _cursor.close();
                                _statement.release();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _cursor.close();
                            _statement.release();
                            throw th;
                        }
                    }
                    this.__db.setTransactionSuccessful();
                    _cursor.close();
                    _statement.release();
                    this.__db.endTransaction();
                    return _result;
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                this.__db.endTransaction();
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(final List<String> ids) {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : ids) {
            if (_item == null) {
                _statement.bindNull(_argIndex);
            } else {
                _statement.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.11
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor _cursor = DBUtil.query(WorkSpecDao_Impl.this.__db, _statement, true, null);
                    try {
                        int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                        int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                        ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                        while (_cursor.moveToNext()) {
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey = _cursor.getString(_cursorIndexOfId);
                                ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                                if (_tmpTagsCollection == null) {
                                    ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                                    _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                                }
                            }
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                                ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                                if (_tmpProgressCollection == null) {
                                    ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                                    _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                                }
                            }
                        }
                        _cursor.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                        List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                        while (_cursor.moveToNext()) {
                            ArrayList<String> _tmpTagsCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                                _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                            }
                            if (_tmpTagsCollection_1 == null) {
                                _tmpTagsCollection_1 = new ArrayList<>();
                            }
                            ArrayList<Data> _tmpProgressCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                                _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                            }
                            if (_tmpProgressCollection_1 == null) {
                                _tmpProgressCollection_1 = new ArrayList<>();
                            }
                            WorkSpec.WorkInfoPojo _item_1 = new WorkSpec.WorkInfoPojo();
                            _item_1.id = _cursor.getString(_cursorIndexOfId);
                            int _tmp = _cursor.getInt(_cursorIndexOfState);
                            _item_1.state = WorkTypeConverters.intToState(_tmp);
                            byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                            _item_1.output = Data.fromByteArray(_tmp_1);
                            _item_1.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                            _item_1.tags = _tmpTagsCollection_1;
                            _item_1.progress = _tmpProgressCollection_1;
                            _result.add(_item_1);
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        _cursor.close();
                        WorkSpecDao_Impl.this.__db.endTransaction();
                        return _result;
                    } catch (Throwable th) {
                        _cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    throw th2;
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(final String tag) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, tag);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor _cursor = DBUtil.query(this.__db, _statement, true, null);
            try {
                int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                while (_cursor.moveToNext()) {
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey = _cursor.getString(_cursorIndexOfId);
                        ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                        if (_tmpTagsCollection == null) {
                            ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                            _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                        }
                    }
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                        if (_tmpProgressCollection == null) {
                            ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                            _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                        }
                    }
                }
                _cursor.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                __fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                while (_cursor.moveToNext()) {
                    ArrayList<String> _tmpTagsCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                        _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                    }
                    if (_tmpTagsCollection_1 == null) {
                        _tmpTagsCollection_1 = new ArrayList<>();
                    }
                    ArrayList<Data> _tmpProgressCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                        _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                    }
                    if (_tmpProgressCollection_1 == null) {
                        _tmpProgressCollection_1 = new ArrayList<>();
                    }
                    WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo();
                    _item.id = _cursor.getString(_cursorIndexOfId);
                    int _tmp = _cursor.getInt(_cursorIndexOfState);
                    int _cursorIndexOfId2 = _cursorIndexOfId;
                    _item.state = WorkTypeConverters.intToState(_tmp);
                    byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                    _item.output = Data.fromByteArray(_tmp_1);
                    _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                    _item.tags = _tmpTagsCollection_1;
                    _item.progress = _tmpProgressCollection_1;
                    _result.add(_item);
                    _cursorIndexOfId = _cursorIndexOfId2;
                }
                this.__db.setTransactionSuccessful();
                _cursor.close();
                _statement.release();
                this.__db.endTransaction();
                return _result;
            } catch (Throwable th) {
                _cursor.close();
                _statement.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.__db.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(final String tag) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, tag);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.12
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor _cursor = DBUtil.query(WorkSpecDao_Impl.this.__db, _statement, true, null);
                    try {
                        int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                        int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                        ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                        while (_cursor.moveToNext()) {
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey = _cursor.getString(_cursorIndexOfId);
                                ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                                if (_tmpTagsCollection == null) {
                                    ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                                    _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                                }
                            }
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                                ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                                if (_tmpProgressCollection == null) {
                                    ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                                    _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                                }
                            }
                        }
                        _cursor.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                        List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                        while (_cursor.moveToNext()) {
                            ArrayList<String> _tmpTagsCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                                _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                            }
                            if (_tmpTagsCollection_1 == null) {
                                _tmpTagsCollection_1 = new ArrayList<>();
                            }
                            ArrayList<Data> _tmpProgressCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                                _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                            }
                            if (_tmpProgressCollection_1 == null) {
                                _tmpProgressCollection_1 = new ArrayList<>();
                            }
                            WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo();
                            _item.id = _cursor.getString(_cursorIndexOfId);
                            int _tmp = _cursor.getInt(_cursorIndexOfState);
                            _item.state = WorkTypeConverters.intToState(_tmp);
                            byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                            _item.output = Data.fromByteArray(_tmp_1);
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                            _item.tags = _tmpTagsCollection_1;
                            _item.progress = _tmpProgressCollection_1;
                            _result.add(_item);
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        _cursor.close();
                        WorkSpecDao_Impl.this.__db.endTransaction();
                        return _result;
                    } catch (Throwable th) {
                        _cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    throw th2;
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(final String name) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor _cursor = DBUtil.query(this.__db, _statement, true, null);
            try {
                int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                while (_cursor.moveToNext()) {
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey = _cursor.getString(_cursorIndexOfId);
                        ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                        if (_tmpTagsCollection == null) {
                            ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                            _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                        }
                    }
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                        if (_tmpProgressCollection == null) {
                            ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                            _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                        }
                    }
                }
                _cursor.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                __fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                while (_cursor.moveToNext()) {
                    ArrayList<String> _tmpTagsCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                        _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                    }
                    if (_tmpTagsCollection_1 == null) {
                        _tmpTagsCollection_1 = new ArrayList<>();
                    }
                    ArrayList<Data> _tmpProgressCollection_1 = null;
                    if (!_cursor.isNull(_cursorIndexOfId)) {
                        String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                        _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                    }
                    if (_tmpProgressCollection_1 == null) {
                        _tmpProgressCollection_1 = new ArrayList<>();
                    }
                    WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo();
                    _item.id = _cursor.getString(_cursorIndexOfId);
                    int _tmp = _cursor.getInt(_cursorIndexOfState);
                    int _cursorIndexOfId2 = _cursorIndexOfId;
                    _item.state = WorkTypeConverters.intToState(_tmp);
                    byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                    _item.output = Data.fromByteArray(_tmp_1);
                    _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                    _item.tags = _tmpTagsCollection_1;
                    _item.progress = _tmpProgressCollection_1;
                    _result.add(_item);
                    _cursorIndexOfId = _cursorIndexOfId2;
                }
                this.__db.setTransactionSuccessful();
                _cursor.close();
                _statement.release();
                this.__db.endTransaction();
                return _result;
            } catch (Throwable th) {
                _cursor.close();
                _statement.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.__db.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(final String name) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, name);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.13
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor _cursor = DBUtil.query(WorkSpecDao_Impl.this.__db, _statement, true, null);
                    try {
                        int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                        int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                        ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                        while (_cursor.moveToNext()) {
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey = _cursor.getString(_cursorIndexOfId);
                                ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                                if (_tmpTagsCollection == null) {
                                    ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                                    _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                                }
                            }
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                                ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                                if (_tmpProgressCollection == null) {
                                    ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                                    _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                                }
                            }
                        }
                        _cursor.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                        List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                        while (_cursor.moveToNext()) {
                            ArrayList<String> _tmpTagsCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                                _tmpTagsCollection_1 = _collectionTags.get(_tmpKey_2);
                            }
                            if (_tmpTagsCollection_1 == null) {
                                _tmpTagsCollection_1 = new ArrayList<>();
                            }
                            ArrayList<Data> _tmpProgressCollection_1 = null;
                            if (!_cursor.isNull(_cursorIndexOfId)) {
                                String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                                _tmpProgressCollection_1 = _collectionProgress.get(_tmpKey_3);
                            }
                            if (_tmpProgressCollection_1 == null) {
                                _tmpProgressCollection_1 = new ArrayList<>();
                            }
                            WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo();
                            _item.id = _cursor.getString(_cursorIndexOfId);
                            int _tmp = _cursor.getInt(_cursorIndexOfState);
                            _item.state = WorkTypeConverters.intToState(_tmp);
                            byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                            _item.output = Data.fromByteArray(_tmp_1);
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                            _item.tags = _tmpTagsCollection_1;
                            _item.progress = _tmpProgressCollection_1;
                            _result.add(_item);
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        _cursor.close();
                        WorkSpecDao_Impl.this.__db.endTransaction();
                        return _result;
                    } catch (Throwable th) {
                        _cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    throw th2;
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<Data> getInputsFromPrerequisites(final String id) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            List<Data> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                byte[] _tmp = _cursor.getBlob(0);
                Data _item = Data.fromByteArray(_tmp);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithTag(final String tag) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (tag == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, tag);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            List<String> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                String _item = _cursor.getString(0);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithName(final String name) {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (name == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            List<String> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                String _item = _cursor.getString(0);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllUnfinishedWork() {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            List<String> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                String _item = _cursor.getString(0);
                _result.add(_item);
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public boolean hasUnfinishedWork() {
        boolean _result = false;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            if (_cursor.moveToFirst()) {
                int _tmp = _cursor.getInt(0);
                if (_tmp != 0) {
                    _result = true;
                }
            } else {
                _result = false;
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData<Long> getScheduleRequestedAtLiveData(final String id) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT schedule_requested_at FROM workspec WHERE id=?", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Long _result;
                Cursor _cursor = DBUtil.query(WorkSpecDao_Impl.this.__db, _statement, false, null);
                try {
                    if (!_cursor.moveToFirst() || _cursor.isNull(0)) {
                        _result = null;
                    } else {
                        _result = Long.valueOf(_cursor.getLong(0));
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getEligibleWorkForScheduling(final int schedulerLimit) throws Throwable {
        RoomSQLiteQuery _statement;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        _statement2.bindLong(1, schedulerLimit);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMRequiresBatteryNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfMRequiresStorageNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfExpedited = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
            try {
                int _cursorIndexOfInputMergerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
                _statement = _statement2;
                try {
                    int _tmp_10 = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
                    try {
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                        int _cursorIndexOfInput = _cursorIndexOfInitialDelay;
                        int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                        int _cursorIndexOfIntervalDuration2 = _cursorIndexOfIntervalDuration;
                        int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                        int _cursorIndexOfIntervalDuration3 = _cursorIndexOfFlexDuration;
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        int _tmp_9 = _cursorIndexOfRunAttemptCount;
                        int _cursorIndexOfRunAttemptCount2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                        int _cursorIndexOfFlexDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                        int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                        int _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime;
                        int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                        int _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration;
                        int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                        int _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt;
                        int _cursorIndexOfExpedited2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                        int _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited2;
                        int _tmp_11 = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                        int _cursorIndexOfOutput2 = _cursorIndexOfOutput;
                        int _cursorIndexOfOutput3 = _cursor.getCount();
                        List<WorkSpec> _result = new ArrayList<>(_cursorIndexOfOutput3);
                        while (_cursor.moveToNext()) {
                            String _tmpId = _cursor.getString(_cursorIndexOfExpedited);
                            String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                            Constraints _tmpConstraints = new Constraints();
                            int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                            NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                            int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                            int _cursorIndexOfWorkerClassName2 = _cursorIndexOfWorkerClassName;
                            _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                            int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                            boolean _tmpMRequiresCharging = _tmp_1 != 0;
                            _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                            int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                            boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                            _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                            int _tmp_3 = _cursor.getInt(_cursorIndexOfMRequiresBatteryNotLow);
                            boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                            _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                            int _tmp_4 = _cursor.getInt(_cursorIndexOfMRequiresStorageNotLow);
                            boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                            _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                            long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                            int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                            int _cursorIndexOfMRequiresDeviceIdle2 = _cursorIndexOfMRequiresDeviceIdle;
                            _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                            long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                            _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                            byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                            ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                            _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                            int _cursorIndexOfId = _cursorIndexOfExpedited;
                            WorkSpec _item = new WorkSpec(_tmpId, _tmpWorkerClassName);
                            int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                            _item.state = WorkTypeConverters.intToState(_tmp_6);
                            _item.inputMergerClassName = _cursor.getString(_cursorIndexOfInputMergerClassName);
                            byte[] _tmp_7 = _cursor.getBlob(_tmp_10);
                            int _cursorIndexOfInputMergerClassName2 = _cursorIndexOfInputMergerClassName;
                            _item.input = Data.fromByteArray(_tmp_7);
                            int _cursorIndexOfOutput4 = _cursorIndexOfOutput2;
                            byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput4);
                            _item.output = Data.fromByteArray(_tmp_8);
                            int _cursorIndexOfInitialDelay3 = _cursorIndexOfInput;
                            int _cursorIndexOfInitialDelay4 = _tmp_10;
                            _item.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                            int _cursorIndexOfIntervalDuration4 = _cursorIndexOfIntervalDuration2;
                            _item.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration4);
                            int _cursorIndexOfFlexDuration3 = _cursorIndexOfIntervalDuration3;
                            _item.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                            int _cursorIndexOfRunAttemptCount3 = _tmp_9;
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount3);
                            int _cursorIndexOfBackoffPolicy = _cursorIndexOfRunAttemptCount2;
                            int _tmp_12 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                            _item.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_12);
                            int _cursorIndexOfBackoffDelayDuration = _cursorIndexOfFlexDuration2;
                            _item.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration);
                            int _cursorIndexOfPeriodStartTime3 = _cursorIndexOfInitialDelay2;
                            _item.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime3);
                            int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfPeriodStartTime2;
                            _item.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                            int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfMinimumRetentionDuration2;
                            _item.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                            int _cursorIndexOfExpedited3 = _cursorIndexOfScheduleRequestedAt2;
                            int _tmp_13 = _cursor.getInt(_cursorIndexOfExpedited3);
                            _item.expedited = _tmp_13 != 0;
                            int _cursorIndexOfOutOfQuotaPolicy = _tmp_11;
                            int _tmp_14 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                            _item.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_14);
                            _item.constraints = _tmpConstraints;
                            _result.add(_item);
                            _tmp_10 = _cursorIndexOfInitialDelay4;
                            _cursorIndexOfIntervalDuration2 = _cursorIndexOfIntervalDuration4;
                            _tmp_9 = _cursorIndexOfRunAttemptCount3;
                            _cursorIndexOfIntervalDuration3 = _cursorIndexOfFlexDuration3;
                            _cursorIndexOfInput = _cursorIndexOfInitialDelay3;
                            _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime3;
                            _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration3;
                            _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt3;
                            _cursorIndexOfWorkerClassName = _cursorIndexOfWorkerClassName2;
                            _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                            _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                            _cursorIndexOfMRequiresDeviceIdle = _cursorIndexOfMRequiresDeviceIdle2;
                            _cursorIndexOfInputMergerClassName = _cursorIndexOfInputMergerClassName2;
                            _cursorIndexOfOutput2 = _cursorIndexOfOutput4;
                            _cursorIndexOfRunAttemptCount2 = _cursorIndexOfBackoffPolicy;
                            _tmp_11 = _cursorIndexOfOutOfQuotaPolicy;
                            _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited3;
                            _cursorIndexOfFlexDuration2 = _cursorIndexOfBackoffDelayDuration;
                            _cursorIndexOfExpedited = _cursorIndexOfId;
                        }
                        _cursor.close();
                        _statement.release();
                        return _result;
                    } catch (Throwable th) {
                        th = th;
                        _cursor.close();
                        _statement.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                _statement = _statement2;
            }
        } catch (Throwable th4) {
            th = th4;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(final int maxLimit) throws Throwable {
        RoomSQLiteQuery _statement;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT ?", 1);
        _statement2.bindLong(1, maxLimit);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMRequiresBatteryNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfMRequiresStorageNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfExpedited = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
            try {
                int _cursorIndexOfInputMergerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
                _statement = _statement2;
                try {
                    int _tmp_10 = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
                    try {
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                        int _cursorIndexOfInput = _cursorIndexOfInitialDelay;
                        int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                        int _cursorIndexOfIntervalDuration2 = _cursorIndexOfIntervalDuration;
                        int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                        int _cursorIndexOfIntervalDuration3 = _cursorIndexOfFlexDuration;
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        int _tmp_9 = _cursorIndexOfRunAttemptCount;
                        int _cursorIndexOfRunAttemptCount2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                        int _cursorIndexOfFlexDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                        int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                        int _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime;
                        int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                        int _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration;
                        int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                        int _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt;
                        int _cursorIndexOfExpedited2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                        int _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited2;
                        int _tmp_11 = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                        int _cursorIndexOfOutput2 = _cursorIndexOfOutput;
                        int _cursorIndexOfOutput3 = _cursor.getCount();
                        List<WorkSpec> _result = new ArrayList<>(_cursorIndexOfOutput3);
                        while (_cursor.moveToNext()) {
                            String _tmpId = _cursor.getString(_cursorIndexOfExpedited);
                            String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                            Constraints _tmpConstraints = new Constraints();
                            int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                            NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                            int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                            int _cursorIndexOfWorkerClassName2 = _cursorIndexOfWorkerClassName;
                            _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                            int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                            boolean _tmpMRequiresCharging = _tmp_1 != 0;
                            _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                            int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                            boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                            _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                            int _tmp_3 = _cursor.getInt(_cursorIndexOfMRequiresBatteryNotLow);
                            boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                            _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                            int _tmp_4 = _cursor.getInt(_cursorIndexOfMRequiresStorageNotLow);
                            boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                            _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                            long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                            int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                            int _cursorIndexOfMRequiresDeviceIdle2 = _cursorIndexOfMRequiresDeviceIdle;
                            _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                            long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                            _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                            byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                            ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                            _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                            int _cursorIndexOfId = _cursorIndexOfExpedited;
                            WorkSpec _item = new WorkSpec(_tmpId, _tmpWorkerClassName);
                            int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                            _item.state = WorkTypeConverters.intToState(_tmp_6);
                            _item.inputMergerClassName = _cursor.getString(_cursorIndexOfInputMergerClassName);
                            byte[] _tmp_7 = _cursor.getBlob(_tmp_10);
                            int _cursorIndexOfInputMergerClassName2 = _cursorIndexOfInputMergerClassName;
                            _item.input = Data.fromByteArray(_tmp_7);
                            int _cursorIndexOfOutput4 = _cursorIndexOfOutput2;
                            byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput4);
                            _item.output = Data.fromByteArray(_tmp_8);
                            int _cursorIndexOfInitialDelay3 = _cursorIndexOfInput;
                            int _cursorIndexOfInitialDelay4 = _tmp_10;
                            _item.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                            int _cursorIndexOfIntervalDuration4 = _cursorIndexOfIntervalDuration2;
                            _item.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration4);
                            int _cursorIndexOfFlexDuration3 = _cursorIndexOfIntervalDuration3;
                            _item.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                            int _cursorIndexOfRunAttemptCount3 = _tmp_9;
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount3);
                            int _cursorIndexOfBackoffPolicy = _cursorIndexOfRunAttemptCount2;
                            int _tmp_12 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                            _item.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_12);
                            int _cursorIndexOfBackoffDelayDuration = _cursorIndexOfFlexDuration2;
                            _item.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration);
                            int _cursorIndexOfPeriodStartTime3 = _cursorIndexOfInitialDelay2;
                            _item.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime3);
                            int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfPeriodStartTime2;
                            _item.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                            int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfMinimumRetentionDuration2;
                            _item.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                            int _cursorIndexOfExpedited3 = _cursorIndexOfScheduleRequestedAt2;
                            int _tmp_13 = _cursor.getInt(_cursorIndexOfExpedited3);
                            _item.expedited = _tmp_13 != 0;
                            int _cursorIndexOfOutOfQuotaPolicy = _tmp_11;
                            int _tmp_14 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                            _item.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_14);
                            _item.constraints = _tmpConstraints;
                            _result.add(_item);
                            _tmp_10 = _cursorIndexOfInitialDelay4;
                            _cursorIndexOfIntervalDuration2 = _cursorIndexOfIntervalDuration4;
                            _tmp_9 = _cursorIndexOfRunAttemptCount3;
                            _cursorIndexOfIntervalDuration3 = _cursorIndexOfFlexDuration3;
                            _cursorIndexOfInput = _cursorIndexOfInitialDelay3;
                            _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime3;
                            _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration3;
                            _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt3;
                            _cursorIndexOfWorkerClassName = _cursorIndexOfWorkerClassName2;
                            _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                            _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                            _cursorIndexOfMRequiresDeviceIdle = _cursorIndexOfMRequiresDeviceIdle2;
                            _cursorIndexOfInputMergerClassName = _cursorIndexOfInputMergerClassName2;
                            _cursorIndexOfOutput2 = _cursorIndexOfOutput4;
                            _cursorIndexOfRunAttemptCount2 = _cursorIndexOfBackoffPolicy;
                            _tmp_11 = _cursorIndexOfOutOfQuotaPolicy;
                            _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited3;
                            _cursorIndexOfFlexDuration2 = _cursorIndexOfBackoffDelayDuration;
                            _cursorIndexOfExpedited = _cursorIndexOfId;
                        }
                        _cursor.close();
                        _statement.release();
                        return _result;
                    } catch (Throwable th) {
                        th = th;
                        _cursor.close();
                        _statement.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                _statement = _statement2;
            }
        } catch (Throwable th4) {
            th = th4;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getScheduledWork() throws Throwable {
        RoomSQLiteQuery _statement;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfBackoffDelayDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
            int _tmp_10 = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
            int _cursorIndexOfInput = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
            try {
                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                _statement = _statement2;
                try {
                    int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                    int _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay;
                    int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                    int _cursorIndexOfMRequiresStorageNotLow = _cursorIndexOfIntervalDuration;
                    int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                    int _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration;
                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                    int _tmp_9 = _cursorIndexOfRunAttemptCount;
                    int _cursorIndexOfRunAttemptCount2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                    int _cursorIndexOfBackoffDelayDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                    int _cursorIndexOfMRequiresBatteryNotLow = _cursorIndexOfBackoffDelayDuration2;
                    int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                    int _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime;
                    int _cursorIndexOfMinimumRetentionDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                    int _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration2;
                    int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                    int _cursorIndexOfFlexDuration2 = _cursorIndexOfScheduleRequestedAt;
                    int _cursorIndexOfScheduleRequestedAt2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                    int _tmp_11 = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                    int _cursorIndexOfOutput2 = _cursorIndexOfOutput;
                    int _cursorIndexOfOutput3 = _cursor.getCount();
                    List<WorkSpec> _result = new ArrayList<>(_cursorIndexOfOutput3);
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                        Constraints _tmpConstraints = new Constraints();
                        int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                        NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                        int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                        int _cursorIndexOfId2 = _cursorIndexOfId;
                        _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                        int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                        boolean _tmpMRequiresCharging = _tmp_1 != 0;
                        int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                        _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                        int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                        boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                        _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                        int _tmp_3 = _cursor.getInt(_cursorIndexOfMinimumRetentionDuration);
                        boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                        _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                        int _tmp_4 = _cursor.getInt(_cursorIndexOfBackoffDelayDuration);
                        boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                        _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                        long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                        int _cursorIndexOfMRequiresDeviceIdle2 = _cursorIndexOfMRequiresDeviceIdle;
                        _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                        long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                        _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                        byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                        ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                        _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                        WorkSpec _item = new WorkSpec(_tmpId, _tmpWorkerClassName);
                        int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                        _item.state = WorkTypeConverters.intToState(_tmp_6);
                        _item.inputMergerClassName = _cursor.getString(_tmp_10);
                        byte[] _tmp_7 = _cursor.getBlob(_cursorIndexOfInput);
                        int _cursorIndexOfInput2 = _cursorIndexOfInput;
                        _item.input = Data.fromByteArray(_tmp_7);
                        int _cursorIndexOfOutput4 = _cursorIndexOfOutput2;
                        byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput4);
                        _item.output = Data.fromByteArray(_tmp_8);
                        int _cursorIndexOfInitialDelay3 = _cursorIndexOfInputMergerClassName;
                        int _cursorIndexOfInitialDelay4 = _tmp_10;
                        _item.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                        int _cursorIndexOfMRequiresBatteryNotLow2 = _cursorIndexOfMinimumRetentionDuration;
                        int _cursorIndexOfIntervalDuration3 = _cursorIndexOfMRequiresStorageNotLow;
                        int _cursorIndexOfIntervalDuration4 = _cursorIndexOfBackoffDelayDuration;
                        _item.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration3);
                        int _cursorIndexOfFlexDuration3 = _cursorIndexOfIntervalDuration2;
                        _item.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                        int _cursorIndexOfRunAttemptCount3 = _tmp_9;
                        _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount3);
                        int _cursorIndexOfBackoffPolicy = _cursorIndexOfRunAttemptCount2;
                        int _tmp_12 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                        _item.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_12);
                        int _cursorIndexOfBackoffDelayDuration3 = _cursorIndexOfMRequiresBatteryNotLow;
                        _item.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration3);
                        int _cursorIndexOfPeriodStartTime3 = _cursorIndexOfInitialDelay2;
                        _item.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime3);
                        int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfPeriodStartTime2;
                        _item.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                        int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfFlexDuration2;
                        _item.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                        int _cursorIndexOfExpedited = _cursorIndexOfScheduleRequestedAt2;
                        int _tmp_13 = _cursor.getInt(_cursorIndexOfExpedited);
                        _item.expedited = _tmp_13 != 0;
                        int _cursorIndexOfOutOfQuotaPolicy = _tmp_11;
                        int _tmp_14 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                        _item.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_14);
                        _item.constraints = _tmpConstraints;
                        _result.add(_item);
                        _tmp_10 = _cursorIndexOfInitialDelay4;
                        _tmp_9 = _cursorIndexOfRunAttemptCount3;
                        _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay3;
                        _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime3;
                        _cursorIndexOfId = _cursorIndexOfId2;
                        _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                        _tmp_11 = _cursorIndexOfOutOfQuotaPolicy;
                        _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                        _cursorIndexOfMRequiresDeviceIdle = _cursorIndexOfMRequiresDeviceIdle2;
                        _cursorIndexOfInput = _cursorIndexOfInput2;
                        _cursorIndexOfOutput2 = _cursorIndexOfOutput4;
                        _cursorIndexOfRunAttemptCount2 = _cursorIndexOfBackoffPolicy;
                        _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration3;
                        _cursorIndexOfMinimumRetentionDuration = _cursorIndexOfMRequiresBatteryNotLow2;
                        _cursorIndexOfMRequiresBatteryNotLow = _cursorIndexOfBackoffDelayDuration3;
                        _cursorIndexOfBackoffDelayDuration = _cursorIndexOfIntervalDuration4;
                        _cursorIndexOfMRequiresStorageNotLow = _cursorIndexOfIntervalDuration3;
                        _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration3;
                        _cursorIndexOfFlexDuration2 = _cursorIndexOfScheduleRequestedAt3;
                        _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited;
                    }
                    _cursor.close();
                    _statement.release();
                    return _result;
                } catch (Throwable th) {
                    th = th;
                    _cursor.close();
                    _statement.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                _statement = _statement2;
            }
        } catch (Throwable th3) {
            th = th3;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRunningWork() throws Throwable {
        RoomSQLiteQuery _statement;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfBackoffDelayDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfMContentUriTriggers = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
            int _tmp_10 = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
            int _cursorIndexOfInput = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
            try {
                int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                _statement = _statement2;
                try {
                    int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                    int _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay;
                    int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                    int _cursorIndexOfMRequiresStorageNotLow = _cursorIndexOfIntervalDuration;
                    int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                    int _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration;
                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                    int _tmp_9 = _cursorIndexOfRunAttemptCount;
                    int _cursorIndexOfRunAttemptCount2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                    int _cursorIndexOfBackoffDelayDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                    int _cursorIndexOfMRequiresBatteryNotLow = _cursorIndexOfBackoffDelayDuration2;
                    int _cursorIndexOfPeriodStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                    int _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime;
                    int _cursorIndexOfMinimumRetentionDuration2 = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                    int _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration2;
                    int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                    int _cursorIndexOfFlexDuration2 = _cursorIndexOfScheduleRequestedAt;
                    int _cursorIndexOfScheduleRequestedAt2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                    int _tmp_11 = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                    int _cursorIndexOfOutput2 = _cursorIndexOfOutput;
                    int _cursorIndexOfOutput3 = _cursor.getCount();
                    List<WorkSpec> _result = new ArrayList<>(_cursorIndexOfOutput3);
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                        Constraints _tmpConstraints = new Constraints();
                        int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                        NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                        int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                        int _cursorIndexOfId2 = _cursorIndexOfId;
                        _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                        int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                        boolean _tmpMRequiresCharging = _tmp_1 != 0;
                        int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                        _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                        int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                        boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                        _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                        int _tmp_3 = _cursor.getInt(_cursorIndexOfMinimumRetentionDuration);
                        boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                        _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                        int _tmp_4 = _cursor.getInt(_cursorIndexOfBackoffDelayDuration);
                        boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                        _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                        long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                        int _cursorIndexOfMRequiresDeviceIdle2 = _cursorIndexOfMRequiresDeviceIdle;
                        _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                        long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                        _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                        byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfMContentUriTriggers);
                        ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                        _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                        WorkSpec _item = new WorkSpec(_tmpId, _tmpWorkerClassName);
                        int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                        _item.state = WorkTypeConverters.intToState(_tmp_6);
                        _item.inputMergerClassName = _cursor.getString(_tmp_10);
                        byte[] _tmp_7 = _cursor.getBlob(_cursorIndexOfInput);
                        int _cursorIndexOfInput2 = _cursorIndexOfInput;
                        _item.input = Data.fromByteArray(_tmp_7);
                        int _cursorIndexOfOutput4 = _cursorIndexOfOutput2;
                        byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput4);
                        _item.output = Data.fromByteArray(_tmp_8);
                        int _cursorIndexOfInitialDelay3 = _cursorIndexOfInputMergerClassName;
                        int _cursorIndexOfInitialDelay4 = _tmp_10;
                        _item.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                        int _cursorIndexOfMRequiresBatteryNotLow2 = _cursorIndexOfMinimumRetentionDuration;
                        int _cursorIndexOfIntervalDuration3 = _cursorIndexOfMRequiresStorageNotLow;
                        int _cursorIndexOfIntervalDuration4 = _cursorIndexOfBackoffDelayDuration;
                        _item.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration3);
                        int _cursorIndexOfFlexDuration3 = _cursorIndexOfIntervalDuration2;
                        _item.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                        int _cursorIndexOfRunAttemptCount3 = _tmp_9;
                        _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount3);
                        int _cursorIndexOfBackoffPolicy = _cursorIndexOfRunAttemptCount2;
                        int _tmp_12 = _cursor.getInt(_cursorIndexOfBackoffPolicy);
                        _item.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_12);
                        int _cursorIndexOfBackoffDelayDuration3 = _cursorIndexOfMRequiresBatteryNotLow;
                        _item.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration3);
                        int _cursorIndexOfPeriodStartTime3 = _cursorIndexOfInitialDelay2;
                        _item.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime3);
                        int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfPeriodStartTime2;
                        _item.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                        int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfFlexDuration2;
                        _item.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                        int _cursorIndexOfExpedited = _cursorIndexOfScheduleRequestedAt2;
                        int _tmp_13 = _cursor.getInt(_cursorIndexOfExpedited);
                        _item.expedited = _tmp_13 != 0;
                        int _cursorIndexOfOutOfQuotaPolicy = _tmp_11;
                        int _tmp_14 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                        _item.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_14);
                        _item.constraints = _tmpConstraints;
                        _result.add(_item);
                        _tmp_10 = _cursorIndexOfInitialDelay4;
                        _tmp_9 = _cursorIndexOfRunAttemptCount3;
                        _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay3;
                        _cursorIndexOfInitialDelay2 = _cursorIndexOfPeriodStartTime3;
                        _cursorIndexOfId = _cursorIndexOfId2;
                        _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                        _tmp_11 = _cursorIndexOfOutOfQuotaPolicy;
                        _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                        _cursorIndexOfMRequiresDeviceIdle = _cursorIndexOfMRequiresDeviceIdle2;
                        _cursorIndexOfInput = _cursorIndexOfInput2;
                        _cursorIndexOfOutput2 = _cursorIndexOfOutput4;
                        _cursorIndexOfRunAttemptCount2 = _cursorIndexOfBackoffPolicy;
                        _cursorIndexOfPeriodStartTime2 = _cursorIndexOfMinimumRetentionDuration3;
                        _cursorIndexOfMinimumRetentionDuration = _cursorIndexOfMRequiresBatteryNotLow2;
                        _cursorIndexOfMRequiresBatteryNotLow = _cursorIndexOfBackoffDelayDuration3;
                        _cursorIndexOfBackoffDelayDuration = _cursorIndexOfIntervalDuration4;
                        _cursorIndexOfMRequiresStorageNotLow = _cursorIndexOfIntervalDuration3;
                        _cursorIndexOfIntervalDuration2 = _cursorIndexOfFlexDuration3;
                        _cursorIndexOfFlexDuration2 = _cursorIndexOfScheduleRequestedAt3;
                        _cursorIndexOfScheduleRequestedAt2 = _cursorIndexOfExpedited;
                    }
                    _cursor.close();
                    _statement.release();
                    return _result;
                } catch (Throwable th) {
                    th = th;
                    _cursor.close();
                    _statement.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                _statement = _statement2;
            }
        } catch (Throwable th3) {
            th = th3;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRecentlyCompletedWork(final long startingAt) throws Throwable {
        RoomSQLiteQuery _statement;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE period_start_time >= ? AND state IN (2, 3, 5) ORDER BY period_start_time DESC", 1);
        _statement2.bindLong(1, startingAt);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfMRequiredNetworkType = CursorUtil.getColumnIndexOrThrow(_cursor, "required_network_type");
            int _cursorIndexOfMRequiresCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_charging");
            int _cursorIndexOfMRequiresDeviceIdle = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_device_idle");
            int _cursorIndexOfMRequiresBatteryNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_battery_not_low");
            int _cursorIndexOfMRequiresStorageNotLow = CursorUtil.getColumnIndexOrThrow(_cursor, "requires_storage_not_low");
            int _cursorIndexOfMTriggerContentUpdateDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_content_update_delay");
            int _cursorIndexOfMTriggerMaxContentDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger_max_content_delay");
            int _cursorIndexOfExpedited = CursorUtil.getColumnIndexOrThrow(_cursor, "content_uri_triggers");
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            try {
                int _cursorIndexOfWorkerClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "worker_class_name");
                _statement = _statement2;
                try {
                    int _tmp_10 = CursorUtil.getColumnIndexOrThrow(_cursor, "input_merger_class_name");
                    try {
                        int _cursorIndexOfScheduleRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "input");
                        int _cursorIndexOfOutput = CursorUtil.getColumnIndexOrThrow(_cursor, "output");
                        int _cursorIndexOfInitialDelay = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_delay");
                        int _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay;
                        int _cursorIndexOfIntervalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "interval_duration");
                        int _cursorIndexOfInput = _cursorIndexOfIntervalDuration;
                        int _cursorIndexOfFlexDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "flex_duration");
                        int _cursorIndexOfFlexDuration2 = _cursorIndexOfFlexDuration;
                        int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "run_attempt_count");
                        int _tmp_9 = _cursorIndexOfRunAttemptCount;
                        int _cursorIndexOfBackoffPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_policy");
                        int _cursorIndexOfInitialDelay2 = _cursorIndexOfBackoffPolicy;
                        int _cursorIndexOfBackoffDelayDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "backoff_delay_duration");
                        int _cursorIndexOfIntervalDuration2 = _cursorIndexOfBackoffDelayDuration;
                        int _cursorIndexOfRunAttemptCount2 = CursorUtil.getColumnIndexOrThrow(_cursor, "period_start_time");
                        int _cursorIndexOfMinimumRetentionDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "minimum_retention_duration");
                        int _cursorIndexOfBackoffPolicy2 = _cursorIndexOfMinimumRetentionDuration;
                        int _cursorIndexOfScheduleRequestedAt2 = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule_requested_at");
                        int _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt2;
                        int _cursorIndexOfExpedited2 = CursorUtil.getColumnIndexOrThrow(_cursor, "run_in_foreground");
                        int _cursorIndexOfBackoffDelayDuration2 = _cursorIndexOfExpedited2;
                        int _tmp_11 = CursorUtil.getColumnIndexOrThrow(_cursor, "out_of_quota_policy");
                        int _cursorIndexOfOutput2 = _cursorIndexOfOutput;
                        int _cursorIndexOfOutput3 = _cursor.getCount();
                        List<WorkSpec> _result = new ArrayList<>(_cursorIndexOfOutput3);
                        while (_cursor.moveToNext()) {
                            String _tmpId = _cursor.getString(_cursorIndexOfId);
                            String _tmpWorkerClassName = _cursor.getString(_cursorIndexOfWorkerClassName);
                            Constraints _tmpConstraints = new Constraints();
                            int _tmp = _cursor.getInt(_cursorIndexOfMRequiredNetworkType);
                            NetworkType _tmpMRequiredNetworkType = WorkTypeConverters.intToNetworkType(_tmp);
                            int _cursorIndexOfMRequiredNetworkType2 = _cursorIndexOfMRequiredNetworkType;
                            int _cursorIndexOfWorkerClassName2 = _cursorIndexOfWorkerClassName;
                            _tmpConstraints.setRequiredNetworkType(_tmpMRequiredNetworkType);
                            int _tmp_1 = _cursor.getInt(_cursorIndexOfMRequiresCharging);
                            boolean _tmpMRequiresCharging = _tmp_1 != 0;
                            _tmpConstraints.setRequiresCharging(_tmpMRequiresCharging);
                            int _tmp_2 = _cursor.getInt(_cursorIndexOfMRequiresDeviceIdle);
                            boolean _tmpMRequiresDeviceIdle = _tmp_2 != 0;
                            _tmpConstraints.setRequiresDeviceIdle(_tmpMRequiresDeviceIdle);
                            int _tmp_3 = _cursor.getInt(_cursorIndexOfMRequiresBatteryNotLow);
                            boolean _tmpMRequiresBatteryNotLow = _tmp_3 != 0;
                            _tmpConstraints.setRequiresBatteryNotLow(_tmpMRequiresBatteryNotLow);
                            int _tmp_4 = _cursor.getInt(_cursorIndexOfMRequiresStorageNotLow);
                            boolean _tmpMRequiresStorageNotLow = _tmp_4 != 0;
                            _tmpConstraints.setRequiresStorageNotLow(_tmpMRequiresStorageNotLow);
                            long _tmpMTriggerContentUpdateDelay = _cursor.getLong(_cursorIndexOfMTriggerContentUpdateDelay);
                            int _cursorIndexOfId2 = _cursorIndexOfId;
                            int _cursorIndexOfMRequiresCharging2 = _cursorIndexOfMRequiresCharging;
                            _tmpConstraints.setTriggerContentUpdateDelay(_tmpMTriggerContentUpdateDelay);
                            long _tmpMTriggerMaxContentDelay = _cursor.getLong(_cursorIndexOfMTriggerMaxContentDelay);
                            _tmpConstraints.setTriggerMaxContentDelay(_tmpMTriggerMaxContentDelay);
                            byte[] _tmp_5 = _cursor.getBlob(_cursorIndexOfExpedited);
                            ContentUriTriggers _tmpMContentUriTriggers = WorkTypeConverters.byteArrayToContentUriTriggers(_tmp_5);
                            _tmpConstraints.setContentUriTriggers(_tmpMContentUriTriggers);
                            int _cursorIndexOfMContentUriTriggers = _cursorIndexOfExpedited;
                            WorkSpec _item = new WorkSpec(_tmpId, _tmpWorkerClassName);
                            int _tmp_6 = _cursor.getInt(_cursorIndexOfState);
                            int _cursorIndexOfState2 = _cursorIndexOfState;
                            _item.state = WorkTypeConverters.intToState(_tmp_6);
                            _item.inputMergerClassName = _cursor.getString(_tmp_10);
                            byte[] _tmp_7 = _cursor.getBlob(_cursorIndexOfScheduleRequestedAt);
                            _item.input = Data.fromByteArray(_tmp_7);
                            int _cursorIndexOfOutput4 = _cursorIndexOfOutput2;
                            byte[] _tmp_8 = _cursor.getBlob(_cursorIndexOfOutput4);
                            _item.output = Data.fromByteArray(_tmp_8);
                            int _cursorIndexOfInitialDelay3 = _cursorIndexOfInputMergerClassName;
                            int _cursorIndexOfInitialDelay4 = _tmp_10;
                            _item.initialDelay = _cursor.getLong(_cursorIndexOfInitialDelay3);
                            int _cursorIndexOfIntervalDuration3 = _cursorIndexOfInput;
                            int _cursorIndexOfIntervalDuration4 = _cursorIndexOfScheduleRequestedAt;
                            _item.intervalDuration = _cursor.getLong(_cursorIndexOfIntervalDuration3);
                            int _cursorIndexOfFlexDuration3 = _cursorIndexOfFlexDuration2;
                            _item.flexDuration = _cursor.getLong(_cursorIndexOfFlexDuration3);
                            int _cursorIndexOfRunAttemptCount3 = _tmp_9;
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount3);
                            int _cursorIndexOfBackoffPolicy3 = _cursorIndexOfInitialDelay2;
                            int _tmp_12 = _cursor.getInt(_cursorIndexOfBackoffPolicy3);
                            _item.backoffPolicy = WorkTypeConverters.intToBackoffPolicy(_tmp_12);
                            int _cursorIndexOfBackoffDelayDuration3 = _cursorIndexOfIntervalDuration2;
                            _item.backoffDelayDuration = _cursor.getLong(_cursorIndexOfBackoffDelayDuration3);
                            int _cursorIndexOfPeriodStartTime = _cursorIndexOfRunAttemptCount2;
                            _item.periodStartTime = _cursor.getLong(_cursorIndexOfPeriodStartTime);
                            int _cursorIndexOfMinimumRetentionDuration3 = _cursorIndexOfBackoffPolicy2;
                            _item.minimumRetentionDuration = _cursor.getLong(_cursorIndexOfMinimumRetentionDuration3);
                            int _cursorIndexOfScheduleRequestedAt3 = _cursorIndexOfMinimumRetentionDuration2;
                            _item.scheduleRequestedAt = _cursor.getLong(_cursorIndexOfScheduleRequestedAt3);
                            int _cursorIndexOfExpedited3 = _cursorIndexOfBackoffDelayDuration2;
                            int _tmp_13 = _cursor.getInt(_cursorIndexOfExpedited3);
                            _item.expedited = _tmp_13 != 0;
                            int _cursorIndexOfOutOfQuotaPolicy = _tmp_11;
                            int _tmp_14 = _cursor.getInt(_cursorIndexOfOutOfQuotaPolicy);
                            _item.outOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(_tmp_14);
                            _item.constraints = _tmpConstraints;
                            _result.add(_item);
                            _tmp_10 = _cursorIndexOfInitialDelay4;
                            _cursorIndexOfInputMergerClassName = _cursorIndexOfInitialDelay3;
                            _tmp_9 = _cursorIndexOfRunAttemptCount3;
                            _cursorIndexOfInitialDelay2 = _cursorIndexOfBackoffPolicy3;
                            _cursorIndexOfBackoffPolicy2 = _cursorIndexOfMinimumRetentionDuration3;
                            _cursorIndexOfWorkerClassName = _cursorIndexOfWorkerClassName2;
                            _cursorIndexOfMRequiredNetworkType = _cursorIndexOfMRequiredNetworkType2;
                            _cursorIndexOfMRequiresCharging = _cursorIndexOfMRequiresCharging2;
                            _cursorIndexOfId = _cursorIndexOfId2;
                            _cursorIndexOfState = _cursorIndexOfState2;
                            _cursorIndexOfOutput2 = _cursorIndexOfOutput4;
                            _cursorIndexOfFlexDuration2 = _cursorIndexOfFlexDuration3;
                            _tmp_11 = _cursorIndexOfOutOfQuotaPolicy;
                            _cursorIndexOfMinimumRetentionDuration2 = _cursorIndexOfScheduleRequestedAt3;
                            _cursorIndexOfRunAttemptCount2 = _cursorIndexOfPeriodStartTime;
                            _cursorIndexOfScheduleRequestedAt = _cursorIndexOfIntervalDuration4;
                            _cursorIndexOfInput = _cursorIndexOfIntervalDuration3;
                            _cursorIndexOfIntervalDuration2 = _cursorIndexOfBackoffDelayDuration3;
                            _cursorIndexOfBackoffDelayDuration2 = _cursorIndexOfExpedited3;
                            _cursorIndexOfExpedited = _cursorIndexOfMContentUriTriggers;
                        }
                        _cursor.close();
                        _statement.release();
                        return _result;
                    } catch (Throwable th) {
                        th = th;
                        _cursor.close();
                        _statement.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                _statement = _statement2;
            }
        } catch (Throwable th4) {
            th = th4;
            _statement = _statement2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(final WorkInfo.State state, final String... ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE workspec SET state=");
        _stringBuilder.append("?");
        _stringBuilder.append(" WHERE id IN (");
        int _inputSize = ids.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        SupportSQLiteStatement _stmt = this.__db.compileStatement(_sql);
        int _tmp = WorkTypeConverters.stateToInt(state);
        _stmt.bindLong(1, _tmp);
        int _argIndex = 2;
        for (String _item : ids) {
            if (_item == null) {
                _stmt.bindNull(_argIndex);
            } else {
                _stmt.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        Set<String> __mapKeySet = _map.keySet();
        if (__mapKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<String>> _tmpInnerMap = new ArrayMap<>(999);
            int _tmpIndex = 0;
            int _mapIndex = 0;
            int _limit = _map.getSize();
            while (_mapIndex < _limit) {
                _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
                _mapIndex++;
                _tmpIndex++;
                if (_tmpIndex == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(_tmpInnerMap);
                    _tmpInnerMap = new ArrayMap<>(999);
                    _tmpIndex = 0;
                }
            }
            if (_tmpIndex > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(_tmpInnerMap);
                return;
            }
            return;
        }
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int _inputSize = __mapKeySet.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : __mapKeySet) {
            if (_item == null) {
                _stmt.bindNull(_argIndex);
            } else {
                _stmt.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        Cursor _cursor = DBUtil.query(this.__db, _stmt, false, null);
        try {
            int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "work_spec_id");
            if (_itemKeyIndex != -1) {
                while (_cursor.moveToNext()) {
                    if (!_cursor.isNull(_itemKeyIndex)) {
                        String _tmpKey = _cursor.getString(_itemKeyIndex);
                        ArrayList<String> _tmpRelation = _map.get(_tmpKey);
                        if (_tmpRelation != null) {
                            String _item_1 = _cursor.getString(0);
                            _tmpRelation.add(_item_1);
                        } else {
                            continue;
                        }
                    }
                }
                _cursor.close();
                return;
            }
            _cursor.close();
        } catch (Throwable th) {
            _cursor.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        Set<String> __mapKeySet = _map.keySet();
        if (__mapKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<Data>> _tmpInnerMap = new ArrayMap<>(999);
            int _tmpIndex = 0;
            int _mapIndex = 0;
            int _limit = _map.getSize();
            while (_mapIndex < _limit) {
                _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
                _mapIndex++;
                _tmpIndex++;
                if (_tmpIndex == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(_tmpInnerMap);
                    _tmpInnerMap = new ArrayMap<>(999);
                    _tmpIndex = 0;
                }
            }
            if (_tmpIndex > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(_tmpInnerMap);
                return;
            }
            return;
        }
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int _inputSize = __mapKeySet.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : __mapKeySet) {
            if (_item == null) {
                _stmt.bindNull(_argIndex);
            } else {
                _stmt.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        Cursor _cursor = DBUtil.query(this.__db, _stmt, false, null);
        try {
            int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "work_spec_id");
            if (_itemKeyIndex != -1) {
                while (_cursor.moveToNext()) {
                    if (!_cursor.isNull(_itemKeyIndex)) {
                        String _tmpKey = _cursor.getString(_itemKeyIndex);
                        ArrayList<Data> _tmpRelation = _map.get(_tmpKey);
                        if (_tmpRelation != null) {
                            byte[] _tmp = _cursor.getBlob(0);
                            Data _item_1 = Data.fromByteArray(_tmp);
                            _tmpRelation.add(_item_1);
                        } else {
                            continue;
                        }
                    }
                }
                _cursor.close();
                return;
            }
            _cursor.close();
        } catch (Throwable th) {
            _cursor.close();
            throw th;
        }
    }
}
