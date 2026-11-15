package com.cloudinary.api;

import java.text.ParseException;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public interface ApiResponse extends Map {
    RateLimit apiRateLimit() throws ParseException;

    Map<String, RateLimit> rateLimits() throws ParseException;
}
