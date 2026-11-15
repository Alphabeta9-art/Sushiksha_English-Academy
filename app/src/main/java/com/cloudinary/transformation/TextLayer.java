package com.cloudinary.transformation;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.cloudinary.SmartUrlEncoder;
import com.cloudinary.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public class TextLayer extends AbstractLayer<TextLayer> {
    protected String resourceType = "text";
    protected String fontFamily = null;
    protected Integer fontSize = null;
    protected String fontWeight = null;
    protected String fontStyle = null;
    protected String fontAntialiasing = null;
    protected String fontHinting = null;
    protected String textDecoration = null;
    protected String textAlign = null;
    protected String stroke = null;
    protected String letterSpacing = null;
    protected Integer lineSpacing = null;
    protected String text = null;
    protected Object textStyle = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.cloudinary.transformation.AbstractLayer
    public TextLayer getThis() {
        return this;
    }

    @Override // com.cloudinary.transformation.AbstractLayer
    public TextLayer resourceType(String resourceType) {
        throw new UnsupportedOperationException("Cannot modify resourceType for text layers");
    }

    @Override // com.cloudinary.transformation.AbstractLayer
    public TextLayer type(String type) {
        throw new UnsupportedOperationException("Cannot modify type for text layers");
    }

    @Override // com.cloudinary.transformation.AbstractLayer
    public TextLayer format(String format) {
        throw new UnsupportedOperationException("Cannot modify format for text layers");
    }

    public TextLayer fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return getThis();
    }

    public TextLayer fontAntialiasing(String fontAntialiasing) {
        this.fontAntialiasing = fontAntialiasing;
        return getThis();
    }

    public TextLayer fontHinting(String fontHinting) {
        this.fontHinting = fontHinting;
        return getThis();
    }

    public TextLayer fontSize(int fontSize) {
        this.fontSize = Integer.valueOf(fontSize);
        return getThis();
    }

    public TextLayer fontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return getThis();
    }

    public TextLayer fontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return getThis();
    }

    public TextLayer textDecoration(String textDecoration) {
        this.textDecoration = textDecoration;
        return getThis();
    }

    public TextLayer textAlign(String textAlign) {
        this.textAlign = textAlign;
        return getThis();
    }

    public TextLayer stroke(String stroke) {
        this.stroke = stroke;
        return getThis();
    }

    public TextLayer letterSpacing(String letterSpacing) {
        this.letterSpacing = letterSpacing;
        return getThis();
    }

    public TextLayer letterSpacing(int letterSpacing) {
        this.letterSpacing = String.valueOf(letterSpacing);
        return getThis();
    }

    public TextLayer lineSpacing(Integer lineSpacing) {
        this.lineSpacing = lineSpacing;
        return getThis();
    }

    public TextLayer text(String text) {
        StringBuffer result = new StringBuffer();
        Matcher m = Pattern.compile("\\$\\([a-zA-Z]\\w+\\)").matcher(text);
        int start = 0;
        while (m.find()) {
            String part = text.substring(start, m.start());
            result.append(SmartUrlEncoder.encode(part));
            result.append(m.group());
            start = m.end();
        }
        String part2 = text.substring(start);
        result.append(SmartUrlEncoder.encode(part2));
        this.text = result.toString().replace("%2C", "%252C").replace(DomExceptionUtils.SEPARATOR, "%252F");
        return getThis();
    }

    public TextLayer textStyle(String textStyleIdentifier) {
        this.textStyle = textStyleIdentifier;
        return getThis();
    }

    public TextLayer textStyle(Expression textStyleIdentifier) {
        this.textStyle = textStyleIdentifier;
        return getThis();
    }

    @Override // com.cloudinary.transformation.AbstractLayer
    public String toString() {
        if (this.publicId == null && this.text == null) {
            throw new IllegalArgumentException("Must supply either text or public_id.");
        }
        ArrayList<String> components = new ArrayList<>();
        components.add(this.resourceType);
        String styleIdentifier = textStyleIdentifier();
        if (styleIdentifier != null) {
            components.add(styleIdentifier);
        }
        if (this.publicId != null) {
            components.add(formattedPublicId());
        }
        if (this.text != null) {
            components.add(this.text);
        }
        return StringUtils.join((List<String>) components, ":");
    }

    protected String textStyleIdentifier() {
        if (StringUtils.isNotBlank(this.textStyle)) {
            return this.textStyle.toString();
        }
        ArrayList<String> components = new ArrayList<>();
        if (StringUtils.isNotBlank(this.fontWeight) && !this.fontWeight.equals("normal")) {
            components.add(this.fontWeight);
        }
        if (StringUtils.isNotBlank(this.fontStyle) && !this.fontStyle.equals("normal")) {
            components.add(this.fontStyle);
        }
        if (StringUtils.isNotBlank(this.fontAntialiasing)) {
            components.add("antialias_" + this.fontAntialiasing);
        }
        if (StringUtils.isNotBlank(this.fontHinting)) {
            components.add("hinting_" + this.fontHinting);
        }
        if (StringUtils.isNotBlank(this.textDecoration) && !this.textDecoration.equals("none")) {
            components.add(this.textDecoration);
        }
        if (StringUtils.isNotBlank(this.textAlign)) {
            components.add(this.textAlign);
        }
        if (StringUtils.isNotBlank(this.stroke) && !this.stroke.equals("none")) {
            components.add(this.stroke);
        }
        if (StringUtils.isNotBlank(this.letterSpacing)) {
            components.add("letter_spacing_" + this.letterSpacing);
        }
        if (this.lineSpacing != null) {
            components.add("line_spacing_" + this.lineSpacing.toString());
        }
        if (this.fontFamily == null && this.fontSize == null && components.isEmpty()) {
            return null;
        }
        if (this.fontFamily == null) {
            throw new IllegalArgumentException("Must supply fontFamily.");
        }
        if (this.fontSize == null) {
            throw new IllegalArgumentException("Must supply fontSize.");
        }
        components.add(0, Integer.toString(this.fontSize.intValue()));
        components.add(0, this.fontFamily);
        return StringUtils.join((List<String>) components, "_");
    }
}
