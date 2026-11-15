package androidx.room.util;

import android.database.Cursor;
import androidx.autofill.HintConstants;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public class FtsTableInfo {
    private static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public FtsTableInfo(String name, Set<String> columns, Set<String> options) {
        this.name = name;
        this.columns = columns;
        this.options = options;
    }

    public FtsTableInfo(String name, Set<String> columns, String createSql) {
        this.name = name;
        this.columns = columns;
        this.options = parseOptions(createSql);
    }

    public static FtsTableInfo read(SupportSQLiteDatabase database, String tableName) {
        Set<String> columns = readColumns(database, tableName);
        Set<String> options = readOptions(database, tableName);
        return new FtsTableInfo(tableName, columns, options);
    }

    private static Set<String> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA table_info(`" + tableName + "`)");
        Set<String> columns = new HashSet<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex(HintConstants.AUTOFILL_HINT_NAME);
                while (cursor.moveToNext()) {
                    columns.add(cursor.getString(nameIndex));
                }
            }
            return columns;
        } finally {
            cursor.close();
        }
    }

    private static Set<String> readOptions(SupportSQLiteDatabase database, String tableName) {
        String sql = "";
        Cursor cursor = database.query("SELECT * FROM sqlite_master WHERE `name` = '" + tableName + "'");
        try {
            if (cursor.moveToFirst()) {
                sql = cursor.getString(cursor.getColumnIndexOrThrow("sql"));
            }
            return parseOptions(sql);
        } finally {
            cursor.close();
        }
    }

    static Set<String> parseOptions(String createStatement) {
        if (createStatement.isEmpty()) {
            return new HashSet();
        }
        String argsString = createStatement.substring(createStatement.indexOf(40) + 1, createStatement.lastIndexOf(41));
        List<String> args = new ArrayList<>();
        ArrayDeque<Character> quoteStack = new ArrayDeque<>();
        int lastDelimiterIndex = -1;
        for (int i = 0; i < argsString.length(); i++) {
            char c = argsString.charAt(i);
            switch (c) {
                case '\"':
                case '\'':
                case '`':
                    if (quoteStack.isEmpty()) {
                        quoteStack.push(Character.valueOf(c));
                    } else if (quoteStack.peek().charValue() == c) {
                        quoteStack.pop();
                    }
                    break;
                case ',':
                    if (quoteStack.isEmpty()) {
                        args.add(argsString.substring(lastDelimiterIndex + 1, i).trim());
                        lastDelimiterIndex = i;
                    }
                    break;
                case '[':
                    if (quoteStack.isEmpty()) {
                        quoteStack.push(Character.valueOf(c));
                    }
                    break;
                case ']':
                    if (!quoteStack.isEmpty() && quoteStack.peek().charValue() == '[') {
                        quoteStack.pop();
                    }
                    break;
            }
        }
        int i2 = lastDelimiterIndex + 1;
        args.add(argsString.substring(i2).trim());
        HashSet<String> options = new HashSet<>();
        for (String arg : args) {
            for (String validOption : FTS_OPTIONS) {
                if (arg.startsWith(validOption)) {
                    options.add(arg);
                }
            }
        }
        return options;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FtsTableInfo that = (FtsTableInfo) o;
        if (this.name == null ? that.name != null : !this.name.equals(that.name)) {
            return false;
        }
        if (this.columns == null ? that.columns != null : !this.columns.equals(that.columns)) {
            return false;
        }
        if (this.options != null) {
            return this.options.equals(that.options);
        }
        if (that.options == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        return (((result * 31) + (this.columns != null ? this.columns.hashCode() : 0)) * 31) + (this.options != null ? this.options.hashCode() : 0);
    }

    public String toString() {
        return "FtsTableInfo{name='" + this.name + "', columns=" + this.columns + ", options=" + this.options + AbstractJsonLexerKt.END_OBJ;
    }
}
