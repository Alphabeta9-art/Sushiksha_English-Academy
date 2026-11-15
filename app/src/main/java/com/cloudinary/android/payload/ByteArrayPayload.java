package com.cloudinary.android.payload;

import android.content.Context;
import android.util.Base64;
import com.cloudinary.android.Logger;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes11.dex */
public class ByteArrayPayload extends Payload<byte[]> {
    public static final String ENCODING_CHARSET = "UTF8";
    private static final String TAG = ByteArrayPayload.class.getSimpleName();
    static final String URI_KEY = "bytes";

    public ByteArrayPayload(byte[] data) {
        super(data);
    }

    public ByteArrayPayload() {
    }

    private static String encode(byte[] data) {
        try {
            return new String(Base64.encode(data, 8), ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Logger.e(TAG, "Cannot encode image bytes", e);
            return null;
        }
    }

    private static byte[] decode(String encoded) {
        try {
            return Base64.decode(encoded.getBytes(ENCODING_CHARSET), 8);
        } catch (UnsupportedEncodingException e) {
            Logger.e(TAG, "Cannot decode image bytes", e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public String toUri() {
        return "bytes://" + encode((byte[]) this.data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public long getLength(Context context) {
        return ((byte[]) this.data).length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public Object prepare(Context context) throws EmptyByteArrayException {
        if (this.data == 0 || ((byte[]) this.data).length < 1) {
            throw new EmptyByteArrayException();
        }
        return this.data;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, byte[]] */
    @Override // com.cloudinary.android.payload.Payload
    void loadData(String encodedData) {
        this.data = decode(encodedData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payload<?> payload = (Payload) o;
        if (this.data != 0) {
            return Arrays.equals((byte[]) this.data, (byte[]) payload.data);
        }
        if (payload.data == 0) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public int hashCode() {
        if (this.data != 0) {
            return Arrays.hashCode((byte[]) this.data);
        }
        return 0;
    }
}
