package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.DataOutput;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public final class SysUtil {
    private static final long APK_DEP_BLOCK_METADATA_LENGTH = 20;
    private static final byte APK_SIGNATURE_VERSION = 1;
    private static final String TAG = "SysUtil";

    public static int findAbiScore(String[] supportedAbis, String abi) {
        for (int i = 0; i < supportedAbis.length; i++) {
            if (supportedAbis[i] != null && abi.equals(supportedAbis[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        File folder = file.getParentFile();
        if (folder != null && !folder.canWrite() && !folder.setWritable(true)) {
            Log.e(TAG, "Enable write permission failed: " + folder);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        int i = Build.VERSION.SDK_INT;
        return MarshmallowSysdeps.getSupportedAbis();
    }

    public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
        int i = Build.VERSION.SDK_INT;
        LollipopSysdeps.fallocateIfSupported(fd, length);
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList == null) {
                return;
            }
            for (File entry : fileList) {
                dumbDeleteRecursive(entry);
            }
        }
        deleteOrThrow(file);
    }

    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            TreeSet<String> allowedAbis = new TreeSet<>();
            try {
                if (is64Bit()) {
                    allowedAbis.add(MinElf.ISA.AARCH64.toString());
                    allowedAbis.add(MinElf.ISA.X86_64.toString());
                } else {
                    allowedAbis.add(MinElf.ISA.ARM.toString());
                    allowedAbis.add(MinElf.ISA.X86.toString());
                }
                ArrayList<String> compatibleSupportedAbis = new ArrayList<>();
                for (String abi : supportedAbis) {
                    if (allowedAbis.contains(abi)) {
                        compatibleSupportedAbis.add(abi);
                    }
                }
                String[] finalAbis = new String[compatibleSupportedAbis.size()];
                return (String[]) compatibleSupportedAbis.toArray(finalAbis);
            } catch (ErrnoException e) {
                Log.e(SysUtil.TAG, String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(supportedAbis), Integer.valueOf(e.errno), e.getMessage()));
                return Build.SUPPORTED_ABIS;
            }
        }

        public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
            try {
                Os.posix_fallocate(fd, 0L, length);
            } catch (ErrnoException ex) {
                if (ex.errno != OsConstants.EOPNOTSUPP && ex.errno != OsConstants.ENOSYS && ex.errno != OsConstants.EINVAL) {
                    throw new IOException(ex.toString(), ex);
                }
            }
        }

        public static boolean is64Bit() throws ErrnoException {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    private static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            TreeSet<String> allowedAbis = new TreeSet<>();
            if (is64Bit()) {
                allowedAbis.add(MinElf.ISA.AARCH64.toString());
                allowedAbis.add(MinElf.ISA.X86_64.toString());
            } else {
                allowedAbis.add(MinElf.ISA.ARM.toString());
                allowedAbis.add(MinElf.ISA.X86.toString());
            }
            ArrayList<String> compatibleSupportedAbis = new ArrayList<>();
            for (String abi : supportedAbis) {
                if (allowedAbis.contains(abi)) {
                    compatibleSupportedAbis.add(abi);
                }
            }
            String[] finalAbis = new String[compatibleSupportedAbis.size()];
            return (String[]) compatibleSupportedAbis.toArray(finalAbis);
        }

        public static boolean is64Bit() {
            return Process.is64Bit();
        }

        public static boolean isSupportedDirectLoad(Context context, int appType) throws IOException {
            if (appType == 2) {
                return isApkUncompressedDso(context);
            }
            return isDisabledExtractNativeLibs(context);
        }

        public static boolean isDisabledExtractNativeLibs(Context context) {
            return context != null && (context.getApplicationInfo().flags & 268435456) == 0;
        }

        private static boolean isApkUncompressedDso(Context context) throws IOException {
            File apkFile = new File(context.getApplicationInfo().sourceDir);
            ZipFile mZipFile = new ZipFile(apkFile);
            try {
                Enumeration<? extends ZipEntry> entries = mZipFile.entries();
                while (true) {
                    if (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        if (entry != null && entry.getName().endsWith(".so") && entry.getName().contains("/lib")) {
                            boolean z = entry.getMethod() == 0;
                            mZipFile.close();
                            return z;
                        }
                    } else {
                        mZipFile.close();
                        return false;
                    }
                }
            } catch (Throwable th) {
                try {
                    mZipFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static void mkdirOrThrow(File dir) throws IOException {
        if (!dir.mkdirs() && !dir.isDirectory()) {
            throw new IOException("cannot mkdir: " + dir);
        }
    }

    static int copyBytes(DataOutput os, InputStream is, int byteLimit, byte[] buffer) throws IOException {
        int bytesCopied = 0;
        while (bytesCopied < byteLimit) {
            int nrRead = is.read(buffer, 0, Math.min(buffer.length, byteLimit - bytesCopied));
            if (nrRead == -1) {
                break;
            }
            os.write(buffer, 0, nrRead);
            bytesCopied += nrRead;
        }
        return bytesCopied;
    }

    public static void fsyncRecursive(File fileName) throws IOException {
        if (fileName.isDirectory()) {
            File[] files = fileName.listFiles();
            if (files == null) {
                throw new IOException("cannot list directory " + fileName);
            }
            for (File file : files) {
                fsyncRecursive(file);
            }
            return;
        }
        if (!fileName.getPath().endsWith("_lock")) {
            RandomAccessFile file2 = new RandomAccessFile(fileName, "r");
            try {
                file2.getFD().sync();
                file2.close();
            } catch (Throwable th) {
                try {
                    file2.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private static long getParcelPadSize(long len) {
        return ((4 - (len % 4)) % 4) + len;
    }

    public static long getApkDepBlockLength(File apkFile) throws IOException {
        long apkFileLen = getParcelPadSize(((long) (apkFile.getCanonicalFile().getPath().length() + 1)) * 2);
        return 20 + apkFileLen;
    }

    public static byte[] makeApkDepBlock(File apkFile, Context context) throws IOException {
        File apkFile2 = apkFile.getCanonicalFile();
        Parcel parcel = Parcel.obtain();
        try {
            parcel.writeByte((byte) 1);
            parcel.writeString(apkFile2.getPath());
            parcel.writeLong(apkFile2.lastModified());
            parcel.writeInt(getAppVersionCode(context));
            return parcel.marshall();
        } finally {
            parcel.recycle();
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm != null) {
            try {
                PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                return pi.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
            } catch (RuntimeException e2) {
            }
        }
        return 0;
    }

    public static boolean is64Bit() {
        int i = Build.VERSION.SDK_INT;
        boolean is64bit = MarshmallowSysdeps.is64Bit();
        return is64bit;
    }

    public static boolean isSupportedDirectLoad(Context context, int appType) throws IOException {
        int i = Build.VERSION.SDK_INT;
        return MarshmallowSysdeps.isSupportedDirectLoad(context, appType);
    }

    public static boolean isDisabledExtractNativeLibs(Context context) {
        int i = Build.VERSION.SDK_INT;
        return MarshmallowSysdeps.isDisabledExtractNativeLibs(context);
    }

    @Nullable
    public static FileLocker getOrCreateLockOnDir(File soDirectory, File lockFileName, boolean blocking) throws IOException {
        try {
            try {
                FileLocker fileLocker = getFileLocker(lockFileName, blocking);
                if (0 != 0 && !soDirectory.setWritable(false)) {
                    Log.w(TAG, "error removing " + soDirectory.getCanonicalPath() + " write permission");
                }
                return fileLocker;
            } catch (FileNotFoundException e) {
                if (!soDirectory.setWritable(true)) {
                    throw e;
                }
                FileLocker fileLocker2 = getFileLocker(lockFileName, blocking);
                if (1 != 0 && !soDirectory.setWritable(false)) {
                    Log.w(TAG, "error removing " + soDirectory.getCanonicalPath() + " write permission");
                }
                return fileLocker2;
            }
        } catch (Throwable e2) {
            if (0 != 0 && !soDirectory.setWritable(false)) {
                Log.w(TAG, "error removing " + soDirectory.getCanonicalPath() + " write permission");
            }
            throw e2;
        }
    }

    @Nullable
    private static FileLocker getFileLocker(File lockFileName, boolean blocking) throws IOException {
        if (blocking) {
            return FileLocker.lock(lockFileName);
        }
        return FileLocker.tryLock(lockFileName);
    }

    public static String getBaseName(String fileName) {
        int index = fileName.lastIndexOf(46);
        if (index > 0) {
            return fileName.substring(0, index);
        }
        return fileName;
    }

    public static class Api14Utils {
        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader != null && !(classLoader instanceof BaseDexClassLoader)) {
                throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
            }
            try {
                BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) classLoader;
                Method getLdLibraryPathMethod = BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]);
                return (String) getLdLibraryPathMethod.invoke(baseDexClassLoader, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e);
            }
        }
    }
}
