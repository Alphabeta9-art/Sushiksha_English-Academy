package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes11.dex */
public final class ProfileVerifier {
    private static final String CUR_PROFILES_BASE_DIR = "/data/misc/profiles/cur/0/";
    private static final String PROFILE_FILE_NAME = "primary.prof";
    private static final String PROFILE_INSTALLED_CACHE_FILE_NAME = "profileInstalled";
    private static final String REF_PROFILES_BASE_DIR = "/data/misc/profiles/ref/";
    private static final String TAG = "ProfileVerifier";
    private static final ResolvableFuture<CompilationStatus> sFuture = ResolvableFuture.create();
    private static final Object SYNC_OBJ = new Object();
    private static CompilationStatus sCompilationStatus = null;

    private ProfileVerifier() {
    }

    public static CompilationStatus writeProfileVerification(Context context) {
        return writeProfileVerification(context, false);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x002d  */
    /* JADX WARN: Code duplicated, block: B:22:0x002f  */
    /* JADX WARN: Code duplicated, block: B:24:0x0032 A[Catch: IOException -> 0x0044, all -> 0x0135, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:10:0x000e, B:12:0x0012, B:13:0x0014, B:16:0x001a, B:24:0x0032, B:36:0x0047, B:38:0x004d, B:40:0x0053, B:46:0x0079, B:51:0x00a0, B:52:0x00a5, B:54:0x00b9, B:63:0x00cb, B:65:0x00d1, B:67:0x00d5, B:80:0x00ef, B:83:0x00f5, B:87:0x0101, B:89:0x0109, B:96:0x011c, B:97:0x0120, B:92:0x0110, B:58:0x00c2, B:59:0x00c6, B:100:0x0123, B:101:0x0129, B:103:0x012b, B:104:0x0131, B:33:0x0043, B:32:0x0040), top: B:112:0x000e, inners: #4, #5 }] */
    static CompilationStatus writeProfileVerification(Context context, boolean forceVerifyCurrentProfile) {
        boolean hasEmbeddedProfile;
        int resultCode;
        int resultCode2;
        int resultCode3;
        AssetFileDescriptor afd;
        boolean hasEmbeddedProfile2;
        if (!forceVerifyCurrentProfile && sCompilationStatus != null) {
            return sCompilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!forceVerifyCurrentProfile) {
                if (sCompilationStatus != null) {
                    return sCompilationStatus;
                }
                boolean hasCurrentProfile = false;
                try {
                    afd = context.getAssets().openFd("dexopt/baseline.prof");
                    try {
                        if (afd.getLength() > 0) {
                            hasEmbeddedProfile2 = true;
                        } else {
                            hasEmbeddedProfile2 = false;
                        }
                        if (afd != null) {
                            afd.close();
                        }
                        hasEmbeddedProfile = hasEmbeddedProfile2;
                        if (Build.VERSION.SDK_INT >= 28 || Build.VERSION.SDK_INT == 30) {
                            return setCompilationStatus(262144, false, false, hasEmbeddedProfile);
                        }
                        File referenceProfileFile = new File(new File(REF_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                        long referenceProfileSize = referenceProfileFile.length();
                        boolean hasReferenceProfile = referenceProfileFile.exists() && referenceProfileSize > 0;
                        File currentProfileFile = new File(new File(CUR_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                        long currentProfileSize = currentProfileFile.length();
                        if (currentProfileFile.exists() && currentProfileSize > 0) {
                            hasCurrentProfile = true;
                        }
                        try {
                            long packageLastUpdateTime = getPackageLastUpdateTime(context);
                            File cacheFile = new File(context.getFilesDir(), PROFILE_INSTALLED_CACHE_FILE_NAME);
                            Cache currentCache = null;
                            if (cacheFile.exists()) {
                                try {
                                    currentCache = Cache.readFromFile(cacheFile);
                                } catch (IOException e) {
                                    return setCompilationStatus(131072, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                                }
                            }
                            if (currentCache == null || currentCache.mPackageLastUpdateTime != packageLastUpdateTime || currentCache.mResultCode == 2) {
                                if (!hasEmbeddedProfile) {
                                    resultCode = CompilationStatus.RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED;
                                } else if (hasReferenceProfile) {
                                    resultCode = 1;
                                } else if (hasCurrentProfile) {
                                    resultCode = 2;
                                } else {
                                    resultCode = 0;
                                }
                            } else {
                                resultCode = currentCache.mResultCode;
                            }
                            if (forceVerifyCurrentProfile && hasCurrentProfile && resultCode != 1) {
                                resultCode = 2;
                            }
                            if (currentCache != null && currentCache.mResultCode == 2 && resultCode == 1 && referenceProfileSize < currentCache.mInstalledCurrentProfileSize) {
                                resultCode2 = 3;
                            } else {
                                resultCode2 = resultCode;
                            }
                            Cache newCache = new Cache(1, resultCode2, packageLastUpdateTime, currentProfileSize);
                            if (currentCache == null || !currentCache.equals(newCache)) {
                                try {
                                    newCache.writeOnFile(cacheFile);
                                } catch (IOException e2) {
                                    resultCode3 = 196608;
                                }
                            }
                            resultCode3 = resultCode2;
                            return setCompilationStatus(resultCode3, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                        } catch (PackageManager.NameNotFoundException e3) {
                            return setCompilationStatus(65536, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                        }
                    } catch (Throwable th) {
                        if (afd == null) {
                            throw th;
                        }
                        try {
                            afd.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    hasEmbeddedProfile = false;
                }
            } else {
                boolean hasCurrentProfile2 = false;
                afd = context.getAssets().openFd("dexopt/baseline.prof");
                if (afd.getLength() > 0) {
                    hasEmbeddedProfile2 = true;
                } else {
                    hasEmbeddedProfile2 = false;
                }
                if (afd != null) {
                    afd.close();
                }
                hasEmbeddedProfile = hasEmbeddedProfile2;
                if (Build.VERSION.SDK_INT >= 28) {
                }
                return setCompilationStatus(262144, false, false, hasEmbeddedProfile);
            }
            throw th;
        }
    }

    private static CompilationStatus setCompilationStatus(int resultCode, boolean hasReferenceProfile, boolean hasCurrentProfile, boolean hasEmbeddedProfile) {
        sCompilationStatus = new CompilationStatus(resultCode, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
        sFuture.set(sCompilationStatus);
        return sCompilationStatus;
    }

    private static long getPackageLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static ListenableFuture<CompilationStatus> getCompilationStatusAsync() {
        return sFuture;
    }

    static class Cache {
        private static final int SCHEMA = 1;
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        Cache(int schema, int resultCode, long packageLastUpdateTime, long installedCurrentProfileSize) {
            this.mSchema = schema;
            this.mResultCode = resultCode;
            this.mPackageLastUpdateTime = packageLastUpdateTime;
            this.mInstalledCurrentProfileSize = installedCurrentProfileSize;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof Cache)) {
                return false;
            }
            Cache cacheFile = (Cache) o;
            if (this.mResultCode == cacheFile.mResultCode && this.mPackageLastUpdateTime == cacheFile.mPackageLastUpdateTime && this.mSchema == cacheFile.mSchema && this.mInstalledCurrentProfileSize == cacheFile.mInstalledCurrentProfileSize) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize));
        }

        void writeOnFile(File file) throws IOException {
            file.delete();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            try {
                dos.writeInt(this.mSchema);
                dos.writeInt(this.mResultCode);
                dos.writeLong(this.mPackageLastUpdateTime);
                dos.writeLong(this.mInstalledCurrentProfileSize);
                dos.close();
            } catch (Throwable th) {
                try {
                    dos.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        static Cache readFromFile(File file) throws IOException {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dis.readInt(), dis.readInt(), dis.readLong(), dis.readLong());
                dis.close();
                return cache;
            } catch (Throwable th) {
                try {
                    dis.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }
    }

    public static class CompilationStatus {
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE = 1;
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE_NON_MATCHING = 3;
        public static final int RESULT_CODE_ERROR_CACHE_FILE_EXISTS_BUT_CANNOT_BE_READ = 131072;
        public static final int RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE = 196608;
        private static final int RESULT_CODE_ERROR_CODE_BIT_SHIFT = 16;
        public static final int RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED = 327680;
        public static final int RESULT_CODE_ERROR_PACKAGE_NAME_DOES_NOT_EXIST = 65536;
        public static final int RESULT_CODE_ERROR_UNSUPPORTED_API_VERSION = 262144;

        @Deprecated
        public static final int RESULT_CODE_NO_PROFILE = 0;
        public static final int RESULT_CODE_NO_PROFILE_INSTALLED = 0;
        public static final int RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION = 2;
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        CompilationStatus(int resultCode, boolean hasReferenceProfile, boolean hasCurrentProfile, boolean hasEmbeddedProfile) {
            this.mResultCode = resultCode;
            this.mHasCurrentProfile = hasCurrentProfile;
            this.mHasReferenceProfile = hasReferenceProfile;
            this.mHasEmbeddedProfile = hasEmbeddedProfile;
        }

        public int getProfileInstallResultCode() {
            return this.mResultCode;
        }

        public boolean isCompiledWithProfile() {
            return this.mHasReferenceProfile;
        }

        public boolean hasProfileEnqueuedForCompilation() {
            return this.mHasCurrentProfile;
        }

        public boolean appApkHasEmbeddedProfile() {
            return this.mHasEmbeddedProfile;
        }
    }

    private static class Api33Impl {
        private Api33Impl() {
        }

        static PackageInfo getPackageInfo(PackageManager packageManager, Context context) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }
}
