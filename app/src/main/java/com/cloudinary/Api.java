package com.cloudinary;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.api.AuthorizationRequired;
import com.cloudinary.api.exceptions.AlreadyExists;
import com.cloudinary.api.exceptions.BadRequest;
import com.cloudinary.api.exceptions.GeneralError;
import com.cloudinary.api.exceptions.NotAllowed;
import com.cloudinary.api.exceptions.NotFound;
import com.cloudinary.api.exceptions.RateLimited;
import com.cloudinary.metadata.MetadataDataSource;
import com.cloudinary.metadata.MetadataField;
import com.cloudinary.strategies.AbstractApiStrategy;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.google.logging.type.LogSeverity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cloudinary.json.JSONArray;

/* JADX INFO: loaded from: classes11.dex */
public class Api {
    public static final Map<Integer, Class<? extends Exception>> CLOUDINARY_API_ERROR_CLASSES = new HashMap();
    public final Cloudinary cloudinary;
    private AbstractApiStrategy strategy;

    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    public AbstractApiStrategy getStrategy() {
        return this.strategy;
    }

    static {
        CLOUDINARY_API_ERROR_CLASSES.put(400, BadRequest.class);
        CLOUDINARY_API_ERROR_CLASSES.put(Integer.valueOf(TypedValues.CycleType.TYPE_CURVE_FIT), AuthorizationRequired.class);
        CLOUDINARY_API_ERROR_CLASSES.put(Integer.valueOf(TypedValues.CycleType.TYPE_ALPHA), NotAllowed.class);
        CLOUDINARY_API_ERROR_CLASSES.put(404, NotFound.class);
        CLOUDINARY_API_ERROR_CLASSES.put(409, AlreadyExists.class);
        CLOUDINARY_API_ERROR_CLASSES.put(Integer.valueOf(TypedValues.CycleType.TYPE_EASING), RateLimited.class);
        CLOUDINARY_API_ERROR_CLASSES.put(Integer.valueOf(LogSeverity.ERROR_VALUE), GeneralError.class);
    }

    protected ApiResponse callApi(HttpMethod method, Iterable<String> uri, Map<String, ? extends Object> params, Map options) throws Exception {
        return this.strategy.callApi(method, uri, params, options);
    }

    public Api(Cloudinary cloudinary, AbstractApiStrategy strategy) {
        this.cloudinary = cloudinary;
        this.strategy = strategy;
        this.strategy.init(this);
    }

    public ApiResponse ping(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("ping"), ObjectUtils.emptyMap(), options);
    }

    public ApiResponse usage(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        List<String> uri = new ArrayList<>();
        uri.add("usage");
        Object date = options.get("date");
        if (date != null) {
            if (date instanceof Date) {
                date = ObjectUtils.toUsageApiDateFormat((Date) date);
            }
            uri.add(date.toString());
        }
        return callApi(HttpMethod.GET, uri, ObjectUtils.emptyMap(), options);
    }

    public ApiResponse resourceTypes(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("resources"), ObjectUtils.emptyMap(), options);
    }

    public ApiResponse resources(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"));
        List<String> uri = new ArrayList<>();
        uri.add("resources");
        uri.add(resourceType);
        if (type != null) {
            uri.add(type);
        }
        ApiResponse response = callApi(HttpMethod.GET, uri, ObjectUtils.only(options, "next_cursor", "direction", "max_results", "prefix", "tags", "context", "moderations", "start_at", TtmlNode.TAG_METADATA), options);
        return response;
    }

    public ApiResponse visualSearch(Map options) throws Exception {
        List<String> uri = new ArrayList<>();
        uri.add("resources/visual_search");
        uri.add("image");
        if (options.get("text") == null && options.get("image_asset_id") == null && options.get("image_url") == null) {
            throw new IllegalArgumentException("Must supply image file, image url, image asset id or text");
        }
        ApiResponse response = callApi(HttpMethod.GET, uri, options, options);
        return response;
    }

    public ApiResponse resourcesByTag(String tag, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", resourceType, "tags", tag), ObjectUtils.only(options, "next_cursor", "direction", "max_results", "tags", "context", "moderations", TtmlNode.TAG_METADATA), options);
        return response;
    }

    public ApiResponse resourcesByContext(String key, Map options) throws Exception {
        return resourcesByContext(key, null, options);
    }

    public ApiResponse resourcesByContext(String key, String value, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "next_cursor", "direction", "max_results", "tags", "context", "moderations", TtmlNode.TAG_METADATA);
        mapOnly.put("key", key);
        if (StringUtils.isNotBlank(value)) {
            mapOnly.put("value", value);
        }
        return callApi(HttpMethod.GET, Arrays.asList("resources", resourceType, "context"), mapOnly, options);
    }

    public ApiResponse resourceByAssetID(String assetId, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", assetId), ObjectUtils.only(options, "tags", "context", "moderations"), options);
        return response;
    }

    public ApiResponse resourcesByAssetIDs(Iterable<String> assetIds, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "public_ids", "tags", "context", "moderations");
        mapOnly.put("asset_ids", assetIds);
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", "by_asset_ids"), mapOnly, options);
        return response;
    }

    public ApiResponse resourcesByAssetFolder(String assetFolder, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "next_cursor", "direction", "max_results", "tags", "context", "moderations");
        mapOnly.put("asset_folder", assetFolder);
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources/by_asset_folder"), mapOnly, options);
        return response;
    }

    public ApiResponse resourcesByIds(Iterable<String> publicIds, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "tags", "context", "moderations");
        mapOnly.put("public_ids", publicIds);
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", resourceType, type), mapOnly, options);
        return response;
    }

    public ApiResponse resourcesByModeration(String kind, String status, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", resourceType, "moderations", kind, status), ObjectUtils.only(options, "next_cursor", "direction", "max_results", "tags", "context", "moderations", TtmlNode.TAG_METADATA), options);
        return response;
    }

    public ApiResponse resource(String public_id, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        ApiResponse response = callApi(HttpMethod.GET, Arrays.asList("resources", resourceType, type, public_id), ObjectUtils.only(options, "exif", "colors", "faces", "coordinates", "image_metadata", "pages", "phash", "max_results", "quality_analysis", "cinemagraph_analysis", "accessibility_analysis", "versions", "media_metadata"), options);
        return response;
    }

    public ApiResponse update(String public_id, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map params = new HashMap();
        Util.processWriteParameters(options, params);
        params.put("moderation_status", options.get("moderation_status"));
        params.put("notification_url", options.get("notification_url"));
        ApiResponse response = callApi(HttpMethod.POST, Arrays.asList("resources", resourceType, type, public_id), params, options);
        return response;
    }

    public ApiResponse deleteResources(Iterable<String> publicIds, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "keep_original", "invalidate", "next_cursor", "transformations");
        mapOnly.put("public_ids", publicIds);
        return callApi(HttpMethod.DELETE, Arrays.asList("resources", resourceType, type), mapOnly, options);
    }

    public ApiResponse deleteDerivedByTransformation(Iterable<String> publicIds, List<Transformation> transformations, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "invalidate", "next_cursor");
        mapOnly.put("keep_original", true);
        mapOnly.put("public_ids", publicIds);
        mapOnly.put("transformations", Util.buildEager(transformations));
        return callApi(HttpMethod.DELETE, Arrays.asList("resources", resourceType, type), mapOnly, options);
    }

    public ApiResponse deleteResourcesByPrefix(String prefix, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "keep_original", "invalidate", "next_cursor");
        mapOnly.put("prefix", prefix);
        return callApi(HttpMethod.DELETE, Arrays.asList("resources", resourceType, type), mapOnly, options);
    }

    public ApiResponse deleteResourcesByTag(String tag, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        return callApi(HttpMethod.DELETE, Arrays.asList("resources", resourceType, "tags", tag), ObjectUtils.only(options, "keep_original", "invalidate", "next_cursor"), options);
    }

    public ApiResponse deleteAllResources(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "keep_original", "invalidate", "next_cursor");
        mapOnly.put(TtmlNode.COMBINE_ALL, true);
        return callApi(HttpMethod.DELETE, Arrays.asList("resources", resourceType, type), mapOnly, options);
    }

    public ApiResponse deleteDerivedResources(Iterable<String> derivedResourceIds, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.DELETE, Arrays.asList("derived_resources"), ObjectUtils.asMap("derived_resource_ids", derivedResourceIds), options);
    }

    public ApiResponse tags(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        return callApi(HttpMethod.GET, Arrays.asList("tags", resourceType), ObjectUtils.only(options, "next_cursor", "max_results", "prefix"), options);
    }

    public ApiResponse transformations(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("transformations"), ObjectUtils.only(options, "next_cursor", "max_results", "named"), options);
    }

    public ApiResponse transformation(String transformation, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "next_cursor", "max_results");
        mapOnly.put("transformation", transformation);
        return callApi(HttpMethod.GET, Arrays.asList("transformations"), mapOnly, options);
    }

    public ApiResponse deleteTransformation(String transformation, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.DELETE, Arrays.asList("transformations"), ObjectUtils.asMap("transformation", transformation), options);
    }

    public ApiResponse updateTransformation(String transformation, Map updates, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        updates.put("transformation", transformation);
        return callApi(HttpMethod.PUT, Arrays.asList("transformations"), updates, options);
    }

    public ApiResponse createTransformation(String name, String definition, Map options) throws Exception {
        return callApi(HttpMethod.POST, Arrays.asList("transformations"), ObjectUtils.asMap("transformation", definition, HintConstants.AUTOFILL_HINT_NAME, name), options);
    }

    public ApiResponse uploadPresets(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("upload_presets"), ObjectUtils.only(options, "next_cursor", "max_results"), options);
    }

    public ApiResponse uploadPreset(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("upload_presets", name), ObjectUtils.only(options, "max_results"), options);
    }

    public ApiResponse deleteUploadPreset(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.DELETE, Arrays.asList("upload_presets", name), ObjectUtils.emptyMap(), options);
    }

    public ApiResponse updateUploadPreset(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, ? extends Object> mapBuildUploadParams = Util.buildUploadParams(options);
        Util.clearEmpty(mapBuildUploadParams);
        mapBuildUploadParams.putAll(ObjectUtils.only(options, "unsigned", "disallow_public_id", "live"));
        return callApi(HttpMethod.PUT, Arrays.asList("upload_presets", name), mapBuildUploadParams, options);
    }

    public ApiResponse createUploadPreset(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, ? extends Object> mapBuildUploadParams = Util.buildUploadParams(options);
        Util.clearEmpty(mapBuildUploadParams);
        mapBuildUploadParams.putAll(ObjectUtils.only(options, HintConstants.AUTOFILL_HINT_NAME, "unsigned", "disallow_public_id", "live"));
        return callApi(HttpMethod.POST, Arrays.asList("upload_presets"), mapBuildUploadParams, options);
    }

    public ApiResponse rootFolders(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("folders"), extractParams(options, Arrays.asList("max_results", "next_cursor")), options);
    }

    public ApiResponse subFolders(String ofFolderPath, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("folders", ofFolderPath), extractParams(options, Arrays.asList("max_results", "next_cursor")), options);
    }

    public ApiResponse createFolder(String folderName, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.POST, Arrays.asList("folders", folderName), ObjectUtils.emptyMap(), options);
    }

    public ApiResponse restore(Iterable<String> publicIds, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        Map params = new HashMap();
        params.put("public_ids", publicIds);
        params.put("versions", options.get("versions"));
        ApiResponse response = callApi(HttpMethod.POST, Arrays.asList("resources", resourceType, type, "restore"), params, options);
        return response;
    }

    public ApiResponse uploadMappings(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("upload_mappings"), ObjectUtils.only(options, "next_cursor", "max_results"), options);
    }

    public ApiResponse uploadMapping(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.GET, Arrays.asList("upload_mappings"), ObjectUtils.asMap("folder", name), options);
    }

    public ApiResponse deleteUploadMapping(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callApi(HttpMethod.DELETE, Arrays.asList("upload_mappings"), ObjectUtils.asMap("folder", name), options);
    }

    public ApiResponse updateUploadMapping(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map params = new HashMap();
        params.put("folder", name);
        params.putAll(ObjectUtils.only(options, "template"));
        return callApi(HttpMethod.PUT, Arrays.asList("upload_mappings"), params, options);
    }

    public ApiResponse createUploadMapping(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map params = new HashMap();
        params.put("folder", name);
        params.putAll(ObjectUtils.only(options, "template"));
        return callApi(HttpMethod.POST, Arrays.asList("upload_mappings"), params, options);
    }

    public ApiResponse publishByPrefix(String prefix, Map options) throws Exception {
        return publishResource("prefix", prefix, options);
    }

    public ApiResponse publishByTag(String tag, Map options) throws Exception {
        return publishResource("tag", tag, options);
    }

    public ApiResponse publishByIds(Iterable<String> publicIds, Map options) throws Exception {
        return publishResource("public_ids", publicIds, options);
    }

    private ApiResponse publishResource(String byKey, Object value, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        List<String> uri = new ArrayList<>();
        uri.add("resources");
        uri.add(resourceType);
        uri.add("publish_resources");
        Map<String, ? extends Object> params = new HashMap<>();
        params.put(byKey, value);
        params.putAll(ObjectUtils.only(options, "invalidate", "overwrite", "type"));
        return callApi(HttpMethod.POST, uri, params, options);
    }

    public ApiResponse createStreamingProfile(String name, String displayName, List<Map> representations, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        List<Map> serializedRepresentations = new ArrayList<>(representations.size());
        for (Map t : representations) {
            Object transformation = t.get("transformation");
            serializedRepresentations.add(ObjectUtils.asMap("transformation", transformation.toString()));
        }
        List<String> uri = Collections.singletonList("streaming_profiles");
        Map<String, ? extends Object> mapAsMap = ObjectUtils.asMap(HintConstants.AUTOFILL_HINT_NAME, name, "representations", new JSONArray(serializedRepresentations.toArray()));
        if (displayName != null) {
            mapAsMap.put("display_name", displayName);
        }
        return callApi(HttpMethod.POST, uri, mapAsMap, options);
    }

    public ApiResponse createStreamingProfile(String name, String displayName, List<Map> representations) throws Exception {
        return createStreamingProfile(name, displayName, representations, null);
    }

    public ApiResponse getStreamingProfile(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        List<String> uri = Arrays.asList("streaming_profiles", name);
        return callApi(HttpMethod.GET, uri, ObjectUtils.emptyMap(), options);
    }

    public ApiResponse getStreamingProfile(String name) throws Exception {
        return getStreamingProfile(name, null);
    }

    public ApiResponse listStreamingProfiles(Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        List<String> uri = Collections.singletonList("streaming_profiles");
        return callApi(HttpMethod.GET, uri, ObjectUtils.emptyMap(), options);
    }

    public ApiResponse listStreamingProfiles() throws Exception {
        return listStreamingProfiles(null);
    }

    public ApiResponse deleteStreamingProfile(String name, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        List<String> uri = Arrays.asList("streaming_profiles", name);
        return callApi(HttpMethod.DELETE, uri, ObjectUtils.emptyMap(), options);
    }

    public ApiResponse deleteStreamingProfile(String name) throws Exception {
        return deleteStreamingProfile(name, null);
    }

    public ApiResponse updateStreamingProfile(String name, String displayName, List<Map> representations, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map params = new HashMap();
        List<String> uri = Arrays.asList("streaming_profiles", name);
        if (representations != null) {
            List<Map> serializedRepresentations = new ArrayList<>(representations.size());
            for (Map t : representations) {
                Object transformation = t.get("transformation");
                serializedRepresentations.add(ObjectUtils.asMap("transformation", transformation.toString()));
            }
            params.put("representations", new JSONArray(serializedRepresentations.toArray()));
        }
        if (displayName != null) {
            params.put("display_name", displayName);
        }
        return callApi(HttpMethod.PUT, uri, params, options);
    }

    public ApiResponse updateStreamingProfile(String name, String displayName, List<Map> representations) throws Exception {
        return createStreamingProfile(name, displayName, representations);
    }

    public ApiResponse updateResourcesAccessModeByPrefix(String accessMode, String prefix, Map options) throws Exception {
        return updateResourcesAccessMode(accessMode, "prefix", prefix, options);
    }

    public ApiResponse updateResourcesAccessModeByTag(String accessMode, String tag, Map options) throws Exception {
        return updateResourcesAccessMode(accessMode, "tag", tag, options);
    }

    public ApiResponse deleteFolder(String folder, Map options) throws Exception {
        List<String> uri = Arrays.asList("folders", folder);
        return callApi(HttpMethod.DELETE, uri, Collections.emptyMap(), options);
    }

    public ApiResponse updateResourcesAccessModeByIds(String accessMode, Iterable<String> publicIds, Map options) throws Exception {
        return updateResourcesAccessMode(accessMode, "public_ids", publicIds, options);
    }

    private ApiResponse updateResourcesAccessMode(String accessMode, String byKey, Object value, Map options) throws Exception {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        String resourceType = ObjectUtils.asString(options.get("resource_type"), "image");
        String type = ObjectUtils.asString(options.get("type"), "upload");
        List<String> uri = Arrays.asList("resources", resourceType, type, "update_access_mode");
        Map<String, ? extends Object> mapOnly = ObjectUtils.only(options, "next_cursor", "max_results");
        mapOnly.put("access_mode", accessMode);
        mapOnly.put(byKey, value);
        return callApi(HttpMethod.POST, uri, mapOnly, options);
    }

    public ApiResponse addMetadataField(MetadataField field) throws Exception {
        return callApi(HttpMethod.POST, Collections.singletonList("metadata_fields"), ObjectUtils.toMap(field), ObjectUtils.asMap("content_type", "json"));
    }

    public ApiResponse listMetadataFields() throws Exception {
        return callApi(HttpMethod.GET, Collections.singletonList("metadata_fields"), Collections.emptyMap(), Collections.emptyMap());
    }

    public ApiResponse metadataFieldByFieldId(String fieldExternalId) throws Exception {
        return callApi(HttpMethod.GET, Arrays.asList("metadata_fields", fieldExternalId), Collections.emptyMap(), Collections.emptyMap());
    }

    public ApiResponse updateMetadataField(String fieldExternalId, MetadataField field) throws Exception {
        List<String> uri = Arrays.asList("metadata_fields", fieldExternalId);
        return callApi(HttpMethod.PUT, uri, ObjectUtils.toMap(field), Collections.singletonMap("content_type", "json"));
    }

    public ApiResponse updateMetadataFieldDatasource(String fieldExternalId, List<MetadataDataSource.Entry> entries) throws Exception {
        List<String> uri = Arrays.asList("metadata_fields", fieldExternalId, "datasource");
        return callApi(HttpMethod.PUT, uri, Collections.singletonMap("values", entries), Collections.singletonMap("content_type", "json"));
    }

    public ApiResponse deleteDatasourceEntries(String fieldExternalId, List<String> entriesExternalId) throws Exception {
        List<String> uri = Arrays.asList("metadata_fields", fieldExternalId, "datasource");
        return callApi(HttpMethod.DELETE, uri, Collections.singletonMap("external_ids", entriesExternalId), Collections.emptyMap());
    }

    public ApiResponse restoreDatasourceEntries(String fieldExternalId, List<String> entriesExternalId) throws Exception {
        List<String> uri = Arrays.asList("metadata_fields", fieldExternalId, "datasource_restore");
        return callApi(HttpMethod.POST, uri, Collections.singletonMap("external_ids", entriesExternalId), Collections.singletonMap("content_type", "json"));
    }

    public ApiResponse deleteMetadataField(String fieldExternalId) throws Exception {
        List<String> uri = Arrays.asList("metadata_fields", fieldExternalId);
        return callApi(HttpMethod.DELETE, uri, Collections.emptyMap(), Collections.emptyMap());
    }

    public ApiResponse reorderMetadataFields(String orderBy, String direction, Map options) throws Exception {
        if (orderBy == null) {
            throw new IllegalArgumentException("Must supply orderBy");
        }
        List<String> uri = Arrays.asList("metadata_fields", "order");
        Map<String, ? extends Object> mapAsMap = ObjectUtils.asMap("order_by", orderBy);
        if (direction != null) {
            mapAsMap.put("direction", direction);
        }
        return callApi(HttpMethod.PUT, uri, mapAsMap, options);
    }

    private Map<String, ?> extractParams(Map options, List<String> keys) {
        Map<String, Object> result = new HashMap<>();
        for (String key : keys) {
            Object option = options.get(key);
            if (option != null) {
                result.put(key, option);
            }
        }
        return result;
    }
}
