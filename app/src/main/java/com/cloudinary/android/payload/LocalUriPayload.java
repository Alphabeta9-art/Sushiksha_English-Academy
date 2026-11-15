package com.cloudinary.android.payload;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.cloudinary.utils.Base64Coder;

/* JADX INFO: loaded from: classes11.dex */
public class LocalUriPayload extends Payload<Uri> {
    public static final String[] PROJECTION = {"_size"};
    static final String URI_KEY = "uri";

    public LocalUriPayload(Uri data) {
        super(data);
    }

    public LocalUriPayload() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public String toUri() {
        return "uri://" + Base64Coder.encodeString(((Uri) this.data).toString());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, android.net.Uri] */
    @Override // com.cloudinary.android.payload.Payload
    void loadData(String encodedData) {
        this.data = Uri.parse(Base64Coder.decodeString(encodedData));
    }

    @Override // com.cloudinary.android.payload.Payload
    public long getLength(Context context) {
        return fetchFileSizeFromUri(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public Object prepare(Context context) throws PayloadNotFoundException {
        try {
            return context.getContentResolver().openInputStream((Uri) this.data);
        } catch (java.io.FileNotFoundException e) {
            throw new LocalUriNotFoundException(String.format("Uri %s could not be found", ((Uri) this.data).toString()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long fetchFileSizeFromUri(Context context) {
        Cursor returnCursor = null;
        long size = 0;
        try {
            returnCursor = context.getContentResolver().query((Uri) this.data, PROJECTION, null, null, null);
            if (returnCursor != null && returnCursor.moveToNext()) {
                int sizeIndex = returnCursor.getColumnIndex("_size");
                size = returnCursor.getLong(sizeIndex);
            }
            return size;
        } finally {
            if (returnCursor != null) {
                returnCursor.close();
            }
        }
    }
}
