package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.work.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class WorkDatabasePathHelper {
    private static final String WORK_DATABASE_NAME = "androidx.work.workdb";
    private static final String TAG = Logger.tagWithPrefix("WrkDbPathHelper");
    private static final String[] DATABASE_EXTRA_FILES = {"-journal", "-shm", "-wal"};

    private WorkDatabasePathHelper() {
    }

    public static String getWorkDatabaseName() {
        return WORK_DATABASE_NAME;
    }

    public static void migrateDatabase(Context context) {
        String message;
        File defaultDatabasePath = getDefaultDatabasePath(context);
        int i = Build.VERSION.SDK_INT;
        if (defaultDatabasePath.exists()) {
            Logger.get().debug(TAG, "Migrating WorkDatabase to the no-backup directory", new Throwable[0]);
            Map<File, File> paths = migrationPaths(context);
            for (File source : paths.keySet()) {
                File destination = paths.get(source);
                if (source.exists() && destination != null) {
                    if (destination.exists()) {
                        String message2 = String.format("Over-writing contents of %s", destination);
                        Logger.get().warning(TAG, message2, new Throwable[0]);
                    }
                    boolean renamed = source.renameTo(destination);
                    if (renamed) {
                        message = String.format("Migrated %s to %s", source, destination);
                    } else {
                        message = String.format("Renaming %s to %s failed", source, destination);
                    }
                    Logger.get().debug(TAG, message, new Throwable[0]);
                }
            }
        }
    }

    public static Map<File, File> migrationPaths(Context context) {
        Map<File, File> paths = new HashMap<>();
        int i = Build.VERSION.SDK_INT;
        File databasePath = getDefaultDatabasePath(context);
        File migratedPath = getDatabasePath(context);
        paths.put(databasePath, migratedPath);
        for (String extra : DATABASE_EXTRA_FILES) {
            File source = new File(databasePath.getPath() + extra);
            File destination = new File(migratedPath.getPath() + extra);
            paths.put(source, destination);
        }
        return paths;
    }

    public static File getDefaultDatabasePath(Context context) {
        return context.getDatabasePath(WORK_DATABASE_NAME);
    }

    public static File getDatabasePath(Context context) {
        int i = Build.VERSION.SDK_INT;
        return getNoBackupPath(context, WORK_DATABASE_NAME);
    }

    private static File getNoBackupPath(Context context, String filePath) {
        return new File(context.getNoBackupFilesDir(), filePath);
    }
}
