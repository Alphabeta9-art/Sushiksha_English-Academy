package com.cloudinary.transformation;

import com.cloudinary.transformation.AbstractLayer;
import com.cloudinary.utils.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractLayer<T extends AbstractLayer<T>> implements Serializable {
    protected String resourceType = null;
    protected String type = null;
    protected String publicId = null;
    protected String format = null;

    abstract T getThis();

    public T resourceType(String str) {
        this.resourceType = str;
        return (T) getThis();
    }

    public T type(String str) {
        this.type = str;
        return (T) getThis();
    }

    public T publicId(String str) {
        this.publicId = str.replace('/', AbstractJsonLexerKt.COLON);
        return (T) getThis();
    }

    public T format(String str) {
        this.format = str;
        return (T) getThis();
    }

    public String toString() {
        ArrayList<String> components = new ArrayList<>();
        if (this.resourceType != null && !this.resourceType.equals("image")) {
            components.add(this.resourceType);
        }
        if (this.type != null && !this.type.equals("upload")) {
            components.add(this.type);
        }
        if (this.publicId == null) {
            throw new IllegalArgumentException("Must supply publicId");
        }
        components.add(formattedPublicId());
        return StringUtils.join((List<String>) components, ":");
    }

    protected String formattedPublicId() {
        String transientPublicId = this.publicId;
        if (this.format != null) {
            return transientPublicId + "." + this.format;
        }
        return transientPublicId;
    }
}
