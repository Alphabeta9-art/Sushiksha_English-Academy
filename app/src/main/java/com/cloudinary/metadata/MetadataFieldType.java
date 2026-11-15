package com.cloudinary.metadata;

/* JADX INFO: loaded from: classes11.dex */
public enum MetadataFieldType {
    STRING,
    INTEGER,
    DATE,
    ENUM,
    SET;

    @Override // java.lang.Enum
    public String toString() {
        return super.toString().toLowerCase();
    }
}
