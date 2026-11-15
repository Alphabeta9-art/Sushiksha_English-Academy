package com.cloudinary.metadata;

import java.util.List;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class MetadataDataSource extends JSONObject {
    public MetadataDataSource(List<Entry> entries) {
        put("values", new JSONArray(entries.toArray()));
    }

    public static class Entry extends JSONObject {
        public Entry(String externalId, String value) {
            setExternalId(externalId);
            setValue(value);
        }

        public Entry(String value) {
            this(null, value);
        }

        public void setExternalId(String externalId) {
            put(MetadataField.EXTERNAL_ID, externalId);
        }

        public String getExternalId() {
            return optString(MetadataField.EXTERNAL_ID);
        }

        public void setValue(String value) {
            put("value", value);
        }

        public String getValue() {
            return optString("value");
        }
    }
}
