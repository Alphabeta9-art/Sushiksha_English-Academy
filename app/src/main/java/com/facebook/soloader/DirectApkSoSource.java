package com.facebook.soloader;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public class DirectApkSoSource extends SoSource {
    private final File mApkFile;
    private final Set<String> mDirectApkLdPaths;
    private final Map<String, Set<String>> mLibsInApkMap = new HashMap();

    public DirectApkSoSource(Context context) {
        String apkPath = context.getApplicationInfo().sourceDir;
        this.mDirectApkLdPaths = getDirectApkLdPaths("", apkPath);
        this.mApkFile = new File(apkPath);
    }

    public DirectApkSoSource(File apkFile) {
        this.mDirectApkLdPaths = getDirectApkLdPaths(SysUtil.getBaseName(apkFile.getName()), apkFile.getAbsolutePath());
        this.mApkFile = apkFile;
    }

    public DirectApkSoSource(File apkFile, Set<String> directApkLdPaths) {
        this.mDirectApkLdPaths = directApkLdPaths;
        this.mApkFile = apkFile;
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader == null) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
        for (String directApkLdPath : this.mDirectApkLdPaths) {
            Set<String> libsInApk = this.mLibsInApkMap.get(directApkLdPath);
            if (TextUtils.isEmpty(directApkLdPath) || libsInApk == null || !libsInApk.contains(soName)) {
                Log.v("SoLoader", soName + " not found on " + directApkLdPath);
            } else {
                loadDependencies(soName, loadFlags, threadPolicy);
                try {
                    String soPath = directApkLdPath + File.separator + soName;
                    loadFlags |= 4;
                    SoLoader.sSoFileLoader.load(soPath, loadFlags);
                    Log.d("SoLoader", soName + " found on " + directApkLdPath);
                    return 1;
                } catch (UnsatisfiedLinkError e) {
                    Log.w("SoLoader", soName + " not found on " + directApkLdPath + " flag: " + loadFlags, e);
                }
            }
        }
        return 0;
    }

    @Override // com.facebook.soloader.SoSource
    public File unpackLibrary(String soName) throws IOException {
        throw new UnsupportedOperationException("DirectAPKSoSource doesn't support unpackLibrary");
    }

    public boolean isValid() {
        return !this.mDirectApkLdPaths.isEmpty();
    }

    static Set<String> getDirectApkLdPaths(String apkName, String apkPath) {
        String fallbackApkLdPath;
        Set<String> directApkPathSet = new HashSet<>();
        int i = Build.VERSION.SDK_INT;
        String classLoaderLdLibraryPath = SysUtil.Api14Utils.getClassLoaderLdLoadLibrary();
        if (classLoaderLdLibraryPath != null) {
            String[] paths = classLoaderLdLibraryPath.split(":");
            for (String path : paths) {
                if (path.contains(apkName + ".apk!/")) {
                    directApkPathSet.add(path);
                }
            }
        }
        if (directApkPathSet.isEmpty() && (fallbackApkLdPath = getFallbackApkLdPath(apkPath)) != null) {
            directApkPathSet.add(fallbackApkLdPath);
        }
        return directApkPathSet;
    }

    private static String[] getDependencies(String soName, ElfByteChannel bc) throws IOException {
        if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
            Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", soName, "]");
        }
        try {
            return NativeDeps.getDependencies(soName, bc);
        } finally {
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.endSection();
            }
        }
    }

    @Nullable
    private static String getFallbackApkLdPath(String apkPath) {
        String[] supportedAbis = SysUtil.getSupportedAbis();
        if (!TextUtils.isEmpty(apkPath) && supportedAbis.length > 0) {
            return apkPath + "!/lib/" + supportedAbis[0];
        }
        return null;
    }

    private void loadDependencies(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        ZipFile mZipFile = new ZipFile(this.mApkFile);
        try {
            Enumeration<? extends ZipEntry> entries = mZipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry != null && entry.getName().endsWith(DomExceptionUtils.SEPARATOR + soName)) {
                    ElfByteChannel bc = new ElfZipFileChannel(mZipFile, entry);
                    try {
                        for (String dependency : getDependencies(soName, bc)) {
                            if (!dependency.startsWith(DomExceptionUtils.SEPARATOR)) {
                                SoLoader.loadLibraryBySoName(dependency, loadFlags | 1, threadPolicy);
                            }
                        }
                        bc.close();
                        break;
                    } catch (Throwable th) {
                        try {
                            bc.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
            }
            mZipFile.close();
        } catch (Throwable th3) {
            try {
                mZipFile.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    @Override // com.facebook.soloader.SoSource
    protected void prepare(int flags) throws IOException {
        int i;
        String subDir = null;
        for (String directApkLdPath : this.mDirectApkLdPaths) {
            if (!TextUtils.isEmpty(directApkLdPath) && (i = directApkLdPath.indexOf(33)) >= 0 && i + 2 < directApkLdPath.length()) {
                subDir = directApkLdPath.substring(i + 2);
            }
            if (!TextUtils.isEmpty(subDir)) {
                ZipFile mZipFile = new ZipFile(this.mApkFile);
                try {
                    Enumeration<? extends ZipEntry> entries = mZipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        if (entry != null && entry.getName().startsWith(subDir) && entry.getName().endsWith(".so") && entry.getMethod() == 0) {
                            String soName = entry.getName().substring(subDir.length() + 1);
                            append(directApkLdPath, soName);
                        }
                    }
                    mZipFile.close();
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
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        return getClass().getName() + "[root = " + this.mDirectApkLdPaths.toString() + AbstractJsonLexerKt.END_LIST;
    }

    private synchronized void append(String ldPath, String soName) {
        if (!this.mLibsInApkMap.containsKey(ldPath)) {
            this.mLibsInApkMap.put(ldPath, new HashSet());
        }
        this.mLibsInApkMap.get(ldPath).add(soName);
    }
}
