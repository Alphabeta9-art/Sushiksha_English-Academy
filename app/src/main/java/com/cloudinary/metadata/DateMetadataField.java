package com.cloudinary.metadata;

import com.cloudinary.utils.ObjectUtils;
import java.text.ParseException;
import java.util.Date;

/* JADX INFO: loaded from: classes11.dex */
public class DateMetadataField extends MetadataField<Date> {
    public DateMetadataField() {
        super(MetadataFieldType.DATE);
    }

    @Override // com.cloudinary.metadata.MetadataField
    public void setDefaultValue(Date defaultValue) {
        put(MetadataField.DEFAULT_VALUE, ObjectUtils.toISO8601DateOnly(defaultValue));
    }

    @Override // com.cloudinary.metadata.MetadataField
    public Date getDefaultValue() throws ParseException {
        Object value = get(MetadataField.DEFAULT_VALUE);
        if (value == null) {
            return null;
        }
        return ObjectUtils.fromISO8601DateOnly(value.toString());
    }
}
