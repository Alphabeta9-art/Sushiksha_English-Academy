package androidx.room.util;

import android.database.Cursor;
import android.database.MatrixCursor;

/* JADX INFO: loaded from: classes11.dex */
public class CursorUtil {
    public static Cursor copyAndClose(Cursor c) {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(c.getColumnNames(), c.getCount());
            while (c.moveToNext()) {
                Object[] row = new Object[c.getColumnCount()];
                for (int i = 0; i < c.getColumnCount(); i++) {
                    switch (c.getType(i)) {
                        case 0:
                            row[i] = null;
                            break;
                        case 1:
                            row[i] = Long.valueOf(c.getLong(i));
                            break;
                        case 2:
                            row[i] = Double.valueOf(c.getDouble(i));
                            break;
                        case 3:
                            row[i] = c.getString(i);
                            break;
                        case 4:
                            row[i] = c.getBlob(i);
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                }
                matrixCursor.addRow(row);
            }
            c.close();
            return matrixCursor;
        } catch (Throwable th) {
            c.close();
            throw th;
        }
    }

    public static int getColumnIndex(Cursor c, String name) {
        int index = c.getColumnIndex(name);
        if (index >= 0) {
            return index;
        }
        return c.getColumnIndex("`" + name + "`");
    }

    public static int getColumnIndexOrThrow(Cursor c, String name) {
        int index = c.getColumnIndex(name);
        if (index >= 0) {
            return index;
        }
        return c.getColumnIndexOrThrow("`" + name + "`");
    }

    private CursorUtil() {
    }
}
