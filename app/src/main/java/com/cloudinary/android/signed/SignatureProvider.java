package com.cloudinary.android.signed;

import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public interface SignatureProvider {
    String getName();

    Signature provideSignature(Map map);
}
