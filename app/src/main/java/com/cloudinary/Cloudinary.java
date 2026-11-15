package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.bumptech.glide.load.Key;
import com.cloudinary.api.signing.ApiResponseSignatureVerifier;
import com.cloudinary.api.signing.NotificationRequestSignatureVerifier;
import com.cloudinary.strategies.AbstractApiStrategy;
import com.cloudinary.strategies.AbstractUploaderStrategy;
import com.cloudinary.strategies.StrategyLoader;
import com.cloudinary.utils.Analytics;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class Cloudinary {
    public static final String AKAMAI_SHARED_CDN = "res.cloudinary.com";
    public static final String CF_SHARED_CDN = "d3jpl91pxevbkh.cloudfront.net";
    public static final String OLD_AKAMAI_SHARED_CDN = "cloudinary-a.akamaihd.net";
    public static final String SHARED_CDN = "res.cloudinary.com";
    public static final String VERSION = "1.38.0";
    private AbstractApiStrategy apiStrategy;
    public final Configuration config;
    private AbstractUploaderStrategy uploaderStrategy;
    private static List<String> UPLOAD_STRATEGIES = new ArrayList(Arrays.asList("com.cloudinary.android.UploaderStrategy", "com.cloudinary.http42.UploaderStrategy", "com.cloudinary.http43.UploaderStrategy", "com.cloudinary.http44.UploaderStrategy", "com.cloudinary.http45.UploaderStrategy"));
    public static List<String> API_STRATEGIES = new ArrayList(Arrays.asList("com.cloudinary.android.ApiStrategy", "com.cloudinary.http42.ApiStrategy", "com.cloudinary.http43.ApiStrategy", "com.cloudinary.http44.ApiStrategy", "com.cloudinary.http45.ApiStrategy"));
    static String USER_AGENT_PREFIX = "CloudinaryJava";
    public static final String USER_AGENT_JAVA_VERSION = "(Java " + System.getProperty("java.version") + ")";
    private static final SecureRandom RND = new SecureRandom();
    private String userAgent = USER_AGENT_PREFIX + DomExceptionUtils.SEPARATOR + VERSION + " " + USER_AGENT_JAVA_VERSION;
    public Analytics analytics = new Analytics();

    public Uploader uploader() {
        return new Uploader(this, this.uploaderStrategy);
    }

    public Api api() {
        return new Api(this, this.apiStrategy);
    }

    public Search search() {
        return new Search(this);
    }

    public SearchFolders searchFolders() {
        return new SearchFolders(this);
    }

    public static void registerUploaderStrategy(String className) {
        if (!UPLOAD_STRATEGIES.contains(className)) {
            UPLOAD_STRATEGIES.add(className);
        }
    }

    public static void registerAPIStrategy(String className) {
        if (!API_STRATEGIES.contains(className)) {
            API_STRATEGIES.add(className);
        }
    }

    private void loadStrategies() {
        if (this.config.loadStrategies) {
            this.uploaderStrategy = (AbstractUploaderStrategy) StrategyLoader.find(UPLOAD_STRATEGIES);
            if (this.uploaderStrategy == null) {
                throw new UnknownError("Can't find Cloudinary platform adapter [" + StringUtils.join(UPLOAD_STRATEGIES, ",") + "]");
            }
            this.apiStrategy = (AbstractApiStrategy) StrategyLoader.find(API_STRATEGIES);
            if (this.apiStrategy == null) {
                throw new UnknownError("Can't find Cloudinary platform adapter [" + StringUtils.join(API_STRATEGIES, ",") + "]");
            }
        }
    }

    public Cloudinary(Map config) {
        this.config = new Configuration(config);
        loadStrategies();
    }

    public Cloudinary(String cloudinaryUrl) {
        this.config = Configuration.from(cloudinaryUrl);
        loadStrategies();
    }

    public Cloudinary() {
        String cloudinaryUrl = System.getProperty("CLOUDINARY_URL", System.getenv("CLOUDINARY_URL"));
        if (cloudinaryUrl != null) {
            this.config = Configuration.from(cloudinaryUrl);
        } else {
            this.config = new Configuration();
        }
        loadStrategies();
    }

    public Url url() {
        return new Url(this);
    }

    public String cloudinaryApiUrl(String action, Map options) {
        String cloudinary = ObjectUtils.asString(options.get("upload_prefix"), ObjectUtils.asString(this.config.uploadPrefix, "https://api.cloudinary.com"));
        String cloud_name = ObjectUtils.asString(options.get("cloud_name"), ObjectUtils.asString(this.config.cloudName));
        if (cloud_name == null) {
            throw new IllegalArgumentException("Must supply cloud_name in tag or in configuration");
        }
        String resource_type = ObjectUtils.asString(options.get("resource_type"), "image");
        return StringUtils.join(new String[]{cloudinary, "v1_1", cloud_name, resource_type, action}, DomExceptionUtils.SEPARATOR);
    }

    public String randomPublicId() {
        byte[] bytes = new byte[8];
        RND.nextBytes(bytes);
        return StringUtils.encodeHexString(bytes);
    }

    public String signedPreloadedImage(Map result) {
        return result.get("resource_type") + "/upload/v" + result.get("version") + DomExceptionUtils.SEPARATOR + result.get("public_id") + (result.containsKey("format") ? "." + result.get("format") : "") + "#" + result.get("signature");
    }

    public String apiSignRequest(Map<String, Object> paramsToSign, String apiSecret) {
        return Util.produceSignature(paramsToSign, apiSecret, this.config.signatureAlgorithm);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String prefix, String version) {
        this.userAgent = prefix + DomExceptionUtils.SEPARATOR + version + " (" + USER_AGENT_PREFIX + " " + VERSION + ") " + USER_AGENT_JAVA_VERSION;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public boolean verifyNotificationSignature(String body, String timestamp, String signature, long validFor) {
        return new NotificationRequestSignatureVerifier(this.config.apiSecret, this.config.signatureAlgorithm).verifySignature(body, timestamp, signature, validFor);
    }

    public boolean verifyApiResponseSignature(String publicId, String version, String signature) {
        return new ApiResponseSignatureVerifier(this.config.apiSecret, this.config.signatureAlgorithm).verifySignature(publicId, version, signature);
    }

    public void signRequest(Map<String, Object> params, Map<String, Object> options) {
        String apiKey = ObjectUtils.asString(options.get("api_key"), this.config.apiKey);
        if (apiKey == null) {
            throw new IllegalArgumentException("Must supply api_key");
        }
        String apiSecret = ObjectUtils.asString(options.get("api_secret"), this.config.apiSecret);
        if (apiSecret == null) {
            throw new IllegalArgumentException("Must supply api_secret");
        }
        Util.clearEmpty(params);
        params.put("signature", apiSignRequest(params, apiSecret));
        params.put("api_key", apiKey);
    }

    public String privateDownload(String publicId, String format, Map<String, Object> options) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("public_id", publicId);
        params.put("format", format);
        params.put("attachment", options.get("attachment"));
        params.put("type", options.get("type"));
        params.put("expires_at", options.get("expires_at"));
        params.put("timestamp", Util.timestamp());
        signRequest(params, options);
        return buildUrl(cloudinaryApiUrl(ArchiveParams.MODE_DOWNLOAD, options), params);
    }

    public String zipDownload(String tag, Map<String, Object> options) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("timestamp", Util.timestamp());
        params.put("tag", tag);
        Object transformation = options.get("transformation");
        if (transformation != null) {
            if (transformation instanceof Transformation) {
                transformation = ((Transformation) transformation).generate();
            }
            params.put("transformation", transformation.toString());
        }
        params.put("transformation", transformation);
        signRequest(params, options);
        return buildUrl(cloudinaryApiUrl("download_tag.zip", options), params);
    }

    public String downloadArchive(Map<String, Object> options, String targetFormat) throws UnsupportedEncodingException {
        Map<String, Object> mapBuildArchiveParams = Util.buildArchiveParams(options, targetFormat);
        mapBuildArchiveParams.put("mode", ArchiveParams.MODE_DOWNLOAD);
        signRequest(mapBuildArchiveParams, options);
        return buildUrl(cloudinaryApiUrl("generate_archive", options), mapBuildArchiveParams);
    }

    public String downloadArchive(ArchiveParams params) throws UnsupportedEncodingException {
        return downloadArchive(params.toMap(), params.targetFormat());
    }

    public String downloadZip(Map<String, Object> options) throws UnsupportedEncodingException {
        return downloadArchive(options, ArchiveParams.FORMAT_ZIP);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String downloadGeneratedSprite(String tag, Map options) throws IOException {
        if (StringUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        if (options == null) {
            options = new HashMap();
        }
        options.put("tag", tag);
        options.put("mode", ArchiveParams.MODE_DOWNLOAD);
        Map<String, Object> mapBuildGenerateSpriteParams = Util.buildGenerateSpriteParams(options);
        signRequest(mapBuildGenerateSpriteParams, options);
        return buildUrl(cloudinaryApiUrl("sprite", options), mapBuildGenerateSpriteParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String downloadGeneratedSprite(String[] urls, Map options) throws IOException {
        if (urls.length < 1) {
            throw new IllegalArgumentException("Request must contain at least one URL.");
        }
        if (options == null) {
            options = new HashMap();
        }
        options.put("urls", urls);
        options.put("mode", ArchiveParams.MODE_DOWNLOAD);
        Map<String, Object> mapBuildGenerateSpriteParams = Util.buildGenerateSpriteParams(options);
        signRequest(mapBuildGenerateSpriteParams, options);
        return buildUrl(cloudinaryApiUrl("sprite", options), mapBuildGenerateSpriteParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String downloadMulti(String tag, Map options) throws IOException {
        if (StringUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        if (options == null) {
            options = new HashMap();
        }
        options.put("tag", tag);
        options.put("mode", ArchiveParams.MODE_DOWNLOAD);
        Map params = Util.buildMultiParams(options);
        signRequest(params, options);
        return buildUrl(cloudinaryApiUrl("multi", options), params);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String downloadMulti(String[] urls, Map options) throws IOException {
        if (urls.length < 1) {
            throw new IllegalArgumentException("Request must contain at least one URL.");
        }
        if (options == null) {
            options = new HashMap();
        }
        options.put("urls", urls);
        options.put("mode", ArchiveParams.MODE_DOWNLOAD);
        Map params = Util.buildMultiParams(options);
        signRequest(params, options);
        return buildUrl(cloudinaryApiUrl("multi", options), params);
    }

    public String downloadFolder(String folderPath, Map options) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(folderPath)) {
            throw new IllegalArgumentException("Folder path parameter value is required");
        }
        Map adjustedOptions = new HashMap();
        if (options != null) {
            adjustedOptions.putAll(options);
        }
        adjustedOptions.put("prefixes", folderPath);
        Object resourceType = adjustedOptions.get("resource_type");
        adjustedOptions.put("resource_type", resourceType != null ? resourceType : TtmlNode.COMBINE_ALL);
        return downloadArchive(adjustedOptions, (String) adjustedOptions.get("target_format"));
    }

    public String downloadBackedupAsset(String assetId, String versionId, Map options) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(assetId)) {
            throw new IllegalArgumentException("AssetId parameter is required");
        }
        if (StringUtils.isEmpty(versionId)) {
            throw new IllegalArgumentException("VersionId parameter is required");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("asset_id", assetId);
        params.put("version_id", versionId);
        params.put("timestamp", Util.timestamp());
        signRequest(params, options);
        return buildUrl(cloudinaryApiUrl("download_backup", options), params);
    }

    private String buildUrl(String base, Map<String, Object> params) throws UnsupportedEncodingException {
        String keyValue;
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(base);
        if (!params.isEmpty()) {
            urlBuilder.append("?");
        }
        boolean first = true;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (param.getValue() != null) {
                Object value = param.getValue();
                if (!first) {
                    urlBuilder.append("&");
                }
                if (value instanceof Object[]) {
                    value = Arrays.asList(value);
                }
                if (value instanceof Collection) {
                    String key = param.getKey() + "[]=";
                    Collection<Object> items = (Collection) value;
                    List<String> encodedItems = new ArrayList<>();
                    for (Object item : items) {
                        encodedItems.add(URLEncoder.encode(item.toString(), Key.STRING_CHARSET_NAME));
                    }
                    keyValue = key + StringUtils.join(encodedItems, "&" + key);
                } else {
                    keyValue = param.getKey() + "=" + URLEncoder.encode(value.toString(), Key.STRING_CHARSET_NAME);
                }
                urlBuilder.append(keyValue);
                first = false;
            }
        }
        return urlBuilder.toString();
    }

    @Deprecated
    public static Map asMap(Object... values) {
        return ObjectUtils.asMap(values);
    }
}
