package com.cloudinary;

import com.cloudinary.utils.StringUtils;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class BaseParam {
    private String param;

    protected BaseParam(List<String> components) {
        this.param = StringUtils.join(components, ":");
    }

    protected BaseParam(String... components) {
        this.param = StringUtils.join(components, ":");
    }

    public String toString() {
        return this.param;
    }
}
