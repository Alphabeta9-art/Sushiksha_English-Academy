package com.cloudinary.android;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.bumptech.glide.load.Key;
import com.cloudinary.ProgressCallback;
import com.cloudinary.strategies.AbstractUploaderStrategy;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.facebook.common.util.UriUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import okhttp3.HttpUrl;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class UploaderStrategy extends AbstractUploaderStrategy {
    protected static String readFully(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int length = in.read(buffer);
            if (length != -1) {
                baos.write(buffer, 0, length);
            } else {
                return new String(baos.toByteArray());
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0256 A[Catch: JSONException -> 0x0273, TryCatch #4 {JSONException -> 0x0273, blocks: (B:97:0x0245, B:99:0x0250, B:101:0x0256, B:105:0x026e, B:102:0x025e, B:103:0x026b), top: B:120:0x0245 }] */
    /* JADX WARN: Code duplicated, block: B:102:0x025e A[Catch: JSONException -> 0x0273, TryCatch #4 {JSONException -> 0x0273, blocks: (B:97:0x0245, B:99:0x0250, B:101:0x0256, B:105:0x026e, B:102:0x025e, B:103:0x026b), top: B:120:0x0245 }] */
    /* JADX WARN: Code duplicated, block: B:104:0x026c  */
    /* JADX WARN: Code duplicated, block: B:113:0x0297  */
    /* JADX WARN: Code duplicated, block: B:86:0x0204  */
    /* JADX WARN: Code duplicated, block: B:87:0x0209  */
    /* JADX WARN: Code duplicated, block: B:99:0x0250 A[Catch: JSONException -> 0x0273, TryCatch #4 {JSONException -> 0x0273, blocks: (B:97:0x0245, B:99:0x0250, B:101:0x0256, B:105:0x026e, B:102:0x025e, B:103:0x026b), top: B:120:0x0245 }] */
    @Override // com.cloudinary.strategies.AbstractUploaderStrategy
    public Map callApi(String action, Map<String, Object> params, Map options, Object file, final ProgressCallback progressCallback) throws IOException {
        MultipartUtility.MultipartCallback multipartCallback;
        int code;
        InputStream responseStream;
        String responseData;
        JSONObject result;
        JSONObject error;
        Iterator<Map.Entry<String, Object>> it;
        Object file2 = file;
        Map options2 = options == null ? ObjectUtils.emptyMap() : options;
        boolean returnError = ObjectUtils.asBoolean(options2.get("return_error"), false).booleanValue();
        if (requiresSigning(action, options2)) {
            String apiKey = ObjectUtils.asString(options2.get("api_key"), cloudinary().config.apiKey);
            if (apiKey == null) {
                throw new IllegalArgumentException("Must supply api_key");
            }
            if (options2.containsKey("signature") && options2.containsKey("timestamp")) {
                params.put("timestamp", options2.get("timestamp"));
                params.put("signature", options2.get("signature"));
                params.put("api_key", apiKey);
            } else {
                String apiSecret = ObjectUtils.asString(options2.get("api_secret"), cloudinary().config.apiSecret);
                if (apiSecret == null) {
                    throw new IllegalArgumentException("Must supply api_secret");
                }
                params.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000).toString());
                params.put("signature", cloudinary().apiSignRequest(params, apiSecret));
                params.put("api_key", apiKey);
            }
        }
        String apiUrl = buildUploadUrl(action, options2);
        if (progressCallback == null) {
            multipartCallback = null;
        } else {
            final long totalBytes = determineLength(file2);
            multipartCallback = new MultipartUtility.MultipartCallback() { // from class: com.cloudinary.android.UploaderStrategy.1
                @Override // com.cloudinary.android.MultipartUtility.MultipartCallback
                public void totalBytesLoaded(long bytes) {
                    progressCallback.onProgress(bytes, totalBytes);
                }
            };
        }
        MultipartUtility multipart = null;
        HttpURLConnection connection = null;
        try {
            try {
                int connectTimeout = options2.get("connect_timeout") != null ? ((Integer) options2.get("connect_timeout")).intValue() : 0;
                int readTimeout = options2.get("read_timeout") != null ? ((Integer) options2.get("read_timeout")).intValue() : 0;
                multipart = new MultipartUtility(apiUrl, Key.STRING_CHARSET_NAME, cloudinary().randomPublicId(), (Map) options2.get("extra_headers"), multipartCallback, connectTimeout, readTimeout);
                Iterator<Map.Entry<String, Object>> it2 = params.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<String, Object> param = it2.next();
                    if (param.getValue() instanceof Collection) {
                        for (Object value : (Collection) param.getValue()) {
                            multipart.addFormField(param.getKey() + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, ObjectUtils.asString(value));
                            it2 = it2;
                        }
                        it = it2;
                    } else {
                        it = it2;
                        if (StringUtils.isNotBlank(param.getValue())) {
                            multipart.addFormField(param.getKey(), param.getValue().toString());
                        }
                    }
                    it2 = it;
                }
                if ((file2 instanceof String) && !StringUtils.isRemoteUrl((String) file2)) {
                    file2 = new File((String) file2);
                }
                try {
                    String filename = (String) options2.get("filename");
                    if (file2 instanceof File) {
                        multipart.addFilePart(UriUtil.LOCAL_FILE_SCHEME, (File) file2, filename);
                    } else if (file2 instanceof String) {
                        multipart.addFormField(UriUtil.LOCAL_FILE_SCHEME, (String) file2);
                    } else if (file2 instanceof InputStream) {
                        multipart.addFilePart(UriUtil.LOCAL_FILE_SCHEME, (InputStream) file2, filename);
                    } else if (file2 instanceof byte[]) {
                        multipart.addFilePart(UriUtil.LOCAL_FILE_SCHEME, new ByteArrayInputStream((byte[]) file2), filename);
                    }
                    connection = multipart.execute();
                } catch (InterruptedIOException e) {
                    e = e;
                    e.printStackTrace();
                    if (multipart != null) {
                    }
                    int code2 = connection.getResponseCode();
                    code = code2;
                    if (code >= 400) {
                        responseStream = connection.getErrorStream();
                    } else {
                        responseStream = connection.getInputStream();
                    }
                    responseData = readFully(responseStream);
                    connection.disconnect();
                    if (code == 200) {
                    }
                    try {
                        result = new JSONObject(responseData);
                        if (result.has("error")) {
                            error = result.getJSONObject("error");
                            if (returnError) {
                                throw new RuntimeException(error.getString("message"));
                            }
                            error.put("http_code", code);
                        }
                        return ObjectUtils.toMap(result);
                    } catch (JSONException e2) {
                        throw new RuntimeException("Invalid JSON response from server " + e2.getMessage());
                    }
                }
            } catch (InterruptedIOException e3) {
                e = e3;
            } catch (Throwable th) {
                e = th;
                if (multipart != null) {
                    multipart.close();
                }
                throw e;
            }
            multipart.close();
            try {
                int code3 = connection.getResponseCode();
                code = code3;
            } catch (IOException e4) {
                if (!e4.getMessage().equals("No authentication challenges found")) {
                    throw e4;
                }
                code = TypedValues.CycleType.TYPE_CURVE_FIT;
            }
            if (code >= 400) {
                responseStream = connection.getErrorStream();
            } else {
                responseStream = connection.getInputStream();
            }
            responseData = readFully(responseStream);
            connection.disconnect();
            if (code == 200 && code != 400 && code != 404 && code != 500) {
                throw new RuntimeException("Server returned unexpected status code - " + code + " - " + responseData);
            }
            result = new JSONObject(responseData);
            if (result.has("error")) {
                error = result.getJSONObject("error");
                if (returnError) {
                    throw new RuntimeException(error.getString("message"));
                }
                error.put("http_code", code);
            }
            return ObjectUtils.toMap(result);
        } catch (Throwable th2) {
            e = th2;
            if (multipart != null) {
                multipart.close();
            }
            throw e;
        }
    }

    private long determineLength(Object file) {
        if (file == null) {
            return -1L;
        }
        if (file instanceof File) {
            long actualLength = ((File) file).length();
            return actualLength;
        }
        if (file instanceof byte[]) {
            long actualLength2 = ((byte[]) file).length;
            return actualLength2;
        }
        if (file instanceof InputStream) {
            return -1L;
        }
        File f = new File(file.toString());
        long actualLength3 = f.length();
        return actualLength3;
    }
}
