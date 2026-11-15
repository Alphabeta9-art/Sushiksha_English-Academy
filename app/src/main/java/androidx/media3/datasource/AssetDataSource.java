package androidx.media3.datasource;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import androidx.collection.SieveCacheKt;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes11.dex */
public final class AssetDataSource extends BaseDataSource {
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private Uri uri;

    public static final class AssetDataSourceException extends DataSourceException {
        @Deprecated
        public AssetDataSourceException(IOException cause) {
            super(cause, 2000);
        }

        public AssetDataSourceException(Throwable cause, int errorCode) {
            super(cause, errorCode);
        }
    }

    public AssetDataSource(Context context) {
        super(false);
        this.assetManager = context.getAssets();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws AssetDataSourceException {
        int i;
        try {
            this.uri = dataSpec.uri;
            String path = (String) Assertions.checkNotNull(this.uri.getPath());
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith(DomExceptionUtils.SEPARATOR)) {
                path = path.substring(1);
            }
            transferInitializing(dataSpec);
            this.inputStream = this.assetManager.open(path, 1);
            long skipped = this.inputStream.skip(dataSpec.position);
            if (skipped < dataSpec.position) {
                throw new AssetDataSourceException(null, 2008);
            }
            if (dataSpec.length != -1) {
                this.bytesRemaining = dataSpec.length;
            } else {
                this.bytesRemaining = this.inputStream.available();
                if (this.bytesRemaining == SieveCacheKt.NodeLinkMask) {
                    this.bytesRemaining = -1L;
                }
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        } catch (AssetDataSourceException e) {
            throw e;
        } catch (IOException e2) {
            if (e2 instanceof FileNotFoundException) {
                i = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
            } else {
                i = 2000;
            }
            throw new AssetDataSourceException(e2, i);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) throws AssetDataSourceException {
        if (length == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int bytesToRead = this.bytesRemaining == -1 ? length : (int) Math.min(this.bytesRemaining, length);
            int bytesRead = ((InputStream) Util.castNonNull(this.inputStream)).read(buffer, offset, bytesToRead);
            if (bytesRead == -1) {
                return -1;
            }
            if (this.bytesRemaining != -1) {
                this.bytesRemaining -= (long) bytesRead;
            }
            bytesTransferred(bytesRead);
            return bytesRead;
        } catch (IOException e) {
            throw new AssetDataSourceException(e, 2000);
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
    public void close() throws androidx.media3.datasource.AssetDataSource.AssetDataSourceException {
        /*
            r5 = this;
            r0 = 0
            r5.uri = r0
            r1 = 0
            java.io.InputStream r2 = r5.inputStream     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
            if (r2 == 0) goto Ld
            java.io.InputStream r2 = r5.inputStream     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
            r2.close()     // Catch: java.io.IOException -> L19 java.lang.Throwable -> L22
        Ld:
            r5.inputStream = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L18
            r5.opened = r1
            r5.transferEnded()
        L18:
            return
        L19:
            r2 = move-exception
            androidx.media3.datasource.AssetDataSource$AssetDataSourceException r3 = new androidx.media3.datasource.AssetDataSource$AssetDataSourceException     // Catch: java.lang.Throwable -> L22
            r4 = 2000(0x7d0, float:2.803E-42)
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L22
            throw r3     // Catch: java.lang.Throwable -> L22
        L22:
            r2 = move-exception
            r5.inputStream = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L2e
            r5.opened = r1
            r5.transferEnded()
        L2e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.AssetDataSource.close():void");
    }
}
