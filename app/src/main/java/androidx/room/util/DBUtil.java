package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class DBUtil {
    @Deprecated
    public static Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy) {
        return query(db, sqLiteQuery, maybeCopy, null);
    }

    public static Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy, CancellationSignal signal) {
        int rowsInWindow;
        Cursor cursor = db.query(sqLiteQuery, signal);
        if (maybeCopy && (cursor instanceof AbstractWindowedCursor)) {
            AbstractWindowedCursor windowedCursor = (AbstractWindowedCursor) cursor;
            int rowsInCursor = windowedCursor.getCount();
            if (windowedCursor.hasWindow()) {
                rowsInWindow = windowedCursor.getWindow().getNumRows();
            } else {
                rowsInWindow = rowsInCursor;
            }
            int i = Build.VERSION.SDK_INT;
            if (rowsInWindow < rowsInCursor) {
                return CursorUtil.copyAndClose(windowedCursor);
            }
        }
        return cursor;
    }

    public static void dropFtsSyncTriggers(SupportSQLiteDatabase db) {
        List<String> existingTriggers = new ArrayList<>();
        Cursor cursor = db.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (cursor.moveToNext()) {
            try {
                existingTriggers.add(cursor.getString(0));
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        cursor.close();
        for (String triggerName : existingTriggers) {
            if (triggerName.startsWith("room_fts_content_sync_")) {
                db.execSQL("DROP TRIGGER IF EXISTS " + triggerName);
            }
        }
    }

    public static int readVersion(File databaseFile) throws Throwable {
        FileChannel input = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            FileChannel input2 = new FileInputStream(databaseFile).getChannel();
            try {
                input2.tryLock(60L, 4L, true);
                input2.position(60L);
                int read = input2.read(buffer);
                if (read != 4) {
                    throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
                }
                buffer.rewind();
                int i = buffer.getInt();
                if (input2 != null) {
                    input2.close();
                }
                return i;
            } catch (Throwable th) {
                th = th;
                input = input2;
                if (input != null) {
                    input.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static CancellationSignal createCancellationSignal() {
        int i = Build.VERSION.SDK_INT;
        return new CancellationSignal();
    }

    private DBUtil() {
    }
}
