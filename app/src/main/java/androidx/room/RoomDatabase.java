package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.Migration;
import androidx.room.util.SneakyThrow;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes11.dex */
public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;

    @Deprecated
    protected List<Callback> mCallbacks;

    @Deprecated
    protected volatile SupportSQLiteDatabase mDatabase;
    private SupportSQLiteOpenHelper mOpenHelper;
    private Executor mQueryExecutor;
    private Executor mTransactionExecutor;
    boolean mWriteAheadLoggingEnabled;
    private final ReentrantReadWriteLock mCloseLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> mSuspendingTransactionId = new ThreadLocal<>();
    private final Map<String, Object> mBackingFieldMap = new ConcurrentHashMap();
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();

    public abstract void clearAllTables();

    protected abstract InvalidationTracker createInvalidationTracker();

    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    Lock getCloseLock() {
        return this.mCloseLock.readLock();
    }

    ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.mSuspendingTransactionId;
    }

    Map<String, Object> getBackingFieldMap() {
        return this.mBackingFieldMap;
    }

    public void init(DatabaseConfiguration configuration) {
        this.mOpenHelper = createOpenHelper(configuration);
        if (this.mOpenHelper instanceof SQLiteCopyOpenHelper) {
            SQLiteCopyOpenHelper copyOpenHelper = (SQLiteCopyOpenHelper) this.mOpenHelper;
            copyOpenHelper.setDatabaseConfiguration(configuration);
        }
        int i = Build.VERSION.SDK_INT;
        boolean wal = configuration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
        this.mOpenHelper.setWriteAheadLoggingEnabled(wal);
        this.mCallbacks = configuration.callbacks;
        this.mQueryExecutor = configuration.queryExecutor;
        this.mTransactionExecutor = new TransactionExecutor(configuration.transactionExecutor);
        this.mAllowMainThreadQueries = configuration.allowMainThreadQueries;
        this.mWriteAheadLoggingEnabled = wal;
        if (configuration.multiInstanceInvalidation) {
            this.mInvalidationTracker.startMultiInstanceInvalidation(configuration.context, configuration.name);
        }
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        return this.mOpenHelper;
    }

    public boolean isOpen() {
        SupportSQLiteDatabase db = this.mDatabase;
        return db != null && db.isOpen();
    }

    public void close() {
        if (isOpen()) {
            Lock closeLock = this.mCloseLock.writeLock();
            try {
                closeLock.lock();
                this.mInvalidationTracker.stopMultiInstanceInvalidation();
                this.mOpenHelper.close();
            } finally {
                closeLock.unlock();
            }
        }
    }

    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.mSuspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public Cursor query(String query, Object[] args) {
        return this.mOpenHelper.getWritableDatabase().query(new SimpleSQLiteQuery(query, args));
    }

    public Cursor query(SupportSQLiteQuery query) {
        return query(query, (CancellationSignal) null);
    }

    public Cursor query(SupportSQLiteQuery query, CancellationSignal signal) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (signal != null) {
            int i = Build.VERSION.SDK_INT;
            return this.mOpenHelper.getWritableDatabase().query(query, signal);
        }
        return this.mOpenHelper.getWritableDatabase().query(query);
    }

    public SupportSQLiteStatement compileStatement(String sql) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return this.mOpenHelper.getWritableDatabase().compileStatement(sql);
    }

    @Deprecated
    public void beginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase database = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(database);
        database.beginTransaction();
    }

    @Deprecated
    public void endTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            this.mInvalidationTracker.refreshVersionsAsync();
        }
    }

    public Executor getQueryExecutor() {
        return this.mQueryExecutor;
    }

    public Executor getTransactionExecutor() {
        return this.mTransactionExecutor;
    }

    @Deprecated
    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(Runnable body) {
        beginTransaction();
        try {
            body.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(Callable<V> body) {
        beginTransaction();
        try {
            try {
                V result = body.call();
                setTransactionSuccessful();
                endTransaction();
                return result;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                SneakyThrow.reThrow(e2);
                endTransaction();
                return null;
            }
        } catch (Throwable e3) {
            endTransaction();
            throw e3;
        }
    }

    protected void internalInitInvalidationTracker(SupportSQLiteDatabase db) {
        this.mInvalidationTracker.internalInit(db);
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        JournalMode resolve(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            int i = Build.VERSION.SDK_INT;
            ActivityManager manager = (ActivityManager) context.getSystemService("activity");
            if (manager != null && !isLowRamDevice(manager)) {
                return WRITE_AHEAD_LOGGING;
            }
            return TRUNCATE;
        }

        private static boolean isLowRamDevice(ActivityManager activityManager) {
            int i = Build.VERSION.SDK_INT;
            return activityManager.isLowRamDevice();
        }
    }

    public static class Builder<T extends RoomDatabase> {
        private boolean mAllowDestructiveMigrationOnDowngrade;
        private boolean mAllowMainThreadQueries;
        private ArrayList<Callback> mCallbacks;
        private final Context mContext;
        private String mCopyFromAssetPath;
        private File mCopyFromFile;
        private final Class<T> mDatabaseClass;
        private SupportSQLiteOpenHelper.Factory mFactory;
        private Set<Integer> mMigrationStartAndEndVersions;
        private Set<Integer> mMigrationsNotRequiredFrom;
        private boolean mMultiInstanceInvalidation;
        private final String mName;
        private Executor mQueryExecutor;
        private Executor mTransactionExecutor;
        private JournalMode mJournalMode = JournalMode.AUTOMATIC;
        private boolean mRequireMigration = true;
        private final MigrationContainer mMigrationContainer = new MigrationContainer();

        Builder(Context context, Class<T> klass, String name) {
            this.mContext = context;
            this.mDatabaseClass = klass;
            this.mName = name;
        }

        public Builder<T> createFromAsset(String databaseFilePath) {
            this.mCopyFromAssetPath = databaseFilePath;
            return this;
        }

        public Builder<T> createFromFile(File databaseFile) {
            this.mCopyFromFile = databaseFile;
            return this;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        public Builder<T> addMigrations(Migration... migrations) {
            if (this.mMigrationStartAndEndVersions == null) {
                this.mMigrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrations) {
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            this.mMigrationContainer.addMigrations(migrations);
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            this.mQueryExecutor = executor;
            return this;
        }

        public Builder<T> setTransactionExecutor(Executor executor) {
            this.mTransactionExecutor = executor;
            return this;
        }

        public Builder<T> enableMultiInstanceInvalidation() {
            this.mMultiInstanceInvalidation = this.mName != null;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            this.mAllowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationOnDowngrade() {
            this.mRequireMigration = true;
            this.mAllowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(int... startVersions) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(startVersions.length);
            }
            for (int startVersion : startVersions) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(startVersion));
            }
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        public T build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.mDatabaseClass == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            if (this.mQueryExecutor == null && this.mTransactionExecutor == null) {
                Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
                this.mTransactionExecutor = iOThreadExecutor;
                this.mQueryExecutor = iOThreadExecutor;
            } else if (this.mQueryExecutor != null && this.mTransactionExecutor == null) {
                this.mTransactionExecutor = this.mQueryExecutor;
            } else if (this.mQueryExecutor == null && this.mTransactionExecutor != null) {
                this.mQueryExecutor = this.mTransactionExecutor;
            }
            if (this.mMigrationStartAndEndVersions != null && this.mMigrationsNotRequiredFrom != null) {
                for (Integer version : this.mMigrationStartAndEndVersions) {
                    if (this.mMigrationsNotRequiredFrom.contains(version)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + version);
                    }
                }
            }
            if (this.mFactory == null) {
                this.mFactory = new FrameworkSQLiteOpenHelperFactory();
            }
            if (this.mCopyFromAssetPath != null || this.mCopyFromFile != null) {
                if (this.mName == null) {
                    throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                }
                if (this.mCopyFromAssetPath == null || this.mCopyFromFile == null) {
                    this.mFactory = new SQLiteCopyOpenHelperFactory(this.mCopyFromAssetPath, this.mCopyFromFile, this.mFactory);
                } else {
                    throw new IllegalArgumentException("Both createFromAsset() and createFromFile() was called on this Builder but the database can only be created using one of the two configurations.");
                }
            }
            DatabaseConfiguration configuration = new DatabaseConfiguration(this.mContext, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(this.mContext), this.mQueryExecutor, this.mTransactionExecutor, this.mMultiInstanceInvalidation, this.mRequireMigration, this.mAllowDestructiveMigrationOnDowngrade, this.mMigrationsNotRequiredFrom, this.mCopyFromAssetPath, this.mCopyFromFile);
            T t = (T) Room.getGeneratedImplementation(this.mDatabaseClass, RoomDatabase.DB_IMPL_SUFFIX);
            t.init(configuration);
            return t;
        }
    }

    public static class MigrationContainer {
        private HashMap<Integer, TreeMap<Integer, Migration>> mMigrations = new HashMap<>();

        public void addMigrations(Migration... migrations) {
            for (Migration migration : migrations) {
                addMigration(migration);
            }
        }

        private void addMigration(Migration migration) {
            int start = migration.startVersion;
            int end = migration.endVersion;
            TreeMap<Integer, Migration> targetMap = this.mMigrations.get(Integer.valueOf(start));
            if (targetMap == null) {
                targetMap = new TreeMap<>();
                this.mMigrations.put(Integer.valueOf(start), targetMap);
            }
            Migration existing = targetMap.get(Integer.valueOf(end));
            if (existing != null) {
                Log.w("ROOM", "Overriding migration " + existing + " with " + migration);
            }
            targetMap.put(Integer.valueOf(end), migration);
        }

        public List<Migration> findMigrationPath(int start, int end) {
            if (start == end) {
                return Collections.emptyList();
            }
            boolean migrateUp = end > start;
            List<Migration> result = new ArrayList<>();
            return findUpMigrationPath(result, migrateUp, start, end);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x001a  */
        /* JADX WARN: Code duplicated, block: B:11:0x001f  */
        /* JADX WARN: Code duplicated, block: B:15:0x002e  */
        /* JADX WARN: Code duplicated, block: B:17:0x003c  */
        /* JADX WARN: Code duplicated, block: B:20:0x0041  */
        /* JADX WARN: Code duplicated, block: B:22:0x0043  */
        /* JADX WARN: Code duplicated, block: B:25:0x0048  */
        /* JADX WARN: Code duplicated, block: B:35:0x0017 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:40:0x004b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:45:? A[LOOP:1: B:13:0x0028->B:45:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:9:0x0018 A[DONT_INVERT] */
        private List<Migration> findUpMigrationPath(List<Migration> result, boolean upgrade, int start, int end) {
            TreeMap<Integer, Migration> targetNodes;
            Set<Integer> keySet;
            boolean found;
            Iterator<Integer> it;
            int targetVersion;
            boolean shouldAddToPath;
            do {
                if (upgrade) {
                    if (start >= end) {
                        return result;
                    }
                    targetNodes = this.mMigrations.get(Integer.valueOf(start));
                    if (targetNodes == null) {
                        return null;
                    }
                    if (upgrade) {
                        keySet = targetNodes.descendingKeySet();
                    } else {
                        keySet = targetNodes.keySet();
                    }
                    found = false;
                    it = keySet.iterator();
                    while (it.hasNext()) {
                        targetVersion = it.next().intValue();
                        shouldAddToPath = true;
                        if (upgrade) {
                            if (targetVersion <= end || targetVersion <= start) {
                                shouldAddToPath = false;
                            }
                        } else if (targetVersion >= end || targetVersion >= start) {
                            shouldAddToPath = false;
                        }
                        if (shouldAddToPath) {
                            result.add(targetNodes.get(Integer.valueOf(targetVersion)));
                            start = targetVersion;
                            found = true;
                            break;
                        }
                    }
                } else {
                    if (start <= end) {
                        return result;
                    }
                    targetNodes = this.mMigrations.get(Integer.valueOf(start));
                    if (targetNodes == null) {
                        return null;
                    }
                    if (upgrade) {
                        keySet = targetNodes.descendingKeySet();
                    } else {
                        keySet = targetNodes.keySet();
                    }
                    found = false;
                    it = keySet.iterator();
                    while (it.hasNext()) {
                        targetVersion = it.next().intValue();
                        shouldAddToPath = true;
                        if (upgrade) {
                            if (targetVersion <= end) {
                                shouldAddToPath = false;
                            } else {
                                shouldAddToPath = false;
                            }
                        } else if (targetVersion >= end) {
                            shouldAddToPath = false;
                        } else {
                            shouldAddToPath = false;
                        }
                        if (shouldAddToPath) {
                            result.add(targetNodes.get(Integer.valueOf(targetVersion)));
                            start = targetVersion;
                            found = true;
                            break;
                            break;
                        }
                    }
                }
            } while (found);
            return null;
        }
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase db) {
        }

        public void onOpen(SupportSQLiteDatabase db) {
        }

        public void onDestructiveMigration(SupportSQLiteDatabase db) {
        }
    }
}
