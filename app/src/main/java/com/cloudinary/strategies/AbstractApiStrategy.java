package com.cloudinary.strategies;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.cloudinary.Api;
import com.cloudinary.SmartUrlEncoder;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.Base64Coder;
import com.cloudinary.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractApiStrategy {
    protected Api api;

    public abstract ApiResponse callAccountApi(Api.HttpMethod httpMethod, Iterable<String> iterable, Map<String, ? extends Object> map, Map map2) throws Exception;

    public abstract ApiResponse callApi(Api.HttpMethod httpMethod, Iterable<String> iterable, Map<String, ? extends Object> map, Map map2) throws Exception;

    public void init(Api api) {
        this.api = api;
    }

    protected String createApiUrl(Iterable<String> uri, String prefix, String cloudName) {
        String apiUrl = StringUtils.join((List<String>) Arrays.asList(prefix, "v1_1", cloudName), DomExceptionUtils.SEPARATOR);
        for (String component : uri) {
            apiUrl = apiUrl + DomExceptionUtils.SEPARATOR + SmartUrlEncoder.encode(component);
        }
        return apiUrl;
    }

    protected String getAuthorizationHeaderValue(String apiKey, String apiSecret, String oauthToken) {
        if (oauthToken != null) {
            return "Bearer " + oauthToken;
        }
        return "Basic " + Base64Coder.encodeString(apiKey + ":" + apiSecret);
    }

    protected void validateAuthorization(String apiKey, String apiSecret, String oauthToken) {
        if (oauthToken == null) {
            if (apiKey == null) {
                throw new IllegalArgumentException("Must supply api_key");
            }
            if (apiSecret == null) {
                throw new IllegalArgumentException("Must supply api_secret");
            }
        }
    }
}
