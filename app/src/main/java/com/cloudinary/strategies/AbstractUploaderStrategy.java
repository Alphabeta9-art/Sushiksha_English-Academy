package com.cloudinary.strategies;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.ProgressCallback;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.google.logging.type.LogSeverity;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractUploaderStrategy {
    private static final int[] ERROR_CODES = {400, TypedValues.CycleType.TYPE_CURVE_FIT, TypedValues.CycleType.TYPE_ALPHA, 404, TypedValues.CycleType.TYPE_EASING, LogSeverity.ERROR_VALUE};
    protected Uploader uploader;

    public abstract Map callApi(String str, Map<String, Object> map, Map map2, Object obj, ProgressCallback progressCallback) throws IOException;

    public void init(Uploader uploader) {
        this.uploader = uploader;
    }

    public Cloudinary cloudinary() {
        return this.uploader.cloudinary();
    }

    public Map callApi(String action, Map<String, Object> params, Map options, Object file) throws IOException {
        return callApi(action, params, options, file, null);
    }

    protected String buildUploadUrl(String action, Map options) {
        String cloudinary = ObjectUtils.asString(options.get("upload_prefix"), ObjectUtils.asString(this.uploader.cloudinary().config.uploadPrefix, "https://api.cloudinary.com"));
        String cloud_name = ObjectUtils.asString(options.get("cloud_name"), ObjectUtils.asString(this.uploader.cloudinary().config.cloudName));
        if (cloud_name == null) {
            throw new IllegalArgumentException("Must supply cloud_name in tag or in configuration");
        }
        if (action.equals("delete_by_token")) {
            return StringUtils.join(new String[]{cloudinary, "v1_1", cloud_name, action}, DomExceptionUtils.SEPARATOR);
        }
        String resource_type = ObjectUtils.asString(options.get("resource_type"), "image");
        return StringUtils.join(new String[]{cloudinary, "v1_1", cloud_name, resource_type, action}, DomExceptionUtils.SEPARATOR);
    }

    protected Map processResponse(boolean returnError, int code, String responseData) {
        String errorMessage = null;
        Map<String, Object> map = null;
        if (code == 200 || includesServerResponse(code)) {
            try {
                JSONObject responseJSON = new JSONObject(responseData);
                map = ObjectUtils.toMap(responseJSON);
                if (map.containsKey("error")) {
                    Map error = (Map) map.get("error");
                    error.put("http_code", Integer.valueOf(code));
                    errorMessage = (String) error.get("message");
                }
            } catch (JSONException e) {
                errorMessage = "Invalid JSON response from server " + e.getMessage();
            }
        } else {
            errorMessage = "Server returned unexpected status code - " + code;
            if (StringUtils.isNotBlank(responseData)) {
                errorMessage = errorMessage + " - " + responseData;
            }
        }
        if (StringUtils.isNotBlank(errorMessage)) {
            if (returnError) {
                if (map == null) {
                    HashMap map2 = new HashMap();
                    map2.put("http_code", Integer.valueOf(code));
                    map2.put("message", errorMessage);
                    Map result = new HashMap();
                    result.put("error", map2);
                    return result;
                }
                return map;
            }
            throw new RuntimeException(errorMessage);
        }
        return map;
    }

    private boolean includesServerResponse(int code) {
        return Arrays.binarySearch(ERROR_CODES, code) >= 0;
    }

    protected boolean requiresSigning(String action, Map options) {
        boolean unsigned = Boolean.TRUE.equals(options.get("unsigned"));
        boolean deleteByToken = "delete_by_token".equals(action);
        return (unsigned || deleteByToken) ? false : true;
    }
}
