package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public class StoredFile {
    private static final String AUTO_RESOURCE_TYPE = "auto";
    private static final String IMAGE_RESOURCE_TYPE = "image";
    private static final Pattern PRELOADED_PATTERN = Pattern.compile("^([^\\/]+)\\/([^\\/]+)\\/v(\\d+)\\/([^#]+)#?([^\\/]+)?$");
    private static final String VIDEO_RESOURCE_TYPE = "video";
    protected String format;
    protected String publicId;
    protected String signature;
    protected Long version;
    protected String type = "upload";
    protected String resourceType = "image";

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    protected String getPublicIdForSigning() {
        return this.publicId + ((this.format == null || this.format.isEmpty() || !this.resourceType.equals("raw")) ? "" : "." + this.format);
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreloadedFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.resourceType).append(DomExceptionUtils.SEPARATOR).append(this.type).append("/v").append(this.version).append(DomExceptionUtils.SEPARATOR).append(this.publicId);
        if (this.format != null && !this.format.isEmpty()) {
            sb.append(".").append(this.format);
        }
        if (this.signature != null && !this.signature.isEmpty()) {
            sb.append("#").append(this.signature);
        }
        return sb.toString();
    }

    public void setPreloadedFile(String uri) {
        if (uri.matches(PRELOADED_PATTERN.pattern())) {
            Matcher match = PRELOADED_PATTERN.matcher(uri);
            match.find();
            this.resourceType = match.group(1);
            this.type = match.group(2);
            this.version = Long.valueOf(Long.parseLong(match.group(3)));
            String filename = match.group(4);
            if (match.groupCount() == 5) {
                this.signature = match.group(5);
            }
            int lastDotIndex = filename.lastIndexOf(46);
            if (lastDotIndex == -1) {
                this.publicId = filename;
            } else {
                this.publicId = filename.substring(0, lastDotIndex);
                this.format = filename.substring(lastDotIndex + 1);
            }
        }
    }

    public String getComputedSignature(Cloudinary cloudinary) {
        Map<String, Object> params = new HashMap<>();
        params.put("version", getVersion().toString());
        params.put("public_id", getPublicIdForSigning());
        cloudinary.signRequest(params, new HashMap());
        return params.get("signature").toString();
    }

    public boolean getIsImage() {
        return "image".equals(this.resourceType) || "auto".equals(this.resourceType);
    }

    public boolean getIsVideo() {
        return "video".equals(this.resourceType);
    }
}
