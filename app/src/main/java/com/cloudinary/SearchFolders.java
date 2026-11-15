package com.cloudinary;

import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class SearchFolders extends Search {
    public SearchFolders(Cloudinary cloudinary) {
        super(cloudinary);
    }

    @Override // com.cloudinary.Search
    public ApiResponse execute() throws Exception {
        Map<String, String> options = ObjectUtils.asMap("content_type", "json");
        return this.api.callApi(Api.HttpMethod.POST, Arrays.asList("folders", "search"), toQuery(), options);
    }
}
