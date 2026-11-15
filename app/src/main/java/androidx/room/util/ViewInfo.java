package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public class ViewInfo {
    public final String name;
    public final String sql;

    public ViewInfo(String name, String sql) {
        this.name = name;
        this.sql = sql;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) o;
        if (this.name == null ? viewInfo.name == null : this.name.equals(viewInfo.name)) {
            if (this.sql != null) {
                if (this.sql.equals(viewInfo.sql)) {
                    return true;
                }
            } else if (viewInfo.sql == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        return (result * 31) + (this.sql != null ? this.sql.hashCode() : 0);
    }

    public String toString() {
        return "ViewInfo{name='" + this.name + "', sql='" + this.sql + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public static ViewInfo read(SupportSQLiteDatabase database, String viewName) {
        Cursor cursor = database.query("SELECT name, sql FROM sqlite_master WHERE type = 'view' AND name = '" + viewName + "'");
        try {
            if (cursor.moveToFirst()) {
                return new ViewInfo(cursor.getString(0), cursor.getString(1));
            }
            return new ViewInfo(viewName, null);
        } finally {
            cursor.close();
        }
    }
}
