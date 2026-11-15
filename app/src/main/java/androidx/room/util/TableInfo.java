package androidx.room.util;

import android.database.Cursor;
import android.os.Build;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public class TableInfo {
    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN = 0;
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys, Set<Index> indices) {
        this.name = name;
        this.columns = Collections.unmodifiableMap(columns);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys);
        this.indices = indices == null ? null : Collections.unmodifiableSet(indices);
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys) {
        this(name, columns, foreignKeys, Collections.emptySet());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        if (this.name == null ? tableInfo.name != null : !this.name.equals(tableInfo.name)) {
            return false;
        }
        if (this.columns == null ? tableInfo.columns != null : !this.columns.equals(tableInfo.columns)) {
            return false;
        }
        if (this.foreignKeys == null ? tableInfo.foreignKeys != null : !this.foreignKeys.equals(tableInfo.foreignKeys)) {
            return false;
        }
        if (this.indices == null || tableInfo.indices == null) {
            return true;
        }
        return this.indices.equals(tableInfo.indices);
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        return (((result * 31) + (this.columns != null ? this.columns.hashCode() : 0)) * 31) + (this.foreignKeys != null ? this.foreignKeys.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + AbstractJsonLexerKt.END_OBJ;
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        Map<String, Column> columns = readColumns(database, tableName);
        Set<ForeignKey> foreignKeys = readForeignKeys(database, tableName);
        Set<Index> indices = readIndices(database, tableName);
        return new TableInfo(tableName, columns, foreignKeys, indices);
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        int idColumnIndex;
        Set<ForeignKey> foreignKeys = new HashSet<>();
        Cursor cursor = database.query("PRAGMA foreign_key_list(`" + tableName + "`)");
        try {
            int idColumnIndex2 = cursor.getColumnIndex("id");
            int seqColumnIndex = cursor.getColumnIndex("seq");
            int tableColumnIndex = cursor.getColumnIndex("table");
            int onDeleteColumnIndex = cursor.getColumnIndex("on_delete");
            int onUpdateColumnIndex = cursor.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> ordered = readForeignKeyFieldMappings(cursor);
            int count = cursor.getCount();
            int position = 0;
            while (position < count) {
                cursor.moveToPosition(position);
                int seq = cursor.getInt(seqColumnIndex);
                if (seq != 0) {
                    idColumnIndex = idColumnIndex2;
                } else {
                    int id = cursor.getInt(idColumnIndex2);
                    List<String> myColumns = new ArrayList<>();
                    List<String> refColumns = new ArrayList<>();
                    for (ForeignKeyWithSequence key : ordered) {
                        int idColumnIndex3 = idColumnIndex2;
                        if (key.mId == id) {
                            myColumns.add(key.mFrom);
                            refColumns.add(key.mTo);
                        }
                        idColumnIndex2 = idColumnIndex3;
                    }
                    idColumnIndex = idColumnIndex2;
                    foreignKeys.add(new ForeignKey(cursor.getString(tableColumnIndex), cursor.getString(onDeleteColumnIndex), cursor.getString(onUpdateColumnIndex), myColumns, refColumns));
                }
                position++;
                idColumnIndex2 = idColumnIndex;
            }
            return foreignKeys;
        } finally {
            cursor.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex("id");
        int seqColumnIndex = cursor.getColumnIndex("seq");
        int fromColumnIndex = cursor.getColumnIndex(TypedValues.TransitionType.S_FROM);
        int toColumnIndex = cursor.getColumnIndex(TypedValues.TransitionType.S_TO);
        int count = cursor.getCount();
        List<ForeignKeyWithSequence> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            result.add(new ForeignKeyWithSequence(cursor.getInt(idColumnIndex), cursor.getInt(seqColumnIndex), cursor.getString(fromColumnIndex), cursor.getString(toColumnIndex)));
        }
        Collections.sort(result);
        return result;
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA table_info(`" + tableName + "`)");
        Map<String, Column> columns = new HashMap<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex(HintConstants.AUTOFILL_HINT_NAME);
                int typeIndex = cursor.getColumnIndex("type");
                int notNullIndex = cursor.getColumnIndex("notnull");
                int pkIndex = cursor.getColumnIndex("pk");
                int defaultValueIndex = cursor.getColumnIndex("dflt_value");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String type = cursor.getString(typeIndex);
                    boolean notNull = cursor.getInt(notNullIndex) != 0;
                    int primaryKeyPosition = cursor.getInt(pkIndex);
                    String defaultValue = cursor.getString(defaultValueIndex);
                    columns.put(name, new Column(name, type, notNull, primaryKeyPosition, defaultValue, 2));
                }
            }
            return columns;
        } finally {
            cursor.close();
        }
    }

    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA index_list(`" + tableName + "`)");
        try {
            int nameColumnIndex = cursor.getColumnIndex(HintConstants.AUTOFILL_HINT_NAME);
            int originColumnIndex = cursor.getColumnIndex("origin");
            int uniqueIndex = cursor.getColumnIndex("unique");
            if (nameColumnIndex == -1 || originColumnIndex == -1 || uniqueIndex == -1) {
                cursor.close();
                return null;
            }
            HashSet<Index> indices = new HashSet<>();
            while (cursor.moveToNext()) {
                String origin = cursor.getString(originColumnIndex);
                if ("c".equals(origin)) {
                    String name = cursor.getString(nameColumnIndex);
                    boolean unique = cursor.getInt(uniqueIndex) == 1;
                    Index index = readIndex(database, name, unique);
                    if (index == null) {
                        cursor.close();
                        return null;
                    }
                    indices.add(index);
                }
            }
            cursor.close();
            return indices;
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    private static Index readIndex(SupportSQLiteDatabase database, String name, boolean unique) {
        Cursor cursor = database.query("PRAGMA index_xinfo(`" + name + "`)");
        try {
            int seqnoColumnIndex = cursor.getColumnIndex("seqno");
            int cidColumnIndex = cursor.getColumnIndex(CmcdConfiguration.KEY_CONTENT_ID);
            int nameColumnIndex = cursor.getColumnIndex(HintConstants.AUTOFILL_HINT_NAME);
            if (seqnoColumnIndex != -1 && cidColumnIndex != -1 && nameColumnIndex != -1) {
                TreeMap<Integer, String> results = new TreeMap<>();
                while (cursor.moveToNext()) {
                    int cid = cursor.getInt(cidColumnIndex);
                    if (cid >= 0) {
                        int seq = cursor.getInt(seqnoColumnIndex);
                        String columnName = cursor.getString(nameColumnIndex);
                        results.put(Integer.valueOf(seq), columnName);
                    }
                }
                List<String> columns = new ArrayList<>(results.size());
                columns.addAll(results.values());
                return new Index(name, unique, columns);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    public static class Column {
        public final int affinity;
        public final String defaultValue;
        private final int mCreatedFrom;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        @Deprecated
        public Column(String name, String type, boolean notNull, int primaryKeyPosition) {
            this(name, type, notNull, primaryKeyPosition, null, 0);
        }

        public Column(String name, String type, boolean notNull, int primaryKeyPosition, String defaultValue, int createdFrom) {
            this.name = name;
            this.type = type;
            this.notNull = notNull;
            this.primaryKeyPosition = primaryKeyPosition;
            this.affinity = findAffinity(type);
            this.defaultValue = defaultValue;
            this.mCreatedFrom = createdFrom;
        }

        private static int findAffinity(String type) {
            if (type == null) {
                return 5;
            }
            String uppercaseType = type.toUpperCase(Locale.US);
            if (uppercaseType.contains("INT")) {
                return 3;
            }
            if (uppercaseType.contains("CHAR") || uppercaseType.contains("CLOB") || uppercaseType.contains("TEXT")) {
                return 2;
            }
            if (uppercaseType.contains("BLOB")) {
                return 5;
            }
            if (uppercaseType.contains("REAL") || uppercaseType.contains("FLOA") || uppercaseType.contains("DOUB")) {
                return 4;
            }
            return 1;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Column column = (Column) o;
            int i = Build.VERSION.SDK_INT;
            if (this.primaryKeyPosition == column.primaryKeyPosition && this.name.equals(column.name) && this.notNull == column.notNull) {
                if (this.mCreatedFrom == 1 && column.mCreatedFrom == 2 && this.defaultValue != null && !this.defaultValue.equals(column.defaultValue)) {
                    return false;
                }
                if (this.mCreatedFrom == 2 && column.mCreatedFrom == 1 && column.defaultValue != null && !column.defaultValue.equals(this.defaultValue)) {
                    return false;
                }
                if ((this.mCreatedFrom == 0 || this.mCreatedFrom != column.mCreatedFrom || (this.defaultValue == null ? column.defaultValue == null : this.defaultValue.equals(column.defaultValue))) && this.affinity == column.affinity) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((((result * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            return "Column{name='" + this.name + "', type='" + this.type + "', affinity='" + this.affinity + "', notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + ", defaultValue='" + this.defaultValue + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String referenceTable, String onDelete, String onUpdate, List<String> columnNames, List<String> referenceColumnNames) {
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = Collections.unmodifiableList(columnNames);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ForeignKey that = (ForeignKey) o;
            if (!this.referenceTable.equals(that.referenceTable) || !this.onDelete.equals(that.onDelete) || !this.onUpdate.equals(that.onUpdate) || !this.columnNames.equals(that.columnNames)) {
                return false;
            }
            return this.referenceColumnNames.equals(that.referenceColumnNames);
        }

        public int hashCode() {
            int result = this.referenceTable.hashCode();
            return (((((((result * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + "', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + AbstractJsonLexerKt.END_OBJ;
        }
    }

    static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id, int sequence, String from, String to) {
            this.mId = id;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        @Override // java.lang.Comparable
        public int compareTo(ForeignKeyWithSequence o) {
            int idCmp = this.mId - o.mId;
            if (idCmp == 0) {
                return this.mSequence - o.mSequence;
            }
            return idCmp;
        }
    }

    public static class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name, boolean unique, List<String> columns) {
            this.name = name;
            this.unique = unique;
            this.columns = columns;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Index index = (Index) o;
            if (this.unique != index.unique || !this.columns.equals(index.columns)) {
                return false;
            }
            if (this.name.startsWith(DEFAULT_PREFIX)) {
                return index.name.startsWith(DEFAULT_PREFIX);
            }
            return this.name.equals(index.name);
        }

        public int hashCode() {
            int iHashCode;
            if (this.name.startsWith(DEFAULT_PREFIX)) {
                iHashCode = DEFAULT_PREFIX.hashCode();
            } else {
                iHashCode = this.name.hashCode();
            }
            return (((iHashCode * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
