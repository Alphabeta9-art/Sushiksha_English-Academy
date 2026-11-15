package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes11.dex */
public class SmartUrlEncoder {
    public static String encode(String input) {
        try {
            return URLEncoder.encode(input, Key.STRING_CHARSET_NAME).replace("%2F", DomExceptionUtils.SEPARATOR).replace("%3A", ":").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
