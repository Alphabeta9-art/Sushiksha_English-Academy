package com.cloudinary;

import com.cloudinary.utils.Base64Coder;

/* JADX INFO: loaded from: classes11.dex */
public class CustomFunction extends BaseParam {
    private CustomFunction(String... components) {
        super(components);
    }

    public static CustomFunction wasm(String publicId) {
        return new CustomFunction("wasm", publicId);
    }

    public static CustomFunction remote(String url) {
        return new CustomFunction("remote", Base64Coder.encodeURLSafeString(url));
    }
}
