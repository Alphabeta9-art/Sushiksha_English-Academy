package com.cloudinary.metadata;

import java.text.ParseException;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class MetadataField<T> extends JSONObject {
    public static final String DEFAULT_VALUE = "default_value";
    public static final String EXTERNAL_ID = "external_id";
    public static final String LABEL = "label";
    public static final String MANDATORY = "mandatory";
    public static final String TYPE = "type";
    public static final String VALIDATION = "validation";

    public MetadataField(MetadataFieldType type) {
        put("type", type.toString());
    }

    public MetadataField(String type) {
        put("type", type);
    }

    public MetadataFieldType getType() {
        return MetadataFieldType.valueOf(optString("type").toUpperCase());
    }

    public String getExternalId() {
        return optString(EXTERNAL_ID);
    }

    public void setExternalId(String externalId) {
        put(EXTERNAL_ID, externalId);
    }

    public String getLabel() {
        return optString(LABEL);
    }

    public void setLabel(String label) {
        put(LABEL, label);
    }

    public boolean isMandatory() {
        return optBoolean(MANDATORY);
    }

    public void setMandatory(Boolean mandatory) {
        put(MANDATORY, mandatory);
    }

    public T getDefaultValue() throws ParseException {
        return (T) opt(DEFAULT_VALUE);
    }

    public void setDefaultValue(T defaultValue) {
        put(DEFAULT_VALUE, defaultValue);
    }

    public MetadataValidation getValidation() {
        return (MetadataValidation) optJSONObject(VALIDATION);
    }

    public void setValidation(MetadataValidation validation) {
        put(VALIDATION, validation);
    }

    public MetadataDataSource getDataSource() {
        return (MetadataDataSource) optJSONObject("datasource");
    }

    public void setDataSource(MetadataDataSource dataSource) {
        put("datasource", dataSource);
    }
}
