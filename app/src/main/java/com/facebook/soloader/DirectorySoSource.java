package com.facebook.soloader;

import android.os.StrictMode;
import android.util.Log;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public class DirectorySoSource extends SoSource {
    public static final int ON_LD_LIBRARY_PATH = 2;
    public static final int RESOLVE_DEPENDENCIES = 1;
    protected final List<String> denyList;
    protected final int flags;
    protected final File soDirectory;

    public DirectorySoSource(File soDirectory, int flags) {
        this(soDirectory, flags, new String[0]);
    }

    public DirectorySoSource(File soDirectory, int flags, String[] denyList) {
        this.soDirectory = soDirectory;
        this.flags = flags;
        this.denyList = Arrays.asList(denyList);
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(soName, loadFlags, this.soDirectory, threadPolicy);
    }

    protected int loadLibraryFrom(String soName, int loadFlags, File libDir, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader == null) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
        if (this.denyList.contains(soName)) {
            Log.d("SoLoader", soName + " is on the denyList, skip loading from " + libDir.getCanonicalPath());
            return 0;
        }
        File soFile = getSoFileByName(soName);
        if (soFile == null) {
            Log.v("SoLoader", soName + " not found on " + libDir.getCanonicalPath());
            return 0;
        }
        Log.d("SoLoader", soName + " found on " + libDir.getCanonicalPath());
        if ((loadFlags & 1) != 0 && (this.flags & 2) != 0) {
            Log.d("SoLoader", soName + " loaded implicitly");
            return 2;
        }
        ElfByteChannel bc = null;
        boolean shouldLoadDependencies = (this.flags & 1) != 0;
        boolean shouldLoadFromFile = soFile.getName().equals(soName);
        if (shouldLoadDependencies || !shouldLoadFromFile) {
            try {
                bc = getChannel(soFile);
            } catch (Throwable th) {
                if (bc != null) {
                    bc.close();
                }
                throw th;
            }
        }
        if (shouldLoadDependencies) {
            loadDependencies(soName, bc, loadFlags, threadPolicy);
        } else {
            Log.d("SoLoader", "Not resolving dependencies for " + soName);
        }
        try {
            if (shouldLoadFromFile) {
                SoLoader.sSoFileLoader.load(soFile.getAbsolutePath(), loadFlags);
            } else {
                SoLoader.sSoFileLoader.loadBytes(soFile.getAbsolutePath(), bc, loadFlags);
            }
            if (bc != null) {
                bc.close();
            }
            return 1;
        } catch (UnsatisfiedLinkError e) {
            if (!e.getMessage().contains("bad ELF magic")) {
                throw e;
            }
            Log.d("SoLoader", "Corrupted lib file detected");
            if (bc == null) {
                return 3;
            }
            bc.close();
            return 3;
        }
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    protected File getSoFileByName(String soName) throws IOException {
        File soFile = new File(this.soDirectory, soName);
        if (soFile.exists()) {
            return soFile;
        }
        return null;
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String getLibraryPath(String soName) throws IOException {
        File soFile = getSoFileByName(soName);
        if (soFile == null) {
            return null;
        }
        return soFile.getCanonicalPath();
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String[] getLibraryDependencies(String soName) throws IOException {
        File soFile = getSoFileByName(soName);
        if (soFile == null) {
            return null;
        }
        ElfByteChannel bc = getChannel(soFile);
        try {
            String[] dependencies = getDependencies(soName, bc);
            if (bc != null) {
                bc.close();
            }
            return dependencies;
        } catch (Throwable th) {
            if (bc != null) {
                try {
                    bc.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void loadDependencies(String soName, ElfByteChannel bc, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        String[] dependencies = getDependencies(soName, bc);
        Log.d("SoLoader", "Loading " + soName + "'s dependencies: " + Arrays.toString(dependencies));
        for (String dependency : dependencies) {
            if (!dependency.startsWith(DomExceptionUtils.SEPARATOR)) {
                SoLoader.loadLibraryBySoName(dependency, loadFlags | 1, threadPolicy);
            }
        }
    }

    protected ElfByteChannel getChannel(File soFile) throws IOException {
        return new ElfFileChannel(soFile);
    }

    protected String[] getDependencies(String soName, ElfByteChannel bc) throws IOException {
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

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File unpackLibrary(String soName) throws IOException {
        return getSoFileByName(soName);
    }

    @Override // com.facebook.soloader.SoSource
    public void addToLdLibraryPath(Collection<String> paths) {
        paths.add(this.soDirectory.getAbsolutePath());
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        String path;
        try {
            path = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException e) {
            path = this.soDirectory.getName();
        }
        return getClass().getName() + "[root = " + path + " flags = " + this.flags + AbstractJsonLexerKt.END_LIST;
    }
}
