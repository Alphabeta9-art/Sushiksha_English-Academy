package androidx.media3.datasource;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.facebook.common.util.UriUtil;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes11.dex */
public final class RawResourceDataSource extends BaseDataSource {
    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private final String packageName;
    private final Resources resources;
    private Uri uri;

    public static class RawResourceDataSourceException extends DataSourceException {
        @Deprecated
        public RawResourceDataSourceException(String message) {
            super(message, null, 2000);
        }

        @Deprecated
        public RawResourceDataSourceException(Throwable cause) {
            super(cause, 2000);
        }

        public RawResourceDataSourceException(String message, Throwable cause, int errorCode) {
            super(message, cause, errorCode);
        }
    }

    public static Uri buildRawResourceUri(int rawResourceId) {
        return Uri.parse("rawresource:///" + rawResourceId);
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.resources = context.getResources();
        this.packageName = context.getPackageName();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws RawResourceDataSourceException {
        int resourceId;
        IOException e;
        Uri uri = dataSpec.uri.normalizeScheme();
        this.uri = uri;
        if (TextUtils.equals(RAW_RESOURCE_SCHEME, uri.getScheme()) || (TextUtils.equals(UriUtil.QUALIFIED_RESOURCE_SCHEME, uri.getScheme()) && uri.getPathSegments().size() == 1 && ((String) Assertions.checkNotNull(uri.getLastPathSegment())).matches("\\d+"))) {
            try {
                resourceId = Integer.parseInt((String) Assertions.checkNotNull(uri.getLastPathSegment()));
            } catch (NumberFormatException e2) {
                throw new RawResourceDataSourceException("Resource identifier must be an integer.", null, 1004);
            }
        } else if (TextUtils.equals(UriUtil.QUALIFIED_RESOURCE_SCHEME, uri.getScheme())) {
            String path = (String) Assertions.checkNotNull(uri.getPath());
            if (path.startsWith(DomExceptionUtils.SEPARATOR)) {
                path = path.substring(1);
            }
            String host = uri.getHost();
            String resourceName = (TextUtils.isEmpty(host) ? "" : host + ":") + path;
            resourceId = this.resources.getIdentifier(resourceName, "raw", this.packageName);
            if (resourceId == 0) {
                throw new RawResourceDataSourceException("Resource not found.", null, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
            }
        } else {
            throw new RawResourceDataSourceException("Unsupported URI scheme (" + uri.getScheme() + "). Only " + RAW_RESOURCE_SCHEME + " and " + UriUtil.QUALIFIED_RESOURCE_SCHEME + " are supported.", null, 1004);
        }
        transferInitializing(dataSpec);
        try {
            AssetFileDescriptor assetFileDescriptor = this.resources.openRawResourceFd(resourceId);
            this.assetFileDescriptor = assetFileDescriptor;
            if (assetFileDescriptor == null) {
                throw new RawResourceDataSourceException("Resource is compressed: " + uri, null, 2000);
            }
            long assetFileDescriptorLength = assetFileDescriptor.getLength();
            FileInputStream inputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
            this.inputStream = inputStream;
            if (assetFileDescriptorLength != -1) {
                try {
                    if (dataSpec.position > assetFileDescriptorLength) {
                        throw new RawResourceDataSourceException(null, null, 2008);
                    }
                } catch (RawResourceDataSourceException e3) {
                    throw e3;
                } catch (IOException e4) {
                    e = e4;
                    throw new RawResourceDataSourceException(null, e, 2000);
                }
            }
            try {
                long assetFileDescriptorOffset = assetFileDescriptor.getStartOffset();
                long skipped = inputStream.skip(dataSpec.position + assetFileDescriptorOffset) - assetFileDescriptorOffset;
                try {
                    if (skipped == dataSpec.position) {
                        if (assetFileDescriptorLength != r12) {
                            this.bytesRemaining = assetFileDescriptorLength - skipped;
                            if (this.bytesRemaining < 0) {
                                throw new DataSourceException(2008);
                            }
                        } else {
                            FileChannel channel = inputStream.getChannel();
                            if (channel.size() == 0) {
                                this.bytesRemaining = -1L;
                            } else {
                                this.bytesRemaining = channel.size() - channel.position();
                                if (this.bytesRemaining < 0) {
                                    throw new RawResourceDataSourceException(null, null, 2008);
                                }
                            }
                        }
                        if (dataSpec.length != -1) {
                            this.bytesRemaining = this.bytesRemaining == -1 ? dataSpec.length : Math.min(this.bytesRemaining, dataSpec.length);
                        }
                        this.opened = true;
                        transferStarted(dataSpec);
                        return dataSpec.length != -1 ? dataSpec.length : this.bytesRemaining;
                    }
                    throw new RawResourceDataSourceException(null, null, 2008);
                } catch (RawResourceDataSourceException e5) {
                    throw e5;
                } catch (IOException e6) {
                    e = e6;
                    throw new RawResourceDataSourceException(null, e, 2000);
                }
            } catch (RawResourceDataSourceException e7) {
                throw e7;
            } catch (IOException e8) {
                e = e8;
            }
        } catch (Resources.NotFoundException e9) {
            throw new RawResourceDataSourceException(null, e9, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) throws RawResourceDataSourceException {
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
                if (this.bytesRemaining == -1) {
                    return -1;
                }
                throw new RawResourceDataSourceException("End of stream reached having not read sufficient data.", new EOFException(), 2000);
            }
            if (this.bytesRemaining != -1) {
                this.bytesRemaining -= (long) bytesRead;
            }
            bytesTransferred(bytesRead);
            return bytesRead;
        } catch (IOException e) {
            throw new RawResourceDataSourceException(null, e, 2000);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x002e */
    /* JADX WARN: Bottom block not found for handler: all -> 0x003d */
    /* JADX WARN: Bottom block not found for handler: all -> 0x005c */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0063, code lost:
    
        r5.opened = false;
        transferEnded();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0068, code lost:
    
        throw r1;
     */
    @Override // androidx.media3.datasource.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            r5.uri = r0
            r1 = 2000(0x7d0, float:2.803E-42)
            r2 = 0
            java.io.InputStream r3 = r5.inputStream     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L3d
            if (r3 == 0) goto Lf
            java.io.InputStream r3 = r5.inputStream     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L3d
            r3.close()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L3d
        Lf:
            r5.inputStream = r0
            android.content.res.AssetFileDescriptor r3 = r5.assetFileDescriptor     // Catch: java.io.IOException -> L27 java.lang.Throwable -> L2e
            if (r3 == 0) goto L1a
            android.content.res.AssetFileDescriptor r3 = r5.assetFileDescriptor     // Catch: java.io.IOException -> L27 java.lang.Throwable -> L2e
            r3.close()     // Catch: java.io.IOException -> L27 java.lang.Throwable -> L2e
        L1a:
            r5.assetFileDescriptor = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L25
            r5.opened = r2
            r5.transferEnded()
        L25:
            return
        L27:
            r3 = move-exception
            androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException r4 = new androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException     // Catch: java.lang.Throwable -> L2e
            r4.<init>(r0, r3, r1)     // Catch: java.lang.Throwable -> L2e
            throw r4     // Catch: java.lang.Throwable -> L2e
        L2e:
            r1 = move-exception
            r5.assetFileDescriptor = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L68
            goto L63
        L36:
            r3 = move-exception
            androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException r4 = new androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException     // Catch: java.lang.Throwable -> L3d
            r4.<init>(r0, r3, r1)     // Catch: java.lang.Throwable -> L3d
            throw r4     // Catch: java.lang.Throwable -> L3d
        L3d:
            r3 = move-exception
            r5.inputStream = r0
            android.content.res.AssetFileDescriptor r4 = r5.assetFileDescriptor     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5c
            if (r4 == 0) goto L49
            android.content.res.AssetFileDescriptor r4 = r5.assetFileDescriptor     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5c
            r4.close()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L5c
        L49:
            r5.assetFileDescriptor = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L54
            r5.opened = r2
            r5.transferEnded()
        L54:
            throw r3
        L55:
            r3 = move-exception
            androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException r4 = new androidx.media3.datasource.RawResourceDataSource$RawResourceDataSourceException     // Catch: java.lang.Throwable -> L5c
            r4.<init>(r0, r3, r1)     // Catch: java.lang.Throwable -> L5c
            throw r4     // Catch: java.lang.Throwable -> L5c
        L5c:
            r1 = move-exception
            r5.assetFileDescriptor = r0
            boolean r0 = r5.opened
            if (r0 == 0) goto L68
        L63:
            r5.opened = r2
            r5.transferEnded()
        L68:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.RawResourceDataSource.close():void");
    }
}
