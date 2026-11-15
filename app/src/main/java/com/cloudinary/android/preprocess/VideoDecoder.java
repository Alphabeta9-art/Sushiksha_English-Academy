package com.cloudinary.android.preprocess;

import android.content.Context;
import android.net.Uri;
import com.cloudinary.android.payload.FilePayload;
import com.cloudinary.android.payload.LocalUriPayload;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadNotFoundException;
import java.io.File;

/* JADX INFO: loaded from: classes11.dex */
public class VideoDecoder implements ResourceDecoder<Uri> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.cloudinary.android.preprocess.ResourceDecoder
    public Uri decode(Context context, Payload payload) throws PayloadNotFoundException, PayloadDecodeException {
        if (payload instanceof LocalUriPayload) {
            Uri uri = ((LocalUriPayload) payload).getData();
            return uri;
        }
        if (payload instanceof FilePayload) {
            File file = (File) payload.prepare(context);
            Uri uri2 = Uri.fromFile(file);
            return uri2;
        }
        throw new PayloadDecodeException();
    }
}
