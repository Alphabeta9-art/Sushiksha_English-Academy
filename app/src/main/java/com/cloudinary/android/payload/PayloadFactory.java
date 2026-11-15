package com.cloudinary.android.payload;

import android.net.Uri;

/* JADX INFO: loaded from: classes11.dex */
public class PayloadFactory {
    private static final String TAG = PayloadFactory.class.getSimpleName();

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x003e  */
    public static Payload fromUri(String uri) {
        Payload payload;
        Uri parsed = Uri.parse(uri);
        String scheme = parsed.getScheme();
        if (scheme == null) {
            return null;
        }
        switch (scheme) {
            case "bytes":
                payload = new ByteArrayPayload();
                break;
            case "file":
                payload = new FilePayload();
                break;
            case "uri":
                payload = new LocalUriPayload();
                break;
            case "resource":
                payload = new ResourcePayload();
                break;
            default:
                return null;
        }
        payload.loadData(parsed.getHost());
        return payload;
    }
}
