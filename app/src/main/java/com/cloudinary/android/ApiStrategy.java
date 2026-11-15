package com.cloudinary.android;

import com.cloudinary.Api;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.strategies.AbstractApiStrategy;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class ApiStrategy extends AbstractApiStrategy {
    @Override // com.cloudinary.strategies.AbstractApiStrategy
    public ApiResponse callApi(Api.HttpMethod method, Iterable<String> uri, Map<String, ?> params, Map options) throws Exception {
        throw new Exception("Administration API is not supported for mobile applications.");
    }

    @Override // com.cloudinary.strategies.AbstractApiStrategy
    public ApiResponse callAccountApi(Api.HttpMethod method, Iterable<String> uri, Map<String, ?> params, Map options) throws Exception {
        throw new Exception("Account API is not supported for mobile applications.");
    }
}
