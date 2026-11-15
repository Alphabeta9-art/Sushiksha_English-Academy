package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes11.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(final SupportSQLiteQuery query) {
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, query, true, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndex(_cursor, "id");
            int _cursorIndexOfState = CursorUtil.getColumnIndex(_cursor, "state");
            int _cursorIndexOfOutput = CursorUtil.getColumnIndex(_cursor, "output");
            int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndex(_cursor, "run_attempt_count");
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
                if (_cursorIndexOfId != -1) {
                    _item.id = _cursor.getString(_cursorIndexOfId);
                }
                if (_cursorIndexOfState != -1) {
                    int _tmp = _cursor.getInt(_cursorIndexOfState);
                    _item.state = WorkTypeConverters.intToState(_tmp);
                }
                if (_cursorIndexOfOutput != -1) {
                    byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                    _item.output = Data.fromByteArray(_tmp_1);
                }
                if (_cursorIndexOfRunAttemptCount != -1) {
                    _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                }
                _item.tags = _tmpTagsCollection_1;
                _item.progress = _tmpProgressCollection_1;
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Cursor _cursor = DBUtil.query(RawWorkInfoDao_Impl.this.__db, query, true, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndex(_cursor, "id");
                    int _cursorIndexOfState = CursorUtil.getColumnIndex(_cursor, "state");
                    int _cursorIndexOfOutput = CursorUtil.getColumnIndex(_cursor, "output");
                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndex(_cursor, "run_attempt_count");
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
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
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
                        if (_cursorIndexOfId != -1) {
                            _item.id = _cursor.getString(_cursorIndexOfId);
                        }
                        if (_cursorIndexOfState != -1) {
                            int _tmp = _cursor.getInt(_cursorIndexOfState);
                            _item.state = WorkTypeConverters.intToState(_tmp);
                        }
                        if (_cursorIndexOfOutput != -1) {
                            byte[] _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                            _item.output = Data.fromByteArray(_tmp_1);
                        }
                        if (_cursorIndexOfRunAttemptCount != -1) {
                            _item.runAttemptCount = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                        }
                        _item.tags = _tmpTagsCollection_1;
                        _item.progress = _tmpProgressCollection_1;
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }
        });
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
