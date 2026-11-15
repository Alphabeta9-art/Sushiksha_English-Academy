package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.bumptech.glide.load.Key;
import com.cloudinary.utils.Base64Coder;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.facebook.common.util.UriUtil;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes11.dex */
public class Url {
    private static final String CL_BLANK = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7";
    public static final String[] DEFAULT_VIDEO_SOURCE_TYPES = {"webm", "mp4", "ogv"};
    private static final Pattern VIDEO_EXTENSION_RE = Pattern.compile("\\.(" + StringUtils.join(DEFAULT_VIDEO_SOURCE_TYPES, "|") + ")$");
    private static Pattern identifierPattern = Pattern.compile("^(?:([^/]+)/)??(?:([^/]+)/)??(?:v(\\d+)/)?(?:([^#/]+?)(?:\\.([^.#/]+))?)(?:#([^/]+))?$");
    private AuthToken authToken;
    private final Cloudinary cloudinary;
    private final Configuration config;
    private boolean longUrlSignature;
    boolean signUrl;
    private String urlSuffix;
    private Boolean useFetchFormat;
    private Boolean useRootPath;
    String publicId = null;
    String type = null;
    String resourceType = null;
    String format = null;
    String version = null;
    Transformation transformation = null;
    String source = null;
    Map<String, Transformation> sourceTransformation = null;
    String[] sourceTypes = null;
    String fallbackContent = null;
    Transformation posterTransformation = null;
    String posterSource = null;
    Url posterUrl = null;

    public Url(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
        this.config = new Configuration(cloudinary.config);
        this.longUrlSignature = this.config.longUrlSignature;
        this.authToken = this.config.authToken;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Url m8550clone() {
        Url cloned = this.cloudinary.url();
        cloned.config.update(this.config.asMap());
        cloned.fallbackContent = this.fallbackContent;
        cloned.format = this.format;
        cloned.posterSource = this.posterSource;
        if (this.posterTransformation != null) {
            cloned.posterTransformation = new Transformation(this.posterTransformation);
        }
        if (this.posterUrl != null) {
            cloned.posterUrl = this.posterUrl.m8550clone();
        }
        cloned.publicId = this.publicId;
        cloned.resourceType = this.resourceType;
        cloned.signUrl = this.signUrl;
        cloned.source = this.source;
        if (this.transformation != null) {
            cloned.transformation = new Transformation(this.transformation);
        }
        if (this.sourceTransformation != null) {
            cloned.sourceTransformation = new HashMap();
            for (Map.Entry<String, Transformation> keyValuePair : this.sourceTransformation.entrySet()) {
                cloned.sourceTransformation.put(keyValuePair.getKey(), keyValuePair.getValue());
            }
        }
        cloned.sourceTypes = this.sourceTypes;
        cloned.urlSuffix = this.urlSuffix;
        cloned.useRootPath = this.useRootPath;
        cloned.useFetchFormat = this.useFetchFormat;
        cloned.longUrlSignature = this.longUrlSignature;
        cloned.authToken = this.authToken;
        return cloned;
    }

    public Url fromIdentifier(String identifier) {
        Matcher matcher = identifierPattern.matcher(identifier);
        if (!matcher.matches()) {
            throw new RuntimeException(String.format("Couldn't parse identifier %s", identifier));
        }
        String resourceType = matcher.group(1);
        if (resourceType != null) {
            resourceType(resourceType);
        }
        String type = matcher.group(2);
        if (type != null) {
            type(type);
        }
        String version = matcher.group(3);
        if (version != null) {
            version(version);
        }
        String publicId = matcher.group(4);
        if (publicId != null) {
            publicId(publicId);
        }
        String format = matcher.group(5);
        if (format != null) {
            format(format);
        }
        return this;
    }

    public Url type(String type) {
        this.type = type;
        return this;
    }

    public Url resourcType(String resourceType) {
        return resourceType(resourceType);
    }

    public Url resourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public Url publicId(Object publicId) {
        this.publicId = ObjectUtils.asString(publicId);
        return this;
    }

    public Url format(String format) {
        this.format = format;
        return this;
    }

    public Url cloudName(String cloudName) {
        this.config.cloudName = cloudName;
        return this;
    }

    public Url secureDistribution(String secureDistribution) {
        this.config.secureDistribution = secureDistribution;
        return this;
    }

    public Url secureCdnSubdomain(boolean secureCdnSubdomain) {
        this.config.secureCdnSubdomain = Boolean.valueOf(secureCdnSubdomain);
        return this;
    }

    public Url suffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
        return this;
    }

    public Url useRootPath(boolean useRootPath) {
        this.useRootPath = Boolean.valueOf(useRootPath);
        return this;
    }

    public Url useFetchFormat(boolean useFetchFormat) {
        this.useFetchFormat = Boolean.valueOf(useFetchFormat);
        return this;
    }

    public Url cname(String cname) {
        this.config.cname = cname;
        return this;
    }

    public Url version(Object version) {
        this.version = ObjectUtils.asString(version);
        return this;
    }

    public Url transformation(Transformation transformation) {
        this.transformation = transformation;
        return this;
    }

    public Url secure(boolean secure) {
        this.config.secure = secure;
        return this;
    }

    public Url privateCdn(boolean privateCdn) {
        this.config.privateCdn = privateCdn;
        return this;
    }

    public Url cdnSubdomain(boolean cdnSubdomain) {
        this.config.cdnSubdomain = cdnSubdomain;
        return this;
    }

    public Url shorten(boolean shorten) {
        this.config.shorten = shorten;
        return this;
    }

    public Transformation transformation() {
        if (this.transformation == null) {
            this.transformation = new Transformation();
        }
        return this.transformation;
    }

    public Url signed(boolean signUrl) {
        this.signUrl = signUrl;
        return this;
    }

    public Url authToken(AuthToken authToken) {
        if (this.authToken == null || authToken == null || authToken.equals(AuthToken.NULL_AUTH_TOKEN)) {
            this.authToken = authToken;
        } else {
            this.authToken = this.authToken.merge(authToken);
        }
        return this;
    }

    public Url longUrlSignature(boolean isLong) {
        this.longUrlSignature = isLong;
        return this;
    }

    public Url sourceTransformation(Map<String, Transformation> sourceTransformation) {
        this.sourceTransformation = sourceTransformation;
        return this;
    }

    public Url sourceTransformationFor(String source, Transformation transformation) {
        if (this.sourceTransformation == null) {
            this.sourceTransformation = new HashMap();
        }
        this.sourceTransformation.put(source, transformation);
        return this;
    }

    public Url sourceTypes(String[] sourceTypes) {
        this.sourceTypes = sourceTypes;
        return this;
    }

    public Url fallbackContent(String fallbackContent) {
        this.fallbackContent = fallbackContent;
        return this;
    }

    public Url posterTransformation(Transformation posterTransformation) {
        this.posterTransformation = posterTransformation;
        return this;
    }

    public Url posterTransformation(List<Map> posterTransformations) {
        this.posterTransformation = new Transformation(posterTransformations);
        return this;
    }

    public Url posterTransformation(Map posterTransformations) {
        List<Map> transformations = new ArrayList<>();
        Map copy = new HashMap();
        copy.putAll(posterTransformations);
        transformations.add(copy);
        this.posterTransformation = new Transformation(transformations);
        return this;
    }

    public Url posterSource(String posterSource) {
        this.posterSource = posterSource;
        return this;
    }

    public Url posterUrl(Url posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public Url poster(Object poster) {
        if (poster instanceof Transformation) {
            return posterTransformation((Transformation) poster);
        }
        if (poster instanceof List) {
            return posterTransformation((List<Map>) poster);
        }
        if (poster instanceof Map) {
            return posterTransformation((Map) poster);
        }
        if (poster instanceof Url) {
            return posterUrl((Url) poster);
        }
        if (poster instanceof String) {
            return posterSource((String) poster);
        }
        if (poster == null || poster.equals(Boolean.FALSE)) {
            return posterSource("");
        }
        throw new IllegalArgumentException("Illegal value type supplied to poster. must be one of: <Transformation>, <List<Map>>, <Map>, <Url>, <String>");
    }

    public Url forceVersion(boolean forceVersion) {
        this.config.forceVersion = forceVersion;
        return this;
    }

    public String generate() {
        return generate(null);
    }

    public String generate(String source) {
        boolean useRootPath;
        String source2;
        String signature;
        boolean useRootPath2 = this.config.useRootPath;
        if (this.useRootPath == null) {
            useRootPath = useRootPath2;
        } else {
            boolean useRootPath3 = this.useRootPath.booleanValue();
            useRootPath = useRootPath3;
        }
        if (StringUtils.isEmpty(this.config.cloudName)) {
            throw new IllegalArgumentException("Must supply cloud_name in tag or in configuration");
        }
        if (source != null) {
            source2 = source;
        } else if (this.publicId == null) {
            if (this.source == null) {
                return null;
            }
            source2 = this.source;
        } else {
            source2 = this.publicId;
        }
        boolean httpSource = StringUtils.isHttpUrl(source2);
        if (httpSource && (StringUtils.isEmpty(this.type) || UriUtil.LOCAL_ASSET_SCHEME.equals(this.type))) {
            return source2;
        }
        if (((this.type != null && this.type.equals("fetch")) || (this.useFetchFormat != null && this.useFetchFormat.booleanValue())) && !StringUtils.isEmpty(this.format)) {
            transformation().fetchFormat(this.format);
            this.format = null;
        }
        String transformationStr = transformation().generate();
        String[] finalizedSource = finalizeSource(source2, this.format, this.urlSuffix);
        String source3 = finalizedSource[0];
        char c = 1;
        String sourceToSign = finalizedSource[1];
        if (this.config.forceVersion && sourceToSign.contains(DomExceptionUtils.SEPARATOR) && !StringUtils.startWithVersionString(sourceToSign) && !httpSource && StringUtils.isEmpty(this.version)) {
            this.version = "1";
        }
        if (this.version == null) {
            this.version = "";
        } else {
            this.version = "v" + this.version;
        }
        if (!this.signUrl || (this.authToken != null && !this.authToken.equals(AuthToken.NULL_AUTH_TOKEN))) {
            signature = "";
        } else {
            SignatureAlgorithm signatureAlgorithm = this.longUrlSignature ? SignatureAlgorithm.SHA256 : this.config.signatureAlgorithm;
            String toSign = StringUtils.join(new String[]{transformationStr, sourceToSign}, DomExceptionUtils.SEPARATOR);
            byte[] hash = Util.hash(StringUtils.mergeSlashesInUrl(StringUtils.removeStartingChars(toSign, '/')) + this.config.apiSecret, signatureAlgorithm);
            String signature2 = Base64Coder.encodeURLSafeString(hash);
            c = 1;
            signature = "s--" + signature2.substring(0, this.longUrlSignature ? 32 : 8) + "--";
        }
        String resourceType = this.resourceType;
        if (resourceType == null) {
            resourceType = "image";
        }
        String finalResourceType = finalizeResourceType(resourceType, this.type, this.urlSuffix, useRootPath, this.config.shorten);
        String prefix = unsignedDownloadUrlPrefix(source3, this.config);
        String[] strArr = new String[6];
        strArr[0] = prefix;
        strArr[c] = finalResourceType;
        strArr[2] = signature;
        strArr[3] = transformationStr;
        strArr[4] = this.version;
        strArr[5] = source3;
        String join = StringUtils.join(strArr, DomExceptionUtils.SEPARATOR);
        String url = StringUtils.mergeSlashesInUrl(join);
        if (this.signUrl && this.authToken != null && !this.authToken.equals(AuthToken.NULL_AUTH_TOKEN)) {
            try {
                String path = new URL(url).getPath();
                String token = this.authToken.generate(path);
                try {
                    url = url + "?" + token;
                } catch (MalformedURLException e) {
                }
            } catch (MalformedURLException e2) {
            }
        }
        if (this.cloudinary.config.analytics != null && this.cloudinary.config.analytics.booleanValue()) {
            try {
                URL tempUrl = new URL(url);
                if (tempUrl.getQuery() == null) {
                    tempUrl.getPath();
                    return url + "?" + this.cloudinary.analytics.toQueryParam();
                }
                return url;
            } catch (MalformedURLException e3) {
                return url;
            }
        }
        return url;
    }

    private String[] finalizeSource(String source, String format, String urlSuffix) {
        String source2;
        String source3;
        String source4 = StringUtils.mergeSlashesInUrl(source);
        String[] result = new String[2];
        if (StringUtils.isHttpUrl(source4)) {
            source3 = SmartUrlEncoder.encode(source4);
            source2 = source3;
        } else {
            try {
                String source5 = SmartUrlEncoder.encode(URLDecoder.decode(source4.replace("+", "%2B"), Key.STRING_CHARSET_NAME));
                if (StringUtils.isNotBlank(urlSuffix)) {
                    if (urlSuffix.contains(".") || urlSuffix.contains(DomExceptionUtils.SEPARATOR)) {
                        throw new IllegalArgumentException("url_suffix should not include . or /");
                    }
                    source5 = source5 + DomExceptionUtils.SEPARATOR + urlSuffix;
                }
                if (!StringUtils.isNotBlank(format)) {
                    String str = source5;
                    source2 = source5;
                    source3 = str;
                } else {
                    String source6 = source5 + "." + format;
                    String sourceToSign = source5 + "." + format;
                    source2 = sourceToSign;
                    source3 = source6;
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        result[0] = source3;
        result[1] = source2;
        return result;
    }

    public String finalizeResourceType(String resourceType, String type, String urlSuffix, boolean useRootPath, boolean shorten) {
        if (type == null) {
            type = "upload";
        }
        if (!StringUtils.isBlank(urlSuffix)) {
            if (resourceType.equals("image") && type.equals("upload")) {
                resourceType = "images";
                type = null;
            } else if (resourceType.equals("image") && type.equals("private")) {
                resourceType = "private_images";
                type = null;
            } else if (resourceType.equals("image") && type.equals("authenticated")) {
                resourceType = "authenticated_images";
                type = null;
            } else if (resourceType.equals("raw") && type.equals("upload")) {
                resourceType = "files";
                type = null;
            } else if (resourceType.equals(MimeTypes.BASE_TYPE_VIDEO) && type.equals("upload")) {
                resourceType = "videos";
                type = null;
            } else {
                throw new IllegalArgumentException("URL Suffix only supported for image/upload, image/private, raw/upload, image/authenticated  and video/upload");
            }
        }
        if (useRootPath) {
            if ((resourceType.equals("image") && type.equals("upload")) || (resourceType.equals("images") && StringUtils.isBlank(type))) {
                resourceType = null;
                type = null;
            } else {
                throw new IllegalArgumentException("Root path only supported for image/upload");
            }
        }
        if (shorten && resourceType.equals("image") && type.equals("upload")) {
            resourceType = "iu";
            type = null;
        }
        String result = resourceType;
        if (type != null) {
            return result + DomExceptionUtils.SEPARATOR + type;
        }
        return result;
    }

    public static String unsignedDownloadUrlPrefix(String source, Configuration config) {
        String prefix;
        if (config.cloudName.startsWith(DomExceptionUtils.SEPARATOR)) {
            return "/res" + config.cloudName;
        }
        boolean sharedDomain = !config.privateCdn;
        String secureDistribution = config.secureDistribution;
        Boolean secureCdnSubdomain = null;
        if (config.secure) {
            if (StringUtils.isEmpty(config.secureDistribution) || config.secureDistribution.equals("cloudinary-a.akamaihd.net")) {
                secureDistribution = config.privateCdn ? config.cloudName + "-res.cloudinary.com" : "res.cloudinary.com";
            }
            if (!sharedDomain) {
                sharedDomain = secureDistribution.equals("res.cloudinary.com");
            }
            if (0 == 0 && sharedDomain) {
                secureCdnSubdomain = Boolean.valueOf(config.cdnSubdomain);
            }
            if (secureCdnSubdomain != null && secureCdnSubdomain.booleanValue()) {
                secureDistribution = config.secureDistribution.replace("res.cloudinary.com", "res-" + shard(source) + ".cloudinary.com");
            }
            prefix = "https://" + secureDistribution;
        } else {
            if (StringUtils.isNotBlank(config.cname)) {
                String subdomain = config.cdnSubdomain ? CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY + shard(source) + "." : "";
                prefix = "http://" + subdomain + config.cname;
            } else {
                String cloudName = config.privateCdn ? config.cloudName + "-" : "";
                String subdomain2 = config.cdnSubdomain ? "-" + shard(source) : "";
                prefix = StringUtils.join(new String[]{"http://", cloudName, UriUtil.LOCAL_RESOURCE_SCHEME, subdomain2, ".cloudinary.com"}, "");
            }
        }
        if (sharedDomain) {
            return prefix + DomExceptionUtils.SEPARATOR + config.cloudName;
        }
        return prefix;
    }

    private static String shard(String input) {
        CRC32 crc32 = new CRC32();
        crc32.update(Util.getUTF8Bytes(input));
        return String.valueOf((((crc32.getValue() % 5) + 5) % 5) + 1);
    }

    public String imageTag(String source) {
        return imageTag(source, ObjectUtils.emptyMap());
    }

    public String imageTag(Map<String, String> attributes) {
        return imageTag(null, attributes);
    }

    public String imageTag(String source, Map<String, String> attributes) {
        String url = generate(source);
        Map<String, String> attributes2 = new TreeMap<>(attributes);
        if (transformation().getHtmlHeight() != null) {
            attributes2.put("height", transformation().getHtmlHeight());
        }
        if (transformation().getHtmlWidth() != null) {
            attributes2.put("width", transformation().getHtmlWidth());
        }
        boolean hiDPI = transformation().isHiDPI();
        boolean responsive = transformation().isResponsive();
        if (!this.config.clientHints && (hiDPI || responsive)) {
            attributes2.put("data-src", url);
            String extraClass = responsive ? "cld-responsive" : "cld-hidpi";
            attributes2.put("class", (StringUtils.isBlank(attributes2.get("class")) ? "" : attributes2.get("class") + " ") + extraClass);
            String responsivePlaceholder = attributes2.remove("responsive_placeholder");
            if ("blank".equals(responsivePlaceholder)) {
                responsivePlaceholder = CL_BLANK;
            }
            url = responsivePlaceholder;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<img");
        if (url != null) {
            builder.append(" src='").append(url).append("'");
        }
        for (Map.Entry<String, String> attr : attributes2.entrySet()) {
            builder.append(" ").append(attr.getKey()).append("='").append(attr.getValue()).append("'");
        }
        builder.append("/>");
        return builder.toString();
    }

    public String videoTag() {
        return videoTag("", new HashMap());
    }

    public String videoTag(String source) {
        return videoTag(source, new HashMap());
    }

    public String videoTag(Map<String, String> attributes) {
        return videoTag("", attributes);
    }

    private String finalizePosterUrl(String source) {
        if (this.posterUrl != null) {
            String posterUrl = this.posterUrl.generate();
            return posterUrl;
        }
        if (this.posterTransformation != null) {
            String posterUrl2 = m8550clone().format("jpg").transformation(new Transformation(this.posterTransformation)).generate(source);
            return posterUrl2;
        }
        if (this.posterSource != null) {
            if (StringUtils.isEmpty(this.posterSource)) {
                return null;
            }
            String posterUrl3 = m8550clone().format("jpg").generate(this.posterSource);
            return posterUrl3;
        }
        String posterUrl4 = m8550clone().format("jpg").generate(source);
        return posterUrl4;
    }

    private void appendVideoSources(StringBuilder html, String source, String sourceType) {
        Url sourceUrl = m8550clone();
        if (this.sourceTransformation != null) {
            Transformation transformation = this.transformation;
            Transformation sourceTransformation = null;
            if (this.sourceTransformation.get(sourceType) != null) {
                sourceTransformation = new Transformation(this.sourceTransformation.get(sourceType));
            }
            if (transformation == null) {
                transformation = sourceTransformation;
            } else if (sourceTransformation != null) {
                transformation = transformation.chainWith(sourceTransformation);
            }
            sourceUrl.transformation(transformation);
        }
        String src = sourceUrl.format(sourceType).generate(source);
        String videoType = sourceType;
        if (sourceType.equals("ogv")) {
            videoType = "ogg";
        }
        String mimeType = "video/" + videoType;
        html.append("<source src='").append(src).append("' type='").append(mimeType).append("'>");
    }

    public String videoTag(String source, Map<String, String> attributes) {
        if (StringUtils.isEmpty(source)) {
            source = this.source;
        }
        if (StringUtils.isEmpty(source)) {
            source = this.publicId;
        }
        if (StringUtils.isEmpty(source)) {
            throw new IllegalArgumentException("must supply source or public id");
        }
        String source2 = VIDEO_EXTENSION_RE.matcher(source).replaceFirst("");
        if (this.resourceType == null) {
            this.resourceType = MimeTypes.BASE_TYPE_VIDEO;
        }
        Map<String, String> attributes2 = new TreeMap<>(attributes);
        String[] sourceTypes = this.sourceTypes;
        if (sourceTypes == null) {
            sourceTypes = DEFAULT_VIDEO_SOURCE_TYPES;
        }
        String posterUrl = finalizePosterUrl(source2);
        if (!StringUtils.isEmpty(posterUrl)) {
            attributes2.put("poster", posterUrl);
        }
        StringBuilder html = new StringBuilder().append("<video");
        boolean multiSource = sourceTypes.length > 1;
        if (!multiSource) {
            String url = generate(source2 + "." + sourceTypes[0]);
            attributes2.put("src", url);
        } else {
            generate(source2);
        }
        if (this.transformation.getHtmlHeight() != null) {
            attributes2.put("height", this.transformation.getHtmlHeight());
        }
        if (attributes2.containsKey("html_height")) {
            attributes2.put("height", attributes2.remove("html_height"));
        }
        if (this.transformation.getHtmlWidth() != null) {
            attributes2.put("width", this.transformation.getHtmlWidth());
        }
        if (attributes2.containsKey("html_width")) {
            attributes2.put("width", attributes2.remove("html_width"));
        }
        for (Map.Entry<String, String> attr : attributes2.entrySet()) {
            html.append(" ").append(attr.getKey());
            if (attr.getValue() != null) {
                String value = ObjectUtils.asString(attr.getValue());
                html.append("='").append(value).append("'");
            }
        }
        html.append(">");
        if (multiSource) {
            for (String sourceType : sourceTypes) {
                appendVideoSources(html, source2, sourceType);
            }
        }
        if (this.fallbackContent != null) {
            html.append(this.fallbackContent);
        }
        html.append("</video>");
        return html.toString();
    }

    public String generateSpriteCss(String source) {
        this.type = "sprite";
        if (!source.endsWith(".css")) {
            this.format = "css";
        }
        return generate(source);
    }

    public Url source(String source) {
        this.source = source;
        return this;
    }

    public Url source(StoredFile source) {
        if (source.getResourceType() != null) {
            this.resourceType = source.getResourceType();
        }
        if (source.getType() != null) {
            this.type = source.getType();
        }
        if (source.getVersion() != null) {
            this.version = source.getVersion().toString();
        }
        this.format = source.getFormat();
        this.source = source.getPublicId();
        return this;
    }
}
