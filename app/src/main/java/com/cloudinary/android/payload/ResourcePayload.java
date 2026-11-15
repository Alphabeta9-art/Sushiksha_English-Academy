package com.cloudinary.android.payload;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import java.io.IOException;

/* JADX INFO: loaded from: classes11.dex */
public class ResourcePayload extends Payload<Integer> {
    static final String URI_KEY = "resource";

    public ResourcePayload(Integer rawResourceId) {
        super(rawResourceId);
    }

    ResourcePayload() {
    }

    @Override // com.cloudinary.android.payload.Payload
    public String toUri() {
        return "resource://" + this.data;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Integer] */
    @Override // com.cloudinary.android.payload.Payload
    void loadData(String encodedData) {
        this.data = Integer.valueOf(Integer.parseInt(encodedData));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public long getLength(Context context) throws PayloadNotFoundException {
        AssetFileDescriptor afd = null;
        try {
            try {
                afd = context.getResources().openRawResourceFd(((Integer) this.data).intValue());
                long size = afd.getLength();
                if (afd != null) {
                    try {
                        afd.close();
                    } catch (IOException e) {
                    }
                }
                return size;
            } catch (Resources.NotFoundException e2) {
                throw new ResourceNotFoundException(String.format("Resource id %d not found", this.data));
            }
        } catch (Throwable e3) {
            if (afd != null) {
                try {
                    afd.close();
                } catch (IOException e4) {
                }
            }
            throw e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public Object prepare(Context context) throws PayloadNotFoundException {
        try {
            return context.getResources().openRawResource(((Integer) this.data).intValue());
        } catch (Resources.NotFoundException e) {
            throw new ResourceNotFoundException(String.format("Resource id %d not found", this.data));
        }
    }
}
