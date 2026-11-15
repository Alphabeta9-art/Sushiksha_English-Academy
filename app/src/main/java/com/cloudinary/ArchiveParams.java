package com.cloudinary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class ArchiveParams {
    public static final String FORMAT_ZIP = "zip";
    public static final String MODE_CREATE = "create";
    public static final String MODE_DOWNLOAD = "download";
    private String resourceType = "image";
    private String type = null;
    private String mode = MODE_CREATE;
    private String targetFormat = null;
    private String targetPublicId = null;
    private boolean flattenFolders = false;
    private boolean flattenTransformations = false;
    private boolean useOriginalFilename = false;
    private boolean async = false;
    private boolean keepDerived = false;
    private boolean skipTransformationName = false;
    private boolean allowMissing = false;
    private String notificationUrl = null;
    private String[] targetTags = null;
    private String[] tags = null;
    private String[] publicIds = null;
    private String[] fullyQualifiedPublicIds = null;
    private String[] prefixes = null;
    private Transformation[] transformations = null;
    private Long expiresAt = null;

    public String resourceType() {
        return this.resourceType;
    }

    public ArchiveParams resourceType(String resourceType) {
        if (resourceType == null) {
            throw new IllegalArgumentException("resource type must be non-null");
        }
        this.resourceType = resourceType;
        return this;
    }

    public String type() {
        return this.type;
    }

    public ArchiveParams type(String type) {
        this.type = type;
        return this;
    }

    public String mode() {
        return this.mode;
    }

    public ArchiveParams mode(String mode) {
        this.mode = mode;
        return this;
    }

    public String targetFormat() {
        return this.targetFormat;
    }

    public ArchiveParams targetFormat(String targetFormat) {
        this.targetFormat = targetFormat;
        return this;
    }

    public String targetPublicId() {
        return this.targetPublicId;
    }

    public ArchiveParams targetPublicId(String targetPublicId) {
        this.targetPublicId = targetPublicId;
        return this;
    }

    public boolean isFlattenFolders() {
        return this.flattenFolders;
    }

    public ArchiveParams flattenFolders(boolean flattenFolders) {
        this.flattenFolders = flattenFolders;
        return this;
    }

    public boolean isFlattenTransformations() {
        return this.flattenTransformations;
    }

    public ArchiveParams flattenTransformations(boolean flattenTransformations) {
        this.flattenTransformations = flattenTransformations;
        return this;
    }

    public boolean isUseOriginalFilename() {
        return this.useOriginalFilename;
    }

    public ArchiveParams useOriginalFilename(boolean useOriginalFilename) {
        this.useOriginalFilename = useOriginalFilename;
        return this;
    }

    public boolean isAsync() {
        return this.async;
    }

    public ArchiveParams async(boolean async) {
        this.async = async;
        return this;
    }

    public boolean isSkipTransformationName() {
        return this.skipTransformationName;
    }

    public ArchiveParams skipTransformationName(boolean skipTransformationName) {
        this.skipTransformationName = skipTransformationName;
        return this;
    }

    public boolean isAllowMissing() {
        return this.allowMissing;
    }

    public ArchiveParams allowMissing(boolean allowMissing) {
        this.allowMissing = allowMissing;
        return this;
    }

    public boolean isKeepDerived() {
        return this.keepDerived;
    }

    public ArchiveParams keepDerived(boolean keepDerived) {
        this.keepDerived = keepDerived;
        return this;
    }

    public String notificationUrl() {
        return this.notificationUrl;
    }

    public ArchiveParams notificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
        return this;
    }

    public String[] targetTags() {
        return this.targetTags;
    }

    public ArchiveParams targetTags(String[] targetTags) {
        this.targetTags = targetTags;
        return this;
    }

    public String[] tags() {
        return this.tags;
    }

    public ArchiveParams tags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public String[] publicIds() {
        return this.publicIds;
    }

    public ArchiveParams publicIds(String[] publicIds) {
        this.publicIds = publicIds;
        return this;
    }

    public String[] fully_qualified_public_ids() {
        return this.fullyQualifiedPublicIds;
    }

    public ArchiveParams fullyQualifiedPublicIds(String[] fullyQualifiedPublicIds) {
        this.fullyQualifiedPublicIds = fullyQualifiedPublicIds;
        return this;
    }

    public String[] prefixes() {
        return this.prefixes;
    }

    public ArchiveParams prefixes(String[] prefixes) {
        this.prefixes = prefixes;
        return this;
    }

    public Transformation[] transformations() {
        return this.transformations;
    }

    public ArchiveParams transformations(Transformation[] transformations) {
        this.transformations = transformations;
        return this;
    }

    public ArchiveParams expiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public Long expiresAt() {
        return this.expiresAt;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("resource_type", this.resourceType);
        params.put("type", this.type);
        params.put("mode", this.mode);
        if (this.targetPublicId != null) {
            params.put("target_public_id", this.targetPublicId);
        }
        params.put("flatten_folders", Boolean.valueOf(this.flattenFolders));
        params.put("flatten_transformations", Boolean.valueOf(this.flattenTransformations));
        params.put("use_original_filename", Boolean.valueOf(this.useOriginalFilename));
        params.put("async", Boolean.valueOf(this.async));
        params.put("keep_derived", Boolean.valueOf(this.keepDerived));
        params.put("skip_transformation_name", Boolean.valueOf(this.skipTransformationName));
        params.put("allow_missing", Boolean.valueOf(this.allowMissing));
        if (this.notificationUrl != null) {
            params.put("notification_url", this.notificationUrl);
        }
        if (this.targetTags != null) {
            params.put("target_tags", this.targetTags);
        }
        if (this.tags != null) {
            params.put("tags", this.tags);
        }
        if (this.publicIds != null) {
            params.put("public_ids", this.publicIds);
        }
        if (this.fullyQualifiedPublicIds != null) {
            params.put("fully_qualified_public_ids", this.fullyQualifiedPublicIds);
        }
        if (this.prefixes != null) {
            params.put("prefixes", this.prefixes);
        }
        if (this.transformations != null) {
            params.put("transformations", Arrays.asList(this.transformations));
        }
        if (this.expiresAt != null) {
            params.put("expires_at", this.expiresAt);
        }
        return params;
    }
}
