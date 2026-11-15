package com.cloudinary;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.bumptech.glide.load.Key;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class Util {
    static final String[] BOOLEAN_UPLOAD_OPTIONS = {"backup", "exif", "faces", "colors", "image_metadata", "use_filename", "unique_filename", "eager_async", "invalidate", "discard_original_filename", "overwrite", "phash", "return_delete_token", "async", "quality_analysis", "cinemagraph_analysis", "accessibility_analysis", "use_filename_as_display_name", "use_asset_folder_as_public_id_prefix", "unique_display_name", "media_metadata", "visual_search"};

    public static final Map<String, Object> buildUploadParams(Map options) {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("public_id", (String) options.get("public_id"));
        params.put("callback", (String) options.get("callback"));
        params.put("format", (String) options.get("format"));
        params.put("type", (String) options.get("type"));
        for (String attr : BOOLEAN_UPLOAD_OPTIONS) {
            putBoolean(attr, options, params);
        }
        params.put("eval", (String) options.get("eval"));
        params.put("notification_url", (String) options.get("notification_url"));
        params.put("eager_notification_url", (String) options.get("eager_notification_url"));
        params.put("proxy", (String) options.get("proxy"));
        params.put("folder", (String) options.get("folder"));
        params.put("allowed_formats", StringUtils.join((List<String>) ObjectUtils.asArray(options.get("allowed_formats")), ","));
        params.put("moderation", options.get("moderation"));
        params.put("access_mode", (String) options.get("access_mode"));
        params.put("filename_override", (String) options.get("filename_override"));
        params.put("public_id_prefix", (String) options.get("public_id_prefix"));
        params.put("asset_folder", (String) options.get("asset_folder"));
        params.put("display_name", (String) options.get("display_name"));
        params.put("on_success", (String) options.get("on_success"));
        Object responsive_breakpoints = options.get("responsive_breakpoints");
        if (responsive_breakpoints != null) {
            params.put("responsive_breakpoints", JSONObject.wrap(responsive_breakpoints));
        }
        params.put("upload_preset", options.get("upload_preset"));
        if (options.get("signature") == null) {
            putEager("eager", options, params);
            Object transformation = options.get("transformation");
            if (transformation != null) {
                if (transformation instanceof Transformation) {
                    transformation = ((Transformation) transformation).generate();
                }
                params.put("transformation", transformation.toString());
            }
            processWriteParameters(options, params);
        } else {
            params.put("eager", (String) options.get("eager"));
            params.put("transformation", (String) options.get("transformation"));
            params.put("headers", (String) options.get("headers"));
            params.put("tags", (String) options.get("tags"));
            params.put("face_coordinates", (String) options.get("face_coordinates"));
            params.put("context", (String) options.get("context"));
            params.put("ocr", (String) options.get("ocr"));
            params.put("raw_convert", (String) options.get("raw_convert"));
            params.put("categorization", (String) options.get("categorization"));
            params.put("detection", (String) options.get("detection"));
            params.put("similarity_search", (String) options.get("similarity_search"));
            params.put("auto_tagging", (String) options.get("auto_tagging"));
            params.put("access_control", (String) options.get("access_control"));
        }
        return params;
    }

    public static Map buildMultiParams(Map options) {
        Map<String, Object> params = new HashMap<>();
        Object transformation = options.get("transformation");
        if (transformation != null) {
            if (transformation instanceof Transformation) {
                transformation = ((Transformation) transformation).generate();
            }
            params.put("transformation", transformation.toString());
        }
        params.put("tag", options.get("tag"));
        if (options.containsKey("urls")) {
            params.put("urls", Arrays.asList((String[]) options.get("urls")));
        }
        params.put("notification_url", (String) options.get("notification_url"));
        params.put("format", (String) options.get("format"));
        params.put("async", ObjectUtils.asBoolean(options.get("async"), false).toString());
        params.put("mode", options.get("mode"));
        putObject("timestamp", options, params, timestamp());
        return params;
    }

    public static Map<String, Object> buildGenerateSpriteParams(Map options) {
        Transformation transformation;
        HashMap<String, Object> params = new HashMap<>();
        Object transParam = options.get("transformation");
        if (transParam instanceof Transformation) {
            transformation = new Transformation((Transformation) transParam);
        } else if (transParam instanceof String) {
            transformation = new Transformation().rawTransformation((String) transParam);
        } else {
            transformation = new Transformation();
        }
        String format = (String) options.get("format");
        if (format != null) {
            transformation.fetchFormat(format);
        }
        params.put("transformation", transformation.generate());
        params.put("tag", options.get("tag"));
        if (options.containsKey("urls")) {
            params.put("urls", Arrays.asList((String[]) options.get("urls")));
        }
        params.put("notification_url", (String) options.get("notification_url"));
        params.put("async", ObjectUtils.asBoolean(options.get("async"), false).toString());
        params.put("mode", options.get("mode"));
        putObject("timestamp", options, params, timestamp());
        return params;
    }

    protected static final String buildEager(List<? extends Transformation> transformations) {
        if (transformations == null) {
            return null;
        }
        List<String> eager = new ArrayList<>();
        for (Transformation transformation : transformations) {
            String transformationString = transformation.generate();
            if (StringUtils.isNotBlank(transformationString)) {
                eager.add(transformationString);
            }
        }
        return StringUtils.join(eager, "|");
    }

    public static final void processWriteParameters(Map<String, Object> options, Map<String, Object> params) {
        if (options.get("headers") != null) {
            params.put("headers", buildCustomHeaders(options.get("headers")));
        }
        if (options.get("tags") != null) {
            params.put("tags", StringUtils.join((List<String>) ObjectUtils.asArray(options.get("tags")), ","));
        }
        if (options.get("face_coordinates") != null) {
            params.put("face_coordinates", Coordinates.parseCoordinates(options.get("face_coordinates")).toString());
        }
        if (options.get("custom_coordinates") != null) {
            params.put("custom_coordinates", Coordinates.parseCoordinates(options.get("custom_coordinates")).toString());
        }
        if (options.get("context") != null) {
            params.put("context", encodeContext(options.get("context")));
        }
        if (options.get(TtmlNode.TAG_METADATA) != null) {
            params.put(TtmlNode.TAG_METADATA, encodeContext(options.get(TtmlNode.TAG_METADATA)));
        }
        if (options.get("access_control") != null) {
            params.put("access_control", encodeAccessControl(options.get("access_control")));
        }
        if (options.get("asset_folder") != null) {
            params.put("asset_folder", options.get("asset_folder"));
        }
        if (options.get("unique_display_name") != null) {
            params.put("unique_display_name", options.get("unique_display_name"));
        }
        if (options.get("display_name") != null) {
            params.put("display_name", options.get("display_name"));
        }
        putObject("ocr", options, params);
        putObject("raw_convert", options, params);
        putObject("categorization", options, params);
        putObject("detection", options, params);
        putObject("similarity_search", options, params);
        putObject("background_removal", options, params);
        if (options.get("auto_tagging") != null) {
            params.put("auto_tagging", ObjectUtils.asFloat(options.get("auto_tagging")));
        }
        if (options.get("clear_invalid") != null) {
            params.put("clear_invalid", options.get("clear_invalid"));
        }
        if (options.get("visual_search") != null) {
            params.put("visual_search", options.get("visual_search"));
        }
    }

    protected static String encodeAccessControl(Object accessControl) {
        if (accessControl instanceof AccessControlRule) {
            accessControl = Arrays.asList(accessControl);
        }
        return JSONObject.wrap(accessControl).toString();
    }

    protected static String encodeContext(Object context) {
        String value;
        if (context instanceof Map) {
            Map<String, Object> mapArg = (Map) context;
            HashSet out = new HashSet();
            for (Map.Entry<String, Object> entry : mapArg.entrySet()) {
                if (entry.getValue() instanceof List) {
                    value = encodeList(((List) entry.getValue()).toArray());
                } else if (entry.getValue() instanceof String[]) {
                    value = encodeList((String[]) entry.getValue());
                } else {
                    value = entry.getValue().toString();
                }
                out.add(entry.getKey() + "=" + encodeSingleContextString(value));
            }
            return StringUtils.join(out.toArray(), "|");
        }
        if (context == null) {
            return null;
        }
        return context.toString();
    }

    private static String encodeList(Object[] list) {
        StringBuilder builder = new StringBuilder("[");
        boolean first = true;
        for (Object s : list) {
            if (!first) {
                builder.append(",");
            }
            builder.append("\"").append(encodeSingleContextString(s.toString())).append("\"");
            first = false;
        }
        return builder.append("]").toString();
    }

    private static String encodeSingleContextString(String value) {
        return value.replaceAll("([=\\|])", "\\\\$1");
    }

    protected static final String buildCustomHeaders(Object headers) {
        if (headers == null) {
            return null;
        }
        if (headers instanceof String) {
            return (String) headers;
        }
        if (headers instanceof Object[]) {
            return StringUtils.join((Object[]) headers, "\n") + "\n";
        }
        Map<String, String> headersMap = (Map) headers;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> header : headersMap.entrySet()) {
            builder.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
        }
        return builder.toString();
    }

    public static void clearEmpty(Map params) {
        Iterator iterator = params.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            if (value == null || "".equals(value)) {
                iterator.remove();
            }
        }
    }

    public static final Map<String, Object> buildArchiveParams(Map options, String targetFormat) {
        Map<String, Object> params = new HashMap<>();
        if (options != null && options.size() > 0) {
            params.put("type", options.get("type"));
            params.put("mode", options.get("mode"));
            params.put("target_format", targetFormat);
            params.put("target_public_id", options.get("target_public_id"));
            putBoolean("flatten_folders", options, params);
            putBoolean("flatten_transformations", options, params);
            putBoolean("use_original_filename", options, params);
            putBoolean("async", options, params);
            putBoolean("keep_derived", options, params);
            params.put("notification_url", options.get("notification_url"));
            putArray("target_tags", options, params);
            putArray("tags", options, params);
            putArray("public_ids", options, params);
            putArray("fully_qualified_public_ids", options, params);
            putArray("prefixes", options, params);
            putEager("transformations", options, params);
            putObject("timestamp", options, params, timestamp());
            putBoolean("skip_transformation_name", options, params);
            putBoolean("allow_missing", options, params);
            putObject("expires_at", options, params);
        }
        return params;
    }

    private static void putEager(String name, Map from, Map<String, Object> to) {
        Object transformations = from.get(name);
        if (transformations != null) {
            to.put(name, buildEager((List) transformations));
        }
    }

    private static void putBoolean(String name, Map from, Map<String, Object> to) {
        Object value = from.get(name);
        if (value != null) {
            to.put(name, ObjectUtils.asBoolean(value));
        }
    }

    private static void putObject(String name, Map from, Map<String, Object> to) {
        putObject(name, from, to, null);
    }

    private static void putObject(String name, Map from, Map<String, Object> to, Object defaultValue) {
        Object value = from.get(name);
        if (value != null) {
            to.put(name, value);
        } else if (defaultValue != null) {
            to.put(name, defaultValue);
        }
    }

    private static void putArray(String name, Map from, Map<String, Object> to) {
        Object value = from.get(name);
        if (value != null) {
            to.put(name, ObjectUtils.asArray(value));
        }
    }

    protected static String timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static byte[] getUTF8Bytes(String string) {
        try {
            return string.getBytes(Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }

    public static String produceSignature(Map<String, Object> paramsToSign, String apiSecret) {
        return produceSignature(paramsToSign, apiSecret, SignatureAlgorithm.SHA1);
    }

    public static String produceSignature(Map<String, Object> paramsToSign, String apiSecret, SignatureAlgorithm signatureAlgorithm) {
        Collection<String> params = new ArrayList<>();
        for (Map.Entry<String, Object> param : new TreeMap(paramsToSign).entrySet()) {
            if (param.getValue() instanceof Collection) {
                params.add(param.getKey() + "=" + StringUtils.join((Collection<String>) param.getValue(), ","));
            } else if (param.getValue() instanceof Object[]) {
                params.add(param.getKey() + "=" + StringUtils.join((Object[]) param.getValue(), ","));
            } else if (StringUtils.isNotBlank(param.getValue())) {
                params.add(param.getKey() + "=" + param.getValue().toString());
            }
        }
        String to_sign = StringUtils.join(params, "&");
        byte[] hash = hash(to_sign + apiSecret, signatureAlgorithm);
        return StringUtils.encodeHexString(hash);
    }

    public static byte[] hash(String input, SignatureAlgorithm signatureAlgorithm) {
        try {
            return MessageDigest.getInstance(signatureAlgorithm.getAlgorithmId()).digest(getUTF8Bytes(input));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }
}
