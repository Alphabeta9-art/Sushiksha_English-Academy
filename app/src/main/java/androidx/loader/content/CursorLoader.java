package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.core.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* JADX INFO: loaded from: classes11.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    CancellationSignal mCancellationSignal;
    Cursor mCursor;
    final Loader<Cursor>.ForceLoadContentObserver mObserver;
    String[] mProjection;
    String mSelection;
    String[] mSelectionArgs;
    String mSortOrder;
    Uri mUri;

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004a, code lost:
    
        throw r0;
     */
    @Override // androidx.loader.content.AsyncTaskLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor loadInBackground() {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r0 = r9.isLoadInBackgroundCanceled()     // Catch: java.lang.Throwable -> L55
            if (r0 != 0) goto L4d
            androidx.core.os.CancellationSignal r0 = new androidx.core.os.CancellationSignal     // Catch: java.lang.Throwable -> L55
            r0.<init>()     // Catch: java.lang.Throwable -> L55
            r9.mCancellationSignal = r0     // Catch: java.lang.Throwable -> L55
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L55
            r1 = 0
            android.content.Context r0 = r9.getContext()     // Catch: java.lang.Throwable -> L43
            android.content.ContentResolver r2 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L43
            android.net.Uri r3 = r9.mUri     // Catch: java.lang.Throwable -> L43
            java.lang.String[] r4 = r9.mProjection     // Catch: java.lang.Throwable -> L43
            java.lang.String r5 = r9.mSelection     // Catch: java.lang.Throwable -> L43
            java.lang.String[] r6 = r9.mSelectionArgs     // Catch: java.lang.Throwable -> L43
            java.lang.String r7 = r9.mSortOrder     // Catch: java.lang.Throwable -> L43
            androidx.core.os.CancellationSignal r8 = r9.mCancellationSignal     // Catch: java.lang.Throwable -> L43
            android.database.Cursor r0 = androidx.core.content.ContentResolverCompat.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L43
            r2 = r0
            if (r2 == 0) goto L39
            r2.getCount()     // Catch: java.lang.RuntimeException -> L34 java.lang.Throwable -> L43
            androidx.loader.content.Loader<android.database.Cursor>$ForceLoadContentObserver r0 = r9.mObserver     // Catch: java.lang.RuntimeException -> L34 java.lang.Throwable -> L43
            r2.registerContentObserver(r0)     // Catch: java.lang.RuntimeException -> L34 java.lang.Throwable -> L43
            goto L39
        L34:
            r0 = move-exception
            r2.close()     // Catch: java.lang.Throwable -> L43
            throw r0     // Catch: java.lang.Throwable -> L43
        L39:
            monitor-enter(r9)
            r9.mCancellationSignal = r1     // Catch: java.lang.Throwable -> L41
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L41
            return r2
        L3f:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L41
            throw r0
        L41:
            r0 = move-exception
            goto L3f
        L43:
            r0 = move-exception
            monitor-enter(r9)
            r9.mCancellationSignal = r1     // Catch: java.lang.Throwable -> L4b
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L4b
            goto L4a
        L49:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L4b
        L4a:
            throw r0
        L4b:
            r0 = move-exception
            goto L49
        L4d:
            androidx.core.os.OperationCanceledException r0 = new androidx.core.os.OperationCanceledException     // Catch: java.lang.Throwable -> L55
            r0.<init>()     // Catch: java.lang.Throwable -> L55
            throw r0     // Catch: java.lang.Throwable -> L55
        L53:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L55
            throw r0
        L55:
            r0 = move-exception
            goto L53
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.loader.content.CursorLoader.loadInBackground():android.database.Cursor");
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (this.mCancellationSignal != null) {
                this.mCancellationSignal.cancel();
            }
        }
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor oldCursor = this.mCursor;
        this.mCursor = cursor;
        if (isStarted()) {
            super.deliverResult(cursor);
        }
        if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    public CursorLoader(Context context) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
    }

    public CursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
        this.mUri = uri;
        this.mProjection = projection;
        this.mSelection = selection;
        this.mSelectionArgs = selectionArgs;
        this.mSortOrder = sortOrder;
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        if (this.mCursor != null) {
            deliverResult(this.mCursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (this.mCursor != null && !this.mCursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }

    public String[] getProjection() {
        return this.mProjection;
    }

    public void setProjection(String[] projection) {
        this.mProjection = projection;
    }

    public String getSelection() {
        return this.mSelection;
    }

    public void setSelection(String selection) {
        this.mSelection = selection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public void setSelectionArgs(String[] selectionArgs) {
        this.mSelectionArgs = selectionArgs;
    }

    public String getSortOrder() {
        return this.mSortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.mSortOrder = sortOrder;
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    @Deprecated
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("mUri=");
        writer.println(this.mUri);
        writer.print(prefix);
        writer.print("mProjection=");
        writer.println(Arrays.toString(this.mProjection));
        writer.print(prefix);
        writer.print("mSelection=");
        writer.println(this.mSelection);
        writer.print(prefix);
        writer.print("mSelectionArgs=");
        writer.println(Arrays.toString(this.mSelectionArgs));
        writer.print(prefix);
        writer.print("mSortOrder=");
        writer.println(this.mSortOrder);
        writer.print(prefix);
        writer.print("mCursor=");
        writer.println(this.mCursor);
        writer.print(prefix);
        writer.print("mContentChanged=");
        writer.println(this.mContentChanged);
    }
}
