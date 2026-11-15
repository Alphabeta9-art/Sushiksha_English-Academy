package androidx.media3.datasource;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes11.dex */
public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private boolean opened;
    private Uri uri;

    public static class FileDataSourceException extends DataSourceException {
        @Deprecated
        public FileDataSourceException(Exception cause) {
            super(cause, 2000);
        }

        @Deprecated
        public FileDataSourceException(String message, IOException cause) {
            super(message, cause, 2000);
        }

        public FileDataSourceException(Throwable cause, int errorCode) {
            super(cause, errorCode);
        }

        public FileDataSourceException(String message, Throwable cause, int errorCode) {
            super(message, cause, errorCode);
        }
    }

    public static final class Factory implements DataSource.Factory {
        private TransferListener listener;

        public Factory setListener(TransferListener listener) {
            this.listener = listener;
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public FileDataSource createDataSource() {
            FileDataSource dataSource = new FileDataSource();
            if (this.listener != null) {
                dataSource.addTransferListener(this.listener);
            }
            return dataSource;
        }
    }

    public FileDataSource() {
        super(false);
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws FileDataSourceException {
        Uri uri = dataSpec.uri;
        this.uri = uri;
        transferInitializing(dataSpec);
        this.file = openLocalFile(uri);
        try {
            this.file.seek(dataSpec.position);
            this.bytesRemaining = dataSpec.length == -1 ? this.file.length() - dataSpec.position : dataSpec.length;
            if (this.bytesRemaining < 0) {
                throw new FileDataSourceException(null, null, 2008);
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new FileDataSourceException(e, 2000);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) throws FileDataSourceException {
        if (length == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int bytesRead = ((RandomAccessFile) Util.castNonNull(this.file)).read(buffer, offset, (int) Math.min(this.bytesRemaining, length));
            if (bytesRead > 0) {
                this.bytesRemaining -= (long) bytesRead;
                bytesTransferred(bytesRead);
            }
            return bytesRead;
        } catch (IOException e) {
            throw new FileDataSourceException(e, 2000);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0022 */
    @Override // androidx.media3.datasource.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close() throws androidx.media3.datasource.FileDataSource.FileDataSourceException {
        /*
            r5 = this;
            r0 = 0
            r5.uri = r0
            r1 = 0
            java.io.RandomAccessFile r2 = r5.file     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
            if (r2 == 0) goto Ld
            java.io.RandomAccessFile r2 = r5.file     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
            r2.close()     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
        Ld:
            r5.file = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L18
            r5.opened = r1
            r5.transferEnded()
        L18:
            return
        L19:
            r2 = move-exception
            androidx.media3.datasource.FileDataSource$FileDataSourceException r3 = new androidx.media3.datasource.FileDataSource$FileDataSourceException     // Catch: java.lang.Throwable -> L22
            r4 = 2000(0x7d0, float:2.803E-42)
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L22
            throw r3     // Catch: java.lang.Throwable -> L22
        L22:
            r2 = move-exception
            r5.file = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L2e
            r5.opened = r1
            r5.transferEnded()
        L2e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.FileDataSource.close():void");
    }

    private static RandomAccessFile openLocalFile(Uri uri) throws FileDataSourceException {
        int i = PlaybackException.ERROR_CODE_IO_NO_PERMISSION;
        try {
            return new RandomAccessFile((String) Assertions.checkNotNull(uri.getPath()), "r");
        } catch (FileNotFoundException e) {
            if (!TextUtils.isEmpty(uri.getQuery()) || !TextUtils.isEmpty(uri.getFragment())) {
                throw new FileDataSourceException(String.format("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=%s,query=%s,fragment=%s", uri.getPath(), uri.getQuery(), uri.getFragment()), e, 1004);
            }
            if (Util.SDK_INT < 21 || !Api21.isPermissionError(e.getCause())) {
                i = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
            }
            throw new FileDataSourceException(e, i);
        } catch (SecurityException e2) {
            throw new FileDataSourceException(e2, PlaybackException.ERROR_CODE_IO_NO_PERMISSION);
        } catch (RuntimeException e3) {
            throw new FileDataSourceException(e3, 2000);
        }
    }

    private static final class Api21 {
        private Api21() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isPermissionError(Throwable e) {
            return (e instanceof ErrnoException) && ((ErrnoException) e).errno == OsConstants.EACCES;
        }
    }
}
