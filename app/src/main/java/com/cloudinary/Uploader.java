package com.cloudinary;

import androidx.core.provider.FontsContractCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.strategies.AbstractUploaderStrategy;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class Uploader {
    public static final int BUFFER_SIZE = 20000000;
    private static final String[] TEXT_PARAMS = {"public_id", "font_family", "font_size", "font_color", "text_align", FontsContractCompat.Columns.WEIGHT, "font_style", "background", "opacity", "text_decoration"};
    private Cloudinary cloudinary;
    private AbstractUploaderStrategy strategy;

    private final class Command {
        static final String add = "add";
        static final String remove = "remove";
        static final String removeAll = "remove_all";
        static final String replace = "replace";

        private Command() {
        }
    }

    public Map callApi(String action, Map<String, Object> params, Map options, Object file) throws IOException {
        return this.strategy.callApi(action, params, options, file, null);
    }

    public Map callApi(String action, Map<String, Object> params, Map options, Object file, ProgressCallback progressCallback) throws IOException {
        return this.strategy.callApi(action, params, options, file, progressCallback);
    }

    public Uploader(Cloudinary cloudinary, AbstractUploaderStrategy strategy) {
        this.cloudinary = cloudinary;
        this.strategy = strategy;
        strategy.init(this);
    }

    public Cloudinary cloudinary() {
        return this.cloudinary;
    }

    public Map<String, Object> buildUploadParams(Map options) {
        return Util.buildUploadParams(options);
    }

    public Map unsignedUpload(Object file, String uploadPreset, Map options) throws IOException {
        return unsignedUpload(file, uploadPreset, options, null);
    }

    public Map unsignedUpload(Object file, String uploadPreset, Map options, ProgressCallback progressCallback) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        HashMap nextOptions = new HashMap(options);
        nextOptions.put("unsigned", true);
        nextOptions.put("upload_preset", uploadPreset);
        return upload(file, nextOptions, progressCallback);
    }

    public Map upload(Object file, Map options) throws IOException {
        return upload(file, options, null);
    }

    public Map upload(Object file, Map options, ProgressCallback progressCallback) throws IOException {
        Map options2;
        if (options != null) {
            options2 = options;
        } else {
            options2 = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = buildUploadParams(options2);
        return callApi("upload", params, options2, file, progressCallback);
    }

    public Map uploadLargeRaw(Object file, Map options) throws IOException {
        return uploadLargeRaw(file, options, BUFFER_SIZE, null);
    }

    public Map uploadLargeRaw(Object file, Map options, ProgressCallback progressCallback) throws IOException {
        return uploadLargeRaw(file, options, BUFFER_SIZE, progressCallback);
    }

    public Map uploadLargeRaw(Object file, Map options, int bufferSize) throws IOException {
        return uploadLargeRaw(file, options, bufferSize, null);
    }

    public Map uploadLargeRaw(Object file, Map options, int bufferSize, ProgressCallback callback) throws IOException {
        Map sentOptions = new HashMap();
        sentOptions.putAll(options);
        sentOptions.put("resource_type", "raw");
        return uploadLarge(file, sentOptions, bufferSize, callback);
    }

    public Map uploadLarge(Object file, Map options) throws IOException {
        return uploadLarge(file, options, (ProgressCallback) null);
    }

    public Map uploadLarge(Object file, Map options, ProgressCallback progressCallback) throws IOException {
        int bufferSize = ObjectUtils.asInteger(options.get("chunk_size"), Integer.valueOf(BUFFER_SIZE)).intValue();
        return uploadLarge(file, options, bufferSize, progressCallback);
    }

    public Map uploadLarge(Object file, Map options, int bufferSize) throws IOException {
        return uploadLarge(file, options, bufferSize, null);
    }

    public Map uploadLarge(Object file, Map options, int bufferSize, ProgressCallback progressCallback) throws IOException {
        return uploadLarge(file, options, bufferSize, 0L, null, progressCallback);
    }

    public Map uploadLarge(Object file, Map options, int bufferSize, long offset, String uniqueUploadId, ProgressCallback progressCallback) throws IOException {
        boolean remote;
        String filename;
        long length;
        InputStream input;
        Map result;
        if (file instanceof InputStream) {
            remote = false;
            filename = null;
            input = (InputStream) file;
            length = -1;
        } else if (file instanceof File) {
            long length2 = ((File) file).length();
            String filename2 = ((File) file).getName();
            remote = false;
            filename = filename2;
            input = new FileInputStream((File) file);
            length = length2;
        } else if (file instanceof byte[]) {
            long length3 = ((byte[]) file).length;
            remote = false;
            filename = null;
            input = new ByteArrayInputStream((byte[]) file);
            length = length3;
        } else if (StringUtils.isRemoteUrl(file.toString())) {
            remote = true;
            filename = null;
            input = null;
            length = -1;
        } else {
            File f = new File(file.toString());
            long length4 = f.length();
            String filename3 = f.getName();
            remote = false;
            filename = filename3;
            length = length4;
            input = new FileInputStream(f);
        }
        try {
            if (remote) {
                result = upload(file, options);
            } else {
                if (!options.containsKey("filename") && StringUtils.isNotBlank(filename)) {
                    options.put("filename", filename);
                }
                result = uploadLargeParts(input, options, bufferSize, length, offset, uniqueUploadId, progressCallback);
            }
            if (input != null) {
                input.close();
            }
            return result;
        } catch (Throwable th) {
            if (input != null) {
                input.close();
            }
            throw th;
        }
    }

    private Map uploadLargeParts(InputStream input, Map options, int bufferSize, long length, long offset, String uniqueUploadId, final ProgressCallback progressCallback) throws IOException {
        byte[] buffer;
        long length2;
        Map sentParams;
        ProgressCallback singlePartProgressCallback;
        Map sentOptions;
        InputStream inputStream = input;
        Uploader uploader = this;
        Map<String, Object> mapBuildUploadParams = uploader.buildUploadParams(options);
        Map sentOptions2 = new HashMap();
        sentOptions2.putAll(options);
        HashMap map = new HashMap();
        map.put("X-Unique-Upload-Id", StringUtils.isBlank(uniqueUploadId) ? uploader.cloudinary().randomPublicId() : uniqueUploadId);
        sentOptions2.put("extra_headers", map);
        byte[] buffer2 = new byte[bufferSize];
        byte[] nibbleBuffer = new byte[1];
        int partNumber = 0;
        final long knownLengthBeforeUpload = length;
        inputStream.skip(offset);
        int currentBufferSize = 0;
        long totalBytesUploaded = offset;
        long totalBytesUploaded2 = offset;
        long length3 = length;
        while (true) {
            int bytesRead = inputStream.read(buffer2, currentBufferSize, bufferSize - currentBufferSize);
            boolean atEnd = bytesRead == -1;
            boolean fullBuffer = !atEnd && bytesRead + currentBufferSize == bufferSize;
            if (!atEnd) {
                currentBufferSize += bytesRead;
            }
            if (atEnd || fullBuffer) {
                byte[] buffer3 = buffer2;
                totalBytesUploaded2 += (long) currentBufferSize;
                long currentLoc = offset + ((long) (bufferSize * partNumber));
                if (!atEnd) {
                    bytesRead = inputStream.read(nibbleBuffer, 0, 1);
                    atEnd = bytesRead == -1;
                }
                if (!atEnd) {
                    buffer = buffer3;
                    length2 = length3;
                } else {
                    if (length3 == -1) {
                        length3 = totalBytesUploaded2;
                    }
                    byte[] finalBuffer = new byte[currentBufferSize];
                    System.arraycopy(buffer3, 0, finalBuffer, 0, currentBufferSize);
                    buffer = finalBuffer;
                    length2 = length3;
                }
                String range = String.format(Locale.US, "bytes %d-%d/%d", Long.valueOf(currentLoc), Long.valueOf((currentLoc + ((long) currentBufferSize)) - 1), Long.valueOf(length2));
                map.put(HttpHeaders.CONTENT_RANGE, range);
                Map sentParams2 = new HashMap();
                sentParams2.putAll(mapBuildUploadParams);
                final long bytesUploadedSoFar = totalBytesUploaded;
                if (progressCallback == null) {
                    sentParams = sentParams2;
                    singlePartProgressCallback = null;
                } else {
                    sentParams = sentParams2;
                    singlePartProgressCallback = new ProgressCallback() { // from class: com.cloudinary.Uploader.1
                        @Override // com.cloudinary.ProgressCallback
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            progressCallback.onProgress(bytesUploadedSoFar + bytesUploaded, knownLengthBeforeUpload);
                        }
                    };
                }
                byte[] buffer4 = buffer;
                sentOptions = sentOptions2;
                Map response = callApi("upload", sentParams, sentOptions, buffer4, singlePartProgressCallback);
                if (!atEnd) {
                    buffer4[0] = nibbleBuffer[0];
                    totalBytesUploaded += (long) currentBufferSize;
                    partNumber++;
                    currentBufferSize = 1;
                    buffer2 = buffer4;
                } else {
                    return response;
                }
            } else {
                nibbleBuffer = nibbleBuffer;
                length2 = length3;
                knownLengthBeforeUpload = knownLengthBeforeUpload;
                sentOptions = sentOptions2;
            }
            uploader = this;
            inputStream = input;
            sentOptions2 = sentOptions;
            length3 = length2;
            nibbleBuffer = nibbleBuffer;
            knownLengthBeforeUpload = knownLengthBeforeUpload;
        }
    }

    public Map destroy(String publicId, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("type", (String) options.get("type"));
        params.put("public_id", publicId);
        params.put("invalidate", ObjectUtils.asBoolean(options.get("invalidate"), false).toString());
        params.put("notification_url", (String) options.get("notification_url"));
        return callApi("destroy", params, options, null);
    }

    public Map rename(String fromPublicId, String toPublicId, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("type", (String) options.get("type"));
        params.put("overwrite", ObjectUtils.asBoolean(options.get("overwrite"), false).toString());
        params.put("from_public_id", fromPublicId);
        params.put("to_public_id", toPublicId);
        params.put("invalidate", ObjectUtils.asBoolean(options.get("invalidate"), false).toString());
        params.put("to_type", options.get("to_type"));
        params.put("context", ObjectUtils.asBoolean(options.get("context"), false).toString());
        params.put(TtmlNode.TAG_METADATA, ObjectUtils.asBoolean(options.get(TtmlNode.TAG_METADATA), false).toString());
        params.put("notification_url", (String) options.get("notification_url"));
        return callApi("rename", params, options, null);
    }

    public Map explicit(String publicId, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = buildUploadParams(options);
        params.put("public_id", publicId);
        return callApi("explicit", params, options, null);
    }

    @Deprecated
    public Map generate_sprite(String tag, Map options) throws IOException {
        return generateSprite(tag, options);
    }

    public Map generateSprite(String tag, Map options) throws IOException {
        if (options == null) {
            options = Collections.singletonMap("tag", tag);
        } else {
            options.put("tag", tag);
        }
        return callApi("sprite", Util.buildGenerateSpriteParams(options), options, null);
    }

    public Map generateSprite(String[] urls, Map options) throws IOException {
        if (options == null) {
            options = Collections.singletonMap("urls", urls);
        } else {
            options.put("urls", urls);
        }
        return callApi("sprite", Util.buildGenerateSpriteParams(options), options, null);
    }

    public Map multi(String[] urls, Map options) throws IOException {
        if (options == null) {
            options = Collections.singletonMap("urls", urls);
        } else {
            options.put("urls", urls);
        }
        return multi(options);
    }

    public Map multi(String tag, Map options) throws IOException {
        if (options == null) {
            options = Collections.singletonMap("tag", tag);
        } else {
            options.put("tag", tag);
        }
        return multi(options);
    }

    private Map multi(Map options) throws IOException {
        return callApi("multi", Util.buildMultiParams(options), options, null);
    }

    public Map explode(String public_id, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        Object transformation = options.get("transformation");
        if (transformation != null) {
            if (transformation instanceof Transformation) {
                transformation = ((Transformation) transformation).generate();
            }
            params.put("transformation", transformation.toString());
        }
        params.put("public_id", public_id);
        params.put("notification_url", (String) options.get("notification_url"));
        params.put("format", (String) options.get("format"));
        return callApi("explode", params, options, null);
    }

    public Map addTag(String tag, String[] publicIds, Map options) throws IOException {
        return addTag(new String[]{tag}, publicIds, options);
    }

    public Map addTag(String[] tag, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        boolean exclusive = ObjectUtils.asBoolean(options.get("exclusive"), false).booleanValue();
        String command = exclusive ? "set_exclusive" : "add";
        return callTagsApi(tag, command, publicIds, options);
    }

    public Map removeTag(String tag, String[] publicIds, Map options) throws IOException {
        return removeTag(new String[]{tag}, publicIds, options);
    }

    public Map removeTag(String[] tag, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callTagsApi(tag, "remove", publicIds, options);
    }

    public Map removeAllTags(String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callTagsApi(null, "remove_all", publicIds, options);
    }

    public Map replaceTag(String tag, String[] publicIds, Map options) throws IOException {
        return replaceTag(new String[]{tag}, publicIds, options);
    }

    public Map replaceTag(String[] tag, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        return callTagsApi(tag, "replace", publicIds, options);
    }

    public Map callTagsApi(String[] tag, String command, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        if (tag != null) {
            params.put("tag", StringUtils.join(tag, ","));
        }
        params.put("command", command);
        params.put("type", (String) options.get("type"));
        params.put("public_ids", Arrays.asList(publicIds));
        return callApi("tags", params, options, null);
    }

    public Map addContext(Map context, String[] publicIds, Map options) throws IOException {
        return callContextApi(context, "add", publicIds, options);
    }

    public Map addContext(String context, String[] publicIds, Map options) throws IOException {
        return callContextApi(context, "add", publicIds, options);
    }

    public Map removeAllContext(String[] publicIds, Map options) throws IOException {
        return callContextApi((String) null, "remove_all", publicIds, options);
    }

    protected Map callContextApi(Map context, String command, String[] publicIds, Map options) throws IOException {
        return callContextApi(Util.encodeContext(context), command, publicIds, options);
    }

    protected Map callContextApi(String context, String command, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        if (context != null) {
            params.put("context", context);
        }
        params.put("command", command);
        params.put("type", (String) options.get("type"));
        params.put("public_ids", Arrays.asList(publicIds));
        return callApi("context", params, options, null);
    }

    public Map text(String text, Map options) throws IOException {
        if (options == null) {
            options = ObjectUtils.emptyMap();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);
        for (String param : TEXT_PARAMS) {
            params.put(param, ObjectUtils.asString(options.get(param)));
        }
        return callApi("text", params, options, null);
    }

    public Map createArchive(Map options, String targetFormat) throws IOException {
        return callApi("generate_archive", Util.buildArchiveParams(options, targetFormat), options, null);
    }

    public Map createZip(Map options) throws IOException {
        return createArchive(options, ArchiveParams.FORMAT_ZIP);
    }

    public Map createArchive(ArchiveParams params) throws IOException {
        return createArchive(params.toMap(), params.targetFormat());
    }

    public void signRequestParams(Map<String, Object> params, Map options) {
        if (!params.containsKey("timestamp")) {
            params.put("timestamp", Util.timestamp());
        }
        this.cloudinary.signRequest(params, options);
    }

    public String uploadTagParams(Map options) {
        if (options == null) {
            options = new HashMap();
        }
        if (options.get("resource_type") == null) {
            options = new HashMap(options);
            options.put("resource_type", "auto");
        }
        String callback = ObjectUtils.asString(options.get("callback"), this.cloudinary.config.callback);
        if (callback == null) {
            throw new IllegalArgumentException("Must supply callback");
        }
        options.put("callback", callback);
        Map<String, Object> params = buildUploadParams(options);
        if (options.get("unsigned") == null || Boolean.FALSE.equals(options.get("unsigned"))) {
            signRequestParams(params, options);
        } else {
            Util.clearEmpty(params);
        }
        return JSONObject.valueToString(params);
    }

    public String getUploadUrl(Map options) {
        if (options == null) {
            options = new HashMap();
        }
        return this.cloudinary.cloudinaryApiUrl("upload", options);
    }

    public String unsignedImageUploadTag(String field, String uploadPreset, Map options, Map<String, Object> htmlOptions) {
        Map nextOptions = new HashMap(options);
        nextOptions.put("upload_preset", uploadPreset);
        nextOptions.put("unsigned", true);
        return imageUploadTag(field, nextOptions, htmlOptions);
    }

    public String imageUploadTag(String field, Map options, Map<String, Object> htmlOptions) {
        if (htmlOptions == null) {
            htmlOptions = ObjectUtils.emptyMap();
        }
        String tagParams = StringUtils.escapeHtml(uploadTagParams(options));
        String cloudinaryUploadUrl = getUploadUrl(options);
        StringBuilder builder = new StringBuilder();
        builder.append("<input type='file' name='file' data-url='").append(cloudinaryUploadUrl).append("' data-form-data='").append(tagParams).append("' data-cloudinary-field='").append(field).append("'");
        if (options.containsKey("chunk_size")) {
            builder.append(" data-max-chunk-size='").append(options.get("chunk_size")).append("'");
        }
        builder.append(" class='cloudinary-fileupload");
        if (htmlOptions.containsKey("class")) {
            builder.append(" ").append(htmlOptions.get("class"));
        }
        for (Map.Entry<String, Object> htmlOption : htmlOptions.entrySet()) {
            if (!htmlOption.getKey().equals("class")) {
                builder.append("' ").append(htmlOption.getKey()).append("='").append(StringUtils.escapeHtml(ObjectUtils.asString(htmlOption.getValue())));
            }
        }
        builder.append("'/>");
        return builder.toString();
    }

    public Map deleteByToken(String token) throws Exception {
        return callApi("delete_by_token", ObjectUtils.asMap("token", token), ObjectUtils.emptyMap(), null);
    }

    public Map updateMetadata(Map metadata, String[] publicIds, Map options) throws IOException {
        if (options == null) {
            options = new HashMap();
        }
        Map<String, Object> params = new HashMap<>();
        params.put(TtmlNode.TAG_METADATA, Util.encodeContext(metadata));
        params.put("public_ids", Arrays.asList(publicIds));
        params.put("type", (String) options.get("type"));
        return callApi(TtmlNode.TAG_METADATA, params, options, null);
    }
}
