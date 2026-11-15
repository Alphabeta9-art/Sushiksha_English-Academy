package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DISABLE_FS_SYNC_JOB = 256;
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    public static final int SOLOADER_ENABLE_DIRECT_SOSOURCE = 64;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_EXPLICITLY_ENABLE_BACKUP_SOSOURCE = 128;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";

    @Nullable
    private static ApplicationSoSource sApplicationSoSource;

    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;
    private static int sFlags;

    @Nullable
    static SoFileLoader sSoFileLoader;
    public static String VERSION = "0.10.5";
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();

    @Nullable
    private static volatile SoSource[] sSoSources = null;
    private static final AtomicInteger sSoSourcesVersion = new AtomicInteger(0);
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());

    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;
    private static boolean isEnabled = true;
    private static final String[] DEFAULT_DENY_LIST = {System.mapLibraryName("breakpad")};
    private static int sAppType = 0;

    interface AppType {
        public static final int SYSTEM_APP = 2;
        public static final int THIRD_PARTY_APP = 1;
        public static final int UNSET = 0;
        public static final int UPDATED_SYSTEM_APP = 3;
    }

    static {
        boolean shouldSystrace = false;
        try {
            int i = Build.VERSION.SDK_INT;
            shouldSystrace = true;
        } catch (NoClassDefFoundError e) {
        } catch (UnsatisfiedLinkError e2) {
        }
        SYSTRACE_LIBRARY_LOADING = shouldSystrace;
    }

    public static void init(Context context, int flags) throws IOException {
        init(context, flags, null, DEFAULT_DENY_LIST);
    }

    public static void init(Context context, int flags, @Nullable SoFileLoader soFileLoader) throws IOException {
        init(context, flags, soFileLoader, DEFAULT_DENY_LIST);
    }

    public static void init(Context context, int flags, @Nullable SoFileLoader soFileLoader, String[] denyList) throws IOException {
        if (isInitialized()) {
            return;
        }
        StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskWrites();
        try {
            isEnabled = initEnableConfig(context);
            if (isEnabled) {
                sAppType = getAppType(context, flags);
                if ((flags & 128) == 0 && SysUtil.isSupportedDirectLoad(context, sAppType)) {
                    flags |= 72;
                }
                initSoLoader(soFileLoader);
                initSoSources(context, flags, denyList);
                Log.v(TAG, "Init SoLoader delegate");
                NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
            } else {
                initDummySoSource();
                Log.v(TAG, "Init System Loader delegate");
                NativeLoader.initIfUninitialized(new SystemDelegate());
            }
        } finally {
            StrictMode.setThreadPolicy(oldPolicy);
        }
    }

    public static void init(Context context, boolean nativeExopackage) {
        try {
            init(context, nativeExopackage ? 1 : 0, null, DEFAULT_DENY_LIST);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean initEnableConfig(Context context) {
        int i = Build.VERSION.SDK_INT;
        Bundle metaData = null;
        String packageName = null;
        try {
            packageName = context.getPackageName();
            metaData = context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
        } catch (Exception e) {
            Log.w(TAG, "Unexpected issue with package manager (" + packageName + ")", e);
        }
        return metaData == null || metaData.getBoolean("com.facebook.soloader.enabled", true);
    }

    private static void initSoSources(Context context, int flags, String[] denyList) throws IOException {
        if (sSoSources != null) {
            return;
        }
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources != null) {
                return;
            }
            sFlags = flags;
            ArrayList<SoSource> soSources = new ArrayList<>();
            AddSystemLibSoSource(soSources, denyList);
            if (context != null) {
                if ((flags & 1) != 0) {
                    addApplicationSoSource(context, soSources, getApplicationSoSourceFlags());
                    sBackupSoSources = null;
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "adding exo package source: lib-main");
                    }
                    soSources.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                } else {
                    if ((flags & 64) != 0) {
                        addDirectApkSoSource(context, soSources);
                    }
                    addApplicationSoSource(context, soSources, getApplicationSoSourceFlags());
                    AddBackupSoSource(context, soSources, 1);
                }
            }
            SoSource[] finalSoSources = (SoSource[]) soSources.toArray(new SoSource[soSources.size()]);
            int prepareFlags = makePrepareFlags();
            int i = finalSoSources.length;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Preparing SO source: " + finalSoSources[i2]);
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.beginTraceSection(TAG, "_", finalSoSources[i2].getClass().getSimpleName());
                }
                finalSoSources[i2].prepare(prepareFlags);
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.endSection();
                }
                i = i2;
            }
            sSoSources = finalSoSources;
            sSoSourcesVersion.getAndIncrement();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static void initDummySoSource() {
        if (sSoSources != null) {
            return;
        }
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources != null) {
                return;
            }
            sSoSources = new SoSource[0];
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int getApplicationSoSourceFlags() {
        switch (sAppType) {
            case 1:
                return 0;
            case 2:
            case 3:
                return 1;
            default:
                throw new RuntimeException("Unsupported app type, we should not reach here");
        }
    }

    private static void addDirectApkSoSource(Context context, ArrayList<SoSource> soSources) {
        int i = Build.VERSION.SDK_INT;
        if (context.getApplicationInfo().splitSourceDirs != null) {
            for (String splitApkDir : context.getApplicationInfo().splitSourceDirs) {
                DirectApkSoSource splitApkSource = new DirectApkSoSource(new File(splitApkDir));
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "validating/adding directApk source from splitApk: " + splitApkSource.toString());
                }
                if (splitApkSource.isValid()) {
                    soSources.add(0, splitApkSource);
                }
            }
        }
        DirectApkSoSource directApkSoSource = new DirectApkSoSource(context);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "validating/adding directApk source: " + directApkSoSource.toString());
        }
        if (directApkSoSource.isValid()) {
            soSources.add(0, directApkSoSource);
        }
    }

    private static void addApplicationSoSource(Context context, ArrayList<SoSource> soSources, int flags) {
        int i = Build.VERSION.SDK_INT;
        sApplicationSoSource = new ApplicationSoSource(context, flags);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding application source: " + sApplicationSoSource.toString());
        }
        soSources.add(0, sApplicationSoSource);
    }

    private static void AddBackupSoSource(Context context, ArrayList<SoSource> soSources, int apkSoSourceFlags) throws IOException {
        if ((sFlags & 8) != 0) {
            sBackupSoSources = null;
            File backupDir = UnpackingSoSource.getSoStorePath(context, SO_STORE_NAME_MAIN);
            try {
                SysUtil.dumbDeleteRecursive(backupDir);
                return;
            } catch (IOException e) {
                Log.w(TAG, "Failed to delete " + backupDir.getCanonicalPath(), e);
                return;
            }
        }
        File mainApkDir = new File(context.getApplicationInfo().sourceDir);
        ArrayList<UnpackingSoSource> backupSources = new ArrayList<>();
        ApkSoSource mainApkSource = new ApkSoSource(context, mainApkDir, SO_STORE_NAME_MAIN, apkSoSourceFlags);
        backupSources.add(mainApkSource);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding backup source from : " + mainApkSource.toString());
        }
        addBackupSoSourceFromSplitApk(context, apkSoSourceFlags, backupSources);
        sBackupSoSources = (UnpackingSoSource[]) backupSources.toArray(new UnpackingSoSource[backupSources.size()]);
        soSources.addAll(0, backupSources);
    }

    private static void addBackupSoSourceFromSplitApk(Context context, int apkSoSourceFlags, ArrayList<UnpackingSoSource> backupSources) {
        int i = Build.VERSION.SDK_INT;
        if (context.getApplicationInfo().splitSourceDirs != null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "adding backup sources from split apks");
            }
            int splitIndex = 0;
            String[] strArr = context.getApplicationInfo().splitSourceDirs;
            int length = strArr.length;
            int i2 = 0;
            while (i2 < length) {
                String splitApkDir = strArr[i2];
                int splitIndex2 = splitIndex + 1;
                ApkSoSource splitApkSource = new ApkSoSource(context, new File(splitApkDir), SO_STORE_NAME_SPLIT + splitIndex, apkSoSourceFlags);
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "adding backup source: " + splitApkSource.toString());
                }
                backupSources.add(splitApkSource);
                i2++;
                splitIndex = splitIndex2;
            }
        }
    }

    private static void AddSystemLibSoSource(ArrayList<SoSource> soSources, String[] denyList) {
        String systemLibPaths = SysUtil.is64Bit() ? "/system/lib64:/vendor/lib64" : "/system/lib:/vendor/lib";
        String LD_LIBRARY_PATH = System.getenv("LD_LIBRARY_PATH");
        if (LD_LIBRARY_PATH != null && !LD_LIBRARY_PATH.equals("")) {
            systemLibPaths = systemLibPaths + ":" + LD_LIBRARY_PATH;
        }
        Set<String> libPathSet = new HashSet<>(Arrays.asList(systemLibPaths.split(":")));
        for (String libPath : libPathSet) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "adding system library source: " + libPath);
            }
            File systemSoDirectory = new File(libPath);
            soSources.add(new DirectorySoSource(systemSoDirectory, 2, denyList));
        }
    }

    private static int makePrepareFlags() {
        int prepareFlags = 0;
        sSoSourcesLock.writeLock().lock();
        try {
            if ((sFlags & 2) != 0) {
                prepareFlags = 0 | 1;
            }
            if ((sFlags & 256) != 0) {
                prepareFlags |= 4;
            }
            return prepareFlags;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        if (soFileLoader == null) {
            if (sSoFileLoader != null) {
                return;
            }
        }
        if (soFileLoader != null) {
            sSoFileLoader = soFileLoader;
            return;
        }
        final Runtime runtime = Runtime.getRuntime();
        final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
        final boolean hasNativeLoadMethod = nativeLoadRuntimeMethod != null;
        final String localLdLibraryPath = hasNativeLoadMethod ? SysUtil.Api14Utils.getClassLoaderLdLoadLibrary() : null;
        final String localLdLibraryPathNoZips = makeNonZipPath(localLdLibraryPath);
        sSoFileLoader = new SoFileLoader() { // from class: com.facebook.soloader.SoLoader.1
            @Override // com.facebook.soloader.SoFileLoader
            public void loadBytes(String pathName, ElfByteChannel bytes, int loadFlags) {
                throw new UnsupportedOperationException();
            }

            @Override // com.facebook.soloader.SoFileLoader
            public void load(String pathToSoFile, int loadFlags) throws Exception {
                String error;
                if (!hasNativeLoadMethod) {
                    System.load(pathToSoFile);
                    return;
                }
                boolean inZip = (loadFlags & 4) == 4;
                String path = inZip ? localLdLibraryPath : localLdLibraryPathNoZips;
                try {
                    try {
                        synchronized (runtime) {
                            try {
                                error = (String) nativeLoadRuntimeMethod.invoke(runtime, pathToSoFile, SoLoader.class.getClassLoader(), path);
                                if (error != null) {
                                    throw new UnsatisfiedLinkError(error);
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (error != null) {
                            Log.e(SoLoader.TAG, "Error when loading lib: " + error + " lib hash: " + getLibHash(pathToSoFile) + " search path is " + path);
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException("Error: Cannot load " + pathToSoFile, e);
                    }
                } catch (Throwable e2) {
                    if (0 != 0) {
                        Log.e(SoLoader.TAG, "Error when loading lib: " + ((String) null) + " lib hash: " + getLibHash(pathToSoFile) + " search path is " + path);
                    }
                    throw e2;
                }
            }

            private String getLibHash(String libPath) {
                try {
                    File libFile = new File(libPath);
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    InputStream libInStream = new FileInputStream(libFile);
                    try {
                        byte[] buffer = new byte[4096];
                        while (true) {
                            int bytesRead = libInStream.read(buffer);
                            if (bytesRead > 0) {
                                digest.update(buffer, 0, bytesRead);
                            } else {
                                String digestStr = String.format("%32x", new BigInteger(1, digest.digest()));
                                libInStream.close();
                                return digestStr;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            libInStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (IOException | SecurityException | NoSuchAlgorithmException e) {
                    String digestStr2 = e.toString();
                    return digestStr2;
                }
            }
        };
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method method = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException | SecurityException e) {
            Log.w(TAG, "Cannot get nativeLoad method", e);
            return null;
        }
    }

    private static int getAppType(Context context, int flags) {
        int type;
        if (sAppType != 0) {
            return sAppType;
        }
        if ((flags & 32) != 0 || context == null) {
            return 1;
        }
        ApplicationInfo appInfo = context.getApplicationInfo();
        if ((appInfo.flags & 1) == 0) {
            type = 1;
        } else {
            int type2 = appInfo.flags;
            if ((type2 & 128) != 0) {
                type = 3;
            } else {
                type = 2;
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "ApplicationInfo.flags is: " + appInfo.flags + " appType is: " + type);
        }
        return type;
    }

    public static void setInTestMode() {
        TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources(null);
    }

    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void setSoSources(SoSource[] sources) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = sources;
                SoLoader.sSoSourcesVersion.getAndIncrement();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }

        static void setSoFileLoader(SoFileLoader loader) {
            SoLoader.sSoFileLoader = loader;
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
            }
            setSoSources(null);
        }
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper wrapper) {
        sSystemLoadLibraryWrapper = wrapper;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable cause, String machine) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + machine);
            initCause(cause);
        }
    }

    @Nullable
    public static String getLibraryPath(String libName) throws IOException {
        String libPath = null;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                for (int i = 0; libPath == null && i < sSoSources.length; i++) {
                    SoSource currentSource = sSoSources[i];
                    libPath = currentSource.getLibraryPath(libName);
                }
            }
            return libPath;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String libName) throws IOException {
        String[] deps = null;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                for (int i = 0; deps == null && i < sSoSources.length; i++) {
                    SoSource currentSource = sSoSources[i];
                    deps = currentSource.getLibraryDependencies(libName);
                }
            }
            return deps;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static File getSoFile(String shortName) {
        String mergedLibName = MergedSoMapping.mapLibName(shortName);
        String soName = mergedLibName != null ? mergedLibName : shortName;
        String mappedName = System.mapLibraryName(soName);
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                for (int i = 0; i < sSoSources.length; i++) {
                    SoSource currentSource = sSoSources[i];
                    try {
                        File soFile = currentSource.getSoFileByName(mappedName);
                        if (soFile != null) {
                            sSoSourcesLock.readLock().unlock();
                            return soFile;
                        }
                    } catch (IOException e) {
                    }
                }
            }
            sSoSourcesLock.readLock().unlock();
            return null;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static boolean loadLibrary(String shortName) {
        return isEnabled ? loadLibrary(shortName, 0) : NativeLoader.loadLibrary(shortName);
    }

    public static boolean loadLibrary(String shortName, int loadFlags) throws UnsatisfiedLinkError {
        Boolean needsLoad = loadLibraryOnNonAndroid(shortName);
        if (needsLoad != null) {
            return needsLoad.booleanValue();
        }
        if (!isEnabled) {
            return NativeLoader.loadLibrary(shortName);
        }
        if ((sAppType == 2 || sAppType == 3) && sSystemLoadLibraryWrapper != null) {
            sSystemLoadLibraryWrapper.loadLibrary(shortName);
            return true;
        }
        String mergedLibName = MergedSoMapping.mapLibName(shortName);
        String soName = mergedLibName != null ? mergedLibName : shortName;
        return loadLibraryBySoName(System.mapLibraryName(soName), shortName, mergedLibName, loadFlags, null);
    }

    @Nullable
    private static Boolean loadLibraryOnNonAndroid(String shortName) {
        Boolean boolValueOf;
        if (sSoSources == null) {
            sSoSourcesLock.readLock().lock();
            try {
                if (sSoSources == null) {
                    if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                        assertInitialized();
                    } else {
                        synchronized (SoLoader.class) {
                            boolean needsLoad = !sLoadedLibraries.contains(shortName);
                            if (needsLoad) {
                                if (sSystemLoadLibraryWrapper != null) {
                                    sSystemLoadLibraryWrapper.loadLibrary(shortName);
                                } else {
                                    System.loadLibrary(shortName);
                                }
                            }
                            boolValueOf = Boolean.valueOf(needsLoad);
                        }
                        sSoSourcesLock.readLock().unlock();
                        return boolValueOf;
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return null;
            } catch (Throwable th) {
                sSoSourcesLock.readLock().unlock();
                throw th;
            }
        }
        return null;
    }

    static void loadLibraryBySoName(String soName, int loadFlags, StrictMode.ThreadPolicy oldPolicy) {
        loadLibraryBySoNameImpl(soName, null, null, loadFlags, oldPolicy);
    }

    private static boolean loadLibraryBySoName(String soName, @Nullable String shortName, @Nullable String mergedLibName, int loadFlags, @Nullable StrictMode.ThreadPolicy oldPolicy) throws IOException {
        boolean retry;
        boolean ret = false;
        do {
            retry = false;
            try {
                boolean ret2 = loadLibraryBySoNameImpl(soName, shortName, mergedLibName, loadFlags, oldPolicy);
                ret = ret2;
            } catch (UnsatisfiedLinkError e) {
                int currentVersion = sSoSourcesVersion.get();
                sSoSourcesLock.writeLock().lock();
                try {
                    try {
                        if (sApplicationSoSource != null && sApplicationSoSource.checkAndMaybeUpdate()) {
                            Log.w(TAG, "sApplicationSoSource updated during load: " + soName + ", attempting load again.");
                            sSoSourcesVersion.getAndIncrement();
                            retry = true;
                        }
                        sSoSourcesLock.writeLock().unlock();
                        if (sSoSourcesVersion.get() == currentVersion) {
                            throw e;
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (Throwable ex2) {
                    sSoSourcesLock.writeLock().unlock();
                    throw ex2;
                }
            }
        } while (retry);
        return ret;
    }

    /* JADX WARN: Code duplicated, block: B:65:0x00de A[Catch: all -> 0x017f, PHI: r0
  0x00de: PHI (r0v3 'loaded' boolean) = (r0v2 'loaded' boolean), (r0v4 'loaded' boolean), (r0v4 'loaded' boolean) binds: [B:23:0x0046, B:35:0x0063, B:47:0x00b2] A[DONT_GENERATE, DONT_INLINE], TryCatch #7 {, blocks: (B:25:0x004a, B:30:0x0056, B:36:0x0065, B:38:0x006d, B:39:0x0085, B:40:0x0089, B:42:0x0091, B:44:0x00ab, B:49:0x00b4, B:53:0x00b8, B:55:0x00be, B:57:0x00c7, B:58:0x00d7, B:60:0x00d9, B:62:0x00db, B:65:0x00de, B:67:0x00e3, B:69:0x00e9, B:74:0x00f6, B:76:0x00fa, B:81:0x0133, B:83:0x0137, B:89:0x0166, B:91:0x016a, B:93:0x016e, B:94:0x016f, B:77:0x0101, B:79:0x0109, B:80:0x012b, B:86:0x013c, B:87:0x0164, B:45:0x00ac, B:46:0x00b1, B:26:0x004b, B:29:0x0055, B:34:0x0062), top: B:117:0x004a, outer: #0, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x00e3 A[Catch: all -> 0x017f, TryCatch #7 {, blocks: (B:25:0x004a, B:30:0x0056, B:36:0x0065, B:38:0x006d, B:39:0x0085, B:40:0x0089, B:42:0x0091, B:44:0x00ab, B:49:0x00b4, B:53:0x00b8, B:55:0x00be, B:57:0x00c7, B:58:0x00d7, B:60:0x00d9, B:62:0x00db, B:65:0x00de, B:67:0x00e3, B:69:0x00e9, B:74:0x00f6, B:76:0x00fa, B:81:0x0133, B:83:0x0137, B:89:0x0166, B:91:0x016a, B:93:0x016e, B:94:0x016f, B:77:0x0101, B:79:0x0109, B:80:0x012b, B:86:0x013c, B:87:0x0164, B:45:0x00ac, B:46:0x00b1, B:26:0x004b, B:29:0x0055, B:34:0x0062), top: B:117:0x004a, outer: #0, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x00e9 A[Catch: all -> 0x017f, TryCatch #7 {, blocks: (B:25:0x004a, B:30:0x0056, B:36:0x0065, B:38:0x006d, B:39:0x0085, B:40:0x0089, B:42:0x0091, B:44:0x00ab, B:49:0x00b4, B:53:0x00b8, B:55:0x00be, B:57:0x00c7, B:58:0x00d7, B:60:0x00d9, B:62:0x00db, B:65:0x00de, B:67:0x00e3, B:69:0x00e9, B:74:0x00f6, B:76:0x00fa, B:81:0x0133, B:83:0x0137, B:89:0x0166, B:91:0x016a, B:93:0x016e, B:94:0x016f, B:77:0x0101, B:79:0x0109, B:80:0x012b, B:86:0x013c, B:87:0x0164, B:45:0x00ac, B:46:0x00b1, B:26:0x004b, B:29:0x0055, B:34:0x0062), top: B:117:0x004a, outer: #0, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x00f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:76:0x00fa A[Catch: all -> 0x017f, TRY_LEAVE, TryCatch #7 {, blocks: (B:25:0x004a, B:30:0x0056, B:36:0x0065, B:38:0x006d, B:39:0x0085, B:40:0x0089, B:42:0x0091, B:44:0x00ab, B:49:0x00b4, B:53:0x00b8, B:55:0x00be, B:57:0x00c7, B:58:0x00d7, B:60:0x00d9, B:62:0x00db, B:65:0x00de, B:67:0x00e3, B:69:0x00e9, B:74:0x00f6, B:76:0x00fa, B:81:0x0133, B:83:0x0137, B:89:0x0166, B:91:0x016a, B:93:0x016e, B:94:0x016f, B:77:0x0101, B:79:0x0109, B:80:0x012b, B:86:0x013c, B:87:0x0164, B:45:0x00ac, B:46:0x00b1, B:26:0x004b, B:29:0x0055, B:34:0x0062), top: B:117:0x004a, outer: #0, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0109 A[Catch: UnsatisfiedLinkError -> 0x013b, all -> 0x0165, TryCatch #6 {UnsatisfiedLinkError -> 0x013b, blocks: (B:77:0x0101, B:79:0x0109, B:80:0x012b), top: B:109:0x0101, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0137 A[Catch: all -> 0x017f, TRY_LEAVE, TryCatch #7 {, blocks: (B:25:0x004a, B:30:0x0056, B:36:0x0065, B:38:0x006d, B:39:0x0085, B:40:0x0089, B:42:0x0091, B:44:0x00ab, B:49:0x00b4, B:53:0x00b8, B:55:0x00be, B:57:0x00c7, B:58:0x00d7, B:60:0x00d9, B:62:0x00db, B:65:0x00de, B:67:0x00e3, B:69:0x00e9, B:74:0x00f6, B:76:0x00fa, B:81:0x0133, B:83:0x0137, B:89:0x0166, B:91:0x016a, B:93:0x016e, B:94:0x016f, B:77:0x0101, B:79:0x0109, B:80:0x012b, B:86:0x013c, B:87:0x0164, B:45:0x00ac, B:46:0x00b1, B:26:0x004b, B:29:0x0055, B:34:0x0062), top: B:117:0x004a, outer: #0, inners: #1, #2, #3, #4 }] */
    private static boolean loadLibraryBySoNameImpl(String soName, @Nullable String shortName, @Nullable String mergedLibName, int loadFlags, @Nullable StrictMode.ThreadPolicy oldPolicy) {
        Object loadingLibLock;
        boolean isAlreadyMerged = false;
        if (!TextUtils.isEmpty(shortName) && sLoadedAndMergedLibraries.contains(shortName)) {
            return false;
        }
        boolean loaded = false;
        synchronized (SoLoader.class) {
            if (sLoadedLibraries.contains(soName)) {
                if (mergedLibName == null) {
                    return false;
                }
                loaded = true;
            }
            if (sLoadingLibraries.containsKey(soName)) {
                loadingLibLock = sLoadingLibraries.get(soName);
            } else {
                loadingLibLock = new Object();
                sLoadingLibraries.put(soName, loadingLibLock);
            }
            sSoSourcesLock.readLock().lock();
            try {
                synchronized (loadingLibLock) {
                    if (!loaded) {
                        synchronized (SoLoader.class) {
                            try {
                                if (sLoadedLibraries.contains(soName)) {
                                    if (mergedLibName != null) {
                                        loaded = true;
                                    } else {
                                        sSoSourcesLock.readLock().unlock();
                                        return false;
                                    }
                                }
                                if (!loaded) {
                                    try {
                                        if (Log.isLoggable(TAG, 3)) {
                                            Log.d(TAG, "About to load: " + soName);
                                        }
                                        doLoadLibraryBySoName(soName, loadFlags, oldPolicy);
                                        if (Log.isLoggable(TAG, 3)) {
                                            Log.d(TAG, "Loaded: " + soName);
                                        }
                                        synchronized (SoLoader.class) {
                                            try {
                                                sLoadedLibraries.add(soName);
                                            } catch (Throwable th) {
                                                throw th;
                                            }
                                        }
                                        if ((loadFlags & 16) == 0) {
                                            if (!TextUtils.isEmpty(shortName) && sLoadedAndMergedLibraries.contains(shortName)) {
                                                isAlreadyMerged = true;
                                            }
                                            if (mergedLibName != null && !isAlreadyMerged) {
                                                if (SYSTRACE_LIBRARY_LOADING) {
                                                    Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", shortName, "]");
                                                }
                                                try {
                                                    try {
                                                        if (Log.isLoggable(TAG, 3)) {
                                                            Log.d(TAG, "About to merge: " + shortName + " / " + soName);
                                                        }
                                                        MergedSoMapping.invokeJniOnload(shortName);
                                                        sLoadedAndMergedLibraries.add(shortName);
                                                        if (SYSTRACE_LIBRARY_LOADING) {
                                                            Api18TraceUtils.endSection();
                                                        }
                                                    } catch (UnsatisfiedLinkError e) {
                                                        throw new RuntimeException("Failed to call JNI_OnLoad from '" + shortName + "', which has been merged into '" + soName + "'.  See comment for details.", e);
                                                    }
                                                } catch (Throwable e2) {
                                                    if (SYSTRACE_LIBRARY_LOADING) {
                                                        Api18TraceUtils.endSection();
                                                    }
                                                    throw e2;
                                                }
                                            }
                                        }
                                        sSoSourcesLock.readLock().unlock();
                                        return !loaded;
                                    } catch (UnsatisfiedLinkError ex) {
                                        String message = ex.getMessage();
                                        if (message == null || !message.contains("unexpected e_machine:")) {
                                            throw ex;
                                        }
                                        String machine_msg = message.substring(message.lastIndexOf("unexpected e_machine:"));
                                        throw new WrongAbiError(ex, machine_msg);
                                    }
                                }
                                if ((loadFlags & 16) == 0) {
                                    if (!TextUtils.isEmpty(shortName)) {
                                        isAlreadyMerged = true;
                                    }
                                    if (mergedLibName != null) {
                                        if (SYSTRACE_LIBRARY_LOADING) {
                                            Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", shortName, "]");
                                        }
                                        if (Log.isLoggable(TAG, 3)) {
                                            Log.d(TAG, "About to merge: " + shortName + " / " + soName);
                                        }
                                        MergedSoMapping.invokeJniOnload(shortName);
                                        sLoadedAndMergedLibraries.add(shortName);
                                        if (SYSTRACE_LIBRARY_LOADING) {
                                            Api18TraceUtils.endSection();
                                        }
                                    }
                                }
                                sSoSourcesLock.readLock().unlock();
                                return !loaded;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                    if ((loadFlags & 16) == 0) {
                        if (!TextUtils.isEmpty(shortName)) {
                            isAlreadyMerged = true;
                        }
                        if (mergedLibName != null) {
                            if (SYSTRACE_LIBRARY_LOADING) {
                                Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", shortName, "]");
                            }
                            if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "About to merge: " + shortName + " / " + soName);
                            }
                            MergedSoMapping.invokeJniOnload(shortName);
                            sLoadedAndMergedLibraries.add(shortName);
                            if (SYSTRACE_LIBRARY_LOADING) {
                                Api18TraceUtils.endSection();
                            }
                        }
                    }
                    sSoSourcesLock.readLock().unlock();
                    return !loaded;
                    throw th;
                }
            } catch (Throwable th3) {
                sSoSourcesLock.readLock().unlock();
                throw th3;
            }
        }
    }

    public static File unpackLibraryAndDependencies(String shortName) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(shortName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void doLoadLibraryBySoName(String soName, int loadFlags, @Nullable StrictMode.ThreadPolicy oldPolicy) throws UnsatisfiedLinkError {
        StrictMode.ThreadPolicy oldPolicy2;
        boolean restoreOldPolicy;
        UnsatisfiedLinkError err;
        int result = 0;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                Log.e(TAG, "Could not load: " + soName + " because no SO source exists");
                throw new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
            }
            sSoSourcesLock.readLock().unlock();
            if (oldPolicy != null) {
                oldPolicy2 = oldPolicy;
                restoreOldPolicy = false;
            } else {
                oldPolicy2 = StrictMode.allowThreadDiskReads();
                restoreOldPolicy = true;
            }
            boolean restoreOldPolicy2 = SYSTRACE_LIBRARY_LOADING;
            if (restoreOldPolicy2) {
                Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", soName, "]");
            }
            Throwable error = null;
            int i = 3;
            try {
                sSoSourcesLock.readLock().lock();
                int i2 = 0;
                while (true) {
                    if (result == 0) {
                        try {
                            if (i2 < sSoSources.length) {
                                SoSource currentSource = sSoSources[i2];
                                result = currentSource.loadLibrary(soName, loadFlags, oldPolicy2);
                                if (result != i || sBackupSoSources == null) {
                                    i2++;
                                    i = 3;
                                } else {
                                    if (Log.isLoggable(TAG, i)) {
                                        Log.d(TAG, "Trying backup SoSource for " + soName);
                                    }
                                    UnpackingSoSource[] unpackingSoSourceArr = sBackupSoSources;
                                    int length = unpackingSoSourceArr.length;
                                    int i3 = 0;
                                    while (i3 < length) {
                                        UnpackingSoSource backupSoSource = unpackingSoSourceArr[i3];
                                        backupSoSource.prepare(soName);
                                        int resultFromBackup = backupSoSource.loadLibrary(soName, loadFlags, oldPolicy2);
                                        int i4 = i2;
                                        if (resultFromBackup != 1) {
                                            i3++;
                                            loadFlags = loadFlags;
                                            i2 = i4;
                                        } else {
                                            result = resultFromBackup;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        } catch (Throwable th) {
                            sSoSourcesLock.readLock().unlock();
                            throw th;
                        }
                    }
                    break;
                }
                sSoSourcesLock.readLock().unlock();
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.endSection();
                }
                if (restoreOldPolicy) {
                    StrictMode.setThreadPolicy(oldPolicy2);
                }
                if (result == 0 || result == 3) {
                    StringBuilder sb = new StringBuilder().append("couldn't find DSO to load: ").append(soName);
                    if (0 != 0) {
                        String cause = error.getMessage();
                        if (cause == null) {
                            cause = error.toString();
                        }
                        sb.append(" caused by: ").append(cause);
                        error.printStackTrace();
                    } else {
                        sSoSourcesLock.readLock().lock();
                        for (int i5 = 0; i5 < sSoSources.length; i5++) {
                            sb.append("\n\tSoSource ").append(i5).append(": ").append(sSoSources[i5].toString());
                        }
                        if (sApplicationSoSource != null) {
                            Context updatedContext = sApplicationSoSource.getUpdatedContext();
                            File updatedNativeLibDir = ApplicationSoSource.getNativeLibDirFromContext(updatedContext);
                            sb.append("\n\tNative lib dir: ").append(updatedNativeLibDir.getAbsolutePath()).append("\n");
                        }
                        sSoSourcesLock.readLock().unlock();
                    }
                    sb.append(" result: ").append(result);
                    String message = sb.toString();
                    Log.e(TAG, message);
                    err = new UnsatisfiedLinkError(message);
                    if (0 == 0) {
                        throw err;
                    }
                    err.initCause(error);
                    throw err;
                }
            } catch (Throwable t) {
                error = t;
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.endSection();
                }
                if (restoreOldPolicy) {
                    StrictMode.setThreadPolicy(oldPolicy2);
                }
                if (result == 0 || result == 3) {
                    StringBuilder sb2 = new StringBuilder().append("couldn't find DSO to load: ").append(soName);
                    String cause2 = error.getMessage();
                    if (cause2 == null) {
                        cause2 = error.toString();
                    }
                    sb2.append(" caused by: ").append(cause2);
                    error.printStackTrace();
                    sb2.append(" result: ").append(result);
                    String message2 = sb2.toString();
                    Log.e(TAG, message2);
                    err = new UnsatisfiedLinkError(message2);
                }
            }
        } catch (Throwable th2) {
            sSoSourcesLock.readLock().unlock();
            throw th2;
        }
    }

    @Nullable
    public static String makeNonZipPath(String localLdLibraryPath) {
        if (localLdLibraryPath == null) {
            return null;
        }
        String[] paths = localLdLibraryPath.split(":");
        ArrayList<String> pathsWithoutZip = new ArrayList<>(paths.length);
        for (String path : paths) {
            if (!path.contains("!")) {
                pathsWithoutZip.add(path);
            }
        }
        return TextUtils.join(":", pathsWithoutZip);
    }

    static File unpackLibraryBySoName(String soName) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource soSource : sSoSources) {
                File unpacked = soSource.unpackLibrary(soName);
                if (unpacked != null) {
                    return unpacked;
                }
            }
            throw new FileNotFoundException(soName);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        if (sSoSources != null) {
            return true;
        }
        sSoSourcesLock.readLock().lock();
        try {
            return sSoSources != null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion.get();
    }

    public static void prependSoSource(SoSource extraSoSource) throws IOException {
        sSoSourcesLock.writeLock().lock();
        try {
            assertInitialized();
            extraSoSource.prepare(makePrepareFlags());
            SoSource[] newSoSources = new SoSource[sSoSources.length + 1];
            newSoSources[0] = extraSoSource;
            System.arraycopy(sSoSources, 0, newSoSources, 1, sSoSources.length);
            sSoSources = newSoSources;
            sSoSourcesVersion.getAndIncrement();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Prepended to SO sources: " + extraSoSource);
            }
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            ArrayList<String> pathElements = new ArrayList<>();
            SoSource[] soSources = sSoSources;
            if (soSources != null) {
                for (SoSource soSource : soSources) {
                    soSource.addToLdLibraryPath(pathElements);
                }
            }
            String joinedPaths = TextUtils.join(":", pathElements);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "makeLdLibraryPath final path: " + joinedPaths);
            }
            return joinedPaths;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean areSoSourcesAbisSupported() {
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                return false;
            }
            String[] supportedAbis = SysUtil.getSupportedAbis();
            for (SoSource soSource : sSoSources) {
                String[] soSourceAbis = soSource.getSoSourceAbis();
                for (String soSourceAbi : soSourceAbis) {
                    boolean soSourceSupported = false;
                    for (int k = 0; k < supportedAbis.length && !soSourceSupported; k++) {
                        soSourceSupported = soSourceAbi.equals(supportedAbis[k]);
                    }
                    if (!soSourceSupported) {
                        Log.e(TAG, "abi not supported: " + soSourceAbi);
                        return false;
                    }
                }
            }
            return true;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean useDepsFile(Context context, boolean async, boolean extractToDisk) {
        return NativeDeps.useDepsFile(context, async, extractToDisk);
    }

    public static int getLoadedLibrariesCount() {
        return sLoadedLibraries.size();
    }
}
