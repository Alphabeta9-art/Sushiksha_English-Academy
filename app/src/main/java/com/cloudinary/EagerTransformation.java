package com.cloudinary;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.cloudinary.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class EagerTransformation extends Transformation<EagerTransformation> {
    protected String format;

    public EagerTransformation(List<Map> transformations) {
        super(transformations);
    }

    public EagerTransformation() {
    }

    public EagerTransformation format(String format) {
        this.format = format;
        return this;
    }

    public String getFormat() {
        return this.format;
    }

    @Override // com.cloudinary.Transformation
    public String generate(Iterable<Map> optionsList) {
        List<String> components = new ArrayList<>();
        for (Map options : optionsList) {
            if (options.size() > 0) {
                components.add(super.generate(options));
            }
        }
        if (this.format != null) {
            components.add(this.format);
        }
        return StringUtils.join(components, DomExceptionUtils.SEPARATOR);
    }

    @Override // com.cloudinary.Transformation
    public String generate(Map options) {
        List<String> eager = new ArrayList<>();
        eager.add(super.generate(options));
        if (this.format != null) {
            eager.add(this.format);
        }
        return StringUtils.join(eager, DomExceptionUtils.SEPARATOR);
    }
}
