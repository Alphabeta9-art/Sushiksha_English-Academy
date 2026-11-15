package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.Base64Coder;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class Search {
    protected final Api api;
    private int ttl = 300;
    private HashMap<String, Object> params = new HashMap<>();
    private ArrayList<HashMap<String, Object>> sortByParam = new ArrayList<>();
    private ArrayList<String> aggregateParam = new ArrayList<>();
    private ArrayList<String> withFieldParam = new ArrayList<>();

    Search(Cloudinary cloudinary) {
        this.api = cloudinary.api();
    }

    public Search ttl(int ttl) {
        this.ttl = ttl;
        return this;
    }

    public Search expression(String value) {
        this.params.put("expression", value);
        return this;
    }

    public Search maxResults(Integer value) {
        this.params.put("max_results", value);
        return this;
    }

    public Search nextCursor(String value) {
        this.params.put("next_cursor", value);
        return this;
    }

    public Search aggregate(String field) {
        if (!this.aggregateParam.contains(field)) {
            this.aggregateParam.add(field);
        }
        return this;
    }

    public Search withField(String field) {
        if (!this.withFieldParam.contains(field)) {
            this.withFieldParam.add(field);
        }
        return this;
    }

    public Search sortBy(String field, String dir) {
        HashMap<String, Object> sortBucket = new HashMap<>();
        sortBucket.put(field, dir);
        for (int i = 0; i < this.sortByParam.size(); i++) {
            if (this.sortByParam.get(i).containsKey(field)) {
                this.sortByParam.add(i, sortBucket);
                return this;
            }
        }
        this.sortByParam.add(sortBucket);
        return this;
    }

    public HashMap<String, Object> toQuery() {
        HashMap<String, Object> queryParams = new HashMap<>(this.params);
        if (this.withFieldParam.size() > 0) {
            queryParams.put("with_field", this.withFieldParam);
        }
        if (this.sortByParam.size() > 0) {
            queryParams.put("sort_by", this.sortByParam);
        }
        if (this.aggregateParam.size() > 0) {
            queryParams.put("aggregate", this.aggregateParam);
        }
        return queryParams;
    }

    public ApiResponse execute() throws Exception {
        Map<String, String> options = ObjectUtils.asMap("content_type", "json");
        return this.api.callApi(Api.HttpMethod.POST, Arrays.asList("resources", "search"), toQuery(), options);
    }

    public String toUrl() throws Exception {
        return toUrl(null, null);
    }

    public String toUrl(String nextCursor) throws Exception {
        return toUrl(null, nextCursor);
    }

    public String toUrl(Integer ttl, String nextCursor) throws Exception {
        String nextCursorParam = nextCursor;
        String apiSecret = this.api.cloudinary.config.apiSecret;
        if (apiSecret == null) {
            throw new IllegalArgumentException("Must supply api_secret");
        }
        if (ttl == null) {
            ttl = Integer.valueOf(this.ttl);
        }
        HashMap<String, Object> query = toQuery();
        if (nextCursorParam == null) {
            nextCursorParam = (String) query.get("next_cursor");
        }
        query.remove("next_cursor");
        JSONObject json = ObjectUtils.toJSON(query);
        String base64Query = Base64Coder.encodeURLSafeString(json.toString());
        String signature = StringUtils.encodeHexString(Util.hash(String.format("%d%s%s", ttl, base64Query, apiSecret), SignatureAlgorithm.SHA256));
        String prefix = Url.unsignedDownloadUrlPrefix(null, this.api.cloudinary.config);
        return String.format("%s/search/%s/%d/%s%s", prefix, signature, ttl, base64Query, (nextCursorParam == null || nextCursorParam.isEmpty()) ? "" : DomExceptionUtils.SEPARATOR + nextCursorParam);
    }
}
