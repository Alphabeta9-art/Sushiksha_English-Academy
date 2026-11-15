package com.cloudinary;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class Configuration {
    public static final String AKAMAI_SHARED_CDN = "res.cloudinary.com";
    public static final String CF_SHARED_CDN = "d3jpl91pxevbkh.cloudfront.net";
    private static final String CONFIG_PROP_SIGNATURE_ALGORITHM = "signature_algorithm";
    public static final boolean DEFAULT_IS_LONG_SIGNATURE = false;
    public static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.SHA1;
    public static final String OLD_AKAMAI_SHARED_CDN = "cloudinary-a.akamaihd.net";
    public static final String SHARED_CDN = "res.cloudinary.com";
    public static final String USER_AGENT = "cld-android-1.0.2";
    public static final String VERSION = "1.0.2";
    public Boolean analytics;
    public String apiKey;
    public String apiSecret;
    public AuthToken authToken;
    public String callback;
    public boolean cdnSubdomain;
    public boolean clientHints;
    public String cloudName;
    public String cname;
    public boolean forceVersion;
    public boolean loadStrategies;
    public boolean longUrlSignature;
    public String oauthToken;
    public boolean privateCdn;
    public Map<String, Object> properties;
    public String proxyHost;
    public int proxyPort;
    public boolean secure;
    public Boolean secureCdnSubdomain;
    public String secureDistribution;
    public boolean shorten;
    public SignatureAlgorithm signatureAlgorithm;
    public int timeout;
    public String uploadPrefix;
    public boolean useFetchFormat;
    public boolean useRootPath;

    public Configuration() {
        this.properties = new HashMap();
        this.loadStrategies = true;
        this.clientHints = false;
        this.forceVersion = true;
        this.longUrlSignature = false;
        this.signatureAlgorithm = DEFAULT_SIGNATURE_ALGORITHM;
        this.oauthToken = null;
    }

    private Configuration(String cloudName, String apiKey, String apiSecret, String secureDistribution, String cname, String uploadPrefix, boolean secure, boolean privateCdn, boolean cdnSubdomain, boolean shorten, String callback, String proxyHost, int proxyPort, Boolean secureCdnSubdomain, boolean useRootPath, boolean useFetchFormat, int timeout, boolean loadStrategies, boolean forceVersion, boolean longUrlSignature, SignatureAlgorithm signatureAlgorithm, String oauthToken, boolean analytics) {
        this.properties = new HashMap();
        this.loadStrategies = true;
        this.clientHints = false;
        this.forceVersion = true;
        this.longUrlSignature = false;
        this.signatureAlgorithm = DEFAULT_SIGNATURE_ALGORITHM;
        this.oauthToken = null;
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.secureDistribution = secureDistribution;
        this.cname = cname;
        this.uploadPrefix = uploadPrefix;
        this.secure = secure;
        this.privateCdn = privateCdn;
        this.cdnSubdomain = cdnSubdomain;
        this.shorten = shorten;
        this.callback = callback;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.secureCdnSubdomain = secureCdnSubdomain;
        this.useRootPath = useRootPath;
        this.useFetchFormat = useFetchFormat;
        this.timeout = timeout;
        this.loadStrategies = loadStrategies;
        this.forceVersion = forceVersion;
        this.longUrlSignature = longUrlSignature;
        this.signatureAlgorithm = signatureAlgorithm;
        this.oauthToken = oauthToken;
        this.analytics = Boolean.valueOf(analytics);
    }

    public Configuration(Map config) {
        this.properties = new HashMap();
        this.loadStrategies = true;
        this.clientHints = false;
        this.forceVersion = true;
        this.longUrlSignature = false;
        this.signatureAlgorithm = DEFAULT_SIGNATURE_ALGORITHM;
        this.oauthToken = null;
        update(config);
    }

    public void update(Map config) {
        this.cloudName = (String) config.get("cloud_name");
        this.apiKey = (String) config.get("api_key");
        this.apiSecret = (String) config.get("api_secret");
        this.secureDistribution = (String) config.get("secure_distribution");
        this.cname = (String) config.get("cname");
        this.secure = ObjectUtils.asBoolean(config.get("secure"), false).booleanValue();
        this.privateCdn = ObjectUtils.asBoolean(config.get("private_cdn"), false).booleanValue();
        this.cdnSubdomain = ObjectUtils.asBoolean(config.get("cdn_subdomain"), false).booleanValue();
        this.shorten = ObjectUtils.asBoolean(config.get("shorten"), false).booleanValue();
        this.uploadPrefix = (String) config.get("upload_prefix");
        this.callback = (String) config.get("callback");
        this.proxyHost = (String) config.get("proxy_host");
        this.proxyPort = ObjectUtils.asInteger(config.get("proxy_port"), 0).intValue();
        this.secureCdnSubdomain = ObjectUtils.asBoolean(config.get("secure_cdn_subdomain"), null);
        this.useRootPath = ObjectUtils.asBoolean(config.get("use_root_path"), false).booleanValue();
        this.useFetchFormat = ObjectUtils.asBoolean(config.get("use_fetch_format"), false).booleanValue();
        this.loadStrategies = ObjectUtils.asBoolean(config.get("load_strategies"), true).booleanValue();
        this.timeout = ObjectUtils.asInteger(config.get("timeout"), 0).intValue();
        this.clientHints = ObjectUtils.asBoolean(config.get("client_hints"), false).booleanValue();
        this.analytics = ObjectUtils.asBoolean(config.get("analytics"), null);
        Map tokenMap = (Map) config.get("auth_token");
        if (tokenMap != null) {
            this.authToken = new AuthToken(tokenMap);
        }
        this.forceVersion = ObjectUtils.asBoolean(config.get("force_version"), true).booleanValue();
        Map<? extends String, ? extends Object> map = (Map) config.get("properties");
        if (map != null) {
            this.properties.putAll(map);
        }
        this.longUrlSignature = ObjectUtils.asBoolean(config.get("long_url_signature"), false).booleanValue();
        this.signatureAlgorithm = SignatureAlgorithm.valueOf(ObjectUtils.asString(config.get(CONFIG_PROP_SIGNATURE_ALGORITHM), DEFAULT_SIGNATURE_ALGORITHM.name()));
        this.oauthToken = (String) config.get("oauth_token");
    }

    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("cloud_name", this.cloudName);
        map.put("api_key", this.apiKey);
        map.put("api_secret", this.apiSecret);
        map.put("secure_distribution", this.secureDistribution);
        map.put("cname", this.cname);
        map.put("secure", Boolean.valueOf(this.secure));
        map.put("private_cdn", Boolean.valueOf(this.privateCdn));
        map.put("cdn_subdomain", Boolean.valueOf(this.cdnSubdomain));
        map.put("shorten", Boolean.valueOf(this.shorten));
        map.put("upload_prefix", this.uploadPrefix);
        map.put("callback", this.callback);
        map.put("proxy_host", this.proxyHost);
        map.put("proxy_port", Integer.valueOf(this.proxyPort));
        map.put("secure_cdn_subdomain", this.secureCdnSubdomain);
        map.put("use_root_path", Boolean.valueOf(this.useRootPath));
        map.put("use_fetch_format", Boolean.valueOf(this.useFetchFormat));
        map.put("load_strategies", Boolean.valueOf(this.loadStrategies));
        map.put("timeout", Integer.valueOf(this.timeout));
        map.put("client_hints", Boolean.valueOf(this.clientHints));
        if (this.authToken != null) {
            map.put("auth_token", this.authToken.asMap());
        }
        map.put("force_version", Boolean.valueOf(this.forceVersion));
        map.put("properties", new HashMap(this.properties));
        map.put("long_url_signature", Boolean.valueOf(this.longUrlSignature));
        map.put(CONFIG_PROP_SIGNATURE_ALGORITHM, this.signatureAlgorithm.toString());
        map.put("oauth_token", this.oauthToken);
        map.put("analytics", this.analytics);
        return map;
    }

    public Configuration(Configuration other) {
        this.properties = new HashMap();
        this.loadStrategies = true;
        this.clientHints = false;
        this.forceVersion = true;
        this.longUrlSignature = false;
        this.signatureAlgorithm = DEFAULT_SIGNATURE_ALGORITHM;
        this.oauthToken = null;
        this.cloudName = other.cloudName;
        this.apiKey = other.apiKey;
        this.apiSecret = other.apiSecret;
        this.secureDistribution = other.secureDistribution;
        this.cname = other.cname;
        this.uploadPrefix = other.uploadPrefix;
        this.secure = other.secure;
        this.privateCdn = other.privateCdn;
        this.cdnSubdomain = other.cdnSubdomain;
        this.shorten = other.shorten;
        this.callback = other.callback;
        this.proxyHost = other.proxyHost;
        this.proxyPort = other.proxyPort;
        this.secureCdnSubdomain = other.secureCdnSubdomain;
        this.useRootPath = other.useRootPath;
        this.useFetchFormat = other.useFetchFormat;
        this.timeout = other.timeout;
        this.clientHints = other.clientHints;
        if (other.authToken != null) {
            this.authToken = other.authToken.copy();
        }
        this.forceVersion = other.forceVersion;
        this.loadStrategies = other.loadStrategies;
        this.properties.putAll(other.properties);
        this.longUrlSignature = other.longUrlSignature;
        this.signatureAlgorithm = other.signatureAlgorithm;
        this.oauthToken = other.oauthToken;
        this.analytics = other.analytics;
    }

    public static Configuration from(Configuration other) {
        return new Builder().from(other).build();
    }

    public static Configuration from(String cloudinaryUrl) {
        Configuration config = new Configuration();
        config.update(parseConfigUrl(cloudinaryUrl));
        return config;
    }

    protected static Map parseConfigUrl(String cloudinaryUrl) {
        Map params = new HashMap();
        URI cloudinaryUri = URI.create(cloudinaryUrl);
        if (cloudinaryUri.getScheme() == null || !cloudinaryUri.getScheme().equalsIgnoreCase("cloudinary")) {
            throw new IllegalArgumentException("Invalid CLOUDINARY_URL scheme. Expecting to start with 'cloudinary://'");
        }
        params.put("cloud_name", cloudinaryUri.getHost());
        if (cloudinaryUri.getUserInfo() != null) {
            String[] creds = cloudinaryUri.getUserInfo().split(":");
            params.put("api_key", creds[0]);
            if (creds.length > 1) {
                params.put("api_secret", creds[1]);
            }
        }
        params.put("private_cdn", Boolean.valueOf(!StringUtils.isEmpty(cloudinaryUri.getPath())));
        params.put("secure_distribution", cloudinaryUri.getPath());
        updateMapfromURI(params, cloudinaryUri);
        return params;
    }

    private static void updateMapfromURI(Map params, URI cloudinaryUri) {
        if (cloudinaryUri.getQuery() != null) {
            for (String param : cloudinaryUri.getQuery().split("&")) {
                String[] keyValue = param.split("=");
                try {
                    String value = URLDecoder.decode(keyValue[1], "ASCII");
                    String key = keyValue[0];
                    if (isNestedKey(key)) {
                        putNestedValue(params, key, value);
                    } else {
                        params.put(key, value);
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Unexpected exception", e);
                }
            }
        }
    }

    private static void putNestedValue(Map params, String key, String value) {
        String[] chain = key.split("[\\[\\]]+");
        Map outer = params;
        String innerKey = chain[0];
        int i = 0;
        while (i < chain.length - 1) {
            Map inner = (Map) outer.get(innerKey);
            if (inner == null) {
                inner = new HashMap();
                outer.put(innerKey, inner);
            }
            outer = inner;
            i++;
            innerKey = chain[i];
        }
        outer.put(innerKey, value);
    }

    private static boolean isNestedKey(String key) {
        return key.matches("\\w+\\[\\w+\\]");
    }

    public static class Builder {
        private boolean analytics;
        private String apiKey;
        private String apiSecret;
        private AuthToken authToken;
        private String callback;
        private boolean cdnSubdomain;
        private String cloudName;
        private String cname;
        private boolean privateCdn;
        private String proxyHost;
        private int proxyPort;
        private boolean secure;
        private Boolean secureCdnSubdomain;
        private String secureDistribution;
        private boolean shorten;
        private int timeout;
        private String uploadPrefix;
        private boolean useFetchFormat;
        private boolean useRootPath;
        private boolean loadStrategies = true;
        private boolean clientHints = false;
        private boolean forceVersion = true;
        private boolean longUrlSignature = false;
        private SignatureAlgorithm signatureAlgorithm = Configuration.DEFAULT_SIGNATURE_ALGORITHM;
        private String oauthToken = null;

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Configuration build() {
            Configuration configuration = new Configuration(this.cloudName, this.apiKey, this.apiSecret, this.secureDistribution, this.cname, this.uploadPrefix, this.secure, this.privateCdn, this.cdnSubdomain, this.shorten, this.callback, this.proxyHost, this.proxyPort, this.secureCdnSubdomain, this.useRootPath, this.useFetchFormat, this.timeout, this.loadStrategies, this.forceVersion, this.longUrlSignature, this.signatureAlgorithm, this.oauthToken, this.analytics);
            configuration.clientHints = this.clientHints;
            return configuration;
        }

        public Builder setCloudName(String cloudName) {
            this.cloudName = cloudName;
            return this;
        }

        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder setApiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
            return this;
        }

        public Builder setSecureDistribution(String secureDistribution) {
            this.secureDistribution = secureDistribution;
            return this;
        }

        public Builder setCname(String cname) {
            this.cname = cname;
            return this;
        }

        public Builder setSecure(boolean secure) {
            this.secure = secure;
            return this;
        }

        public Builder setPrivateCdn(boolean privateCdn) {
            this.privateCdn = privateCdn;
            return this;
        }

        public Builder setSecureCdnSubdomain(Boolean secureCdnSubdomain) {
            this.secureCdnSubdomain = secureCdnSubdomain;
            return this;
        }

        public Builder setCdnSubdomain(boolean cdnSubdomain) {
            this.cdnSubdomain = cdnSubdomain;
            return this;
        }

        public Builder setShorten(boolean shorten) {
            this.shorten = shorten;
            return this;
        }

        public Builder setCallback(String callback) {
            this.callback = callback;
            return this;
        }

        public Builder setUploadPrefix(String uploadPrefix) {
            this.uploadPrefix = uploadPrefix;
            return this;
        }

        public Builder setUseRootPath(boolean useRootPath) {
            this.useRootPath = useRootPath;
            return this;
        }

        public Builder setUseFetchFormat(boolean useFetchFormat) {
            this.useFetchFormat = useFetchFormat;
            return this;
        }

        public Builder setLoadStrategies(boolean loadStrategies) {
            this.loadStrategies = loadStrategies;
            return this;
        }

        public Builder setAnalytics(boolean analytics) {
            this.analytics = analytics;
            return this;
        }

        public Builder setClientHints(boolean clientHints) {
            this.clientHints = clientHints;
            return this;
        }

        public Builder setAuthToken(AuthToken authToken) {
            this.authToken = authToken;
            return this;
        }

        public Builder setForceVersion(boolean forceVersion) {
            this.forceVersion = forceVersion;
            return this;
        }

        public Builder setIsLongUrlSignature(boolean isLong) {
            this.longUrlSignature = isLong;
            return this;
        }

        public Builder setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
            this.signatureAlgorithm = signatureAlgorithm;
            return this;
        }

        public Builder setOAuthToken(String oauthToken) {
            this.oauthToken = oauthToken;
            return this;
        }

        public Builder from(Configuration other) {
            this.cloudName = other.cloudName;
            this.apiKey = other.apiKey;
            this.apiSecret = other.apiSecret;
            this.secureDistribution = other.secureDistribution;
            this.cname = other.cname;
            this.uploadPrefix = other.uploadPrefix;
            this.secure = other.secure;
            this.privateCdn = other.privateCdn;
            this.cdnSubdomain = other.cdnSubdomain;
            this.shorten = other.shorten;
            this.callback = other.callback;
            this.proxyHost = other.proxyHost;
            this.proxyPort = other.proxyPort;
            this.secureCdnSubdomain = other.secureCdnSubdomain;
            this.useRootPath = other.useRootPath;
            this.useFetchFormat = other.useFetchFormat;
            this.loadStrategies = other.loadStrategies;
            this.timeout = other.timeout;
            this.clientHints = other.clientHints;
            this.authToken = other.authToken == null ? null : other.authToken.copy();
            this.forceVersion = other.forceVersion;
            this.longUrlSignature = other.longUrlSignature;
            this.signatureAlgorithm = other.signatureAlgorithm;
            this.oauthToken = other.oauthToken;
            this.analytics = other.analytics.booleanValue();
            return this;
        }
    }
}
